package pcd.lab01.ex01;

public class WordThread extends Thread {

    private String word;
    private int x0;
    private int speed;
    private Screen screen;
    private int consoleSize;

    public WordThread(String word, int x0, int speed, Screen screen, int consoleSize) {
        this.word = word;
        this.x0 = x0;
        this.speed = speed;
        this.screen = screen;
        this.consoleSize = consoleSize;
    }

    public void run() {
        for (int i = 1; i < this.consoleSize; i++) {
            try {
                Thread.sleep(this.speed);
            } catch (Exception ex) {
            }
            screen.writeStringAt(i, x0, org.fusesource.jansi.Ansi.Color.BLACK, " ".repeat(word.length()));
            screen.writeStringAt(1 + i, x0, org.fusesource.jansi.Ansi.Color.BLACK, word);
        }
    }
}
