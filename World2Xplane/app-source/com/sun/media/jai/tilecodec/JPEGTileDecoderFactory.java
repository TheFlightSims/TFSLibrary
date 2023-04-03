/*     */ package com.sun.media.jai.tilecodec;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.ParameterListDescriptor;
/*     */ import javax.media.jai.ParameterListDescriptorImpl;
/*     */ import javax.media.jai.remote.NegotiableCapability;
/*     */ import javax.media.jai.remote.NegotiableCollection;
/*     */ import javax.media.jai.remote.NegotiableNumericRange;
/*     */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*     */ import javax.media.jai.tilecodec.TileDecoder;
/*     */ import javax.media.jai.tilecodec.TileDecoderFactory;
/*     */ 
/*     */ public class JPEGTileDecoderFactory implements TileDecoderFactory {
/*     */   public TileDecoder createDecoder(InputStream input, TileCodecParameterList param) {
/*  79 */     if (input == null)
/*  80 */       throw new IllegalArgumentException(JaiI18N.getString("TileDecoder0")); 
/*  82 */     return (TileDecoder)new JPEGTileDecoder(input, param);
/*     */   }
/*     */   
/*     */   public NegotiableCapability getDecodeCapability() {
/*  91 */     Vector generators = new Vector();
/*  92 */     generators.add(JPEGTileDecoderFactory.class);
/*  94 */     ParameterListDescriptor jpegPld = JAI.getDefaultInstance().getOperationRegistry().getDescriptor("tileDecoder", "jpeg").getParameterListDescriptor("tileDecoder");
/*  96 */     Class[] paramClasses = { NegotiableNumericRange.class, NegotiableCollection.class, NegotiableNumericRange.class, NegotiableCollection.class, NegotiableCollection.class, NegotiableCollection.class };
/* 114 */     String[] paramNames = { "quality", "qualitySet", "restartInterval", "writeImageInfo", "writeTableInfo", "writeJFIFHeader" };
/* 125 */     Vector v = new Vector();
/* 126 */     v.add(new Boolean(true));
/* 127 */     v.add(new Boolean(false));
/* 128 */     NegotiableCollection negCollection = new NegotiableCollection(v);
/* 130 */     NegotiableNumericRange nnr1 = new NegotiableNumericRange(jpegPld.getParamValueRange(paramNames[0]));
/* 134 */     NegotiableNumericRange nnr2 = new NegotiableNumericRange(jpegPld.getParamValueRange(paramNames[2]));
/* 139 */     Object[] defaults = { nnr1, negCollection, nnr2, negCollection, negCollection, negCollection };
/* 148 */     NegotiableCapability decodeCap = new NegotiableCapability("tileCodec", "jpeg", generators, (ParameterListDescriptor)new ParameterListDescriptorImpl(null, paramNames, paramClasses, defaults, null), false);
/* 161 */     decodeCap.setParameter(paramNames[0], nnr1);
/* 162 */     decodeCap.setParameter(paramNames[1], negCollection);
/* 163 */     decodeCap.setParameter(paramNames[2], nnr2);
/* 164 */     decodeCap.setParameter(paramNames[3], negCollection);
/* 165 */     decodeCap.setParameter(paramNames[4], negCollection);
/* 166 */     decodeCap.setParameter(paramNames[5], negCollection);
/* 168 */     return decodeCap;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\tilecodec\JPEGTileDecoderFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */