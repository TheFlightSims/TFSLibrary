/*     */ package akka.pattern;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSelection;
/*     */ import akka.util.Timeout;
/*     */ import scala.concurrent.Future;
/*     */ 
/*     */ public abstract class AskSupport$class {
/*     */   public static void $init$(AskSupport $this) {}
/*     */   
/*     */   public static ActorRef ask(AskSupport $this, ActorRef actorRef) {
/*  46 */     return actorRef;
/*     */   }
/*     */   
/*     */   public static Future ask(AskSupport $this, ActorRef actorRef, Object message, Timeout timeout) {
/*  75 */     return AskableActorRef$.MODULE$.$qmark$extension($this.ask(actorRef), message, timeout);
/*     */   }
/*     */   
/*     */   public static ActorSelection ask(AskSupport $this, ActorSelection actorSelection) {
/*  92 */     return actorSelection;
/*     */   }
/*     */   
/*     */   public static Future ask(AskSupport $this, ActorSelection actorSelection, Object message, Timeout timeout) {
/* 121 */     return AskableActorSelection$.MODULE$.$qmark$extension($this.ask(actorSelection), message, timeout);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\AskSupport$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */