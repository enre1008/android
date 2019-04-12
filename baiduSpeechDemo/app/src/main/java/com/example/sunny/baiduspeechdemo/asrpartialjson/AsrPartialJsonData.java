package com.example.sunny.baiduspeechdemo.asrpartialjson;

import com.example.sunny.baiduspeechdemo.asrfinishjson.OriginResult;

import java.util.ArrayList;

public class AsrPartialJsonData {
    private ArrayList<String> result_recognition;
    private OriginResult origin_result;
    private String error;
    private String best_result;
    private String result_type;

    public ArrayList<String> getResult_recognition() {
        return result_recognition;
    }

    public OriginResult getOrigin_result() {
        return origin_result;
    }

    public String getError() {
        return error;
    }

    public String getBest_result() {
        return best_result;
    }

    public String getResult_type() {
        return result_type;
    }
}
