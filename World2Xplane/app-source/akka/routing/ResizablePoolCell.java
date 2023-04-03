/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.ActorSystemImpl;
/*     */ import akka.actor.InternalActorRef;
/*     */ import akka.actor.Props;
/*     */ import akka.dispatch.Envelope;
/*     */ import akka.dispatch.Envelope$;
/*     */ import akka.dispatch.MessageDispatcher;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import scala.Function0;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.package$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001e4Q!\001\002\003\t\031\021\021CU3tSj\f'\r\\3Q_>d7)\0327m\025\t\031A!A\004s_V$\030N\\4\013\003\025\tA!Y6lCN\021\001a\002\t\003\021%i\021AA\005\003\025\t\021qBU8vi\026$\027i\031;pe\016+G\016\034\005\t\031\001\021\t\021)A\005\035\0059ql]=ti\026l7\001\001\t\003\037Ii\021\001\005\006\003#\021\tQ!Y2u_JL!a\005\t\003\037\005\033Go\034:TsN$X-\\%na2D\001\"\006\001\003\002\003\006IAF\001\005?J,g\r\005\002\020/%\021\001\004\005\002\021\023:$XM\0358bY\006\033Go\034:SK\032D\001B\007\001\003\002\003\006IaG\001\r?J|W\017^3s!J|\007o\035\t\003\037qI!!\b\t\003\013A\023x\016]:\t\021}\001!\021!Q\001\n\001\n\021c\030:pkR,'\017R5ta\006$8\r[3s!\t\tC%D\001#\025\t\031C!\001\005eSN\004\030\r^2i\023\t)#EA\tNKN\034\030mZ3ESN\004\030\r^2iKJD\021b\n\001\003\002\003\006Ia\007\025\002\031}\023x.\036;fKB\023x\016]:\n\005%J\021a\003:pkR,W\r\025:paND\001b\013\001\003\002\003\006IAF\001\f?N,\b/\032:wSN|'\017\003\005.\001\t\025\r\021\"\001/\003\021\001xn\0347\026\003=\002\"\001\003\031\n\005E\022!\001\002)p_2D\001b\r\001\003\002\003\006IaL\001\006a>|G\016\t\005\006k\001!\tAN\001\007y%t\027\016\036 \025\021]B\024HO\036={y\002\"\001\003\001\t\0131!\004\031\001\b\t\013U!\004\031\001\f\t\013i!\004\031A\016\t\013}!\004\031\001\021\t\013\035\"\004\031A\016\t\013-\"\004\031\001\f\t\0135\"\004\031A\030\t\017\001\003!\031!C\001\003\0069!/Z:ju\026\024X#\001\"\021\005!\031\025B\001#\003\005\035\021Vm]5{KJDaA\022\001!\002\023\021\025\001\003:fg&TXM\035\021\t\017!\003!\031!C\005\023\006\001\"/Z:ju\026Le\016\025:pOJ,7o]\013\002\025B\0211\nV\007\002\031*\021QJT\001\007CR|W.[2\013\005=\003\026AC2p]\016,(O]3oi*\021\021KU\001\005kRLGNC\001T\003\021Q\027M^1\n\005Uc%!D!u_6L7MQ8pY\026\fg\016\003\004X\001\001\006IAS\001\022e\026\034\030N_3J]B\023xn\032:fgN\004\003bB-\001\005\004%IAW\001\016e\026\034\030N_3D_VtG/\032:\026\003m\003\"a\023/\n\005uc%AC!u_6L7\rT8oO\"1q\f\001Q\001\nm\013aB]3tSj,7i\\;oi\026\024\b\005C\003b\001\021E#-A\007qe\026\034V\017]3s'R\f'\017\036\013\002GB\021AmZ\007\002K*\ta-A\003tG\006d\027-\003\002iK\n!QK\\5u\021\025Q\007\001\"\021l\003-\031XM\0343NKN\034\030mZ3\025\005\rd\007\"B7j\001\004q\027\001C3om\026dw\016]3\021\005\005z\027B\0019#\005!)eN^3m_B,\007B\002:\001\t\003!1/\001\004sKNL'0\032\013\003GRDQ!^9A\002Y\fq!\0338ji&\fG\016\005\002eo&\021\0010\032\002\b\005>|G.Z1o\001")
/*     */ public final class ResizablePoolCell extends RoutedActorCell {
/*     */   private final Pool pool;
/*     */   
/*     */   private final Resizer resizer;
/*     */   
/*     */   private final AtomicBoolean resizeInProgress;
/*     */   
/*     */   private final AtomicLong resizeCounter;
/*     */   
/*     */   public Pool pool() {
/* 256 */     return this.pool;
/*     */   }
/*     */   
/*     */   public ResizablePoolCell(ActorSystemImpl _system, InternalActorRef _ref, Props _routerProps, MessageDispatcher _routerDispatcher, Props _routeeProps, InternalActorRef _supervisor, Pool pool) {
/* 257 */     super(_system, _ref, _routerProps, _routerDispatcher, _routeeProps, _supervisor);
/* 259 */     Predef$.MODULE$.require(pool.resizer().isDefined(), (Function0)new ResizablePoolCell$$anonfun$1(this));
/* 260 */     this.resizer = (Resizer)pool.resizer().get();
/* 261 */     this.resizeInProgress = new AtomicBoolean();
/* 262 */     this.resizeCounter = new AtomicLong();
/*     */   }
/*     */   
/*     */   public class ResizablePoolCell$$anonfun$1 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/*     */       return "RouterConfig must be a Pool with defined resizer";
/*     */     }
/*     */     
/*     */     public ResizablePoolCell$$anonfun$1(ResizablePoolCell $outer) {}
/*     */   }
/*     */   
/*     */   public Resizer resizer() {
/*     */     return this.resizer;
/*     */   }
/*     */   
/*     */   private AtomicBoolean resizeInProgress() {
/*     */     return this.resizeInProgress;
/*     */   }
/*     */   
/*     */   private AtomicLong resizeCounter() {
/* 262 */     return this.resizeCounter;
/*     */   }
/*     */   
/*     */   public void preSuperStart() {
/* 266 */     if (resizer().isTimeForResize(resizeCounter().getAndIncrement()))
/* 267 */       resize(true); 
/*     */   }
/*     */   
/*     */   public void sendMessage(Envelope envelope) {
/* 272 */     if (!routerConfig().isManagementMessage(envelope.message()) && 
/* 273 */       resizer().isTimeForResize(resizeCounter().getAndIncrement()) && resizeInProgress().compareAndSet(false, true))
/* 274 */       super.sendMessage(Envelope$.MODULE$.apply(ResizablePoolActor.Resize$.MODULE$, (ActorRef)self(), (ActorSystem)system())); 
/* 276 */     super.sendMessage(envelope);
/*     */   }
/*     */   
/*     */   public void resize(boolean initial) {
/* 280 */     if (resizeInProgress().get() || initial)
/*     */       try {
/* 281 */         int requestedCapacity = resizer().resize(router().routees());
/* 282 */         if (requestedCapacity > 0) {
/* 283 */           Vector newRoutees = (Vector)package$.MODULE$.Vector().fill(requestedCapacity, (Function0)new ResizablePoolCell$$anonfun$2(this));
/* 284 */           addRoutees((Iterable<Routee>)newRoutees);
/* 285 */         } else if (requestedCapacity < 0) {
/* 286 */           IndexedSeq<Routee> currentRoutees = router().routees();
/* 287 */           IndexedSeq abandon = (IndexedSeq)currentRoutees.drop(currentRoutees.length() + requestedCapacity);
/* 288 */           removeRoutees((Iterable<Routee>)abandon, true);
/*     */         } 
/*     */         return;
/*     */       } finally {
/* 290 */         resizeInProgress().set(false);
/*     */       }  
/*     */   }
/*     */   
/*     */   public class ResizablePoolCell$$anonfun$2 extends AbstractFunction0<Routee> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Routee apply() {
/*     */       return this.$outer.pool().newRoutee(this.$outer.routeeProps(), (ActorContext)this.$outer);
/*     */     }
/*     */     
/*     */     public ResizablePoolCell$$anonfun$2(ResizablePoolCell $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ResizablePoolCell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */