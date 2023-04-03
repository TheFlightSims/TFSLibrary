/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.Predef$;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005ub\001B\001\003\001\036\021Q\"Q2u_JLE-\0328uSRL(BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001a\005\003\001\0219\t\002CA\005\r\033\005Q!\"A\006\002\013M\034\027\r\\1\n\0055Q!AB!osJ+g\r\005\002\n\037%\021\001C\003\002\b!J|G-^2u!\tI!#\003\002\024\025\ta1+\032:jC2L'0\0312mK\"AQ\003\001BK\002\023\005a#A\007d_J\024X\r\\1uS>t\027\nZ\013\002/A\021\021\002G\005\0033)\0211!\0218z\021!Y\002A!E!\002\0239\022AD2peJ,G.\031;j_:LE\r\t\005\t;\001\021)\032!C\001=\005\031!/\0324\026\003}\0012!\003\021#\023\t\t#B\001\004PaRLwN\034\t\003G\021j\021AA\005\003K\t\021\001\"Q2u_J\024VM\032\005\tO\001\021\t\022)A\005?\005!!/\0324!\021\025I\003\001\"\001+\003\031a\024N\\5u}Q\0311\006L\027\021\005\r\002\001\"B\013)\001\0049\002\"B\017)\001\004y\002\"B\030\001\t\003\001\024AB4fiJ+g-F\001#\021\035\021\004!!A\005\002M\nAaY8qsR\0311\006N\033\t\017U\t\004\023!a\001/!9Q$\rI\001\002\004y\002bB\034\001#\003%\t\001O\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\005I$FA\f;W\005Y\004C\001\037B\033\005i$B\001 @\003%)hn\0315fG.,GM\003\002A\025\005Q\021M\0348pi\006$\030n\0348\n\005\tk$!E;oG\",7m[3e-\006\024\030.\0318dK\"9A\tAI\001\n\003)\025AD2paf$C-\0324bk2$HEM\013\002\r*\022qD\017\005\b\021\002\t\t\021\"\021J\0035\001(o\0343vGR\004&/\0324jqV\t!\n\005\002L!6\tAJ\003\002N\035\006!A.\0318h\025\005y\025\001\0026bm\006L!!\025'\003\rM#(/\0338h\021\035\031\006!!A\005\002Q\013A\002\035:pIV\034G/\021:jif,\022!\026\t\003\023YK!a\026\006\003\007%sG\017C\004Z\001\005\005I\021\001.\002\035A\024x\016Z;di\026cW-\\3oiR\021qc\027\005\b9b\013\t\0211\001V\003\rAH%\r\005\b=\002\t\t\021\"\021`\003=\001(o\0343vGRLE/\032:bi>\024X#\0011\021\007\005$w#D\001c\025\t\031'\"\001\006d_2dWm\031;j_:L!!\0322\003\021%#XM]1u_JDqa\032\001\002\002\023\005\001.\001\005dC:,\025/^1m)\tIG\016\005\002\nU&\0211N\003\002\b\005>|G.Z1o\021\035af-!AA\002]AqA\034\001\002\002\023\005s.\001\005iCND7i\0343f)\005)\006bB9\001\003\003%\tE]\001\ti>\034FO]5oOR\t!\nC\004u\001\005\005I\021I;\002\r\025\fX/\0317t)\tIg\017C\004]g\006\005\t\031A\f)\007\001A8\020\005\002\ns&\021!P\003\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022!A\004\b{\n\t\t\021#\001\0035\t5\r^8s\023\022,g\016^5usB\0211e \004\t\003\t\t\t\021#\001\002\002M!q0a\001\022!\035\t)!a\003\030?-j!!a\002\013\007\005%!\"A\004sk:$\030.\\3\n\t\0055\021q\001\002\022\003\n\034HO]1di\032+hn\031;j_:\024\004BB\025\000\t\003\t\t\002F\001\021\035\tx0!A\005FID\021\"a\006\000\003\003%\t)!\007\002\013\005\004\b\017\\=\025\013-\nY\"!\b\t\rU\t)\0021\001\030\021\031i\022Q\003a\001?!I\021\021E@\002\002\023\005\0251E\001\bk:\f\007\017\0357z)\021\t)#!\f\021\t%\001\023q\005\t\006\023\005%rcH\005\004\003WQ!A\002+va2,'\007C\005\0020\005}\021\021!a\001W\005\031\001\020\n\031\t\023\005Mr0!A\005\n\005U\022a\003:fC\022\024Vm]8mm\026$\"!a\016\021\007-\013I$C\002\002<1\023aa\0242kK\016$\b")
/*    */ public class ActorIdentity implements Product, Serializable {
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   private final Object correlationId;
/*    */   
/*    */   private final Option<ActorRef> ref;
/*    */   
/*    */   public Object correlationId() {
/* 73 */     return this.correlationId;
/*    */   }
/*    */   
/*    */   public Option<ActorRef> ref() {
/* 73 */     return this.ref;
/*    */   }
/*    */   
/*    */   public ActorIdentity copy(Object correlationId, Option<ActorRef> ref) {
/* 73 */     return new ActorIdentity(correlationId, ref);
/*    */   }
/*    */   
/*    */   public Object copy$default$1() {
/* 73 */     return correlationId();
/*    */   }
/*    */   
/*    */   public Option<ActorRef> copy$default$2() {
/* 73 */     return ref();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 73 */     return "ActorIdentity";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 73 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 73 */     int i = x$1;
/* 73 */     switch (i) {
/*    */       default:
/* 73 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 73 */     return correlationId();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 73 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 73 */     return x$1 instanceof ActorIdentity;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 73 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 73 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 95
/*    */     //   5: aload_1
/*    */     //   6: astore_2
/*    */     //   7: aload_2
/*    */     //   8: instanceof akka/actor/ActorIdentity
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 99
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/actor/ActorIdentity
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual correlationId : ()Ljava/lang/Object;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual correlationId : ()Ljava/lang/Object;
/*    */     //   40: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*    */     //   43: ifeq -> 91
/*    */     //   46: aload_0
/*    */     //   47: invokevirtual ref : ()Lscala/Option;
/*    */     //   50: aload #4
/*    */     //   52: invokevirtual ref : ()Lscala/Option;
/*    */     //   55: astore #5
/*    */     //   57: dup
/*    */     //   58: ifnonnull -> 70
/*    */     //   61: pop
/*    */     //   62: aload #5
/*    */     //   64: ifnull -> 78
/*    */     //   67: goto -> 91
/*    */     //   70: aload #5
/*    */     //   72: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   75: ifeq -> 91
/*    */     //   78: aload #4
/*    */     //   80: aload_0
/*    */     //   81: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   84: ifeq -> 91
/*    */     //   87: iconst_1
/*    */     //   88: goto -> 92
/*    */     //   91: iconst_0
/*    */     //   92: ifeq -> 99
/*    */     //   95: iconst_1
/*    */     //   96: goto -> 100
/*    */     //   99: iconst_0
/*    */     //   100: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #73	-> 0
/*    */     //   #63	-> 14
/*    */     //   #73	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	101	0	this	Lakka/actor/ActorIdentity;
/*    */     //   0	101	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public ActorIdentity(Object correlationId, Option<ActorRef> ref) {
/* 73 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public ActorRef getRef() {
/* 78 */     return (ActorRef)ref().orNull(Predef$.MODULE$.conforms());
/*    */   }
/*    */   
/*    */   public static Function1<Tuple2<Object, Option<ActorRef>>, ActorIdentity> tupled() {
/*    */     return ActorIdentity$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<Object, Function1<Option<ActorRef>, ActorIdentity>> curried() {
/*    */     return ActorIdentity$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorIdentity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */