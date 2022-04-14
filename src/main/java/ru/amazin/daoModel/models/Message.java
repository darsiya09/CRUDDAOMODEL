package ru.amazin.daoModel.models;

public class Message implements MessageTemplate{

    private String first;
    private String second;


    public Message(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }
}
