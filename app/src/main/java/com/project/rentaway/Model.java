package com.project.rentaway;

public class Model {

    public static final int NORMAL_TYPE=0;
    public static final int BOLD_TYPE=1;
    private String text;
    private int type;

    public Model(String text, int type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public int getType() {
        return type;
    }
}
