/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.TileObserver;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.awt.image.WritableRenderedImage;
/*     */ 
/*     */ public final class WritableRenderedImageAdapter extends RenderedImageAdapter implements WritableRenderedImage {
/*     */   private WritableRenderedImage theWritableImage;
/*     */   
/*     */   public WritableRenderedImageAdapter(WritableRenderedImage im) {
/*  57 */     super(im);
/*  58 */     this.theWritableImage = im;
/*     */   }
/*     */   
/*     */   public final void addTileObserver(TileObserver tileObserver) {
/*  70 */     if (tileObserver == null)
/*  71 */       throw new IllegalArgumentException(JaiI18N.getString("WritableRenderedImageAdapter0")); 
/*  73 */     this.theWritableImage.addTileObserver(tileObserver);
/*     */   }
/*     */   
/*     */   public final void removeTileObserver(TileObserver tileObserver) {
/*  86 */     if (tileObserver == null)
/*  87 */       throw new IllegalArgumentException(JaiI18N.getString("WritableRenderedImageAdapter0")); 
/*  89 */     this.theWritableImage.removeTileObserver(tileObserver);
/*     */   }
/*     */   
/*     */   public final WritableRaster getWritableTile(int tileX, int tileY) {
/* 104 */     return this.theWritableImage.getWritableTile(tileX, tileY);
/*     */   }
/*     */   
/*     */   public final void releaseWritableTile(int tileX, int tileY) {
/* 122 */     this.theWritableImage.releaseWritableTile(tileX, tileY);
/*     */   }
/*     */   
/*     */   public final boolean isTileWritable(int tileX, int tileY) {
/* 134 */     return this.theWritableImage.isTileWritable(tileX, tileY);
/*     */   }
/*     */   
/*     */   public final Point[] getWritableTileIndices() {
/* 145 */     return this.theWritableImage.getWritableTileIndices();
/*     */   }
/*     */   
/*     */   public final boolean hasTileWriters() {
/* 155 */     return this.theWritableImage.hasTileWriters();
/*     */   }
/*     */   
/*     */   public final void setData(Raster raster) {
/* 167 */     if (raster == null)
/* 168 */       throw new IllegalArgumentException(JaiI18N.getString("WritableRenderedImageAdapter1")); 
/* 170 */     this.theWritableImage.setData(raster);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\WritableRenderedImageAdapter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */