/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ 
/*     */ public final class PackedImageData {
/*     */   public final Raster raster;
/*     */   
/*     */   public final Rectangle rect;
/*     */   
/*     */   public final byte[] data;
/*     */   
/*     */   public final int lineStride;
/*     */   
/*     */   public final int offset;
/*     */   
/*     */   public final int bitOffset;
/*     */   
/*     */   public final boolean coercedZeroOffset;
/*     */   
/*     */   public final boolean convertToDest;
/*     */   
/*     */   public PackedImageData(Raster raster, Rectangle rect, byte[] data, int lineStride, int offset, int bitOffset, boolean coercedZeroOffset, boolean convertToDest) {
/* 100 */     this.raster = raster;
/* 101 */     this.rect = rect;
/* 102 */     this.data = data;
/* 103 */     this.lineStride = lineStride;
/* 104 */     this.offset = offset;
/* 105 */     this.bitOffset = bitOffset;
/* 106 */     this.coercedZeroOffset = coercedZeroOffset;
/* 107 */     this.convertToDest = convertToDest;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\PackedImageData.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */