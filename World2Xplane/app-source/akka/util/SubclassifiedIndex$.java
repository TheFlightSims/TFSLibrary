/*     */ package akka.util;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SetLike;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BooleanRef;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class SubclassifiedIndex$ {
/*     */   public static final SubclassifiedIndex$ MODULE$;
/*     */   
/*     */   private final Map<Object, Set<Object>> internalEmptyMergeMap;
/*     */   
/*     */   private SubclassifiedIndex$() {
/*  22 */     MODULE$ = this;
/*  60 */     this.internalEmptyMergeMap = ((Map)scala.Predef$.MODULE$.Map().apply((Seq)scala.collection.immutable.Nil$.MODULE$)).withDefaultValue(scala.Predef$.MODULE$.Set().apply((Seq)scala.collection.immutable.Nil$.MODULE$));
/*     */   }
/*     */   
/*     */   public <K, V> Map<K, Set<V>> emptyMergeMap() {
/*     */     return (Map)this.internalEmptyMergeMap;
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$3 extends AbstractFunction1<SubclassifiedIndex.Nonroot<K, V>, Seq<Tuple2<K, Set<V>>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object key$4;
/*     */     
/*     */     private final BooleanRef found$1;
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$3(SubclassifiedIndex $outer, Object key$4, BooleanRef found$1) {}
/*     */     
/*     */     public final Seq<Tuple2<K, Set<V>>> apply(SubclassifiedIndex.Nonroot<K, V> n) {
/*  99 */       this.found$1.elem = true;
/* 102 */       this.found$1.elem = true;
/* 103 */       return this.$outer.akka$util$SubclassifiedIndex$$sc.isEqual(this.key$4, n.key()) ? (Seq<Tuple2<K, Set<V>>>)scala.collection.immutable.Nil$.MODULE$ : (this.$outer.akka$util$SubclassifiedIndex$$sc.isSubclass(this.key$4, n.key()) ? n.innerAddKey((K)this.key$4) : 
/* 104 */         (Seq<Tuple2<K, Set<V>>>)scala.collection.immutable.Nil$.MODULE$);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$4 extends AbstractFunction1<SubclassifiedIndex.Nonroot<K, V>, Seq<Tuple2<K, Set<V>>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object key$1;
/*     */     
/*     */     private final Object value$2;
/*     */     
/*     */     private final BooleanRef found$2;
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$4(SubclassifiedIndex $outer, Object key$1, Object value$2, BooleanRef found$2) {}
/*     */     
/*     */     public final Seq<Tuple2<K, Set<V>>> apply(SubclassifiedIndex.Nonroot<K, V> n) {
/* 123 */       this.found$2.elem = true;
/* 124 */       return this.$outer.akka$util$SubclassifiedIndex$$sc.isSubclass(this.key$1, n.key()) ? n.innerAddValue((K)this.key$1, (V)this.value$2) : 
/* 125 */         (Seq<Tuple2<K, Set<V>>>)scala.collection.immutable.Nil$.MODULE$;
/*     */     }
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$removeValue$1 extends AbstractFunction1<Tuple2<K, Set<V>>, Tuple2<K, Set<V>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<K, Set<V>> apply(Tuple2 x0$1) {
/* 143 */       Tuple2 tuple2 = x0$1;
/* 143 */       if (tuple2 != null) {
/* 144 */         Object k = tuple2._1();
/* 144 */         return new Tuple2(k, this.$outer.findValues(k));
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$removeValue$1(SubclassifiedIndex $outer) {}
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$5 extends AbstractFunction1<SubclassifiedIndex.Nonroot<K, V>, Seq<Tuple2<K, Set<V>>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object key$2;
/*     */     
/*     */     private final Object value$4;
/*     */     
/*     */     private final BooleanRef found$3;
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$5(SubclassifiedIndex $outer, Object key$2, Object value$4, BooleanRef found$3) {}
/*     */     
/*     */     public final Seq<Tuple2<K, Set<V>>> apply(SubclassifiedIndex.Nonroot<K, V> n) {
/* 152 */       this.found$3.elem = true;
/* 153 */       return this.$outer.akka$util$SubclassifiedIndex$$sc.isSubclass(this.key$2, n.key()) ? n.innerRemoveValue((K)this.key$2, (V)this.value$4) : 
/* 154 */         (Seq<Tuple2<K, Set<V>>>)scala.collection.immutable.Nil$.MODULE$;
/*     */     }
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$removeValue$2 extends AbstractFunction1<SubclassifiedIndex.Nonroot<K, V>, Seq<Tuple2<K, Set<V>>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object value$1;
/*     */     
/*     */     public final Seq<Tuple2<K, Set<V>>> apply(SubclassifiedIndex.Nonroot<K, V> x$3) {
/* 167 */       return x$3.removeValue((V)this.value$1);
/*     */     }
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$removeValue$2(SubclassifiedIndex $outer, Object value$1) {}
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$innerFindValues$1 extends AbstractFunction2<Set<V>, SubclassifiedIndex.Nonroot<K, V>, Set<V>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object key$3;
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$innerFindValues$1(SubclassifiedIndex $outer, Object key$3) {}
/*     */     
/*     */     public final Set<V> apply(Set<V> s, SubclassifiedIndex.Nonroot n) {
/* 175 */       return this.$outer.akka$util$SubclassifiedIndex$$sc.isSubclass(this.key$3, n.key()) ? 
/* 176 */         (Set<V>)s.$plus$plus((GenTraversableOnce)n.innerFindValues(this.key$3)) : 
/*     */         
/* 178 */         s;
/*     */     }
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$innerFindSubKeys$1 extends AbstractFunction2<Set<K>, SubclassifiedIndex.Nonroot<K, V>, Set<K>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Object key$5;
/*     */     
/*     */     private final Vector except$1;
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$innerFindSubKeys$1(SubclassifiedIndex $outer, Object key$5, Vector except$1) {}
/*     */     
/*     */     public final Set<K> apply(Set<K> s, SubclassifiedIndex.Nonroot n) {
/* 187 */       return this.$outer.akka$util$SubclassifiedIndex$$sc.isEqual(this.key$5, n.key()) ? s : 
/* 188 */         (Set<K>)n.innerFindSubKeys(this.key$5, this.except$1).$plus$plus((
/* 189 */           this.$outer.akka$util$SubclassifiedIndex$$sc.isSubclass(n.key(), this.key$5) && !this.except$1.exists((Function1)new SubclassifiedIndex$$anonfun$innerFindSubKeys$1$$anonfun$apply$1(this))) ? 
/* 190 */           (GenTraversableOnce)s.$plus(n.key()) : 
/*     */           
/* 192 */           (GenTraversableOnce)s);
/*     */     }
/*     */     
/*     */     public class SubclassifiedIndex$$anonfun$innerFindSubKeys$1$$anonfun$apply$1 extends AbstractFunction1<SubclassifiedIndex.Nonroot<K, V>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(SubclassifiedIndex.Nonroot e) {
/*     */         return (this.$outer.akka$util$SubclassifiedIndex$$anonfun$$$outer()).akka$util$SubclassifiedIndex$$sc.isEqual(this.$outer.key$5, e.key());
/*     */       }
/*     */       
/*     */       public SubclassifiedIndex$$anonfun$innerFindSubKeys$1$$anonfun$apply$1(SubclassifiedIndex$$anonfun$innerFindSubKeys$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class $anonfun$6 extends AbstractFunction1<SubclassifiedIndex.Nonroot<K, V>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final SubclassifiedIndex.Nonroot n$1;
/*     */     
/*     */     public final boolean apply(SubclassifiedIndex.Nonroot k) {
/* 203 */       return this.$outer.akka$util$SubclassifiedIndex$$sc.isSubclass(k.key(), this.n$1.key());
/*     */     }
/*     */     
/*     */     public $anonfun$6(SubclassifiedIndex $outer, SubclassifiedIndex.Nonroot n$1) {}
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$integrate$1 extends AbstractFunction1<K, SubclassifiedIndex.Nonroot<K, V>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final SubclassifiedIndex.Nonroot<K, V> apply(Object k) {
/* 206 */       return new SubclassifiedIndex.Nonroot<K, V>(this.$outer.root(), (K)k, this.$outer.values(), this.$outer.akka$util$SubclassifiedIndex$$sc);
/*     */     }
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$integrate$1(SubclassifiedIndex $outer) {}
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$integrate$2 extends AbstractFunction1<SubclassifiedIndex.Nonroot<K, V>, Tuple2<K, Set<V>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<K, Set<V>> apply(SubclassifiedIndex.Nonroot n) {
/* 207 */       return new Tuple2(n.key(), n.values().toSet());
/*     */     }
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$integrate$2(SubclassifiedIndex $outer) {}
/*     */   }
/*     */   
/*     */   public class SubclassifiedIndex$$anonfun$mergeChangesByKey$1 extends AbstractFunction2<Map<K, Set<V>>, Tuple2<K, Set<V>>, Map<K, Set<V>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Map<K, Set<V>> apply(Map x0$2, Tuple2 x1$1) {
/* 211 */       Tuple2 tuple2 = new Tuple2(x0$2, x1$1);
/* 211 */       if (tuple2 != null) {
/* 212 */         Map m = (Map)tuple2._1();
/* 212 */         Tuple2 tuple21 = (Tuple2)tuple2._2();
/*     */         if (tuple21 != null) {
/* 212 */           Object k = tuple21._1();
/* 212 */           Set s = (Set)tuple21._2();
/* 212 */           return m.updated(k, ((SetLike)m.apply(k)).$plus$plus((GenTraversableOnce)s));
/*     */         } 
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public SubclassifiedIndex$$anonfun$mergeChangesByKey$1(SubclassifiedIndex $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\SubclassifiedIndex$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */