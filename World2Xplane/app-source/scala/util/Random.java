/*     */ package scala.util;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Range$;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Stream$;
/*     */ import scala.collection.immutable.Stream$Empty$;
/*     */ import scala.collection.immutable.Stream$cons$;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005%d\001B\001\003\001\035\021aAU1oI>l'BA\002\005\003\021)H/\0337\013\003\025\tQa]2bY\006\034\001a\005\002\001\021A\021\021BC\007\002\t%\0211\002\002\002\007\003:L(+\0324\t\0215\001!Q1A\005\0029\tAa]3mMV\tq\002\005\002\021)5\t\021C\003\002\004%)\t1#\001\003kCZ\f\027BA\001\022\021!1\002A!A!\002\023y\021!B:fY\032\004\003\"\002\r\001\t\003I\022A\002\037j]&$h\b\006\002\0339A\0211\004A\007\002\005!)Qb\006a\001\037!)\001\004\001C\001=Q\021!d\b\005\006Au\001\r!I\001\005g\026,G\r\005\002\nE%\0211\005\002\002\005\031>tw\rC\003\031\001\021\005Q\005\006\002\033M!)\001\005\na\001OA\021\021\002K\005\003S\021\0211!\0238u\021\025A\002\001\"\001,)\005Q\002\"B\027\001\t\003q\023a\0038fqR\024un\0347fC:$\022a\f\t\003\023AJ!!\r\003\003\017\t{w\016\\3b]\")1\007\001C\001i\005Ia.\032=u\005f$Xm\035\013\003ka\002\"!\003\034\n\005]\"!\001B+oSRDQ!\017\032A\002i\nQAY=uKN\0042!C\036>\023\taDAA\003BeJ\f\027\020\005\002\n}%\021q\b\002\002\005\005f$X\rC\003B\001\021\005!)\001\006oKb$Hi\\;cY\026$\022a\021\t\003\023\021K!!\022\003\003\r\021{WO\0317f\021\0259\005\001\"\001I\003%qW\r\037;GY>\fG\017F\001J!\tI!*\003\002L\t\t)a\t\\8bi\")Q\n\001C\001\005\006aa.\032=u\017\006,8o]5b]\")q\n\001C\001!\0069a.\032=u\023:$H#A\024\t\013=\003A\021\001*\025\005\035\032\006\"\002+R\001\0049\023!\0018\t\013Y\003A\021A,\002\0219,\007\020\036'p]\036$\022!\t\005\0063\002!\tAW\001\013]\026DHo\025;sS:<GCA.c!\tavL\004\002\n;&\021a\fB\001\007!J,G-\0324\n\005\001\f'AB*ue&twM\003\002_\t!)1\r\027a\001O\0051A.\0328hi\"DQ!\032\001\005\002\031\f\021C\\3yiB\023\030N\034;bE2,7\t[1s)\0059\007CA\005i\023\tIGA\001\003DQ\006\024\b\"B6\001\t\003a\027aB:fiN+W\r\032\013\003k5DQ\001\t6A\002\005BQa\034\001\005\002A\fqa\0355vM\032dW-\006\003r\003G)Hc\001:\002<Q\0311/a\n\021\tQ,\030\021\005\007\001\t\0251hN1\001x\005\t\0315)F\002y\003'\t\"!\037?\021\005%Q\030BA>\005\005\035qu\016\0365j]\036\004R!`A\006\003#q1A`A\004\035\ry\030QA\007\003\003\003Q1!a\001\007\003\031a$o\\8u}%\tQ!C\002\002\n\021\tq\001]1dW\006<W-\003\003\002\016\005=!a\004+sCZ,'o]1cY\026|enY3\013\007\005%A\001E\002u\003'!\001\"!\006\002\030\t\007\021\021\004\002\0021\022)aO\034b\001oF\031\0210a\007\021\007%\ti\"C\002\002 \021\0211!\0218z!\r!\0301\005\003\b\003Kq'\031AA\r\005\005!\006bBA\025]\002\017\0211F\001\003E\032\004\002\"!\f\0028M\f\tc]\007\003\003_QA!!\r\0024\0059q-\0328fe&\034'bAA\033\t\005Q1m\0347mK\016$\030n\0348\n\t\005e\022q\006\002\r\007\006t')^5mI\032\023x.\034\005\007\003{q\007\031A:\002\005a\034\bbBA!\001\021\005\0211I\001\rC2\004\b.\0318v[\026\024\030nY\013\003\003\013\002R!a\022\002N\035l!!!\023\013\t\005-\0231G\001\nS6lW\017^1cY\026LA!a\024\002J\t11\013\036:fC6<q!a\025\003\021\003\t)&\001\004SC:$w.\034\t\0047\005]cAB\001\003\021\003\tIfE\002\002XiAq\001GA,\t\003\ti\006\006\002\002V!A\021\021MA,\t\007\t\031'\001\nkCZ\f'+\0318e_6$vNU1oI>lGc\001\016\002f!9\021qMA0\001\004y\021!\001:")
/*     */ public class Random {
/*     */   private final java.util.Random self;
/*     */   
/*     */   public java.util.Random self() {
/*  20 */     return this.self;
/*     */   }
/*     */   
/*     */   public Random(java.util.Random self) {}
/*     */   
/*     */   public Random(long seed) {
/*  22 */     this(new java.util.Random(seed));
/*     */   }
/*     */   
/*     */   public Random(int seed) {
/*  25 */     this(seed);
/*     */   }
/*     */   
/*     */   public Random() {
/*  28 */     this(new java.util.Random());
/*     */   }
/*     */   
/*     */   public boolean nextBoolean() {
/*  33 */     return self().nextBoolean();
/*     */   }
/*     */   
/*     */   public void nextBytes(byte[] bytes) {
/*  38 */     self().nextBytes(bytes);
/*     */   }
/*     */   
/*     */   public double nextDouble() {
/*  43 */     return self().nextDouble();
/*     */   }
/*     */   
/*     */   public float nextFloat() {
/*  48 */     return self().nextFloat();
/*     */   }
/*     */   
/*     */   public double nextGaussian() {
/*  54 */     return self().nextGaussian();
/*     */   }
/*     */   
/*     */   public int nextInt() {
/*  59 */     return self().nextInt();
/*     */   }
/*     */   
/*     */   public int nextInt(int n) {
/*  65 */     return self().nextInt(n);
/*     */   }
/*     */   
/*     */   public long nextLong() {
/*  70 */     return self().nextLong();
/*     */   }
/*     */   
/*     */   public final char scala$util$Random$$safeChar$1() {
/*  83 */     int res = nextInt(55296 - 1) + 1;
/*  85 */     return (char)res;
/*     */   }
/*     */   
/*     */   public String nextString(int length) {
/*  88 */     return ((TraversableOnce)List$.MODULE$.fill(length, (Function0)new Random$$anonfun$nextString$1(this))).mkString();
/*     */   }
/*     */   
/*     */   public class Random$$anonfun$nextString$1 extends AbstractFunction0.mcC.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final char apply() {
/*  88 */       return this.$outer.scala$util$Random$$safeChar$1();
/*     */     }
/*     */     
/*     */     public char apply$mcC$sp() {
/*  88 */       return this.$outer.scala$util$Random$$safeChar$1();
/*     */     }
/*     */     
/*     */     public Random$$anonfun$nextString$1(Random $outer) {}
/*     */   }
/*     */   
/*     */   public char nextPrintableChar() {
/*  95 */     return (char)(self().nextInt(127 - 33) + 33);
/*     */   }
/*     */   
/*     */   public void setSeed(long seed) {
/* 100 */     self().setSeed(seed);
/*     */   }
/*     */   
/*     */   public <T, CC extends TraversableOnce<Object>> CC shuffle(TraversableOnce xs, CanBuildFrom bf) {
/* 107 */     ArrayBuffer buf = (new ArrayBuffer()).$plus$plus$eq(xs);
/* 115 */     int i = buf.length();
/* 115 */     Predef$ predef$ = Predef$.MODULE$;
/* 115 */     Range$ range$ = Range$.MODULE$;
/* 115 */     Random$$anonfun$shuffle$1 random$$anonfun$shuffle$1 = new Random$$anonfun$shuffle$1(this, buf);
/*     */     Range range;
/* 115 */     if ((range = (new Range.Inclusive(i, 2, 1)).by(-1)).validateRangeBoundaries((Function1)random$$anonfun$shuffle$1)) {
/*     */       int terminal1;
/*     */       int step1;
/*     */       int i1;
/* 115 */       for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 115 */         int k1 = nextInt(i1);
/* 115 */         scala$util$Random$$swap$1(i1 - 1, k1, buf);
/* 115 */         i1 += step1;
/*     */       } 
/*     */     } 
/* 120 */     return (CC)((Builder)bf.apply(xs).$plus$plus$eq((TraversableOnce)buf)).result();
/*     */   }
/*     */   
/*     */   public final void scala$util$Random$$swap$1(int i1, int i2, ArrayBuffer buf$1) {
/*     */     Object tmp = buf$1.apply(i1);
/*     */     buf$1.update(i1, buf$1.apply(i2));
/*     */     buf$1.update(i2, tmp);
/*     */   }
/*     */   
/*     */   public class Random$$anonfun$shuffle$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ArrayBuffer buf$1;
/*     */     
/*     */     public final void apply(int n) {
/*     */       apply$mcVI$sp(n);
/*     */     }
/*     */     
/*     */     public Random$$anonfun$shuffle$1(Random $outer, ArrayBuffer buf$1) {}
/*     */     
/*     */     public void apply$mcVI$sp(int n) {
/*     */       int k = this.$outer.nextInt(n);
/*     */       this.$outer.scala$util$Random$$swap$1(n - 1, k, this.buf$1);
/*     */     }
/*     */   }
/*     */   
/*     */   public final boolean scala$util$Random$$isAlphaNum$1(char c) {
/* 129 */     return ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'));
/*     */   }
/*     */   
/*     */   public Stream<Object> alphanumeric() {
/* 131 */     Random$$anonfun$alphanumeric$1 random$$anonfun$alphanumeric$1 = new Random$$anonfun$alphanumeric$1(this);
/* 131 */     Stream$ stream$ = Stream$.MODULE$;
/* 131 */     Object object = new Object((Function0)random$$anonfun$alphanumeric$1);
/* 131 */     Character character = BoxesRunTime.boxToCharacter(nextPrintableChar());
/* 131 */     Stream$cons$ stream$cons$ = Stream$cons$.MODULE$;
/* 131 */     Random$$anonfun$alphanumeric$2 random$$anonfun$alphanumeric$2 = new Random$$anonfun$alphanumeric$2(this);
/* 131 */     Stream.Cons cons = new Stream.Cons(character, (Function0)object);
/*     */     while (true) {
/*     */       Stream stream;
/* 131 */       if (!cons.isEmpty()) {
/* 131 */         char c = BoxesRunTime.unboxToChar(cons.head());
/* 131 */         if (!scala$util$Random$$isAlphaNum$1(c)) {
/* 131 */           stream = (Stream)cons.tail();
/*     */           continue;
/*     */         } 
/*     */       } 
/* 131 */       Stream$ stream$1 = Stream$.MODULE$;
/* 131 */       Object object2 = new Object(stream, (Function1)random$$anonfun$alphanumeric$2), object1 = stream.head();
/* 131 */       Stream$cons$ stream$cons$1 = Stream$cons$.MODULE$;
/* 131 */       return stream.nonEmpty() ? (Stream<Object>)new Stream.Cons(object1, (Function0)object2) : (Stream<Object>)Stream$Empty$.MODULE$;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Random javaRandomToRandom(java.util.Random paramRandom) {
/*     */     return Random$.MODULE$.javaRandomToRandom(paramRandom);
/*     */   }
/*     */   
/*     */   public class Random$$anonfun$alphanumeric$1 extends AbstractFunction0.mcC.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final char apply() {
/* 131 */       return this.$outer.nextPrintableChar();
/*     */     }
/*     */     
/*     */     public char apply$mcC$sp() {
/* 131 */       return this.$outer.nextPrintableChar();
/*     */     }
/*     */     
/*     */     public Random$$anonfun$alphanumeric$1(Random $outer) {}
/*     */   }
/*     */   
/*     */   public class Random$$anonfun$alphanumeric$2 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(char c) {
/* 131 */       return this.$outer.scala$util$Random$$isAlphaNum$1(c);
/*     */     }
/*     */     
/*     */     public Random$$anonfun$alphanumeric$2(Random $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Random.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */