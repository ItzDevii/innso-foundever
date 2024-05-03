package Innso.innso.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client_case")
@Entity
public class ClientCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_reference")
    private String clientReference;

    @OneToMany(mappedBy = "clientCase", cascade = CascadeType.ALL)
    private List<Message> messages;
}