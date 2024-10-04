import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayPanel extends JPanel {

    private Engine engine;

    public PlayPanel(Engine engine) {
        this.engine = engine;
        this.setFocusable(true);
        this.addMouseListener(engine.getMouseInput());
        this.addKeyListener(engine.getInput());
    }


    public void update() {
        engine.getPlayer().updatePlayer();
        engine.getStage().updateStage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        engine.getStage().draw(g2);
        engine.getPlayer().draw(g2);
        for (int i = 0; i < engine.getStage().getInteractables().size(); i++) {
            if (engine.getStage().getInteractables().get(i) instanceof NPC) {
                if (((NPC) engine.getStage().getInteractables().get(i)).isStoodOn()) {
                    ((NPC) engine.getStage().getInteractables().get(i)).draw(g2);
                }
            }
        }
    }
}
