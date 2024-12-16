package com.jeido.functional.ui;

public abstract class Submenu extends MenuComponent {
    private final ISubmenuLogic logic;
    public Submenu(String title, ISubmenuLogic logic) {
        super(title);
        this.logic = logic;
    }

    @Override
    public void open(String[] args) {
        logic.open(args);
    }
}
