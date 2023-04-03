/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import com.sun.media.jai.codec.ImageDecodeParam;
/*    */ import com.sun.media.jai.codec.SeekableStream;
/*    */ import com.sun.media.jai.util.ImageUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.URL;
/*    */ import javax.media.jai.JAI;
/*    */ import javax.media.jai.OperationRegistry;
/*    */ import javax.media.jai.registry.RIFRegistry;
/*    */ import javax.media.jai.util.ImagingListener;
/*    */ 
/*    */ public class URLRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/*    */     try {
/* 47 */       URL url = (URL)paramBlock.getObjectParameter(0);
/* 48 */       InputStream stream = url.openStream();
/* 49 */       SeekableStream src = SeekableStream.wrapInputStream(stream, true);
/* 51 */       ImageDecodeParam param = null;
/* 52 */       if (paramBlock.getNumParameters() > 1)
/* 53 */         param = (ImageDecodeParam)paramBlock.getObjectParameter(1); 
/* 56 */       ParameterBlock newParamBlock = new ParameterBlock();
/* 57 */       newParamBlock.add(src);
/* 58 */       newParamBlock.add(param);
/* 60 */       RenderingHints.Key key = JAI.KEY_OPERATION_BOUND;
/* 61 */       int bound = 3;
/* 62 */       if (renderHints == null) {
/* 63 */         renderHints = new RenderingHints(key, new Integer(bound));
/* 64 */       } else if (!renderHints.containsKey(key)) {
/* 65 */         renderHints.put(key, new Integer(bound));
/*    */       } 
/* 70 */       OperationRegistry registry = (OperationRegistry)renderHints.get(JAI.KEY_OPERATION_REGISTRY);
/* 74 */       RenderedImage image = RIFRegistry.create(registry, "stream", newParamBlock, renderHints);
/* 78 */       return (image == null) ? null : (RenderedImage)new StreamImage(image, (InputStream)src);
/* 79 */     } catch (IOException e) {
/* 80 */       ImagingListener listener = ImageUtil.getImagingListener(renderHints);
/* 82 */       String message = JaiI18N.getString("URLRIF0");
/* 83 */       listener.errorOccurred(message, e, this, false);
/* 85 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\URLRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */