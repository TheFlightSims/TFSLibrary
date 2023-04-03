/*     */ package javax.media.jai;
/*     */ 
/*     */ public final class InterpolationBilinear extends Interpolation {
/*     */   private int one;
/*     */   
/*     */   private int round;
/*     */   
/*     */   private int shift;
/*     */   
/*     */   private int shift2;
/*     */   
/*     */   private int round2;
/*     */   
/*     */   static final int DEFAULT_SUBSAMPLE_BITS = 8;
/*     */   
/*     */   public InterpolationBilinear(int subsampleBits) {
/*  97 */     super(2, 2, 0, 1, 0, 1, subsampleBits, subsampleBits);
/*  99 */     this.shift = subsampleBits;
/* 100 */     this.one = 1 << this.shift;
/* 101 */     this.round = 1 << this.shift - 1;
/* 103 */     this.shift2 = 2 * subsampleBits;
/* 104 */     this.round2 = 1 << this.shift2 - 1;
/*     */   }
/*     */   
/*     */   public InterpolationBilinear() {
/* 112 */     this(8);
/*     */   }
/*     */   
/*     */   public final int interpolateH(int[] samples, int xfrac) {
/* 123 */     return interpolateH(samples[0], samples[1], xfrac);
/*     */   }
/*     */   
/*     */   public final int interpolateV(int[] samples, int yfrac) {
/* 134 */     return interpolateV(samples[0], samples[1], yfrac);
/*     */   }
/*     */   
/*     */   public final int interpolate(int[][] samples, int xfrac, int yfrac) {
/* 146 */     return interpolate(samples[0][0], samples[0][1], samples[1][0], samples[1][1], xfrac, yfrac);
/*     */   }
/*     */   
/*     */   public final int interpolateH(int s0, int s1, int xfrac) {
/* 161 */     return (s1 - s0) * xfrac + (s0 << this.shift) + this.round >> this.shift;
/*     */   }
/*     */   
/*     */   public final int interpolateV(int s0, int s1, int yfrac) {
/* 174 */     return (s1 - s0) * yfrac + (s0 << this.shift) + this.round >> this.shift;
/*     */   }
/*     */   
/*     */   public final int interpolateH(int s_, int s0, int s1, int s2, int xfrac) {
/* 182 */     return interpolateH(s0, s1, xfrac);
/*     */   }
/*     */   
/*     */   public final int interpolateV(int s_, int s0, int s1, int s2, int yfrac) {
/* 190 */     return interpolateV(s0, s1, yfrac);
/*     */   }
/*     */   
/*     */   public final int interpolate(int s00, int s01, int s10, int s11, int xfrac, int yfrac) {
/* 207 */     int s0 = (s01 - s00) * xfrac + (s00 << this.shift);
/* 208 */     int s1 = (s11 - s10) * xfrac + (s10 << this.shift);
/* 209 */     return (s1 - s0) * yfrac + (s0 << this.shift) + this.round2 >> this.shift2;
/*     */   }
/*     */   
/*     */   public final int interpolate(int s__, int s_0, int s_1, int s_2, int s0_, int s00, int s01, int s02, int s1_, int s10, int s11, int s12, int s2_, int s20, int s21, int s22, int xfrac, int yfrac) {
/* 221 */     return interpolate(s00, s01, s10, s11, xfrac, yfrac);
/*     */   }
/*     */   
/*     */   public final float interpolateH(float[] samples, float xfrac) {
/* 233 */     return interpolateH(samples[0], samples[1], xfrac);
/*     */   }
/*     */   
/*     */   public final float interpolateV(float[] samples, float yfrac) {
/* 245 */     return interpolateV(samples[0], samples[1], yfrac);
/*     */   }
/*     */   
/*     */   public final float interpolate(float[][] samples, float xfrac, float yfrac) {
/* 259 */     return interpolate(samples[0][0], samples[0][1], samples[1][0], samples[1][1], xfrac, yfrac);
/*     */   }
/*     */   
/*     */   public final float interpolateH(float s0, float s1, float xfrac) {
/* 275 */     return (s1 - s0) * xfrac + s0;
/*     */   }
/*     */   
/*     */   public final float interpolateV(float s0, float s1, float yfrac) {
/* 289 */     return (s1 - s0) * yfrac + s0;
/*     */   }
/*     */   
/*     */   public final float interpolateH(float s_, float s0, float s1, float s2, float frac) {
/* 298 */     return interpolateH(s0, s1, frac);
/*     */   }
/*     */   
/*     */   public final float interpolateV(float s_, float s0, float s1, float s2, float frac) {
/* 307 */     return interpolateV(s0, s1, frac);
/*     */   }
/*     */   
/*     */   public final float interpolate(float s00, float s01, float s10, float s11, float xfrac, float yfrac) {
/* 324 */     float s0 = (s01 - s00) * xfrac + s00;
/* 325 */     float s1 = (s11 - s10) * xfrac + s10;
/* 326 */     return (s1 - s0) * yfrac + s0;
/*     */   }
/*     */   
/*     */   public final float interpolate(float s__, float s_0, float s_1, float s_2, float s0_, float s00, float s01, float s02, float s1_, float s10, float s11, float s12, float s2_, float s20, float s21, float s22, float xfrac, float yfrac) {
/* 338 */     return interpolate(s00, s01, s10, s11, xfrac, yfrac);
/*     */   }
/*     */   
/*     */   public final double interpolateH(double[] samples, float xfrac) {
/* 350 */     return interpolateH(samples[0], samples[1], xfrac);
/*     */   }
/*     */   
/*     */   public final double interpolateV(double[] samples, float yfrac) {
/* 362 */     return interpolateV(samples[0], samples[1], yfrac);
/*     */   }
/*     */   
/*     */   public final double interpolate(double[][] samples, float xfrac, float yfrac) {
/* 376 */     return interpolate(samples[0][0], samples[0][1], samples[1][0], samples[1][1], xfrac, yfrac);
/*     */   }
/*     */   
/*     */   public final double interpolateH(double s0, double s1, float xfrac) {
/* 392 */     return (s1 - s0) * xfrac + s0;
/*     */   }
/*     */   
/*     */   public final double interpolateV(double s0, double s1, float yfrac) {
/* 406 */     return (s1 - s0) * yfrac + s0;
/*     */   }
/*     */   
/*     */   public final double interpolateH(double s_, double s0, double s1, double s2, float xfrac) {
/* 415 */     return interpolateH(s0, s1, xfrac);
/*     */   }
/*     */   
/*     */   public final double interpolateV(double s_, double s0, double s1, double s2, float yfrac) {
/* 424 */     return interpolateV(s0, s1, yfrac);
/*     */   }
/*     */   
/*     */   public final double interpolate(double s00, double s01, double s10, double s11, float xfrac, float yfrac) {
/* 441 */     double s0 = (s01 - s00) * xfrac + s00;
/* 442 */     double s1 = (s11 - s10) * xfrac + s10;
/* 443 */     return (s1 - s0) * yfrac + s0;
/*     */   }
/*     */   
/*     */   public final double interpolate(double s__, double s_0, double s_1, double s_2, double s0_, double s00, double s01, double s02, double s1_, double s10, double s11, double s12, double s2_, double s20, double s21, double s22, float xfrac, float yfrac) {
/* 455 */     return interpolate(s00, s01, s10, s11, xfrac, yfrac);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\InterpolationBilinear.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */