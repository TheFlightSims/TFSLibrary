/*    */ package akka.japi;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ 
/*    */ public final class Pair$ implements Serializable {
/*    */   public static final Pair$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 59 */     return "Pair";
/*    */   }
/*    */   
/*    */   public <A, B> Pair<A, B> apply(Object first, Object second) {
/* 59 */     return new Pair<A, B>((A)first, (B)second);
/*    */   }
/*    */   
/*    */   public <A, B> Option<Tuple2<A, B>> unapply(Pair x$0) {
/* 59 */     return (x$0 == null) ? (Option<Tuple2<A, B>>)scala.None$.MODULE$ : (Option<Tuple2<A, B>>)new Some(new Tuple2(x$0.first(), x$0.second()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 59 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Pair$() {
/* 59 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\Pair$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */