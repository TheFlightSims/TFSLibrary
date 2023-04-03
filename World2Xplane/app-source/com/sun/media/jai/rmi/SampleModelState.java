/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import com.sun.media.jai.codecimpl.util.ComponentSampleModelJAI;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BandedSampleModel;
/*     */ import java.awt.image.ComponentSampleModel;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.PixelInterleavedSampleModel;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import javax.media.jai.ComponentSampleModelJAI;
/*     */ import javax.media.jai.RasterFactory;
/*     */ 
/*     */ public class SampleModelState extends SerializableStateImpl {
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
/*     */   public static Class[] getSupportedClasses() {
/*  56 */     return new Class[] { BandedSampleModel.class, PixelInterleavedSampleModel.class, ComponentSampleModel.class, MultiPixelPackedSampleModel.class, SinglePixelPackedSampleModel.class, ComponentSampleModelJAI.class, ComponentSampleModelJAI.class };
/*     */   }
/*     */   
/*     */   public SampleModelState(Class c, Object o, RenderingHints h) {
/*  92 */     super(c, o, h);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 101 */     SampleModel sampleModel = (SampleModel)this.theObject;
/* 102 */     if (sampleModel instanceof ComponentSampleModel) {
/* 103 */       ComponentSampleModel sm = (ComponentSampleModel)sampleModel;
/* 104 */       int sampleModelType = 6;
/* 105 */       int transferType = sm.getTransferType();
/* 106 */       if (sampleModel instanceof PixelInterleavedSampleModel) {
/* 107 */         sampleModelType = 2;
/* 108 */       } else if (sampleModel instanceof BandedSampleModel) {
/* 109 */         sampleModelType = 1;
/* 110 */       } else if (sampleModel instanceof ComponentSampleModelJAI || transferType == 4 || transferType == 5) {
/* 113 */         sampleModelType = 5;
/*     */       } 
/* 115 */       out.writeInt(sampleModelType);
/* 116 */       out.writeInt(transferType);
/* 117 */       out.writeInt(sm.getWidth());
/* 118 */       out.writeInt(sm.getHeight());
/* 119 */       if (sampleModelType != 1)
/* 120 */         out.writeInt(sm.getPixelStride()); 
/* 122 */       out.writeInt(sm.getScanlineStride());
/* 123 */       if (sampleModelType != 2)
/* 124 */         out.writeObject(sm.getBankIndices()); 
/* 126 */       out.writeObject(sm.getBandOffsets());
/* 127 */     } else if (sampleModel instanceof SinglePixelPackedSampleModel) {
/* 129 */       SinglePixelPackedSampleModel sm = (SinglePixelPackedSampleModel)sampleModel;
/* 131 */       out.writeInt(3);
/* 132 */       out.writeInt(sm.getTransferType());
/* 133 */       out.writeInt(sm.getWidth());
/* 134 */       out.writeInt(sm.getHeight());
/* 135 */       out.writeInt(sm.getScanlineStride());
/* 136 */       out.writeObject(sm.getBitMasks());
/* 137 */     } else if (sampleModel instanceof MultiPixelPackedSampleModel) {
/* 138 */       MultiPixelPackedSampleModel sm = (MultiPixelPackedSampleModel)sampleModel;
/* 140 */       out.writeInt(4);
/* 141 */       out.writeInt(sm.getTransferType());
/* 142 */       out.writeInt(sm.getWidth());
/* 143 */       out.writeInt(sm.getHeight());
/* 144 */       out.writeInt(sm.getPixelBitStride());
/* 145 */       out.writeInt(sm.getScanlineStride());
/* 146 */       out.writeInt(sm.getDataBitOffset());
/*     */     } else {
/* 148 */       throw new RuntimeException(JaiI18N.getString("SampleModelState0"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*     */     ComponentSampleModelJAI componentSampleModelJAI;
/*     */     ComponentSampleModel componentSampleModel;
/*     */     SinglePixelPackedSampleModel singlePixelPackedSampleModel;
/*     */     MultiPixelPackedSampleModel multiPixelPackedSampleModel;
/* 159 */     SampleModel sampleModel = null;
/* 160 */     int sampleModelType = in.readInt();
/* 161 */     switch (sampleModelType) {
/*     */       case 2:
/* 163 */         sampleModel = RasterFactory.createPixelInterleavedSampleModel(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), (int[])in.readObject());
/*     */         break;
/*     */       case 1:
/* 172 */         sampleModel = RasterFactory.createBandedSampleModel(in.readInt(), in.readInt(), in.readInt(), in.readInt(), (int[])in.readObject(), (int[])in.readObject());
/*     */         break;
/*     */       case 5:
/* 181 */         componentSampleModelJAI = new ComponentSampleModelJAI(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), (int[])in.readObject(), (int[])in.readObject());
/*     */         break;
/*     */       case 6:
/* 191 */         componentSampleModel = new ComponentSampleModel(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), (int[])in.readObject(), (int[])in.readObject());
/*     */         break;
/*     */       case 3:
/* 201 */         singlePixelPackedSampleModel = new SinglePixelPackedSampleModel(in.readInt(), in.readInt(), in.readInt(), in.readInt(), (int[])in.readObject());
/*     */         break;
/*     */       case 4:
/* 208 */         multiPixelPackedSampleModel = new MultiPixelPackedSampleModel(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt());
/*     */         break;
/*     */       default:
/* 216 */         throw new RuntimeException(JaiI18N.getString("SampleModelState0"));
/*     */     } 
/* 218 */     this.theObject = multiPixelPackedSampleModel;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\SampleModelState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */