/*    */ package akka.event;
/*    */ 
/*    */ import akka.actor.ActorSystem;
/*    */ import akka.util.Subclassification;
/*    */ 
/*    */ public final class EventStream$ {
/*    */   public static final EventStream$ MODULE$;
/*    */   
/*    */   private EventStream$() {
/* 12 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public EventStream fromActorSystem(ActorSystem system) {
/* 14 */     return system.eventStream();
/*    */   }
/*    */   
/*    */   public boolean $lessinit$greater$default$1() {
/* 26 */     return false;
/*    */   }
/*    */   
/*    */   public class $anon$1 implements Subclassification<Class<?>> {
/*    */     public $anon$1(EventStream $outer) {}
/*    */     
/*    */     public boolean isEqual(Class x, Class y) {
/* 32 */       Class clazz = y;
/* 32 */       if (x == null) {
/* 32 */         if (clazz != null);
/* 32 */       } else if (x.equals(clazz)) {
/*    */       
/*    */       } 
/*    */     }
/*    */     
/*    */     public boolean isSubclass(Class<?> x, Class y) {
/* 33 */       return y.isAssignableFrom(x);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\EventStream$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */