
//The purpose of engine is to allow each class to access every object made
public class Engine {

    private Player player;
    private Frame frame;
    private PlayPanel playPanel;
    private Input input;

    public Engine(Frame frame) {
        this.frame = frame;
        player = new Player(this);
        input = new Input(this);
        playPanel = new PlayPanel(this);


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
}
