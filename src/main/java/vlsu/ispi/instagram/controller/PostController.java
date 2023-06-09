package vlsu.ispi.instagram.controller;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import liquibase.pro.packaged.S;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vlsu.ispi.instagram.dto.AddPostDto;
import vlsu.ispi.instagram.dto.FeedDto;
import vlsu.ispi.instagram.dto.ReadPostDto;
import vlsu.ispi.instagram.service.GigaService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final GigaService gigaService;

    @PostMapping
    @Secured("ROLE_USER")
    public void post(@RequestParam(name = "caption") String caption, @RequestParam(name = "file") List<MultipartFile> photos) {
        gigaService.addPost(caption, photos);
    }

    @GetMapping("/{externalId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ReadPostDto readPost(@PathVariable(name = "externalId") UUID postExternalId) {
        return gigaService.readPost(postExternalId);
    }

    @PutMapping("/like/{externalId}")
    @Secured("ROLE_USER")
    public void like(@PathVariable(name = "externalId") UUID externalId) {
        gigaService.likePost(externalId);
    }

    @GetMapping
    @Secured("ROLE_USER")
    public FeedDto getFeed() {
        return gigaService.getFeed();
    }
}
