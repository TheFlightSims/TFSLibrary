/*     */ package scala.runtime;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import scala.MatchError;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.StringContext;
/*     */ import scala.Tuple2;
/*     */ import scala.UninitializedError;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ClassTag;
/*     */ 
/*     */ public final class ScalaRunTime$ {
/*     */   public static final ScalaRunTime$ MODULE$;
/*     */   
/*     */   private ScalaRunTime$() {
/*  28 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public boolean isArray(Object x) {
/*  29 */     return isArray(x, 1);
/*     */   }
/*     */   
/*     */   public boolean isArray(Object x, int atLevel) {
/*  31 */     return (x != null && isArrayClass(x.getClass(), atLevel));
/*     */   }
/*     */   
/*     */   private boolean isArrayClass(Class<?> clazz, int atLevel) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokevirtual isArray : ()Z
/*     */     //   4: ifeq -> 28
/*     */     //   7: iload_2
/*     */     //   8: iconst_1
/*     */     //   9: if_icmpne -> 16
/*     */     //   12: iconst_1
/*     */     //   13: goto -> 29
/*     */     //   16: aload_1
/*     */     //   17: invokevirtual getComponentType : ()Ljava/lang/Class;
/*     */     //   20: iload_2
/*     */     //   21: iconst_1
/*     */     //   22: isub
/*     */     //   23: istore_2
/*     */     //   24: astore_1
/*     */     //   25: goto -> 0
/*     */     //   28: iconst_0
/*     */     //   29: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #34	-> 0
/*     */     //   #33	-> 29
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	30	0	this	Lscala/runtime/ScalaRunTime$;
/*     */     //   0	30	1	clazz	Ljava/lang/Class;
/*     */     //   0	30	2	atLevel	I
/*     */   }
/*     */   
/*     */   public boolean isValueClass(Class clazz) {
/*  36 */     return clazz.isPrimitive();
/*     */   }
/*     */   
/*     */   public boolean isTuple(Object x) {
/*  39 */     return (x != null && x.getClass().getName().startsWith("scala.Tuple"));
/*     */   }
/*     */   
/*     */   public boolean isAnyVal(Object x) {
/*     */     boolean bool1;
/*     */     boolean bool2;
/*  40 */     if (x instanceof Byte) {
/*  40 */       bool1 = true;
/*  40 */     } else if (x instanceof Short) {
/*  40 */       bool1 = true;
/*  40 */     } else if (x instanceof Character) {
/*  40 */       bool1 = true;
/*  40 */     } else if (x instanceof Integer) {
/*  40 */       bool1 = true;
/*  40 */     } else if (x instanceof Long) {
/*  40 */       bool1 = true;
/*  40 */     } else if (x instanceof Float) {
/*  40 */       bool1 = true;
/*  40 */     } else if (x instanceof Double) {
/*  40 */       bool1 = true;
/*  40 */     } else if (x instanceof Boolean) {
/*  40 */       bool1 = true;
/*  40 */     } else if (x instanceof BoxedUnit) {
/*  40 */       bool1 = true;
/*     */     } else {
/*  40 */       bool1 = false;
/*     */     } 
/*  40 */     if (bool1) {
/*  40 */       bool2 = true;
/*     */     } else {
/*  42 */       bool2 = false;
/*     */     } 
/*     */     return bool2;
/*     */   }
/*     */   
/*     */   public Class<?> arrayClass(Class clazz) {
/*  49 */     Class<void> clazz1 = void.class;
/*  49 */     if (clazz == null) {
/*  49 */       if (clazz1 != null);
/*  49 */     } else if (clazz.equals(clazz1)) {
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public Class<?> arrayElementClass(Object schematic) {
/*  55 */     if (schematic instanceof Class) {
/*  55 */       Class clazz = (Class)schematic, clazz1 = clazz.getComponentType();
/*     */     } else {
/*  57 */       if (schematic instanceof ClassTag) {
/*  57 */         ClassTag classTag = (ClassTag)schematic;
/*  57 */         return classTag.runtimeClass();
/*     */       } 
/*  59 */       (new String[3])[0] = "unsupported schematic ";
/*  59 */       (new String[3])[1] = " (";
/*  59 */       (new String[3])[2] = ")";
/*  59 */       throw new UnsupportedOperationException((new StringContext(scala.Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s(scala.Predef$.MODULE$.genericWrapArray(new Object[] { schematic, schematic.getClass() })));
/*     */     } 
/*     */     return (Class<?>)SYNTHETIC_LOCAL_VARIABLE_4;
/*     */   }
/*     */   
/*     */   public <T> Class<T> anyValClass(Object value, ClassTag evidence$1) {
/*  67 */     return scala.reflect.package$.MODULE$.classTag(evidence$1).runtimeClass();
/*     */   }
/*     */   
/*     */   public Object array_apply(Object xs, int idx) {
/*  71 */     if (xs instanceof Object[]) {
/*  71 */       Object arrayOfObject[] = (Object[])xs, object = arrayOfObject[idx];
/*  73 */     } else if (xs instanceof int[]) {
/*  73 */       int[] arrayOfInt = (int[])xs;
/*  73 */       Integer integer = BoxesRunTime.boxToInteger(arrayOfInt[idx]);
/*  74 */     } else if (xs instanceof double[]) {
/*  74 */       double[] arrayOfDouble = (double[])xs;
/*  74 */       Double double_ = BoxesRunTime.boxToDouble(arrayOfDouble[idx]);
/*  75 */     } else if (xs instanceof long[]) {
/*  75 */       long[] arrayOfLong = (long[])xs;
/*  75 */       Long long_ = BoxesRunTime.boxToLong(arrayOfLong[idx]);
/*  76 */     } else if (xs instanceof float[]) {
/*  76 */       float[] arrayOfFloat = (float[])xs;
/*  76 */       Float float_ = BoxesRunTime.boxToFloat(arrayOfFloat[idx]);
/*  77 */     } else if (xs instanceof char[]) {
/*  77 */       char[] arrayOfChar = (char[])xs;
/*  77 */       Character character = BoxesRunTime.boxToCharacter(arrayOfChar[idx]);
/*  78 */     } else if (xs instanceof byte[]) {
/*  78 */       byte[] arrayOfByte = (byte[])xs;
/*  78 */       Byte byte_ = BoxesRunTime.boxToByte(arrayOfByte[idx]);
/*  79 */     } else if (xs instanceof short[]) {
/*  79 */       short[] arrayOfShort = (short[])xs;
/*  79 */       Short short_ = BoxesRunTime.boxToShort(arrayOfShort[idx]);
/*  80 */     } else if (xs instanceof boolean[]) {
/*  80 */       boolean[] arrayOfBoolean = (boolean[])xs;
/*  80 */       Boolean bool = BoxesRunTime.boxToBoolean(arrayOfBoolean[idx]);
/*     */     } else {
/*  81 */       if (xs instanceof BoxedUnit[]) {
/*  81 */         BoxedUnit[] arrayOfBoxedUnit = (BoxedUnit[])xs;
/*  81 */         return arrayOfBoxedUnit[idx];
/*     */       } 
/*  82 */       if (xs == null)
/*  82 */         throw new NullPointerException(); 
/*     */       throw new MatchError(xs);
/*     */     } 
/*     */     return SYNTHETIC_LOCAL_VARIABLE_13;
/*     */   }
/*     */   
/*     */   public void array_update(Object xs, int idx, Object value) {
/*  88 */     if (xs instanceof Object[]) {
/*  88 */       Object[] arrayOfObject = (Object[])xs;
/*  88 */       arrayOfObject[idx] = value;
/*  90 */     } else if (xs instanceof int[]) {
/*  90 */       int[] arrayOfInt = (int[])xs;
/*  90 */       arrayOfInt[idx] = BoxesRunTime.unboxToInt(value);
/*  91 */     } else if (xs instanceof double[]) {
/*  91 */       double[] arrayOfDouble = (double[])xs;
/*  91 */       arrayOfDouble[idx] = BoxesRunTime.unboxToDouble(value);
/*  92 */     } else if (xs instanceof long[]) {
/*  92 */       long[] arrayOfLong = (long[])xs;
/*  92 */       arrayOfLong[idx] = BoxesRunTime.unboxToLong(value);
/*  93 */     } else if (xs instanceof float[]) {
/*  93 */       float[] arrayOfFloat = (float[])xs;
/*  93 */       arrayOfFloat[idx] = BoxesRunTime.unboxToFloat(value);
/*  94 */     } else if (xs instanceof char[]) {
/*  94 */       char[] arrayOfChar = (char[])xs;
/*  94 */       arrayOfChar[idx] = BoxesRunTime.unboxToChar(value);
/*  95 */     } else if (xs instanceof byte[]) {
/*  95 */       byte[] arrayOfByte = (byte[])xs;
/*  95 */       arrayOfByte[idx] = BoxesRunTime.unboxToByte(value);
/*  96 */     } else if (xs instanceof short[]) {
/*  96 */       short[] arrayOfShort = (short[])xs;
/*  96 */       arrayOfShort[idx] = BoxesRunTime.unboxToShort(value);
/*  97 */     } else if (xs instanceof boolean[]) {
/*  97 */       boolean[] arrayOfBoolean = (boolean[])xs;
/*  97 */       arrayOfBoolean[idx] = BoxesRunTime.unboxToBoolean(value);
/*     */     } else {
/*  98 */       if (xs instanceof BoxedUnit[]) {
/*  98 */         BoxedUnit[] arrayOfBoxedUnit = (BoxedUnit[])xs;
/*  98 */         arrayOfBoxedUnit[idx] = (BoxedUnit)value;
/*     */         return;
/*     */       } 
/*  99 */       if (xs == null)
/*  99 */         throw new NullPointerException(); 
/*     */       throw new MatchError(xs);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int array_length(Object xs) {
/* 104 */     if (xs instanceof Object[]) {
/* 104 */       Object[] arrayOfObject = (Object[])xs;
/* 104 */       int i = arrayOfObject.length;
/* 106 */     } else if (xs instanceof int[]) {
/* 106 */       int arrayOfInt[] = (int[])xs, i = arrayOfInt.length;
/* 107 */     } else if (xs instanceof double[]) {
/* 107 */       double[] arrayOfDouble = (double[])xs;
/* 107 */       int i = arrayOfDouble.length;
/* 108 */     } else if (xs instanceof long[]) {
/* 108 */       long[] arrayOfLong = (long[])xs;
/* 108 */       int i = arrayOfLong.length;
/* 109 */     } else if (xs instanceof float[]) {
/* 109 */       float[] arrayOfFloat = (float[])xs;
/* 109 */       int i = arrayOfFloat.length;
/* 110 */     } else if (xs instanceof char[]) {
/* 110 */       char[] arrayOfChar = (char[])xs;
/* 110 */       int i = arrayOfChar.length;
/* 111 */     } else if (xs instanceof byte[]) {
/* 111 */       byte[] arrayOfByte = (byte[])xs;
/* 111 */       int i = arrayOfByte.length;
/* 112 */     } else if (xs instanceof short[]) {
/* 112 */       short[] arrayOfShort = (short[])xs;
/* 112 */       int i = arrayOfShort.length;
/* 113 */     } else if (xs instanceof boolean[]) {
/* 113 */       boolean[] arrayOfBoolean = (boolean[])xs;
/* 113 */       int i = arrayOfBoolean.length;
/*     */     } else {
/* 114 */       if (xs instanceof BoxedUnit[]) {
/* 114 */         BoxedUnit[] arrayOfBoxedUnit = (BoxedUnit[])xs;
/* 114 */         return arrayOfBoxedUnit.length;
/*     */       } 
/* 115 */       if (xs == null)
/* 115 */         throw new NullPointerException(); 
/*     */       throw new MatchError(xs);
/*     */     } 
/*     */     return SYNTHETIC_LOCAL_VARIABLE_12;
/*     */   }
/*     */   
/*     */   public Object array_clone(Object xs) {
/* 118 */     if (xs instanceof Object[]) {
/* 118 */       Object[] arrayOfObject1 = (Object[])xs, arrayOfObject2 = ArrayRuntime.cloneArray(arrayOfObject1);
/* 120 */     } else if (xs instanceof int[]) {
/* 120 */       int[] arrayOfInt1 = (int[])xs, arrayOfInt2 = ArrayRuntime.cloneArray(arrayOfInt1);
/* 121 */     } else if (xs instanceof double[]) {
/* 121 */       double[] arrayOfDouble1 = (double[])xs, arrayOfDouble2 = ArrayRuntime.cloneArray(arrayOfDouble1);
/* 122 */     } else if (xs instanceof long[]) {
/* 122 */       long[] arrayOfLong1 = (long[])xs, arrayOfLong2 = ArrayRuntime.cloneArray(arrayOfLong1);
/* 123 */     } else if (xs instanceof float[]) {
/* 123 */       float[] arrayOfFloat1 = (float[])xs, arrayOfFloat2 = ArrayRuntime.cloneArray(arrayOfFloat1);
/* 124 */     } else if (xs instanceof char[]) {
/* 124 */       char[] arrayOfChar1 = (char[])xs, arrayOfChar2 = ArrayRuntime.cloneArray(arrayOfChar1);
/* 125 */     } else if (xs instanceof byte[]) {
/* 125 */       byte[] arrayOfByte1 = (byte[])xs, arrayOfByte2 = ArrayRuntime.cloneArray(arrayOfByte1);
/* 126 */     } else if (xs instanceof short[]) {
/* 126 */       short[] arrayOfShort1 = (short[])xs, arrayOfShort2 = ArrayRuntime.cloneArray(arrayOfShort1);
/* 127 */     } else if (xs instanceof boolean[]) {
/* 127 */       boolean[] arrayOfBoolean1 = (boolean[])xs, arrayOfBoolean2 = ArrayRuntime.cloneArray(arrayOfBoolean1);
/*     */     } else {
/* 128 */       if (xs instanceof BoxedUnit[]) {
/*     */         BoxedUnit[] arrayOfBoxedUnit;
/* 128 */         return arrayOfBoxedUnit = (BoxedUnit[])xs;
/*     */       } 
/* 129 */       if (xs == null)
/* 129 */         throw new NullPointerException(); 
/*     */       throw new MatchError(xs);
/*     */     } 
/*     */     return SYNTHETIC_LOCAL_VARIABLE_12;
/*     */   }
/*     */   
/*     */   public Object[] toObjectArray(Object src) {
/*     */     Object[] arrayOfObject;
/* 136 */     if (src instanceof Object[]) {
/* 136 */       Object[] arrayOfObject1 = (Object[])src;
/*     */     } else {
/* 139 */       int length = array_length(src);
/* 140 */       Object[] dest = new Object[length];
/* 141 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 141 */       scala.collection.immutable.Range$ range$ = scala.collection.immutable.Range$.MODULE$;
/* 141 */       ScalaRunTime$$anonfun$toObjectArray$1 scalaRunTime$$anonfun$toObjectArray$1 = new ScalaRunTime$$anonfun$toObjectArray$1(src, dest);
/*     */       Range range;
/* 141 */       if ((range = new Range(0, length, 1)).validateRangeBoundaries(scalaRunTime$$anonfun$toObjectArray$1)) {
/*     */         int terminal1;
/*     */         int step1;
/*     */         int i1;
/* 141 */         for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 141 */           MODULE$.array_update(dest, i1, MODULE$.array_apply(src, i1));
/* 141 */           i1 += step1;
/*     */         } 
/*     */       } 
/* 143 */       arrayOfObject = dest;
/*     */     } 
/*     */     return arrayOfObject;
/*     */   }
/*     */   
/*     */   public static class ScalaRunTime$$anonfun$toObjectArray$1 extends AbstractFunction1$mcVI$sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Object src$1;
/*     */     
/*     */     public final Object[] dest$1;
/*     */     
/*     */     public final void apply(int i) {
/*     */       apply$mcVI$sp(i);
/*     */     }
/*     */     
/*     */     public ScalaRunTime$$anonfun$toObjectArray$1(Object src$1, Object[] dest$1) {}
/*     */     
/*     */     public void apply$mcVI$sp(int i) {
/*     */       ScalaRunTime$.MODULE$.array_update(this.dest$1, i, ScalaRunTime$.MODULE$.array_apply(this.src$1, i));
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Object[] toArray(Seq xs) {
/* 147 */     Object[] arr = new Object[xs.length()];
/* 148 */     IntRef i = new IntRef(0);
/* 149 */     xs.foreach(new ScalaRunTime$$anonfun$toArray$1(arr, i));
/* 153 */     return arr;
/*     */   }
/*     */   
/*     */   public static class ScalaRunTime$$anonfun$toArray$1 extends AbstractFunction1<T, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object[] arr$1;
/*     */     
/*     */     private final IntRef i$1;
/*     */     
/*     */     public ScalaRunTime$$anonfun$toArray$1(Object[] arr$1, IntRef i$1) {}
/*     */     
/*     */     public final void apply(Object x) {
/*     */       this.arr$1[this.i$1.elem] = x;
/*     */       this.i$1.elem++;
/*     */     }
/*     */   }
/*     */   
/*     */   public Method ensureAccessible(Method m) {
/* 159 */     if (!m.isAccessible())
/*     */       try {
/* 160 */         m.setAccessible(true);
/* 160 */       } catch (SecurityException securityException) {} 
/* 163 */     return m;
/*     */   }
/*     */   
/*     */   public <T> T checkInitialized(Object x) {
/* 167 */     if (x == null)
/* 167 */       throw new UninitializedError(); 
/* 167 */     return (T)x;
/*     */   }
/*     */   
/*     */   public String _toString(Product x) {
/* 170 */     return x.productIterator().mkString((new StringBuilder()).append(x.productPrefix()).append("(").toString(), ",", ")");
/*     */   }
/*     */   
/*     */   public int _hashCode(Product x) {
/* 172 */     return scala.util.hashing.MurmurHash3$.MODULE$.productHash(x);
/*     */   }
/*     */   
/*     */   public <T> Iterator<T> typedProductIterator(Product x) {
/* 176 */     return (Iterator<T>)new ScalaRunTime$$anon$1(x);
/*     */   }
/*     */   
/*     */   public static class ScalaRunTime$$anon$1 extends AbstractIterator<T> {
/*     */     private int c;
/*     */     
/*     */     private final int cmax;
/*     */     
/*     */     private final Product x$2;
/*     */     
/*     */     public ScalaRunTime$$anon$1(Product x$2) {
/* 177 */       this.c = 0;
/* 178 */       this.cmax = x$2.productArity();
/*     */     }
/*     */     
/*     */     private int c() {
/*     */       return this.c;
/*     */     }
/*     */     
/*     */     private void c_$eq(int x$1) {
/*     */       this.c = x$1;
/*     */     }
/*     */     
/*     */     private int cmax() {
/* 178 */       return this.cmax;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 179 */       return (c() < cmax());
/*     */     }
/*     */     
/*     */     public T next() {
/* 181 */       Object result = this.x$2.productElement(c());
/* 182 */       c_$eq(c() + 1);
/* 183 */       return (T)result;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean inlinedEquals(Object x, Object y) {
/* 191 */     return (x == y) ? true : (
/* 192 */       (x == null) ? false : (
/* 193 */       (x instanceof Number) ? BoxesRunTime.equalsNumObject((Number)x, y) : (
/* 194 */       (x instanceof Character) ? BoxesRunTime.equalsCharObject((Character)x, y) : 
/* 195 */       x.equals(y))));
/*     */   }
/*     */   
/*     */   public boolean _equals(Product x, Object y) {
/* 197 */     if (y instanceof Product) {
/* 197 */       Product product = (Product)y;
/* 197 */       if (x.productArity() == product.productArity())
/* 197 */         return x.productIterator().sameElements(product.productIterator()); 
/*     */     } 
/* 197 */     return false;
/*     */   }
/*     */   
/*     */   public int hash(Object x) {
/* 208 */     return (x == null) ? 0 : (
/* 209 */       (x instanceof Number) ? BoxesRunTime.hashFromNumber((Number)x) : 
/* 210 */       x.hashCode());
/*     */   }
/*     */   
/*     */   public int hash(double dv) {
/* 213 */     int iv = (int)dv;
/* 214 */     if (iv == dv)
/* 214 */       return iv; 
/* 216 */     long lv = (long)dv;
/* 217 */     if (lv == dv)
/* 217 */       return BoxesRunTime.boxToLong(lv).hashCode(); 
/* 219 */     float fv = (float)dv;
/* 220 */     return (fv == dv) ? BoxesRunTime.boxToFloat(fv).hashCode() : BoxesRunTime.boxToDouble(dv).hashCode();
/*     */   }
/*     */   
/*     */   public int hash(float fv) {
/* 223 */     int iv = (int)fv;
/* 224 */     if (iv == fv)
/* 224 */       return iv; 
/* 226 */     long lv = (long)fv;
/* 227 */     if (lv == fv)
/* 227 */       return hash(lv); 
/* 228 */     return BoxesRunTime.boxToFloat(fv).hashCode();
/*     */   }
/*     */   
/*     */   public int hash(long lv) {
/* 231 */     int low = (int)lv;
/* 232 */     int lowSign = low >>> 31;
/* 233 */     int high = (int)(lv >>> 32L);
/* 234 */     return low ^ high + lowSign;
/*     */   }
/*     */   
/*     */   public int hash(Number x) {
/* 236 */     return BoxesRunTime.hashFromNumber(x);
/*     */   }
/*     */   
/*     */   public int hash(int x) {
/* 240 */     return x;
/*     */   }
/*     */   
/*     */   public int hash(short x) {
/* 241 */     return x;
/*     */   }
/*     */   
/*     */   public int hash(byte x) {
/* 242 */     return x;
/*     */   }
/*     */   
/*     */   public int hash(char x) {
/* 243 */     return x;
/*     */   }
/*     */   
/*     */   public int hash(boolean x) {
/* 244 */     return x ? BoxesRunTime.boxToBoolean(true).hashCode() : BoxesRunTime.boxToBoolean(false).hashCode();
/*     */   }
/*     */   
/*     */   public int hash(BoxedUnit x) {
/* 245 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean sameElements(Seq xs1, Seq xs2) {
/* 252 */     return xs1.sameElements((GenIterable)xs2);
/*     */   }
/*     */   
/*     */   public String stringOf(Object arg) {
/* 266 */     return stringOf(arg, 2147483647);
/*     */   }
/*     */   
/*     */   private final String packageOf$1(Object x) {
/*     */     String str;
/* 268 */     Package package_ = x.getClass().getPackage();
/* 269 */     if (package_ == null) {
/* 269 */       str = "";
/*     */     } else {
/* 270 */       str = package_.getName();
/*     */     } 
/*     */     return str;
/*     */   }
/*     */   
/*     */   private final boolean isScalaClass$1(Object x) {
/* 272 */     return packageOf$1(x).startsWith("scala.");
/*     */   }
/*     */   
/*     */   private final boolean isScalaCompilerClass$1(Object x) {
/* 273 */     return packageOf$1(x).startsWith("scala.tools.nsc.");
/*     */   }
/*     */   
/*     */   private final boolean useOwnToString$1(Object x) {
/*     */     boolean bool1;
/*     */     boolean bool2;
/* 276 */     if (x instanceof scala.xml.Node) {
/* 276 */       bool1 = true;
/* 276 */     } else if (x instanceof scala.xml.MetaData) {
/* 276 */       bool1 = true;
/*     */     } else {
/* 276 */       bool1 = false;
/*     */     } 
/* 276 */     if (bool1) {
/* 276 */       bool2 = true;
/*     */     } else {
/*     */       boolean bool;
/* 280 */       if (x instanceof Range) {
/* 280 */         bool = true;
/* 280 */       } else if (x instanceof scala.collection.immutable.NumericRange) {
/* 280 */         bool = true;
/*     */       } else {
/* 280 */         bool = false;
/*     */       } 
/* 280 */       if (bool) {
/* 280 */         bool2 = true;
/* 282 */       } else if (x instanceof scala.collection.generic.Sorted) {
/* 282 */         bool2 = true;
/* 284 */       } else if (x instanceof scala.collection.immutable.StringLike) {
/* 284 */         bool2 = true;
/* 286 */       } else if (x instanceof scala.collection.TraversableView) {
/* 286 */         bool2 = true;
/* 290 */       } else if (x instanceof Traversable) {
/* 290 */         Traversable traversable = (Traversable)x;
/* 290 */         bool2 = (traversable.hasDefiniteSize() && isScalaClass$1(traversable) && !isScalaCompilerClass$1(traversable)) ? false : true;
/*     */       } else {
/* 292 */         bool2 = false;
/*     */       } 
/*     */     } 
/*     */     return bool2;
/*     */   }
/*     */   
/*     */   public final String scala$runtime$ScalaRunTime$$mapInner$1(Object arg, int maxElements$1) {
/*     */     String str;
/* 296 */     if (arg instanceof Tuple2) {
/* 296 */       Tuple2 tuple2 = (Tuple2)arg;
/* 297 */       str = (new StringBuilder()).append(scala$runtime$ScalaRunTime$$inner$1(tuple2._1(), maxElements$1)).append(" -> ").append(scala$runtime$ScalaRunTime$$inner$1(tuple2._2(), maxElements$1)).toString();
/*     */     } else {
/* 298 */       str = scala$runtime$ScalaRunTime$$inner$1(arg, maxElements$1);
/*     */     } 
/*     */     return str;
/*     */   }
/*     */   
/*     */   private final String arrayToString$1(Object x, int maxElements$1) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokevirtual getClass : ()Ljava/lang/Class;
/*     */     //   4: invokevirtual getComponentType : ()Ljava/lang/Class;
/*     */     //   7: dup
/*     */     //   8: ifnonnull -> 20
/*     */     //   11: pop
/*     */     //   12: ldc scala/runtime/BoxedUnit
/*     */     //   14: ifnull -> 28
/*     */     //   17: goto -> 95
/*     */     //   20: ldc scala/runtime/BoxedUnit
/*     */     //   22: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   25: ifeq -> 95
/*     */     //   28: getstatic scala/runtime/RichInt$.MODULE$ : Lscala/runtime/RichInt$;
/*     */     //   31: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   34: astore_3
/*     */     //   35: iconst_0
/*     */     //   36: getstatic scala/runtime/RichInt$.MODULE$ : Lscala/runtime/RichInt$;
/*     */     //   39: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   42: aload_0
/*     */     //   43: aload_1
/*     */     //   44: invokevirtual array_length : (Ljava/lang/Object;)I
/*     */     //   47: istore #5
/*     */     //   49: astore #4
/*     */     //   51: iload #5
/*     */     //   53: iload_2
/*     */     //   54: invokevirtual min$extension : (II)I
/*     */     //   57: invokevirtual until$extension0 : (II)Lscala/collection/immutable/Range;
/*     */     //   60: new scala/runtime/ScalaRunTime$$anonfun$arrayToString$1$1
/*     */     //   63: dup
/*     */     //   64: invokespecial <init> : ()V
/*     */     //   67: getstatic scala/collection/immutable/IndexedSeq$.MODULE$ : Lscala/collection/immutable/IndexedSeq$;
/*     */     //   70: invokevirtual canBuildFrom : ()Lscala/collection/generic/CanBuildFrom;
/*     */     //   73: invokevirtual map : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   76: checkcast scala/collection/TraversableOnce
/*     */     //   79: ldc_w 'Array('
/*     */     //   82: ldc_w ', '
/*     */     //   85: ldc ')'
/*     */     //   87: invokeinterface mkString : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
/*     */     //   92: goto -> 152
/*     */     //   95: getstatic scala/collection/mutable/WrappedArray$.MODULE$ : Lscala/collection/mutable/WrappedArray$;
/*     */     //   98: aload_1
/*     */     //   99: invokevirtual make : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   102: iload_2
/*     */     //   103: invokevirtual take : (I)Ljava/lang/Object;
/*     */     //   106: checkcast scala/collection/TraversableLike
/*     */     //   109: new scala/runtime/ScalaRunTime$$anonfun$arrayToString$1$2
/*     */     //   112: dup
/*     */     //   113: iload_2
/*     */     //   114: invokespecial <init> : (I)V
/*     */     //   117: getstatic scala/collection/mutable/WrappedArray$.MODULE$ : Lscala/collection/mutable/WrappedArray$;
/*     */     //   120: getstatic scala/reflect/ClassTag$.MODULE$ : Lscala/reflect/ClassTag$;
/*     */     //   123: ldc java/lang/String
/*     */     //   125: invokevirtual apply : (Ljava/lang/Class;)Lscala/reflect/ClassTag;
/*     */     //   128: invokevirtual canBuildFrom : (Lscala/reflect/ClassTag;)Lscala/collection/generic/CanBuildFrom;
/*     */     //   131: invokeinterface map : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   136: checkcast scala/collection/TraversableOnce
/*     */     //   139: ldc_w 'Array('
/*     */     //   142: ldc_w ', '
/*     */     //   145: ldc ')'
/*     */     //   147: invokeinterface mkString : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
/*     */     //   152: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #303	-> 0
/*     */     //   #304	-> 31
/*     */     //   #306	-> 95
/*     */     //   #303	-> 152
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	153	0	this	Lscala/runtime/ScalaRunTime$;
/*     */     //   0	153	1	x	Ljava/lang/Object;
/*     */     //   0	153	2	maxElements$1	I
/*     */   }
/*     */   
/*     */   public static class ScalaRunTime$$anonfun$arrayToString$1$1 extends AbstractFunction1<Object, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(int x$1) {
/* 304 */       return "()";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ScalaRunTime$$anonfun$arrayToString$1$2 extends AbstractFunction1<Object, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int maxElements$1;
/*     */     
/*     */     public final String apply(Object arg) {
/* 306 */       return ScalaRunTime$.MODULE$.scala$runtime$ScalaRunTime$$inner$1(arg, this.maxElements$1);
/*     */     }
/*     */     
/*     */     public ScalaRunTime$$anonfun$arrayToString$1$2(int maxElements$1) {}
/*     */   }
/*     */   
/*     */   public final String scala$runtime$ScalaRunTime$$inner$1(Object arg, int maxElements$1) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: ifnonnull -> 12
/*     */     //   4: ldc_w 'null'
/*     */     //   7: astore #15
/*     */     //   9: goto -> 587
/*     */     //   12: ldc_w ''
/*     */     //   15: dup
/*     */     //   16: ifnonnull -> 27
/*     */     //   19: pop
/*     */     //   20: aload_1
/*     */     //   21: ifnull -> 34
/*     */     //   24: goto -> 42
/*     */     //   27: aload_1
/*     */     //   28: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   31: ifeq -> 42
/*     */     //   34: ldc_w '""'
/*     */     //   37: astore #15
/*     */     //   39: goto -> 587
/*     */     //   42: aload_1
/*     */     //   43: instanceof java/lang/String
/*     */     //   46: ifeq -> 167
/*     */     //   49: aload_1
/*     */     //   50: checkcast java/lang/String
/*     */     //   53: astore #9
/*     */     //   55: getstatic scala/runtime/RichChar$.MODULE$ : Lscala/runtime/RichChar$;
/*     */     //   58: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   61: new scala/collection/immutable/StringOps
/*     */     //   64: dup
/*     */     //   65: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   68: astore_3
/*     */     //   69: aload #9
/*     */     //   71: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   74: invokevirtual head : ()Ljava/lang/Object;
/*     */     //   77: invokestatic unboxToChar : (Ljava/lang/Object;)C
/*     */     //   80: istore #5
/*     */     //   82: astore #4
/*     */     //   84: iload #5
/*     */     //   86: invokevirtual isWhitespace$extension : (C)Z
/*     */     //   89: ifne -> 130
/*     */     //   92: getstatic scala/runtime/RichChar$.MODULE$ : Lscala/runtime/RichChar$;
/*     */     //   95: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   98: new scala/collection/immutable/StringOps
/*     */     //   101: dup
/*     */     //   102: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   105: astore #6
/*     */     //   107: aload #9
/*     */     //   109: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   112: invokevirtual last : ()Ljava/lang/Object;
/*     */     //   115: invokestatic unboxToChar : (Ljava/lang/Object;)C
/*     */     //   118: istore #8
/*     */     //   120: astore #7
/*     */     //   122: iload #8
/*     */     //   124: invokevirtual isWhitespace$extension : (C)Z
/*     */     //   127: ifeq -> 160
/*     */     //   130: new scala/collection/mutable/StringBuilder
/*     */     //   133: dup
/*     */     //   134: invokespecial <init> : ()V
/*     */     //   137: ldc_w '"'
/*     */     //   140: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   143: aload #9
/*     */     //   145: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   148: ldc_w '"'
/*     */     //   151: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   154: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   157: goto -> 162
/*     */     //   160: aload #9
/*     */     //   162: astore #15
/*     */     //   164: goto -> 587
/*     */     //   167: aload_0
/*     */     //   168: aload_1
/*     */     //   169: invokespecial useOwnToString$1 : (Ljava/lang/Object;)Z
/*     */     //   172: ifeq -> 184
/*     */     //   175: aload_1
/*     */     //   176: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   179: astore #15
/*     */     //   181: goto -> 587
/*     */     //   184: aload_1
/*     */     //   185: instanceof java/lang/Object
/*     */     //   188: ifeq -> 210
/*     */     //   191: aload_0
/*     */     //   192: aload_1
/*     */     //   193: invokevirtual isArray : (Ljava/lang/Object;)Z
/*     */     //   196: ifeq -> 210
/*     */     //   199: aload_0
/*     */     //   200: aload_1
/*     */     //   201: iload_2
/*     */     //   202: invokespecial arrayToString$1 : (Ljava/lang/Object;I)Ljava/lang/String;
/*     */     //   205: astore #15
/*     */     //   207: goto -> 587
/*     */     //   210: aload_1
/*     */     //   211: instanceof scala/collection/Map
/*     */     //   214: ifeq -> 290
/*     */     //   217: aload_1
/*     */     //   218: checkcast scala/collection/Map
/*     */     //   221: astore #10
/*     */     //   223: aload #10
/*     */     //   225: invokeinterface iterator : ()Lscala/collection/Iterator;
/*     */     //   230: iload_2
/*     */     //   231: invokeinterface take : (I)Lscala/collection/Iterator;
/*     */     //   236: new scala/runtime/ScalaRunTime$$anonfun$scala$runtime$ScalaRunTime$$inner$1$1
/*     */     //   239: dup
/*     */     //   240: iload_2
/*     */     //   241: invokespecial <init> : (I)V
/*     */     //   244: invokeinterface map : (Lscala/Function1;)Lscala/collection/Iterator;
/*     */     //   249: new scala/collection/mutable/StringBuilder
/*     */     //   252: dup
/*     */     //   253: invokespecial <init> : ()V
/*     */     //   256: aload #10
/*     */     //   258: invokeinterface stringPrefix : ()Ljava/lang/String;
/*     */     //   263: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   266: ldc_w '('
/*     */     //   269: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   272: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   275: ldc_w ', '
/*     */     //   278: ldc ')'
/*     */     //   280: invokeinterface mkString : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
/*     */     //   285: astore #15
/*     */     //   287: goto -> 587
/*     */     //   290: aload_1
/*     */     //   291: instanceof scala/collection/Iterable
/*     */     //   294: ifeq -> 370
/*     */     //   297: aload_1
/*     */     //   298: checkcast scala/collection/Iterable
/*     */     //   301: astore #11
/*     */     //   303: aload #11
/*     */     //   305: invokeinterface iterator : ()Lscala/collection/Iterator;
/*     */     //   310: iload_2
/*     */     //   311: invokeinterface take : (I)Lscala/collection/Iterator;
/*     */     //   316: new scala/runtime/ScalaRunTime$$anonfun$scala$runtime$ScalaRunTime$$inner$1$2
/*     */     //   319: dup
/*     */     //   320: iload_2
/*     */     //   321: invokespecial <init> : (I)V
/*     */     //   324: invokeinterface map : (Lscala/Function1;)Lscala/collection/Iterator;
/*     */     //   329: new scala/collection/mutable/StringBuilder
/*     */     //   332: dup
/*     */     //   333: invokespecial <init> : ()V
/*     */     //   336: aload #11
/*     */     //   338: invokeinterface stringPrefix : ()Ljava/lang/String;
/*     */     //   343: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   346: ldc_w '('
/*     */     //   349: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   352: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   355: ldc_w ', '
/*     */     //   358: ldc ')'
/*     */     //   360: invokeinterface mkString : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
/*     */     //   365: astore #15
/*     */     //   367: goto -> 587
/*     */     //   370: aload_1
/*     */     //   371: instanceof scala/collection/Traversable
/*     */     //   374: ifeq -> 457
/*     */     //   377: aload_1
/*     */     //   378: checkcast scala/collection/Traversable
/*     */     //   381: astore #12
/*     */     //   383: aload #12
/*     */     //   385: iload_2
/*     */     //   386: invokeinterface take : (I)Ljava/lang/Object;
/*     */     //   391: checkcast scala/collection/TraversableLike
/*     */     //   394: new scala/runtime/ScalaRunTime$$anonfun$scala$runtime$ScalaRunTime$$inner$1$3
/*     */     //   397: dup
/*     */     //   398: iload_2
/*     */     //   399: invokespecial <init> : (I)V
/*     */     //   402: getstatic scala/collection/Traversable$.MODULE$ : Lscala/collection/Traversable$;
/*     */     //   405: invokevirtual canBuildFrom : ()Lscala/collection/generic/CanBuildFrom;
/*     */     //   408: invokeinterface map : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   413: checkcast scala/collection/TraversableOnce
/*     */     //   416: new scala/collection/mutable/StringBuilder
/*     */     //   419: dup
/*     */     //   420: invokespecial <init> : ()V
/*     */     //   423: aload #12
/*     */     //   425: invokeinterface stringPrefix : ()Ljava/lang/String;
/*     */     //   430: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   433: ldc_w '('
/*     */     //   436: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   439: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   442: ldc_w ', '
/*     */     //   445: ldc ')'
/*     */     //   447: invokeinterface mkString : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
/*     */     //   452: astore #15
/*     */     //   454: goto -> 587
/*     */     //   457: aload_1
/*     */     //   458: instanceof scala/Product1
/*     */     //   461: ifeq -> 521
/*     */     //   464: aload_1
/*     */     //   465: checkcast scala/Product1
/*     */     //   468: astore #13
/*     */     //   470: aload_0
/*     */     //   471: aload #13
/*     */     //   473: invokevirtual isTuple : (Ljava/lang/Object;)Z
/*     */     //   476: ifeq -> 521
/*     */     //   479: new scala/collection/mutable/StringBuilder
/*     */     //   482: dup
/*     */     //   483: invokespecial <init> : ()V
/*     */     //   486: ldc_w '('
/*     */     //   489: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   492: aload_0
/*     */     //   493: aload #13
/*     */     //   495: invokeinterface _1 : ()Ljava/lang/Object;
/*     */     //   500: iload_2
/*     */     //   501: invokevirtual scala$runtime$ScalaRunTime$$inner$1 : (Ljava/lang/Object;I)Ljava/lang/String;
/*     */     //   504: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   507: ldc_w ',)'
/*     */     //   510: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   513: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   516: astore #15
/*     */     //   518: goto -> 587
/*     */     //   521: aload_1
/*     */     //   522: instanceof scala/Product
/*     */     //   525: ifeq -> 581
/*     */     //   528: aload_1
/*     */     //   529: checkcast scala/Product
/*     */     //   532: astore #14
/*     */     //   534: aload_0
/*     */     //   535: aload #14
/*     */     //   537: invokevirtual isTuple : (Ljava/lang/Object;)Z
/*     */     //   540: ifeq -> 581
/*     */     //   543: aload #14
/*     */     //   545: invokeinterface productIterator : ()Lscala/collection/Iterator;
/*     */     //   550: new scala/runtime/ScalaRunTime$$anonfun$scala$runtime$ScalaRunTime$$inner$1$4
/*     */     //   553: dup
/*     */     //   554: iload_2
/*     */     //   555: invokespecial <init> : (I)V
/*     */     //   558: invokeinterface map : (Lscala/Function1;)Lscala/collection/Iterator;
/*     */     //   563: ldc_w '('
/*     */     //   566: ldc_w ','
/*     */     //   569: ldc ')'
/*     */     //   571: invokeinterface mkString : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
/*     */     //   576: astore #15
/*     */     //   578: goto -> 587
/*     */     //   581: aload_1
/*     */     //   582: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   585: astore #15
/*     */     //   587: aload #15
/*     */     //   589: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #314	-> 0
/*     */     //   #313	-> 0
/*     */     //   #315	-> 12
/*     */     //   #316	-> 42
/*     */     //   #317	-> 167
/*     */     //   #318	-> 184
/*     */     //   #319	-> 210
/*     */     //   #320	-> 290
/*     */     //   #321	-> 370
/*     */     //   #322	-> 457
/*     */     //   #323	-> 521
/*     */     //   #324	-> 581
/*     */     //   #313	-> 587
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	590	0	this	Lscala/runtime/ScalaRunTime$;
/*     */     //   0	590	1	arg	Ljava/lang/Object;
/*     */     //   0	590	2	maxElements$1	I
/*     */   }
/*     */   
/*     */   public static class ScalaRunTime$$anonfun$scala$runtime$ScalaRunTime$$inner$1$1 extends AbstractFunction1<Object, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int maxElements$1;
/*     */     
/*     */     public final String apply(Object arg) {
/* 319 */       return ScalaRunTime$.MODULE$.scala$runtime$ScalaRunTime$$mapInner$1(arg, this.maxElements$1);
/*     */     }
/*     */     
/*     */     public ScalaRunTime$$anonfun$scala$runtime$ScalaRunTime$$inner$1$1(int maxElements$1) {}
/*     */   }
/*     */   
/*     */   public static class ScalaRunTime$$anonfun$scala$runtime$ScalaRunTime$$inner$1$2 extends AbstractFunction1<Object, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int maxElements$1;
/*     */     
/*     */     public final String apply(Object arg) {
/* 320 */       return ScalaRunTime$.MODULE$.scala$runtime$ScalaRunTime$$inner$1(arg, this.maxElements$1);
/*     */     }
/*     */     
/*     */     public ScalaRunTime$$anonfun$scala$runtime$ScalaRunTime$$inner$1$2(int maxElements$1) {}
/*     */   }
/*     */   
/*     */   public static class ScalaRunTime$$anonfun$scala$runtime$ScalaRunTime$$inner$1$3 extends AbstractFunction1<Object, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int maxElements$1;
/*     */     
/*     */     public final String apply(Object arg) {
/* 321 */       return ScalaRunTime$.MODULE$.scala$runtime$ScalaRunTime$$inner$1(arg, this.maxElements$1);
/*     */     }
/*     */     
/*     */     public ScalaRunTime$$anonfun$scala$runtime$ScalaRunTime$$inner$1$3(int maxElements$1) {}
/*     */   }
/*     */   
/*     */   public static class ScalaRunTime$$anonfun$scala$runtime$ScalaRunTime$$inner$1$4 extends AbstractFunction1<Object, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int maxElements$1;
/*     */     
/*     */     public final String apply(Object arg) {
/* 323 */       return ScalaRunTime$.MODULE$.scala$runtime$ScalaRunTime$$inner$1(arg, this.maxElements$1);
/*     */     }
/*     */     
/*     */     public ScalaRunTime$$anonfun$scala$runtime$ScalaRunTime$$inner$1$4(int maxElements$1) {}
/*     */   }
/*     */   
/*     */   public String stringOf(Object arg, int maxElements) {
/*     */     try {
/*     */     
/*     */     } finally {
/*     */       boolean bool;
/* 329 */       Exception exception = null;
/* 331 */       if (exception instanceof StackOverflowError) {
/* 331 */         bool = true;
/* 331 */       } else if (exception instanceof UnsupportedOperationException) {
/* 331 */         bool = true;
/* 331 */       } else if (exception instanceof AssertionError) {
/* 331 */         bool = true;
/*     */       } else {
/* 331 */         bool = false;
/*     */       } 
/*     */     } 
/*     */     return scala$runtime$ScalaRunTime$$inner$1(arg, maxElements);
/*     */   }
/*     */   
/*     */   public String replStringOf(Object arg, int maxElements) {
/* 337 */     String s = stringOf(arg, maxElements);
/* 338 */     String nl = s.contains("\n") ? "\n" : "";
/* 340 */     return (new StringBuilder()).append(nl).append(s).append("\n").toString();
/*     */   }
/*     */   
/*     */   public void checkZip(String what, TraversableOnce coll1, TraversableOnce coll2) {
/* 343 */     if (scala.sys.package$.MODULE$.props().contains("scala.debug.zip")) {
/* 344 */       IndexedSeq xs = coll1.toIndexedSeq();
/* 345 */       IndexedSeq ys = coll2.toIndexedSeq();
/* 346 */       if (xs.length() != ys.length()) {
/* 347 */         scala.Console$.MODULE$.err().println((
/*     */             
/* 350 */             new StringBuilder()).append("Mismatched zip in ").append(what).append(":\n").append("  this: ").append(xs.mkString(", ")).append("\n").append("  that: ").append(ys.mkString(", ")).toString());
/* 352 */         scala.Predef$.MODULE$.refArrayOps((Object[])scala.Predef$.MODULE$.refArrayOps((Object[])scala.Predef$.MODULE$.refArrayOps((Object[])(new Exception()).getStackTrace()).drop(2)).take(10)).foreach(new ScalaRunTime$$anonfun$checkZip$1());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class ScalaRunTime$$anonfun$checkZip$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Object x) {
/* 352 */       scala.Predef$.MODULE$.println(x);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\ScalaRunTime$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */