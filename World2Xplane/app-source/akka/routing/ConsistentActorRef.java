/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.Address;
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005]c!B\001\003\001\0221!AE\"p]NL7\017^3oi\006\033Go\034:SK\032T!a\001\003\002\017I|W\017^5oO*\tQ!\001\003bW.\f7\003\002\001\b\033A\001\"\001C\006\016\003%Q\021AC\001\006g\016\fG.Y\005\003\031%\021a!\0218z%\0264\007C\001\005\017\023\ty\021BA\004Qe>$Wo\031;\021\005!\t\022B\001\n\n\0051\031VM]5bY&T\030M\0317f\021!!\002A!f\001\n\0031\022\001C1di>\024(+\0324\004\001U\tq\003\005\002\03175\t\021D\003\002\033\t\005)\021m\031;pe&\021A$\007\002\t\003\016$xN\035*fM\"Aa\004\001B\tB\003%q#A\005bGR|'OU3gA!A\001\005\001BK\002\023\005\021%A\006tK24\027\t\0323sKN\034X#\001\022\021\005a\031\023B\001\023\032\005\035\tE\r\032:fgND\001B\n\001\003\022\003\006IAI\001\rg\026dg-\0213ee\026\0348\017\t\005\006Q\001!\t!K\001\007y%t\027\016\036 \025\007)bS\006\005\002,\0015\t!\001C\003\025O\001\007q\003C\003!O\001\007!\005C\0030\001\021\005\003'\001\005u_N#(/\0338h)\005\t\004C\001\0326\035\tA1'\003\0025\023\0051\001K]3eK\032L!AN\034\003\rM#(/\0338h\025\t!\024\002C\004:\001\005\005I\021\001\036\002\t\r|\007/\037\013\004Umb\004b\002\0139!\003\005\ra\006\005\bAa\002\n\0211\001#\021\035q\004!%A\005\002}\nabY8qs\022\"WMZ1vYR$\023'F\001AU\t9\022iK\001C!\t\031\005*D\001E\025\t)e)A\005v]\016DWmY6fI*\021q)C\001\013C:tw\016^1uS>t\027BA%E\005E)hn\0315fG.,GMV1sS\006t7-\032\005\b\027\002\t\n\021\"\001M\0039\031w\016]=%I\0264\027-\0367uII*\022!\024\026\003E\005Cqa\024\001\002\002\023\005\003+A\007qe>$Wo\031;Qe\0264\027\016_\013\002#B\021!kV\007\002'*\021A+V\001\005Y\006twMC\001W\003\021Q\027M^1\n\005Y\032\006bB-\001\003\003%\tAW\001\raJ|G-^2u\003JLG/_\013\0027B\021\001\002X\005\003;&\0211!\0238u\021\035y\006!!A\005\002\001\fa\002\035:pIV\034G/\0227f[\026tG\017\006\002bIB\021\001BY\005\003G&\0211!\0218z\021\035)g,!AA\002m\0131\001\037\0232\021\0359\007!!A\005B!\fq\002\035:pIV\034G/\023;fe\006$xN]\013\002SB\031!.\\1\016\003-T!\001\\\005\002\025\r|G\016\\3di&|g.\003\002oW\nA\021\n^3sCR|'\017C\004q\001\005\005I\021A9\002\021\r\fg.R9vC2$\"A];\021\005!\031\030B\001;\n\005\035\021un\0347fC:Dq!Z8\002\002\003\007\021\rC\004x\001\005\005I\021\t=\002\021!\f7\017[\"pI\026$\022a\027\005\bu\002\t\t\021\"\021|\003\031)\027/^1mgR\021!\017 \005\bKf\f\t\0211\001bQ\031\001a0a\001\002\bA\021\001b`\005\004\003\003I!A\0033faJ,7-\031;fI\006\022\021QA\001\035%\026\004H.Y2fI\002\022\027\020I\"p]NL7\017^3oiJ{W\017^3fC\t\tI!A\0023]M:!\"!\004\003\003\003E\t\001BA\b\003I\031uN\\:jgR,g\016^!di>\024(+\0324\021\007-\n\tBB\005\002\005\005\005\t\022\001\003\002\024M)\021\021CA\013!A9\021qCA\017/\tRSBAA\r\025\r\tY\"C\001\beVtG/[7f\023\021\ty\"!\007\003#\005\0237\017\036:bGR4UO\\2uS>t'\007C\004)\003#!\t!a\t\025\005\005=\001\"C\030\002\022\005\005IQIA\024)\005\t\006BCA\026\003#\t\t\021\"!\002.\005)\021\r\0359msR)!&a\f\0022!1A#!\013A\002]Aa\001IA\025\001\004\021\003BCA\033\003#\t\t\021\"!\0028\0059QO\\1qa2LH\003BA\035\003\013\002R\001CA\036\003I1!!\020\n\005\031y\005\017^5p]B)\001\"!\021\030E%\031\0211I\005\003\rQ+\b\017\\33\021%\t9%a\r\002\002\003\007!&A\002yIAB!\"a\023\002\022\005\005I\021BA'\003-\021X-\0313SKN|GN^3\025\005\005=\003c\001*\002R%\031\0211K*\003\r=\023'.Z2uQ\035\t\tB`A\002\003\017\001")
/*     */ public class ConsistentActorRef implements Product, Serializable {
/*     */   private final ActorRef actorRef;
/*     */   
/*     */   private final Address selfAddress;
/*     */   
/*     */   public ActorRef actorRef() {
/* 564 */     return this.actorRef;
/*     */   }
/*     */   
/*     */   public Address selfAddress() {
/* 564 */     return this.selfAddress;
/*     */   }
/*     */   
/*     */   public ConsistentActorRef copy(ActorRef actorRef, Address selfAddress) {
/* 564 */     return new ConsistentActorRef(actorRef, selfAddress);
/*     */   }
/*     */   
/*     */   public ActorRef copy$default$1() {
/* 564 */     return actorRef();
/*     */   }
/*     */   
/*     */   public Address copy$default$2() {
/* 564 */     return selfAddress();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 564 */     return "ConsistentActorRef";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 564 */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 564 */     int i = x$1;
/* 564 */     switch (i) {
/*     */       default:
/* 564 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 564 */     return actorRef();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 564 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 564 */     return x$1 instanceof ConsistentActorRef;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 564 */     return ScalaRunTime$.MODULE$._hashCode(this);
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
/*     */     //   8: instanceof akka/routing/ConsistentActorRef
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 116
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/ConsistentActorRef
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual actorRef : ()Lakka/actor/ActorRef;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual actorRef : ()Lakka/actor/ActorRef;
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
/*     */     //   #564	-> 0
/*     */     //   #63	-> 14
/*     */     //   #564	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	118	0	this	Lakka/routing/ConsistentActorRef;
/*     */     //   0	118	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ConsistentActorRef(ActorRef actorRef, Address selfAddress) {
/* 564 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual actorRef : ()Lakka/actor/ActorRef;
/*     */     //   4: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   7: invokeinterface address : ()Lakka/actor/Address;
/*     */     //   12: astore_1
/*     */     //   13: aload_1
/*     */     //   14: ifnull -> 102
/*     */     //   17: aload_1
/*     */     //   18: invokevirtual host : ()Lscala/Option;
/*     */     //   21: astore_2
/*     */     //   22: aload_1
/*     */     //   23: invokevirtual port : ()Lscala/Option;
/*     */     //   26: astore_3
/*     */     //   27: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   30: aload_2
/*     */     //   31: astore #4
/*     */     //   33: dup
/*     */     //   34: ifnonnull -> 46
/*     */     //   37: pop
/*     */     //   38: aload #4
/*     */     //   40: ifnull -> 54
/*     */     //   43: goto -> 102
/*     */     //   46: aload #4
/*     */     //   48: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   51: ifeq -> 102
/*     */     //   54: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   57: aload_3
/*     */     //   58: astore #5
/*     */     //   60: dup
/*     */     //   61: ifnonnull -> 73
/*     */     //   64: pop
/*     */     //   65: aload #5
/*     */     //   67: ifnull -> 81
/*     */     //   70: goto -> 102
/*     */     //   73: aload #5
/*     */     //   75: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   78: ifeq -> 102
/*     */     //   81: aload_0
/*     */     //   82: invokevirtual actorRef : ()Lakka/actor/ActorRef;
/*     */     //   85: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   88: aload_0
/*     */     //   89: invokevirtual selfAddress : ()Lakka/actor/Address;
/*     */     //   92: invokeinterface toStringWithAddress : (Lakka/actor/Address;)Ljava/lang/String;
/*     */     //   97: astore #6
/*     */     //   99: goto -> 114
/*     */     //   102: aload_0
/*     */     //   103: invokevirtual actorRef : ()Lakka/actor/ActorRef;
/*     */     //   106: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   109: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   112: astore #6
/*     */     //   114: aload #6
/*     */     //   116: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #566	-> 0
/*     */     //   #567	-> 17
/*     */     //   #568	-> 102
/*     */     //   #566	-> 114
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	117	0	this	Lakka/routing/ConsistentActorRef;
/*     */   }
/*     */   
/*     */   public static Function1<Tuple2<ActorRef, Address>, ConsistentActorRef> tupled() {
/*     */     return ConsistentActorRef$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<ActorRef, Function1<Address, ConsistentActorRef>> curried() {
/*     */     return ConsistentActorRef$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ConsistentActorRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */