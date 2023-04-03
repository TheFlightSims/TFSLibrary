/*     */ package com.sun.media.jai.tilecodec;
/*     */ 
/*     */ import com.sun.image.codec.jpeg.JPEGCodec;
/*     */ import com.sun.image.codec.jpeg.JPEGDecodeParam;
/*     */ import com.sun.image.codec.jpeg.JPEGImageDecoder;
/*     */ import com.sun.image.codec.jpeg.JPEGQTable;
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.Point;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import javax.media.jai.ParameterListDescriptor;
/*     */ import javax.media.jai.RasterFactory;
/*     */ import javax.media.jai.tilecodec.TileCodecDescriptor;
/*     */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*     */ import javax.media.jai.tilecodec.TileDecoderImpl;
/*     */ import javax.media.jai.util.ImagingListener;
/*     */ 
/*     */ public class JPEGTileDecoder extends TileDecoderImpl {
/*  41 */   private TileCodecDescriptor tcd = null;
/*     */   
/*     */   public JPEGTileDecoder(InputStream input, TileCodecParameterList param) {
/*  63 */     super("jpeg", input, param);
/*  64 */     this.tcd = TileCodecUtils.getTileCodecDescriptor("tileDecoder", "jpeg");
/*     */   }
/*     */   
/*     */   public Raster decode() throws IOException {
/*  81 */     if (!this.tcd.includesLocationInfo())
/*  82 */       throw new IllegalArgumentException(JaiI18N.getString("JPEGTileDecoder0")); 
/*  84 */     return decode((Point)null);
/*     */   }
/*     */   
/*     */   public Raster decode(Point location) throws IOException {
/*  88 */     SampleModel sm = null;
/*  89 */     byte[] data = null;
/*  91 */     ObjectInputStream ois = new ObjectInputStream(this.inputStream);
/*     */     try {
/*  95 */       this.paramList.setParameter("quality", ois.readFloat());
/*  96 */       this.paramList.setParameter("qualitySet", ois.readBoolean());
/*  97 */       sm = TileCodecUtils.deserializeSampleModel(ois.readObject());
/*  98 */       location = (Point)ois.readObject();
/*  99 */       data = (byte[])ois.readObject();
/* 101 */     } catch (ClassNotFoundException e) {
/* 102 */       ImagingListener listener = ImageUtil.getImagingListener((RenderingHints)null);
/* 104 */       listener.errorOccurred(JaiI18N.getString("ClassNotFound"), e, this, false);
/* 107 */       return null;
/*     */     } finally {
/* 110 */       ois.close();
/*     */     } 
/* 113 */     ByteArrayInputStream bais = new ByteArrayInputStream(data);
/* 114 */     JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(bais);
/* 116 */     Raster ras = decoder.decodeAsRaster().createTranslatedChild(location.x, location.y);
/* 118 */     extractParameters(decoder.getJPEGDecodeParam(), ras.getSampleModel().getNumBands());
/* 122 */     if (sm != null) {
/* 123 */       int minX = ras.getMinX();
/* 124 */       int minY = ras.getMinY();
/* 125 */       int h = ras.getHeight();
/* 126 */       int w = ras.getWidth();
/* 127 */       double[] buf = ras.getPixels(minX, minY, w, h, (double[])null);
/* 128 */       ras = RasterFactory.createWritableRaster(sm, new Point(minX, minY));
/* 130 */       ((WritableRaster)ras).setPixels(minX, minY, w, h, buf);
/*     */     } 
/* 132 */     return ras;
/*     */   }
/*     */   
/*     */   private void extractParameters(JPEGDecodeParam jdp, int bandNum) {
/* 138 */     int[] horizontalSubsampling = new int[bandNum];
/* 139 */     for (int i = 0; i < bandNum; i++)
/* 140 */       horizontalSubsampling[i] = jdp.getHorizontalSubsampling(i); 
/* 141 */     this.paramList.setParameter("horizontalSubsampling", horizontalSubsampling);
/* 144 */     int[] verticalSubsampling = new int[bandNum];
/*     */     int j;
/* 145 */     for (j = 0; j < bandNum; j++)
/* 146 */       verticalSubsampling[j] = jdp.getVerticalSubsampling(j); 
/* 147 */     this.paramList.setParameter("verticalSubsampling", verticalSubsampling);
/* 151 */     if (!this.paramList.getBooleanParameter("qualitySet")) {
/* 152 */       for (j = 0; j < 4; j++) {
/* 153 */         JPEGQTable table = jdp.getQTable(j);
/* 154 */         this.paramList.setParameter("quantizationTable" + j, (table == null) ? null : table.getTable());
/*     */       } 
/*     */     } else {
/* 158 */       ParameterListDescriptor pld = this.paramList.getParameterListDescriptor();
/* 160 */       for (int m = 0; m < 4; m++)
/* 161 */         this.paramList.setParameter("quantizationTable" + m, pld.getParamDefaultValue("quantizationTable" + m)); 
/*     */     } 
/* 167 */     int[] quanTableMapping = new int[bandNum];
/* 168 */     for (int k = 0; k < bandNum; k++)
/* 169 */       quanTableMapping[k] = jdp.getQTableComponentMapping(k); 
/* 170 */     this.paramList.setParameter("quantizationTableMapping", quanTableMapping);
/* 173 */     this.paramList.setParameter("writeTableInfo", jdp.isTableInfoValid());
/* 174 */     this.paramList.setParameter("writeImageInfo", jdp.isImageInfoValid());
/* 177 */     this.paramList.setParameter("restartInterval", jdp.getRestartInterval());
/* 180 */     this.paramList.setParameter("writeJFIFHeader", jdp.getMarker(224));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\tilecodec\JPEGTileDecoder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */