package com.snipit.snipit;

public class SnipitModel {
    int id;
    String language;
    String code;

    SnipitModel(int id, String language, String code){
        this.id = id;
        this.language = language;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString() {
        return " \n {\n  id: "+id+",\n  lang: "+language+",\n  code:"+code+"\n }";
    }
}
