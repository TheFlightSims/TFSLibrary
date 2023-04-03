/*    */ package com.sun.media.jai.tilecodec;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.util.Vector;
/*    */ import javax.media.jai.ParameterListDescriptor;
/*    */ import javax.media.jai.ParameterListDescriptorImpl;
/*    */ import javax.media.jai.remote.NegotiableCapability;
/*    */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*    */ import javax.media.jai.tilecodec.TileDecoder;
/*    */ import javax.media.jai.tilecodec.TileDecoderFactory;
/*    */ 
/*    */ public class RawTileDecoderFactory implements TileDecoderFactory {
/*    */   public TileDecoder createDecoder(InputStream input, TileCodecParameterList param) {
/* 75 */     if (input == null)
/* 76 */       throw new IllegalArgumentException(JaiI18N.getString("TileDecoder0")); 
/* 77 */     return (TileDecoder)new RawTileDecoder(input, param);
/*    */   }
/*    */   
/*    */   public NegotiableCapability getDecodeCapability() {
/* 86 */     Vector generators = new Vector();
/* 87 */     generators.add(RawTileDecoderFactory.class);
/* 89 */     return new NegotiableCapability("tileCodec", "raw", generators, (ParameterListDescriptor)new ParameterListDescriptorImpl(null, null, null, null, null), false);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\tilecodec\RawTileDecoderFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */