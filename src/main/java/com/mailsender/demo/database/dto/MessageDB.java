package com.mailsender.demo.database.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
//@Document(collection = "Addressees")
@AllArgsConstructor
@NoArgsConstructor
public class MessageDB {
    @Id
    private Long id;
    private String subject;
    private String email;
}

