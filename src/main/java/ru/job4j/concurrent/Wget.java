package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;

public class Wget implements Runnable {

    private final String url;
    private final int speed;
    private final String outFileName;
    private static final long SECOND = 1000;
    private static final int BUFFER = 1024;

    public Wget(String url, int speed, String outFileName) {
        this.url = url;
        this.speed = speed;
        this.outFileName = outFileName;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(outFileName)) {
            byte[] dataBuffer = new byte[BUFFER];
            int bytesRead;
            long bytesWrited = 0;
            Timestamp timeStart = new Timestamp(System.currentTimeMillis());
            Timestamp timeGapStart = new Timestamp(System.currentTimeMillis());
            while ((bytesRead = in.read(dataBuffer, 0, BUFFER)) != -1) {
                bytesWrited += bytesRead;
                if (bytesWrited >= speed) {
                    bytesWrited = 0;
                    long downloadSpeed = new Timestamp(System.currentTimeMillis()).getTime() - timeGapStart.getTime();
                    if (downloadSpeed < SECOND) {
                        Thread.sleep(SECOND - downloadSpeed);
                    }
                    timeGapStart = new Timestamp(System.currentTimeMillis());
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            System.out.println("Время закачки файла:"
                    + (new Timestamp(System.currentTimeMillis()).getTime() - timeStart.getTime()) / SECOND + " сек");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            throw new IllegalArgumentException("add args: " + "url " + "speed in bytes" + "file name");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String outFileName = args[2];
        Thread wget = new Thread(new Wget(url, speed, outFileName));
        wget.start();
        wget.join();
    }
}
