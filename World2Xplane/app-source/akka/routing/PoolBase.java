/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorPath;
/*     */ import akka.actor.Props;
/*     */ import scala.Option;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001Y1Q!\001\002\002\002\035\021\001\002U8pY\n\0137/\032\006\003\007\021\tqA]8vi&twMC\001\006\003\021\t7n[1\004\001M\031\001\001\003\b\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g!\ty\001#D\001\003\023\t\t\"A\001\003Q_>d\007\"B\n\001\t\003!\022A\002\037j]&$h\bF\001\026!\ty\001\001")
/*     */ public abstract class PoolBase implements Pool {
/*     */   public boolean usePoolDispatcher() {
/* 172 */     return Pool$class.usePoolDispatcher(this);
/*     */   }
/*     */   
/*     */   public Routee newRoutee(Props routeeProps, ActorContext context) {
/* 172 */     return Pool$class.newRoutee(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props enrichWithPoolDispatcher(Props routeeProps, ActorContext context) {
/* 172 */     return Pool$class.enrichWithPoolDispatcher(this, routeeProps, context);
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/* 172 */     return Pool$class.props(this, routeeProps);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 172 */     return Pool$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 172 */     return Pool$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 172 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 172 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 172 */     return RouterConfig$class.withFallback(this, other);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 172 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public PoolBase() {
/* 172 */     RouterConfig$class.$init$(this);
/* 172 */     Pool$class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\PoolBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */