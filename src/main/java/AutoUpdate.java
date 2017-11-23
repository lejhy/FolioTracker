import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AutoUpdate extends Observable implements IAutoUpdate {
    private boolean isRunning;
    Thread auto;

    public AutoUpdate() {
        isRunning = false;
        resetThread();
    }

    public void flipIsRunning() {
        // TODO: Could be cleaner, maybe a case statement
        if(!isRunning && auto.getState()== Thread.State.TERMINATED) {
            System.out.println("Restarting thread");
            resetThread();
            isRunning=true;
            auto.start();
        }
        else if(!isRunning && auto.getState()== Thread.State.NEW) {
            System.out.println("Thread is new, starting");
            isRunning=true;
            auto.start();

        }
        else if(isRunning && auto.getState() == Thread.State.TIMED_WAITING) {
            System.out.println("Thread is waiting, terminating now");
            auto.interrupt();
            isRunning= false;
        }
        else if(!isRunning && auto.getState()== Thread.State.TIMED_WAITING) {
            System.out.println("Shouldn't have happened, will have to wait");
        }

    }

    private void resetThread() {
        auto = new Thread(() -> {
            while (isRunning) {
                try {
                    System.out.println("Auto Updating");
                    setChanged();
                    notifyObservers("Auto");
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    System.out.println("Stopping auto refresh, time to escape the loop");
                    break;
                }
            }
        });
    }


}

