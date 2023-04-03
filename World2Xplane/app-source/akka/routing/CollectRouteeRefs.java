/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorIdentity;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.Identify;
/*     */ import akka.actor.ReceiveTimeout$;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import akka.actor.package$;
/*     */ import scala.Function1;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.immutable.Vector$;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.duration.package;
/*     */ import scala.concurrent.duration.package$;
/*     */ import scala.package$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001U3Q!\001\002\001\t\031\021\021cQ8mY\026\034GOU8vi\026,'+\0324t\025\t\031A!A\004s_V$\030N\\4\013\003\025\tA!Y6lCN\031\001aB\007\021\005!YQ\"A\005\013\003)\tQa]2bY\006L!\001D\005\003\r\005s\027PU3g!\tq\021#D\001\020\025\t\001B!A\003bGR|'/\003\002\023\037\t)\021i\031;pe\"AA\003\001B\001B\003%a#A\004s_V$X-Z:\004\001A\031q\003\b\020\016\003aQ!!\007\016\002\023%lW.\036;bE2,'BA\016\n\003)\031w\016\0347fGRLwN\\\005\003;a\021!\"\0238eKb,GmU3r!\ty\002%D\001\003\023\t\t#A\001\004S_V$X-\032\005\tG\001\021\t\021)A\005I\0059!/\0329msR{\007C\001\b&\023\t1sB\001\005BGR|'OU3g\021\025A\003\001\"\001*\003\031a\024N\\5u}Q\031!f\013\027\021\005}\001\001\"\002\013(\001\0041\002\"B\022(\001\004!\003b\002\030\001\001\004%\taL\001\nG>dG.Z2uK\022,\022\001\r\t\004/E\"\023B\001\032\031\005\0311Vm\031;pe\"9A\007\001a\001\n\003)\024!D2pY2,7\r^3e?\022*\027\017\006\0027sA\021\001bN\005\003q%\021A!\0268ji\"9!hMA\001\002\004\001\024a\001=%c!1A\b\001Q!\nA\n!bY8mY\026\034G/\0323!\021\035q\004\0011A\005\002}\nQaY8v]R,\022\001\021\t\003\021\005K!AQ\005\003\007%sG\017C\004E\001\001\007I\021A#\002\023\r|WO\034;`I\025\fHC\001\034G\021\035Q4)!AA\002\001Ca\001\023\001!B\023\001\025AB2pk:$\b\005C\003K\001\021\0051*A\004sK\016,\027N^3\026\0031\003B\001C'Pm%\021a*\003\002\020!\006\024H/[1m\rVt7\r^5p]B\021\001\002U\005\003#&\0211!\0218z\021\025\031\006\001\"\001U\003\021!wN\\3\025\003Y\002")
/*     */ public class CollectRouteeRefs implements Actor {
/*     */   public final IndexedSeq<Routee> akka$routing$CollectRouteeRefs$$routees;
/*     */   
/*     */   private final ActorRef replyTo;
/*     */   
/*     */   private Vector<ActorRef> collected;
/*     */   
/*     */   private int count;
/*     */   
/*     */   private final ActorContext context;
/*     */   
/*     */   private final ActorRef self;
/*     */   
/*     */   public ActorContext context() {
/* 195 */     return this.context;
/*     */   }
/*     */   
/*     */   public final ActorRef self() {
/* 195 */     return this.self;
/*     */   }
/*     */   
/*     */   public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/* 195 */     this.context = x$1;
/*     */   }
/*     */   
/*     */   public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/* 195 */     this.self = x$1;
/*     */   }
/*     */   
/*     */   public final ActorRef sender() {
/* 195 */     return Actor.class.sender(this);
/*     */   }
/*     */   
/*     */   public void aroundReceive(PartialFunction receive, Object msg) {
/* 195 */     Actor.class.aroundReceive(this, receive, msg);
/*     */   }
/*     */   
/*     */   public void aroundPreStart() {
/* 195 */     Actor.class.aroundPreStart(this);
/*     */   }
/*     */   
/*     */   public void aroundPostStop() {
/* 195 */     Actor.class.aroundPostStop(this);
/*     */   }
/*     */   
/*     */   public void aroundPreRestart(Throwable reason, Option message) {
/* 195 */     Actor.class.aroundPreRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void aroundPostRestart(Throwable reason) {
/* 195 */     Actor.class.aroundPostRestart(this, reason);
/*     */   }
/*     */   
/*     */   public SupervisorStrategy supervisorStrategy() {
/* 195 */     return Actor.class.supervisorStrategy(this);
/*     */   }
/*     */   
/*     */   public void preStart() throws Exception {
/* 195 */     Actor.class.preStart(this);
/*     */   }
/*     */   
/*     */   public void postStop() throws Exception {
/* 195 */     Actor.class.postStop(this);
/*     */   }
/*     */   
/*     */   public void preRestart(Throwable reason, Option message) throws Exception {
/* 195 */     Actor.class.preRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void postRestart(Throwable reason) throws Exception {
/* 195 */     Actor.class.postRestart(this, reason);
/*     */   }
/*     */   
/*     */   public void unhandled(Object message) {
/* 195 */     Actor.class.unhandled(this, message);
/*     */   }
/*     */   
/*     */   public CollectRouteeRefs(IndexedSeq<Routee> routees, ActorRef replyTo) {
/* 195 */     Actor.class.$init$(this);
/* 197 */     this.collected = package$.MODULE$.Vector().empty();
/* 198 */     this.count = 0;
/* 199 */     routees.foreach((Function1)new CollectRouteeRefs$$anonfun$3(this));
/* 202 */     context().system().scheduler().scheduleOnce((new package.DurationInt(package$.MODULE$.DurationInt(10))).seconds(), self(), ReceiveTimeout$.MODULE$, (ExecutionContext)context().dispatcher(), self());
/*     */   }
/*     */   
/*     */   public Vector<ActorRef> collected() {
/*     */     return this.collected;
/*     */   }
/*     */   
/*     */   public void collected_$eq(Vector<ActorRef> x$1) {
/*     */     this.collected = x$1;
/*     */   }
/*     */   
/*     */   public int count() {
/*     */     return this.count;
/*     */   }
/*     */   
/*     */   public void count_$eq(int x$1) {
/*     */     this.count = x$1;
/*     */   }
/*     */   
/*     */   public class CollectRouteeRefs$$anonfun$3 extends AbstractFunction1<Routee, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Routee x$3) {
/*     */       x$3.send(new Identify(None$.MODULE$), this.$outer.self());
/*     */     }
/*     */     
/*     */     public CollectRouteeRefs$$anonfun$3(CollectRouteeRefs $outer) {}
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> receive() {
/* 204 */     return (PartialFunction<Object, BoxedUnit>)new CollectRouteeRefs$$anonfun$receive$3(this);
/*     */   }
/*     */   
/*     */   public class CollectRouteeRefs$$anonfun$receive$3 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x3, Function1 default) {
/*     */       BoxedUnit boxedUnit;
/* 204 */       Object object = x3;
/* 205 */       if (object instanceof ActorIdentity) {
/* 205 */         ActorIdentity actorIdentity = (ActorIdentity)object;
/* 205 */         Option refOption = actorIdentity.ref();
/* 206 */         refOption.foreach((Function1)new CollectRouteeRefs$$anonfun$receive$3$$anonfun$applyOrElse$2(this));
/* 207 */         this.$outer.count_$eq(this.$outer.count() + 1);
/* 208 */         this.$outer.done();
/* 208 */         boxedUnit = (this.$outer.count() == this.$outer.akka$routing$CollectRouteeRefs$$routees.size()) ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/*     */       } else {
/* 209 */         Object object1 = object;
/* 209 */         if (ReceiveTimeout$.MODULE$ == null) {
/* 209 */           if (object1 != null)
/*     */             Object object2 = default.apply(x3); 
/*     */         } else {
/* 209 */           if (ReceiveTimeout$.MODULE$.equals(object1)) {
/* 209 */             this.$outer.done();
/* 209 */             BoxedUnit boxedUnit1 = BoxedUnit.UNIT;
/*     */           } 
/*     */           Object object2 = default.apply(x3);
/*     */         } 
/* 209 */         this.$outer.done();
/* 209 */         boxedUnit = BoxedUnit.UNIT;
/*     */       } 
/*     */       return (B1)boxedUnit;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x3) {
/*     */       boolean bool;
/*     */       Object object = x3;
/*     */       if (object instanceof ActorIdentity) {
/*     */         bool = true;
/*     */       } else {
/* 209 */         Object object1 = object;
/* 209 */         if (ReceiveTimeout$.MODULE$ == null) {
/* 209 */           if (object1 != null)
/*     */             boolean bool1 = false; 
/*     */         } else {
/* 209 */           if (ReceiveTimeout$.MODULE$.equals(object1))
/* 209 */             boolean bool2 = true; 
/*     */           boolean bool1 = false;
/*     */         } 
/* 209 */         bool = true;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public CollectRouteeRefs$$anonfun$receive$3(CollectRouteeRefs $outer) {}
/*     */     
/*     */     public class CollectRouteeRefs$$anonfun$receive$3$$anonfun$applyOrElse$2 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final void apply(ActorRef ref) {
/*     */         this.$outer.akka$routing$CollectRouteeRefs$$anonfun$$$outer().collected_$eq((Vector<ActorRef>)this.$outer.akka$routing$CollectRouteeRefs$$anonfun$$$outer().collected().$colon$plus(ref, Vector$.MODULE$.canBuildFrom()));
/*     */       }
/*     */       
/*     */       public CollectRouteeRefs$$anonfun$receive$3$$anonfun$applyOrElse$2(CollectRouteeRefs$$anonfun$receive$3 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public void done() {
/* 213 */     package$.MODULE$.actorRef2Scala(this.replyTo).$bang(new RouterRoutees((IndexedSeq<ActorRef>)collected()), self());
/* 214 */     context().stop(self());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\CollectRouteeRefs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */