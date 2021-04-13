package gprog;

import java.awt.event.*;
import java.awt.*;

public class MouseHandler implements MouseMotionListener, MouseListener {
    private Point inicio = new Point();
    private Point fin = new Point();
    private GraphicsPanel p;
    private GProg f;

    public MouseHandler(GProg f) {this.f = f;}

    @Override
    public void mouseDragged(MouseEvent e) {
      if (f.getOperation() == GProg.DRAW) {
	      fin.setLocation(e.getX(), e.getY());
	      p.draw(fin);
      }
    }

    @Override
    public void mousePressed(MouseEvent e) {
      switch (f.getOperation()) {
        case GProg.DRAW:
          if (inicio != null) {
		        inicio.setLocation(e.getX(), e.getY());
       	    p = (GraphicsPanel)e.getSource();
	          p.setStart(inicio, f);
          }
	        break;

        case GProg.FILL:
          break;

        case GProg.SELECT:
          break;
       }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      switch(f.getOperation()) {
        case GProg.DRAW:
         	inicio = null;
      		break;
        case GProg.FILL:
          f.getGraphicsPanel().cambiarColor(e.getX(), e.getY(), f.getColor());
          break;

        case GProg.SELECT:
          f.getGraphicsPanel().select(e.getX(), e.getY());
          break;

        case GProg.DELETE:
          f.getGraphicsPanel().borrar(e.getX(), e.getY());
          break;
       }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      if (f.getOperation() == GProg.DRAW) {
   	    if (inicio != null) {
      		p.record(fin);
	      } else {
		      inicio = new Point();
	      }
          fin.setLocation(0,0);
	        inicio.setLocation(0,0);
      }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
