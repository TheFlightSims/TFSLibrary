/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public final class SeveralRoutees$ extends AbstractFunction1<IndexedSeq<Routee>, SeveralRoutees> implements Serializable {
/*    */   public static final SeveralRoutees$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 68 */     return "SeveralRoutees";
/*    */   }
/*    */   
/*    */   public SeveralRoutees apply(IndexedSeq<Routee> routees) {
/* 68 */     return new SeveralRoutees(routees);
/*    */   }
/*    */   
/*    */   public Option<IndexedSeq<Routee>> unapply(SeveralRoutees x$0) {
/* 68 */     return (x$0 == null) ? (Option<IndexedSeq<Routee>>)scala.None$.MODULE$ : (Option<IndexedSeq<Routee>>)new Some(x$0.routees());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 68 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private SeveralRoutees$() {
/* 68 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public class SeveralRoutees$$anonfun$send$1 extends AbstractFunction1<Routee, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object message$1;
/*    */     
/*    */     private final ActorRef sender$1;
/*    */     
/*    */     public final void apply(Routee x$1) {
/* 84 */       x$1.send(this.message$1, this.sender$1);
/*    */     }
/*    */     
/*    */     public SeveralRoutees$$anonfun$send$1(SeveralRoutees $outer, Object message$1, ActorRef sender$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\SeveralRoutees$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */