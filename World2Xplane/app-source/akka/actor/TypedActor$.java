/*     */ package akka.actor;
/*     */ 
/*     */ import scala.concurrent.ExecutionContextExecutor;
/*     */ 
/*     */ public final class TypedActor$ implements ExtensionId<TypedActorExtension>, ExtensionIdProvider {
/*     */   public static final TypedActor$ MODULE$;
/*     */   
/*     */   private final ThreadLocal<Object> akka$actor$TypedActor$$selfReference;
/*     */   
/*     */   private final ThreadLocal<ActorContext> akka$actor$TypedActor$$currentContext;
/*     */   
/*     */   public Extension apply(ActorSystem system) {
/* 105 */     return ExtensionId$class.apply(this, system);
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 105 */     return ExtensionId$class.hashCode(this);
/*     */   }
/*     */   
/*     */   public final boolean equals(Object other) {
/* 105 */     return ExtensionId$class.equals(this, other);
/*     */   }
/*     */   
/*     */   private TypedActor$() {
/* 105 */     MODULE$ = this;
/* 105 */     ExtensionId$class.$init$(this);
/* 199 */     this.akka$actor$TypedActor$$selfReference = new ThreadLocal();
/* 200 */     this.akka$actor$TypedActor$$currentContext = new ThreadLocal<ActorContext>();
/*     */   }
/*     */   
/*     */   public TypedActorExtension get(ActorSystem system) {
/*     */     return (TypedActorExtension)ExtensionId$class.get(this, system);
/*     */   }
/*     */   
/*     */   public TypedActor$ lookup() {
/*     */     return this;
/*     */   }
/*     */   
/*     */   public TypedActorExtension createExtension(ExtendedActorSystem system) {
/*     */     return new TypedActorExtension(system);
/*     */   }
/*     */   
/*     */   public TypedActorFactory apply(ActorContext context) {
/*     */     return new ContextualTypedActorFactory((TypedActorExtension)apply(context.system()), context);
/*     */   }
/*     */   
/*     */   public TypedActorFactory get(ActorContext context) {
/*     */     return apply(context);
/*     */   }
/*     */   
/*     */   public ThreadLocal<Object> akka$actor$TypedActor$$selfReference() {
/*     */     return this.akka$actor$TypedActor$$selfReference;
/*     */   }
/*     */   
/*     */   public ThreadLocal<ActorContext> akka$actor$TypedActor$$currentContext() {
/* 200 */     return this.akka$actor$TypedActor$$currentContext;
/*     */   }
/*     */   
/*     */   public <T> T self() {
/* 223 */     Object object = akka$actor$TypedActor$$selfReference().get();
/* 224 */     if (object == null)
/* 224 */       throw new IllegalStateException("Calling TypedActor.self outside of a TypedActor implementation method!"); 
/* 225 */     return (T)object;
/*     */   }
/*     */   
/*     */   public ActorContext context() {
/* 231 */     ActorContext actorContext = akka$actor$TypedActor$$currentContext().get();
/* 232 */     if (actorContext == null)
/* 232 */       throw new IllegalStateException("Calling TypedActor.context outside of a TypedActor implementation method!"); 
/* 233 */     return actorContext;
/*     */   }
/*     */   
/*     */   public ExecutionContextExecutor dispatcher() {
/* 239 */     return context().dispatcher();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\TypedActor$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */