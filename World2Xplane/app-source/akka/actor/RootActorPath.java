/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.Product;
/*     */ import scala.StringContext;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.StringAdd$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005-f\001B\001\003\005\036\021QBU8pi\006\033Go\034:QCRD'BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001aE\003\001\0219\021R\003\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\t\003\037Ai\021AA\005\003#\t\021\021\"Q2u_J\004\026\r\0365\021\005%\031\022B\001\013\013\005\035\001&o\0343vGR\004\"!\003\f\n\005]Q!\001D*fe&\fG.\033>bE2,\007\002C\r\001\005+\007I\021\001\016\002\017\005$GM]3tgV\t1\004\005\002\0209%\021QD\001\002\b\003\022$'/Z:t\021!y\002A!E!\002\023Y\022\001C1eIJ,7o\035\021\t\021\005\002!Q3A\005\002\t\nAA\\1nKV\t1\005\005\002%O9\021\021\"J\005\003M)\ta\001\025:fI\0264\027B\001\025*\005\031\031FO]5oO*\021aE\003\005\tW\001\021\t\022)A\005G\005)a.Y7fA!)Q\006\001C\001]\0051A(\0338jiz\"2a\f\0312!\ty\001\001C\003\032Y\001\0071\004C\004\"YA\005\t\031A\022\t\013M\002A\021\t\033\002\rA\f'/\0328u+\005q\001\"\002\034\001\t\003:\024\001\002:p_R,\022a\f\005\006s\001!\tEO\001\005I\021Lg\017\006\002\017w!)A\b\017a\001G\005)1\r[5mI\")a\b\001C!\005AQ\r\\3nK:$8/F\001A!\r\teiI\007\002\005*\0211\tR\001\nS6lW\017^1cY\026T!!\022\006\002\025\r|G\016\\3di&|g.\003\002H\005\nA\021\n^3sC\ndW\rC\004J\001\t\007I\021\t\022\002\021Q|7\013\036:j]\036Daa\023\001!\002\023\031\023!\003;p'R\024\030N\\4!\021\035i\005A1A\005B\t\nQ\003^8TKJL\027\r\\5{CRLwN\034$pe6\fG\017\003\004P\001\001\006IaI\001\027i>\034VM]5bY&T\030\r^5p]\032{'/\\1uA!)\021\013\001C!%\006\031Bo\\*ue&twmV5uQ\006#GM]3tgR\0211e\025\005\006)B\003\raG\001\005C\022$'\017C\003W\001\021\005s+\001\021u_N+'/[1mSj\fG/[8o\r>\024X.\031;XSRD\027\t\0323sKN\034HCA\022Y\021\025!V\0131\001\034\021\025Q\006\001\"\021\\\003%\031w.\0349be\026$v\016\006\002]?B\021\021\"X\005\003=*\0211!\0238u\021\025\001\027\f1\001\017\003\025yG\017[3s\021\031\021\007\001\"\001\005G\006\031Q/\0333\026\003qCa!\032\001\005B\0211\027aB<ji\",\026\016\032\013\003\035\035DQA\0313A\002qCq!\033\001\002\002\023\005!.\001\003d_BLHcA\030lY\"9\021\004\033I\001\002\004Y\002bB\021i!\003\005\ra\t\005\b]\002\t\n\021\"\001p\0039\031w\016]=%I\0264\027-\0367uIE*\022\001\035\026\0037E\\\023A\035\t\003gbl\021\001\036\006\003kZ\f\021\"\0368dQ\026\0347.\0323\013\005]T\021AC1o]>$\030\r^5p]&\021\021\020\036\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007bB>\001#\003%\t\001`\001\017G>\004\030\020\n3fM\006,H\016\036\0233+\005i(FA\022r\021!y\b!!A\005B\005\005\021!\0049s_\022,8\r\036)sK\032L\0070\006\002\002\004A!\021QAA\b\033\t\t9A\003\003\002\n\005-\021\001\0027b]\036T!!!\004\002\t)\fg/Y\005\004Q\005\035\001\002CA\n\001\005\005I\021A2\002\031A\024x\016Z;di\006\023\030\016^=\t\023\005]\001!!A\005\002\005e\021A\0049s_\022,8\r^#mK6,g\016\036\013\005\0037\t\t\003E\002\n\003;I1!a\b\013\005\r\te.\037\005\n\003G\t)\"!AA\002q\0131\001\037\0232\021%\t9\003AA\001\n\003\nI#A\bqe>$Wo\031;Ji\026\024\030\r^8s+\t\tY\003\005\004\002.\005=\0221D\007\002\t&\031\021\021\007#\003\021%#XM]1u_JD\021\"!\016\001\003\003%\t!a\016\002\021\r\fg.R9vC2$B!!\017\002@A\031\021\"a\017\n\007\005u\"BA\004C_>dW-\0318\t\025\005\r\0221GA\001\002\004\tY\002C\005\002D\001\t\t\021\"\021\002F\005A\001.Y:i\007>$W\rF\001]\021%\tI\005AA\001\n\003\nY%\001\004fcV\fGn\035\013\005\003s\ti\005\003\006\002$\005\035\023\021!a\001\0037AS\001AA)\003/\0022!CA*\023\r\t)F\003\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022!A\004\n\0037\022\021\021!E\001\003;\nQBU8pi\006\033Go\034:QCRD\007cA\b\002`\031A\021AAA\001\022\003\t\tgE\003\002`\005\rT\003E\004\002f\005-4dI\030\016\005\005\035$bAA5\025\0059!/\0368uS6,\027\002BA7\003O\022\021#\0212tiJ\f7\r\036$v]\016$\030n\03483\021\035i\023q\fC\001\003c\"\"!!\030\t\023%\013y&!A\005F\005UDCAA\002\021)\tI(a\030\002\002\023\005\0251P\001\006CB\004H.\037\013\006_\005u\024q\020\005\0073\005]\004\031A\016\t\021\005\n9\b%AA\002\rB!\"a!\002`\005\005I\021QAC\003\035)h.\0319qYf$B!a\"\002\024B)\021\"!#\002\016&\031\0211\022\006\003\r=\003H/[8o!\025I\021qR\016$\023\r\t\tJ\003\002\007)V\004H.\032\032\t\023\005U\025\021QA\001\002\004y\023a\001=%a!I\021\021TA0#\003%\t\001`\001\020CB\004H.\037\023eK\032\fW\017\034;%e!I\021QTA0#\003%\t\001`\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017\n\032\t\025\005\005\026qLA\001\n\023\t\031+A\006sK\006$'+Z:pYZ,GCAAS!\021\t)!a*\n\t\005%\026q\001\002\007\037\nTWm\031;")
/*     */ public final class RootActorPath implements ActorPath, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Address address;
/*     */   
/*     */   private final String name;
/*     */   
/*     */   private final String toString;
/*     */   
/*     */   private final String toSerializationFormat;
/*     */   
/*     */   public ActorPath child(String child) {
/* 152 */     return ActorPath$class.child(this, child);
/*     */   }
/*     */   
/*     */   public ActorPath $div(Iterable child) {
/* 152 */     return ActorPath$class.$div(this, child);
/*     */   }
/*     */   
/*     */   public ActorPath descendant(Iterable names) {
/* 152 */     return ActorPath$class.descendant(this, names);
/*     */   }
/*     */   
/*     */   public Iterable<String> getElements() {
/* 152 */     return ActorPath$class.getElements(this);
/*     */   }
/*     */   
/*     */   public String toStringWithoutAddress() {
/* 152 */     return ActorPath$class.toStringWithoutAddress(this);
/*     */   }
/*     */   
/*     */   public Address address() {
/* 152 */     return this.address;
/*     */   }
/*     */   
/*     */   public String name() {
/* 152 */     return this.name;
/*     */   }
/*     */   
/*     */   public RootActorPath copy(Address address, String name) {
/* 152 */     return new RootActorPath(address, name);
/*     */   }
/*     */   
/*     */   public Address copy$default$1() {
/* 152 */     return address();
/*     */   }
/*     */   
/*     */   public String copy$default$2() {
/* 152 */     return name();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 152 */     return "RootActorPath";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 152 */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 152 */     int i = x$1;
/* 152 */     switch (i) {
/*     */       default:
/* 152 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 152 */     return address();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 152 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 152 */     return x$1 instanceof RootActorPath;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 152 */     return ScalaRunTime$.MODULE$._hashCode(this);
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
/*     */     //   8: instanceof akka/actor/RootActorPath
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 107
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/RootActorPath
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual address : ()Lakka/actor/Address;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual address : ()Lakka/actor/Address;
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
/*     */     //   64: invokevirtual name : ()Ljava/lang/String;
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual name : ()Ljava/lang/String;
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
/*     */     //   #152	-> 0
/*     */     //   #63	-> 14
/*     */     //   #152	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	109	0	this	Lakka/actor/RootActorPath;
/*     */     //   0	109	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public RootActorPath(Address address, String name) {
/* 152 */     ActorPath$class.$init$(this);
/* 152 */     Product.class.$init$(this);
/* 165 */     this.toString = StringAdd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(address), name);
/* 167 */     this.toSerializationFormat = toString();
/*     */   }
/*     */   
/*     */   public ActorPath parent() {
/*     */     return this;
/*     */   }
/*     */   
/*     */   public RootActorPath root() {
/*     */     return this;
/*     */   }
/*     */   
/*     */   public ActorPath $div(String child) {
/*     */     Tuple2<String, Object> tuple2 = ActorCell$.MODULE$.splitNameAndUid(child);
/*     */     if (tuple2 != null) {
/*     */       String childName = (String)tuple2._1();
/*     */       int uid = tuple2._2$mcI$sp();
/*     */       Tuple2 tuple22 = new Tuple2(childName, BoxesRunTime.boxToInteger(uid)), tuple21 = tuple22;
/*     */       String str1 = (String)tuple21._1();
/*     */       int i = tuple21._2$mcI$sp();
/*     */       return new ChildActorPath(this, str1, i);
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   public Iterable<String> elements() {
/*     */     return ActorPath$.MODULE$.emptyActorPath();
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     return this.toString;
/*     */   }
/*     */   
/*     */   public String toSerializationFormat() {
/* 167 */     return this.toSerializationFormat;
/*     */   }
/*     */   
/*     */   public String toStringWithAddress(Address addr) {
/* 170 */     return address().host().isDefined() ? StringAdd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(address()), name()) : StringAdd$.MODULE$
/* 171 */       .$plus$extension(Predef$.MODULE$.any2stringadd(addr), name());
/*     */   }
/*     */   
/*     */   public String toSerializationFormatWithAddress(Address addr) {
/* 173 */     return toStringWithAddress(addr);
/*     */   }
/*     */   
/*     */   public int compareTo(ActorPath other) {
/* 175 */     ActorPath actorPath = other;
/* 176 */     if (actorPath instanceof RootActorPath) {
/* 176 */       RootActorPath rootActorPath = (RootActorPath)actorPath;
/* 176 */       int i = toString().compareTo(rootActorPath.toString());
/*     */     } else {
/* 177 */       if (actorPath instanceof ChildActorPath)
/* 177 */         return 1; 
/*     */       throw new MatchError(actorPath);
/*     */     } 
/*     */     return SYNTHETIC_LOCAL_VARIABLE_4;
/*     */   }
/*     */   
/*     */   public int uid() {
/* 183 */     return 0;
/*     */   }
/*     */   
/*     */   public ActorPath withUid(int uid) {
/* 189 */     if (uid == 0)
/* 189 */       return this; 
/* 190 */     (new String[3])[0] = "RootActorPath must have undefinedUid, [";
/* 190 */     (new String[3])[1] = " != ";
/* 190 */     (new String[3])[2] = "";
/* 190 */     throw new IllegalStateException((new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s(Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(uid), BoxesRunTime.boxToInteger(0) })));
/*     */   }
/*     */   
/*     */   public static String $lessinit$greater$default$2() {
/*     */     return RootActorPath$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static String apply$default$2() {
/*     */     return RootActorPath$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static Function1<Tuple2<Address, String>, RootActorPath> tupled() {
/*     */     return RootActorPath$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Address, Function1<String, RootActorPath>> curried() {
/*     */     return RootActorPath$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\RootActorPath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */