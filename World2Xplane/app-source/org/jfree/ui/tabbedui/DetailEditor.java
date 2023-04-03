/*     */ package org.jfree.ui.tabbedui;
/*     */ 
/*     */ import javax.swing.JComponent;
/*     */ 
/*     */ public abstract class DetailEditor extends JComponent {
/*     */   private Object object;
/*     */   
/*     */   private boolean confirmed;
/*     */   
/*     */   public void update() {
/*  70 */     if (this.object == null)
/*  71 */       throw new IllegalStateException(); 
/*  74 */     updateObject(this.object);
/*  76 */     setConfirmed(false);
/*     */   }
/*     */   
/*     */   public Object getObject() {
/*  85 */     return this.object;
/*     */   }
/*     */   
/*     */   public void setObject(Object object) {
/*  94 */     if (object == null)
/*  95 */       throw new NullPointerException(); 
/*  97 */     this.object = object;
/*  98 */     setConfirmed(false);
/*  99 */     fillObject();
/*     */   }
/*     */   
/*     */   protected static int parseInt(String text, int def) {
/*     */     try {
/* 113 */       return Integer.parseInt(text);
/* 115 */     } catch (NumberFormatException fe) {
/* 116 */       return def;
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract void clear();
/*     */   
/*     */   protected abstract void fillObject();
/*     */   
/*     */   protected abstract void updateObject(Object paramObject);
/*     */   
/*     */   public boolean isConfirmed() {
/* 144 */     return this.confirmed;
/*     */   }
/*     */   
/*     */   protected void setConfirmed(boolean confirmed) {
/* 153 */     boolean oldConfirmed = this.confirmed;
/* 154 */     this.confirmed = confirmed;
/* 155 */     firePropertyChange("confirmed", oldConfirmed, confirmed);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\tabbedui\DetailEditor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */