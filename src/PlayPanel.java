import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayPanel extends JPanel {

    private Engine engine;

    public PlayPanel(Engine engine) {
        this.engine = engine;
        this.setFocusable(true);
        this.addKeyListener(engine.getInput());
    }


    public void update() {
        engine.getPlayer().updatePlayer();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        engine.getStage().draw(g2);
        engine.getPlayer().draw(g2);
    }
}
