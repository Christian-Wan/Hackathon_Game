import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player {

    private Engine engine;
    private Rectangle hitBox;
    private int speed, xCord, yCord;

    public Player(Engine engine) {
        this.engine = engine;
        hitBox = new Rectangle(xCord, yCord, 10, 10);
        speed = 4;
        xCord = 10;
        yCord = 10;
    }

    public void updatePlayer() {
        movePlayer();
        interact();
    }

    public void movePlayer() {
        ArrayList<Integer> input = engine.getInput().getInputs();
        int up = input.indexOf(KeyEvent.VK_W);
        int left = input.indexOf(KeyEvent.VK_A);
        int down = input.indexOf(KeyEvent.VK_S);
        int right = input.indexOf(KeyEvent.VK_D);

        if (up > left && up > down && up > right) {
            yCord -= speed;
        }
        else if (left > up && left > down && left > right) {
            xCord -= speed;
        }
        else if (down > left && down > up && down > right) {
            yCord += speed;
        }
        else if (right > left && right > down && right > up) {
            xCord += speed;
        }
        hitBox.setLocation(xCord, yCord);
    }

    public void interact() {

        for (int i = 0; i < engine.getStage().getInteractables().size(); i++) {

            if (engine.getStage().getInteractables().get(i).getInteractBox().contains(hitBox)) {
                System.out.println("test");
                if (engine.getStage().getInteractables().get(i) instanceof NPC) {
                    //SHOW TEXT ABOVE PLAYER HEAD THAT STATES THE PLAYER CAN PRESS E TO INTERACT
                    System.out.println("NPC HERE");

                }
                else {
                    if (engine.getStage().getInteractables().get(i).getInteractBox().getX() > 60) {
                        String nextMap = "map" +  (Integer.parseInt(engine.getStage().getCurrentMap().substring(3)) + 1);
                        engine.setStage(new Stage(engine, nextMap));
                        xCord = 20;

                    }
                    else {
                        System.out.println("GO PREVIOUS");
                        String nextMap = "map" +  (Integer.parseInt(engine.getStage().getCurrentMap().substring(3)) - 1);
                        engine.setStage(new Stage(engine, nextMap));
                        xCord = 1250;
                    }

                    hitBox.setLocation(xCord, yCord);
               }
            }
        }
    }

    public void draw(Graphics2D g) {
        g.drawRect(xCord, yCord, hitBox.width, hitBox.height);
    }
}
