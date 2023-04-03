/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.ColorModel;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class ColorConvertCRIF extends CRIFImpl {
/*    */   public ColorConvertCRIF() {
/* 35 */     super("colorconvert");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints renderHints) {
/* 48 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 51 */     return (RenderedImage)new ColorConvertOpImage(args.getRenderedSource(0), renderHints, layout, (ColorModel)args.getObjectParameter(0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ColorConvertCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */