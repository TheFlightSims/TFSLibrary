/*     */ package akka.actor;
/*     */ 
/*     */ import akka.event.Logging$;
/*     */ import scala.PartialFunction;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public abstract class DiagnosticActorLogging$class {
/*     */   public static void $init$(DiagnosticActorLogging $this) {
/* 312 */     $this.akka$actor$DiagnosticActorLogging$_setter_$log_$eq(Logging$.MODULE$.apply($this));
/*     */   }
/*     */   
/*     */   public static Map mdc(DiagnosticActorLogging $this, Object currentMessage) {
/* 313 */     return Logging$.MODULE$.emptyMDC();
/*     */   }
/*     */   
/*     */   public static void aroundReceive(DiagnosticActorLogging $this, PartialFunction<Object, BoxedUnit> receive, Object msg) {
/*     */     try {
/* 316 */       $this.log().mdc($this.mdc(msg));
/* 317 */       $this.akka$actor$DiagnosticActorLogging$$super$aroundReceive(receive, msg);
/*     */       return;
/*     */     } finally {
/* 319 */       $this.log().clearMDC();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\DiagnosticActorLogging$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */