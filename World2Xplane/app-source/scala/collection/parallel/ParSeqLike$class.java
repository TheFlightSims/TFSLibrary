/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.AtomicIndexFlag;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.DefaultSignalling;
/*     */ import scala.collection.generic.Signalling;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.immutable.Repetition;
/*     */ import scala.collection.parallel.mutable.ParArray$;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichInt$;
/*     */ 
/*     */ public abstract class ParSeqLike$class {
/*     */   public static void $init$(ParSeqLike $this) {}
/*     */   
/*     */   public static PreciseSplitter iterator(ParSeqLike $this) {
/*  57 */     return $this.splitter();
/*     */   }
/*     */   
/*     */   public static int size(ParSeqLike $this) {
/*  59 */     return $this.length();
/*     */   }
/*     */   
/*     */   public static int segmentLength(ParSeqLike<T, Repr, Sequential> $this, Function1<T, Object> p, int from) {
/* 106 */     int realfrom = (from < 0) ? 0 : from;
/* 107 */     DefaultSignalling ctx = new ParSeqLike$$anon$4($this);
/* 108 */     ((AtomicIndexFlag)ctx).setIndexFlag(2147483647);
/* 109 */     return (from >= $this.length()) ? 0 : ((Tuple2)$this.tasksupport().executeAndWaitResult(new ParSeqLike.SegmentLength($this, p, 0, $this.delegatedSignalling2ops($this.splitter().psplitWithSignalling((Seq)Predef$.MODULE$.wrapIntArray(new int[] { realfrom, $this.length() - realfrom })).apply(1)).assign((Signalling)ctx))))._1$mcI$sp();
/*     */   }
/*     */   
/*     */   public static int indexWhere(ParSeqLike<T, Repr, Sequential> $this, Function1<T, Object> p, int from) {
/* 124 */     int realfrom = (from < 0) ? 0 : from;
/* 125 */     DefaultSignalling ctx = new ParSeqLike$$anon$5($this);
/* 126 */     ((AtomicIndexFlag)ctx).setIndexFlag(2147483647);
/* 127 */     return (from >= $this.length()) ? -1 : BoxesRunTime.unboxToInt($this.tasksupport().executeAndWaitResult(new ParSeqLike.IndexWhere($this, p, realfrom, $this.delegatedSignalling2ops($this.splitter().psplitWithSignalling((Seq)Predef$.MODULE$.wrapIntArray(new int[] { realfrom, $this.length() - realfrom })).apply(1)).assign((Signalling)ctx))));
/*     */   }
/*     */   
/*     */   public static int lastIndexWhere(ParSeqLike<T, Repr, Sequential> $this, Function1<T, Object> p, int end) {
/* 142 */     int until = (end >= $this.length()) ? $this.length() : (end + 1);
/* 143 */     DefaultSignalling ctx = new ParSeqLike$$anon$6($this);
/* 144 */     ((AtomicIndexFlag)ctx).setIndexFlag(-2147483648);
/* 145 */     return (end < 0) ? -1 : BoxesRunTime.unboxToInt($this.tasksupport().executeAndWaitResult(new ParSeqLike.LastIndexWhere($this, p, 0, $this.delegatedSignalling2ops($this.splitter().psplitWithSignalling((Seq)Predef$.MODULE$.wrapIntArray(new int[] { until, $this.length() - until })).apply(0)).assign((Signalling)ctx))));
/*     */   }
/*     */   
/*     */   public static ParSeq reverse(ParSeqLike $this) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*     */     //   6: aload_0
/*     */     //   7: new scala/collection/parallel/ParSeqLike$Reverse
/*     */     //   10: dup
/*     */     //   11: aload_0
/*     */     //   12: new scala/collection/parallel/ParSeqLike$$anonfun$reverse$1
/*     */     //   15: dup
/*     */     //   16: aload_0
/*     */     //   17: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;)V
/*     */     //   20: aload_0
/*     */     //   21: invokeinterface splitter : ()Lscala/collection/parallel/SeqSplitter;
/*     */     //   26: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;Lscala/Function0;Lscala/collection/parallel/SeqSplitter;)V
/*     */     //   29: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*     */     //   34: new scala/collection/parallel/ParSeqLike$$anonfun$reverse$2
/*     */     //   37: dup
/*     */     //   38: aload_0
/*     */     //   39: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;)V
/*     */     //   42: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*     */     //   47: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*     */     //   52: checkcast scala/collection/parallel/ParSeq
/*     */     //   55: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #149	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	56	0	$this	Lscala/collection/parallel/ParSeqLike;
/*     */   }
/*     */   
/*     */   public static Object reverseMap(ParSeqLike $this, Function1 f, CanBuildFrom bf) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_2
/*     */     //   2: aload_0
/*     */     //   3: invokeinterface repr : ()Lscala/collection/parallel/ParIterable;
/*     */     //   8: invokeinterface apply : (Ljava/lang/Object;)Lscala/collection/mutable/Builder;
/*     */     //   13: invokeinterface builder2ops : (Lscala/collection/mutable/Builder;)Lscala/collection/parallel/ParIterableLike$BuilderOps;
/*     */     //   18: invokeinterface isCombiner : ()Z
/*     */     //   23: ifeq -> 83
/*     */     //   26: aload_0
/*     */     //   27: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*     */     //   32: aload_0
/*     */     //   33: new scala/collection/parallel/ParSeqLike$ReverseMap
/*     */     //   36: dup
/*     */     //   37: aload_0
/*     */     //   38: aload_1
/*     */     //   39: new scala/collection/parallel/ParSeqLike$$anonfun$reverseMap$1
/*     */     //   42: dup
/*     */     //   43: aload_0
/*     */     //   44: aload_2
/*     */     //   45: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;Lscala/collection/generic/CanBuildFrom;)V
/*     */     //   48: aload_0
/*     */     //   49: invokeinterface splitter : ()Lscala/collection/parallel/SeqSplitter;
/*     */     //   54: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;Lscala/Function1;Lscala/Function0;Lscala/collection/parallel/SeqSplitter;)V
/*     */     //   57: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*     */     //   62: new scala/collection/parallel/ParSeqLike$$anonfun$reverseMap$2
/*     */     //   65: dup
/*     */     //   66: aload_0
/*     */     //   67: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;)V
/*     */     //   70: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*     */     //   75: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*     */     //   80: goto -> 117
/*     */     //   83: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*     */     //   86: aload_0
/*     */     //   87: invokeinterface seq : ()Lscala/collection/Iterable;
/*     */     //   92: checkcast scala/collection/SeqLike
/*     */     //   95: aload_1
/*     */     //   96: aload_0
/*     */     //   97: aload_2
/*     */     //   98: invokeinterface bf2seq : (Lscala/collection/generic/CanBuildFrom;)Lscala/collection/generic/CanBuildFrom;
/*     */     //   103: invokeinterface reverseMap : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   108: aload_0
/*     */     //   109: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*     */     //   114: invokevirtual setTaskSupport : (Ljava/lang/Object;Lscala/collection/parallel/TaskSupport;)Ljava/lang/Object;
/*     */     //   117: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #152	-> 0
/*     */     //   #153	-> 26
/*     */     //   #154	-> 32
/*     */     //   #153	-> 75
/*     */     //   #156	-> 83
/*     */     //   #152	-> 117
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	118	0	$this	Lscala/collection/parallel/ParSeqLike;
/*     */     //   0	118	1	f	Lscala/Function1;
/*     */     //   0	118	2	bf	Lscala/collection/generic/CanBuildFrom;
/*     */   }
/*     */   
/*     */   public static boolean startsWith(ParSeqLike $this, GenSeq that, int offset) {
/* 180 */     return BoxesRunTime.unboxToBoolean(package$.MODULE$.traversable2ops((GenTraversableOnce<?>)that).ifParSeq((Function1)new ParSeqLike$$anonfun$startsWith$2($this, offset)).otherwise((Function0)new ParSeqLike$$anonfun$startsWith$1($this, that, offset)));
/*     */   }
/*     */   
/*     */   public static boolean sameElements(ParSeqLike<T, Repr, Sequential> $this, GenIterable that) {
/* 185 */     return BoxesRunTime.unboxToBoolean(package$.MODULE$.traversable2ops((GenTraversableOnce<?>)that).ifParSeq((Function1)new ParSeqLike$$anonfun$sameElements$2($this)).otherwise((Function0)new ParSeqLike$$anonfun$sameElements$1($this, (ParSeqLike<T, Repr, Sequential>)that)));
/*     */   }
/*     */   
/*     */   public static boolean endsWith(ParSeqLike $this, GenSeq that) {
/* 203 */     return BoxesRunTime.unboxToBoolean(package$.MODULE$.traversable2ops((GenTraversableOnce<?>)that).ifParSeq((Function1)new ParSeqLike$$anonfun$endsWith$2($this, (ParSeqLike<T, Repr, Sequential>)that)).otherwise((Function0)new ParSeqLike$$anonfun$endsWith$1($this, (ParSeqLike<T, Repr, Sequential>)that)));
/*     */   }
/*     */   
/*     */   public static Object patch(ParSeqLike $this, int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/*     */     // Byte code:
/*     */     //   0: getstatic scala/runtime/RichInt$.MODULE$ : Lscala/runtime/RichInt$;
/*     */     //   3: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   6: astore #5
/*     */     //   8: iload_3
/*     */     //   9: aload_0
/*     */     //   10: invokeinterface length : ()I
/*     */     //   15: iload_1
/*     */     //   16: isub
/*     */     //   17: invokevirtual min$extension : (II)I
/*     */     //   20: istore #6
/*     */     //   22: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*     */     //   25: aload_2
/*     */     //   26: invokevirtual traversable2ops : (Lscala/collection/GenTraversableOnce;)Lscala/collection/parallel/TraversableOps;
/*     */     //   29: invokeinterface isParSeq : ()Z
/*     */     //   34: ifeq -> 308
/*     */     //   37: aload_0
/*     */     //   38: aload #4
/*     */     //   40: aload_0
/*     */     //   41: invokeinterface repr : ()Lscala/collection/parallel/ParIterable;
/*     */     //   46: invokeinterface apply : (Ljava/lang/Object;)Lscala/collection/mutable/Builder;
/*     */     //   51: invokeinterface builder2ops : (Lscala/collection/mutable/Builder;)Lscala/collection/parallel/ParIterableLike$BuilderOps;
/*     */     //   56: invokeinterface isCombiner : ()Z
/*     */     //   61: ifeq -> 308
/*     */     //   64: aload_0
/*     */     //   65: invokeinterface size : ()I
/*     */     //   70: iload #6
/*     */     //   72: isub
/*     */     //   73: aload_2
/*     */     //   74: invokeinterface size : ()I
/*     */     //   79: iadd
/*     */     //   80: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*     */     //   83: invokevirtual MIN_FOR_COPY : ()I
/*     */     //   86: if_icmple -> 308
/*     */     //   89: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*     */     //   92: aload_2
/*     */     //   93: invokevirtual traversable2ops : (Lscala/collection/GenTraversableOnce;)Lscala/collection/parallel/TraversableOps;
/*     */     //   96: invokeinterface asParSeq : ()Lscala/collection/parallel/ParSeq;
/*     */     //   101: astore #7
/*     */     //   103: aload_0
/*     */     //   104: invokeinterface splitter : ()Lscala/collection/parallel/SeqSplitter;
/*     */     //   109: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   112: iconst_3
/*     */     //   113: newarray int
/*     */     //   115: dup
/*     */     //   116: iconst_0
/*     */     //   117: iload_1
/*     */     //   118: iastore
/*     */     //   119: dup
/*     */     //   120: iconst_1
/*     */     //   121: iload_3
/*     */     //   122: iastore
/*     */     //   123: dup
/*     */     //   124: iconst_2
/*     */     //   125: aload_0
/*     */     //   126: invokeinterface length : ()I
/*     */     //   131: iload_1
/*     */     //   132: isub
/*     */     //   133: iload #6
/*     */     //   135: isub
/*     */     //   136: iastore
/*     */     //   137: invokevirtual wrapIntArray : ([I)Lscala/collection/mutable/WrappedArray;
/*     */     //   140: invokeinterface psplitWithSignalling : (Lscala/collection/Seq;)Lscala/collection/Seq;
/*     */     //   145: astore #9
/*     */     //   147: aload_0
/*     */     //   148: new scala/collection/parallel/ParSeqLike$$anonfun$2
/*     */     //   151: dup
/*     */     //   152: aload_0
/*     */     //   153: aload #4
/*     */     //   155: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;Lscala/collection/generic/CanBuildFrom;)V
/*     */     //   158: invokeinterface combinerFactory : (Lscala/Function0;)Lscala/collection/parallel/CombinerFactory;
/*     */     //   163: astore #8
/*     */     //   165: new scala/collection/parallel/ParIterableLike$Copy
/*     */     //   168: dup
/*     */     //   169: aload_0
/*     */     //   170: aload #8
/*     */     //   172: aload #9
/*     */     //   174: iconst_0
/*     */     //   175: invokeinterface apply : (I)Ljava/lang/Object;
/*     */     //   180: checkcast scala/collection/parallel/IterableSplitter
/*     */     //   183: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*     */     //   186: astore #10
/*     */     //   188: aload_0
/*     */     //   189: new scala/collection/parallel/ParSeqLike$$anonfun$3
/*     */     //   192: dup
/*     */     //   193: aload_0
/*     */     //   194: aload #7
/*     */     //   196: aload #8
/*     */     //   198: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;Lscala/collection/parallel/ParSeq;Lscala/collection/parallel/CombinerFactory;)V
/*     */     //   201: invokeinterface wrap : (Lscala/Function0;)Lscala/collection/parallel/ParIterableLike$NonDivisible;
/*     */     //   206: astore #11
/*     */     //   208: new scala/collection/parallel/ParIterableLike$Copy
/*     */     //   211: dup
/*     */     //   212: aload_0
/*     */     //   213: aload #8
/*     */     //   215: aload #9
/*     */     //   217: iconst_2
/*     */     //   218: invokeinterface apply : (I)Ljava/lang/Object;
/*     */     //   223: checkcast scala/collection/parallel/IterableSplitter
/*     */     //   226: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*     */     //   229: astore #12
/*     */     //   231: aload_0
/*     */     //   232: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*     */     //   237: aload_0
/*     */     //   238: aload_0
/*     */     //   239: aload_0
/*     */     //   240: aload #10
/*     */     //   242: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*     */     //   247: aload #11
/*     */     //   249: new scala/collection/parallel/ParSeqLike$$anonfun$patch$1
/*     */     //   252: dup
/*     */     //   253: aload_0
/*     */     //   254: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;)V
/*     */     //   257: invokeinterface parallel : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;Lscala/Function2;)Lscala/collection/parallel/ParIterableLike$ParComposite;
/*     */     //   262: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*     */     //   267: aload #12
/*     */     //   269: new scala/collection/parallel/ParSeqLike$$anonfun$patch$2
/*     */     //   272: dup
/*     */     //   273: aload_0
/*     */     //   274: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;)V
/*     */     //   277: invokeinterface parallel : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;Lscala/Function2;)Lscala/collection/parallel/ParIterableLike$ParComposite;
/*     */     //   282: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*     */     //   287: new scala/collection/parallel/ParSeqLike$$anonfun$patch$3
/*     */     //   290: dup
/*     */     //   291: aload_0
/*     */     //   292: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;)V
/*     */     //   295: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*     */     //   300: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*     */     //   305: goto -> 322
/*     */     //   308: aload_0
/*     */     //   309: iload_1
/*     */     //   310: aload_2
/*     */     //   311: invokeinterface seq : ()Lscala/collection/Seq;
/*     */     //   316: iload_3
/*     */     //   317: aload #4
/*     */     //   319: invokestatic patch_sequential : (Lscala/collection/parallel/ParSeqLike;ILscala/collection/Seq;ILscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   322: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #206	-> 3
/*     */     //   #207	-> 22
/*     */     //   #208	-> 89
/*     */     //   #209	-> 103
/*     */     //   #210	-> 147
/*     */     //   #211	-> 165
/*     */     //   #212	-> 188
/*     */     //   #216	-> 208
/*     */     //   #217	-> 231
/*     */     //   #218	-> 287
/*     */     //   #217	-> 295
/*     */     //   #220	-> 308
/*     */     //   #205	-> 322
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	323	0	$this	Lscala/collection/parallel/ParSeqLike;
/*     */     //   0	323	1	from	I
/*     */     //   0	323	2	patch	Lscala/collection/GenSeq;
/*     */     //   0	323	3	replaced	I
/*     */     //   0	323	4	bf	Lscala/collection/generic/CanBuildFrom;
/*     */     //   22	301	6	realreplaced	I
/*     */     //   103	202	7	that	Lscala/collection/parallel/ParSeq;
/*     */     //   147	158	9	pits	Lscala/collection/Seq;
/*     */     //   165	140	8	cfactory	Lscala/collection/parallel/CombinerFactory;
/*     */     //   188	117	10	copystart	Lscala/collection/parallel/ParIterableLike$Copy;
/*     */     //   208	97	11	copymiddle	Lscala/collection/parallel/ParIterableLike$NonDivisible;
/*     */     //   231	74	12	copyend	Lscala/collection/parallel/ParIterableLike$Copy;
/*     */   }
/*     */   
/*     */   private static Object patch_sequential(ParSeqLike $this, int fromarg, Seq patch, int r, CanBuildFrom bf) {
/* 224 */     Predef$ predef$1 = Predef$.MODULE$;
/* 224 */     int from = RichInt$.MODULE$.max$extension(0, fromarg);
/* 225 */     Builder b = bf.apply($this.repr());
/* 226 */     Predef$ predef$2 = Predef$.MODULE$;
/* 226 */     int i = RichInt$.MODULE$.min$extension(r, $this.length() - from);
/* 226 */     Predef$ predef$3 = Predef$.MODULE$;
/* 226 */     int repl = RichInt$.MODULE$.max$extension(i, 0);
/* 227 */     Seq pits = $this.splitter().psplitWithSignalling((Seq)Predef$.MODULE$.wrapIntArray(new int[] { from, repl, $this.length() - from - repl }));
/* 228 */     b.$plus$plus$eq((TraversableOnce)pits.apply(0));
/* 229 */     b.$plus$plus$eq((TraversableOnce)patch);
/* 230 */     b.$plus$plus$eq((TraversableOnce)pits.apply(2));
/* 231 */     return package$.MODULE$.setTaskSupport(b.result(), $this.tasksupport());
/*     */   }
/*     */   
/*     */   public static Object updated(ParSeqLike $this, int index, Object elem, CanBuildFrom bf) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_3
/*     */     //   2: aload_0
/*     */     //   3: invokeinterface repr : ()Lscala/collection/parallel/ParIterable;
/*     */     //   8: invokeinterface apply : (Ljava/lang/Object;)Lscala/collection/mutable/Builder;
/*     */     //   13: invokeinterface builder2ops : (Lscala/collection/mutable/Builder;)Lscala/collection/parallel/ParIterableLike$BuilderOps;
/*     */     //   18: invokeinterface isCombiner : ()Z
/*     */     //   23: ifeq -> 90
/*     */     //   26: aload_0
/*     */     //   27: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*     */     //   32: aload_0
/*     */     //   33: new scala/collection/parallel/ParSeqLike$Updated
/*     */     //   36: dup
/*     */     //   37: aload_0
/*     */     //   38: iload_1
/*     */     //   39: aload_2
/*     */     //   40: aload_0
/*     */     //   41: new scala/collection/parallel/ParSeqLike$$anonfun$updated$1
/*     */     //   44: dup
/*     */     //   45: aload_0
/*     */     //   46: aload_3
/*     */     //   47: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;Lscala/collection/generic/CanBuildFrom;)V
/*     */     //   50: invokeinterface combinerFactory : (Lscala/Function0;)Lscala/collection/parallel/CombinerFactory;
/*     */     //   55: aload_0
/*     */     //   56: invokeinterface splitter : ()Lscala/collection/parallel/SeqSplitter;
/*     */     //   61: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;ILjava/lang/Object;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/SeqSplitter;)V
/*     */     //   64: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*     */     //   69: new scala/collection/parallel/ParSeqLike$$anonfun$updated$2
/*     */     //   72: dup
/*     */     //   73: aload_0
/*     */     //   74: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;)V
/*     */     //   77: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*     */     //   82: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*     */     //   87: goto -> 125
/*     */     //   90: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*     */     //   93: aload_0
/*     */     //   94: invokeinterface seq : ()Lscala/collection/Iterable;
/*     */     //   99: checkcast scala/collection/SeqLike
/*     */     //   102: iload_1
/*     */     //   103: aload_2
/*     */     //   104: aload_0
/*     */     //   105: aload_3
/*     */     //   106: invokeinterface bf2seq : (Lscala/collection/generic/CanBuildFrom;)Lscala/collection/generic/CanBuildFrom;
/*     */     //   111: invokeinterface updated : (ILjava/lang/Object;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   116: aload_0
/*     */     //   117: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*     */     //   122: invokevirtual setTaskSupport : (Ljava/lang/Object;Lscala/collection/parallel/TaskSupport;)Ljava/lang/Object;
/*     */     //   125: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #234	-> 0
/*     */     //   #235	-> 26
/*     */     //   #236	-> 32
/*     */     //   #237	-> 69
/*     */     //   #236	-> 77
/*     */     //   #235	-> 82
/*     */     //   #240	-> 90
/*     */     //   #234	-> 125
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	126	0	$this	Lscala/collection/parallel/ParSeqLike;
/*     */     //   0	126	1	index	I
/*     */     //   0	126	2	elem	Ljava/lang/Object;
/*     */     //   0	126	3	bf	Lscala/collection/generic/CanBuildFrom;
/*     */   }
/*     */   
/*     */   public static Object $plus$colon(ParSeqLike $this, Object elem, CanBuildFrom bf) {
/* 246 */     return $this.patch(0, (GenSeq)ParArray$.MODULE$.apply((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { elem }, )), 0, bf);
/*     */   }
/*     */   
/*     */   public static Object $colon$plus(ParSeqLike $this, Object elem, CanBuildFrom bf) {
/* 250 */     return $this.patch($this.length(), (GenSeq)ParArray$.MODULE$.apply((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { elem }, )), 0, bf);
/*     */   }
/*     */   
/*     */   public static Object padTo(ParSeqLike $this, int len, Object elem, CanBuildFrom bf) {
/* 253 */     return ($this.length() < len) ? 
/* 254 */       $this.patch($this.length(), (GenSeq)new Repetition(elem, len - $this.length()), 0, bf) : 
/* 255 */       $this.patch($this.length(), (GenSeq)Nil$.MODULE$, 0, bf);
/*     */   }
/*     */   
/*     */   public static Object zip(ParSeqLike $this, GenIterable that, CanBuildFrom bf) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_2
/*     */     //   2: aload_0
/*     */     //   3: invokeinterface repr : ()Lscala/collection/parallel/ParIterable;
/*     */     //   8: invokeinterface apply : (Ljava/lang/Object;)Lscala/collection/mutable/Builder;
/*     */     //   13: invokeinterface builder2ops : (Lscala/collection/mutable/Builder;)Lscala/collection/parallel/ParIterableLike$BuilderOps;
/*     */     //   18: invokeinterface isCombiner : ()Z
/*     */     //   23: ifeq -> 151
/*     */     //   26: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*     */     //   29: aload_1
/*     */     //   30: invokevirtual traversable2ops : (Lscala/collection/GenTraversableOnce;)Lscala/collection/parallel/TraversableOps;
/*     */     //   33: invokeinterface isParSeq : ()Z
/*     */     //   38: ifeq -> 151
/*     */     //   41: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*     */     //   44: aload_1
/*     */     //   45: invokevirtual traversable2ops : (Lscala/collection/GenTraversableOnce;)Lscala/collection/parallel/TraversableOps;
/*     */     //   48: invokeinterface asParSeq : ()Lscala/collection/parallel/ParSeq;
/*     */     //   53: astore #5
/*     */     //   55: aload_0
/*     */     //   56: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*     */     //   61: aload_0
/*     */     //   62: new scala/collection/parallel/ParSeqLike$Zip
/*     */     //   65: dup
/*     */     //   66: aload_0
/*     */     //   67: getstatic scala/runtime/RichInt$.MODULE$ : Lscala/runtime/RichInt$;
/*     */     //   70: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   73: aload_0
/*     */     //   74: invokeinterface length : ()I
/*     */     //   79: istore #4
/*     */     //   81: astore_3
/*     */     //   82: iload #4
/*     */     //   84: aload #5
/*     */     //   86: invokeinterface length : ()I
/*     */     //   91: invokevirtual min$extension : (II)I
/*     */     //   94: aload_0
/*     */     //   95: new scala/collection/parallel/ParSeqLike$$anonfun$zip$1
/*     */     //   98: dup
/*     */     //   99: aload_0
/*     */     //   100: aload_2
/*     */     //   101: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;Lscala/collection/generic/CanBuildFrom;)V
/*     */     //   104: invokeinterface combinerFactory : (Lscala/Function0;)Lscala/collection/parallel/CombinerFactory;
/*     */     //   109: aload_0
/*     */     //   110: invokeinterface splitter : ()Lscala/collection/parallel/SeqSplitter;
/*     */     //   115: aload #5
/*     */     //   117: invokeinterface splitter : ()Lscala/collection/parallel/SeqSplitter;
/*     */     //   122: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;ILscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/SeqSplitter;Lscala/collection/parallel/SeqSplitter;)V
/*     */     //   125: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*     */     //   130: new scala/collection/parallel/ParSeqLike$$anonfun$zip$2
/*     */     //   133: dup
/*     */     //   134: aload_0
/*     */     //   135: invokespecial <init> : (Lscala/collection/parallel/ParSeqLike;)V
/*     */     //   138: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*     */     //   143: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*     */     //   148: goto -> 159
/*     */     //   151: aload_0
/*     */     //   152: aload_1
/*     */     //   153: aload_2
/*     */     //   154: invokeinterface scala$collection$parallel$ParSeqLike$$super$zip : (Lscala/collection/GenIterable;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   159: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #257	-> 0
/*     */     //   #258	-> 41
/*     */     //   #259	-> 55
/*     */     //   #260	-> 61
/*     */     //   #261	-> 130
/*     */     //   #260	-> 138
/*     */     //   #259	-> 143
/*     */     //   #264	-> 151
/*     */     //   #257	-> 159
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	160	0	$this	Lscala/collection/parallel/ParSeqLike;
/*     */     //   0	160	1	that	Lscala/collection/GenIterable;
/*     */     //   0	160	2	bf	Lscala/collection/generic/CanBuildFrom;
/*     */     //   55	93	5	thatseq	Lscala/collection/parallel/ParSeq;
/*     */   }
/*     */   
/*     */   public static boolean corresponds(ParSeqLike $this, GenSeq that, Function2 p) {
/* 281 */     return BoxesRunTime.unboxToBoolean(package$.MODULE$.traversable2ops((GenTraversableOnce<?>)that).ifParSeq((Function1)new ParSeqLike$$anonfun$corresponds$2($this, (ParSeqLike<T, Repr, Sequential>)p)).otherwise((Function0)new ParSeqLike$$anonfun$corresponds$1($this, that, (ParSeqLike<T, Repr, Sequential>)p)));
/*     */   }
/*     */   
/*     */   public static ParSeq diff(ParSeqLike $this, GenSeq that) {
/* 283 */     return (ParSeq)$this.sequentially(
/* 284 */         (Function1)new ParSeqLike$$anonfun$diff$1($this, (ParSeqLike<T, Repr, Sequential>)that));
/*     */   }
/*     */   
/*     */   public static ParSeq intersect(ParSeqLike $this, GenSeq that) {
/* 308 */     return (ParSeq)$this.sequentially(
/* 309 */         (Function1)new ParSeqLike$$anonfun$intersect$1($this, (ParSeqLike<T, Repr, Sequential>)that));
/*     */   }
/*     */   
/*     */   public static ParSeq distinct(ParSeqLike<T, Repr, Sequential> $this) {
/* 317 */     return (ParSeq)$this.sequentially(
/* 318 */         (Function1)new ParSeqLike$$anonfun$distinct$1($this));
/*     */   }
/*     */   
/*     */   public static String toString(ParSeqLike $this) {
/* 321 */     return $this.seq().mkString((new StringBuilder()).append($this.stringPrefix()).append("(").toString(), ", ", ")");
/*     */   }
/*     */   
/*     */   public static ParSeq toSeq(ParSeqLike $this) {
/* 323 */     return (ParSeq)$this;
/*     */   }
/*     */   
/*     */   public static ParSeqView view(ParSeqLike<T, Repr, Sequential> $this) {
/* 325 */     return new ParSeqLike$$anon$1($this);
/*     */   }
/*     */   
/*     */   public static SeqSplitter down(ParSeqLike $this, IterableSplitter p) {
/* 337 */     return (SeqSplitter)p;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParSeqLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */