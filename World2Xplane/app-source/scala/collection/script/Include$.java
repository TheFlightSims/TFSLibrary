/*    */ package scala.collection.script;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ 
/*    */ public final class Include$ implements Serializable {
/*    */   public static final Include$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 31 */     return "Include";
/*    */   }
/*    */   
/*    */   public <A> Include<A> apply(Location location, Object elem) {
/* 31 */     return new Include<A>(location, (A)elem);
/*    */   }
/*    */   
/*    */   public <A> Option<Tuple2<Location, A>> unapply(Include x$0) {
/* 31 */     return (x$0 == null) ? (Option<Tuple2<Location, A>>)scala.None$.MODULE$ : (Option<Tuple2<Location, A>>)new Some(new Tuple2(x$0.location(), x$0.elem()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 31 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Include$() {
/* 31 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\script\Include$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */