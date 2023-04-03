package org.hsqldb.util;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import org.hsqldb.lib.RCData;
import org.hsqldb.lib.java.JavaSystem;

public class DatabaseManager extends Applet implements ActionListener, WindowListener, KeyListener {
  static final String NL = System.getProperty("line.separator");
  
  static final int iMaxRecent = 24;
  
  private static boolean TT_AVAILABLE = false;
  
  private static final String HELP_TEXT = "See the forums, mailing lists, and HSQLDB User Guide\nat http://hsqldb.org.\n\nPlease paste the following version identifier with any\nproblem reports or help requests:  $Revision: 5221 $" + (TT_AVAILABLE ? "" : "\n\nTransferTool classes are not in CLASSPATH.\nTo enable the Tools menu, add 'transfer.jar' to your class path.");
  
  private static final String ABOUT_TEXT = "$Revision: 5221 $ of DatabaseManager\n\nCopyright (c) 1995-2000, The Hypersonic SQL Group.\nCopyright (c) 2001-2011, The HSQL Development Group.\nhttp://hsqldb.org  (User Guide available at this site).\n\n\nYou may use and redistribute according to the HSQLDB\nlicense documented in the source code and at the web\nsite above." + (TT_AVAILABLE ? "\n\nTransferTool options are available." : "");
  
  Connection cConn;
  
  DatabaseMetaData dMeta;
  
  Statement sStatement;
  
  Menu mRecent;
  
  String[] sRecent;
  
  int iRecent;
  
  TextArea txtCommand;
  
  Button butExecute;
  
  Button butClear;
  
  Tree tTree;
  
  Panel pResult;
  
  long lTime;
  
  int iResult;
  
  Grid gResult;
  
  TextArea txtResult;
  
  boolean bHelp;
  
  Frame fMain;
  
  Image imgEmpty;
  
  static boolean bMustExit;
  
  String ifHuge = "";
  
  static String defDriver = "org.hsqldb.jdbcDriver";
  
  static String defURL = "jdbc:hsqldb:mem:.";
  
  static String defUser = "SA";
  
  static String defPassword = "";
  
  static String defScript;
  
  static String defDirectory;
  
  public void connect(Connection paramConnection) {
    if (paramConnection == null)
      return; 
    if (this.cConn != null)
      try {
        this.cConn.close();
      } catch (SQLException sQLException) {} 
    this.cConn = paramConnection;
    try {
      this.dMeta = this.cConn.getMetaData();
      this.sStatement = this.cConn.createStatement();
      refreshTree();
    } catch (SQLException sQLException) {
      sQLException.printStackTrace();
    } 
  }
  
  public void init() {
    DatabaseManager databaseManager = new DatabaseManager();
    databaseManager.main();
    try {
      databaseManager.connect(ConnectionDialog.createConnection(defDriver, defURL, defUser, defPassword));
      databaseManager.insertTestData();
      databaseManager.refreshTree();
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public static void threadedDBM() {
    System.getProperties().put("sun.java2d.noddraw", "true");
    Object object1 = null;
    Object object2 = null;
    boolean bool1 = false;
    boolean bool2 = false;
    bMustExit = false;
    DatabaseManager databaseManager = new DatabaseManager();
    databaseManager.main();
    Connection connection = null;
    try {
      connection = ConnectionDialog.createConnection(databaseManager.fMain, "Connect");
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    if (connection == null)
      return; 
    databaseManager.connect(connection);
  }
  
  public static void main(String[] paramArrayOfString) {
    System.getProperties().put("sun.java2d.noddraw", "true");
    String str1 = null;
    String str2 = null;
    boolean bool1 = false;
    boolean bool2 = false;
    bMustExit = true;
    for (byte b = 0; b < paramArrayOfString.length; b++) {
      String str3 = paramArrayOfString[b];
      String str4 = paramArrayOfString[b].toLowerCase();
      if (str4.startsWith("--"))
        str4 = str4.substring(1); 
      if (!str4.equals("-noexit") && !str4.equals("-help") && b == paramArrayOfString.length - 1)
        throw new IllegalArgumentException("No value for argument " + str3); 
      b++;
      if (str4.equals("-driver")) {
        defDriver = paramArrayOfString[b];
        bool1 = true;
      } else if (str4.equals("-url")) {
        defURL = paramArrayOfString[b];
        bool1 = true;
      } else if (str4.equals("-user")) {
        defUser = paramArrayOfString[b];
        bool1 = true;
      } else if (str4.equals("-password")) {
        defPassword = paramArrayOfString[b];
        bool1 = true;
      } else if (str4.equals("-urlid")) {
        str1 = paramArrayOfString[b];
        bool2 = true;
      } else if (str4.equals("-rcfile")) {
        str2 = paramArrayOfString[b];
        bool2 = true;
      } else if (str4.equals("-dir")) {
        defDirectory = paramArrayOfString[b];
      } else if (str4.equals("-script")) {
        defScript = paramArrayOfString[b];
      } else if (str4.equals("-noexit")) {
        bMustExit = false;
        b--;
      } else {
        if (str4.equals("-help")) {
          showUsage();
          return;
        } 
        throw new IllegalArgumentException("invalid argrument " + str3 + " try:  java... " + DatabaseManagerSwing.class.getName() + " --help");
      } 
    } 
    DatabaseManager databaseManager = new DatabaseManager();
    databaseManager.main();
    Connection connection = null;
    try {
      if (bool1 && bool2)
        throw new IllegalArgumentException("You may not specify both (urlid) AND (url/user/password)."); 
      if (bool1) {
        connection = ConnectionDialog.createConnection(defDriver, defURL, defUser, defPassword);
      } else if (bool2) {
        if (str1 == null)
          throw new IllegalArgumentException("You must specify an 'urlid' to use an RC file"); 
        bool1 = true;
        if (str2 == null)
          str2 = System.getProperty("user.home") + "/dbmanager.rc"; 
        connection = (new RCData(new File(str2), str1)).getConnection(null, System.getProperty("javax.net.ssl.trustStore"));
      } else {
        connection = ConnectionDialog.createConnection(databaseManager.fMain, "Connect");
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    if (connection == null)
      return; 
    databaseManager.connect(connection);
  }
  
  private static void showUsage() {
    System.out.println("Usage: java DatabaseManager [--options]\nwhere options include:\n    --help                show this message\n    --driver <classname>  jdbc driver class\n    --url <name>          jdbc url\n    --user <name>         username used for connection\n    --password <password> password for this user\n    --urlid <urlid>       use url/user/password/driver in rc file\n    --rcfile <file>       (defaults to 'dbmanager.rc' in home dir)\n    --dir <path>          default directory\n    --script <file>       reads from script file\n    --noexit              do not call system.exit()");
  }
  
  void insertTestData() {
    try {
      DatabaseManagerCommon.createTestTables(this.sStatement);
      refreshTree();
      this.txtCommand.setText(DatabaseManagerCommon.createTestData(this.sStatement));
      refreshTree();
      for (byte b = 0; b < DatabaseManagerCommon.testDataSql.length; b++)
        addToRecent(DatabaseManagerCommon.testDataSql[b]); 
      execute();
    } catch (SQLException sQLException) {
      sQLException.printStackTrace();
    } 
  }
  
  public void main() {
    this.fMain = new Frame("HSQL Database Manager");
    this.imgEmpty = createImage(new MemoryImageSource(2, 2, new int[16], 2, 2));
    this.fMain.setIconImage(this.imgEmpty);
    this.fMain.addWindowListener(this);
    MenuBar menuBar = new MenuBar();
    String[] arrayOfString1 = { "-Connect...", "--", "-Open Script...", "-Save Script...", "-Save Result...", "-Save Result csv...", "--", "-Exit" };
    addMenu(menuBar, "File", arrayOfString1);
    String[] arrayOfString2 = { "RRefresh Tree", "--", "GResults in Grid", "TResults in Text", "--", "1Shrink Tree", "2Enlarge Tree", "3Shrink Command", "4Enlarge Command" };
    addMenu(menuBar, "View", arrayOfString2);
    String[] arrayOfString3 = { 
        "SSELECT", "IINSERT", "UUPDATE", "DDELETE", "--", "-CREATE TABLE", "-DROP TABLE", "-CREATE INDEX", "-DROP INDEX", "--", 
        "-CHECKPOINT", "-SCRIPT", "-SET", "-SHUTDOWN", "--", "-Test Script" };
    addMenu(menuBar, "Command", arrayOfString3);
    Menu menu1 = new Menu("Recent");
    this.mRecent = new Menu("Recent");
    menuBar.add(this.mRecent);
    String[] arrayOfString4 = { 
        "-AutoCommit on", "-AutoCommit off", "OCommit", "LRollback", "--", "-Disable MaxRows", "-Set MaxRows to 100", "--", "-Logging on", "-Logging off", 
        "--", "-Insert test data" };
    addMenu(menuBar, "Options", arrayOfString4);
    String[] arrayOfString5 = { "-Dump", "-Restore", "-Transfer" };
    addMenu(menuBar, "Tools", arrayOfString5);
    Menu menu2 = new Menu("Help");
    MenuItem menuItem1 = new MenuItem("About");
    menuItem1.setShortcut(new MenuShortcut(65));
    menuItem1.addActionListener(this);
    menu2.add(menuItem1);
    MenuItem menuItem2 = new MenuItem("Help");
    menuItem2.setShortcut(new MenuShortcut(72));
    menuItem2.addActionListener(this);
    menu2.add(menuItem2);
    this.fMain.setMenuBar(menuBar);
    this.fMain.setSize(640, 480);
    this.fMain.add("Center", this);
    initGUI();
    this.sRecent = new String[24];
    Dimension dimension1 = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension dimension2 = this.fMain.getSize();
    if (dimension1.width >= 640) {
      this.fMain.setLocation((dimension1.width - dimension2.width) / 2, (dimension1.height - dimension2.height) / 2);
    } else {
      this.fMain.setLocation(0, 0);
      this.fMain.setSize(dimension1);
    } 
    this.fMain.show();
    if (defScript != null) {
      if (defDirectory != null)
        defScript = defDirectory + File.separator + defScript; 
      this.txtCommand.setText(DatabaseManagerCommon.readFile(defScript));
    } 
    this.txtCommand.requestFocus();
  }
  
  void addMenu(MenuBar paramMenuBar, String paramString, String[] paramArrayOfString) {
    Menu menu = new Menu(paramString);
    if (paramString.equals("Tools") && !TT_AVAILABLE)
      menu.setEnabled(false); 
    addMenuItems(menu, paramArrayOfString);
    paramMenuBar.add(menu);
  }
  
  void addMenuItems(Menu paramMenu, String[] paramArrayOfString) {
    for (byte b = 0; b < paramArrayOfString.length; b++) {
      MenuItem menuItem = new MenuItem(paramArrayOfString[b].substring(1));
      char c = paramArrayOfString[b].charAt(0);
      if (c != '-')
        menuItem.setShortcut(new MenuShortcut(c)); 
      menuItem.addActionListener(this);
      paramMenu.add(menuItem);
    } 
  }
  
  public void keyPressed(KeyEvent paramKeyEvent) {}
  
  public void keyReleased(KeyEvent paramKeyEvent) {}
  
  public void keyTyped(KeyEvent paramKeyEvent) {
    if (paramKeyEvent.getKeyChar() == '\n' && paramKeyEvent.isControlDown()) {
      paramKeyEvent.consume();
      execute();
    } 
  }
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    String str = paramActionEvent.getActionCommand();
    if (str == null && paramActionEvent.getSource() instanceof MenuItem)
      str = ((MenuItem)paramActionEvent.getSource()).getLabel(); 
    if (str != null)
      if (str.equals("Execute")) {
        execute();
      } else if (str.equals("Clear")) {
        clear();
      } else if (str.equals("Exit")) {
        windowClosing((WindowEvent)null);
      } else if (str.equals("Transfer")) {
        Transfer.work(null);
      } else if (str.equals("Dump")) {
        Transfer.work(new String[] { "-d" });
      } else if (str.equals("Restore")) {
        Transfer.work(new String[] { "-r" });
        refreshTree();
      } else if (str.equals("Logging on")) {
        JavaSystem.setLogToSystem(true);
      } else if (str.equals("Logging off")) {
        JavaSystem.setLogToSystem(false);
      } else if (str.equals("Help")) {
        showHelp(new String[] { "", HELP_TEXT });
      } else if (str.equals("About")) {
        showHelp(new String[] { "", ABOUT_TEXT });
      } else if (str.equals("Refresh Tree")) {
        refreshTree();
      } else if (str.startsWith("#")) {
        int i = Integer.parseInt(str.substring(1));
        this.txtCommand.setText(this.sRecent[i]);
      } else if (str.equals("Connect...")) {
        connect(ConnectionDialog.createConnection(this.fMain, "Connect"));
        refreshTree();
      } else if (str.equals("Results in Grid")) {
        this.iResult = 0;
        this.pResult.removeAll();
        this.pResult.add("Center", this.gResult);
        this.pResult.doLayout();
      } else if (str.equals("Open Script...")) {
        FileDialog fileDialog = new FileDialog(this.fMain, "Open Script", 0);
        if (defDirectory != null)
          fileDialog.setDirectory(defDirectory); 
        fileDialog.show();
        String str1 = fileDialog.getFile();
        if (str1 != null) {
          StringBuffer stringBuffer = new StringBuffer();
          this.ifHuge = DatabaseManagerCommon.readFile(fileDialog.getDirectory() + str1);
          if (4096 <= this.ifHuge.length()) {
            stringBuffer.append("This huge file cannot be edited.\n Please execute or clear\n");
            this.txtCommand.setText(stringBuffer.toString());
          } else {
            this.txtCommand.setText(this.ifHuge);
          } 
        } 
      } else if (str.equals("Save Script...")) {
        FileDialog fileDialog = new FileDialog(this.fMain, "Save Script", 1);
        if (defDirectory != null)
          fileDialog.setDirectory(defDirectory); 
        fileDialog.show();
        String str1 = fileDialog.getFile();
        if (str1 != null)
          DatabaseManagerCommon.writeFile(fileDialog.getDirectory() + str1, this.txtCommand.getText()); 
      } else if (str.equals("Save Result csv...")) {
        FileDialog fileDialog = new FileDialog(this.fMain, "Save Result CSV", 1);
        if (defDirectory != null)
          fileDialog.setDirectory(defDirectory); 
        fileDialog.show();
        String str1 = fileDialog.getDirectory();
        String str2 = fileDialog.getFile();
        if (str1 != null)
          str2 = str1 + "/" + str2; 
        if (str2 != null) {
          showResultInText();
          saveAsCsv(str2);
        } 
      } else if (str.equals("Save Result...")) {
        FileDialog fileDialog = new FileDialog(this.fMain, "Save Result", 1);
        if (defDirectory != null)
          fileDialog.setDirectory(defDirectory); 
        fileDialog.show();
        String str1 = fileDialog.getFile();
        if (str1 != null) {
          showResultInText();
          DatabaseManagerCommon.writeFile(fileDialog.getDirectory() + str1, this.txtResult.getText());
        } 
      } else if (str.equals("Results in Text")) {
        this.iResult = 1;
        this.pResult.removeAll();
        this.pResult.add("Center", this.txtResult);
        this.pResult.doLayout();
        showResultInText();
      } else if (str.equals("AutoCommit on")) {
        try {
          this.cConn.setAutoCommit(true);
        } catch (SQLException sQLException) {}
      } else if (str.equals("AutoCommit off")) {
        try {
          this.cConn.setAutoCommit(false);
        } catch (SQLException sQLException) {}
      } else if (str.equals("Enlarge Tree")) {
        Dimension dimension = this.tTree.getMinimumSize();
        dimension.width += 20;
        this.tTree.setMinimumSize(dimension);
        this.fMain.pack();
      } else if (str.equals("Shrink Tree")) {
        Dimension dimension = this.tTree.getMinimumSize();
        dimension.width -= 20;
        if (dimension.width >= 0)
          this.tTree.setMinimumSize(dimension); 
        this.fMain.pack();
      } else if (str.equals("Enlarge Command")) {
        this.txtCommand.setRows(this.txtCommand.getRows() + 1);
        this.fMain.pack();
      } else if (str.equals("Shrink Command")) {
        int i = this.txtCommand.getRows() - 1;
        this.txtCommand.setRows((i < 1) ? 1 : i);
        this.fMain.pack();
      } else if (str.equals("Commit")) {
        try {
          this.cConn.commit();
        } catch (SQLException sQLException) {}
      } else if (str.equals("Insert test data")) {
        insertTestData();
      } else if (str.equals("Rollback")) {
        try {
          this.cConn.rollback();
        } catch (SQLException sQLException) {}
      } else if (str.equals("Disable MaxRows")) {
        try {
          this.sStatement.setMaxRows(0);
        } catch (SQLException sQLException) {}
      } else if (str.equals("Set MaxRows to 100")) {
        try {
          this.sStatement.setMaxRows(100);
        } catch (SQLException sQLException) {}
      } else if (str.equals("SELECT")) {
        showHelp(DatabaseManagerCommon.selectHelp);
      } else if (str.equals("INSERT")) {
        showHelp(DatabaseManagerCommon.insertHelp);
      } else if (str.equals("UPDATE")) {
        showHelp(DatabaseManagerCommon.updateHelp);
      } else if (str.equals("DELETE")) {
        showHelp(DatabaseManagerCommon.deleteHelp);
      } else if (str.equals("CREATE TABLE")) {
        showHelp(DatabaseManagerCommon.createTableHelp);
      } else if (str.equals("DROP TABLE")) {
        showHelp(DatabaseManagerCommon.dropTableHelp);
      } else if (str.equals("CREATE INDEX")) {
        showHelp(DatabaseManagerCommon.createIndexHelp);
      } else if (str.equals("DROP INDEX")) {
        showHelp(DatabaseManagerCommon.dropIndexHelp);
      } else if (str.equals("CHECKPOINT")) {
        showHelp(DatabaseManagerCommon.checkpointHelp);
      } else if (str.equals("SCRIPT")) {
        showHelp(DatabaseManagerCommon.scriptHelp);
      } else if (str.equals("SHUTDOWN")) {
        showHelp(DatabaseManagerCommon.shutdownHelp);
      } else if (str.equals("SET")) {
        showHelp(DatabaseManagerCommon.setHelp);
      } else if (str.equals("Test Script")) {
        showHelp(DatabaseManagerCommon.testHelp);
      }  
  }
  
  void showHelp(String[] paramArrayOfString) {
    this.txtCommand.setText(paramArrayOfString[0]);
    this.txtResult.setText(paramArrayOfString[1]);
    this.bHelp = true;
    this.pResult.removeAll();
    this.pResult.add("Center", this.txtResult);
    this.pResult.doLayout();
    this.txtCommand.requestFocus();
    this.txtCommand.setCaretPosition(paramArrayOfString[0].length());
  }
  
  public void windowActivated(WindowEvent paramWindowEvent) {}
  
  public void windowDeactivated(WindowEvent paramWindowEvent) {}
  
  public void windowClosed(WindowEvent paramWindowEvent) {}
  
  public void windowClosing(WindowEvent paramWindowEvent) {
    try {
      if (this.cConn != null)
        this.cConn.close(); 
    } catch (Exception exception) {}
    this.fMain.dispose();
    if (bMustExit)
      System.exit(0); 
  }
  
  public void windowDeiconified(WindowEvent paramWindowEvent) {}
  
  public void windowIconified(WindowEvent paramWindowEvent) {}
  
  public void windowOpened(WindowEvent paramWindowEvent) {}
  
  void clear() {
    this.ifHuge = "";
    this.txtCommand.setText(this.ifHuge);
  }
  
  void execute() {
    String str = null;
    if (4096 <= this.ifHuge.length()) {
      str = this.ifHuge;
    } else {
      str = this.txtCommand.getText();
    } 
    if (str.startsWith("-->>>TEST<<<--")) {
      testPerformance();
      return;
    } 
    String[] arrayOfString = new String[1];
    this.lTime = System.currentTimeMillis();
    try {
      if (this.sStatement == null)
        return; 
      this.sStatement.execute(str);
      this.lTime = System.currentTimeMillis() - this.lTime;
      int i = this.sStatement.getUpdateCount();
      if (i == -1) {
        ResultSet resultSet = this.sStatement.getResultSet();
        try {
          formatResultSet(resultSet);
        } catch (Throwable throwable) {
          arrayOfString[0] = "Error displaying the ResultSet";
          this.gResult.setHead(arrayOfString);
          String str1 = throwable.getMessage();
          arrayOfString[0] = str1;
          this.gResult.addRow(arrayOfString);
        } 
      } else {
        arrayOfString[0] = "update count";
        this.gResult.setHead(arrayOfString);
        arrayOfString[0] = String.valueOf(i);
        this.gResult.addRow(arrayOfString);
      } 
      addToRecent(this.txtCommand.getText());
    } catch (SQLException sQLException) {
      this.lTime = System.currentTimeMillis() - this.lTime;
      arrayOfString[0] = "SQL Error";
      this.gResult.setHead(arrayOfString);
      String str1 = sQLException.getMessage();
      str1 = str1 + " / Error Code: " + sQLException.getErrorCode();
      str1 = str1 + " / State: " + sQLException.getSQLState();
      arrayOfString[0] = str1;
      this.gResult.addRow(arrayOfString);
    } 
    updateResult();
    System.gc();
  }
  
  void updateResult() {
    if (this.iResult == 0) {
      if (this.bHelp) {
        this.pResult.removeAll();
        this.pResult.add("Center", this.gResult);
        this.pResult.doLayout();
        this.bHelp = false;
      } 
      this.gResult.update();
      this.gResult.repaint();
    } else {
      showResultInText();
    } 
    this.txtCommand.selectAll();
    this.txtCommand.requestFocus();
  }
  
  void formatResultSet(ResultSet paramResultSet) {
    if (paramResultSet == null) {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "Result";
      this.gResult.setHead(arrayOfString);
      arrayOfString[0] = "(empty)";
      this.gResult.addRow(arrayOfString);
      return;
    } 
    try {
      ResultSetMetaData resultSetMetaData = paramResultSet.getMetaData();
      int i = resultSetMetaData.getColumnCount();
      String[] arrayOfString = new String[i];
      byte b;
      for (b = 1; b <= i; b++)
        arrayOfString[b - 1] = resultSetMetaData.getColumnLabel(b); 
      this.gResult.setHead(arrayOfString);
      while (paramResultSet.next()) {
        for (b = 1; b <= i; b++) {
          try {
            arrayOfString[b - 1] = paramResultSet.getString(b);
            if (paramResultSet.wasNull())
              arrayOfString[b - 1] = "(null)"; 
          } catch (SQLException sQLException) {
            arrayOfString[b - 1] = "(binary data)";
          } 
        } 
        this.gResult.addRow(arrayOfString);
      } 
      paramResultSet.close();
    } catch (SQLException sQLException) {}
  }
  
  void testPerformance() {
    String str = this.txtCommand.getText();
    StringBuffer stringBuffer = new StringBuffer();
    long l = 0L;
    for (byte b = 0; b < str.length(); b++) {
      char c = str.charAt(b);
      if (c != '\n')
        stringBuffer.append(c); 
    } 
    str = stringBuffer.toString();
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "ms";
    arrayOfString[1] = "count";
    arrayOfString[2] = "sql";
    arrayOfString[3] = "error";
    this.gResult.setHead(arrayOfString);
    int i = 1;
    this.lTime = System.currentTimeMillis() - this.lTime;
    while (!str.equals("")) {
      String str1;
      int j = str.indexOf(';');
      if (j != -1) {
        str1 = str.substring(0, j);
        str = str.substring(j + 1);
      } else {
        str1 = str;
        str = "";
      } 
      if (str1.startsWith("--#")) {
        i = Integer.parseInt(str1.substring(3));
        continue;
      } 
      if (str1.startsWith("--"))
        continue; 
      arrayOfString[2] = str1;
      long l1 = 0L;
      try {
        l1 = DatabaseManagerCommon.testStatement(this.sStatement, str1, i);
        l += l1;
        arrayOfString[0] = String.valueOf(l1);
        arrayOfString[1] = String.valueOf(i);
        arrayOfString[3] = "";
      } catch (SQLException sQLException) {
        arrayOfString[1] = "n/a";
        arrayOfString[0] = "n/a";
        arrayOfString[3] = sQLException.toString();
      } 
      this.gResult.addRow(arrayOfString);
      System.out.println(l1 + " ms : " + str1);
    } 
    arrayOfString[0] = "" + l;
    arrayOfString[1] = "total";
    arrayOfString[2] = "";
    this.gResult.addRow(arrayOfString);
    this.lTime = System.currentTimeMillis() - this.lTime;
    updateResult();
  }
  
  void saveAsCsv(String paramString) {
    try {
      File file = new File(paramString);
      CSVWriter cSVWriter = new CSVWriter(file, null);
      String[] arrayOfString = this.gResult.getHead();
      int i = arrayOfString.length;
      Vector<String[]> vector = this.gResult.getData();
      int j = vector.size();
      cSVWriter.writeHeader(arrayOfString);
      for (byte b = 0; b < j; b++) {
        String[] arrayOfString1 = vector.elementAt(b);
        String[] arrayOfString2 = new String[arrayOfString1.length];
        for (byte b1 = 0; b1 < arrayOfString1.length; b1++) {
          String str = arrayOfString1[b1];
          if (str.equals("(null)"))
            str = ""; 
          arrayOfString2[b1] = str;
        } 
        cSVWriter.writeData(arrayOfString2);
      } 
      cSVWriter.close();
    } catch (IOException iOException) {
      throw new RuntimeException("IOError: " + iOException.getMessage());
    } 
  }
  
  void showResultInText() {
    String[] arrayOfString = this.gResult.getHead();
    int i = arrayOfString.length;
    int[] arrayOfInt = new int[i];
    Vector<String[]> vector = this.gResult.getData();
    int j = vector.size();
    byte b1;
    for (b1 = 0; b1 < i; b1++)
      arrayOfInt[b1] = arrayOfString[b1].length(); 
    for (b1 = 0; b1 < j; b1++) {
      String[] arrayOfString1 = vector.elementAt(b1);
      for (byte b = 0; b < i; b++) {
        int k = arrayOfString1[b].length();
        if (k > arrayOfInt[b])
          arrayOfInt[b] = k; 
      } 
    } 
    StringBuffer stringBuffer = new StringBuffer();
    byte b2;
    for (b2 = 0; b2 < i; b2++) {
      stringBuffer.append(arrayOfString[b2]);
      for (int k = arrayOfString[b2].length(); k <= arrayOfInt[b2]; k++)
        stringBuffer.append(' '); 
    } 
    stringBuffer.append(NL);
    for (b2 = 0; b2 < i; b2++) {
      for (byte b = 0; b < arrayOfInt[b2]; b++)
        stringBuffer.append('-'); 
      stringBuffer.append(' ');
    } 
    stringBuffer.append(NL);
    for (b2 = 0; b2 < j; b2++) {
      String[] arrayOfString1 = vector.elementAt(b2);
      for (byte b = 0; b < i; b++) {
        stringBuffer.append(arrayOfString1[b]);
        for (int k = arrayOfString1[b].length(); k <= arrayOfInt[b]; k++)
          stringBuffer.append(' '); 
      } 
      stringBuffer.append(NL);
    } 
    stringBuffer.append(NL + j + " row(s) in " + this.lTime + " ms");
    this.txtResult.setText(stringBuffer.toString());
  }
  
  private void addToRecent(String paramString) {
    for (byte b = 0; b < 24; b++) {
      if (paramString.equals(this.sRecent[b]))
        return; 
    } 
    if (this.sRecent[this.iRecent] != null)
      this.mRecent.remove(this.iRecent); 
    this.sRecent[this.iRecent] = paramString;
    if (paramString.length() > 43)
      paramString = paramString.substring(0, 40) + "..."; 
    MenuItem menuItem = new MenuItem(paramString);
    menuItem.setActionCommand("#" + this.iRecent);
    menuItem.addActionListener(this);
    this.mRecent.insert(menuItem, this.iRecent);
    this.iRecent = (this.iRecent + 1) % 24;
  }
  
  private void initGUI() {
    Panel panel1 = new Panel();
    Panel panel2 = new Panel();
    this.pResult = new Panel();
    panel1.setLayout(new BorderLayout());
    panel2.setLayout(new BorderLayout());
    this.pResult.setLayout(new BorderLayout());
    Font font = new Font("Dialog", 0, 12);
    this.txtCommand = new TextArea(5, 40);
    this.txtCommand.addKeyListener(this);
    this.txtResult = new TextArea(20, 40);
    this.txtCommand.setFont(font);
    this.txtResult.setFont(new Font("Courier", 0, 12));
    this.butExecute = new Button("Execute");
    this.butClear = new Button("Clear");
    this.butExecute.addActionListener(this);
    this.butClear.addActionListener(this);
    panel2.add("East", this.butExecute);
    panel2.add("West", this.butClear);
    panel2.add("Center", this.txtCommand);
    this.gResult = new Grid();
    setLayout(new BorderLayout());
    this.pResult.add("Center", this.gResult);
    panel1.add("North", panel2);
    panel1.add("Center", this.pResult);
    this.fMain.add("Center", panel1);
    this.tTree = new Tree();
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    if (dimension.width >= 640) {
      this.tTree.setMinimumSize(new Dimension(200, 100));
    } else {
      this.tTree.setMinimumSize(new Dimension(80, 100));
    } 
    this.gResult.setMinimumSize(new Dimension(200, 300));
    this.fMain.add("West", this.tTree);
    doLayout();
    this.fMain.pack();
  }
  
  protected void refreshTree() {
    boolean bool = false;
    this.tTree.removeAll();
    try {
      bool = this.cConn.getAutoCommit();
      this.cConn.setAutoCommit(false);
      int i = Color.yellow.getRGB();
      int j = Color.orange.getRGB();
      int k = Color.red.getRGB();
      this.tTree.addRow("", this.dMeta.getURL(), "-", 0);
      String[] arrayOfString = { "TABLE", "GLOBAL TEMPORARY", "VIEW" };
      Vector<String> vector1 = new Vector();
      Vector<String> vector2 = new Vector();
      Vector<String> vector3 = new Vector();
      ResultSet resultSet = this.dMeta.getTables(null, null, null, arrayOfString);
      try {
        while (resultSet.next()) {
          vector1.addElement(resultSet.getString(2));
          vector2.addElement(resultSet.getString(3));
          vector3.addElement(resultSet.getString(5));
        } 
      } finally {
        resultSet.close();
      } 
      for (byte b = 0; b < vector2.size(); b++) {
        String str1 = vector2.elementAt(b);
        String str2 = vector1.elementAt(b);
        String str3 = "tab-" + str1 + "-";
        this.tTree.addRow(str3, str1, "+", i);
        String str4 = vector3.elementAt(b);
        if (str2 != null && !str2.trim().equals(""))
          this.tTree.addRow(str3 + "s", "schema: " + str2); 
        if (str4 != null && !str4.trim().equals(""))
          this.tTree.addRow(str3 + "r", " " + str4); 
        ResultSet resultSet1 = this.dMeta.getColumns(null, str2, str1, null);
        try {
          while (resultSet1.next()) {
            String str6 = resultSet1.getString(4);
            String str7 = str3 + "col-" + str6 + "-";
            this.tTree.addRow(str7, str6, "+", j);
            String str8 = resultSet1.getString(6);
            this.tTree.addRow(str7 + "t", "Type: " + str8);
            boolean bool1 = (resultSet1.getInt(11) != 0) ? true : false;
            this.tTree.addRow(str7 + "n", "Nullable: " + bool1);
          } 
        } finally {
          resultSet1.close();
        } 
        this.tTree.addRow(str3 + "ind", "Indices", "+", 0);
        ResultSet resultSet2 = this.dMeta.getIndexInfo(null, str2, str1, false, false);
        String str5 = null;
        try {
          while (resultSet2.next()) {
            boolean bool1 = resultSet2.getBoolean(4);
            String str6 = resultSet2.getString(6);
            String str7 = str3 + "ind-" + str6 + "-";
            if (str5 == null || !str5.equals(str6)) {
              this.tTree.addRow(str7, str6, "+", k);
              this.tTree.addRow(str7 + "u", "Unique: " + (!bool1 ? 1 : 0));
              str5 = str6;
            } 
            String str8 = resultSet2.getString(9);
            this.tTree.addRow(str7 + "c-" + str8 + "-", str8);
          } 
        } finally {
          resultSet2.close();
        } 
      } 
      this.tTree.addRow("p", "Properties", "+", 0);
      this.tTree.addRow("pu", "User: " + this.dMeta.getUserName());
      this.tTree.addRow("pr", "ReadOnly: " + this.cConn.isReadOnly());
      this.tTree.addRow("pa", "AutoCommit: " + this.cConn.getAutoCommit());
      this.tTree.addRow("pd", "Driver: " + this.dMeta.getDriverName());
      this.tTree.addRow("pp", "Product: " + this.dMeta.getDatabaseProductName());
      this.tTree.addRow("pv", "Version: " + this.dMeta.getDatabaseProductVersion());
    } catch (SQLException sQLException) {
      this.tTree.addRow("", "Error getting metadata:", "-", 0);
      this.tTree.addRow("-", sQLException.getMessage());
      this.tTree.addRow("-", sQLException.getSQLState());
    } finally {
      try {
        this.cConn.setAutoCommit(bool);
      } catch (SQLException sQLException) {}
    } 
    this.tTree.update();
  }
  
  static {
    try {
      Class.forName(DatabaseManager.class.getPackage().getName() + ".Transfer");
      TT_AVAILABLE = true;
    } catch (Throwable throwable) {}
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqld\\util\DatabaseManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */