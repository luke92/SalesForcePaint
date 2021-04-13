package gprog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowHandler extends WindowAdapter {

    private static int cx = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
    private static int cy = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
    private JFrame f;
    private String[] res = {"Yes", "No"};
    private String message = "Sure?";
    private String title = "EXIT";
    private boolean confirm;

    public WindowHandler(JFrame f) {
        this(f, true);
    }

    public WindowHandler(JFrame f, boolean c) {
        this.f = f;
        this.confirm = c;
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public static int getCX() {
        return cx;
    }

    public static int getCY() {
        return cy;
    }

    public void setText(String[] s) {
        if (s.length == 4) {
            message = s[0];
            title = s[1];
            res[0] = s[2];
            res[1] = s[3];
        }
    }

    public void windowClosing(WindowEvent e) {
        close();
    }

    public void show() {
        f.pack();
        setVisible(f);
    }

    public void show(int width, int height) {
        f.setSize(width, height);
        setVisible(f);
    }

    public void show(int x, int y, double width, double height) {
        if (x < 0) {
            width = width + (2 * x);
            x = 0;
        }

        if (y < 0) {
            height = height + (2 * y);
            y = 0;
        }

        f.setSize((int) Math.round(width), (int) Math.round(height));
        f.setLocation(x, y);
        f.setVisible(true);
    }

    private void setVisible(JFrame f) {
        f.setLocation(cx - (f.getWidth() / 2), cy - (f.getHeight() / 2));
        f.setVisible(true);
    }

    public void close() {
        boolean close = true;
        if (confirm) {
            close = JOptionPane.showOptionDialog(f, message, title, JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, res, res[1]) == 0;
        }

        if (close) {
            System.exit(0);
        }
    }
}
