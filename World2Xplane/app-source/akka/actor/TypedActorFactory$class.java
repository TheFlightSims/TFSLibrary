/*    */ package akka.actor;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicReference;
/*    */ import scala.Function0;
/*    */ import scala.collection.immutable.Seq;
/*    */ import scala.reflect.ClassTag$;
/*    */ 
/*    */ public abstract class TypedActorFactory$class {
/*    */   public static void $init$(TypedActorFactory $this) {}
/*    */   
/*    */   public static boolean stop(TypedActorFactory $this, Object proxy) {
/*    */     boolean bool;
/* 47 */     ActorRef actorRef = $this.getActorRefFor(proxy);
/* 48 */     if (actorRef == null) {
/* 48 */       bool = false;
/*    */     } else {
/* 49 */       ((InternalActorRef)actorRef).stop();
/* 49 */       bool = true;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */   
/*    */   public static boolean poisonPill(TypedActorFactory $this, Object proxy) {
/*    */     boolean bool;
/* 56 */     ActorRef actorRef = $this.getActorRefFor(proxy);
/* 57 */     if (actorRef == null) {
/* 57 */       bool = false;
/*    */     } else {
/* 58 */       ScalaActorRef qual$1 = package$.MODULE$.actorRef2Scala(actorRef);
/* 58 */       PoisonPill$ x$2 = PoisonPill$.MODULE$;
/* 58 */       ActorRef x$3 = qual$1.$bang$default$2(x$2);
/* 58 */       qual$1.$bang(x$2, x$3);
/* 58 */       bool = true;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */   
/*    */   public static Object typedActorOf(TypedActorFactory $this, TypedProps props) {
/* 75 */     AtomicReference<?> proxyVar = new AtomicReference();
/* 76 */     Function0 c = props.creator();
/* 77 */     Seq<Class<?>> i = props.interfaces();
/* 78 */     Props ap = Props$.MODULE$.<Actor>apply((Function0<Actor>)new TypedActorFactory$$anonfun$1($this, proxyVar, c, i), ClassTag$.MODULE$.apply(TypedActor.TypedActor.class)).withDeploy(props.actorProps().deploy());
/* 79 */     return $this.typedActor().createActorRefProxy(props, proxyVar, (Function0<ActorRef>)new TypedActorFactory$$anonfun$typedActorOf$1($this, ap));
/*    */   }
/*    */   
/*    */   public static Object typedActorOf(TypedActorFactory $this, TypedProps props, String name) {
/* 86 */     AtomicReference<?> proxyVar = new AtomicReference();
/* 87 */     Function0 c = props.creator();
/* 88 */     Seq<Class<?>> i = props.interfaces();
/* 89 */     Props ap = Props$.MODULE$.<Actor>apply((Function0<Actor>)new TypedActorFactory$$anonfun$2($this, proxyVar, c, i), ClassTag$.MODULE$.apply(TypedActor.TypedActor.class)).withDeploy(props.actorProps().deploy());
/* 90 */     return $this.typedActor().createActorRefProxy(props, proxyVar, (Function0<ActorRef>)new TypedActorFactory$$anonfun$typedActorOf$2($this, ap, name));
/*    */   }
/*    */   
/*    */   public static Object typedActorOf(TypedActorFactory $this, TypedProps props, ActorRef actorRef) {
/* 98 */     null;
/* 98 */     return $this.typedActor().createActorRefProxy(props, null, (Function0<ActorRef>)new TypedActorFactory$$anonfun$typedActorOf$3($this, actorRef));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\TypedActorFactory$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */