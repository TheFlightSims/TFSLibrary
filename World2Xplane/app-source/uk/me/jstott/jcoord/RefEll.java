/*     */ package uk.me.jstott.jcoord;
/*     */ 
/*     */ public class RefEll {
/*  35 */   public static final RefEll AIRY_1830 = new RefEll(6377563.396D, 6356256.909D);
/*     */   
/*  44 */   public static final RefEll BESSEL_1841 = new RefEll(6377397.155D, 299.1528128D);
/*     */   
/*  53 */   public static final RefEll CLARKE_1866 = new RefEll(6378206.4D, 294.9786982D);
/*     */   
/*  62 */   public static final RefEll CLARKE_1880 = new RefEll(6378249.145D, 293.465D);
/*     */   
/*  71 */   public static final RefEll EVEREST_1830 = new RefEll(6377276.345D, 300.8017D);
/*     */   
/*  80 */   public static final RefEll FISCHER_1960 = new RefEll(6378166.0D, 298.3D);
/*     */   
/*  87 */   public static final RefEll FISCHER_1968 = new RefEll(6378150.0D, 298.3D);
/*     */   
/*  94 */   public static final RefEll GRS_1967 = new RefEll(6378160.0D, 298.247167427D);
/*     */   
/* 103 */   public static final RefEll GRS_1975 = new RefEll(6378140.0D, 298.257D);
/*     */   
/* 112 */   public static final RefEll GRS_1980 = new RefEll(6378137.0D, 298.257222101D);
/*     */   
/* 121 */   public static final RefEll HOUGH_1956 = new RefEll(6378270.0D, 297.0D);
/*     */   
/* 128 */   public static final RefEll INTERNATIONAL = new RefEll(6378388.0D, 297.0D);
/*     */   
/* 135 */   public static final RefEll KRASSOVSKY_1940 = new RefEll(6378245.0D, 298.3D);
/*     */   
/* 142 */   public static final RefEll SOUTH_AMERICAN_1969 = new RefEll(6378160.0D, 298.25D);
/*     */   
/* 151 */   public static final RefEll WGS60 = new RefEll(6378165.0D, 298.3D);
/*     */   
/* 158 */   public static final RefEll WGS66 = new RefEll(6378145.0D, 298.25D);
/*     */   
/* 167 */   public static final RefEll WGS72 = new RefEll(6378135.0D, 298.26D);
/*     */   
/* 176 */   public static final RefEll WGS84 = new RefEll(6378137.0D, 6356752.3141D);
/*     */   
/*     */   private double maj;
/*     */   
/*     */   private double min;
/*     */   
/*     */   private double ecc;
/*     */   
/*     */   public RefEll(double maj, double min) {
/* 212 */     this.maj = maj;
/* 213 */     this.min = min;
/* 214 */     this.ecc = (maj * maj - min * min) / maj * maj;
/*     */   }
/*     */   
/*     */   public double getMaj() {
/* 225 */     return this.maj;
/*     */   }
/*     */   
/*     */   public double getMin() {
/* 236 */     return this.min;
/*     */   }
/*     */   
/*     */   public double getEcc() {
/* 247 */     return this.ecc;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\RefEll.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */