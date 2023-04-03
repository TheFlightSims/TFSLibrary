/*     */ package akka.actor.dsl;
/*     */ 
/*     */ import akka.actor.ActorDSL$;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import scala.concurrent.duration.package;
/*     */ import scala.concurrent.duration.package$;
/*     */ 
/*     */ public abstract class Inbox$class {
/*     */   public static void $init$(ActorDSL$ $this) {
/*  58 */     $this.akka$actor$dsl$Inbox$_setter_$akka$actor$dsl$Inbox$$deadlineOrder_$eq(new Inbox.$anon$1($this));
/* 156 */     $this.akka$actor$dsl$Inbox$_setter_$akka$actor$dsl$Inbox$$extraTime_$eq((new package.DurationInt(package$.MODULE$.DurationInt(1))).minute());
/*     */   }
/*     */   
/*     */   public static Inbox.Inbox inbox(ActorDSL$ $this, ActorSystem system) {
/* 165 */     return new Inbox.Inbox($this, system);
/*     */   }
/*     */   
/*     */   public static ActorRef senderFromInbox(ActorDSL$ $this, Inbox.Inbox inbox) {
/* 224 */     return inbox.receiver();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dsl\Inbox$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */