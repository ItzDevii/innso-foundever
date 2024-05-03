package Innso.innso.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClientCaseDTO {
    private String clientName;
    private List<Long> messageIds;
}