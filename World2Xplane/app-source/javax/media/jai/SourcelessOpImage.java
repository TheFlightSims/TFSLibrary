/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public abstract class SourcelessOpImage extends OpImage {
/*     */   private static ImageLayout layoutHelper(int minX, int minY, int width, int height, SampleModel sampleModel, ImageLayout il) {
/*  43 */     ImageLayout layout = (il == null) ? new ImageLayout() : (ImageLayout)il.clone();
/*  46 */     layout.setMinX(minX);
/*  47 */     layout.setMinY(minY);
/*  48 */     layout.setWidth(width);
/*  49 */     layout.setHeight(height);
/*  50 */     layout.setSampleModel(sampleModel);
/*  52 */     if (!layout.isValid(16))
/*  53 */       layout.setTileGridXOffset(layout.getMinX(null)); 
/*  55 */     if (!layout.isValid(32))
/*  56 */       layout.setTileGridYOffset(layout.getMinY(null)); 
/*  59 */     return layout;
/*     */   }
/*     */   
/*     */   public SourcelessOpImage(ImageLayout layout, Map configuration, SampleModel sampleModel, int minX, int minY, int width, int height) {
/* 101 */     super((Vector)null, layoutHelper(minX, minY, width, height, sampleModel, layout), configuration, false);
/*     */   }
/*     */   
/*     */   public boolean computesUniqueTiles() {
/* 113 */     return false;
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 129 */     Point org = new Point(tileXToX(tileX), tileYToY(tileY));
/* 130 */     WritableRaster dest = createWritableRaster(this.sampleModel, org);
/* 133 */     Rectangle rect = new Rectangle(org.x, org.y, this.sampleModel.getWidth(), this.sampleModel.getHeight());
/* 136 */     Rectangle destRect = rect.intersection(getBounds());
/* 137 */     computeRect((PlanarImage[])null, dest, destRect);
/* 138 */     return dest;
/*     */   }
/*     */   
/*     */   public Rectangle mapSourceRect(Rectangle sourceRect, int sourceIndex) {
/* 152 */     throw new IllegalArgumentException(JaiI18N.getString("SourcelessOpImage0"));
/*     */   }
/*     */   
/*     */   public Rectangle mapDestRect(Rectangle destRect, int sourceIndex) {
/* 167 */     throw new IllegalArgumentException(JaiI18N.getString("SourcelessOpImage0"));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\SourcelessOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */