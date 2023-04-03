/*     */ package akka.actor;
/*     */ 
/*     */ import akka.AkkaException;
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005ua\001B\001\003\001\036\021q#\0238wC2LG-T3tg\006<W-\022=dKB$\030n\0348\013\005\r!\021!B1di>\024(\"A\003\002\t\005\\7.Y\002\001'\021\001\001\002\004\n\021\005%QQ\"\001\003\n\005-!!!D!lW\006,\005pY3qi&|g\016\005\002\016!5\taBC\001\020\003\025\0318-\0317b\023\t\tbBA\004Qe>$Wo\031;\021\0055\031\022B\001\013\017\0051\031VM]5bY&T\030M\0317f\021!1\002A!f\001\n\0039\022aB7fgN\fw-Z\013\0021A\021\021\004\b\b\003\033iI!a\007\b\002\rA\023X\rZ3g\023\tibD\001\004TiJLgn\032\006\00379A\001\002\t\001\003\022\003\006I\001G\001\t[\026\0348/Y4fA!1!\005\001C\001\t\r\na\001P5oSRtDC\001\023'!\t)\003!D\001\003\021\0251\022\0051\001\031\021\035A\003!!A\005\002%\nAaY8qsR\021AE\013\005\b-\035\002\n\0211\001\031\021\035a\003!%A\005\0025\nabY8qs\022\"WMZ1vYR$\023'F\001/U\tArfK\0011!\t\td'D\0013\025\t\031D'A\005v]\016DWmY6fI*\021QGD\001\013C:tw\016^1uS>t\027BA\0343\005E)hn\0315fG.,GMV1sS\006t7-\032\005\bs\001\t\t\021\"\021;\0035\001(o\0343vGR\004&/\0324jqV\t1\b\005\002=\0036\tQH\003\002?\005!A.\0318h\025\005\001\025\001\0026bm\006L!!H\037\t\017\r\003\021\021!C\001\t\006a\001O]8ek\016$\030I]5usV\tQ\t\005\002\016\r&\021qI\004\002\004\023:$\bbB%\001\003\003%\tAS\001\017aJ|G-^2u\0132,W.\0328u)\tYe\n\005\002\016\031&\021QJ\004\002\004\003:L\bbB(I\003\003\005\r!R\001\004q\022\n\004bB)\001\003\003%\tEU\001\020aJ|G-^2u\023R,'/\031;peV\t1\013E\002U/.k\021!\026\006\003-:\t!bY8mY\026\034G/[8o\023\tAVK\001\005Ji\026\024\030\r^8s\021\035Q\006!!A\005\002m\013\001bY1o\013F,\030\r\034\013\0039~\003\"!D/\n\005ys!a\002\"p_2,\027M\034\005\b\037f\013\t\0211\001L\021\035\t\007!!A\005B\t\f\001\002[1tQ\016{G-\032\013\002\013\"9A\rAA\001\n\003*\027AB3rk\006d7\017\006\002]M\"9qjYA\001\002\004Y\005f\001\001iWB\021Q\"[\005\003U:\021\001cU3sS\006dg+\032:tS>tW+\023#\037\003\0059q!\034\002\002\002#\005a.A\fJ]Z\fG.\0333NKN\034\030mZ3Fq\016,\007\017^5p]B\021Qe\034\004\b\003\t\t\t\021#\001q'\ry\027O\005\t\005eVDB%D\001t\025\t!h\"A\004sk:$\030.\\3\n\005Y\034(!E!cgR\024\030m\031;Gk:\034G/[8oc!)!e\034C\001qR\ta\016C\004{_\006\005IQI>\002\021Q|7\013\036:j]\036$\022a\017\005\b{>\f\t\021\"!\003\025\t\007\017\0357z)\t!s\020C\003\027y\002\007\001\004C\005\002\004=\f\t\021\"!\002\006\0059QO\\1qa2LH\003BA\004\003\033\001B!DA\0051%\031\0211\002\b\003\r=\003H/[8o\021%\ty!!\001\002\002\003\007A%A\002yIAB\021\"a\005p\003\003%I!!\006\002\027I,\027\r\032*fg>dg/\032\013\003\003/\0012\001PA\r\023\r\tY\"\020\002\007\037\nTWm\031;")
/*     */ public class InvalidMessageException extends AkkaException implements Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final String message;
/*     */   
/*     */   public static <A> Function1<String, A> andThen(Function1<InvalidMessageException, A> paramFunction1) {
/*     */     return InvalidMessageException$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, InvalidMessageException> compose(Function1<A, String> paramFunction1) {
/*     */     return InvalidMessageException$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public String message() {
/* 223 */     return this.message;
/*     */   }
/*     */   
/*     */   public InvalidMessageException copy(String message) {
/* 223 */     return new InvalidMessageException(message);
/*     */   }
/*     */   
/*     */   public String copy$default$1() {
/* 223 */     return message();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 223 */     return "InvalidMessageException";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 223 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 223 */     int i = x$1;
/* 223 */     switch (i) {
/*     */       default:
/* 223 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 223 */     return message();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 223 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 223 */     return x$1 instanceof InvalidMessageException;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 223 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 80
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/actor/InvalidMessageException
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/InvalidMessageException
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual message : ()Ljava/lang/String;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual message : ()Ljava/lang/String;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 76
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 76
/*     */     //   63: aload #4
/*     */     //   65: aload_0
/*     */     //   66: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   69: ifeq -> 76
/*     */     //   72: iconst_1
/*     */     //   73: goto -> 77
/*     */     //   76: iconst_0
/*     */     //   77: ifeq -> 84
/*     */     //   80: iconst_1
/*     */     //   81: goto -> 85
/*     */     //   84: iconst_0
/*     */     //   85: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #223	-> 0
/*     */     //   #63	-> 14
/*     */     //   #223	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/actor/InvalidMessageException;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public InvalidMessageException(String message) {
/* 223 */     super(message);
/* 223 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\InvalidMessageException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */