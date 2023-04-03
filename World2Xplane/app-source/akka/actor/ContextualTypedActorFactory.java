/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005Ec\001B\001\003\001\036\0211dQ8oi\026DH/^1m)f\004X\rZ!di>\024h)Y2u_JL(BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001aE\003\001\0219\021R\003\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\t\003\037Ai\021AA\005\003#\t\021\021\003V=qK\022\f5\r^8s\r\006\034Go\034:z!\tI1#\003\002\025\025\t9\001K]8ek\016$\bCA\005\027\023\t9\"B\001\007TKJL\027\r\\5{C\ndW\r\003\005\032\001\tU\r\021\"\001\033\003)!\030\020]3e\003\016$xN]\013\0027A\021q\002H\005\003;\t\0211\003V=qK\022\f5\r^8s\013b$XM\\:j_:D\001b\b\001\003\022\003\006IaG\001\fif\004X\rZ!di>\024\b\005\003\005\"\001\tU\r\021\"\001#\0031\t7\r^8s\r\006\034Go\034:z+\005\031\003CA\b%\023\t)#A\001\007BGR|'oQ8oi\026DH\017\003\005(\001\tE\t\025!\003$\0035\t7\r^8s\r\006\034Go\034:zA!)\021\006\001C\001U\0051A(\0338jiz\"2a\013\027.!\ty\001\001C\003\032Q\001\0071\004C\003\"Q\001\0071\005C\0030\001\021\005\003'\001\bhKR\f5\r^8s%\0264gi\034:\025\005E\"\004CA\b3\023\t\031$A\001\005BGR|'OU3g\021\025)d\0061\001\t\003\025\001(o\034=z\021\0259\004\001\"\0219\0031I7\017V=qK\022\f5\r^8s)\tID\b\005\002\nu%\0211H\003\002\b\005>|G.Z1o\021\025id\0071\001\t\003)\001(o\034=z\037Jtu\016\036\005\b\001\t\t\021\"\001A\003\021\031w\016]=\025\007-\n%\tC\004\032}A\005\t\031A\016\t\017\005r\004\023!a\001G!9A\tAI\001\n\003)\025AD2paf$C-\0324bk2$H%M\013\002\r*\0221dR\026\002\021B\021\021JT\007\002\025*\0211\nT\001\nk:\034\007.Z2lK\022T!!\024\006\002\025\005tgn\034;bi&|g.\003\002P\025\n\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\017E\003\021\023!C\001%\006q1m\0349zI\021,g-Y;mi\022\022T#A*+\005\r:\005bB+\001\003\003%\tEV\001\016aJ|G-^2u!J,g-\033=\026\003]\003\"\001W/\016\003eS!AW.\002\t1\fgn\032\006\0029\006!!.\031<b\023\tq\026L\001\004TiJLgn\032\005\bA\002\t\t\021\"\001b\0031\001(o\0343vGR\f%/\033;z+\005\021\007CA\005d\023\t!'BA\002J]RDqA\032\001\002\002\023\005q-\001\bqe>$Wo\031;FY\026lWM\034;\025\005!\\\007CA\005j\023\tQ'BA\002B]fDq\001\\3\002\002\003\007!-A\002yIEBqA\034\001\002\002\023\005s.A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005\001\bcA9uQ6\t!O\003\002t\025\005Q1m\0347mK\016$\030n\0348\n\005U\024(\001C%uKJ\fGo\034:\t\017]\004\021\021!C\001q\006A1-\0318FcV\fG\016\006\002:s\"9AN^A\001\002\004A\007bB>\001\003\003%\t\005`\001\tQ\006\034\bnQ8eKR\t!\rC\004\001\005\005I\021I@\002\021Q|7\013\036:j]\036$\022a\026\005\n\003\007\001\021\021!C!\003\013\ta!Z9vC2\034HcA\035\002\b!AA.!\001\002\002\003\007\001nB\005\002\f\t\t\t\021#\001\002\016\005Y2i\0348uKb$X/\0317UsB,G-Q2u_J4\025m\031;pef\0042aDA\b\r!\t!!!A\t\002\005E1#BA\b\003')\002cBA\013\0037Y2eK\007\003\003/Q1!!\007\013\003\035\021XO\034;j[\026LA!!\b\002\030\t\t\022IY:ue\006\034GOR;oGRLwN\034\032\t\017%\ny\001\"\001\002\"Q\021\021Q\002\005\t}\006=\021\021!C#\"Q\021qEA\b\003\003%\t)!\013\002\013\005\004\b\017\\=\025\013-\nY#!\f\t\re\t)\0031\001\034\021\031\t\023Q\005a\001G!Q\021\021GA\b\003\003%\t)a\r\002\017Ut\027\r\0359msR!\021QGA!!\025I\021qGA\036\023\r\tID\003\002\007\037B$\030n\0348\021\013%\tidG\022\n\007\005}\"B\001\004UkBdWM\r\005\n\003\007\ny#!AA\002-\n1\001\037\0231\021)\t9%a\004\002\002\023%\021\021J\001\fe\026\fGMU3t_24X\r\006\002\002LA\031\001,!\024\n\007\005=\023L\001\004PE*,7\r\036")
/*     */ public class ContextualTypedActorFactory implements TypedActorFactory, Product, Serializable {
/*     */   private final TypedActorExtension typedActor;
/*     */   
/*     */   private final ActorContext actorFactory;
/*     */   
/*     */   public boolean stop(Object proxy) {
/* 629 */     return TypedActorFactory$class.stop(this, proxy);
/*     */   }
/*     */   
/*     */   public boolean poisonPill(Object proxy) {
/* 629 */     return TypedActorFactory$class.poisonPill(this, proxy);
/*     */   }
/*     */   
/*     */   public <R, T extends R> R typedActorOf(TypedProps props) {
/* 629 */     return (R)TypedActorFactory$class.typedActorOf(this, props);
/*     */   }
/*     */   
/*     */   public <R, T extends R> R typedActorOf(TypedProps props, String name) {
/* 629 */     return (R)TypedActorFactory$class.typedActorOf(this, props, name);
/*     */   }
/*     */   
/*     */   public <R, T extends R> R typedActorOf(TypedProps props, ActorRef actorRef) {
/* 629 */     return (R)TypedActorFactory$class.typedActorOf(this, props, actorRef);
/*     */   }
/*     */   
/*     */   public TypedActorExtension typedActor() {
/* 629 */     return this.typedActor;
/*     */   }
/*     */   
/*     */   public ActorContext actorFactory() {
/* 629 */     return this.actorFactory;
/*     */   }
/*     */   
/*     */   public ContextualTypedActorFactory copy(TypedActorExtension typedActor, ActorContext actorFactory) {
/* 629 */     return new ContextualTypedActorFactory(typedActor, actorFactory);
/*     */   }
/*     */   
/*     */   public TypedActorExtension copy$default$1() {
/* 629 */     return typedActor();
/*     */   }
/*     */   
/*     */   public ActorContext copy$default$2() {
/* 629 */     return actorFactory();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 629 */     return "ContextualTypedActorFactory";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 629 */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 629 */     int i = x$1;
/* 629 */     switch (i) {
/*     */       default:
/* 629 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 629 */     return typedActor();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 629 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 629 */     return x$1 instanceof ContextualTypedActorFactory;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 629 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 629 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 112
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/actor/ContextualTypedActorFactory
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 116
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/ContextualTypedActorFactory
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual typedActor : ()Lakka/actor/TypedActorExtension;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual typedActor : ()Lakka/actor/TypedActorExtension;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 108
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 108
/*     */     //   63: aload_0
/*     */     //   64: invokevirtual actorFactory : ()Lakka/actor/ActorContext;
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual actorFactory : ()Lakka/actor/ActorContext;
/*     */     //   72: astore #6
/*     */     //   74: dup
/*     */     //   75: ifnonnull -> 87
/*     */     //   78: pop
/*     */     //   79: aload #6
/*     */     //   81: ifnull -> 95
/*     */     //   84: goto -> 108
/*     */     //   87: aload #6
/*     */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   92: ifeq -> 108
/*     */     //   95: aload #4
/*     */     //   97: aload_0
/*     */     //   98: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   101: ifeq -> 108
/*     */     //   104: iconst_1
/*     */     //   105: goto -> 109
/*     */     //   108: iconst_0
/*     */     //   109: ifeq -> 116
/*     */     //   112: iconst_1
/*     */     //   113: goto -> 117
/*     */     //   116: iconst_0
/*     */     //   117: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #629	-> 0
/*     */     //   #63	-> 14
/*     */     //   #629	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	118	0	this	Lakka/actor/ContextualTypedActorFactory;
/*     */     //   0	118	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ContextualTypedActorFactory(TypedActorExtension typedActor, ActorContext actorFactory) {
/* 629 */     TypedActorFactory$class.$init$(this);
/* 629 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public ActorRef getActorRefFor(Object proxy) {
/* 630 */     return typedActor().getActorRefFor(proxy);
/*     */   }
/*     */   
/*     */   public boolean isTypedActor(Object proxyOrNot) {
/* 631 */     return typedActor().isTypedActor(proxyOrNot);
/*     */   }
/*     */   
/*     */   public static Function1<Tuple2<TypedActorExtension, ActorContext>, ContextualTypedActorFactory> tupled() {
/*     */     return ContextualTypedActorFactory$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<TypedActorExtension, Function1<ActorContext, ContextualTypedActorFactory>> curried() {
/*     */     return ContextualTypedActorFactory$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ContextualTypedActorFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */