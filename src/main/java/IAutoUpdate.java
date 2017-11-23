import java.util.Observer;

public interface IAutoUpdate {

    void flipIsRunning();

    void addObserver(Observer o);

    void deleteObserver(Observer o);

}
