/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSelection;
/*     */ import akka.japi.Util$;
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.IndexedSeq$;
/*     */ import scala.package$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0055g\001B\001\003\005\036\021aAU8vi\026\024(BA\002\005\003\035\021x.\036;j]\036T\021!B\001\005C.\\\027m\001\001\024\t\001Aa\"\005\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\021\005%y\021B\001\t\013\005\035\001&o\0343vGR\004\"!\003\n\n\005MQ!\001D*fe&\fG.\033>bE2,\007\002C\013\001\005+\007I\021\001\f\002\0131|w-[2\026\003]\001\"\001G\r\016\003\tI!A\007\002\003\031I{W\017^5oO2{w-[2\t\021q\001!\021#Q\001\n]\ta\001\\8hS\016\004\003\002\003\020\001\005+\007I\021A\020\002\017I|W\017^3fgV\t\001\005E\002\"M!j\021A\t\006\003G\021\n\021\"[7nkR\f'\r\\3\013\005\025R\021AC2pY2,7\r^5p]&\021qE\t\002\013\023:$W\r_3e'\026\f\bC\001\r*\023\tQ#A\001\004S_V$X-\032\005\tY\001\021\t\022)A\005A\005A!o\\;uK\026\034\b\005C\003/\001\021\005q&\001\004=S:LGO\020\013\004aE\022\004C\001\r\001\021\025)R\0061\001\030\021\035qR\006%AA\002\001BQA\f\001\005\002Q\"\"\001M\033\t\013U\031\004\031A\f\t\0139\002A\021A\034\025\007AB\024\bC\003\026m\001\007q\003C\003\037m\001\007!\bE\002<\001\"j\021\001\020\006\003{y\nA\001\\1oO*\tq(\001\003kCZ\f\027BA!=\005!IE/\032:bE2,\007\"B\"\001\t\003!\025!\002:pkR,GcA#I\033B\021\021BR\005\003\017*\021A!\0268ji\")\021J\021a\001\025\0069Q.Z:tC\036,\007CA\005L\023\ta%BA\002B]fDQA\024\"A\002=\013aa]3oI\026\024\bC\001)T\033\005\t&B\001*\005\003\025\t7\r^8s\023\t!\026K\001\005BGR|'OU3g\021\0251\006\001\"\003X\003\021\031XM\0343\025\t\025C&\f\030\005\0063V\003\r\001K\001\007e>,H/Z3\t\013m+\006\031\001&\002\0075\034x\rC\003O+\002\007q\nC\003_\001\021%q,\001\004v]^\024\030\r\035\013\003\025\002DQaW/A\002)CQA\031\001\005\002\r\f1b^5uQJ{W\017^3fgR\021\001\007\032\005\006K\006\004\r\001I\001\003eNDQa\032\001\005\002!\f\021\"\0313e%>,H/Z3\025\005AJ\007\"B-g\001\004A\003\"B4\001\t\003YGC\001\031m\021\025i'\0161\001P\003\r\021XM\032\005\006O\002!\ta\034\013\003aADQ!\0358A\002I\f1a]3m!\t\0016/\003\002u#\nq\021i\031;peN+G.Z2uS>t\007\"\002<\001\t\0039\030\001\004:f[>4XMU8vi\026,GC\001\031y\021\025IV\0171\001)\021\0251\b\001\"\001{)\t\0014\020C\003ns\002\007q\nC\003w\001\021\005Q\020\006\0021}\")\021\017 a\001e\"I\021\021\001\001\002\002\023\005\0211A\001\005G>\004\030\020F\0031\003\013\t9\001C\004\026B\005\t\031A\f\t\017yy\b\023!a\001A!I\0211\002\001\022\002\023\005\021QB\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\t\tyAK\002\030\003#Y#!a\005\021\t\005U\021qD\007\003\003/QA!!\007\002\034\005IQO\\2iK\016\\W\r\032\006\004\003;Q\021AC1o]>$\030\r^5p]&!\021\021EA\f\005E)hn\0315fG.,GMV1sS\006t7-\032\005\n\003K\001\021\023!C\001\003O\tabY8qs\022\"WMZ1vYR$#'\006\002\002*)\032\001%!\005\t\023\0055\002!!A\005B\005=\022!\0049s_\022,8\r\036)sK\032L\0070\006\002\0022A\0311(a\r\n\007\005UBH\001\004TiJLgn\032\005\n\003s\001\021\021!C\001\003w\tA\002\035:pIV\034G/\021:jif,\"!!\020\021\007%\ty$C\002\002B)\0211!\0238u\021%\t)\005AA\001\n\003\t9%\001\bqe>$Wo\031;FY\026lWM\034;\025\007)\013I\005\003\006\002L\005\r\023\021!a\001\003{\t1\001\037\0232\021%\ty\005AA\001\n\003\n\t&A\bqe>$Wo\031;Ji\026\024\030\r^8s+\t\t\031\006E\003\002V\005]#*D\001%\023\r\tI\006\n\002\t\023R,'/\031;pe\"I\021Q\f\001\002\002\023\005\021qL\001\tG\006tW)];bYR!\021\021MA4!\rI\0211M\005\004\003KR!a\002\"p_2,\027M\034\005\n\003\027\nY&!AA\002)C\021\"a\033\001\003\003%\t%!\034\002\021!\f7\017[\"pI\026$\"!!\020\t\023\005E\004!!A\005B\005M\024\001\003;p'R\024\030N\\4\025\005\005E\002\"CA<\001\005\005I\021IA=\003\031)\027/^1mgR!\021\021MA>\021%\tY%!\036\002\002\003\007!jB\005\002\000\t\t\t\021#\001\002\002\0061!k\\;uKJ\0042\001GAB\r!\t!!!A\t\002\005\0255#BAB\003\017\013\002cBAE\003\037;\002\005M\007\003\003\027S1!!$\013\003\035\021XO\034;j[\026LA!!%\002\f\n\t\022IY:ue\006\034GOR;oGRLwN\034\032\t\0179\n\031\t\"\001\002\026R\021\021\021\021\005\013\003c\n\031)!A\005F\005M\004BCAN\003\007\013\t\021\"!\002\036\006)\021\r\0359msR)\001'a(\002\"\"1Q#!'A\002]A\001BHAM!\003\005\r\001\t\005\013\003K\013\031)!A\005\002\006\035\026aB;oCB\004H.\037\013\005\003S\013)\fE\003\n\003W\013y+C\002\002.*\021aa\0249uS>t\007#B\005\0022^\001\023bAAZ\025\t1A+\0369mKJB\021\"a.\002$\006\005\t\031\001\031\002\007a$\003\007\003\006\002<\006\r\025\023!C\001\003O\t1\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\022\004BCA`\003\007\013\n\021\"\001\002(\005y\021\r\0359ms\022\"WMZ1vYR$#\007\003\006\002D\006\r\025\021!C\005\003\013\f1B]3bIJ+7o\0347wKR\021\021q\031\t\004w\005%\027bAAfy\t1qJ\0316fGR\004")
/*     */ public final class Router implements Product, Serializable {
/*     */   private final RoutingLogic logic;
/*     */   
/*     */   private final IndexedSeq<Routee> routees;
/*     */   
/*     */   public RoutingLogic logic() {
/*  95 */     return this.logic;
/*     */   }
/*     */   
/*     */   public IndexedSeq<Routee> routees() {
/*  95 */     return this.routees;
/*     */   }
/*     */   
/*     */   public Router copy(RoutingLogic logic, IndexedSeq<Routee> routees) {
/*  95 */     return new Router(logic, routees);
/*     */   }
/*     */   
/*     */   public RoutingLogic copy$default$1() {
/*  95 */     return logic();
/*     */   }
/*     */   
/*     */   public IndexedSeq<Routee> copy$default$2() {
/*  95 */     return routees();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*  95 */     return "Router";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*  95 */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*  95 */     int i = x$1;
/*  95 */     switch (i) {
/*     */       default:
/*  95 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/*  95 */     return logic();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*  95 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*  95 */     return x$1 instanceof Router;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  95 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  95 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 103
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/routing/Router
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 107
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/Router
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual logic : ()Lakka/routing/RoutingLogic;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual logic : ()Lakka/routing/RoutingLogic;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 99
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 99
/*     */     //   63: aload_0
/*     */     //   64: invokevirtual routees : ()Lscala/collection/immutable/IndexedSeq;
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual routees : ()Lscala/collection/immutable/IndexedSeq;
/*     */     //   72: astore #6
/*     */     //   74: dup
/*     */     //   75: ifnonnull -> 87
/*     */     //   78: pop
/*     */     //   79: aload #6
/*     */     //   81: ifnull -> 95
/*     */     //   84: goto -> 99
/*     */     //   87: aload #6
/*     */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   92: ifeq -> 99
/*     */     //   95: iconst_1
/*     */     //   96: goto -> 100
/*     */     //   99: iconst_0
/*     */     //   100: ifeq -> 107
/*     */     //   103: iconst_1
/*     */     //   104: goto -> 108
/*     */     //   107: iconst_0
/*     */     //   108: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #95	-> 0
/*     */     //   #63	-> 14
/*     */     //   #95	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	109	0	this	Lakka/routing/Router;
/*     */     //   0	109	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Router(RoutingLogic logic, IndexedSeq<Routee> routees) {
/*  95 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public Router(RoutingLogic logic) {
/* 100 */     this(logic, (IndexedSeq<Routee>)package$.MODULE$.Vector().empty());
/*     */   }
/*     */   
/*     */   public Router(RoutingLogic logic, Iterable routees) {
/* 105 */     this(logic, (IndexedSeq<Routee>)Util$.MODULE$.immutableSeq(routees).toVector());
/*     */   }
/*     */   
/*     */   public void route(Object message, ActorRef sender) {
/* 114 */     Object object = message;
/* 115 */     if (object instanceof Broadcast) {
/* 115 */       Broadcast broadcast = (Broadcast)object;
/* 115 */       Object msg = broadcast.message();
/* 115 */       (new SeveralRoutees(routees())).send(msg, sender);
/* 115 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } else {
/* 116 */       send(logic().select(object, routees()), message, sender);
/* 116 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void send(Routee routee, Object msg, ActorRef sender) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: getstatic akka/routing/NoRoutee$.MODULE$ : Lakka/routing/NoRoutee$;
/*     */     //   4: astore #4
/*     */     //   6: dup
/*     */     //   7: ifnonnull -> 19
/*     */     //   10: pop
/*     */     //   11: aload #4
/*     */     //   13: ifnull -> 27
/*     */     //   16: goto -> 58
/*     */     //   19: aload #4
/*     */     //   21: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   24: ifeq -> 58
/*     */     //   27: aload_3
/*     */     //   28: instanceof akka/actor/InternalActorRef
/*     */     //   31: ifeq -> 58
/*     */     //   34: aload_3
/*     */     //   35: checkcast akka/actor/InternalActorRef
/*     */     //   38: invokevirtual provider : ()Lakka/actor/ActorRefProvider;
/*     */     //   41: invokeinterface deadLetters : ()Lakka/actor/ActorRef;
/*     */     //   46: aload_0
/*     */     //   47: aload_2
/*     */     //   48: invokespecial unwrap : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   51: aload_3
/*     */     //   52: invokevirtual tell : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   55: goto -> 70
/*     */     //   58: aload_1
/*     */     //   59: aload_0
/*     */     //   60: aload_2
/*     */     //   61: invokespecial unwrap : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   64: aload_3
/*     */     //   65: invokeinterface send : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   70: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #120	-> 0
/*     */     //   #121	-> 34
/*     */     //   #123	-> 58
/*     */     //   #120	-> 70
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	71	0	this	Lakka/routing/Router;
/*     */     //   0	71	1	routee	Lakka/routing/Routee;
/*     */     //   0	71	2	msg	Ljava/lang/Object;
/*     */     //   0	71	3	sender	Lakka/actor/ActorRef;
/*     */   }
/*     */   
/*     */   private Object unwrap(Object msg) {
/* 126 */     Object object2, object1 = msg;
/* 127 */     if (object1 instanceof RouterEnvelope) {
/* 127 */       RouterEnvelope routerEnvelope = (RouterEnvelope)object1;
/* 127 */       object2 = routerEnvelope.message();
/*     */     } else {
/* 128 */       object2 = msg;
/*     */     } 
/*     */     return object2;
/*     */   }
/*     */   
/*     */   public Router withRoutees(IndexedSeq<Routee> rs) {
/* 134 */     IndexedSeq<Routee> x$3 = rs;
/* 134 */     RoutingLogic x$4 = copy$default$1();
/* 134 */     return copy(x$4, x$3);
/*     */   }
/*     */   
/*     */   public Router addRoutee(Routee routee) {
/* 139 */     IndexedSeq<Routee> x$5 = (IndexedSeq)routees().$colon$plus(routee, IndexedSeq$.MODULE$.canBuildFrom());
/* 139 */     RoutingLogic x$6 = copy$default$1();
/* 139 */     return copy(x$6, x$5);
/*     */   }
/*     */   
/*     */   public Router addRoutee(ActorRef ref) {
/* 145 */     return addRoutee(new ActorRefRoutee(ref));
/*     */   }
/*     */   
/*     */   public Router addRoutee(ActorSelection sel) {
/* 151 */     return addRoutee(new ActorSelectionRoutee(sel));
/*     */   }
/*     */   
/*     */   public Router removeRoutee(Routee routee) {
/* 156 */     IndexedSeq<Routee> x$7 = (IndexedSeq)routees().filterNot((Function1)new Router$$anonfun$1(this, routee));
/* 156 */     RoutingLogic x$8 = copy$default$1();
/* 156 */     return copy(x$8, x$7);
/*     */   }
/*     */   
/*     */   public class Router$$anonfun$1 extends AbstractFunction1<Routee, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Routee routee$1;
/*     */     
/*     */     public final boolean apply(Routee x$2) {
/* 156 */       Routee routee = this.routee$1;
/* 156 */       if (x$2 == null) {
/* 156 */         if (routee != null);
/* 156 */       } else if (x$2.equals(routee)) {
/*     */       
/*     */       } 
/*     */     }
/*     */     
/*     */     public Router$$anonfun$1(Router $outer, Routee routee$1) {}
/*     */   }
/*     */   
/*     */   public Router removeRoutee(ActorRef ref) {
/* 162 */     return removeRoutee(new ActorRefRoutee(ref));
/*     */   }
/*     */   
/*     */   public Router removeRoutee(ActorSelection sel) {
/* 168 */     return removeRoutee(new ActorSelectionRoutee(sel));
/*     */   }
/*     */   
/*     */   public static IndexedSeq<Routee> apply$default$2() {
/*     */     return Router$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static IndexedSeq<Routee> $lessinit$greater$default$2() {
/*     */     return Router$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static Function1<Tuple2<RoutingLogic, IndexedSeq<Routee>>, Router> tupled() {
/*     */     return Router$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<RoutingLogic, Function1<IndexedSeq<Routee>, Router>> curried() {
/*     */     return Router$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Router.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */