package Model;

public interface IAutoUpdate {

    /**
     *
     * @modifies: this
     * @effects: if the auto-updater is already running return false
     *           else start the auto-updater and return true
     *
     */
    boolean start();

    /**
     *
     * @modifies: this
     * @effects: if the auto-updater is not running return false
     *           else stop the auto-updater and return true
     *
     */
    boolean stop();


    /**
     *
     * @effects: if the auto-refresher is running return true
     *           else return false
     *
     */
    boolean isRunning();

}
