package com.main;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainAudioOpen {
    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        Desktop d = Desktop.getDesktop();
        System.out.print("Enter song name: ");
        String song = console.nextLine();
        File f = new File("D:/test/mp3/" + song + ".mp3");
        d.open(f);
    }
}
