package com.samsung.kaliyev.satpreparation;

public class Tests {
    String name, type, test_url, essay_url, answ_url, exp_url;

    public Tests(){

    }

    public Tests(String name, String type, String test_url, String essay_url, String answ_url, String exp_url) {
        this.name = name;
        this.type = type;
        this.test_url = test_url;
        this.essay_url = essay_url;
        this.answ_url = answ_url;
        this.exp_url = exp_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTest_url() {
        return test_url;
    }

    public void setTest_url(String test_url) {
        this.test_url = test_url;
    }

    public String getEssay_url() {
        return essay_url;
    }

    public void setEssay_url(String essay_url) {
        this.essay_url = essay_url;
    }

    public String getAnsw_url() {
        return answ_url;
    }

    public void setAnsw_url(String answ_url) {
        this.answ_url = answ_url;
    }

    public String getExp_url() {
        return exp_url;
    }

    public void setExp_url(String exp_url) {
        this.exp_url = exp_url;
    }
}
