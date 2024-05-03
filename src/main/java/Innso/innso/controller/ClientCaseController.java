package Innso.innso.controller;

import Innso.innso.dto.ClientCaseDTO;
import Innso.innso.entity.ClientCase;
import Innso.innso.service.ClientCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client-cases")
public class ClientCaseController {
    private final ClientCaseService clientCaseService;

    @Autowired
    public ClientCaseController(ClientCaseService clientCaseService){
        this.clientCaseService = clientCaseService;
    }

    @GetMapping
    public ResponseEntity<List<ClientCase>> getAllClientCases(){
        List<ClientCase> clientCases = clientCaseService.getAllClientCases();
        return ResponseEntity.ok(clientCases);
    }

    @PostMapping
    public ResponseEntity<ClientCase> createClientCase(@RequestBody ClientCaseDTO clientCaseDTO){
        ClientCase createdClientCase = clientCaseService.createClientCase(clientCaseDTO);
        return new ResponseEntity<>(createdClientCase, HttpStatus.CREATED);
    }

    @PutMapping("/{clientCaseId}")
    public ResponseEntity<ClientCase> updateClientReference(@PathVariable Long clientCaseId,
                                                            @RequestBody String clientCaseReference){
        ClientCase updatedClientCase = clientCaseService.updateClientCaseReference(clientCaseId, clientCaseReference);
        return ResponseEntity.ok(updatedClientCase);
    }

    @DeleteMapping("/{clientCaseId}")
    public ResponseEntity<Void> deleteClientCase(@PathVariable Long clientCaseId){
        clientCaseService.deleteClientCaseById(clientCaseId);
        return ResponseEntity.noContent().build();
    }

}