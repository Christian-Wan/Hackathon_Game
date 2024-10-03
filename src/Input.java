import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Input implements KeyListener {

    private Engine engine;
    private ArrayList<Integer> inputs;

    public Input(Engine engine) {
        this.engine = engine;
        inputs = new ArrayList<Integer>();
    }

    public ArrayList<Integer> getInputs() {
        return inputs;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!inputs.contains(e.getKeyCode())) {
            inputs.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputs.remove(Integer.valueOf(e.getKeyCode()));
    }
}
