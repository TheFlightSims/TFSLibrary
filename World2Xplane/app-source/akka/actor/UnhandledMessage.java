/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005=d\001B\001\003\001\036\021\001#\0268iC:$G.\0323NKN\034\030mZ3\013\005\r!\021!B1di>\024(\"A\003\002\t\005\\7.Y\002\001'\021\001\001BD\t\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g!\tIq\"\003\002\021\025\t9\001K]8ek\016$\bCA\005\023\023\t\031\"B\001\007TKJL\027\r\\5{C\ndW\r\003\005\026\001\tU\r\021\"\001\027\003\035iWm]:bO\026,\022a\006\t\003\023aI!!\007\006\003\007\005s\027\020\003\005\034\001\tE\t\025!\003\030\003!iWm]:bO\026\004\003F\001\016\036!\tq\022%D\001 \025\t\001#\"A\003cK\006t7/\003\002#?\ta!)Z1o!J|\007/\032:us\")A\005\001C\001K\005Qq-\032;NKN\034\030mZ3\025\003]A\001b\n\001\003\026\004%\t\001K\001\007g\026tG-\032:\026\003%\002\"AK\026\016\003\tI!\001\f\002\003\021\005\033Go\034:SK\032D\001B\f\001\003\022\003\006I!K\001\bg\026tG-\032:!Q\tiS\004C\0032\001\021\005!'A\005hKR\034VM\0343feR\t\021\006\003\0055\001\tU\r\021\"\001)\003%\021XmY5qS\026tG\017\003\0057\001\tE\t\025!\003*\003)\021XmY5qS\026tG\017\t\025\003kuAQ!\017\001\005\002I\nAbZ3u%\026\034\027\016]5f]RDQa\017\001\005\002q\na\001P5oSRtD\003B\037?\001\003\"A\013\001\t\013UQ\004\031A\f\t\013\035R\004\031A\025\t\013QR\004\031A\025\t\017\t\003\021\021!C\001\007\006!1m\0349z)\021iD)\022$\t\017U\t\005\023!a\001/!9q%\021I\001\002\004I\003b\002\033B!\003\005\r!\013\005\b\021\002\t\n\021\"\001J\0039\031w\016]=%I\0264\027-\0367uIE*\022A\023\026\003/-[\023\001\024\t\003\033Jk\021A\024\006\003\037B\013\021\"\0368dQ\026\0347.\0323\013\005ES\021AC1o]>$\030\r^5p]&\0211K\024\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007bB+\001#\003%\tAV\001\017G>\004\030\020\n3fM\006,H\016\036\0233+\0059&FA\025L\021\035I\006!%A\005\002Y\013abY8qs\022\"WMZ1vYR$3\007C\004\\\001\005\005I\021\t/\002\033A\024x\016Z;diB\023XMZ5y+\005i\006C\0010d\033\005y&B\0011b\003\021a\027M\\4\013\003\t\fAA[1wC&\021Am\030\002\007'R\024\030N\\4\t\017\031\004\021\021!C\001O\006a\001O]8ek\016$\030I]5usV\t\001\016\005\002\nS&\021!N\003\002\004\023:$\bb\0027\001\003\003%\t!\\\001\017aJ|G-^2u\0132,W.\0328u)\t9b\016C\004pW\006\005\t\031\0015\002\007a$\023\007C\004r\001\005\005I\021\t:\002\037A\024x\016Z;di&#XM]1u_J,\022a\035\t\004i^<R\"A;\013\005YT\021AC2pY2,7\r^5p]&\021\0010\036\002\t\023R,'/\031;pe\"9!\020AA\001\n\003Y\030\001C2b]\026\013X/\0317\025\005q|\bCA\005~\023\tq(BA\004C_>dW-\0318\t\017=L\030\021!a\001/!I\0211\001\001\002\002\023\005\023QA\001\tQ\006\034\bnQ8eKR\t\001\016C\005\002\n\001\t\t\021\"\021\002\f\005AAo\\*ue&tw\rF\001^\021%\ty\001AA\001\n\003\n\t\"\001\004fcV\fGn\035\013\004y\006M\001\002C8\002\016\005\005\t\031A\f)\013\001\t9\"!\b\021\007%\tI\"C\002\002\034)\021\001cU3sS\006dg+\032:tS>tW+\023#\037\003\0059\021\"!\t\003\003\003E\t!a\t\002!Us\007.\0318eY\026$W*Z:tC\036,\007c\001\026\002&\031A\021AAA\001\022\003\t9cE\003\002&\005%\022\003\005\005\002,\005Er#K\025>\033\t\tiCC\002\0020)\tqA];oi&lW-\003\003\0024\0055\"!E!cgR\024\030m\031;Gk:\034G/[8og!91(!\n\005\002\005]BCAA\022\021)\tI!!\n\002\002\023\025\0231\002\005\013\003{\t)#!A\005\002\006}\022!B1qa2LHcB\037\002B\005\025\023\021\n\005\007+\005m\002\031A\f)\007\005\005S\004\003\004(\003w\001\r!\013\025\004\003\013j\002B\002\033\002<\001\007\021\006K\002\002JuA!\"a\024\002&\005\005I\021QA)\003\035)h.\0319qYf$B!a\025\002`A)\021\"!\026\002Z%\031\021q\013\006\003\r=\003H/[8o!\031I\0211L\f*S%\031\021Q\f\006\003\rQ+\b\017\\34\021%\t\t'!\024\002\002\003\007Q(A\002yIAB!\"!\032\002&\005\005I\021BA4\003-\021X-\0313SKN|GN^3\025\005\005%\004c\0010\002l%\031\021QN0\003\r=\023'.Z2u\001")
/*     */ public class UnhandledMessage implements Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Object message;
/*     */   
/*     */   private final ActorRef sender;
/*     */   
/*     */   private final ActorRef recipient;
/*     */   
/*     */   public static Function1<Tuple3<Object, ActorRef, ActorRef>, UnhandledMessage> tupled() {
/*     */     return UnhandledMessage$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Object, Function1<ActorRef, Function1<ActorRef, UnhandledMessage>>> curried() {
/*     */     return UnhandledMessage$.MODULE$.curried();
/*     */   }
/*     */   
/*     */   public Object message() {
/* 245 */     return this.message;
/*     */   }
/*     */   
/*     */   public ActorRef sender() {
/* 245 */     return this.sender;
/*     */   }
/*     */   
/*     */   public ActorRef recipient() {
/* 245 */     return this.recipient;
/*     */   }
/*     */   
/*     */   public Object getMessage() {
/* 245 */     return message();
/*     */   }
/*     */   
/*     */   public ActorRef getSender() {
/* 245 */     return sender();
/*     */   }
/*     */   
/*     */   public ActorRef getRecipient() {
/* 245 */     return recipient();
/*     */   }
/*     */   
/*     */   public UnhandledMessage copy(Object message, ActorRef sender, ActorRef recipient) {
/* 245 */     return new UnhandledMessage(message, sender, recipient);
/*     */   }
/*     */   
/*     */   public Object copy$default$1() {
/* 245 */     return message();
/*     */   }
/*     */   
/*     */   public ActorRef copy$default$2() {
/* 245 */     return sender();
/*     */   }
/*     */   
/*     */   public ActorRef copy$default$3() {
/* 245 */     return recipient();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 245 */     return "UnhandledMessage";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 245 */     return 3;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 245 */     int i = x$1;
/* 245 */     switch (i) {
/*     */       default:
/* 245 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 245 */     return message();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 245 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 245 */     return x$1 instanceof UnhandledMessage;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 245 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 245 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 127
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/actor/UnhandledMessage
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 131
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/UnhandledMessage
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual message : ()Ljava/lang/Object;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual message : ()Ljava/lang/Object;
/*     */     //   40: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   43: ifeq -> 123
/*     */     //   46: aload_0
/*     */     //   47: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */     //   50: aload #4
/*     */     //   52: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */     //   55: astore #5
/*     */     //   57: dup
/*     */     //   58: ifnonnull -> 70
/*     */     //   61: pop
/*     */     //   62: aload #5
/*     */     //   64: ifnull -> 78
/*     */     //   67: goto -> 123
/*     */     //   70: aload #5
/*     */     //   72: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   75: ifeq -> 123
/*     */     //   78: aload_0
/*     */     //   79: invokevirtual recipient : ()Lakka/actor/ActorRef;
/*     */     //   82: aload #4
/*     */     //   84: invokevirtual recipient : ()Lakka/actor/ActorRef;
/*     */     //   87: astore #6
/*     */     //   89: dup
/*     */     //   90: ifnonnull -> 102
/*     */     //   93: pop
/*     */     //   94: aload #6
/*     */     //   96: ifnull -> 110
/*     */     //   99: goto -> 123
/*     */     //   102: aload #6
/*     */     //   104: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   107: ifeq -> 123
/*     */     //   110: aload #4
/*     */     //   112: aload_0
/*     */     //   113: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   116: ifeq -> 123
/*     */     //   119: iconst_1
/*     */     //   120: goto -> 124
/*     */     //   123: iconst_0
/*     */     //   124: ifeq -> 131
/*     */     //   127: iconst_1
/*     */     //   128: goto -> 132
/*     */     //   131: iconst_0
/*     */     //   132: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #245	-> 0
/*     */     //   #63	-> 14
/*     */     //   #245	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	133	0	this	Lakka/actor/UnhandledMessage;
/*     */     //   0	133	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public UnhandledMessage(Object message, ActorRef sender, ActorRef recipient) {
/* 245 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\UnhandledMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */