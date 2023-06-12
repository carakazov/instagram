package vlsu.ispi.instagram.controller;

import java.util.UUID;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import vlsu.ispi.instagram.dto.DialogDto;
import vlsu.ispi.instagram.dto.SendMessageDto;
import vlsu.ispi.instagram.service.GigaService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {
    private final GigaService gigaService;

    @PostMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void sendMessage(@RequestBody SendMessageDto request) {
        gigaService.sendMessage(request);
    }

    @GetMapping("/{externalId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public DialogDto readDialog(@PathVariable(name = "externalid")UUID externalId) {
        return gigaService.getDialog(externalId);
    }

}
