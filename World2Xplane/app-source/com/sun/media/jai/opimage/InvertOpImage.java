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
/*     */ final class InvertOpImage extends ColormapOpImage {
/*     */   public InvertOpImage(RenderedImage source, Map config, ImageLayout layout) {
/*  65 */     super(source, layout, config, true);
/*  68 */     permitInPlaceOperation();
/*  71 */     initializeColormapOperation();
/*     */   }
/*     */   
/*     */   protected void transformColormap(byte[][] colormap) {
/*  79 */     for (int b = 0; b < 3; b++) {
/*  80 */       byte[] map = colormap[b];
/*  81 */       int mapSize = map.length;
/*  83 */       for (int i = 0; i < mapSize; i++)
/*  84 */         map[i] = (byte)(255 - (map[i] & 0xFF)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 101 */     RasterFormatTag[] formatTags = getFormatTags();
/* 104 */     RasterAccessor s = new RasterAccessor(sources[0], destRect, formatTags[0], getSourceImage(0).getColorModel());
/* 107 */     RasterAccessor d = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 110 */     if (d.isBinary()) {
/* 111 */       byte[] srcBits = s.getBinaryDataArray();
/* 112 */       byte[] dstBits = d.getBinaryDataArray();
/* 113 */       int length = dstBits.length;
/* 114 */       for (int i = 0; i < length; i++)
/* 115 */         dstBits[i] = (byte)(srcBits[i] ^ 0xFFFFFFFF); 
/* 117 */       d.copyBinaryDataToRaster();
/*     */     } else {
/* 119 */       switch (d.getDataType()) {
/*     */         case 0:
/* 121 */           computeRectByte(s, d);
/*     */           break;
/*     */         case 1:
/* 124 */           computeRectUShort(s, d);
/*     */           break;
/*     */         case 2:
/* 127 */           computeRectShort(s, d);
/*     */           break;
/*     */         case 3:
/* 130 */           computeRectInt(s, d);
/*     */           break;
/*     */         case 4:
/*     */         case 5:
/* 134 */           throw new RuntimeException(JaiI18N.getString("InvertOpImage0"));
/*     */       } 
/* 137 */       d.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor src, RasterAccessor dst) {
/* 142 */     int sLineStride = src.getScanlineStride();
/* 143 */     int sPixelStride = src.getPixelStride();
/* 144 */     int[] sBandOffsets = src.getBandOffsets();
/* 145 */     byte[][] sData = src.getByteDataArrays();
/* 147 */     int dwidth = dst.getWidth();
/* 148 */     int dheight = dst.getHeight();
/* 149 */     int bands = dst.getNumBands();
/* 150 */     int dLineStride = dst.getScanlineStride();
/* 151 */     int dPixelStride = dst.getPixelStride();
/* 152 */     int[] dBandOffsets = dst.getBandOffsets();
/* 153 */     byte[][] dData = dst.getByteDataArrays();
/* 155 */     for (int b = 0; b < bands; b++) {
/* 156 */       byte[] s = sData[b];
/* 157 */       byte[] d = dData[b];
/* 159 */       int sLineOffset = sBandOffsets[b];
/* 160 */       int dLineOffset = dBandOffsets[b];
/* 162 */       for (int h = 0; h < dheight; h++) {
/* 163 */         int sPixelOffset = sLineOffset;
/* 164 */         int dPixelOffset = dLineOffset;
/* 166 */         sLineOffset += sLineStride;
/* 167 */         dLineOffset += dLineStride;
/* 169 */         int dstEnd = dPixelOffset + dwidth * dPixelStride;
/* 170 */         while (dPixelOffset < dstEnd) {
/* 171 */           d[dPixelOffset] = (byte)(255 - (s[sPixelOffset] & 0xFF));
/* 172 */           sPixelOffset += sPixelStride;
/* 173 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src, RasterAccessor dst) {
/* 180 */     int sLineStride = src.getScanlineStride();
/* 181 */     int sPixelStride = src.getPixelStride();
/* 182 */     int[] sBandOffsets = src.getBandOffsets();
/* 183 */     short[][] sData = src.getShortDataArrays();
/* 185 */     int dwidth = dst.getWidth();
/* 186 */     int dheight = dst.getHeight();
/* 187 */     int bands = dst.getNumBands();
/* 188 */     int dLineStride = dst.getScanlineStride();
/* 189 */     int dPixelStride = dst.getPixelStride();
/* 190 */     int[] dBandOffsets = dst.getBandOffsets();
/* 191 */     short[][] dData = dst.getShortDataArrays();
/* 193 */     for (int b = 0; b < bands; b++) {
/* 194 */       short[] s = sData[b];
/* 195 */       short[] d = dData[b];
/* 197 */       int sLineOffset = sBandOffsets[b];
/* 198 */       int dLineOffset = dBandOffsets[b];
/* 200 */       for (int h = 0; h < dheight; h++) {
/* 201 */         int sPixelOffset = sLineOffset;
/* 202 */         int dPixelOffset = dLineOffset;
/* 204 */         sLineOffset += sLineStride;
/* 205 */         dLineOffset += dLineStride;
/* 207 */         int dstEnd = dPixelOffset + dwidth * dPixelStride;
/* 208 */         while (dPixelOffset < dstEnd) {
/* 209 */           d[dPixelOffset] = (short)(65535 - (s[sPixelOffset] & 0xFFFF));
/* 210 */           sPixelOffset += sPixelStride;
/* 211 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/* 218 */     int sLineStride = src.getScanlineStride();
/* 219 */     int sPixelStride = src.getPixelStride();
/* 220 */     int[] sBandOffsets = src.getBandOffsets();
/* 221 */     short[][] sData = src.getShortDataArrays();
/* 223 */     int dwidth = dst.getWidth();
/* 224 */     int dheight = dst.getHeight();
/* 225 */     int bands = dst.getNumBands();
/* 226 */     int dLineStride = dst.getScanlineStride();
/* 227 */     int dPixelStride = dst.getPixelStride();
/* 228 */     int[] dBandOffsets = dst.getBandOffsets();
/* 229 */     short[][] dData = dst.getShortDataArrays();
/* 231 */     for (int b = 0; b < bands; b++) {
/* 232 */       short[] s = sData[b];
/* 233 */       short[] d = dData[b];
/* 235 */       int sLineOffset = sBandOffsets[b];
/* 236 */       int dLineOffset = dBandOffsets[b];
/* 238 */       for (int h = 0; h < dheight; h++) {
/* 239 */         int sPixelOffset = sLineOffset;
/* 240 */         int dPixelOffset = dLineOffset;
/* 242 */         sLineOffset += sLineStride;
/* 243 */         dLineOffset += dLineStride;
/* 245 */         int dstEnd = dPixelOffset + dwidth * dPixelStride;
/* 246 */         while (dPixelOffset < dstEnd) {
/* 247 */           d[dPixelOffset] = (short)(32767 - s[sPixelOffset]);
/* 250 */           sPixelOffset += sPixelStride;
/* 251 */           dPixelOffset += dPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/* 258 */     int i, b, sLineStride = src.getScanlineStride();
/* 259 */     int sPixelStride = src.getPixelStride();
/* 260 */     int[] sBandOffsets = src.getBandOffsets();
/* 261 */     int[][] sData = src.getIntDataArrays();
/* 263 */     int dwidth = dst.getWidth();
/* 264 */     int dheight = dst.getHeight();
/* 265 */     int bands = dst.getNumBands();
/* 266 */     int dLineStride = dst.getScanlineStride();
/* 267 */     int dPixelStride = dst.getPixelStride();
/* 268 */     int[] dBandOffsets = dst.getBandOffsets();
/* 269 */     int[][] dData = dst.getIntDataArrays();
/* 276 */     int[] s = sData[0];
/* 277 */     int[] d = dData[0];
/* 278 */     int pixels = d.length;
/* 285 */     switch (this.sampleModel.getTransferType()) {
/*     */       case 0:
/* 287 */         for (i = 0; i < pixels; i++)
/* 288 */           d[i] = (s[i] ^ 0xFFFFFFFF) & 0xFF; 
/*     */         break;
/*     */       case 1:
/* 293 */         for (i = 0; i < pixels; i++)
/* 294 */           d[i] = (s[i] ^ 0xFFFFFFFF) & 0xFFFF; 
/*     */         break;
/*     */       case 2:
/* 299 */         for (i = 0; i < pixels; i++)
/* 300 */           d[i] = 32767 - s[i]; 
/*     */         break;
/*     */       case 3:
/* 305 */         for (b = 0; b < bands; b++) {
/* 306 */           s = sData[b];
/* 307 */           d = dData[b];
/* 309 */           int sLineOffset = sBandOffsets[b];
/* 310 */           int dLineOffset = dBandOffsets[b];
/* 312 */           for (int h = 0; h < dheight; h++) {
/* 313 */             int sPixelOffset = sLineOffset;
/* 314 */             int dPixelOffset = dLineOffset;
/* 316 */             sLineOffset += sLineStride;
/* 317 */             dLineOffset += dLineStride;
/* 319 */             int dstEnd = dPixelOffset + dwidth * dPixelStride;
/* 320 */             while (dPixelOffset < dstEnd) {
/* 321 */               d[dPixelOffset] = Integer.MAX_VALUE - s[sPixelOffset];
/* 323 */               sPixelOffset += sPixelStride;
/* 324 */               dPixelOffset += dPixelStride;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\InvertOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */