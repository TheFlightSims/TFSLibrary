/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class RemoveRoutee$ extends AbstractFunction1<Routee, RemoveRoutee> implements Serializable {
/*     */   public static final RemoveRoutee$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 406 */     return "RemoveRoutee";
/*     */   }
/*     */   
/*     */   public RemoveRoutee apply(Routee routee) {
/* 406 */     return new RemoveRoutee(routee);
/*     */   }
/*     */   
/*     */   public Option<Routee> unapply(RemoveRoutee x$0) {
/* 406 */     return (x$0 == null) ? (Option<Routee>)scala.None$.MODULE$ : (Option<Routee>)new Some(x$0.routee());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 406 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private RemoveRoutee$() {
/* 406 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RemoveRoutee$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */