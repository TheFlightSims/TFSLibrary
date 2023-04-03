/*     */ package akka.pattern;
/*     */ 
/*     */ import scala.Serializable;
/*     */ 
/*     */ public final class CircuitBreakerOpenException$ implements Serializable {
/*     */   public static final CircuitBreakerOpenException$ MODULE$;
/*     */   
/*     */   private CircuitBreakerOpenException$() {
/* 501 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 501 */     return MODULE$;
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$2() {
/* 503 */     return "Circuit Breaker is open; calls are failing fast";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\CircuitBreakerOpenException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */