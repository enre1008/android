package com.example.sunny.baiduspeechdemo.asrpartialjson;

import java.util.ArrayList;

public class Result {
    private ArrayList<String> uncertain_word;
    private ArrayList<String> word;

    private ArrayList<String> getUncertain_word(){
        return uncertain_word;
    }

    public ArrayList<String> getWord() {
        return word;
    }
}
