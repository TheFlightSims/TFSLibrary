package org.hsqldb.util;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class FontDialogSwing extends JDialog {
  private static boolean isRunning = false;
  
  private static final String BACKGROUND = "Background";
  
  private static String defaultFont = "Dialog";
  
  private static final String FOREGROUND = "Foreground";
  
  private static JButton bgColorButton;
  
  private static JCheckBox ckbbold;
  
  private static JButton closeButton;
  
  private static JButton fgColorButton;
  
  private static JComboBox fontsComboBox;
  
  private static JComboBox fontSizesComboBox;
  
  private static final String[] fontSizes = new String[] { 
      "8", "9", "10", "11", "12", "13", "14", "16", "18", "24", 
      "36" };
  
  private static DatabaseManagerSwing fOwner;
  
  private static JFrame frame = new JFrame("DataBaseManagerSwing Font Selection Dialog");
  
  private static JCheckBox ckbitalic;
  
  public static void creatFontDialog(DatabaseManagerSwing paramDatabaseManagerSwing) {
    if (isRunning) {
      frame.setVisible(true);
    } else {
      CommonSwing.setSwingLAF(frame, CommonSwing.Native);
      fOwner = paramDatabaseManagerSwing;
      frame.setIconImage(CommonSwing.getIcon("Frame"));
      isRunning = true;
      frame.setSize(600, 100);
      CommonSwing.setFramePositon(frame);
      ckbitalic = new JCheckBox(new ImageIcon(CommonSwing.getIcon("ItalicFont")));
      ckbitalic.putClientProperty("is3DEnabled", Boolean.TRUE);
      ckbitalic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent param1ActionEvent) {
              FontDialogSwing.setStyle();
            }
          });
      ckbbold = new JCheckBox(new ImageIcon(CommonSwing.getIcon("BoldFont")));
      ckbbold.putClientProperty("is3DEnabled", Boolean.TRUE);
      ckbbold.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent param1ActionEvent) {
              FontDialogSwing.setStyle();
            }
          });
      fgColorButton = new JButton("Foreground", new ImageIcon(CommonSwing.getIcon("ColorSelection")));
      fgColorButton.putClientProperty("is3DEnabled", Boolean.TRUE);
      fgColorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent param1ActionEvent) {
              FontDialogSwing.setColor("Foreground");
            }
          });
      bgColorButton = new JButton("Background", new ImageIcon(CommonSwing.getIcon("ColorSelection")));
      bgColorButton.putClientProperty("is3DEnabled", Boolean.TRUE);
      bgColorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent param1ActionEvent) {
              FontDialogSwing.setColor("Background");
            }
          });
      closeButton = new JButton("Close", new ImageIcon(CommonSwing.getIcon("Close")));
      closeButton.putClientProperty("is3DEnabled", Boolean.TRUE);
      closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent param1ActionEvent) {
              FontDialogSwing.frame.setVisible(false);
            }
          });
      GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
      String[] arrayOfString = graphicsEnvironment.getAvailableFontFamilyNames();
      Dimension dimension1 = new Dimension(160, 25);
      fontsComboBox = new JComboBox<String>(arrayOfString);
      fontsComboBox.putClientProperty("is3DEnabled", Boolean.TRUE);
      fontsComboBox.setMaximumSize(dimension1);
      fontsComboBox.setPreferredSize(dimension1);
      fontsComboBox.setMaximumSize(dimension1);
      fontsComboBox.setEditable(false);
      fontsComboBox.setSelectedItem(defaultFont);
      fontsComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent param1ActionEvent) {
              FontDialogSwing.setFont();
            }
          });
      fontSizesComboBox = new JComboBox<String>(fontSizes);
      Dimension dimension2 = new Dimension(45, 25);
      fontSizesComboBox.putClientProperty("is3DEnabled", Boolean.TRUE);
      fontSizesComboBox.setMinimumSize(dimension2);
      fontSizesComboBox.setPreferredSize(dimension2);
      fontSizesComboBox.setMaximumSize(dimension2);
      fontSizesComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent param1ItemEvent) {
              if (param1ItemEvent.getStateChange() == 1)
                FontDialogSwing.setFontSize((String)param1ItemEvent.getItem()); 
            }
          });
      Container container = frame.getContentPane();
      container.setLayout(new FlowLayout());
      container.add(fontsComboBox);
      container.add(fontSizesComboBox);
      container.add(ckbbold);
      container.add(ckbitalic);
      container.add(fgColorButton);
      container.add(bgColorButton);
      container.add(closeButton);
      frame.pack();
      frame.setVisible(false);
    } 
  }
  
  public static void setFont() {
    Font font1 = fOwner.txtResult.getFont();
    fOwner.txtResult.setFont(new Font(fontsComboBox.getSelectedItem().toString(), font1.getStyle(), font1.getSize()));
    Font font2 = fOwner.txtResult.getFont();
    fOwner.txtCommand.setFont(new Font(fontsComboBox.getSelectedItem().toString(), font2.getStyle(), font2.getSize()));
    Font font3 = fOwner.txtResult.getFont();
    fOwner.tTree.setFont(new Font(fontsComboBox.getSelectedItem().toString(), font3.getStyle(), font3.getSize()));
  }
  
  public static void setFontSize(String paramString) {
    Float float_ = new Float(paramString);
    float f = float_.floatValue();
    Font font1 = fOwner.tTree.getFont().deriveFont(f);
    fOwner.tTree.setFont(font1);
    Font font2 = fOwner.txtCommand.getFont().deriveFont(f);
    fOwner.txtCommand.setFont(font2);
    Font font3 = fOwner.txtResult.getFont().deriveFont(f);
    fOwner.txtResult.setFont(font3);
  }
  
  public static void setStyle() {
    int i = 0;
    if (ckbbold.isSelected())
      i |= 0x1; 
    if (ckbitalic.isSelected())
      i |= 0x2; 
    fOwner.tTree.setFont(fOwner.txtCommand.getFont().deriveFont(i));
    fOwner.txtCommand.setFont(fOwner.txtCommand.getFont().deriveFont(i));
    fOwner.txtResult.setFont(fOwner.txtResult.getFont().deriveFont(i));
  }
  
  public static void setColor(String paramString) {
    if (paramString.equals("Background")) {
      Color color = JColorChooser.showDialog(null, "DataBaseManagerSwing Choose Background Color", fOwner.txtResult.getBackground());
      if (color != null) {
        bgColorButton.setBackground(color);
        fOwner.txtCommand.setBackground(color);
        fOwner.txtResult.setBackground(color);
      } 
    } else {
      Color color = JColorChooser.showDialog(null, "DataBaseManagerSwing Choose Foreground Color", fOwner.txtResult.getForeground());
      if (color != null) {
        fgColorButton.setBackground(color);
        fOwner.txtCommand.setForeground(color);
        fOwner.txtResult.setForeground(color);
      } 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqld\\util\FontDialogSwing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */