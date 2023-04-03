/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.Props;
/*     */ import akka.actor.Props$;
/*     */ 
/*     */ public abstract class Group$class {
/*     */   public static void $init$(Group $this) {}
/*     */   
/*     */   public static Props props(Group $this) {
/* 149 */     return Props$.MODULE$.empty().withRouter($this);
/*     */   }
/*     */   
/*     */   public static Routee routeeFor(Group $this, String path, ActorContext context) {
/* 155 */     return new ActorSelectionRoutee(context.actorSelection(path));
/*     */   }
/*     */   
/*     */   public static RouterActor createRouterActor(Group $this) {
/* 160 */     return new RouterActor();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Group$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */