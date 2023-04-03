package org.hsqldb.util;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.Hashtable;

class ConnectionDialog extends Dialog implements ActionListener, ItemListener {
  protected Connection mConnection;
  
  protected TextField mName;
  
  protected TextField mDriver;
  
  protected TextField mURL;
  
  protected TextField mUser;
  
  protected TextField mPassword;
  
  protected Label mError;
  
  private String[][] connTypes;
  
  private Hashtable settings;
  
  private Choice types;
  
  private Choice recent;
  
  public static Connection createConnection(String paramString1, String paramString2, String paramString3, String paramString4) throws Exception {
    Class.forName(paramString1).newInstance();
    return DriverManager.getConnection(paramString2, paramString3, paramString4);
  }
  
  ConnectionDialog(Frame paramFrame, String paramString) {
    super(paramFrame, paramString, true);
  }
  
  private void create() {
    Panel panel2;
    Panel panel3;
    Panel panel4;
    Panel panel5;
    Dimension dimension1 = Toolkit.getDefaultToolkit().getScreenSize();
    setLayout(new BorderLayout());
    Panel panel1 = new Panel(new BorderLayout());
    if (dimension1.width >= 640) {
      panel2 = new Panel(new GridLayout(8, 1, 10, 10));
      panel3 = new Panel(new GridLayout(8, 1, 10, 10));
      panel4 = new Panel(new GridLayout(1, 2, 10, 10));
      panel5 = new Panel(new GridLayout(8, 1, 10, 10));
    } else {
      panel2 = new Panel(new GridLayout(8, 1));
      panel3 = new Panel(new GridLayout(8, 1));
      panel4 = new Panel(new GridLayout(1, 2));
      panel5 = new Panel(new GridLayout(8, 1));
    } 
    panel1.add("West", panel2);
    panel1.add("Center", panel3);
    panel1.add("South", panel4);
    panel1.add("North", createLabel(""));
    panel1.add("East", panel5);
    panel1.setBackground(SystemColor.control);
    panel3.setBackground(SystemColor.control);
    panel2.setBackground(SystemColor.control);
    panel4.setBackground(SystemColor.control);
    panel2.add(createLabel("Recent:"));
    this.recent = new Choice();
    try {
      this.settings = ConnectionDialogCommon.loadRecentConnectionSettings();
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
    this.recent.add(ConnectionDialogCommon.emptySettingName);
    Enumeration<ConnectionSetting> enumeration = this.settings.elements();
    while (enumeration.hasMoreElements())
      this.recent.add(((ConnectionSetting)enumeration.nextElement()).getName()); 
    this.recent.addItemListener(new ItemListener() {
          public void itemStateChanged(ItemEvent param1ItemEvent) {
            String str = (String)param1ItemEvent.getItem();
            ConnectionSetting connectionSetting = (ConnectionSetting)ConnectionDialog.this.settings.get(str);
            if (connectionSetting != null) {
              ConnectionDialog.this.mName.setText(connectionSetting.getName());
              ConnectionDialog.this.mDriver.setText(connectionSetting.getDriver());
              ConnectionDialog.this.mURL.setText(connectionSetting.getUrl());
              ConnectionDialog.this.mUser.setText(connectionSetting.getUser());
              ConnectionDialog.this.mPassword.setText(connectionSetting.getPassword());
            } 
          }
        });
    panel3.add(this.recent);
    Button button = new Button("Clr");
    button.setActionCommand("Clear");
    button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent param1ActionEvent) {
            ConnectionDialogCommon.deleteRecentConnectionSettings();
            ConnectionDialog.this.settings = new Hashtable<Object, Object>();
            ConnectionDialog.this.recent.removeAll();
            ConnectionDialog.this.recent.add(ConnectionDialogCommon.emptySettingName);
            ConnectionDialog.this.mName.setText((String)null);
          }
        });
    panel5.add(button);
    panel2.add(createLabel("Setting Name:"));
    this.mName = new TextField("");
    panel3.add(this.mName);
    panel2.add(createLabel("Type:"));
    this.types = new Choice();
    this.connTypes = ConnectionDialogCommon.getTypes();
    for (byte b = 0; b < this.connTypes.length; b++)
      this.types.add(this.connTypes[b][0]); 
    this.types.addItemListener(this);
    panel3.add(this.types);
    panel2.add(createLabel("Driver:"));
    this.mDriver = new TextField(this.connTypes[0][1]);
    panel3.add(this.mDriver);
    panel2.add(createLabel("URL:"));
    this.mURL = new TextField(this.connTypes[0][2]);
    this.mURL.addActionListener(this);
    panel3.add(this.mURL);
    panel2.add(createLabel("User:"));
    this.mUser = new TextField("SA");
    this.mUser.addActionListener(this);
    panel3.add(this.mUser);
    panel2.add(createLabel("Password:"));
    this.mPassword = new TextField("");
    this.mPassword.addActionListener(this);
    this.mPassword.setEchoChar('*');
    panel3.add(this.mPassword);
    button = new Button("Ok");
    button.setActionCommand("ConnectOk");
    button.addActionListener(this);
    panel4.add(button);
    button = new Button("Cancel");
    button.setActionCommand("ConnectCancel");
    button.addActionListener(this);
    panel4.add(button);
    add("East", createLabel(""));
    add("West", createLabel(""));
    this.mError = new Label("");
    Panel panel6 = createBorderPanel(this.mError);
    add("South", panel6);
    add("North", createLabel(""));
    add("Center", panel1);
    doLayout();
    pack();
    Dimension dimension2 = getSize();
    if (dimension1.width >= 640) {
      setLocation((dimension1.width - dimension2.width) / 2, (dimension1.height - dimension2.height) / 2);
    } else {
      setLocation(0, 0);
      setSize(dimension1);
    } 
    show();
  }
  
  public static Connection createConnection(Frame paramFrame, String paramString) {
    ConnectionDialog connectionDialog = new ConnectionDialog(paramFrame, paramString);
    connectionDialog.create();
    return connectionDialog.mConnection;
  }
  
  protected static Label createLabel(String paramString) {
    Label label = new Label(paramString);
    label.setBackground(SystemColor.control);
    return label;
  }
  
  protected static Panel createBorderPanel(Component paramComponent) {
    Panel panel = new Panel();
    panel.setBackground(SystemColor.control);
    panel.setLayout(new BorderLayout());
    panel.add("Center", paramComponent);
    panel.add("North", createLabel(""));
    panel.add("South", createLabel(""));
    panel.add("East", createLabel(""));
    panel.add("West", createLabel(""));
    panel.setBackground(SystemColor.control);
    return panel;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    String str = paramActionEvent.getActionCommand();
    if (str.equals("ConnectOk") || paramActionEvent.getSource() instanceof TextField) {
      try {
        if (this.mURL.getText().indexOf('«') >= 0)
          throw new Exception("please specify db path"); 
        this.mConnection = createConnection(this.mDriver.getText(), this.mURL.getText(), this.mUser.getText(), this.mPassword.getText());
        if (this.mName.getText() != null && this.mName.getText().trim().length() != 0) {
          ConnectionSetting connectionSetting = new ConnectionSetting(this.mName.getText(), this.mDriver.getText(), this.mURL.getText(), this.mUser.getText(), this.mPassword.getText());
          ConnectionDialogCommon.addToRecentConnectionSettings(this.settings, connectionSetting);
        } 
        dispose();
      } catch (IOException iOException) {
        dispose();
      } catch (Exception exception) {
        exception.printStackTrace();
        this.mError.setText(exception.toString());
      } 
    } else if (str.equals("ConnectCancel")) {
      dispose();
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqld\\util\ConnectionDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */