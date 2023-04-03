/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ 
/*     */ public final class UnpackedImageData {
/*     */   public final Raster raster;
/*     */   
/*     */   public final Rectangle rect;
/*     */   
/*     */   public final int type;
/*     */   
/*     */   public final Object data;
/*     */   
/*     */   public final int pixelStride;
/*     */   
/*     */   public final int lineStride;
/*     */   
/*     */   public final int[] bandOffsets;
/*     */   
/*     */   public final boolean convertToDest;
/*     */   
/*     */   public UnpackedImageData(Raster raster, Rectangle rect, int type, Object data, int pixelStride, int lineStride, int[] bandOffsets, boolean convertToDest) {
/*  91 */     this.raster = raster;
/*  92 */     this.rect = rect;
/*  93 */     this.type = type;
/*  94 */     this.data = data;
/*  95 */     this.pixelStride = pixelStride;
/*  96 */     this.lineStride = lineStride;
/*  97 */     this.bandOffsets = bandOffsets;
/*  98 */     this.convertToDest = convertToDest;
/*     */   }
/*     */   
/*     */   public byte[][] getByteData() {
/* 109 */     return (this.type == 0) ? (byte[][])this.data : (byte[][])null;
/*     */   }
/*     */   
/*     */   public byte[] getByteData(int b) {
/* 119 */     byte[][] d = getByteData();
/* 120 */     return (d == null) ? null : d[b];
/*     */   }
/*     */   
/*     */   public short[][] getShortData() {
/* 131 */     return (this.type == 1 || this.type == 2) ? (short[][])this.data : (short[][])null;
/*     */   }
/*     */   
/*     */   public short[] getShortData(int b) {
/* 143 */     short[][] d = getShortData();
/* 144 */     return (d == null) ? null : d[b];
/*     */   }
/*     */   
/*     */   public int[][] getIntData() {
/* 155 */     return (this.type == 3) ? (int[][])this.data : (int[][])null;
/*     */   }
/*     */   
/*     */   public int[] getIntData(int b) {
/* 165 */     int[][] d = getIntData();
/* 166 */     return (d == null) ? null : d[b];
/*     */   }
/*     */   
/*     */   public float[][] getFloatData() {
/* 177 */     return (this.type == 4) ? (float[][])this.data : (float[][])null;
/*     */   }
/*     */   
/*     */   public float[] getFloatData(int b) {
/* 187 */     float[][] d = getFloatData();
/* 188 */     return (d == null) ? null : d[b];
/*     */   }
/*     */   
/*     */   public double[][] getDoubleData() {
/* 199 */     return (this.type == 5) ? (double[][])this.data : (double[][])null;
/*     */   }
/*     */   
/*     */   public double[] getDoubleData(int b) {
/* 209 */     double[][] d = getDoubleData();
/* 210 */     return (d == null) ? null : d[b];
/*     */   }
/*     */   
/*     */   public int getOffset(int b) {
/* 218 */     return this.bandOffsets[b];
/*     */   }
/*     */   
/*     */   public int getMinOffset() {
/* 225 */     int min = this.bandOffsets[0];
/* 226 */     for (int i = 1; i < this.bandOffsets.length; i++)
/* 227 */       min = Math.min(min, this.bandOffsets[i]); 
/* 229 */     return min;
/*     */   }
/*     */   
/*     */   public int getMaxOffset() {
/* 236 */     int max = this.bandOffsets[0];
/* 237 */     for (int i = 1; i < this.bandOffsets.length; i++)
/* 238 */       max = Math.max(max, this.bandOffsets[i]); 
/* 240 */     return max;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\UnpackedImageData.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */