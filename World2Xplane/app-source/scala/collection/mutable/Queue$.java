/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SeqFactory;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class Queue$ extends SeqFactory<Queue> implements Serializable {
/*     */   public static final Queue$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/* 198 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Queue$() {
/* 198 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<Queue<?>, A, Queue<A>> canBuildFrom() {
/* 199 */     return (CanBuildFrom<Queue<?>, A, Queue<A>>)ReusableCBF();
/*     */   }
/*     */   
/*     */   public <A> Builder<A, Queue<A>> newBuilder() {
/* 201 */     return (new MutableList<A>()).mapResult((Function1<MutableList<A>, Queue<A>>)new Queue$$anonfun$newBuilder$1());
/*     */   }
/*     */   
/*     */   public static class Queue$$anonfun$newBuilder$1 extends AbstractFunction1<MutableList<A>, Queue<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Queue<A> apply(MutableList<A> x$1) {
/* 201 */       return x$1.toQueue();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Queue$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */