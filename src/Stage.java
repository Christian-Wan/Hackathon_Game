import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Stage {

    private Engine engine;
    private ArrayList<Interactable> interactables;
    private String currentMap;

    public Stage(Engine engine) {
        this.engine = engine;
        currentMap = "map1";
    }


    public void updateStage() {

    }

    public void draw() {

    }

    public ArrayList<Interactable> getInteractables() {
        return interactables;
    }

    private void parseMap() {
        File f = new File("map_data");
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

            if (split.length == 4) {
                interactables.add(new Interactable(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3])));
            }
            else {
                interactables.add(new NPC(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]), split[0]));
            }
        }


    }
}
