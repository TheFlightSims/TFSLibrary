/*     */ package scala.util;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Seq;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichDouble;
/*     */ import scala.runtime.RichFloat;
/*     */ 
/*     */ public final class Sorting$ {
/*     */   public static final Sorting$ MODULE$;
/*     */   
/*     */   private Sorting$() {
/*  28 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public void quickSort(double[] a) {
/*  30 */     sort1(a, 0, a.length);
/*     */   }
/*     */   
/*     */   public <K> void quickSort(Object a, Ordering<?> evidence$1) {
/*  33 */     sort1(a, 0, scala.runtime.ScalaRunTime$.MODULE$.array_length(a), evidence$1);
/*     */   }
/*     */   
/*     */   public void quickSort(int[] a) {
/*  36 */     sort1(a, 0, a.length);
/*     */   }
/*     */   
/*     */   public void quickSort(float[] a) {
/*  39 */     sort1(a, 0, a.length);
/*     */   }
/*     */   
/*     */   public <K> void stableSort(Object a, ClassTag<?> evidence$2, Ordering evidence$3) {
/*  44 */     Ordering ordering = scala.math.Ordering$.MODULE$.apply(evidence$3);
/*  44 */     stableSort(a, 0, scala.runtime.ScalaRunTime$.MODULE$.array_length(a) - 1, evidence$2.newArray(scala.runtime.ScalaRunTime$.MODULE$.array_length(a)), (Function2<?, ?, Object>)new Sorting$$anonfun$stableSort$1(ordering), evidence$2);
/*     */   }
/*     */   
/*     */   public static class Sorting$$anonfun$stableSort$1 extends AbstractFunction2<K, K, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Ordering eta$0$1$1;
/*     */     
/*     */     public final boolean apply(Object x, Object y) {
/*  44 */       return this.eta$0$1$1.lt(x, y);
/*     */     }
/*     */     
/*     */     public Sorting$$anonfun$stableSort$1(Ordering eta$0$1$1) {}
/*     */   }
/*     */   
/*     */   public <K> void stableSort(Object a, Function2<?, ?, Object> f, ClassTag<?> evidence$4) {
/*  51 */     stableSort(a, 0, scala.runtime.ScalaRunTime$.MODULE$.array_length(a) - 1, evidence$4.newArray(scala.runtime.ScalaRunTime$.MODULE$.array_length(a)), f, evidence$4);
/*     */   }
/*     */   
/*     */   public <K> Object stableSort(Seq a, Function2<?, ?, Object> f, ClassTag<?> evidence$5) {
/*  62 */     Object ret = a.toArray(evidence$5);
/*  63 */     stableSort(ret, f, evidence$5);
/*  64 */     return ret;
/*     */   }
/*     */   
/*     */   public <K> Object stableSort(Seq<?> a, ClassTag<?> evidence$6, Ordering evidence$7) {
/*  69 */     Ordering ordering = scala.math.Ordering$.MODULE$.apply(evidence$7);
/*  69 */     return stableSort(a, (Function2<?, ?, Object>)new Sorting$$anonfun$stableSort$2(ordering), evidence$6);
/*     */   }
/*     */   
/*     */   public static class Sorting$$anonfun$stableSort$2 extends AbstractFunction2<K, K, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Ordering eta$0$2$1;
/*     */     
/*     */     public final boolean apply(Object x, Object y) {
/*  69 */       return this.eta$0$2$1.lt(x, y);
/*     */     }
/*     */     
/*     */     public Sorting$$anonfun$stableSort$2(Ordering eta$0$2$1) {}
/*     */   }
/*     */   
/*     */   public <K, M> Object stableSort(Seq<?> a, Function1 f, ClassTag<?> evidence$8, Ordering evidence$9) {
/*  79 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*  79 */     return stableSort(a, evidence$8, scala.math.Ordering$.MODULE$.apply(evidence$9).on(f));
/*     */   }
/*     */   
/*     */   private <K> void sort1(Object x, int off, int len, Ordering evidence$10) {
/*  82 */     Ordering ord = scala.math.Ordering$.MODULE$.apply(evidence$10);
/* 182 */     sort2$1(off, len, x, ord);
/*     */   }
/*     */   
/*     */   private final void swap$1(int a, int b, Object x$4) {
/*     */     Object t = scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, a);
/*     */     scala.runtime.ScalaRunTime$.MODULE$.array_update(x$4, a, scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, b));
/*     */     scala.runtime.ScalaRunTime$.MODULE$.array_update(x$4, b, t);
/*     */   }
/*     */   
/*     */   private final void vecswap$1(int _a, int _b, int n, Object x$4) {
/*     */     int a = _a;
/*     */     int b = _b;
/*     */     int i = 0;
/*     */     while (i < n) {
/*     */       swap$1(a, b, x$4);
/*     */       i++;
/*     */       a++;
/*     */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final int med3$1(int a, int b, int c, Object x$4, Ordering ord$1) {
/*     */     return ord$1.mkOrderingOps(scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, a)).$less(scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, b)) ? (ord$1.mkOrderingOps(scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, b)).$less(scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, c)) ? b : (ord$1.mkOrderingOps(scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, a)).$less(scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, c)) ? c : a)) : (ord$1.mkOrderingOps(scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, b)).$greater(scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, c)) ? b : (ord$1.mkOrderingOps(scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, a)).$greater(scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, c)) ? c : a));
/*     */   }
/*     */   
/*     */   private final void sort2$1(int off, int len, Object x$4, Ordering ord$1) {
/*     */     label71: while (true) {
/*     */       if (len < 7) {
/*     */         int i = off;
/*     */         while (i < len + off) {
/*     */           int j = i;
/*     */           while (j > off && ord$1.mkOrderingOps(scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, j - 1)).$greater(scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, j))) {
/*     */             swap$1(j, j - 1, x$4);
/*     */             j--;
/*     */           } 
/*     */           i++;
/*     */         } 
/*     */         continue;
/*     */       } 
/*     */       int m = off + (len >> 1);
/*     */       if (len > 7) {
/*     */         int l = off;
/*     */         int n = off + len - 1;
/*     */         if (len > 40) {
/*     */           int s = len / 8;
/*     */           l = med3$1(off, off + s, off + 2 * s, x$4, ord$1);
/*     */           m = med3$1(m - s, m, m + s, x$4, ord$1);
/*     */           n = med3$1(n - 2 * s, n - s, n, x$4, ord$1);
/*     */         } 
/*     */         m = med3$1(l, m, n, x$4, ord$1);
/*     */       } 
/*     */       Object v = scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, m);
/*     */       int a = off;
/*     */       int b = off;
/*     */       int c = off + len - 1;
/*     */       int d = c;
/*     */       boolean done = false;
/*     */       while (true) {
/*     */         if (done) {
/*     */           int n = off + len;
/*     */           int s = scala.math.package$.MODULE$.min(a - off, b - a);
/*     */           vecswap$1(off, b - s, s, x$4);
/*     */           s = scala.math.package$.MODULE$.min(d - c, n - d - 1);
/*     */           vecswap$1(b, n - s, s, x$4);
/*     */           if ((s = b - a) > 1)
/*     */             sort2$1(off, s, x$4, ord$1); 
/*     */           if ((s = d - c) > 1) {
/*     */             len = s;
/*     */             off = n - s;
/*     */             continue label71;
/*     */           } 
/*     */           return;
/*     */         } 
/*     */         while (b <= c && ord$1.mkOrderingOps(scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, b)).$less$eq(v)) {
/*     */           Object object;
/*     */           if (((object = scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, b)) == v) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, v) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, v) : object.equals(v))))) {
/*     */             swap$1(a, b, x$4);
/*     */             a++;
/*     */           } 
/*     */           b++;
/*     */         } 
/*     */         while (c >= b && ord$1.mkOrderingOps(scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, c)).$greater$eq(v)) {
/*     */           Object object;
/*     */           if (((object = scala.runtime.ScalaRunTime$.MODULE$.array_apply(x$4, c)) == v) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, v) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, v) : object.equals(v))))) {
/*     */             swap$1(c, d, x$4);
/*     */             d--;
/*     */           } 
/*     */           c--;
/*     */         } 
/*     */         if (b > c) {
/*     */           done = true;
/*     */           continue;
/*     */         } 
/*     */         swap$1(b, c, x$4);
/*     */         c--;
/*     */         b++;
/*     */       } 
/*     */       break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void swap$2(int a, int b, int[] x$3) {
/* 187 */     int t = x$3[a];
/* 188 */     x$3[a] = x$3[b];
/* 189 */     x$3[b] = t;
/*     */   }
/*     */   
/*     */   private final void vecswap$2(int _a, int _b, int n, int[] x$3) {
/* 192 */     int a = _a;
/* 193 */     int b = _b;
/* 194 */     int i = 0;
/* 195 */     while (i < n) {
/* 196 */       swap$2(a, b, x$3);
/* 197 */       i++;
/* 198 */       a++;
/* 199 */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final int med3$2(int a, int b, int c, int[] x$3) {
/* 203 */     return (x$3[a] < x$3[b]) ? (
/* 204 */       (x$3[b] < x$3[c]) ? b : ((x$3[a] < x$3[c]) ? c : a)) : (
/*     */       
/* 206 */       (x$3[b] > x$3[c]) ? b : ((x$3[a] > x$3[c]) ? c : a));
/*     */   }
/*     */   
/*     */   private final void sort2$2(int off, int len, int[] x$3) {
/*     */     label51: while (true) {
/* 211 */       if (len < 7) {
/* 212 */         int i = off;
/* 213 */         while (i < len + off) {
/* 214 */           int j = i;
/* 215 */           while (j > off && x$3[j - 1] > x$3[j]) {
/* 216 */             swap$2(j, j - 1, x$3);
/* 217 */             j--;
/*     */           } 
/* 219 */           i++;
/*     */         } 
/*     */         continue;
/*     */       } 
/* 223 */       int m = off + (len >> 1);
/* 224 */       if (len > 7) {
/* 225 */         int l = off;
/* 226 */         int n = off + len - 1;
/* 227 */         if (len > 40) {
/* 228 */           int s = len / 8;
/* 229 */           l = med3$2(off, off + s, off + 2 * s, x$3);
/* 230 */           m = med3$2(m - s, m, m + s, x$3);
/* 231 */           n = med3$2(n - 2 * s, n - s, n, x$3);
/*     */         } 
/* 233 */         m = med3$2(l, m, n, x$3);
/*     */       } 
/* 235 */       int v = x$3[m];
/* 238 */       int a = off;
/* 239 */       int b = off;
/* 240 */       int c = off + len - 1;
/* 241 */       int d = c;
/* 242 */       boolean done = false;
/*     */       while (true) {
/* 243 */         if (done) {
/* 268 */           int n = off + len;
/* 269 */           int s = scala.math.package$.MODULE$.min(a - off, b - a);
/* 270 */           vecswap$2(off, b - s, s, x$3);
/* 271 */           s = scala.math.package$.MODULE$.min(d - c, n - d - 1);
/* 272 */           vecswap$2(b, n - s, s, x$3);
/* 275 */           if ((s = b - a) > 
/* 276 */             1)
/* 277 */             sort2$2(off, s, x$3); 
/* 278 */           if ((s = d - c) > 
/* 279 */             1) {
/* 280 */             len = s;
/* 280 */             off = n - s;
/*     */             continue label51;
/*     */           } 
/*     */           return;
/*     */         } 
/*     */         while (b <= c && x$3[b] <= v) {
/*     */           if (x$3[b] == v) {
/*     */             swap$2(a, b, x$3);
/*     */             a++;
/*     */           } 
/*     */           b++;
/*     */         } 
/*     */         while (c >= b && x$3[c] >= v) {
/*     */           if (x$3[c] == v) {
/*     */             swap$2(c, d, x$3);
/*     */             d--;
/*     */           } 
/*     */           c--;
/*     */         } 
/*     */         if (b > c) {
/*     */           done = true;
/*     */           continue;
/*     */         } 
/*     */         swap$2(b, c, x$3);
/*     */         c--;
/*     */         b++;
/*     */       } 
/*     */       break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void sort1(int[] x, int off, int len) {
/* 283 */     sort2$2(off, len, x);
/*     */   }
/*     */   
/*     */   private final void swap$3(int a, int b, double[] x$2) {
/* 288 */     double t = x$2[a];
/* 289 */     x$2[a] = x$2[b];
/* 290 */     x$2[b] = t;
/*     */   }
/*     */   
/*     */   private final void vecswap$3(int _a, int _b, int n, double[] x$2) {
/* 293 */     int a = _a;
/* 294 */     int b = _b;
/* 295 */     int i = 0;
/* 296 */     while (i < n) {
/* 297 */       swap$3(a, b, x$2);
/* 298 */       i++;
/* 299 */       a++;
/* 300 */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final int med3$3(int a, int b, int c, double[] x$2) {
/* 304 */     double d1 = x$2[a];
/* 304 */     scala.Predef$ predef$1 = scala.Predef$.MODULE$;
/* 304 */     int ab = (new RichDouble(d1)).compare(BoxesRunTime.boxToDouble(x$2[b]));
/* 305 */     double d2 = x$2[b];
/* 305 */     scala.Predef$ predef$2 = scala.Predef$.MODULE$;
/* 305 */     int bc = (new RichDouble(d2)).compare(BoxesRunTime.boxToDouble(x$2[c]));
/* 306 */     double d3 = x$2[a];
/* 306 */     scala.Predef$ predef$3 = scala.Predef$.MODULE$;
/* 306 */     int ac = (new RichDouble(d3)).compare(BoxesRunTime.boxToDouble(x$2[c]));
/* 307 */     return (ab < 0) ? (
/* 308 */       (bc < 0) ? b : ((ac < 0) ? c : a)) : (
/*     */       
/* 310 */       (bc > 0) ? b : ((ac > 0) ? c : a));
/*     */   }
/*     */   
/*     */   private final void sort2$3(int off, int len, double[] x$2) {
/*     */     label58: while (true) {
/* 315 */       if (len < 7) {
/* 316 */         int i = off;
/* 317 */         while (i < len + off) {
/* 318 */           int j = i;
/*     */           scala.Predef$ predef$;
/*     */           double d1;
/* 319 */           for (d1 = x$2[j - 1], predef$ = scala.Predef$.MODULE$; j > off && (new RichDouble(d1)).compare(BoxesRunTime.boxToDouble(x$2[j])) > 0; ) {
/* 320 */             swap$3(j, j - 1, x$2);
/* 321 */             j--;
/*     */           } 
/* 323 */           i++;
/*     */         } 
/*     */         continue;
/*     */       } 
/* 327 */       int m = off + (len >> 1);
/* 328 */       if (len > 7) {
/* 329 */         int l = off;
/* 330 */         int n = off + len - 1;
/* 331 */         if (len > 40) {
/* 332 */           int s = len / 8;
/* 333 */           l = med3$3(off, off + s, off + 2 * s, x$2);
/* 334 */           m = med3$3(m - s, m, m + s, x$2);
/* 335 */           n = med3$3(n - 2 * s, n - s, n, x$2);
/*     */         } 
/* 337 */         m = med3$3(l, m, n, x$2);
/*     */       } 
/* 339 */       double v = x$2[m];
/* 342 */       int a = off;
/* 343 */       int b = off;
/* 344 */       int c = off + len - 1;
/* 345 */       int d = c;
/* 346 */       boolean done = false;
/*     */       while (true) {
/* 347 */         if (done) {
/* 376 */           int n = off + len;
/* 377 */           int s = scala.math.package$.MODULE$.min(a - off, b - a);
/* 378 */           vecswap$3(off, b - s, s, x$2);
/* 379 */           s = scala.math.package$.MODULE$.min(d - c, n - d - 1);
/* 380 */           vecswap$3(b, n - s, s, x$2);
/* 383 */           if ((s = b - a) > 
/* 384 */             1)
/* 385 */             sort2$3(off, s, x$2); 
/* 386 */           if ((s = d - c) > 
/* 387 */             1) {
/* 388 */             len = s;
/* 388 */             off = n - s;
/*     */             continue label58;
/*     */           } 
/*     */           return;
/*     */         } 
/*     */         double d1 = x$2[b];
/*     */         scala.Predef$ predef$1 = scala.Predef$.MODULE$;
/*     */         int bv = (new RichDouble(d1)).compare(BoxesRunTime.boxToDouble(v));
/*     */         while (b <= c && bv <= 0) {
/*     */           if (bv == 0) {
/*     */             swap$3(a, b, x$2);
/*     */             a++;
/*     */           } 
/*     */           if (++b <= c) {
/*     */             double d3 = x$2[b];
/*     */             scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*     */             bv = (new RichDouble(d3)).compare(BoxesRunTime.boxToDouble(v));
/*     */           } 
/*     */         } 
/*     */         double d2 = x$2[c];
/*     */         scala.Predef$ predef$2 = scala.Predef$.MODULE$;
/*     */         int cv = (new RichDouble(d2)).compare(BoxesRunTime.boxToDouble(v));
/*     */         while (c >= b && cv >= 0) {
/*     */           if (cv == 0) {
/*     */             swap$3(c, d, x$2);
/*     */             d--;
/*     */           } 
/*     */           if (--c >= b) {
/*     */             double d3 = x$2[c];
/*     */             scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*     */             cv = (new RichDouble(d3)).compare(BoxesRunTime.boxToDouble(v));
/*     */           } 
/*     */         } 
/*     */         if (b > c) {
/*     */           done = true;
/*     */           continue;
/*     */         } 
/*     */         swap$3(b, c, x$2);
/*     */         c--;
/*     */         b++;
/*     */       } 
/*     */       break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void sort1(double[] x, int off, int len) {
/* 391 */     sort2$3(off, len, x);
/*     */   }
/*     */   
/*     */   private final void swap$4(int a, int b, float[] x$1) {
/* 396 */     float t = x$1[a];
/* 397 */     x$1[a] = x$1[b];
/* 398 */     x$1[b] = t;
/*     */   }
/*     */   
/*     */   private final void vecswap$4(int _a, int _b, int n, float[] x$1) {
/* 401 */     int a = _a;
/* 402 */     int b = _b;
/* 403 */     int i = 0;
/* 404 */     while (i < n) {
/* 405 */       swap$4(a, b, x$1);
/* 406 */       i++;
/* 407 */       a++;
/* 408 */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final int med3$4(int a, int b, int c, float[] x$1) {
/* 412 */     float f1 = x$1[a];
/* 412 */     scala.Predef$ predef$1 = scala.Predef$.MODULE$;
/* 412 */     int ab = (new RichFloat(f1)).compare(BoxesRunTime.boxToFloat(x$1[b]));
/* 413 */     float f2 = x$1[b];
/* 413 */     scala.Predef$ predef$2 = scala.Predef$.MODULE$;
/* 413 */     int bc = (new RichFloat(f2)).compare(BoxesRunTime.boxToFloat(x$1[c]));
/* 414 */     float f3 = x$1[a];
/* 414 */     scala.Predef$ predef$3 = scala.Predef$.MODULE$;
/* 414 */     int ac = (new RichFloat(f3)).compare(BoxesRunTime.boxToFloat(x$1[c]));
/* 415 */     return (ab < 0) ? (
/* 416 */       (bc < 0) ? b : ((ac < 0) ? c : a)) : (
/*     */       
/* 418 */       (bc > 0) ? b : ((ac > 0) ? c : a));
/*     */   }
/*     */   
/*     */   private final void sort2$4(int off, int len, float[] x$1) {
/*     */     label58: while (true) {
/* 423 */       if (len < 7) {
/* 424 */         int i = off;
/* 425 */         while (i < len + off) {
/* 426 */           int j = i;
/*     */           scala.Predef$ predef$;
/*     */           float f;
/* 427 */           for (f = x$1[j - 1], predef$ = scala.Predef$.MODULE$; j > off && (new RichFloat(f)).compare(BoxesRunTime.boxToFloat(x$1[j])) > 0; ) {
/* 428 */             swap$4(j, j - 1, x$1);
/* 429 */             j--;
/*     */           } 
/* 431 */           i++;
/*     */         } 
/*     */         continue;
/*     */       } 
/* 435 */       int m = off + (len >> 1);
/* 436 */       if (len > 7) {
/* 437 */         int l = off;
/* 438 */         int n = off + len - 1;
/* 439 */         if (len > 40) {
/* 440 */           int s = len / 8;
/* 441 */           l = med3$4(off, off + s, off + 2 * s, x$1);
/* 442 */           m = med3$4(m - s, m, m + s, x$1);
/* 443 */           n = med3$4(n - 2 * s, n - s, n, x$1);
/*     */         } 
/* 445 */         m = med3$4(l, m, n, x$1);
/*     */       } 
/* 447 */       float v = x$1[m];
/* 450 */       int a = off;
/* 451 */       int b = off;
/* 452 */       int c = off + len - 1;
/* 453 */       int d = c;
/* 454 */       boolean done = false;
/*     */       while (true) {
/* 455 */         if (done) {
/* 484 */           int n = off + len;
/* 485 */           int s = scala.math.package$.MODULE$.min(a - off, b - a);
/* 486 */           vecswap$4(off, b - s, s, x$1);
/* 487 */           s = scala.math.package$.MODULE$.min(d - c, n - d - 1);
/* 488 */           vecswap$4(b, n - s, s, x$1);
/* 491 */           if ((s = b - a) > 
/* 492 */             1)
/* 493 */             sort2$4(off, s, x$1); 
/* 494 */           if ((s = d - c) > 
/* 495 */             1) {
/* 496 */             len = s;
/* 496 */             off = n - s;
/*     */             continue label58;
/*     */           } 
/*     */           return;
/*     */         } 
/*     */         float f1 = x$1[b];
/*     */         scala.Predef$ predef$1 = scala.Predef$.MODULE$;
/*     */         int bv = (new RichFloat(f1)).compare(BoxesRunTime.boxToFloat(v));
/*     */         while (b <= c && bv <= 0) {
/*     */           if (bv == 0) {
/*     */             swap$4(a, b, x$1);
/*     */             a++;
/*     */           } 
/*     */           if (++b <= c) {
/*     */             float f = x$1[b];
/*     */             scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*     */             bv = (new RichFloat(f)).compare(BoxesRunTime.boxToFloat(v));
/*     */           } 
/*     */         } 
/*     */         float f2 = x$1[c];
/*     */         scala.Predef$ predef$2 = scala.Predef$.MODULE$;
/*     */         int cv = (new RichFloat(f2)).compare(BoxesRunTime.boxToFloat(v));
/*     */         while (c >= b && cv >= 0) {
/*     */           if (cv == 0) {
/*     */             swap$4(c, d, x$1);
/*     */             d--;
/*     */           } 
/*     */           if (--c >= b) {
/*     */             float f = x$1[c];
/*     */             scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*     */             cv = (new RichFloat(f)).compare(BoxesRunTime.boxToFloat(v));
/*     */           } 
/*     */         } 
/*     */         if (b > c) {
/*     */           done = true;
/*     */           continue;
/*     */         } 
/*     */         swap$4(b, c, x$1);
/*     */         c--;
/*     */         b++;
/*     */       } 
/*     */       break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void sort1(float[] x, int off, int len) {
/* 499 */     sort2$4(off, len, x);
/*     */   }
/*     */   
/*     */   private <K> void stableSort(Object a, int lo, int hi, Object scratch, Function2<?, ?, Object> f, ClassTag<?> evidence$11) {
/* 503 */     if (lo < hi) {
/* 504 */       int mid = (lo + hi) / 2;
/* 505 */       stableSort(a, lo, mid, scratch, f, evidence$11);
/* 506 */       stableSort(a, mid + 1, hi, scratch, f, evidence$11);
/* 507 */       int k = lo, t_lo = lo;
/* 508 */       int t_hi = mid + 1;
/* 509 */       while (k <= hi) {
/* 510 */         if (t_lo <= mid && (t_hi > hi || !BoxesRunTime.unboxToBoolean(f.apply(scala.runtime.ScalaRunTime$.MODULE$.array_apply(a, t_hi), scala.runtime.ScalaRunTime$.MODULE$.array_apply(a, t_lo))))) {
/* 511 */           scala.runtime.ScalaRunTime$.MODULE$.array_update(scratch, k, scala.runtime.ScalaRunTime$.MODULE$.array_apply(a, t_lo));
/* 512 */           t_lo++;
/*     */         } else {
/* 514 */           scala.runtime.ScalaRunTime$.MODULE$.array_update(scratch, k, scala.runtime.ScalaRunTime$.MODULE$.array_apply(a, t_hi));
/* 515 */           t_hi++;
/*     */         } 
/* 517 */         k++;
/*     */       } 
/* 519 */       k = lo;
/* 520 */       while (k <= hi) {
/* 521 */         scala.runtime.ScalaRunTime$.MODULE$.array_update(a, k, scala.runtime.ScalaRunTime$.MODULE$.array_apply(scratch, k));
/* 522 */         k++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Sorting$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */