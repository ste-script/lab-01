package pcd.lab01.ex01;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        var sentence = "This is a simple sentence with words ready to fall";
        Screen screen = Screen.getInstance();
        screen.clear();

        var sentenceWordList = sentence.split(" ");
        var threadList = new ArrayList<WordThread>();
        var x0 = 1;
        var speed = 0;
        var speedDifferences = 100;
        var minimumSpeed = 50;
        var consoleSize = 15;
        var random = new Random();
        for (String string : sentenceWordList) {
            speed = random.nextInt(speedDifferences) + minimumSpeed;
            var wordThread = new WordThread(string, x0, speed, screen, consoleSize);
            threadList.add(wordThread);
            x0 = x0 + string.length() + 1;
        }

        threadList.forEach(Thread::start);

        try {
            for (WordThread wordThread : threadList) {
                wordThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
