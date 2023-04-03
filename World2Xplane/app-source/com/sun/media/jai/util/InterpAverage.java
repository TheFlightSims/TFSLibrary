/*    */ package com.sun.media.jai.util;
/*    */ 
/*    */ import javax.media.jai.Interpolation;
/*    */ 
/*    */ public class InterpAverage extends Interpolation {
/*    */   public InterpAverage(int blockX, int blockY) {
/* 37 */     super(blockX, blockY, (blockX - 1) / 2, blockX - 1 - (blockX - 1) / 2, (blockY - 1) / 2, blockY - 1 - (blockY - 1) / 2, 32, 32);
/* 42 */     if (blockX <= 0 || blockY <= 0)
/* 43 */       throw new IllegalArgumentException("blockX <= 0 || blockY <= 0"); 
/*    */   }
/*    */   
/*    */   public int interpolateH(int[] samples, int xfrac) {
/* 52 */     int numSamples = samples.length;
/* 53 */     double total = 0.0D;
/* 54 */     for (int i = 0; i < numSamples; i++)
/* 55 */       total += (samples[i] / numSamples); 
/* 57 */     return (int)(total + 0.5D);
/*    */   }
/*    */   
/*    */   public float interpolateH(float[] samples, float xfrac) {
/* 65 */     int numSamples = samples.length;
/* 66 */     float total = 0.0F;
/* 67 */     for (int i = 0; i < numSamples; i++)
/* 68 */       total += samples[i] / numSamples; 
/* 70 */     return total;
/*    */   }
/*    */   
/*    */   public double interpolateH(double[] samples, float xfrac) {
/* 78 */     int numSamples = samples.length;
/* 79 */     double total = 0.0D;
/* 80 */     for (int i = 0; i < numSamples; i++)
/* 81 */       total += samples[i] / numSamples; 
/* 83 */     return total;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\InterpAverage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */