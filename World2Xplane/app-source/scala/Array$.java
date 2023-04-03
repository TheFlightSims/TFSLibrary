/*     */ package scala;
/*     */ 
/*     */ import scala.collection.IndexedSeq;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Range$;
/*     */ import scala.collection.mutable.ArrayBuilder;
/*     */ import scala.collection.mutable.ArrayBuilder$;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.compat.Platform$;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Numeric$IntIsIntegral$;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractFunction3;
/*     */ import scala.runtime.AbstractFunction4;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ public final class Array$ extends FallbackArrayBuilding implements Serializable {
/*     */   public static final Array$ MODULE$;
/*     */   
/*     */   private final boolean[] emptyBooleanArray;
/*     */   
/*     */   private final byte[] emptyByteArray;
/*     */   
/*     */   private final char[] emptyCharArray;
/*     */   
/*     */   private final double[] emptyDoubleArray;
/*     */   
/*     */   private final float[] emptyFloatArray;
/*     */   
/*     */   private final int[] emptyIntArray;
/*     */   
/*     */   private final long[] emptyLongArray;
/*     */   
/*     */   private final short[] emptyShortArray;
/*     */   
/*     */   private final Object[] emptyObjectArray;
/*     */   
/*     */   private Object readResolve() {
/*  50 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Array$() {
/*  50 */     MODULE$ = this;
/*  51 */     this.emptyBooleanArray = new boolean[0];
/*  52 */     this.emptyByteArray = new byte[0];
/*  53 */     this.emptyCharArray = new char[0];
/*  54 */     this.emptyDoubleArray = new double[0];
/*  55 */     this.emptyFloatArray = new float[0];
/*  56 */     this.emptyIntArray = new int[0];
/*  57 */     this.emptyLongArray = new long[0];
/*  58 */     this.emptyShortArray = new short[0];
/*  59 */     this.emptyObjectArray = new Object[0];
/*     */   }
/*     */   
/*     */   public boolean[] emptyBooleanArray() {
/*     */     return this.emptyBooleanArray;
/*     */   }
/*     */   
/*     */   public byte[] emptyByteArray() {
/*     */     return this.emptyByteArray;
/*     */   }
/*     */   
/*     */   public char[] emptyCharArray() {
/*     */     return this.emptyCharArray;
/*     */   }
/*     */   
/*     */   public double[] emptyDoubleArray() {
/*     */     return this.emptyDoubleArray;
/*     */   }
/*     */   
/*     */   public float[] emptyFloatArray() {
/*     */     return this.emptyFloatArray;
/*     */   }
/*     */   
/*     */   public int[] emptyIntArray() {
/*     */     return this.emptyIntArray;
/*     */   }
/*     */   
/*     */   public long[] emptyLongArray() {
/*     */     return this.emptyLongArray;
/*     */   }
/*     */   
/*     */   public short[] emptyShortArray() {
/*     */     return this.emptyShortArray;
/*     */   }
/*     */   
/*     */   public Object[] emptyObjectArray() {
/*  59 */     return this.emptyObjectArray;
/*     */   }
/*     */   
/*     */   public <T> CanBuildFrom<Object, T, Object> canBuildFrom(ClassTag t) {
/*  62 */     return new Array$$anon$2(t);
/*     */   }
/*     */   
/*     */   public static class Array$$anon$2 implements CanBuildFrom<Object, T, Object> {
/*     */     private final ClassTag t$1;
/*     */     
/*     */     public Array$$anon$2(ClassTag t$1) {}
/*     */     
/*     */     public ArrayBuilder<T> apply(Object from) {
/*  63 */       return ArrayBuilder$.MODULE$.make(this.t$1);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<T> apply() {
/*  64 */       return ArrayBuilder$.MODULE$.make(this.t$1);
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> ArrayBuilder<T> newBuilder(ClassTag t) {
/*  70 */     return ArrayBuilder$.MODULE$.make(t);
/*     */   }
/*     */   
/*     */   private void slowcopy(Object src, int srcPos, Object dest, int destPos, int length) {
/*  77 */     int i = srcPos;
/*  78 */     int j = destPos;
/*  79 */     int srcUntil = srcPos + length;
/*  80 */     while (i < srcUntil) {
/*  81 */       ScalaRunTime$.MODULE$.array_update(dest, j, ScalaRunTime$.MODULE$.array_apply(src, i));
/*  82 */       i++;
/*  83 */       j++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void copy(Object src, int srcPos, Object dest, int destPos, int length) {
/* 103 */     Class<?> srcClass = src.getClass();
/* 104 */     if (srcClass.isArray() && dest.getClass().isAssignableFrom(srcClass)) {
/* 105 */       Platform$ platform$ = Platform$.MODULE$;
/* 105 */       System.arraycopy(src, srcPos, dest, destPos, length);
/*     */     } else {
/* 107 */       slowcopy(src, srcPos, dest, destPos, length);
/*     */     } 
/*     */   }
/*     */   
/*     */   public <T> Object empty(ClassTag evidence$1) {
/* 111 */     return evidence$1.newArray(0);
/*     */   }
/*     */   
/*     */   public <T> Object apply(Seq xs, ClassTag evidence$2) {
/* 121 */     Object array = evidence$2.newArray(xs.length());
/* 122 */     IntRef i = new IntRef(0);
/* 123 */     xs.iterator().foreach((Function1)new Array$$anonfun$apply$5(array, i));
/* 124 */     return array;
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$apply$5 extends AbstractFunction1<T, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object array$1;
/*     */     
/*     */     private final IntRef i$1;
/*     */     
/*     */     public final void apply(Object x) {
/*     */       ScalaRunTime$.MODULE$.array_update(this.array$1, this.i$1.elem, x);
/*     */       this.i$1.elem++;
/*     */     }
/*     */     
/*     */     public Array$$anonfun$apply$5(Object array$1, IntRef i$1) {}
/*     */   }
/*     */   
/*     */   public boolean[] apply(boolean x, Seq xs) {
/* 130 */     boolean[] array = new boolean[xs.length() + 1];
/* 131 */     array[0] = x;
/* 132 */     IntRef i = new IntRef(1);
/* 133 */     xs.iterator().foreach((Function1)new Array$$anonfun$apply$6(array, i));
/* 134 */     return array;
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$apply$6 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final boolean[] array$2;
/*     */     
/*     */     private final IntRef i$2;
/*     */     
/*     */     public final void apply(boolean x) {
/*     */       this.array$2[this.i$2.elem] = x;
/*     */       this.i$2.elem++;
/*     */     }
/*     */     
/*     */     public Array$$anonfun$apply$6(boolean[] array$2, IntRef i$2) {}
/*     */   }
/*     */   
/*     */   public byte[] apply(byte x, Seq xs) {
/* 140 */     byte[] array = new byte[xs.length() + 1];
/* 141 */     array[0] = x;
/* 142 */     IntRef i = new IntRef(1);
/* 143 */     xs.iterator().foreach((Function1)new Array$$anonfun$apply$7(array, i));
/* 144 */     return array;
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$apply$7 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final byte[] array$3;
/*     */     
/*     */     private final IntRef i$3;
/*     */     
/*     */     public final void apply(byte x) {
/*     */       this.array$3[this.i$3.elem] = x;
/*     */       this.i$3.elem++;
/*     */     }
/*     */     
/*     */     public Array$$anonfun$apply$7(byte[] array$3, IntRef i$3) {}
/*     */   }
/*     */   
/*     */   public short[] apply(short x, Seq xs) {
/* 150 */     short[] array = new short[xs.length() + 1];
/* 151 */     array[0] = x;
/* 152 */     IntRef i = new IntRef(1);
/* 153 */     xs.iterator().foreach((Function1)new Array$$anonfun$apply$8(array, i));
/* 154 */     return array;
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$apply$8 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final short[] array$4;
/*     */     
/*     */     private final IntRef i$4;
/*     */     
/*     */     public final void apply(short x) {
/*     */       this.array$4[this.i$4.elem] = x;
/*     */       this.i$4.elem++;
/*     */     }
/*     */     
/*     */     public Array$$anonfun$apply$8(short[] array$4, IntRef i$4) {}
/*     */   }
/*     */   
/*     */   public char[] apply(char x, Seq xs) {
/* 160 */     char[] array = new char[xs.length() + 1];
/* 161 */     array[0] = x;
/* 162 */     IntRef i = new IntRef(1);
/* 163 */     xs.iterator().foreach((Function1)new Array$$anonfun$apply$9(array, i));
/* 164 */     return array;
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$apply$9 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final char[] array$5;
/*     */     
/*     */     private final IntRef i$5;
/*     */     
/*     */     public final void apply(char x) {
/*     */       this.array$5[this.i$5.elem] = x;
/*     */       this.i$5.elem++;
/*     */     }
/*     */     
/*     */     public Array$$anonfun$apply$9(char[] array$5, IntRef i$5) {}
/*     */   }
/*     */   
/*     */   public int[] apply(int x, Seq xs) {
/* 170 */     int[] array = new int[xs.length() + 1];
/* 171 */     array[0] = x;
/* 172 */     IntRef i = new IntRef(1);
/* 173 */     xs.iterator().foreach((Function1)new Array$$anonfun$apply$1(array, i));
/* 174 */     return array;
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$apply$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int[] array$6;
/*     */     
/*     */     private final IntRef i$6;
/*     */     
/*     */     public final void apply(int x) {
/*     */       apply$mcVI$sp(x);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int x) {
/*     */       this.array$6[this.i$6.elem] = x;
/*     */       this.i$6.elem++;
/*     */     }
/*     */     
/*     */     public Array$$anonfun$apply$1(int[] array$6, IntRef i$6) {}
/*     */   }
/*     */   
/*     */   public long[] apply(long x, Seq xs) {
/* 180 */     long[] array = new long[xs.length() + 1];
/* 181 */     array[0] = x;
/* 182 */     IntRef i = new IntRef(1);
/* 183 */     xs.iterator().foreach((Function1)new Array$$anonfun$apply$2(array, i));
/* 184 */     return array;
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$apply$2 extends AbstractFunction1.mcVJ.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final long[] array$7;
/*     */     
/*     */     private final IntRef i$7;
/*     */     
/*     */     public final void apply(long x) {
/*     */       apply$mcVJ$sp(x);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long x) {
/*     */       this.array$7[this.i$7.elem] = x;
/*     */       this.i$7.elem++;
/*     */     }
/*     */     
/*     */     public Array$$anonfun$apply$2(long[] array$7, IntRef i$7) {}
/*     */   }
/*     */   
/*     */   public float[] apply(float x, Seq xs) {
/* 190 */     float[] array = new float[xs.length() + 1];
/* 191 */     array[0] = x;
/* 192 */     IntRef i = new IntRef(1);
/* 193 */     xs.iterator().foreach((Function1)new Array$$anonfun$apply$3(array, i));
/* 194 */     return array;
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$apply$3 extends AbstractFunction1.mcVF.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final float[] array$8;
/*     */     
/*     */     private final IntRef i$8;
/*     */     
/*     */     public final void apply(float x) {
/*     */       apply$mcVF$sp(x);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float x) {
/*     */       this.array$8[this.i$8.elem] = x;
/*     */       this.i$8.elem++;
/*     */     }
/*     */     
/*     */     public Array$$anonfun$apply$3(float[] array$8, IntRef i$8) {}
/*     */   }
/*     */   
/*     */   public double[] apply(double x, Seq xs) {
/* 200 */     double[] array = new double[xs.length() + 1];
/* 201 */     array[0] = x;
/* 202 */     IntRef i = new IntRef(1);
/* 203 */     xs.iterator().foreach((Function1)new Array$$anonfun$apply$4(array, i));
/* 204 */     return array;
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$apply$4 extends AbstractFunction1.mcVD.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final double[] array$9;
/*     */     
/*     */     private final IntRef i$9;
/*     */     
/*     */     public final void apply(double x) {
/*     */       apply$mcVD$sp(x);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double x) {
/*     */       this.array$9[this.i$9.elem] = x;
/*     */       this.i$9.elem++;
/*     */     }
/*     */     
/*     */     public Array$$anonfun$apply$4(double[] array$9, IntRef i$9) {}
/*     */   }
/*     */   
/*     */   public BoxedUnit[] apply(BoxedUnit x, Seq xs) {
/* 209 */     BoxedUnit[] array = new BoxedUnit[xs.length() + 1];
/* 210 */     array[0] = x;
/* 211 */     IntRef i = new IntRef(1);
/* 212 */     xs.iterator().foreach((Function1)new Array$$anonfun$apply$10(array, i));
/* 213 */     return array;
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$apply$10 extends AbstractFunction1<BoxedUnit, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BoxedUnit[] array$10;
/*     */     
/*     */     private final IntRef i$10;
/*     */     
/*     */     public final void apply(BoxedUnit x) {
/*     */       this.array$10[this.i$10.elem] = x;
/*     */       this.i$10.elem++;
/*     */     }
/*     */     
/*     */     public Array$$anonfun$apply$10(BoxedUnit[] array$10, IntRef i$10) {}
/*     */   }
/*     */   
/*     */   public <T> Object ofDim(int n1, ClassTag evidence$3) {
/* 218 */     return evidence$3.newArray(n1);
/*     */   }
/*     */   
/*     */   public <T> Object[] ofDim(int n1, int n2, ClassTag evidence$4) {
/* 221 */     Object[] arr = (Object[])ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$4.runtimeClass())).newArray(n1);
/* 222 */     Predef$ predef$ = Predef$.MODULE$;
/* 222 */     Range$ range$ = Range$.MODULE$;
/* 222 */     Array$$anonfun$ofDim$1 array$$anonfun$ofDim$1 = new Array$$anonfun$ofDim$1(n2, evidence$4, arr);
/*     */     Range range;
/* 222 */     if ((range = new Range(0, n1, 1)).validateRangeBoundaries((Function1)array$$anonfun$ofDim$1)) {
/*     */       int terminal1;
/*     */       int step1;
/*     */       int i1;
/* 222 */       for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 222 */         arr[i1] = evidence$4.newArray(n2);
/* 222 */         i1 += step1;
/*     */       } 
/*     */     } 
/* 223 */     return arr;
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$ofDim$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int n2$1;
/*     */     
/*     */     public final ClassTag evidence$4$1;
/*     */     
/*     */     public final Object[] arr$1;
/*     */     
/*     */     public final void apply(int i) {
/*     */       apply$mcVI$sp(i);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int i) {
/*     */       this.arr$1[i] = this.evidence$4$1.newArray(this.n2$1);
/*     */     }
/*     */     
/*     */     public Array$$anonfun$ofDim$1(int n2$1, ClassTag evidence$4$1, Object[] arr$1) {}
/*     */   }
/*     */   
/*     */   public <T> Object[][] ofDim(int n1, int n2, int n3, ClassTag evidence$5) {
/* 228 */     return (Object[][])tabulate(n1, (Function1<Object, ?>)new Array$$anonfun$ofDim$2(n2, n3, evidence$5), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$5.runtimeClass()))));
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$ofDim$2 extends AbstractFunction1<Object, Object[]> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$2;
/*     */     
/*     */     private final int n3$1;
/*     */     
/*     */     private final ClassTag evidence$5$1;
/*     */     
/*     */     public final Object[] apply(int x$1) {
/* 228 */       return Array$.MODULE$.ofDim(this.n2$2, this.n3$1, this.evidence$5$1);
/*     */     }
/*     */     
/*     */     public Array$$anonfun$ofDim$2(int n2$2, int n3$1, ClassTag evidence$5$1) {}
/*     */   }
/*     */   
/*     */   public <T> Object[][][] ofDim(int n1, int n2, int n3, int n4, ClassTag evidence$6) {
/* 231 */     return (Object[][][])tabulate(n1, (Function1<Object, ?>)new Array$$anonfun$ofDim$3(n2, n3, n4, evidence$6), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$6.runtimeClass())))));
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$ofDim$3 extends AbstractFunction1<Object, Object[][]> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$8;
/*     */     
/*     */     private final int n3$6;
/*     */     
/*     */     private final int n4$4;
/*     */     
/*     */     private final ClassTag evidence$6$1;
/*     */     
/*     */     public final Object[][] apply(int x$2) {
/* 231 */       return Array$.MODULE$.ofDim(this.n2$8, this.n3$6, this.n4$4, this.evidence$6$1);
/*     */     }
/*     */     
/*     */     public Array$$anonfun$ofDim$3(int n2$8, int n3$6, int n4$4, ClassTag evidence$6$1) {}
/*     */   }
/*     */   
/*     */   public <T> Object[][][][] ofDim(int n1, int n2, int n3, int n4, int n5, ClassTag evidence$7) {
/* 234 */     return (Object[][][][])tabulate(n1, (Function1<Object, ?>)new Array$$anonfun$ofDim$4(n2, n3, n4, n5, evidence$7), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$7.runtimeClass()))))));
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$ofDim$4 extends AbstractFunction1<Object, Object[][][]> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$7;
/*     */     
/*     */     private final int n3$5;
/*     */     
/*     */     private final int n4$3;
/*     */     
/*     */     private final int n5$2;
/*     */     
/*     */     private final ClassTag evidence$7$1;
/*     */     
/*     */     public final Object[][][] apply(int x$3) {
/* 234 */       return Array$.MODULE$.ofDim(this.n2$7, this.n3$5, this.n4$3, this.n5$2, this.evidence$7$1);
/*     */     }
/*     */     
/*     */     public Array$$anonfun$ofDim$4(int n2$7, int n3$5, int n4$3, int n5$2, ClassTag evidence$7$1) {}
/*     */   }
/*     */   
/*     */   public <T> Object concat(Seq xss, ClassTag<?> evidence$8) {
/* 242 */     ArrayBuilder<?> b = newBuilder(evidence$8);
/* 243 */     b.sizeHint(BoxesRunTime.unboxToInt(((TraversableOnce)xss.map((Function1)new Array$$anonfun$concat$1(), Seq$.MODULE$.canBuildFrom())).sum((Numeric)Numeric$IntIsIntegral$.MODULE$)));
/* 244 */     xss.foreach((Function1)new Array$$anonfun$concat$2(b));
/* 245 */     return b.result();
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$concat$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(Object x$4) {
/*     */       return Predef$.MODULE$.<T>genericArrayOps(x$4).size();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$concat$2 extends AbstractFunction1<Object, ArrayBuilder<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ArrayBuilder b$1;
/*     */     
/*     */     public final ArrayBuilder<T> apply(Object xs) {
/*     */       return (ArrayBuilder<T>)this.b$1.$plus$plus$eq((TraversableOnce)Predef$.MODULE$.genericArrayOps(xs));
/*     */     }
/*     */     
/*     */     public Array$$anonfun$concat$2(ArrayBuilder b$1) {}
/*     */   }
/*     */   
/*     */   public <T> Object fill(int n, Function0 elem, ClassTag<?> evidence$9) {
/* 263 */     ArrayBuilder<?> b = newBuilder(evidence$9);
/* 264 */     b.sizeHint(n);
/* 265 */     int i = 0;
/* 266 */     while (i < n) {
/* 267 */       b.$plus$eq(elem.apply());
/* 268 */       i++;
/*     */     } 
/* 270 */     return b.result();
/*     */   }
/*     */   
/*     */   public <T> Object[] fill(int n1, int n2, Function0 elem, ClassTag evidence$10) {
/* 281 */     return (Object[])tabulate(n1, (Function1<Object, ?>)new Array$$anonfun$fill$1(n2, elem, evidence$10), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$10.runtimeClass())));
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$fill$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$9;
/*     */     
/*     */     private final Function0 elem$1;
/*     */     
/*     */     private final ClassTag evidence$10$1;
/*     */     
/*     */     public final Object apply(int x$5) {
/* 281 */       return Array$.MODULE$.fill(this.n2$9, this.elem$1, this.evidence$10$1);
/*     */     }
/*     */     
/*     */     public Array$$anonfun$fill$1(int n2$9, Function0 elem$1, ClassTag evidence$10$1) {}
/*     */   }
/*     */   
/*     */   public <T> Object[][] fill(int n1, int n2, int n3, Function0 elem, ClassTag evidence$11) {
/* 292 */     return (Object[][])tabulate(n1, (Function1<Object, ?>)new Array$$anonfun$fill$2(n2, n3, elem, evidence$11), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$11.runtimeClass()))));
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$fill$2 extends AbstractFunction1<Object, Object[]> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$12;
/*     */     
/*     */     private final int n3$9;
/*     */     
/*     */     private final Function0 elem$4;
/*     */     
/*     */     private final ClassTag evidence$11$1;
/*     */     
/*     */     public final Object[] apply(int x$6) {
/* 292 */       return Array$.MODULE$.fill(this.n2$12, this.n3$9, this.elem$4, this.evidence$11$1);
/*     */     }
/*     */     
/*     */     public Array$$anonfun$fill$2(int n2$12, int n3$9, Function0 elem$4, ClassTag evidence$11$1) {}
/*     */   }
/*     */   
/*     */   public <T> Object[][][] fill(int n1, int n2, int n3, int n4, Function0 elem, ClassTag evidence$12) {
/* 304 */     return (Object[][][])tabulate(n1, (Function1<Object, ?>)new Array$$anonfun$fill$3(n2, n3, n4, elem, evidence$12), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$12.runtimeClass())))));
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$fill$3 extends AbstractFunction1<Object, Object[][]> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$11;
/*     */     
/*     */     private final int n3$8;
/*     */     
/*     */     private final int n4$6;
/*     */     
/*     */     private final Function0 elem$3;
/*     */     
/*     */     private final ClassTag evidence$12$1;
/*     */     
/*     */     public final Object[][] apply(int x$7) {
/* 304 */       return Array$.MODULE$.fill(this.n2$11, this.n3$8, this.n4$6, this.elem$3, this.evidence$12$1);
/*     */     }
/*     */     
/*     */     public Array$$anonfun$fill$3(int n2$11, int n3$8, int n4$6, Function0 elem$3, ClassTag evidence$12$1) {}
/*     */   }
/*     */   
/*     */   public <T> Object[][][][] fill(int n1, int n2, int n3, int n4, int n5, Function0 elem, ClassTag evidence$13) {
/* 317 */     return (Object[][][][])tabulate(n1, (Function1<Object, ?>)new Array$$anonfun$fill$4(n2, n3, n4, n5, elem, evidence$13), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$13.runtimeClass()))))));
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$fill$4 extends AbstractFunction1<Object, Object[][][]> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$10;
/*     */     
/*     */     private final int n3$7;
/*     */     
/*     */     private final int n4$5;
/*     */     
/*     */     private final int n5$3;
/*     */     
/*     */     private final Function0 elem$2;
/*     */     
/*     */     private final ClassTag evidence$13$1;
/*     */     
/*     */     public final Object[][][] apply(int x$8) {
/* 317 */       return Array$.MODULE$.fill(this.n2$10, this.n3$7, this.n4$5, this.n5$3, this.elem$2, this.evidence$13$1);
/*     */     }
/*     */     
/*     */     public Array$$anonfun$fill$4(int n2$10, int n3$7, int n4$5, int n5$3, Function0 elem$2, ClassTag evidence$13$1) {}
/*     */   }
/*     */   
/*     */   public <T> Object tabulate(int n, Function1 f, ClassTag<?> evidence$14) {
/* 327 */     ArrayBuilder<?> b = newBuilder(evidence$14);
/* 328 */     b.sizeHint(n);
/* 329 */     int i = 0;
/* 330 */     while (i < n) {
/* 331 */       b.$plus$eq(f.apply(BoxesRunTime.boxToInteger(i)));
/* 332 */       i++;
/*     */     } 
/* 334 */     return b.result();
/*     */   }
/*     */   
/*     */   public <T> Object[] tabulate(int n1, int n2, Function2 f, ClassTag evidence$15) {
/* 345 */     return (Object[])tabulate(n1, (Function1<Object, ?>)new Array$$anonfun$tabulate$1(n2, f, evidence$15), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$15.runtimeClass())));
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$tabulate$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$6;
/*     */     
/*     */     public final Function2 f$4;
/*     */     
/*     */     private final ClassTag evidence$15$1;
/*     */     
/*     */     public final Object apply(int i1) {
/* 345 */       return Array$.MODULE$.tabulate(this.n2$6, (Function1<Object, ?>)new Array$$anonfun$tabulate$1$$anonfun$apply$11(this, i1), this.evidence$15$1);
/*     */     }
/*     */     
/*     */     public Array$$anonfun$tabulate$1(int n2$6, Function2 f$4, ClassTag evidence$15$1) {}
/*     */     
/*     */     public class Array$$anonfun$tabulate$1$$anonfun$apply$11 extends AbstractFunction1<Object, T> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final int i1$1;
/*     */       
/*     */       public final T apply(int x$9) {
/* 345 */         return this.$outer.f$4.apply(BoxesRunTime.boxToInteger(this.i1$1), BoxesRunTime.boxToInteger(x$9));
/*     */       }
/*     */       
/*     */       public Array$$anonfun$tabulate$1$$anonfun$apply$11(Array$$anonfun$tabulate$1 $outer, int i1$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Object[][] tabulate(int n1, int n2, int n3, Function3 f, ClassTag evidence$16) {
/* 356 */     return (Object[][])tabulate(n1, (Function1<Object, ?>)new Array$$anonfun$tabulate$2(n2, n3, f, evidence$16), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$16.runtimeClass()))));
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$tabulate$2 extends AbstractFunction1<Object, Object[]> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$5;
/*     */     
/*     */     private final int n3$4;
/*     */     
/*     */     public final Function3 f$3;
/*     */     
/*     */     private final ClassTag evidence$16$1;
/*     */     
/*     */     public final Object[] apply(int i1) {
/* 356 */       return Array$.MODULE$.tabulate(this.n2$5, this.n3$4, (Function2<Object, Object, ?>)new Array$$anonfun$tabulate$2$$anonfun$apply$12(this, i1), this.evidence$16$1);
/*     */     }
/*     */     
/*     */     public Array$$anonfun$tabulate$2(int n2$5, int n3$4, Function3 f$3, ClassTag evidence$16$1) {}
/*     */     
/*     */     public class Array$$anonfun$tabulate$2$$anonfun$apply$12 extends AbstractFunction2<Object, Object, T> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final int i1$2;
/*     */       
/*     */       public final T apply(int x$10, int x$11) {
/* 356 */         return this.$outer.f$3.apply(BoxesRunTime.boxToInteger(this.i1$2), BoxesRunTime.boxToInteger(x$10), BoxesRunTime.boxToInteger(x$11));
/*     */       }
/*     */       
/*     */       public Array$$anonfun$tabulate$2$$anonfun$apply$12(Array$$anonfun$tabulate$2 $outer, int i1$2) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Object[][][] tabulate(int n1, int n2, int n3, int n4, Function4 f, ClassTag evidence$17) {
/* 368 */     return (Object[][][])tabulate(n1, (Function1<Object, ?>)new Array$$anonfun$tabulate$3(n2, n3, n4, f, evidence$17), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$17.runtimeClass())))));
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$tabulate$3 extends AbstractFunction1<Object, Object[][]> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$4;
/*     */     
/*     */     private final int n3$3;
/*     */     
/*     */     private final int n4$2;
/*     */     
/*     */     public final Function4 f$2;
/*     */     
/*     */     private final ClassTag evidence$17$1;
/*     */     
/*     */     public final Object[][] apply(int i1) {
/* 368 */       return Array$.MODULE$.tabulate(this.n2$4, this.n3$3, this.n4$2, (Function3<Object, Object, Object, ?>)new Array$$anonfun$tabulate$3$$anonfun$apply$13(this, i1), this.evidence$17$1);
/*     */     }
/*     */     
/*     */     public Array$$anonfun$tabulate$3(int n2$4, int n3$3, int n4$2, Function4 f$2, ClassTag evidence$17$1) {}
/*     */     
/*     */     public class Array$$anonfun$tabulate$3$$anonfun$apply$13 extends AbstractFunction3<Object, Object, Object, T> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final int i1$3;
/*     */       
/*     */       public final T apply(int x$12, int x$13, int x$14) {
/* 368 */         return this.$outer.f$2.apply(BoxesRunTime.boxToInteger(this.i1$3), BoxesRunTime.boxToInteger(x$12), BoxesRunTime.boxToInteger(x$13), BoxesRunTime.boxToInteger(x$14));
/*     */       }
/*     */       
/*     */       public Array$$anonfun$tabulate$3$$anonfun$apply$13(Array$$anonfun$tabulate$3 $outer, int i1$3) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Object[][][][] tabulate(int n1, int n2, int n3, int n4, int n5, Function5 f, ClassTag evidence$18) {
/* 381 */     return (Object[][][][])tabulate(n1, (Function1<Object, ?>)new Array$$anonfun$tabulate$4(n2, n3, n4, n5, f, evidence$18), ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$18.runtimeClass()))))));
/*     */   }
/*     */   
/*     */   public static class Array$$anonfun$tabulate$4 extends AbstractFunction1<Object, Object[][][]> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$3;
/*     */     
/*     */     private final int n3$2;
/*     */     
/*     */     private final int n4$1;
/*     */     
/*     */     private final int n5$1;
/*     */     
/*     */     public final Function5 f$1;
/*     */     
/*     */     private final ClassTag evidence$18$1;
/*     */     
/*     */     public final Object[][][] apply(int i1) {
/* 381 */       return Array$.MODULE$.tabulate(this.n2$3, this.n3$2, this.n4$1, this.n5$1, (Function4<Object, Object, Object, Object, ?>)new Array$$anonfun$tabulate$4$$anonfun$apply$14(this, i1), this.evidence$18$1);
/*     */     }
/*     */     
/*     */     public Array$$anonfun$tabulate$4(int n2$3, int n3$2, int n4$1, int n5$1, Function5 f$1, ClassTag evidence$18$1) {}
/*     */     
/*     */     public class Array$$anonfun$tabulate$4$$anonfun$apply$14 extends AbstractFunction4<Object, Object, Object, Object, T> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final int i1$4;
/*     */       
/*     */       public final T apply(int x$15, int x$16, int x$17, int x$18) {
/* 381 */         return this.$outer.f$1.apply(BoxesRunTime.boxToInteger(this.i1$4), BoxesRunTime.boxToInteger(x$15), BoxesRunTime.boxToInteger(x$16), BoxesRunTime.boxToInteger(x$17), BoxesRunTime.boxToInteger(x$18));
/*     */       }
/*     */       
/*     */       public Array$$anonfun$tabulate$4$$anonfun$apply$14(Array$$anonfun$tabulate$4 $outer, int i1$4) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public int[] range(int start, int end) {
/* 390 */     return range(start, end, 1);
/*     */   }
/*     */   
/*     */   public int[] range(int start, int end, int step) {
/* 400 */     if (step == 0)
/* 400 */       throw new IllegalArgumentException("zero step"); 
/* 401 */     ArrayBuilder<?> b = newBuilder(ClassTag$.MODULE$.Int());
/* 402 */     b.sizeHint(Range$.MODULE$.count(start, end, step, false));
/* 404 */     int i = start;
/*     */     while (true) {
/* 405 */       if ((step < 0) ? ((end < i)) : ((i < end))) {
/* 406 */         b.$plus$eq(BoxesRunTime.boxToInteger(i));
/* 407 */         i += step;
/*     */         continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 409 */     return (int[])b.result();
/*     */   }
/*     */   
/*     */   public <T> Object iterate(Object start, int len, Function1 f, ClassTag<?> evidence$19) {
/* 420 */     ArrayBuilder<?> b = newBuilder(evidence$19);
/* 422 */     if (len > 0) {
/* 423 */       b.sizeHint(len);
/* 424 */       Object acc = start;
/* 425 */       int i = 1;
/* 426 */       b.$plus$eq(start);
/* 428 */       while (i < len) {
/* 429 */         acc = f.apply(acc);
/* 430 */         i++;
/* 431 */         b.$plus$eq(acc);
/*     */       } 
/*     */     } 
/* 434 */     return b.result();
/*     */   }
/*     */   
/*     */   public <T> Option<IndexedSeq<T>> unapplySeq(Object x) {
/* 443 */     return (x == null) ? None$.MODULE$ : new Some(Predef$.MODULE$.<T>genericArrayOps(x).toIndexedSeq());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Array$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */