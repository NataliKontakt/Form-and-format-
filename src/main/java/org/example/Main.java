package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> validPaths = List.of("/index.html", "/about.html", "/classic.html");
        Server.startServer(validPaths);
    }
}