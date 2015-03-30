package com.main;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class MainAudioJLayer {
    private String filename;
    private Player player;

    // constructor that takes the name of an MP3 file
    public MainAudioJLayer(String filename) {
        this.filename = filename;
    }

    public void close() { if (player != null) player.close(); }

    // play the MP3 file to the sound card
    public void play() {
        try {
            File f = new File("D:/test/mp3/" + filename + ".mp3");
            FileInputStream fis     = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }

        // run in new thread to play in background
        new Thread() {
            public void run() {
                try { player.play(); }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();




    }


    // test client
    public static void main(String[] args) {
        System.out.print("Enter song name: ");
        String filename = new Scanner(System.in).nextLine();
        MainAudioJLayer mp3 = new MainAudioJLayer(filename);
        mp3.play();

        // do whatever computation you like, while music plays
        int N = 4000;
        double sum = 0.0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += Math.sin(i + j);
            }
        }
        System.out.println(sum);

        // when the computation is done, stop playing it
        mp3.close();

        // play from the beginning
        mp3 = new MainAudioJLayer(filename);
        mp3.play();

    }
}
