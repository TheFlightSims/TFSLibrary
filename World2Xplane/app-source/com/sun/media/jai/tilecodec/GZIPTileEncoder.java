/*    */ package com.sun.media.jai.tilecodec;
/*    */ 
/*    */ import java.awt.image.Raster;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.util.zip.GZIPOutputStream;
/*    */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*    */ import javax.media.jai.tilecodec.TileEncoderImpl;
/*    */ 
/*    */ public class GZIPTileEncoder extends TileEncoderImpl {
/*    */   public GZIPTileEncoder(OutputStream output, TileCodecParameterList param) {
/* 39 */     super("gzip", output, param);
/*    */   }
/*    */   
/*    */   public void encode(Raster ras) throws IOException {
/* 53 */     if (ras == null)
/* 54 */       throw new IllegalArgumentException(JaiI18N.getString("TileEncoder1")); 
/* 57 */     ObjectOutputStream oos = new ObjectOutputStream(new GZIPOutputStream(this.outputStream));
/* 59 */     Object object = TileCodecUtils.serializeRaster(ras);
/* 60 */     oos.writeObject(object);
/* 61 */     oos.close();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\tilecodec\GZIPTileEncoder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */