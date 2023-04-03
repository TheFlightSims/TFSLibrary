/*     */ package scala.reflect;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Some;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.ArrayBuilder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.mutable.WrappedArray;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class ClassManifestFactory$ {
/*     */   public static final ClassManifestFactory$ MODULE$;
/*     */   
/*     */   private final AnyValManifest<Object> Byte;
/*     */   
/*     */   private final AnyValManifest<Object> Short;
/*     */   
/*     */   private final AnyValManifest<Object> Char;
/*     */   
/*     */   private final AnyValManifest<Object> Int;
/*     */   
/*     */   private final AnyValManifest<Object> Long;
/*     */   
/*     */   private final AnyValManifest<Object> Float;
/*     */   
/*     */   private final AnyValManifest<Object> Double;
/*     */   
/*     */   private final AnyValManifest<Object> Boolean;
/*     */   
/*     */   private final AnyValManifest<BoxedUnit> Unit;
/*     */   
/*     */   private final Manifest<Object> Any;
/*     */   
/*     */   private final Manifest<Object> Object;
/*     */   
/*     */   private final Manifest<Object> AnyVal;
/*     */   
/*     */   private final Manifest<scala.runtime.Nothing$> Nothing;
/*     */   
/*     */   private final Manifest<scala.runtime.Null$> Null;
/*     */   
/*     */   private ClassManifestFactory$() {
/* 149 */     MODULE$ = this;
/* 150 */     this.Byte = ManifestFactory$.MODULE$.Byte();
/* 151 */     this.Short = ManifestFactory$.MODULE$.Short();
/* 152 */     this.Char = ManifestFactory$.MODULE$.Char();
/* 153 */     this.Int = ManifestFactory$.MODULE$.Int();
/* 154 */     this.Long = ManifestFactory$.MODULE$.Long();
/* 155 */     this.Float = ManifestFactory$.MODULE$.Float();
/* 156 */     this.Double = ManifestFactory$.MODULE$.Double();
/* 157 */     this.Boolean = ManifestFactory$.MODULE$.Boolean();
/* 158 */     this.Unit = ManifestFactory$.MODULE$.Unit();
/* 159 */     this.Any = ManifestFactory$.MODULE$.Any();
/* 160 */     this.Object = ManifestFactory$.MODULE$.Object();
/* 161 */     this.AnyVal = ManifestFactory$.MODULE$.AnyVal();
/* 162 */     this.Nothing = ManifestFactory$.MODULE$.Nothing();
/* 163 */     this.Null = ManifestFactory$.MODULE$.Null();
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Byte() {
/*     */     return this.Byte;
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Short() {
/*     */     return this.Short;
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Char() {
/*     */     return this.Char;
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Int() {
/*     */     return this.Int;
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Long() {
/*     */     return this.Long;
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Float() {
/*     */     return this.Float;
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Double() {
/*     */     return this.Double;
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Boolean() {
/*     */     return this.Boolean;
/*     */   }
/*     */   
/*     */   public AnyValManifest<BoxedUnit> Unit() {
/*     */     return this.Unit;
/*     */   }
/*     */   
/*     */   public Manifest<Object> Any() {
/*     */     return this.Any;
/*     */   }
/*     */   
/*     */   public Manifest<Object> Object() {
/*     */     return this.Object;
/*     */   }
/*     */   
/*     */   public Manifest<Object> AnyVal() {
/*     */     return this.AnyVal;
/*     */   }
/*     */   
/*     */   public Manifest<scala.runtime.Nothing$> Nothing() {
/*     */     return this.Nothing;
/*     */   }
/*     */   
/*     */   public Manifest<scala.runtime.Null$> Null() {
/* 163 */     return this.Null;
/*     */   }
/*     */   
/*     */   public <T> ClassTag<T> fromClass(Class clazz) {
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
/*     */     //   23: invokevirtual Byte : ()Lscala/reflect/AnyValManifest;
/*     */     //   26: astore_2
/*     */     //   27: goto -> 276
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
/*     */     //   53: invokevirtual Short : ()Lscala/reflect/AnyValManifest;
/*     */     //   56: astore_2
/*     */     //   57: goto -> 276
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
/*     */     //   83: invokevirtual Char : ()Lscala/reflect/AnyValManifest;
/*     */     //   86: astore_2
/*     */     //   87: goto -> 276
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
/*     */     //   113: invokevirtual Int : ()Lscala/reflect/AnyValManifest;
/*     */     //   116: astore_2
/*     */     //   117: goto -> 276
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
/*     */     //   143: invokevirtual Long : ()Lscala/reflect/AnyValManifest;
/*     */     //   146: astore_2
/*     */     //   147: goto -> 276
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
/*     */     //   173: invokevirtual Float : ()Lscala/reflect/AnyValManifest;
/*     */     //   176: astore_2
/*     */     //   177: goto -> 276
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
/*     */     //   203: invokevirtual Double : ()Lscala/reflect/AnyValManifest;
/*     */     //   206: astore_2
/*     */     //   207: goto -> 276
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
/*     */     //   233: invokevirtual Boolean : ()Lscala/reflect/AnyValManifest;
/*     */     //   236: astore_2
/*     */     //   237: goto -> 276
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
/*     */     //   263: invokevirtual Unit : ()Lscala/reflect/AnyValManifest;
/*     */     //   266: astore_2
/*     */     //   267: goto -> 276
/*     */     //   270: aload_0
/*     */     //   271: aload_1
/*     */     //   272: invokevirtual classType : (Ljava/lang/Class;)Lscala/reflect/ClassTag;
/*     */     //   275: astore_2
/*     */     //   276: aload_2
/*     */     //   277: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #166	-> 0
/*     */     //   #165	-> 0
/*     */     //   #167	-> 30
/*     */     //   #168	-> 60
/*     */     //   #169	-> 90
/*     */     //   #170	-> 120
/*     */     //   #171	-> 150
/*     */     //   #172	-> 180
/*     */     //   #173	-> 210
/*     */     //   #174	-> 240
/*     */     //   #175	-> 270
/*     */     //   #165	-> 276
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	278	0	this	Lscala/reflect/ClassManifestFactory$;
/*     */     //   0	278	1	clazz	Ljava/lang/Class;
/*     */   }
/*     */   
/*     */   public <T> Manifest<T> singleType(Object value) {
/* 178 */     return package$.MODULE$.Manifest().singleType(value);
/*     */   }
/*     */   
/*     */   public <T> ClassTag<T> classType(Class<?> clazz) {
/* 188 */     return new ClassTypeManifest<T>((Option<OptManifest<?>>)scala.None$.MODULE$, clazz, (List<OptManifest<?>>)scala.collection.immutable.Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   public <T> ClassTag<T> classType(Class<?> clazz, OptManifest arg1, Seq args) {
/* 193 */     return new ClassTypeManifest<T>((Option<OptManifest<?>>)scala.None$.MODULE$, clazz, args.toList().$colon$colon(arg1));
/*     */   }
/*     */   
/*     */   public <T> ClassTag<T> classType(OptManifest prefix, Class<?> clazz, Seq args) {
/* 199 */     return new ClassTypeManifest<T>((Option<OptManifest<?>>)new Some(prefix), clazz, args.toList());
/*     */   }
/*     */   
/*     */   public <T> ClassTag<Object> arrayType(OptManifest arg) {
/* 201 */     if (NoManifest$.MODULE$ == null) {
/* 201 */       if (arg != null)
/* 203 */         if (arg instanceof ClassTag) {
/* 203 */           ClassTag classTag = (ClassTag)arg;
/* 203 */           return classTag.arrayManifest();
/*     */         }  
/*     */     } else {
/*     */       if (NoManifest$.MODULE$.equals(arg))
/*     */         return Object(); 
/* 203 */       if (arg instanceof ClassTag) {
/* 203 */         ClassTag classTag = (ClassTag)arg;
/* 203 */         return classTag.arrayManifest();
/*     */       } 
/*     */     } 
/*     */     return Object();
/*     */   }
/*     */   
/*     */   public <T> ClassTag<T> abstractType(OptManifest prefix, String name, Class clazz, Seq args) {
/* 210 */     return new ClassManifestFactory$$anon$1(prefix, name, clazz, args);
/*     */   }
/*     */   
/*     */   public static class ClassManifestFactory$$anon$1 implements ClassTag<T> {
/*     */     private final List<OptManifest<?>> typeArguments;
/*     */     
/*     */     private final OptManifest prefix$1;
/*     */     
/*     */     private final String name$1;
/*     */     
/*     */     private final Class clazz$1;
/*     */     
/*     */     public ClassTag<Object> wrap() {
/* 210 */       return ClassTag$class.wrap(this);
/*     */     }
/*     */     
/*     */     public Object newArray(int len) {
/* 210 */       return ClassTag$class.newArray(this, len);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(Object x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(byte x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(short x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(char x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(int x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(long x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(float x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(double x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(boolean x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(BoxedUnit x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x) {
/* 210 */       return ClassTag$class.canEqual(this, x);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x) {
/* 210 */       return ClassTag$class.equals(this, x);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 210 */       return ClassTag$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public Class<?> erasure() {
/* 210 */       return ClassManifestDeprecatedApis$class.erasure(this);
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 210 */       return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
/*     */     }
/*     */     
/*     */     public boolean $greater$colon$greater(ClassTag that) {
/* 210 */       return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*     */     }
/*     */     
/*     */     public <T> Class<Object> arrayClass(Class tp) {
/* 210 */       return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*     */     }
/*     */     
/*     */     public ClassTag<Object> arrayManifest() {
/* 210 */       return ClassManifestDeprecatedApis$class.arrayManifest(this);
/*     */     }
/*     */     
/*     */     public Object[] newArray2(int len) {
/* 210 */       return ClassManifestDeprecatedApis$class.newArray2(this, len);
/*     */     }
/*     */     
/*     */     public Object[][] newArray3(int len) {
/* 210 */       return ClassManifestDeprecatedApis$class.newArray3(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][] newArray4(int len) {
/* 210 */       return ClassManifestDeprecatedApis$class.newArray4(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][][] newArray5(int len) {
/* 210 */       return ClassManifestDeprecatedApis$class.newArray5(this, len);
/*     */     }
/*     */     
/*     */     public WrappedArray<T> newWrappedArray(int len) {
/* 210 */       return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<T> newArrayBuilder() {
/* 210 */       return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*     */     }
/*     */     
/*     */     public String argString() {
/* 210 */       return ClassManifestDeprecatedApis$class.argString(this);
/*     */     }
/*     */     
/*     */     public ClassManifestFactory$$anon$1(OptManifest prefix$1, String name$1, Class clazz$1, Seq args$1) {
/* 210 */       ClassManifestDeprecatedApis$class.$init$(this);
/* 210 */       ClassTag$class.$init$(this);
/* 212 */       this.typeArguments = args$1.toList();
/*     */     }
/*     */     
/*     */     public Class<?> runtimeClass() {
/*     */       return this.clazz$1;
/*     */     }
/*     */     
/*     */     public List<OptManifest<?>> typeArguments() {
/* 212 */       return this.typeArguments;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 213 */       return (new StringBuilder()).append(this.prefix$1.toString()).append("#").append(this.name$1).append(argString()).toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> ClassTag<T> abstractType(OptManifest prefix, String name, ClassTag upperbound, Seq args) {
/* 222 */     return new ClassManifestFactory$$anon$2(prefix, name, upperbound, args);
/*     */   }
/*     */   
/*     */   public static class ClassManifestFactory$$anon$2 implements ClassTag<T> {
/*     */     private final List<OptManifest<?>> typeArguments;
/*     */     
/*     */     private final OptManifest prefix$2;
/*     */     
/*     */     private final String name$2;
/*     */     
/*     */     private final ClassTag upperbound$1;
/*     */     
/*     */     public ClassTag<Object> wrap() {
/* 222 */       return ClassTag$class.wrap(this);
/*     */     }
/*     */     
/*     */     public Object newArray(int len) {
/* 222 */       return ClassTag$class.newArray(this, len);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(Object x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(byte x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(short x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(char x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(int x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(long x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(float x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(double x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(boolean x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(BoxedUnit x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x) {
/* 222 */       return ClassTag$class.canEqual(this, x);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x) {
/* 222 */       return ClassTag$class.equals(this, x);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 222 */       return ClassTag$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public Class<?> erasure() {
/* 222 */       return ClassManifestDeprecatedApis$class.erasure(this);
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 222 */       return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
/*     */     }
/*     */     
/*     */     public boolean $greater$colon$greater(ClassTag that) {
/* 222 */       return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*     */     }
/*     */     
/*     */     public <T> Class<Object> arrayClass(Class tp) {
/* 222 */       return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*     */     }
/*     */     
/*     */     public ClassTag<Object> arrayManifest() {
/* 222 */       return ClassManifestDeprecatedApis$class.arrayManifest(this);
/*     */     }
/*     */     
/*     */     public Object[] newArray2(int len) {
/* 222 */       return ClassManifestDeprecatedApis$class.newArray2(this, len);
/*     */     }
/*     */     
/*     */     public Object[][] newArray3(int len) {
/* 222 */       return ClassManifestDeprecatedApis$class.newArray3(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][] newArray4(int len) {
/* 222 */       return ClassManifestDeprecatedApis$class.newArray4(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][][] newArray5(int len) {
/* 222 */       return ClassManifestDeprecatedApis$class.newArray5(this, len);
/*     */     }
/*     */     
/*     */     public WrappedArray<T> newWrappedArray(int len) {
/* 222 */       return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<T> newArrayBuilder() {
/* 222 */       return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*     */     }
/*     */     
/*     */     public String argString() {
/* 222 */       return ClassManifestDeprecatedApis$class.argString(this);
/*     */     }
/*     */     
/*     */     public ClassManifestFactory$$anon$2(OptManifest prefix$2, String name$2, ClassTag upperbound$1, Seq args$2) {
/* 222 */       ClassManifestDeprecatedApis$class.$init$(this);
/* 222 */       ClassTag$class.$init$(this);
/* 224 */       this.typeArguments = args$2.toList();
/*     */     }
/*     */     
/*     */     public Class<?> runtimeClass() {
/*     */       return this.upperbound$1.erasure();
/*     */     }
/*     */     
/*     */     public List<OptManifest<?>> typeArguments() {
/* 224 */       return this.typeArguments;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 225 */       return (new StringBuilder()).append(this.prefix$2.toString()).append("#").append(this.name$2).append(argString()).toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\ClassManifestFactory$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */