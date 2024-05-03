package Innso.innso.controller;

import Innso.innso.dto.MessageDTO;
import Innso.innso.entity.Message;
import Innso.innso.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody MessageDTO messageDTO){
        Message createdMessage = messageService.createMessage(messageDTO);
        return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
    }

    @PatchMapping("/{clientCaseId}")
    public ResponseEntity<Message> addMessageToClientCase(@PathVariable Long clientCaseId, @RequestBody MessageDTO messageDTO){
        Message createdMessage = messageService.addMessageToClientCase(messageDTO.getAuthor(), messageDTO.getContent(), clientCaseId);
        return ResponseEntity.ok(createdMessage);
    }
}