import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class Frame extends JFrame implements Runnable {


    private Engine engine;
    private Thread windowThread;
    private int frame;

    public Frame() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        engine = new Engine(this);
        this.add(engine.getPlayPanel());
        engine.getPlayPanel().requestFocus();
        engine.getPlayPanel().setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 640);
        this.setLocation(320, 220);
        this.setVisible(true);
        this.setResizable(false);
        frame = 0;
        startThread();
    }


    public void startThread() {
        windowThread = new Thread(this);
        windowThread.start();
    }


    public void run() {
        double drawInterval = (double) 1000000000 / 60;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (true) {
            frame++;
            if (frame == 61) {
                frame = 0;
            }
            try {
                engine.getPlayPanel().update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            engine.getPlayPanel().repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }
}
