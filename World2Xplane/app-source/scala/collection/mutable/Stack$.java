/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SeqFactory;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class Stack$ extends SeqFactory<Stack> implements Serializable {
/*     */   public static final Stack$ MODULE$;
/*     */   
/*     */   private final Stack<scala.runtime.Nothing$> empty;
/*     */   
/*     */   private Object readResolve() {
/*  25 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Stack$() {
/*  25 */     MODULE$ = this;
/*  35 */     this.empty = new Stack<scala.runtime.Nothing$>((List<scala.runtime.Nothing$>)scala.collection.immutable.Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<Stack<?>, A, Stack<A>> canBuildFrom() {
/*     */     return (CanBuildFrom<Stack<?>, A, Stack<A>>)ReusableCBF();
/*     */   }
/*     */   
/*     */   public <A> Builder<A, Stack<A>> newBuilder() {
/*     */     return new Stack.StackBuilder<A>();
/*     */   }
/*     */   
/*     */   public Stack<scala.runtime.Nothing$> empty() {
/*  35 */     return this.empty;
/*     */   }
/*     */   
/*     */   public class Stack$$anonfun$pushAll$1 extends AbstractFunction1<A, Stack<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Stack<A> apply(Object elem) {
/* 121 */       return this.$outer.push((A)elem);
/*     */     }
/*     */     
/*     */     public Stack$$anonfun$pushAll$1(Stack $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Stack$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */