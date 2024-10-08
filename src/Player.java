import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player {

    private Engine engine;
    private Rectangle hitBox;
    private int speed, xCord, yCord, frameNumber, frameCounter, numOfCorrectAnswers, totalNumOfQuestions;
    private boolean onNPC, interacting, moving;
    private BufferedImage key, standDown, standLeft, standUp, standRight, walkDown, walkLeft, walkRight, walkUp;
    private String direction;

    public Player(Engine engine) {
        this.engine = engine;
        hitBox = new Rectangle(xCord, yCord, 10, 10);
        speed = 5;
        xCord = 10;
        yCord = 300;
        numOfCorrectAnswers = 0;
        interacting = false;
        direction = "down";
        moving = false;
        try {
            key = ImageIO.read(new File("images/Hack_Key.png"));
            standDown = ImageIO.read(new File("images/down0.png"));
            standLeft = ImageIO.read(new File("images/left0.png"));
            standRight = ImageIO.read(new File("images/right0.png"));
            standUp = ImageIO.read(new File("images/up0.png"));
            walkDown = ImageIO.read(new File("images/down-sheet.png"));
            walkLeft = ImageIO.read(new File("images/left-sheet.png"));
            walkRight = ImageIO.read(new File("images/right-sheet.png"));
            walkUp = ImageIO.read(new File("images/up-sheet.png"));
        } catch (IOException e) {}
    }

    public void updatePlayer() throws IOException {
        movePlayer();
        interact();
    }

    public void movePlayer() {
        ArrayList<Integer> input = engine.getInput().getInputs();
        int up = input.indexOf(KeyEvent.VK_W);
        int left = input.indexOf(KeyEvent.VK_A);
        int down = input.indexOf(KeyEvent.VK_S);
        int right = input.indexOf(KeyEvent.VK_D);

        if (up != -1 || left != -1 || down != -1 || right != -1) {
            moving = true;
            if (up > left && up > down && up > right) {
                yCord -= speed;
                direction = "up";
            } else if (left > up && left > down && left > right) {
                xCord -= speed;
                direction = "left";
            } else if (down > left && down > up && down > right) {
                yCord += speed;
                direction = "down";
            } else if (right > left && right > down && right > up) {
                xCord += speed;
                direction = "right";
            }
        }
        else {
            moving = false;
        }
        hitBox.setLocation(xCord, yCord);
    }

    public void interact() throws IOException {
        boolean checked = false;
        for (int i = 0; i < engine.getStage().getInteractables().size(); i++) {

            if (engine.getStage().getInteractables().get(i).getInteractBox().contains(hitBox)) {
                checked = true;
                if (engine.getStage().getInteractables().get(i) instanceof NPC) {
                    onNPC = true;
                    if (((NPC) engine.getStage().getInteractables().get(i)).getCorrect() && !((NPC) engine.getStage().getInteractables().get(i)).getCheckedOnce()) {
                        numOfCorrectAnswers ++;
                        ((NPC) engine.getStage().getInteractables().get(i)).setCheckedOnce(true);
                    }
                    if (((NPC) engine.getStage().getInteractables().get(i)).getIncorrect() && !((NPC) engine.getStage().getInteractables().get(i)).getCheckedOnce()) {
                        numOfCorrectAnswers --;
                        ((NPC) engine.getStage().getInteractables().get(i)).setCheckedOnce(true);
                    }
                    ((NPC) engine.getStage().getInteractables().get(i)).setStoodOn(true);
                    ArrayList<Integer> input = engine.getInput().getInputs();
                    if (input.contains(KeyEvent.VK_E)) {
                        interacting = true;
                    }
                }
                else {
                    if (engine.getStage().getInteractables().get(i).getInteractBox().getX() > 60) {
                        String nextMap = "map" +  (Integer.parseInt(engine.getStage().getCurrentMap().substring(3)) + 1);
                        engine.setStage(new Stage(engine, nextMap));
                        xCord = 20;

                    }
                    else {
                        String nextMap = "map" +  (Integer.parseInt(engine.getStage().getCurrentMap().substring(3)) - 1);
                        engine.setStage(new Stage(engine, nextMap));
                        xCord = 1250;
                    }

                    hitBox.setLocation(xCord, yCord);
                }
                break;
            }
            if (engine.getStage().getInteractables().get(i) instanceof NPC) {
                ((NPC) engine.getStage().getInteractables().get(i)).setStoodOn(false);
            }
        }
        if (!checked) {
            onNPC = false;
            interacting = false;
            engine.getStage().setAnswer("");
        }
    }

    public void draw(Graphics2D g) {
        BufferedImage image = null;
        frameCounter++;
        if (frameCounter == 6) {
            frameNumber++;
            frameCounter = 0;
        }
        if (frameNumber == 2) {
            frameNumber = 0;
        }
        if (!moving) {
            image = switch (direction) {
                case "up" -> standUp;
                case "left" -> standLeft;
                case "down" -> standDown;
                case "right" -> standRight;
                default -> image;
            };
        }
        else {
            image = switch (direction) {
                case "up" -> walkUp.getSubimage(32 * frameNumber, 0, 32, 32);
                case "left" -> walkLeft.getSubimage(32 * frameNumber, 0, 32, 32);
                case "down" -> walkDown.getSubimage(32 * frameNumber, 0, 32, 32);
                case "right" -> walkRight.getSubimage(32 * frameNumber, 0, 32, 32);
                default -> image;
            };
        }

        g.drawImage(image, xCord - 25, yCord - 10, 64, 64, null);
        if (onNPC) {
            g.drawImage(key, xCord - 10, yCord - 40, 32, 32, null);
        }
        Font font = new Font("Consolas", Font.PLAIN, 40);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Correct: " + numOfCorrectAnswers, 1000, 50);
        g.setColor(Color.BLACK);
    }

    public boolean isInteracting() {
        return interacting;
    }

    public void setInteracting(boolean interacting) {
        this.interacting = interacting;
    }
}
