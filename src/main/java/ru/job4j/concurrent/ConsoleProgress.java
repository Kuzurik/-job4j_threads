package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        char[] process = {'\\', '|', '/'};
        int index = 0;
        while (!Thread.currentThread().isInterrupted()) {
            if (index == process.length) {
                index = 0;
            }
            System.out.print("\r load: " + process[index]);
            index++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}
