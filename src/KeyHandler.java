import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean rightPressed, leftPressed;

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_LEFT) {
            this.leftPressed = true;
        }

        if(code == KeyEvent.VK_RIGHT) {
            this.rightPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_LEFT) {
            this.leftPressed = false;
        }

        if(code == KeyEvent.VK_RIGHT) {
            this.rightPressed = false;
        }

    }
}
