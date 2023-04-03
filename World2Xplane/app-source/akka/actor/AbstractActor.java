/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001y;Q!\001\002\t\002\035\tQ\"\0212tiJ\f7\r^!di>\024(BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051BA\007BEN$(/Y2u\003\016$xN]\n\003\0231\001\"!\004\t\016\0039Q\021aD\001\006g\016\fG.Y\005\003#9\021a!\0218z%\0264\007\"B\n\n\t\003!\022A\002\037j]&$h\bF\001\b\021\0351\022B1A\005\006]\tQ\"Z7qif\024U\r[1wS>\024X#\001\r\017\005eabB\001\005\033\023\tY\"!A\003BGR|'/\003\002\027;)\0211D\001\005\007?%\001\013Q\002\r\002\035\025l\007\017^=CK\"\fg/[8sA\031)!BAA\001CM\031\001\005\004\022\021\005!\031\023B\001\023\003\005\025\t5\r^8s\021\025\031\002\005\"\001')\0059\003C\001\005!\021\035I\003\0051A\005\n)\n\001b\030:fG\026Lg/Z\013\002WA\021A&L\007\002A%\021af\t\002\b%\026\034W-\033<f\021\035\001\004\0051A\005\nE\nAb\030:fG\026Lg/Z0%KF$\"AM\033\021\0055\031\024B\001\033\017\005\021)f.\033;\t\017Yz\023\021!a\001W\005\031\001\020J\031\t\ra\002\003\025)\003,\003%y&/Z2fSZ,\007\005C\003;A\021E1(A\004sK\016,\027N^3\025\005Ib\004\"\002\036:\001\004Y\003fA\035?!B\031QbP!\n\005\001s!A\002;ie><8\017\005\002C\0072\001A!\002#\001\005\004)%!\001+\022\005\031K\005CA\007H\023\tAeBA\004O_RD\027N\\4\021\005)keBA\007L\023\tae\"A\004qC\016\\\027mZ3\n\0059{%!\003+ie><\030M\0317f\025\taebI\001R!\tA!+\003\002T\005\tQ\022\n\0347fO\006d\027i\031;peN#\030\r^3Fq\016,\007\017^5p]\")Q\013\tC\001-\006Qq-\032;D_:$X\r\037;\025\003]\003\"\001\003-\n\005e\023!\001F!cgR\024\030m\031;BGR|'oQ8oi\026DH\017C\003;A\021\0053,F\001]!\tIR,\003\002/;\001")
/*    */ public abstract class AbstractActor implements Actor {
/*    */   private PartialFunction<Object, BoxedUnit> _receive;
/*    */   
/*    */   private final ActorContext context;
/*    */   
/*    */   private final ActorRef self;
/*    */   
/*    */   public ActorContext context() {
/* 47 */     return this.context;
/*    */   }
/*    */   
/*    */   public final ActorRef self() {
/* 47 */     return this.self;
/*    */   }
/*    */   
/*    */   public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/* 47 */     this.context = x$1;
/*    */   }
/*    */   
/*    */   public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/* 47 */     this.self = x$1;
/*    */   }
/*    */   
/*    */   public final ActorRef sender() {
/* 47 */     return Actor$class.sender(this);
/*    */   }
/*    */   
/*    */   public void aroundReceive(PartialFunction receive, Object msg) {
/* 47 */     Actor$class.aroundReceive(this, receive, msg);
/*    */   }
/*    */   
/*    */   public void aroundPreStart() {
/* 47 */     Actor$class.aroundPreStart(this);
/*    */   }
/*    */   
/*    */   public void aroundPostStop() {
/* 47 */     Actor$class.aroundPostStop(this);
/*    */   }
/*    */   
/*    */   public void aroundPreRestart(Throwable reason, Option message) {
/* 47 */     Actor$class.aroundPreRestart(this, reason, message);
/*    */   }
/*    */   
/*    */   public void aroundPostRestart(Throwable reason) {
/* 47 */     Actor$class.aroundPostRestart(this, reason);
/*    */   }
/*    */   
/*    */   public SupervisorStrategy supervisorStrategy() {
/* 47 */     return Actor$class.supervisorStrategy(this);
/*    */   }
/*    */   
/*    */   public void preStart() throws Exception {
/* 47 */     Actor$class.preStart(this);
/*    */   }
/*    */   
/*    */   public void postStop() throws Exception {
/* 47 */     Actor$class.postStop(this);
/*    */   }
/*    */   
/*    */   public void preRestart(Throwable reason, Option message) throws Exception {
/* 47 */     Actor$class.preRestart(this, reason, message);
/*    */   }
/*    */   
/*    */   public void postRestart(Throwable reason) throws Exception {
/* 47 */     Actor$class.postRestart(this, reason);
/*    */   }
/*    */   
/*    */   public void unhandled(Object message) {
/* 47 */     Actor$class.unhandled(this, message);
/*    */   }
/*    */   
/*    */   public AbstractActor() {
/* 47 */     Actor$class.$init$(this);
/* 49 */     null;
/* 49 */     this._receive = null;
/*    */   }
/*    */   
/*    */   private PartialFunction<Object, BoxedUnit> _receive() {
/* 49 */     return this._receive;
/*    */   }
/*    */   
/*    */   private void _receive_$eq(PartialFunction<Object, BoxedUnit> x$1) {
/* 49 */     this._receive = x$1;
/*    */   }
/*    */   
/*    */   public void receive(PartialFunction<Object, BoxedUnit> receive) throws IllegalActorStateException {
/* 58 */     if (_receive() == null) {
/* 58 */       _receive_$eq(receive);
/*    */       return;
/*    */     } 
/* 59 */     throw new IllegalActorStateException("Actor behavior has already been set with receive(...), use context().become(...) to change it later");
/*    */   }
/*    */   
/*    */   public AbstractActorContext getContext() {
/* 67 */     return (AbstractActorContext)context();
/*    */   }
/*    */   
/*    */   public PartialFunction<Object, BoxedUnit> receive() {
/* 70 */     if (_receive() == null)
/* 71 */       throw new IllegalActorStateException("Actor behavior has not been set with receive(...)"); 
/*    */     return _receive();
/*    */   }
/*    */   
/*    */   public static Actor.emptyBehavior$ emptyBehavior() {
/*    */     return AbstractActor$.MODULE$.emptyBehavior();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AbstractActor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */