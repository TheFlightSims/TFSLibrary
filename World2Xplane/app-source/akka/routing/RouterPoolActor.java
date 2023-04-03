/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorInitializationException$;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import akka.actor.Terminated;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.package$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001M2Q!\001\002\001\t\031\021qBU8vi\026\024\bk\\8m\003\016$xN\035\006\003\007\021\tqA]8vi&twMC\001\006\003\021\t7n[1\024\005\0019\001C\001\005\n\033\005\021\021B\001\006\003\005-\021v.\036;fe\006\033Go\034:\t\0211\001!Q1A\005B9\t!c];qKJ4\030n]8s'R\024\030\r^3hs\016\001Q#A\b\021\005A\031R\"A\t\013\005I!\021!B1di>\024\030B\001\013\022\005I\031V\017]3sm&\034xN]*ue\006$XmZ=\t\021Y\001!\021!Q\001\n=\t1c];qKJ4\030n]8s'R\024\030\r^3hs\002BQ\001\007\001\005\002e\ta\001P5oSRtDC\001\016\034!\tA\001\001C\003\r/\001\007q\002C\004\036\001\t\007I\021\001\020\002\tA|w\016\\\013\002?A\021\001\002I\005\003C\t\021A\001U8pY\"11\005\001Q\001\n}\tQ\001]8pY\002BQ!\n\001\005B\031\nqA]3dK&4X-F\001(!\021A3&\f\031\016\003%R\021AK\001\006g\016\fG.Y\005\003Y%\022q\002U1si&\fGNR;oGRLwN\034\t\003Q9J!aL\025\003\007\005s\027\020\005\002)c%\021!'\013\002\005+:LG\017")
/*     */ public class RouterPoolActor extends RouterActor {
/*     */   private final SupervisorStrategy supervisorStrategy;
/*     */   
/*     */   private final Pool pool;
/*     */   
/*     */   public SupervisorStrategy supervisorStrategy() {
/* 221 */     return this.supervisorStrategy;
/*     */   }
/*     */   
/*     */   public RouterPoolActor(SupervisorStrategy supervisorStrategy) {
/* 223 */     RouterConfig routerConfig = cell().routerConfig();
/* 224 */     if (routerConfig instanceof Pool) {
/* 224 */       Pool pool1 = (Pool)routerConfig, pool2 = pool1;
/*     */       this.pool = pool2;
/*     */       return;
/*     */     } 
/* 226 */     throw ActorInitializationException$.MODULE$.apply((new StringBuilder()).append("RouterPoolActor can only be used with Pool, not ").append(routerConfig.getClass()).toString());
/*     */   }
/*     */   
/*     */   public Pool pool() {
/*     */     return this.pool;
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> receive() {
/* 242 */     return (new RouterPoolActor$$anonfun$receive$2(this)).orElse(super.receive());
/*     */   }
/*     */   
/*     */   public class RouterPoolActor$$anonfun$receive$2 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x2, Function1 default) {
/*     */       Object object2, object1 = x2;
/*     */       if (object1 instanceof Terminated) {
/*     */         Terminated terminated = (Terminated)object1;
/*     */         ActorRef child = terminated.actor();
/*     */         this.$outer.cell().removeRoutee(new ActorRefRoutee(child), false);
/*     */         this.$outer.stopIfAllRouteesRemoved();
/*     */         object2 = BoxedUnit.UNIT;
/*     */       } else if (object1 instanceof AdjustPoolSize) {
/*     */         AdjustPoolSize adjustPoolSize = (AdjustPoolSize)object1;
/*     */         int change = adjustPoolSize.change(), i = change;
/*     */         Vector newRoutees = (Vector)package$.MODULE$.Vector().fill(i, (Function0)new RouterPoolActor$$anonfun$receive$2$$anonfun$4(this));
/*     */         this.$outer.cell().addRoutees((Iterable<Routee>)newRoutees);
/*     */         IndexedSeq<Routee> currentRoutees = this.$outer.cell().router().routees();
/*     */         IndexedSeq abandon = (IndexedSeq)currentRoutees.drop(currentRoutees.length() + i);
/*     */         this.$outer.cell().removeRoutees((Iterable<Routee>)abandon, true);
/*     */         object2 = (i > 0) ? BoxedUnit.UNIT : ((i < 0) ? BoxedUnit.UNIT : BoxedUnit.UNIT);
/*     */       } else {
/*     */         object2 = default.apply(x2);
/*     */       } 
/*     */       return (B1)object2;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x2) {
/*     */       boolean bool;
/*     */       Object object = x2;
/*     */       if (object instanceof Terminated) {
/*     */         bool = true;
/*     */       } else if (object instanceof AdjustPoolSize) {
/*     */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public RouterPoolActor$$anonfun$receive$2(RouterPoolActor $outer) {}
/*     */     
/*     */     public class RouterPoolActor$$anonfun$receive$2$$anonfun$4 extends AbstractFunction0<Routee> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Routee apply() {
/*     */         return this.$outer.akka$routing$RouterPoolActor$$anonfun$$$outer().pool().newRoutee(this.$outer.akka$routing$RouterPoolActor$$anonfun$$$outer().cell().routeeProps(), this.$outer.akka$routing$RouterPoolActor$$anonfun$$$outer().context());
/*     */       }
/*     */       
/*     */       public RouterPoolActor$$anonfun$receive$2$$anonfun$4(RouterPoolActor$$anonfun$receive$2 $outer) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RouterPoolActor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */