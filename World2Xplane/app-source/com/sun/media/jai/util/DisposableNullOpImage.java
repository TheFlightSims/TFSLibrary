/*    */ package com.sun.media.jai.util;
/*    */ 
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.lang.reflect.AccessibleObject;
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.Map;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.NullOpImage;
/*    */ import javax.media.jai.PlanarImage;
/*    */ import javax.media.jai.RenderedImageAdapter;
/*    */ 
/*    */ public class DisposableNullOpImage extends NullOpImage {
/*    */   public DisposableNullOpImage(RenderedImage source, ImageLayout layout, Map configuration, int computeType) {
/* 39 */     super(source, layout, configuration, computeType);
/*    */   }
/*    */   
/*    */   public synchronized void dispose() {
/* 43 */     PlanarImage src = getSource(0);
/* 44 */     if (src instanceof RenderedImageAdapter) {
/* 46 */       RenderedImage trueSrc = ((RenderedImageAdapter)src).getWrappedImage();
/* 48 */       Method disposeMethod = null;
/*    */       try {
/* 50 */         Class cls = trueSrc.getClass();
/* 51 */         disposeMethod = cls.getMethod("dispose", null);
/* 52 */         if (!disposeMethod.isAccessible())
/* 53 */           AccessibleObject.setAccessible(new AccessibleObject[] { disposeMethod }, true); 
/* 57 */         disposeMethod.invoke(trueSrc, null);
/* 58 */       } catch (Exception e) {}
/*    */     } else {
/* 63 */       src.dispose();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\DisposableNullOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */