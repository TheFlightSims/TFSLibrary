/*    */ package com.sun.media.jai.tilecodec;
/*    */ 
/*    */ import java.awt.image.Raster;
/*    */ import java.awt.image.SampleModel;
/*    */ import java.text.MessageFormat;
/*    */ import javax.media.jai.JAI;
/*    */ import javax.media.jai.remote.SerializableState;
/*    */ import javax.media.jai.remote.SerializerFactory;
/*    */ import javax.media.jai.tilecodec.TileCodecDescriptor;
/*    */ 
/*    */ public class TileCodecUtils {
/* 26 */   private static MessageFormat formatter = new MessageFormat("");
/*    */   
/*    */   public static TileCodecDescriptor getTileCodecDescriptor(String registryMode, String formatName) {
/* 34 */     return (TileCodecDescriptor)JAI.getDefaultInstance().getOperationRegistry().getDescriptor(registryMode, formatName);
/*    */   }
/*    */   
/*    */   public static Raster deserializeRaster(Object object) {
/* 41 */     if (!(object instanceof SerializableState))
/* 42 */       return null; 
/* 44 */     SerializableState ss = (SerializableState)object;
/* 45 */     Class c = ss.getObjectClass();
/* 46 */     if (Raster.class.isAssignableFrom(c))
/* 47 */       return (Raster)ss.getObject(); 
/* 49 */     return null;
/*    */   }
/*    */   
/*    */   public static SampleModel deserializeSampleModel(Object object) {
/* 54 */     if (!(object instanceof SerializableState))
/* 55 */       return null; 
/* 57 */     SerializableState ss = (SerializableState)object;
/* 58 */     Class c = ss.getObjectClass();
/* 59 */     if (SampleModel.class.isAssignableFrom(c))
/* 60 */       return (SampleModel)ss.getObject(); 
/* 62 */     return null;
/*    */   }
/*    */   
/*    */   public static Object serializeRaster(Raster ras) {
/* 67 */     return SerializerFactory.getState(ras, null);
/*    */   }
/*    */   
/*    */   public static Object serializeSampleModel(SampleModel sm) {
/* 72 */     return SerializerFactory.getState(sm, null);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\tilecodec\TileCodecUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */