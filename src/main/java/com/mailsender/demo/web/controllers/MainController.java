package com.mailsender.demo.web.controllers;

import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.mapper.Converter;
import com.mailsender.demo.registration.service.UserDetailServiceImp;
import com.mailsender.demo.registration.service.UserService;
import com.mailsender.demo.registration.validation.UserValidator;
import com.mailsender.demo.service.AddresseesService;
import com.mailsender.demo.service.MailService;
import com.mailsender.demo.web.dto.DigitRange;
import com.mailsender.demo.web.dto.MessageWebDTO;
import com.mailsender.demo.web.dto.WebUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(maxAge = 3600)
@Controller
public class MainController {
    @Autowired
    private AddresseesService addresseesService;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;
    @Autowired
    private Converter converter;
    @Autowired
    private UserValidator validator;

    @RequestMapping(value = "/addr", method = RequestMethod.GET)
    public String getListOfAddressees(Model model, Principal principal) {
        model.addAttribute("userName",principal.getName());
        return "addressees";
    }

    @RequestMapping(value = "/mess", method = RequestMethod.GET)
    public String getMessageForm(Model model, Principal principal) {
        model.addAttribute("userName", principal.getName());
        return "messageredactor";
    }

    @RequestMapping(value = "/mess/{id}", method = RequestMethod.GET)
    public String getMessageForm(@PathVariable ("id") Long id, Model model, Principal principal) {
        MessageWebDTO message = converter
                .databaseToWebMessage(mailService.getMessageOnId(id, principal));
        model.addAttribute("userName", principal.getName());
//        model.addAttribute("emList", addresseesService.getAddresseesOnMessage(id, principal).stream()
//                                    .map(AddresseesDB::getEmail).collect(Collectors.toList()));
        model.addAttribute("subject", message.getSubject());
        model.addAttribute("text", message.getEmail());
        return "messageredactor";
    }

    @RequestMapping(value = "/messlist", method = RequestMethod.GET)
    public String getMessageForm(DigitRange digitRange, Model model, Principal principal) {
        final int len = 30;
        model.addAttribute("lastMessList", mailService.getMessageNSbj(digitRange.getFrom(),
                digitRange.getCount(), len, principal));
        model.addAttribute("userName", principal.getName());
        return "messlist";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") WebUser user, Model model, BindingResult result) {
        validator.validate(user, result);
       if (result.hasErrors()) {
            return "registration";
        }
        try {
            userService.saveUser(converter.webToDBUser(user));
        } catch (Exception e) {
            log.error(e.getMessage());
            return "registration";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new WebUser());
        return "registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        return "login";
    }

}
