package com.jeido.functional.ui;

import com.jeido.functional.utils.StringUtils;
import com.jeido.functional.utils.exception.InputOutOfBoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu extends MenuComponent {
    private List<MenuComponent> subMenus = new ArrayList<>();
    private final Scanner scanner = ScannerManager.getInstance().getScanner();

    public Menu(String title) {
        super(title);
    }

    public List<MenuComponent> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<MenuComponent> subMenus) {
        this.subMenus = subMenus;
    }

    public void addSubMenu(MenuComponent subMenu) {
        subMenus.add(subMenu);
    }

    public void removeSubMenu(MenuComponent subMenu) {
        subMenus.remove(subMenu);
    }

    @Override
    public void open(String[] args) {
        if (args.length == 0) {
            int preSize = (subMenus.size() / 10) + 3;
            int menuSize = subMenus.stream().collect(Collectors.groupingBy(
                            MenuComponent::getTitle,
                            Collectors.summingInt(m -> m.getTitle().length())
                    )).values()
                    .stream()
                    .max(Integer::compareTo)
                    .orElse(title.length()) + preSize * 2;
            while (true) {
                StringBuilder sb = new StringBuilder(StringUtils.left(" ", preSize, '='));
                sb.append(StringUtils.left(StringUtils.capitalizeAll(title) + " ", menuSize, '=')).append('\n');
                for (int i = 0; i < subMenus.size(); i++) {
                    MenuComponent subMenu = subMenus.get(i);
                    sb.append(StringUtils.left((i + 1) + "", preSize, '0')).append(". ");
                    sb.append(StringUtils.capitalize(subMenu.getTitle())).append('\n');
                }
                sb.append("0. Quit");

                String[] input = scanner.nextLine().split(" ");
                try {
                    int choice = Integer.parseInt(input[0]);
                    if (choice < 0 || choice > subMenus.size()) {
                        throw new InputOutOfBoundException(choice, subMenus.size());
                    }
                    if (choice == 0) {
                        return;
                    }
                    subMenus.get(choice - 1).open(Arrays.copyOfRange(input, 1, input.length));
                } catch (InputOutOfBoundException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number");
                }
            }
        } else {
            String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
            try {
                int choice = Integer.parseInt(args[0]);
                if (choice <= 0 || choice > subMenus.size()) {
                    throw new InputOutOfBoundException(choice, 1, subMenus.size());
                }
                subMenus.get(choice - 1).open(newArgs);
            } catch (InputOutOfBoundException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
