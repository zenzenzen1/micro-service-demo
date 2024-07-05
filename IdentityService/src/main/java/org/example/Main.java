package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // System.out.println(1 / 0);
        System.out.println(String.join(" ", new ArrayList<String>().stream().toArray(String[]::new))
                .length());
    }
}
