/*    */ package akka.io;
/*    */ 
/*    */ import akka.actor.Actor;
/*    */ import akka.actor.ActorLogging;
/*    */ import akka.actor.package$;
/*    */ import java.nio.ByteBuffer;
/*    */ import scala.PartialFunction;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public abstract class WithUdpSend$class {
/*    */   public static void $init$(WithUdpSend $this) {
/* 17 */     null;
/* 17 */     $this.akka$io$WithUdpSend$$pendingSend_$eq(null);
/* 18 */     null;
/* 18 */     $this.akka$io$WithUdpSend$$pendingCommander_$eq(null);
/* 21 */     $this.akka$io$WithUdpSend$$retriedSend_$eq(false);
/* 26 */     $this.akka$io$WithUdpSend$_setter_$settings_$eq($this.udp().settings());
/*    */   }
/*    */   
/*    */   public static boolean akka$io$WithUdpSend$$hasWritePending(WithUdpSend $this) {
/*    */     return ($this.akka$io$WithUdpSend$$pendingSend() != null);
/*    */   }
/*    */   
/*    */   public static PartialFunction sendHandlers(WithUdpSend $this, ChannelRegistration registration) {
/* 30 */     return (PartialFunction)new WithUdpSend$$anonfun$sendHandlers$1($this, registration);
/*    */   }
/*    */   
/*    */   public static void akka$io$WithUdpSend$$doSend(WithUdpSend $this, ChannelRegistration registration) {
/* 48 */     ByteBuffer buffer = $this.udp().bufferPool().acquire();
/*    */     try {
/* 50 */       buffer.clear();
/* 51 */       $this.akka$io$WithUdpSend$$pendingSend().payload().copyToBuffer(buffer);
/* 52 */       buffer.flip();
/* 53 */       int writtenBytes = $this.channel().send(buffer, $this.akka$io$WithUdpSend$$pendingSend().target());
/* 54 */       if ($this.settings().TraceLogging())
/* 54 */         ((ActorLogging)$this).log().debug("Wrote [{}] bytes to channel", BoxesRunTime.boxToInteger(writtenBytes)); 
/* 57 */       if (writtenBytes == 0) {
/* 58 */         if ($this.akka$io$WithUdpSend$$retriedSend()) {
/* 59 */           package$.MODULE$.actorRef2Scala($this.akka$io$WithUdpSend$$pendingCommander()).$bang(new Udp.CommandFailed($this.akka$io$WithUdpSend$$pendingSend()), ((Actor)$this).self());
/* 60 */           $this.akka$io$WithUdpSend$$retriedSend_$eq(false);
/* 61 */           null;
/* 61 */           $this.akka$io$WithUdpSend$$pendingSend_$eq(null);
/* 62 */           null;
/* 62 */           $this.akka$io$WithUdpSend$$pendingCommander_$eq(null);
/*    */         } else {
/* 64 */           registration.enableInterest(4);
/* 65 */           $this.akka$io$WithUdpSend$$retriedSend_$eq(true);
/*    */         } 
/*    */       } else {
/* 68 */         if ($this.akka$io$WithUdpSend$$pendingSend().wantsAck())
/* 68 */           package$.MODULE$.actorRef2Scala($this.akka$io$WithUdpSend$$pendingCommander()).$bang($this.akka$io$WithUdpSend$$pendingSend().ack(), ((Actor)$this).self()); 
/* 69 */         $this.akka$io$WithUdpSend$$retriedSend_$eq(false);
/* 70 */         null;
/* 70 */         $this.akka$io$WithUdpSend$$pendingSend_$eq(null);
/* 71 */         null;
/* 71 */         $this.akka$io$WithUdpSend$$pendingCommander_$eq(null);
/*    */       } 
/*    */       return;
/*    */     } finally {
/* 74 */       $this.udp().bufferPool().release(buffer);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\WithUdpSend$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */