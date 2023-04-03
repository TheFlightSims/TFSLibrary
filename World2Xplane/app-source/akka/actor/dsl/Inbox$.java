/*    */ package akka.actor.dsl;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.Some;
/*    */ import scala.concurrent.duration.Duration;
/*    */ import scala.math.Ordering;
/*    */ import scala.math.PartialOrdering;
/*    */ 
/*    */ public final class Inbox$ {
/*    */   public static final Inbox$ MODULE$;
/*    */   
/*    */   private Inbox$() {
/* 27 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public class $anon$1 implements Ordering<Inbox.Query> {
/*    */     public Some<Object> tryCompare(Object x, Object y) {
/* 58 */       return Ordering.class.tryCompare(this, x, y);
/*    */     }
/*    */     
/*    */     public boolean lteq(Object x, Object y) {
/* 58 */       return Ordering.class.lteq(this, x, y);
/*    */     }
/*    */     
/*    */     public boolean gteq(Object x, Object y) {
/* 58 */       return Ordering.class.gteq(this, x, y);
/*    */     }
/*    */     
/*    */     public boolean lt(Object x, Object y) {
/* 58 */       return Ordering.class.lt(this, x, y);
/*    */     }
/*    */     
/*    */     public boolean gt(Object x, Object y) {
/* 58 */       return Ordering.class.gt(this, x, y);
/*    */     }
/*    */     
/*    */     public boolean equiv(Object x, Object y) {
/* 58 */       return Ordering.class.equiv(this, x, y);
/*    */     }
/*    */     
/*    */     public Object max(Object x, Object y) {
/* 58 */       return Ordering.class.max(this, x, y);
/*    */     }
/*    */     
/*    */     public Object min(Object x, Object y) {
/* 58 */       return Ordering.class.min(this, x, y);
/*    */     }
/*    */     
/*    */     public Ordering<Inbox.Query> reverse() {
/* 58 */       return Ordering.class.reverse(this);
/*    */     }
/*    */     
/*    */     public <U> Ordering<U> on(Function1 f) {
/* 58 */       return Ordering.class.on(this, f);
/*    */     }
/*    */     
/*    */     public Ordering<Inbox.Query>.Ops mkOrderingOps(Object lhs) {
/* 58 */       return Ordering.class.mkOrderingOps(this, lhs);
/*    */     }
/*    */     
/*    */     public $anon$1(akka.actor.ActorDSL$ $outer) {
/* 58 */       PartialOrdering.class.$init$((PartialOrdering)this);
/* 58 */       Ordering.class.$init$(this);
/*    */     }
/*    */     
/*    */     public int compare(Inbox.Query left, Inbox.Query right) {
/* 59 */       return left.deadline().time().compare((Duration)right.deadline().time());
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dsl\Inbox$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */