/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.Actor$;
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.package$;
/*    */ import java.util.Iterator;
/*    */ import java.util.TreeSet;
/*    */ import scala.PartialFunction;
/*    */ 
/*    */ public abstract class Listeners$class {
/*    */   public static void $init$(Listeners $this) {
/* 27 */     $this.akka$routing$Listeners$_setter_$listeners_$eq(new TreeSet());
/*    */   }
/*    */   
/*    */   public static PartialFunction listenerManagement(Listeners $this) {
/* 34 */     return (PartialFunction)new Listeners$$anonfun$listenerManagement$1($this);
/*    */   }
/*    */   
/*    */   public static ActorRef gossip$default$2(Listeners $this, Object msg) {
/* 48 */     return Actor$.MODULE$.noSender();
/*    */   }
/*    */   
/*    */   public static void gossip(Listeners $this, Object msg, ActorRef sender) {
/* 49 */     Iterator<ActorRef> i = $this.listeners().iterator();
/* 50 */     for (; i.hasNext(); package$.MODULE$.actorRef2Scala(i.next()).$bang(msg, sender));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Listeners$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */