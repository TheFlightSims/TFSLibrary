/*    */ package com.sun.media.jai.util;
/*    */ 
/*    */ import java.awt.Image;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.AreaOpImage;
/*    */ import javax.media.jai.PlanarImage;
/*    */ import javax.media.jai.ROI;
/*    */ import javax.media.jai.ROIShape;
/*    */ import javax.media.jai.RenderedOp;
/*    */ 
/*    */ public class AreaOpPropertyGenerator extends PropertyGeneratorImpl {
/*    */   public AreaOpPropertyGenerator() {
/* 26 */     super(new String[] { "ROI" }, new Class[] { ROI.class }, new Class[] { RenderedOp.class });
/*    */   }
/*    */   
/*    */   public Object getProperty(String name, Object opNode) {
/* 38 */     validate(name, opNode);
/* 40 */     if (opNode instanceof RenderedOp && name.equalsIgnoreCase("roi")) {
/* 42 */       RenderedOp op = (RenderedOp)opNode;
/* 44 */       ParameterBlock pb = op.getParameterBlock();
/* 47 */       PlanarImage src = (PlanarImage)pb.getRenderedSource(0);
/* 48 */       Object roiProperty = src.getProperty("ROI");
/* 49 */       if (roiProperty == null || roiProperty == Image.UndefinedProperty || !(roiProperty instanceof ROI))
/* 52 */         return Image.UndefinedProperty; 
/* 54 */       ROI roi = (ROI)roiProperty;
/* 57 */       Rectangle dstBounds = null;
/* 58 */       PlanarImage dst = op.getRendering();
/* 59 */       if (dst instanceof AreaOpImage && ((AreaOpImage)dst).getBorderExtender() == null) {
/* 61 */         AreaOpImage aoi = (AreaOpImage)dst;
/* 62 */         dstBounds = new Rectangle(aoi.getMinX() + aoi.getLeftPadding(), aoi.getMinY() + aoi.getTopPadding(), aoi.getWidth() - aoi.getLeftPadding() - aoi.getRightPadding(), aoi.getHeight() - aoi.getTopPadding() - aoi.getBottomPadding());
/*    */       } else {
/* 72 */         dstBounds = dst.getBounds();
/*    */       } 
/* 77 */       if (!dstBounds.contains(roi.getBounds()))
/* 78 */         roi = roi.intersect((ROI)new ROIShape(dstBounds)); 
/* 81 */       return roi;
/*    */     } 
/* 84 */     return Image.UndefinedProperty;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\AreaOpPropertyGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */