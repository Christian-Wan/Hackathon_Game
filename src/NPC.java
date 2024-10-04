import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NPC extends Interactable {

    private String name;
    private BufferedImage pfp;
    private BufferedImage textbox;
    private Engine engine;
    private boolean stoodOn;
    private Textbox text;
    private Textbox correctText;
    private OpenAIClient openAIClient;
    private String answer;
    private Textbox hint;
    private Boolean correct;
    private Boolean hintDisplay;
    private Boolean checkedOnce;



    public NPC(int x, int y, int height, int width, String name, Engine engine) {
        super(x, y, height, width);
        this.engine = engine;
        this.name = name;
        stoodOn = false;
        checkedOnce = false;
        correct = false;
        hintDisplay = false;
        openAIClient = new OpenAIClient();
        String question = openAIClient.generateQuestions("Generate a question about " + name + ", it can be anything about " + name);
        System.out.println(question);
        answer = question.substring(question.indexOf("[") + 1, question.indexOf("[") + 2);
        if (answer.equals(" ")) {
            answer = question.substring(question.indexOf("[") + 2, question.indexOf("[") + 3);
        }
        String hint = question.substring(question.indexOf("|") + 1);
        System.out.println(hint);
        System.out.println(answer);
        question = question.substring(0, question.indexOf("["));
        text = new Textbox(question, 234, 469, 1188, 556, engine);
        this.hint = new Textbox(hint,234, 50, 1188, 95, engine);
        correctText = new Textbox("You are correct!", 234, 469, 1188, 556, engine);


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

    public void printHint(Graphics2D g2) {
    }

    public void draw(Graphics2D g2) {
        if (engine.getPlayer().isInteracting()) {
            g2.drawImage(textbox, 0, 0, null);
            g2.drawImage(pfp, 82, 439, null);
            Font font = new Font("Consolas", Font.PLAIN, 20);
            g2.setFont(font);
            g2.drawString(name, 82, 555);
            System.out.println(engine.getStage().getAnswer());
            System.out.println(answer);
            if (engine.getStage().getAnswer().equals(answer)) {
                System.out.println("i am sigma");
                correct = true;
            }
            if (!correct) {
                text.draw(g2);
            }
            if (correct) {
                correctText.draw(g2);
            }
            if (engine.getStage().getAnswer().equals("hint")) {
                hintDisplay = true;
            }
            if (hintDisplay) {
                hint.draw(g2);
            }
        }
    }

    public boolean isStoodOn() {
        return stoodOn;
    }

    public void setStoodOn(boolean stoodOn) {
        this.stoodOn = stoodOn;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public Boolean getCheckedOnce() {
        return checkedOnce;
    }

    public void setCheckedOnce(Boolean checkedOnce) {
        this.checkedOnce = checkedOnce;
    }
}


