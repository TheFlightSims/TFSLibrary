/*    */ package com.sun.media.jai.tilecodec;
/*    */ 
/*    */ import java.awt.image.Raster;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.OutputStream;
/*    */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*    */ import javax.media.jai.tilecodec.TileEncoderImpl;
/*    */ 
/*    */ public class RawTileEncoder extends TileEncoderImpl {
/*    */   public RawTileEncoder(OutputStream output, TileCodecParameterList param) {
/* 49 */     super("raw", output, param);
/*    */   }
/*    */   
/*    */   public void encode(Raster ras) throws IOException {
/* 63 */     if (ras == null)
/* 64 */       throw new IllegalArgumentException(JaiI18N.getString("TileEncoder1")); 
/* 67 */     ObjectOutputStream oos = new ObjectOutputStream(this.outputStream);
/* 69 */     Object object = TileCodecUtils.serializeRaster(ras);
/* 71 */     oos.writeObject(object);
/* 72 */     oos.close();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\tilecodec\RawTileEncoder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */