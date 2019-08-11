package com.mailsender.demo.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageWebDTO {
    private Long id;
    private String subject;
    private String email;
}


