/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ final class OrOpImage extends PointOpImage {
/*     */   public OrOpImage(RenderedImage source1, RenderedImage source2, Map config, ImageLayout layout) {
/*  78 */     super(source1, source2, layout, config, true);
/*  81 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  97 */     RasterFormatTag[] formatTags = getFormatTags();
/* 100 */     RasterAccessor s1 = new RasterAccessor(sources[0], destRect, formatTags[0], getSource(0).getColorModel());
/* 103 */     RasterAccessor s2 = new RasterAccessor(sources[1], destRect, formatTags[1], getSource(1).getColorModel());
/* 106 */     RasterAccessor d = new RasterAccessor(dest, destRect, formatTags[2], getColorModel());
/* 110 */     if (d.isBinary()) {
/* 111 */       byte[] src1Bits = s1.getBinaryDataArray();
/* 112 */       byte[] src2Bits = s2.getBinaryDataArray();
/* 113 */       byte[] dstBits = d.getBinaryDataArray();
/* 115 */       int length = dstBits.length;
/* 116 */       for (int i = 0; i < length; i++)
/* 117 */         dstBits[i] = (byte)(src1Bits[i] | src2Bits[i]); 
/* 120 */       d.copyBinaryDataToRaster();
/*     */       return;
/*     */     } 
/* 124 */     int src1LineStride = s1.getScanlineStride();
/* 125 */     int src1PixelStride = s1.getPixelStride();
/* 126 */     int[] src1BandOffsets = s1.getBandOffsets();
/* 128 */     int src2LineStride = s2.getScanlineStride();
/* 129 */     int src2PixelStride = s2.getPixelStride();
/* 130 */     int[] src2BandOffsets = s2.getBandOffsets();
/* 132 */     int dstNumBands = d.getNumBands();
/* 133 */     int dstWidth = d.getWidth();
/* 134 */     int dstHeight = d.getHeight();
/* 135 */     int dstLineStride = d.getScanlineStride();
/* 136 */     int dstPixelStride = d.getPixelStride();
/* 137 */     int[] dstBandOffsets = d.getBandOffsets();
/* 139 */     switch (d.getDataType()) {
/*     */       case 0:
/* 142 */         byteLoop(dstNumBands, dstWidth, dstHeight, src1LineStride, src1PixelStride, src1BandOffsets, s1.getByteDataArrays(), src2LineStride, src2PixelStride, src2BandOffsets, s2.getByteDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, d.getByteDataArrays());
/*     */         break;
/*     */       case 1:
/*     */       case 2:
/* 153 */         shortLoop(dstNumBands, dstWidth, dstHeight, src1LineStride, src1PixelStride, src1BandOffsets, s1.getShortDataArrays(), src2LineStride, src2PixelStride, src2BandOffsets, s2.getShortDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, d.getShortDataArrays());
/*     */         break;
/*     */       case 3:
/* 163 */         intLoop(dstNumBands, dstWidth, dstHeight, src1LineStride, src1PixelStride, src1BandOffsets, s1.getIntDataArrays(), src2LineStride, src2PixelStride, src2BandOffsets, s2.getIntDataArrays(), dstLineStride, dstPixelStride, dstBandOffsets, d.getIntDataArrays());
/*     */         break;
/*     */     } 
/* 173 */     d.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void byteLoop(int dstNumBands, int dstWidth, int dstHeight, int src1LineStride, int src1PixelStride, int[] src1BandOffsets, byte[][] src1Data, int src2LineStride, int src2PixelStride, int[] src2BandOffsets, byte[][] src2Data, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, byte[][] dstData) {
/* 184 */     for (int b = 0; b < dstNumBands; b++) {
/* 185 */       byte[] s1 = src1Data[b];
/* 186 */       byte[] s2 = src2Data[b];
/* 187 */       byte[] d = dstData[b];
/* 188 */       int src1LineOffset = src1BandOffsets[b];
/* 189 */       int src2LineOffset = src2BandOffsets[b];
/* 190 */       int dstLineOffset = dstBandOffsets[b];
/* 192 */       for (int h = 0; h < dstHeight; h++) {
/* 193 */         int src1PixelOffset = src1LineOffset;
/* 194 */         int src2PixelOffset = src2LineOffset;
/* 195 */         int dstPixelOffset = dstLineOffset;
/* 196 */         src1LineOffset += src1LineStride;
/* 197 */         src2LineOffset += src2LineStride;
/* 198 */         dstLineOffset += dstLineStride;
/* 200 */         for (int w = 0; w < dstWidth; w++) {
/* 201 */           d[dstPixelOffset] = (byte)(s1[src1PixelOffset] | s2[src2PixelOffset]);
/* 203 */           src1PixelOffset += src1PixelStride;
/* 204 */           src2PixelOffset += src2PixelStride;
/* 205 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shortLoop(int dstNumBands, int dstWidth, int dstHeight, int src1LineStride, int src1PixelStride, int[] src1BandOffsets, short[][] src1Data, int src2LineStride, int src2PixelStride, int[] src2BandOffsets, short[][] src2Data, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, short[][] dstData) {
/* 219 */     for (int b = 0; b < dstNumBands; b++) {
/* 220 */       short[] s1 = src1Data[b];
/* 221 */       short[] s2 = src2Data[b];
/* 222 */       short[] d = dstData[b];
/* 223 */       int src1LineOffset = src1BandOffsets[b];
/* 224 */       int src2LineOffset = src2BandOffsets[b];
/* 225 */       int dstLineOffset = dstBandOffsets[b];
/* 227 */       for (int h = 0; h < dstHeight; h++) {
/* 228 */         int src1PixelOffset = src1LineOffset;
/* 229 */         int src2PixelOffset = src2LineOffset;
/* 230 */         int dstPixelOffset = dstLineOffset;
/* 231 */         src1LineOffset += src1LineStride;
/* 232 */         src2LineOffset += src2LineStride;
/* 233 */         dstLineOffset += dstLineStride;
/* 235 */         for (int w = 0; w < dstWidth; w++) {
/* 236 */           d[dstPixelOffset] = (short)(s1[src1PixelOffset] | s2[src2PixelOffset]);
/* 239 */           src1PixelOffset += src1PixelStride;
/* 240 */           src2PixelOffset += src2PixelStride;
/* 241 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void intLoop(int dstNumBands, int dstWidth, int dstHeight, int src1LineStride, int src1PixelStride, int[] src1BandOffsets, int[][] src1Data, int src2LineStride, int src2PixelStride, int[] src2BandOffsets, int[][] src2Data, int dstLineStride, int dstPixelStride, int[] dstBandOffsets, int[][] dstData) {
/* 255 */     for (int b = 0; b < dstNumBands; b++) {
/* 256 */       int[] s1 = src1Data[b];
/* 257 */       int[] s2 = src2Data[b];
/* 258 */       int[] d = dstData[b];
/* 259 */       int src1LineOffset = src1BandOffsets[b];
/* 260 */       int src2LineOffset = src2BandOffsets[b];
/* 261 */       int dstLineOffset = dstBandOffsets[b];
/* 263 */       for (int h = 0; h < dstHeight; h++) {
/* 264 */         int src1PixelOffset = src1LineOffset;
/* 265 */         int src2PixelOffset = src2LineOffset;
/* 266 */         int dstPixelOffset = dstLineOffset;
/* 267 */         src1LineOffset += src1LineStride;
/* 268 */         src2LineOffset += src2LineStride;
/* 269 */         dstLineOffset += dstLineStride;
/* 271 */         for (int w = 0; w < dstWidth; w++) {
/* 272 */           d[dstPixelOffset] = s1[src1PixelOffset] | s2[src2PixelOffset];
/* 274 */           src1PixelOffset += src1PixelStride;
/* 275 */           src2PixelOffset += src2PixelStride;
/* 276 */           dstPixelOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\OrOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */