/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class AddRoutee$ extends AbstractFunction1<Routee, AddRoutee> implements Serializable {
/*     */   public static final AddRoutee$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 394 */     return "AddRoutee";
/*     */   }
/*     */   
/*     */   public AddRoutee apply(Routee routee) {
/* 394 */     return new AddRoutee(routee);
/*     */   }
/*     */   
/*     */   public Option<Routee> unapply(AddRoutee x$0) {
/* 394 */     return (x$0 == null) ? (Option<Routee>)scala.None$.MODULE$ : (Option<Routee>)new Some(x$0.routee());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 394 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private AddRoutee$() {
/* 394 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\AddRoutee$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */