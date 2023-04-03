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
/*    */ public class GZIPTileDecoderFactory implements TileDecoderFactory {
/*    */   public TileDecoder createDecoder(InputStream input, TileCodecParameterList param) {
/* 76 */     if (input == null)
/* 77 */       throw new IllegalArgumentException(JaiI18N.getString("TileDecoder0")); 
/* 79 */     return (TileDecoder)new GZIPTileDecoder(input, param);
/*    */   }
/*    */   
/*    */   public NegotiableCapability getDecodeCapability() {
/* 88 */     Vector generators = new Vector();
/* 89 */     generators.add(GZIPTileDecoderFactory.class);
/* 91 */     return new NegotiableCapability("tileCodec", "gzip", generators, (ParameterListDescriptor)new ParameterListDescriptorImpl(null, null, null, null, null), false);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\tilecodec\GZIPTileDecoderFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */