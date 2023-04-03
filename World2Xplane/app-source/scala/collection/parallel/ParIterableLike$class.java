/*      */ package scala.collection.parallel;
/*      */ 
/*      */ import scala.Function0;
/*      */ import scala.Function1;
/*      */ import scala.Function2;
/*      */ import scala.None$;
/*      */ import scala.Option;
/*      */ import scala.PartialFunction;
/*      */ import scala.Predef$;
/*      */ import scala.Some;
/*      */ import scala.Tuple2;
/*      */ import scala.collection.DebugUtils$;
/*      */ import scala.collection.GenIterable;
/*      */ import scala.collection.GenTraversable;
/*      */ import scala.collection.GenTraversableOnce;
/*      */ import scala.collection.Iterator;
/*      */ import scala.collection.Parallelizable;
/*      */ import scala.collection.Seq;
/*      */ import scala.collection.Seq$;
/*      */ import scala.collection.generic.CanBuildFrom;
/*      */ import scala.collection.generic.DelegatedSignalling;
/*      */ import scala.collection.generic.Signalling;
/*      */ import scala.collection.immutable.IndexedSeq;
/*      */ import scala.collection.immutable.List;
/*      */ import scala.collection.immutable.Nil$;
/*      */ import scala.collection.immutable.Stream;
/*      */ import scala.collection.immutable.Vector;
/*      */ import scala.collection.immutable.Vector$;
/*      */ import scala.collection.mutable.ArrayBuffer;
/*      */ import scala.collection.mutable.Buffer;
/*      */ import scala.collection.mutable.Builder;
/*      */ import scala.collection.mutable.StringBuilder;
/*      */ import scala.collection.parallel.immutable.ParMap;
/*      */ import scala.collection.parallel.immutable.ParRange$;
/*      */ import scala.collection.parallel.immutable.ParSet;
/*      */ import scala.math.Numeric;
/*      */ import scala.math.Ordering;
/*      */ import scala.reflect.ClassTag;
/*      */ import scala.runtime.BoxesRunTime;
/*      */ import scala.runtime.ObjectRef;
/*      */ import scala.runtime.RichInt$;
/*      */ import scala.runtime.ScalaRunTime$;
/*      */ 
/*      */ public abstract class ParIterableLike$class {
/*      */   public static void $init$(ParIterableLike $this) {
/*  166 */     $this.scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(package$.MODULE$.defaultTaskSupport());
/*      */   }
/*      */   
/*      */   public static void initTaskSupport(ParIterableLike $this) {
/*  169 */     $this.scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(package$.MODULE$.defaultTaskSupport());
/*      */   }
/*      */   
/*      */   public static TaskSupport tasksupport(ParIterableLike $this) {
/*  178 */     TaskSupport ts = $this.scala$collection$parallel$ParIterableLike$$_tasksupport();
/*  180 */     $this.scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(package$.MODULE$.defaultTaskSupport());
/*  181 */     return (ts == null) ? package$.MODULE$.defaultTaskSupport() : 
/*  182 */       ts;
/*      */   }
/*      */   
/*      */   public static void tasksupport_$eq(ParIterableLike $this, TaskSupport ts) {
/*  203 */     $this.scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(ts);
/*      */   }
/*      */   
/*      */   public static ParIterable repr(ParIterableLike $this) {
/*  207 */     return (ParIterable)$this;
/*      */   }
/*      */   
/*      */   public static final boolean isTraversableAgain(ParIterableLike $this) {
/*  209 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean hasDefiniteSize(ParIterableLike $this) {
/*  211 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isEmpty(ParIterableLike $this) {
/*  213 */     return ($this.size() == 0);
/*      */   }
/*      */   
/*      */   public static boolean nonEmpty(ParIterableLike $this) {
/*  215 */     return ($this.size() != 0);
/*      */   }
/*      */   
/*      */   public static Object head(ParIterableLike $this) {
/*  217 */     return $this.iterator().next();
/*      */   }
/*      */   
/*      */   public static Option headOption(ParIterableLike $this) {
/*  219 */     return $this.nonEmpty() ? (Option)new Some($this.head()) : (Option)None$.MODULE$;
/*      */   }
/*      */   
/*      */   public static ParIterable tail(ParIterableLike $this) {
/*  221 */     return (ParIterable)$this.drop(1);
/*      */   }
/*      */   
/*      */   public static Object last(ParIterableLike $this) {
/*  224 */     ObjectRef lst = new ObjectRef($this.head());
/*  225 */     $this.seq().foreach((Function1)new ParIterableLike$$anonfun$last$1($this, (ParIterableLike<T, Repr, Sequential>)lst));
/*  226 */     return lst.elem;
/*      */   }
/*      */   
/*      */   public static Option lastOption(ParIterableLike $this) {
/*  229 */     return $this.nonEmpty() ? (Option)new Some($this.last()) : (Option)None$.MODULE$;
/*      */   }
/*      */   
/*      */   public static ParIterable init(ParIterableLike $this) {
/*  231 */     return (ParIterable)$this.take($this.size() - 1);
/*      */   }
/*      */   
/*      */   public static Splitter iterator(ParIterableLike $this) {
/*  247 */     return $this.splitter();
/*      */   }
/*      */   
/*      */   public static ParIterable par(ParIterableLike $this) {
/*  249 */     return (ParIterable)$this.repr();
/*      */   }
/*      */   
/*      */   public static boolean isStrictSplitterCollection(ParIterableLike $this) {
/*  261 */     return true;
/*      */   }
/*      */   
/*      */   public static Combiner reuse(ParIterableLike $this, Option oldc, Combiner newc) {
/*  276 */     return newc;
/*      */   }
/*      */   
/*      */   public static ParIterableLike.TaskOps task2ops(ParIterableLike $this, ParIterableLike.StrictSplitterCheckTask tsk) {
/*  304 */     return new ParIterableLike$$anon$12($this, (ParIterableLike<T, Repr, Sequential>)tsk);
/*      */   }
/*      */   
/*      */   public static ParIterableLike.NonDivisible wrap(ParIterableLike $this, Function0 body) {
/*  318 */     return new ParIterableLike$$anon$5($this, (ParIterableLike<T, Repr, Sequential>)body);
/*      */   }
/*      */   
/*      */   public static ParIterableLike.SignallingOps delegatedSignalling2ops(ParIterableLike $this, DelegatedSignalling it) {
/*  324 */     return new ParIterableLike$$anon$13($this, (ParIterableLike<T, Repr, Sequential>)it);
/*      */   }
/*      */   
/*      */   public static ParIterableLike.BuilderOps builder2ops(ParIterableLike $this, Builder cb) {
/*  331 */     return new ParIterableLike$$anon$14($this, (ParIterableLike<T, Repr, Sequential>)cb);
/*      */   }
/*      */   
/*      */   public static CanBuildFrom bf2seq(ParIterableLike $this, CanBuildFrom bf) {
/*  341 */     return new ParIterableLike$$anon$16($this, (ParIterableLike<T, Repr, Sequential>)bf);
/*      */   }
/*      */   
/*      */   public static ParIterable sequentially(ParIterableLike $this, Function1 b) {
/*  346 */     return (ParIterable)((Parallelizable)b.apply($this.seq())).par();
/*      */   }
/*      */   
/*      */   public static String mkString(ParIterableLike $this, String start, String sep, String end) {
/*  348 */     return $this.seq().mkString(start, sep, end);
/*      */   }
/*      */   
/*      */   public static String mkString(ParIterableLike $this, String sep) {
/*  350 */     return $this.seq().mkString("", sep, "");
/*      */   }
/*      */   
/*      */   public static String mkString(ParIterableLike $this) {
/*  352 */     return $this.seq().mkString("");
/*      */   }
/*      */   
/*      */   public static String toString(ParIterableLike $this) {
/*  354 */     return $this.seq().mkString((new StringBuilder()).append($this.stringPrefix()).append("(").toString(), ", ", ")");
/*      */   }
/*      */   
/*      */   public static boolean canEqual(ParIterableLike $this, Object other) {
/*  356 */     return true;
/*      */   }
/*      */   
/*      */   public static Object reduce(ParIterableLike $this, Function2 op) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: aload_0
/*      */     //   7: new scala/collection/parallel/ParIterableLike$Reduce
/*      */     //   10: dup
/*      */     //   11: aload_0
/*      */     //   12: aload_1
/*      */     //   13: aload_0
/*      */     //   14: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   19: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/Function2;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   22: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   27: new scala/collection/parallel/ParIterableLike$$anonfun$reduce$1
/*      */     //   30: dup
/*      */     //   31: aload_0
/*      */     //   32: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   35: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   40: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   45: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #374	-> 0
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	46	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	46	1	op	Lscala/Function2;
/*      */   }
/*      */   
/*      */   public static Option reduceOption(ParIterableLike $this, Function2 op) {
/*  391 */     return $this.isEmpty() ? (Option)None$.MODULE$ : (Option)new Some($this.reduce(op));
/*      */   }
/*      */   
/*      */   public static Object fold(ParIterableLike $this, Object z, Function2 op) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: new scala/collection/parallel/ParIterableLike$Fold
/*      */     //   9: dup
/*      */     //   10: aload_0
/*      */     //   11: aload_1
/*      */     //   12: aload_2
/*      */     //   13: aload_0
/*      */     //   14: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   19: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Ljava/lang/Object;Lscala/Function2;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   22: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   27: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #409	-> 0
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	28	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	28	1	z	Ljava/lang/Object;
/*      */     //   0	28	2	op	Lscala/Function2;
/*      */   }
/*      */   
/*      */   public static Object aggregate(ParIterableLike $this, Object z, Function2 seqop, Function2 combop) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: new scala/collection/parallel/ParIterableLike$Aggregate
/*      */     //   9: dup
/*      */     //   10: aload_0
/*      */     //   11: aload_1
/*      */     //   12: aload_2
/*      */     //   13: aload_3
/*      */     //   14: aload_0
/*      */     //   15: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   20: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Ljava/lang/Object;Lscala/Function2;Lscala/Function2;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   23: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   28: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #441	-> 0
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	29	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	29	1	z	Ljava/lang/Object;
/*      */     //   0	29	2	seqop	Lscala/Function2;
/*      */     //   0	29	3	combop	Lscala/Function2;
/*      */   }
/*      */   
/*      */   public static Object foldLeft(ParIterableLike $this, Object z, Function2 op) {
/*  444 */     return $this.seq().foldLeft(z, op);
/*      */   }
/*      */   
/*      */   public static Object foldRight(ParIterableLike $this, Object z, Function2 op) {
/*  446 */     return $this.seq().foldRight(z, op);
/*      */   }
/*      */   
/*      */   public static Object reduceLeft(ParIterableLike $this, Function2 op) {
/*  448 */     return $this.seq().reduceLeft(op);
/*      */   }
/*      */   
/*      */   public static Object reduceRight(ParIterableLike $this, Function2 op) {
/*  450 */     return $this.seq().reduceRight(op);
/*      */   }
/*      */   
/*      */   public static Option reduceLeftOption(ParIterableLike $this, Function2 op) {
/*  452 */     return $this.seq().reduceLeftOption(op);
/*      */   }
/*      */   
/*      */   public static Option reduceRightOption(ParIterableLike $this, Function2 op) {
/*  454 */     return $this.seq().reduceRightOption(op);
/*      */   }
/*      */   
/*      */   public static void foreach(ParIterableLike $this, Function1 f) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: new scala/collection/parallel/ParIterableLike$Foreach
/*      */     //   9: dup
/*      */     //   10: aload_0
/*      */     //   11: aload_1
/*      */     //   12: aload_0
/*      */     //   13: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   18: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/Function1;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   21: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   26: pop
/*      */     //   27: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #462	-> 0
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	28	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	28	1	f	Lscala/Function1;
/*      */   }
/*      */   
/*      */   public static int count(ParIterableLike<T, Repr, Sequential> $this, Function1<T, Object> p) {
/*  466 */     return BoxesRunTime.unboxToInt($this.tasksupport().executeAndWaitResult(new ParIterableLike.Count($this, p, $this.splitter())));
/*      */   }
/*      */   
/*      */   public static Object sum(ParIterableLike $this, Numeric num) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: new scala/collection/parallel/ParIterableLike$Sum
/*      */     //   9: dup
/*      */     //   10: aload_0
/*      */     //   11: aload_1
/*      */     //   12: aload_0
/*      */     //   13: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   18: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/math/Numeric;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   21: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   26: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #470	-> 0
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	27	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	27	1	num	Lscala/math/Numeric;
/*      */   }
/*      */   
/*      */   public static Object product(ParIterableLike $this, Numeric num) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: new scala/collection/parallel/ParIterableLike$Product
/*      */     //   9: dup
/*      */     //   10: aload_0
/*      */     //   11: aload_1
/*      */     //   12: aload_0
/*      */     //   13: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   18: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/math/Numeric;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   21: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   26: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #474	-> 0
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	27	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	27	1	num	Lscala/math/Numeric;
/*      */   }
/*      */   
/*      */   public static Object min(ParIterableLike $this, Ordering ord) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: aload_0
/*      */     //   7: new scala/collection/parallel/ParIterableLike$Min
/*      */     //   10: dup
/*      */     //   11: aload_0
/*      */     //   12: aload_1
/*      */     //   13: aload_0
/*      */     //   14: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   19: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/math/Ordering;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   22: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   27: new scala/collection/parallel/ParIterableLike$$anonfun$min$1
/*      */     //   30: dup
/*      */     //   31: aload_0
/*      */     //   32: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   35: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   40: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   45: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #478	-> 0
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	46	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	46	1	ord	Lscala/math/Ordering;
/*      */   }
/*      */   
/*      */   public static Object max(ParIterableLike $this, Ordering ord) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: aload_0
/*      */     //   7: new scala/collection/parallel/ParIterableLike$Max
/*      */     //   10: dup
/*      */     //   11: aload_0
/*      */     //   12: aload_1
/*      */     //   13: aload_0
/*      */     //   14: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   19: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/math/Ordering;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   22: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   27: new scala/collection/parallel/ParIterableLike$$anonfun$max$1
/*      */     //   30: dup
/*      */     //   31: aload_0
/*      */     //   32: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   35: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   40: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   45: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #482	-> 0
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	46	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	46	1	ord	Lscala/math/Ordering;
/*      */   }
/*      */   
/*      */   public static Object maxBy(ParIterableLike $this, Function1 f, Ordering cmp) {
/*  486 */     if ($this.isEmpty())
/*  486 */       throw new UnsupportedOperationException("empty.maxBy"); 
/*  488 */     return $this.reduce((Function2)new ParIterableLike$$anonfun$maxBy$1($this, f, (ParIterableLike<T, Repr, Sequential>)cmp));
/*      */   }
/*      */   
/*      */   public static Object minBy(ParIterableLike $this, Function1 f, Ordering cmp) {
/*  492 */     if ($this.isEmpty())
/*  492 */       throw new UnsupportedOperationException("empty.minBy"); 
/*  494 */     return $this.reduce((Function2)new ParIterableLike$$anonfun$minBy$1($this, f, (ParIterableLike<T, Repr, Sequential>)cmp));
/*      */   }
/*      */   
/*      */   public static Object map(ParIterableLike $this, Function1 f, CanBuildFrom bf) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: aload_2
/*      */     //   2: aload_0
/*      */     //   3: invokeinterface repr : ()Lscala/collection/parallel/ParIterable;
/*      */     //   8: invokeinterface apply : (Ljava/lang/Object;)Lscala/collection/mutable/Builder;
/*      */     //   13: invokeinterface builder2ops : (Lscala/collection/mutable/Builder;)Lscala/collection/parallel/ParIterableLike$BuilderOps;
/*      */     //   18: invokeinterface isCombiner : ()Z
/*      */     //   23: ifeq -> 89
/*      */     //   26: aload_0
/*      */     //   27: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   32: aload_0
/*      */     //   33: new scala/collection/parallel/ParIterableLike$Map
/*      */     //   36: dup
/*      */     //   37: aload_0
/*      */     //   38: aload_1
/*      */     //   39: aload_0
/*      */     //   40: new scala/collection/parallel/ParIterableLike$$anonfun$map$1
/*      */     //   43: dup
/*      */     //   44: aload_0
/*      */     //   45: aload_2
/*      */     //   46: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/generic/CanBuildFrom;)V
/*      */     //   49: invokeinterface combinerFactory : (Lscala/Function0;)Lscala/collection/parallel/CombinerFactory;
/*      */     //   54: aload_0
/*      */     //   55: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   60: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/Function1;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   63: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   68: new scala/collection/parallel/ParIterableLike$$anonfun$map$2
/*      */     //   71: dup
/*      */     //   72: aload_0
/*      */     //   73: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   76: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   81: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   86: goto -> 120
/*      */     //   89: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   92: aload_0
/*      */     //   93: invokeinterface seq : ()Lscala/collection/Iterable;
/*      */     //   98: aload_1
/*      */     //   99: aload_0
/*      */     //   100: aload_2
/*      */     //   101: invokeinterface bf2seq : (Lscala/collection/generic/CanBuildFrom;)Lscala/collection/generic/CanBuildFrom;
/*      */     //   106: invokeinterface map : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*      */     //   111: aload_0
/*      */     //   112: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   117: invokevirtual setTaskSupport : (Ljava/lang/Object;Lscala/collection/parallel/TaskSupport;)Ljava/lang/Object;
/*      */     //   120: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #497	-> 0
/*      */     //   #498	-> 26
/*      */     //   #499	-> 89
/*      */     //   #497	-> 120
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	121	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	121	1	f	Lscala/Function1;
/*      */     //   0	121	2	bf	Lscala/collection/generic/CanBuildFrom;
/*      */   }
/*      */   
/*      */   public static Object collect(ParIterableLike $this, PartialFunction pf, CanBuildFrom bf) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: aload_2
/*      */     //   2: aload_0
/*      */     //   3: invokeinterface repr : ()Lscala/collection/parallel/ParIterable;
/*      */     //   8: invokeinterface apply : (Ljava/lang/Object;)Lscala/collection/mutable/Builder;
/*      */     //   13: invokeinterface builder2ops : (Lscala/collection/mutable/Builder;)Lscala/collection/parallel/ParIterableLike$BuilderOps;
/*      */     //   18: invokeinterface isCombiner : ()Z
/*      */     //   23: ifeq -> 89
/*      */     //   26: aload_0
/*      */     //   27: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   32: aload_0
/*      */     //   33: new scala/collection/parallel/ParIterableLike$Collect
/*      */     //   36: dup
/*      */     //   37: aload_0
/*      */     //   38: aload_1
/*      */     //   39: aload_0
/*      */     //   40: new scala/collection/parallel/ParIterableLike$$anonfun$collect$1
/*      */     //   43: dup
/*      */     //   44: aload_0
/*      */     //   45: aload_2
/*      */     //   46: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/generic/CanBuildFrom;)V
/*      */     //   49: invokeinterface combinerFactory : (Lscala/Function0;)Lscala/collection/parallel/CombinerFactory;
/*      */     //   54: aload_0
/*      */     //   55: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   60: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/PartialFunction;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   63: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   68: new scala/collection/parallel/ParIterableLike$$anonfun$collect$2
/*      */     //   71: dup
/*      */     //   72: aload_0
/*      */     //   73: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   76: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   81: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   86: goto -> 120
/*      */     //   89: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   92: aload_0
/*      */     //   93: invokeinterface seq : ()Lscala/collection/Iterable;
/*      */     //   98: aload_1
/*      */     //   99: aload_0
/*      */     //   100: aload_2
/*      */     //   101: invokeinterface bf2seq : (Lscala/collection/generic/CanBuildFrom;)Lscala/collection/generic/CanBuildFrom;
/*      */     //   106: invokeinterface collect : (Lscala/PartialFunction;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*      */     //   111: aload_0
/*      */     //   112: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   117: invokevirtual setTaskSupport : (Ljava/lang/Object;Lscala/collection/parallel/TaskSupport;)Ljava/lang/Object;
/*      */     //   120: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #504	-> 0
/*      */     //   #505	-> 26
/*      */     //   #506	-> 89
/*      */     //   #504	-> 120
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	121	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	121	1	pf	Lscala/PartialFunction;
/*      */     //   0	121	2	bf	Lscala/collection/generic/CanBuildFrom;
/*      */   }
/*      */   
/*      */   public static Object flatMap(ParIterableLike $this, Function1 f, CanBuildFrom bf) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: aload_2
/*      */     //   2: aload_0
/*      */     //   3: invokeinterface repr : ()Lscala/collection/parallel/ParIterable;
/*      */     //   8: invokeinterface apply : (Ljava/lang/Object;)Lscala/collection/mutable/Builder;
/*      */     //   13: invokeinterface builder2ops : (Lscala/collection/mutable/Builder;)Lscala/collection/parallel/ParIterableLike$BuilderOps;
/*      */     //   18: invokeinterface isCombiner : ()Z
/*      */     //   23: ifeq -> 89
/*      */     //   26: aload_0
/*      */     //   27: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   32: aload_0
/*      */     //   33: new scala/collection/parallel/ParIterableLike$FlatMap
/*      */     //   36: dup
/*      */     //   37: aload_0
/*      */     //   38: aload_1
/*      */     //   39: aload_0
/*      */     //   40: new scala/collection/parallel/ParIterableLike$$anonfun$flatMap$1
/*      */     //   43: dup
/*      */     //   44: aload_0
/*      */     //   45: aload_2
/*      */     //   46: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/generic/CanBuildFrom;)V
/*      */     //   49: invokeinterface combinerFactory : (Lscala/Function0;)Lscala/collection/parallel/CombinerFactory;
/*      */     //   54: aload_0
/*      */     //   55: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   60: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/Function1;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   63: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   68: new scala/collection/parallel/ParIterableLike$$anonfun$flatMap$2
/*      */     //   71: dup
/*      */     //   72: aload_0
/*      */     //   73: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   76: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   81: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   86: goto -> 120
/*      */     //   89: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   92: aload_0
/*      */     //   93: invokeinterface seq : ()Lscala/collection/Iterable;
/*      */     //   98: aload_1
/*      */     //   99: aload_0
/*      */     //   100: aload_2
/*      */     //   101: invokeinterface bf2seq : (Lscala/collection/generic/CanBuildFrom;)Lscala/collection/generic/CanBuildFrom;
/*      */     //   106: invokeinterface flatMap : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*      */     //   111: aload_0
/*      */     //   112: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   117: invokevirtual setTaskSupport : (Ljava/lang/Object;Lscala/collection/parallel/TaskSupport;)Ljava/lang/Object;
/*      */     //   120: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #511	-> 0
/*      */     //   #512	-> 26
/*      */     //   #513	-> 89
/*      */     //   #511	-> 120
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	121	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	121	1	f	Lscala/Function1;
/*      */     //   0	121	2	bf	Lscala/collection/generic/CanBuildFrom;
/*      */   }
/*      */   
/*      */   public static boolean forall(ParIterableLike<T, Repr, Sequential> $this, Function1<T, Object> pred) {
/*  526 */     return BoxesRunTime.unboxToBoolean($this.tasksupport().executeAndWaitResult(new ParIterableLike.Forall($this, pred, $this.<DelegatedSignalling>delegatedSignalling2ops($this.splitter()).assign((Signalling)new ParIterableLike$$anon$9($this)))));
/*      */   }
/*      */   
/*      */   public static boolean exists(ParIterableLike<T, Repr, Sequential> $this, Function1<T, Object> pred) {
/*  537 */     return BoxesRunTime.unboxToBoolean($this.tasksupport().executeAndWaitResult(new ParIterableLike.Exists($this, pred, $this.<DelegatedSignalling>delegatedSignalling2ops($this.splitter()).assign((Signalling)new ParIterableLike$$anon$10($this)))));
/*      */   }
/*      */   
/*      */   public static Option find(ParIterableLike $this, Function1 pred) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: new scala/collection/parallel/ParIterableLike$Find
/*      */     //   9: dup
/*      */     //   10: aload_0
/*      */     //   11: aload_1
/*      */     //   12: aload_0
/*      */     //   13: aload_0
/*      */     //   14: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   19: invokeinterface delegatedSignalling2ops : (Lscala/collection/generic/DelegatedSignalling;)Lscala/collection/parallel/ParIterableLike$SignallingOps;
/*      */     //   24: new scala/collection/parallel/ParIterableLike$$anon$11
/*      */     //   27: dup
/*      */     //   28: aload_0
/*      */     //   29: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   32: invokeinterface assign : (Lscala/collection/generic/Signalling;)Lscala/collection/generic/DelegatedSignalling;
/*      */     //   37: checkcast scala/collection/parallel/IterableSplitter
/*      */     //   40: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/Function1;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   43: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   48: checkcast scala/Option
/*      */     //   51: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #552	-> 0
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	52	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	52	1	pred	Lscala/Function1;
/*      */   }
/*      */   
/*      */   public static CombinerFactory combinerFactory(ParIterableLike<T, Repr, Sequential> $this) {
/*  567 */     Combiner combiner = $this.newCombiner();
/*  568 */     combiner.combinerTaskSupport_$eq($this.tasksupport());
/*  569 */     return combiner.canBeShared() ? new ParIterableLike$$anon$19($this, (ParIterableLike<T, Repr, Sequential>)combiner) : 
/*      */       
/*  573 */       new ParIterableLike$$anon$20($this);
/*      */   }
/*      */   
/*      */   public static CombinerFactory combinerFactory(ParIterableLike $this, Function0 cbf) {
/*  580 */     Combiner combiner = (Combiner)cbf.apply();
/*  581 */     combiner.combinerTaskSupport_$eq($this.tasksupport());
/*  582 */     return combiner.canBeShared() ? new ParIterableLike$$anon$17($this, (ParIterableLike<T, Repr, Sequential>)combiner) : 
/*      */       
/*  586 */       new ParIterableLike$$anon$18($this, (ParIterableLike<T, Repr, Sequential>)cbf);
/*      */   }
/*      */   
/*      */   public static ParIterable filter(ParIterableLike $this, Function1 pred) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: aload_0
/*      */     //   7: new scala/collection/parallel/ParIterableLike$Filter
/*      */     //   10: dup
/*      */     //   11: aload_0
/*      */     //   12: aload_1
/*      */     //   13: aload_0
/*      */     //   14: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   19: aload_0
/*      */     //   20: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   25: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/Function1;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   28: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   33: new scala/collection/parallel/ParIterableLike$$anonfun$filter$1
/*      */     //   36: dup
/*      */     //   37: aload_0
/*      */     //   38: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   41: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   46: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   51: checkcast scala/collection/parallel/ParIterable
/*      */     //   54: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #593	-> 0
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	55	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	55	1	pred	Lscala/Function1;
/*      */   }
/*      */   
/*      */   public static ParIterable filterNot(ParIterableLike $this, Function1 pred) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: aload_0
/*      */     //   7: new scala/collection/parallel/ParIterableLike$FilterNot
/*      */     //   10: dup
/*      */     //   11: aload_0
/*      */     //   12: aload_1
/*      */     //   13: aload_0
/*      */     //   14: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   19: aload_0
/*      */     //   20: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   25: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/Function1;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   28: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   33: new scala/collection/parallel/ParIterableLike$$anonfun$filterNot$1
/*      */     //   36: dup
/*      */     //   37: aload_0
/*      */     //   38: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   41: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   46: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   51: checkcast scala/collection/parallel/ParIterable
/*      */     //   54: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #597	-> 0
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	55	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	55	1	pred	Lscala/Function1;
/*      */   }
/*      */   
/*      */   public static Object $plus$plus(ParIterableLike $this, GenTraversableOnce<?> that, CanBuildFrom<?, ?, ?> bf) {
/*      */     // Byte code:
/*      */     //   0: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   3: aload_1
/*      */     //   4: invokevirtual traversable2ops : (Lscala/collection/GenTraversableOnce;)Lscala/collection/parallel/TraversableOps;
/*      */     //   7: invokeinterface isParallel : ()Z
/*      */     //   12: ifeq -> 172
/*      */     //   15: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   18: aload_2
/*      */     //   19: invokevirtual factory2ops : (Lscala/collection/generic/CanBuildFrom;)Lscala/collection/parallel/FactoryOps;
/*      */     //   22: invokeinterface isParallel : ()Z
/*      */     //   27: ifeq -> 172
/*      */     //   30: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   33: aload_1
/*      */     //   34: invokevirtual traversable2ops : (Lscala/collection/GenTraversableOnce;)Lscala/collection/parallel/TraversableOps;
/*      */     //   37: invokeinterface asParIterable : ()Lscala/collection/parallel/ParIterable;
/*      */     //   42: astore_3
/*      */     //   43: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   46: aload_2
/*      */     //   47: invokevirtual factory2ops : (Lscala/collection/generic/CanBuildFrom;)Lscala/collection/parallel/FactoryOps;
/*      */     //   50: invokeinterface asParallel : ()Lscala/collection/generic/CanCombineFrom;
/*      */     //   55: astore #4
/*      */     //   57: aload_0
/*      */     //   58: new scala/collection/parallel/ParIterableLike$$anonfun$1
/*      */     //   61: dup
/*      */     //   62: aload_0
/*      */     //   63: aload #4
/*      */     //   65: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/generic/CanCombineFrom;)V
/*      */     //   68: invokeinterface combinerFactory : (Lscala/Function0;)Lscala/collection/parallel/CombinerFactory;
/*      */     //   73: astore #5
/*      */     //   75: new scala/collection/parallel/ParIterableLike$Copy
/*      */     //   78: dup
/*      */     //   79: aload_0
/*      */     //   80: aload #5
/*      */     //   82: aload_0
/*      */     //   83: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   88: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   91: astore #6
/*      */     //   93: aload_0
/*      */     //   94: new scala/collection/parallel/ParIterableLike$$anonfun$2
/*      */     //   97: dup
/*      */     //   98: aload_0
/*      */     //   99: aload_3
/*      */     //   100: aload #5
/*      */     //   102: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/parallel/ParIterable;Lscala/collection/parallel/CombinerFactory;)V
/*      */     //   105: invokeinterface wrap : (Lscala/Function0;)Lscala/collection/parallel/ParIterableLike$NonDivisible;
/*      */     //   110: astore #7
/*      */     //   112: aload_0
/*      */     //   113: aload_0
/*      */     //   114: aload #6
/*      */     //   116: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   121: aload #7
/*      */     //   123: new scala/collection/parallel/ParIterableLike$$anonfun$3
/*      */     //   126: dup
/*      */     //   127: aload_0
/*      */     //   128: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   131: invokeinterface parallel : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;Lscala/Function2;)Lscala/collection/parallel/ParIterableLike$ParComposite;
/*      */     //   136: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   141: new scala/collection/parallel/ParIterableLike$$anonfun$4
/*      */     //   144: dup
/*      */     //   145: aload_0
/*      */     //   146: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   149: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   154: astore #8
/*      */     //   156: aload_0
/*      */     //   157: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   162: aload #8
/*      */     //   164: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   169: goto -> 371
/*      */     //   172: aload_0
/*      */     //   173: aload_2
/*      */     //   174: aload_0
/*      */     //   175: invokeinterface repr : ()Lscala/collection/parallel/ParIterable;
/*      */     //   180: invokeinterface apply : (Ljava/lang/Object;)Lscala/collection/mutable/Builder;
/*      */     //   185: invokeinterface builder2ops : (Lscala/collection/mutable/Builder;)Lscala/collection/parallel/ParIterableLike$BuilderOps;
/*      */     //   190: invokeinterface isCombiner : ()Z
/*      */     //   195: ifeq -> 303
/*      */     //   198: new scala/collection/parallel/ParIterableLike$Copy
/*      */     //   201: dup
/*      */     //   202: aload_0
/*      */     //   203: aload_0
/*      */     //   204: new scala/collection/parallel/ParIterableLike$$anonfun$5
/*      */     //   207: dup
/*      */     //   208: aload_0
/*      */     //   209: aload_2
/*      */     //   210: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/generic/CanBuildFrom;)V
/*      */     //   213: invokeinterface combinerFactory : (Lscala/Function0;)Lscala/collection/parallel/CombinerFactory;
/*      */     //   218: aload_0
/*      */     //   219: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   224: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   227: astore #9
/*      */     //   229: aload_0
/*      */     //   230: new scala/collection/parallel/ParIterableLike$$anonfun$6
/*      */     //   233: dup
/*      */     //   234: aload_0
/*      */     //   235: aload_1
/*      */     //   236: aload_2
/*      */     //   237: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/GenTraversableOnce;Lscala/collection/generic/CanBuildFrom;)V
/*      */     //   240: invokeinterface wrap : (Lscala/Function0;)Lscala/collection/parallel/ParIterableLike$NonDivisible;
/*      */     //   245: astore #10
/*      */     //   247: aload_0
/*      */     //   248: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   253: aload_0
/*      */     //   254: aload_0
/*      */     //   255: aload #9
/*      */     //   257: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   262: aload #10
/*      */     //   264: new scala/collection/parallel/ParIterableLike$$anonfun$$plus$plus$1
/*      */     //   267: dup
/*      */     //   268: aload_0
/*      */     //   269: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   272: invokeinterface parallel : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;Lscala/Function2;)Lscala/collection/parallel/ParIterableLike$ParComposite;
/*      */     //   277: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   282: new scala/collection/parallel/ParIterableLike$$anonfun$$plus$plus$2
/*      */     //   285: dup
/*      */     //   286: aload_0
/*      */     //   287: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   290: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   295: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   300: goto -> 371
/*      */     //   303: aload_2
/*      */     //   304: aload_0
/*      */     //   305: invokeinterface repr : ()Lscala/collection/parallel/ParIterable;
/*      */     //   310: invokeinterface apply : (Ljava/lang/Object;)Lscala/collection/mutable/Builder;
/*      */     //   315: astore #11
/*      */     //   317: aload_0
/*      */     //   318: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   323: aload #11
/*      */     //   325: invokeinterface copy2builder : (Lscala/collection/mutable/Builder;)Lscala/collection/mutable/Builder;
/*      */     //   330: pop
/*      */     //   331: aload_1
/*      */     //   332: invokeinterface seq : ()Lscala/collection/TraversableOnce;
/*      */     //   337: new scala/collection/parallel/ParIterableLike$$anonfun$$plus$plus$3
/*      */     //   340: dup
/*      */     //   341: aload_0
/*      */     //   342: aload #11
/*      */     //   344: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/mutable/Builder;)V
/*      */     //   347: invokeinterface foreach : (Lscala/Function1;)V
/*      */     //   352: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   355: aload #11
/*      */     //   357: invokeinterface result : ()Ljava/lang/Object;
/*      */     //   362: aload_0
/*      */     //   363: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   368: invokevirtual setTaskSupport : (Ljava/lang/Object;Lscala/collection/parallel/TaskSupport;)Ljava/lang/Object;
/*      */     //   371: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #601	-> 0
/*      */     //   #603	-> 30
/*      */     //   #604	-> 43
/*      */     //   #605	-> 57
/*      */     //   #606	-> 75
/*      */     //   #607	-> 93
/*      */     //   #611	-> 112
/*      */     //   #612	-> 141
/*      */     //   #611	-> 149
/*      */     //   #614	-> 156
/*      */     //   #615	-> 172
/*      */     //   #617	-> 198
/*      */     //   #618	-> 229
/*      */     //   #623	-> 247
/*      */     //   #626	-> 303
/*      */     //   #627	-> 317
/*      */     //   #628	-> 331
/*      */     //   #629	-> 352
/*      */     //   #601	-> 371
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	372	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	372	1	that	Lscala/collection/GenTraversableOnce;
/*      */     //   0	372	2	bf	Lscala/collection/generic/CanBuildFrom;
/*      */     //   43	126	3	other	Lscala/collection/parallel/ParIterable;
/*      */     //   57	112	4	pbf	Lscala/collection/generic/CanCombineFrom;
/*      */     //   75	94	5	cfactory	Lscala/collection/parallel/CombinerFactory;
/*      */     //   93	76	6	copythis	Lscala/collection/parallel/ParIterableLike$Copy;
/*      */     //   112	57	7	copythat	Lscala/collection/parallel/ParIterableLike$NonDivisible;
/*      */     //   156	13	8	task	Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   229	71	9	copythis	Lscala/collection/parallel/ParIterableLike$Copy;
/*      */     //   247	53	10	copythat	Lscala/collection/parallel/ParIterableLike$NonDivisible;
/*      */     //   317	54	11	b	Lscala/collection/mutable/Builder;
/*      */   }
/*      */   
/*      */   public static Tuple2 partition(ParIterableLike $this, Function1 pred) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: aload_0
/*      */     //   7: new scala/collection/parallel/ParIterableLike$Partition
/*      */     //   10: dup
/*      */     //   11: aload_0
/*      */     //   12: aload_1
/*      */     //   13: aload_0
/*      */     //   14: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   19: aload_0
/*      */     //   20: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   25: aload_0
/*      */     //   26: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   31: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/Function1;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   34: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   39: new scala/collection/parallel/ParIterableLike$$anonfun$partition$1
/*      */     //   42: dup
/*      */     //   43: aload_0
/*      */     //   44: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   47: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   52: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   57: checkcast scala/Tuple2
/*      */     //   60: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #634	-> 0
/*      */     //   #635	-> 6
/*      */     //   #636	-> 39
/*      */     //   #635	-> 47
/*      */     //   #634	-> 52
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	61	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	61	1	pred	Lscala/Function1;
/*      */   }
/*      */   
/*      */   public static ParMap groupBy(ParIterableLike $this, Function1 f) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: aload_0
/*      */     //   7: new scala/collection/parallel/ParIterableLike$GroupBy
/*      */     //   10: dup
/*      */     //   11: aload_0
/*      */     //   12: aload_1
/*      */     //   13: new scala/collection/parallel/ParIterableLike$$anonfun$7
/*      */     //   16: dup
/*      */     //   17: aload_0
/*      */     //   18: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   21: aload_0
/*      */     //   22: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   27: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/Function1;Lscala/Function0;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   30: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   35: new scala/collection/parallel/ParIterableLike$$anonfun$8
/*      */     //   38: dup
/*      */     //   39: aload_0
/*      */     //   40: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   43: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   48: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   53: checkcast scala/collection/parallel/immutable/ParHashMap
/*      */     //   56: astore_2
/*      */     //   57: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   60: aload_2
/*      */     //   61: aload_0
/*      */     //   62: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   67: invokevirtual setTaskSupport : (Ljava/lang/Object;Lscala/collection/parallel/TaskSupport;)Ljava/lang/Object;
/*      */     //   70: checkcast scala/collection/parallel/immutable/ParMap
/*      */     //   73: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #642	-> 0
/*      */     //   #643	-> 35
/*      */     //   #642	-> 43
/*      */     //   #645	-> 57
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	74	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	74	1	f	Lscala/Function1;
/*      */     //   57	16	2	r	Lscala/collection/parallel/immutable/ParHashMap;
/*      */   }
/*      */   
/*      */   public static ParIterable take(ParIterableLike $this, int n) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface size : ()I
/*      */     //   6: iload_1
/*      */     //   7: if_icmple -> 14
/*      */     //   10: iload_1
/*      */     //   11: goto -> 20
/*      */     //   14: aload_0
/*      */     //   15: invokeinterface size : ()I
/*      */     //   20: istore_2
/*      */     //   21: iload_2
/*      */     //   22: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   25: invokevirtual MIN_FOR_COPY : ()I
/*      */     //   28: if_icmpge -> 39
/*      */     //   31: aload_0
/*      */     //   32: iload_2
/*      */     //   33: invokestatic take_sequential : (Lscala/collection/parallel/ParIterableLike;I)Lscala/collection/parallel/ParIterable;
/*      */     //   36: goto -> 93
/*      */     //   39: aload_0
/*      */     //   40: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   45: aload_0
/*      */     //   46: new scala/collection/parallel/ParIterableLike$Take
/*      */     //   49: dup
/*      */     //   50: aload_0
/*      */     //   51: iload_2
/*      */     //   52: aload_0
/*      */     //   53: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   58: aload_0
/*      */     //   59: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   64: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;ILscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   67: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   72: new scala/collection/parallel/ParIterableLike$$anonfun$take$1
/*      */     //   75: dup
/*      */     //   76: aload_0
/*      */     //   77: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   80: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   85: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   90: checkcast scala/collection/parallel/ParIterable
/*      */     //   93: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #649	-> 0
/*      */     //   #650	-> 21
/*      */     //   #651	-> 39
/*      */     //   #652	-> 72
/*      */     //   #651	-> 80
/*      */     //   #648	-> 93
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	94	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	94	1	n	I
/*      */     //   21	73	2	actualn	I
/*      */   }
/*      */   
/*      */   private static ParIterable take_sequential(ParIterableLike $this, int n) {
/*  657 */     Combiner cb = $this.newCombiner();
/*  658 */     cb.sizeHint(n);
/*  659 */     IterableSplitter it = $this.splitter();
/*  660 */     int left = n;
/*  661 */     while (left > 0) {
/*  662 */       cb.$plus$eq(it.next());
/*  663 */       left--;
/*      */     } 
/*  665 */     return (ParIterable)cb.resultWithTaskSupport();
/*      */   }
/*      */   
/*      */   public static ParIterable drop(ParIterableLike $this, int n) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface size : ()I
/*      */     //   6: iload_1
/*      */     //   7: if_icmple -> 14
/*      */     //   10: iload_1
/*      */     //   11: goto -> 20
/*      */     //   14: aload_0
/*      */     //   15: invokeinterface size : ()I
/*      */     //   20: istore_2
/*      */     //   21: aload_0
/*      */     //   22: invokeinterface size : ()I
/*      */     //   27: iload_2
/*      */     //   28: isub
/*      */     //   29: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   32: invokevirtual MIN_FOR_COPY : ()I
/*      */     //   35: if_icmpge -> 46
/*      */     //   38: aload_0
/*      */     //   39: iload_2
/*      */     //   40: invokestatic drop_sequential : (Lscala/collection/parallel/ParIterableLike;I)Lscala/collection/parallel/ParIterable;
/*      */     //   43: goto -> 100
/*      */     //   46: aload_0
/*      */     //   47: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   52: aload_0
/*      */     //   53: new scala/collection/parallel/ParIterableLike$Drop
/*      */     //   56: dup
/*      */     //   57: aload_0
/*      */     //   58: iload_2
/*      */     //   59: aload_0
/*      */     //   60: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   65: aload_0
/*      */     //   66: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   71: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;ILscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   74: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   79: new scala/collection/parallel/ParIterableLike$$anonfun$drop$1
/*      */     //   82: dup
/*      */     //   83: aload_0
/*      */     //   84: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   87: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   92: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   97: checkcast scala/collection/parallel/ParIterable
/*      */     //   100: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #669	-> 0
/*      */     //   #670	-> 21
/*      */     //   #671	-> 46
/*      */     //   #668	-> 100
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	101	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	101	1	n	I
/*      */     //   21	80	2	actualn	I
/*      */   }
/*      */   
/*      */   private static ParIterable drop_sequential(ParIterableLike $this, int n) {
/*  675 */     Iterator it = $this.splitter().drop(n);
/*  676 */     Combiner cb = $this.newCombiner();
/*  677 */     cb.sizeHint($this.size() - n);
/*  678 */     for (; it.hasNext(); cb.$plus$eq(it.next()));
/*  679 */     return (ParIterable)cb.resultWithTaskSupport();
/*      */   }
/*      */   
/*      */   public static ParIterable slice(ParIterableLike $this, int unc_from, int unc_until) {
/*      */     // Byte code:
/*      */     //   0: getstatic scala/runtime/RichInt$.MODULE$ : Lscala/runtime/RichInt$;
/*      */     //   3: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*      */     //   6: getstatic scala/runtime/RichInt$.MODULE$ : Lscala/runtime/RichInt$;
/*      */     //   9: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*      */     //   12: astore_3
/*      */     //   13: iload_1
/*      */     //   14: aload_0
/*      */     //   15: invokeinterface size : ()I
/*      */     //   20: invokevirtual min$extension : (II)I
/*      */     //   23: istore #5
/*      */     //   25: astore #4
/*      */     //   27: iload #5
/*      */     //   29: iconst_0
/*      */     //   30: invokevirtual max$extension : (II)I
/*      */     //   33: istore #9
/*      */     //   35: getstatic scala/runtime/RichInt$.MODULE$ : Lscala/runtime/RichInt$;
/*      */     //   38: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*      */     //   41: getstatic scala/runtime/RichInt$.MODULE$ : Lscala/runtime/RichInt$;
/*      */     //   44: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*      */     //   47: astore #6
/*      */     //   49: iload_2
/*      */     //   50: aload_0
/*      */     //   51: invokeinterface size : ()I
/*      */     //   56: invokevirtual min$extension : (II)I
/*      */     //   59: istore #8
/*      */     //   61: astore #7
/*      */     //   63: iload #8
/*      */     //   65: iload #9
/*      */     //   67: invokevirtual max$extension : (II)I
/*      */     //   70: istore #10
/*      */     //   72: iload #10
/*      */     //   74: iload #9
/*      */     //   76: isub
/*      */     //   77: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   80: invokevirtual MIN_FOR_COPY : ()I
/*      */     //   83: if_icmpgt -> 97
/*      */     //   86: aload_0
/*      */     //   87: iload #9
/*      */     //   89: iload #10
/*      */     //   91: invokestatic slice_sequential : (Lscala/collection/parallel/ParIterableLike;II)Lscala/collection/parallel/ParIterable;
/*      */     //   94: goto -> 154
/*      */     //   97: aload_0
/*      */     //   98: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   103: aload_0
/*      */     //   104: new scala/collection/parallel/ParIterableLike$Slice
/*      */     //   107: dup
/*      */     //   108: aload_0
/*      */     //   109: iload #9
/*      */     //   111: iload #10
/*      */     //   113: aload_0
/*      */     //   114: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   119: aload_0
/*      */     //   120: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   125: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;IILscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   128: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   133: new scala/collection/parallel/ParIterableLike$$anonfun$slice$1
/*      */     //   136: dup
/*      */     //   137: aload_0
/*      */     //   138: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   141: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   146: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   151: checkcast scala/collection/parallel/ParIterable
/*      */     //   154: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #683	-> 3
/*      */     //   #684	-> 38
/*      */     //   #685	-> 72
/*      */     //   #686	-> 97
/*      */     //   #682	-> 154
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	155	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	155	1	unc_from	I
/*      */     //   0	155	2	unc_until	I
/*      */     //   35	120	9	from	I
/*      */     //   72	83	10	until	I
/*      */   }
/*      */   
/*      */   private static ParIterable slice_sequential(ParIterableLike $this, int from, int until) {
/*  690 */     Combiner cb = $this.newCombiner();
/*  691 */     int left = until - from;
/*  692 */     Iterator it = $this.splitter().drop(from);
/*  693 */     while (left > 0) {
/*  694 */       cb.$plus$eq(it.next());
/*  695 */       left--;
/*      */     } 
/*  697 */     return (ParIterable)cb.resultWithTaskSupport();
/*      */   }
/*      */   
/*      */   public static Tuple2 splitAt(ParIterableLike $this, int n) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: aload_0
/*      */     //   7: new scala/collection/parallel/ParIterableLike$SplitAt
/*      */     //   10: dup
/*      */     //   11: aload_0
/*      */     //   12: iload_1
/*      */     //   13: aload_0
/*      */     //   14: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   19: aload_0
/*      */     //   20: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   25: aload_0
/*      */     //   26: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   31: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;ILscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   34: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   39: new scala/collection/parallel/ParIterableLike$$anonfun$splitAt$1
/*      */     //   42: dup
/*      */     //   43: aload_0
/*      */     //   44: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   47: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   52: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   57: checkcast scala/Tuple2
/*      */     //   60: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #701	-> 0
/*      */     //   #702	-> 6
/*      */     //   #703	-> 39
/*      */     //   #702	-> 47
/*      */     //   #701	-> 52
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	61	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	61	1	n	I
/*      */   }
/*      */   
/*      */   public static Object scan(ParIterableLike $this, Object z, Function2 op, CanBuildFrom bf) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: aload_3
/*      */     //   2: aload_0
/*      */     //   3: invokeinterface repr : ()Lscala/collection/parallel/ParIterable;
/*      */     //   8: invokeinterface apply : (Ljava/lang/Object;)Lscala/collection/mutable/Builder;
/*      */     //   13: invokeinterface builder2ops : (Lscala/collection/mutable/Builder;)Lscala/collection/parallel/ParIterableLike$BuilderOps;
/*      */     //   18: invokeinterface isCombiner : ()Z
/*      */     //   23: ifeq -> 183
/*      */     //   26: aload_0
/*      */     //   27: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   32: invokeinterface parallelismLevel : ()I
/*      */     //   37: iconst_1
/*      */     //   38: if_icmple -> 148
/*      */     //   41: aload_0
/*      */     //   42: invokeinterface size : ()I
/*      */     //   47: iconst_0
/*      */     //   48: if_icmple -> 110
/*      */     //   51: aload_0
/*      */     //   52: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   57: aload_0
/*      */     //   58: new scala/collection/parallel/ParIterableLike$CreateScanTree
/*      */     //   61: dup
/*      */     //   62: aload_0
/*      */     //   63: iconst_0
/*      */     //   64: aload_0
/*      */     //   65: invokeinterface size : ()I
/*      */     //   70: aload_1
/*      */     //   71: aload_2
/*      */     //   72: aload_0
/*      */     //   73: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   78: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;IILjava/lang/Object;Lscala/Function2;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   81: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   86: new scala/collection/parallel/ParIterableLike$$anonfun$scan$1
/*      */     //   89: dup
/*      */     //   90: aload_0
/*      */     //   91: aload_1
/*      */     //   92: aload_2
/*      */     //   93: aload_3
/*      */     //   94: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Ljava/lang/Object;Lscala/Function2;Lscala/collection/generic/CanBuildFrom;)V
/*      */     //   97: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   102: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   107: goto -> 215
/*      */     //   110: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   113: aload_3
/*      */     //   114: aload_0
/*      */     //   115: invokeinterface repr : ()Lscala/collection/parallel/ParIterable;
/*      */     //   120: invokeinterface apply : (Ljava/lang/Object;)Lscala/collection/mutable/Builder;
/*      */     //   125: aload_1
/*      */     //   126: invokeinterface $plus$eq : (Ljava/lang/Object;)Lscala/collection/mutable/Builder;
/*      */     //   131: invokeinterface result : ()Ljava/lang/Object;
/*      */     //   136: aload_0
/*      */     //   137: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   142: invokevirtual setTaskSupport : (Ljava/lang/Object;Lscala/collection/parallel/TaskSupport;)Ljava/lang/Object;
/*      */     //   145: goto -> 215
/*      */     //   148: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   151: aload_0
/*      */     //   152: invokeinterface seq : ()Lscala/collection/Iterable;
/*      */     //   157: aload_1
/*      */     //   158: aload_2
/*      */     //   159: aload_0
/*      */     //   160: aload_3
/*      */     //   161: invokeinterface bf2seq : (Lscala/collection/generic/CanBuildFrom;)Lscala/collection/generic/CanBuildFrom;
/*      */     //   166: invokeinterface scan : (Ljava/lang/Object;Lscala/Function2;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*      */     //   171: aload_0
/*      */     //   172: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   177: invokevirtual setTaskSupport : (Ljava/lang/Object;Lscala/collection/parallel/TaskSupport;)Ljava/lang/Object;
/*      */     //   180: goto -> 215
/*      */     //   183: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   186: aload_0
/*      */     //   187: invokeinterface seq : ()Lscala/collection/Iterable;
/*      */     //   192: aload_1
/*      */     //   193: aload_2
/*      */     //   194: aload_0
/*      */     //   195: aload_3
/*      */     //   196: invokeinterface bf2seq : (Lscala/collection/generic/CanBuildFrom;)Lscala/collection/generic/CanBuildFrom;
/*      */     //   201: invokeinterface scan : (Ljava/lang/Object;Lscala/Function2;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*      */     //   206: aload_0
/*      */     //   207: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   212: invokevirtual setTaskSupport : (Ljava/lang/Object;Lscala/collection/parallel/TaskSupport;)Ljava/lang/Object;
/*      */     //   215: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #724	-> 0
/*      */     //   #725	-> 26
/*      */     //   #726	-> 41
/*      */     //   #727	-> 86
/*      */     //   #726	-> 97
/*      */     //   #730	-> 110
/*      */     //   #731	-> 148
/*      */     //   #732	-> 183
/*      */     //   #724	-> 215
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	216	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	216	1	z	Ljava/lang/Object;
/*      */     //   0	216	2	op	Lscala/Function2;
/*      */     //   0	216	3	bf	Lscala/collection/generic/CanBuildFrom;
/*      */   }
/*      */   
/*      */   public static Object scanLeft(ParIterableLike $this, Object z, Function2 op, CanBuildFrom bf) {
/*  734 */     return package$.MODULE$.setTaskSupport($this.seq().scanLeft(z, op, (CanBuildFrom)$this.bf2seq(bf)), $this.tasksupport());
/*      */   }
/*      */   
/*      */   public static Object scanRight(ParIterableLike $this, Object z, Function2 op, CanBuildFrom bf) {
/*  736 */     return package$.MODULE$.setTaskSupport($this.seq().scanRight(z, op, (CanBuildFrom)$this.bf2seq(bf)), $this.tasksupport());
/*      */   }
/*      */   
/*      */   public static ParIterable takeWhile(ParIterableLike<T, Repr, Sequential> $this, Function1 pred) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   6: astore_2
/*      */     //   7: aload_2
/*      */     //   8: invokeinterface doesShareCombiners : ()Z
/*      */     //   13: ifeq -> 88
/*      */     //   16: aload_0
/*      */     //   17: invokeinterface toSeq : ()Lscala/collection/parallel/ParSeq;
/*      */     //   22: aload_1
/*      */     //   23: invokeinterface takeWhile : (Lscala/Function1;)Lscala/collection/parallel/ParIterable;
/*      */     //   28: checkcast scala/collection/parallel/ParSeq
/*      */     //   31: astore_3
/*      */     //   32: aload_0
/*      */     //   33: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   38: aload_0
/*      */     //   39: new scala/collection/parallel/ParIterableLike$Copy
/*      */     //   42: dup
/*      */     //   43: aload_0
/*      */     //   44: aload_0
/*      */     //   45: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   50: aload_3
/*      */     //   51: invokeinterface splitter : ()Lscala/collection/parallel/SeqSplitter;
/*      */     //   56: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   59: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   64: new scala/collection/parallel/ParIterableLike$$anonfun$takeWhile$1
/*      */     //   67: dup
/*      */     //   68: aload_0
/*      */     //   69: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   72: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   77: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   82: checkcast scala/collection/parallel/ParIterable
/*      */     //   85: goto -> 182
/*      */     //   88: new scala/collection/parallel/ParIterableLike$$anon$6
/*      */     //   91: dup
/*      */     //   92: aload_0
/*      */     //   93: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   96: astore #4
/*      */     //   98: aload #4
/*      */     //   100: checkcast scala/collection/generic/AtomicIndexFlag
/*      */     //   103: ldc_w 2147483647
/*      */     //   106: invokeinterface setIndexFlag : (I)V
/*      */     //   111: aload_0
/*      */     //   112: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   117: aload_0
/*      */     //   118: new scala/collection/parallel/ParIterableLike$TakeWhile
/*      */     //   121: dup
/*      */     //   122: aload_0
/*      */     //   123: iconst_0
/*      */     //   124: aload_1
/*      */     //   125: aload_0
/*      */     //   126: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   131: aload_0
/*      */     //   132: aload_0
/*      */     //   133: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   138: invokeinterface delegatedSignalling2ops : (Lscala/collection/generic/DelegatedSignalling;)Lscala/collection/parallel/ParIterableLike$SignallingOps;
/*      */     //   143: aload #4
/*      */     //   145: invokeinterface assign : (Lscala/collection/generic/Signalling;)Lscala/collection/generic/DelegatedSignalling;
/*      */     //   150: checkcast scala/collection/parallel/IterableSplitter
/*      */     //   153: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;ILscala/Function1;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   156: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   161: new scala/collection/parallel/ParIterableLike$$anonfun$takeWhile$2
/*      */     //   164: dup
/*      */     //   165: aload_0
/*      */     //   166: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   169: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   174: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   179: checkcast scala/collection/parallel/ParIterable
/*      */     //   182: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #747	-> 0
/*      */     //   #748	-> 7
/*      */     //   #749	-> 16
/*      */     //   #750	-> 32
/*      */     //   #751	-> 64
/*      */     //   #750	-> 72
/*      */     //   #754	-> 88
/*      */     //   #755	-> 98
/*      */     //   #756	-> 111
/*      */     //   #757	-> 161
/*      */     //   #756	-> 169
/*      */     //   #746	-> 182
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	183	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	183	1	pred	Lscala/Function1;
/*      */     //   7	176	2	cbf	Lscala/collection/parallel/CombinerFactory;
/*      */     //   32	53	3	parseqspan	Lscala/collection/parallel/ParSeq;
/*      */     //   98	84	4	cntx	Lscala/collection/generic/DefaultSignalling;
/*      */   }
/*      */   
/*      */   public static Tuple2 span(ParIterableLike $this, Function1 pred) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   6: astore_2
/*      */     //   7: aload_2
/*      */     //   8: invokeinterface doesShareCombiners : ()Z
/*      */     //   13: ifeq -> 209
/*      */     //   16: aload_0
/*      */     //   17: invokeinterface toSeq : ()Lscala/collection/parallel/ParSeq;
/*      */     //   22: aload_1
/*      */     //   23: invokeinterface span : (Lscala/Function1;)Lscala/Tuple2;
/*      */     //   28: astore #9
/*      */     //   30: aload #9
/*      */     //   32: ifnull -> 199
/*      */     //   35: new scala/Tuple2
/*      */     //   38: dup
/*      */     //   39: aload #9
/*      */     //   41: invokevirtual _1 : ()Ljava/lang/Object;
/*      */     //   44: aload #9
/*      */     //   46: invokevirtual _2 : ()Ljava/lang/Object;
/*      */     //   49: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*      */     //   52: astore_3
/*      */     //   53: aload_3
/*      */     //   54: invokevirtual _1 : ()Ljava/lang/Object;
/*      */     //   57: checkcast scala/collection/parallel/ParSeq
/*      */     //   60: astore #4
/*      */     //   62: aload_3
/*      */     //   63: invokevirtual _2 : ()Ljava/lang/Object;
/*      */     //   66: checkcast scala/collection/parallel/ParSeq
/*      */     //   69: astore #5
/*      */     //   71: aload_0
/*      */     //   72: new scala/collection/parallel/ParIterableLike$Copy
/*      */     //   75: dup
/*      */     //   76: aload_0
/*      */     //   77: aload_0
/*      */     //   78: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   83: aload #4
/*      */     //   85: invokeinterface splitter : ()Lscala/collection/parallel/SeqSplitter;
/*      */     //   90: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   93: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   98: new scala/collection/parallel/ParIterableLike$$anonfun$9
/*      */     //   101: dup
/*      */     //   102: aload_0
/*      */     //   103: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   106: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   111: astore #6
/*      */     //   113: aload_0
/*      */     //   114: new scala/collection/parallel/ParIterableLike$Copy
/*      */     //   117: dup
/*      */     //   118: aload_0
/*      */     //   119: aload_0
/*      */     //   120: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   125: aload #5
/*      */     //   127: invokeinterface splitter : ()Lscala/collection/parallel/SeqSplitter;
/*      */     //   132: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   135: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   140: new scala/collection/parallel/ParIterableLike$$anonfun$10
/*      */     //   143: dup
/*      */     //   144: aload_0
/*      */     //   145: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   148: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   153: astore #7
/*      */     //   155: aload_0
/*      */     //   156: aload #6
/*      */     //   158: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   163: aload #7
/*      */     //   165: new scala/collection/parallel/ParIterableLike$$anonfun$11
/*      */     //   168: dup
/*      */     //   169: aload_0
/*      */     //   170: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   173: invokeinterface parallel : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;Lscala/Function2;)Lscala/collection/parallel/ParIterableLike$ParComposite;
/*      */     //   178: astore #8
/*      */     //   180: aload_0
/*      */     //   181: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   186: aload #8
/*      */     //   188: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   193: checkcast scala/Tuple2
/*      */     //   196: goto -> 309
/*      */     //   199: new scala/MatchError
/*      */     //   202: dup
/*      */     //   203: aload #9
/*      */     //   205: invokespecial <init> : (Ljava/lang/Object;)V
/*      */     //   208: athrow
/*      */     //   209: new scala/collection/parallel/ParIterableLike$$anon$7
/*      */     //   212: dup
/*      */     //   213: aload_0
/*      */     //   214: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   217: astore #10
/*      */     //   219: aload #10
/*      */     //   221: checkcast scala/collection/generic/AtomicIndexFlag
/*      */     //   224: ldc_w 2147483647
/*      */     //   227: invokeinterface setIndexFlag : (I)V
/*      */     //   232: aload_0
/*      */     //   233: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   238: aload_0
/*      */     //   239: new scala/collection/parallel/ParIterableLike$Span
/*      */     //   242: dup
/*      */     //   243: aload_0
/*      */     //   244: iconst_0
/*      */     //   245: aload_1
/*      */     //   246: aload_0
/*      */     //   247: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   252: aload_0
/*      */     //   253: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   258: aload_0
/*      */     //   259: aload_0
/*      */     //   260: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   265: invokeinterface delegatedSignalling2ops : (Lscala/collection/generic/DelegatedSignalling;)Lscala/collection/parallel/ParIterableLike$SignallingOps;
/*      */     //   270: aload #10
/*      */     //   272: invokeinterface assign : (Lscala/collection/generic/Signalling;)Lscala/collection/generic/DelegatedSignalling;
/*      */     //   277: checkcast scala/collection/parallel/IterableSplitter
/*      */     //   280: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;ILscala/Function1;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   283: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   288: new scala/collection/parallel/ParIterableLike$$anonfun$span$1
/*      */     //   291: dup
/*      */     //   292: aload_0
/*      */     //   293: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   296: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   301: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   306: checkcast scala/Tuple2
/*      */     //   309: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #772	-> 0
/*      */     //   #773	-> 7
/*      */     //   #774	-> 16
/*      */     //   #775	-> 71
/*      */     //   #776	-> 113
/*      */     //   #777	-> 155
/*      */     //   #778	-> 165
/*      */     //   #777	-> 173
/*      */     //   #780	-> 180
/*      */     //   #774	-> 199
/*      */     //   #782	-> 209
/*      */     //   #783	-> 219
/*      */     //   #784	-> 232
/*      */     //   #785	-> 288
/*      */     //   #784	-> 296
/*      */     //   #771	-> 309
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	310	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	310	1	pred	Lscala/Function1;
/*      */     //   7	303	2	cbf	Lscala/collection/parallel/CombinerFactory;
/*      */     //   62	134	4	xs	Lscala/collection/parallel/ParSeq;
/*      */     //   71	125	5	ys	Lscala/collection/parallel/ParSeq;
/*      */     //   113	83	6	copyxs	Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   155	41	7	copyys	Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   180	16	8	copyall	Lscala/collection/parallel/ParIterableLike$ParComposite;
/*      */     //   219	90	10	cntx	Lscala/collection/generic/DefaultSignalling;
/*      */   }
/*      */   
/*      */   public static ParIterable dropWhile(ParIterableLike<T, Repr, Sequential> $this, Function1 pred) {
/*      */     // Byte code:
/*      */     //   0: new scala/collection/parallel/ParIterableLike$$anon$8
/*      */     //   3: dup
/*      */     //   4: aload_0
/*      */     //   5: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   8: astore_2
/*      */     //   9: aload_2
/*      */     //   10: checkcast scala/collection/generic/AtomicIndexFlag
/*      */     //   13: ldc_w 2147483647
/*      */     //   16: invokeinterface setIndexFlag : (I)V
/*      */     //   21: aload_0
/*      */     //   22: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   27: aload_0
/*      */     //   28: new scala/collection/parallel/ParIterableLike$Span
/*      */     //   31: dup
/*      */     //   32: aload_0
/*      */     //   33: iconst_0
/*      */     //   34: aload_1
/*      */     //   35: aload_0
/*      */     //   36: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   41: aload_0
/*      */     //   42: invokeinterface combinerFactory : ()Lscala/collection/parallel/CombinerFactory;
/*      */     //   47: aload_0
/*      */     //   48: aload_0
/*      */     //   49: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   54: invokeinterface delegatedSignalling2ops : (Lscala/collection/generic/DelegatedSignalling;)Lscala/collection/parallel/ParIterableLike$SignallingOps;
/*      */     //   59: aload_2
/*      */     //   60: invokeinterface assign : (Lscala/collection/generic/Signalling;)Lscala/collection/generic/DelegatedSignalling;
/*      */     //   65: checkcast scala/collection/parallel/IterableSplitter
/*      */     //   68: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;ILscala/Function1;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   71: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   76: new scala/collection/parallel/ParIterableLike$$anonfun$dropWhile$1
/*      */     //   79: dup
/*      */     //   80: aload_0
/*      */     //   81: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   84: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   89: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   94: checkcast scala/collection/parallel/ParIterable
/*      */     //   97: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #801	-> 0
/*      */     //   #802	-> 9
/*      */     //   #803	-> 21
/*      */     //   #804	-> 27
/*      */     //   #805	-> 76
/*      */     //   #804	-> 84
/*      */     //   #803	-> 89
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	98	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	98	1	pred	Lscala/Function1;
/*      */     //   9	88	2	cntx	Lscala/collection/generic/DefaultSignalling;
/*      */   }
/*      */   
/*      */   public static void copyToArray(ParIterableLike $this, Object xs) {
/*  810 */     $this.copyToArray(xs, 0);
/*      */   }
/*      */   
/*      */   public static void copyToArray(ParIterableLike $this, Object xs, int start) {
/*  812 */     $this.copyToArray(xs, start, ScalaRunTime$.MODULE$.array_length(xs) - start);
/*      */   }
/*      */   
/*      */   public static void copyToArray(ParIterableLike $this, Object xs, int start, int len) {
/*      */     // Byte code:
/*      */     //   0: iload_3
/*      */     //   1: iconst_0
/*      */     //   2: if_icmple -> 34
/*      */     //   5: aload_0
/*      */     //   6: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   11: new scala/collection/parallel/ParIterableLike$CopyToArray
/*      */     //   14: dup
/*      */     //   15: aload_0
/*      */     //   16: iload_2
/*      */     //   17: iload_3
/*      */     //   18: aload_1
/*      */     //   19: aload_0
/*      */     //   20: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   25: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;IILjava/lang/Object;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   28: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   33: pop
/*      */     //   34: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #814	-> 0
/*      */     //   #815	-> 5
/*      */     //   #814	-> 34
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	35	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	35	1	xs	Ljava/lang/Object;
/*      */     //   0	35	2	start	I
/*      */     //   0	35	3	len	I
/*      */   }
/*      */   
/*      */   public static boolean sameElements(ParIterableLike $this, GenIterable that) {
/*  818 */     return $this.seq().sameElements(that);
/*      */   }
/*      */   
/*      */   public static Object zip(ParIterableLike $this, GenIterable that, CanBuildFrom bf) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: aload_2
/*      */     //   2: aload_0
/*      */     //   3: invokeinterface repr : ()Lscala/collection/parallel/ParIterable;
/*      */     //   8: invokeinterface apply : (Ljava/lang/Object;)Lscala/collection/mutable/Builder;
/*      */     //   13: invokeinterface builder2ops : (Lscala/collection/mutable/Builder;)Lscala/collection/parallel/ParIterableLike$BuilderOps;
/*      */     //   18: invokeinterface isCombiner : ()Z
/*      */     //   23: ifeq -> 122
/*      */     //   26: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   29: aload_1
/*      */     //   30: invokevirtual traversable2ops : (Lscala/collection/GenTraversableOnce;)Lscala/collection/parallel/TraversableOps;
/*      */     //   33: invokeinterface isParSeq : ()Z
/*      */     //   38: ifeq -> 122
/*      */     //   41: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   44: aload_1
/*      */     //   45: invokevirtual traversable2ops : (Lscala/collection/GenTraversableOnce;)Lscala/collection/parallel/TraversableOps;
/*      */     //   48: invokeinterface asParSeq : ()Lscala/collection/parallel/ParSeq;
/*      */     //   53: astore_3
/*      */     //   54: aload_0
/*      */     //   55: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   60: aload_0
/*      */     //   61: new scala/collection/parallel/ParIterableLike$Zip
/*      */     //   64: dup
/*      */     //   65: aload_0
/*      */     //   66: aload_0
/*      */     //   67: new scala/collection/parallel/ParIterableLike$$anonfun$zip$1
/*      */     //   70: dup
/*      */     //   71: aload_0
/*      */     //   72: aload_2
/*      */     //   73: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/generic/CanBuildFrom;)V
/*      */     //   76: invokeinterface combinerFactory : (Lscala/Function0;)Lscala/collection/parallel/CombinerFactory;
/*      */     //   81: aload_0
/*      */     //   82: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   87: aload_3
/*      */     //   88: invokeinterface splitter : ()Lscala/collection/parallel/SeqSplitter;
/*      */     //   93: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;Lscala/collection/parallel/SeqSplitter;)V
/*      */     //   96: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   101: new scala/collection/parallel/ParIterableLike$$anonfun$zip$2
/*      */     //   104: dup
/*      */     //   105: aload_0
/*      */     //   106: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   109: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   114: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   119: goto -> 153
/*      */     //   122: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   125: aload_0
/*      */     //   126: invokeinterface seq : ()Lscala/collection/Iterable;
/*      */     //   131: aload_1
/*      */     //   132: aload_0
/*      */     //   133: aload_2
/*      */     //   134: invokeinterface bf2seq : (Lscala/collection/generic/CanBuildFrom;)Lscala/collection/generic/CanBuildFrom;
/*      */     //   139: invokeinterface zip : (Lscala/collection/GenIterable;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*      */     //   144: aload_0
/*      */     //   145: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   150: invokevirtual setTaskSupport : (Ljava/lang/Object;Lscala/collection/parallel/TaskSupport;)Ljava/lang/Object;
/*      */     //   153: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #820	-> 0
/*      */     //   #821	-> 41
/*      */     //   #822	-> 54
/*      */     //   #823	-> 122
/*      */     //   #820	-> 153
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	154	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	154	1	that	Lscala/collection/GenIterable;
/*      */     //   0	154	2	bf	Lscala/collection/generic/CanBuildFrom;
/*      */     //   54	65	3	thatseq	Lscala/collection/parallel/ParSeq;
/*      */   }
/*      */   
/*      */   public static Object zipWithIndex(ParIterableLike $this, CanBuildFrom bf) {
/*  825 */     return $this.zip((GenIterable)ParRange$.MODULE$.apply(0, $this.size(), 1, false), bf);
/*      */   }
/*      */   
/*      */   public static Object zipAll(ParIterableLike $this, GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: aload #4
/*      */     //   3: aload_0
/*      */     //   4: invokeinterface repr : ()Lscala/collection/parallel/ParIterable;
/*      */     //   9: invokeinterface apply : (Ljava/lang/Object;)Lscala/collection/mutable/Builder;
/*      */     //   14: invokeinterface builder2ops : (Lscala/collection/mutable/Builder;)Lscala/collection/parallel/ParIterableLike$BuilderOps;
/*      */     //   19: invokeinterface isCombiner : ()Z
/*      */     //   24: ifeq -> 156
/*      */     //   27: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   30: aload_1
/*      */     //   31: invokevirtual traversable2ops : (Lscala/collection/GenTraversableOnce;)Lscala/collection/parallel/TraversableOps;
/*      */     //   34: invokeinterface isParSeq : ()Z
/*      */     //   39: ifeq -> 156
/*      */     //   42: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   45: aload_1
/*      */     //   46: invokevirtual traversable2ops : (Lscala/collection/GenTraversableOnce;)Lscala/collection/parallel/TraversableOps;
/*      */     //   49: invokeinterface asParSeq : ()Lscala/collection/parallel/ParSeq;
/*      */     //   54: astore #7
/*      */     //   56: aload_0
/*      */     //   57: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   62: aload_0
/*      */     //   63: new scala/collection/parallel/ParIterableLike$ZipAll
/*      */     //   66: dup
/*      */     //   67: aload_0
/*      */     //   68: getstatic scala/runtime/RichInt$.MODULE$ : Lscala/runtime/RichInt$;
/*      */     //   71: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*      */     //   74: aload_0
/*      */     //   75: invokeinterface size : ()I
/*      */     //   80: istore #6
/*      */     //   82: astore #5
/*      */     //   84: iload #6
/*      */     //   86: aload #7
/*      */     //   88: invokeinterface length : ()I
/*      */     //   93: invokevirtual max$extension : (II)I
/*      */     //   96: aload_2
/*      */     //   97: aload_3
/*      */     //   98: aload_0
/*      */     //   99: new scala/collection/parallel/ParIterableLike$$anonfun$zipAll$1
/*      */     //   102: dup
/*      */     //   103: aload_0
/*      */     //   104: aload #4
/*      */     //   106: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/generic/CanBuildFrom;)V
/*      */     //   109: invokeinterface combinerFactory : (Lscala/Function0;)Lscala/collection/parallel/CombinerFactory;
/*      */     //   114: aload_0
/*      */     //   115: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   120: aload #7
/*      */     //   122: invokeinterface splitter : ()Lscala/collection/parallel/SeqSplitter;
/*      */     //   127: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;ILjava/lang/Object;Ljava/lang/Object;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;Lscala/collection/parallel/SeqSplitter;)V
/*      */     //   130: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   135: new scala/collection/parallel/ParIterableLike$$anonfun$zipAll$2
/*      */     //   138: dup
/*      */     //   139: aload_0
/*      */     //   140: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   143: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   148: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   153: goto -> 190
/*      */     //   156: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*      */     //   159: aload_0
/*      */     //   160: invokeinterface seq : ()Lscala/collection/Iterable;
/*      */     //   165: aload_1
/*      */     //   166: aload_2
/*      */     //   167: aload_3
/*      */     //   168: aload_0
/*      */     //   169: aload #4
/*      */     //   171: invokeinterface bf2seq : (Lscala/collection/generic/CanBuildFrom;)Lscala/collection/generic/CanBuildFrom;
/*      */     //   176: invokeinterface zipAll : (Lscala/collection/GenIterable;Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*      */     //   181: aload_0
/*      */     //   182: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   187: invokevirtual setTaskSupport : (Ljava/lang/Object;Lscala/collection/parallel/TaskSupport;)Ljava/lang/Object;
/*      */     //   190: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #827	-> 0
/*      */     //   #828	-> 42
/*      */     //   #829	-> 56
/*      */     //   #830	-> 62
/*      */     //   #831	-> 135
/*      */     //   #830	-> 143
/*      */     //   #829	-> 148
/*      */     //   #834	-> 156
/*      */     //   #827	-> 190
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	191	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	191	1	that	Lscala/collection/GenIterable;
/*      */     //   0	191	2	thisElem	Ljava/lang/Object;
/*      */     //   0	191	3	thatElem	Ljava/lang/Object;
/*      */     //   0	191	4	bf	Lscala/collection/generic/CanBuildFrom;
/*      */     //   56	97	7	thatseq	Lscala/collection/parallel/ParSeq;
/*      */   }
/*      */   
/*      */   public static Object toParCollection(ParIterableLike $this, Function0 cbf) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: aload_0
/*      */     //   7: new scala/collection/parallel/ParIterableLike$ToParCollection
/*      */     //   10: dup
/*      */     //   11: aload_0
/*      */     //   12: aload_0
/*      */     //   13: aload_1
/*      */     //   14: invokeinterface combinerFactory : (Lscala/Function0;)Lscala/collection/parallel/CombinerFactory;
/*      */     //   19: aload_0
/*      */     //   20: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   25: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;)V
/*      */     //   28: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   33: new scala/collection/parallel/ParIterableLike$$anonfun$toParCollection$1
/*      */     //   36: dup
/*      */     //   37: aload_0
/*      */     //   38: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   41: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   46: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   51: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #837	-> 0
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	52	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	52	1	cbf	Lscala/Function0;
/*      */   }
/*      */   
/*      */   public static Object toParMap(ParIterableLike $this, Function0 cbf, Predef$.less.colon.less ev) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokeinterface tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*      */     //   6: aload_0
/*      */     //   7: new scala/collection/parallel/ParIterableLike$ToParMap
/*      */     //   10: dup
/*      */     //   11: aload_0
/*      */     //   12: aload_0
/*      */     //   13: aload_1
/*      */     //   14: invokeinterface combinerFactory : (Lscala/Function0;)Lscala/collection/parallel/CombinerFactory;
/*      */     //   19: aload_0
/*      */     //   20: invokeinterface splitter : ()Lscala/collection/parallel/IterableSplitter;
/*      */     //   25: aload_2
/*      */     //   26: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;Lscala/collection/parallel/CombinerFactory;Lscala/collection/parallel/IterableSplitter;Lscala/Predef$$less$colon$less;)V
/*      */     //   29: invokeinterface task2ops : (Lscala/collection/parallel/ParIterableLike$StrictSplitterCheckTask;)Lscala/collection/parallel/ParIterableLike$TaskOps;
/*      */     //   34: new scala/collection/parallel/ParIterableLike$$anonfun$toParMap$1
/*      */     //   37: dup
/*      */     //   38: aload_0
/*      */     //   39: invokespecial <init> : (Lscala/collection/parallel/ParIterableLike;)V
/*      */     //   42: invokeinterface mapResult : (Lscala/Function1;)Lscala/collection/parallel/ParIterableLike$ResultMapping;
/*      */     //   47: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*      */     //   52: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #841	-> 0
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	53	0	$this	Lscala/collection/parallel/ParIterableLike;
/*      */     //   0	53	1	cbf	Lscala/Function0;
/*      */     //   0	53	2	ev	Lscala/Predef$$less$colon$less;
/*      */   }
/*      */   
/*      */   public static ParIterableView view(ParIterableLike<T, Repr, Sequential> $this) {
/*  844 */     return new ParIterableLike$$anon$1($this);
/*      */   }
/*      */   
/*      */   public static Object toArray(ParIterableLike $this, ClassTag evidence$1) {
/*  854 */     Object arr = evidence$1.newArray($this.size());
/*  855 */     $this.copyToArray(arr);
/*  856 */     return arr;
/*      */   }
/*      */   
/*      */   public static List toList(ParIterableLike $this) {
/*  859 */     return $this.seq().toList();
/*      */   }
/*      */   
/*      */   public static IndexedSeq toIndexedSeq(ParIterableLike $this) {
/*  861 */     return $this.seq().toIndexedSeq();
/*      */   }
/*      */   
/*      */   public static Stream toStream(ParIterableLike $this) {
/*  863 */     return $this.seq().toStream();
/*      */   }
/*      */   
/*      */   public static Iterator toIterator(ParIterableLike $this) {
/*  865 */     return $this.splitter();
/*      */   }
/*      */   
/*      */   public static Buffer toBuffer(ParIterableLike $this) {
/*  869 */     return $this.seq().toBuffer();
/*      */   }
/*      */   
/*      */   public static GenTraversable toTraversable(ParIterableLike $this) {
/*  871 */     return (GenTraversable)$this;
/*      */   }
/*      */   
/*      */   public static ParIterable toIterable(ParIterableLike $this) {
/*  873 */     return (ParIterable)$this;
/*      */   }
/*      */   
/*      */   public static ParSeq toSeq(ParIterableLike<T, Repr, Sequential> $this) {
/*  875 */     return (ParSeq)$this.toParCollection((Function0)new ParIterableLike$$anonfun$toSeq$1($this));
/*      */   }
/*      */   
/*      */   public static ParSet toSet(ParIterableLike<T, Repr, Sequential> $this) {
/*  877 */     return (ParSet)$this.toParCollection((Function0)new ParIterableLike$$anonfun$toSet$1($this));
/*      */   }
/*      */   
/*      */   public static ParMap toMap(ParIterableLike<T, Repr, Sequential> $this, Predef$.less.colon.less ev) {
/*  879 */     return (ParMap)$this.toParMap((Function0)new ParIterableLike$$anonfun$toMap$1($this), ev);
/*      */   }
/*      */   
/*      */   public static Vector toVector(ParIterableLike $this) {
/*  881 */     return (Vector)$this.to(Vector$.MODULE$.canBuildFrom());
/*      */   }
/*      */   
/*      */   public static Object to(ParIterableLike $this, CanBuildFrom cbf) {
/*  883 */     return $this.builder2ops(cbf.apply()).isCombiner() ? 
/*  884 */       $this.toParCollection((Function0)new ParIterableLike$$anonfun$to$1($this, (ParIterableLike<T, Repr, Sequential>)cbf)) : 
/*  885 */       $this.seq().to(cbf);
/*      */   }
/*      */   
/*      */   public static int scanBlockSize(ParIterableLike $this) {
/* 1433 */     int i = package$.MODULE$.thresholdFromSize($this.size(), $this.tasksupport().parallelismLevel()) / 2;
/* 1433 */     Predef$ predef$ = Predef$.MODULE$;
/* 1433 */     return RichInt$.MODULE$.max$extension(i, 1);
/*      */   }
/*      */   
/*      */   public static Object $div$colon(ParIterableLike $this, Object z, Function2 op) {
/* 1476 */     return $this.foldLeft(z, op);
/*      */   }
/*      */   
/*      */   public static Object $colon$bslash(ParIterableLike $this, Object z, Function2 op) {
/* 1478 */     return $this.foldRight(z, op);
/*      */   }
/*      */   
/*      */   public static String debugInformation(ParIterableLike $this) {
/* 1482 */     return (new StringBuilder()).append("Parallel collection: ").append($this.getClass()).toString();
/*      */   }
/*      */   
/*      */   public static Seq brokenInvariants(ParIterableLike $this) {
/* 1484 */     return (Seq)Seq$.MODULE$.apply((Seq)Nil$.MODULE$);
/*      */   }
/*      */   
/*      */   public static void debugclear(ParIterableLike $this) {
/* 1490 */     synchronized ($this) {
/* 1491 */       $this.debugBuffer().clear();
/*      */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/parallel/ParIterableLike}, name=$this} */
/*      */       return;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static ArrayBuffer debuglog(ParIterableLike $this, String s) {
/* 1494 */     synchronized ($this) {
/* 1495 */       ArrayBuffer arrayBuffer = $this.debugBuffer().$plus$eq(s);
/*      */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/parallel/ParIterableLike}, name=$this} */
/*      */       return arrayBuffer;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void printDebugBuffer(ParIterableLike<T, Repr, Sequential> $this) {
/* 1499 */     Predef$.MODULE$.println(DebugUtils$.MODULE$.buildString(
/* 1500 */           (Function1)new ParIterableLike$$anonfun$printDebugBuffer$1($this)));
/*      */   }
/*      */   
/*      */   public static ArrayBuffer debugBuffer(ParIterableLike $this) {
/*      */     return null;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParIterableLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */