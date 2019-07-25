package com.mailsender.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.mapper.Converter;
import com.mailsender.demo.service.DatabaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DatabaseService databaseService;

    @Autowired
    private Converter converter;

    @Test
    public void testGet() throws Exception {
        List<AddresseesDB> addresseesDBS = Arrays.asList
                (new AddresseesDB(1L, "Heretic"),
                        new AddresseesDB(2L, "Horus"),
                        new AddresseesDB(3L, "Magnus"));

        when(databaseService.getListOfAddresses())
                .thenReturn(addresseesDBS);

        String jsonExpected = new ObjectMapper().writeValueAsString(addresseesDBS);

        mockMvc.perform(get("/api/addresses"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(AddressesController.class))
                .andExpect(handler().methodName("getListOfAddressees"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(jsonExpected));

    }

    @Test
    public void testAdd() throws Exception {
        AddressesWebDTO addresseesDBS = new AddressesWebDTO(1L, "Heretic");
        String jsonExpected = new ObjectMapper().writeValueAsString(addresseesDBS);

        mockMvc.perform(put("/api/addresses")
                .content(jsonExpected)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(handler().handlerType(AddressesController.class))
                .andExpect(handler().methodName("add"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testPostOk() throws Exception {
        AddressesWebDTO addresseesDBS = new AddressesWebDTO(1L, "Heretic");
        String jsonExpected = new ObjectMapper().writeValueAsString(addresseesDBS);

        mockMvc.perform(post("/api/addresses")
                .content(jsonExpected)
                .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(handler().handlerType(AddressesController.class))
                .andExpect(handler().methodName("update"))
                .andExpect(status().isOk());
    }
}
