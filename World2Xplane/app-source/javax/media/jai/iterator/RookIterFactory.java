/*     */ package javax.media.jai.iterator;
/*     */ 
/*     */ import com.sun.media.jai.iterator.RookIterFallback;
/*     */ import com.sun.media.jai.iterator.WrapperRI;
/*     */ import com.sun.media.jai.iterator.WrapperWRI;
/*     */ import com.sun.media.jai.iterator.WritableRookIterFallback;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.awt.image.WritableRenderedImage;
/*     */ 
/*     */ public class RookIterFactory {
/*     */   public static RookIter create(RenderedImage im, Rectangle bounds) {
/*  63 */     if (bounds == null)
/*  64 */       bounds = new Rectangle(im.getMinX(), im.getMinY(), im.getWidth(), im.getHeight()); 
/*  68 */     SampleModel sm = im.getSampleModel();
/*  69 */     if (sm instanceof java.awt.image.ComponentSampleModel)
/*  70 */       switch (sm.getDataType()) {
/*     */       
/*     */       }  
/*  86 */     return (RookIter)new RookIterFallback(im, bounds);
/*     */   }
/*     */   
/*     */   public static RookIter create(Raster ras, Rectangle bounds) {
/* 101 */     WrapperRI wrapperRI = new WrapperRI(ras);
/* 102 */     return create((RenderedImage)wrapperRI, bounds);
/*     */   }
/*     */   
/*     */   public static WritableRookIter createWritable(WritableRenderedImage im, Rectangle bounds) {
/* 117 */     if (bounds == null)
/* 118 */       bounds = new Rectangle(im.getMinX(), im.getMinY(), im.getWidth(), im.getHeight()); 
/* 122 */     SampleModel sm = im.getSampleModel();
/* 123 */     if (sm instanceof java.awt.image.ComponentSampleModel)
/* 124 */       switch (sm.getDataType()) {
/*     */       
/*     */       }  
/* 140 */     return (WritableRookIter)new WritableRookIterFallback(im, bounds);
/*     */   }
/*     */   
/*     */   public static WritableRookIter createWritable(WritableRaster ras, Rectangle bounds) {
/* 155 */     WrapperWRI wrapperWRI = new WrapperWRI(ras);
/* 156 */     return createWritable((WritableRenderedImage)wrapperWRI, bounds);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\iterator\RookIterFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */