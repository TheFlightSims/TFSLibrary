/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.medialib.mlib.Image;
/*    */ import com.sun.medialib.mlib.mediaLibImage;
/*    */ import java.awt.image.ColorModel;
/*    */ 
/*    */ final class MlibUtils {
/*    */   static final int[] initConstants(int[] constants, int numBands) {
/* 24 */     int[] c = null;
/* 25 */     if (constants.length < numBands) {
/* 26 */       c = new int[numBands];
/* 27 */       for (int i = 0; i < numBands; i++)
/* 28 */         c[i] = constants[0]; 
/*    */     } else {
/* 31 */       c = (int[])constants.clone();
/*    */     } 
/* 34 */     return c;
/*    */   }
/*    */   
/*    */   static final double[] initConstants(double[] constants, int numBands) {
/* 43 */     double[] c = null;
/* 44 */     if (constants.length < numBands) {
/* 45 */       c = new double[numBands];
/* 46 */       for (int i = 0; i < numBands; i++)
/* 47 */         c[i] = constants[0]; 
/*    */     } else {
/* 50 */       c = (double[])constants.clone();
/*    */     } 
/* 53 */     return c;
/*    */   }
/*    */   
/*    */   static void clampImage(mediaLibImage image, ColorModel colorModel) {
/* 63 */     if (image == null)
/* 64 */       throw new IllegalArgumentException("image == null!"); 
/* 67 */     if (colorModel != null) {
/* 69 */       int fullDepth = 0;
/* 70 */       switch (image.getType()) {
/*    */         case 1:
/* 72 */           fullDepth = 8;
/*    */           break;
/*    */         case 3:
/* 75 */           fullDepth = 32;
/*    */           break;
/*    */         default:
/* 78 */           fullDepth = 16;
/*    */           break;
/*    */       } 
/* 82 */       int[] numBits = colorModel.getComponentSize();
/* 83 */       int[] high = new int[numBits.length];
/* 84 */       int[] low = new int[numBits.length];
/* 85 */       boolean applyThreshold = false;
/* 86 */       for (int j = 0; j < numBits.length; j++) {
/* 87 */         high[j] = (1 << numBits[j]) - 1;
/* 88 */         if (numBits[j] != fullDepth)
/* 89 */           applyThreshold = true; 
/*    */       } 
/* 94 */       if (applyThreshold)
/* 95 */         Image.Thresh4(image, high, low, high, low); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */