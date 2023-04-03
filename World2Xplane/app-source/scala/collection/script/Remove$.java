/*    */ package scala.collection.script;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ 
/*    */ public final class Remove$ implements Serializable {
/*    */   public static final Remove$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 51 */     return "Remove";
/*    */   }
/*    */   
/*    */   public <A> Remove<A> apply(Location location, Object elem) {
/* 51 */     return new Remove<A>(location, (A)elem);
/*    */   }
/*    */   
/*    */   public <A> Option<Tuple2<Location, A>> unapply(Remove x$0) {
/* 51 */     return (x$0 == null) ? (Option<Tuple2<Location, A>>)scala.None$.MODULE$ : (Option<Tuple2<Location, A>>)new Some(new Tuple2(x$0.location(), x$0.elem()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 51 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Remove$() {
/* 51 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\script\Remove$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */