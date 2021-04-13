package gprog;

import java.awt.event.*;
import javax.swing.*;

public class ActionHandler implements ActionListener {
    private GProg f;

    public ActionHandler(GProg f) {
        this.f = f;
    }

    public void actionPerformed(ActionEvent e) {
        String s;

        f.getGraphicsPanel().deseleccionar();
        if (e.getSource() instanceof JComboBox) {
            s = (String) (((JComboBox) e.getSource()).getSelectedItem());
        } else {
            s = e.getActionCommand();
        }
        handleAction(s);
    }

    private void handleAction(String s) {
        if (s.equals("New")) {
            f.getGraphicsPanel().clear();
        } else if (s.equals("Close")) {

        } else if (s.equals("Exit")) {
            f.exit();
        } else if (s.equals("Delete")) {
            f.setOperation(GProg.DELETE);
        } else if (s.equals("Select")) {
            f.setOperation(GProg.SELECT);
        } else if (s.equals("Fill")) {
            f.setOperation(GProg.FILL);
        } else if (s.equals("Line")) {
            f.setShape(Graphic.LINE);
            f.setOperation(GProg.DRAW);
        } else if (s.equals("Rectangle")) {
            f.setShape(Graphic.RECTANGLE);
            f.setOperation(GProg.DRAW);
        } else if (s.equals("Ellipse")) {
            f.setShape(Graphic.ELLIPSE);
            f.setOperation(GProg.DRAW);
        }
    }
}
