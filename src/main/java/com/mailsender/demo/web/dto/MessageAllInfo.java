package com.mailsender.demo.web.dto;

import com.mailsender.demo.web.dto.AddressesWebDTO;
import com.mailsender.demo.web.dto.MessageWebDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageAllInfo {
    private MessageWebDTO message;
    private List<AddressesWebDTO> addresses;
}
