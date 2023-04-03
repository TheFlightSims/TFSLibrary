/*     */ package org.jfree.ui.tabbedui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class TabbedDialog extends JDialog {
/*     */   private AbstractTabbedUI tabbedUI;
/*     */   
/*     */   private class MenuBarChangeListener implements PropertyChangeListener {
/*     */     private final TabbedDialog this$0;
/*     */     
/*     */     public MenuBarChangeListener(TabbedDialog this$0) {
/*  75 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent evt) {
/*  85 */       if (evt.getPropertyName().equals("jMenuBar"))
/*  86 */         this.this$0.setJMenuBar(this.this$0.getTabbedUI().getJMenuBar()); 
/*     */     }
/*     */   }
/*     */   
/*     */   public TabbedDialog() {}
/*     */   
/*     */   public TabbedDialog(Dialog owner) {
/* 102 */     super(owner);
/*     */   }
/*     */   
/*     */   public TabbedDialog(Dialog owner, boolean modal) {
/* 112 */     super(owner, modal);
/*     */   }
/*     */   
/*     */   public TabbedDialog(Dialog owner, String title) {
/* 122 */     super(owner, title);
/*     */   }
/*     */   
/*     */   public TabbedDialog(Dialog owner, String title, boolean modal) {
/* 133 */     super(owner, title, modal);
/*     */   }
/*     */   
/*     */   public TabbedDialog(Frame owner) {
/* 142 */     super(owner);
/*     */   }
/*     */   
/*     */   public TabbedDialog(Frame owner, boolean modal) {
/* 152 */     super(owner, modal);
/*     */   }
/*     */   
/*     */   public TabbedDialog(Frame owner, String title) {
/* 162 */     super(owner, title);
/*     */   }
/*     */   
/*     */   public TabbedDialog(Frame owner, String title, boolean modal) {
/* 173 */     super(owner, title, modal);
/*     */   }
/*     */   
/*     */   protected final AbstractTabbedUI getTabbedUI() {
/* 184 */     return this.tabbedUI;
/*     */   }
/*     */   
/*     */   public void init(AbstractTabbedUI tabbedUI) {
/* 194 */     this.tabbedUI = tabbedUI;
/* 195 */     this.tabbedUI.addPropertyChangeListener("jMenuBar", new MenuBarChangeListener(this));
/* 198 */     addWindowListener(new WindowAdapter(this) {
/*     */           private final TabbedDialog this$0;
/*     */           
/*     */           public void windowClosing(WindowEvent e) {
/* 200 */             this.this$0.getTabbedUI().getCloseAction().actionPerformed(new ActionEvent(this, 1001, null, 0));
/*     */           }
/*     */         });
/* 205 */     JPanel panel = new JPanel();
/* 206 */     panel.setLayout(new BorderLayout());
/* 207 */     panel.add(tabbedUI, "Center");
/* 208 */     setContentPane(panel);
/* 209 */     setJMenuBar(tabbedUI.getJMenuBar());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\tabbedui\TabbedDialog.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */