/*     */ package scala.concurrent.duration;
/*     */ 
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class Duration$ implements Serializable {
/*     */   public static final Duration$ MODULE$;
/*     */   
/*     */   private final double maxPreciseDouble;
/*     */   
/*     */   private final List<Tuple2<TimeUnit, String>> timeUnitLabels;
/*     */   
/*     */   private final Map<TimeUnit, String> timeUnitName;
/*     */   
/*     */   private final Map<String, TimeUnit> timeUnit;
/*     */   
/*     */   private final long µs_per_ns;
/*     */   
/*     */   private final long ms_per_ns;
/*     */   
/*     */   private final long s_per_ns;
/*     */   
/*     */   private final long min_per_ns;
/*     */   
/*     */   private final long h_per_ns;
/*     */   
/*     */   private final long d_per_ns;
/*     */   
/*     */   private final FiniteDuration Zero;
/*     */   
/*     */   private final Duration.Infinite Undefined;
/*     */   
/*     */   private final Duration.Infinite Inf;
/*     */   
/*     */   private final Duration.Infinite MinusInf;
/*     */   
/*     */   private Object readResolve() {
/*  14 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Duration$() {
/*  14 */     MODULE$ = this;
/*  78 */     scala.Predef$ predef$1 = scala.Predef$.MODULE$;
/*  78 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$1 = scala.Predef$ArrowAssoc$.MODULE$;
/*  78 */     (new Tuple2[7])[0] = new Tuple2(TimeUnit.DAYS, "d day");
/*  79 */     scala.Predef$ predef$2 = scala.Predef$.MODULE$;
/*  79 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$2 = scala.Predef$ArrowAssoc$.MODULE$;
/*  79 */     (new Tuple2[7])[1] = new Tuple2(TimeUnit.HOURS, "h hour");
/*  80 */     scala.Predef$ predef$3 = scala.Predef$.MODULE$;
/*  80 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$3 = scala.Predef$ArrowAssoc$.MODULE$;
/*  80 */     (new Tuple2[7])[2] = new Tuple2(TimeUnit.MINUTES, "min minute");
/*  81 */     scala.Predef$ predef$4 = scala.Predef$.MODULE$;
/*  81 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$4 = scala.Predef$ArrowAssoc$.MODULE$;
/*  81 */     (new Tuple2[7])[3] = new Tuple2(TimeUnit.SECONDS, "s sec second");
/*  82 */     scala.Predef$ predef$5 = scala.Predef$.MODULE$;
/*  82 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$5 = scala.Predef$ArrowAssoc$.MODULE$;
/*  82 */     (new Tuple2[7])[4] = new Tuple2(TimeUnit.MILLISECONDS, "ms milli millisecond");
/*  83 */     scala.Predef$ predef$6 = scala.Predef$.MODULE$;
/*  83 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$6 = scala.Predef$ArrowAssoc$.MODULE$;
/*  83 */     (new Tuple2[7])[5] = new Tuple2(TimeUnit.MICROSECONDS, "µs micro microsecond");
/*  84 */     scala.Predef$ predef$7 = scala.Predef$.MODULE$;
/*  84 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$7 = scala.Predef$ArrowAssoc$.MODULE$;
/*  84 */     (new Tuple2[7])[6] = new Tuple2(TimeUnit.NANOSECONDS, "ns nano nanosecond");
/*     */     this.timeUnitLabels = scala.collection.immutable.List$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[7]));
/*  88 */     this.timeUnitName = 
/*  89 */       this.timeUnitLabels.toMap(scala.Predef$.MODULE$.conforms()).mapValues((Function1)new Duration.$anonfun$3()).toMap(scala.Predef$.MODULE$.conforms());
/*  92 */     this.timeUnit = (
/*  93 */       (TraversableOnce)this.timeUnitLabels.flatMap((Function1)new Duration.$anonfun$4(), scala.collection.immutable.List$.MODULE$.canBuildFrom())).toMap(scala.Predef$.MODULE$.conforms());
/* 160 */     this.Zero = new FiniteDuration(0L, TimeUnit.DAYS);
/* 173 */     this.Undefined = new Duration.$anon$1();
/* 230 */     this.Inf = new Duration.$anon$2();
/* 246 */     this.MinusInf = new Duration.$anon$3();
/*     */   }
/*     */   
/*     */   public Duration apply(double length, TimeUnit unit) {
/*     */     return fromNanos(unit.toNanos(1L) * length);
/*     */   }
/*     */   
/*     */   public FiniteDuration apply(long length, TimeUnit unit) {
/*     */     return new FiniteDuration(length, unit);
/*     */   }
/*     */   
/*     */   public FiniteDuration apply(long length, String unit) {
/*     */     return new FiniteDuration(length, (TimeUnit)timeUnit().apply(unit));
/*     */   }
/*     */   
/*     */   public Duration apply(String s) {
/*     */     // Byte code:
/*     */     //   0: new scala/collection/immutable/StringOps
/*     */     //   3: dup
/*     */     //   4: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   7: astore_2
/*     */     //   8: aload_1
/*     */     //   9: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   12: new scala/concurrent/duration/Duration$$anonfun$1
/*     */     //   15: dup
/*     */     //   16: invokespecial <init> : ()V
/*     */     //   19: invokestatic filterNot : (Lscala/collection/TraversableLike;Lscala/Function1;)Ljava/lang/Object;
/*     */     //   22: checkcast java/lang/String
/*     */     //   25: astore #12
/*     */     //   27: ldc 'Inf'
/*     */     //   29: dup
/*     */     //   30: ifnonnull -> 42
/*     */     //   33: pop
/*     */     //   34: aload #12
/*     */     //   36: ifnull -> 50
/*     */     //   39: goto -> 55
/*     */     //   42: aload #12
/*     */     //   44: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   47: ifeq -> 55
/*     */     //   50: iconst_1
/*     */     //   51: istore_3
/*     */     //   52: goto -> 113
/*     */     //   55: ldc 'PlusInf'
/*     */     //   57: dup
/*     */     //   58: ifnonnull -> 70
/*     */     //   61: pop
/*     */     //   62: aload #12
/*     */     //   64: ifnull -> 78
/*     */     //   67: goto -> 83
/*     */     //   70: aload #12
/*     */     //   72: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   75: ifeq -> 83
/*     */     //   78: iconst_1
/*     */     //   79: istore_3
/*     */     //   80: goto -> 113
/*     */     //   83: ldc '+Inf'
/*     */     //   85: dup
/*     */     //   86: ifnonnull -> 98
/*     */     //   89: pop
/*     */     //   90: aload #12
/*     */     //   92: ifnull -> 106
/*     */     //   95: goto -> 111
/*     */     //   98: aload #12
/*     */     //   100: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   103: ifeq -> 111
/*     */     //   106: iconst_1
/*     */     //   107: istore_3
/*     */     //   108: goto -> 113
/*     */     //   111: iconst_0
/*     */     //   112: istore_3
/*     */     //   113: iload_3
/*     */     //   114: ifeq -> 126
/*     */     //   117: aload_0
/*     */     //   118: invokevirtual Inf : ()Lscala/concurrent/duration/Duration$Infinite;
/*     */     //   121: astore #23
/*     */     //   123: goto -> 411
/*     */     //   126: ldc 'MinusInf'
/*     */     //   128: dup
/*     */     //   129: ifnonnull -> 141
/*     */     //   132: pop
/*     */     //   133: aload #12
/*     */     //   135: ifnull -> 149
/*     */     //   138: goto -> 155
/*     */     //   141: aload #12
/*     */     //   143: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   146: ifeq -> 155
/*     */     //   149: iconst_1
/*     */     //   150: istore #4
/*     */     //   152: goto -> 187
/*     */     //   155: ldc '-Inf'
/*     */     //   157: dup
/*     */     //   158: ifnonnull -> 170
/*     */     //   161: pop
/*     */     //   162: aload #12
/*     */     //   164: ifnull -> 178
/*     */     //   167: goto -> 184
/*     */     //   170: aload #12
/*     */     //   172: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   175: ifeq -> 184
/*     */     //   178: iconst_1
/*     */     //   179: istore #4
/*     */     //   181: goto -> 187
/*     */     //   184: iconst_0
/*     */     //   185: istore #4
/*     */     //   187: iload #4
/*     */     //   189: ifeq -> 201
/*     */     //   192: aload_0
/*     */     //   193: invokevirtual MinusInf : ()Lscala/concurrent/duration/Duration$Infinite;
/*     */     //   196: astore #23
/*     */     //   198: goto -> 411
/*     */     //   201: new scala/collection/immutable/StringOps
/*     */     //   204: dup
/*     */     //   205: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   208: new scala/collection/immutable/StringOps
/*     */     //   211: dup
/*     */     //   212: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   215: new scala/collection/immutable/StringOps
/*     */     //   218: dup
/*     */     //   219: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   222: astore #5
/*     */     //   224: aload #12
/*     */     //   226: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   229: invokestatic reverse : (Lscala/collection/IndexedSeqOptimized;)Ljava/lang/Object;
/*     */     //   232: checkcast java/lang/String
/*     */     //   235: astore #7
/*     */     //   237: astore #6
/*     */     //   239: aload #7
/*     */     //   241: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   244: new scala/concurrent/duration/Duration$$anonfun$2
/*     */     //   247: dup
/*     */     //   248: invokespecial <init> : ()V
/*     */     //   251: invokestatic takeWhile : (Lscala/collection/IndexedSeqOptimized;Lscala/Function1;)Ljava/lang/Object;
/*     */     //   254: checkcast java/lang/String
/*     */     //   257: astore #9
/*     */     //   259: astore #8
/*     */     //   261: aload #9
/*     */     //   263: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   266: invokestatic reverse : (Lscala/collection/IndexedSeqOptimized;)Ljava/lang/Object;
/*     */     //   269: checkcast java/lang/String
/*     */     //   272: astore #13
/*     */     //   274: aload_0
/*     */     //   275: invokevirtual timeUnit : ()Lscala/collection/immutable/Map;
/*     */     //   278: aload #13
/*     */     //   280: invokeinterface get : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   285: astore #10
/*     */     //   287: aload #10
/*     */     //   289: instanceof scala/Some
/*     */     //   292: ifeq -> 414
/*     */     //   295: aload #10
/*     */     //   297: checkcast scala/Some
/*     */     //   300: astore #18
/*     */     //   302: new scala/collection/immutable/StringOps
/*     */     //   305: dup
/*     */     //   306: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   309: astore #11
/*     */     //   311: aload #12
/*     */     //   313: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   316: aload #13
/*     */     //   318: invokevirtual length : ()I
/*     */     //   321: invokestatic dropRight : (Lscala/collection/IndexedSeqOptimized;I)Ljava/lang/Object;
/*     */     //   324: checkcast java/lang/String
/*     */     //   327: astore #17
/*     */     //   329: aload #17
/*     */     //   331: invokestatic parseDouble : (Ljava/lang/String;)D
/*     */     //   334: dstore #15
/*     */     //   336: dload #15
/*     */     //   338: ldc2_w 9.007199254740992E15
/*     */     //   341: dneg
/*     */     //   342: dcmpl
/*     */     //   343: iflt -> 378
/*     */     //   346: dload #15
/*     */     //   348: ldc2_w 9.007199254740992E15
/*     */     //   351: dcmpg
/*     */     //   352: ifgt -> 378
/*     */     //   355: aload_0
/*     */     //   356: aload #18
/*     */     //   358: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   361: checkcast java/util/concurrent/TimeUnit
/*     */     //   364: lconst_1
/*     */     //   365: invokevirtual toNanos : (J)J
/*     */     //   368: l2d
/*     */     //   369: dload #15
/*     */     //   371: dmul
/*     */     //   372: invokevirtual fromNanos : (D)Lscala/concurrent/duration/Duration;
/*     */     //   375: goto -> 409
/*     */     //   378: aload_0
/*     */     //   379: aload #17
/*     */     //   381: invokestatic parseLong : (Ljava/lang/String;)J
/*     */     //   384: aload #18
/*     */     //   386: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   389: checkcast java/util/concurrent/TimeUnit
/*     */     //   392: astore #22
/*     */     //   394: lstore #20
/*     */     //   396: astore #19
/*     */     //   398: new scala/concurrent/duration/FiniteDuration
/*     */     //   401: dup
/*     */     //   402: lload #20
/*     */     //   404: aload #22
/*     */     //   406: invokespecial <init> : (JLjava/util/concurrent/TimeUnit;)V
/*     */     //   409: astore #23
/*     */     //   411: aload #23
/*     */     //   413: areturn
/*     */     //   414: new java/lang/NumberFormatException
/*     */     //   417: dup
/*     */     //   418: new scala/collection/mutable/StringBuilder
/*     */     //   421: dup
/*     */     //   422: invokespecial <init> : ()V
/*     */     //   425: ldc 'format error '
/*     */     //   427: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   430: aload_1
/*     */     //   431: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   434: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   437: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   440: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #54	-> 0
/*     */     //   #56	-> 27
/*     */     //   #55	-> 27
/*     */     //   #57	-> 126
/*     */     //   #59	-> 201
/*     */     //   #60	-> 274
/*     */     //   #61	-> 287
/*     */     //   #62	-> 302
/*     */     //   #63	-> 329
/*     */     //   #64	-> 336
/*     */     //   #60	-> 356
/*     */     //   #64	-> 358
/*     */     //   #65	-> 378
/*     */     //   #60	-> 384
/*     */     //   #65	-> 386
/*     */     //   #58	-> 409
/*     */     //   #60	-> 409
/*     */     //   #55	-> 411
/*     */     //   #66	-> 414
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	441	0	this	Lscala/concurrent/duration/Duration$;
/*     */     //   0	441	1	s	Ljava/lang/String;
/*     */     //   27	386	12	s1	Ljava/lang/String;
/*     */     //   274	135	13	unitName	Ljava/lang/String;
/*     */     //   329	112	17	valueStr	Ljava/lang/String;
/*     */     //   336	105	15	valueD	D
/*     */   }
/*     */   
/*     */   public static class Duration$$anonfun$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(char x$1) {
/*     */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*     */       return scala.runtime.RichChar$.MODULE$.isWhitespace$extension(x$1);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Duration$$anonfun$2 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(char x$2) {
/*     */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*     */       return scala.runtime.RichChar$.MODULE$.isLetter$extension(x$2);
/*     */     }
/*     */   }
/*     */   
/*     */   public List<String> scala$concurrent$duration$Duration$$words(String s) {
/*     */     return scala.Predef$.MODULE$.refArrayOps((Object[])s.trim().split("\\s+")).toList();
/*     */   }
/*     */   
/*     */   public List<String> scala$concurrent$duration$Duration$$expandLabels(String labels) {
/*     */     List<String> list = scala$concurrent$duration$Duration$$words(labels);
/*     */     if (list instanceof scala.collection.immutable..colon.colon) {
/*     */       scala.collection.immutable..colon.colon colon = (scala.collection.immutable..colon.colon)list;
/*     */       Tuple2 tuple2 = new Tuple2(colon.hd$1(), colon.tl$1());
/*     */       String hd = (String)tuple2._1();
/*     */       List rest = (List)tuple2._2();
/*     */       return ((List)rest.flatMap((Function1)new Duration$$anonfun$scala$concurrent$duration$Duration$$expandLabels$1(), scala.collection.immutable.List$.MODULE$.canBuildFrom())).$colon$colon(hd);
/*     */     } 
/*     */     throw new MatchError(list);
/*     */   }
/*     */   
/*     */   public static class Duration$$anonfun$scala$concurrent$duration$Duration$$expandLabels$1 extends AbstractFunction1<String, List<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<String> apply(String s) {
/*     */       (new String[2])[0] = s;
/*     */       (new String[2])[1] = (new StringBuilder()).append(s).append("s").toString();
/*     */       return scala.collection.immutable.List$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new String[2]));
/*     */     }
/*     */   }
/*     */   
/*     */   public Map<TimeUnit, String> timeUnitName() {
/*     */     return this.timeUnitName;
/*     */   }
/*     */   
/*     */   public static class $anonfun$3 extends AbstractFunction1<String, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(String s) {
/*     */       return (String)Duration$.MODULE$.scala$concurrent$duration$Duration$$words(s).last();
/*     */     }
/*     */   }
/*     */   
/*     */   public Map<String, TimeUnit> timeUnit() {
/*     */     return this.timeUnit;
/*     */   }
/*     */   
/*     */   public static class $anonfun$4 extends AbstractFunction1<Tuple2<TimeUnit, String>, List<Tuple2<String, TimeUnit>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<Tuple2<String, TimeUnit>> apply(Tuple2 x0$1) {
/*     */       if (x0$1 != null)
/*     */         return (List<Tuple2<String, TimeUnit>>)Duration$.MODULE$.scala$concurrent$duration$Duration$$expandLabels((String)x0$1._2()).map((Function1)new Duration$$anonfun$4$$anonfun$apply$1(this, x0$1), scala.collection.immutable.List$.MODULE$.canBuildFrom()); 
/*     */       throw new MatchError(x0$1);
/*     */     }
/*     */     
/*     */     public class Duration$$anonfun$4$$anonfun$apply$1 extends AbstractFunction1<String, Tuple2<String, TimeUnit>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Tuple2 x1$1;
/*     */       
/*     */       public final Tuple2<String, TimeUnit> apply(String x$5) {
/*     */         scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*     */         Object object = this.x1$1._1();
/*     */         scala.Predef$ArrowAssoc$ predef$ArrowAssoc$ = scala.Predef$ArrowAssoc$.MODULE$;
/*     */         return new Tuple2(x$5, object);
/*     */       }
/*     */       
/*     */       public Duration$$anonfun$4$$anonfun$apply$1(Duration.$anonfun$4 $outer, Tuple2 x1$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public Option<Tuple2<Object, TimeUnit>> unapply(String s) {
/*     */     Duration duration = (Duration)option.get();
/*     */     Option option;
/*     */     return (option = liftedTree1$1(s)).isEmpty() ? (Option<Tuple2<Object, TimeUnit>>)scala.None$.MODULE$ : MODULE$.unapply(duration);
/*     */   }
/*     */   
/*     */   private final Option liftedTree1$1(String s$1) {
/*     */     try {
/*     */     
/*     */     } catch (RuntimeException runtimeException) {}
/*     */     return (Option)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Tuple2<Object, TimeUnit>> unapply(Duration d) {
/*     */     return d.isFinite() ? (Option<Tuple2<Object, TimeUnit>>)new Some(new Tuple2(BoxesRunTime.boxToLong(d.length()), d.unit())) : (Option<Tuple2<Object, TimeUnit>>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public Duration fromNanos(double nanos) {
/*     */     if (nanos > Long.MAX_VALUE || nanos < Long.MIN_VALUE)
/*     */       throw new IllegalArgumentException((new StringBuilder()).append("trying to construct too large duration with ").append(BoxesRunTime.boxToDouble(nanos)).append("ns").toString()); 
/*     */     return scala.Predef$.MODULE$.double2Double(nanos).isInfinite() ? ((nanos > false) ? Inf() : MinusInf()) : (scala.Predef$.MODULE$.double2Double(nanos).isNaN() ? Undefined() : fromNanos((long)(nanos + 0.5D)));
/*     */   }
/*     */   
/*     */   public FiniteDuration fromNanos(long nanos) {
/*     */     return (nanos % 86400000000000L == 0L) ? apply(nanos / 86400000000000L, TimeUnit.DAYS) : ((nanos % 3600000000000L == 0L) ? apply(nanos / 3600000000000L, TimeUnit.HOURS) : ((nanos % 60000000000L == 0L) ? apply(nanos / 60000000000L, TimeUnit.MINUTES) : ((nanos % 1000000000L == 0L) ? apply(nanos / 1000000000L, TimeUnit.SECONDS) : ((nanos % 1000000L == 0L) ? apply(nanos / 1000000L, TimeUnit.MILLISECONDS) : ((nanos % 1000L == 0L) ? apply(nanos / 1000L, TimeUnit.MICROSECONDS) : apply(nanos, TimeUnit.NANOSECONDS))))));
/*     */   }
/*     */   
/*     */   public FiniteDuration Zero() {
/*     */     return this.Zero;
/*     */   }
/*     */   
/*     */   public Duration.Infinite Undefined() {
/*     */     return this.Undefined;
/*     */   }
/*     */   
/*     */   public static class $anon$1 extends Duration.Infinite {
/*     */     public String toString() {
/*     */       return "Duration.Undefined";
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*     */       return false;
/*     */     }
/*     */     
/*     */     public Duration $plus(Duration other) {
/*     */       return this;
/*     */     }
/*     */     
/*     */     public Duration $minus(Duration other) {
/*     */       return this;
/*     */     }
/*     */     
/*     */     public Duration $times(double factor) {
/*     */       return this;
/*     */     }
/*     */     
/*     */     public Duration $div(double factor) {
/*     */       return this;
/*     */     }
/*     */     
/*     */     public double $div(Duration other) {
/*     */       return Double.NaN;
/*     */     }
/*     */     
/*     */     public int compare(Duration other) {
/*     */       return (other == this) ? 0 : 1;
/*     */     }
/*     */     
/*     */     public Duration unary_$minus() {
/*     */       return this;
/*     */     }
/*     */     
/*     */     public double toUnit(TimeUnit unit) {
/*     */       return Double.NaN;
/*     */     }
/*     */   }
/*     */   
/*     */   public Duration.Infinite Inf() {
/*     */     return this.Inf;
/*     */   }
/*     */   
/*     */   public static class $anon$2 extends Duration.Infinite {
/*     */     public String toString() {
/*     */       return "Duration.Inf";
/*     */     }
/*     */     
/*     */     public int compare(Duration other) {
/*     */       boolean bool;
/*     */       if (other == Duration$.MODULE$.Undefined()) {
/*     */         bool = true;
/*     */       } else if (other == this) {
/*     */         bool = false;
/*     */       } else {
/*     */         bool = true;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public Duration unary_$minus() {
/*     */       return Duration$.MODULE$.MinusInf();
/*     */     }
/*     */     
/*     */     public double toUnit(TimeUnit unit) {
/*     */       return Double.POSITIVE_INFINITY;
/*     */     }
/*     */   }
/*     */   
/*     */   public Duration.Infinite MinusInf() {
/* 246 */     return this.MinusInf;
/*     */   }
/*     */   
/*     */   public static class $anon$3 extends Duration.Infinite {
/*     */     public String toString() {
/* 247 */       return "Duration.MinusInf";
/*     */     }
/*     */     
/*     */     public int compare(Duration other) {
/* 248 */       return (other == this) ? 0 : -1;
/*     */     }
/*     */     
/*     */     public Duration unary_$minus() {
/* 249 */       return Duration$.MODULE$.Inf();
/*     */     }
/*     */     
/*     */     public double toUnit(TimeUnit unit) {
/* 250 */       return Double.NEGATIVE_INFINITY;
/*     */     }
/*     */   }
/*     */   
/*     */   public FiniteDuration create(long length, TimeUnit unit) {
/* 259 */     return apply(length, unit);
/*     */   }
/*     */   
/*     */   public Duration create(double length, TimeUnit unit) {
/* 270 */     return apply(length, unit);
/*     */   }
/*     */   
/*     */   public FiniteDuration create(long length, String unit) {
/* 278 */     return apply(length, unit);
/*     */   }
/*     */   
/*     */   public Duration create(String s) {
/* 286 */     return apply(s);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\duration\Duration$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */