/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorPath;
/*     */ import akka.actor.Props;
/*     */ import scala.Option;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0312Q!\001\002\002\002\035\021!cQ;ti>l'k\\;uKJ\034uN\0344jO*\0211\001B\001\be>,H/\0338h\025\005)\021\001B1lW\006\034\001aE\002\001\0219\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007CA\b\021\033\005\021\021BA\t\003\0051\021v.\036;fe\016{gNZ5h\021\025\031\002\001\"\001\025\003\031a\024N\\5u}Q\tQ\003\005\002\020\001!1q\003\001C!\ta\t\021c\031:fCR,'k\\;uKJ\f5\r^8s)\005I\002CA\b\033\023\tY\"AA\006S_V$XM]!di>\024\b\"B\017\001\t\003r\022\001\005:pkR,'\017R5ta\006$8\r[3s+\005y\002C\001\021$\035\tI\021%\003\002#\025\0051\001K]3eK\032L!\001J\023\003\rM#(/\0338h\025\t\021#\002")
/*     */ public abstract class CustomRouterConfig implements RouterConfig {
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 248 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 248 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 248 */     return RouterConfig$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 248 */     return RouterConfig$class.withFallback(this, other);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 248 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public CustomRouterConfig() {
/* 248 */     RouterConfig$class.$init$(this);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 252 */     return new RouterActor();
/*     */   }
/*     */   
/*     */   public String routerDispatcher() {
/* 254 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\CustomRouterConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */