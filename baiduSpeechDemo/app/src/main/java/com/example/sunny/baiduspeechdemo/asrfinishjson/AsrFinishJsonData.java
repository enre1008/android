package com.example.sunny.baiduspeechdemo.asrfinishjson;

import com.example.sunny.baiduspeechdemo.asrpartialjson.Result;

public class AsrFinishJsonData {
    private String error;
    private String sub_error;
    private String desc;
    private OriginResult origin_result;

    public String getError() {
        return error;
    }

    public String getSub_error() {
        return sub_error;
    }

    public String getDesc() {
        return desc;
    }

    public OriginResult getOrigin_result() {
        return origin_result;
    }

    public static class OriginResult {
        private String corpus_no;
        private String err_no;
        private String sn;
        private Result result;

        public String getCorpus_no() {
            return corpus_no;
        }

        public String getErr_no() {
            return err_no;
        }

        public String getSn() {
            return sn;
        }

        public Result getResult() {
            return result;
        }
    }
}
