/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.Address;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class ConsistentRoutee$ extends AbstractFunction2<Routee, Address, ConsistentRoutee> implements Serializable {
/*     */   public static final ConsistentRoutee$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 417 */     return "ConsistentRoutee";
/*     */   }
/*     */   
/*     */   public ConsistentRoutee apply(Routee routee, Address selfAddress) {
/* 417 */     return new ConsistentRoutee(routee, selfAddress);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<Routee, Address>> unapply(ConsistentRoutee x$0) {
/* 417 */     return (x$0 == null) ? (Option<Tuple2<Routee, Address>>)scala.None$.MODULE$ : (Option<Tuple2<Routee, Address>>)new Some(new Tuple2(x$0.routee(), x$0.selfAddress()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 417 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ConsistentRoutee$() {
/* 417 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ConsistentRoutee$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */