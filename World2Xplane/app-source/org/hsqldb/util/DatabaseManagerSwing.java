package org.hsqldb.util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.hsqldb.lib.RCData;
import org.hsqldb.lib.java.JavaSystem;

public class DatabaseManagerSwing extends JApplet implements ActionListener, WindowListener, KeyListener, MouseListener {
  private static String homedir = null;
  
  private boolean isOracle = false;
  
  ArrayList localActionList = new ArrayList();
  
  private JFrame jframe = null;
  
  private static final String DEFAULT_RCFILE = homedir + "/dbmanager.rc";
  
  private static boolean TT_AVAILABLE = false;
  
  private static final String HELP_TEXT = "See the HSQLDB Utilities Guide, forums and mailing lists \nat http://hsqldb.org.\n\nPlease paste the following version identifier with any\nproblem reports or help requests:  $Revision: 5221 $" + (TT_AVAILABLE ? "" : "\n\nTransferTool classes are not in CLASSPATH.\nTo enable the Tools menu, add 'transfer.jar' to your class path.");
  
  private static final String ABOUT_TEXT = "$Revision: 5221 $ of DatabaseManagerSwing\n\nCopyright (c) 2001-2010, The HSQL Development Group.\nhttp://hsqldb.org  (Utilities Guide available at this site).\n\n\nYou may use and redistribute according to the HSQLDB\nlicense documented in the source code and at the web\nsite above." + (TT_AVAILABLE ? "\n\nTransferTool options are available." : "");
  
  static final String NL = System.getProperty("line.separator");
  
  static final String NULL_STR = "[null]";
  
  static int iMaxRecent = 24;
  
  Connection cConn;
  
  Connection rowConn;
  
  DatabaseMetaData dMeta;
  
  Statement sStatement;
  
  JMenu mRecent;
  
  String[] sRecent;
  
  int iRecent;
  
  JTextArea txtCommand;
  
  JScrollPane txtCommandScroll;
  
  JButton butExecute;
  
  JTree tTree;
  
  JScrollPane tScrollPane;
  
  DefaultTreeModel treeModel;
  
  TableModel tableModel;
  
  DefaultMutableTreeNode rootNode;
  
  JPanel pResult;
  
  long lTime;
  
  GridSwing gResult;
  
  JTable gResultTable;
  
  JScrollPane gScrollPane;
  
  JTextArea txtResult;
  
  JScrollPane txtResultScroll;
  
  JSplitPane nsSplitPane;
  
  JSplitPane ewSplitPane;
  
  boolean bHelp;
  
  RootPaneContainer fMain;
  
  static boolean bMustExit;
  
  String sqlScriptBuffer = null;
  
  JToolBar jtoolbar;
  
  private boolean showSchemas = true;
  
  private boolean showTooltips = true;
  
  private boolean autoRefresh = true;
  
  private boolean gridFormat = true;
  
  static DatabaseManagerSwing refForFontDialogSwing;
  
  boolean displayRowCounts = false;
  
  boolean showSys = false;
  
  boolean showIndexDetails = true;
  
  String currentLAF = null;
  
  JPanel pStatus;
  
  static JButton iReadyStatus;
  
  JRadioButtonMenuItem rbAllSchemas = new JRadioButtonMenuItem("*");
  
  JMenuItem mitemAbout = new JMenuItem("About", 65);
  
  JMenuItem mitemHelp = new JMenuItem("Help", 72);
  
  JMenuItem mitemUpdateSchemas = new JMenuItem("Update Schemas");
  
  JCheckBoxMenuItem boxAutoCommit = new JCheckBoxMenuItem("Autocommit mode");
  
  JCheckBoxMenuItem boxLogging = new JCheckBoxMenuItem("Logging mode");
  
  JCheckBoxMenuItem boxShowSchemas = new JCheckBoxMenuItem("Show schemas");
  
  JCheckBoxMenuItem boxAutoRefresh = new JCheckBoxMenuItem("Auto-refresh tree");
  
  JCheckBoxMenuItem boxTooltips = new JCheckBoxMenuItem("Show Tooltips");
  
  JCheckBoxMenuItem boxRowCounts = new JCheckBoxMenuItem("Show row counts");
  
  JCheckBoxMenuItem boxShowGrid = new JCheckBoxMenuItem("Show results in Grid (a.o.t. Text)");
  
  JCheckBoxMenuItem boxShowSys = new JCheckBoxMenuItem("Show system tables");
  
  JRadioButtonMenuItem rbNativeLF = new JRadioButtonMenuItem("Native Look & Feel");
  
  JRadioButtonMenuItem rbJavaLF = new JRadioButtonMenuItem("Java Look & Feel");
  
  JRadioButtonMenuItem rbMotifLF = new JRadioButtonMenuItem("Motif Look & Feel");
  
  JLabel jStatusLine;
  
  static String READY_STATUS = "Ready";
  
  private static final String AUTOCOMMIT_BOX_TEXT = "Autocommit mode";
  
  private static final String LOGGING_BOX_TEXT = "Logging mode";
  
  private static final String SHOWSCHEMAS_BOX_TEXT = "Show schemas";
  
  private static final String AUTOREFRESH_BOX_TEXT = "Auto-refresh tree";
  
  private static final String SHOWTIPS_BOX_TEXT = "Show Tooltips";
  
  private static final String ROWCOUNTS_BOX_TEXT = "Show row counts";
  
  private static final String SHOWSYS_BOX_TEXT = "Show system tables";
  
  private static final String GRID_BOX_TEXT = "Show results in Grid (a.o.t. Text)";
  
  Cursor fMainCursor;
  
  Cursor txtCommandCursor;
  
  Cursor txtResultCursor;
  
  HashMap tipMap = new HashMap<Object, Object>();
  
  private JMenu mnuSchemas = new JMenu("Schemas");
  
  private final Cursor waitCursor = new Cursor(3);
  
  static String defDriver = "org.hsqldb.jdbcDriver";
  
  static String defURL = "jdbc:hsqldb:mem:.";
  
  static String defUser = "SA";
  
  static String defPassword = "";
  
  static String defScript;
  
  static String defDirectory;
  
  private String schemaFilter = null;
  
  private DBMPrefs prefs = null;
  
  Thread dummyThread = new Thread("dummy");
  
  private String busyText = null;
  
  private Runnable enableButtonRunnable = new Runnable() {
      public void run() {
        DatabaseManagerSwing.this.jbuttonClear.setEnabled(true);
        DatabaseManagerSwing.this.jbuttonExecute.setEnabled(true);
      }
    };
  
  private Runnable disableButtonRunnable = new Runnable() {
      public void run() {
        DatabaseManagerSwing.this.jbuttonClear.setEnabled(false);
        DatabaseManagerSwing.this.jbuttonExecute.setEnabled(false);
      }
    };
  
  private Thread buttonUpdaterThread = null;
  
  private static final int BUTTON_CHECK_PERIOD = 500;
  
  private Runnable buttonUpdater = new Runnable() {
      public void run() {
        while (true) {
          try {
            Thread.sleep(500L);
          } catch (InterruptedException interruptedException) {}
          if (DatabaseManagerSwing.this.buttonUpdaterThread == null)
            return; 
          boolean bool = (DatabaseManagerSwing.this.txtCommand.getText().length() > 0);
          if (DatabaseManagerSwing.this.jbuttonClear.isEnabled() != bool)
            SwingUtilities.invokeLater(bool ? DatabaseManagerSwing.this.enableButtonRunnable : DatabaseManagerSwing.this.disableButtonRunnable); 
        } 
      }
    };
  
  private JButton jbuttonClear;
  
  private JButton jbuttonExecute;
  
  private Runnable treeRefreshRunnable = new Runnable() {
      public void run() {
        try {
          DatabaseManagerSwing.this.directRefreshTree();
        } catch (RuntimeException runtimeException) {
          CommonSwing.errorMessage(runtimeException);
          throw runtimeException;
        } finally {
          DatabaseManagerSwing.this.setWaiting((String)null);
        } 
      }
    };
  
  private MouseEvent alreadyHandled = null;
  
  private static final String[] usertables = new String[] { "TABLE", "GLOBAL TEMPORARY", "VIEW", "SYSTEM TABLE" };
  
  private static final String[] nonSystables = new String[] { "TABLE", "GLOBAL TEMPORARY", "VIEW" };
  
  private static final HashSet oracleSysUsers = new HashSet();
  
  private static final String[] oracleSysSchemas = new String[] { 
      "SYS", "SYSTEM", "OUTLN", "DBSNMP", "OUTLN", "MDSYS", "ORDSYS", "ORDPLUGINS", "CTXSYS", "DSSYS", 
      "PERFSTAT", "WKPROXY", "WKSYS", "WMSYS", "XDB", "ANONYMOUS", "ODM", "ODM_MTR", "OLAPSYS", "TRACESVR", 
      "REPADMIN" };
  
  ActionListener schemaListListener = new ActionListener() {
      public void actionPerformed(ActionEvent param1ActionEvent) {
        DatabaseManagerSwing.this.schemaFilter = param1ActionEvent.getActionCommand();
        if (DatabaseManagerSwing.this.schemaFilter.equals("*"))
          DatabaseManagerSwing.this.schemaFilter = null; 
        DatabaseManagerSwing.this.refreshTree();
      }
    };
  
  private static final String tString = Boolean.TRUE.toString();
  
  private static final String fString = Boolean.FALSE.toString();
  
  public DatabaseManagerSwing() {
    this.jframe = new JFrame("HSQLDB DatabaseManager");
    this.fMain = this.jframe;
  }
  
  public DatabaseManagerSwing(JFrame paramJFrame) {
    this.jframe = paramJFrame;
    this.fMain = this.jframe;
  }
  
  public void init() {
    this.fMain = this;
    main();
    for (byte b = 0; b < this.localActionList.size(); b++) {
      AbstractButton abstractButton = this.localActionList.get(b);
      abstractButton.setEnabled(false);
    } 
    Connection connection = null;
    boolean bool = false;
    if (getParameter("jdbcDriver") != null) {
      bool = true;
      defDriver = getParameter("jdbcDriver");
    } 
    if (getParameter("jdbcUrl") != null) {
      bool = true;
      defURL = getParameter("jdbcUrl");
    } 
    if (getParameter("jdbcUser") != null) {
      bool = true;
      defUser = getParameter("jdbcUser");
    } 
    if (getParameter("jdbcPassword") != null) {
      bool = true;
      defPassword = getParameter("jdbcPassword");
    } 
    try {
      setWaiting("Initializing");
      connection = bool ? ConnectionDialogSwing.createConnection(defDriver, defURL, defUser, defPassword) : ConnectionDialogSwing.createConnection(this.jframe, "Connect");
    } catch (Exception exception) {
      CommonSwing.errorMessage(exception);
    } finally {
      setWaiting((String)null);
    } 
    if (connection != null)
      connect(connection); 
    if (getParameter("loadSampleData") != null && getParameter("loadSampleData").equals("true")) {
      insertTestData();
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException interruptedException) {}
      refreshTree();
    } 
    if (getParameter("schemaFilter") != null)
      this.schemaFilter = getParameter("schemaFilter"); 
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
    DatabaseManagerSwing databaseManagerSwing = new DatabaseManagerSwing(new JFrame("HSQL Database Manager"));
    refForFontDialogSwing = databaseManagerSwing;
    databaseManagerSwing.main();
    Connection connection = null;
    databaseManagerSwing.setWaiting("Initializing");
    try {
      if (bool1 && bool2)
        throw new IllegalArgumentException("You may not specify both (urlid) AND (url/user/password)."); 
      if (bool1) {
        connection = ConnectionDialogSwing.createConnection(defDriver, defURL, defUser, defPassword);
      } else if (bool2) {
        if (str1 == null)
          throw new IllegalArgumentException("You must specify an 'urlid' to use an RC file"); 
        bool1 = true;
        String str = (str2 == null) ? DEFAULT_RCFILE : str2;
        RCData rCData = new RCData(new File(str), str1);
        connection = rCData.getConnection(null, System.getProperty("javax.net.ssl.trustStore"));
      } else {
        connection = ConnectionDialogSwing.createConnection(databaseManagerSwing.jframe, "Connect");
      } 
    } catch (Exception exception) {
      CommonSwing.errorMessage(exception);
    } finally {
      databaseManagerSwing.setWaiting((String)null);
    } 
    if (connection != null)
      databaseManagerSwing.connect(connection); 
    FontDialogSwing.creatFontDialog(refForFontDialogSwing);
    databaseManagerSwing.start();
  }
  
  public void connect(Connection paramConnection) {
    this.schemaFilter = null;
    if (paramConnection == null)
      return; 
    if (this.cConn != null)
      try {
        this.cConn.close();
      } catch (SQLException sQLException) {
        CommonSwing.errorMessage(sQLException);
      }  
    this.cConn = paramConnection;
    this.rowConn = paramConnection;
    try {
      this.dMeta = this.cConn.getMetaData();
      this.isOracle = (this.dMeta.getDatabaseProductName().indexOf("Oracle") >= 0);
      this.sStatement = this.cConn.createStatement();
      updateAutoCommitBox();
      this.showIndexDetails = !this.isOracle;
      Driver driver = DriverManager.getDriver(this.dMeta.getURL());
      ConnectionSetting connectionSetting = new ConnectionSetting(this.dMeta.getDatabaseProductName(), driver.getClass().getName(), this.dMeta.getURL(), this.dMeta.getUserName().replaceAll("@localhost", ""), "");
      Hashtable hashtable = ConnectionDialogCommon.loadRecentConnectionSettings();
      ConnectionDialogCommon.addToRecentConnectionSettings(hashtable, connectionSetting);
      ConnectionDialogSwing.setConnectionSetting(connectionSetting);
      refreshTree();
      clearResultPanel();
      if (this.fMain instanceof JApplet)
        getAppletContext().showStatus("JDBC Connection established to a " + this.dMeta.getDatabaseProductName() + " v. " + this.dMeta.getDatabaseProductVersion() + " database as '" + this.dMeta.getUserName() + "'."); 
    } catch (SQLException sQLException) {
      CommonSwing.errorMessage(sQLException);
    } catch (IOException iOException) {
      CommonSwing.errorMessage(iOException);
    } catch (Exception exception) {
      CommonSwing.errorMessage(exception);
    } 
  }
  
  private static void showUsage() {
    System.out.println("Usage: java DatabaseManagerSwing [--options]\nwhere options include:\n    --help                show this message\n    --driver <classname>  jdbc driver class\n    --url <name>          jdbc url\n    --user <name>         username used for connection\n    --password <password> password for this user\n    --urlid <urlid>       use url/user/password/driver in rc file\n    --rcfile <file>       (defaults to 'dbmanager.rc' in home dir)\n    --dir <path>          default directory\n    --script <file>       reads from script file\n    --noexit              do not call system.exit()");
  }
  
  private void insertTestData() {
    try {
      DatabaseManagerCommon.createTestTables(this.sStatement);
      this.txtCommand.setText(DatabaseManagerCommon.createTestData(this.sStatement));
      for (byte b = 0; b < DatabaseManagerCommon.testDataSql.length; b++)
        addToRecent(DatabaseManagerCommon.testDataSql[b]); 
      executeCurrentSQL();
    } catch (SQLException sQLException) {
      CommonSwing.errorMessage(sQLException);
    } 
  }
  
  public void setMustExit(boolean paramBoolean) {
    this;
    bMustExit = paramBoolean;
  }
  
  public void main() {
    try {
      this.prefs = new DBMPrefs(this.fMain instanceof JApplet);
    } catch (Exception exception) {}
    if (this.prefs == null) {
      setLF(CommonSwing.Native);
    } else {
      this.autoRefresh = this.prefs.autoRefresh;
      this.displayRowCounts = this.prefs.showRowCounts;
      this.showSys = this.prefs.showSysTables;
      this.showSchemas = this.prefs.showSchemas;
      this.gridFormat = this.prefs.resultGrid;
      this.showTooltips = this.prefs.showTooltips;
      setLF(this.prefs.laf);
    } 
    this.fMain.getContentPane().add(createToolBar(), "North");
    if (this.fMain instanceof Frame)
      ((Frame)this.fMain).setIconImage(CommonSwing.getIcon("Frame")); 
    if (this.fMain instanceof Window)
      ((Window)this.fMain).addWindowListener(this); 
    JMenuBar jMenuBar = new JMenuBar();
    String[] arrayOfString1 = { "-Connect...", "--", "OOpen Script...", "-Save Script...", "-Save Result...", "--", "-Exit" };
    JMenu jMenu1 = addMenu(jMenuBar, "File", (Object[])arrayOfString1);
    for (byte b1 = 2; b1 < jMenu1.getItemCount(); b1++) {
      JMenuItem jMenuItem = jMenu1.getItem(b1);
      if (jMenuItem != null)
        this.localActionList.add(jMenuItem); 
    } 
    Object[] arrayOfObject1 = { "RRefresh Tree", this.boxAutoRefresh, "--", this.boxRowCounts, this.boxShowSys, this.boxShowSchemas, this.boxShowGrid };
    addMenu(jMenuBar, "View", arrayOfObject1);
    String[] arrayOfString2 = { 
        "SSELECT", "IINSERT", "UUPDATE", "DDELETE", "EEXECUTE", "---", "-CREATE TABLE", "-DROP TABLE", "-CREATE INDEX", "-DROP INDEX", 
        "--", "CCOMMIT*", "LROLLBACK*", "-CHECKPOINT*", "-SCRIPT", "-SET", "-SHUTDOWN", "--", "-Test Script" };
    addMenu(jMenuBar, "Command", (Object[])arrayOfString2);
    this.mRecent = new JMenu("Recent");
    this.mRecent.setMnemonic(82);
    jMenuBar.add(this.mRecent);
    ButtonGroup buttonGroup = new ButtonGroup();
    buttonGroup.add(this.rbNativeLF);
    buttonGroup.add(this.rbJavaLF);
    buttonGroup.add(this.rbMotifLF);
    this.boxShowSchemas.setSelected(this.showSchemas);
    this.boxShowGrid.setSelected(this.gridFormat);
    this.boxTooltips.setSelected(this.showTooltips);
    this.boxShowGrid.setAccelerator(KeyStroke.getKeyStroke(71, 2));
    this.boxAutoRefresh.setSelected(this.autoRefresh);
    this.boxRowCounts.setSelected(this.displayRowCounts);
    this.boxShowSys.setSelected(this.showSys);
    this.rbNativeLF.setActionCommand("LFMODE:" + CommonSwing.Native);
    this.rbJavaLF.setActionCommand("LFMODE:" + CommonSwing.Java);
    this.rbMotifLF.setActionCommand("LFMODE:" + CommonSwing.Motif);
    this.tipMap.put(this.mitemUpdateSchemas, "Refresh the schema list in this menu");
    this.tipMap.put(this.rbAllSchemas, "Display items in all schemas");
    this.tipMap.put(this.mitemAbout, "Display product information");
    this.tipMap.put(this.mitemHelp, "Display advice for obtaining help");
    this.tipMap.put(this.boxAutoRefresh, "Refresh tree (and schema list) automaticallywhen YOU modify database objects");
    this.tipMap.put(this.boxShowSchemas, "Display object names in tree-like schemaname.basename");
    this.tipMap.put(this.rbNativeLF, "Set Look and Feel to Native for your platform");
    this.tipMap.put(this.rbJavaLF, "Set Look and Feel to Java");
    this.tipMap.put(this.rbMotifLF, "Set Look and Feel to Motif");
    this.boxTooltips.setToolTipText("Display tooltips (hover text), like this");
    this.tipMap.put(this.boxAutoCommit, "Shows current Auto-commit mode.  Click to change");
    this.tipMap.put(this.boxLogging, "Shows current JDBC DriverManager logging mode.  Click to change");
    this.tipMap.put(this.boxShowSys, "Show system tables in table tree to the left");
    this.tipMap.put(this.boxShowGrid, "Show query results in grid (in text if off)");
    this.tipMap.put(this.boxRowCounts, "Show row counts with table names in tree");
    this.boxAutoRefresh.setMnemonic(67);
    this.boxShowSchemas.setMnemonic(89);
    this.boxAutoCommit.setMnemonic(65);
    this.boxShowSys.setMnemonic(89);
    this.boxShowGrid.setMnemonic(71);
    this.boxRowCounts.setMnemonic(67);
    this.boxLogging.setMnemonic(76);
    this.rbAllSchemas.setMnemonic(151);
    this.rbNativeLF.setMnemonic(78);
    this.rbJavaLF.setMnemonic(74);
    this.rbMotifLF.setMnemonic(77);
    this.mitemUpdateSchemas.setMnemonic(85);
    Object[] arrayOfObject2 = { 
        this.rbNativeLF, this.rbJavaLF, this.rbMotifLF, "--", "-Set Fonts", "--", this.boxAutoCommit, "--", "-Disable MaxRows", "-Set MaxRows to 100", 
        "--", this.boxLogging, "--", "-Insert test data" };
    addMenu(jMenuBar, "Options", arrayOfObject2);
    String[] arrayOfString3 = { "-Dump", "-Restore", "-Transfer" };
    jMenu1 = addMenu(jMenuBar, "Tools", (Object[])arrayOfString3);
    jMenu1.setEnabled(TT_AVAILABLE);
    this.localActionList.add(jMenu1);
    for (byte b2 = 0; b2 < jMenu1.getItemCount(); b2++) {
      JMenuItem jMenuItem = jMenu1.getItem(b2);
      if (jMenuItem != null)
        this.localActionList.add(jMenuItem); 
    } 
    this.mnuSchemas.setMnemonic(83);
    jMenuBar.add(this.mnuSchemas);
    JMenu jMenu2 = new JMenu("Help");
    jMenu2.setMnemonic(72);
    jMenu2.add(this.mitemAbout);
    jMenu2.add(this.mitemHelp);
    jMenu2.add(this.boxTooltips);
    this.rbAllSchemas.addActionListener(this.schemaListListener);
    this.mitemUpdateSchemas.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent param1ActionEvent) {
            DatabaseManagerSwing.this.updateSchemaList();
          }
        });
    this.mitemHelp.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent param1ActionEvent) {
            JOptionPane.showMessageDialog(DatabaseManagerSwing.this.fMain.getContentPane(), DatabaseManagerSwing.HELP_TEXT, "HELP", 1);
          }
        });
    this.mitemAbout.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent param1ActionEvent) {
            JOptionPane.showMessageDialog(DatabaseManagerSwing.this.fMain.getContentPane(), DatabaseManagerSwing.ABOUT_TEXT, "About", 1);
          }
        });
    this.boxTooltips.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent param1ActionEvent) {
            DatabaseManagerSwing.this.showTooltips = DatabaseManagerSwing.this.boxTooltips.isSelected();
            DatabaseManagerSwing.this.resetTooltips();
          }
        });
    jMenuBar.add(jMenu2);
    if (this.fMain instanceof JApplet) {
      ((JApplet)this.fMain).setJMenuBar(jMenuBar);
    } else if (this.fMain instanceof JFrame) {
      ((JFrame)this.fMain).setJMenuBar(jMenuBar);
    } 
    initGUI();
    this.sRecent = new String[iMaxRecent];
    if (!(this.fMain instanceof JApplet))
      CommonSwing.setFramePositon((JFrame)this.fMain); 
    ((Component)this.fMain).setVisible(true);
    if (defScript != null) {
      if (defDirectory != null)
        defScript = defDirectory + File.separator + defScript; 
      this.sqlScriptBuffer = DatabaseManagerCommon.readFile(defScript);
      if (4096 <= this.sqlScriptBuffer.length()) {
        int i = this.sqlScriptBuffer.indexOf('\n');
        if (i > 0)
          i = this.sqlScriptBuffer.indexOf('\n', i + 1); 
        if (i > 0)
          i = this.sqlScriptBuffer.indexOf('\n', i + 1); 
        if (i < 1)
          i = 100; 
        this.txtCommand.setText("............... Script File loaded: " + defScript + " ..................... \n" + "............... Click Execute or Clear " + "...................\n" + this.sqlScriptBuffer.substring(0, i + 1) + "..........................................." + "..............................\n" + "............................................." + "............................\n");
        this.txtCommand.setEnabled(false);
      } else {
        this.txtCommand.setText(this.sqlScriptBuffer);
        this.sqlScriptBuffer = null;
        this.txtCommand.setEnabled(true);
      } 
    } 
    resetTooltips();
    this.txtCommand.requestFocus();
  }
  
  private JMenu addMenu(JMenuBar paramJMenuBar, String paramString, Object[] paramArrayOfObject) {
    JMenu jMenu = new JMenu(paramString);
    jMenu.setMnemonic(paramString.charAt(0));
    addMenuItems(jMenu, paramArrayOfObject);
    paramJMenuBar.add(jMenu);
    return jMenu;
  }
  
  private void addMenuItems(JMenu paramJMenu, Object[] paramArrayOfObject) {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    for (byte b = 0; b < paramArrayOfObject.length; b++) {
      if (paramArrayOfObject[b].equals("--")) {
        paramJMenu.addSeparator();
      } else if (paramArrayOfObject[b].equals("---")) {
        if (dimension.width >= 640) {
          paramJMenu.addSeparator();
        } else {
          return;
        } 
      } else {
        JMenuItem jMenuItem;
        if (paramArrayOfObject[b] instanceof JMenuItem) {
          jMenuItem = (JMenuItem)paramArrayOfObject[b];
        } else if (paramArrayOfObject[b] instanceof String) {
          jMenuItem = new JMenuItem(((String)paramArrayOfObject[b]).substring(1));
          char c = ((String)paramArrayOfObject[b]).charAt(0);
          if (c != '-') {
            KeyStroke keyStroke = KeyStroke.getKeyStroke(c, 2);
            jMenuItem.setAccelerator(keyStroke);
          } 
        } else {
          throw new RuntimeException("Unexpected element for menu item creation: " + paramArrayOfObject[b].getClass().getName());
        } 
        jMenuItem.addActionListener(this);
        paramJMenu.add(jMenuItem);
      } 
    } 
  }
  
  public void keyPressed(KeyEvent paramKeyEvent) {}
  
  public void keyReleased(KeyEvent paramKeyEvent) {}
  
  public void keyTyped(KeyEvent paramKeyEvent) {
    if (paramKeyEvent.getKeyChar() == '\n' && paramKeyEvent.isControlDown()) {
      paramKeyEvent.consume();
      executeCurrentSQL();
    } 
  }
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    String str = paramActionEvent.getActionCommand();
    if (str == null && paramActionEvent.getSource() instanceof JMenuItem)
      str = ((JMenuItem)paramActionEvent.getSource()).getText(); 
    if (str != null)
      if (str.equals("Exit")) {
        windowClosing((WindowEvent)null);
      } else if (str.equals("Transfer")) {
        Transfer.work(null);
      } else if (str.equals("Dump")) {
        Transfer.work(new String[] { "-d" });
      } else if (str.equals("Restore")) {
        JOptionPane.showMessageDialog(this.fMain.getContentPane(), "Use Ctrl-R or the View menu to\nupdate nav. tree after Restoration", "Suggestion", 1);
        Transfer.work(new String[] { "-r" });
      } else if (str.equals("Logging mode")) {
        JavaSystem.setLogToSystem(this.boxLogging.isSelected());
      } else if (str.equals("Auto-refresh tree")) {
        this.autoRefresh = this.boxAutoRefresh.isSelected();
        refreshTree();
      } else if (str.equals("Refresh Tree")) {
        refreshTree();
      } else if (str.startsWith("#")) {
        int i = Integer.parseInt(str.substring(1));
        this.txtCommand.setText(this.sRecent[i]);
      } else if (str.equals("Connect...")) {
        Connection connection = null;
        try {
          setWaiting("Connecting");
          connection = ConnectionDialogSwing.createConnection(this.jframe, "Connect");
        } finally {
          setWaiting((String)null);
        } 
        connect(connection);
      } else if (str.equals("Show results in Grid (a.o.t. Text)")) {
        this.gridFormat = this.boxShowGrid.isSelected();
        displayResults();
      } else if (str.equals("Open Script...")) {
        JFileChooser jFileChooser = new JFileChooser(".");
        jFileChooser.setDialogTitle("Open Script...");
        if (defDirectory != null)
          jFileChooser.setCurrentDirectory(new File(defDirectory)); 
        int i = jFileChooser.showOpenDialog((Component)this.fMain);
        if (i == 0) {
          File file = jFileChooser.getSelectedFile();
          if (file != null) {
            this.sqlScriptBuffer = DatabaseManagerCommon.readFile(file.getAbsolutePath());
            if (4096 <= this.sqlScriptBuffer.length()) {
              int j = this.sqlScriptBuffer.indexOf('\n');
              if (j > 0)
                j = this.sqlScriptBuffer.indexOf('\n', j + 1); 
              if (j > 0)
                j = this.sqlScriptBuffer.indexOf('\n', j + 1); 
              if (j < 1)
                j = 100; 
              this.txtCommand.setText("............... Script File loaded: " + file + " ..................... \n" + "............... Click Execute or Clear " + "...................\n" + this.sqlScriptBuffer.substring(0, j + 1) + "........................................." + "................................\n" + "..........................................." + "..............................\n");
              this.txtCommand.setEnabled(false);
            } else {
              this.txtCommand.setText(this.sqlScriptBuffer);
              this.sqlScriptBuffer = null;
              this.txtCommand.setEnabled(true);
            } 
          } 
        } 
      } else if (str.equals("Save Script...")) {
        JFileChooser jFileChooser = new JFileChooser(".");
        jFileChooser.setDialogTitle("Save Script");
        if (defDirectory != null)
          jFileChooser.setCurrentDirectory(new File(defDirectory)); 
        int i = jFileChooser.showSaveDialog((Component)this.fMain);
        if (i == 0) {
          File file = jFileChooser.getSelectedFile();
          if (file != null)
            DatabaseManagerCommon.writeFile(file.getAbsolutePath(), this.txtCommand.getText()); 
        } 
      } else if (str.equals("Save Result...")) {
        JFileChooser jFileChooser = new JFileChooser(".");
        jFileChooser.setDialogTitle("Save Result...");
        if (defDirectory != null)
          jFileChooser.setCurrentDirectory(new File(defDirectory)); 
        int i = jFileChooser.showSaveDialog((Component)this.fMain);
        if (i == 0) {
          File file = jFileChooser.getSelectedFile();
          if (file != null) {
            showResultInText();
            DatabaseManagerCommon.writeFile(file.getAbsolutePath(), this.txtResult.getText());
          } 
        } 
      } else if (str.equals("Show system tables")) {
        this.showSys = this.boxShowSys.isSelected();
        refreshTree();
      } else if (str.equals("Show row counts")) {
        this.displayRowCounts = this.boxRowCounts.isSelected();
        refreshTree();
      } else if (str.startsWith("LFMODE:")) {
        setLF(str.substring("LFMODE:".length()));
      } else if (str.equals("Set Fonts")) {
        FontDialogSwing.creatFontDialog(refForFontDialogSwing);
      } else if (str.equals("Autocommit mode")) {
        try {
          this.cConn.setAutoCommit(this.boxAutoCommit.isSelected());
        } catch (SQLException sQLException) {
          this.boxAutoCommit.setSelected(!this.boxAutoCommit.isSelected());
          CommonSwing.errorMessage(sQLException);
        } 
      } else if (str.equals("COMMIT*")) {
        try {
          this.cConn.commit();
          showHelp(new String[] { "", "COMMIT executed" });
        } catch (SQLException sQLException) {
          CommonSwing.errorMessage(sQLException);
        } 
      } else if (str.equals("Insert test data")) {
        insertTestData();
        refreshTree();
      } else if (str.equals("ROLLBACK*")) {
        try {
          this.cConn.rollback();
          showHelp(new String[] { "", "ROLLBACK executed" });
        } catch (SQLException sQLException) {
          CommonSwing.errorMessage(sQLException);
        } 
      } else if (str.equals("Disable MaxRows")) {
        try {
          this.sStatement.setMaxRows(0);
        } catch (SQLException sQLException) {
          CommonSwing.errorMessage(sQLException);
        } 
      } else if (str.equals("Set MaxRows to 100")) {
        try {
          this.sStatement.setMaxRows(100);
        } catch (SQLException sQLException) {
          CommonSwing.errorMessage(sQLException);
        } 
      } else if (str.equals("SELECT")) {
        showHelp(DatabaseManagerCommon.selectHelp);
      } else if (str.equals("INSERT")) {
        showHelp(DatabaseManagerCommon.insertHelp);
      } else if (str.equals("UPDATE")) {
        showHelp(DatabaseManagerCommon.updateHelp);
      } else if (str.equals("DELETE")) {
        showHelp(DatabaseManagerCommon.deleteHelp);
      } else if (str.equals("EXECUTE")) {
        executeCurrentSQL();
      } else if (str.equals("CREATE TABLE")) {
        showHelp(DatabaseManagerCommon.createTableHelp);
      } else if (str.equals("DROP TABLE")) {
        showHelp(DatabaseManagerCommon.dropTableHelp);
      } else if (str.equals("CREATE INDEX")) {
        showHelp(DatabaseManagerCommon.createIndexHelp);
      } else if (str.equals("DROP INDEX")) {
        showHelp(DatabaseManagerCommon.dropIndexHelp);
      } else if (str.equals("CHECKPOINT*")) {
        try {
          this.cConn.createStatement().executeUpdate("CHECKPOINT");
          showHelp(new String[] { "", "CHECKPOINT executed" });
        } catch (SQLException sQLException) {
          CommonSwing.errorMessage(sQLException);
        } 
      } else if (str.equals("SCRIPT")) {
        showHelp(DatabaseManagerCommon.scriptHelp);
      } else if (str.equals("SHUTDOWN")) {
        showHelp(DatabaseManagerCommon.shutdownHelp);
      } else if (str.equals("SET")) {
        showHelp(DatabaseManagerCommon.setHelp);
      } else if (str.equals("Test Script")) {
        showHelp(DatabaseManagerCommon.testHelp);
      } else if (str.equals("Show schemas")) {
        this.showSchemas = this.boxShowSchemas.isSelected();
        refreshTree();
      } else {
        throw new RuntimeException("Unexpected action triggered: " + str);
      }  
  }
  
  private void displayResults() {
    if (this.gridFormat) {
      setResultsInGrid();
    } else {
      setResultsInText();
    } 
  }
  
  private void setResultsInGrid() {
    this.pResult.removeAll();
    this.pResult.add(this.gScrollPane, "Center");
    this.pResult.doLayout();
    this.gResult.fireTableChanged(null);
    this.pResult.repaint();
  }
  
  private void setResultsInText() {
    this.pResult.removeAll();
    this.pResult.add(this.txtResultScroll, "Center");
    this.pResult.doLayout();
    showResultInText();
    this.pResult.repaint();
  }
  
  private void showHelp(String[] paramArrayOfString) {
    this.txtCommand.setText(paramArrayOfString[0]);
    this.bHelp = true;
    this.pResult.removeAll();
    this.pResult.add(this.txtResultScroll, "Center");
    this.pResult.doLayout();
    this.txtResult.setText(paramArrayOfString[1]);
    this.pResult.repaint();
    this.txtCommand.requestFocus();
    this.txtCommand.setCaretPosition(paramArrayOfString[0].length());
  }
  
  public void windowActivated(WindowEvent paramWindowEvent) {}
  
  public void windowDeactivated(WindowEvent paramWindowEvent) {}
  
  public void windowClosed(WindowEvent paramWindowEvent) {}
  
  public void windowDeiconified(WindowEvent paramWindowEvent) {}
  
  public void windowIconified(WindowEvent paramWindowEvent) {}
  
  public void windowOpened(WindowEvent paramWindowEvent) {}
  
  public void windowClosing(WindowEvent paramWindowEvent) {
    stop();
    try {
      if (this.cConn != null)
        this.cConn.close(); 
      if (this.prefs != null) {
        this.prefs.autoRefresh = this.autoRefresh;
        this.prefs.showRowCounts = this.displayRowCounts;
        this.prefs.showSysTables = this.showSys;
        this.prefs.showSchemas = this.showSchemas;
        this.prefs.resultGrid = this.gridFormat;
        this.prefs.showTooltips = this.showTooltips;
        this.prefs.laf = this.currentLAF;
        this.prefs.store();
      } 
    } catch (Exception exception) {
      CommonSwing.errorMessage(exception);
    } 
    if (this.fMain instanceof Window)
      ((Window)this.fMain).dispose(); 
    if (bMustExit)
      System.exit(0); 
  }
  
  private void clear() {
    this.sqlScriptBuffer = null;
    this.txtCommand.setText("");
    this.txtCommand.setEnabled(true);
  }
  
  private void backgroundIt(Runnable paramRunnable, String paramString) {
    if (this.busyText != null) {
      Toolkit.getDefaultToolkit().beep();
      return;
    } 
    setWaiting(paramString);
    SwingUtilities.invokeLater(paramRunnable);
  }
  
  private void clearResultPanel() {
    this.gResult.setHead(new Object[0]);
    this.gResult.clear();
    if (this.gridFormat) {
      this.gResult.fireTableChanged(null);
    } else {
      showResultInText();
    } 
  }
  
  public void setWaiting(String paramString) {
    this.busyText = paramString;
    if (this.busyText == null) {
      if (this.fMain instanceof Frame) {
        ((Frame)this.fMain).setCursor(this.fMainCursor);
      } else {
        ((Component)this.fMain).setCursor(this.fMainCursor);
      } 
      this.txtCommand.setCursor(this.txtCommandCursor);
      this.txtResult.setCursor(this.txtResultCursor);
    } else {
      if (this.fMainCursor == null) {
        this.fMainCursor = (this.fMain instanceof Frame) ? ((Frame)this.fMain).getCursor() : ((Component)this.fMain).getCursor();
        this.txtCommandCursor = this.txtCommand.getCursor();
        this.txtResultCursor = this.txtResult.getCursor();
      } 
      if (this.fMain instanceof Frame) {
        ((Frame)this.fMain).setCursor(this.waitCursor);
      } else {
        ((Component)this.fMain).setCursor(this.waitCursor);
      } 
      this.txtCommand.setCursor(this.waitCursor);
      this.txtResult.setCursor(this.waitCursor);
    } 
    setStatusLine(this.busyText, (this.busyText == null) ? this.gResult.getRowCount() : 0);
  }
  
  public void start() {
    if (this.buttonUpdaterThread == null)
      this.buttonUpdaterThread = new Thread(this.buttonUpdater); 
    this.buttonUpdaterThread.start();
  }
  
  public void stop() {
    System.err.println("Stopping");
    Thread thread = this.buttonUpdaterThread;
    if (thread != null)
      thread.setContextClassLoader(null); 
    this.buttonUpdaterThread = null;
  }
  
  protected void executeCurrentSQL() {
    if (this.txtCommand.getText().length() < 1) {
      CommonSwing.errorMessage("No SQL to execute");
      return;
    } 
    backgroundIt(new StatementExecRunnable(), "Executing SQL");
  }
  
  private void executeSQL() {
    String[] arrayOfString = new String[1];
    String str = null;
    try {
      this.lTime = System.currentTimeMillis();
      str = (this.sqlScriptBuffer == null) ? this.txtCommand.getText() : this.sqlScriptBuffer;
      this.sStatement.execute(str);
      int i = this.sStatement.getUpdateCount();
      if (i == -1) {
        ResultSet resultSet = this.sStatement.getResultSet();
        try {
          formatResultSet(resultSet);
        } catch (Throwable throwable) {
          arrayOfString[0] = "Error displaying the ResultSet";
          this.gResult.setHead((Object[])arrayOfString);
          String str1 = throwable.getMessage();
          arrayOfString[0] = str1;
          this.gResult.addRow((Object[])arrayOfString);
        } 
      } else {
        arrayOfString[0] = "update count";
        this.gResult.setHead((Object[])arrayOfString);
        arrayOfString[0] = "" + i;
        this.gResult.addRow((Object[])arrayOfString);
      } 
      this.lTime = System.currentTimeMillis() - this.lTime;
      if (this.sqlScriptBuffer == null) {
        addToRecent(str);
        this.txtCommand.setEnabled(true);
      } else {
        clear();
      } 
    } catch (SQLException sQLException) {
      this.lTime = System.currentTimeMillis() - this.lTime;
      arrayOfString[0] = "SQL Error";
      this.gResult.setHead((Object[])arrayOfString);
      String str1 = sQLException.getMessage();
      str1 = str1 + " / Error Code: " + sQLException.getErrorCode();
      str1 = str1 + " / State: " + sQLException.getSQLState();
      arrayOfString[0] = str1;
      this.gResult.addRow((Object[])arrayOfString);
      CommonSwing.errorMessage(sQLException);
      return;
    } 
    if (this.autoRefresh) {
      setStatusLine("Refreshing object tree", 0);
      String str1 = str.toUpperCase(Locale.ENGLISH);
      if (str1.indexOf("ALTER") > -1 || str1.indexOf("DROP") > -1 || str1.indexOf("CREATE") > -1)
        directRefreshTree(); 
    } 
  }
  
  private void updateResult() {
    if (this.gridFormat) {
      if (this.bHelp) {
        this.pResult.removeAll();
        this.pResult.add(this.gScrollPane, "Center");
        this.pResult.doLayout();
        this.gResult.fireTableChanged(null);
        this.pResult.repaint();
        this.bHelp = false;
      } 
    } else {
      showResultInText();
    } 
    this.txtCommand.selectAll();
    this.txtCommand.requestFocus();
  }
  
  private void formatResultSet(ResultSet paramResultSet) {
    if (paramResultSet == null) {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "Result";
      this.gResult.setHead((Object[])arrayOfString);
      arrayOfString[0] = "(empty)";
      this.gResult.addRow((Object[])arrayOfString);
      return;
    } 
    try {
      ResultSetMetaData resultSetMetaData = paramResultSet.getMetaData();
      int i = resultSetMetaData.getColumnCount();
      Object[] arrayOfObject = new Object[i];
      boolean[] arrayOfBoolean = new boolean[i];
      byte b;
      for (b = 1; b <= i; b++) {
        arrayOfObject[b - 1] = resultSetMetaData.getColumnLabel(b);
        arrayOfBoolean[b - 1] = (resultSetMetaData.getColumnType(b) == 12);
      } 
      this.gResult.setHead(arrayOfObject);
      while (paramResultSet.next()) {
        for (b = 1; b <= i; b++) {
          try {
            arrayOfObject[b - 1] = paramResultSet.getObject(b);
            if (paramResultSet.wasNull())
              arrayOfObject[b - 1] = arrayOfBoolean[b - 1] ? "[null]" : null; 
          } catch (SQLException sQLException) {}
        } 
        this.gResult.addRow(arrayOfObject);
      } 
      paramResultSet.close();
    } catch (SQLException sQLException) {
      CommonSwing.errorMessage(sQLException);
    } 
  }
  
  private void testPerformance() {
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
    this.gResult.setHead((Object[])arrayOfString);
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
        arrayOfString[0] = "" + l1;
        arrayOfString[1] = "" + i;
        arrayOfString[3] = "";
      } catch (SQLException sQLException) {
        arrayOfString[1] = "n/a";
        arrayOfString[0] = "n/a";
        arrayOfString[3] = sQLException.toString();
        CommonSwing.errorMessage(sQLException);
      } 
      this.gResult.addRow((Object[])arrayOfString);
      System.out.println(l1 + " ms : " + str1);
    } 
    arrayOfString[0] = "" + l;
    arrayOfString[1] = "total";
    arrayOfString[2] = "";
    this.gResult.addRow((Object[])arrayOfString);
    this.lTime = System.currentTimeMillis() - this.lTime;
  }
  
  private void showResultInText() {
    Object[] arrayOfObject = this.gResult.getHead();
    int i = arrayOfObject.length;
    int[] arrayOfInt = new int[i];
    Vector<Object[]> vector = this.gResult.getData();
    int j = vector.size();
    byte b1;
    for (b1 = 0; b1 < i; b1++)
      arrayOfInt[b1] = arrayOfObject[b1].toString().length(); 
    for (b1 = 0; b1 < j; b1++) {
      Object[] arrayOfObject1 = vector.elementAt(b1);
      for (byte b = 0; b < i; b++) {
        String str = (arrayOfObject1[b] == null) ? "" : arrayOfObject1[b].toString();
        int k = str.length();
        if (k > arrayOfInt[b])
          arrayOfInt[b] = k; 
      } 
    } 
    StringBuffer stringBuffer = new StringBuffer();
    byte b2;
    for (b2 = 0; b2 < i; b2++) {
      stringBuffer.append(arrayOfObject[b2]);
      for (int k = arrayOfObject[b2].toString().length(); k <= arrayOfInt[b2]; k++)
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
      Object[] arrayOfObject1 = vector.elementAt(b2);
      for (byte b = 0; b < i; b++) {
        String str = (arrayOfObject1[b] == null) ? "" : arrayOfObject1[b].toString();
        stringBuffer.append(str);
        for (int k = str.length(); k <= arrayOfInt[b]; k++)
          stringBuffer.append(' '); 
      } 
      stringBuffer.append(NL);
    } 
    this.txtResult.setText(stringBuffer.toString());
  }
  
  private void addToRecent(String paramString) {
    for (byte b = 0; b < iMaxRecent; b++) {
      if (paramString.equals(this.sRecent[b]))
        return; 
    } 
    if (this.sRecent[this.iRecent] != null)
      this.mRecent.remove(this.iRecent); 
    this.sRecent[this.iRecent] = paramString;
    if (paramString.length() > 43)
      paramString = paramString.substring(0, 40) + "..."; 
    JMenuItem jMenuItem = new JMenuItem(paramString);
    jMenuItem.setActionCommand("#" + this.iRecent);
    jMenuItem.addActionListener(this);
    this.mRecent.insert(jMenuItem, this.iRecent);
    this.iRecent = (this.iRecent + 1) % iMaxRecent;
  }
  
  public final void mouseClicked(MouseEvent paramMouseEvent) {}
  
  public final void mouseEntered(MouseEvent paramMouseEvent) {}
  
  public final void mouseExited(MouseEvent paramMouseEvent) {}
  
  public final void mousePressed(MouseEvent paramMouseEvent) {
    if (this.alreadyHandled == paramMouseEvent)
      return; 
    handlePopup(paramMouseEvent);
    this.alreadyHandled = paramMouseEvent;
  }
  
  public final void mouseReleased(MouseEvent paramMouseEvent) {
    if (this.alreadyHandled == paramMouseEvent)
      return; 
    handlePopup(paramMouseEvent);
    this.alreadyHandled = paramMouseEvent;
  }
  
  public final void handlePopup(MouseEvent paramMouseEvent) {
    if (!paramMouseEvent.isPopupTrigger())
      return; 
    Object object = paramMouseEvent.getSource();
    if (!(object instanceof JTree))
      return; 
    JTree jTree = (JTree)object;
    TreePath treePath = jTree.getPathForLocation(paramMouseEvent.getX(), paramMouseEvent.getY());
    if (treePath == null)
      return; 
    JPopupMenu jPopupMenu = new JPopupMenu();
    String[] arrayOfString = { "Select", "Delete", "Update", "Insert" };
    for (byte b = 0; b < arrayOfString.length; b++) {
      PopupListener popupListener = new PopupListener(arrayOfString[b], treePath);
      String str = popupListener.toString();
      if (str == null)
        return; 
      if (str.length() > 40)
        str = str.substring(0, 40) + "..."; 
      JMenuItem jMenuItem = new JMenuItem(str);
      jMenuItem.addActionListener(popupListener);
      jPopupMenu.add(jMenuItem);
    } 
    jPopupMenu.show(paramMouseEvent.getComponent(), paramMouseEvent.getX(), paramMouseEvent.getY());
  }
  
  private String quoteTableName(String paramString) {
    int i = paramString.indexOf(".");
    if (i < 0) {
      int k = paramString.indexOf(" (");
      if (k >= 0)
        paramString = paramString.substring(0, k); 
      return quoteObjectName(paramString);
    } 
    String str1 = paramString.substring(0, i);
    String str2 = paramString.substring(i + 1);
    int j = str2.indexOf(" (");
    if (j >= 0)
      str2 = str2.substring(0, j); 
    return quoteObjectName(str1) + '.' + quoteObjectName(str2);
  }
  
  private String quoteObjectName(String paramString) {
    return "\"" + paramString + "\"";
  }
  
  private void initGUI() {
    JPanel jPanel = new JPanel();
    this.pResult = new JPanel();
    this.nsSplitPane = new JSplitPane(0, jPanel, this.pResult);
    this.nsSplitPane.setOneTouchExpandable(true);
    jPanel.setLayout(new BorderLayout());
    this.pResult.setLayout(new BorderLayout());
    Font font = new Font("Dialog", 0, 12);
    this.txtCommand = new JTextArea(7, 40);
    this.txtCommand.setMargin(new Insets(5, 5, 5, 5));
    this.txtCommand.addKeyListener(this);
    this.txtCommandScroll = new JScrollPane(this.txtCommand);
    this.txtResult = new JTextArea(25, 40);
    this.txtResult.setMargin(new Insets(5, 5, 5, 5));
    this.txtResultScroll = new JScrollPane(this.txtResult);
    this.txtCommand.setFont(font);
    this.txtResult.setFont(new Font("Courier", 0, 12));
    jPanel.add(this.txtCommandScroll, "Center");
    this.gResult = new GridSwing();
    TableSorter tableSorter = new TableSorter(this.gResult);
    this.tableModel = tableSorter;
    this.gResultTable = new JTable(tableSorter);
    tableSorter.setTableHeader(this.gResultTable.getTableHeader());
    this.gScrollPane = new JScrollPane(this.gResultTable);
    this.gResultTable.setAutoResizeMode(0);
    this.gResult.setJTable(this.gResultTable);
    this.pResult.add(this.gScrollPane, "Center");
    this.rootNode = new DefaultMutableTreeNode("Connection");
    this.treeModel = new DefaultTreeModel(this.rootNode);
    this.tTree = new JTree(this.treeModel);
    this.tScrollPane = new JScrollPane(this.tTree);
    this.tTree.addMouseListener(this);
    this.tScrollPane.setPreferredSize(new Dimension(200, 400));
    this.tScrollPane.setMinimumSize(new Dimension(70, 100));
    this.txtCommandScroll.setPreferredSize(new Dimension(560, 100));
    this.txtCommandScroll.setMinimumSize(new Dimension(180, 100));
    this.gScrollPane.setPreferredSize(new Dimension(460, 300));
    this.ewSplitPane = new JSplitPane(1, this.tScrollPane, this.nsSplitPane);
    this.ewSplitPane.setOneTouchExpandable(true);
    this.fMain.getContentPane().add(this.ewSplitPane, "Center");
    this.jStatusLine = new JLabel();
    iReadyStatus = new JButton(new ImageIcon(CommonSwing.getIcon("StatusReady")));
    iReadyStatus.setSelectedIcon(new ImageIcon(CommonSwing.getIcon("StatusRunning")));
    this.pStatus = new JPanel();
    this.pStatus.setLayout(new BorderLayout());
    this.pStatus.add(iReadyStatus, "West");
    this.pStatus.add(this.jStatusLine, "Center");
    this.fMain.getContentPane().add(this.pStatus, "South");
    doLayout();
    if (this.fMain instanceof Window) {
      ((Window)this.fMain).pack();
    } else {
      ((Container)this.fMain).validate();
    } 
  }
  
  private DefaultMutableTreeNode makeNode(Object paramObject, MutableTreeNode paramMutableTreeNode) {
    DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(paramObject);
    if (paramMutableTreeNode != null)
      this.treeModel.insertNodeInto(defaultMutableTreeNode, paramMutableTreeNode, paramMutableTreeNode.getChildCount()); 
    return defaultMutableTreeNode;
  }
  
  protected void refreshTree() {
    backgroundIt(this.treeRefreshRunnable, "Refreshing object tree");
  }
  
  protected void directRefreshTree() {
    DecimalFormat decimalFormat = new DecimalFormat(" ( ####,###,####,##0 )");
    while (this.treeModel.getChildCount(this.rootNode) > 0) {
      DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)this.treeModel.getChild(this.rootNode, 0);
      this.treeModel.removeNodeFromParent(defaultMutableTreeNode);
      defaultMutableTreeNode.removeAllChildren();
      defaultMutableTreeNode.removeFromParent();
    } 
    this.treeModel.nodeStructureChanged(this.rootNode);
    this.treeModel.reload();
    this.tScrollPane.repaint();
    ResultSet resultSet = null;
    try {
      this.rootNode.setUserObject(this.dMeta.getURL());
      resultSet = this.dMeta.getTables(null, null, null, this.showSys ? usertables : nonSystables);
      Vector<String> vector1 = new Vector();
      Vector<String> vector2 = new Vector();
      Vector<String> vector3 = new Vector();
      while (resultSet.next()) {
        String str = resultSet.getString(2);
        if ((this.showSys || !this.isOracle || !oracleSysUsers.contains(str)) && (this.schemaFilter == null || str.equals(this.schemaFilter))) {
          vector2.addElement(str);
          vector1.addElement(resultSet.getString(3));
          vector3.addElement(resultSet.getString(5));
        } 
      } 
      resultSet.close();
      resultSet = null;
      int[] arrayOfInt = new int[vector1.size()];
      try {
        arrayOfInt = getRowCounts(vector1, vector2);
      } catch (Exception exception) {
        CommonSwing.errorMessage(exception);
      } 
      for (byte b = 0; b < vector1.size(); b++) {
        ResultSet resultSet1 = null;
        try {
          String str = vector1.elementAt(b);
        } finally {
          if (resultSet1 != null)
            try {
              resultSet1.close();
            } catch (SQLException sQLException) {} 
        } 
      } 
      DefaultMutableTreeNode defaultMutableTreeNode = makeNode("Properties", this.rootNode);
      makeNode("User: " + this.dMeta.getUserName(), defaultMutableTreeNode);
      makeNode("ReadOnly: " + this.cConn.isReadOnly(), defaultMutableTreeNode);
      makeNode("AutoCommit: " + this.cConn.getAutoCommit(), defaultMutableTreeNode);
      makeNode("Driver: " + this.dMeta.getDriverName(), defaultMutableTreeNode);
      makeNode("Product: " + this.dMeta.getDatabaseProductName(), defaultMutableTreeNode);
      makeNode("Version: " + this.dMeta.getDatabaseProductVersion(), defaultMutableTreeNode);
    } catch (SQLException sQLException) {
      DefaultMutableTreeNode defaultMutableTreeNode = makeNode("Error getting metadata:", this.rootNode);
      makeNode(sQLException.getMessage(), defaultMutableTreeNode);
      makeNode(sQLException.getSQLState(), defaultMutableTreeNode);
      CommonSwing.errorMessage(sQLException);
    } finally {
      if (resultSet != null)
        try {
          resultSet.close();
        } catch (SQLException sQLException) {} 
    } 
    this.treeModel.nodeStructureChanged(this.rootNode);
    this.treeModel.reload();
    this.tScrollPane.repaint();
    updateSchemaList();
  }
  
  void setStatusLine(String paramString, int paramInt) {
    iReadyStatus.setSelected((paramString != null));
    if (paramString == null) {
      String str = "";
      if (this.schemaFilter != null)
        str = " /  Tree showing objects in schema '" + this.schemaFilter + "'"; 
      if (paramInt > 1)
        str = str + " / " + paramInt + " rows retrieved"; 
      this.jStatusLine.setText("  " + READY_STATUS + str);
    } else {
      this.jStatusLine.setText("  " + paramString + "...");
    } 
  }
  
  protected int[] getRowCounts(Vector<String> paramVector1, Vector<String> paramVector2) throws Exception {
    if (!this.displayRowCounts)
      return null; 
    String str = "SELECT COUNT(*) FROM ";
    int[] arrayOfInt = new int[paramVector1.size()];
    try {
      Statement statement = this.rowConn.createStatement();
      for (byte b = 0; b < paramVector1.size(); b++) {
        try {
          String str2 = paramVector2.elementAt(b);
          str2 = (str2 == null) ? "" : ("\"" + str2 + "\".\"");
          String str1 = str2 + (String)paramVector1.elementAt(b) + "\"";
          ResultSet resultSet = statement.executeQuery(str + str1);
          while (resultSet.next())
            arrayOfInt[b] = resultSet.getInt(1); 
        } catch (Exception exception) {
          System.err.println("Unable to get row count for table " + paramVector2.elementAt(b) + '.' + paramVector1.elementAt(b) + ".  Using value '0': " + exception);
        } 
      } 
    } catch (Exception exception) {
      CommonSwing.errorMessage(exception);
    } 
    return arrayOfInt;
  }
  
  protected JToolBar createToolBar() {
    JToolBar jToolBar = new JToolBar();
    jToolBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
    this.jbuttonClear = new JButton("Clear SQL", new ImageIcon(CommonSwing.getIcon("Clear")));
    this.jbuttonClear.putClientProperty("is3DEnabled", Boolean.TRUE);
    this.tipMap.put(this.jbuttonClear, "Clear SQL");
    this.jbuttonClear.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent param1ActionEvent) {
            if (DatabaseManagerSwing.this.sqlScriptBuffer == null && DatabaseManagerSwing.this.txtCommand.getText().length() < 1) {
              CommonSwing.errorMessage("No SQL to clear");
              return;
            } 
            DatabaseManagerSwing.this.clear();
          }
        });
    this.jbuttonExecute = new JButton("Execute SQL", new ImageIcon(CommonSwing.getIcon("Execute")));
    this.tipMap.put(this.jbuttonExecute, "Execute SQL");
    this.jbuttonExecute.putClientProperty("is3DEnabled", Boolean.TRUE);
    this.jbuttonExecute.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent param1ActionEvent) {
            DatabaseManagerSwing.this.executeCurrentSQL();
          }
        });
    jToolBar.addSeparator();
    jToolBar.add(this.jbuttonClear);
    jToolBar.addSeparator();
    jToolBar.add(this.jbuttonExecute);
    jToolBar.addSeparator();
    this.jbuttonClear.setAlignmentY(0.5F);
    this.jbuttonClear.setAlignmentX(0.5F);
    this.jbuttonExecute.setAlignmentY(0.5F);
    this.jbuttonExecute.setAlignmentX(0.5F);
    return jToolBar;
  }
  
  void updateAutoCommitBox() {
    try {
      this.boxAutoCommit.setSelected(this.cConn.getAutoCommit());
    } catch (SQLException sQLException) {
      CommonSwing.errorMessage(sQLException);
    } 
  }
  
  private void setLF(String paramString) {
    if (this.currentLAF != null && this.currentLAF == paramString)
      return; 
    if (this.pResult != null && this.gridFormat)
      this.pResult.removeAll(); 
    CommonSwing.setSwingLAF((Component)this.fMain, paramString);
    if (this.pResult != null && this.gridFormat)
      setResultsInGrid(); 
    this.currentLAF = paramString;
    if (this.currentLAF.equals(CommonSwing.Native)) {
      this.rbNativeLF.setSelected(true);
    } else if (this.currentLAF.equals(CommonSwing.Java)) {
      this.rbJavaLF.setSelected(true);
    } else if (this.currentLAF.equals(CommonSwing.Motif)) {
      this.rbMotifLF.setSelected(true);
    } 
  }
  
  void resetTooltips() {
    for (JComponent jComponent : this.tipMap.keySet())
      jComponent.setToolTipText(this.showTooltips ? (String)this.tipMap.get(jComponent) : (String)null); 
  }
  
  private void updateSchemaList() {
    ButtonGroup buttonGroup = new ButtonGroup();
    ArrayList<String> arrayList = new ArrayList();
    ResultSet resultSet = null;
    try {
      resultSet = this.dMeta.getSchemas();
      if (resultSet == null)
        throw new SQLException("Failed to get metadata from database"); 
      while (resultSet.next())
        arrayList.add(resultSet.getString(1)); 
    } catch (SQLException sQLException) {
      CommonSwing.errorMessage(sQLException);
    } finally {
      if (resultSet != null)
        try {
          resultSet.close();
        } catch (SQLException sQLException) {} 
    } 
    this.mnuSchemas.removeAll();
    this.rbAllSchemas.setSelected((this.schemaFilter == null));
    buttonGroup.add(this.rbAllSchemas);
    this.mnuSchemas.add(this.rbAllSchemas);
    for (byte b = 0; b < arrayList.size(); b++) {
      String str = arrayList.get(b);
      JRadioButtonMenuItem jRadioButtonMenuItem = new JRadioButtonMenuItem(str);
      buttonGroup.add(jRadioButtonMenuItem);
      this.mnuSchemas.add(jRadioButtonMenuItem);
      jRadioButtonMenuItem.setSelected((this.schemaFilter != null && this.schemaFilter.equals(str)));
      jRadioButtonMenuItem.addActionListener(this.schemaListListener);
      jRadioButtonMenuItem.setEnabled((arrayList.size() > 1));
    } 
    this.mnuSchemas.addSeparator();
    this.mnuSchemas.add(this.mitemUpdateSchemas);
  }
  
  static {
    try {
      Class<?> clazz = Class.forName("sun.security.action.GetPropertyAction");
      Constructor<?> constructor = clazz.getConstructor(new Class[] { String.class });
      PrivilegedAction<String> privilegedAction = (PrivilegedAction)constructor.newInstance(new Object[] { "user.home" });
      homedir = AccessController.<String>doPrivileged(privilegedAction);
    } catch (IllegalAccessException illegalAccessException) {
      System.err.println("Failed to get home directory.\nTherefore not retrieving/storing user preferences.\n(" + illegalAccessException.getMessage() + ')');
    } catch (NoSuchMethodException noSuchMethodException) {
      System.err.println("Failed to get home directory.\nTherefore not retrieving/storing user preferences.\n(" + noSuchMethodException.getMessage() + ')');
    } catch (ClassNotFoundException classNotFoundException) {
      System.err.println("Failed to get home directory.\nTherefore not retrieving/storing user preferences.\n(" + classNotFoundException.getMessage() + ')');
    } catch (InstantiationException instantiationException) {
      System.err.println("Failed to get home directory.\nTherefore not retrieving/storing user preferences.\n(" + instantiationException.getMessage() + ')');
    } catch (InvocationTargetException invocationTargetException) {
      System.err.println("Failed to get home directory.\nTherefore not retrieving/storing user preferences.\n(" + invocationTargetException.getMessage() + ')');
    } catch (AccessControlException accessControlException) {
      System.err.println("Failed to get home directory.\nTherefore not retrieving/storing user preferences.\n(" + accessControlException.getMessage() + ')');
    } 
  }
  
  static {
    try {
      Class.forName(DatabaseManagerSwing.class.getPackage().getName() + ".Transfer");
      TT_AVAILABLE = true;
    } catch (Throwable throwable) {}
  }
  
  static {
    for (byte b = 0; b < oracleSysSchemas.length; b++)
      oracleSysUsers.add(oracleSysSchemas[b]); 
  }
  
  public class DBMPrefs {
    public File prefsFile = null;
    
    boolean autoRefresh = true;
    
    boolean showRowCounts = false;
    
    boolean showSysTables = false;
    
    boolean showSchemas = true;
    
    boolean resultGrid = true;
    
    String laf = CommonSwing.Native;
    
    boolean showTooltips = true;
    
    public DBMPrefs(boolean param1Boolean) throws IOException {
      if (!param1Boolean) {
        if (DatabaseManagerSwing.homedir == null)
          throw new IOException("Skipping preferences since do not know home dir"); 
        this.prefsFile = new File(DatabaseManagerSwing.homedir, "dbmprefs.properties");
      } 
      load();
    }
    
    public void load() throws IOException {
      if (this.prefsFile == null) {
        String str = DatabaseManagerSwing.this.getParameter("autoRefresh");
        if (str != null)
          this.autoRefresh = Boolean.valueOf(str).booleanValue(); 
        str = DatabaseManagerSwing.this.getParameter("showRowCounts");
        if (str != null)
          this.showRowCounts = Boolean.valueOf(str).booleanValue(); 
        str = DatabaseManagerSwing.this.getParameter("showSysTables");
        if (str != null)
          this.showSysTables = Boolean.valueOf(str).booleanValue(); 
        str = DatabaseManagerSwing.this.getParameter("showSchemas");
        if (str != null)
          this.showSchemas = Boolean.valueOf(str).booleanValue(); 
        str = DatabaseManagerSwing.this.getParameter("resultGrid");
        if (str != null)
          this.resultGrid = Boolean.valueOf(str).booleanValue(); 
        str = DatabaseManagerSwing.this.getParameter("laf");
        this.laf = (str == null) ? CommonSwing.Native : str;
        str = DatabaseManagerSwing.this.getParameter("showTooltips");
        if (str != null)
          this.showTooltips = Boolean.valueOf(str).booleanValue(); 
      } else {
        if (!this.prefsFile.exists())
          throw new IOException("No such file: " + this.prefsFile); 
        Properties properties = new Properties();
        try {
          FileInputStream fileInputStream = new FileInputStream(this.prefsFile);
          properties.load(fileInputStream);
          fileInputStream.close();
        } catch (IOException iOException) {
          throw new IOException("Failed to read preferences file '" + this.prefsFile + "':  " + iOException.getMessage());
        } 
        String str = properties.getProperty("autoRefresh");
        if (str != null)
          this.autoRefresh = Boolean.valueOf(str).booleanValue(); 
        str = properties.getProperty("showRowCounts");
        if (str != null)
          this.showRowCounts = Boolean.valueOf(str).booleanValue(); 
        str = properties.getProperty("showSysTables");
        if (str != null)
          this.showSysTables = Boolean.valueOf(str).booleanValue(); 
        str = properties.getProperty("showSchemas");
        if (str != null)
          this.showSchemas = Boolean.valueOf(str).booleanValue(); 
        str = properties.getProperty("resultGrid");
        if (str != null)
          this.resultGrid = Boolean.valueOf(str).booleanValue(); 
        str = properties.getProperty("laf");
        this.laf = (str == null) ? CommonSwing.Native : str;
        str = properties.getProperty("showTooltips");
        if (str != null)
          this.showTooltips = Boolean.valueOf(str).booleanValue(); 
      } 
    }
    
    public void store() {
      if (this.prefsFile == null)
        return; 
      Properties properties = new Properties();
      properties.setProperty("autoRefresh", this.autoRefresh ? DatabaseManagerSwing.tString : DatabaseManagerSwing.fString);
      properties.setProperty("showRowCounts", this.showRowCounts ? DatabaseManagerSwing.tString : DatabaseManagerSwing.fString);
      properties.setProperty("showSysTables", this.showSysTables ? DatabaseManagerSwing.tString : DatabaseManagerSwing.fString);
      properties.setProperty("showSchemas", this.showSchemas ? DatabaseManagerSwing.tString : DatabaseManagerSwing.fString);
      properties.setProperty("resultGrid", this.resultGrid ? DatabaseManagerSwing.tString : DatabaseManagerSwing.fString);
      properties.setProperty("laf", this.laf);
      properties.setProperty("showTooltips", this.showTooltips ? DatabaseManagerSwing.tString : DatabaseManagerSwing.fString);
      try {
        FileOutputStream fileOutputStream = new FileOutputStream(this.prefsFile);
        properties.store(fileOutputStream, "DatabaseManagerSwing user preferences");
        fileOutputStream.flush();
        fileOutputStream.close();
      } catch (IOException iOException) {
        throw new RuntimeException("Failed to prepare preferences file '" + this.prefsFile + "':  " + iOException.getMessage());
      } 
    }
  }
  
  private class PopupListener implements ActionListener {
    public static final int DEPTH_URL = 1;
    
    public static final int DEPTH_TABLE = 2;
    
    public static final int DEPTH_COLUMN = 3;
    
    String command;
    
    TreePath treePath;
    
    TreePath tablePath;
    
    TreePath columnPath;
    
    String table = null;
    
    String column = null;
    
    PopupListener(String param1String, TreePath param1TreePath) {
      this.command = param1String;
      this.treePath = param1TreePath;
    }
    
    public void actionPerformed(ActionEvent param1ActionEvent) {
      DatabaseManagerSwing.this.txtCommand.setText(getCommandString());
    }
    
    public String toString() {
      return getCommandString();
    }
    
    public String getCommandString() {
      int i = this.treePath.getPathCount();
      if (i == 1)
        return ""; 
      if (i == 2) {
        this.tablePath = this.treePath;
        this.table = this.treePath.getPathComponent(1).toString();
      } 
      if (i == 3) {
        this.tablePath = this.treePath.getParentPath();
        this.table = this.treePath.getPathComponent(1).toString();
        this.columnPath = this.treePath;
        this.column = this.treePath.getPathComponent(2).toString();
      } 
      if (this.command.toUpperCase().equals("SELECT")) {
        String str = "SELECT * FROM " + DatabaseManagerSwing.this.quoteTableName(this.table);
        if (this.column != null) {
          DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)this.treePath.getLastPathComponent();
          String str1 = null;
          if (defaultMutableTreeNode.getChildCount() > 0) {
            str1 = defaultMutableTreeNode.getFirstChild().toString();
            boolean bool = (str1.indexOf("CHAR") >= 0) ? true : false;
            str = str + " WHERE " + DatabaseManagerSwing.this.quoteObjectName(this.column);
            if (bool) {
              str = str + " LIKE '%%'";
            } else {
              str = str + " = ";
            } 
          } 
        } 
        return str;
      } 
      if (this.command.toUpperCase().equals("UPDATE")) {
        String str = "UPDATE " + DatabaseManagerSwing.this.quoteTableName(this.table) + " SET ";
        if (this.column != null)
          str = str + DatabaseManagerSwing.this.quoteObjectName(this.column) + " = "; 
        return str;
      } 
      if (this.command.toUpperCase().equals("DELETE")) {
        String str = "DELETE FROM " + DatabaseManagerSwing.this.quoteTableName(this.table);
        if (this.column != null) {
          DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)this.treePath.getLastPathComponent();
          String str1 = null;
          if (defaultMutableTreeNode.getChildCount() > 0) {
            str1 = defaultMutableTreeNode.getFirstChild().toString();
            boolean bool = (str1.indexOf("CHAR") >= 0) ? true : false;
            str = str + " WHERE " + DatabaseManagerSwing.this.quoteObjectName(this.column);
            if (bool) {
              str = str + " LIKE '%%'";
            } else {
              str = str + " = ";
            } 
          } 
        } 
        return str;
      } 
      if (this.command.toUpperCase().equals("INSERT")) {
        String str1 = "";
        String str2 = " ";
        String str3 = "";
        String str4 = "";
        if (this.tablePath == null)
          return null; 
        TreeNode treeNode = (TreeNode)this.tablePath.getLastPathComponent();
        Enumeration<? extends TreeNode> enumeration = treeNode.children();
        while (enumeration.hasMoreElements()) {
          DefaultMutableTreeNode defaultMutableTreeNode1 = (DefaultMutableTreeNode)enumeration.nextElement();
          if (defaultMutableTreeNode1.toString().equals("Indices"))
            continue; 
          DefaultMutableTreeNode defaultMutableTreeNode2 = defaultMutableTreeNode1;
          String str = null;
          if (defaultMutableTreeNode2.getChildCount() == 0)
            continue; 
          str = defaultMutableTreeNode2.getFirstChild().toString();
          if (str.indexOf("CHAR") >= 0) {
            str4 = "''";
          } else {
            str4 = "";
          } 
          str1 = str1 + str3 + DatabaseManagerSwing.this.quoteObjectName(defaultMutableTreeNode1.toString());
          str2 = str2 + str3 + str4;
          str3 = ", ";
        } 
        return "INSERT INTO " + DatabaseManagerSwing.this.quoteTableName(this.table) + "\n( " + str1 + " )\nVALUES (" + str2 + ")";
      } 
      return "Got here in error " + this.command + ".  Should never happen";
    }
  }
  
  protected class StatementExecRunnable implements Runnable {
    public void run() {
      DatabaseManagerSwing.this.gResult.clear();
      try {
        if (DatabaseManagerSwing.this.txtCommand.getText().startsWith("-->>>TEST<<<--")) {
          DatabaseManagerSwing.this.testPerformance();
        } else {
          DatabaseManagerSwing.this.executeSQL();
        } 
        DatabaseManagerSwing.this.updateResult();
        DatabaseManagerSwing.this.displayResults();
        DatabaseManagerSwing.this.updateAutoCommitBox();
      } catch (RuntimeException runtimeException) {
        CommonSwing.errorMessage(runtimeException);
        throw runtimeException;
      } finally {
        DatabaseManagerSwing.this.setWaiting((String)null);
      } 
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqld\\util\DatabaseManagerSwing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */