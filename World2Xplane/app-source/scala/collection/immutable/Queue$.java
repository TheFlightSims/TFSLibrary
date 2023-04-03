/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SeqFactory;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.ListBuffer;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class Queue$ extends SeqFactory<Queue> implements Serializable {
/*     */   public static final Queue$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/* 137 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Queue$() {
/* 137 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<Queue<?>, A, Queue<A>> canBuildFrom() {
/* 139 */     return (CanBuildFrom<Queue<?>, A, Queue<A>>)ReusableCBF();
/*     */   }
/*     */   
/*     */   public <A> Builder<A, Queue<A>> newBuilder() {
/* 140 */     return Builder.class.mapResult((Builder)new ListBuffer(), (Function1)new Queue$$anonfun$newBuilder$1());
/*     */   }
/*     */   
/*     */   public static class Queue$$anonfun$newBuilder$1 extends AbstractFunction1<List<A>, Queue<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Queue<A> apply(List<A> x) {
/* 140 */       return new Queue<A>(Nil$.MODULE$, x.toList());
/*     */     }
/*     */   }
/*     */   
/*     */   public <A> Queue<A> empty() {
/* 141 */     return Queue.EmptyQueue$.MODULE$;
/*     */   }
/*     */   
/*     */   public <A> Queue<A> apply(Seq xs) {
/* 142 */     return new Queue<A>(Nil$.MODULE$, xs.toList());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Queue$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */