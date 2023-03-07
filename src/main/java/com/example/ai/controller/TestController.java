package com.example.ai.controller;

import com.example.ai.model.Option;
import com.example.ai.model.Question;
import com.example.ai.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    private User useer = new User("Taras");
    private List<Question> p1questions = Arrays.asList(new Question("Переживаєте за успіх в роботі", Arrays.asList(new Option("сильно", 5), new Option("не дуже", 3), new Option("спокійний", 2))), new Question("Прагнете досягти швидко результату", Arrays.asList(new Option("поступово", 2), new Option("якомога швидше", 3), new Option("дуже", 5))), new Question("Легко попадаєте в тупик при проблемах в роботі", Arrays.asList(new Option("неодмінно", 5), new Option("поступово", 3), new Option("зрідка", 2))), new Question("Чи   потрібен чіткий алгоритм для вирішення задач", Arrays.asList(new Option("так", 5), new Option("окремих випадках", 3), new Option("не потрібен", 2))));
    private List<Question> p2questions = Arrays.asList(new Question("Чи використовуєте власний досвід при вирішенні задач", Arrays.asList(new Option("зрідка", 5), new Option("частково", 3), new Option("ні", 2))), new Question("Чи користуєтесь фіксованими правилами  для вирішення задач", Arrays.asList(new Option("так", 2), new Option("в окремих випадках", 3), new Option("не потрібні", 5))), new Question("Чи відчуваєте ви загальний контекст вирішення задачі", Arrays.asList(new Option("в окремих випадках", 5), new Option("частково", 3), new Option("так", 2))));
    private List<Question> p3questions = Arrays.asList(new Question("Чи можете ви побудувати модель вирішуваної задачі", Arrays.asList(new Option("так", 5), new Option("не повністю", 3), new Option("в окремих випадках", 2))), new Question("Чи вистачає вам ініціативи при вирішенні задач", Arrays.asList(new Option("так", 5), new Option("зрідка", 3), new Option("потрібне натхнення", 2))), new Question("Чи можете вирішувати проблеми, з якими ще не стикались", Arrays.asList(new Option("в окремих випадках", 3), new Option("ні", 5), new Option("так", 2))));
    private List<Question> p4questions = Arrays.asList(new Question("Чи  необхідний вам весь контекст задачі", Arrays.asList(new Option("так", 5), new Option("в загальному", 2), new Option("в окремих випадках", 3))), new Question("Чи переглядаєте ви свої наміри до вирішення задачі", Arrays.asList(new Option("так", 5), new Option("зрідка", 3), new Option("коли є потреба", 2))), new Question("Чи здатні  ви  навчатись у інших", Arrays.asList(new Option("коли є потреба", 2), new Option("зрідка", 3), new Option("так", 5))));
    private List<Question> p5questions = Arrays.asList(new Question("Чи обираєте ви нові методи своєї роботи", Arrays.asList(new Option("так", 5), new Option("вибірково", 3), new Option("при емоційному напруженні", 2))), new Question("Чи допомагає власна інтуїція при вирішенні задач", Arrays.asList(new Option("так", 5), new Option("зрідка", 3), new Option("коли є потреба", 2))), new Question("Чи застовуєте рішення задач за аналогією", Arrays.asList(new Option("часто", 5), new Option("зрідка", 3), new Option("тільки власний варіант", 2))));

    @GetMapping("/novice")
    public String getTest(Model model) {
        model.addAttribute("questions", p1questions);
        return "test_novice";
    }

    @PostMapping("/novice/results")
    public String getResults(@RequestParam Map<String, String> answers, Model model) {
        findResult(answers, model, 0, p1questions);


        return "results_novice";
    }

    @GetMapping("/beginner")
    public String getTest2(Model model) {
        model.addAttribute("questions", p2questions);
        return "test_beginner";
    }

    @PostMapping("/beginner/results")
    public String getResults2(@RequestParam Map<String, String> answers, Model model) {
        findResult(answers, model, 1, p2questions);
        return "results_beginner";
    }

    @GetMapping("/competent")
    public String getTest3(Model model) {
        model.addAttribute("questions", p3questions);
        return "test_competent";
    }

    @PostMapping("/competent/results")
    public String getResults3(@RequestParam Map<String, String> answers, Model model) {
        findResult(answers, model, 2, p3questions);
        return "results_competent";
    }


    @GetMapping("/proficient")
    public String getTest4(Model model) {
        model.addAttribute("questions", p4questions);
        return "test_proficient";
    }

    @PostMapping("/proficient/results")
    public String getResults4(@RequestParam Map<String, String> answers, Model model) {
        findResult(answers, model, 3, p4questions);
        return "results_proficient";
    }

    @GetMapping("/expert")
    public String getTest5(Model model) {
        model.addAttribute("questions", p5questions);
        return "test_expert";
    }

    @PostMapping("/expert/results")
    public String getResults5(@RequestParam Map<String, String> answers, Model model) {
        findResult(answers, model, 4, p5questions);
        return "results_expert";
    }

    private void findResult(Map<String, String> answers, Model model, int part, List<Question> list) {
        int score = 0;
        int i = 0;
        for (Map.Entry<String, String> entry : answers.entrySet()) {
            score += Integer.parseInt(entry.getValue());
            list.get(i).setResult(Integer.parseInt(entry.getValue()));
            i++;
        }
        try {
            useer.getMyMarks().set(part, score);
        } catch (Exception e) {
            useer.addMark(score);
        }
        model.addAttribute("questions", list);
        model.addAttribute("user", useer);
    }

}
