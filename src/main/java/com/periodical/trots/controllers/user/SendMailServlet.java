package com.periodical.trots.controllers.user;

import com.periodical.trots.utils.Mailer;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;

@Controller
public class SendMailServlet {

    @GetMapping("/send-message")
    public String sendMessageGet() {
        return "SendMailPage";
    }

    @PostMapping("/send-message")
    public String sendMessagePost(RedirectAttributes redirectAttributes, @RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("message") String message) {
        try {
            Mailer.send(to, subject, message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        String lang = String.valueOf(LocaleContextHolder.getLocale());
        if (lang.equals("en_US") || lang.equals("en")) {
            redirectAttributes.addFlashAttribute("ex", "Message sent successfully");
        }else{
            redirectAttributes.addFlashAttribute("ex", "Лист успішно надіслано");
        }
        return "redirect:/send-message";
    }


}
