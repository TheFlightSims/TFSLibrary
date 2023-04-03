/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.generic.CanCombineFrom;
/*     */ import scala.collection.generic.ParMapFactory;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.DefaultEntry;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ParHashMap$ extends ParMapFactory<ParHashMap> implements Serializable {
/*     */   public static final ParHashMap$ MODULE$;
/*     */   
/*     */   private int iters;
/*     */   
/*     */   public class ParHashMap$$anonfun$writeObject$1 extends AbstractFunction1<DefaultEntry<K, V>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectOutputStream out$1;
/*     */     
/*     */     public ParHashMap$$anonfun$writeObject$1(ParHashMap $outer, ObjectOutputStream out$1) {}
/*     */     
/*     */     public final void apply(DefaultEntry entry) {
/* 111 */       this.out$1.writeObject(entry.key());
/* 112 */       this.out$1.writeObject(entry.value());
/*     */     }
/*     */   }
/*     */   
/*     */   public class ParHashMap$$anonfun$readObject$1 extends AbstractFunction0<DefaultEntry<K, V>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectInputStream in$1;
/*     */     
/*     */     public final DefaultEntry<K, V> apply() {
/* 117 */       return this.$outer.createNewEntry((K)this.in$1.readObject(), this.in$1.readObject());
/*     */     }
/*     */     
/*     */     public ParHashMap$$anonfun$readObject$1(ParHashMap $outer, ObjectInputStream in$1) {}
/*     */   }
/*     */   
/*     */   public class ParHashMap$$anonfun$2 extends AbstractFunction1<Object, List<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<String> apply(int i) {
/* 122 */       return this.$outer.scala$collection$parallel$mutable$ParHashMap$$checkBucket(i);
/*     */     }
/*     */     
/*     */     public ParHashMap$$anonfun$2(ParHashMap $outer) {}
/*     */   }
/*     */   
/*     */   public class ParHashMap$$anonfun$3 extends AbstractFunction1<Object, List<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<String> apply(int i) {
/* 125 */       return this.$outer.scala$collection$parallel$mutable$ParHashMap$$checkEntry(i);
/*     */     }
/*     */     
/*     */     public ParHashMap$$anonfun$3(ParHashMap $outer) {}
/*     */   }
/*     */   
/*     */   public class ParHashMap$$anonfun$brokenInvariants$1 extends AbstractFunction1<List<String>, List<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<String> apply(List<String> x) {
/* 127 */       return x;
/*     */     }
/*     */     
/*     */     public ParHashMap$$anonfun$brokenInvariants$1(ParHashMap $outer) {}
/*     */   }
/*     */   
/*     */   public class ParHashMap$$anonfun$brokenInvariants$2 extends AbstractFunction1<List<String>, List<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<String> apply(List<String> x) {
/* 127 */       return x;
/*     */     }
/*     */     
/*     */     public ParHashMap$$anonfun$brokenInvariants$2(ParHashMap $outer) {}
/*     */   }
/*     */   
/*     */   public class ParHashMap$$anonfun$1 extends AbstractFunction2.mcIII.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int acc, int c) {
/* 134 */       return apply$mcIII$sp(acc, c);
/*     */     }
/*     */     
/*     */     public int apply$mcIII$sp(int acc, int c) {
/* 134 */       return acc + this.$outer.scala$collection$parallel$mutable$ParHashMap$$count$1(this.$outer.table()[c]);
/*     */     }
/*     */     
/*     */     public ParHashMap$$anonfun$1(ParHashMap $outer) {}
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 154 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ParHashMap$() {
/* 154 */     MODULE$ = this;
/* 155 */     this.iters = 0;
/*     */   }
/*     */   
/*     */   public int iters() {
/* 155 */     return this.iters;
/*     */   }
/*     */   
/*     */   public void iters_$eq(int x$1) {
/* 155 */     this.iters = x$1;
/*     */   }
/*     */   
/*     */   public <K, V> ParHashMap<K, V> empty() {
/* 157 */     return new ParHashMap<K, V>();
/*     */   }
/*     */   
/*     */   public <K, V> Combiner<Tuple2<K, V>, ParHashMap<K, V>> newCombiner() {
/* 159 */     return (Combiner)ParHashMapCombiner$.MODULE$.apply();
/*     */   }
/*     */   
/*     */   public <K, V> CanCombineFrom<ParHashMap<?, ?>, Tuple2<K, V>, ParHashMap<K, V>> canBuildFrom() {
/* 161 */     return (CanCombineFrom<ParHashMap<?, ?>, Tuple2<K, V>, ParHashMap<K, V>>)new ParMapFactory.CanCombineFromMap(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParHashMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */