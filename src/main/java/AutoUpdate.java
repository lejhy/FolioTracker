import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AutoUpdate extends Observable implements IAutoUpdate {

    private boolean isRunning;
    private Runnable action;
    private int delayMilis;
    private Thread thread;

    public AutoUpdate(Runnable action, int delayMilis) {
        isRunning = false;
        this.action = action;
        this.delayMilis = delayMilis;
    }

    @Override
    public boolean start() {
        if (isRunning == false) {
            isRunning = true;
            startThread();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean stop() {
        if (isRunning == true) {
            isRunning = false;
            thread.interrupt();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    private void startThread() {
        thread = new Thread(() -> {
            while (isRunning) {
                try {
                    System.out.println("Auto Updating");
                    action.run();
                    Thread.sleep(delayMilis);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        thread.start();
    }
}

