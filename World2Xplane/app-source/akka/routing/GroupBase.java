/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorPath;
/*     */ import akka.actor.Props;
/*     */ import akka.japi.Util$;
/*     */ import scala.Option;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001I2Q!\001\002\002\002\035\021\021b\022:pkB\024\025m]3\013\005\r!\021a\002:pkRLgn\032\006\002\013\005!\021m[6b\007\001\0312\001\001\005\017!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fMB\021q\002E\007\002\005%\021\021C\001\002\006\017J|W\017\035\005\006'\001!\t\001F\001\007y%t\027\016\036 \025\003U\001\"a\004\001\t\013]\001a\021\001\r\002\021\035,G\017U1uQN,\022!\007\t\0045}\tS\"A\016\013\005qi\022\001\0027b]\036T\021AH\001\005U\0064\030-\003\002!7\tA\021\n^3sC\ndW\r\005\002#K9\021\021bI\005\003I)\ta\001\025:fI\0264\027B\001\024(\005\031\031FO]5oO*\021AE\003\005\006S\001!)EK\001\006a\006$\bn]\013\002WA\031A&M\021\016\0035R!AL\030\002\023%lW.\036;bE2,'B\001\031\013\003)\031w\016\0347fGRLwN\\\005\003A5\002")
/*     */ public abstract class GroupBase implements Group {
/*     */   public Props props() {
/* 130 */     return Group$class.props(this);
/*     */   }
/*     */   
/*     */   public Routee routeeFor(String path, ActorContext context) {
/* 130 */     return Group$class.routeeFor(this, path, context);
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 130 */     return Group$class.createRouterActor(this);
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 130 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 130 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 130 */     return RouterConfig$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 130 */     return RouterConfig$class.withFallback(this, other);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 130 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public GroupBase() {
/* 130 */     RouterConfig$class.$init$(this);
/* 130 */     Group$class.$init$(this);
/*     */   }
/*     */   
/*     */   public final Iterable<String> paths() {
/* 133 */     return (Iterable<String>)Util$.MODULE$.immutableSeq(getPaths());
/*     */   }
/*     */   
/*     */   public abstract Iterable<String> getPaths();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\GroupBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */