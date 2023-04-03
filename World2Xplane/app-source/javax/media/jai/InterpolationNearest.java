/*     */ package javax.media.jai;
/*     */ 
/*     */ public final class InterpolationNearest extends Interpolation {
/*     */   public InterpolationNearest() {
/*  48 */     super(1, 1, 0, 0, 0, 0, 0, 0);
/*     */   }
/*     */   
/*     */   public int interpolateH(int[] samples, int xfrac) {
/*  57 */     return samples[0];
/*     */   }
/*     */   
/*     */   public int interpolateV(int[] samples, int yfrac) {
/*  65 */     return samples[0];
/*     */   }
/*     */   
/*     */   public int interpolate(int[][] samples, int xfrac, int yfrac) {
/*  73 */     return samples[0][0];
/*     */   }
/*     */   
/*     */   public int interpolateH(int s0, int s1, int xfrac) {
/*  81 */     return s0;
/*     */   }
/*     */   
/*     */   public int interpolateV(int s0, int s1, int yfrac) {
/*  89 */     return s0;
/*     */   }
/*     */   
/*     */   public int interpolate(int s00, int s01, int s10, int s11, int xfrac, int yfrac) {
/*  99 */     return s00;
/*     */   }
/*     */   
/*     */   public int interpolate(int s__, int s_0, int s_1, int s_2, int s0_, int s00, int s01, int s02, int s1_, int s10, int s11, int s12, int s2_, int s20, int s21, int s22, int xfrac, int yfrac) {
/* 111 */     return s00;
/*     */   }
/*     */   
/*     */   public float interpolateH(float[] samples, float xfrac) {
/* 119 */     return samples[0];
/*     */   }
/*     */   
/*     */   public float interpolateV(float[] samples, float yfrac) {
/* 127 */     return samples[0];
/*     */   }
/*     */   
/*     */   public float interpolate(float[][] samples, float xfrac, float yfrac) {
/* 137 */     return samples[0][0];
/*     */   }
/*     */   
/*     */   public float interpolateH(float s0, float s1, float xfrac) {
/* 145 */     return s0;
/*     */   }
/*     */   
/*     */   public float interpolateV(float s0, float s1, float yfrac) {
/* 153 */     return s0;
/*     */   }
/*     */   
/*     */   public float interpolate(float s00, float s01, float s10, float s11, float xfrac, float yfrac) {
/* 163 */     return s00;
/*     */   }
/*     */   
/*     */   public float interpolate(float s__, float s_0, float s_1, float s_2, float s0_, float s00, float s01, float s02, float s1_, float s10, float s11, float s12, float s2_, float s20, float s21, float s22, float xfrac, float yfrac) {
/* 175 */     return s00;
/*     */   }
/*     */   
/*     */   public double interpolateH(double[] samples, float xfrac) {
/* 183 */     return samples[0];
/*     */   }
/*     */   
/*     */   public double interpolateV(double[] samples, float yfrac) {
/* 191 */     return samples[0];
/*     */   }
/*     */   
/*     */   public double interpolate(double[][] samples, float xfrac, float yfrac) {
/* 201 */     return samples[0][0];
/*     */   }
/*     */   
/*     */   public double interpolateH(double s0, double s1, float xfrac) {
/* 209 */     return s0;
/*     */   }
/*     */   
/*     */   public double interpolateV(double s0, double s1, float yfrac) {
/* 217 */     return s0;
/*     */   }
/*     */   
/*     */   public double interpolate(double s00, double s01, double s10, double s11, float xfrac, float yfrac) {
/* 227 */     return s00;
/*     */   }
/*     */   
/*     */   public double interpolate(double s__, double s_0, double s_1, double s_2, double s0_, double s00, double s01, double s02, double s1_, double s10, double s11, double s12, double s2_, double s20, double s21, double s22, float xfrac, float yfrac) {
/* 239 */     return s00;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\InterpolationNearest.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */