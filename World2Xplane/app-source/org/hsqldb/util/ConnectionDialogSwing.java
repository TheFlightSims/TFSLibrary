package org.hsqldb.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

class ConnectionDialogSwing extends JDialog implements ActionListener, ItemListener {
  private static final long serialVersionUID = 1L;
  
  private Connection mConnection;
  
  private JTextField mName;
  
  private JTextField mDriver;
  
  private JTextField mURL;
  
  private JTextField mUser;
  
  private JPasswordField mPassword;
  
  private String[][] connTypes;
  
  private Hashtable settings;
  
  private JButton okCancel;
  
  private JButton clear;
  
  private JComboBox mSettingName = new JComboBox(loadRecentConnectionSettings());
  
  private static ConnectionSetting currentConnectionSetting = null;
  
  public static void setConnectionSetting(ConnectionSetting paramConnectionSetting) {
    currentConnectionSetting = paramConnectionSetting;
  }
  
  public static Connection createConnection(String paramString1, String paramString2, String paramString3, String paramString4) throws Exception {
    Class.forName(paramString1).newInstance();
    return DriverManager.getConnection(paramString2, paramString3, paramString4);
  }
  
  ConnectionDialogSwing(JFrame paramJFrame, String paramString) {
    super(paramJFrame, paramString, true);
  }
  
  private void create() {
    Box box1 = Box.createHorizontalBox();
    Box box2 = Box.createVerticalBox();
    Box box3 = Box.createVerticalBox();
    Box box4 = Box.createHorizontalBox();
    Box box5 = Box.createVerticalBox();
    Box box6 = Box.createHorizontalBox();
    box1.add(Box.createHorizontalStrut(10));
    box1.add(Box.createHorizontalGlue());
    box1.add(box2);
    box1.add(Box.createHorizontalStrut(10));
    box1.add(Box.createHorizontalGlue());
    box1.add(box3);
    box1.add(Box.createHorizontalStrut(10));
    box1.add(Box.createVerticalGlue());
    box1.add(box6);
    box1.add(Box.createVerticalGlue());
    box5.add(Box.createVerticalGlue());
    box5.add(Box.createVerticalStrut(10));
    box5.add(box1);
    box5.add(Box.createVerticalGlue());
    box5.add(Box.createVerticalStrut(10));
    box5.add(box4);
    box5.add(Box.createVerticalGlue());
    box5.add(Box.createVerticalStrut(10));
    box5.add(Box.createVerticalGlue());
    box2.add(createLabel("Recent Setting:"));
    box2.add(Box.createVerticalGlue());
    box2.add(createLabel("Setting Name:"));
    box2.add(Box.createVerticalGlue());
    box2.add(createLabel("Type:"));
    box2.add(Box.createVerticalGlue());
    box2.add(createLabel("Driver:"));
    box2.add(Box.createVerticalGlue());
    box2.add(createLabel("URL:"));
    box2.add(Box.createVerticalGlue());
    box2.add(createLabel("User:"));
    box2.add(Box.createVerticalGlue());
    box2.add(createLabel("Password:"));
    box2.add(Box.createVerticalGlue());
    box2.add(Box.createVerticalStrut(10));
    box3.add(Box.createVerticalGlue());
    this.mSettingName.setActionCommand("Select Setting");
    this.mSettingName.addActionListener(this);
    box3.add(this.mSettingName);
    box3.add(Box.createHorizontalGlue());
    this.mName = new JTextField();
    this.mName.addActionListener(this);
    box3.add(this.mName);
    this.clear = new JButton("Clear Names");
    this.clear.setActionCommand("Clear");
    this.clear.addActionListener(this);
    box4.add(this.clear);
    box4.add(Box.createHorizontalGlue());
    box4.add(Box.createHorizontalStrut(10));
    JComboBox<String> jComboBox = new JComboBox();
    this.connTypes = ConnectionDialogCommon.getTypes();
    for (byte b = 0; b < this.connTypes.length; b++)
      jComboBox.addItem(this.connTypes[b][0]); 
    jComboBox.addItemListener(this);
    box3.add(jComboBox);
    box3.add(Box.createVerticalGlue());
    this.mDriver = new JTextField(this.connTypes[0][1]);
    this.mDriver.addActionListener(this);
    box3.add(this.mDriver);
    this.mURL = new JTextField(this.connTypes[0][2]);
    this.mURL.addActionListener(this);
    box3.add(this.mURL);
    box3.add(Box.createVerticalGlue());
    this.mUser = new JTextField("SA");
    this.mUser.addActionListener(this);
    box3.add(this.mUser);
    box3.add(Box.createVerticalGlue());
    this.mPassword = new JPasswordField("");
    this.mPassword.addActionListener(this);
    box3.add(this.mPassword);
    box3.add(Box.createVerticalGlue());
    box3.add(Box.createVerticalStrut(10));
    box4.add(Box.createHorizontalGlue());
    box4.add(Box.createHorizontalStrut(10));
    this.okCancel = new JButton("     Ok      ");
    this.okCancel.setActionCommand("ConnectOk");
    this.okCancel.addActionListener(this);
    box4.add(this.okCancel);
    getRootPane().setDefaultButton(this.okCancel);
    box4.add(Box.createHorizontalGlue());
    box4.add(Box.createHorizontalStrut(20));
    this.okCancel = new JButton("  Cancel   ");
    this.okCancel.setActionCommand("ConnectCancel");
    this.okCancel.addActionListener(this);
    box4.add(this.okCancel);
    box4.add(Box.createHorizontalGlue());
    box4.add(Box.createHorizontalStrut(10));
    JPanel jPanel = new JPanel();
    jPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    jPanel.add("Center", box5);
    getContentPane().add("Center", jPanel);
    doLayout();
    pack();
    Dimension dimension1 = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension dimension2 = getSize();
    if (currentConnectionSetting != null) {
      this.mName.setText(currentConnectionSetting.getName());
      this.mDriver.setText(currentConnectionSetting.getDriver());
      this.mURL.setText(currentConnectionSetting.getUrl());
      this.mUser.setText(currentConnectionSetting.getUser());
      this.mPassword.setText(currentConnectionSetting.getPassword());
    } 
    if (dimension1.width >= 640) {
      setLocation((dimension1.width - dimension2.width) / 2, (dimension1.height - dimension2.height) / 2);
    } else {
      setLocation(0, 0);
      setSize(dimension1);
    } 
    setVisible(true);
  }
  
  public static Connection createConnection(JFrame paramJFrame, String paramString) {
    ConnectionDialogSwing connectionDialogSwing = new ConnectionDialogSwing(paramJFrame, paramString);
    try {
      SwingUtilities.updateComponentTreeUI(connectionDialogSwing);
    } catch (Exception exception) {
      CommonSwing.errorMessage(exception);
    } 
    connectionDialogSwing.create();
    return connectionDialogSwing.mConnection;
  }
  
  private static JLabel createLabel(String paramString) {
    return new JLabel(paramString);
  }
  
  public Vector loadRecentConnectionSettings() {
    Vector<String> vector = new Vector();
    this.settings = new Hashtable<Object, Object>();
    try {
      this.settings = ConnectionDialogCommon.loadRecentConnectionSettings();
      Iterator<ConnectionSetting> iterator = this.settings.values().iterator();
      vector.add(ConnectionDialogCommon.emptySettingName);
      while (iterator.hasNext())
        vector.add(((ConnectionSetting)iterator.next()).getName()); 
    } catch (IOException iOException) {
      CommonSwing.errorMessage(iOException);
    } 
    return vector;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    String str = paramActionEvent.getActionCommand();
    if (str.equals("ConnectOk") || paramActionEvent.getSource() instanceof JTextField) {
      try {
        if (this.mURL.getText().indexOf('«') >= 0)
          throw new Exception("please specify db path"); 
        this.mConnection = createConnection(this.mDriver.getText(), this.mURL.getText(), this.mUser.getText(), new String(this.mPassword.getPassword()));
        if (this.mName.getText() != null && this.mName.getText().trim().length() != 0) {
          ConnectionSetting connectionSetting = new ConnectionSetting(this.mName.getText(), this.mDriver.getText(), this.mURL.getText(), this.mUser.getText(), new String(this.mPassword.getPassword()));
          ConnectionDialogCommon.addToRecentConnectionSettings(this.settings, connectionSetting);
        } 
        dispose();
      } catch (SQLException sQLException) {
        this.mConnection = null;
        CommonSwing.errorMessage(sQLException, true);
      } catch (Exception exception) {
        CommonSwing.errorMessage(exception);
      } 
    } else if (str.equals("Select Setting")) {
      String str1 = (String)this.mSettingName.getSelectedItem();
      ConnectionSetting connectionSetting = (ConnectionSetting)this.settings.get(str1);
      if (connectionSetting != null) {
        this.mName.setText(connectionSetting.getName());
        this.mDriver.setText(connectionSetting.getDriver());
        this.mURL.setText(connectionSetting.getUrl());
        this.mUser.setText(connectionSetting.getUser());
        this.mPassword.setText(connectionSetting.getPassword());
      } 
    } else if (str.equals("ConnectCancel")) {
      dispose();
    } else if (str.equals("Clear")) {
      ConnectionDialogCommon.deleteRecentConnectionSettings();
      this.settings = new Hashtable<Object, Object>();
      this.mSettingName.removeAllItems();
      this.mSettingName.addItem(ConnectionDialogCommon.emptySettingName);
      this.mName.setText(null);
    } 
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent) {
    String str = (String)paramItemEvent.getItem();
    for (byte b = 0; b < this.connTypes.length; b++) {
      if (str.equals(this.connTypes[b][0])) {
        this.mDriver.setText(this.connTypes[b][1]);
        this.mURL.setText(this.connTypes[b][2]);
      } 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqld\\util\ConnectionDialogSwing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */