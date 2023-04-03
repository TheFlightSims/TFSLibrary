/*    */ package akka.event;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.ActorSystem;
/*    */ import akka.actor.AddressTerminated;
/*    */ import akka.actor.ExtendedActorSystem;
/*    */ import akka.actor.Extension;
/*    */ import akka.actor.ExtensionId;
/*    */ import akka.actor.ExtensionIdProvider;
/*    */ import scala.Serializable;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public final class AddressTerminatedTopic$ implements ExtensionId<AddressTerminatedTopic>, ExtensionIdProvider {
/*    */   public static final AddressTerminatedTopic$ MODULE$;
/*    */   
/*    */   public Extension apply(ActorSystem system) {
/* 24 */     return ExtensionId.class.apply(this, system);
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/* 24 */     return ExtensionId.class.hashCode(this);
/*    */   }
/*    */   
/*    */   public final boolean equals(Object other) {
/* 24 */     return ExtensionId.class.equals(this, other);
/*    */   }
/*    */   
/*    */   private AddressTerminatedTopic$() {
/* 24 */     MODULE$ = this;
/* 24 */     ExtensionId.class.$init$(this);
/*    */   }
/*    */   
/*    */   public AddressTerminatedTopic get(ActorSystem system) {
/* 25 */     return (AddressTerminatedTopic)ExtensionId.class.get(this, system);
/*    */   }
/*    */   
/*    */   public AddressTerminatedTopic$ lookup() {
/* 27 */     return this;
/*    */   }
/*    */   
/*    */   public AddressTerminatedTopic createExtension(ExtendedActorSystem system) {
/* 30 */     return new AddressTerminatedTopic();
/*    */   }
/*    */   
/*    */   public class AddressTerminatedTopic$$anonfun$publish$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final AddressTerminated msg$1;
/*    */     
/*    */     public final void apply(ActorRef x$1) {
/* 53 */       x$1.tell(this.msg$1, akka.actor.ActorRef$.MODULE$.noSender());
/*    */     }
/*    */     
/*    */     public AddressTerminatedTopic$$anonfun$publish$1(AddressTerminatedTopic $outer, AddressTerminated msg$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\AddressTerminatedTopic$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */