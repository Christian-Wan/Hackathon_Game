import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayPanel extends JPanel {

    private Engine engine;
    private BufferedImage menu;

    public PlayPanel(Engine engine) throws IOException {
        this.engine = engine;
        this.setFocusable(true);
        this.addMouseListener(engine.getMouseInput());
        this.addKeyListener(engine.getInput());
        menu = ImageIO.read(new File("images/Pause_Button.png"));
    }


    public void update() throws IOException {
        engine.getPlayer().updatePlayer();
        engine.getStage().updateStage();
        engine.getSoundControl().update();
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
        if (engine.getStage().isSettingsOpen()) {
            engine.getSoundControl().draw(g2);
        }
        g.drawImage(menu, 10, 10, 64, 64, null);
    }
}
