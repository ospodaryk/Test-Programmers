package com.example.ai;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class User {
    private String firstName;
//    private String lastName;
//    private String email;
//    private String password;

    public User(String firstName) {
        this.firstName = firstName;
        this.myMarks = new ArrayList<>();
        this.myMarks.addAll(Arrays.asList(0,0,0,0,0));
    }

    private List<Integer> myMarks;

    public void addMark(Integer element) {
        this.myMarks.add(element);
    }
}
