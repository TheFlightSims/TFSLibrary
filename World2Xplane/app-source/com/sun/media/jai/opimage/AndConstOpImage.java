/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ColormapOpImage;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ final class AndConstOpImage extends ColormapOpImage {
/*     */   protected int[] constants;
/*     */   
/*     */   public AndConstOpImage(RenderedImage source, Map config, ImageLayout layout, int[] constants) {
/*  68 */     super(source, layout, config, true);
/*  70 */     int numBands = getSampleModel().getNumBands();
/*  72 */     if (constants.length < numBands) {
/*  73 */       this.constants = new int[numBands];
/*  74 */       for (int i = 0; i < numBands; i++)
/*  75 */         this.constants[i] = constants[0]; 
/*     */     } else {
/*  78 */       this.constants = (int[])constants.clone();
/*     */     } 
/*  82 */     permitInPlaceOperation();
/*  85 */     initializeColormapOperation();
/*     */   }
/*     */   
/*     */   protected void transformColormap(byte[][] colormap) {
/*  92 */     for (int b = 0; b < 3; b++) {
/*  93 */       byte[] map = colormap[b];
/*  94 */       int mapSize = map.length;
/*  96 */       int c = (b < this.constants.length) ? this.constants[b] : this.constants[0];
/*  98 */       for (int i = 0; i < mapSize; i++)
/*  99 */         map[i] = ImageUtil.clampRoundByte((map[i] & 0xFF & c)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 117 */     RasterFormatTag[] formatTags = getFormatTags();
/* 119 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 121 */     RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 123 */     RasterAccessor src = new RasterAccessor(sources[0], srcRect, formatTags[0], getSourceImage(0).getColorModel());
/* 127 */     switch (dst.getDataType()) {
/*     */       case 0:
/* 129 */         computeRectByte(src, dst);
/*     */         break;
/*     */       case 1:
/*     */       case 2:
/* 133 */         computeRectShort(src, dst);
/*     */         break;
/*     */       case 3:
/* 136 */         computeRectInt(src, dst);
/*     */         break;
/*     */     } 
/* 141 */     dst.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor src, RasterAccessor dst) {
/* 146 */     int dstWidth = dst.getWidth();
/* 147 */     int dstHeight = dst.getHeight();
/* 148 */     int dstBands = dst.getNumBands();
/* 150 */     int dstLineStride = dst.getScanlineStride();
/* 151 */     int dstPixelStride = dst.getPixelStride();
/* 152 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 153 */     byte[][] dstData = dst.getByteDataArrays();
/* 155 */     int srcLineStride = src.getScanlineStride();
/* 156 */     int srcPixelStride = src.getPixelStride();
/* 157 */     int[] srcBandOffsets = src.getBandOffsets();
/* 158 */     byte[][] srcData = src.getByteDataArrays();
/* 160 */     for (int b = 0; b < dstBands; b++) {
/* 161 */       int c = this.constants[b];
/* 162 */       byte[] d = dstData[b];
/* 163 */       byte[] s = srcData[b];
/* 165 */       int dstLineOffset = dstBandOffsets[b];
/* 166 */       int srcLineOffset = srcBandOffsets[b];
/* 168 */       for (int h = 0; h < dstHeight; h++) {
/* 169 */         int dstPixelOffset = dstLineOffset;
/* 170 */         int srcPixelOffset = srcLineOffset;
/* 172 */         dstLineOffset += dstLineStride;
/* 173 */         srcLineOffset += srcLineStride;
/* 175 */         for (int w = 0; w < dstWidth; w++) {
/* 176 */           d[dstPixelOffset] = (byte)(s[srcPixelOffset] & c);
/* 178 */           dstPixelOffset += dstPixelStride;
/* 179 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/* 187 */     int dstWidth = dst.getWidth();
/* 188 */     int dstHeight = dst.getHeight();
/* 189 */     int dstBands = dst.getNumBands();
/* 191 */     int dstLineStride = dst.getScanlineStride();
/* 192 */     int dstPixelStride = dst.getPixelStride();
/* 193 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 194 */     short[][] dstData = dst.getShortDataArrays();
/* 196 */     int srcLineStride = src.getScanlineStride();
/* 197 */     int srcPixelStride = src.getPixelStride();
/* 198 */     int[] srcBandOffsets = src.getBandOffsets();
/* 199 */     short[][] srcData = src.getShortDataArrays();
/* 201 */     for (int b = 0; b < dstBands; b++) {
/* 202 */       int c = this.constants[b];
/* 203 */       short[] d = dstData[b];
/* 204 */       short[] s = srcData[b];
/* 206 */       int dstLineOffset = dstBandOffsets[b];
/* 207 */       int srcLineOffset = srcBandOffsets[b];
/* 209 */       for (int h = 0; h < dstHeight; h++) {
/* 210 */         int dstPixelOffset = dstLineOffset;
/* 211 */         int srcPixelOffset = srcLineOffset;
/* 213 */         dstLineOffset += dstLineStride;
/* 214 */         srcLineOffset += srcLineStride;
/* 216 */         for (int w = 0; w < dstWidth; w++) {
/* 217 */           d[dstPixelOffset] = (short)(s[srcPixelOffset] & c);
/* 219 */           dstPixelOffset += dstPixelStride;
/* 220 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/* 228 */     int dstWidth = dst.getWidth();
/* 229 */     int dstHeight = dst.getHeight();
/* 230 */     int dstBands = dst.getNumBands();
/* 232 */     int dstLineStride = dst.getScanlineStride();
/* 233 */     int dstPixelStride = dst.getPixelStride();
/* 234 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 235 */     int[][] dstData = dst.getIntDataArrays();
/* 237 */     int srcLineStride = src.getScanlineStride();
/* 238 */     int srcPixelStride = src.getPixelStride();
/* 239 */     int[] srcBandOffsets = src.getBandOffsets();
/* 240 */     int[][] srcData = src.getIntDataArrays();
/* 242 */     for (int b = 0; b < dstBands; b++) {
/* 243 */       int c = this.constants[b];
/* 244 */       int[] d = dstData[b];
/* 245 */       int[] s = srcData[b];
/* 247 */       int dstLineOffset = dstBandOffsets[b];
/* 248 */       int srcLineOffset = srcBandOffsets[b];
/* 250 */       for (int h = 0; h < dstHeight; h++) {
/* 251 */         int dstPixelOffset = dstLineOffset;
/* 252 */         int srcPixelOffset = srcLineOffset;
/* 254 */         dstLineOffset += dstLineStride;
/* 255 */         srcLineOffset += srcLineStride;
/* 257 */         for (int w = 0; w < dstWidth; w++) {
/* 258 */           d[dstPixelOffset] = s[srcPixelOffset] & c;
/* 260 */           dstPixelOffset += dstPixelStride;
/* 261 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AndConstOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */