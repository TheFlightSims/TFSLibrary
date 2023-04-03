/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005mc!B\001\003\001\0221!!F!di>\0248+\0327fGRLwN\\'fgN\fw-\032\006\003\007\021\tQ!Y2u_JT\021!B\001\005C.\\\027m\005\004\001\0175\tBc\006\t\003\021-i\021!\003\006\002\025\005)1oY1mC&\021A\"\003\002\007\003:L(+\0324\021\0059yQ\"\001\002\n\005A\021!aE!vi>\024VmY3jm\026$W*Z:tC\036,\007C\001\b\023\023\t\031\"AA\bQ_N\034\030N\0317z\021\006\024XNZ;m!\tAQ#\003\002\027\023\t9\001K]8ek\016$\bC\001\005\031\023\tI\022B\001\007TKJL\027\r\\5{C\ndW\r\003\005\034\001\tU\r\021\"\001\036\003\ri7oZ\002\001+\005q\002C\001\005 \023\t\001\023BA\002B]fD\001B\t\001\003\022\003\006IAH\001\005[N<\007\005\003\005%\001\tU\r\021\"\001&\003!)G.Z7f]R\034X#\001\024\021\007\035bc&D\001)\025\tI#&A\005j[6,H/\0312mK*\0211&C\001\013G>dG.Z2uS>t\027BA\027)\005!IE/\032:bE2,\007C\001\b0\023\t\001$A\001\013TK2,7\r^5p]B\013G\017[#mK6,g\016\036\005\te\001\021\t\022)A\005M\005IQ\r\\3nK:$8\017\t\005\006i\001!\t!N\001\007y%t\027\016\036 \025\007Y:\004\b\005\002\017\001!)1d\ra\001=!)Ae\ra\001M!)!\b\001C\001w\005y\021\016Z3oi&4\027PU3rk\026\034H/F\001=!\rAQhP\005\003}%\021aa\0249uS>t\007C\001\bA\023\t\t%A\001\005JI\026tG/\0334z\021\035\031\005!!A\005\002\021\013AaY8qsR\031a'\022$\t\017m\021\005\023!a\001=!9AE\021I\001\002\0041\003b\002%\001#\003%\t!S\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\005Q%F\001\020LW\005a\005CA'S\033\005q%BA(Q\003%)hn\0315fG.,GM\003\002R\023\005Q\021M\0348pi\006$\030n\0348\n\005Ms%!E;oG\",7m[3e-\006\024\030.\0318dK\"9Q\013AI\001\n\0031\026AD2paf$C-\0324bk2$HEM\013\002/*\022ae\023\005\b3\002\t\t\021\"\021[\0035\001(o\0343vGR\004&/\0324jqV\t1\f\005\002]C6\tQL\003\002_?\006!A.\0318h\025\005\001\027\001\0026bm\006L!AY/\003\rM#(/\0338h\021\035!\007!!A\005\002\025\fA\002\035:pIV\034G/\021:jif,\022A\032\t\003\021\035L!\001[\005\003\007%sG\017C\004k\001\005\005I\021A6\002\035A\024x\016Z;di\026cW-\\3oiR\021a\004\034\005\b[&\f\t\0211\001g\003\rAH%\r\005\b_\002\t\t\021\"\021q\003=\001(o\0343vGRLE/\032:bi>\024X#A9\021\007I\034h$D\001+\023\t!(F\001\005Ji\026\024\030\r^8s\021\0351\b!!A\005\002]\f\001bY1o\013F,\030\r\034\013\003qn\004\"\001C=\n\005iL!a\002\"p_2,\027M\034\005\b[V\f\t\0211\001\037\021\035i\b!!A\005By\f\001\002[1tQ\016{G-\032\013\002M\"I\021\021\001\001\002\002\023\005\0231A\001\ti>\034FO]5oOR\t1\fC\005\002\b\001\t\t\021\"\021\002\n\0051Q-];bYN$2\001_A\006\021!i\027QAA\001\002\004q\002&\002\001\002\020\005U\001c\001\005\002\022%\031\0211C\005\003!M+'/[1m-\026\0248/[8o+&#e$A\001\b\025\005e!!!A\t\002\021\tY\"A\013BGR|'oU3mK\016$\030n\0348NKN\034\030mZ3\021\0079\tiBB\005\002\005\005\005\t\022\001\003\002 M)\021QDA\021/A9\0211EA\025=\0312TBAA\023\025\r\t9#C\001\beVtG/[7f\023\021\tY#!\n\003#\005\0237\017\036:bGR4UO\\2uS>t'\007C\0045\003;!\t!a\f\025\005\005m\001BCA\001\003;\t\t\021\"\022\002\004!Q\021QGA\017\003\003%\t)a\016\002\013\005\004\b\017\\=\025\013Y\nI$a\017\t\rm\t\031\0041\001\037\021\031!\0231\007a\001M!Q\021qHA\017\003\003%\t)!\021\002\017Ut\027\r\0359msR!\0211IA&!\021AQ(!\022\021\013!\t9E\b\024\n\007\005%\023B\001\004UkBdWM\r\005\n\003\033\ni$!AA\002Y\n1\001\037\0231\021)\t\t&!\b\002\002\023%\0211K\001\fe\026\fGMU3t_24X\r\006\002\002VA\031A,a\026\n\007\005eSL\001\004PE*,7\r\036")
/*     */ public class ActorSelectionMessage implements AutoReceivedMessage, PossiblyHarmful, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Object msg;
/*     */   
/*     */   private final Iterable<SelectionPathElement> elements;
/*     */   
/*     */   public Object msg() {
/* 242 */     return this.msg;
/*     */   }
/*     */   
/*     */   public Iterable<SelectionPathElement> elements() {
/* 242 */     return this.elements;
/*     */   }
/*     */   
/*     */   public ActorSelectionMessage copy(Object msg, Iterable<SelectionPathElement> elements) {
/* 242 */     return new ActorSelectionMessage(msg, elements);
/*     */   }
/*     */   
/*     */   public Object copy$default$1() {
/* 242 */     return msg();
/*     */   }
/*     */   
/*     */   public Iterable<SelectionPathElement> copy$default$2() {
/* 242 */     return elements();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 242 */     return "ActorSelectionMessage";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 242 */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 242 */     int i = x$1;
/* 242 */     switch (i) {
/*     */       default:
/* 242 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 242 */     return msg();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 242 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 242 */     return x$1 instanceof ActorSelectionMessage;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 242 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 242 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 95
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/actor/ActorSelectionMessage
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 99
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/ActorSelectionMessage
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual msg : ()Ljava/lang/Object;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual msg : ()Ljava/lang/Object;
/*     */     //   40: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   43: ifeq -> 91
/*     */     //   46: aload_0
/*     */     //   47: invokevirtual elements : ()Lscala/collection/immutable/Iterable;
/*     */     //   50: aload #4
/*     */     //   52: invokevirtual elements : ()Lscala/collection/immutable/Iterable;
/*     */     //   55: astore #5
/*     */     //   57: dup
/*     */     //   58: ifnonnull -> 70
/*     */     //   61: pop
/*     */     //   62: aload #5
/*     */     //   64: ifnull -> 78
/*     */     //   67: goto -> 91
/*     */     //   70: aload #5
/*     */     //   72: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   75: ifeq -> 91
/*     */     //   78: aload #4
/*     */     //   80: aload_0
/*     */     //   81: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   84: ifeq -> 91
/*     */     //   87: iconst_1
/*     */     //   88: goto -> 92
/*     */     //   91: iconst_0
/*     */     //   92: ifeq -> 99
/*     */     //   95: iconst_1
/*     */     //   96: goto -> 100
/*     */     //   99: iconst_0
/*     */     //   100: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #242	-> 0
/*     */     //   #63	-> 14
/*     */     //   #242	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	101	0	this	Lakka/actor/ActorSelectionMessage;
/*     */     //   0	101	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ActorSelectionMessage(Object msg, Iterable<SelectionPathElement> elements) {
/* 242 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public Option<Identify> identifyRequest() {
/*     */     None$ none$;
/* 245 */     Object object = msg();
/* 246 */     if (object instanceof Identify) {
/* 246 */       Identify identify = (Identify)object;
/* 246 */       Some some = new Some(identify);
/*     */     } else {
/* 247 */       none$ = None$.MODULE$;
/*     */     } 
/*     */     return (Option<Identify>)none$;
/*     */   }
/*     */   
/*     */   public static Function1<Tuple2<Object, Iterable<SelectionPathElement>>, ActorSelectionMessage> tupled() {
/*     */     return ActorSelectionMessage$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Object, Function1<Iterable<SelectionPathElement>, ActorSelectionMessage>> curried() {
/*     */     return ActorSelectionMessage$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorSelectionMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */