import java.util.List;
import java.util.Observer;

public interface IUpdater {

    void addObserver(Observer o);

    List<IFolio> getFolios();

    void addFolio(String name);

    String[][] updateGUI();


}
