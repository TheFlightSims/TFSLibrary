/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class SubtractConstCRIF extends CRIFImpl {
/*    */   public SubtractConstCRIF() {
/* 33 */     super("subtractconst");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 46 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 49 */     double[] constants = (double[])args.getObjectParameter(0);
/* 50 */     int length = constants.length;
/* 52 */     double[] negConstants = new double[length];
/* 54 */     for (int i = 0; i < length; i++)
/* 55 */       negConstants[i] = -constants[i]; 
/* 58 */     return (RenderedImage)new AddConstOpImage(args.getRenderedSource(0), hints, layout, negConstants);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\SubtractConstCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */