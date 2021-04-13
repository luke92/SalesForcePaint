package gprog;

import java.awt.event.*;

public class SelectionHandler implements ItemListener {
  private GProg f;

  public SelectionHandler(GProg f) {this.f = f;}
  
  @Override
  public void itemStateChanged(ItemEvent e) {
    String s = e.getSource().toString();

    if (s.equals("Line")) {
      f.setShape(Graphic.LINE);
    } else if (s.equals("Rectangle")) {
      f.setShape(Graphic.RECTANGLE);
    } else if (s.equals("Ellipse")) {
      f.setShape(Graphic.ELLIPSE);
    } else if(s.equals("Delete")){
      f.setOperation(GProg.DELETE);
    } else if(s.equals("Select")){
      f.setOperation(GProg.SELECT);
    } else if(s.equals("Fill")){
      f.setOperation(GProg.FILL);
    }
  }
}

