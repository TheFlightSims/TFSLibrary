/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.generic.CanCombineFrom;
/*     */ import scala.collection.generic.ParFactory;
/*     */ import scala.collection.mutable.ArraySeq;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.ParIterableLike;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class ParArray$ extends ParFactory<ParArray> implements Serializable {
/*     */   public static final ParArray$ MODULE$;
/*     */   
/*     */   public class ParArray$$anonfun$scan$1 extends AbstractFunction1<ParIterableLike<T, ParArray<T>, ArraySeq<T>>.ScanTree<U>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object z$1;
/*     */     
/*     */     private final Function2 op$1;
/*     */     
/*     */     private final Object[] targetarr$1;
/*     */     
/*     */     public final void apply(ParIterableLike.ScanTree tree) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield $outer : Lscala/collection/parallel/mutable/ParArray;
/*     */       //   4: invokevirtual tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*     */       //   7: new scala/collection/parallel/mutable/ParArray$ScanToArray
/*     */       //   10: dup
/*     */       //   11: aload_0
/*     */       //   12: getfield $outer : Lscala/collection/parallel/mutable/ParArray;
/*     */       //   15: aload_1
/*     */       //   16: aload_0
/*     */       //   17: getfield z$1 : Ljava/lang/Object;
/*     */       //   20: aload_0
/*     */       //   21: getfield op$1 : Lscala/Function2;
/*     */       //   24: aload_0
/*     */       //   25: getfield targetarr$1 : [Ljava/lang/Object;
/*     */       //   28: invokespecial <init> : (Lscala/collection/parallel/mutable/ParArray;Lscala/collection/parallel/ParIterableLike$ScanTree;Ljava/lang/Object;Lscala/Function2;[Ljava/lang/Object;)V
/*     */       //   31: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*     */       //   36: pop
/*     */       //   37: return
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #604	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	38	0	this	Lscala/collection/parallel/mutable/ParArray$$anonfun$scan$1;
/*     */       //   0	38	1	tree	Lscala/collection/parallel/ParIterableLike$ScanTree;
/*     */     }
/*     */     
/*     */     public ParArray$$anonfun$scan$1(ParArray $outer, Object z$1, Function2 op$1, Object[] targetarr$1) {}
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 689 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ParArray$() {
/* 689 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <T> CanCombineFrom<ParArray<?>, T, ParArray<T>> canBuildFrom() {
/* 690 */     return (CanCombineFrom<ParArray<?>, T, ParArray<T>>)new ParFactory.GenericCanCombineFrom(this);
/*     */   }
/*     */   
/*     */   public <T> Combiner<T, ParArray<T>> newBuilder() {
/* 691 */     return newCombiner();
/*     */   }
/*     */   
/*     */   public <T> Combiner<T, ParArray<T>> newCombiner() {
/* 692 */     return package$.MODULE$.ParArrayCombiner().apply();
/*     */   }
/*     */   
/*     */   public <T> ParArray<T> handoff(Object arr) {
/* 696 */     return wrapOrRebuild(arr, scala.runtime.ScalaRunTime$.MODULE$.array_length(arr));
/*     */   }
/*     */   
/*     */   public <T> ParArray<T> handoff(Object arr, int sz) {
/* 700 */     return wrapOrRebuild(arr, sz);
/*     */   }
/*     */   
/*     */   private <T> ParArray<T> wrapOrRebuild(Object arr, int sz) {
/*     */     ParArray<T> parArray;
/* 702 */     if (arr instanceof Object[]) {
/* 702 */       Object[] arrayOfObject = (Object[])arr;
/* 702 */       parArray = new ParArray(new ExposedArraySeq(arrayOfObject, sz));
/*     */     } else {
/* 704 */       parArray = new ParArray(new ExposedArraySeq(scala.runtime.ScalaRunTime$.MODULE$.toObjectArray(arr), sz));
/*     */     } 
/*     */     return parArray;
/*     */   }
/*     */   
/*     */   public <T> ParArray<T> createFromCopy(Object[] arr, ClassTag evidence$1) {
/* 708 */     Object[] newarr = (Object[])evidence$1.newArray(arr.length);
/* 709 */     scala.Array$.MODULE$.copy(arr, 0, newarr, 0, arr.length);
/* 710 */     return handoff(newarr);
/*     */   }
/*     */   
/*     */   public <T> ParArray<T> fromTraversables(Seq xss) {
/* 714 */     ResizableParArrayCombiner<?> cb = package$.MODULE$.ParArrayCombiner().apply();
/* 715 */     xss.foreach((Function1)new ParArray$$anonfun$fromTraversables$1(cb));
/* 718 */     return (ParArray)cb.result();
/*     */   }
/*     */   
/*     */   public static class ParArray$$anonfun$fromTraversables$1 extends AbstractFunction1<GenTraversableOnce<T>, ResizableParArrayCombiner<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ResizableParArrayCombiner cb$1;
/*     */     
/*     */     public ParArray$$anonfun$fromTraversables$1(ResizableParArrayCombiner cb$1) {}
/*     */     
/*     */     public final ResizableParArrayCombiner<T> apply(GenTraversableOnce xs) {
/*     */       return (ResizableParArrayCombiner<T>)this.cb$1.$plus$plus$eq(xs.seq());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParArray$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */