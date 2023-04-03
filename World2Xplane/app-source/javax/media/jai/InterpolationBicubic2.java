/*     */ package javax.media.jai;
/*     */ 
/*     */ public final class InterpolationBicubic2 extends InterpolationTable {
/*     */   private static final int PRECISION_BITS = 8;
/*     */   
/*     */   private static final float A = -1.0F;
/*     */   
/*     */   private static final float A3 = 1.0F;
/*     */   
/*     */   private static final float A2 = -2.0F;
/*     */   
/*     */   private static final float A0 = 1.0F;
/*     */   
/*     */   private static final float B3 = -1.0F;
/*     */   
/*     */   private static final float B2 = 5.0F;
/*     */   
/*     */   private static final float B1 = -8.0F;
/*     */   
/*     */   private static final float B0 = 4.0F;
/*     */   
/*     */   private static float[] dataHelper(int subsampleBits) {
/*  96 */     int one = 1 << subsampleBits;
/*  97 */     int arrayLength = one * 4;
/*  98 */     float[] tableValues = new float[arrayLength];
/* 101 */     float onef = one;
/* 103 */     int count = 0;
/* 104 */     for (int i = 0; i < one; i++) {
/* 105 */       float t = i;
/* 106 */       float f = i / onef;
/* 108 */       tableValues[count++] = bicubic(f + 1.0F);
/* 109 */       tableValues[count++] = bicubic(f);
/* 110 */       tableValues[count++] = bicubic(f - 1.0F);
/* 111 */       tableValues[count++] = bicubic(f - 2.0F);
/*     */     } 
/* 115 */     return tableValues;
/*     */   }
/*     */   
/*     */   private static float bicubic(float x) {
/* 134 */     if (x < 0.0F)
/* 135 */       x = -x; 
/* 139 */     if (x >= 1.0F)
/* 140 */       return ((-1.0F * x + 5.0F) * x + -8.0F) * x + 4.0F; 
/* 142 */     return (1.0F * x + -2.0F) * x * x + 1.0F;
/*     */   }
/*     */   
/*     */   public InterpolationBicubic2(int subsampleBits) {
/* 159 */     super(1, 1, 4, 4, subsampleBits, subsampleBits, 8, dataHelper(subsampleBits), (float[])null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\InterpolationBicubic2.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */