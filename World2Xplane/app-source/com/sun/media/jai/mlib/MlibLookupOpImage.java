/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ComponentSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.LookupTableJAI;
/*     */ import javax.media.jai.PointOpImage;
/*     */ 
/*     */ final class MlibLookupOpImage extends PointOpImage {
/*     */   private LookupTableJAI table;
/*     */   
/*     */   public MlibLookupOpImage(RenderedImage source, Map config, ImageLayout layout, LookupTableJAI table) {
/*  36 */     super(source, layout, config, true);
/*  38 */     this.table = table;
/*  40 */     SampleModel sm = source.getSampleModel();
/*  42 */     if (this.sampleModel.getTransferType() != table.getDataType() || this.sampleModel.getNumBands() != table.getDestNumBands(sm.getNumBands())) {
/*  50 */       this.sampleModel = table.getDestSampleModel(sm, this.tileWidth, this.tileHeight);
/*  51 */       if (this.colorModel != null && !JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel))
/*  54 */         this.colorModel = ImageUtil.getCompatibleColorModel(this.sampleModel, config); 
/*     */     } 
/*  59 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  65 */     Raster source = sources[0];
/*  66 */     Rectangle srcRect = mapDestRect(destRect, 0);
/*  68 */     int srcTag = MediaLibAccessor.findCompatibleTag(null, source);
/*  69 */     int dstTag = MediaLibAccessor.findCompatibleTag(null, dest);
/*  71 */     SampleModel sm = source.getSampleModel();
/*  72 */     if (sm.getNumBands() > 1) {
/*  73 */       int srcCopy = srcTag & 0x80;
/*  74 */       int dstCopy = dstTag & 0x80;
/*  76 */       int srcDtype = srcTag & 0x7F;
/*  77 */       int dstDtype = dstTag & 0x7F;
/*  79 */       if (srcCopy != 0 || dstCopy != 0 || !MediaLibAccessor.isPixelSequential(sm) || !MediaLibAccessor.isPixelSequential(this.sampleModel) || !MediaLibAccessor.hasMatchingBandOffsets((ComponentSampleModel)sm, (ComponentSampleModel)this.sampleModel)) {
/*  87 */         srcTag = srcDtype | 0x80;
/*  88 */         dstTag = dstDtype | 0x80;
/*     */       } 
/*     */     } 
/*  92 */     MediaLibAccessor src = new MediaLibAccessor(source, srcRect, srcTag);
/*  93 */     MediaLibAccessor dst = new MediaLibAccessor(dest, destRect, dstTag);
/*  95 */     mediaLibImage[] srcMLI = src.getMediaLibImages();
/*  96 */     mediaLibImage[] dstMLI = dst.getMediaLibImages();
/*  98 */     if (srcMLI.length < dstMLI.length) {
/*  99 */       mediaLibImage srcMLI0 = srcMLI[0];
/* 100 */       srcMLI = new mediaLibImage[dstMLI.length];
/* 102 */       for (int j = 0; j < dstMLI.length; j++)
/* 103 */         srcMLI[j] = srcMLI0; 
/*     */     } 
/* 107 */     int[] bandOffsets = dst.getBandOffsets();
/* 108 */     Object table = getTableData(bandOffsets);
/* 109 */     int[] offsets = getTableOffsets(bandOffsets);
/* 111 */     for (int i = 0; i < dstMLI.length; i++)
/* 112 */       Image.LookUp2(dstMLI[i], srcMLI[i], table, offsets); 
/* 116 */     if (dst.isDataCopy())
/* 117 */       dst.copyDataToRaster(); 
/*     */   }
/*     */   
/*     */   private Object getTableData(int[] bandOffsets) {
/*     */     byte[][] bdata;
/*     */     short[][] sdata;
/*     */     int[][] idata;
/*     */     float[][] fdata;
/*     */     double[][] ddata;
/* 122 */     int tbands = this.table.getNumBands();
/* 123 */     int dbands = this.sampleModel.getNumBands();
/* 124 */     Object data = null;
/* 126 */     switch (this.table.getDataType()) {
/*     */       case 0:
/* 128 */         bdata = new byte[dbands][];
/* 129 */         if (tbands < dbands) {
/* 130 */           for (int i = 0; i < dbands; i++)
/* 131 */             bdata[i] = this.table.getByteData(0); 
/*     */         } else {
/* 134 */           for (int i = 0; i < dbands; i++)
/* 135 */             bdata[i] = this.table.getByteData(bandOffsets[i]); 
/*     */         } 
/* 138 */         data = bdata;
/*     */         break;
/*     */       case 1:
/*     */       case 2:
/* 142 */         sdata = new short[dbands][];
/* 143 */         if (tbands < dbands) {
/* 144 */           for (int i = 0; i < dbands; i++)
/* 145 */             sdata[i] = this.table.getShortData(0); 
/*     */         } else {
/* 148 */           for (int i = 0; i < dbands; i++)
/* 149 */             sdata[i] = this.table.getShortData(bandOffsets[i]); 
/*     */         } 
/* 152 */         data = sdata;
/*     */         break;
/*     */       case 3:
/* 155 */         idata = new int[dbands][];
/* 156 */         if (tbands < dbands) {
/* 157 */           for (int i = 0; i < dbands; i++)
/* 158 */             idata[i] = this.table.getIntData(0); 
/*     */         } else {
/* 161 */           for (int i = 0; i < dbands; i++)
/* 162 */             idata[i] = this.table.getIntData(bandOffsets[i]); 
/*     */         } 
/* 165 */         data = idata;
/*     */         break;
/*     */       case 4:
/* 168 */         fdata = new float[dbands][];
/* 169 */         if (tbands < dbands) {
/* 170 */           for (int i = 0; i < dbands; i++)
/* 171 */             fdata[i] = this.table.getFloatData(0); 
/*     */         } else {
/* 174 */           for (int i = 0; i < dbands; i++)
/* 175 */             fdata[i] = this.table.getFloatData(bandOffsets[i]); 
/*     */         } 
/* 178 */         data = fdata;
/*     */         break;
/*     */       case 5:
/* 181 */         ddata = new double[dbands][];
/* 182 */         if (tbands < dbands) {
/* 183 */           for (int i = 0; i < dbands; i++)
/* 184 */             ddata[i] = this.table.getDoubleData(0); 
/*     */         } else {
/* 187 */           for (int i = 0; i < dbands; i++)
/* 188 */             ddata[i] = this.table.getDoubleData(bandOffsets[i]); 
/*     */         } 
/* 191 */         data = ddata;
/*     */         break;
/*     */     } 
/* 195 */     return data;
/*     */   }
/*     */   
/*     */   private int[] getTableOffsets(int[] bandOffsets) {
/* 199 */     int tbands = this.table.getNumBands();
/* 200 */     int dbands = this.sampleModel.getNumBands();
/* 201 */     int[] offsets = new int[dbands];
/* 203 */     if (tbands < dbands) {
/* 204 */       for (int i = 0; i < dbands; i++)
/* 205 */         offsets[i] = this.table.getOffset(0); 
/*     */     } else {
/* 208 */       for (int i = 0; i < dbands; i++)
/* 209 */         offsets[i] = this.table.getOffset(bandOffsets[i]); 
/*     */     } 
/* 213 */     return offsets;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibLookupOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */