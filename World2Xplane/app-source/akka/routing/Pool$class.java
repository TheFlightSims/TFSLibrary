/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.Props;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ 
/*     */ public abstract class Pool$class {
/*     */   public static void $init$(Pool $this) {}
/*     */   
/*     */   public static boolean usePoolDispatcher(Pool $this) {
/* 189 */     return false;
/*     */   }
/*     */   
/*     */   public static Routee newRoutee(Pool $this, Props routeeProps, ActorContext context) {
/* 195 */     return new ActorRefRoutee(context.actorOf($this.enrichWithPoolDispatcher(routeeProps, context)));
/*     */   }
/*     */   
/*     */   public static Props enrichWithPoolDispatcher(Pool $this, Props routeeProps, ActorContext context) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokeinterface usePoolDispatcher : ()Z
/*     */     //   6: ifeq -> 99
/*     */     //   9: aload_1
/*     */     //   10: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   13: ldc 'akka.actor.default-dispatcher'
/*     */     //   15: astore_3
/*     */     //   16: dup
/*     */     //   17: ifnonnull -> 28
/*     */     //   20: pop
/*     */     //   21: aload_3
/*     */     //   22: ifnull -> 35
/*     */     //   25: goto -> 99
/*     */     //   28: aload_3
/*     */     //   29: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   32: ifeq -> 99
/*     */     //   35: aload_1
/*     */     //   36: new scala/collection/mutable/StringBuilder
/*     */     //   39: dup
/*     */     //   40: invokespecial <init> : ()V
/*     */     //   43: ldc 'akka.actor.deployment.'
/*     */     //   45: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   48: aload_2
/*     */     //   49: invokeinterface self : ()Lakka/actor/ActorRef;
/*     */     //   54: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   57: invokeinterface elements : ()Lscala/collection/immutable/Iterable;
/*     */     //   62: iconst_1
/*     */     //   63: invokeinterface drop : (I)Ljava/lang/Object;
/*     */     //   68: checkcast scala/collection/TraversableOnce
/*     */     //   71: ldc '/'
/*     */     //   73: ldc '/'
/*     */     //   75: ldc ''
/*     */     //   77: invokeinterface mkString : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
/*     */     //   82: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   85: ldc '.pool-dispatcher'
/*     */     //   87: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   90: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   93: invokevirtual withDispatcher : (Ljava/lang/String;)Lakka/actor/Props;
/*     */     //   96: goto -> 100
/*     */     //   99: aload_1
/*     */     //   100: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #201	-> 0
/*     */     //   #202	-> 35
/*     */     //   #203	-> 36
/*     */     //   #202	-> 43
/*     */     //   #203	-> 85
/*     */     //   #202	-> 93
/*     */     //   #205	-> 99
/*     */     //   #201	-> 100
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	101	0	$this	Lakka/routing/Pool;
/*     */     //   0	101	1	routeeProps	Lakka/actor/Props;
/*     */     //   0	101	2	context	Lakka/actor/ActorContext;
/*     */   }
/*     */   
/*     */   public static Props props(Pool $this, Props routeeProps) {
/* 225 */     return routeeProps.withRouter($this);
/*     */   }
/*     */   
/*     */   public static boolean stopRouterWhenAllRouteesRemoved(Pool $this) {
/* 231 */     return $this.resizer().isEmpty();
/*     */   }
/*     */   
/*     */   public static RouterActor createRouterActor(Pool $this) {
/* 237 */     Option<Resizer> option1 = $this.resizer();
/* 238 */     Option<Resizer> option2 = option1;
/* 238 */     if (None$.MODULE$ == null) {
/* 238 */       if (option2 != null)
/* 239 */         if (option1 instanceof scala.Some)
/* 239 */           return new ResizablePoolActor($this.supervisorStrategy());  
/*     */     } else {
/*     */       if (None$.MODULE$.equals(option2))
/*     */         return new RouterPoolActor($this.supervisorStrategy()); 
/* 239 */       if (option1 instanceof scala.Some)
/* 239 */         return new ResizablePoolActor($this.supervisorStrategy()); 
/*     */     } 
/*     */     return new RouterPoolActor($this.supervisorStrategy());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Pool$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */