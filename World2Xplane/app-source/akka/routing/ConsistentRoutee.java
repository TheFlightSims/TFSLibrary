/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorPath;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSelection;
/*     */ import akka.actor.Address;
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005Uc!B\001\003\001\0221!\001E\"p]NL7\017^3oiJ{W\017^3f\025\t\031A!A\004s_V$\030N\\4\013\003\025\tA!Y6lCN!\001aB\007\021!\tA1\"D\001\n\025\005Q\021!B:dC2\f\027B\001\007\n\005\031\te.\037*fMB\021\001BD\005\003\037%\021q\001\025:pIV\034G\017\005\002\t#%\021!#\003\002\r'\026\024\030.\0317ju\006\024G.\032\005\t)\001\021)\032!C\001-\0051!o\\;uK\026\034\001!F\001\030!\tA\022$D\001\003\023\tQ\"A\001\004S_V$X-\032\005\t9\001\021\t\022)A\005/\0059!o\\;uK\026\004\003\002\003\020\001\005+\007I\021A\020\002\027M,GNZ!eIJ,7o]\013\002AA\021\021\005J\007\002E)\0211\005B\001\006C\016$xN]\005\003K\t\022q!\0213ee\026\0348\017\003\005(\001\tE\t\025!\003!\0031\031X\r\0344BI\022\024Xm]:!\021\025I\003\001\"\001+\003\031a\024N\\5u}Q\0311\006L\027\021\005a\001\001\"\002\013)\001\0049\002\"\002\020)\001\004\001\003\"B\030\001\t\003\002\024\001\003;p'R\024\030N\\4\025\003E\002\"AM\033\017\005!\031\024B\001\033\n\003\031\001&/\0323fM&\021ag\016\002\007'R\024\030N\\4\013\005QJ\001\"B\035\001\t\023Q\024a\006;p'R\024\030N\\4XSRDg-\0367m\003\022$'/Z:t)\t\t4\bC\003=q\001\007Q(\001\003qCRD\007CA\021?\023\ty$EA\005BGR|'\017U1uQ\"9\021\tAA\001\n\003\021\025\001B2paf$2aK\"E\021\035!\002\t%AA\002]AqA\b!\021\002\003\007\001\005C\004G\001E\005I\021A$\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\t\001J\013\002\030\023.\n!\n\005\002L!6\tAJ\003\002N\035\006IQO\\2iK\016\\W\r\032\006\003\037&\t!\"\0318o_R\fG/[8o\023\t\tFJA\tv]\016DWmY6fIZ\013'/[1oG\026Dqa\025\001\022\002\023\005A+\001\bd_BLH\005Z3gCVdG\017\n\032\026\003US#\001I%\t\017]\003\021\021!C!1\006i\001O]8ek\016$\bK]3gSb,\022!\027\t\0035~k\021a\027\006\0039v\013A\001\\1oO*\ta,\001\003kCZ\f\027B\001\034\\\021\035\t\007!!A\005\002\t\fA\002\035:pIV\034G/\021:jif,\022a\031\t\003\021\021L!!Z\005\003\007%sG\017C\004h\001\005\005I\021\0015\002\035A\024x\016Z;di\026cW-\\3oiR\021\021\016\034\t\003\021)L!a[\005\003\007\005s\027\020C\004nM\006\005\t\031A2\002\007a$\023\007C\004p\001\005\005I\021\t9\002\037A\024x\016Z;di&#XM]1u_J,\022!\035\t\004eVLW\"A:\013\005QL\021AC2pY2,7\r^5p]&\021ao\035\002\t\023R,'/\031;pe\"9\001\020AA\001\n\003I\030\001C2b]\026\013X/\0317\025\005il\bC\001\005|\023\ta\030BA\004C_>dW-\0318\t\0175<\030\021!a\001S\"Aq\020AA\001\n\003\n\t!\001\005iCND7i\0343f)\005\031\007\"CA\003\001\005\005I\021IA\004\003\031)\027/^1mgR\031!0!\003\t\0215\f\031!!AA\002%<!\"!\004\003\003\003E\t\001BA\b\003A\031uN\\:jgR,g\016\036*pkR,W\rE\002\031\003#1\021\"\001\002\002\002#\005A!a\005\024\013\005E\021Q\003\t\021\017\005]\021QD\f!W5\021\021\021\004\006\004\0037I\021a\002:v]RLW.Z\005\005\003?\tIBA\tBEN$(/Y2u\rVt7\r^5p]JBq!KA\t\t\003\t\031\003\006\002\002\020!Iq&!\005\002\002\023\025\023q\005\013\0023\"Q\0211FA\t\003\003%\t)!\f\002\013\005\004\b\017\\=\025\013-\ny#!\r\t\rQ\tI\0031\001\030\021\031q\022\021\006a\001A!Q\021QGA\t\003\003%\t)a\016\002\017Ut\027\r\0359msR!\021\021HA#!\025A\0211HA \023\r\ti$\003\002\007\037B$\030n\0348\021\013!\t\te\006\021\n\007\005\r\023B\001\004UkBdWM\r\005\n\003\017\n\031$!AA\002-\n1\001\037\0231\021)\tY%!\005\002\002\023%\021QJ\001\fe\026\fGMU3t_24X\r\006\002\002PA\031!,!\025\n\007\005M3L\001\004PE*,7\r\036")
/*     */ public class ConsistentRoutee implements Product, Serializable {
/*     */   private final Routee routee;
/*     */   
/*     */   private final Address selfAddress;
/*     */   
/*     */   public Routee routee() {
/* 417 */     return this.routee;
/*     */   }
/*     */   
/*     */   public Address selfAddress() {
/* 417 */     return this.selfAddress;
/*     */   }
/*     */   
/*     */   public ConsistentRoutee copy(Routee routee, Address selfAddress) {
/* 417 */     return new ConsistentRoutee(routee, selfAddress);
/*     */   }
/*     */   
/*     */   public Routee copy$default$1() {
/* 417 */     return routee();
/*     */   }
/*     */   
/*     */   public Address copy$default$2() {
/* 417 */     return selfAddress();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 417 */     return "ConsistentRoutee";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 417 */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 417 */     int i = x$1;
/* 417 */     switch (i) {
/*     */       default:
/* 417 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 417 */     return routee();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 417 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 417 */     return x$1 instanceof ConsistentRoutee;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 417 */     return ScalaRunTime$.MODULE$._hashCode(this);
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
/*     */     //   8: instanceof akka/routing/ConsistentRoutee
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 116
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/ConsistentRoutee
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual routee : ()Lakka/routing/Routee;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual routee : ()Lakka/routing/Routee;
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
/*     */     //   64: invokevirtual selfAddress : ()Lakka/actor/Address;
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual selfAddress : ()Lakka/actor/Address;
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
/*     */     //   #417	-> 0
/*     */     //   #63	-> 14
/*     */     //   #417	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	118	0	this	Lakka/routing/ConsistentRoutee;
/*     */     //   0	118	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ConsistentRoutee(Routee routee, Address selfAddress) {
/* 417 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     String str;
/* 419 */     Routee routee = routee();
/* 420 */     if (routee instanceof ActorRefRoutee) {
/* 420 */       ActorRefRoutee actorRefRoutee = (ActorRefRoutee)routee;
/* 420 */       ActorRef ref = actorRefRoutee.ref();
/* 420 */       str = toStringWithfullAddress(ref.path());
/* 421 */     } else if (routee instanceof ActorSelectionRoutee) {
/* 421 */       ActorSelectionRoutee actorSelectionRoutee = (ActorSelectionRoutee)routee;
/* 421 */       ActorSelection sel = actorSelectionRoutee.selection();
/* 421 */       str = (new StringBuilder()).append(toStringWithfullAddress(sel.anchorPath())).append(sel.pathString()).toString();
/*     */     } else {
/* 422 */       str = routee.toString();
/*     */     } 
/*     */     return str;
/*     */   }
/*     */   
/*     */   private String toStringWithfullAddress(ActorPath path) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokeinterface address : ()Lakka/actor/Address;
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: ifnull -> 92
/*     */     //   11: aload_2
/*     */     //   12: invokevirtual host : ()Lscala/Option;
/*     */     //   15: astore_3
/*     */     //   16: aload_2
/*     */     //   17: invokevirtual port : ()Lscala/Option;
/*     */     //   20: astore #4
/*     */     //   22: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   25: aload_3
/*     */     //   26: astore #5
/*     */     //   28: dup
/*     */     //   29: ifnonnull -> 41
/*     */     //   32: pop
/*     */     //   33: aload #5
/*     */     //   35: ifnull -> 49
/*     */     //   38: goto -> 92
/*     */     //   41: aload #5
/*     */     //   43: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   46: ifeq -> 92
/*     */     //   49: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   52: aload #4
/*     */     //   54: astore #6
/*     */     //   56: dup
/*     */     //   57: ifnonnull -> 69
/*     */     //   60: pop
/*     */     //   61: aload #6
/*     */     //   63: ifnull -> 77
/*     */     //   66: goto -> 92
/*     */     //   69: aload #6
/*     */     //   71: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   74: ifeq -> 92
/*     */     //   77: aload_1
/*     */     //   78: aload_0
/*     */     //   79: invokevirtual selfAddress : ()Lakka/actor/Address;
/*     */     //   82: invokeinterface toStringWithAddress : (Lakka/actor/Address;)Ljava/lang/String;
/*     */     //   87: astore #7
/*     */     //   89: goto -> 98
/*     */     //   92: aload_1
/*     */     //   93: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   96: astore #7
/*     */     //   98: aload #7
/*     */     //   100: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #426	-> 0
/*     */     //   #427	-> 11
/*     */     //   #428	-> 92
/*     */     //   #426	-> 98
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	101	0	this	Lakka/routing/ConsistentRoutee;
/*     */     //   0	101	1	path	Lakka/actor/ActorPath;
/*     */   }
/*     */   
/*     */   public static Function1<Tuple2<Routee, Address>, ConsistentRoutee> tupled() {
/*     */     return ConsistentRoutee$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Routee, Function1<Address, ConsistentRoutee>> curried() {
/*     */     return ConsistentRoutee$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ConsistentRoutee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */