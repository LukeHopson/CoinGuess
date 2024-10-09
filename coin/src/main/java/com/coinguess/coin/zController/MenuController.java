package com.coinguess.coin.zController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;

import com.coinguess.coin.Objects.Answer;
import com.coinguess.coin.Objects.Coin;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("shownCoin")
public class MenuController {

    @GetMapping("/")
    public String menuForm(Model model) {
        // TODO: Generate random coin each time from the csv instead of this test one
        Coin shownCoin = new Coin("Australia", "https://en.numista.com/catalogue/photos/australie/9067-original.jpg", "https://en.numista.com/catalogue/photos/australie/9066-original.jpg", "");
        model.addAttribute("shownCoin", shownCoin);
        model.addAttribute("answer", new Answer(""));
        return "menu"; 
    }

    @PostMapping("/")
    public String menuSubmit(@ModelAttribute Answer answer, Model model, HttpSession session) {
        Coin shownCoin = (Coin) session.getAttribute("shownCoin");
        if (shownCoin == null) {
            return "redirect:/";
        }

        // For some reason the answer has a comma at the end so we need to remove the last char, otherwise it won't match up!
        String cutAnswerComma = answer.getAnswer();
        cutAnswerComma = cutAnswerComma.substring(0, cutAnswerComma.length() - 1);
        answer.setAnswer(cutAnswerComma); 
        
        model.addAttribute("answer", answer);
        
        if (shownCoin.getCountry().equals(answer.answer)) {
            return "correct";
        } else {
            return "wrong";
        }
    }

    
}