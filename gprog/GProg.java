package gprog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class GProg {

    protected static final String TITLE = "GProg: Untitled";
    public static final int DRAW = 0;
    public static final int SELECT = 1;
    public static final int FILL = 2;
    public static final int DELETE = 3;
    private final String[][] mitems = {
        {"New", "Close", "", "Exit"},
        {"Delete"}
    };

    private int[] center = new int[2];
    private Color color = Color.black;
    private int shape = Graphic.LINE;
    private Color strokeColor = Color.black;
    private boolean _modified = false;
    private JFrame f = new JFrame(TITLE);
    private GraphicsPanel p;
    private int operation = 0;
    private ActionHandler ma = new ActionHandler(this);
    private JComboBox cmb;
    WindowHandler wh = new WindowHandler(f);
    
    public void go() {        
        MouseHandler mh = new MouseHandler(this);
        JPanel pane = new JPanel();

        p = new GraphicsPanel(mh, this);

        pane.setLayout(new BorderLayout());
        pane.add(createButtonsPanel(ma), BorderLayout.NORTH);
        pane.add(createColorsPanel(mh), BorderLayout.SOUTH);
        pane.add(p, BorderLayout.CENTER);
        pane.setBorder(new CompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));
        f.setJMenuBar(createMenu(ma));
        f.setContentPane(new JScrollPane(pane));
        f.addWindowListener(wh);
        wh.show(750, 500);
    }

    private JMenuBar createMenu(ActionHandler ma) {
        JMenuBar mb = new JMenuBar();
        String[] menues = {"File", "Edit"};
        JMenu menu;

        for (int i = 0; i < menues.length; i++) {
            menu = new JMenu(menues[i]);

            for (int j = 0; j < mitems[i].length; j++) {
                if (!mitems[i][j].equals("")) {
                    JMenuItem mi = new JMenuItem(mitems[i][j]);
                    mi.addActionListener(ma);
                    menu.add(mi);
                } else {
                    menu.addSeparator();
                }
            }
            mb.add(menu);
            /*if (i == menues.length - 1)
	      mb.setHelpMenu(menu); */
        }
        return mb;
    }

    private JPanel createButtonsPanel(ActionHandler ma) {
        String[] action = {"New", "Exit", "Delete", "Select",
            "Fill", "Line", "Rectangle", "Ellipse"};

        JPanel p = new JPanel();
        JToolBar tb = new JToolBar();
        ButtonGroup bg = new ButtonGroup();

        cmb = new JComboBox();
        for (int j = 4; j < action.length; j++) {
            cmb.addItem(action[j]);
        }

        cmb.setSelectedItem("Line");
        cmb.addActionListener(ma);
        cmb.setEditable(false);
        p.add(cmb);

        for (int i = 0; i < action.length; i++) {
            if (action[i].equals("")) {
                tb.addSeparator();
            } else {
                JButton b = new JButton();
                b.setText(action[i]);
                //b.setIcon(new ImageIcon("./icons/" + accion[i] + ".gif"));
                b.setToolTipText(action[i]);
                b.addActionListener(ma);
                b.setActionCommand(action[i]);
                tb.add(b);
                bg.add(b);
            }
        }
        p.add(tb);
        p.setBorder(new EtchedBorder());
        return p;
    }

    private JPanel createColorsPanel(MouseListener ml) {
        final JColorChooser cc = new JColorChooser();
        cc.getSelectionModel().addChangeListener(
                new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                setColor(cc.getColor());
            }
        }
        );
        cc.setPreviewPanel(new JPanel());
        JPanel[] p = cc.getChooserPanels();
        p[0].setBorder(new EtchedBorder());
        p[0].addMouseListener(ml);
        return p[0];
    }

    public GraphicsPanel getGraphicsPanel() {
        return p;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        color = c;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color c) {
        strokeColor = c;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int f) {
        shape = f;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int op) {
        operation = op;
        switch (op) {
            case DRAW:
                switch (getShape()) {
                    case Graphic.LINE:
                        cmb.setSelectedItem("Line");
                        break;
                    case Graphic.ELLIPSE:
                        cmb.setSelectedItem("Ellipse");
                        break;
                    case Graphic.RECTANGLE:
                        cmb.setSelectedItem("Rectangle");
                        break;
                }
                break;
            case SELECT:
                cmb.setSelectedItem("Select");
                break;
            case FILL:
                cmb.setSelectedItem("Fill");
                break;
            case DELETE:
                cmb.setSelectedItem("Delte");
                break;
        }
    }

    public boolean getModified() {
        return _modified;
    }

    public void setModified(boolean e) {
        if (_modified != e) {
            _modified = e;
            if (_modified) {
                f.setTitle(f.getTitle() + " [Modified]");
            } else {
                f.setTitle(TITLE);
            }
        }
    }

    public JFrame getFrame() {
        return f;
    }

    void exit() {
        wh.close();
    }
}
