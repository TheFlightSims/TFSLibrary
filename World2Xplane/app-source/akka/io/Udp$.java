/*    */ package akka.io;
/*    */ 
/*    */ import akka.actor.ActorSystem;
/*    */ import akka.actor.ExtendedActorSystem;
/*    */ import akka.actor.Extension;
/*    */ import akka.actor.ExtensionId;
/*    */ import akka.actor.ExtensionIdProvider;
/*    */ 
/*    */ public final class Udp$ implements ExtensionId<UdpExt>, ExtensionIdProvider {
/*    */   public static final Udp$ MODULE$;
/*    */   
/*    */   public Extension apply(ActorSystem system) {
/* 27 */     return ExtensionId.class.apply(this, system);
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/* 27 */     return ExtensionId.class.hashCode(this);
/*    */   }
/*    */   
/*    */   public final boolean equals(Object other) {
/* 27 */     return ExtensionId.class.equals(this, other);
/*    */   }
/*    */   
/*    */   private Udp$() {
/* 27 */     MODULE$ = this;
/* 27 */     ExtensionId.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Udp$ lookup() {
/* 29 */     return this;
/*    */   }
/*    */   
/*    */   public UdpExt createExtension(ExtendedActorSystem system) {
/* 31 */     return new UdpExt(system);
/*    */   }
/*    */   
/*    */   public UdpExt get(ActorSystem system) {
/* 36 */     return (UdpExt)ExtensionId.class.get(this, system);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\Udp$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */