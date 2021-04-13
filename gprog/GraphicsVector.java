package gprog;

public class GraphicsVector extends java.util.Vector {

  public GraphicsVector() {
    super(10, 10);
  }

  public Graphic select(double x, double y) {
    Graphic g = null;
    deselect();
    for (int i = elementCount - 1; i >= 0 && g == null; i--)
      if (((Graphic)elementAt(i)).select(x, y))
        g = (Graphic)elementAt(i);
    return g;
  }

  public void deselect(){
    for (int i = elementCount - 1 ; i >= 0; i--) {
      ((Graphic)elementAt(i)).setSelected(false);
    }
  }

  public void draw(java.awt.Graphics2D g) {
    for (int i = 0; i < elementCount; i++) {
      ((Graphic)elementAt(i)).draw(g);
    }
  }
}