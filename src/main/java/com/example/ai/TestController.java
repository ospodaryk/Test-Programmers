package com.example.ai;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    private List<Question> questions = Arrays.asList(
            new Question("What is the capital of France?", Arrays.asList(
                    new Option("Paris", 10),
                    new Option("London", 0),
                    new Option("Berlin", 0)
            )),
            new Question("What is the tallest mountain in the world?", Arrays.asList(
                    new Option("Mount Everest", 10),
                    new Option("K2", 0),
                    new Option("Makalu", 0)
            )),
            new Question("What is the largest country by area?", Arrays.asList(
                    new Option("Russia", 10),
                    new Option("Canada", 0),
                    new Option("China", 0)
            ))
    );

    @GetMapping("/test")
    public String getTest(Model model) {
        model.addAttribute("questions", questions);
        return "test";
    }

    @PostMapping("/results")
    public String getResults(@RequestParam Map<String,String> answers, Model model) {
        int score = 0;
        for (Map.Entry<String, String> entry : answers.entrySet()) {
            score += Integer.parseInt(entry.getValue());
        }
        model.addAttribute("score", score);
        return "results";
    }
}
