/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class ContextualTypedActorFactory$ extends AbstractFunction2<TypedActorExtension, ActorContext, ContextualTypedActorFactory> implements Serializable {
/*     */   public static final ContextualTypedActorFactory$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 629 */     return "ContextualTypedActorFactory";
/*     */   }
/*     */   
/*     */   public ContextualTypedActorFactory apply(TypedActorExtension typedActor, ActorContext actorFactory) {
/* 629 */     return new ContextualTypedActorFactory(typedActor, actorFactory);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<TypedActorExtension, ActorContext>> unapply(ContextualTypedActorFactory x$0) {
/* 629 */     return (x$0 == null) ? (Option<Tuple2<TypedActorExtension, ActorContext>>)scala.None$.MODULE$ : (Option<Tuple2<TypedActorExtension, ActorContext>>)new Some(new Tuple2(x$0.typedActor(), x$0.actorFactory()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 629 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ContextualTypedActorFactory$() {
/* 629 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ContextualTypedActorFactory$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */