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

// To read csv file
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Controller
@SessionAttributes("shownCoin")
public class MenuController {

    @GetMapping("/")
    public String menuForm(Model model) {
        List<Coin> coinList = loadCoinsFromCSV("static/2003.csv");
        Coin shownCoin = getRandomCoin(coinList);
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
        
        if (shownCoin.getIssuer().equals(answer.answer)) {
            return "correct";
        } else {
            return "wrong";
        }
    }

    private List<Coin> loadCoinsFromCSV(String filePath){
        List<Coin> coinList = new ArrayList<>();
        String line = "";
        boolean firstLineRead = false;
        try { // Gotta read arabic characters sometimes to give credit, so need UTF-8
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            while ((line = reader.readLine()) != null){
                // Need to skip the header line, it cannot be used to create a coin object!
                if (!firstLineRead){
                    firstLineRead = true;
                    continue;
                }
                String[] data = line.split(","); // Split line on commas
                // Create coin object added to ArrayList with read data
                int id = Integer.parseInt(data[0]);
                coinList.add(new Coin(id, data[1], data[2], data[3], data[4], data[5], data[6], data[7]));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coinList;
    }

    private Coin getRandomCoin(List<Coin> coinList){
        if (coinList.isEmpty()) {
            return null; 
        }
        // Generate a random int within the size of the coinList
        Random rand = new Random();
        int random = rand.nextInt(coinList.size());
        // Use that random number as the index to get a coin from the list
        Coin randomCoin = coinList.get(random);
        return randomCoin;
    }
    
}