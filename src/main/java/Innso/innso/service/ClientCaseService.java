package Innso.innso.service;

import Innso.innso.dto.ClientCaseDTO;
import Innso.innso.entity.ClientCase;
import Innso.innso.entity.Message;
import Innso.innso.repository.ClientCaseRepository;
import Innso.innso.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClientCaseService {
    private final ClientCaseRepository clientCaseRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public ClientCaseService(ClientCaseRepository clientCaseRepository, MessageRepository messageRepository){
        this.clientCaseRepository = clientCaseRepository;
        this.messageRepository = messageRepository;
    }

    public List<ClientCase> getAllClientCases(){
        return clientCaseRepository.findAll();
    }

    public ClientCase createClientCase(ClientCaseDTO clientCaseDTO){
        ClientCase clientCase = new ClientCase();
        clientCase.setClientName(clientCaseDTO.getClientName());
        List<Message> messages = clientCaseDTO.getMessageIds().stream()
                .map(messageId -> messageRepository.findById(messageId)
                        .orElseThrow(()-> new RuntimeException("Message not found with the ID: " + messageId)))
                .toList();
        clientCase.setMessages(messages);

        for (Message message : messages){
            message.setClientCase(clientCase);
        }
        return clientCaseRepository.save(clientCase);
    }

    public ClientCase updateClientCaseReference(Long clientCaseId, String clientCaseReference){
        ClientCase clientCase = clientCaseRepository.findById(clientCaseId)
                .orElseThrow(()-> new RuntimeException("Client case not found with the ID: " +clientCaseId));

        clientCase.setClientReference(clientCaseReference);
        return clientCaseRepository.save(clientCase);
    }

    public void deleteClientCaseById(Long clientCaseId){
        clientCaseRepository.deleteById(clientCaseId);
    }

}