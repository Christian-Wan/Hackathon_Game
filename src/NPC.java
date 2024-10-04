import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NPC extends Interactable {

    private String name;
    private BufferedImage pfp;
    private BufferedImage textbox;
    private Engine engine;
    private boolean stoodOn;


    public NPC(int x, int y, int height, int width, String name, Engine engine) {
        super(x, y, height, width);
        this.engine = engine;
        this.name = name;
        stoodOn = false;
        createImages();
    }

    private void createImages() {
        try {
            System.out.println(name);
            pfp = ImageIO.read(new File("images/" + "Hack_" + name + ".png"));
            textbox = ImageIO.read(new File("images/Hack_Text_Holder.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        if (engine.getPlayer().isInteracting()) {
            g2.drawImage(textbox, 0, 0, null);
            g2.drawImage(pfp, 82, 439, null);
        }
    }

    public boolean isStoodOn() {
        return stoodOn;
    }

    public void setStoodOn(boolean stoodOn) {
        this.stoodOn = stoodOn;
    }
}
