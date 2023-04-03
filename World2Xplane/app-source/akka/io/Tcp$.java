/*    */ package akka.io;
/*    */ 
/*    */ import akka.actor.ActorSystem;
/*    */ import akka.actor.ExtendedActorSystem;
/*    */ import akka.actor.Extension;
/*    */ import akka.actor.ExtensionId;
/*    */ import akka.actor.ExtensionIdProvider;
/*    */ 
/*    */ public final class Tcp$ implements ExtensionId<TcpExt>, ExtensionIdProvider {
/*    */   public static final Tcp$ MODULE$;
/*    */   
/*    */   public Extension apply(ActorSystem system) {
/* 33 */     return ExtensionId.class.apply(this, system);
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/* 33 */     return ExtensionId.class.hashCode(this);
/*    */   }
/*    */   
/*    */   public final boolean equals(Object other) {
/* 33 */     return ExtensionId.class.equals(this, other);
/*    */   }
/*    */   
/*    */   private Tcp$() {
/* 33 */     MODULE$ = this;
/* 33 */     ExtensionId.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Tcp$ lookup() {
/* 35 */     return this;
/*    */   }
/*    */   
/*    */   public TcpExt createExtension(ExtendedActorSystem system) {
/* 37 */     return new TcpExt(system);
/*    */   }
/*    */   
/*    */   public TcpExt get(ActorSystem system) {
/* 42 */     return (TcpExt)ExtensionId.class.get(this, system);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\Tcp$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */