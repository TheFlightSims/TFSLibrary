/*     */ package com.sun.media.jai.tilecodec;
/*     */ 
/*     */ import com.sun.image.codec.jpeg.JPEGCodec;
/*     */ import com.sun.image.codec.jpeg.JPEGEncodeParam;
/*     */ import com.sun.image.codec.jpeg.JPEGImageEncoder;
/*     */ import com.sun.image.codec.jpeg.JPEGQTable;
/*     */ import java.awt.Point;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.OutputStream;
/*     */ import javax.media.jai.ParameterListDescriptor;
/*     */ import javax.media.jai.tilecodec.TileCodecDescriptor;
/*     */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*     */ import javax.media.jai.tilecodec.TileEncoderImpl;
/*     */ import sun.awt.image.codec.JPEGParam;
/*     */ 
/*     */ public class JPEGTileEncoder extends TileEncoderImpl {
/*  40 */   private TileCodecDescriptor tcd = null;
/*     */   
/*     */   public JPEGTileEncoder(OutputStream output, TileCodecParameterList param) {
/*  63 */     super("jpeg", output, param);
/*  64 */     this.tcd = TileCodecUtils.getTileCodecDescriptor("tileEncoder", "jpeg");
/*     */   }
/*     */   
/*     */   public void encode(Raster ras) throws IOException {
/*  78 */     if (ras == null)
/*  79 */       throw new IllegalArgumentException(JaiI18N.getString("TileEncoder1")); 
/*  82 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*  84 */     SampleModel sm = ras.getSampleModel();
/*  86 */     JPEGEncodeParam j2dEP = convertToJ2DJPEGEncodeParam(this.paramList, sm);
/*  87 */     ((JPEGParam)j2dEP).setWidth(ras.getWidth());
/*  88 */     ((JPEGParam)j2dEP).setHeight(ras.getHeight());
/*  90 */     JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos, j2dEP);
/*  91 */     encoder.encode(ras);
/*  93 */     byte[] data = baos.toByteArray();
/*  95 */     ObjectOutputStream oos = new ObjectOutputStream(this.outputStream);
/*  96 */     oos.writeFloat(this.paramList.getFloatParameter("quality"));
/*  97 */     oos.writeBoolean(this.paramList.getBooleanParameter("qualitySet"));
/*  98 */     oos.writeObject(TileCodecUtils.serializeSampleModel(sm));
/* 100 */     Point location = new Point(ras.getMinX(), ras.getMinY());
/* 101 */     oos.writeObject(location);
/* 103 */     oos.writeObject(data);
/* 104 */     oos.close();
/*     */   }
/*     */   
/*     */   private JPEGEncodeParam convertToJ2DJPEGEncodeParam(TileCodecParameterList paramList, SampleModel sm) {
/* 110 */     if (sm == null)
/* 111 */       return null; 
/* 113 */     int nbands = sm.getNumBands();
/* 115 */     JPEGParam j2dJP = createDefaultJ2DJPEGEncodeParam(nbands);
/* 117 */     int[] hSubSamp = (int[])paramList.getObjectParameter("horizontalSubsampling");
/* 119 */     int[] vSubSamp = (int[])paramList.getObjectParameter("verticalSubsampling");
/* 121 */     int[] qTabSlot = (int[])paramList.getObjectParameter("quantizationTableMapping");
/* 124 */     for (int i = 0; i < nbands; i++) {
/* 125 */       j2dJP.setHorizontalSubsampling(i, hSubSamp[i]);
/* 126 */       j2dJP.setVerticalSubsampling(i, vSubSamp[i]);
/* 128 */       int[] qTab = (int[])paramList.getObjectParameter("quantizationTable" + i);
/* 130 */       if (qTab != null && qTab.equals(ParameterListDescriptor.NO_PARAMETER_DEFAULT)) {
/* 132 */         j2dJP.setQTableComponentMapping(i, qTabSlot[i]);
/* 133 */         j2dJP.setQTable(qTabSlot[i], new JPEGQTable(qTab));
/*     */       } 
/*     */     } 
/* 137 */     if (paramList.getBooleanParameter("qualitySet")) {
/* 138 */       float quality = paramList.getFloatParameter("quality");
/* 139 */       j2dJP.setQuality(quality, true);
/*     */     } 
/* 142 */     int rInt = paramList.getIntParameter("restartInterval");
/* 143 */     j2dJP.setRestartInterval(rInt);
/* 145 */     j2dJP.setImageInfoValid(paramList.getBooleanParameter("writeImageInfo"));
/* 146 */     j2dJP.setTableInfoValid(paramList.getBooleanParameter("writeTableInfo"));
/* 148 */     if (paramList.getBooleanParameter("writeJFIFHeader"))
/* 149 */       j2dJP.setMarkerData(224, (byte[][])null); 
/* 152 */     return (JPEGEncodeParam)j2dJP;
/*     */   }
/*     */   
/*     */   private JPEGParam createDefaultJ2DJPEGEncodeParam(int nbands) {
/* 156 */     if (nbands == 1)
/* 157 */       return new JPEGParam(1, 1); 
/* 158 */     if (nbands == 3)
/* 159 */       return new JPEGParam(3, 3); 
/* 160 */     if (nbands == 4)
/* 161 */       return new JPEGParam(4, 4); 
/* 162 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\tilecodec\JPEGTileEncoder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */