/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.SampleModel;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.EnumeratedParameter;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class TransposeCRIF extends CRIFImpl {
/*    */   public TransposeCRIF() {
/* 38 */     super("transpose");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 47 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 50 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 52 */     EnumeratedParameter type = (EnumeratedParameter)paramBlock.getObjectParameter(0);
/* 55 */     SampleModel sm = source.getSampleModel();
/* 56 */     if (sm instanceof java.awt.image.MultiPixelPackedSampleModel && sm.getSampleSize(0) == 1 && (sm.getDataType() == 0 || sm.getDataType() == 1 || sm.getDataType() == 3))
/* 61 */       return (RenderedImage)new TransposeBinaryOpImage(source, renderHints, layout, type.getValue()); 
/* 64 */     return (RenderedImage)new TransposeOpImage(source, renderHints, layout, type.getValue());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\TransposeCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */