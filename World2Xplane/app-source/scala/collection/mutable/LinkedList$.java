/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SeqFactory;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class LinkedList$ extends SeqFactory<LinkedList> implements Serializable {
/*     */   public static final LinkedList$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/* 115 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private LinkedList$() {
/* 115 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> LinkedList<A> empty() {
/* 116 */     return new LinkedList<A>();
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<LinkedList<?>, A, LinkedList<A>> canBuildFrom() {
/* 117 */     return (CanBuildFrom<LinkedList<?>, A, LinkedList<A>>)ReusableCBF();
/*     */   }
/*     */   
/*     */   public <A> Builder<A, LinkedList<A>> newBuilder() {
/* 120 */     return (new MutableList<A>()).mapResult((Function1<MutableList<A>, LinkedList<A>>)new LinkedList$$anonfun$newBuilder$1());
/*     */   }
/*     */   
/*     */   public static class LinkedList$$anonfun$newBuilder$1 extends AbstractFunction1<MutableList<A>, LinkedList<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final LinkedList<A> apply(MutableList<A> l) {
/* 120 */       return l.toLinkedList();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\LinkedList$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */