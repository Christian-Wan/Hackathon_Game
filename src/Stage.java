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

    public Stage(Engine engine) {
        this.engine = engine;
        currentMap = "map1";
        interactables = new ArrayList<>();
        parseMap();
    }

    public Stage(Engine engine, String map) {
        this.engine = engine;
        currentMap = map;
        interactables = new ArrayList<>();
        parseMap();
    }


    public void updateStage() {

    }

    public void draw(Graphics2D g) {
        String path = "images/" + currentMap + ".png";
        BufferedImage background = null;
        try {
            background = ImageIO.read(new File(path));
        } catch (IOException e) {}
        g.drawImage(background, 0, 0, 1280, 640, null);
        for (int i = 0; i < interactables.size(); i++) {
            g.drawRect((int) interactables.get(i).getInteractBox().getX(),(int) interactables.get(i).getInteractBox().getY(),(int) interactables.get(i).getInteractBox().getWidth(),(int) interactables.get(i).getInteractBox().getHeight());
        }
    }

    public ArrayList<Interactable> getInteractables() {
        return interactables;
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
                interactables.add(new NPC(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), split[4]));
            }
        }


    }
}
