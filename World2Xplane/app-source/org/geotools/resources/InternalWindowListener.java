/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.awt.event.WindowListener;
/*     */ import javax.swing.event.InternalFrameEvent;
/*     */ import javax.swing.event.InternalFrameListener;
/*     */ 
/*     */ final class InternalWindowListener implements InternalFrameListener {
/*     */   private final WindowListener listener;
/*     */   
/*     */   public static InternalFrameListener wrap(WindowListener listener) {
/*  46 */     if (listener == null)
/*  47 */       return null; 
/*  49 */     if (listener instanceof InternalFrameListener)
/*  50 */       return (InternalFrameListener)listener; 
/*  52 */     return new InternalWindowListener(listener);
/*     */   }
/*     */   
/*     */   private InternalWindowListener(WindowListener listener) {
/*  60 */     this.listener = listener;
/*     */   }
/*     */   
/*     */   public void internalFrameOpened(InternalFrameEvent event) {
/*  67 */     this.listener.windowOpened(null);
/*     */   }
/*     */   
/*     */   public void internalFrameClosing(InternalFrameEvent event) {
/*  75 */     this.listener.windowClosing(null);
/*     */   }
/*     */   
/*     */   public void internalFrameClosed(InternalFrameEvent event) {
/*  82 */     this.listener.windowClosed(null);
/*     */   }
/*     */   
/*     */   public void internalFrameIconified(InternalFrameEvent event) {
/*  89 */     this.listener.windowIconified(null);
/*     */   }
/*     */   
/*     */   public void internalFrameDeiconified(InternalFrameEvent event) {
/*  96 */     this.listener.windowDeiconified(null);
/*     */   }
/*     */   
/*     */   public void internalFrameActivated(InternalFrameEvent event) {
/* 103 */     this.listener.windowActivated(null);
/*     */   }
/*     */   
/*     */   public void internalFrameDeactivated(InternalFrameEvent event) {
/* 110 */     this.listener.windowDeactivated(null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\InternalWindowListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */