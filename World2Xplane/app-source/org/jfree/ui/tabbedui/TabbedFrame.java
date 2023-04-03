/*     */ package org.jfree.ui.tabbedui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class TabbedFrame extends JFrame {
/*     */   private AbstractTabbedUI tabbedUI;
/*     */   
/*     */   private class MenuBarChangeListener implements PropertyChangeListener {
/*     */     private final TabbedFrame this$0;
/*     */     
/*     */     public MenuBarChangeListener(TabbedFrame this$0) {
/*  73 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent evt) {
/*  83 */       if (evt.getPropertyName().equals("jMenuBar"))
/*  84 */         this.this$0.setJMenuBar(this.this$0.getTabbedUI().getJMenuBar()); 
/*     */     }
/*     */   }
/*     */   
/*     */   public TabbedFrame() {}
/*     */   
/*     */   public TabbedFrame(String title) {
/* 101 */     super(title);
/*     */   }
/*     */   
/*     */   protected final AbstractTabbedUI getTabbedUI() {
/* 111 */     return this.tabbedUI;
/*     */   }
/*     */   
/*     */   public void init(AbstractTabbedUI tabbedUI) {
/* 121 */     this.tabbedUI = tabbedUI;
/* 122 */     this.tabbedUI.addPropertyChangeListener("jMenuBar", new MenuBarChangeListener(this));
/* 126 */     addWindowListener(new WindowAdapter(this) {
/*     */           private final TabbedFrame this$0;
/*     */           
/*     */           public void windowClosing(WindowEvent e) {
/* 128 */             this.this$0.getTabbedUI().getCloseAction().actionPerformed(new ActionEvent(this, 1001, null, 0));
/*     */           }
/*     */         });
/* 133 */     JPanel panel = new JPanel();
/* 134 */     panel.setLayout(new BorderLayout());
/* 135 */     panel.add(tabbedUI, "Center");
/* 136 */     setContentPane(panel);
/* 137 */     setJMenuBar(tabbedUI.getJMenuBar());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\tabbedui\TabbedFrame.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */