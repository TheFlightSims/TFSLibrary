/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class Broadcast$ extends AbstractFunction1<Object, Broadcast> implements Serializable {
/*     */   public static final Broadcast$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 178 */     return "Broadcast";
/*     */   }
/*     */   
/*     */   public Broadcast apply(Object message) {
/* 178 */     return new Broadcast(message);
/*     */   }
/*     */   
/*     */   public Option<Object> unapply(Broadcast x$0) {
/* 178 */     return (x$0 == null) ? (Option<Object>)scala.None$.MODULE$ : (Option<Object>)new Some(x$0.message());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 178 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Broadcast$() {
/* 178 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Broadcast$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */