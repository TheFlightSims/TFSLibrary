/*    */ package akka.io;
/*    */ 
/*    */ import akka.actor.ActorSystem;
/*    */ import akka.actor.ExtendedActorSystem;
/*    */ import akka.actor.Extension;
/*    */ import akka.actor.ExtensionId;
/*    */ import akka.actor.ExtensionIdProvider;
/*    */ 
/*    */ public final class UdpConnected$ implements ExtensionId<UdpConnectedExt>, ExtensionIdProvider {
/*    */   public static final UdpConnected$ MODULE$;
/*    */   
/*    */   public Extension apply(ActorSystem system) {
/* 26 */     return ExtensionId.class.apply(this, system);
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/* 26 */     return ExtensionId.class.hashCode(this);
/*    */   }
/*    */   
/*    */   public final boolean equals(Object other) {
/* 26 */     return ExtensionId.class.equals(this, other);
/*    */   }
/*    */   
/*    */   private UdpConnected$() {
/* 26 */     MODULE$ = this;
/* 26 */     ExtensionId.class.$init$(this);
/*    */   }
/*    */   
/*    */   public UdpConnected$ lookup() {
/* 28 */     return this;
/*    */   }
/*    */   
/*    */   public UdpConnectedExt createExtension(ExtendedActorSystem system) {
/* 30 */     return new UdpConnectedExt(system);
/*    */   }
/*    */   
/*    */   public UdpConnectedExt get(ActorSystem system) {
/* 35 */     return (UdpConnectedExt)ExtensionId.class.get(this, system);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\UdpConnected$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */