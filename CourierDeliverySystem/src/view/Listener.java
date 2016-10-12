package view;

import java.awt.event.ActionListener;

/**
 * Created by Dave on 14/09/2016.
 */
public interface Listener extends ActionListener {
    void sendActionPerformed();
    void cancelActionPerformed(); 
}
