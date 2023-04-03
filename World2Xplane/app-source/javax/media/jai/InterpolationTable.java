/*      */ package javax.media.jai;
/*      */ 
/*      */ public class InterpolationTable extends Interpolation {
/*      */   protected int precisionBits;
/*      */   
/*      */   private int round;
/*      */   
/*      */   private int numSubsamplesH;
/*      */   
/*      */   private int numSubsamplesV;
/*      */   
/*      */   protected double[] dataHd;
/*      */   
/*      */   protected double[] dataVd;
/*      */   
/*      */   protected float[] dataHf;
/*      */   
/*      */   protected float[] dataVf;
/*      */   
/*      */   protected int[] dataHi;
/*      */   
/*      */   protected int[] dataVi;
/*      */   
/*      */   public InterpolationTable(int keyX, int keyY, int width, int height, int subsampleBitsH, int subsampleBitsV, int precisionBits, int[] dataH, int[] dataV) {
/*  118 */     this.leftPadding = keyX;
/*  119 */     this.topPadding = keyY;
/*  120 */     this.width = width;
/*  121 */     this.rightPadding = width - keyX - 1;
/*  123 */     this.precisionBits = precisionBits;
/*  124 */     if (precisionBits > 0)
/*  125 */       this.round = 1 << precisionBits - 1; 
/*  128 */     this.subsampleBitsH = subsampleBitsH;
/*  129 */     this.numSubsamplesH = 1 << subsampleBitsH;
/*  130 */     int entriesH = width * this.numSubsamplesH;
/*  131 */     if (dataH.length != entriesH)
/*  132 */       throw new IllegalArgumentException(JaiI18N.getString("InterpolationTable0")); 
/*  136 */     double prec = (1 << precisionBits);
/*  139 */     this.dataHi = (int[])dataH.clone();
/*  140 */     this.dataHf = new float[entriesH];
/*  141 */     this.dataHd = new double[entriesH];
/*      */     int i;
/*  143 */     for (i = 0; i < entriesH; i++) {
/*  144 */       double d = this.dataHi[i] / prec;
/*  145 */       this.dataHf[i] = (float)d;
/*  146 */       this.dataHd[i] = d;
/*      */     } 
/*  149 */     if (dataV != null) {
/*  150 */       this.height = height;
/*  151 */       this.subsampleBitsV = subsampleBitsV;
/*  152 */       this.numSubsamplesV = 1 << subsampleBitsV;
/*  153 */       int entriesV = height * this.numSubsamplesV;
/*  154 */       if (dataV.length != entriesV)
/*  155 */         throw new IllegalArgumentException(JaiI18N.getString("InterpolationTable1")); 
/*  159 */       this.dataVi = (int[])dataV.clone();
/*  160 */       this.dataVf = new float[entriesV];
/*  161 */       this.dataVd = new double[entriesV];
/*  162 */       for (i = 0; i < entriesV; i++) {
/*  163 */         double d = this.dataVi[i] / prec;
/*  164 */         this.dataVf[i] = (float)d;
/*  165 */         this.dataVd[i] = d;
/*      */       } 
/*      */     } else {
/*  168 */       this.height = width;
/*  169 */       this.subsampleBitsV = subsampleBitsH;
/*  170 */       this.numSubsamplesV = this.numSubsamplesH;
/*  171 */       this.dataVf = this.dataHf;
/*  172 */       this.dataVi = this.dataHi;
/*  173 */       this.dataVd = this.dataHd;
/*      */     } 
/*  175 */     this.bottomPadding = this.height - keyY - 1;
/*      */   }
/*      */   
/*      */   public InterpolationTable(int key, int width, int subsampleBits, int precisionBits, int[] data) {
/*  197 */     this(key, key, width, width, subsampleBits, subsampleBits, precisionBits, data, (int[])null);
/*      */   }
/*      */   
/*      */   public InterpolationTable(int keyX, int keyY, int width, int height, int subsampleBitsH, int subsampleBitsV, int precisionBits, float[] dataH, float[] dataV) {
/*  254 */     this.leftPadding = keyX;
/*  255 */     this.topPadding = keyY;
/*  256 */     this.width = width;
/*  257 */     this.rightPadding = width - keyX - 1;
/*  259 */     this.precisionBits = precisionBits;
/*  260 */     if (precisionBits > 0)
/*  261 */       this.round = 1 << precisionBits - 1; 
/*  264 */     this.subsampleBitsH = subsampleBitsH;
/*  265 */     this.numSubsamplesH = 1 << subsampleBitsH;
/*  266 */     int entriesH = width * this.numSubsamplesH;
/*  267 */     if (dataH.length != entriesH)
/*  268 */       throw new IllegalArgumentException(JaiI18N.getString("InterpolationTable0")); 
/*  272 */     float prec = (1 << precisionBits);
/*  275 */     this.dataHf = (float[])dataH.clone();
/*  276 */     this.dataHi = new int[entriesH];
/*  277 */     this.dataHd = new double[entriesH];
/*      */     int i;
/*  279 */     for (i = 0; i < entriesH; i++) {
/*  280 */       float f = this.dataHf[i];
/*  281 */       this.dataHi[i] = Math.round(f * prec);
/*  282 */       this.dataHd[i] = f;
/*      */     } 
/*  285 */     if (dataV != null) {
/*  286 */       this.height = height;
/*  287 */       this.subsampleBitsV = subsampleBitsV;
/*  288 */       this.numSubsamplesV = 1 << subsampleBitsV;
/*  289 */       int entriesV = height * this.numSubsamplesV;
/*  290 */       if (dataV.length != entriesV)
/*  291 */         throw new IllegalArgumentException(JaiI18N.getString("InterpolationTable1")); 
/*  295 */       this.dataVf = (float[])dataV.clone();
/*  296 */       this.dataVi = new int[entriesV];
/*  297 */       this.dataVd = new double[entriesV];
/*  298 */       for (i = 0; i < entriesV; i++) {
/*  299 */         float f = this.dataVf[i];
/*  300 */         this.dataVi[i] = Math.round(f * prec);
/*  301 */         this.dataVd[i] = f;
/*      */       } 
/*      */     } else {
/*  304 */       this.height = width;
/*  305 */       this.subsampleBitsV = subsampleBitsH;
/*  306 */       this.numSubsamplesV = this.numSubsamplesH;
/*  307 */       this.dataVf = this.dataHf;
/*  308 */       this.dataVi = this.dataHi;
/*  309 */       this.dataVd = this.dataHd;
/*      */     } 
/*  311 */     this.bottomPadding = this.height - keyY - 1;
/*      */   }
/*      */   
/*      */   public InterpolationTable(int key, int width, int subsampleBits, int precisionBits, float[] data) {
/*  334 */     this(key, key, width, width, subsampleBits, subsampleBits, precisionBits, data, (float[])null);
/*      */   }
/*      */   
/*      */   public InterpolationTable(int keyX, int keyY, int width, int height, int subsampleBitsH, int subsampleBitsV, int precisionBits, double[] dataH, double[] dataV) {
/*  389 */     this.leftPadding = keyX;
/*  390 */     this.topPadding = keyY;
/*  391 */     this.width = width;
/*  392 */     this.rightPadding = width - keyX - 1;
/*  394 */     this.precisionBits = precisionBits;
/*  395 */     if (precisionBits > 0)
/*  396 */       this.round = 1 << precisionBits - 1; 
/*  399 */     this.subsampleBitsH = subsampleBitsH;
/*  400 */     this.numSubsamplesH = 1 << subsampleBitsH;
/*  401 */     int entriesH = width * this.numSubsamplesH;
/*  402 */     if (dataH.length != entriesH)
/*  403 */       throw new IllegalArgumentException(JaiI18N.getString("InterpolationTable0")); 
/*  407 */     double prec = (1 << precisionBits);
/*  410 */     this.dataHd = (double[])dataH.clone();
/*  411 */     this.dataHi = new int[entriesH];
/*  412 */     this.dataHf = new float[entriesH];
/*      */     int i;
/*  413 */     for (i = 0; i < entriesH; i++) {
/*  414 */       double d = this.dataHd[i];
/*  415 */       this.dataHi[i] = (int)Math.round(d * prec);
/*  416 */       this.dataHf[i] = (float)d;
/*      */     } 
/*  419 */     if (dataV != null) {
/*  420 */       this.height = height;
/*  421 */       this.subsampleBitsV = subsampleBitsV;
/*  422 */       this.numSubsamplesV = 1 << subsampleBitsV;
/*  423 */       int entriesV = height * this.numSubsamplesV;
/*  424 */       if (dataV.length != entriesV)
/*  425 */         throw new IllegalArgumentException(JaiI18N.getString("InterpolationTable1")); 
/*  429 */       this.dataVd = (double[])dataV.clone();
/*  430 */       this.dataVi = new int[entriesV];
/*  431 */       this.dataVf = new float[entriesV];
/*  432 */       for (i = 0; i < entriesV; i++) {
/*  433 */         double d = this.dataVd[i];
/*  434 */         this.dataVi[i] = (int)Math.round(d * prec);
/*  435 */         this.dataVf[i] = (float)d;
/*      */       } 
/*      */     } else {
/*  438 */       this.height = width;
/*  439 */       this.subsampleBitsV = subsampleBitsH;
/*  440 */       this.numSubsamplesV = this.numSubsamplesH;
/*  441 */       this.dataVd = this.dataHd;
/*  442 */       this.dataVf = this.dataHf;
/*  443 */       this.dataVi = this.dataHi;
/*      */     } 
/*  445 */     this.bottomPadding = this.height - keyY - 1;
/*      */   }
/*      */   
/*      */   public InterpolationTable(int key, int width, int subsampleBits, int precisionBits, double[] data) {
/*  468 */     this(key, key, width, width, subsampleBits, subsampleBits, precisionBits, data, (double[])null);
/*      */   }
/*      */   
/*      */   public int getPrecisionBits() {
/*  478 */     return this.precisionBits;
/*      */   }
/*      */   
/*      */   public int[] getHorizontalTableData() {
/*  515 */     return this.dataHi;
/*      */   }
/*      */   
/*      */   public int[] getVerticalTableData() {
/*  552 */     return this.dataVi;
/*      */   }
/*      */   
/*      */   public float[] getHorizontalTableDataFloat() {
/*  590 */     return this.dataHf;
/*      */   }
/*      */   
/*      */   public float[] getVerticalTableDataFloat() {
/*  628 */     return this.dataVf;
/*      */   }
/*      */   
/*      */   public double[] getHorizontalTableDataDouble() {
/*  666 */     return this.dataHd;
/*      */   }
/*      */   
/*      */   public double[] getVerticalTableDataDouble() {
/*  704 */     return this.dataVd;
/*      */   }
/*      */   
/*      */   public int interpolateH(int[] samples, int xfrac) {
/*  721 */     int sum = 0;
/*  722 */     int offset = this.width * xfrac;
/*  724 */     for (int i = 0; i < this.width; i++)
/*  725 */       sum += this.dataHi[offset + i] * samples[i]; 
/*  727 */     return sum + this.round >> this.precisionBits;
/*      */   }
/*      */   
/*      */   public int interpolateV(int[] samples, int yfrac) {
/*  744 */     int sum = 0;
/*  745 */     int offset = this.width * yfrac;
/*  747 */     for (int i = 0; i < this.width; i++)
/*  748 */       sum += this.dataVi[offset + i] * samples[i]; 
/*  750 */     return sum + this.round >> this.precisionBits;
/*      */   }
/*      */   
/*      */   public int interpolateH(int s0, int s1, int xfrac) {
/*  770 */     int offset = 2 * xfrac;
/*  771 */     int sum = this.dataHi[offset] * s0;
/*  772 */     sum += this.dataHi[offset + 1] * s1;
/*  773 */     return sum + this.round >> this.precisionBits;
/*      */   }
/*      */   
/*      */   public int interpolateH(int s_, int s0, int s1, int s2, int xfrac) {
/*  795 */     int offset = 4 * xfrac;
/*  796 */     int sum = this.dataHi[offset] * s_;
/*  797 */     sum += this.dataHi[offset + 1] * s0;
/*  798 */     sum += this.dataHi[offset + 2] * s1;
/*  799 */     sum += this.dataHi[offset + 3] * s2;
/*  800 */     return sum + this.round >> this.precisionBits;
/*      */   }
/*      */   
/*      */   public int interpolateV(int s0, int s1, int yfrac) {
/*  820 */     int offset = 2 * yfrac;
/*  821 */     int sum = this.dataVi[offset] * s0;
/*  822 */     sum += this.dataVi[offset + 1] * s1;
/*  823 */     return sum + this.round >> this.precisionBits;
/*      */   }
/*      */   
/*      */   public int interpolateV(int s_, int s0, int s1, int s2, int yfrac) {
/*  845 */     int offset = 4 * yfrac;
/*  846 */     int sum = this.dataVi[offset] * s_;
/*  847 */     sum += this.dataVi[offset + 1] * s0;
/*  848 */     sum += this.dataVi[offset + 2] * s1;
/*  849 */     sum += this.dataVi[offset + 3] * s2;
/*  850 */     return sum + this.round >> this.precisionBits;
/*      */   }
/*      */   
/*      */   public int interpolate(int s00, int s01, int s10, int s11, int xfrac, int yfrac) {
/*  877 */     int offsetX = 2 * xfrac;
/*  878 */     int sum0 = this.dataHi[offsetX] * s00 + this.dataHi[offsetX + 1] * s01;
/*  879 */     int sum1 = this.dataHi[offsetX] * s10 + this.dataHi[offsetX + 1] * s11;
/*  882 */     sum0 = sum0 + this.round >> this.precisionBits;
/*  883 */     sum1 = sum1 + this.round >> this.precisionBits;
/*  886 */     int offsetY = 2 * yfrac;
/*  887 */     int sum = this.dataVi[offsetY] * sum0 + this.dataVi[offsetY + 1] * sum1;
/*  889 */     return sum + this.round >> this.precisionBits;
/*      */   }
/*      */   
/*      */   public int interpolate(int s__, int s_0, int s_1, int s_2, int s0_, int s00, int s01, int s02, int s1_, int s10, int s11, int s12, int s2_, int s20, int s21, int s22, int xfrac, int yfrac) {
/*  931 */     int offsetX = 4 * xfrac;
/*  932 */     int offsetX1 = offsetX + 1;
/*  933 */     int offsetX2 = offsetX + 2;
/*  934 */     int offsetX3 = offsetX + 3;
/*  936 */     long sum_ = this.dataHi[offsetX] * s__;
/*  937 */     sum_ += this.dataHi[offsetX1] * s_0;
/*  938 */     sum_ += this.dataHi[offsetX2] * s_1;
/*  939 */     sum_ += this.dataHi[offsetX3] * s_2;
/*  941 */     long sum0 = this.dataHi[offsetX] * s0_;
/*  942 */     sum0 += this.dataHi[offsetX1] * s00;
/*  943 */     sum0 += this.dataHi[offsetX2] * s01;
/*  944 */     sum0 += this.dataHi[offsetX3] * s02;
/*  946 */     long sum1 = this.dataHi[offsetX] * s1_;
/*  947 */     sum1 += this.dataHi[offsetX1] * s10;
/*  948 */     sum1 += this.dataHi[offsetX2] * s11;
/*  949 */     sum1 += this.dataHi[offsetX3] * s12;
/*  951 */     long sum2 = this.dataHi[offsetX] * s2_;
/*  952 */     sum2 += this.dataHi[offsetX1] * s20;
/*  953 */     sum2 += this.dataHi[offsetX2] * s21;
/*  954 */     sum2 += this.dataHi[offsetX3] * s22;
/*  957 */     sum_ = sum_ + this.round >> this.precisionBits;
/*  958 */     sum0 = sum0 + this.round >> this.precisionBits;
/*  959 */     sum1 = sum1 + this.round >> this.precisionBits;
/*  960 */     sum2 = sum2 + this.round >> this.precisionBits;
/*  963 */     int offsetY = 4 * yfrac;
/*  964 */     long sum = this.dataVi[offsetY] * sum_;
/*  965 */     sum += this.dataVi[offsetY + 1] * sum0;
/*  966 */     sum += this.dataVi[offsetY + 2] * sum1;
/*  967 */     sum += this.dataVi[offsetY + 3] * sum2;
/*  969 */     return (int)(sum + this.round >> this.precisionBits);
/*      */   }
/*      */   
/*      */   public int interpolateF(int s__, int s_0, int s_1, int s_2, int s0_, int s00, int s01, int s02, int s1_, int s10, int s11, int s12, int s2_, int s20, int s21, int s22, int xfrac, int yfrac) {
/* 1012 */     int offsetX = 4 * xfrac;
/* 1014 */     float sum_ = this.dataHf[offsetX] * s__;
/* 1015 */     sum_ += this.dataHf[offsetX + 1] * s_0;
/* 1016 */     sum_ += this.dataHf[offsetX + 2] * s_1;
/* 1017 */     sum_ += this.dataHf[offsetX + 3] * s_2;
/* 1019 */     float sum0 = this.dataHf[offsetX] * s0_;
/* 1020 */     sum0 += this.dataHf[offsetX + 1] * s00;
/* 1021 */     sum0 += this.dataHf[offsetX + 2] * s01;
/* 1022 */     sum0 += this.dataHf[offsetX + 3] * s02;
/* 1024 */     float sum1 = this.dataHf[offsetX] * s1_;
/* 1025 */     sum1 += this.dataHf[offsetX + 1] * s10;
/* 1026 */     sum1 += this.dataHf[offsetX + 2] * s11;
/* 1027 */     sum1 += this.dataHf[offsetX + 3] * s12;
/* 1029 */     float sum2 = this.dataHf[offsetX] * s2_;
/* 1030 */     sum2 += this.dataHf[offsetX + 1] * s20;
/* 1031 */     sum2 += this.dataHf[offsetX + 2] * s21;
/* 1032 */     sum2 += this.dataHf[offsetX + 3] * s22;
/* 1035 */     int offsetY = 4 * yfrac;
/* 1036 */     float sum = this.dataVf[offsetY] * sum_;
/* 1037 */     sum += this.dataVf[offsetY + 1] * sum0;
/* 1038 */     sum += this.dataVf[offsetY + 2] * sum1;
/* 1039 */     sum += this.dataVf[offsetY + 3] * sum2;
/* 1041 */     int isum = (int)sum;
/* 1043 */     return isum;
/*      */   }
/*      */   
/*      */   public float interpolateH(float[] samples, float xfrac) {
/* 1059 */     float sum = 0.0F;
/* 1060 */     int ifrac = (int)(xfrac * this.numSubsamplesH);
/* 1061 */     int offset = this.width * ifrac;
/* 1063 */     for (int i = 0; i < this.width; i++)
/* 1064 */       sum += this.dataHf[offset + i] * samples[i]; 
/* 1066 */     return sum;
/*      */   }
/*      */   
/*      */   public float interpolateV(float[] samples, float yfrac) {
/* 1082 */     float sum = 0.0F;
/* 1083 */     int ifrac = (int)(yfrac * this.numSubsamplesV);
/* 1084 */     int offset = this.width * ifrac;
/* 1086 */     for (int i = 0; i < this.width; i++)
/* 1087 */       sum += this.dataVf[offset + i] * samples[i]; 
/* 1089 */     return sum;
/*      */   }
/*      */   
/*      */   public float interpolateH(float s0, float s1, float xfrac) {
/* 1107 */     float sum = 0.0F;
/* 1108 */     int ifrac = (int)(xfrac * this.numSubsamplesH);
/* 1110 */     int offset = 2 * ifrac;
/* 1112 */     sum = this.dataHf[offset] * s0 + this.dataHf[offset + 1] * s1;
/* 1113 */     return sum;
/*      */   }
/*      */   
/*      */   public float interpolateH(float s_, float s0, float s1, float s2, float xfrac) {
/* 1134 */     int ifrac = (int)(xfrac * this.numSubsamplesH);
/* 1136 */     int offset = 4 * ifrac;
/* 1138 */     float sum = this.dataHf[offset] * s_;
/* 1139 */     sum += this.dataHf[offset + 1] * s0;
/* 1140 */     sum += this.dataHf[offset + 2] * s1;
/* 1141 */     sum += this.dataHf[offset + 3] * s2;
/* 1142 */     return sum;
/*      */   }
/*      */   
/*      */   public float interpolateV(float s0, float s1, float yfrac) {
/* 1160 */     int ifrac = (int)(yfrac * this.numSubsamplesV);
/* 1162 */     int offset = 2 * ifrac;
/* 1163 */     float sum = this.dataVf[offset] * s0;
/* 1164 */     sum += this.dataVf[offset + 1] * s1;
/* 1165 */     return sum;
/*      */   }
/*      */   
/*      */   public float interpolateV(float s_, float s0, float s1, float s2, float yfrac) {
/* 1186 */     int ifrac = (int)(yfrac * this.numSubsamplesV);
/* 1188 */     int offset = 4 * ifrac;
/* 1189 */     float sum = this.dataVf[offset] * s_;
/* 1190 */     sum += this.dataVf[offset + 1] * s0;
/* 1191 */     sum += this.dataVf[offset + 2] * s1;
/* 1192 */     sum += this.dataVf[offset + 3] * s2;
/* 1193 */     return sum;
/*      */   }
/*      */   
/*      */   public float interpolate(float s00, float s01, float s10, float s11, float xfrac, float yfrac) {
/* 1216 */     int ifrac = (int)(xfrac * this.numSubsamplesH);
/* 1218 */     int offsetX = 2 * ifrac;
/* 1219 */     float sum0 = this.dataHf[offsetX] * s00 + this.dataHf[offsetX + 1] * s01;
/* 1220 */     float sum1 = this.dataHf[offsetX] * s10 + this.dataHf[offsetX + 1] * s11;
/* 1223 */     ifrac = (int)(yfrac * this.numSubsamplesV);
/* 1224 */     int offsetY = 2 * ifrac;
/* 1225 */     float sum = this.dataVf[offsetY] * sum0 + this.dataVf[offsetY + 1] * sum1;
/* 1227 */     return sum;
/*      */   }
/*      */   
/*      */   public float interpolate(float s__, float s_0, float s_1, float s_2, float s0_, float s00, float s01, float s02, float s1_, float s10, float s11, float s12, float s2_, float s20, float s21, float s22, float xfrac, float yfrac) {
/* 1265 */     int ifrac = (int)(xfrac * this.numSubsamplesH);
/* 1267 */     int offsetX = 4 * ifrac;
/* 1268 */     int offsetX1 = offsetX + 1;
/* 1269 */     int offsetX2 = offsetX + 2;
/* 1270 */     int offsetX3 = offsetX + 3;
/* 1272 */     float sum_ = this.dataHf[offsetX] * s__;
/* 1273 */     sum_ += this.dataHf[offsetX1] * s_0;
/* 1274 */     sum_ += this.dataHf[offsetX2] * s_1;
/* 1275 */     sum_ += this.dataHf[offsetX3] * s_2;
/* 1277 */     float sum0 = this.dataHf[offsetX] * s0_;
/* 1278 */     sum0 += this.dataHf[offsetX1] * s00;
/* 1279 */     sum0 += this.dataHf[offsetX2] * s01;
/* 1280 */     sum0 += this.dataHf[offsetX3] * s02;
/* 1282 */     float sum1 = this.dataHf[offsetX] * s1_;
/* 1283 */     sum1 += this.dataHf[offsetX1] * s10;
/* 1284 */     sum1 += this.dataHf[offsetX2] * s11;
/* 1285 */     sum1 += this.dataHf[offsetX3] * s12;
/* 1287 */     float sum2 = this.dataHf[offsetX] * s2_;
/* 1288 */     sum2 += this.dataHf[offsetX1] * s20;
/* 1289 */     sum2 += this.dataHf[offsetX2] * s21;
/* 1290 */     sum2 += this.dataHf[offsetX3] * s22;
/* 1293 */     ifrac = (int)(yfrac * this.numSubsamplesV);
/* 1294 */     int offsetY = 4 * ifrac;
/* 1295 */     float sum = this.dataVf[offsetY] * sum_;
/* 1296 */     sum += this.dataVf[offsetY + 1] * sum0;
/* 1297 */     sum += this.dataVf[offsetY + 2] * sum1;
/* 1298 */     sum += this.dataVf[offsetY + 3] * sum2;
/* 1300 */     return sum;
/*      */   }
/*      */   
/*      */   public double interpolateH(double[] samples, float xfrac) {
/* 1316 */     double sum = 0.0D;
/* 1317 */     int ifrac = (int)(xfrac * this.numSubsamplesH);
/* 1318 */     int offset = this.width * ifrac;
/* 1320 */     for (int i = 0; i < this.width; i++)
/* 1321 */       sum += this.dataHd[offset + i] * samples[i]; 
/* 1323 */     return sum;
/*      */   }
/*      */   
/*      */   public double interpolateV(double[] samples, float yfrac) {
/* 1339 */     double sum = 0.0D;
/* 1340 */     int ifrac = (int)(yfrac * this.numSubsamplesV);
/* 1341 */     int offset = this.width * ifrac;
/* 1343 */     for (int i = 0; i < this.width; i++)
/* 1344 */       sum += this.dataVd[offset + i] * samples[i]; 
/* 1346 */     return sum;
/*      */   }
/*      */   
/*      */   public double interpolateH(double s0, double s1, float xfrac) {
/* 1364 */     double sum = 0.0D;
/* 1365 */     int ifrac = (int)(xfrac * this.numSubsamplesH);
/* 1367 */     int offset = 2 * ifrac;
/* 1369 */     sum = this.dataHd[offset] * s0 + this.dataHd[offset + 1] * s1;
/* 1370 */     return sum;
/*      */   }
/*      */   
/*      */   public double interpolateH(double s_, double s0, double s1, double s2, float xfrac) {
/* 1391 */     int ifrac = (int)(xfrac * this.numSubsamplesH);
/* 1393 */     int offset = 4 * ifrac;
/* 1395 */     double sum = this.dataHd[offset] * s_;
/* 1396 */     sum += this.dataHd[offset + 1] * s0;
/* 1397 */     sum += this.dataHd[offset + 2] * s1;
/* 1398 */     sum += this.dataHd[offset + 3] * s2;
/* 1399 */     return sum;
/*      */   }
/*      */   
/*      */   public double interpolateV(double s0, double s1, float yfrac) {
/* 1417 */     int ifrac = (int)(yfrac * this.numSubsamplesV);
/* 1419 */     int offset = 2 * ifrac;
/* 1420 */     double sum = this.dataVd[offset] * s0;
/* 1421 */     sum += this.dataVd[offset + 1] * s1;
/* 1422 */     return sum;
/*      */   }
/*      */   
/*      */   public double interpolateV(double s_, double s0, double s1, double s2, float yfrac) {
/* 1443 */     int ifrac = (int)(yfrac * this.numSubsamplesV);
/* 1445 */     int offset = 4 * ifrac;
/* 1446 */     double sum = this.dataVd[offset] * s_;
/* 1447 */     sum += this.dataVd[offset + 1] * s0;
/* 1448 */     sum += this.dataVd[offset + 2] * s1;
/* 1449 */     sum += this.dataVd[offset + 3] * s2;
/* 1450 */     return sum;
/*      */   }
/*      */   
/*      */   public double interpolate(double s00, double s01, double s10, double s11, float xfrac, float yfrac) {
/* 1473 */     int ifrac = (int)(xfrac * this.numSubsamplesH);
/* 1475 */     int offsetX = 2 * ifrac;
/* 1476 */     double sum0 = this.dataHd[offsetX] * s00 + this.dataHd[offsetX + 1] * s01;
/* 1477 */     double sum1 = this.dataHd[offsetX] * s10 + this.dataHd[offsetX + 1] * s11;
/* 1480 */     ifrac = (int)(yfrac * this.numSubsamplesV);
/* 1481 */     int offsetY = 2 * ifrac;
/* 1482 */     double sum = this.dataVd[offsetY] * sum0 + this.dataVd[offsetY + 1] * sum1;
/* 1484 */     return sum;
/*      */   }
/*      */   
/*      */   public double interpolate(double s__, double s_0, double s_1, double s_2, double s0_, double s00, double s01, double s02, double s1_, double s10, double s11, double s12, double s2_, double s20, double s21, double s22, float xfrac, float yfrac) {
/* 1522 */     int ifrac = (int)(xfrac * this.numSubsamplesH);
/* 1524 */     int offsetX = 4 * ifrac;
/* 1525 */     int offsetX1 = offsetX + 1;
/* 1526 */     int offsetX2 = offsetX + 2;
/* 1527 */     int offsetX3 = offsetX + 3;
/* 1529 */     double sum_ = this.dataHd[offsetX] * s__;
/* 1530 */     sum_ += this.dataHd[offsetX1] * s_0;
/* 1531 */     sum_ += this.dataHd[offsetX2] * s_1;
/* 1532 */     sum_ += this.dataHd[offsetX3] * s_2;
/* 1534 */     double sum0 = this.dataHd[offsetX] * s0_;
/* 1535 */     sum0 += this.dataHd[offsetX1] * s00;
/* 1536 */     sum0 += this.dataHd[offsetX2] * s01;
/* 1537 */     sum0 += this.dataHd[offsetX3] * s02;
/* 1539 */     double sum1 = this.dataHd[offsetX] * s1_;
/* 1540 */     sum1 += this.dataHd[offsetX1] * s10;
/* 1541 */     sum1 += this.dataHd[offsetX2] * s11;
/* 1542 */     sum1 += this.dataHd[offsetX3] * s12;
/* 1544 */     double sum2 = this.dataHd[offsetX] * s2_;
/* 1545 */     sum2 += this.dataHd[offsetX1] * s20;
/* 1546 */     sum2 += this.dataHd[offsetX2] * s21;
/* 1547 */     sum2 += this.dataHd[offsetX3] * s22;
/* 1550 */     ifrac = (int)(yfrac * this.numSubsamplesV);
/* 1551 */     int offsetY = 4 * ifrac;
/* 1552 */     double sum = this.dataVd[offsetY] * sum_;
/* 1553 */     sum += this.dataVd[offsetY + 1] * sum0;
/* 1554 */     sum += this.dataVd[offsetY + 2] * sum1;
/* 1555 */     sum += this.dataVd[offsetY + 3] * sum2;
/* 1557 */     return sum;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\InterpolationTable.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */