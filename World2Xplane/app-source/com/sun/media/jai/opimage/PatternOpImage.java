/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.image.ColorModel;
/*    */ import java.awt.image.Raster;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.SourcelessOpImage;
/*    */ 
/*    */ public class PatternOpImage extends SourcelessOpImage {
/*    */   protected Raster pattern;
/*    */   
/*    */   private static ImageLayout layoutHelper(Raster pattern, ColorModel colorModel) {
/* 42 */     return new ImageLayout(pattern.getMinX(), pattern.getMinY(), pattern.getWidth(), pattern.getHeight(), pattern.getSampleModel(), colorModel);
/*    */   }
/*    */   
/*    */   public PatternOpImage(Raster pattern, ColorModel colorModel, int minX, int minY, int width, int height) {
/* 59 */     super(layoutHelper(pattern, colorModel), null, pattern.getSampleModel(), minX, minY, width, height);
/* 64 */     this.pattern = pattern;
/*    */   }
/*    */   
/*    */   public Raster getTile(int tileX, int tileY) {
/* 68 */     return computeTile(tileX, tileY);
/*    */   }
/*    */   
/*    */   public Raster computeTile(int tileX, int tileY) {
/* 79 */     return this.pattern.createChild(this.tileGridXOffset, this.tileGridYOffset, this.tileWidth, this.tileHeight, tileXToX(tileX), tileYToY(tileY), null);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\PatternOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */