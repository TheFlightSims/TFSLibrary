/*     */ package com.sun.media.jai.tilecodec;
/*     */ 
/*     */ import java.awt.image.SampleModel;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.ParameterListDescriptor;
/*     */ import javax.media.jai.ParameterListDescriptorImpl;
/*     */ import javax.media.jai.remote.NegotiableCapability;
/*     */ import javax.media.jai.remote.NegotiableCollection;
/*     */ import javax.media.jai.remote.NegotiableNumericRange;
/*     */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*     */ import javax.media.jai.tilecodec.TileEncoder;
/*     */ import javax.media.jai.tilecodec.TileEncoderFactory;
/*     */ 
/*     */ public class JPEGTileEncoderFactory implements TileEncoderFactory {
/*     */   public TileEncoder createEncoder(OutputStream output, TileCodecParameterList paramList, SampleModel sampleModel) {
/*  72 */     if (output == null)
/*  73 */       throw new IllegalArgumentException(JaiI18N.getString("TileEncoder0")); 
/*  74 */     int nbands = sampleModel.getNumBands();
/*  75 */     if (nbands != 1 && nbands != 3 && nbands != 4)
/*  76 */       throw new IllegalArgumentException(JaiI18N.getString("JPEGTileEncoder0")); 
/*  79 */     if (sampleModel.getDataType() != 0)
/*  80 */       throw new IllegalArgumentException(JaiI18N.getString("JPEGTileEncoder1")); 
/*  83 */     return (TileEncoder)new JPEGTileEncoder(output, paramList);
/*     */   }
/*     */   
/*     */   public NegotiableCapability getEncodeCapability() {
/*  92 */     Vector generators = new Vector();
/*  93 */     generators.add(JPEGTileEncoderFactory.class);
/*  95 */     ParameterListDescriptor jpegPld = JAI.getDefaultInstance().getOperationRegistry().getDescriptor("tileEncoder", "jpeg").getParameterListDescriptor("tileEncoder");
/*  98 */     Class[] paramClasses = { NegotiableNumericRange.class, NegotiableCollection.class, NegotiableNumericRange.class, NegotiableCollection.class, NegotiableCollection.class, NegotiableCollection.class };
/* 115 */     String[] paramNames = { "quality", "qualitySet", "restartInterval", "writeImageInfo", "writeTableInfo", "writeJFIFHeader" };
/* 126 */     Vector v = new Vector();
/* 127 */     v.add(new Boolean(true));
/* 128 */     v.add(new Boolean(false));
/* 129 */     NegotiableCollection negCollection = new NegotiableCollection(v);
/* 131 */     NegotiableNumericRange nnr1 = new NegotiableNumericRange(jpegPld.getParamValueRange(paramNames[0]));
/* 135 */     NegotiableNumericRange nnr2 = new NegotiableNumericRange(jpegPld.getParamValueRange(paramNames[2]));
/* 140 */     Object[] defaults = { nnr1, negCollection, nnr2, negCollection, negCollection, negCollection };
/* 149 */     NegotiableCapability encodeCap = new NegotiableCapability("tileCodec", "jpeg", generators, (ParameterListDescriptor)new ParameterListDescriptorImpl(null, paramNames, paramClasses, defaults, null), false);
/* 162 */     encodeCap.setParameter(paramNames[0], nnr1);
/* 163 */     encodeCap.setParameter(paramNames[1], negCollection);
/* 164 */     encodeCap.setParameter(paramNames[2], nnr2);
/* 165 */     encodeCap.setParameter(paramNames[3], negCollection);
/* 166 */     encodeCap.setParameter(paramNames[4], negCollection);
/* 167 */     encodeCap.setParameter(paramNames[5], negCollection);
/* 169 */     return encodeCap;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\tilecodec\JPEGTileEncoderFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */