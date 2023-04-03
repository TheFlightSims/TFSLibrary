/*     */ package org.jfree.ui.action;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.Action;
/*     */ 
/*     */ public class ActionConcentrator {
/*  64 */   private final ArrayList actions = new ArrayList();
/*     */   
/*     */   public void addAction(Action a) {
/*  73 */     if (a == null)
/*  74 */       throw new NullPointerException(); 
/*  76 */     this.actions.add(a);
/*     */   }
/*     */   
/*     */   public void removeAction(Action a) {
/*  85 */     if (a == null)
/*  86 */       throw new NullPointerException(); 
/*  88 */     this.actions.remove(a);
/*     */   }
/*     */   
/*     */   public void setEnabled(boolean b) {
/*  97 */     for (int i = 0; i < this.actions.size(); i++) {
/*  98 */       Action a = this.actions.get(i);
/*  99 */       a.setEnabled(b);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEnabled() {
/* 112 */     for (int i = 0; i < this.actions.size(); i++) {
/* 113 */       Action a = this.actions.get(i);
/* 114 */       if (a.isEnabled())
/* 115 */         return true; 
/*     */     } 
/* 118 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\action\ActionConcentrator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */