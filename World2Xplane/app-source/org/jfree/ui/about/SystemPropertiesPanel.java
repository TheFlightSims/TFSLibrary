/*     */ package org.jfree.ui.about;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.Clipboard;
/*     */ import java.awt.datatransfer.StringSelection;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.ListSelectionModel;
/*     */ 
/*     */ public class SystemPropertiesPanel extends JPanel {
/*     */   private JTable table;
/*     */   
/*     */   private JPopupMenu copyPopupMenu;
/*     */   
/*     */   private JMenuItem copyMenuItem;
/*     */   
/*     */   private PopupListener copyPopupListener;
/*     */   
/*     */   public SystemPropertiesPanel() {
/*  92 */     String baseName = "org.jfree.ui.about.resources.AboutResources";
/*  93 */     ResourceBundle resources = ResourceBundle.getBundle("org.jfree.ui.about.resources.AboutResources");
/*  95 */     setLayout(new BorderLayout());
/*  96 */     this.table = (JTable)SystemProperties.createSystemPropertiesTable();
/*  97 */     add(new JScrollPane(this.table));
/* 100 */     this.copyPopupMenu = new JPopupMenu();
/* 102 */     String label = resources.getString("system-properties-panel.popup-menu.copy");
/* 103 */     KeyStroke accelerator = (KeyStroke)resources.getObject("system-properties-panel.popup-menu.copy.accelerator");
/* 105 */     this.copyMenuItem = new JMenuItem(label);
/* 106 */     this.copyMenuItem.setAccelerator(accelerator);
/* 107 */     this.copyMenuItem.getAccessibleContext().setAccessibleDescription(label);
/* 108 */     this.copyMenuItem.addActionListener(new ActionListener(this) {
/*     */           private final SystemPropertiesPanel this$0;
/*     */           
/*     */           public void actionPerformed(ActionEvent e) {
/* 110 */             this.this$0.copySystemPropertiesToClipboard();
/*     */           }
/*     */         });
/* 113 */     this.copyPopupMenu.add(this.copyMenuItem);
/* 116 */     this.copyPopupListener = new PopupListener(this);
/* 117 */     this.table.addMouseListener(this.copyPopupListener);
/*     */   }
/*     */   
/*     */   public void copySystemPropertiesToClipboard() {
/* 126 */     StringBuffer buffer = new StringBuffer();
/* 127 */     ListSelectionModel selection = this.table.getSelectionModel();
/* 128 */     int firstRow = selection.getMinSelectionIndex();
/* 129 */     int lastRow = selection.getMaxSelectionIndex();
/* 130 */     if (firstRow != -1 && lastRow != -1)
/* 131 */       for (int r = firstRow; r <= lastRow; r++) {
/* 132 */         for (int c = 0; c < this.table.getColumnCount(); c++) {
/* 133 */           buffer.append(this.table.getValueAt(r, c));
/* 134 */           if (c != 2)
/* 135 */             buffer.append("\t"); 
/*     */         } 
/* 138 */         buffer.append("\n");
/*     */       }  
/* 141 */     StringSelection ss = new StringSelection(buffer.toString());
/* 142 */     Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 143 */     cb.setContents(ss, ss);
/*     */   }
/*     */   
/*     */   protected final JPopupMenu getCopyPopupMenu() {
/* 155 */     return this.copyPopupMenu;
/*     */   }
/*     */   
/*     */   protected final JTable getTable() {
/* 164 */     return this.table;
/*     */   }
/*     */   
/*     */   private class PopupListener extends MouseAdapter {
/*     */     private final SystemPropertiesPanel this$0;
/*     */     
/*     */     public PopupListener(SystemPropertiesPanel this$0) {
/* 175 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void mousePressed(MouseEvent e) {
/* 184 */       maybeShowPopup(e);
/*     */     }
/*     */     
/*     */     public void mouseReleased(MouseEvent e) {
/* 193 */       maybeShowPopup(e);
/*     */     }
/*     */     
/*     */     private void maybeShowPopup(MouseEvent e) {
/* 202 */       if (e.isPopupTrigger())
/* 203 */         this.this$0.getCopyPopupMenu().show(this.this$0.getTable(), e.getX(), e.getY()); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\about\SystemPropertiesPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */