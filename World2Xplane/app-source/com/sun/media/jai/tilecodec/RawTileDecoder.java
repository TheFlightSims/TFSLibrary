/*    */ package com.sun.media.jai.tilecodec;
/*    */ 
/*    */ import com.sun.media.jai.util.ImageUtil;
/*    */ import java.awt.Point;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.Raster;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.ObjectInputStream;
/*    */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*    */ import javax.media.jai.tilecodec.TileDecoderImpl;
/*    */ import javax.media.jai.util.ImagingListener;
/*    */ 
/*    */ public class RawTileDecoder extends TileDecoderImpl {
/*    */   public RawTileDecoder(InputStream input, TileCodecParameterList param) {
/* 51 */     super("raw", input, param);
/*    */   }
/*    */   
/*    */   public Raster decode() throws IOException {
/* 69 */     ObjectInputStream ois = new ObjectInputStream(this.inputStream);
/*    */     try {
/* 72 */       Object object = ois.readObject();
/* 73 */       return TileCodecUtils.deserializeRaster(object);
/* 75 */     } catch (ClassNotFoundException e) {
/* 76 */       ImagingListener listener = ImageUtil.getImagingListener((RenderingHints)null);
/* 78 */       listener.errorOccurred(JaiI18N.getString("ClassNotFound"), e, this, false);
/* 81 */       return null;
/*    */     } finally {
/* 84 */       ois.close();
/*    */     } 
/*    */   }
/*    */   
/*    */   public Raster decode(Point location) throws IOException {
/* 89 */     return decode();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\tilecodec\RawTileDecoder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */