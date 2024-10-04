import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Textbox {

    private Engine engine;
    private ArrayList<String> text;
    private int xCordStart;
    private int yCordStart;
    private int xCordEnd;
    private int yCordEnd;
    private Font font;
    private int index;

    public Textbox(String text, int xCordStart, int yCordStart, int xCordEnd, int yCordEnd, Engine engine) {
        this.xCordStart = xCordStart;
        this.yCordStart = yCordStart;
        this.xCordEnd = xCordEnd;
        this.yCordEnd = yCordEnd;
        this.text = new ArrayList<String>();
        convertToList(text);
        this.engine = engine;
        font = new Font("Consolas", Font.PLAIN, 25);
        index = 0;
    }

    private void convertToList(String text) {
        for (int i = 0; i < text.length(); i ++) {
            this.text.add(text.substring(i, i + 1));
        }
    }
    public void draw(Graphics2D g2) {
        if (engine.getFrame().getFrame() % 2 == 0 && index != text.size()) {
            index ++;
        }
        int x = xCordStart;
        int y = yCordStart;
        for (int i = 0; i < index; i ++) {
            if (x > xCordEnd) {
                y += 25;
                x = xCordStart;
            }
            g2.setFont(font);
            g2.drawString(text.get(i), x, y);
            x += 15;
        }
    }

    public void clear() {
        text.clear();
    }
}
