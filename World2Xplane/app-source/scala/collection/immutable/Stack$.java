/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.SeqFactory;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ 
/*    */ public final class Stack$ extends SeqFactory<Stack> implements Serializable {
/*    */   public static final Stack$ MODULE$;
/*    */   
/*    */   private Object readResolve() {
/* 19 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Stack$() {
/* 19 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<Stack<?>, A, Stack<A>> canBuildFrom() {
/* 21 */     return (CanBuildFrom<Stack<?>, A, Stack<A>>)ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, Stack<A>> newBuilder() {
/* 22 */     return (new ArrayBuffer()).mapResult((Function1)new Stack$$anonfun$newBuilder$1());
/*    */   }
/*    */   
/*    */   public static class Stack$$anonfun$newBuilder$1 extends AbstractFunction1<ArrayBuffer<A>, Stack<A>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Stack<A> apply(ArrayBuffer buf) {
/* 22 */       return new Stack<A>(buf.toList());
/*    */     }
/*    */   }
/*    */   
/*    */   public class Stack$$anonfun$pushAll$1 extends AbstractFunction2<Stack<B>, B, Stack<B>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Stack<B> apply(Stack x$3, Object x$4) {
/* 91 */       return x$3.push(x$4);
/*    */     }
/*    */     
/*    */     public Stack$$anonfun$pushAll$1(Stack $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Stack$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */