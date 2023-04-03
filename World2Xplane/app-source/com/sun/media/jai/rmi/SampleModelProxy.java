/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import java.awt.image.ComponentSampleModel;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import javax.media.jai.ComponentSampleModelJAI;
/*     */ import javax.media.jai.RasterFactory;
/*     */ 
/*     */ public class SampleModelProxy implements Serializable {
/*     */   private static final int TYPE_BANDED = 1;
/*     */   
/*     */   private static final int TYPE_PIXEL_INTERLEAVED = 2;
/*     */   
/*     */   private static final int TYPE_SINGLE_PIXEL_PACKED = 3;
/*     */   
/*     */   private static final int TYPE_MULTI_PIXEL_PACKED = 4;
/*     */   
/*     */   private static final int TYPE_COMPONENT_JAI = 5;
/*     */   
/*     */   private static final int TYPE_COMPONENT = 6;
/*     */   
/*     */   private transient SampleModel sampleModel;
/*     */   
/*     */   public SampleModelProxy(SampleModel source) {
/*  81 */     this.sampleModel = source;
/*     */   }
/*     */   
/*     */   public SampleModel getSampleModel() {
/*  89 */     return this.sampleModel;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  98 */     if (this.sampleModel instanceof ComponentSampleModel) {
/*  99 */       ComponentSampleModel sm = (ComponentSampleModel)this.sampleModel;
/* 100 */       int sampleModelType = 6;
/* 101 */       int transferType = sm.getTransferType();
/* 102 */       if (this.sampleModel instanceof java.awt.image.PixelInterleavedSampleModel) {
/* 103 */         sampleModelType = 2;
/* 104 */       } else if (this.sampleModel instanceof java.awt.image.BandedSampleModel) {
/* 105 */         sampleModelType = 1;
/* 106 */       } else if (this.sampleModel instanceof ComponentSampleModelJAI || transferType == 4 || transferType == 5) {
/* 109 */         sampleModelType = 5;
/*     */       } 
/* 111 */       out.writeInt(sampleModelType);
/* 112 */       out.writeInt(transferType);
/* 113 */       out.writeInt(sm.getWidth());
/* 114 */       out.writeInt(sm.getHeight());
/* 115 */       if (sampleModelType != 1)
/* 116 */         out.writeInt(sm.getPixelStride()); 
/* 118 */       out.writeInt(sm.getScanlineStride());
/* 119 */       if (sampleModelType != 2)
/* 120 */         out.writeObject(sm.getBankIndices()); 
/* 122 */       out.writeObject(sm.getBandOffsets());
/* 123 */     } else if (this.sampleModel instanceof SinglePixelPackedSampleModel) {
/* 125 */       SinglePixelPackedSampleModel sm = (SinglePixelPackedSampleModel)this.sampleModel;
/* 127 */       out.writeInt(3);
/* 128 */       out.writeInt(sm.getTransferType());
/* 129 */       out.writeInt(sm.getWidth());
/* 130 */       out.writeInt(sm.getHeight());
/* 131 */       out.writeInt(sm.getScanlineStride());
/* 132 */       out.writeObject(sm.getBitMasks());
/* 133 */     } else if (this.sampleModel instanceof MultiPixelPackedSampleModel) {
/* 134 */       MultiPixelPackedSampleModel sm = (MultiPixelPackedSampleModel)this.sampleModel;
/* 136 */       out.writeInt(4);
/* 137 */       out.writeInt(sm.getTransferType());
/* 138 */       out.writeInt(sm.getWidth());
/* 139 */       out.writeInt(sm.getHeight());
/* 140 */       out.writeInt(sm.getPixelBitStride());
/* 141 */       out.writeInt(sm.getScanlineStride());
/* 142 */       out.writeInt(sm.getDataBitOffset());
/*     */     } else {
/* 144 */       throw new RuntimeException(JaiI18N.getString("SampleModelProxy0"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 155 */     int sampleModelType = in.readInt();
/* 156 */     switch (sampleModelType) {
/*     */       case 2:
/* 158 */         this.sampleModel = RasterFactory.createPixelInterleavedSampleModel(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), (int[])in.readObject());
/*     */         return;
/*     */       case 1:
/* 167 */         this.sampleModel = RasterFactory.createBandedSampleModel(in.readInt(), in.readInt(), in.readInt(), in.readInt(), (int[])in.readObject(), (int[])in.readObject());
/*     */         return;
/*     */       case 5:
/* 176 */         this.sampleModel = (SampleModel)new ComponentSampleModelJAI(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), (int[])in.readObject(), (int[])in.readObject());
/*     */         return;
/*     */       case 6:
/* 186 */         this.sampleModel = new ComponentSampleModel(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), (int[])in.readObject(), (int[])in.readObject());
/*     */         return;
/*     */       case 3:
/* 196 */         this.sampleModel = new SinglePixelPackedSampleModel(in.readInt(), in.readInt(), in.readInt(), in.readInt(), (int[])in.readObject());
/*     */         return;
/*     */       case 4:
/* 203 */         this.sampleModel = new MultiPixelPackedSampleModel(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt());
/*     */         return;
/*     */     } 
/* 211 */     throw new RuntimeException(JaiI18N.getString("SampleModelProxy0"));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\SampleModelProxy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */