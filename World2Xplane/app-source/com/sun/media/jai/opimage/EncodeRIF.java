/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import com.sun.media.jai.codec.ImageCodec;
/*    */ import com.sun.media.jai.codec.ImageEncodeParam;
/*    */ import com.sun.media.jai.codec.ImageEncoder;
/*    */ import com.sun.media.jai.util.ImageUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import javax.media.jai.util.ImagingListener;
/*    */ 
/*    */ public class EncodeRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 44 */     ImagingListener listener = ImageUtil.getImagingListener(renderHints);
/* 47 */     OutputStream stream = (OutputStream)paramBlock.getObjectParameter(0);
/* 50 */     String format = (String)paramBlock.getObjectParameter(1);
/* 53 */     ImageEncodeParam param = null;
/* 54 */     if (paramBlock.getNumParameters() > 2)
/* 55 */       param = (ImageEncodeParam)paramBlock.getObjectParameter(2); 
/* 59 */     ImageEncoder encoder = ImageCodec.createImageEncoder(format, stream, param);
/* 63 */     if (encoder == null)
/* 64 */       throw new RuntimeException(JaiI18N.getString("EncodeRIF0")); 
/* 68 */     RenderedImage im = (RenderedImage)paramBlock.getSource(0);
/*    */     try {
/* 70 */       encoder.encode(im);
/* 71 */       stream.flush();
/* 75 */     } catch (IOException e) {
/* 76 */       String message = JaiI18N.getString("EncodeRIF1") + " " + format;
/* 77 */       listener.errorOccurred(message, e, this, false);
/* 79 */       return null;
/*    */     } 
/* 82 */     return im;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\EncodeRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */