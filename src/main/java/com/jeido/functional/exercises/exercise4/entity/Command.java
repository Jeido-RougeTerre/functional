package com.jeido.functional.exercises.exercise4.entity;

import java.util.List;

public class Command {
    private final int id;
    private final String client;
    private List<String> articles;
    private double totalPrice;
    private boolean delivered;

    public Command(int id, String client, List<String> articles, double totalPrice, boolean delivered) {
        this.id = id;
        this.client = client;
        this.articles = articles;
        this.totalPrice = totalPrice;
        this.delivered = delivered;
    }

    public int getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public List<String> getArticles() {
        return articles;
    }

    public void setArticles(List<String> articles) {
        this.articles = articles;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    @Override
    public String toString() {
        return String.format("Command#%d for '%s' contains :%n%s%nTotal price: %.2f$ - %s", id, client, articles, totalPrice, (delivered ? "delivered" : "not delivered"));
    }
}
