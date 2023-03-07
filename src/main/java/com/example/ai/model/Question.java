package com.example.ai.model;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Data
@AllArgsConstructor
public class Question {
    private String text;
    private List<Option> options;
    private Integer result;

    public Question(String text, List<Option> options) {
        this.text = text;
        this.options = options;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
