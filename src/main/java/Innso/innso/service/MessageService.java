package Innso.innso.service;

import Innso.innso.dto.MessageDTO;
import Innso.innso.entity.ClientCase;
import Innso.innso.entity.Message;
import Innso.innso.repository.ClientCaseRepository;
import Innso.innso.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;
    private final ClientCaseRepository clientCaseRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, ClientCaseRepository clientCaseRepository){
        this.messageRepository = messageRepository;
        this.clientCaseRepository = clientCaseRepository;
    }

    public Message createMessage(MessageDTO messageDTO){
        Message message = new Message();
        message.setAuthor(messageDTO.getAuthor());
        message.setContent(messageDTO.getContent());
        return messageRepository.save(message);
    }

    public Message addMessageToClientCase(String author, String content, Long clientCaseId){
        ClientCase clientCase = clientCaseRepository.findById(clientCaseId)
                .orElseThrow(()-> new RuntimeException("Client Case Not Found With The ID: " + clientCaseId));

        Message message = new Message();
        message.setAuthor(author);
        message.setContent(content);

        message.setClientCase(clientCase);
        return messageRepository.save(message);
    }
}