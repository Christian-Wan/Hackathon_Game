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
    private Rectangle button1, button2, button3, button4, button5, menuButton;
    private String answer;
    private boolean settingsOpen, firstPress, pressedMusicSlider, pressedSfxSlider;


    public Stage(Engine engine) throws IOException {
        answer = "";
        this.engine = engine;
        currentMap = "map1";
        interactables = new ArrayList<>();
        button1 = new Rectangle(224, 384, 96, 46);
        button2 = new Rectangle(336, 384, 96, 46);
        button3 = new Rectangle(448, 384, 96, 46);
        button4 = new Rectangle(560, 384, 96, 46);
        button5 = new Rectangle(674, 384, 96, 46);
        menuButton = new Rectangle(10, 10, 64, 64);

        parseMap();
    }

    public Stage(Engine engine, String map) throws IOException {
        this.engine = engine;
        currentMap = map;
        answer = "";
        interactables = new ArrayList<>();
        button1 = new Rectangle(224, 384, 96, 46);
        button2 = new Rectangle(336, 384, 96, 46);
        button3 = new Rectangle(448, 384, 96, 46);
        button4 = new Rectangle(560, 384, 96, 46);
        button5 = new Rectangle(674, 384, 96, 46);
        menuButton = new Rectangle(10, 10, 64, 64);
        parseMap();
    }


    public void updateStage() {
        if (!settingsOpen) {
            if (menuButton.contains(engine.getMouseInput().getPoint())) {
                settingsOpen = true;
            }
        }
        else {
            if (engine.getSoundControl().getClose().contains(engine.getMouseInput().getPoint())) {
                settingsOpen = false;
            }
            else if (engine.getSoundControl().getSfxSlider().contains(engine.getMouseInput().getPoint())) {
                pressedSfxSlider = true;
            }
            else if (engine.getSoundControl().getMusicSlider().contains(engine.getMouseInput().getPoint())) {
                pressedMusicSlider = true;
                System.out.println(MouseInfo.getPointerInfo().getLocation());
            }
        }
        if (pressedSfxSlider) {
            engine.getSoundControl().changeSfxSlider(MouseInfo.getPointerInfo().getLocation(), firstPress);
            firstPress = true;
        }
        if (pressedMusicSlider) {
            engine.getSoundControl().changeMusicSlider(MouseInfo.getPointerInfo().getLocation(), firstPress);
            firstPress = true;
        }
        if (engine.getPlayer().isInteracting()) {
            if (button1.contains(engine.getMouseInput().getPoint())) {
                answer = "A";
            }
            else if (button2.contains(engine.getMouseInput().getPoint())) {
                answer = "B";
            }
            else if (button3.contains(engine.getMouseInput().getPoint())) {
                answer = "C";
            }
            else if (button4.contains(engine.getMouseInput().getPoint())) {
                answer = "D";
            }
            else if (button5.contains(engine.getMouseInput().getPoint())) {
                answer = "hint";
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public boolean isFirstPress() {
        return firstPress;
    }

    public void setFirstPress(boolean firstPress) {
        this.firstPress = firstPress;
    }

    public void setPressedMusicSlider(boolean pressedMusicSlider) {
        this.pressedMusicSlider = pressedMusicSlider;
    }

    public void setPressedSfxSlider(boolean pressedSfxSlider) {
        this.pressedSfxSlider = pressedSfxSlider;
    }

    public boolean isSettingsOpen() {
        return settingsOpen;
    }
}
