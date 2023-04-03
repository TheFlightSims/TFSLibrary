/*     */ package org.geotools.nature;
/*     */ 
/*     */ public final class SeaWater {
/*     */   public static final double STANDARD_CONDUCTIVITY = 42.914D;
/*     */   
/*  64 */   private static final double[] EOS80_A = new double[] { -28.263737D, 0.06793952D, -0.00909529D, 1.001685E-4D, -1.120083E-6D, 6.536332E-9D };
/*     */   
/*  65 */   private static final double[] EOS80_B = new double[] { 0.824493D, -0.0040899D, 7.6438E-5D, -8.2467E-7D, 5.3875E-9D };
/*     */   
/*  66 */   private static final double[] EOS80_C = new double[] { -0.00572466D, 1.0227E-4D, -1.6546E-6D };
/*     */   
/*     */   private static final double EOS80_D = 4.8314E-4D;
/*     */   
/*  68 */   private static final double[] EOS80_E = new double[] { -1930.06D, 148.4206D, -2.327105D, 0.01360477D, -5.155288E-5D };
/*     */   
/*  69 */   private static final double[] EOS80_F = new double[] { 54.6746D, -0.603459D, 0.0109987D, -6.167E-5D };
/*     */   
/*  70 */   private static final double[] EOS80_G = new double[] { 0.07944D, 0.016483D, -5.3009E-4D };
/*     */   
/*  71 */   private static final double[] EOS80_H = new double[] { -0.1194975D, 0.00143713D, 1.16092E-4D, -5.77905E-7D };
/*     */   
/*  72 */   private static final double[] EOS80_I = new double[] { 0.0022838D, -1.0981E-5D, -1.6078E-6D };
/*     */   
/*     */   private static final double EOS80_J = 1.91075E-4D;
/*     */   
/*  74 */   private static final double[] EOS80_K = new double[] { 3.47718E-5D, -6.12293E-6D, 5.2787E-8D };
/*     */   
/*  75 */   private static final double[] EOS80_M = new double[] { -9.9348E-7D, 2.0816E-8D, 9.1697E-10D };
/*     */   
/*  76 */   private static final double[] EOS80_N = new double[] { 21582.27D, 3.359406D, 5.03217E-5D };
/*     */   
/*     */   private static final double RHO_35_0_0 = 1028.1063D;
/*     */   
/*     */   private static final double DR_35_0_0 = 28.106331D;
/*     */   
/*  85 */   private static final double[] EOS80_At = new double[] { 999.842594D, 0.06793952D, -0.00909529D, 1.001685E-4D, -1.120083E-6D, 6.536332E-9D };
/*     */   
/*  86 */   private static final double[] EOS80_Et = new double[] { 19652.21D, 148.4206D, -2.327105D, 0.01360477D, -5.155288E-5D };
/*     */   
/*  87 */   private static final double[] EOS80_Ht = new double[] { 3.239908D, 0.00143713D, 1.16092E-4D, -5.77905E-7D };
/*     */   
/*  88 */   private static final double[] EOS80_Kt = new double[] { 8.50935E-5D, -6.12293E-6D, 5.2787E-8D };
/*     */   
/*  94 */   private static final double[] PSS78_A = new double[] { 0.008D, -0.1692D, 25.3851D, 14.0941D, -7.0261D, 2.7081D };
/*     */   
/*  95 */   private static final double[] PSS78_B = new double[] { 5.0E-4D, -0.0056D, -0.0066D, -0.0375D, 0.0636D, -0.0144D };
/*     */   
/*  96 */   private static final double[] PSS78_C = new double[] { 0.6766097D, 0.0200564D, 1.104259E-4D, -6.9698E-7D, 1.0031E-9D };
/*     */   
/*  97 */   private static final double[] PSS78_D = new double[] { 0.03426D, 4.464E-4D, 0.4215D, -0.003107D };
/*     */   
/*  98 */   private static final double[] PSS78_E = new double[] { 2.07E-5D, -6.37E-10D, 3.989E-15D };
/*     */   
/*  99 */   private static final double[] PSS78_G = new double[] { -0.1692D, 50.7702D, 42.2823D, -28.1044D, 13.5405D };
/*     */   
/* 100 */   private static final double[] PSS78_H = new double[] { -0.0188D, -0.1125D, 0.2544D, -0.072D };
/*     */   
/*     */   private static final double PSS78_K = 0.0162D;
/*     */   
/* 107 */   private static final double[] PSS78_AR = new double[] { 7.737D, -9.819D, 8.663D, -2.625D };
/*     */   
/* 108 */   private static final double[] PSS78_AT = new double[] { 0.03473D, 0.003188D, -4.655E-5D };
/*     */   
/* 109 */   private static final double[] PSS78_CR = new double[] { -0.1001D, 0.0482D, -6.682E-4D };
/*     */   
/* 117 */   private static final double[] HEAT_AA = new double[] { -7.643575D, 0.1072763D, -0.00138385D };
/*     */   
/* 118 */   private static final double[] HEAT_BB = new double[] { 0.1770383D, -0.00407718D, 5.148E-5D };
/*     */   
/* 119 */   private static final double[] HEAT_CC = new double[] { 4217.4D, -3.720283D, 0.1412855D, -0.002654387D, 2.093236E-5D };
/*     */   
/* 120 */   private static final double[] HEAT_A = new double[] { -0.49592D, 0.0145747D, -3.13885E-4D, 2.0357E-6D, 1.7168E-8D };
/*     */   
/* 121 */   private static final double[] HEAT_B = new double[] { 2.4931E-4D, -1.08645E-5D, 2.87533E-7D, -4.0027E-9D, 2.2956E-11D };
/*     */   
/* 122 */   private static final double[] HEAT_C = new double[] { -5.422E-8D, 2.638E-9D, -6.5637E-11D, 6.136E-13D };
/*     */   
/* 123 */   private static final double[] HEAT_D = new double[] { 0.0049247D, -1.28315E-4D, 9.802E-7D, 2.5941E-8D, -2.9179E-10D };
/*     */   
/* 124 */   private static final double[] HEAT_E = new double[] { -1.2331E-4D, -1.517E-6D, 3.122E-8D };
/*     */   
/* 125 */   private static final double[] HEAT_F = new double[] { -2.9558E-6D, 1.17054E-7D, -2.3905E-9D, 1.8448E-11D };
/*     */   
/*     */   private static final double HEAT_G = 9.971E-8D;
/*     */   
/* 127 */   private static final double[] HEAT_H = new double[] { 5.54E-10D, -1.7682E-11D, 3.513E-13D };
/*     */   
/*     */   private static final double HEAT_J = -1.43E-12D;
/*     */   
/* 136 */   private static final double[] GRAD_A = new double[] { 3.5803E-5D, 8.5258E-6D, -6.836E-8D, 6.6228E-10D };
/*     */   
/* 137 */   private static final double[] GRAD_B = new double[] { 1.8932E-6D, -4.2393E-8D };
/*     */   
/* 138 */   private static final double[] GRAD_C = new double[] { 1.8741E-8D, -6.7795E-10D, 8.733E-12D, -5.4481E-14D };
/*     */   
/* 139 */   private static final double[] GRAD_D = new double[] { -1.1351E-10D, 2.7759E-12D };
/*     */   
/* 140 */   private static final double[] GRAD_E = new double[] { -4.6206E-13D, 1.8676E-14D, -2.1687E-16D };
/*     */   
/* 148 */   private static final double[] DEPTH_C = new double[] { 9.72659D, -2.2512E-5D, 2.279E-10D, -1.82E-15D };
/*     */   
/* 156 */   private static final double[] SOUND_A0 = new double[] { 1.389D, -0.01262D, 7.164E-5D, 2.006E-6D, -3.21E-8D };
/*     */   
/* 157 */   private static final double[] SOUND_A1 = new double[] { 9.4742E-5D, -1.258E-5D, -6.4885E-8D, 1.0507E-8D, -2.0122E-10D };
/*     */   
/* 158 */   private static final double[] SOUND_A2 = new double[] { -3.9064E-7D, 9.1041E-9D, -1.6002E-10D, 7.988E-12D };
/*     */   
/* 159 */   private static final double[] SOUND_A3 = new double[] { 1.1E-10D, 6.649E-12D, -3.389E-13D };
/*     */   
/* 160 */   private static final double[] SOUND_B0 = new double[] { -0.01922D, -4.42E-5D };
/*     */   
/* 161 */   private static final double[] SOUND_B1 = new double[] { 7.3637E-5D, 1.7945E-7D };
/*     */   
/* 162 */   private static final double[] SOUND_C0 = new double[] { 1402.388D, 5.03711D, -0.0580852D, 3.342E-4D, -1.478E-6D, 3.1464E-9D };
/*     */   
/* 163 */   private static final double[] SOUND_C1 = new double[] { 0.153563D, 6.8982E-4D, -8.1788E-6D, 1.3621E-7D, -6.1185E-10D };
/*     */   
/* 164 */   private static final double[] SOUND_C2 = new double[] { 3.126E-5D, -1.7107E-6D, 2.5974E-8D, -2.5335E-10D, 1.0405E-12D };
/*     */   
/* 165 */   private static final double[] SOUND_C3 = new double[] { -9.7729E-9D, 3.8504E-10D, -2.3643E-12D };
/*     */   
/*     */   private static final double SOUND_D0 = 0.001727D;
/*     */   
/*     */   private static final double SOUND_D1 = -7.9836E-6D;
/*     */   
/* 175 */   private static final double[] O2_AT = new double[] { -135.29996D, 157228.8D, -6.637149E7D, 1.243678E10D, -8.621061E11D };
/*     */   
/* 176 */   private static final double[] O2_AS = new double[] { 0.020573D, -12.142D, 2363.0D, 1.0D };
/*     */   
/*     */   public static double density(double S, double T, double P) {
/* 196 */     P /= 10.0D;
/* 199 */     double RHO_0_T_0 = polynome(T, EOS80_At);
/* 202 */     double SR = Math.sqrt(S);
/* 203 */     double RHO_S_T_0 = (4.8314E-4D * S + polynome(T, EOS80_C) * SR + polynome(T, EOS80_B)) * S + RHO_0_T_0;
/* 206 */     double K_S_T_0 = (polynome(T, EOS80_F) + polynome(T, EOS80_G) * SR) * S + polynome(T, EOS80_Et);
/* 207 */     double K_S_T_P = K_S_T_0 + ((1.91075E-4D * SR + polynome(T, EOS80_I)) * S + polynome(T, EOS80_Ht) + (polynome(T, EOS80_Kt) + polynome(T, EOS80_M) * S) * P) * P;
/* 209 */     return RHO_S_T_0 / (1.0D - P / K_S_T_P);
/*     */   }
/*     */   
/*     */   public static double densitySigmaT(double S, double T, double P) {
/* 224 */     P /= 10.0D;
/* 226 */     double SR = Math.sqrt(S);
/* 227 */     double RHO = (4.8314E-4D * S + polynome(T, EOS80_C) * SR + polynome(T, EOS80_B)) * S + polynome(T, EOS80_A);
/* 230 */     double V_35_0_0 = 9.726620681149411E-4D;
/* 231 */     double SVAN_S_T_0 = -RHO * 9.726620681149411E-4D / (RHO + 1028.1063D);
/* 232 */     if (P <= 0.0D)
/* 233 */       return RHO + 28.106331D; 
/* 236 */     double K0 = (polynome(T, EOS80_F) + polynome(T, EOS80_G) * SR) * S + polynome(T, EOS80_E);
/* 237 */     double DK = K0 + ((1.91075E-4D * SR + polynome(T, EOS80_I)) * S + polynome(T, EOS80_H) + (polynome(T, EOS80_K) + polynome(T, EOS80_M) * S) * P) * P;
/* 240 */     double K_35_0_P = polynome(P, EOS80_N);
/* 241 */     double V_S_T_0 = SVAN_S_T_0 + 9.726620681149411E-4D;
/* 242 */     double SVANS = SVAN_S_T_0 * (1.0D - P / K_35_0_P) + V_S_T_0 * P * DK / K_35_0_P * (K_35_0_P + DK);
/* 246 */     double V_35_0_P = 9.726620681149411E-4D * (1.0D - P / K_35_0_P);
/* 247 */     double DR_35_0_P = P / K_35_0_P * V_35_0_P;
/* 248 */     double DVAN = SVANS / V_35_0_P * (V_35_0_P + SVANS);
/* 249 */     return 28.106331D + DR_35_0_P - DVAN;
/*     */   }
/*     */   
/*     */   public static double volume(double S, double T, double P) {
/* 263 */     P /= 10.0D;
/* 265 */     double SR = Math.sqrt(S);
/* 266 */     double RHO = (4.8314E-4D * S + polynome(T, EOS80_C) * SR + polynome(T, EOS80_B)) * S + polynome(T, EOS80_A);
/* 269 */     double V_35_0_0 = 9.726620681149411E-4D;
/* 270 */     double SVAN_S_T_0 = -RHO * 9.726620681149411E-4D / (RHO + 1028.1063D);
/* 271 */     if (P <= 0.0D)
/* 272 */       return SVAN_S_T_0 + 9.726620681149411E-4D; 
/* 275 */     double K0 = (polynome(T, EOS80_F) + polynome(T, EOS80_G) * SR) * S + polynome(T, EOS80_E);
/* 276 */     double DK = K0 + ((1.91075E-4D * SR + polynome(T, EOS80_I)) * S + polynome(T, EOS80_H) + (polynome(T, EOS80_K) + polynome(T, EOS80_M) * S) * P) * P;
/* 279 */     double K_35_0_P = polynome(P, EOS80_N);
/* 280 */     double V_S_T_0 = SVAN_S_T_0 + 9.726620681149411E-4D;
/* 281 */     return (SVAN_S_T_0 + 9.726620681149411E-4D) * (1.0D - P / K_35_0_P) + V_S_T_0 * P * DK / K_35_0_P * (K_35_0_P + DK);
/*     */   }
/*     */   
/*     */   public static double volumeAnomaly(double S, double T, double P) {
/* 297 */     P /= 10.0D;
/* 299 */     double SR = Math.sqrt(S);
/* 300 */     double RHO = (4.8314E-4D * S + polynome(T, EOS80_C) * SR + polynome(T, EOS80_B)) * S + polynome(T, EOS80_A);
/* 303 */     double V_35_0_0 = 9.726620681149411E-4D;
/* 304 */     double SVAN_S_T_0 = -RHO * 9.726620681149411E-4D / (RHO + 1028.1063D);
/* 305 */     if (P <= 0.0D)
/* 306 */       return SVAN_S_T_0; 
/* 309 */     double K0 = (polynome(T, EOS80_F) + polynome(T, EOS80_G) * SR) * S + polynome(T, EOS80_E);
/* 310 */     double DK = K0 + ((1.91075E-4D * SR + polynome(T, EOS80_I)) * S + polynome(T, EOS80_H) + (polynome(T, EOS80_K) + polynome(T, EOS80_M) * S) * P) * P;
/* 313 */     double K_35_0_P = polynome(P, EOS80_N);
/* 314 */     double V_S_T_0 = SVAN_S_T_0 + 9.726620681149411E-4D;
/* 315 */     return SVAN_S_T_0 * (1.0D - P / K_35_0_P) + V_S_T_0 * P * DK / K_35_0_P * (K_35_0_P + DK);
/*     */   }
/*     */   
/*     */   private static double sal(double RT, double XT) {
/* 323 */     return polynome(RT, PSS78_A) + XT / (1.0D + 0.0162D * XT) * polynome(RT, PSS78_B);
/*     */   }
/*     */   
/*     */   private static double dsal(double RT, double XT) {
/* 331 */     return polynome(RT, PSS78_G) + XT / (1.0D + 0.0162D * XT) * polynome(RT, PSS78_H);
/*     */   }
/*     */   
/*     */   public static double salinity(double C, double T, double P) {
/* 348 */     C /= 42.914D;
/* 349 */     if (C >= 5.0E-4D) {
/* 350 */       double XR = Math.sqrt(C / polynome(T, PSS78_C) * (1.0D + polynome(P, PSS78_E) * P / ((PSS78_D[1] * T + PSS78_D[0]) * T + 1.0D + (PSS78_D[3] * T + PSS78_D[2]) * C)));
/* 352 */       double S = sal(XR, T - 15.0D);
/* 353 */       if (S < 42.0D)
/* 353 */         return S; 
/* 360 */       return 35.0D * C + C * (C - 1.0D) * (polynome(C, PSS78_AR) + T * (polynome(T, PSS78_AT) + C * (PSS78_CR[0] + PSS78_CR[1] * C + PSS78_CR[2] * T)));
/*     */     } 
/* 364 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public static double conductivity(double S, double T, double P) {
/* 377 */     if (S >= 0.02D) {
/* 378 */       double XT = T - 15.0D;
/* 379 */       double RT = Math.sqrt(S / 35.0D);
/* 380 */       double SI = sal(RT, XT);
/* 381 */       for (int n = 0; n < 10; n++) {
/* 382 */         RT += (S - SI) / dsal(RT, XT);
/* 383 */         SI = sal(RT, XT);
/* 384 */         if (Math.abs(SI - S) < 1.0E-4D)
/*     */           break; 
/*     */       } 
/* 386 */       double RTT = polynome(T, PSS78_C) * RT * RT;
/* 387 */       double AT = PSS78_D[3] * T + PSS78_D[2];
/* 388 */       double BT = (PSS78_D[1] * T + PSS78_D[0]) * T + 1.0D;
/* 389 */       double CP = RTT * (BT + polynome(P, PSS78_E) * P);
/* 390 */       BT -= RTT * AT;
/* 392 */       double cnd = 0.5D * (Math.sqrt(Math.abs(BT * BT + 4.0D * AT * CP)) - BT) / AT;
/* 393 */       return cnd * 42.914D;
/*     */     } 
/* 395 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public static double specificHeat(double S, double T, double P) {
/* 408 */     P /= 10.0D;
/* 409 */     double SR = Math.sqrt(S);
/* 410 */     return polynome(T, HEAT_CC) + (polynome(T, HEAT_BB) * SR + polynome(T, HEAT_AA)) * S + ((polynome(T, HEAT_C) * P + polynome(T, HEAT_B)) * P + polynome(T, HEAT_A)) * P + (((-1.43E-12D * SR + polynome(T, HEAT_H)) * S * P + (9.971E-8D * SR + polynome(T, HEAT_F)) * S) * P + (polynome(T, HEAT_E) * SR + polynome(T, HEAT_D)) * S) * P;
/*     */   }
/*     */   
/*     */   public static double fusionTemperature(double S, double P) {
/* 424 */     return (-0.0575D + 0.001710523D * Math.sqrt(S) + -2.154996E-4D * S) * S + -7.53E-4D * P;
/*     */   }
/*     */   
/*     */   public static double adiabeticTemperatureGradient(double S, double T, double P) {
/* 436 */     S -= 35.0D;
/* 437 */     return polynome(T, GRAD_A) + polynome(T, GRAD_B) * S + (polynome(T, GRAD_C) + polynome(T, GRAD_D) * S + polynome(T, GRAD_E) * P) * P;
/*     */   }
/*     */   
/*     */   public static double depth(double P, double lat) {
/* 449 */     lat = Math.sin(lat);
/* 450 */     lat *= lat;
/* 451 */     lat = 9.780318D * (1.0D + 0.0052788D * lat + 2.36E-5D * lat * lat);
/* 452 */     return polynome(P, DEPTH_C) * P / (lat + 1.092E-6D * P);
/*     */   }
/*     */   
/*     */   public static double soundVelocity(double S, double T, double P) {
/* 465 */     double CW = ((polynome(T, SOUND_C3) * P + polynome(T, SOUND_C2)) * P + polynome(T, SOUND_C1)) * P + polynome(T, SOUND_C0);
/* 468 */     double A = ((polynome(T, SOUND_A3) * P + polynome(T, SOUND_A2)) * P + polynome(T, SOUND_A1)) * P + polynome(T, SOUND_A0);
/* 471 */     double B = polynome(T, SOUND_B0) + polynome(T, SOUND_B1) * P;
/* 474 */     double D = 0.001727D + -7.9836E-6D * P;
/* 477 */     return CW + (D * S + B * Math.sqrt(S) + A) * S;
/*     */   }
/*     */   
/*     */   public static double saturationO2(double S, double T) {
/* 488 */     T += 273.15D;
/* 489 */     return Math.exp(polynome_neg(T, O2_AT) + S * polynome_neg(T, O2_AS));
/*     */   }
/*     */   
/*     */   private static double polynome(double x, double[] c) {
/* 511 */     int n = c.length - 1;
/* 512 */     double y = c[n];
/* 513 */     while (n > 0)
/* 514 */       y = y * x + c[--n]; 
/* 516 */     return y;
/*     */   }
/*     */   
/*     */   private static double polynome_neg(double x, double[] c) {
/* 537 */     int n = c.length - 1;
/* 538 */     double y = c[n];
/* 539 */     while (n > 0)
/* 540 */       y = y / x + c[--n]; 
/* 542 */     return y;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\nature\SeaWater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */