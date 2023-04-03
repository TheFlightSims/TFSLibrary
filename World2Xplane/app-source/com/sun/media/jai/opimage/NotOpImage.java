/*     */ package com.sun.media.jai.opimage;
/*     */ 
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
/*     */ final class NotOpImage extends ColormapOpImage {
/*     */   public NotOpImage(RenderedImage source, Map config, ImageLayout layout) {
/*  61 */     super(source, layout, config, true);
/*  64 */     permitInPlaceOperation();
/*  67 */     initializeColormapOperation();
/*     */   }
/*     */   
/*     */   protected void transformColormap(byte[][] colormap) {
/*  75 */     for (int b = 0; b < 3; b++) {
/*  76 */       byte[] map = colormap[b];
/*  77 */       int mapSize = map.length;
/*  79 */       for (int i = 0; i < mapSize; i++)
/*  80 */         map[i] = (byte)(map[i] ^ 0xFFFFFFFF); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  98 */     RasterFormatTag[] formatTags = getFormatTags();
/* 101 */     RasterAccessor src = new RasterAccessor(sources[0], destRect, formatTags[0], getSource(0).getColorModel());
/* 104 */     RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 107 */     if (dst.isBinary()) {
/* 108 */       byte[] srcBits = src.getBinaryDataArray();
/* 109 */       byte[] dstBits = dst.getBinaryDataArray();
/* 111 */       int length = dstBits.length;
/* 112 */       for (int i = 0; i < length; i++)
/* 113 */         dstBits[i] = (byte)(srcBits[i] ^ 0xFFFFFFFF); 
/* 116 */       dst.copyBinaryDataToRaster();
/*     */       return;
/*     */     } 
/* 121 */     int srcLineStride = src.getScanlineStride();
/* 122 */     int srcPixelStride = src.getPixelStride();
/* 123 */     int[] srcBandOffsets = src.getBandOffsets();
/* 125 */     int dstNumBands = dst.getNumBands();
/* 126 */     int dstWidth = dst.getWidth();
/* 127 */     int dstHeight = dst.getHeight();
/* 128 */     int dstLineStride = dst.getScanlineStride();
/* 129 */     int dstPixelStride = dst.getPixelStride();
/* 130 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 132 */     switch (dst.getDataType()) {
/*     */       case 0:
/* 135 */         byteLoop(dstNumBands, dstWidth, dstHeight, srcLineStride, srcPixelStride, srcBandOffsets, src.getByteDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, dst.getByteDataArrays());
/*     */         break;
/*     */       case 1:
/*     */       case 2:
/* 144 */         shortLoop(dstNumBands, dstWidth, dstHeight, srcLineStride, srcPixelStride, srcBandOffsets, src.getShortDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, dst.getShortDataArrays());
/*     */         break;
/*     */       case 3:
/* 152 */         intLoop(dstNumBands, dstWidth, dstHeight, srcLineStride, srcPixelStride, srcBandOffsets, src.getIntDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, dst.getIntDataArrays());
/*     */         break;
/*     */     } 
/* 160 */     dst.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void byteLoop(int dstNumBands, int dstWidth, int dstHeight, int srcLineStride, int srcPixelStride, int[] srcBandOffsets, byte[][] srcData, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, byte[][] dstData) {
/* 169 */     for (int b = 0; b < dstNumBands; b++) {
/* 170 */       byte[] s = srcData[b];
/* 171 */       byte[] d = dstData[b];
/* 172 */       int srcLineOffset = srcBandOffsets[b];
/* 173 */       int dstLineOffset = dstBandOffsets[b];
/* 175 */       for (int h = 0; h < dstHeight; h++) {
/* 176 */         int srcPixelOffset = srcLineOffset;
/* 177 */         int dstPixelOffset = dstLineOffset;
/* 178 */         srcLineOffset += srcLineStride;
/* 179 */         dstLineOffset += dstLineStride;
/* 181 */         for (int w = 0; w < dstWidth; w++) {
/* 182 */           d[dstPixelOffset] = (byte)(s[srcPixelOffset] ^ 0xFFFFFFFF);
/* 183 */           srcPixelOffset += srcPixelStride;
/* 184 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(int dstNumBands, int dstWidth, int dstHeight, int srcLineStride, int srcPixelStride, int[] srcBandOffsets, short[][] srcData, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, short[][] dstData) {
/* 196 */     for (int b = 0; b < dstNumBands; b++) {
/* 197 */       short[] s = srcData[b];
/* 198 */       short[] d = dstData[b];
/* 199 */       int srcLineOffset = srcBandOffsets[b];
/* 200 */       int dstLineOffset = dstBandOffsets[b];
/* 202 */       for (int h = 0; h < dstHeight; h++) {
/* 203 */         int srcPixelOffset = srcLineOffset;
/* 204 */         int dstPixelOffset = dstLineOffset;
/* 205 */         srcLineOffset += srcLineStride;
/* 206 */         dstLineOffset += dstLineStride;
/* 208 */         for (int w = 0; w < dstWidth; w++) {
/* 209 */           d[dstPixelOffset] = (short)(s[srcPixelOffset] ^ 0xFFFFFFFF);
/* 210 */           srcPixelOffset += srcPixelStride;
/* 211 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(int dstNumBands, int dstWidth, int dstHeight, int srcLineStride, int srcPixelStride, int[] srcBandOffsets, int[][] srcData, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, int[][] dstData) {
/* 223 */     for (int b = 0; b < dstNumBands; b++) {
/* 224 */       int[] s = srcData[b];
/* 225 */       int[] d = dstData[b];
/* 226 */       int srcLineOffset = srcBandOffsets[b];
/* 227 */       int dstLineOffset = dstBandOffsets[b];
/* 229 */       for (int h = 0; h < dstHeight; h++) {
/* 230 */         int srcPixelOffset = srcLineOffset;
/* 231 */         int dstPixelOffset = dstLineOffset;
/* 232 */         srcLineOffset += srcLineStride;
/* 233 */         dstLineOffset += dstLineStride;
/* 235 */         for (int w = 0; w < dstWidth; w++) {
/* 236 */           d[dstPixelOffset] = s[srcPixelOffset] ^ 0xFFFFFFFF;
/* 237 */           srcPixelOffset += srcPixelStride;
/* 238 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\NotOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */