/*     */ package org.apache.commons.math3.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ 
/*     */ class FastMathCalc {
/*     */   private static final long HEX_40000000 = 1073741824L;
/*     */   
/*  36 */   private static final double[] FACT = new double[] { 
/*  36 */       1.0D, 1.0D, 2.0D, 6.0D, 24.0D, 120.0D, 720.0D, 5040.0D, 40320.0D, 362880.0D, 
/*  36 */       3628800.0D, 3.99168E7D, 4.790016E8D, 6.2270208E9D, 8.71782912E10D, 1.307674368E12D, 2.0922789888E13D, 3.55687428096E14D, 6.402373705728E15D, 1.21645100408832E17D };
/*     */   
/*  61 */   private static final double[][] LN_SPLIT_COEF = new double[][] { 
/*  61 */       { 2.0D, 0.0D }, { 0.6666666269302368D, 3.9736429850260626E-8D }, { 0.3999999761581421D, 2.3841857910019882E-8D }, { 0.2857142686843872D, 1.7029898543501842E-8D }, { 0.2222222089767456D, 1.3245471311735498E-8D }, { 0.1818181574344635D, 2.4384203044354907E-8D }, { 0.1538461446762085D, 9.140260083262505E-9D }, { 0.13333332538604736D, 9.220590270857665E-9D }, { 0.11764700710773468D, 1.2393345855018391E-8D }, { 0.10526403784751892D, 8.251545029714408E-9D }, 
/*  61 */       { 0.0952233225107193D, 1.2675934823758863E-8D }, { 0.08713622391223907D, 1.1430250008909141E-8D }, { 0.07842259109020233D, 2.404307984052299E-9D }, { 0.08371849358081818D, 1.176342548272881E-8D }, { 0.030589580535888672D, 1.2958646899018938E-9D }, { 0.14982303977012634D, 1.225743062930824E-8D } };
/*     */   
/*     */   private static final String TABLE_START_DECL = "    {";
/*     */   
/*     */   private static final String TABLE_END_DECL = "    };";
/*     */   
/*     */   private static void buildSinCosTables(double[] SINE_TABLE_A, double[] SINE_TABLE_B, double[] COSINE_TABLE_A, double[] COSINE_TABLE_B, int SINE_TABLE_LEN, double[] TANGENT_TABLE_A, double[] TANGENT_TABLE_B) {
/* 105 */     double[] result = new double[2];
/*     */     int i;
/* 108 */     for (i = 0; i < 7; i++) {
/* 109 */       double x = i / 8.0D;
/* 111 */       slowSin(x, result);
/* 112 */       SINE_TABLE_A[i] = result[0];
/* 113 */       SINE_TABLE_B[i] = result[1];
/* 115 */       slowCos(x, result);
/* 116 */       COSINE_TABLE_A[i] = result[0];
/* 117 */       COSINE_TABLE_B[i] = result[1];
/*     */     } 
/* 121 */     for (i = 7; i < SINE_TABLE_LEN; i++) {
/* 122 */       double[] xs = new double[2];
/* 123 */       double[] ys = new double[2];
/* 124 */       double[] as = new double[2];
/* 125 */       double[] bs = new double[2];
/* 126 */       double[] temps = new double[2];
/* 128 */       if ((i & 0x1) == 0) {
/* 130 */         xs[0] = SINE_TABLE_A[i / 2];
/* 131 */         xs[1] = SINE_TABLE_B[i / 2];
/* 132 */         ys[0] = COSINE_TABLE_A[i / 2];
/* 133 */         ys[1] = COSINE_TABLE_B[i / 2];
/* 136 */         splitMult(xs, ys, result);
/* 137 */         SINE_TABLE_A[i] = result[0] * 2.0D;
/* 138 */         SINE_TABLE_B[i] = result[1] * 2.0D;
/* 141 */         splitMult(ys, ys, as);
/* 142 */         splitMult(xs, xs, temps);
/* 143 */         temps[0] = -temps[0];
/* 144 */         temps[1] = -temps[1];
/* 145 */         splitAdd(as, temps, result);
/* 146 */         COSINE_TABLE_A[i] = result[0];
/* 147 */         COSINE_TABLE_B[i] = result[1];
/*     */       } else {
/* 149 */         xs[0] = SINE_TABLE_A[i / 2];
/* 150 */         xs[1] = SINE_TABLE_B[i / 2];
/* 151 */         ys[0] = COSINE_TABLE_A[i / 2];
/* 152 */         ys[1] = COSINE_TABLE_B[i / 2];
/* 153 */         as[0] = SINE_TABLE_A[i / 2 + 1];
/* 154 */         as[1] = SINE_TABLE_B[i / 2 + 1];
/* 155 */         bs[0] = COSINE_TABLE_A[i / 2 + 1];
/* 156 */         bs[1] = COSINE_TABLE_B[i / 2 + 1];
/* 159 */         splitMult(xs, bs, temps);
/* 160 */         splitMult(ys, as, result);
/* 161 */         splitAdd(result, temps, result);
/* 162 */         SINE_TABLE_A[i] = result[0];
/* 163 */         SINE_TABLE_B[i] = result[1];
/* 166 */         splitMult(ys, bs, result);
/* 167 */         splitMult(xs, as, temps);
/* 168 */         temps[0] = -temps[0];
/* 169 */         temps[1] = -temps[1];
/* 170 */         splitAdd(result, temps, result);
/* 171 */         COSINE_TABLE_A[i] = result[0];
/* 172 */         COSINE_TABLE_B[i] = result[1];
/*     */       } 
/*     */     } 
/* 177 */     for (i = 0; i < SINE_TABLE_LEN; i++) {
/* 178 */       double[] xs = new double[2];
/* 179 */       double[] ys = new double[2];
/* 180 */       double[] as = new double[2];
/* 182 */       as[0] = COSINE_TABLE_A[i];
/* 183 */       as[1] = COSINE_TABLE_B[i];
/* 185 */       splitReciprocal(as, ys);
/* 187 */       xs[0] = SINE_TABLE_A[i];
/* 188 */       xs[1] = SINE_TABLE_B[i];
/* 190 */       splitMult(xs, ys, as);
/* 192 */       TANGENT_TABLE_A[i] = as[0];
/* 193 */       TANGENT_TABLE_B[i] = as[1];
/*     */     } 
/*     */   }
/*     */   
/*     */   static double slowCos(double x, double[] result) {
/* 208 */     double[] xs = new double[2];
/* 209 */     double[] ys = new double[2];
/* 210 */     double[] facts = new double[2];
/* 211 */     double[] as = new double[2];
/* 212 */     split(x, xs);
/* 213 */     ys[1] = 0.0D;
/* 213 */     ys[0] = 0.0D;
/* 215 */     for (int i = FACT.length - 1; i >= 0; i--) {
/* 216 */       splitMult(xs, ys, as);
/* 217 */       ys[0] = as[0];
/* 217 */       ys[1] = as[1];
/* 219 */       if ((i & 0x1) == 0) {
/* 223 */         split(FACT[i], as);
/* 224 */         splitReciprocal(as, facts);
/* 226 */         if ((i & 0x2) != 0) {
/* 227 */           facts[0] = -facts[0];
/* 228 */           facts[1] = -facts[1];
/*     */         } 
/* 231 */         splitAdd(ys, facts, as);
/* 232 */         ys[0] = as[0];
/* 232 */         ys[1] = as[1];
/*     */       } 
/*     */     } 
/* 235 */     if (result != null) {
/* 236 */       result[0] = ys[0];
/* 237 */       result[1] = ys[1];
/*     */     } 
/* 240 */     return ys[0] + ys[1];
/*     */   }
/*     */   
/*     */   static double slowSin(double x, double[] result) {
/* 252 */     double[] xs = new double[2];
/* 253 */     double[] ys = new double[2];
/* 254 */     double[] facts = new double[2];
/* 255 */     double[] as = new double[2];
/* 256 */     split(x, xs);
/* 257 */     ys[1] = 0.0D;
/* 257 */     ys[0] = 0.0D;
/* 259 */     for (int i = FACT.length - 1; i >= 0; i--) {
/* 260 */       splitMult(xs, ys, as);
/* 261 */       ys[0] = as[0];
/* 261 */       ys[1] = as[1];
/* 263 */       if ((i & 0x1) != 0) {
/* 267 */         split(FACT[i], as);
/* 268 */         splitReciprocal(as, facts);
/* 270 */         if ((i & 0x2) != 0) {
/* 271 */           facts[0] = -facts[0];
/* 272 */           facts[1] = -facts[1];
/*     */         } 
/* 275 */         splitAdd(ys, facts, as);
/* 276 */         ys[0] = as[0];
/* 276 */         ys[1] = as[1];
/*     */       } 
/*     */     } 
/* 279 */     if (result != null) {
/* 280 */       result[0] = ys[0];
/* 281 */       result[1] = ys[1];
/*     */     } 
/* 284 */     return ys[0] + ys[1];
/*     */   }
/*     */   
/*     */   static double slowexp(double x, double[] result) {
/* 296 */     double[] xs = new double[2];
/* 297 */     double[] ys = new double[2];
/* 298 */     double[] facts = new double[2];
/* 299 */     double[] as = new double[2];
/* 300 */     split(x, xs);
/* 301 */     ys[1] = 0.0D;
/* 301 */     ys[0] = 0.0D;
/* 303 */     for (int i = FACT.length - 1; i >= 0; i--) {
/* 304 */       splitMult(xs, ys, as);
/* 305 */       ys[0] = as[0];
/* 306 */       ys[1] = as[1];
/* 308 */       split(FACT[i], as);
/* 309 */       splitReciprocal(as, facts);
/* 311 */       splitAdd(ys, facts, as);
/* 312 */       ys[0] = as[0];
/* 313 */       ys[1] = as[1];
/*     */     } 
/* 316 */     if (result != null) {
/* 317 */       result[0] = ys[0];
/* 318 */       result[1] = ys[1];
/*     */     } 
/* 321 */     return ys[0] + ys[1];
/*     */   }
/*     */   
/*     */   private static void split(double d, double[] split) {
/* 330 */     if (d < 8.0E298D && d > -8.0E298D) {
/* 331 */       double a = d * 1.073741824E9D;
/* 332 */       split[0] = d + a - a;
/* 333 */       split[1] = d - split[0];
/*     */     } else {
/* 335 */       double a = d * 9.313225746154785E-10D;
/* 336 */       split[0] = (d + a - d) * 1.073741824E9D;
/* 337 */       split[1] = d - split[0];
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void resplit(double[] a) {
/* 346 */     double c = a[0] + a[1];
/* 347 */     double d = -(c - a[0] - a[1]);
/* 349 */     if (c < 8.0E298D && c > -8.0E298D) {
/* 350 */       double z = c * 1.073741824E9D;
/* 351 */       a[0] = c + z - z;
/* 352 */       a[1] = c - a[0] + d;
/*     */     } else {
/* 354 */       double z = c * 9.313225746154785E-10D;
/* 355 */       a[0] = (c + z - c) * 1.073741824E9D;
/* 356 */       a[1] = c - a[0] + d;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void splitMult(double[] a, double[] b, double[] ans) {
/* 366 */     ans[0] = a[0] * b[0];
/* 367 */     ans[1] = a[0] * b[1] + a[1] * b[0] + a[1] * b[1];
/* 370 */     resplit(ans);
/*     */   }
/*     */   
/*     */   private static void splitAdd(double[] a, double[] b, double[] ans) {
/* 379 */     ans[0] = a[0] + b[0];
/* 380 */     ans[1] = a[1] + b[1];
/* 382 */     resplit(ans);
/*     */   }
/*     */   
/*     */   static void splitReciprocal(double[] in, double[] result) {
/* 404 */     double b = 2.384185791015625E-7D;
/* 405 */     double a = 0.9999997615814209D;
/* 407 */     if (in[0] == 0.0D) {
/* 408 */       in[0] = in[1];
/* 409 */       in[1] = 0.0D;
/*     */     } 
/* 412 */     result[0] = 0.9999997615814209D / in[0];
/* 413 */     result[1] = (2.384185791015625E-7D * in[0] - 0.9999997615814209D * in[1]) / (in[0] * in[0] + in[0] * in[1]);
/* 415 */     if (result[1] != result[1])
/* 416 */       result[1] = 0.0D; 
/* 420 */     resplit(result);
/* 422 */     for (int i = 0; i < 2; i++) {
/* 424 */       double err = 1.0D - result[0] * in[0] - result[0] * in[1] - result[1] * in[0] - result[1] * in[1];
/* 427 */       err *= result[0] + result[1];
/* 429 */       result[1] = result[1] + err;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void quadMult(double[] a, double[] b, double[] result) {
/* 439 */     double[] xs = new double[2];
/* 440 */     double[] ys = new double[2];
/* 441 */     double[] zs = new double[2];
/* 444 */     split(a[0], xs);
/* 445 */     split(b[0], ys);
/* 446 */     splitMult(xs, ys, zs);
/* 448 */     result[0] = zs[0];
/* 449 */     result[1] = zs[1];
/* 452 */     split(b[1], ys);
/* 453 */     splitMult(xs, ys, zs);
/* 455 */     double tmp = result[0] + zs[0];
/* 456 */     result[1] = result[1] - tmp - result[0] - zs[0];
/* 457 */     result[0] = tmp;
/* 458 */     tmp = result[0] + zs[1];
/* 459 */     result[1] = result[1] - tmp - result[0] - zs[1];
/* 460 */     result[0] = tmp;
/* 463 */     split(a[1], xs);
/* 464 */     split(b[0], ys);
/* 465 */     splitMult(xs, ys, zs);
/* 467 */     tmp = result[0] + zs[0];
/* 468 */     result[1] = result[1] - tmp - result[0] - zs[0];
/* 469 */     result[0] = tmp;
/* 470 */     tmp = result[0] + zs[1];
/* 471 */     result[1] = result[1] - tmp - result[0] - zs[1];
/* 472 */     result[0] = tmp;
/* 475 */     split(a[1], xs);
/* 476 */     split(b[1], ys);
/* 477 */     splitMult(xs, ys, zs);
/* 479 */     tmp = result[0] + zs[0];
/* 480 */     result[1] = result[1] - tmp - result[0] - zs[0];
/* 481 */     result[0] = tmp;
/* 482 */     tmp = result[0] + zs[1];
/* 483 */     result[1] = result[1] - tmp - result[0] - zs[1];
/* 484 */     result[0] = tmp;
/*     */   }
/*     */   
/*     */   static double expint(int p, double[] result) {
/* 494 */     double[] xs = new double[2];
/* 495 */     double[] as = new double[2];
/* 496 */     double[] ys = new double[2];
/* 505 */     xs[0] = Math.E;
/* 506 */     xs[1] = 1.4456468917292502E-16D;
/* 508 */     split(1.0D, ys);
/* 510 */     while (p > 0) {
/* 511 */       if ((p & 0x1) != 0) {
/* 512 */         quadMult(ys, xs, as);
/* 513 */         ys[0] = as[0];
/* 513 */         ys[1] = as[1];
/*     */       } 
/* 516 */       quadMult(xs, xs, as);
/* 517 */       xs[0] = as[0];
/* 517 */       xs[1] = as[1];
/* 519 */       p >>= 1;
/*     */     } 
/* 522 */     if (result != null) {
/* 523 */       result[0] = ys[0];
/* 524 */       result[1] = ys[1];
/* 526 */       resplit(result);
/*     */     } 
/* 529 */     return ys[0] + ys[1];
/*     */   }
/*     */   
/*     */   static double[] slowLog(double xi) {
/* 551 */     double[] x = new double[2];
/* 552 */     double[] x2 = new double[2];
/* 553 */     double[] y = new double[2];
/* 554 */     double[] a = new double[2];
/* 556 */     split(xi, x);
/* 559 */     x[0] = x[0] + 1.0D;
/* 560 */     resplit(x);
/* 561 */     splitReciprocal(x, a);
/* 562 */     x[0] = x[0] - 2.0D;
/* 563 */     resplit(x);
/* 564 */     splitMult(x, a, y);
/* 565 */     x[0] = y[0];
/* 566 */     x[1] = y[1];
/* 569 */     splitMult(x, x, x2);
/* 575 */     y[0] = LN_SPLIT_COEF[LN_SPLIT_COEF.length - 1][0];
/* 576 */     y[1] = LN_SPLIT_COEF[LN_SPLIT_COEF.length - 1][1];
/* 578 */     for (int i = LN_SPLIT_COEF.length - 2; i >= 0; i--) {
/* 579 */       splitMult(y, x2, a);
/* 580 */       y[0] = a[0];
/* 581 */       y[1] = a[1];
/* 582 */       splitAdd(y, LN_SPLIT_COEF[i], a);
/* 583 */       y[0] = a[0];
/* 584 */       y[1] = a[1];
/*     */     } 
/* 587 */     splitMult(y, x, a);
/* 588 */     y[0] = a[0];
/* 589 */     y[1] = a[1];
/* 591 */     return y;
/*     */   }
/*     */   
/*     */   static void printarray(PrintStream out, String name, int expectedLen, double[][] array2d) {
/* 603 */     out.println(name);
/* 604 */     checkLen(expectedLen, array2d.length);
/* 605 */     out.println("    { ");
/* 606 */     int i = 0;
/* 607 */     for (double[] array : array2d) {
/* 608 */       out.print("        {");
/* 609 */       for (double d : array) {
/* 610 */         out.printf("%-25.25s", new Object[] { format(d) });
/*     */       } 
/* 612 */       out.println("}, // " + i++);
/*     */     } 
/* 614 */     out.println("    };");
/*     */   }
/*     */   
/*     */   static void printarray(PrintStream out, String name, int expectedLen, double[] array) {
/* 625 */     out.println(name + "=");
/* 626 */     checkLen(expectedLen, array.length);
/* 627 */     out.println("    {");
/* 628 */     for (double d : array) {
/* 629 */       out.printf("        %s%n", new Object[] { format(d) });
/*     */     } 
/* 631 */     out.println("    };");
/*     */   }
/*     */   
/*     */   static String format(double d) {
/* 639 */     if (d != d)
/* 640 */       return "Double.NaN,"; 
/* 642 */     return ((d >= 0.0D) ? "+" : "") + Double.toString(d) + "d,";
/*     */   }
/*     */   
/*     */   private static void checkLen(int expectedLen, int actual) throws DimensionMismatchException {
/* 654 */     if (expectedLen != actual)
/* 655 */       throw new DimensionMismatchException(actual, expectedLen); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\FastMathCalc.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */