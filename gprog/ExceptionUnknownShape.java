package gprog;

import javax.swing.*;

public class ExceptionUnknownShape extends RuntimeException {
  private String title;
  private String message;

  public ExceptionUnknownShape(String message, String title) {
    this.title = title;
    this.title = message;
  }

  public void showMessage() {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
  }
}

 