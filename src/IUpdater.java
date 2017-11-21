import java.util.List;
import java.util.Observer;
import java.util.Vector;

public interface IUpdater {

    void addObserver(Observer o);

    void addFolio(String name);

    void removeFolio(int name);

    void manualUpdateGUI();

    Vector<Vector<String>> getData(String name);

    void folioModified(Vector<String> i, String name, int x);
}
