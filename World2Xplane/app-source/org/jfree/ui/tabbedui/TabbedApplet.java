/*     */ package org.jfree.ui.tabbedui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.JApplet;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class TabbedApplet extends JApplet {
/*     */   private AbstractTabbedUI tabbedUI;
/*     */   
/*     */   private class MenuBarChangeListener implements PropertyChangeListener {
/*     */     private final TabbedApplet this$0;
/*     */     
/*     */     public MenuBarChangeListener(TabbedApplet this$0) {
/*  66 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent evt) {
/*  76 */       if (evt.getPropertyName().equals("jMenuBar"))
/*  77 */         this.this$0.setJMenuBar(this.this$0.getTabbedUI().getJMenuBar()); 
/*     */     }
/*     */   }
/*     */   
/*     */   protected final AbstractTabbedUI getTabbedUI() {
/*  99 */     return this.tabbedUI;
/*     */   }
/*     */   
/*     */   public void init(AbstractTabbedUI tabbedUI) {
/* 108 */     this.tabbedUI = tabbedUI;
/* 109 */     this.tabbedUI.addPropertyChangeListener("jMenuBar", new MenuBarChangeListener(this));
/* 112 */     JPanel panel = new JPanel();
/* 113 */     panel.setLayout(new BorderLayout());
/* 114 */     panel.add(tabbedUI, "Center");
/* 115 */     setContentPane(panel);
/* 116 */     setJMenuBar(tabbedUI.getJMenuBar());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\tabbedui\TabbedApplet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */