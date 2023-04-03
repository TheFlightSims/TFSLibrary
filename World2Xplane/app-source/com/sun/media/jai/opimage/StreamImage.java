/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.Image;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.io.InputStream;
/*    */ import java.lang.reflect.AccessibleObject;
/*    */ import java.lang.reflect.Method;
/*    */ import javax.media.jai.OpImage;
/*    */ import javax.media.jai.RenderedImageAdapter;
/*    */ 
/*    */ class StreamImage extends RenderedImageAdapter {
/*    */   private InputStream stream;
/*    */   
/*    */   public StreamImage(RenderedImage image, InputStream stream) {
/* 46 */     super(image);
/* 47 */     this.stream = stream;
/* 48 */     if (image instanceof OpImage) {
/* 51 */       setProperty("tile_cache_key", image);
/* 52 */       Object tileCache = ((OpImage)image).getTileCache();
/* 53 */       setProperty("tile_cache", (tileCache == null) ? Image.UndefinedProperty : tileCache);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {
/* 61 */     RenderedImage trueSrc = getWrappedImage();
/* 62 */     Method disposeMethod = null;
/*    */     try {
/* 64 */       Class cls = trueSrc.getClass();
/* 65 */       disposeMethod = cls.getMethod("dispose", null);
/* 66 */       if (!disposeMethod.isAccessible())
/* 67 */         AccessibleObject.setAccessible(new AccessibleObject[] { disposeMethod }, true); 
/* 71 */       disposeMethod.invoke(trueSrc, null);
/* 72 */     } catch (Exception e) {}
/*    */   }
/*    */   
/*    */   protected void finalize() throws Throwable {
/* 81 */     this.stream.close();
/* 82 */     super.finalize();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\StreamImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */