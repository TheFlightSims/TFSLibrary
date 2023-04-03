/*     */ package org.jfree.ui.about;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class SystemPropertiesFrame extends JFrame implements ActionListener {
/*     */   private static final String COPY_COMMAND = "COPY";
/*     */   
/*     */   private static final String CLOSE_COMMAND = "CLOSE";
/*     */   
/*     */   private SystemPropertiesPanel panel;
/*     */   
/*     */   public SystemPropertiesFrame(boolean menu) {
/*  93 */     String baseName = "org.jfree.ui.about.resources.AboutResources";
/*  94 */     ResourceBundle resources = ResourceBundle.getBundle("org.jfree.ui.about.resources.AboutResources");
/*  96 */     String title = resources.getString("system-frame.title");
/*  97 */     setTitle(title);
/*  99 */     setDefaultCloseOperation(2);
/* 101 */     if (menu)
/* 102 */       setJMenuBar(createMenuBar(resources)); 
/* 105 */     JPanel content = new JPanel(new BorderLayout());
/* 106 */     this.panel = new SystemPropertiesPanel();
/* 107 */     this.panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
/* 109 */     content.add(this.panel, "Center");
/* 111 */     JPanel buttonPanel = new JPanel(new BorderLayout());
/* 112 */     buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
/* 114 */     String label = resources.getString("system-frame.button.close");
/* 115 */     Character mnemonic = (Character)resources.getObject("system-frame.button.close.mnemonic");
/* 116 */     JButton closeButton = new JButton(label);
/* 117 */     closeButton.setMnemonic(mnemonic.charValue());
/* 119 */     closeButton.setActionCommand("CLOSE");
/* 120 */     closeButton.addActionListener(this);
/* 122 */     buttonPanel.add(closeButton, "East");
/* 123 */     content.add(buttonPanel, "South");
/* 125 */     setContentPane(content);
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 136 */     String command = e.getActionCommand();
/* 137 */     if (command.equals("CLOSE")) {
/* 138 */       dispose();
/* 140 */     } else if (command.equals("COPY")) {
/* 141 */       this.panel.copySystemPropertiesToClipboard();
/*     */     } 
/*     */   }
/*     */   
/*     */   private JMenuBar createMenuBar(ResourceBundle resources) {
/* 156 */     JMenuBar menuBar = new JMenuBar();
/* 158 */     String label = resources.getString("system-frame.menu.file");
/* 159 */     Character mnemonic = (Character)resources.getObject("system-frame.menu.file.mnemonic");
/* 160 */     JMenu fileMenu = new JMenu(label, true);
/* 161 */     fileMenu.setMnemonic(mnemonic.charValue());
/* 163 */     label = resources.getString("system-frame.menu.file.close");
/* 164 */     mnemonic = (Character)resources.getObject("system-frame.menu.file.close.mnemonic");
/* 165 */     JMenuItem closeItem = new JMenuItem(label, mnemonic.charValue());
/* 166 */     closeItem.setActionCommand("CLOSE");
/* 168 */     closeItem.addActionListener(this);
/* 169 */     fileMenu.add(closeItem);
/* 171 */     label = resources.getString("system-frame.menu.edit");
/* 172 */     mnemonic = (Character)resources.getObject("system-frame.menu.edit.mnemonic");
/* 173 */     JMenu editMenu = new JMenu(label);
/* 174 */     editMenu.setMnemonic(mnemonic.charValue());
/* 176 */     label = resources.getString("system-frame.menu.edit.copy");
/* 177 */     mnemonic = (Character)resources.getObject("system-frame.menu.edit.copy.mnemonic");
/* 178 */     JMenuItem copyItem = new JMenuItem(label, mnemonic.charValue());
/* 179 */     copyItem.setActionCommand("COPY");
/* 180 */     copyItem.addActionListener(this);
/* 181 */     editMenu.add(copyItem);
/* 183 */     menuBar.add(fileMenu);
/* 184 */     menuBar.add(editMenu);
/* 185 */     return menuBar;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\about\SystemPropertiesFrame.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */