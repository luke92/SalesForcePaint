package gprog;

import java.awt.*;
import javax.swing.border.*;

public class GraphicsPanel extends javax.swing.JPanel {

    private GraphicsVector v;
    private Graphic d = null;
    private GProg f;

    public GraphicsPanel(MouseHandler ml, GProg f) {
        super();
        v = new GraphicsVector();
        addMouseListener(ml);
        addMouseMotionListener(ml);
        setBackground(Color.white);
        this.f = f;
        setBorder(new EtchedBorder());
    }
    
    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g = (Graphics2D) gr;
        v.draw(g);
        if (d != null) {
            d.draw(g);
        }
    }

    public void draw(Point p) {
        d.setEnd(p);
        redraw();
    }

    public void redraw() {
        f.setModified(true);
        repaint();
    }

    public void record(Point p) {
        v.addElement(d);
        d = null;
    }

    public void setStart(Point p, GProg f) {
        d = GraphicsFactory.getGraphic(f);
        d.setStart(p);
    }

    public void clear() {
        v = new GraphicsVector();
        d = null;
        repaint();
        f.setModified(false);
    }

    public void select(double x, double y) {
        Graphic g = v.select(x, y);
        if (g != null) {
            g.setSelected(true);
            redraw();
        }
    }

    public void deseleccionar() {
        v.deselect();
        redraw();
    }

    public void cambiarColor(double x, double y, Color c) {
        Graphic g = v.select(x, y);
        if (g != null) {
            g.setColor(c);
            redraw();
        }
    }

    public void borrar(double x, double y) {
        Graphic g = v.select(x, y);
        if (g != null) {
            v.remove(g);
            redraw();
        }
    }
}
