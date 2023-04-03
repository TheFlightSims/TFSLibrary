/*     */ package javax.media.jai;
/*     */ 
/*     */ public final class InterpolationBicubic extends InterpolationTable {
/*     */   private static final int PRECISION_BITS = 8;
/*     */   
/*     */   private static final float A = -0.5F;
/*     */   
/*     */   private static final float A3 = 1.5F;
/*     */   
/*     */   private static final float A2 = -2.5F;
/*     */   
/*     */   private static final float A0 = 1.0F;
/*     */   
/*     */   private static final float B3 = -0.5F;
/*     */   
/*     */   private static final float B2 = 2.5F;
/*     */   
/*     */   private static final float B1 = -4.0F;
/*     */   
/*     */   private static final float B0 = 2.0F;
/*     */   
/*     */   private static float[] dataHelper(int subsampleBits) {
/*  95 */     int one = 1 << subsampleBits;
/*  96 */     int arrayLength = one * 4;
/*  97 */     float[] tableValues = new float[arrayLength];
/* 100 */     float onef = one;
/* 102 */     int count = 0;
/* 103 */     for (int i = 0; i < one; i++) {
/* 104 */       float t = i;
/* 105 */       float f = i / onef;
/* 107 */       tableValues[count++] = bicubic(f + 1.0F);
/* 108 */       tableValues[count++] = bicubic(f);
/* 109 */       tableValues[count++] = bicubic(f - 1.0F);
/* 110 */       tableValues[count++] = bicubic(f - 2.0F);
/*     */     } 
/* 114 */     return tableValues;
/*     */   }
/*     */   
/*     */   private static float bicubic(float x) {
/* 135 */     if (x < 0.0F)
/* 136 */       x = -x; 
/* 140 */     if (x >= 1.0F)
/* 141 */       return ((-0.5F * x + 2.5F) * x + -4.0F) * x + 2.0F; 
/* 143 */     return (1.5F * x + -2.5F) * x * x + 1.0F;
/*     */   }
/*     */   
/*     */   public InterpolationBicubic(int subsampleBits) {
/* 159 */     super(1, 1, 4, 4, subsampleBits, subsampleBits, 8, dataHelper(subsampleBits), (float[])null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\InterpolationBicubic.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */