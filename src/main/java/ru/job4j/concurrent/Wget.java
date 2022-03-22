package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {

    private final String url;
    private final int speed;
    private final String outFileName;

    public Wget(String url, int speed, String outFileName) {
        this.url = url;
        this.speed = speed;
        this.outFileName = outFileName;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(outFileName)) {
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String outFileName = args[2];
        Thread wget = new Thread(new Wget(url, speed, outFileName));
        wget.start();
        wget.join();
    }
}
