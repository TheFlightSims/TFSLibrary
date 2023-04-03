/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorPath;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ 
/*    */ public abstract class RouterConfig$class {
/*    */   public static void $init$(RouterConfig $this) {}
/*    */   
/*    */   public static Option routingLogicController(RouterConfig $this, RoutingLogic routingLogic) {
/* 60 */     return (Option)None$.MODULE$;
/*    */   }
/*    */   
/*    */   public static boolean isManagementMessage(RouterConfig $this, Object msg) {
/*    */     boolean bool1, bool2;
/* 66 */     Object object = msg;
/* 67 */     if (object instanceof akka.actor.AutoReceivedMessage) {
/* 67 */       bool1 = true;
/* 67 */     } else if (object instanceof akka.actor.Terminated) {
/* 67 */       bool1 = true;
/* 67 */     } else if (object instanceof RouterManagementMesssage) {
/* 67 */       bool1 = true;
/*    */     } else {
/* 67 */       bool1 = false;
/*    */     } 
/* 67 */     if (bool1) {
/* 67 */       bool2 = true;
/*    */     } else {
/* 68 */       bool2 = false;
/*    */     } 
/*    */     return bool2;
/*    */   }
/*    */   
/*    */   public static boolean stopRouterWhenAllRouteesRemoved(RouterConfig $this) {
/* 75 */     return true;
/*    */   }
/*    */   
/*    */   public static RouterConfig withFallback(RouterConfig $this, RouterConfig other) {
/* 80 */     return $this;
/*    */   }
/*    */   
/*    */   public static void verifyConfig(RouterConfig $this, ActorPath path) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RouterConfig$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */