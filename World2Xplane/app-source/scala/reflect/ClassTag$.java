/*     */ package scala.reflect;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.ArrayBuilder;
/*     */ import scala.collection.mutable.WrappedArray;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class ClassTag$ implements Serializable {
/*     */   public static final ClassTag$ MODULE$;
/*     */   
/*     */   private final Class<Object> ObjectTYPE;
/*     */   
/*     */   private final Class<scala.runtime.Nothing$> NothingTYPE;
/*     */   
/*     */   private final Class<scala.runtime.Null$> NullTYPE;
/*     */   
/*     */   private final ClassTag<Object> Byte;
/*     */   
/*     */   private final ClassTag<Object> Short;
/*     */   
/*     */   private final ClassTag<Object> Char;
/*     */   
/*     */   private final ClassTag<Object> Int;
/*     */   
/*     */   private final ClassTag<Object> Long;
/*     */   
/*     */   private final ClassTag<Object> Float;
/*     */   
/*     */   private final ClassTag<Object> Double;
/*     */   
/*     */   private final ClassTag<Object> Boolean;
/*     */   
/*     */   private final ClassTag<BoxedUnit> Unit;
/*     */   
/*     */   private final ClassTag<Object> Any;
/*     */   
/*     */   private final ClassTag<Object> Object;
/*     */   
/*     */   private final ClassTag<Object> AnyVal;
/*     */   
/*     */   private final ClassTag<Object> AnyRef;
/*     */   
/*     */   private final ClassTag<scala.runtime.Nothing$> Nothing;
/*     */   
/*     */   private final ClassTag<scala.runtime.Null$> Null;
/*     */   
/*     */   private Object readResolve() {
/* 109 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ClassTag$() {
/* 109 */     MODULE$ = this;
/* 110 */     this.ObjectTYPE = Object.class;
/* 111 */     this.NothingTYPE = scala.runtime.Nothing$.class;
/* 112 */     this.NullTYPE = scala.runtime.Null$.class;
/* 114 */     this.Byte = package$.MODULE$.Manifest().Byte();
/* 115 */     this.Short = package$.MODULE$.Manifest().Short();
/* 116 */     this.Char = package$.MODULE$.Manifest().Char();
/* 117 */     this.Int = package$.MODULE$.Manifest().Int();
/* 118 */     this.Long = package$.MODULE$.Manifest().Long();
/* 119 */     this.Float = package$.MODULE$.Manifest().Float();
/* 120 */     this.Double = package$.MODULE$.Manifest().Double();
/* 121 */     this.Boolean = package$.MODULE$.Manifest().Boolean();
/* 122 */     this.Unit = package$.MODULE$.Manifest().Unit();
/* 123 */     this.Any = package$.MODULE$.Manifest().Any();
/* 124 */     this.Object = package$.MODULE$.Manifest().Object();
/* 125 */     this.AnyVal = package$.MODULE$.Manifest().AnyVal();
/* 126 */     this.AnyRef = package$.MODULE$.Manifest().AnyRef();
/* 127 */     this.Nothing = package$.MODULE$.Manifest().Nothing();
/* 128 */     this.Null = package$.MODULE$.Manifest().Null();
/*     */   }
/*     */   
/*     */   private Class<Object> ObjectTYPE() {
/*     */     return this.ObjectTYPE;
/*     */   }
/*     */   
/*     */   private Class<scala.runtime.Nothing$> NothingTYPE() {
/*     */     return this.NothingTYPE;
/*     */   }
/*     */   
/*     */   private Class<scala.runtime.Null$> NullTYPE() {
/*     */     return this.NullTYPE;
/*     */   }
/*     */   
/*     */   public ClassTag<Object> Byte() {
/*     */     return this.Byte;
/*     */   }
/*     */   
/*     */   public ClassTag<Object> Short() {
/*     */     return this.Short;
/*     */   }
/*     */   
/*     */   public ClassTag<Object> Char() {
/*     */     return this.Char;
/*     */   }
/*     */   
/*     */   public ClassTag<Object> Int() {
/*     */     return this.Int;
/*     */   }
/*     */   
/*     */   public ClassTag<Object> Long() {
/*     */     return this.Long;
/*     */   }
/*     */   
/*     */   public ClassTag<Object> Float() {
/*     */     return this.Float;
/*     */   }
/*     */   
/*     */   public ClassTag<Object> Double() {
/*     */     return this.Double;
/*     */   }
/*     */   
/*     */   public ClassTag<Object> Boolean() {
/*     */     return this.Boolean;
/*     */   }
/*     */   
/*     */   public ClassTag<BoxedUnit> Unit() {
/*     */     return this.Unit;
/*     */   }
/*     */   
/*     */   public ClassTag<Object> Any() {
/*     */     return this.Any;
/*     */   }
/*     */   
/*     */   public ClassTag<Object> Object() {
/*     */     return this.Object;
/*     */   }
/*     */   
/*     */   public ClassTag<Object> AnyVal() {
/*     */     return this.AnyVal;
/*     */   }
/*     */   
/*     */   public ClassTag<Object> AnyRef() {
/*     */     return this.AnyRef;
/*     */   }
/*     */   
/*     */   public ClassTag<scala.runtime.Nothing$> Nothing() {
/*     */     return this.Nothing;
/*     */   }
/*     */   
/*     */   public ClassTag<scala.runtime.Null$> Null() {
/* 128 */     return this.Null;
/*     */   }
/*     */   
/*     */   public <T> ClassTag<T> apply(Class runtimeClass1) {
/*     */     // Byte code:
/*     */     //   0: getstatic java/lang/Byte.TYPE : Ljava/lang/Class;
/*     */     //   3: dup
/*     */     //   4: ifnonnull -> 15
/*     */     //   7: pop
/*     */     //   8: aload_1
/*     */     //   9: ifnull -> 22
/*     */     //   12: goto -> 30
/*     */     //   15: aload_1
/*     */     //   16: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   19: ifeq -> 30
/*     */     //   22: aload_0
/*     */     //   23: invokevirtual Byte : ()Lscala/reflect/ClassTag;
/*     */     //   26: astore_2
/*     */     //   27: goto -> 372
/*     */     //   30: getstatic java/lang/Short.TYPE : Ljava/lang/Class;
/*     */     //   33: dup
/*     */     //   34: ifnonnull -> 45
/*     */     //   37: pop
/*     */     //   38: aload_1
/*     */     //   39: ifnull -> 52
/*     */     //   42: goto -> 60
/*     */     //   45: aload_1
/*     */     //   46: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   49: ifeq -> 60
/*     */     //   52: aload_0
/*     */     //   53: invokevirtual Short : ()Lscala/reflect/ClassTag;
/*     */     //   56: astore_2
/*     */     //   57: goto -> 372
/*     */     //   60: getstatic java/lang/Character.TYPE : Ljava/lang/Class;
/*     */     //   63: dup
/*     */     //   64: ifnonnull -> 75
/*     */     //   67: pop
/*     */     //   68: aload_1
/*     */     //   69: ifnull -> 82
/*     */     //   72: goto -> 90
/*     */     //   75: aload_1
/*     */     //   76: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   79: ifeq -> 90
/*     */     //   82: aload_0
/*     */     //   83: invokevirtual Char : ()Lscala/reflect/ClassTag;
/*     */     //   86: astore_2
/*     */     //   87: goto -> 372
/*     */     //   90: getstatic java/lang/Integer.TYPE : Ljava/lang/Class;
/*     */     //   93: dup
/*     */     //   94: ifnonnull -> 105
/*     */     //   97: pop
/*     */     //   98: aload_1
/*     */     //   99: ifnull -> 112
/*     */     //   102: goto -> 120
/*     */     //   105: aload_1
/*     */     //   106: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   109: ifeq -> 120
/*     */     //   112: aload_0
/*     */     //   113: invokevirtual Int : ()Lscala/reflect/ClassTag;
/*     */     //   116: astore_2
/*     */     //   117: goto -> 372
/*     */     //   120: getstatic java/lang/Long.TYPE : Ljava/lang/Class;
/*     */     //   123: dup
/*     */     //   124: ifnonnull -> 135
/*     */     //   127: pop
/*     */     //   128: aload_1
/*     */     //   129: ifnull -> 142
/*     */     //   132: goto -> 150
/*     */     //   135: aload_1
/*     */     //   136: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   139: ifeq -> 150
/*     */     //   142: aload_0
/*     */     //   143: invokevirtual Long : ()Lscala/reflect/ClassTag;
/*     */     //   146: astore_2
/*     */     //   147: goto -> 372
/*     */     //   150: getstatic java/lang/Float.TYPE : Ljava/lang/Class;
/*     */     //   153: dup
/*     */     //   154: ifnonnull -> 165
/*     */     //   157: pop
/*     */     //   158: aload_1
/*     */     //   159: ifnull -> 172
/*     */     //   162: goto -> 180
/*     */     //   165: aload_1
/*     */     //   166: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   169: ifeq -> 180
/*     */     //   172: aload_0
/*     */     //   173: invokevirtual Float : ()Lscala/reflect/ClassTag;
/*     */     //   176: astore_2
/*     */     //   177: goto -> 372
/*     */     //   180: getstatic java/lang/Double.TYPE : Ljava/lang/Class;
/*     */     //   183: dup
/*     */     //   184: ifnonnull -> 195
/*     */     //   187: pop
/*     */     //   188: aload_1
/*     */     //   189: ifnull -> 202
/*     */     //   192: goto -> 210
/*     */     //   195: aload_1
/*     */     //   196: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   199: ifeq -> 210
/*     */     //   202: aload_0
/*     */     //   203: invokevirtual Double : ()Lscala/reflect/ClassTag;
/*     */     //   206: astore_2
/*     */     //   207: goto -> 372
/*     */     //   210: getstatic java/lang/Boolean.TYPE : Ljava/lang/Class;
/*     */     //   213: dup
/*     */     //   214: ifnonnull -> 225
/*     */     //   217: pop
/*     */     //   218: aload_1
/*     */     //   219: ifnull -> 232
/*     */     //   222: goto -> 240
/*     */     //   225: aload_1
/*     */     //   226: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   229: ifeq -> 240
/*     */     //   232: aload_0
/*     */     //   233: invokevirtual Boolean : ()Lscala/reflect/ClassTag;
/*     */     //   236: astore_2
/*     */     //   237: goto -> 372
/*     */     //   240: getstatic java/lang/Void.TYPE : Ljava/lang/Class;
/*     */     //   243: dup
/*     */     //   244: ifnonnull -> 255
/*     */     //   247: pop
/*     */     //   248: aload_1
/*     */     //   249: ifnull -> 262
/*     */     //   252: goto -> 270
/*     */     //   255: aload_1
/*     */     //   256: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   259: ifeq -> 270
/*     */     //   262: aload_0
/*     */     //   263: invokevirtual Unit : ()Lscala/reflect/ClassTag;
/*     */     //   266: astore_2
/*     */     //   267: goto -> 372
/*     */     //   270: aload_0
/*     */     //   271: invokespecial ObjectTYPE : ()Ljava/lang/Class;
/*     */     //   274: dup
/*     */     //   275: ifnonnull -> 286
/*     */     //   278: pop
/*     */     //   279: aload_1
/*     */     //   280: ifnull -> 293
/*     */     //   283: goto -> 301
/*     */     //   286: aload_1
/*     */     //   287: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   290: ifeq -> 301
/*     */     //   293: aload_0
/*     */     //   294: invokevirtual Object : ()Lscala/reflect/ClassTag;
/*     */     //   297: astore_2
/*     */     //   298: goto -> 372
/*     */     //   301: aload_0
/*     */     //   302: invokespecial NothingTYPE : ()Ljava/lang/Class;
/*     */     //   305: dup
/*     */     //   306: ifnonnull -> 317
/*     */     //   309: pop
/*     */     //   310: aload_1
/*     */     //   311: ifnull -> 324
/*     */     //   314: goto -> 332
/*     */     //   317: aload_1
/*     */     //   318: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   321: ifeq -> 332
/*     */     //   324: aload_0
/*     */     //   325: invokevirtual Nothing : ()Lscala/reflect/ClassTag;
/*     */     //   328: astore_2
/*     */     //   329: goto -> 372
/*     */     //   332: aload_0
/*     */     //   333: invokespecial NullTYPE : ()Ljava/lang/Class;
/*     */     //   336: dup
/*     */     //   337: ifnonnull -> 348
/*     */     //   340: pop
/*     */     //   341: aload_1
/*     */     //   342: ifnull -> 355
/*     */     //   345: goto -> 363
/*     */     //   348: aload_1
/*     */     //   349: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   352: ifeq -> 363
/*     */     //   355: aload_0
/*     */     //   356: invokevirtual Null : ()Lscala/reflect/ClassTag;
/*     */     //   359: astore_2
/*     */     //   360: goto -> 372
/*     */     //   363: new scala/reflect/ClassTag$$anon$1
/*     */     //   366: dup
/*     */     //   367: aload_1
/*     */     //   368: invokespecial <init> : (Ljava/lang/Class;)V
/*     */     //   371: astore_2
/*     */     //   372: aload_2
/*     */     //   373: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #132	-> 0
/*     */     //   #131	-> 0
/*     */     //   #133	-> 30
/*     */     //   #134	-> 60
/*     */     //   #135	-> 90
/*     */     //   #136	-> 120
/*     */     //   #137	-> 150
/*     */     //   #138	-> 180
/*     */     //   #139	-> 210
/*     */     //   #140	-> 240
/*     */     //   #141	-> 270
/*     */     //   #142	-> 301
/*     */     //   #143	-> 332
/*     */     //   #144	-> 363
/*     */     //   #131	-> 372
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	374	0	this	Lscala/reflect/ClassTag$;
/*     */     //   0	374	1	runtimeClass1	Ljava/lang/Class;
/*     */   }
/*     */   
/*     */   public static class ClassTag$$anon$1 implements ClassTag<T> {
/*     */     private final Class runtimeClass1$1;
/*     */     
/*     */     public ClassTag<Object> wrap() {
/* 144 */       return ClassTag$class.wrap(this);
/*     */     }
/*     */     
/*     */     public Object newArray(int len) {
/* 144 */       return ClassTag$class.newArray(this, len);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(Object x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(byte x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(short x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(char x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(int x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(long x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(float x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(double x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(boolean x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(BoxedUnit x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x) {
/* 144 */       return ClassTag$class.canEqual(this, x);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x) {
/* 144 */       return ClassTag$class.equals(this, x);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 144 */       return ClassTag$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 144 */       return ClassTag$class.toString(this);
/*     */     }
/*     */     
/*     */     public Class<?> erasure() {
/* 144 */       return ClassManifestDeprecatedApis$class.erasure(this);
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 144 */       return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
/*     */     }
/*     */     
/*     */     public boolean $greater$colon$greater(ClassTag that) {
/* 144 */       return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*     */     }
/*     */     
/*     */     public <T> Class<Object> arrayClass(Class tp) {
/* 144 */       return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*     */     }
/*     */     
/*     */     public ClassTag<Object> arrayManifest() {
/* 144 */       return ClassManifestDeprecatedApis$class.arrayManifest(this);
/*     */     }
/*     */     
/*     */     public Object[] newArray2(int len) {
/* 144 */       return ClassManifestDeprecatedApis$class.newArray2(this, len);
/*     */     }
/*     */     
/*     */     public Object[][] newArray3(int len) {
/* 144 */       return ClassManifestDeprecatedApis$class.newArray3(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][] newArray4(int len) {
/* 144 */       return ClassManifestDeprecatedApis$class.newArray4(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][][] newArray5(int len) {
/* 144 */       return ClassManifestDeprecatedApis$class.newArray5(this, len);
/*     */     }
/*     */     
/*     */     public WrappedArray<T> newWrappedArray(int len) {
/* 144 */       return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<T> newArrayBuilder() {
/* 144 */       return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*     */     }
/*     */     
/*     */     public List<OptManifest<?>> typeArguments() {
/* 144 */       return ClassManifestDeprecatedApis$class.typeArguments(this);
/*     */     }
/*     */     
/*     */     public String argString() {
/* 144 */       return ClassManifestDeprecatedApis$class.argString(this);
/*     */     }
/*     */     
/*     */     public Class<?> runtimeClass() {
/* 144 */       return this.runtimeClass1$1;
/*     */     }
/*     */     
/*     */     public ClassTag$$anon$1(Class runtimeClass1$1) {
/* 144 */       ClassManifestDeprecatedApis$class.$init$(this);
/* 144 */       ClassTag$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Option<Class<?>> unapply(ClassTag ctag) {
/* 147 */     return (Option<Class<?>>)new Some(ctag.runtimeClass());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\ClassTag$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */