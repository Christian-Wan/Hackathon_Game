import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    private Engine engine;
    private Point point;

    public MouseInput(Engine engine) {
        this.engine = engine;
        point = new Point(0, 0);
    }

    public Point getPoint() {
        return point;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        engine.getSoundControl().playClick();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        point = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        point = new Point(0, 0);
        engine.getStage().setFirstPress(false);
        engine.getStage().setPressedMusicSlider(false);
        engine.getStage().setPressedSfxSlider(false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
