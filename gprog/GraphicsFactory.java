package gprog;

public abstract class GraphicsFactory {
  public static Graphic getGraphic(GProg f) {
    Graphic d;
    switch(f.getShape()) {
      case Graphic.ELLIPSE:
        d = new Graphic(Graphic.ELLIPSE);
        break;
      case Graphic.LINE:
        d = new Graphic(Graphic.LINE);
        break;
      case Graphic.RECTANGLE:
        d = new Graphic(Graphic.RECTANGLE);
        break;
      default:
        d = new Graphic(Graphic.LINE);
    }
    d.setColor(f.getColor());
    d.setStrokeColor(f.getStrokeColor());
    return d;
  }
}