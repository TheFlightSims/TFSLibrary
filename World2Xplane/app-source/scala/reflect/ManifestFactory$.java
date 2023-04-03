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
/*     */ public final class ManifestFactory$ {
/*     */   public static final ManifestFactory$ MODULE$;
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
/*     */   private final Class<Object> scala$reflect$ManifestFactory$$ObjectTYPE;
/*     */   
/*     */   private final Class<scala.runtime.Nothing$> scala$reflect$ManifestFactory$$NothingTYPE;
/*     */   
/*     */   private final Class<scala.runtime.Null$> scala$reflect$ManifestFactory$$NullTYPE;
/*     */   
/*     */   private final Manifest<Object> Any;
/*     */   
/*     */   private final Manifest<Object> Object;
/*     */   
/*     */   private final Manifest<Object> AnyRef;
/*     */   
/*     */   private final Manifest<Object> AnyVal;
/*     */   
/*     */   private final Manifest<scala.runtime.Null$> Null;
/*     */   
/*     */   private final Manifest<scala.runtime.Nothing$> Nothing;
/*     */   
/*     */   private ManifestFactory$() {
/*  84 */     MODULE$ = this;
/*  88 */     this.Byte = new ManifestFactory.$anon$6();
/*  96 */     this.Short = new ManifestFactory.$anon$7();
/* 104 */     this.Char = new ManifestFactory.$anon$8();
/* 112 */     this.Int = new ManifestFactory.$anon$9();
/* 120 */     this.Long = new ManifestFactory.$anon$10();
/* 128 */     this.Float = new ManifestFactory.$anon$11();
/* 136 */     this.Double = new ManifestFactory.$anon$12();
/* 144 */     this.Boolean = new ManifestFactory.$anon$13();
/* 152 */     this.Unit = new ManifestFactory.$anon$14();
/* 160 */     this.scala$reflect$ManifestFactory$$ObjectTYPE = Object.class;
/* 161 */     this.scala$reflect$ManifestFactory$$NothingTYPE = scala.runtime.Nothing$.class;
/* 162 */     this.scala$reflect$ManifestFactory$$NullTYPE = scala.runtime.Null$.class;
/* 164 */     this.Any = new ManifestFactory.$anon$1();
/* 170 */     this.Object = new ManifestFactory.$anon$2();
/* 176 */     this.AnyRef = Object();
/* 178 */     this.AnyVal = new ManifestFactory.$anon$3();
/* 184 */     this.Null = new ManifestFactory.$anon$4();
/* 191 */     this.Nothing = new ManifestFactory.$anon$5();
/*     */   }
/*     */   
/*     */   public List<AnyValManifest<?>> valueManifests() {
/*     */     (new AnyValManifest[9])[0] = Byte();
/*     */     (new AnyValManifest[9])[1] = Short();
/*     */     (new AnyValManifest[9])[2] = Char();
/*     */     (new AnyValManifest[9])[3] = Int();
/*     */     (new AnyValManifest[9])[4] = Long();
/*     */     (new AnyValManifest[9])[5] = Float();
/*     */     (new AnyValManifest[9])[6] = Double();
/*     */     (new AnyValManifest[9])[7] = Boolean();
/*     */     (new AnyValManifest[9])[8] = Unit();
/*     */     return scala.collection.immutable.List$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new AnyValManifest[9]));
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Byte() {
/*     */     return this.Byte;
/*     */   }
/*     */   
/*     */   public static class $anon$6 extends AnyValManifest<Object> {
/*     */     public $anon$6() {
/*     */       super("Byte");
/*     */     }
/*     */     
/*     */     public Class<Byte> runtimeClass() {
/*     */       return (Class)byte.class;
/*     */     }
/*     */     
/*     */     public byte[] newArray(int len) {
/*     */       return new byte[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/*     */       return (WrappedArray<Object>)new WrappedArray.ofByte(new byte[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/*     */       return (ArrayBuilder<Object>)new ArrayBuilder.ofByte();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return package$.MODULE$.Manifest().Byte();
/*     */     }
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Short() {
/*     */     return this.Short;
/*     */   }
/*     */   
/*     */   public static class $anon$7 extends AnyValManifest<Object> {
/*     */     public $anon$7() {
/*     */       super("Short");
/*     */     }
/*     */     
/*     */     public Class<Short> runtimeClass() {
/*     */       return (Class)short.class;
/*     */     }
/*     */     
/*     */     public short[] newArray(int len) {
/*     */       return new short[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/*     */       return (WrappedArray<Object>)new WrappedArray.ofShort(new short[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/*     */       return (ArrayBuilder<Object>)new ArrayBuilder.ofShort();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return package$.MODULE$.Manifest().Short();
/*     */     }
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Char() {
/*     */     return this.Char;
/*     */   }
/*     */   
/*     */   public static class $anon$8 extends AnyValManifest<Object> {
/*     */     public $anon$8() {
/*     */       super("Char");
/*     */     }
/*     */     
/*     */     public Class<Character> runtimeClass() {
/*     */       return (Class)char.class;
/*     */     }
/*     */     
/*     */     public char[] newArray(int len) {
/*     */       return new char[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/*     */       return (WrappedArray<Object>)new WrappedArray.ofChar(new char[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/*     */       return (ArrayBuilder<Object>)new ArrayBuilder.ofChar();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return package$.MODULE$.Manifest().Char();
/*     */     }
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Int() {
/*     */     return this.Int;
/*     */   }
/*     */   
/*     */   public static class $anon$9 extends AnyValManifest<Object> {
/*     */     public $anon$9() {
/*     */       super("Int");
/*     */     }
/*     */     
/*     */     public Class<Integer> runtimeClass() {
/*     */       return (Class)int.class;
/*     */     }
/*     */     
/*     */     public int[] newArray(int len) {
/*     */       return new int[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/*     */       return (WrappedArray<Object>)new WrappedArray.ofInt(new int[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/*     */       return (ArrayBuilder<Object>)new ArrayBuilder.ofInt();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return package$.MODULE$.Manifest().Int();
/*     */     }
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Long() {
/*     */     return this.Long;
/*     */   }
/*     */   
/*     */   public static class $anon$10 extends AnyValManifest<Object> {
/*     */     public $anon$10() {
/*     */       super("Long");
/*     */     }
/*     */     
/*     */     public Class<Long> runtimeClass() {
/*     */       return (Class)long.class;
/*     */     }
/*     */     
/*     */     public long[] newArray(int len) {
/*     */       return new long[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/*     */       return (WrappedArray<Object>)new WrappedArray.ofLong(new long[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/*     */       return (ArrayBuilder<Object>)new ArrayBuilder.ofLong();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return package$.MODULE$.Manifest().Long();
/*     */     }
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Float() {
/*     */     return this.Float;
/*     */   }
/*     */   
/*     */   public static class $anon$11 extends AnyValManifest<Object> {
/*     */     public $anon$11() {
/*     */       super("Float");
/*     */     }
/*     */     
/*     */     public Class<Float> runtimeClass() {
/*     */       return (Class)float.class;
/*     */     }
/*     */     
/*     */     public float[] newArray(int len) {
/*     */       return new float[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/*     */       return (WrappedArray<Object>)new WrappedArray.ofFloat(new float[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/*     */       return (ArrayBuilder<Object>)new ArrayBuilder.ofFloat();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return package$.MODULE$.Manifest().Float();
/*     */     }
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Double() {
/*     */     return this.Double;
/*     */   }
/*     */   
/*     */   public static class $anon$12 extends AnyValManifest<Object> {
/*     */     public $anon$12() {
/*     */       super("Double");
/*     */     }
/*     */     
/*     */     public Class<Double> runtimeClass() {
/*     */       return (Class)double.class;
/*     */     }
/*     */     
/*     */     public double[] newArray(int len) {
/*     */       return new double[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/*     */       return (WrappedArray<Object>)new WrappedArray.ofDouble(new double[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/*     */       return (ArrayBuilder<Object>)new ArrayBuilder.ofDouble();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return package$.MODULE$.Manifest().Double();
/*     */     }
/*     */   }
/*     */   
/*     */   public AnyValManifest<Object> Boolean() {
/*     */     return this.Boolean;
/*     */   }
/*     */   
/*     */   public static class $anon$13 extends AnyValManifest<Object> {
/*     */     public $anon$13() {
/*     */       super("Boolean");
/*     */     }
/*     */     
/*     */     public Class<Boolean> runtimeClass() {
/*     */       return (Class)boolean.class;
/*     */     }
/*     */     
/*     */     public boolean[] newArray(int len) {
/*     */       return new boolean[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/*     */       return (WrappedArray<Object>)new WrappedArray.ofBoolean(new boolean[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/*     */       return (ArrayBuilder<Object>)new ArrayBuilder.ofBoolean();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return package$.MODULE$.Manifest().Boolean();
/*     */     }
/*     */   }
/*     */   
/*     */   public AnyValManifest<BoxedUnit> Unit() {
/*     */     return this.Unit;
/*     */   }
/*     */   
/*     */   public static class $anon$14 extends AnyValManifest<BoxedUnit> {
/*     */     public $anon$14() {
/*     */       super("Unit");
/*     */     }
/*     */     
/*     */     public Class<Void> runtimeClass() {
/*     */       return (Class)void.class;
/*     */     }
/*     */     
/*     */     public BoxedUnit[] newArray(int len) {
/*     */       return new BoxedUnit[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<BoxedUnit> newWrappedArray(int len) {
/*     */       return (WrappedArray<BoxedUnit>)new WrappedArray.ofUnit(new BoxedUnit[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<BoxedUnit> newArrayBuilder() {
/*     */       return (ArrayBuilder<BoxedUnit>)new ArrayBuilder.ofUnit();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return package$.MODULE$.Manifest().Unit();
/*     */     }
/*     */   }
/*     */   
/*     */   public Class<Object> scala$reflect$ManifestFactory$$ObjectTYPE() {
/*     */     return this.scala$reflect$ManifestFactory$$ObjectTYPE;
/*     */   }
/*     */   
/*     */   public Class<scala.runtime.Nothing$> scala$reflect$ManifestFactory$$NothingTYPE() {
/*     */     return this.scala$reflect$ManifestFactory$$NothingTYPE;
/*     */   }
/*     */   
/*     */   public Class<scala.runtime.Null$> scala$reflect$ManifestFactory$$NullTYPE() {
/*     */     return this.scala$reflect$ManifestFactory$$NullTYPE;
/*     */   }
/*     */   
/*     */   public Manifest<Object> Any() {
/*     */     return this.Any;
/*     */   }
/*     */   
/*     */   public static class $anon$1 extends ManifestFactory.PhantomManifest<Object> {
/*     */     public $anon$1() {
/*     */       super(ManifestFactory$.MODULE$.scala$reflect$ManifestFactory$$ObjectTYPE(), "Any");
/*     */     }
/*     */     
/*     */     public Object[] newArray(int len) {
/*     */       return new Object[len];
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/*     */       return (that == this);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return package$.MODULE$.Manifest().Any();
/*     */     }
/*     */   }
/*     */   
/*     */   public Manifest<Object> Object() {
/*     */     return this.Object;
/*     */   }
/*     */   
/*     */   public static class $anon$2 extends ManifestFactory.PhantomManifest<Object> {
/*     */     public $anon$2() {
/*     */       super(ManifestFactory$.MODULE$.scala$reflect$ManifestFactory$$ObjectTYPE(), "Object");
/*     */     }
/*     */     
/*     */     public Object[] newArray(int len) {
/*     */       return new Object[len];
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/*     */       return (that == this || that == ManifestFactory$.MODULE$.Any());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return package$.MODULE$.Manifest().Object();
/*     */     }
/*     */   }
/*     */   
/*     */   public Manifest<Object> AnyRef() {
/*     */     return this.AnyRef;
/*     */   }
/*     */   
/*     */   public Manifest<Object> AnyVal() {
/*     */     return this.AnyVal;
/*     */   }
/*     */   
/*     */   public static class $anon$3 extends ManifestFactory.PhantomManifest<Object> {
/*     */     public $anon$3() {
/*     */       super(ManifestFactory$.MODULE$.scala$reflect$ManifestFactory$$ObjectTYPE(), "AnyVal");
/*     */     }
/*     */     
/*     */     public Object[] newArray(int len) {
/*     */       return new Object[len];
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/*     */       return (that == this || that == ManifestFactory$.MODULE$.Any());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return package$.MODULE$.Manifest().AnyVal();
/*     */     }
/*     */   }
/*     */   
/*     */   public Manifest<scala.runtime.Null$> Null() {
/*     */     return this.Null;
/*     */   }
/*     */   
/*     */   public static class $anon$4 extends ManifestFactory.PhantomManifest<scala.runtime.Null$> {
/*     */     public $anon$4() {
/*     */       super(ManifestFactory$.MODULE$.scala$reflect$ManifestFactory$$NullTYPE(), "Null");
/*     */     }
/*     */     
/*     */     public Object[] newArray(int len) {
/*     */       return new Object[len];
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/*     */       return (that != null && that != ManifestFactory$.MODULE$.Nothing() && !that.$less$colon$less(ManifestFactory$.MODULE$.AnyVal()));
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return package$.MODULE$.Manifest().Null();
/*     */     }
/*     */   }
/*     */   
/*     */   public Manifest<scala.runtime.Nothing$> Nothing() {
/* 191 */     return this.Nothing;
/*     */   }
/*     */   
/*     */   public static class $anon$5 extends ManifestFactory.PhantomManifest<scala.runtime.Nothing$> {
/*     */     public $anon$5() {
/* 191 */       super(ManifestFactory$.MODULE$.scala$reflect$ManifestFactory$$NothingTYPE(), "Nothing");
/*     */     }
/*     */     
/*     */     public Object[] newArray(int len) {
/* 192 */       return new Object[len];
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 193 */       return (that != null);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 194 */       return package$.MODULE$.Manifest().Nothing();
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Manifest<T> singleType(Object value) {
/* 204 */     return new ManifestFactory.SingletonTypeManifest<T>(value);
/*     */   }
/*     */   
/*     */   public <T> Manifest<T> classType(Class<?> clazz) {
/* 214 */     return new ManifestFactory.ClassTypeManifest<T>((Option<Manifest<?>>)scala.None$.MODULE$, clazz, (List<Manifest<?>>)scala.collection.immutable.Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   public <T> Manifest<T> classType(Class<?> clazz, Manifest arg1, Seq args) {
/* 219 */     return new ManifestFactory.ClassTypeManifest<T>((Option<Manifest<?>>)scala.None$.MODULE$, clazz, args.toList().$colon$colon(arg1));
/*     */   }
/*     */   
/*     */   public <T> Manifest<T> classType(Manifest prefix, Class<?> clazz, Seq args) {
/* 225 */     return new ManifestFactory.ClassTypeManifest<T>((Option<Manifest<?>>)new Some(prefix), clazz, args.toList());
/*     */   }
/*     */   
/*     */   public <T> Manifest<Object> arrayType(Manifest arg) {
/* 245 */     return arg.arrayManifest();
/*     */   }
/*     */   
/*     */   public <T> Manifest<T> abstractType(Manifest prefix, String name, Class upperBound, Seq args) {
/* 251 */     return new ManifestFactory$$anon$15(prefix, name, upperBound, args);
/*     */   }
/*     */   
/*     */   public static class ManifestFactory$$anon$15 implements Manifest<T> {
/*     */     private final List<Manifest<?>> typeArguments;
/*     */     
/*     */     private final Manifest prefix$1;
/*     */     
/*     */     private final String name$1;
/*     */     
/*     */     private final Class upperBound$1;
/*     */     
/*     */     public Manifest<Object> arrayManifest() {
/* 251 */       return Manifest$class.arrayManifest(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/* 251 */       return Manifest$class.canEqual(this, that);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 251 */       return Manifest$class.equals(this, that);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 251 */       return Manifest$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public ClassTag<Object> wrap() {
/* 251 */       return ClassTag$class.wrap(this);
/*     */     }
/*     */     
/*     */     public Object newArray(int len) {
/* 251 */       return ClassTag$class.newArray(this, len);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(Object x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(byte x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(short x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(char x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(int x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(long x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(float x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(double x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(boolean x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(BoxedUnit x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Class<?> erasure() {
/* 251 */       return ClassManifestDeprecatedApis$class.erasure(this);
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 251 */       return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
/*     */     }
/*     */     
/*     */     public boolean $greater$colon$greater(ClassTag that) {
/* 251 */       return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*     */     }
/*     */     
/*     */     public <T> Class<Object> arrayClass(Class tp) {
/* 251 */       return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*     */     }
/*     */     
/*     */     public Object[] newArray2(int len) {
/* 251 */       return ClassManifestDeprecatedApis$class.newArray2(this, len);
/*     */     }
/*     */     
/*     */     public Object[][] newArray3(int len) {
/* 251 */       return ClassManifestDeprecatedApis$class.newArray3(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][] newArray4(int len) {
/* 251 */       return ClassManifestDeprecatedApis$class.newArray4(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][][] newArray5(int len) {
/* 251 */       return ClassManifestDeprecatedApis$class.newArray5(this, len);
/*     */     }
/*     */     
/*     */     public WrappedArray<T> newWrappedArray(int len) {
/* 251 */       return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<T> newArrayBuilder() {
/* 251 */       return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*     */     }
/*     */     
/*     */     public String argString() {
/* 251 */       return ClassManifestDeprecatedApis$class.argString(this);
/*     */     }
/*     */     
/*     */     public ManifestFactory$$anon$15(Manifest prefix$1, String name$1, Class upperBound$1, Seq args$1) {
/* 251 */       ClassManifestDeprecatedApis$class.$init$(this);
/* 251 */       ClassTag$class.$init$(this);
/* 251 */       Manifest$class.$init$(this);
/* 253 */       this.typeArguments = args$1.toList();
/*     */     }
/*     */     
/*     */     public Class<?> runtimeClass() {
/*     */       return this.upperBound$1;
/*     */     }
/*     */     
/*     */     public List<Manifest<?>> typeArguments() {
/* 253 */       return this.typeArguments;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 254 */       return (new StringBuilder()).append(this.prefix$1.toString()).append("#").append(this.name$1).append(argString()).toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Manifest<T> wildcardType(Manifest lowerBound, Manifest upperBound) {
/* 260 */     return new ManifestFactory$$anon$16(lowerBound, upperBound);
/*     */   }
/*     */   
/*     */   public static class ManifestFactory$$anon$16 implements Manifest<T> {
/*     */     private final Manifest lowerBound$1;
/*     */     
/*     */     private final Manifest upperBound$2;
/*     */     
/*     */     public List<Manifest<?>> typeArguments() {
/* 260 */       return Manifest$class.typeArguments(this);
/*     */     }
/*     */     
/*     */     public Manifest<Object> arrayManifest() {
/* 260 */       return Manifest$class.arrayManifest(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/* 260 */       return Manifest$class.canEqual(this, that);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 260 */       return Manifest$class.equals(this, that);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 260 */       return Manifest$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public ClassTag<Object> wrap() {
/* 260 */       return ClassTag$class.wrap(this);
/*     */     }
/*     */     
/*     */     public Object newArray(int len) {
/* 260 */       return ClassTag$class.newArray(this, len);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(Object x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(byte x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(short x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(char x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(int x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(long x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(float x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(double x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(boolean x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(BoxedUnit x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Class<?> erasure() {
/* 260 */       return ClassManifestDeprecatedApis$class.erasure(this);
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 260 */       return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
/*     */     }
/*     */     
/*     */     public boolean $greater$colon$greater(ClassTag that) {
/* 260 */       return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*     */     }
/*     */     
/*     */     public <T> Class<Object> arrayClass(Class tp) {
/* 260 */       return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*     */     }
/*     */     
/*     */     public Object[] newArray2(int len) {
/* 260 */       return ClassManifestDeprecatedApis$class.newArray2(this, len);
/*     */     }
/*     */     
/*     */     public Object[][] newArray3(int len) {
/* 260 */       return ClassManifestDeprecatedApis$class.newArray3(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][] newArray4(int len) {
/* 260 */       return ClassManifestDeprecatedApis$class.newArray4(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][][] newArray5(int len) {
/* 260 */       return ClassManifestDeprecatedApis$class.newArray5(this, len);
/*     */     }
/*     */     
/*     */     public WrappedArray<T> newWrappedArray(int len) {
/* 260 */       return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<T> newArrayBuilder() {
/* 260 */       return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*     */     }
/*     */     
/*     */     public String argString() {
/* 260 */       return ClassManifestDeprecatedApis$class.argString(this);
/*     */     }
/*     */     
/*     */     public ManifestFactory$$anon$16(Manifest lowerBound$1, Manifest upperBound$2) {
/* 260 */       ClassManifestDeprecatedApis$class.$init$(this);
/* 260 */       ClassTag$class.$init$(this);
/* 260 */       Manifest$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Class<?> runtimeClass() {
/* 261 */       return this.upperBound$2.erasure();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 264 */       return (new StringBuilder()).append("_").append((this.lowerBound$1 == ManifestFactory$.MODULE$.Nothing()) ? "" : (new StringBuilder()).append(" >: ").append(this.lowerBound$1).toString())
/* 265 */         .append((this.upperBound$2 == ManifestFactory$.MODULE$.Nothing()) ? "" : (new StringBuilder()).append(" <: ").append(this.upperBound$2).toString()).toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Manifest<T> intersectionType(Seq parents) {
/* 270 */     return new ManifestFactory$$anon$17(parents);
/*     */   }
/*     */   
/*     */   public static class ManifestFactory$$anon$17 implements Manifest<T> {
/*     */     private final Seq parents$1;
/*     */     
/*     */     public List<Manifest<?>> typeArguments() {
/* 270 */       return Manifest$class.typeArguments(this);
/*     */     }
/*     */     
/*     */     public Manifest<Object> arrayManifest() {
/* 270 */       return Manifest$class.arrayManifest(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/* 270 */       return Manifest$class.canEqual(this, that);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 270 */       return Manifest$class.equals(this, that);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 270 */       return Manifest$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public ClassTag<Object> wrap() {
/* 270 */       return ClassTag$class.wrap(this);
/*     */     }
/*     */     
/*     */     public Object newArray(int len) {
/* 270 */       return ClassTag$class.newArray(this, len);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(Object x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(byte x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(short x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(char x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(int x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(long x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(float x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(double x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(boolean x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(BoxedUnit x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Class<?> erasure() {
/* 270 */       return ClassManifestDeprecatedApis$class.erasure(this);
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 270 */       return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
/*     */     }
/*     */     
/*     */     public boolean $greater$colon$greater(ClassTag that) {
/* 270 */       return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*     */     }
/*     */     
/*     */     public <T> Class<Object> arrayClass(Class tp) {
/* 270 */       return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*     */     }
/*     */     
/*     */     public Object[] newArray2(int len) {
/* 270 */       return ClassManifestDeprecatedApis$class.newArray2(this, len);
/*     */     }
/*     */     
/*     */     public Object[][] newArray3(int len) {
/* 270 */       return ClassManifestDeprecatedApis$class.newArray3(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][] newArray4(int len) {
/* 270 */       return ClassManifestDeprecatedApis$class.newArray4(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][][] newArray5(int len) {
/* 270 */       return ClassManifestDeprecatedApis$class.newArray5(this, len);
/*     */     }
/*     */     
/*     */     public WrappedArray<T> newWrappedArray(int len) {
/* 270 */       return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<T> newArrayBuilder() {
/* 270 */       return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*     */     }
/*     */     
/*     */     public String argString() {
/* 270 */       return ClassManifestDeprecatedApis$class.argString(this);
/*     */     }
/*     */     
/*     */     public ManifestFactory$$anon$17(Seq parents$1) {
/* 270 */       ClassManifestDeprecatedApis$class.$init$(this);
/* 270 */       ClassTag$class.$init$(this);
/* 270 */       Manifest$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Class<?> runtimeClass() {
/* 271 */       return ((ClassManifestDeprecatedApis)this.parents$1.head()).erasure();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 272 */       return this.parents$1.mkString(" with ");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\ManifestFactory$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */