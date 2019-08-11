package com.mailsender.demo.database.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
//@Document(collection = "Addressees")
@AllArgsConstructor
@NoArgsConstructor
public class AddresseesDB {
    @Id
    private Long id;
  //  @Field("email")
    private String email;
}

