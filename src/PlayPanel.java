import javax.swing.*;
import java.awt.*;

public class PlayPanel extends JPanel {

    private Engine engine;

    public PlayPanel(Engine engine) {
        this.engine = engine;
        this.setFocusable(true);
        this.addKeyListener(engine.getInput());
    }


    public void update() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
