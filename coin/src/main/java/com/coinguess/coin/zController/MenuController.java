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
    public String menuForm(Model model, HttpSession session) {
        List<Coin> coinList = loadCoinsFromCSV("static/2003.csv"); // This line is specific to my docker build, might need to be change if running local.
        Coin shownCoin = getRandomCoin(coinList);
        model.addAttribute("shownCoin", shownCoin);
        model.addAttribute("answer", new Answer(""));
        return "menu"; 
    }

    @PostMapping("/")
    public String menuSubmit(@ModelAttribute Answer answer, Model model, HttpSession session) {
        try {
            Coin shownCoin = (Coin) model.getAttribute("shownCoin");
    
            if (shownCoin == null) {
                // Log the error or handle the redirection to avoid issues with missing data
                return "redirect:/"; // Redirect to the main menu if the coin is null
            }
    
            // Process the answer (removing the last comma)
            String cutAnswerComma = answer.getAnswer();
            if (cutAnswerComma != null && !cutAnswerComma.isEmpty()) {
                try {
                    cutAnswerComma = cutAnswerComma.substring(0, cutAnswerComma.length() - 1);
                    answer.setAnswer(cutAnswerComma);
                } catch (Exception e) {
                    // Handle any potential StringIndexOutOfBoundsException or other issues
                    System.out.println("Error processing the answer string: " + e.getMessage());
                    return "error"; // Return an error view if there's an issue
                }
            } else {
                // Handle the case where the answer is null or empty
                System.out.println("Answer is empty or null, cannot process.");
                return "error"; // Redirect to error page or return an error view
            }
    
            model.addAttribute("answer", answer);
    
            // Compare the answer with the coin's issuer
            try {
                if (shownCoin.getIssuer().equals(answer.getAnswer())) {
                    return "correct"; // If correct, navigate to the correct page
                } else {
                    return "wrong"; // If incorrect, navigate to the wrong page
                }
            } catch (NullPointerException e) {
                // Handle the case where either `shownCoin.getIssuer()` or `answer.getAnswer()` is null
                System.out.println("Error comparing issuer and answer: " + e.getMessage());
                return "error"; // Return error view if comparison fails
            }
    
        } catch (Exception e) {
            // General exception handling in case something unexpected happens
            System.out.println("Unexpected error occurred: " + e.getMessage());
            return "error"; // Return error view if an unexpected exception occurs
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