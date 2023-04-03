/*      */ package javax.media.jai;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ 
/*      */ public abstract class Interpolation implements Serializable {
/*      */   public static final int INTERP_NEAREST = 0;
/*      */   
/*      */   public static final int INTERP_BILINEAR = 1;
/*      */   
/*      */   public static final int INTERP_BICUBIC = 2;
/*      */   
/*      */   public static final int INTERP_BICUBIC_2 = 3;
/*      */   
/*  215 */   private static Interpolation nearestInstance = null;
/*      */   
/*  217 */   private static Interpolation bilinearInstance = null;
/*      */   
/*  219 */   private static Interpolation bicubicInstance = null;
/*      */   
/*  221 */   private static Interpolation bicubic2Instance = null;
/*      */   
/*      */   protected int leftPadding;
/*      */   
/*      */   protected int rightPadding;
/*      */   
/*      */   protected int topPadding;
/*      */   
/*      */   protected int bottomPadding;
/*      */   
/*      */   protected int subsampleBitsH;
/*      */   
/*      */   protected int subsampleBitsV;
/*      */   
/*      */   protected int width;
/*      */   
/*      */   protected int height;
/*      */   
/*      */   public static synchronized Interpolation getInstance(int type) {
/*  284 */     Interpolation interp = null;
/*  286 */     switch (type) {
/*      */       case 0:
/*  288 */         if (nearestInstance == null) {
/*  289 */           interp = nearestInstance = new InterpolationNearest();
/*      */         } else {
/*  291 */           interp = nearestInstance;
/*      */         } 
/*  320 */         return interp;
/*      */       case 1:
/*      */         if (bilinearInstance == null) {
/*      */           interp = bilinearInstance = new InterpolationBilinear();
/*      */         } else {
/*      */           interp = bilinearInstance;
/*      */         } 
/*  320 */         return interp;
/*      */       case 2:
/*      */         if (bicubicInstance == null) {
/*      */           interp = bicubicInstance = new InterpolationBicubic(8);
/*      */         } else {
/*      */           interp = bicubicInstance;
/*      */         } 
/*  320 */         return interp;
/*      */       case 3:
/*      */         if (bicubic2Instance == null) {
/*      */           interp = bicubic2Instance = new InterpolationBicubic2(8);
/*      */         } else {
/*      */           interp = bicubic2Instance;
/*      */         } 
/*  320 */         return interp;
/*      */     } 
/*      */     throw new IllegalArgumentException(JaiI18N.getString("Interpolation0"));
/*      */   }
/*      */   
/*      */   protected Interpolation() {}
/*      */   
/*      */   public Interpolation(int width, int height, int leftPadding, int rightPadding, int topPadding, int bottomPadding, int subsampleBitsH, int subsampleBitsV) {
/*  343 */     this.width = width;
/*  344 */     this.height = height;
/*  345 */     this.leftPadding = leftPadding;
/*  346 */     this.rightPadding = rightPadding;
/*  347 */     this.topPadding = topPadding;
/*  348 */     this.bottomPadding = bottomPadding;
/*  349 */     this.subsampleBitsH = subsampleBitsH;
/*  350 */     this.subsampleBitsV = subsampleBitsV;
/*      */   }
/*      */   
/*      */   public int getLeftPadding() {
/*  355 */     return this.leftPadding;
/*      */   }
/*      */   
/*      */   public int getRightPadding() {
/*  361 */     return this.rightPadding;
/*      */   }
/*      */   
/*      */   public int getTopPadding() {
/*  366 */     return this.topPadding;
/*      */   }
/*      */   
/*      */   public int getBottomPadding() {
/*  372 */     return this.bottomPadding;
/*      */   }
/*      */   
/*      */   public int getWidth() {
/*  377 */     return this.width;
/*      */   }
/*      */   
/*      */   public int getHeight() {
/*  383 */     return this.height;
/*      */   }
/*      */   
/*      */   public boolean isSeparable() {
/*  393 */     return true;
/*      */   }
/*      */   
/*      */   public int getSubsampleBitsH() {
/*  408 */     return this.subsampleBitsH;
/*      */   }
/*      */   
/*      */   public int getSubsampleBitsV() {
/*  417 */     return this.subsampleBitsV;
/*      */   }
/*      */   
/*      */   public abstract int interpolateH(int[] paramArrayOfint, int paramInt);
/*      */   
/*      */   public int interpolateV(int[] samples, int yfrac) {
/*  449 */     return interpolateH(samples, yfrac);
/*      */   }
/*      */   
/*      */   public int interpolate(int[][] samples, int xfrac, int yfrac) {
/*  463 */     int[] interpH = new int[this.height];
/*  465 */     for (int i = 0; i < this.height; i++)
/*  466 */       interpH[i] = interpolateH(samples[i], xfrac); 
/*  468 */     return interpolateV(interpH, yfrac);
/*      */   }
/*      */   
/*      */   public int interpolateH(int s0, int s1, int xfrac) {
/*  486 */     int[] s = new int[2];
/*  487 */     s[0] = s0;
/*  488 */     s[1] = s1;
/*  489 */     return interpolateH(s, xfrac);
/*      */   }
/*      */   
/*      */   public int interpolateH(int s_, int s0, int s1, int s2, int xfrac) {
/*  508 */     int[] s = new int[4];
/*  509 */     s[0] = s_;
/*  510 */     s[1] = s0;
/*  511 */     s[2] = s1;
/*  512 */     s[3] = s2;
/*  513 */     return interpolateH(s, xfrac);
/*      */   }
/*      */   
/*      */   public int interpolateV(int s0, int s1, int yfrac) {
/*  534 */     int[] s = new int[2];
/*  535 */     s[0] = s0;
/*  536 */     s[1] = s1;
/*  537 */     return interpolateV(s, yfrac);
/*      */   }
/*      */   
/*      */   public int interpolateV(int s_, int s0, int s1, int s2, int yfrac) {
/*  560 */     int[] s = new int[4];
/*  561 */     s[0] = s_;
/*  562 */     s[1] = s0;
/*  563 */     s[2] = s1;
/*  564 */     s[3] = s2;
/*  565 */     return interpolateV(s, yfrac);
/*      */   }
/*      */   
/*      */   public int interpolate(int s00, int s01, int s10, int s11, int xfrac, int yfrac) {
/*  588 */     int[][] s = new int[4][4];
/*  589 */     s[0][0] = s00;
/*  590 */     s[0][1] = s01;
/*  591 */     s[1][0] = s10;
/*  592 */     s[1][1] = s11;
/*  593 */     return interpolate(s, xfrac, yfrac);
/*      */   }
/*      */   
/*      */   public int interpolate(int s__, int s_0, int s_1, int s_2, int s0_, int s00, int s01, int s02, int s1_, int s10, int s11, int s12, int s2_, int s20, int s21, int s22, int xfrac, int yfrac) {
/*  630 */     int[][] s = new int[4][4];
/*  631 */     s[0][0] = s__;
/*  632 */     s[0][1] = s_0;
/*  633 */     s[0][2] = s_1;
/*  634 */     s[0][3] = s_2;
/*  635 */     s[1][0] = s0_;
/*  636 */     s[1][1] = s00;
/*  637 */     s[1][2] = s01;
/*  638 */     s[1][3] = s02;
/*  639 */     s[2][0] = s1_;
/*  640 */     s[2][1] = s10;
/*  641 */     s[2][2] = s11;
/*  642 */     s[2][3] = s12;
/*  643 */     s[3][0] = s2_;
/*  644 */     s[3][1] = s20;
/*  645 */     s[3][2] = s21;
/*  646 */     s[3][3] = s22;
/*  647 */     return interpolate(s, xfrac, yfrac);
/*      */   }
/*      */   
/*      */   public abstract float interpolateH(float[] paramArrayOffloat, float paramFloat);
/*      */   
/*      */   public float interpolateV(float[] samples, float yfrac) {
/*  678 */     return interpolateH(samples, yfrac);
/*      */   }
/*      */   
/*      */   public float interpolate(float[][] samples, float xfrac, float yfrac) {
/*  696 */     float[] interpH = new float[this.height];
/*  698 */     for (int i = 0; i < this.height; i++)
/*  699 */       interpH[i] = interpolateH(samples[i], xfrac); 
/*  701 */     return interpolateV(interpH, yfrac);
/*      */   }
/*      */   
/*      */   public float interpolateH(float s0, float s1, float xfrac) {
/*  719 */     float[] s = new float[2];
/*  720 */     s[0] = s0;
/*  721 */     s[1] = s1;
/*  722 */     return interpolateH(s, xfrac);
/*      */   }
/*      */   
/*      */   public float interpolateH(float s_, float s0, float s1, float s2, float xfrac) {
/*  743 */     float[] s = new float[4];
/*  744 */     s[0] = s_;
/*  745 */     s[1] = s0;
/*  746 */     s[2] = s1;
/*  747 */     s[3] = s2;
/*  748 */     return interpolateH(s, xfrac);
/*      */   }
/*      */   
/*      */   public float interpolateV(float s0, float s1, float yfrac) {
/*  770 */     float[] s = new float[2];
/*  771 */     s[0] = s0;
/*  772 */     s[1] = s1;
/*  773 */     return interpolateV(s, yfrac);
/*      */   }
/*      */   
/*      */   public float interpolateV(float s_, float s0, float s1, float s2, float yfrac) {
/*  799 */     float[] s = new float[4];
/*  800 */     s[0] = s_;
/*  801 */     s[1] = s0;
/*  802 */     s[2] = s1;
/*  803 */     s[3] = s2;
/*  804 */     return interpolateV(s, yfrac);
/*      */   }
/*      */   
/*      */   public float interpolate(float s00, float s01, float s10, float s11, float xfrac, float yfrac) {
/*  828 */     float[][] s = new float[4][4];
/*  829 */     s[0][0] = s00;
/*  830 */     s[0][1] = s01;
/*  831 */     s[1][0] = s10;
/*  832 */     s[1][1] = s11;
/*  833 */     return interpolate(s, xfrac, yfrac);
/*      */   }
/*      */   
/*      */   public float interpolate(float s__, float s_0, float s_1, float s_2, float s0_, float s00, float s01, float s02, float s1_, float s10, float s11, float s12, float s2_, float s20, float s21, float s22, float xfrac, float yfrac) {
/*  871 */     float[][] s = new float[4][4];
/*  872 */     s[0][0] = s__;
/*  873 */     s[0][1] = s_0;
/*  874 */     s[0][2] = s_1;
/*  875 */     s[0][3] = s_2;
/*  876 */     s[1][0] = s0_;
/*  877 */     s[1][1] = s00;
/*  878 */     s[1][2] = s01;
/*  879 */     s[1][3] = s02;
/*  880 */     s[2][0] = s1_;
/*  881 */     s[2][1] = s10;
/*  882 */     s[2][2] = s11;
/*  883 */     s[2][3] = s12;
/*  884 */     s[3][0] = s2_;
/*  885 */     s[3][1] = s20;
/*  886 */     s[3][2] = s21;
/*  887 */     s[3][3] = s22;
/*  888 */     return interpolate(s, xfrac, yfrac);
/*      */   }
/*      */   
/*      */   public abstract double interpolateH(double[] paramArrayOfdouble, float paramFloat);
/*      */   
/*      */   public double interpolateV(double[] samples, float yfrac) {
/*  918 */     return interpolateH(samples, yfrac);
/*      */   }
/*      */   
/*      */   public double interpolate(double[][] samples, float xfrac, float yfrac) {
/*  935 */     double[] interpH = new double[this.height];
/*  937 */     for (int i = 0; i < this.height; i++)
/*  938 */       interpH[i] = interpolateH(samples[i], xfrac); 
/*  940 */     return interpolateV(interpH, yfrac);
/*      */   }
/*      */   
/*      */   public double interpolateH(double s0, double s1, float xfrac) {
/*  958 */     double[] s = new double[2];
/*  959 */     s[0] = s0;
/*  960 */     s[1] = s1;
/*  961 */     return interpolateH(s, xfrac);
/*      */   }
/*      */   
/*      */   public double interpolateH(double s_, double s0, double s1, double s2, float xfrac) {
/*  982 */     double[] s = new double[4];
/*  983 */     s[0] = s_;
/*  984 */     s[1] = s0;
/*  985 */     s[2] = s1;
/*  986 */     s[3] = s2;
/*  987 */     return interpolateH(s, xfrac);
/*      */   }
/*      */   
/*      */   public double interpolateV(double s0, double s1, float yfrac) {
/* 1009 */     double[] s = new double[2];
/* 1010 */     s[0] = s0;
/* 1011 */     s[1] = s1;
/* 1012 */     return interpolateV(s, yfrac);
/*      */   }
/*      */   
/*      */   public double interpolateV(double s_, double s0, double s1, double s2, float yfrac) {
/* 1037 */     double[] s = new double[4];
/* 1038 */     s[0] = s_;
/* 1039 */     s[1] = s0;
/* 1040 */     s[2] = s1;
/* 1041 */     s[3] = s2;
/* 1042 */     return interpolateV(s, yfrac);
/*      */   }
/*      */   
/*      */   public double interpolate(double s00, double s01, double s10, double s11, float xfrac, float yfrac) {
/* 1066 */     double[][] s = new double[4][4];
/* 1067 */     s[0][0] = s00;
/* 1068 */     s[0][1] = s01;
/* 1069 */     s[1][0] = s10;
/* 1070 */     s[1][1] = s11;
/* 1071 */     return interpolate(s, xfrac, yfrac);
/*      */   }
/*      */   
/*      */   public double interpolate(double s__, double s_0, double s_1, double s_2, double s0_, double s00, double s01, double s02, double s1_, double s10, double s11, double s12, double s2_, double s20, double s21, double s22, float xfrac, float yfrac) {
/* 1106 */     double[][] s = new double[4][4];
/* 1107 */     s[0][0] = s__;
/* 1108 */     s[0][1] = s_0;
/* 1109 */     s[0][2] = s_1;
/* 1110 */     s[0][3] = s_2;
/* 1111 */     s[1][0] = s0_;
/* 1112 */     s[1][1] = s00;
/* 1113 */     s[1][2] = s01;
/* 1114 */     s[1][3] = s02;
/* 1115 */     s[2][0] = s1_;
/* 1116 */     s[2][1] = s10;
/* 1117 */     s[2][2] = s11;
/* 1118 */     s[2][3] = s12;
/* 1119 */     s[3][0] = s2_;
/* 1120 */     s[3][1] = s20;
/* 1121 */     s[3][2] = s21;
/* 1122 */     s[3][3] = s22;
/* 1123 */     return interpolate(s, xfrac, yfrac);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\Interpolation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */