/*    */ package com.sun.media.jai.util;
/*    */ 
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.ColorModel;
/*    */ import java.awt.image.ImageConsumer;
/*    */ import java.awt.image.ImageProducer;
/*    */ import java.awt.image.Raster;
/*    */ import java.util.Vector;
/*    */ import javax.media.jai.PlanarImage;
/*    */ 
/*    */ public class PlanarImageProducer implements ImageProducer {
/*    */   PlanarImage im;
/*    */   
/* 24 */   Vector consumers = new Vector();
/*    */   
/*    */   public PlanarImageProducer(PlanarImage im) {
/* 27 */     this.im = im.createSnapshot();
/*    */   }
/*    */   
/*    */   public void addConsumer(ImageConsumer ic) {
/* 31 */     if (!this.consumers.contains(ic))
/* 32 */       this.consumers.add(ic); 
/* 34 */     produceImage();
/*    */   }
/*    */   
/*    */   public boolean isConsumer(ImageConsumer ic) {
/* 38 */     return this.consumers.contains(ic);
/*    */   }
/*    */   
/*    */   public void removeConsumer(ImageConsumer ic) {
/* 42 */     this.consumers.remove(ic);
/*    */   }
/*    */   
/*    */   public void requestTopDownLeftRightResend(ImageConsumer ic) {
/* 46 */     startProduction(ic);
/*    */   }
/*    */   
/*    */   public void startProduction(ImageConsumer ic) {
/* 50 */     if (!this.consumers.contains(ic))
/* 51 */       this.consumers.add(ic); 
/* 53 */     produceImage();
/*    */   }
/*    */   
/*    */   private synchronized void produceImage() {
/* 57 */     int numConsumers = this.consumers.size();
/* 59 */     int minX = this.im.getMinX();
/* 60 */     int minY = this.im.getMinY();
/* 61 */     int width = this.im.getWidth();
/* 62 */     int height = this.im.getHeight();
/* 63 */     int numBands = this.im.getSampleModel().getNumBands();
/* 64 */     int scansize = width * numBands;
/* 65 */     ColorModel colorModel = this.im.getColorModel();
/* 66 */     int[] pixels = new int[scansize];
/* 68 */     Rectangle rect = new Rectangle(minX, minY, width, 1);
/* 70 */     for (int j = 0; j < numConsumers; j++) {
/* 71 */       ImageConsumer ic = this.consumers.elementAt(j);
/* 72 */       ic.setHints(22);
/*    */     } 
/* 77 */     for (int y = minY; y < minY + height; y++) {
/* 78 */       rect.y = y;
/* 79 */       Raster row = this.im.getData(rect);
/* 80 */       row.getPixels(minX, y, width, 1, pixels);
/* 82 */       for (int k = 0; k < numConsumers; k++) {
/* 83 */         ImageConsumer ic = this.consumers.elementAt(k);
/* 84 */         ic.setPixels(0, y - minY, width, 1, colorModel, pixels, 0, scansize);
/*    */       } 
/*    */     } 
/* 89 */     for (int i = 0; i < numConsumers; i++) {
/* 90 */       ImageConsumer ic = this.consumers.elementAt(i);
/* 91 */       ic.imageComplete(3);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\PlanarImageProducer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */