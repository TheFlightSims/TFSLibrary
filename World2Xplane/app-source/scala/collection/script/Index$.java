/*    */ package scala.collection.script;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class Index$ extends AbstractFunction1<Object, Index> implements Serializable {
/*    */   public static final Index$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 24 */     return "Index";
/*    */   }
/*    */   
/*    */   public Index apply(int n) {
/* 24 */     return new Index(n);
/*    */   }
/*    */   
/*    */   public Option<Object> unapply(Index x$0) {
/* 24 */     return (x$0 == null) ? (Option<Object>)scala.None$.MODULE$ : (Option<Object>)new Some(BoxesRunTime.boxToInteger(x$0.n()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 24 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Index$() {
/* 24 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\script\Index$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */