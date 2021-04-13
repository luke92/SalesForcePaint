package gprog;

import java.awt.*;

public class Graphic {
  protected static final int LINE = 0;
  protected static final int RECTANGLE = 1;
  protected static final int ELLIPSE = 2;

  int ix = 0;
  int iy = 0;
  int fx = 0;
  int fy = 0;
  
  protected int[] pts = new int[4]; 
  protected Color color;
  protected Color strokeColor;
  protected boolean selected = false;
  int shape;
  
  public Graphic(int shape) {
      this.shape = shape;
  }
  
  public void draw(Graphics2D g) {
    if (shape == ELLIPSE) {
        g.setColor(color);
        check(ELLIPSE);  
        g.fillOval(pts[0], pts[1], pts[2] - pts[0], pts[3] - pts[1]);
        g.setColor(strokeColor);
        g.drawOval(pts[0], pts[1], pts[2] - pts[0], pts[3] - pts[1]);
        if (selected) 
            drawSelection(g);
    } else if (shape == LINE) {
        g.setColor(color);
        check(LINE);
        g.drawLine(pts[0], pts[1], pts[2], pts[3]);
        if (selected)
          drawSelection(g);        
    } else if (shape == RECTANGLE) {
        g.setColor(color);
        check(RECTANGLE);
        g.fillRect(pts[0], pts[1], pts[2] - pts[0], pts[3] - pts[1]);
        g.setColor(strokeColor);
        g.drawRect(pts[0], pts[1], pts[2] - pts[0], pts[3] - pts[1]);
        if (selected) 
          drawSelection(g);        
    } else {
      throw new ExceptionUnknownShape("Unknown shape " + shape, "ERROR");        
    }
  }
  
  public boolean select(double x, double y) {
      if (shape ==ELLIPSE) {
        if (x >= ix && x <= fx && y >= iy && y <= fy) {
          return true;
        } else {
          return false;
        }          
      } else if (shape == LINE) {
        // y = ax + b
        double a = ((double)(fy - iy)) / ((double)(fx - ix));
        double b = fy - ((double)(a * fx));
        double yy = (a * x) + b;
        yy -= y;

        if (Math.abs(yy) <= 1) {
          return true;
        } else {
          return false;
        }          
      } else if (shape == RECTANGLE) {
        if (x >= ix && x <= fx && y >= iy && y <= fy) {
          return true;
        } else {
          return false;
        } 
      }
      
      throw new ExceptionUnknownShape("Unknown shape " + shape, "ERROR");
  }

  public void setStart(Point p) {
    for (int i = 0; i < pts.length; i++) 
	    pts[i] = 0;
    ix = (int)p.getX();
    iy = (int)p.getY();
  }

  public Point getStart() {return new Point(ix, iy);}	

  public void setColor(Color c) {color = c;}
  public Color getColor() {return color;}
	
  public void setStrokeColor(Color c) {strokeColor = c;}		
  public Color getStrokeColor() {return strokeColor;}	
  
  public void setEnd(Point p) {
    fx = (int)p.getX();
    fy = (int)p.getY();
  }

  public Point getEnd() {return new Point(fx, fy);}

  public void setSelected(boolean b) {selected = b;}

  public void drawSelection(Graphics g) {
     if (strokeColor.equals(Color.black)) {
      g.setColor(Color.lightGray);
    } else {
      g.setColor(Color.black);
    }  
    
    g.fillRect(pts[0] - 8, pts[1] - 8, 6, 6);
    g.fillRect(pts[2] + 1, pts[3] + 1, 6, 6);
    g.drawRect(pts[0], pts[1], pts[2] - pts[0], pts[3] - pts[1]);
  }
  
  public String toString() {
    return "Start: [" + ix + ", " + iy + "] End: [" + fx + ", " + fy + "]. Color: " + color.toString();
  }

  public void check(int forma) {
    if (fx < ix && fy < iy) { 
      pts[0] = fx;
      pts[1] = fy;
      pts[2] = ix;
      pts[3] = iy;				
    } else if (forma != LINE & (fx >= ix && fy < iy)) {
      pts[0] = ix;
      pts[1] = fy;
      pts[2] = fx;
      pts[3] = iy;				
    } else if (forma != LINE & (fx < ix && fy >= iy)) {
      pts[0] = fx;
      pts[1] = iy;
      pts[2] = ix;
      pts[3] = fy;				
    } else {
      pts[0] = ix;
      pts[1] = iy;
      pts[2] = fx;
      pts[3] = fy;
    }
  }							
}