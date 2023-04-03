package org.hsqldb.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

class CommonSwing {
  protected static String messagerHeader = "Database Manager Swing Error";
  
  protected static String Native = "Native";
  
  protected static String Java = "Java";
  
  protected static String Motif = "Motif";
  
  protected static String plaf = "plaf";
  
  protected static String GTK = "GTK";
  
  static Image getIcon(String paramString) {
    return paramString.equalsIgnoreCase("SystemCursor") ? (new ImageIcon(CommonSwing.class.getResource("Hourglass.gif"))).getImage() : (paramString.equalsIgnoreCase("Frame") ? (new ImageIcon(CommonSwing.class.getResource("hsqldb.gif"))).getImage() : (paramString.equalsIgnoreCase("Execute") ? (new ImageIcon(CommonSwing.class.getResource("run_exc.gif"))).getImage() : (paramString.equalsIgnoreCase("StatusRunning") ? (new ImageIcon(CommonSwing.class.getResource("RedCircle.gif"))).getImage() : (paramString.equalsIgnoreCase("StatusReady") ? (new ImageIcon(CommonSwing.class.getResource("GreenCircle.gif"))).getImage() : (paramString.equalsIgnoreCase("Clear") ? (new ImageIcon(CommonSwing.class.getResource("Clear.png"))).getImage() : (paramString.equalsIgnoreCase("Problem") ? (new ImageIcon(CommonSwing.class.getResource("problems.gif"))).getImage() : (paramString.equalsIgnoreCase("BoldFont") ? (new ImageIcon(CommonSwing.class.getResource("Bold.gif"))).getImage() : (paramString.equalsIgnoreCase("ItalicFont") ? (new ImageIcon(CommonSwing.class.getResource("Italic.gif"))).getImage() : (paramString.equalsIgnoreCase("ColorSelection") ? (new ImageIcon(CommonSwing.class.getResource("Colors.png"))).getImage() : (paramString.equalsIgnoreCase("Close") ? (new ImageIcon(CommonSwing.class.getResource("Close.png"))).getImage() : null))))))))));
  }
  
  protected static void errorMessage(String paramString) {
    Object[] arrayOfObject = { "OK" };
    JOptionPane.showOptionDialog(null, paramString, messagerHeader, -1, 2, null, arrayOfObject, arrayOfObject[0]);
  }
  
  public static void errorMessage(Exception paramException) {
    errorMessage(paramException, false);
  }
  
  public static void errorMessage(Exception paramException, boolean paramBoolean) {
    Object[] arrayOfObject = { "OK" };
    JOptionPane.showOptionDialog(null, paramException, messagerHeader, -1, 0, null, arrayOfObject, arrayOfObject[0]);
    if (!paramBoolean)
      paramException.printStackTrace(); 
  }
  
  static void setFramePositon(JFrame paramJFrame) {
    Dimension dimension1 = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension dimension2 = paramJFrame.getSize();
    if (dimension1.width >= 640) {
      paramJFrame.setLocation((dimension1.width - dimension2.width) / 2, (dimension1.height - dimension2.height) / 2);
    } else {
      paramJFrame.setLocation(0, 0);
      paramJFrame.setSize(dimension1);
    } 
  }
  
  static void setSwingLAF(Component paramComponent, String paramString) {
    try {
      if (paramString.equalsIgnoreCase(Native)) {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } else if (paramString.equalsIgnoreCase(Java)) {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } else if (paramString.equalsIgnoreCase(Motif)) {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      } 
      SwingUtilities.updateComponentTreeUI(paramComponent);
      if (paramComponent instanceof Frame)
        ((Frame)paramComponent).pack(); 
    } catch (Exception exception) {
      errorMessage(exception);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqld\\util\CommonSwing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */