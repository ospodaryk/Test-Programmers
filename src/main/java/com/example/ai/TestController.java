package com.example.ai;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    private User useer = new User("Taras");
    private List<Question> p1questions = Arrays.asList(
            new Question("Переживаєте за успіх в роботі?", Arrays.asList(
                    new Option("сильно", 5),
                    new Option("не дуже", 3),
                    new Option("спокійний", 2)
            )),
            new Question("Прагнете досягти швидко результату?", Arrays.asList(
                    new Option("поступово", 2),
                    new Option("якомога швидше", 3),
                    new Option("дуже", 5)
            )),
            new Question("Легко попадаєте в тупик при проблемах в роботі?", Arrays.asList(
                    new Option("неодмінно", 5),
                    new Option("поступово", 3),
                    new Option("зрідка", 2)
            )),
            new Question("Чи   потрібен чіткий алгоритм для вирішення задач?", Arrays.asList(
                    new Option("так", 5),
                    new Option("окремих випадках", 3),
                    new Option("не потрібен", 2)
            ))
    );
    private List<Question> p2questions = Arrays.asList(
            new Question("Чи використовуєте власний досвід при вирішенні задач?(зрідка(5), частково(3), ні(2)", Arrays.asList(
                    new Option("зрідка", 5),
                    new Option("частково", 3),
                    new Option("ні", 2)
            )),
            new Question("Чи користуєтесь фіксованими правилами  для вирішення задач?", Arrays.asList(
                    new Option("так", 2),
                    new Option("в окремих випадках", 3),
                    new Option("не потрібні", 5)
            )),
            new Question("Чи відчуваєте ви загальний контекст вирішення задачі?", Arrays.asList(
                    new Option("в окремих випадках", 5),
                    new Option("частково", 3),
                    new Option("так", 2)
            ))
    );

    @GetMapping("/test1")
    public String getTest(Model model) {
        model.addAttribute("questions", p1questions);
        return "test_p1";
    }

    @PostMapping("/results1")
    public String getResults(@RequestParam Map<String, String> answers, Model model) {
        findResult(answers, model, 0);
        return "results_p1";
    }

    @GetMapping("/test2")
    public String getTest2(Model model) {
        model.addAttribute("questions", p2questions);
        return "test_p2";
    }

    @PostMapping("/results2")
    public String getResults2(@RequestParam Map<String, String> answers, Model model) {
        findResult(answers, model, 1);
        return "results_p2";
    }

    private void findResult(Map<String, String> answers, Model model, int part) {
        int score = 0;
        for (Map.Entry<String, String> entry : answers.entrySet()) {
            score += Integer.parseInt(entry.getValue());
        }
        try {
            useer.getMyMarks().set(part, score);
        } catch (Exception e) {
            useer.addMark(score);
        }

        model.addAttribute("user", useer);
    }

    public void findResult() {

    }
}
