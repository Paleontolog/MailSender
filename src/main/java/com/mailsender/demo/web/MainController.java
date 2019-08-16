package com.mailsender.demo.web;

import com.mailsender.demo.mapper.Converter;
import com.mailsender.demo.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@CrossOrigin(maxAge = 3600)
@Controller
public class MainController {
    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private Converter converter;

    @RequestMapping(value = "/addr", method = RequestMethod.GET)
    public String getListOfAddressees() {
        return "addressees";
    }

    @RequestMapping(value = "/mess", method = RequestMethod.GET)
    public String getMessageForm() { return "messageredactor"; }

    @RequestMapping(value = "/mess/{id}", method = RequestMethod.GET)
    public String getMessageForm(@PathVariable ("id") Long id, Model model) {
        MessageWebDTO message = converter
                .databaseToWebMessage(databaseService.getMessageOnId(id));
        model.addAttribute("subject", message.getSubject());
        model.addAttribute("text", message.getEmail());
        return "messageredactor";
    }

    @RequestMapping(value = "/messlist", method = RequestMethod.GET)
    public String getMessageForm(DigitRange digitRange, Model model) {
        model.addAttribute("lastMessList", databaseService.getMessageNSbj(digitRange.getFrom(),
                digitRange.getCount()));
        return "messlist";
    }

}
