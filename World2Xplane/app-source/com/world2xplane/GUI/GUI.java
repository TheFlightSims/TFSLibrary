/*     */ package com.world2xplane.GUI;
/*     */ 
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import com.world2xplane.World2XPlane;
/*     */ import com.world2xplane.XPlane.DSFTile;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Label;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PipedInputStream;
/*     */ import java.io.PipedOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.prefs.Preferences;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ import javax.swing.border.BevelBorder;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class GUI extends WindowAdapter implements WindowListener, ActionListener, Runnable {
/*  45 */   private static Logger log = LoggerFactory.getLogger(GUI.class);
/*     */   
/*     */   private final JTabbedPane tabbedPane;
/*     */   
/*     */   private final ProgressWindow progressWindow;
/*     */   
/*     */   private JProgressBar progressBar;
/*     */   
/*     */   private JButton startButtton;
/*     */   
/*     */   private JFilePicker osmFilePicker;
/*     */   
/*     */   private JFilePicker xplaneDirectory;
/*     */   
/*     */   private JFilePicker polyFile;
/*     */   
/*     */   private JFilePicker configfile;
/*     */   
/*  57 */   private long peakMemory = 0L;
/*     */   
/*     */   private JLabel memoryField;
/*     */   
/*     */   private JLabel busy;
/*     */   
/*     */   private JFrame frame;
/*     */   
/*     */   private JTextArea textArea;
/*     */   
/*     */   private JTextField coreNumber;
/*     */   
/*     */   private JCheckBox validateDirectory;
/*     */   
/*     */   private JCheckBox useDatabase;
/*     */   
/*     */   private Thread reader;
/*     */   
/*     */   private Thread reader2;
/*     */   
/*     */   private boolean quit;
/*     */   
/*  72 */   private final PipedInputStream pin = new PipedInputStream();
/*     */   
/*  73 */   private final PipedInputStream pin2 = new PipedInputStream();
/*     */   
/*  74 */   Preferences prefs = Preferences.userNodeForPackage(getClass());
/*     */   
/*     */   private static final String PBF_LOCATION = "PBFLOC";
/*     */   
/*     */   private static final String POLYFILE_LOCATION = "POLYFILE";
/*     */   
/*     */   private static final String CONFIG_FILE = "CONFIGFILE";
/*     */   
/*     */   private static final String XPL_LOCATION = "XPLLOC";
/*     */   
/*     */   private static final String VALIDATE = "VALIDATE";
/*     */   
/*     */   private static final String LOCAL_STORAGE = "LOCAL_STORAGE";
/*     */   
/*     */   private static final String CORES = "CORES";
/*     */   
/*     */   private Worker worker;
/*     */   
/*     */   private MemoryUsageDemo.DataGenerator dataGenerator;
/*     */   
/*     */   public GUI() throws Exception {
/*  91 */     ImageIcon img = new ImageIcon("./resources/icon.png");
/*  95 */     JFrame.setDefaultLookAndFeelDecorated(false);
/*  96 */     JDialog.setDefaultLookAndFeelDecorated(false);
/*  97 */     UIManager.put("ProgressBarUI", "javax.swing.plaf.metal.MetalProgressBarUI");
/*  98 */     UIManager.put("ProgressBar.background", Color.WHITE);
/*  99 */     UIManager.put("ProgressBar.foreground", Color.GREEN);
/* 100 */     UIManager.put("ProgressBar.selectionForeground", Color.black);
/* 101 */     UIManager.put("ProgressBar.selectionBackground", Color.black);
/*     */     try {
/* 104 */       UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
/* 106 */     } catch (ClassNotFoundException ex) {
/*     */     
/* 108 */     } catch (InstantiationException ex) {
/*     */     
/* 110 */     } catch (IllegalAccessException ex) {
/*     */     
/* 112 */     } catch (UnsupportedLookAndFeelException ex) {}
/* 119 */     this.frame = new JFrame("World2XPlane 0.7.4");
/* 120 */     this.frame.setIconImage(img.getImage());
/* 122 */     this.frame.setDefaultCloseOperation(3);
/* 123 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 124 */     Dimension frameSize = new Dimension(screenSize.width / 2, screenSize.height / 2);
/* 125 */     int x = frameSize.width / 2;
/* 126 */     int y = frameSize.height / 2;
/* 127 */     this.frame.setBounds(x, y, frameSize.width, frameSize.height);
/* 130 */     this.startButtton = new JButton("Start");
/* 131 */     this.startButtton.addActionListener(this);
/* 133 */     ImageIcon image = new ImageIcon("./resources/wait16.gif");
/* 134 */     this.busy = new JLabel(image);
/* 135 */     this.busy.setVisible(false);
/* 138 */     this.coreNumber = new JTextField();
/* 139 */     this.coreNumber.setText(this.prefs.get("CORES", "1"));
/* 141 */     this.osmFilePicker = new JFilePicker("OSM PBF File:", "Browse...");
/* 142 */     this.osmFilePicker.setMode(1);
/* 143 */     this.osmFilePicker.addFileTypeFilter(".pbf", "OSM PBF Files");
/*     */     try {
/* 145 */       String pbfFile = this.prefs.get("PBFLOC", "");
/* 146 */       if (pbfFile != null && pbfFile.length() > 0 && pbfFile.endsWith(".pbf") && (new File(pbfFile)).exists())
/* 147 */         this.osmFilePicker.setSelectedFilePath(this.prefs.get("PBFLOC", "")); 
/* 148 */     } catch (Exception e) {}
/* 152 */     this.polyFile = new JFilePicker("Poly File (For Exclusions):", "Browse...");
/* 153 */     this.polyFile.setMode(1);
/* 154 */     this.polyFile.addFileTypeFilter(".poly", "OSM Poly Files");
/*     */     try {
/* 156 */       String polyPath = this.prefs.get("POLYFILE", "");
/* 157 */       if (polyPath != null && polyPath.length() > 0 && polyPath.endsWith(".poly") && (new File(polyPath)).exists())
/* 158 */         this.polyFile.setSelectedFilePath(this.prefs.get("POLYFILE", "")); 
/* 159 */     } catch (Exception e) {}
/* 163 */     this.configfile = new JFilePicker("Config File:", "Browse...");
/* 164 */     this.configfile.setMode(1);
/* 165 */     this.configfile.addFileTypeFilter(".xml", "World2XPlane Config Files");
/*     */     try {
/* 168 */       this.configfile.setSelectedFilePath(this.prefs.get("CONFIGFILE", "./resources/config.xml"));
/* 169 */     } catch (Exception e) {}
/* 173 */     this.xplaneDirectory = new JFilePicker("X-Plane Main Directory:", "Browse...");
/* 174 */     this.xplaneDirectory.setMode(1);
/* 175 */     this.xplaneDirectory.getFileChooser().setFileSelectionMode(1);
/*     */     try {
/* 180 */       this.xplaneDirectory.getFileChooser().setCurrentDirectory(new File(this.prefs.get("XPLLOC", "./")));
/* 181 */       this.xplaneDirectory.setSelectedFilePath(this.prefs.get("XPLLOC", "./"));
/* 182 */     } catch (Exception e) {
/* 183 */       e.printStackTrace();
/*     */     } 
/* 186 */     this.textArea = new JTextArea();
/* 187 */     this.textArea.setBackground(Color.black);
/* 188 */     this.textArea.setForeground(Color.green);
/* 189 */     this.textArea.setEditable(false);
/* 191 */     this.useDatabase = new JCheckBox("Use Local Storage");
/* 192 */     this.useDatabase.setToolTipText("Use a local database to store information. This option is slower, but will use less memory and supports resuming.");
/* 193 */     this.useDatabase.setSelected(this.prefs.getBoolean("LOCAL_STORAGE", true));
/* 195 */     BorderLayout borderLayout = new BorderLayout();
/* 196 */     this.frame.getContentPane().setLayout(borderLayout);
/* 198 */     JPanel topPanel = new JPanel() {
/*     */         public Dimension getMaximumSize() {
/* 200 */           Dimension dim = super.getMaximumSize();
/* 201 */           dim.height = 18;
/* 202 */           return dim;
/*     */         }
/*     */       };
/* 206 */     FlowLayout topLayout = new FlowLayout();
/* 207 */     topLayout.setAlignment(0);
/* 208 */     topPanel.setLayout(topLayout);
/* 209 */     topPanel.add(this.osmFilePicker);
/* 211 */     this.osmFilePicker.getLabel().setPreferredSize(new Dimension(150, 20));
/* 213 */     this.validateDirectory = new JCheckBox("Validate Objects/Facades");
/* 214 */     this.validateDirectory.setSelected(this.prefs.getBoolean("VALIDATE", false));
/* 218 */     JPanel thirdPanel = new JPanel() {
/*     */         public Dimension getMaximumSize() {
/* 220 */           Dimension dim = super.getMaximumSize();
/* 221 */           dim.height = 32;
/* 222 */           return dim;
/*     */         }
/*     */       };
/* 225 */     thirdPanel.setLayout(new FlowLayout());
/* 226 */     thirdPanel.add(this.startButtton);
/* 227 */     thirdPanel.add(this.busy);
/* 230 */     this.tabbedPane = new JTabbedPane();
/* 233 */     JPanel mainPanel = new JPanel();
/* 234 */     BoxLayout boxLayout = new BoxLayout(mainPanel, 1);
/* 235 */     mainPanel.setLayout(boxLayout);
/* 236 */     mainPanel.add(topPanel);
/* 237 */     mainPanel.add(thirdPanel);
/* 238 */     this.progressWindow = new ProgressWindow();
/* 239 */     mainPanel.add(new JScrollPane(this.progressWindow));
/* 240 */     this.tabbedPane.addTab("Generation", mainPanel);
/* 241 */     this.frame.add(this.tabbedPane, "Center");
/* 247 */     JPanel statusPanel = new JPanel();
/* 248 */     statusPanel.setBorder(new BevelBorder(1));
/* 249 */     this.frame.add(statusPanel, "South");
/* 250 */     statusPanel.setPreferredSize(new Dimension(this.frame.getWidth(), 20));
/* 251 */     statusPanel.setLayout(new BoxLayout(statusPanel, 0));
/* 252 */     this.memoryField = new JLabel("");
/* 253 */     this.memoryField.setHorizontalAlignment(2);
/* 256 */     statusPanel.add(this.memoryField);
/* 259 */     this.progressBar = new JProgressBar(0, 100);
/* 260 */     this.progressBar.setStringPainted(true);
/* 261 */     statusPanel.add(this.progressBar);
/* 262 */     this.frame.getContentPane().add(statusPanel, "South");
/* 266 */     secondTab();
/* 267 */     thirdTab();
/* 269 */     this.frame.setVisible(true);
/* 270 */     this.frame.addWindowListener(this);
/*     */     try {
/* 277 */       PipedOutputStream pout = new PipedOutputStream(this.pin);
/* 278 */       System.setOut(new PrintStream(pout, true));
/* 279 */     } catch (IOException io) {
/* 280 */       this.textArea.append("Couldn't redirect STDOUT to this console\n" + io.getMessage());
/* 281 */     } catch (SecurityException se) {
/* 282 */       this.textArea.append("Couldn't redirect STDOUT to this console\n" + se.getMessage());
/*     */     } 
/*     */     try {
/* 286 */       PipedOutputStream pout2 = new PipedOutputStream(this.pin2);
/* 287 */       System.setErr(new PrintStream(pout2, true));
/* 288 */     } catch (IOException io) {
/* 289 */       this.textArea.append("Couldn't redirect STDERR to this console\n" + io.getMessage());
/* 290 */     } catch (SecurityException se) {
/* 291 */       this.textArea.append("Couldn't redirect STDERR to this console\n" + se.getMessage());
/*     */     } 
/* 294 */     this.quit = false;
/* 298 */     this.reader = new Thread(this);
/* 299 */     this.reader.setDaemon(true);
/* 300 */     this.reader.start();
/* 302 */     this.reader2 = new Thread(this);
/* 303 */     this.reader2.setDaemon(true);
/* 304 */     this.reader2.start();
/* 306 */     ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
/* 307 */     service.scheduleWithFixedDelay(new Runnable() {
/*     */           public void run() {
/* 309 */             int mb = 1048576;
/* 310 */             Runtime runtime = Runtime.getRuntime();
/* 311 */             long vmMemory = runtime.maxMemory() / mb;
/* 312 */             long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / mb;
/* 314 */             float percentage = (float)usedMemory / (float)vmMemory * 100.0F;
/* 315 */             if (percentage > 90.0F) {
/* 316 */               GUI.this.memoryField.setForeground(Color.red);
/* 317 */             } else if (percentage > 70.0F) {
/* 318 */               GUI.this.memoryField.setForeground(Color.blue);
/*     */             } else {
/* 320 */               GUI.this.memoryField.setForeground(Color.black);
/*     */             } 
/* 323 */             if (usedMemory > GUI.this.peakMemory)
/* 324 */               GUI.this.peakMemory = usedMemory; 
/* 327 */             GUI.this.memoryField.setText("Used/Available Memory: " + usedMemory + "/" + vmMemory + "MB " + (int)percentage + "%      ");
/* 328 */             if (GUI.this.worker != null) {
/* 329 */               float progress = GUI.this.worker.getProgress();
/* 330 */               if (progress == 0.0F) {
/* 331 */                 GUI.this.progressBar.setStringPainted(true);
/*     */               } else {
/* 333 */                 GUI.this.progressBar.setValue((int)GUI.this.worker.getProgress());
/* 335 */                 GUI.this.progressBar.setStringPainted(true);
/*     */               } 
/*     */             } 
/*     */           }
/*     */         },  0L, 4L, TimeUnit.SECONDS);
/*     */   }
/*     */   
/*     */   private void secondTab() {
/* 346 */     JPanel configPanel = new JPanel();
/* 347 */     BoxLayout boxLayout1 = new BoxLayout(configPanel, 1);
/* 348 */     configPanel.setLayout(boxLayout1);
/* 350 */     JPanel firstPanel = new JPanel() {
/*     */         public Dimension getMaximumSize() {
/* 352 */           Dimension dim = super.getMaximumSize();
/* 353 */           dim.height = 32;
/* 354 */           return dim;
/*     */         }
/*     */       };
/* 357 */     FlowLayout flowLayout1 = new FlowLayout();
/* 358 */     flowLayout1.setAlignment(0);
/* 359 */     firstPanel.setLayout(flowLayout1);
/* 360 */     firstPanel.add(this.configfile);
/* 361 */     this.configfile.getLabel().setPreferredSize(new Dimension(200, 22));
/* 362 */     firstPanel.add(this.useDatabase);
/* 363 */     configPanel.add(firstPanel);
/* 365 */     JPanel secondPanel = new JPanel() {
/*     */         public Dimension getMaximumSize() {
/* 367 */           Dimension dim = super.getMaximumSize();
/* 368 */           dim.height = 32;
/* 369 */           return dim;
/*     */         }
/*     */       };
/* 372 */     FlowLayout secondLayout = new FlowLayout();
/* 373 */     secondLayout.setAlignment(0);
/* 374 */     secondPanel.setLayout(secondLayout);
/* 375 */     secondPanel.add(this.xplaneDirectory);
/* 376 */     this.xplaneDirectory.getLabel().setPreferredSize(new Dimension(200, 22));
/* 378 */     secondPanel.add(this.validateDirectory);
/* 379 */     configPanel.add(secondPanel);
/* 381 */     JPanel thirdPanel = new JPanel() {
/*     */         public Dimension getMaximumSize() {
/* 383 */           Dimension dim = super.getMaximumSize();
/* 384 */           dim.height = 32;
/* 385 */           return dim;
/*     */         }
/*     */       };
/* 388 */     FlowLayout thirdLayout = new FlowLayout();
/* 389 */     thirdLayout.setAlignment(0);
/* 390 */     thirdPanel.setLayout(thirdLayout);
/* 391 */     thirdPanel.add(this.polyFile);
/* 392 */     this.polyFile.getLabel().setPreferredSize(new Dimension(200, 22));
/* 393 */     configPanel.add(thirdPanel);
/* 395 */     JPanel fourthPanel = new JPanel() {
/*     */         public Dimension getMaximumSize() {
/* 397 */           Dimension dim = super.getMaximumSize();
/* 398 */           dim.height = 32;
/* 399 */           return dim;
/*     */         }
/*     */       };
/* 402 */     FlowLayout fourthLayout = new FlowLayout();
/* 403 */     fourthLayout.setAlignment(0);
/* 404 */     fourthPanel.setLayout(thirdLayout);
/* 405 */     Label label = new Label("Number of processor instances");
/* 406 */     fourthPanel.add(label);
/* 407 */     fourthPanel.add(this.coreNumber);
/* 408 */     label = new Label("(Set to number of CPUs available. A too high value may cause a crash)");
/* 409 */     fourthPanel.add(label);
/* 411 */     this.polyFile.getLabel().setPreferredSize(new Dimension(200, 22));
/* 412 */     configPanel.add(fourthPanel);
/* 416 */     MemoryUsageDemo panel = new MemoryUsageDemo(30000);
/* 420 */     panel.getClass();
/* 420 */     this.dataGenerator = new MemoryUsageDemo.DataGenerator(panel, 500);
/* 421 */     this.dataGenerator.start();
/* 422 */     configPanel.add(panel);
/* 423 */     this.tabbedPane.add(configPanel, "Advanced");
/* 425 */     this.tabbedPane.addChangeListener(new ChangeListener() {
/*     */           public void stateChanged(ChangeEvent changeEvent) {
/* 428 */             if (GUI.this.tabbedPane.getSelectedIndex() == 0)
/* 429 */               GUI.this.dataGenerator.stop(); 
/* 431 */             if (GUI.this.tabbedPane.getSelectedIndex() == 1)
/* 432 */               GUI.this.dataGenerator.start(); 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void thirdTab() {
/* 439 */     JPanel logWindow = new JPanel();
/* 440 */     logWindow.setLayout(new BorderLayout());
/* 443 */     logWindow.add(new JScrollPane(this.textArea), "Center");
/* 444 */     this.tabbedPane.add(logWindow, "Log");
/*     */   }
/*     */   
/*     */   public synchronized void windowClosed(WindowEvent evt) {
/* 450 */     this.quit = true;
/* 451 */     notifyAll();
/*     */     try {
/* 453 */       this.reader.join(1000L);
/* 454 */       this.pin.close();
/* 455 */     } catch (Exception e) {}
/*     */     try {
/* 458 */       this.reader2.join(1000L);
/* 459 */       this.pin2.close();
/* 460 */     } catch (Exception e) {}
/* 462 */     System.exit(0);
/*     */   }
/*     */   
/*     */   public synchronized void windowClosing(WindowEvent evt) {
/* 466 */     this.frame.setVisible(false);
/* 467 */     this.frame.dispose();
/*     */   }
/*     */   
/*     */   public synchronized void actionPerformed(ActionEvent evt) {
/* 472 */     if (evt.getSource() == this.startButtton) {
/* 473 */       if (this.startButtton.getText().equals("Start")) {
/* 474 */         this.startButtton.setText("Stop");
/* 475 */         this.busy.setVisible(true);
/* 476 */         begin();
/*     */       } else {
/* 478 */         this.worker.interrupt();
/* 479 */         this.worker.stop();
/* 480 */         this.startButtton.setText("Start");
/* 481 */         this.busy.setVisible(false);
/*     */       } 
/*     */     } else {
/* 484 */       this.textArea.setText("");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void begin() {
/* 489 */     this.prefs.put("PBFLOC", this.osmFilePicker.getSelectedFilePath());
/* 490 */     this.prefs.put("CONFIGFILE", this.configfile.getSelectedFilePath());
/* 491 */     this.prefs.put("POLYFILE", this.polyFile.getSelectedFilePath());
/* 492 */     this.prefs.put("XPLLOC", this.xplaneDirectory.getSelectedFilePath());
/* 493 */     this.prefs.putBoolean("VALIDATE", this.validateDirectory.isSelected());
/* 494 */     this.prefs.putBoolean("LOCAL_STORAGE", this.useDatabase.isSelected());
/* 495 */     this.prefs.put("CORES", this.coreNumber.getText());
/* 499 */     if (this.osmFilePicker.getSelectedFilePath().toLowerCase().trim().endsWith(".xml")) {
/* 500 */       JOptionPane.showMessageDialog(null, "World2XPlane does not support OSM XML Files. Please convert the file to PBF (Consult manual for details).");
/* 501 */       this.startButtton.setText("Start");
/* 502 */       this.busy.setVisible(false);
/*     */       return;
/*     */     } 
/* 506 */     if (this.xplaneDirectory.getSelectedFilePath() == null || this.xplaneDirectory.getSelectedFilePath().length() == 0) {
/* 507 */       JOptionPane.showMessageDialog(null, "Please set the location of your X-Plane installation.");
/* 508 */       this.startButtton.setText("Start");
/* 509 */       this.busy.setVisible(false);
/*     */       return;
/*     */     } 
/* 513 */     File xPlane = new File(this.xplaneDirectory.getSelectedFilePath(), "Custom Scenery");
/* 514 */     if (!xPlane.exists()) {
/* 515 */       JOptionPane.showMessageDialog(null, "Cannot find your X-Plane directory. Please check the path and try again.");
/* 516 */       this.startButtton.setText("Start");
/* 517 */       this.busy.setVisible(false);
/*     */       return;
/*     */     } 
/* 522 */     File worldModels = new File(xPlane, "world-models");
/* 523 */     if (!worldModels.exists()) {
/* 524 */       worldModels = new File(xPlane, "world-models-master");
/* 525 */       if (!worldModels.exists()) {
/* 526 */         JOptionPane.showMessageDialog(null, "Cannot find the world-models library inside your X-Plane installation.Please install the latest world-models library into the Custom Scenery folder and try again.");
/* 528 */         this.startButtton.setText("Start");
/* 529 */         this.busy.setVisible(false);
/*     */         return;
/*     */       } 
/*     */     } 
/* 534 */     File path = new File(GeneratorStore.createOutputFolder(this.osmFilePicker.getSelectedFilePath()));
/* 535 */     boolean resume = false;
/* 536 */     if (path.exists()) {
/* 537 */       int response = JOptionPane.showConfirmDialog(null, "This file has been previously generated, do you wish to resume?", "Confirm", 0, 3);
/* 539 */       if (response == 0)
/* 540 */         resume = true; 
/*     */     } 
/* 545 */     String configFile = this.configfile.getSelectedFilePath();
/* 546 */     if (!(new File(configFile)).exists()) {
/* 547 */       JOptionPane.showMessageDialog(null, "The selected config file does not exist. Please check the path and try again");
/*     */       return;
/*     */     } 
/* 551 */     String polyFileName = this.polyFile.getSelectedFilePath();
/* 552 */     if (polyFileName != null && polyFileName.length() > 0 && !(new File(polyFileName)).exists()) {
/* 553 */       JOptionPane.showMessageDialog(null, "The selected polygon file does not exist. Please check the path and try again");
/*     */       return;
/*     */     } 
/* 556 */     int cores = 1;
/*     */     try {
/* 558 */       cores = (new Integer(this.coreNumber.getText())).intValue();
/* 559 */     } catch (Exception e) {}
/* 562 */     this.worker = new Worker(Integer.valueOf(cores), configFile, this.osmFilePicker.getSelectedFilePath(), this.xplaneDirectory.getSelectedFilePath(), polyFileName, this.validateDirectory.isSelected(), resume, this.useDatabase.isSelected(), new World2XPlane.StatusReporter() {
/*     */           public void onCompleted() {
/* 565 */             GUI.this.busy.setVisible(false);
/* 566 */             GUI.log.info("Generation Completed - Output file " + GeneratorStore.getGeneratorStore().getOutputFolder());
/* 567 */             GUI.log.info("Peak Memory Usage " + GUI.this.peakMemory + "MB");
/* 568 */             JOptionPane.showMessageDialog(null, "Generation Completed! " + GeneratorStore.getGeneratorStore().getOutputFolder());
/* 569 */             GUI.this.finished();
/*     */           }
/*     */           
/*     */           public void onError() {
/* 573 */             GUI.this.busy.setVisible(false);
/* 574 */             JOptionPane.showMessageDialog(null, "Generation Failed, Please check log window for details!");
/* 575 */             GUI.this.finished();
/*     */           }
/*     */           
/*     */           public void tileList(Set<DSFTile> dsfTiles) {
/* 580 */             GUI.this.progressWindow.setTiles(dsfTiles);
/*     */           }
/*     */           
/*     */           public void tileProcessing(DSFTile dsfTile) {
/* 585 */             GUI.this.progressWindow.setTileLoading(dsfTile);
/*     */           }
/*     */           
/*     */           public void tileStatus(DSFTile dsfTile, float status, String message) {
/* 590 */             GUI.this.progressWindow.setTileProgress(dsfTile, status, message);
/*     */           }
/*     */           
/*     */           public void tileComplete(DSFTile dsfTile, File file) {
/* 595 */             GUI.this.progressWindow.setTileComplete(dsfTile, file);
/*     */           }
/*     */           
/*     */           public void showMessage(String message) {
/* 600 */             GUI.this.progressWindow.showStatus(message);
/*     */           }
/*     */         });
/* 604 */     this.worker.start();
/* 605 */     this.busy.setVisible(true);
/*     */   }
/*     */   
/*     */   private void finished() {
/* 610 */     this.startButtton.setText("Start");
/* 611 */     this.dataGenerator.stop();
/*     */   }
/*     */   
/*     */   public synchronized void run() {
/*     */     try {
/* 616 */       while (Thread.currentThread() == this.reader) {
/*     */         try {
/* 618 */           wait(100L);
/* 619 */         } catch (InterruptedException ie) {}
/* 621 */         if (this.pin.available() != 0) {
/* 622 */           String input = readLine(this.pin);
/* 623 */           this.textArea.append(input);
/* 624 */           this.textArea.setCaretPosition(this.textArea.getDocument().getLength());
/*     */         } 
/* 628 */         if (this.quit)
/*     */           return; 
/*     */       } 
/* 631 */       while (Thread.currentThread() == this.reader2) {
/*     */         try {
/* 633 */           wait(100L);
/* 634 */         } catch (InterruptedException ie) {}
/* 636 */         if (this.pin2.available() != 0) {
/* 637 */           String input = readLine(this.pin2);
/* 638 */           this.textArea.append(input);
/* 639 */           this.textArea.setCaretPosition(this.textArea.getDocument().getLength());
/*     */         } 
/* 641 */         if (this.quit)
/*     */           return; 
/*     */       } 
/* 643 */     } catch (Exception e) {
/* 644 */       this.textArea.append("\nConsole reports an Internal error.");
/* 645 */       this.textArea.append("The error is: " + e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized String readLine(PipedInputStream in) throws IOException {
/* 652 */     String input = "";
/*     */     do {
/* 654 */       int available = in.available();
/* 655 */       if (available == 0)
/*     */         break; 
/* 656 */       byte[] b = new byte[available];
/* 657 */       in.read(b);
/* 658 */       input = input + new String(b, 0, b.length);
/* 659 */     } while (!input.endsWith("\n") && !input.endsWith("\r\n") && !this.quit);
/* 660 */     return input;
/*     */   }
/*     */   
/*     */   public static void main(String[] arg) throws Exception {
/* 666 */     int mb = 1048576;
/* 667 */     Runtime runtime = Runtime.getRuntime();
/* 669 */     long vmMemory = runtime.maxMemory() / mb;
/* 670 */     if (vmMemory < 2048L)
/* 671 */       JOptionPane.showMessageDialog(null, "You have a maximum of " + vmMemory + "MB assigned to this application. Consider assigning more memory, otherwise you may receive out of memory errors."); 
/* 677 */     new GUI();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\GUI\GUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */