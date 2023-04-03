/*    */ package scala.collection.script;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ 
/*    */ public final class Update$ implements Serializable {
/*    */   public static final Update$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 41 */     return "Update";
/*    */   }
/*    */   
/*    */   public <A> Update<A> apply(Location location, Object elem) {
/* 41 */     return new Update<A>(location, (A)elem);
/*    */   }
/*    */   
/*    */   public <A> Option<Tuple2<Location, A>> unapply(Update x$0) {
/* 41 */     return (x$0 == null) ? (Option<Tuple2<Location, A>>)scala.None$.MODULE$ : (Option<Tuple2<Location, A>>)new Some(new Tuple2(x$0.location(), x$0.elem()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 41 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Update$() {
/* 41 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\script\Update$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */