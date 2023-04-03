/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction;
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractPartialFunction;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001I3\001\"\001\002\021\002\007\005qA\023\002\n\031&\034H/\0328feNT!a\001\003\002\017I|W\017^5oO*\tQ!\001\003bW.\f7\001A\n\003\001!\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007\"B\b\001\t\003\001\022A\002\023j]&$H\005F\001\022!\tI!#\003\002\024\025\t!QK\\5u\021\035)\002A1A\005\022Y\t\021\002\\5ti\026tWM]:\026\003]\0012\001G\017 \033\005I\"B\001\016\034\003\021)H/\0337\013\003q\tAA[1wC&\021a$\007\002\004'\026$\bC\001\021$\033\005\t#B\001\022\005\003\025\t7\r^8s\023\t!\023E\001\005BGR|'OU3g\021\0311\003\001)A\005/\005QA.[:uK:,'o\035\021\t\013!\002A\021C\025\002%1L7\017^3oKJl\025M\\1hK6,g\016^\013\002UA\0211F\f\b\003A1J!!L\021\002\013\005\033Go\034:\n\005=\002$a\002*fG\026Lg/\032\006\003[\005BQA\r\001\005\022M\naaZ8tg&\004HC\001\0338)\t\tR\007C\0047cA\005\t9A\020\002\rM,g\016Z3s\021\025A\024\0071\001:\003\ri7o\032\t\003\023iJ!a\017\006\003\007\005s\027\020C\004>\001E\005I\021\003 \002!\035|7o]5qI\021,g-Y;mi\022\022DCA JU\ty\002iK\001B!\t\021u)D\001D\025\t!U)A\005v]\016DWmY6fI*\021aIC\001\013C:tw\016^1uS>t\027B\001%D\005E)hn\0315fG.,GMV1sS\006t7-\032\005\006qq\002\r!\017\n\004\0276{e\001\002'\001\001)\023A\002\020:fM&tW-\\3oiz\002\"A\024\001\016\003\t\001\"\001\t)\n\005E\013#!B!di>\024\b")
/*    */ public interface Listeners {
/*    */   void akka$routing$Listeners$_setter_$listeners_$eq(Set paramSet);
/*    */   
/*    */   Set<ActorRef> listeners();
/*    */   
/*    */   PartialFunction<Object, BoxedUnit> listenerManagement();
/*    */   
/*    */   void gossip(Object paramObject, ActorRef paramActorRef);
/*    */   
/*    */   ActorRef gossip$default$2(Object paramObject);
/*    */   
/*    */   public class Listeners$$anonfun$listenerManagement$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/* 34 */       Object object2, object1 = x1;
/* 35 */       if (object1 instanceof Listen) {
/* 35 */         Listen listen = (Listen)object1;
/* 35 */         ActorRef l = listen.listener();
/* 35 */         this.$outer.listeners().add(l);
/* 35 */         object2 = BoxedUnit.UNIT;
/* 36 */       } else if (object1 instanceof Deafen) {
/* 36 */         Deafen deafen = (Deafen)object1;
/* 36 */         ActorRef l = deafen.listener();
/* 36 */         this.$outer.listeners().remove(l);
/* 36 */         object2 = BoxedUnit.UNIT;
/* 37 */       } else if (object1 instanceof WithListeners) {
/* 37 */         WithListeners withListeners = (WithListeners)object1;
/* 37 */         Function1<ActorRef, BoxedUnit> f = withListeners.f();
/* 38 */         Iterator<ActorRef> i = this.$outer.listeners().iterator();
/* 39 */         for (; i.hasNext(); f.apply(i.next()));
/* 39 */         object2 = BoxedUnit.UNIT;
/*    */       } else {
/*    */         object2 = default.apply(x1);
/*    */       } 
/*    */       return (B1)object2;
/*    */     }
/*    */     
/*    */     public final boolean isDefinedAt(Object x1) {
/*    */       boolean bool;
/*    */       Object object = x1;
/*    */       if (object instanceof Listen) {
/*    */         bool = true;
/*    */       } else if (object instanceof Deafen) {
/*    */         bool = true;
/*    */       } else if (object instanceof WithListeners) {
/*    */         bool = true;
/*    */       } else {
/*    */         bool = false;
/*    */       } 
/*    */       return bool;
/*    */     }
/*    */     
/*    */     public Listeners$$anonfun$listenerManagement$1(Listeners $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Listeners.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */