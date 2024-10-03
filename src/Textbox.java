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
    private int index;

    public Textbox(String text, int xCordStart, int yCordStart, int xCordEnd, int yCordEnd, Engine engine) {
        this.xCordStart = xCordStart;
        this.yCordStart = yCordStart;
        this.xCordEnd = xCordEnd;
        this.yCordEnd = yCordEnd;
        this.text = new ArrayList<String>();
        convertToList(text);
        this.engine = engine;
    }

    private void convertToList(String text) {
        for (int i = 0; i < text.length(); i ++) {
            this.text.add(text.substring(i, i++));
        }
    }
    public void draw(Graphics2D g2) {
        System.out.println(text.toString());
        if (engine.getFrame().getFrame() % 3 == 0 && index != text.size()) {
            index ++;
        }
        int x = xCordStart;
        for (int i = 0; i < index; i ++) {
            g2.drawString(text.get(i), x, yCordStart);
            x ++;
        }
    }
}
