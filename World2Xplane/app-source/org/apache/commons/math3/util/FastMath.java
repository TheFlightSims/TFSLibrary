/*      */ package org.apache.commons.math3.util;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ 
/*      */ public class FastMath {
/*      */   public static final double PI = 3.141592653589793D;
/*      */   
/*      */   public static final double E = 2.718281828459045D;
/*      */   
/*      */   static final int EXP_INT_TABLE_MAX_INDEX = 750;
/*      */   
/*      */   static final int EXP_INT_TABLE_LEN = 1500;
/*      */   
/*      */   static final int LN_MANT_LEN = 1024;
/*      */   
/*      */   static final int EXP_FRAC_TABLE_LEN = 1025;
/*      */   
/*      */   private static final boolean RECOMPUTE_TABLES_AT_RUNTIME = false;
/*      */   
/*      */   private static final double LN_2_A = 0.6931470632553101D;
/*      */   
/*      */   private static final double LN_2_B = 1.1730463525082348E-7D;
/*      */   
/*  113 */   private static final double[][] LN_QUICK_COEF = new double[][] { { 1.0D, 5.669184079525E-24D }, { -0.25D, -0.25D }, { 0.3333333134651184D, 1.986821492305628E-8D }, { -0.25D, -6.663542893624021E-14D }, { 0.19999998807907104D, 1.1921056801463227E-8D }, { -0.1666666567325592D, -7.800414592973399E-9D }, { 0.1428571343421936D, 5.650007086920087E-9D }, { -0.12502530217170715D, -7.44321345601866E-11D }, { 0.11113807559013367D, 9.219544613762692E-9D } };
/*      */   
/*  126 */   private static final double[][] LN_HI_PREC_COEF = new double[][] { { 1.0D, -6.032174644509064E-23D }, { -0.25D, -0.25D }, { 0.3333333134651184D, 1.9868161777724352E-8D }, { -0.2499999701976776D, -2.957007209750105E-8D }, { 0.19999954104423523D, 1.5830993332061267E-10D }, { -0.16624879837036133D, -2.6033824355191673E-8D } };
/*      */   
/*      */   private static final int SINE_TABLE_LEN = 14;
/*      */   
/*  139 */   private static final double[] SINE_TABLE_A = new double[] { 
/*  139 */       0.0D, 0.1246747374534607D, 0.24740394949913025D, 0.366272509098053D, 0.4794255495071411D, 0.5850973129272461D, 0.6816387176513672D, 0.7675435543060303D, 0.8414709568023682D, 0.902267575263977D, 
/*  139 */       0.9489846229553223D, 0.9808930158615112D, 0.9974949359893799D, 0.9985313415527344D };
/*      */   
/*  158 */   private static final double[] SINE_TABLE_B = new double[] { 
/*  158 */       0.0D, -4.068233003401932E-9D, 9.755392680573412E-9D, 1.9987994582857286E-8D, -1.0902938113007961E-8D, -3.9986783938944604E-8D, 4.23719669792332E-8D, -5.207000323380292E-8D, 2.800552834259E-8D, 1.883511811213715E-8D, 
/*  158 */       -3.5997360512765566E-9D, 4.116164446561962E-8D, 5.0614674548127384E-8D, -1.0129027912496858E-9D };
/*      */   
/*  177 */   private static final double[] COSINE_TABLE_A = new double[] { 
/*  177 */       1.0D, 0.9921976327896118D, 0.9689123630523682D, 0.9305076599121094D, 0.8775825500488281D, 0.8109631538391113D, 0.7316888570785522D, 0.6409968137741089D, 0.5403022766113281D, 0.4311765432357788D, 
/*  177 */       0.3153223395347595D, 0.19454771280288696D, 0.07073719799518585D, -0.05417713522911072D };
/*      */   
/*  196 */   private static final double[] COSINE_TABLE_B = new double[] { 
/*  196 */       0.0D, 3.4439717236742845E-8D, 5.865827662008209E-8D, -3.7999795083850525E-8D, 1.184154459111628E-8D, -3.43338934259355E-8D, 1.1795268640216787E-8D, 4.438921624363781E-8D, 2.925681159240093E-8D, -2.6437112632041807E-8D, 
/*  196 */       2.2860509143963117E-8D, -4.813899778443457E-9D, 3.6725170580355583E-9D, 2.0217439756338078E-10D };
/*      */   
/*  216 */   private static final double[] TANGENT_TABLE_A = new double[] { 
/*  216 */       0.0D, 0.1256551444530487D, 0.25534194707870483D, 0.3936265707015991D, 0.5463024377822876D, 0.7214844226837158D, 0.9315965175628662D, 1.1974215507507324D, 1.5574076175689697D, 2.092571258544922D, 
/*  216 */       3.0095696449279785D, 5.041914939880371D, 14.101419448852539D, -18.430862426757812D };
/*      */   
/*  235 */   private static final double[] TANGENT_TABLE_B = new double[] { 
/*  235 */       0.0D, -7.877917738262007E-9D, -2.5857668567479893E-8D, 5.2240336371356666E-9D, 5.206150291559893E-8D, 1.8307188599677033E-8D, -5.7618793749770706E-8D, 7.848361555046424E-8D, 1.0708593250394448E-7D, 1.7827257129423813E-8D, 
/*  235 */       2.893485277253286E-8D, 3.1660099222737955E-7D, 4.983191803254889E-7D, -3.356118100840571E-7D };
/*      */   
/*  254 */   private static final long[] RECIP_2PI = new long[] { 
/*  254 */       2935890503282001226L, 9154082963658192752L, 3952090531849364496L, 9193070505571053912L, 7910884519577875640L, 113236205062349959L, 4577762542105553359L, -5034868814120038111L, 4208363204685324176L, 5648769086999809661L, 
/*  254 */       2819561105158720014L, -4035746434778044925L, -302932621132653753L, -2644281811660520851L, -3183605296591799669L, 6722166367014452318L, -3512299194304650054L, -7278142539171889152L };
/*      */   
/*  275 */   private static final long[] PI_O_4_BITS = new long[] { -3958705157555305932L, -4267615245585081135L };
/*      */   
/*  283 */   private static final double[] EIGHTHS = new double[] { 
/*  283 */       0.0D, 0.125D, 0.25D, 0.375D, 0.5D, 0.625D, 0.75D, 0.875D, 1.0D, 1.125D, 
/*  283 */       1.25D, 1.375D, 1.5D, 1.625D };
/*      */   
/*  286 */   private static final double[] CBRTTWO = new double[] { 0.6299605249474366D, 0.7937005259840998D, 1.0D, 1.2599210498948732D, 1.5874010519681994D };
/*      */   
/*      */   private static final long HEX_40000000 = 1073741824L;
/*      */   
/*      */   private static final long MASK_30BITS = -1073741824L;
/*      */   
/*      */   private static final double TWO_POWER_52 = 4.503599627370496E15D;
/*      */   
/*      */   private static final double F_1_3 = 0.3333333333333333D;
/*      */   
/*      */   private static final double F_1_5 = 0.2D;
/*      */   
/*      */   private static final double F_1_7 = 0.14285714285714285D;
/*      */   
/*      */   private static final double F_1_9 = 0.1111111111111111D;
/*      */   
/*      */   private static final double F_1_11 = 0.09090909090909091D;
/*      */   
/*      */   private static final double F_1_13 = 0.07692307692307693D;
/*      */   
/*      */   private static final double F_1_15 = 0.06666666666666667D;
/*      */   
/*      */   private static final double F_1_17 = 0.058823529411764705D;
/*      */   
/*      */   private static final double F_3_4 = 0.75D;
/*      */   
/*      */   private static final double F_15_16 = 0.9375D;
/*      */   
/*      */   private static final double F_13_14 = 0.9285714285714286D;
/*      */   
/*      */   private static final double F_11_12 = 0.9166666666666666D;
/*      */   
/*      */   private static final double F_9_10 = 0.9D;
/*      */   
/*      */   private static final double F_7_8 = 0.875D;
/*      */   
/*      */   private static final double F_5_6 = 0.8333333333333334D;
/*      */   
/*      */   private static final double F_1_2 = 0.5D;
/*      */   
/*      */   private static final double F_1_4 = 0.25D;
/*      */   
/*      */   private static double doubleHighPart(double d) {
/*  361 */     if (d > -2.2250738585072014E-308D && d < 2.2250738585072014E-308D)
/*  362 */       return d; 
/*  364 */     long xl = Double.doubleToLongBits(d);
/*  365 */     xl &= 0xFFFFFFFFC0000000L;
/*  366 */     return Double.longBitsToDouble(xl);
/*      */   }
/*      */   
/*      */   public static double sqrt(double a) {
/*  375 */     return Math.sqrt(a);
/*      */   }
/*      */   
/*      */   public static double cosh(double x) {
/*  383 */     if (x != x)
/*  384 */       return x; 
/*  392 */     if (x > 20.0D)
/*  393 */       return exp(x) / 2.0D; 
/*  396 */     if (x < -20.0D)
/*  397 */       return exp(-x) / 2.0D; 
/*  400 */     double[] hiPrec = new double[2];
/*  401 */     if (x < 0.0D)
/*  402 */       x = -x; 
/*  404 */     exp(x, 0.0D, hiPrec);
/*  406 */     double ya = hiPrec[0] + hiPrec[1];
/*  407 */     double yb = -(ya - hiPrec[0] - hiPrec[1]);
/*  409 */     double temp = ya * 1.073741824E9D;
/*  410 */     double yaa = ya + temp - temp;
/*  411 */     double yab = ya - yaa;
/*  414 */     double recip = 1.0D / ya;
/*  415 */     temp = recip * 1.073741824E9D;
/*  416 */     double recipa = recip + temp - temp;
/*  417 */     double recipb = recip - recipa;
/*  420 */     recipb += (1.0D - yaa * recipa - yaa * recipb - yab * recipa - yab * recipb) * recip;
/*  422 */     recipb += -yb * recip * recip;
/*  425 */     temp = ya + recipa;
/*  426 */     yb += -(temp - ya - recipa);
/*  427 */     ya = temp;
/*  428 */     temp = ya + recipb;
/*  429 */     yb += -(temp - ya - recipb);
/*  430 */     ya = temp;
/*  432 */     double result = ya + yb;
/*  433 */     result *= 0.5D;
/*  434 */     return result;
/*      */   }
/*      */   
/*      */   public static double sinh(double x) {
/*      */     double result;
/*  442 */     boolean negate = false;
/*  443 */     if (x != x)
/*  444 */       return x; 
/*  452 */     if (x > 20.0D)
/*  453 */       return exp(x) / 2.0D; 
/*  456 */     if (x < -20.0D)
/*  457 */       return -exp(-x) / 2.0D; 
/*  460 */     if (x == 0.0D)
/*  461 */       return x; 
/*  464 */     if (x < 0.0D) {
/*  465 */       x = -x;
/*  466 */       negate = true;
/*      */     } 
/*  471 */     if (x > 0.25D) {
/*  472 */       double[] hiPrec = new double[2];
/*  473 */       exp(x, 0.0D, hiPrec);
/*  475 */       double ya = hiPrec[0] + hiPrec[1];
/*  476 */       double yb = -(ya - hiPrec[0] - hiPrec[1]);
/*  478 */       double temp = ya * 1.073741824E9D;
/*  479 */       double yaa = ya + temp - temp;
/*  480 */       double yab = ya - yaa;
/*  483 */       double recip = 1.0D / ya;
/*  484 */       temp = recip * 1.073741824E9D;
/*  485 */       double recipa = recip + temp - temp;
/*  486 */       double recipb = recip - recipa;
/*  489 */       recipb += (1.0D - yaa * recipa - yaa * recipb - yab * recipa - yab * recipb) * recip;
/*  491 */       recipb += -yb * recip * recip;
/*  493 */       recipa = -recipa;
/*  494 */       recipb = -recipb;
/*  497 */       temp = ya + recipa;
/*  498 */       yb += -(temp - ya - recipa);
/*  499 */       ya = temp;
/*  500 */       temp = ya + recipb;
/*  501 */       yb += -(temp - ya - recipb);
/*  502 */       ya = temp;
/*  504 */       result = ya + yb;
/*  505 */       result *= 0.5D;
/*      */     } else {
/*  508 */       double[] hiPrec = new double[2];
/*  509 */       expm1(x, hiPrec);
/*  511 */       double ya = hiPrec[0] + hiPrec[1];
/*  512 */       double yb = -(ya - hiPrec[0] - hiPrec[1]);
/*  515 */       double denom = 1.0D + ya;
/*  516 */       double denomr = 1.0D / denom;
/*  517 */       double denomb = -(denom - 1.0D - ya) + yb;
/*  518 */       double ratio = ya * denomr;
/*  519 */       double temp = ratio * 1.073741824E9D;
/*  520 */       double ra = ratio + temp - temp;
/*  521 */       double rb = ratio - ra;
/*  523 */       temp = denom * 1.073741824E9D;
/*  524 */       double za = denom + temp - temp;
/*  525 */       double zb = denom - za;
/*  527 */       rb += (ya - za * ra - za * rb - zb * ra - zb * rb) * denomr;
/*  530 */       rb += yb * denomr;
/*  531 */       rb += -ya * denomb * denomr * denomr;
/*  534 */       temp = ya + ra;
/*  535 */       yb += -(temp - ya - ra);
/*  536 */       ya = temp;
/*  537 */       temp = ya + rb;
/*  538 */       yb += -(temp - ya - rb);
/*  539 */       ya = temp;
/*  541 */       result = ya + yb;
/*  542 */       result *= 0.5D;
/*      */     } 
/*  545 */     if (negate)
/*  546 */       result = -result; 
/*  549 */     return result;
/*      */   }
/*      */   
/*      */   public static double tanh(double x) {
/*      */     double result;
/*  557 */     boolean negate = false;
/*  559 */     if (x != x)
/*  560 */       return x; 
/*  569 */     if (x > 20.0D)
/*  570 */       return 1.0D; 
/*  573 */     if (x < -20.0D)
/*  574 */       return -1.0D; 
/*  577 */     if (x == 0.0D)
/*  578 */       return x; 
/*  581 */     if (x < 0.0D) {
/*  582 */       x = -x;
/*  583 */       negate = true;
/*      */     } 
/*  587 */     if (x >= 0.5D) {
/*  588 */       double[] hiPrec = new double[2];
/*  590 */       exp(x * 2.0D, 0.0D, hiPrec);
/*  592 */       double ya = hiPrec[0] + hiPrec[1];
/*  593 */       double yb = -(ya - hiPrec[0] - hiPrec[1]);
/*  596 */       double na = -1.0D + ya;
/*  597 */       double nb = -(na + 1.0D - ya);
/*  598 */       double temp = na + yb;
/*  599 */       nb += -(temp - na - yb);
/*  600 */       na = temp;
/*  603 */       double da = 1.0D + ya;
/*  604 */       double db = -(da - 1.0D - ya);
/*  605 */       temp = da + yb;
/*  606 */       db += -(temp - da - yb);
/*  607 */       da = temp;
/*  609 */       temp = da * 1.073741824E9D;
/*  610 */       double daa = da + temp - temp;
/*  611 */       double dab = da - daa;
/*  614 */       double ratio = na / da;
/*  615 */       temp = ratio * 1.073741824E9D;
/*  616 */       double ratioa = ratio + temp - temp;
/*  617 */       double ratiob = ratio - ratioa;
/*  620 */       ratiob += (na - daa * ratioa - daa * ratiob - dab * ratioa - dab * ratiob) / da;
/*  623 */       ratiob += nb / da;
/*  625 */       ratiob += -db * na / da / da;
/*  627 */       result = ratioa + ratiob;
/*      */     } else {
/*  630 */       double[] hiPrec = new double[2];
/*  632 */       expm1(x * 2.0D, hiPrec);
/*  634 */       double ya = hiPrec[0] + hiPrec[1];
/*  635 */       double yb = -(ya - hiPrec[0] - hiPrec[1]);
/*  638 */       double na = ya;
/*  639 */       double nb = yb;
/*  642 */       double da = 2.0D + ya;
/*  643 */       double db = -(da - 2.0D - ya);
/*  644 */       double temp = da + yb;
/*  645 */       db += -(temp - da - yb);
/*  646 */       da = temp;
/*  648 */       temp = da * 1.073741824E9D;
/*  649 */       double daa = da + temp - temp;
/*  650 */       double dab = da - daa;
/*  653 */       double ratio = na / da;
/*  654 */       temp = ratio * 1.073741824E9D;
/*  655 */       double ratioa = ratio + temp - temp;
/*  656 */       double ratiob = ratio - ratioa;
/*  659 */       ratiob += (na - daa * ratioa - daa * ratiob - dab * ratioa - dab * ratiob) / da;
/*  662 */       ratiob += nb / da;
/*  664 */       ratiob += -db * na / da / da;
/*  666 */       result = ratioa + ratiob;
/*      */     } 
/*  669 */     if (negate)
/*  670 */       result = -result; 
/*  673 */     return result;
/*      */   }
/*      */   
/*      */   public static double acosh(double a) {
/*  681 */     return log(a + sqrt(a * a - 1.0D));
/*      */   }
/*      */   
/*      */   public static double asinh(double a) {
/*      */     double absAsinh;
/*  689 */     boolean negative = false;
/*  690 */     if (a < 0.0D) {
/*  691 */       negative = true;
/*  692 */       a = -a;
/*      */     } 
/*  696 */     if (a > 0.167D) {
/*  697 */       absAsinh = log(sqrt(a * a + 1.0D) + a);
/*      */     } else {
/*  699 */       double a2 = a * a;
/*  700 */       if (a > 0.097D) {
/*  701 */         absAsinh = a * (1.0D - a2 * (0.3333333333333333D - a2 * (0.2D - a2 * (0.14285714285714285D - a2 * (0.1111111111111111D - a2 * (0.09090909090909091D - a2 * (0.07692307692307693D - a2 * (0.06666666666666667D - a2 * 0.058823529411764705D * 0.9375D) * 0.9285714285714286D) * 0.9166666666666666D) * 0.9D) * 0.875D) * 0.8333333333333334D) * 0.75D) * 0.5D);
/*  702 */       } else if (a > 0.036D) {
/*  703 */         absAsinh = a * (1.0D - a2 * (0.3333333333333333D - a2 * (0.2D - a2 * (0.14285714285714285D - a2 * (0.1111111111111111D - a2 * (0.09090909090909091D - a2 * 0.07692307692307693D * 0.9166666666666666D) * 0.9D) * 0.875D) * 0.8333333333333334D) * 0.75D) * 0.5D);
/*  704 */       } else if (a > 0.0036D) {
/*  705 */         absAsinh = a * (1.0D - a2 * (0.3333333333333333D - a2 * (0.2D - a2 * (0.14285714285714285D - a2 * 0.1111111111111111D * 0.875D) * 0.8333333333333334D) * 0.75D) * 0.5D);
/*      */       } else {
/*  707 */         absAsinh = a * (1.0D - a2 * (0.3333333333333333D - a2 * 0.2D * 0.75D) * 0.5D);
/*      */       } 
/*      */     } 
/*  711 */     return negative ? -absAsinh : absAsinh;
/*      */   }
/*      */   
/*      */   public static double atanh(double a) {
/*      */     double absAtanh;
/*  719 */     boolean negative = false;
/*  720 */     if (a < 0.0D) {
/*  721 */       negative = true;
/*  722 */       a = -a;
/*      */     } 
/*  726 */     if (a > 0.15D) {
/*  727 */       absAtanh = 0.5D * log((1.0D + a) / (1.0D - a));
/*      */     } else {
/*  729 */       double a2 = a * a;
/*  730 */       if (a > 0.087D) {
/*  731 */         absAtanh = a * (1.0D + a2 * (0.3333333333333333D + a2 * (0.2D + a2 * (0.14285714285714285D + a2 * (0.1111111111111111D + a2 * (0.09090909090909091D + a2 * (0.07692307692307693D + a2 * (0.06666666666666667D + a2 * 0.058823529411764705D))))))));
/*  732 */       } else if (a > 0.031D) {
/*  733 */         absAtanh = a * (1.0D + a2 * (0.3333333333333333D + a2 * (0.2D + a2 * (0.14285714285714285D + a2 * (0.1111111111111111D + a2 * (0.09090909090909091D + a2 * 0.07692307692307693D))))));
/*  734 */       } else if (a > 0.003D) {
/*  735 */         absAtanh = a * (1.0D + a2 * (0.3333333333333333D + a2 * (0.2D + a2 * (0.14285714285714285D + a2 * 0.1111111111111111D))));
/*      */       } else {
/*  737 */         absAtanh = a * (1.0D + a2 * (0.3333333333333333D + a2 * 0.2D));
/*      */       } 
/*      */     } 
/*  741 */     return negative ? -absAtanh : absAtanh;
/*      */   }
/*      */   
/*      */   public static double signum(double a) {
/*  750 */     return (a < 0.0D) ? -1.0D : ((a > 0.0D) ? 1.0D : a);
/*      */   }
/*      */   
/*      */   public static float signum(float a) {
/*  759 */     return (a < 0.0F) ? -1.0F : ((a > 0.0F) ? 1.0F : a);
/*      */   }
/*      */   
/*      */   public static double nextUp(double a) {
/*  767 */     return nextAfter(a, Double.POSITIVE_INFINITY);
/*      */   }
/*      */   
/*      */   public static float nextUp(float a) {
/*  775 */     return nextAfter(a, Double.POSITIVE_INFINITY);
/*      */   }
/*      */   
/*      */   public static double random() {
/*  783 */     return Math.random();
/*      */   }
/*      */   
/*      */   public static double exp(double x) {
/*  807 */     return exp(x, 0.0D, null);
/*      */   }
/*      */   
/*      */   private static double exp(double x, double extra, double[] hiPrec) {
/*      */     double intPartA, intPartB;
/*      */     int intVal;
/*      */     double result;
/*  826 */     if (x < 0.0D) {
/*  827 */       intVal = (int)-x;
/*  829 */       if (intVal > 746) {
/*  830 */         if (hiPrec != null) {
/*  831 */           hiPrec[0] = 0.0D;
/*  832 */           hiPrec[1] = 0.0D;
/*      */         } 
/*  834 */         return 0.0D;
/*      */       } 
/*  837 */       if (intVal > 709) {
/*  839 */         double d = exp(x + 40.19140625D, extra, hiPrec) / 2.8504009514401178E17D;
/*  840 */         if (hiPrec != null) {
/*  841 */           hiPrec[0] = hiPrec[0] / 2.8504009514401178E17D;
/*  842 */           hiPrec[1] = hiPrec[1] / 2.8504009514401178E17D;
/*      */         } 
/*  844 */         return d;
/*      */       } 
/*  847 */       if (intVal == 709) {
/*  849 */         double d = exp(x + 1.494140625D, extra, hiPrec) / 4.455505956692757D;
/*  850 */         if (hiPrec != null) {
/*  851 */           hiPrec[0] = hiPrec[0] / 4.455505956692757D;
/*  852 */           hiPrec[1] = hiPrec[1] / 4.455505956692757D;
/*      */         } 
/*  854 */         return d;
/*      */       } 
/*  857 */       intVal++;
/*  859 */       intPartA = ExpIntTable.EXP_INT_TABLE_A[750 - intVal];
/*  860 */       intPartB = ExpIntTable.EXP_INT_TABLE_B[750 - intVal];
/*  862 */       intVal = -intVal;
/*      */     } else {
/*  864 */       intVal = (int)x;
/*  866 */       if (intVal > 709) {
/*  867 */         if (hiPrec != null) {
/*  868 */           hiPrec[0] = Double.POSITIVE_INFINITY;
/*  869 */           hiPrec[1] = 0.0D;
/*      */         } 
/*  871 */         return Double.POSITIVE_INFINITY;
/*      */       } 
/*  874 */       intPartA = ExpIntTable.EXP_INT_TABLE_A[750 + intVal];
/*  875 */       intPartB = ExpIntTable.EXP_INT_TABLE_B[750 + intVal];
/*      */     } 
/*  882 */     int intFrac = (int)((x - intVal) * 1024.0D);
/*  883 */     double fracPartA = ExpFracTable.EXP_FRAC_TABLE_A[intFrac];
/*  884 */     double fracPartB = ExpFracTable.EXP_FRAC_TABLE_B[intFrac];
/*  890 */     double epsilon = x - intVal + intFrac / 1024.0D;
/*  899 */     double z = 0.04168701738764507D;
/*  900 */     z = z * epsilon + 0.1666666505023083D;
/*  901 */     z = z * epsilon + 0.5000000000042687D;
/*  902 */     z = z * epsilon + 1.0D;
/*  903 */     z = z * epsilon + -3.940510424527919E-20D;
/*  910 */     double tempA = intPartA * fracPartA;
/*  911 */     double tempB = intPartA * fracPartB + intPartB * fracPartA + intPartB * fracPartB;
/*  917 */     double tempC = tempB + tempA;
/*  919 */     if (extra != 0.0D) {
/*  920 */       result = tempC * extra * z + tempC * extra + tempC * z + tempB + tempA;
/*      */     } else {
/*  922 */       result = tempC * z + tempB + tempA;
/*      */     } 
/*  925 */     if (hiPrec != null) {
/*  927 */       hiPrec[0] = tempA;
/*  928 */       hiPrec[1] = tempC * extra * z + tempC * extra + tempC * z + tempB;
/*      */     } 
/*  931 */     return result;
/*      */   }
/*      */   
/*      */   public static double expm1(double x) {
/*  939 */     return expm1(x, null);
/*      */   }
/*      */   
/*      */   private static double expm1(double x, double[] hiPrecOut) {
/*  948 */     if (x != x || x == 0.0D)
/*  949 */       return x; 
/*  952 */     if (x <= -1.0D || x >= 1.0D) {
/*  955 */       double[] hiPrec = new double[2];
/*  956 */       exp(x, 0.0D, hiPrec);
/*  957 */       if (x > 0.0D)
/*  958 */         return -1.0D + hiPrec[0] + hiPrec[1]; 
/*  960 */       double ra = -1.0D + hiPrec[0];
/*  961 */       double rb = -(ra + 1.0D - hiPrec[0]);
/*  962 */       rb += hiPrec[1];
/*  963 */       return ra + rb;
/*      */     } 
/*  970 */     boolean negative = false;
/*  972 */     if (x < 0.0D) {
/*  973 */       x = -x;
/*  974 */       negative = true;
/*      */     } 
/*  978 */     int intFrac = (int)(x * 1024.0D);
/*  979 */     double tempA = ExpFracTable.EXP_FRAC_TABLE_A[intFrac] - 1.0D;
/*  980 */     double tempB = ExpFracTable.EXP_FRAC_TABLE_B[intFrac];
/*  982 */     double d1 = tempA + tempB;
/*  983 */     tempB = -(d1 - tempA - tempB);
/*  984 */     tempA = d1;
/*  986 */     d1 = tempA * 1.073741824E9D;
/*  987 */     double baseA = tempA + d1 - d1;
/*  988 */     double baseB = tempB + tempA - baseA;
/*  990 */     double epsilon = x - intFrac / 1024.0D;
/*  995 */     double zb = 0.008336750013465571D;
/*  996 */     zb = zb * epsilon + 0.041666663879186654D;
/*  997 */     zb = zb * epsilon + 0.16666666666745392D;
/*  998 */     zb = zb * epsilon + 0.49999999999999994D;
/*  999 */     zb *= epsilon;
/* 1000 */     zb *= epsilon;
/* 1002 */     double za = epsilon;
/* 1003 */     double temp = za + zb;
/* 1004 */     zb = -(temp - za - zb);
/* 1005 */     za = temp;
/* 1007 */     temp = za * 1.073741824E9D;
/* 1008 */     temp = za + temp - temp;
/* 1009 */     zb += za - temp;
/* 1010 */     za = temp;
/* 1013 */     double ya = za * baseA;
/* 1015 */     temp = ya + za * baseB;
/* 1016 */     double yb = -(temp - ya - za * baseB);
/* 1017 */     ya = temp;
/* 1019 */     temp = ya + zb * baseA;
/* 1020 */     yb += -(temp - ya - zb * baseA);
/* 1021 */     ya = temp;
/* 1023 */     temp = ya + zb * baseB;
/* 1024 */     yb += -(temp - ya - zb * baseB);
/* 1025 */     ya = temp;
/* 1029 */     temp = ya + baseA;
/* 1030 */     yb += -(temp - baseA - ya);
/* 1031 */     ya = temp;
/* 1033 */     temp = ya + za;
/* 1035 */     yb += -(temp - ya - za);
/* 1036 */     ya = temp;
/* 1038 */     temp = ya + baseB;
/* 1040 */     yb += -(temp - ya - baseB);
/* 1041 */     ya = temp;
/* 1043 */     temp = ya + zb;
/* 1045 */     yb += -(temp - ya - zb);
/* 1046 */     ya = temp;
/* 1048 */     if (negative) {
/* 1050 */       double denom = 1.0D + ya;
/* 1051 */       double denomr = 1.0D / denom;
/* 1052 */       double denomb = -(denom - 1.0D - ya) + yb;
/* 1053 */       double ratio = ya * denomr;
/* 1054 */       temp = ratio * 1.073741824E9D;
/* 1055 */       double ra = ratio + temp - temp;
/* 1056 */       double rb = ratio - ra;
/* 1058 */       temp = denom * 1.073741824E9D;
/* 1059 */       za = denom + temp - temp;
/* 1060 */       zb = denom - za;
/* 1062 */       rb += (ya - za * ra - za * rb - zb * ra - zb * rb) * denomr;
/* 1073 */       rb += yb * denomr;
/* 1074 */       rb += -ya * denomb * denomr * denomr;
/* 1077 */       ya = -ra;
/* 1078 */       yb = -rb;
/*      */     } 
/* 1081 */     if (hiPrecOut != null) {
/* 1082 */       hiPrecOut[0] = ya;
/* 1083 */       hiPrecOut[1] = yb;
/*      */     } 
/* 1086 */     return ya + yb;
/*      */   }
/*      */   
/*      */   public static double log(double x) {
/* 1096 */     return log(x, (double[])null);
/*      */   }
/*      */   
/*      */   private static double log(double x, double[] hiPrec) {
/* 1106 */     if (x == 0.0D)
/* 1107 */       return Double.NEGATIVE_INFINITY; 
/* 1109 */     long bits = Double.doubleToLongBits(x);
/* 1112 */     if (((bits & Long.MIN_VALUE) != 0L || x != x) && 
/* 1113 */       x != 0.0D) {
/* 1114 */       if (hiPrec != null)
/* 1115 */         hiPrec[0] = Double.NaN; 
/* 1118 */       return Double.NaN;
/*      */     } 
/* 1123 */     if (x == Double.POSITIVE_INFINITY) {
/* 1124 */       if (hiPrec != null)
/* 1125 */         hiPrec[0] = Double.POSITIVE_INFINITY; 
/* 1128 */       return Double.POSITIVE_INFINITY;
/*      */     } 
/* 1132 */     int exp = (int)(bits >> 52L) - 1023;
/* 1134 */     if ((bits & 0x7FF0000000000000L) == 0L) {
/* 1136 */       if (x == 0.0D) {
/* 1138 */         if (hiPrec != null)
/* 1139 */           hiPrec[0] = Double.NEGATIVE_INFINITY; 
/* 1142 */         return Double.NEGATIVE_INFINITY;
/*      */       } 
/* 1146 */       bits <<= 1L;
/* 1147 */       while ((bits & 0x10000000000000L) == 0L) {
/* 1148 */         exp--;
/* 1149 */         bits <<= 1L;
/*      */       } 
/*      */     } 
/* 1154 */     if ((exp == -1 || exp == 0) && 
/* 1155 */       x < 1.01D && x > 0.99D && hiPrec == null) {
/* 1160 */       double xa = x - 1.0D;
/* 1161 */       double xb = xa - x + 1.0D;
/* 1162 */       double tmp = xa * 1.073741824E9D;
/* 1163 */       double aa = xa + tmp - tmp;
/* 1164 */       double ab = xa - aa;
/* 1165 */       xa = aa;
/* 1166 */       xb = ab;
/* 1168 */       double ya = LN_QUICK_COEF[LN_QUICK_COEF.length - 1][0];
/* 1169 */       double yb = LN_QUICK_COEF[LN_QUICK_COEF.length - 1][1];
/* 1171 */       for (int i = LN_QUICK_COEF.length - 2; i >= 0; i--) {
/* 1173 */         aa = ya * xa;
/* 1174 */         ab = ya * xb + yb * xa + yb * xb;
/* 1176 */         tmp = aa * 1.073741824E9D;
/* 1177 */         ya = aa + tmp - tmp;
/* 1178 */         yb = aa - ya + ab;
/* 1181 */         aa = ya + LN_QUICK_COEF[i][0];
/* 1182 */         ab = yb + LN_QUICK_COEF[i][1];
/* 1184 */         tmp = aa * 1.073741824E9D;
/* 1185 */         ya = aa + tmp - tmp;
/* 1186 */         yb = aa - ya + ab;
/*      */       } 
/* 1190 */       aa = ya * xa;
/* 1191 */       ab = ya * xb + yb * xa + yb * xb;
/* 1193 */       tmp = aa * 1.073741824E9D;
/* 1194 */       ya = aa + tmp - tmp;
/* 1195 */       yb = aa - ya + ab;
/* 1197 */       return ya + yb;
/*      */     } 
/* 1202 */     double[] lnm = lnMant.LN_MANT[(int)((bits & 0xFFC0000000000L) >> 42L)];
/* 1213 */     double epsilon = (bits & 0x3FFFFFFFFFFL) / (4.503599627370496E15D + (bits & 0xFFC0000000000L));
/* 1215 */     double lnza = 0.0D;
/* 1216 */     double lnzb = 0.0D;
/* 1218 */     if (hiPrec != null) {
/* 1220 */       double tmp = epsilon * 1.073741824E9D;
/* 1221 */       double aa = epsilon + tmp - tmp;
/* 1222 */       double ab = epsilon - aa;
/* 1223 */       double xa = aa;
/* 1224 */       double xb = ab;
/* 1227 */       double numer = (bits & 0x3FFFFFFFFFFL);
/* 1228 */       double denom = 4.503599627370496E15D + (bits & 0xFFC0000000000L);
/* 1229 */       aa = numer - xa * denom - xb * denom;
/* 1230 */       xb += aa / denom;
/* 1233 */       double ya = LN_HI_PREC_COEF[LN_HI_PREC_COEF.length - 1][0];
/* 1234 */       double yb = LN_HI_PREC_COEF[LN_HI_PREC_COEF.length - 1][1];
/* 1236 */       for (int i = LN_HI_PREC_COEF.length - 2; i >= 0; i--) {
/* 1238 */         aa = ya * xa;
/* 1239 */         ab = ya * xb + yb * xa + yb * xb;
/* 1241 */         tmp = aa * 1.073741824E9D;
/* 1242 */         ya = aa + tmp - tmp;
/* 1243 */         yb = aa - ya + ab;
/* 1246 */         aa = ya + LN_HI_PREC_COEF[i][0];
/* 1247 */         ab = yb + LN_HI_PREC_COEF[i][1];
/* 1249 */         tmp = aa * 1.073741824E9D;
/* 1250 */         ya = aa + tmp - tmp;
/* 1251 */         yb = aa - ya + ab;
/*      */       } 
/* 1255 */       aa = ya * xa;
/* 1256 */       ab = ya * xb + yb * xa + yb * xb;
/* 1264 */       lnza = aa + ab;
/* 1265 */       lnzb = -(lnza - aa - ab);
/*      */     } else {
/* 1269 */       lnza = -0.16624882440418567D;
/* 1270 */       lnza = lnza * epsilon + 0.19999954120254515D;
/* 1271 */       lnza = lnza * epsilon + -0.2499999997677497D;
/* 1272 */       lnza = lnza * epsilon + 0.3333333333332802D;
/* 1273 */       lnza = lnza * epsilon + -0.5D;
/* 1274 */       lnza = lnza * epsilon + 1.0D;
/* 1275 */       lnza *= epsilon;
/*      */     } 
/* 1292 */     double a = 0.6931470632553101D * exp;
/* 1293 */     double b = 0.0D;
/* 1294 */     double c = a + lnm[0];
/* 1295 */     double d = -(c - a - lnm[0]);
/* 1296 */     a = c;
/* 1297 */     b += d;
/* 1299 */     c = a + lnza;
/* 1300 */     d = -(c - a - lnza);
/* 1301 */     a = c;
/* 1302 */     b += d;
/* 1304 */     c = a + 1.1730463525082348E-7D * exp;
/* 1305 */     d = -(c - a - 1.1730463525082348E-7D * exp);
/* 1306 */     a = c;
/* 1307 */     b += d;
/* 1309 */     c = a + lnm[1];
/* 1310 */     d = -(c - a - lnm[1]);
/* 1311 */     a = c;
/* 1312 */     b += d;
/* 1314 */     c = a + lnzb;
/* 1315 */     d = -(c - a - lnzb);
/* 1316 */     a = c;
/* 1317 */     b += d;
/* 1319 */     if (hiPrec != null) {
/* 1320 */       hiPrec[0] = a;
/* 1321 */       hiPrec[1] = b;
/*      */     } 
/* 1324 */     return a + b;
/*      */   }
/*      */   
/*      */   public static double log1p(double x) {
/* 1333 */     if (x == -1.0D)
/* 1334 */       return x / 0.0D; 
/* 1337 */     if (x > 0.0D && 1.0D / x == 0.0D)
/* 1338 */       return x; 
/* 1341 */     if (x > 1.0E-6D || x < -1.0E-6D) {
/* 1342 */       double xpa = 1.0D + x;
/* 1343 */       double xpb = -(xpa - 1.0D - x);
/* 1345 */       double[] hiPrec = new double[2];
/* 1347 */       double lores = log(xpa, hiPrec);
/* 1348 */       if (Double.isInfinite(lores))
/* 1349 */         return lores; 
/* 1354 */       double fx1 = xpb / xpa;
/* 1356 */       double epsilon = 0.5D * fx1 + 1.0D;
/* 1357 */       epsilon *= fx1;
/* 1359 */       return epsilon + hiPrec[1] + hiPrec[0];
/*      */     } 
/* 1363 */     double y = x * 0.3333333333333333D - 0.5D;
/* 1364 */     y = y * x + 1.0D;
/* 1365 */     y *= x;
/* 1367 */     return y;
/*      */   }
/*      */   
/*      */   public static double log10(double x) {
/* 1375 */     double[] hiPrec = new double[2];
/* 1377 */     double lores = log(x, hiPrec);
/* 1378 */     if (Double.isInfinite(lores))
/* 1379 */       return lores; 
/* 1382 */     double tmp = hiPrec[0] * 1.073741824E9D;
/* 1383 */     double lna = hiPrec[0] + tmp - tmp;
/* 1384 */     double lnb = hiPrec[0] - lna + hiPrec[1];
/* 1386 */     double rln10a = 0.4342944622039795D;
/* 1387 */     double rln10b = 1.9699272335463627E-8D;
/* 1389 */     return 1.9699272335463627E-8D * lnb + 1.9699272335463627E-8D * lna + 0.4342944622039795D * lnb + 0.4342944622039795D * lna;
/*      */   }
/*      */   
/*      */   public static double log(double base, double x) {
/* 1409 */     return log(x) / log(base);
/*      */   }
/*      */   
/*      */   public static double pow(double x, double y) {
/* 1420 */     double ya, yb, lns[] = new double[2];
/* 1422 */     if (y == 0.0D)
/* 1423 */       return 1.0D; 
/* 1426 */     if (x != x)
/* 1427 */       return x; 
/* 1431 */     if (x == 0.0D) {
/* 1432 */       long bits = Double.doubleToLongBits(x);
/* 1433 */       if ((bits & Long.MIN_VALUE) != 0L) {
/* 1435 */         long yi = (long)y;
/* 1437 */         if (y < 0.0D && y == yi && (yi & 0x1L) == 1L)
/* 1438 */           return Double.NEGATIVE_INFINITY; 
/* 1441 */         if (y > 0.0D && y == yi && (yi & 0x1L) == 1L)
/* 1442 */           return -0.0D; 
/*      */       } 
/* 1446 */       if (y < 0.0D)
/* 1447 */         return Double.POSITIVE_INFINITY; 
/* 1449 */       if (y > 0.0D)
/* 1450 */         return 0.0D; 
/* 1453 */       return Double.NaN;
/*      */     } 
/* 1456 */     if (x == Double.POSITIVE_INFINITY) {
/* 1457 */       if (y != y)
/* 1458 */         return y; 
/* 1460 */       if (y < 0.0D)
/* 1461 */         return 0.0D; 
/* 1463 */       return Double.POSITIVE_INFINITY;
/*      */     } 
/* 1467 */     if (y == Double.POSITIVE_INFINITY) {
/* 1468 */       if (x * x == 1.0D)
/* 1469 */         return Double.NaN; 
/* 1472 */       if (x * x > 1.0D)
/* 1473 */         return Double.POSITIVE_INFINITY; 
/* 1475 */       return 0.0D;
/*      */     } 
/* 1479 */     if (x == Double.NEGATIVE_INFINITY) {
/* 1480 */       if (y != y)
/* 1481 */         return y; 
/* 1484 */       if (y < 0.0D) {
/* 1485 */         long yi = (long)y;
/* 1486 */         if (y == yi && (yi & 0x1L) == 1L)
/* 1487 */           return -0.0D; 
/* 1490 */         return 0.0D;
/*      */       } 
/* 1493 */       if (y > 0.0D) {
/* 1494 */         long yi = (long)y;
/* 1495 */         if (y == yi && (yi & 0x1L) == 1L)
/* 1496 */           return Double.NEGATIVE_INFINITY; 
/* 1499 */         return Double.POSITIVE_INFINITY;
/*      */       } 
/*      */     } 
/* 1503 */     if (y == Double.NEGATIVE_INFINITY) {
/* 1505 */       if (x * x == 1.0D)
/* 1506 */         return Double.NaN; 
/* 1509 */       if (x * x < 1.0D)
/* 1510 */         return Double.POSITIVE_INFINITY; 
/* 1512 */       return 0.0D;
/*      */     } 
/* 1517 */     if (x < 0.0D) {
/* 1519 */       if (y >= 4.503599627370496E15D || y <= -4.503599627370496E15D)
/* 1520 */         return pow(-x, y); 
/* 1523 */       if (y == (long)y)
/* 1525 */         return (((long)y & 0x1L) == 0L) ? pow(-x, y) : -pow(-x, y); 
/* 1527 */       return Double.NaN;
/*      */     } 
/* 1534 */     if (y < 8.0E298D && y > -8.0E298D) {
/* 1535 */       double d = y * 1.073741824E9D;
/* 1536 */       ya = y + d - d;
/* 1537 */       yb = y - ya;
/*      */     } else {
/* 1539 */       double d1 = y * 9.313225746154785E-10D;
/* 1540 */       double d2 = d1 * 9.313225746154785E-10D;
/* 1541 */       ya = (d1 + d2 - d1) * 1.073741824E9D * 1.073741824E9D;
/* 1542 */       yb = y - ya;
/*      */     } 
/* 1546 */     double lores = log(x, lns);
/* 1547 */     if (Double.isInfinite(lores))
/* 1548 */       return lores; 
/* 1551 */     double lna = lns[0];
/* 1552 */     double lnb = lns[1];
/* 1555 */     double tmp1 = lna * 1.073741824E9D;
/* 1556 */     double tmp2 = lna + tmp1 - tmp1;
/* 1557 */     lnb += lna - tmp2;
/* 1558 */     lna = tmp2;
/* 1561 */     double aa = lna * ya;
/* 1562 */     double ab = lna * yb + lnb * ya + lnb * yb;
/* 1564 */     lna = aa + ab;
/* 1565 */     lnb = -(lna - aa - ab);
/* 1567 */     double z = 0.008333333333333333D;
/* 1568 */     z = z * lnb + 0.041666666666666664D;
/* 1569 */     z = z * lnb + 0.16666666666666666D;
/* 1570 */     z = z * lnb + 0.5D;
/* 1571 */     z = z * lnb + 1.0D;
/* 1572 */     z *= lnb;
/* 1574 */     double result = exp(lna, z, null);
/* 1576 */     return result;
/*      */   }
/*      */   
/*      */   private static double polySine(double x) {
/* 1588 */     double x2 = x * x;
/* 1590 */     double p = 2.7553817452272217E-6D;
/* 1591 */     p = p * x2 + -1.9841269659586505E-4D;
/* 1592 */     p = p * x2 + 0.008333333333329196D;
/* 1593 */     p = p * x2 + -0.16666666666666666D;
/* 1596 */     p = p * x2 * x;
/* 1598 */     return p;
/*      */   }
/*      */   
/*      */   private static double polyCosine(double x) {
/* 1608 */     double x2 = x * x;
/* 1610 */     double p = 2.479773539153719E-5D;
/* 1611 */     p = p * x2 + -0.0013888888689039883D;
/* 1612 */     p = p * x2 + 0.041666666666621166D;
/* 1613 */     p = p * x2 + -0.49999999999999994D;
/* 1614 */     p *= x2;
/* 1616 */     return p;
/*      */   }
/*      */   
/*      */   private static double sinQ(double xa, double xb) {
/* 1627 */     int idx = (int)(xa * 8.0D + 0.5D);
/* 1628 */     double epsilon = xa - EIGHTHS[idx];
/* 1631 */     double sintA = SINE_TABLE_A[idx];
/* 1632 */     double sintB = SINE_TABLE_B[idx];
/* 1633 */     double costA = COSINE_TABLE_A[idx];
/* 1634 */     double costB = COSINE_TABLE_B[idx];
/* 1637 */     double sinEpsA = epsilon;
/* 1638 */     double sinEpsB = polySine(epsilon);
/* 1639 */     double cosEpsA = 1.0D;
/* 1640 */     double cosEpsB = polyCosine(epsilon);
/* 1643 */     double temp = sinEpsA * 1.073741824E9D;
/* 1644 */     double temp2 = sinEpsA + temp - temp;
/* 1645 */     sinEpsB += sinEpsA - temp2;
/* 1646 */     sinEpsA = temp2;
/* 1672 */     double a = 0.0D;
/* 1673 */     double b = 0.0D;
/* 1675 */     double t = sintA;
/* 1676 */     double c = a + t;
/* 1677 */     double d = -(c - a - t);
/* 1678 */     a = c;
/* 1679 */     b += d;
/* 1681 */     t = costA * sinEpsA;
/* 1682 */     c = a + t;
/* 1683 */     d = -(c - a - t);
/* 1684 */     a = c;
/* 1685 */     b += d;
/* 1687 */     b = b + sintA * cosEpsB + costA * sinEpsB;
/* 1702 */     b = b + sintB + costB * sinEpsA + sintB * cosEpsB + costB * sinEpsB;
/* 1729 */     if (xb != 0.0D) {
/* 1730 */       t = ((costA + costB) * (1.0D + cosEpsB) - (sintA + sintB) * (sinEpsA + sinEpsB)) * xb;
/* 1732 */       c = a + t;
/* 1733 */       d = -(c - a - t);
/* 1734 */       a = c;
/* 1735 */       b += d;
/*      */     } 
/* 1738 */     double result = a + b;
/* 1740 */     return result;
/*      */   }
/*      */   
/*      */   private static double cosQ(double xa, double xb) {
/* 1751 */     double pi2a = 1.5707963267948966D;
/* 1752 */     double pi2b = 6.123233995736766E-17D;
/* 1754 */     double a = 1.5707963267948966D - xa;
/* 1755 */     double b = -(a - 1.5707963267948966D + xa);
/* 1756 */     b += 6.123233995736766E-17D - xb;
/* 1758 */     return sinQ(a, b);
/*      */   }
/*      */   
/*      */   private static double tanQ(double xa, double xb, boolean cotanFlag) {
/* 1771 */     int idx = (int)(xa * 8.0D + 0.5D);
/* 1772 */     double epsilon = xa - EIGHTHS[idx];
/* 1775 */     double sintA = SINE_TABLE_A[idx];
/* 1776 */     double sintB = SINE_TABLE_B[idx];
/* 1777 */     double costA = COSINE_TABLE_A[idx];
/* 1778 */     double costB = COSINE_TABLE_B[idx];
/* 1781 */     double sinEpsA = epsilon;
/* 1782 */     double sinEpsB = polySine(epsilon);
/* 1783 */     double cosEpsA = 1.0D;
/* 1784 */     double cosEpsB = polyCosine(epsilon);
/* 1787 */     double temp = sinEpsA * 1.073741824E9D;
/* 1788 */     double temp2 = sinEpsA + temp - temp;
/* 1789 */     sinEpsB += sinEpsA - temp2;
/* 1790 */     sinEpsA = temp2;
/* 1815 */     double a = 0.0D;
/* 1816 */     double b = 0.0D;
/* 1819 */     double t = sintA;
/* 1820 */     double c = a + t;
/* 1821 */     double d = -(c - a - t);
/* 1822 */     a = c;
/* 1823 */     b += d;
/* 1825 */     t = costA * sinEpsA;
/* 1826 */     c = a + t;
/* 1827 */     d = -(c - a - t);
/* 1828 */     a = c;
/* 1829 */     b += d;
/* 1831 */     b = b + sintA * cosEpsB + costA * sinEpsB;
/* 1832 */     b = b + sintB + costB * sinEpsA + sintB * cosEpsB + costB * sinEpsB;
/* 1834 */     double sina = a + b;
/* 1835 */     double sinb = -(sina - a - b);
/* 1839 */     a = b = c = d = 0.0D;
/* 1841 */     t = costA * 1.0D;
/* 1842 */     c = a + t;
/* 1843 */     d = -(c - a - t);
/* 1844 */     a = c;
/* 1845 */     b += d;
/* 1847 */     t = -sintA * sinEpsA;
/* 1848 */     c = a + t;
/* 1849 */     d = -(c - a - t);
/* 1850 */     a = c;
/* 1851 */     b += d;
/* 1853 */     b = b + costB * 1.0D + costA * cosEpsB + costB * cosEpsB;
/* 1854 */     b -= sintB * sinEpsA + sintA * sinEpsB + sintB * sinEpsB;
/* 1856 */     double cosa = a + b;
/* 1857 */     double cosb = -(cosa - a - b);
/* 1859 */     if (cotanFlag) {
/* 1861 */       double tmp = cosa;
/* 1861 */       cosa = sina;
/* 1861 */       sina = tmp;
/* 1862 */       tmp = cosb;
/* 1862 */       cosb = sinb;
/* 1862 */       sinb = tmp;
/*      */     } 
/* 1876 */     double est = sina / cosa;
/* 1879 */     temp = est * 1.073741824E9D;
/* 1880 */     double esta = est + temp - temp;
/* 1881 */     double estb = est - esta;
/* 1883 */     temp = cosa * 1.073741824E9D;
/* 1884 */     double cosaa = cosa + temp - temp;
/* 1885 */     double cosab = cosa - cosaa;
/* 1888 */     double err = (sina - esta * cosaa - esta * cosab - estb * cosaa - estb * cosab) / cosa;
/* 1889 */     err += sinb / cosa;
/* 1890 */     err += -sina * cosb / cosa / cosa;
/* 1892 */     if (xb != 0.0D) {
/* 1895 */       double xbadj = xb + est * est * xb;
/* 1896 */       if (cotanFlag)
/* 1897 */         xbadj = -xbadj; 
/* 1900 */       err += xbadj;
/*      */     } 
/* 1903 */     return est + err;
/*      */   }
/*      */   
/*      */   private static void reducePayneHanek(double x, double[] result) {
/* 1920 */     long shpi0, shpiA, shpiB, inbits = Double.doubleToLongBits(x);
/* 1921 */     int exponent = (int)(inbits >> 52L & 0x7FFL) - 1023;
/* 1924 */     inbits &= 0xFFFFFFFFFFFFFL;
/* 1925 */     inbits |= 0x10000000000000L;
/* 1928 */     exponent++;
/* 1929 */     inbits <<= 11L;
/* 1935 */     int idx = exponent >> 6;
/* 1936 */     int shift = exponent - (idx << 6);
/* 1938 */     if (shift != 0) {
/* 1939 */       shpi0 = (idx == 0) ? 0L : (RECIP_2PI[idx - 1] << shift);
/* 1940 */       shpi0 |= RECIP_2PI[idx] >>> 64 - shift;
/* 1941 */       shpiA = RECIP_2PI[idx] << shift | RECIP_2PI[idx + 1] >>> 64 - shift;
/* 1942 */       shpiB = RECIP_2PI[idx + 1] << shift | RECIP_2PI[idx + 2] >>> 64 - shift;
/*      */     } else {
/* 1944 */       shpi0 = (idx == 0) ? 0L : RECIP_2PI[idx - 1];
/* 1945 */       shpiA = RECIP_2PI[idx];
/* 1946 */       shpiB = RECIP_2PI[idx + 1];
/*      */     } 
/* 1950 */     long a = inbits >>> 32L;
/* 1951 */     long b = inbits & 0xFFFFFFFFL;
/* 1953 */     long c = shpiA >>> 32L;
/* 1954 */     long d = shpiA & 0xFFFFFFFFL;
/* 1956 */     long ac = a * c;
/* 1957 */     long bd = b * d;
/* 1958 */     long bc = b * c;
/* 1959 */     long ad = a * d;
/* 1961 */     long prodB = bd + (ad << 32L);
/* 1962 */     long prodA = ac + (ad >>> 32L);
/* 1964 */     boolean bita = ((bd & Long.MIN_VALUE) != 0L);
/* 1965 */     boolean bitb = ((ad & 0x80000000L) != 0L);
/* 1966 */     boolean bitsum = ((prodB & Long.MIN_VALUE) != 0L);
/* 1969 */     if ((bita && bitb) || ((bita || bitb) && !bitsum))
/* 1971 */       prodA++; 
/* 1974 */     bita = ((prodB & Long.MIN_VALUE) != 0L);
/* 1975 */     bitb = ((bc & 0x80000000L) != 0L);
/* 1977 */     prodB += bc << 32L;
/* 1978 */     prodA += bc >>> 32L;
/* 1980 */     bitsum = ((prodB & Long.MIN_VALUE) != 0L);
/* 1983 */     if ((bita && bitb) || ((bita || bitb) && !bitsum))
/* 1985 */       prodA++; 
/* 1989 */     c = shpiB >>> 32L;
/* 1990 */     d = shpiB & 0xFFFFFFFFL;
/* 1991 */     ac = a * c;
/* 1992 */     bc = b * c;
/* 1993 */     ad = a * d;
/* 1996 */     ac += bc + ad >>> 32L;
/* 1998 */     bita = ((prodB & Long.MIN_VALUE) != 0L);
/* 1999 */     bitb = ((ac & Long.MIN_VALUE) != 0L);
/* 2000 */     prodB += ac;
/* 2001 */     bitsum = ((prodB & Long.MIN_VALUE) != 0L);
/* 2003 */     if ((bita && bitb) || ((bita || bitb) && !bitsum))
/* 2005 */       prodA++; 
/* 2009 */     c = shpi0 >>> 32L;
/* 2010 */     d = shpi0 & 0xFFFFFFFFL;
/* 2012 */     bd = b * d;
/* 2013 */     bc = b * c;
/* 2014 */     ad = a * d;
/* 2016 */     prodA += bd + (bc + ad << 32L);
/* 2028 */     int intPart = (int)(prodA >>> 62L);
/* 2031 */     prodA <<= 2L;
/* 2032 */     prodA |= prodB >>> 62L;
/* 2033 */     prodB <<= 2L;
/* 2036 */     a = prodA >>> 32L;
/* 2037 */     b = prodA & 0xFFFFFFFFL;
/* 2039 */     c = PI_O_4_BITS[0] >>> 32L;
/* 2040 */     d = PI_O_4_BITS[0] & 0xFFFFFFFFL;
/* 2042 */     ac = a * c;
/* 2043 */     bd = b * d;
/* 2044 */     bc = b * c;
/* 2045 */     ad = a * d;
/* 2047 */     long prod2B = bd + (ad << 32L);
/* 2048 */     long prod2A = ac + (ad >>> 32L);
/* 2050 */     bita = ((bd & Long.MIN_VALUE) != 0L);
/* 2051 */     bitb = ((ad & 0x80000000L) != 0L);
/* 2052 */     bitsum = ((prod2B & Long.MIN_VALUE) != 0L);
/* 2055 */     if ((bita && bitb) || ((bita || bitb) && !bitsum))
/* 2057 */       prod2A++; 
/* 2060 */     bita = ((prod2B & Long.MIN_VALUE) != 0L);
/* 2061 */     bitb = ((bc & 0x80000000L) != 0L);
/* 2063 */     prod2B += bc << 32L;
/* 2064 */     prod2A += bc >>> 32L;
/* 2066 */     bitsum = ((prod2B & Long.MIN_VALUE) != 0L);
/* 2069 */     if ((bita && bitb) || ((bita || bitb) && !bitsum))
/* 2071 */       prod2A++; 
/* 2075 */     c = PI_O_4_BITS[1] >>> 32L;
/* 2076 */     d = PI_O_4_BITS[1] & 0xFFFFFFFFL;
/* 2077 */     ac = a * c;
/* 2078 */     bc = b * c;
/* 2079 */     ad = a * d;
/* 2082 */     ac += bc + ad >>> 32L;
/* 2084 */     bita = ((prod2B & Long.MIN_VALUE) != 0L);
/* 2085 */     bitb = ((ac & Long.MIN_VALUE) != 0L);
/* 2086 */     prod2B += ac;
/* 2087 */     bitsum = ((prod2B & Long.MIN_VALUE) != 0L);
/* 2089 */     if ((bita && bitb) || ((bita || bitb) && !bitsum))
/* 2091 */       prod2A++; 
/* 2095 */     a = prodB >>> 32L;
/* 2096 */     b = prodB & 0xFFFFFFFFL;
/* 2097 */     c = PI_O_4_BITS[0] >>> 32L;
/* 2098 */     d = PI_O_4_BITS[0] & 0xFFFFFFFFL;
/* 2099 */     ac = a * c;
/* 2100 */     bc = b * c;
/* 2101 */     ad = a * d;
/* 2104 */     ac += bc + ad >>> 32L;
/* 2106 */     bita = ((prod2B & Long.MIN_VALUE) != 0L);
/* 2107 */     bitb = ((ac & Long.MIN_VALUE) != 0L);
/* 2108 */     prod2B += ac;
/* 2109 */     bitsum = ((prod2B & Long.MIN_VALUE) != 0L);
/* 2111 */     if ((bita && bitb) || ((bita || bitb) && !bitsum))
/* 2113 */       prod2A++; 
/* 2117 */     double tmpA = (prod2A >>> 12L) / 4.503599627370496E15D;
/* 2118 */     double tmpB = (((prod2A & 0xFFFL) << 40L) + (prod2B >>> 24L)) / 4.503599627370496E15D / 4.503599627370496E15D;
/* 2120 */     double sumA = tmpA + tmpB;
/* 2121 */     double sumB = -(sumA - tmpA - tmpB);
/* 2124 */     result[0] = intPart;
/* 2125 */     result[1] = sumA * 2.0D;
/* 2126 */     result[2] = sumB * 2.0D;
/*      */   }
/*      */   
/*      */   public static double sin(double x) {
/* 2135 */     boolean negative = false;
/* 2136 */     int quadrant = 0;
/* 2138 */     double xb = 0.0D;
/* 2141 */     double xa = x;
/* 2142 */     if (x < 0.0D) {
/* 2143 */       negative = true;
/* 2144 */       xa = -xa;
/*      */     } 
/* 2148 */     if (xa == 0.0D) {
/* 2149 */       long bits = Double.doubleToLongBits(x);
/* 2150 */       if (bits < 0L)
/* 2151 */         return -0.0D; 
/* 2153 */       return 0.0D;
/*      */     } 
/* 2156 */     if (xa != xa || xa == Double.POSITIVE_INFINITY)
/* 2157 */       return Double.NaN; 
/* 2161 */     if (xa > 3294198.0D) {
/* 2165 */       double[] reduceResults = new double[3];
/* 2166 */       reducePayneHanek(xa, reduceResults);
/* 2167 */       quadrant = (int)reduceResults[0] & 0x3;
/* 2168 */       xa = reduceResults[1];
/* 2169 */       xb = reduceResults[2];
/* 2170 */     } else if (xa > 1.5707963267948966D) {
/*      */       double remA, remB;
/* 2175 */       int k = (int)(xa * 0.6366197723675814D);
/*      */       while (true) {
/* 2181 */         double a = -k * 1.570796251296997D;
/* 2182 */         remA = xa + a;
/* 2183 */         remB = -(remA - xa - a);
/* 2185 */         a = -k * 7.549789948768648E-8D;
/* 2186 */         double b = remA;
/* 2187 */         remA = a + b;
/* 2188 */         remB += -(remA - b - a);
/* 2190 */         a = -k * 6.123233995736766E-17D;
/* 2191 */         b = remA;
/* 2192 */         remA = a + b;
/* 2193 */         remB += -(remA - b - a);
/* 2195 */         if (remA > 0.0D)
/*      */           break; 
/* 2202 */         k--;
/*      */       } 
/* 2204 */       quadrant = k & 0x3;
/* 2205 */       xa = remA;
/* 2206 */       xb = remB;
/*      */     } 
/* 2209 */     if (negative)
/* 2210 */       quadrant ^= 0x2; 
/* 2213 */     switch (quadrant) {
/*      */       case 0:
/* 2215 */         return sinQ(xa, xb);
/*      */       case 1:
/* 2217 */         return cosQ(xa, xb);
/*      */       case 2:
/* 2219 */         return -sinQ(xa, xb);
/*      */       case 3:
/* 2221 */         return -cosQ(xa, xb);
/*      */     } 
/* 2223 */     return Double.NaN;
/*      */   }
/*      */   
/*      */   public static double cos(double x) {
/* 2233 */     int quadrant = 0;
/* 2236 */     double xa = x;
/* 2237 */     if (x < 0.0D)
/* 2238 */       xa = -xa; 
/* 2241 */     if (xa != xa || xa == Double.POSITIVE_INFINITY)
/* 2242 */       return Double.NaN; 
/* 2246 */     double xb = 0.0D;
/* 2247 */     if (xa > 3294198.0D) {
/* 2251 */       double[] reduceResults = new double[3];
/* 2252 */       reducePayneHanek(xa, reduceResults);
/* 2253 */       quadrant = (int)reduceResults[0] & 0x3;
/* 2254 */       xa = reduceResults[1];
/* 2255 */       xb = reduceResults[2];
/* 2256 */     } else if (xa > 1.5707963267948966D) {
/*      */       double remA, remB;
/* 2261 */       int k = (int)(xa * 0.6366197723675814D);
/*      */       while (true) {
/* 2267 */         double a = -k * 1.570796251296997D;
/* 2268 */         remA = xa + a;
/* 2269 */         remB = -(remA - xa - a);
/* 2271 */         a = -k * 7.549789948768648E-8D;
/* 2272 */         double b = remA;
/* 2273 */         remA = a + b;
/* 2274 */         remB += -(remA - b - a);
/* 2276 */         a = -k * 6.123233995736766E-17D;
/* 2277 */         b = remA;
/* 2278 */         remA = a + b;
/* 2279 */         remB += -(remA - b - a);
/* 2281 */         if (remA > 0.0D)
/*      */           break; 
/* 2288 */         k--;
/*      */       } 
/* 2290 */       quadrant = k & 0x3;
/* 2291 */       xa = remA;
/* 2292 */       xb = remB;
/*      */     } 
/* 2298 */     switch (quadrant) {
/*      */       case 0:
/* 2300 */         return cosQ(xa, xb);
/*      */       case 1:
/* 2302 */         return -sinQ(xa, xb);
/*      */       case 2:
/* 2304 */         return -cosQ(xa, xb);
/*      */       case 3:
/* 2306 */         return sinQ(xa, xb);
/*      */     } 
/* 2308 */     return Double.NaN;
/*      */   }
/*      */   
/*      */   public static double tan(double x) {
/*      */     int i;
/*      */     double result;
/* 2318 */     boolean negative = false;
/* 2319 */     int quadrant = 0;
/* 2322 */     double xa = x;
/* 2323 */     if (x < 0.0D) {
/* 2324 */       negative = true;
/* 2325 */       xa = -xa;
/*      */     } 
/* 2329 */     if (xa == 0.0D) {
/* 2330 */       long bits = Double.doubleToLongBits(x);
/* 2331 */       if (bits < 0L)
/* 2332 */         return -0.0D; 
/* 2334 */       return 0.0D;
/*      */     } 
/* 2337 */     if (xa != xa || xa == Double.POSITIVE_INFINITY)
/* 2338 */       return Double.NaN; 
/* 2342 */     double xb = 0.0D;
/* 2343 */     if (xa > 3294198.0D) {
/* 2347 */       double[] reduceResults = new double[3];
/* 2348 */       reducePayneHanek(xa, reduceResults);
/* 2349 */       quadrant = (int)reduceResults[0] & 0x3;
/* 2350 */       xa = reduceResults[1];
/* 2351 */       xb = reduceResults[2];
/* 2352 */     } else if (xa > 1.5707963267948966D) {
/*      */       double remA, remB;
/* 2357 */       int k = (int)(xa * 0.6366197723675814D);
/*      */       while (true) {
/* 2363 */         double a = -k * 1.570796251296997D;
/* 2364 */         remA = xa + a;
/* 2365 */         remB = -(remA - xa - a);
/* 2367 */         a = -k * 7.549789948768648E-8D;
/* 2368 */         double b = remA;
/* 2369 */         remA = a + b;
/* 2370 */         remB += -(remA - b - a);
/* 2372 */         a = -k * 6.123233995736766E-17D;
/* 2373 */         b = remA;
/* 2374 */         remA = a + b;
/* 2375 */         remB += -(remA - b - a);
/* 2377 */         if (remA > 0.0D)
/*      */           break; 
/* 2384 */         k--;
/*      */       } 
/* 2386 */       quadrant = k & 0x3;
/* 2387 */       xa = remA;
/* 2388 */       xb = remB;
/*      */     } 
/* 2391 */     if (xa > 1.5D) {
/* 2393 */       double pi2a = 1.5707963267948966D;
/* 2394 */       double pi2b = 6.123233995736766E-17D;
/* 2396 */       double a = 1.5707963267948966D - xa;
/* 2397 */       double b = -(a - 1.5707963267948966D + xa);
/* 2398 */       b += 6.123233995736766E-17D - xb;
/* 2400 */       xa = a + b;
/* 2401 */       xb = -(xa - a - b);
/* 2402 */       quadrant ^= 0x1;
/* 2403 */       i = negative ^ true;
/*      */     } 
/* 2407 */     if ((quadrant & 0x1) == 0) {
/* 2408 */       result = tanQ(xa, xb, false);
/*      */     } else {
/* 2410 */       result = -tanQ(xa, xb, true);
/*      */     } 
/* 2413 */     if (i != 0)
/* 2414 */       result = -result; 
/* 2417 */     return result;
/*      */   }
/*      */   
/*      */   public static double atan(double x) {
/* 2426 */     return atan(x, 0.0D, false);
/*      */   }
/*      */   
/*      */   private static double atan(double xa, double xb, boolean leftPlane) {
/*      */     int idx;
/* 2436 */     boolean negate = false;
/* 2439 */     if (xa == 0.0D)
/* 2440 */       return leftPlane ? copySign(Math.PI, xa) : xa; 
/* 2443 */     if (xa < 0.0D) {
/* 2445 */       xa = -xa;
/* 2446 */       xb = -xb;
/* 2447 */       negate = true;
/*      */     } 
/* 2450 */     if (xa > 1.633123935319537E16D)
/* 2451 */       return (negate ^ leftPlane) ? -1.5707963267948966D : 1.5707963267948966D; 
/* 2455 */     if (xa < 1.0D) {
/* 2456 */       idx = (int)((-1.7168146928204135D * xa * xa + 8.0D) * xa + 0.5D);
/*      */     } else {
/* 2458 */       double oneOverXa = 1.0D / xa;
/* 2459 */       idx = (int)(-((-1.7168146928204135D * oneOverXa * oneOverXa + 8.0D) * oneOverXa) + 13.07D);
/*      */     } 
/* 2461 */     double epsA = xa - TANGENT_TABLE_A[idx];
/* 2462 */     double epsB = -(epsA - xa + TANGENT_TABLE_A[idx]);
/* 2463 */     epsB += xb - TANGENT_TABLE_B[idx];
/* 2465 */     double temp = epsA + epsB;
/* 2466 */     epsB = -(temp - epsA - epsB);
/* 2467 */     epsA = temp;
/* 2470 */     temp = xa * 1.073741824E9D;
/* 2471 */     double ya = xa + temp - temp;
/* 2472 */     double yb = xb + xa - ya;
/* 2473 */     xa = ya;
/* 2474 */     xb += yb;
/* 2477 */     if (idx == 0) {
/* 2480 */       double denom = 1.0D / (1.0D + (xa + xb) * (TANGENT_TABLE_A[idx] + TANGENT_TABLE_B[idx]));
/* 2482 */       ya = epsA * denom;
/* 2483 */       yb = epsB * denom;
/*      */     } else {
/* 2485 */       double temp2 = xa * TANGENT_TABLE_A[idx];
/* 2486 */       double d1 = 1.0D + temp2;
/* 2487 */       double d2 = -(d1 - 1.0D - temp2);
/* 2488 */       temp2 = xb * TANGENT_TABLE_A[idx] + xa * TANGENT_TABLE_B[idx];
/* 2489 */       temp = d1 + temp2;
/* 2490 */       d2 += -(temp - d1 - temp2);
/* 2491 */       d1 = temp;
/* 2493 */       d2 += xb * TANGENT_TABLE_B[idx];
/* 2494 */       ya = epsA / d1;
/* 2496 */       temp = ya * 1.073741824E9D;
/* 2497 */       double yaa = ya + temp - temp;
/* 2498 */       double yab = ya - yaa;
/* 2500 */       temp = d1 * 1.073741824E9D;
/* 2501 */       double zaa = d1 + temp - temp;
/* 2502 */       double zab = d1 - zaa;
/* 2505 */       yb = (epsA - yaa * zaa - yaa * zab - yab * zaa - yab * zab) / d1;
/* 2507 */       yb += -epsA * d2 / d1 / d1;
/* 2508 */       yb += epsB / d1;
/*      */     } 
/* 2512 */     epsA = ya;
/* 2513 */     epsB = yb;
/* 2516 */     double epsA2 = epsA * epsA;
/* 2527 */     yb = 0.07490822288864472D;
/* 2528 */     yb = yb * epsA2 + -0.09088450866185192D;
/* 2529 */     yb = yb * epsA2 + 0.11111095942313305D;
/* 2530 */     yb = yb * epsA2 + -0.1428571423679182D;
/* 2531 */     yb = yb * epsA2 + 0.19999999999923582D;
/* 2532 */     yb = yb * epsA2 + -0.33333333333333287D;
/* 2533 */     yb = yb * epsA2 * epsA;
/* 2536 */     ya = epsA;
/* 2538 */     temp = ya + yb;
/* 2539 */     yb = -(temp - ya - yb);
/* 2540 */     ya = temp;
/* 2543 */     yb += epsB / (1.0D + epsA * epsA);
/* 2546 */     double za = EIGHTHS[idx] + ya;
/* 2547 */     double zb = -(za - EIGHTHS[idx] - ya);
/* 2548 */     temp = za + yb;
/* 2549 */     zb += -(temp - za - yb);
/* 2550 */     za = temp;
/* 2552 */     double result = za + zb;
/* 2553 */     double resultb = -(result - za - zb);
/* 2555 */     if (leftPlane) {
/* 2557 */       double pia = Math.PI;
/* 2558 */       double pib = 1.2246467991473532E-16D;
/* 2560 */       za = Math.PI - result;
/* 2561 */       zb = -(za - Math.PI + result);
/* 2562 */       zb += 1.2246467991473532E-16D - resultb;
/* 2564 */       result = za + zb;
/* 2565 */       resultb = -(result - za - zb);
/*      */     } 
/* 2569 */     if (negate ^ leftPlane)
/* 2570 */       result = -result; 
/* 2573 */     return result;
/*      */   }
/*      */   
/*      */   public static double atan2(double y, double x) {
/* 2583 */     if (x != x || y != y)
/* 2584 */       return Double.NaN; 
/* 2587 */     if (y == 0.0D) {
/* 2588 */       double d1 = x * y;
/* 2589 */       double invx = 1.0D / x;
/* 2590 */       double invy = 1.0D / y;
/* 2592 */       if (invx == 0.0D) {
/* 2593 */         if (x > 0.0D)
/* 2594 */           return y; 
/* 2596 */         return copySign(Math.PI, y);
/*      */       } 
/* 2600 */       if (x < 0.0D || invx < 0.0D) {
/* 2601 */         if (y < 0.0D || invy < 0.0D)
/* 2602 */           return -3.141592653589793D; 
/* 2604 */         return Math.PI;
/*      */       } 
/* 2607 */       return d1;
/*      */     } 
/* 2613 */     if (y == Double.POSITIVE_INFINITY) {
/* 2614 */       if (x == Double.POSITIVE_INFINITY)
/* 2615 */         return 0.7853981633974483D; 
/* 2618 */       if (x == Double.NEGATIVE_INFINITY)
/* 2619 */         return 2.356194490192345D; 
/* 2622 */       return 1.5707963267948966D;
/*      */     } 
/* 2625 */     if (y == Double.NEGATIVE_INFINITY) {
/* 2626 */       if (x == Double.POSITIVE_INFINITY)
/* 2627 */         return -0.7853981633974483D; 
/* 2630 */       if (x == Double.NEGATIVE_INFINITY)
/* 2631 */         return -2.356194490192345D; 
/* 2634 */       return -1.5707963267948966D;
/*      */     } 
/* 2637 */     if (x == Double.POSITIVE_INFINITY) {
/* 2638 */       if (y > 0.0D || 1.0D / y > 0.0D)
/* 2639 */         return 0.0D; 
/* 2642 */       if (y < 0.0D || 1.0D / y < 0.0D)
/* 2643 */         return -0.0D; 
/*      */     } 
/* 2647 */     if (x == Double.NEGATIVE_INFINITY) {
/* 2649 */       if (y > 0.0D || 1.0D / y > 0.0D)
/* 2650 */         return Math.PI; 
/* 2653 */       if (y < 0.0D || 1.0D / y < 0.0D)
/* 2654 */         return -3.141592653589793D; 
/*      */     } 
/* 2660 */     if (x == 0.0D) {
/* 2661 */       if (y > 0.0D || 1.0D / y > 0.0D)
/* 2662 */         return 1.5707963267948966D; 
/* 2665 */       if (y < 0.0D || 1.0D / y < 0.0D)
/* 2666 */         return -1.5707963267948966D; 
/*      */     } 
/* 2671 */     double r = y / x;
/* 2672 */     if (Double.isInfinite(r))
/* 2673 */       return atan(r, 0.0D, (x < 0.0D)); 
/* 2676 */     double ra = doubleHighPart(r);
/* 2677 */     double rb = r - ra;
/* 2680 */     double xa = doubleHighPart(x);
/* 2681 */     double xb = x - xa;
/* 2683 */     rb += (y - ra * xa - ra * xb - rb * xa - rb * xb) / x;
/* 2685 */     double temp = ra + rb;
/* 2686 */     rb = -(temp - ra - rb);
/* 2687 */     ra = temp;
/* 2689 */     if (ra == 0.0D)
/* 2690 */       ra = copySign(0.0D, y); 
/* 2694 */     double result = atan(ra, rb, (x < 0.0D));
/* 2696 */     return result;
/*      */   }
/*      */   
/*      */   public static double asin(double x) {
/* 2704 */     if (x != x)
/* 2705 */       return Double.NaN; 
/* 2708 */     if (x > 1.0D || x < -1.0D)
/* 2709 */       return Double.NaN; 
/* 2712 */     if (x == 1.0D)
/* 2713 */       return 1.5707963267948966D; 
/* 2716 */     if (x == -1.0D)
/* 2717 */       return -1.5707963267948966D; 
/* 2720 */     if (x == 0.0D)
/* 2721 */       return x; 
/* 2727 */     double temp = x * 1.073741824E9D;
/* 2728 */     double xa = x + temp - temp;
/* 2729 */     double xb = x - xa;
/* 2732 */     double ya = xa * xa;
/* 2733 */     double yb = xa * xb * 2.0D + xb * xb;
/* 2736 */     ya = -ya;
/* 2737 */     yb = -yb;
/* 2739 */     double za = 1.0D + ya;
/* 2740 */     double zb = -(za - 1.0D - ya);
/* 2742 */     temp = za + yb;
/* 2743 */     zb += -(temp - za - yb);
/* 2744 */     za = temp;
/* 2748 */     double y = sqrt(za);
/* 2749 */     temp = y * 1.073741824E9D;
/* 2750 */     ya = y + temp - temp;
/* 2751 */     yb = y - ya;
/* 2754 */     yb += (za - ya * ya - 2.0D * ya * yb - yb * yb) / 2.0D * y;
/* 2757 */     double dx = zb / 2.0D * y;
/* 2760 */     double r = x / y;
/* 2761 */     temp = r * 1.073741824E9D;
/* 2762 */     double ra = r + temp - temp;
/* 2763 */     double rb = r - ra;
/* 2765 */     rb += (x - ra * ya - ra * yb - rb * ya - rb * yb) / y;
/* 2766 */     rb += -x * dx / y / y;
/* 2768 */     temp = ra + rb;
/* 2769 */     rb = -(temp - ra - rb);
/* 2770 */     ra = temp;
/* 2772 */     return atan(ra, rb, false);
/*      */   }
/*      */   
/*      */   public static double acos(double x) {
/* 2780 */     if (x != x)
/* 2781 */       return Double.NaN; 
/* 2784 */     if (x > 1.0D || x < -1.0D)
/* 2785 */       return Double.NaN; 
/* 2788 */     if (x == -1.0D)
/* 2789 */       return Math.PI; 
/* 2792 */     if (x == 1.0D)
/* 2793 */       return 0.0D; 
/* 2796 */     if (x == 0.0D)
/* 2797 */       return 1.5707963267948966D; 
/* 2803 */     double temp = x * 1.073741824E9D;
/* 2804 */     double xa = x + temp - temp;
/* 2805 */     double xb = x - xa;
/* 2808 */     double ya = xa * xa;
/* 2809 */     double yb = xa * xb * 2.0D + xb * xb;
/* 2812 */     ya = -ya;
/* 2813 */     yb = -yb;
/* 2815 */     double za = 1.0D + ya;
/* 2816 */     double zb = -(za - 1.0D - ya);
/* 2818 */     temp = za + yb;
/* 2819 */     zb += -(temp - za - yb);
/* 2820 */     za = temp;
/* 2823 */     double y = sqrt(za);
/* 2824 */     temp = y * 1.073741824E9D;
/* 2825 */     ya = y + temp - temp;
/* 2826 */     yb = y - ya;
/* 2829 */     yb += (za - ya * ya - 2.0D * ya * yb - yb * yb) / 2.0D * y;
/* 2832 */     yb += zb / 2.0D * y;
/* 2833 */     y = ya + yb;
/* 2834 */     yb = -(y - ya - yb);
/* 2837 */     double r = y / x;
/* 2840 */     if (Double.isInfinite(r))
/* 2841 */       return 1.5707963267948966D; 
/* 2844 */     double ra = doubleHighPart(r);
/* 2845 */     double rb = r - ra;
/* 2847 */     rb += (y - ra * xa - ra * xb - rb * xa - rb * xb) / x;
/* 2848 */     rb += yb / x;
/* 2850 */     temp = ra + rb;
/* 2851 */     rb = -(temp - ra - rb);
/* 2852 */     ra = temp;
/* 2854 */     return atan(ra, rb, (x < 0.0D));
/*      */   }
/*      */   
/*      */   public static double cbrt(double x) {
/* 2863 */     long inbits = Double.doubleToLongBits(x);
/* 2864 */     int exponent = (int)(inbits >> 52L & 0x7FFL) - 1023;
/* 2865 */     boolean subnormal = false;
/* 2867 */     if (exponent == -1023) {
/* 2868 */       if (x == 0.0D)
/* 2869 */         return x; 
/* 2873 */       subnormal = true;
/* 2874 */       x *= 1.8014398509481984E16D;
/* 2875 */       inbits = Double.doubleToLongBits(x);
/* 2876 */       exponent = (int)(inbits >> 52L & 0x7FFL) - 1023;
/*      */     } 
/* 2879 */     if (exponent == 1024)
/* 2881 */       return x; 
/* 2885 */     int exp3 = exponent / 3;
/* 2888 */     double p2 = Double.longBitsToDouble(inbits & Long.MIN_VALUE | (exp3 + 1023 & 0x7FF) << 52L);
/* 2892 */     double mant = Double.longBitsToDouble(inbits & 0xFFFFFFFFFFFFFL | 0x3FF0000000000000L);
/* 2895 */     double est = -0.010714690733195933D;
/* 2896 */     est = est * mant + 0.0875862700108075D;
/* 2897 */     est = est * mant + -0.3058015757857271D;
/* 2898 */     est = est * mant + 0.7249995199969751D;
/* 2899 */     est = est * mant + 0.5039018405998233D;
/* 2901 */     est *= CBRTTWO[exponent % 3 + 2];
/* 2906 */     double xs = x / p2 * p2 * p2;
/* 2907 */     est += (xs - est * est * est) / 3.0D * est * est;
/* 2908 */     est += (xs - est * est * est) / 3.0D * est * est;
/* 2911 */     double temp = est * 1.073741824E9D;
/* 2912 */     double ya = est + temp - temp;
/* 2913 */     double yb = est - ya;
/* 2915 */     double za = ya * ya;
/* 2916 */     double zb = ya * yb * 2.0D + yb * yb;
/* 2917 */     temp = za * 1.073741824E9D;
/* 2918 */     double temp2 = za + temp - temp;
/* 2919 */     zb += za - temp2;
/* 2920 */     za = temp2;
/* 2922 */     zb = za * yb + ya * zb + zb * yb;
/* 2923 */     za *= ya;
/* 2925 */     double na = xs - za;
/* 2926 */     double nb = -(na - xs + za);
/* 2927 */     nb -= zb;
/* 2929 */     est += (na + nb) / 3.0D * est * est;
/* 2932 */     est *= p2;
/* 2934 */     if (subnormal)
/* 2935 */       est *= 3.814697265625E-6D; 
/* 2938 */     return est;
/*      */   }
/*      */   
/*      */   public static double toRadians(double x) {
/* 2948 */     if (Double.isInfinite(x) || x == 0.0D)
/* 2949 */       return x; 
/* 2953 */     double facta = 0.01745329052209854D;
/* 2954 */     double factb = 1.997844754509471E-9D;
/* 2956 */     double xa = doubleHighPart(x);
/* 2957 */     double xb = x - xa;
/* 2959 */     double result = xb * 1.997844754509471E-9D + xb * 0.01745329052209854D + xa * 1.997844754509471E-9D + xa * 0.01745329052209854D;
/* 2960 */     if (result == 0.0D)
/* 2961 */       result *= x; 
/* 2963 */     return result;
/*      */   }
/*      */   
/*      */   public static double toDegrees(double x) {
/* 2973 */     if (Double.isInfinite(x) || x == 0.0D)
/* 2974 */       return x; 
/* 2978 */     double facta = 57.2957763671875D;
/* 2979 */     double factb = 3.145894820876798E-6D;
/* 2981 */     double xa = doubleHighPart(x);
/* 2982 */     double xb = x - xa;
/* 2984 */     return xb * 3.145894820876798E-6D + xb * 57.2957763671875D + xa * 3.145894820876798E-6D + xa * 57.2957763671875D;
/*      */   }
/*      */   
/*      */   public static int abs(int x) {
/* 2993 */     return (x < 0) ? -x : x;
/*      */   }
/*      */   
/*      */   public static long abs(long x) {
/* 3002 */     return (x < 0L) ? -x : x;
/*      */   }
/*      */   
/*      */   public static float abs(float x) {
/* 3011 */     return (x < 0.0F) ? -x : ((x == 0.0F) ? 0.0F : x);
/*      */   }
/*      */   
/*      */   public static double abs(double x) {
/* 3020 */     return (x < 0.0D) ? -x : ((x == 0.0D) ? 0.0D : x);
/*      */   }
/*      */   
/*      */   public static double ulp(double x) {
/* 3029 */     if (Double.isInfinite(x))
/* 3030 */       return Double.POSITIVE_INFINITY; 
/* 3032 */     return abs(x - Double.longBitsToDouble(Double.doubleToLongBits(x) ^ 0x1L));
/*      */   }
/*      */   
/*      */   public static float ulp(float x) {
/* 3041 */     if (Float.isInfinite(x))
/* 3042 */       return Float.POSITIVE_INFINITY; 
/* 3044 */     return abs(x - Float.intBitsToFloat(Float.floatToIntBits(x) ^ 0x1));
/*      */   }
/*      */   
/*      */   public static double scalb(double d, int n) {
/* 3056 */     if (n > -1023 && n < 1024)
/* 3057 */       return d * Double.longBitsToDouble((n + 1023) << 52L); 
/* 3061 */     if (Double.isNaN(d) || Double.isInfinite(d) || d == 0.0D)
/* 3062 */       return d; 
/* 3064 */     if (n < -2098)
/* 3065 */       return (d > 0.0D) ? 0.0D : -0.0D; 
/* 3067 */     if (n > 2097)
/* 3068 */       return (d > 0.0D) ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY; 
/* 3072 */     long bits = Double.doubleToLongBits(d);
/* 3073 */     long sign = bits & Long.MIN_VALUE;
/* 3074 */     int exponent = (int)(bits >>> 52L) & 0x7FF;
/* 3075 */     long mantissa = bits & 0xFFFFFFFFFFFFFL;
/* 3078 */     int scaledExponent = exponent + n;
/* 3080 */     if (n < 0) {
/* 3082 */       if (scaledExponent > 0)
/* 3084 */         return Double.longBitsToDouble(sign | scaledExponent << 52L | mantissa); 
/* 3085 */       if (scaledExponent > -53) {
/* 3089 */         mantissa |= 0x10000000000000L;
/* 3092 */         long mostSignificantLostBit = mantissa & 1L << -scaledExponent;
/* 3093 */         mantissa >>>= 1 - scaledExponent;
/* 3094 */         if (mostSignificantLostBit != 0L)
/* 3096 */           mantissa++; 
/* 3098 */         return Double.longBitsToDouble(sign | mantissa);
/*      */       } 
/* 3102 */       return (sign == 0L) ? 0.0D : -0.0D;
/*      */     } 
/* 3106 */     if (exponent == 0) {
/* 3109 */       while (mantissa >>> 52L != 1L) {
/* 3110 */         mantissa <<= 1L;
/* 3111 */         scaledExponent--;
/*      */       } 
/* 3113 */       scaledExponent++;
/* 3114 */       mantissa &= 0xFFFFFFFFFFFFFL;
/* 3116 */       if (scaledExponent < 2047)
/* 3117 */         return Double.longBitsToDouble(sign | scaledExponent << 52L | mantissa); 
/* 3119 */       return (sign == 0L) ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
/*      */     } 
/* 3122 */     if (scaledExponent < 2047)
/* 3123 */       return Double.longBitsToDouble(sign | scaledExponent << 52L | mantissa); 
/* 3125 */     return (sign == 0L) ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
/*      */   }
/*      */   
/*      */   public static float scalb(float f, int n) {
/* 3140 */     if (n > -127 && n < 128)
/* 3141 */       return f * Float.intBitsToFloat(n + 127 << 23); 
/* 3145 */     if (Float.isNaN(f) || Float.isInfinite(f) || f == 0.0F)
/* 3146 */       return f; 
/* 3148 */     if (n < -277)
/* 3149 */       return (f > 0.0F) ? 0.0F : -0.0F; 
/* 3151 */     if (n > 276)
/* 3152 */       return (f > 0.0F) ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY; 
/* 3156 */     int bits = Float.floatToIntBits(f);
/* 3157 */     int sign = bits & Integer.MIN_VALUE;
/* 3158 */     int exponent = bits >>> 23 & 0xFF;
/* 3159 */     int mantissa = bits & 0x7FFFFF;
/* 3162 */     int scaledExponent = exponent + n;
/* 3164 */     if (n < 0) {
/* 3166 */       if (scaledExponent > 0)
/* 3168 */         return Float.intBitsToFloat(sign | scaledExponent << 23 | mantissa); 
/* 3169 */       if (scaledExponent > -24) {
/* 3173 */         mantissa |= 0x800000;
/* 3176 */         int mostSignificantLostBit = mantissa & 1 << -scaledExponent;
/* 3177 */         mantissa >>>= 1 - scaledExponent;
/* 3178 */         if (mostSignificantLostBit != 0)
/* 3180 */           mantissa++; 
/* 3182 */         return Float.intBitsToFloat(sign | mantissa);
/*      */       } 
/* 3186 */       return (sign == 0) ? 0.0F : -0.0F;
/*      */     } 
/* 3190 */     if (exponent == 0) {
/* 3193 */       while (mantissa >>> 23 != 1) {
/* 3194 */         mantissa <<= 1;
/* 3195 */         scaledExponent--;
/*      */       } 
/* 3197 */       scaledExponent++;
/* 3198 */       mantissa &= 0x7FFFFF;
/* 3200 */       if (scaledExponent < 255)
/* 3201 */         return Float.intBitsToFloat(sign | scaledExponent << 23 | mantissa); 
/* 3203 */       return (sign == 0) ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
/*      */     } 
/* 3206 */     if (scaledExponent < 255)
/* 3207 */       return Float.intBitsToFloat(sign | scaledExponent << 23 | mantissa); 
/* 3209 */     return (sign == 0) ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
/*      */   }
/*      */   
/*      */   public static double nextAfter(double d, double direction) {
/* 3249 */     if (Double.isNaN(d) || Double.isNaN(direction))
/* 3250 */       return Double.NaN; 
/* 3251 */     if (d == direction)
/* 3252 */       return direction; 
/* 3253 */     if (Double.isInfinite(d))
/* 3254 */       return (d < 0.0D) ? -1.7976931348623157E308D : Double.MAX_VALUE; 
/* 3255 */     if (d == 0.0D)
/* 3256 */       return (direction < 0.0D) ? -4.9E-324D : Double.MIN_VALUE; 
/* 3261 */     long bits = Double.doubleToLongBits(d);
/* 3262 */     long sign = bits & Long.MIN_VALUE;
/* 3263 */     if ((((direction < d) ? 1 : 0) ^ ((sign == 0L) ? 1 : 0)) != 0)
/* 3264 */       return Double.longBitsToDouble(sign | (bits & Long.MAX_VALUE) + 1L); 
/* 3266 */     return Double.longBitsToDouble(sign | (bits & Long.MAX_VALUE) - 1L);
/*      */   }
/*      */   
/*      */   public static float nextAfter(float f, double direction) {
/* 3305 */     if (Double.isNaN(f) || Double.isNaN(direction))
/* 3306 */       return Float.NaN; 
/* 3307 */     if (f == direction)
/* 3308 */       return (float)direction; 
/* 3309 */     if (Float.isInfinite(f))
/* 3310 */       return (f < 0.0F) ? -3.4028235E38F : Float.MAX_VALUE; 
/* 3311 */     if (f == 0.0F)
/* 3312 */       return (direction < 0.0D) ? -1.4E-45F : Float.MIN_VALUE; 
/* 3317 */     int bits = Float.floatToIntBits(f);
/* 3318 */     int sign = bits & Integer.MIN_VALUE;
/* 3319 */     if ((((direction < f) ? 1 : 0) ^ ((sign == 0) ? 1 : 0)) != 0)
/* 3320 */       return Float.intBitsToFloat(sign | (bits & Integer.MAX_VALUE) + 1); 
/* 3322 */     return Float.intBitsToFloat(sign | (bits & Integer.MAX_VALUE) - 1);
/*      */   }
/*      */   
/*      */   public static double floor(double x) {
/* 3334 */     if (x != x)
/* 3335 */       return x; 
/* 3338 */     if (x >= 4.503599627370496E15D || x <= -4.503599627370496E15D)
/* 3339 */       return x; 
/* 3342 */     long y = (long)x;
/* 3343 */     if (x < 0.0D && y != x)
/* 3344 */       y--; 
/* 3347 */     if (y == 0L)
/* 3348 */       return x * y; 
/* 3351 */     return y;
/*      */   }
/*      */   
/*      */   public static double ceil(double x) {
/* 3361 */     if (x != x)
/* 3362 */       return x; 
/* 3365 */     double y = floor(x);
/* 3366 */     if (y == x)
/* 3367 */       return y; 
/* 3370 */     y++;
/* 3372 */     if (y == 0.0D)
/* 3373 */       return x * y; 
/* 3376 */     return y;
/*      */   }
/*      */   
/*      */   public static double rint(double x) {
/* 3384 */     double y = floor(x);
/* 3385 */     double d = x - y;
/* 3387 */     if (d > 0.5D) {
/* 3388 */       if (y == -1.0D)
/* 3389 */         return -0.0D; 
/* 3391 */       return y + 1.0D;
/*      */     } 
/* 3393 */     if (d < 0.5D)
/* 3394 */       return y; 
/* 3398 */     long z = (long)y;
/* 3399 */     return ((z & 0x1L) == 0L) ? y : (y + 1.0D);
/*      */   }
/*      */   
/*      */   public static long round(double x) {
/* 3407 */     return (long)floor(x + 0.5D);
/*      */   }
/*      */   
/*      */   public static int round(float x) {
/* 3415 */     return (int)floor((x + 0.5F));
/*      */   }
/*      */   
/*      */   public static int min(int a, int b) {
/* 3424 */     return (a <= b) ? a : b;
/*      */   }
/*      */   
/*      */   public static long min(long a, long b) {
/* 3433 */     return (a <= b) ? a : b;
/*      */   }
/*      */   
/*      */   public static float min(float a, float b) {
/* 3442 */     if (a > b)
/* 3443 */       return b; 
/* 3445 */     if (a < b)
/* 3446 */       return a; 
/* 3449 */     if (a != b)
/* 3450 */       return Float.NaN; 
/* 3454 */     int bits = Float.floatToRawIntBits(a);
/* 3455 */     if (bits == Integer.MIN_VALUE)
/* 3456 */       return a; 
/* 3458 */     return b;
/*      */   }
/*      */   
/*      */   public static double min(double a, double b) {
/* 3467 */     if (a > b)
/* 3468 */       return b; 
/* 3470 */     if (a < b)
/* 3471 */       return a; 
/* 3474 */     if (a != b)
/* 3475 */       return Double.NaN; 
/* 3479 */     long bits = Double.doubleToRawLongBits(a);
/* 3480 */     if (bits == Long.MIN_VALUE)
/* 3481 */       return a; 
/* 3483 */     return b;
/*      */   }
/*      */   
/*      */   public static int max(int a, int b) {
/* 3492 */     return (a <= b) ? b : a;
/*      */   }
/*      */   
/*      */   public static long max(long a, long b) {
/* 3501 */     return (a <= b) ? b : a;
/*      */   }
/*      */   
/*      */   public static float max(float a, float b) {
/* 3510 */     if (a > b)
/* 3511 */       return a; 
/* 3513 */     if (a < b)
/* 3514 */       return b; 
/* 3517 */     if (a != b)
/* 3518 */       return Float.NaN; 
/* 3522 */     int bits = Float.floatToRawIntBits(a);
/* 3523 */     if (bits == Integer.MIN_VALUE)
/* 3524 */       return b; 
/* 3526 */     return a;
/*      */   }
/*      */   
/*      */   public static double max(double a, double b) {
/* 3535 */     if (a > b)
/* 3536 */       return a; 
/* 3538 */     if (a < b)
/* 3539 */       return b; 
/* 3542 */     if (a != b)
/* 3543 */       return Double.NaN; 
/* 3547 */     long bits = Double.doubleToRawLongBits(a);
/* 3548 */     if (bits == Long.MIN_VALUE)
/* 3549 */       return b; 
/* 3551 */     return a;
/*      */   }
/*      */   
/*      */   public static double hypot(double x, double y) {
/* 3569 */     if (Double.isInfinite(x) || Double.isInfinite(y))
/* 3570 */       return Double.POSITIVE_INFINITY; 
/* 3571 */     if (Double.isNaN(x) || Double.isNaN(y))
/* 3572 */       return Double.NaN; 
/* 3575 */     int expX = getExponent(x);
/* 3576 */     int expY = getExponent(y);
/* 3577 */     if (expX > expY + 27)
/* 3579 */       return abs(x); 
/* 3580 */     if (expY > expX + 27)
/* 3582 */       return abs(y); 
/* 3586 */     int middleExp = (expX + expY) / 2;
/* 3589 */     double scaledX = scalb(x, -middleExp);
/* 3590 */     double scaledY = scalb(y, -middleExp);
/* 3593 */     double scaledH = sqrt(scaledX * scaledX + scaledY * scaledY);
/* 3596 */     return scalb(scaledH, middleExp);
/*      */   }
/*      */   
/*      */   public static double IEEEremainder(double dividend, double divisor) {
/* 3624 */     return StrictMath.IEEEremainder(dividend, divisor);
/*      */   }
/*      */   
/*      */   public static double copySign(double magnitude, double sign) {
/* 3636 */     long m = Double.doubleToLongBits(magnitude);
/* 3637 */     long s = Double.doubleToLongBits(sign);
/* 3638 */     if ((m >= 0L && s >= 0L) || (m < 0L && s < 0L))
/* 3639 */       return magnitude; 
/* 3641 */     return -magnitude;
/*      */   }
/*      */   
/*      */   public static float copySign(float magnitude, float sign) {
/* 3653 */     int m = Float.floatToIntBits(magnitude);
/* 3654 */     int s = Float.floatToIntBits(sign);
/* 3655 */     if ((m >= 0 && s >= 0) || (m < 0 && s < 0))
/* 3656 */       return magnitude; 
/* 3658 */     return -magnitude;
/*      */   }
/*      */   
/*      */   public static int getExponent(double d) {
/* 3671 */     return (int)(Double.doubleToLongBits(d) >>> 52L & 0x7FFL) - 1023;
/*      */   }
/*      */   
/*      */   public static int getExponent(float f) {
/* 3684 */     return (Float.floatToIntBits(f) >>> 23 & 0xFF) - 127;
/*      */   }
/*      */   
/*      */   public static void main(String[] a) {
/* 3693 */     PrintStream out = System.out;
/* 3694 */     FastMathCalc.printarray(out, "EXP_INT_TABLE_A", 1500, ExpIntTable.EXP_INT_TABLE_A);
/* 3695 */     FastMathCalc.printarray(out, "EXP_INT_TABLE_B", 1500, ExpIntTable.EXP_INT_TABLE_B);
/* 3696 */     FastMathCalc.printarray(out, "EXP_FRAC_TABLE_A", 1025, ExpFracTable.EXP_FRAC_TABLE_A);
/* 3697 */     FastMathCalc.printarray(out, "EXP_FRAC_TABLE_B", 1025, ExpFracTable.EXP_FRAC_TABLE_B);
/* 3698 */     FastMathCalc.printarray(out, "LN_MANT", 1024, lnMant.LN_MANT);
/* 3699 */     FastMathCalc.printarray(out, "SINE_TABLE_A", 14, SINE_TABLE_A);
/* 3700 */     FastMathCalc.printarray(out, "SINE_TABLE_B", 14, SINE_TABLE_B);
/* 3701 */     FastMathCalc.printarray(out, "COSINE_TABLE_A", 14, COSINE_TABLE_A);
/* 3702 */     FastMathCalc.printarray(out, "COSINE_TABLE_B", 14, COSINE_TABLE_B);
/* 3703 */     FastMathCalc.printarray(out, "TANGENT_TABLE_A", 14, TANGENT_TABLE_A);
/* 3704 */     FastMathCalc.printarray(out, "TANGENT_TABLE_B", 14, TANGENT_TABLE_B);
/*      */   }
/*      */   
/*      */   private static class ExpIntTable {
/* 3740 */     private static final double[] EXP_INT_TABLE_A = FastMathLiteralArrays.loadExpIntA();
/*      */     
/* 3741 */     private static final double[] EXP_INT_TABLE_B = FastMathLiteralArrays.loadExpIntB();
/*      */   }
/*      */   
/*      */   private static class ExpFracTable {
/* 3773 */     private static final double[] EXP_FRAC_TABLE_A = FastMathLiteralArrays.loadExpFracA();
/*      */     
/* 3774 */     private static final double[] EXP_FRAC_TABLE_B = FastMathLiteralArrays.loadExpFracB();
/*      */   }
/*      */   
/*      */   private static class lnMant {
/* 3794 */     private static final double[][] LN_MANT = FastMathLiteralArrays.loadLnMant();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\FastMath.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */