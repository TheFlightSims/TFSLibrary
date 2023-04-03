/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import javax.swing.text.JTextComponent;
/*     */ 
/*     */ public final class JTextObserver implements FocusListener {
/*     */   private static JTextObserver singleton;
/*     */   
/*     */   public static JTextObserver getInstance() {
/*  73 */     if (singleton == null)
/*  74 */       singleton = new JTextObserver(); 
/*  76 */     return singleton;
/*     */   }
/*     */   
/*     */   public void focusGained(FocusEvent e) {
/*  85 */     if (e.getSource() instanceof JTextComponent) {
/*  86 */       JTextComponent tex = (JTextComponent)e.getSource();
/*  87 */       tex.selectAll();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void focusLost(FocusEvent e) {
/*  97 */     if (e.getSource() instanceof JTextComponent) {
/*  98 */       JTextComponent tex = (JTextComponent)e.getSource();
/*  99 */       tex.select(0, 0);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void addTextComponent(JTextComponent t) {
/* 109 */     if (singleton == null)
/* 110 */       singleton = new JTextObserver(); 
/* 112 */     t.addFocusListener(singleton);
/*     */   }
/*     */   
/*     */   public static void removeTextComponent(JTextComponent t) {
/* 121 */     if (singleton == null)
/* 122 */       singleton = new JTextObserver(); 
/* 124 */     t.removeFocusListener(singleton);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\JTextObserver.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */