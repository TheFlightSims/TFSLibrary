/*    */ package akka.serialization;
/*    */ 
/*    */ import akka.actor.ActorSystem;
/*    */ import akka.actor.ExtendedActorSystem;
/*    */ import akka.actor.Extension;
/*    */ import akka.actor.ExtensionId;
/*    */ import akka.actor.ExtensionIdProvider;
/*    */ 
/*    */ public final class SerializationExtension$ implements ExtensionId<Serialization>, ExtensionIdProvider {
/*    */   public static final SerializationExtension$ MODULE$;
/*    */   
/*    */   public Extension apply(ActorSystem system) {
/* 12 */     return ExtensionId.class.apply(this, system);
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/* 12 */     return ExtensionId.class.hashCode(this);
/*    */   }
/*    */   
/*    */   public final boolean equals(Object other) {
/* 12 */     return ExtensionId.class.equals(this, other);
/*    */   }
/*    */   
/*    */   private SerializationExtension$() {
/* 12 */     MODULE$ = this;
/* 12 */     ExtensionId.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Serialization get(ActorSystem system) {
/* 13 */     return (Serialization)ExtensionId.class.get(this, system);
/*    */   }
/*    */   
/*    */   public SerializationExtension$ lookup() {
/* 14 */     return this;
/*    */   }
/*    */   
/*    */   public Serialization createExtension(ExtendedActorSystem system) {
/* 15 */     return new Serialization(system);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\serialization\SerializationExtension$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */