/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class WaitingImageObserver implements ImageObserver, Serializable, Cloneable {
/*     */   static final long serialVersionUID = -807204410581383550L;
/*     */   
/*     */   private boolean lock;
/*     */   
/*     */   private Image image;
/*     */   
/*     */   private boolean error;
/*     */   
/*     */   public WaitingImageObserver(Image image) {
/*  88 */     if (image == null)
/*  89 */       throw new NullPointerException(); 
/*  91 */     this.image = image;
/*  92 */     this.lock = true;
/*     */   }
/*     */   
/*     */   public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
/* 121 */     if ((infoflags & 0x20) == 32) {
/* 122 */       this.lock = false;
/* 123 */       this.error = false;
/* 125 */     } else if ((infoflags & 0x80) == 128 || (infoflags & 0x40) == 64) {
/* 127 */       this.lock = false;
/* 128 */       this.error = true;
/*     */     } 
/* 130 */     return true;
/*     */   }
/*     */   
/*     */   public void waitImageLoaded() {
/* 138 */     BufferedImage img = new BufferedImage(1, 1, 1);
/* 141 */     Graphics g = img.getGraphics();
/* 143 */     while (this.lock) {
/* 144 */       if (g.drawImage(this.image, 0, 0, img.getWidth(this), img.getHeight(this), this))
/*     */         return; 
/*     */       try {
/* 149 */         Thread.sleep(200L);
/* 151 */       } catch (InterruptedException e) {
/* 152 */         Log.info("WaitingImageObserver.waitImageLoaded(): InterruptedException thrown", e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 168 */     WaitingImageObserver obs = (WaitingImageObserver)super.clone();
/* 169 */     return obs;
/*     */   }
/*     */   
/*     */   public boolean isError() {
/* 178 */     return this.error;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\WaitingImageObserver.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */