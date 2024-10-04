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
    private OpenAIClient openAIClient;
    private String answer;


    public NPC(int x, int y, int height, int width, String name, Engine engine) {
        super(x, y, height, width);
        this.engine = engine;
        this.name = name;
        stoodOn = false;
        openAIClient = new OpenAIClient();
        String question = openAIClient.generateQuestions("Generate a multiple choice question about " + name + " Include the right answer choice at the end in brackets. Example: QUESTION A) ANSWER CHOICE ONE B) ANSWER CHOICE TWO C) ANSWER CHOICE THREE D) ANSWER CHOICE FOUR | [THE CORRECT ANSWER] (only put brackets around the answer not the question or choices, and do not put any spaces in the section for the correct answer CHOICE, PLEASE MAKE SURE TO ONLY PUT THE LETTER IN THE ANSWER SECTION AND MAKE SURE TO INCLUDE THE BRACKETS, NO SPACES NO SPACES NO SPACES NO SPACES, DO NOT PUT A SPACE. DO NOT PUT [QUESTION] IN FRONT OF THE QUESTION, THE ONLY BRACKETS SHOULD BE AROUND THE ANSWER CHOICE. PLEASE PUT BRAKCETS AROUND THE ANSWER CHOICE)");
        String answer = question.substring(question.indexOf("[") + 1, question.indexOf("[") + 2);
        if (answer.equals(" ")) {
            answer = question.substring(question.indexOf("[") + 2, question.indexOf("[") + 3);
        }
        System.out.println(answer);
        text = new Textbox(question, 234, 469, 1198, 556, engine);

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
            Font font = new Font("Consolas", Font.PLAIN, 20);
            g2.setFont(font);
            g2.drawString(name, 82, 555);
            text.draw(g2);
        }
    }

    public boolean isStoodOn() {
        return stoodOn;
    }

    public void setStoodOn(boolean stoodOn) {
        this.stoodOn = stoodOn;
    }
}
