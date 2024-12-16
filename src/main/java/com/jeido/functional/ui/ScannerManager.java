package com.jeido.functional.ui;

import java.util.Scanner;

public class ScannerManager {
    private Scanner scanner;
    private static ScannerManager instance;

    private ScannerManager() {}

    public static ScannerManager getInstance() {
        return (instance == null) ? instance = new ScannerManager() : instance;
    }

    public Scanner getScanner() {
        return (this.scanner == null) ? this.scanner = new Scanner(System.in) : this.scanner;
    }
}
