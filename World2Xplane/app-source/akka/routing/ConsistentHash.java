/*     */ package akka.routing;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.Predef$ArrowAssoc$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.IndexedSeq$;
/*     */ import scala.collection.immutable.SortedMap;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.math.package$;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichInt$;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005}d\001B\001\003\001\035\021abQ8og&\034H/\0328u\021\006\034\bN\003\002\004\t\0059!o\\;uS:<'\"A\003\002\t\005\\7.Y\002\001+\tAad\005\002\001\023A\021!\"D\007\002\027)\tA\"A\003tG\006d\027-\003\002\017\027\t1\021I\\=SK\032D\001\002\005\001\003\002\003\006I!E\001\006]>$Wm\035\t\005%]IB$D\001\024\025\t!R#A\005j[6,H/\0312mK*\021acC\001\013G>dG.Z2uS>t\027B\001\r\024\005%\031vN\035;fI6\013\007\017\005\002\0135%\0211d\003\002\004\023:$\bCA\017\037\031\001!Qa\b\001C\002\001\022\021\001V\t\003C\021\002\"A\003\022\n\005\rZ!a\002(pi\"Lgn\032\t\003\025\025J!AJ\006\003\007\005s\027\020\003\005)\001\t\025\r\021\"\001*\003I1\030N\035;vC2tu\016Z3t\r\006\034Go\034:\026\003eA\001b\013\001\003\002\003\006I!G\001\024m&\024H/^1m\035>$Wm\035$bGR|'\017\t\005\t[\001\021\031\021)A\006]\005QQM^5eK:\034W\rJ\031\021\007=\022D$D\0011\025\t\t4\"A\004sK\032dWm\031;\n\005M\002$\001C\"mCN\034H+Y4\t\013U\002A\021\002\034\002\rqJg.\033;?)\r94\b\020\013\003qi\0022!\017\001\035\033\005\021\001\"B\0275\001\bq\003\"\002\t5\001\004\t\002\"\002\0255\001\004I\002b\002 \001\003\003\006IaP\001\004q\022\022\004\003\002\006A\005\026K!!Q\006\003\rQ+\b\017\\33!\rQ1)G\005\003\t.\021Q!\021:sCf\0042AC\"\035\021\0359\005A1A\005\n!\013AB\\8eK\"\0137\017\033*j]\036,\022A\021\005\007\025\002\001\013\021\002\"\002\0339|G-\032%bg\"\024\026N\\4!\021\035a\005A1A\005\n5\013\001B\\8eKJKgnZ\013\002\013\"1q\n\001Q\001\n\025\013\021B\\8eKJKgn\032\021\t\013E\003A\021\001*\002\027\021\032w\016\\8oIAdWo\035\013\003qMCQ\001\026)A\002q\tAA\\8eK\")a\013\001C\001/\006\031\021\r\0323\025\005aB\006\"\002+V\001\004a\002\"\002.\001\t\003Y\026\001\004\023d_2|g\016J7j]V\034HC\001\035]\021\025!\026\f1\001\035\021\025q\006\001\"\001`\003\031\021X-\\8wKR\021\001\b\031\005\006)v\003\r\001\b\005\006E\002!IaY\001\004S\022DHCA\re\021\025)\027\r1\001\032\003\005I\007\"B4\001\t\003A\027a\0028pI\0264uN\035\013\0039%DQA\0334A\002-\f1a[3z!\rQ1\t\034\t\003\0255L!A\\\006\003\t\tKH/\032\005\006O\002!\t\001\035\013\0039EDQA[8A\002I\004\"a\035<\017\005)!\030BA;\f\003\031\001&/\0323fM&\021q\017\037\002\007'R\024\030N\\4\013\005U\\\001\"\002>\001\t\003Y\030aB5t\0136\004H/_\013\002yB\021!\"`\005\003}.\021qAQ8pY\026\fgnB\004\002\002\tA\t!a\001\002\035\r{gn]5ti\026tG\017S1tQB\031\021(!\002\007\r\005\021\001\022AA\004'\r\t)!\003\005\bk\005\025A\021AA\006)\t\t\031\001\003\005\002\020\005\025A\021AA\t\003\025\t\007\017\0357z+\021\t\031\"a\007\025\r\005U\0211EA\037)\021\t9\"!\b\021\te\002\021\021\004\t\004;\005mAAB\020\002\016\t\007\001\005\003\006\002 \0055\021\021!a\002\003C\t!\"\032<jI\026t7-\032\0233!\021y#'!\007\t\017A\ti\0011\001\002&A1\021qEA\034\0033qA!!\013\00249!\0211FA\031\033\t\tiCC\002\0020\031\ta\001\020:p_Rt\024\"\001\007\n\007\005U2\"A\004qC\016\\\027mZ3\n\t\005e\0221\b\002\t\023R,'/\0312mK*\031\021QG\006\t\r!\ni\0011\001\032\021!\t\t%!\002\005\002\005\r\023AB2sK\006$X-\006\003\002F\005-CCBA$\003\033\ni\006\005\003:\001\005%\003cA\017\002L\0211q$a\020C\002\001Bq\001EA \001\004\ty\005\005\004\002R\005m\023\021J\007\003\003'RA!!\026\002X\005!A.\0318h\025\t\tI&\001\003kCZ\f\027\002BA\035\003'Ba\001KA \001\004I\002\002CA1\003\013!I!a\031\002'\r|gnY1uK:\fG/\032(pI\026D\025m\0355\025\013e\t)'!\033\t\017\005\035\024q\fa\0013\005Aan\0343f\021\006\034\b\016C\004\002l\005}\003\031A\r\002\013Ytw\016Z3\t\021\005=\024Q\001C\005\003c\nq\001[1tQ\032{'\017F\002\032\003gBq!!\036\002n\001\0071.A\003csR,7\017\003\005\002p\005\025A\021BA=)\rI\0221\020\005\b\003{\n9\b1\001s\003\031\031HO]5oO\002")
/*     */ public class ConsistentHash<T> {
/*     */   private final SortedMap<Object, T> nodes;
/*     */   
/*     */   private final int virtualNodesFactor;
/*     */   
/*     */   private final ClassTag<T> evidence$1;
/*     */   
/*     */   private final Tuple2<int[], Object> x$2;
/*     */   
/*     */   private final int[] nodeHashRing;
/*     */   
/*     */   private final Object nodeRing;
/*     */   
/*     */   public int virtualNodesFactor() {
/*  21 */     return this.virtualNodesFactor;
/*     */   }
/*     */   
/*     */   public ConsistentHash(SortedMap<Object, T> nodes, int virtualNodesFactor, ClassTag<T> evidence$1) {
/*  25 */     if (virtualNodesFactor < 1)
/*  25 */       throw new IllegalArgumentException("virtualNodesFactor must be >= 1"); 
/*  31 */     Tuple2 tuple2 = nodes.toSeq().unzip((Function1)Predef$.MODULE$.conforms());
/*  31 */     if (tuple2 != null) {
/*  31 */       Seq nhr = (Seq)tuple2._1(), nr = (Seq)tuple2._2();
/*  31 */       if (nhr != null) {
/*  31 */         Seq seq = nhr;
/*  31 */         if (nr != null) {
/*  31 */           Seq seq1 = nr;
/*  31 */           Tuple2 tuple23 = new Tuple2(seq, seq1), tuple22 = tuple23;
/*  31 */           Seq seq2 = (Seq)tuple22._1(), seq3 = (Seq)tuple22._2();
/*  32 */           Tuple2 tuple21 = new Tuple2(seq2.toArray(ClassTag$.MODULE$.Int()), seq3.toArray(evidence$1));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   private int[] nodeHashRing() {
/*     */     return this.nodeHashRing;
/*     */   }
/*     */   
/*     */   private Object nodeRing() {
/*     */     return this.nodeRing;
/*     */   }
/*     */   
/*     */   public ConsistentHash<T> $colon$plus(Object node) {
/*  41 */     int nodeHash = ConsistentHash$.MODULE$.akka$routing$ConsistentHash$$hashFor(node.toString());
/*  42 */     return new ConsistentHash(this.nodes.$plus$plus((GenTraversableOnce)RichInt$.MODULE$.to$extension0(Predef$.MODULE$.intWrapper(1), virtualNodesFactor()).map((Function1)new ConsistentHash$$anonfun$$colon$plus$1(this, node, nodeHash), IndexedSeq$.MODULE$.canBuildFrom())), 
/*  43 */         virtualNodesFactor(), this.evidence$1);
/*     */   }
/*     */   
/*     */   public class ConsistentHash$$anonfun$$colon$plus$1 extends AbstractFunction1<Object, Tuple2<Object, T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object node$1;
/*     */     
/*     */     private final int nodeHash$1;
/*     */     
/*     */     public final Tuple2<Object, T> apply(int r) {
/*     */       return Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.any2ArrowAssoc(BoxesRunTime.boxToInteger(ConsistentHash$.MODULE$.akka$routing$ConsistentHash$$concatenateNodeHash(this.nodeHash$1, r))), this.node$1);
/*     */     }
/*     */     
/*     */     public ConsistentHash$$anonfun$$colon$plus$1(ConsistentHash $outer, Object node$1, int nodeHash$1) {}
/*     */   }
/*     */   
/*     */   public ConsistentHash<T> add(Object node) {
/*  51 */     return $colon$plus((T)node);
/*     */   }
/*     */   
/*     */   public ConsistentHash<T> $colon$minus(Object node) {
/*  59 */     int nodeHash = ConsistentHash$.MODULE$.akka$routing$ConsistentHash$$hashFor(node.toString());
/*  60 */     return new ConsistentHash((SortedMap<Object, T>)this.nodes.$minus$minus((GenTraversableOnce)RichInt$.MODULE$.to$extension0(Predef$.MODULE$.intWrapper(1), virtualNodesFactor()).map((Function1)new ConsistentHash$$anonfun$$colon$minus$1(this, nodeHash), IndexedSeq$.MODULE$.canBuildFrom())), 
/*  61 */         virtualNodesFactor(), this.evidence$1);
/*     */   }
/*     */   
/*     */   public class ConsistentHash$$anonfun$$colon$minus$1 extends AbstractFunction1.mcII.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int nodeHash$2;
/*     */     
/*     */     public final int apply(int r) {
/*     */       return apply$mcII$sp(r);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int r) {
/*     */       return ConsistentHash$.MODULE$.akka$routing$ConsistentHash$$concatenateNodeHash(this.nodeHash$2, r);
/*     */     }
/*     */     
/*     */     public ConsistentHash$$anonfun$$colon$minus$1(ConsistentHash $outer, int nodeHash$2) {}
/*     */   }
/*     */   
/*     */   public ConsistentHash<T> remove(Object node) {
/*  69 */     return $colon$minus((T)node);
/*     */   }
/*     */   
/*     */   private int idx(int i) {
/*  76 */     int j = package$.MODULE$.abs(i + 1);
/*  77 */     return (i >= 0) ? i : ((j >= (nodeHashRing()).length) ? 0 : 
/*  78 */       j);
/*     */   }
/*     */   
/*     */   public T nodeFor(byte[] key) {
/*  88 */     if (isEmpty())
/*  88 */       throw new IllegalStateException((new StringOps(Predef$.MODULE$.augmentString("Can't get node for [%s] from an empty node ring"))).format(Predef$.MODULE$.genericWrapArray(new Object[] { key }))); 
/*  90 */     return (T)ScalaRunTime$.MODULE$.array_apply(nodeRing(), idx(Arrays.binarySearch(nodeHashRing(), ConsistentHash$.MODULE$.akka$routing$ConsistentHash$$hashFor(key))));
/*     */   }
/*     */   
/*     */   public T nodeFor(String key) {
/*  99 */     if (isEmpty())
/*  99 */       throw new IllegalStateException((new StringOps(Predef$.MODULE$.augmentString("Can't get node for [%s] from an empty node ring"))).format(Predef$.MODULE$.genericWrapArray(new Object[] { key }))); 
/* 101 */     return (T)ScalaRunTime$.MODULE$.array_apply(nodeRing(), idx(Arrays.binarySearch(nodeHashRing(), ConsistentHash$.MODULE$.akka$routing$ConsistentHash$$hashFor(key))));
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 107 */     return this.nodes.isEmpty();
/*     */   }
/*     */   
/*     */   public static <T> ConsistentHash<T> create(Iterable<T> paramIterable, int paramInt) {
/*     */     return ConsistentHash$.MODULE$.create(paramIterable, paramInt);
/*     */   }
/*     */   
/*     */   public static <T> ConsistentHash<T> apply(Iterable<T> paramIterable, int paramInt, ClassTag<T> paramClassTag) {
/*     */     return ConsistentHash$.MODULE$.apply(paramIterable, paramInt, paramClassTag);
/*     */   }
/*     */   
/*     */   public static class ConsistentHash$$anonfun$apply$2 extends AbstractFunction1<Tuple2<T, Object>, IndexedSeq<Tuple2<Object, T>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int virtualNodesFactor$1;
/*     */     
/*     */     public final IndexedSeq<Tuple2<Object, T>> apply(Tuple2 x$3) {
/* 115 */       Tuple2 tuple2 = x$3;
/* 115 */       if (tuple2 != null) {
/* 115 */         Object node = tuple2._1();
/* 116 */         int nodeHash = tuple2._2$mcI$sp();
/* 116 */         return (IndexedSeq)RichInt$.MODULE$
/* 117 */           .to$extension0(Predef$.MODULE$.intWrapper(1), this.virtualNodesFactor$1).map((Function1)new ConsistentHash$$anonfun$apply$2$$anonfun$apply$3(this, node, nodeHash), IndexedSeq$.MODULE$.canBuildFrom());
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public ConsistentHash$$anonfun$apply$2(int virtualNodesFactor$1) {}
/*     */     
/*     */     public class ConsistentHash$$anonfun$apply$2$$anonfun$apply$3 extends AbstractFunction1<Object, Tuple2<Object, T>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object node$2;
/*     */       
/*     */       private final int nodeHash$3;
/*     */       
/*     */       public ConsistentHash$$anonfun$apply$2$$anonfun$apply$3(ConsistentHash$$anonfun$apply$2 $outer, Object node$2, int nodeHash$3) {}
/*     */       
/*     */       public final Tuple2<Object, T> apply(int vnode) {
/* 118 */         return Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.any2ArrowAssoc(BoxesRunTime.boxToInteger(ConsistentHash$.MODULE$.akka$routing$ConsistentHash$$concatenateNodeHash(this.nodeHash$3, vnode))), this.node$2);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ConsistentHash$$anonfun$apply$1 extends AbstractFunction1<T, Tuple2<T, Object>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<T, Object> apply(Object node) {
/*     */       int nodeHash = ConsistentHash$.MODULE$.akka$routing$ConsistentHash$$hashFor(node.toString());
/*     */       return new Tuple2(node, BoxesRunTime.boxToInteger(nodeHash));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ConsistentHash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */