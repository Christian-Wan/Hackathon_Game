
//The purpose of engine is to allow each class to access every object made
public class Engine {

    private Player player;
    private Frame frame;
    private PlayPanel playPanel;
    private Input input;
    private Stage stage;
    private Textbox textbox;

    public Engine(Frame frame) {
        this.frame = frame;
        player = new Player(this);
        input = new Input(this);
        playPanel = new PlayPanel(this);
        stage = new Stage(this);
        textbox = new Textbox("what the flip! yabadabadoo i am the sigmaest of them all!", 100,100,0,0, this);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public PlayPanel getPlayPanel() {
        return playPanel;
    }

    public void setPlayPanel(PlayPanel playPanel) {
        this.playPanel = playPanel;
    }

    public Textbox getTextbox() {
        return textbox;
    }

    public void setTextbox(Textbox textbox) {
        this.textbox = textbox;
    }
}
