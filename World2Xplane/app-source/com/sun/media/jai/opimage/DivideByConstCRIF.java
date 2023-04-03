/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class DivideByConstCRIF extends CRIFImpl {
/*    */   public DivideByConstCRIF() {
/* 34 */     super("dividebyconst");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints renderHints) {
/* 47 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 51 */     double[] constants = (double[])args.getObjectParameter(0);
/* 52 */     int length = constants.length;
/* 54 */     double[] invConstants = new double[length];
/* 56 */     for (int i = 0; i < length; i++)
/* 57 */       invConstants[i] = 1.0D / constants[i]; 
/* 60 */     return (RenderedImage)new MultiplyConstOpImage(args.getRenderedSource(0), renderHints, layout, invConstants);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\DivideByConstCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */