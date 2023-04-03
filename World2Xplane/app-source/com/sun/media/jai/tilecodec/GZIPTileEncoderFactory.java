/*    */ package com.sun.media.jai.tilecodec;
/*    */ 
/*    */ import java.awt.image.SampleModel;
/*    */ import java.io.OutputStream;
/*    */ import java.util.Vector;
/*    */ import javax.media.jai.ParameterListDescriptor;
/*    */ import javax.media.jai.ParameterListDescriptorImpl;
/*    */ import javax.media.jai.remote.NegotiableCapability;
/*    */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*    */ import javax.media.jai.tilecodec.TileEncoder;
/*    */ import javax.media.jai.tilecodec.TileEncoderFactory;
/*    */ 
/*    */ public class GZIPTileEncoderFactory implements TileEncoderFactory {
/*    */   public TileEncoder createEncoder(OutputStream output, TileCodecParameterList paramList, SampleModel sampleModel) {
/* 68 */     if (output == null)
/* 69 */       throw new IllegalArgumentException(JaiI18N.getString("TileEncoder0")); 
/* 71 */     return (TileEncoder)new GZIPTileEncoder(output, paramList);
/*    */   }
/*    */   
/*    */   public NegotiableCapability getEncodeCapability() {
/* 80 */     Vector generators = new Vector();
/* 81 */     generators.add(GZIPTileEncoderFactory.class);
/* 83 */     return new NegotiableCapability("tileCodec", "gzip", generators, (ParameterListDescriptor)new ParameterListDescriptorImpl(null, null, null, null, null), false);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\tilecodec\GZIPTileEncoderFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */