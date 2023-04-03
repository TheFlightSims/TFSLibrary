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
/*    */ public class RawTileEncoderFactory implements TileEncoderFactory {
/*    */   public TileEncoder createEncoder(OutputStream output, TileCodecParameterList paramList, SampleModel sampleModel) {
/* 67 */     if (output == null)
/* 68 */       throw new IllegalArgumentException(JaiI18N.getString("TileEncoder0")); 
/* 69 */     return (TileEncoder)new RawTileEncoder(output, paramList);
/*    */   }
/*    */   
/*    */   public NegotiableCapability getEncodeCapability() {
/* 78 */     Vector generators = new Vector();
/* 79 */     generators.add(RawTileEncoderFactory.class);
/* 81 */     return new NegotiableCapability("tileCodec", "raw", generators, (ParameterListDescriptor)new ParameterListDescriptorImpl(null, null, null, null, null), false);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\tilecodec\RawTileEncoderFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */