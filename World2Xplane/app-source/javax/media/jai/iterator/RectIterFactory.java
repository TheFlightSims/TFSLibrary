/*     */ package javax.media.jai.iterator;
/*     */ 
/*     */ import com.sun.media.jai.iterator.RectIterCSMByte;
/*     */ import com.sun.media.jai.iterator.RectIterCSMFloat;
/*     */ import com.sun.media.jai.iterator.RectIterFallback;
/*     */ import com.sun.media.jai.iterator.WrapperRI;
/*     */ import com.sun.media.jai.iterator.WrapperWRI;
/*     */ import com.sun.media.jai.iterator.WritableRectIterCSMByte;
/*     */ import com.sun.media.jai.iterator.WritableRectIterCSMFloat;
/*     */ import com.sun.media.jai.iterator.WritableRectIterFallback;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.awt.image.WritableRenderedImage;
/*     */ 
/*     */ public class RectIterFactory {
/*     */   public static RectIter create(RenderedImage im, Rectangle bounds) {
/*  63 */     if (bounds == null)
/*  64 */       bounds = new Rectangle(im.getMinX(), im.getMinY(), im.getWidth(), im.getHeight()); 
/*  68 */     SampleModel sm = im.getSampleModel();
/*  69 */     if (sm instanceof java.awt.image.ComponentSampleModel)
/*  70 */       switch (sm.getDataType()) {
/*     */         case 0:
/*  72 */           return (RectIter)new RectIterCSMByte(im, bounds);
/*     */         case 4:
/*  83 */           return (RectIter)new RectIterCSMFloat(im, bounds);
/*     */       }  
/*  90 */     return (RectIter)new RectIterFallback(im, bounds);
/*     */   }
/*     */   
/*     */   public static RectIter create(Raster ras, Rectangle bounds) {
/* 105 */     WrapperRI wrapperRI = new WrapperRI(ras);
/* 106 */     return create((RenderedImage)wrapperRI, bounds);
/*     */   }
/*     */   
/*     */   public static WritableRectIter createWritable(WritableRenderedImage im, Rectangle bounds) {
/* 121 */     if (bounds == null)
/* 122 */       bounds = new Rectangle(im.getMinX(), im.getMinY(), im.getWidth(), im.getHeight()); 
/* 126 */     SampleModel sm = im.getSampleModel();
/* 127 */     if (sm instanceof java.awt.image.ComponentSampleModel)
/* 128 */       switch (sm.getDataType()) {
/*     */         case 0:
/* 130 */           return (WritableRectIter)new WritableRectIterCSMByte(im, bounds);
/*     */         case 4:
/* 141 */           return (WritableRectIter)new WritableRectIterCSMFloat(im, bounds);
/*     */       }  
/* 148 */     return (WritableRectIter)new WritableRectIterFallback(im, bounds);
/*     */   }
/*     */   
/*     */   public static WritableRectIter createWritable(WritableRaster ras, Rectangle bounds) {
/* 163 */     WrapperWRI wrapperWRI = new WrapperWRI(ras);
/* 164 */     return createWritable((WritableRenderedImage)wrapperWRI, bounds);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\iterator\RectIterFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */