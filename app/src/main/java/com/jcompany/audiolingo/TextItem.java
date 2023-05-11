package com.jcompany.audiolingo;

public class TextItem {
    private String name,text,id;

    public TextItem(String name, String text, String id){
        this.name = name;
        this.text = text;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
