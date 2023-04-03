/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.AbstractButton;
/*     */ 
/*     */ public final class FloatingButtonEnabler extends MouseAdapter {
/*     */   private static FloatingButtonEnabler singleton;
/*     */   
/*     */   public static FloatingButtonEnabler getInstance() {
/*  74 */     if (singleton == null)
/*  75 */       singleton = new FloatingButtonEnabler(); 
/*  77 */     return singleton;
/*     */   }
/*     */   
/*     */   public void addButton(AbstractButton button) {
/*  86 */     button.addMouseListener(this);
/*  87 */     button.setBorderPainted(false);
/*     */   }
/*     */   
/*     */   public void removeButton(AbstractButton button) {
/*  96 */     button.addMouseListener(this);
/*  97 */     button.setBorderPainted(true);
/*     */   }
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {
/* 106 */     if (e.getSource() instanceof AbstractButton) {
/* 107 */       AbstractButton button = (AbstractButton)e.getSource();
/* 108 */       if (button.isEnabled())
/* 109 */         button.setBorderPainted(true); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void mouseExited(MouseEvent e) {
/* 120 */     if (e.getSource() instanceof AbstractButton) {
/* 121 */       AbstractButton button = (AbstractButton)e.getSource();
/* 122 */       button.setBorderPainted(false);
/* 123 */       if (button.getParent() != null)
/* 127 */         button.getParent().repaint(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\FloatingButtonEnabler.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */