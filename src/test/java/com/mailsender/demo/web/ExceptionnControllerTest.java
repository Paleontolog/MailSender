package com.mailsender.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.exceptions.DatabaseException;
import com.mailsender.demo.mapper.Converter;
import com.mailsender.demo.service.AddresseesService;
import com.mailsender.demo.web.dto.AddressesWebDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionnControllerTest {

    private static final Long USER = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddresseesService addresseesService;

    @Autowired
    private Converter converter;

    @Test
    public void testPostNotOk() throws Exception {
        AddressesWebDTO addresseesWeb = new AddressesWebDTO(1L, "Heretic");
        AddresseesDB addresseesDB = converter.webAddressesToDatabase(addresseesWeb);

        when(addresseesService.updateAddresses(addresseesDB, USER))
                .thenThrow(DatabaseException.class);

        String jsonExpected = new ObjectMapper().writeValueAsString(addresseesWeb);

        mockMvc.perform(post("/api/addresses").content(jsonExpected)
                .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
