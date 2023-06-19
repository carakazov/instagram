package vlsu.ispi.instagram.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import liquibase.pro.packaged.M;
import liquibase.pro.packaged.S;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.aspectj.bridge.MessageWriter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vlsu.ispi.instagram.dto.*;
import vlsu.ispi.instagram.dto.auth.ReadPostPhotoDto;
import vlsu.ispi.instagram.model.*;
import vlsu.ispi.instagram.repository.*;
import vlsu.ispi.instagram.service.GigaService;
import vlsu.ispi.instagram.utils.SecurityContextHelper;
import vlsu.ispi.instagram.utils.exception.ExceptionCode;
import vlsu.ispi.instagram.utils.exception.GigaException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class GigaServiceImpl implements GigaService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final PhotoRepository photoRepository;
    private final PostRepository postRepository;
    private final MessageRepository messageRepository;

    @Override
    public UUID register(RegistrationDto request) {
        if(userRepository.existsByLogin(request.getLogin())) {
            throw new GigaException(ExceptionCode.WRONG_LOGIN);
        }
        RoleEntity role = roleRepository.findByRole(Role.ROLE_USER);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        UserEntity user = new UserEntity()
            .setLogin(request.getLogin())
            .setPassword(encodedPassword)
            .setName(request.getName())
            .setSurname(request.getSurname())
            .setMiddleName(request.getMiddleName())
            .setBirthdate(request.getBirthdate())
            .setExternalId(UUID.randomUUID())
            .setAccessStatus(AccessStatus.FULL)
            .setRole(role);
        UserEntity savedUser = userRepository.save(user);
        return savedUser.getExternalId();
    }

    @Override
    public ProfileDto getProfile(UUID externalId) {
        UserEntity user = userRepository.findByExternalId(externalId);
        List<PostSimpleDto> simplePosts = new ArrayList<>();
        List<PostEntity> posts = postRepository.findAllByAuthor(user);

        String login = SecurityContextHelper.getCurrentUserLogin();
        UserEntity currentUser = userRepository.findByLogin(login);

        for(PostEntity post : posts) {
            List<PhotoEntity> photos = photoRepository.findAllByPost(post);
            PostSimpleDto simplePost = new PostSimpleDto();
            ReadPostPhotoDto cover = new ReadPostPhotoDto();
            cover.setExternalId(photos.get(0).getExternalId());
            cover.setPhotoBase64(new String(photos.get(0).getPhotoBase64()));
            simplePost.setCover(cover);
            simplePost.setExternalId(post.getExternalId());
            simplePost.setPostingTime(post.getPostingTime());
            simplePosts.add(simplePost);
        }

        Comparator<PostSimpleDto> comparator = (p1, p2) -> p2.getPostingTime().compareTo(p1.getPostingTime());
        simplePosts.sort(comparator);

        return new ProfileDto()
            .setName(user.getName())
            .setSurname(user.getSurname())
            .setMiddleName(user.getMiddleName())
            .setBirthdate(user.getBirthdate())
            .setExternalId(user.getExternalId())
            .setSimplePosts(simplePosts)
            .setIsRequesterBlacklisted(user.getBlacklistedUsers().contains(currentUser))
                .setIsUserBlacklistedByRequester(currentUser.getBlacklistedUsers().contains(user));
    }

    @Override
    public void addPost(String caption, List<MultipartFile> photos) {
        String currentLogin = SecurityContextHelper.getCurrentUserLogin();
        UserEntity user = userRepository.findByLogin(currentLogin);
        PostEntity post = new PostEntity();
        post.setText(caption);
        post.setLikes(0L);
        post.setPostingTime(LocalDateTime.now());
        post.setAuthor(user);
        post.setExternalId(UUID.randomUUID());

        post = postRepository.save(post);


        try {
            for(MultipartFile uploadedPhoto : photos) {
                PhotoEntity photo = new PhotoEntity();
                photo.setExternalId(UUID.randomUUID());
                photo.setPost(post);
                String base64 = Base64.getEncoder().encodeToString(uploadedPhoto.getBytes());
                photo.setPhotoBase64(base64.getBytes());
                photoRepository.save(photo);
            }
        } catch(IOException exception) {
            log.error("Exception: ", exception);
            throw new GigaException(ExceptionCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public ReadPostDto readPost(UUID postExternalId) {
        PostEntity post = postRepository.findByExternalId(postExternalId);
        List<PhotoEntity> photos = photoRepository.findAllByPost(post);

        String login = SecurityContextHelper.getCurrentUserLogin();
        UserEntity user = userRepository.findByLogin(login);

        ReadPostAuthorDto author = new ReadPostAuthorDto();
        author.setName(post.getAuthor().getName());
        author.setExternalId(post.getAuthor().getExternalId());

        ReadPostDto readPostDto = new ReadPostDto();
        readPostDto.setCaption(post.getText());
        readPostDto.setAuthor(author);
        readPostDto.setPostingTime(post.getPostingTime());
        readPostDto.setLikes(post.getLikes());
        readPostDto.setExternalId(post.getExternalId());
        readPostDto.setLikedByRequester(post.getLikedUsers().contains(user));
        readPostDto.setPhotosBase64(new ArrayList<>());

        for(PhotoEntity photo : photos) {
            ReadPostPhotoDto returnedPhoto = new ReadPostPhotoDto();
            String base64Photo = new String(photo.getPhotoBase64());
            returnedPhoto.setPhotoBase64(base64Photo);
            returnedPhoto.setExternalId(photo.getExternalId());
            readPostDto.getPhotosBase64().add(returnedPhoto);
        }

        return readPostDto;
    }

    @Override
    public void likePost(UUID postExternalId) {
        PostEntity post = postRepository.findByExternalId(postExternalId);
        long likes = post.getLikes();
        post.setLikes(++likes);
        String login = SecurityContextHelper.getCurrentUserLogin();
        UserEntity user = userRepository.findByLogin(login);
        post.getLikedUsers().add(user);
    }

    @Override
    public void sendMessage(SendMessageDto request) {
        String login = SecurityContextHelper.getCurrentUserLogin();
        UserEntity sender = userRepository.findByLogin(login);
        UserEntity receiver = userRepository.findByExternalId(request.getReceiverId());

        MessageEntity message = new MessageEntity();
        message.setText(request.getText());
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setExternalId(UUID.randomUUID());
        message.setSendingTime(LocalDateTime.now());

        messageRepository.save(message);
    }

    @Override
    public DialogDto getDialog(UUID externalId) {
        String login = SecurityContextHelper.getCurrentUserLogin();
        UserEntity currentUser = userRepository.findByLogin(login);
        UserEntity anotherUser = userRepository.findByExternalId(externalId);

        List<MessageEntity> messages = new ArrayList<>();
        List<MessageEntity> firstList = messageRepository.findAllBySenderAndReceiver(currentUser, anotherUser);
        List<MessageEntity> secondList = messageRepository.findAllBySenderAndReceiver(anotherUser, currentUser);
        messages.addAll(firstList);
        messages.addAll(secondList);


        DialogDto dialog = new DialogDto();
        dialog.setMessages(new ArrayList<>());

        for(MessageEntity message : messages) {
            MessageAuthorDto author = new MessageAuthorDto();
            author.setName(message.getSender().getName());
            author.setSurname(message.getSender().getSurname());
            author.setExternalId(message.getSender().getExternalId());

            ReadMessageDto readMessage = new ReadMessageDto();
            readMessage.setText(message.getText());
            readMessage.setSendingTime(message.getSendingTime());
            readMessage.setExternalId(message.getExternalId());
            readMessage.setAuthor(author);
            dialog.getMessages().add(readMessage);
        }

        Comparator<ReadMessageDto> comparator = Comparator.comparing(ReadMessageDto::getSendingTime);
        dialog.getMessages().sort(comparator);

        return dialog;
    }

    @Override
    public void blackList(UUID externalId) {
        String login = SecurityContextHelper.getCurrentUserLogin();
        UserEntity currentUser = userRepository.findByLogin(login);

        UserEntity userToBlackList = userRepository.findByExternalId(externalId);

        if(currentUser.getBlacklistedUsers().contains(userToBlackList)) {
            currentUser.getBlacklistedUsers().remove(userToBlackList);
        } else {
            currentUser.getBlacklistedUsers().add(userToBlackList);
        }
    }

    @Override
    public void changeAccess(UUID externalId, AccessStatus accessStatus) {
        UserEntity user = userRepository.findByExternalId(externalId);
        user.setAccessStatus(accessStatus);
    }

    @Override
    public UserListResponseDto getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        UserListResponseDto response = new UserListResponseDto();
        response.setUsers(new ArrayList<>());

        for(UserEntity user : users) {
            UserDto userDto = new UserDto();
            userDto.setExternalId(user.getExternalId());
            userDto.setName(user.getName());
            userDto.setSurname(user.getSurname());
            userDto.setMiddleName(user.getMiddleName());
            userDto.setAccess(user.getAccessStatus().toString());
            response.getUsers().add(userDto);
        }

        return response;
    }

    @Override
    public FeedDto getFeed() {
        FeedDto feed = new FeedDto();
        feed.setFeed(new ArrayList<>());
        List<PostEntity> allPosts = postRepository.findAll();
        Comparator<PostEntity> comparator = (p1, p2) -> p2.getPostingTime().compareTo(p1.getPostingTime());
        allPosts.sort(comparator);
        for(PostEntity post : allPosts) {
            List<PhotoEntity> photos = photoRepository.findAllByPost(post);
            PhotoEntity photo = photos.get(0);
            ReadPostPhotoDto readPostPhoto = new ReadPostPhotoDto();
            readPostPhoto.setExternalId(photo.getExternalId());
            readPostPhoto.setPhotoBase64(new String(photo.getPhotoBase64()));
            PostSimpleDto simplePost = new PostSimpleDto();
            simplePost.setExternalId(post.getExternalId());
            simplePost.setCover(readPostPhoto);
            simplePost.setPostingTime(post.getPostingTime());
            feed.getFeed().add(simplePost);
        }

        return feed;
    }
}



























