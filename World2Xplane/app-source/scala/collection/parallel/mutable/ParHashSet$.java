/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.generic.CanCombineFrom;
/*     */ import scala.collection.generic.ParSetFactory;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ParHashSet$ extends ParSetFactory<ParHashSet> implements Serializable {
/*     */   public static final ParHashSet$ MODULE$;
/*     */   
/*     */   public class ParHashSet$$anonfun$readObject$1 extends AbstractFunction1<T, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Object x) {}
/*     */     
/*     */     public ParHashSet$$anonfun$readObject$1(ParHashSet $outer) {}
/*     */   }
/*     */   
/*     */   public class ParHashSet$$anonfun$debugInformation$1 extends AbstractFunction1<Function1<Object, BoxedUnit>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public ParHashSet$$anonfun$debugInformation$1(ParHashSet $outer) {}
/*     */     
/*     */     public final void apply(Function1 append) {
/*  94 */       append.apply("Parallel flat hash table set");
/*  95 */       append.apply((new StringBuilder()).append("No. elems: ").append(BoxesRunTime.boxToInteger(this.$outer.tableSize())).toString());
/*  96 */       append.apply((new StringBuilder()).append("Table length: ").append(BoxesRunTime.boxToInteger((this.$outer.table()).length)).toString());
/*  97 */       append.apply("Table: ");
/*  98 */       append.apply(scala.collection.DebugUtils$.MODULE$.arrayString(this.$outer.table(), 0, (this.$outer.table()).length));
/*  99 */       append.apply("Sizemap: ");
/* 100 */       append.apply(scala.collection.DebugUtils$.MODULE$.arrayString(this.$outer.sizemap(), 0, (this.$outer.sizemap()).length));
/*     */     }
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 110 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ParHashSet$() {
/* 110 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <T> CanCombineFrom<ParHashSet<?>, T, ParHashSet<T>> canBuildFrom() {
/* 111 */     return (CanCombineFrom<ParHashSet<?>, T, ParHashSet<T>>)new ParSetFactory.GenericCanCombineFrom(this);
/*     */   }
/*     */   
/*     */   public <T> Combiner<T, ParHashSet<T>> newBuilder() {
/* 113 */     return newCombiner();
/*     */   }
/*     */   
/*     */   public <T> Combiner<T, ParHashSet<T>> newCombiner() {
/* 115 */     return (Combiner)ParHashSetCombiner$.MODULE$.apply();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParHashSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */