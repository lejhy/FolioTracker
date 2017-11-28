package Model;

import java.util.Observable;

public class AutoUpdate extends Observable implements IAutoUpdate {

    private boolean isRunning;
    private Runnable action;
    private int delayMilis;
    private Thread thread;

    AutoUpdate(Runnable action, int delayMilis) {
        isRunning = false;
        this.action = action;
        this.delayMilis = delayMilis;
    }

    @Override
    public boolean start() {
        if (!isRunning) {
            isRunning = true;
            startThread();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean stop() {
        if (isRunning) {
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

    /*
    Assertion checking for precondition
     */
    private void startThread() {
        assert(isRunning);

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

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof AutoUpdate) {
                AutoUpdate autoUpdate = (AutoUpdate) o;
                if (this.isRunning == autoUpdate.isRunning &&
                        this.delayMilis == autoUpdate.delayMilis) {
                    return true;
                }
            }
        }
        return false;
    }

}

