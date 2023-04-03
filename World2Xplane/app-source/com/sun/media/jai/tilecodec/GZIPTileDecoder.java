/*    */ package com.sun.media.jai.tilecodec;
/*    */ 
/*    */ import com.sun.media.jai.util.ImageUtil;
/*    */ import java.awt.Point;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.Raster;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.util.zip.GZIPInputStream;
/*    */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*    */ import javax.media.jai.tilecodec.TileDecoderImpl;
/*    */ import javax.media.jai.util.ImagingListener;
/*    */ 
/*    */ public class GZIPTileDecoder extends TileDecoderImpl {
/*    */   public GZIPTileDecoder(InputStream input, TileCodecParameterList param) {
/* 52 */     super("gzip", input, param);
/*    */   }
/*    */   
/*    */   public Raster decode() throws IOException {
/* 70 */     ObjectInputStream ois = new ObjectInputStream(new GZIPInputStream(this.inputStream));
/*    */     try {
/* 74 */       Object object = ois.readObject();
/* 75 */       return TileCodecUtils.deserializeRaster(object);
/* 77 */     } catch (ClassNotFoundException e) {
/* 78 */       ImagingListener listener = ImageUtil.getImagingListener((RenderingHints)null);
/* 80 */       listener.errorOccurred(JaiI18N.getString("ClassNotFound"), e, this, false);
/* 84 */       return null;
/*    */     } finally {
/* 87 */       ois.close();
/*    */     } 
/*    */   }
/*    */   
/*    */   public Raster decode(Point location) throws IOException {
/* 92 */     return decode();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\tilecodec\GZIPTileDecoder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */