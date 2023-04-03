/*     */ package scala;
/*     */ 
/*     */ import scala.collection.IndexedSeq;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map$;
/*     */ import scala.collection.immutable.Set$;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.ArrayOps;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.mutable.StringBuilder$;
/*     */ import scala.reflect.ClassManifestFactory$;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.Manifest;
/*     */ import scala.reflect.ManifestFactory$;
/*     */ import scala.reflect.NoManifest$;
/*     */ import scala.reflect.OptManifest;
/*     */ import scala.reflect.package$;
/*     */ import scala.runtime.ArrayCharSequence;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.RichException;
/*     */ import scala.runtime.SeqCharSequence;
/*     */ import scala.sys.package$;
/*     */ import scala.xml.TopScope$;
/*     */ 
/*     */ public final class Predef$ extends LowPriorityImplicits {
/*     */   public static final Predef$ MODULE$;
/*     */   
/*     */   private final Map$ Map;
/*     */   
/*     */   private final Set$ Set;
/*     */   
/*     */   private final ClassManifestFactory$ ClassManifest;
/*     */   
/*     */   private final ManifestFactory$ Manifest;
/*     */   
/*     */   private final NoManifest$ NoManifest;
/*     */   
/*     */   private final TopScope$ $scope;
/*     */   
/*     */   private final CanBuildFrom<String, Object, String> StringCanBuildFrom;
/*     */   
/*     */   private final Predef.$less$colon$less<Object, Object> singleton_$less$colon$less;
/*     */   
/*     */   public final Predef.$eq$colon$eq<Object, Object> scala$Predef$$singleton_$eq$colon$eq;
/*     */   
/*     */   private Predef$() {
/*  71 */     MODULE$ = this;
/*  97 */     this.Map = Map$.MODULE$;
/*  98 */     this.Set = Set$.MODULE$;
/* 114 */     this.ClassManifest = package$.MODULE$.ClassManifest();
/* 117 */     this.Manifest = package$.MODULE$.Manifest();
/* 120 */     this.NoManifest = NoManifest$.MODULE$;
/* 137 */     this.$scope = TopScope$.MODULE$;
/* 408 */     this.StringCanBuildFrom = new Predef.$anon$3();
/* 434 */     this.singleton_$less$colon$less = new Predef.$anon$1();
/* 445 */     this.scala$Predef$$singleton_$eq$colon$eq = new Predef.$anon$2();
/*     */   }
/*     */   
/*     */   public Map$ Map() {
/*     */     return this.Map;
/*     */   }
/*     */   
/*     */   public Set$ Set() {
/*     */     return this.Set;
/*     */   }
/*     */   
/*     */   public ClassManifestFactory$ ClassManifest() {
/*     */     return this.ClassManifest;
/*     */   }
/*     */   
/*     */   public ManifestFactory$ Manifest() {
/*     */     return this.Manifest;
/*     */   }
/*     */   
/*     */   public NoManifest$ NoManifest() {
/*     */     return this.NoManifest;
/*     */   }
/*     */   
/*     */   public <T> Manifest<T> manifest(Manifest<T> m) {
/*     */     return m;
/*     */   }
/*     */   
/*     */   public <T> ClassTag<T> classManifest(ClassTag<T> m) {
/*     */     return m;
/*     */   }
/*     */   
/*     */   public <T> OptManifest<T> optManifest(OptManifest<T> m) {
/*     */     return m;
/*     */   }
/*     */   
/*     */   public <A> A identity(Object x) {
/*     */     return (A)x;
/*     */   }
/*     */   
/*     */   public <T> T implicitly(Object e) {
/*     */     return (T)e;
/*     */   }
/*     */   
/*     */   public <T> T locally(Object x) {
/*     */     return (T)x;
/*     */   }
/*     */   
/*     */   public TopScope$ $scope() {
/*     */     return this.$scope;
/*     */   }
/*     */   
/*     */   public Nothing$ error(String message) {
/*     */     return package$.MODULE$.error(message);
/*     */   }
/*     */   
/*     */   public Nothing$ exit() {
/*     */     return package$.MODULE$.exit();
/*     */   }
/*     */   
/*     */   public Nothing$ exit(int status) {
/*     */     return package$.MODULE$.exit(status);
/*     */   }
/*     */   
/*     */   public String format(String text, Seq xs) {
/*     */     return (new StringOps(text)).format(xs);
/*     */   }
/*     */   
/*     */   public void assert(boolean assertion) {
/*     */     if (assertion)
/*     */       return; 
/*     */     throw new AssertionError("assertion failed");
/*     */   }
/*     */   
/*     */   public final void assert(boolean assertion, Function0 message) {
/*     */     if (assertion)
/*     */       return; 
/*     */     throw new AssertionError((new StringBuilder()).append("assertion failed: ").append(message.apply()).toString());
/*     */   }
/*     */   
/*     */   public void assume(boolean assumption) {
/*     */     if (assumption)
/*     */       return; 
/*     */     throw new AssertionError("assumption failed");
/*     */   }
/*     */   
/*     */   public final void assume(boolean assumption, Function0 message) {
/*     */     if (assumption)
/*     */       return; 
/*     */     throw new AssertionError((new StringBuilder()).append("assumption failed: ").append(message.apply()).toString());
/*     */   }
/*     */   
/*     */   public void require(boolean requirement) {
/*     */     if (requirement)
/*     */       return; 
/*     */     throw new IllegalArgumentException("requirement failed");
/*     */   }
/*     */   
/*     */   public final void require(boolean requirement, Function0 message) {
/*     */     if (requirement)
/*     */       return; 
/*     */     throw new IllegalArgumentException((new StringBuilder()).append("requirement failed: ").append(message.apply()).toString());
/*     */   }
/*     */   
/*     */   public <A> A any2Ensuring(Object x) {
/*     */     return (A)x;
/*     */   }
/*     */   
/*     */   public Nothing$ $qmark$qmark$qmark() {
/*     */     throw new NotImplementedError();
/*     */   }
/*     */   
/*     */   public <A> A any2ArrowAssoc(Object x) {
/*     */     return (A)x;
/*     */   }
/*     */   
/*     */   public void print(Object x) {
/*     */     Console$.MODULE$.print(x);
/*     */   }
/*     */   
/*     */   public void println() {
/*     */     Console$.MODULE$.println();
/*     */   }
/*     */   
/*     */   public void println(Object x) {
/*     */     Console$.MODULE$.println(x);
/*     */   }
/*     */   
/*     */   public void printf(String text, Seq xs) {
/*     */     Console$.MODULE$.print((new StringOps(text)).format(xs));
/*     */   }
/*     */   
/*     */   public String readLine() {
/*     */     return Console$.MODULE$.readLine();
/*     */   }
/*     */   
/*     */   public String readLine(String text, Seq<Object> args) {
/*     */     return Console$.MODULE$.readLine(text, args);
/*     */   }
/*     */   
/*     */   public boolean readBoolean() {
/*     */     return Console$.MODULE$.readBoolean();
/*     */   }
/*     */   
/*     */   public byte readByte() {
/*     */     return Console$.MODULE$.readByte();
/*     */   }
/*     */   
/*     */   public short readShort() {
/*     */     return Console$.MODULE$.readShort();
/*     */   }
/*     */   
/*     */   public char readChar() {
/*     */     return Console$.MODULE$.readChar();
/*     */   }
/*     */   
/*     */   public int readInt() {
/*     */     return Console$.MODULE$.readInt();
/*     */   }
/*     */   
/*     */   public long readLong() {
/*     */     return Console$.MODULE$.readLong();
/*     */   }
/*     */   
/*     */   public float readFloat() {
/*     */     return Console$.MODULE$.readFloat();
/*     */   }
/*     */   
/*     */   public double readDouble() {
/*     */     return Console$.MODULE$.readDouble();
/*     */   }
/*     */   
/*     */   public List<Object> readf(String format) {
/*     */     return Console$.MODULE$.readf(format);
/*     */   }
/*     */   
/*     */   public Object readf1(String format) {
/*     */     return Console$.MODULE$.readf1(format);
/*     */   }
/*     */   
/*     */   public Tuple2<Object, Object> readf2(String format) {
/*     */     return Console$.MODULE$.readf2(format);
/*     */   }
/*     */   
/*     */   public Tuple3<Object, Object, Object> readf3(String format) {
/*     */     return Console$.MODULE$.readf3(format);
/*     */   }
/*     */   
/*     */   public RichException exceptionWrapper(Throwable exc) {
/*     */     return new RichException(exc);
/*     */   }
/*     */   
/*     */   public <T1, T2> Tuple2<T1, T2> tuple2ToZippedOps(Tuple2<T1, T2> x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public <T1, T2, T3> Tuple3<T1, T2, T3> tuple3ToZippedOps(Tuple3<T1, T2, T3> x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public CharSequence seqToCharSequence(IndexedSeq xs) {
/*     */     return (CharSequence)new SeqCharSequence(xs);
/*     */   }
/*     */   
/*     */   public CharSequence arrayToCharSequence(char[] xs) {
/*     */     return (CharSequence)new ArrayCharSequence(xs, 0, xs.length);
/*     */   }
/*     */   
/*     */   public <T> ArrayOps<T> genericArrayOps(Object xs) {
/*     */     if (xs instanceof Object[]) {
/*     */       Object[] arrayOfObject = (Object[])xs;
/*     */       ArrayOps arrayOps = refArrayOps(arrayOfObject);
/*     */     } else if (xs instanceof boolean[]) {
/*     */       boolean[] arrayOfBoolean = (boolean[])xs;
/*     */       ArrayOps<Object> arrayOps = booleanArrayOps(arrayOfBoolean);
/*     */     } else if (xs instanceof byte[]) {
/*     */       byte[] arrayOfByte = (byte[])xs;
/*     */       ArrayOps<Object> arrayOps = byteArrayOps(arrayOfByte);
/*     */     } else if (xs instanceof char[]) {
/*     */       char[] arrayOfChar = (char[])xs;
/*     */       ArrayOps<Object> arrayOps = charArrayOps(arrayOfChar);
/*     */     } else if (xs instanceof double[]) {
/*     */       double[] arrayOfDouble = (double[])xs;
/*     */       ArrayOps<Object> arrayOps = doubleArrayOps(arrayOfDouble);
/*     */     } else if (xs instanceof float[]) {
/*     */       float[] arrayOfFloat = (float[])xs;
/*     */       ArrayOps<Object> arrayOps = floatArrayOps(arrayOfFloat);
/*     */     } else if (xs instanceof int[]) {
/*     */       int[] arrayOfInt = (int[])xs;
/*     */       ArrayOps<Object> arrayOps = intArrayOps(arrayOfInt);
/*     */     } else if (xs instanceof long[]) {
/*     */       long[] arrayOfLong = (long[])xs;
/*     */       ArrayOps<Object> arrayOps = longArrayOps(arrayOfLong);
/*     */     } else if (xs instanceof short[]) {
/*     */       short[] arrayOfShort = (short[])xs;
/*     */       ArrayOps<Object> arrayOps = shortArrayOps(arrayOfShort);
/*     */     } else if (xs instanceof BoxedUnit[]) {
/*     */       BoxedUnit[] arrayOfBoxedUnit = (BoxedUnit[])xs;
/*     */       ArrayOps<BoxedUnit> arrayOps = unitArrayOps(arrayOfBoxedUnit);
/*     */     } else {
/*     */       if (xs == null)
/*     */         return null; 
/*     */       throw new MatchError(xs);
/*     */     } 
/*     */     return (ArrayOps<T>)SYNTHETIC_LOCAL_VARIABLE_12;
/*     */   }
/*     */   
/*     */   public ArrayOps<Object> booleanArrayOps(boolean[] xs) {
/*     */     return (ArrayOps<Object>)new ArrayOps.ofBoolean(xs);
/*     */   }
/*     */   
/*     */   public ArrayOps<Object> byteArrayOps(byte[] xs) {
/*     */     return (ArrayOps<Object>)new ArrayOps.ofByte(xs);
/*     */   }
/*     */   
/*     */   public ArrayOps<Object> charArrayOps(char[] xs) {
/*     */     return (ArrayOps<Object>)new ArrayOps.ofChar(xs);
/*     */   }
/*     */   
/*     */   public ArrayOps<Object> doubleArrayOps(double[] xs) {
/*     */     return (ArrayOps<Object>)new ArrayOps.ofDouble(xs);
/*     */   }
/*     */   
/*     */   public ArrayOps<Object> floatArrayOps(float[] xs) {
/*     */     return (ArrayOps<Object>)new ArrayOps.ofFloat(xs);
/*     */   }
/*     */   
/*     */   public ArrayOps<Object> intArrayOps(int[] xs) {
/*     */     return (ArrayOps<Object>)new ArrayOps.ofInt(xs);
/*     */   }
/*     */   
/*     */   public ArrayOps<Object> longArrayOps(long[] xs) {
/*     */     return (ArrayOps<Object>)new ArrayOps.ofLong(xs);
/*     */   }
/*     */   
/*     */   public <T> ArrayOps<T> refArrayOps(Object[] xs) {
/*     */     return (ArrayOps<T>)new ArrayOps.ofRef(xs);
/*     */   }
/*     */   
/*     */   public ArrayOps<Object> shortArrayOps(short[] xs) {
/*     */     return (ArrayOps<Object>)new ArrayOps.ofShort(xs);
/*     */   }
/*     */   
/*     */   public ArrayOps<BoxedUnit> unitArrayOps(BoxedUnit[] xs) {
/*     */     return (ArrayOps<BoxedUnit>)new ArrayOps.ofUnit(xs);
/*     */   }
/*     */   
/*     */   public short byte2short(byte x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public int byte2int(byte x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public long byte2long(byte x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public float byte2float(byte x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public double byte2double(byte x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public int short2int(short x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public long short2long(short x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public float short2float(short x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public double short2double(short x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public int char2int(char x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public long char2long(char x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public float char2float(char x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public double char2double(char x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public long int2long(int x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public float int2float(int x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public double int2double(int x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public float long2float(long x) {
/*     */     return (float)x;
/*     */   }
/*     */   
/*     */   public double long2double(long x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public double float2double(float x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public java.lang.Byte byte2Byte(byte x) {
/*     */     return java.lang.Byte.valueOf(x);
/*     */   }
/*     */   
/*     */   public java.lang.Short short2Short(short x) {
/*     */     return java.lang.Short.valueOf(x);
/*     */   }
/*     */   
/*     */   public Character char2Character(char x) {
/*     */     return Character.valueOf(x);
/*     */   }
/*     */   
/*     */   public Integer int2Integer(int x) {
/*     */     return Integer.valueOf(x);
/*     */   }
/*     */   
/*     */   public java.lang.Long long2Long(long x) {
/*     */     return java.lang.Long.valueOf(x);
/*     */   }
/*     */   
/*     */   public java.lang.Float float2Float(float x) {
/*     */     return java.lang.Float.valueOf(x);
/*     */   }
/*     */   
/*     */   public java.lang.Double double2Double(double x) {
/*     */     return java.lang.Double.valueOf(x);
/*     */   }
/*     */   
/*     */   public java.lang.Boolean boolean2Boolean(boolean x) {
/*     */     return java.lang.Boolean.valueOf(x);
/*     */   }
/*     */   
/*     */   public Object byte2ByteConflict(byte x) {
/*     */     return new Object();
/*     */   }
/*     */   
/*     */   public Object short2ShortConflict(short x) {
/*     */     return new Object();
/*     */   }
/*     */   
/*     */   public Object char2CharacterConflict(char x) {
/*     */     return new Object();
/*     */   }
/*     */   
/*     */   public Object int2IntegerConflict(int x) {
/*     */     return new Object();
/*     */   }
/*     */   
/*     */   public Object long2LongConflict(long x) {
/*     */     return new Object();
/*     */   }
/*     */   
/*     */   public Object float2FloatConflict(float x) {
/*     */     return new Object();
/*     */   }
/*     */   
/*     */   public Object double2DoubleConflict(double x) {
/*     */     return new Object();
/*     */   }
/*     */   
/*     */   public Object boolean2BooleanConflict(boolean x) {
/*     */     return new Object();
/*     */   }
/*     */   
/*     */   public byte Byte2byte(java.lang.Byte x) {
/*     */     return x.byteValue();
/*     */   }
/*     */   
/*     */   public short Short2short(java.lang.Short x) {
/*     */     return x.shortValue();
/*     */   }
/*     */   
/*     */   public char Character2char(Character x) {
/*     */     return x.charValue();
/*     */   }
/*     */   
/*     */   public int Integer2int(Integer x) {
/*     */     return x.intValue();
/*     */   }
/*     */   
/*     */   public long Long2long(java.lang.Long x) {
/*     */     return x.longValue();
/*     */   }
/*     */   
/*     */   public float Float2float(java.lang.Float x) {
/*     */     return x.floatValue();
/*     */   }
/*     */   
/*     */   public double Double2double(java.lang.Double x) {
/*     */     return x.doubleValue();
/*     */   }
/*     */   
/*     */   public boolean Boolean2boolean(java.lang.Boolean x) {
/*     */     return x.booleanValue();
/*     */   }
/*     */   
/*     */   public Object any2stringfmt(Object x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public String augmentString(String x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public Object any2stringadd(Object x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public String unaugmentString(String x) {
/*     */     return x;
/*     */   }
/*     */   
/*     */   public CanBuildFrom<String, Object, String> stringCanBuildFrom() {
/*     */     return StringCanBuildFrom();
/*     */   }
/*     */   
/*     */   public CanBuildFrom<String, Object, String> StringCanBuildFrom() {
/*     */     return this.StringCanBuildFrom;
/*     */   }
/*     */   
/*     */   public static class $anon$3 implements CanBuildFrom<String, Object, String> {
/*     */     public StringBuilder apply(String from) {
/*     */       return apply();
/*     */     }
/*     */     
/*     */     public StringBuilder apply() {
/*     */       return StringBuilder$.MODULE$.newBuilder();
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class $less$colon$less<From, To> implements Function1<From, To>, Serializable {
/*     */     public boolean apply$mcZD$sp(double v1) {
/*     */       return Function1$class.apply$mcZD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/*     */       return Function1$class.apply$mcDD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/*     */       return Function1$class.apply$mcFD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/*     */       return Function1$class.apply$mcID$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/*     */       return Function1$class.apply$mcJD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/*     */       Function1$class.apply$mcVD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/*     */       return Function1$class.apply$mcZF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/*     */       return Function1$class.apply$mcDF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/*     */       return Function1$class.apply$mcFF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/*     */       return Function1$class.apply$mcIF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/*     */       return Function1$class.apply$mcJF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/*     */       Function1$class.apply$mcVF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/*     */       return Function1$class.apply$mcZI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/*     */       return Function1$class.apply$mcDI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/*     */       return Function1$class.apply$mcFI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/*     */       return Function1$class.apply$mcII$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/*     */       return Function1$class.apply$mcJI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/*     */       Function1$class.apply$mcVI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/*     */       return Function1$class.apply$mcZJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/*     */       return Function1$class.apply$mcDJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/*     */       return Function1$class.apply$mcFJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/*     */       return Function1$class.apply$mcIJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/*     */       return Function1$class.apply$mcJJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/*     */       Function1$class.apply$mcVJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, To> compose(Function1 g) {
/*     */       return Function1$class.compose(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*     */       return Function1$class.compose$mcZD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*     */       return Function1$class.compose$mcDD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*     */       return Function1$class.compose$mcFD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*     */       return Function1$class.compose$mcID$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*     */       return Function1$class.compose$mcJD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*     */       return Function1$class.compose$mcVD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*     */       return Function1$class.compose$mcZF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*     */       return Function1$class.compose$mcDF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*     */       return Function1$class.compose$mcFF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*     */       return Function1$class.compose$mcIF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*     */       return Function1$class.compose$mcJF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*     */       return Function1$class.compose$mcVF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*     */       return Function1$class.compose$mcZI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*     */       return Function1$class.compose$mcDI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*     */       return Function1$class.compose$mcFI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*     */       return Function1$class.compose$mcII$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*     */       return Function1$class.compose$mcJI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*     */       return Function1$class.compose$mcVI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*     */       return Function1$class.compose$mcZJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*     */       return Function1$class.compose$mcDJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*     */       return Function1$class.compose$mcFJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*     */       return Function1$class.compose$mcIJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*     */       return Function1$class.compose$mcJJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*     */       return Function1$class.compose$mcVJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<From, A> andThen(Function1 g) {
/*     */       return Function1$class.andThen(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcZD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcDD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcFD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcID$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcJD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcVD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcZF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcDF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcFF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcIF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcJF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcVF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcZI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcDI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcFI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcII$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcJI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcVI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcZJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcDJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcFJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcIJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcJJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcVJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return Function1$class.toString(this);
/*     */     }
/*     */     
/*     */     public $less$colon$less() {
/*     */       Function1$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$1 extends Predef.$less$colon$less<Object, Object> {
/*     */     public Object apply(Object x) {
/*     */       return x;
/*     */     }
/*     */   }
/*     */   
/*     */   public <A> Predef.$less$colon$less<A, A> conforms() {
/*     */     return (Predef.$less$colon$less)this.singleton_$less$colon$less;
/*     */   }
/*     */   
/*     */   public <T> Class<T> classOf() {
/*     */     return null;
/*     */   }
/*     */   
/*     */   public static abstract class $eq$colon$eq<From, To> implements Function1<From, To>, Serializable {
/*     */     public boolean apply$mcZD$sp(double v1) {
/*     */       return Function1$class.apply$mcZD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/*     */       return Function1$class.apply$mcDD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/*     */       return Function1$class.apply$mcFD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/*     */       return Function1$class.apply$mcID$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/*     */       return Function1$class.apply$mcJD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/*     */       Function1$class.apply$mcVD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/*     */       return Function1$class.apply$mcZF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/*     */       return Function1$class.apply$mcDF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/*     */       return Function1$class.apply$mcFF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/*     */       return Function1$class.apply$mcIF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/*     */       return Function1$class.apply$mcJF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/*     */       Function1$class.apply$mcVF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/*     */       return Function1$class.apply$mcZI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/*     */       return Function1$class.apply$mcDI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/*     */       return Function1$class.apply$mcFI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/*     */       return Function1$class.apply$mcII$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/*     */       return Function1$class.apply$mcJI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/*     */       Function1$class.apply$mcVI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/*     */       return Function1$class.apply$mcZJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/*     */       return Function1$class.apply$mcDJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/*     */       return Function1$class.apply$mcFJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/*     */       return Function1$class.apply$mcIJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/*     */       return Function1$class.apply$mcJJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/*     */       Function1$class.apply$mcVJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, To> compose(Function1 g) {
/*     */       return Function1$class.compose(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*     */       return Function1$class.compose$mcZD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*     */       return Function1$class.compose$mcDD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*     */       return Function1$class.compose$mcFD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*     */       return Function1$class.compose$mcID$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*     */       return Function1$class.compose$mcJD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*     */       return Function1$class.compose$mcVD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*     */       return Function1$class.compose$mcZF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*     */       return Function1$class.compose$mcDF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*     */       return Function1$class.compose$mcFF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*     */       return Function1$class.compose$mcIF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*     */       return Function1$class.compose$mcJF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*     */       return Function1$class.compose$mcVF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*     */       return Function1$class.compose$mcZI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*     */       return Function1$class.compose$mcDI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*     */       return Function1$class.compose$mcFI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*     */       return Function1$class.compose$mcII$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*     */       return Function1$class.compose$mcJI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*     */       return Function1$class.compose$mcVI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*     */       return Function1$class.compose$mcZJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*     */       return Function1$class.compose$mcDJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*     */       return Function1$class.compose$mcFJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*     */       return Function1$class.compose$mcIJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*     */       return Function1$class.compose$mcJJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*     */       return Function1$class.compose$mcVJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<From, A> andThen(Function1 g) {
/*     */       return Function1$class.andThen(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcZD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcDD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcFD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcID$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcJD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcVD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcZF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcDF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcFF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcIF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcJF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcVF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcZI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcDI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcFI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcII$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcJI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcVI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcZJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcDJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcFJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcIJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcJJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*     */       return Function1$class.andThen$mcVJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return Function1$class.toString(this);
/*     */     }
/*     */     
/*     */     public $eq$colon$eq() {
/*     */       Function1$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$2 extends Predef.$eq$colon$eq<Object, Object> {
/*     */     public Object apply(Object x) {
/* 445 */       return x;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $eq$colon$eq$ implements Serializable {
/*     */     public static final $eq$colon$eq$ MODULE$;
/*     */     
/*     */     public $eq$colon$eq$() {
/* 446 */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 446 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public <A> Predef.$eq$colon$eq<A, A> tpEquals() {
/* 447 */       return (Predef.$eq$colon$eq)Predef$.MODULE$.scala$Predef$$singleton_$eq$colon$eq;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Predef$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */