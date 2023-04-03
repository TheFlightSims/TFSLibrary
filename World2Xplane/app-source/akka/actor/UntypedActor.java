/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001}4Q!\001\002\002\002\035\021A\"\0268usB,G-Q2u_JT!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lC\016\0011c\001\001\t\035A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\013\005\033Go\034:\t\013M\001A\021\001\013\002\rqJg.\033;?)\005)\002CA\b\001\021\0259\002A\"\001\031\003%ygNU3dK&4X\r\006\002\0329A\021\021BG\005\0037)\021A!\0268ji\")QD\006a\001=\0059Q.Z:tC\036,\007CA\005 \023\t\001#BA\002B]fD3A\006\0225!\rI1%J\005\003I)\021a\001\0365s_^\034\bC\001\024(\031\001!Q\001\013\001C\002%\022\021\001V\t\003U5\002\"!C\026\n\0051R!a\002(pi\"Lgn\032\t\003]Er!!C\030\n\005AR\021a\0029bG.\fw-Z\005\003eM\022\021\002\0265s_^\f'\r\\3\013\005AR1%A\033\021\005YbdBA\0340\035\tA4(D\001:\025\tQd!\001\004=e>|GOP\005\002\027%\021Qh\r\002\n\013b\034W\r\035;j_:DQa\020\001\005\002\001\013!bZ3u\007>tG/\032=u)\005\t\005CA\bC\023\t\031%AA\nV]RL\b/\0323BGR|'oQ8oi\026DH\017C\003F\001\021\005a)A\004hKR\034V\r\0344\025\003\035\003\"a\004%\n\005%\023!\001C!di>\024(+\0324\t\013-\003A\021\001$\002\023\035,GoU3oI\026\024\b\"B'\001\t\003r\025AE:va\026\024h/[:peN#(/\031;fOf,\022a\024\t\003\037AK!!\025\002\003%M+\b/\032:wSN|'o\025;sCR,w-\037\005\006'\002!\t\005V\001\taJ,7\013^1siR\t\021\004K\002S-R\0022!C\022X!\t1\003\fB\003)\001\t\007\021\006C\003[\001\021\005C+\001\005q_N$8\013^8qQ\rIF\f\016\t\004\023\rj\006C\001\024_\t\025A\003A1\001*\021\025\001\007\001\"\021b\003)\001(/\032*fgR\f'\017\036\013\0043\t,\007\"B2`\001\004!\027A\002:fCN|g\016\005\0027c!)Qd\030a\001MB\031\021b\032\020\n\005!T!AB(qi&|g\016K\002`UR\0022!C\022l!\t1C\016B\003)\001\t\007\021\006C\003o\001\021\005s.A\006q_N$(+Z:uCJ$HCA\rq\021\025\031W\0161\001eQ\ri'\017\016\t\004\023\r\032\bC\001\024u\t\025A\003A1\001*\021\0251\b\001\"\002x\003\035\021XmY3jm\026,\022\001\037\t\005\023et\022$\003\002{\025\ty\001+\031:uS\006dg)\0368di&|g\016C\003}\001\021\005S0A\005v]\"\fg\016\0327fIR\021\021D \005\006;m\004\rA\b")
/*     */ public abstract class UntypedActor implements Actor {
/*     */   private final ActorContext context;
/*     */   
/*     */   private final ActorRef self;
/*     */   
/*     */   public ActorContext context() {
/*  97 */     return this.context;
/*     */   }
/*     */   
/*     */   public final ActorRef self() {
/*  97 */     return this.self;
/*     */   }
/*     */   
/*     */   public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/*  97 */     this.context = x$1;
/*     */   }
/*     */   
/*     */   public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/*  97 */     this.self = x$1;
/*     */   }
/*     */   
/*     */   public final ActorRef sender() {
/*  97 */     return Actor$class.sender(this);
/*     */   }
/*     */   
/*     */   public void aroundReceive(PartialFunction receive, Object msg) {
/*  97 */     Actor$class.aroundReceive(this, receive, msg);
/*     */   }
/*     */   
/*     */   public void aroundPreStart() {
/*  97 */     Actor$class.aroundPreStart(this);
/*     */   }
/*     */   
/*     */   public void aroundPostStop() {
/*  97 */     Actor$class.aroundPostStop(this);
/*     */   }
/*     */   
/*     */   public void aroundPreRestart(Throwable reason, Option message) {
/*  97 */     Actor$class.aroundPreRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void aroundPostRestart(Throwable reason) {
/*  97 */     Actor$class.aroundPostRestart(this, reason);
/*     */   }
/*     */   
/*     */   public UntypedActor() {
/*  97 */     Actor$class.$init$(this);
/*     */   }
/*     */   
/*     */   public UntypedActorContext getContext() {
/* 111 */     return (UntypedActorContext)context();
/*     */   }
/*     */   
/*     */   public ActorRef getSelf() {
/* 116 */     return self();
/*     */   }
/*     */   
/*     */   public ActorRef getSender() {
/* 123 */     return sender();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy supervisorStrategy() {
/* 129 */     return Actor$class.supervisorStrategy(this);
/*     */   }
/*     */   
/*     */   public void preStart() throws Exception {
/* 139 */     Actor$class.preStart(this);
/*     */   }
/*     */   
/*     */   public void postStop() throws Exception {
/* 148 */     Actor$class.postStop(this);
/*     */   }
/*     */   
/*     */   public void preRestart(Throwable reason, Option message) throws Exception {
/* 157 */     Actor$class.preRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void postRestart(Throwable reason) throws Exception {
/* 165 */     Actor$class.postRestart(this, reason);
/*     */   }
/*     */   
/*     */   public final PartialFunction<Object, BoxedUnit> receive() {
/* 167 */     return (PartialFunction<Object, BoxedUnit>)new UntypedActor$$anonfun$receive$1(this);
/*     */   }
/*     */   
/*     */   public class UntypedActor$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/* 167 */       Object object = x1;
/* 167 */       this.$outer.onReceive(object);
/* 167 */       return (B1)BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x1) {
/* 167 */       Object object = x1;
/* 167 */       return true;
/*     */     }
/*     */     
/*     */     public UntypedActor$$anonfun$receive$1(UntypedActor $outer) {}
/*     */   }
/*     */   
/*     */   public void unhandled(Object message) {
/* 176 */     Actor$class.unhandled(this, message);
/*     */   }
/*     */   
/*     */   public abstract void onReceive(Object paramObject) throws Exception;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\UntypedActor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */