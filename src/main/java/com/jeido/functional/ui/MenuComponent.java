package com.jeido.functional.ui;


public abstract class MenuComponent {
    protected String title;
    public MenuComponent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract void open(String[] args);

}
