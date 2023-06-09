package vlsu.ispi.instagram.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vlsu.ispi.instagram.dto.AddPostDto;
import vlsu.ispi.instagram.service.GigaService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final GigaService gigaService;

    @PostMapping
    @Secured("ROLE_USER")
    public void post(@RequestBody AddPostDto request, @RequestParam(name = "file") List<MultipartFile> photos) {
        gigaService.addPost(request, photos);
    }
}
