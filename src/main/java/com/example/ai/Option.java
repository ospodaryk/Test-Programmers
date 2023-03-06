package com.example.ai;

public class Option {
    private String text;
    private int grade;

    public Option(String text, int grade) {
        this.text = text;
        this.grade = grade;
    }

    public String getText() {
        return text;
    }

    public int getGrade() {
        return grade;
    }
}
