/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.geom.NoninvertibleTransformException;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.util.Map;
/*    */ import javax.media.jai.NullOpImage;
/*    */ import javax.media.jai.PlanarImage;
/*    */ 
/*    */ public class PointMapperOpImage extends NullOpImage {
/*    */   private AffineTransform transform;
/*    */   
/*    */   private AffineTransform inverseTransform;
/*    */   
/*    */   public PointMapperOpImage(PlanarImage source, Map configuration, AffineTransform transform) throws NoninvertibleTransformException {
/* 34 */     super((RenderedImage)source, null, configuration, 1);
/* 36 */     if (transform == null)
/* 37 */       throw new IllegalArgumentException("transform == null!"); 
/* 40 */     this.transform = transform;
/* 41 */     this.inverseTransform = transform.createInverse();
/*    */   }
/*    */   
/*    */   public Point2D mapDestPoint(Point2D destPt, int sourceIndex) {
/* 45 */     if (sourceIndex != 0)
/* 46 */       throw new IndexOutOfBoundsException("sourceIndex != 0!"); 
/* 49 */     return this.inverseTransform.transform(destPt, null);
/*    */   }
/*    */   
/*    */   public Point2D mapSourcePoint(Point2D sourcePt, int sourceIndex) {
/* 53 */     if (sourceIndex != 0)
/* 54 */       throw new IndexOutOfBoundsException("sourceIndex != 0!"); 
/* 57 */     return this.inverseTransform.transform(sourcePt, null);
/*    */   }
/*    */   
/*    */   public synchronized void dispose() {
/* 61 */     getSourceImage(0).dispose();
/* 62 */     super.dispose();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\PointMapperOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */