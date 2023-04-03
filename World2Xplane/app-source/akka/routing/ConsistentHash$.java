/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ConsistentHash$ {
/*     */   public static final ConsistentHash$ MODULE$;
/*     */   
/*     */   public class ConsistentHash$$anonfun$$colon$plus$1 extends AbstractFunction1<Object, Tuple2<Object, T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object node$1;
/*     */     
/*     */     private final int nodeHash$1;
/*     */     
/*     */     public final Tuple2<Object, T> apply(int r) {
/*  42 */       return scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(BoxesRunTime.boxToInteger(ConsistentHash$.MODULE$.akka$routing$ConsistentHash$$concatenateNodeHash(this.nodeHash$1, r))), this.node$1);
/*     */     }
/*     */     
/*     */     public ConsistentHash$$anonfun$$colon$plus$1(ConsistentHash $outer, Object node$1, int nodeHash$1) {}
/*     */   }
/*     */   
/*     */   public class ConsistentHash$$anonfun$$colon$minus$1 extends AbstractFunction1.mcII.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int nodeHash$2;
/*     */     
/*     */     public final int apply(int r) {
/*  60 */       return apply$mcII$sp(r);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int r) {
/*  60 */       return ConsistentHash$.MODULE$.akka$routing$ConsistentHash$$concatenateNodeHash(this.nodeHash$2, r);
/*     */     }
/*     */     
/*     */     public ConsistentHash$$anonfun$$colon$minus$1(ConsistentHash $outer, int nodeHash$2) {}
/*     */   }
/*     */   
/*     */   private ConsistentHash$() {
/* 111 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <T> ConsistentHash<T> apply(Iterable nodes, int virtualNodesFactor, ClassTag<T> evidence$2) {
/* 113 */     return new ConsistentHash<T>(scala.collection.immutable.SortedMap$.MODULE$.empty((Ordering)scala.math.Ordering$Int$.MODULE$).$plus$plus(
/*     */           
/* 115 */           (GenTraversableOnce)((TraversableLike)nodes.map((Function1)new ConsistentHash$$anonfun$apply$1(), scala.collection.Iterable$.MODULE$.canBuildFrom())).flatMap((Function1)new ConsistentHash$$anonfun$apply$2(virtualNodesFactor), scala.collection.Iterable$.MODULE$.canBuildFrom())), 
/*     */         
/* 119 */         virtualNodesFactor, evidence$2);
/*     */   }
/*     */   
/*     */   public static class ConsistentHash$$anonfun$apply$2 extends AbstractFunction1<Tuple2<T, Object>, IndexedSeq<Tuple2<Object, T>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int virtualNodesFactor$1;
/*     */     
/*     */     public final IndexedSeq<Tuple2<Object, T>> apply(Tuple2 x$3) {
/*     */       Tuple2 tuple2 = x$3;
/*     */       if (tuple2 != null) {
/*     */         Object node = tuple2._1();
/*     */         int nodeHash = tuple2._2$mcI$sp();
/*     */         return (IndexedSeq)scala.runtime.RichInt$.MODULE$.to$extension0(scala.Predef$.MODULE$.intWrapper(1), this.virtualNodesFactor$1).map((Function1)new ConsistentHash$$anonfun$apply$2$$anonfun$apply$3(this, node, nodeHash), scala.collection.immutable.IndexedSeq$.MODULE$.canBuildFrom());
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
/*     */         return scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(BoxesRunTime.boxToInteger(ConsistentHash$.MODULE$.akka$routing$ConsistentHash$$concatenateNodeHash(this.nodeHash$3, vnode))), this.node$2);
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
/*     */   
/*     */   public <T> ConsistentHash<T> create(Iterable nodes, int virtualNodesFactor) {
/* 127 */     return apply((Iterable<T>)scala.collection.JavaConverters$.MODULE$.iterableAsScalaIterableConverter(nodes).asScala(), virtualNodesFactor, scala.reflect.ClassTag$.MODULE$.apply(Object.class));
/*     */   }
/*     */   
/*     */   public int akka$routing$ConsistentHash$$concatenateNodeHash(int nodeHash, int vnode) {
/* 132 */     int h = MurmurHash$.MODULE$.startHash(nodeHash);
/* 133 */     h = MurmurHash$.MODULE$.extendHash(h, vnode, MurmurHash$.MODULE$.startMagicA(), MurmurHash$.MODULE$.startMagicB());
/* 134 */     return MurmurHash$.MODULE$.finalizeHash(h);
/*     */   }
/*     */   
/*     */   public int akka$routing$ConsistentHash$$hashFor(byte[] bytes) {
/* 137 */     return MurmurHash$.MODULE$.arrayHash$mBc$sp(bytes);
/*     */   }
/*     */   
/*     */   public int akka$routing$ConsistentHash$$hashFor(String string) {
/* 139 */     return MurmurHash$.MODULE$.stringHash(string);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ConsistentHash$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */