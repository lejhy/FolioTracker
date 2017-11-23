import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static java.awt.event.ItemEvent.DESELECTED;
import static java.awt.event.ItemEvent.SELECTED;

public class FolioPanelItemListener implements ItemListener{

    enum Type {AUTO_REFRESH}

    private Type type;
    private IFolio folio;

    public FolioPanelItemListener (Type type, IFolio folio) {
        this.type = type;
        this.folio = folio;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        switch (e.getStateChange()) {
            case SELECTED:
                folio.autoRefreshStart();
                break;
            case DESELECTED:
                folio.autoRefreshStop();
                break;
        }
    }
}
