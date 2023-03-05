package com.example.ai;

import java.util.List;

public class Question {
    private String text;
    private List<Option> options;

    public Question(String text, List<Option> options) {
        this.text = text;
        this.options = options;
    }

    public String getText() {
        return text;
    }

    public List<Option> getOptions() {
        return options;
    }
}
