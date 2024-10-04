import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Stage {

    private Engine engine;
    private ArrayList<Interactable> interactables;
    private String currentMap;
    private Rectangle button1, button2, button3, button4;

    public Stage(Engine engine) {
        this.engine = engine;
        currentMap = "map1";
        interactables = new ArrayList<>();
        button1 = new Rectangle(224, 384, 96, 46);
        button2 = new Rectangle(336, 384, 96, 46);
        button3 = new Rectangle(448, 384, 96, 46);
        button4 = new Rectangle(560, 384, 96, 46);
        parseMap();
    }

    public Stage(Engine engine, String map) {
        this.engine = engine;
        currentMap = map;
        interactables = new ArrayList<>();
        button1 = new Rectangle(224, 384, 96, 46);
        button2 = new Rectangle(336, 384, 96, 46);
        button3 = new Rectangle(448, 384, 96, 46);
        button4 = new Rectangle(560, 384, 96, 46);
        parseMap();
    }


    public void updateStage() {
        if (engine.getPlayer().isInteracting()) {
            if (button1.contains(engine.getMouseInput().getPoint())) {
                System.out.println("Button1");
            }
            else if (button2.contains(engine.getMouseInput().getPoint())) {
                System.out.println("Button2");
            }
            else if (button3.contains(engine.getMouseInput().getPoint())) {
                System.out.println("Button3");
            }
            else if (button4.contains(engine.getMouseInput().getPoint())) {
                System.out.println("Button4");
            }
        }
    }

    public void draw(Graphics2D g) {
        String path = "images/" + currentMap + ".png";
        BufferedImage background = null;
        try {
            background = ImageIO.read(new File(path));
        } catch (IOException e) {}
        g.drawImage(background, 0, 0, 1280, 640, null);
        for (int i = 0; i < interactables.size(); i++) {
            g.drawRect(button1.x, button1.y, button1.width, button1.height);
            g.drawRect(button2.x, button2.y, button2.width, button2.height);
            g.drawRect(button3.x, button3.y, button3.width, button3.height);
            g.drawRect(button4.x, button4.y, button4.width, button4.height);
            if (interactables.get(i) instanceof NPC) {
                if (((NPC) interactables.get(i)).isStoodOn()) {
                    ((NPC) interactables.get(i)).draw(g);
                }
            }
        }

    }



    private void parseMap() {
        String path = "map_data/" + currentMap;
        File f = new File(path);
        Scanner s = null;
        String line;
        String[] split;
        try {
            s= new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(s.hasNext()) {
            line = s.nextLine();
            split = line.split(", ");
            System.out.println(Arrays.toString(split));
            if (split.length == 4) {
                //x, y, height, width
                interactables.add(new Interactable(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3])));
            }
            else {
                //x, y, height, width, name
                interactables.add(new NPC(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), split[4], engine));
            }
        }
    }

    public ArrayList<Interactable> getInteractables() {
        return interactables;
    }

    public String getCurrentMap() {
        return currentMap;
    }
}
