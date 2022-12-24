package ru.dmdev.servlets;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class UrlExample {

    public static void main(String[] args) throws IOException {
        checkGoogle();

    }

    private static void checkGoogle() throws IOException {
        URL url = new URL("https://google.com");
        URLConnection urlConnection = url.openConnection();
        Map<String, List<String>> map = urlConnection.getHeaderFields();
        byte[] bytes = urlConnection.getInputStream().readAllBytes();

        System.out.println(new String(bytes));
    }
}
