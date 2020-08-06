package com.github.cschen1205.ess.engine;

import java.util.Scanner;

public class Console {
    public static String showInputDialog(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question + " ");
        return scanner.next();
    }
}
