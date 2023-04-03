/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005Ea\001B\001\003\001&\021!BR1uC2,%O]8s\025\t\031A!A\004qCJ\034\030N\\4\013\005\0251\021a\001=nY*\tq!A\003tG\006d\027m\001\001\024\t\001Q!C\006\t\003\027Ai\021\001\004\006\003\0339\tA\001\\1oO*\tq\"\001\003kCZ\f\027BA\t\r\005A\021VO\034;j[\026,\005pY3qi&|g\016\005\002\024)5\ta!\003\002\026\r\t9\001K]8ek\016$\bCA\n\030\023\tAbA\001\007TKJL\027\r\\5{C\ndW\r\003\005\033\001\tU\r\021\"\001\034\003\ri7oZ\013\0029A\021Q\004\t\b\003'yI!a\b\004\002\rA\023X\rZ3g\023\t\t#E\001\004TiJLgn\032\006\003?\031A\001\002\n\001\003\022\003\006I\001H\001\005[N<\007\005C\003'\001\021\005q%\001\004=S:LGO\020\013\003Q)\002\"!\013\001\016\003\tAQAG\023A\002qAq\001\f\001\002\002\023\005Q&\001\003d_BLHC\001\025/\021\035Q2\006%AA\002qAq\001\r\001\022\002\023\005\021'\001\bd_BLH\005Z3gCVdG\017J\031\026\003IR#\001H\032,\003Q\002\"!\016\036\016\003YR!a\016\035\002\023Ut7\r[3dW\026$'BA\035\007\003)\tgN\\8uCRLwN\\\005\003wY\022\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021\035i\004!!A\005By\nQ\002\035:pIV\034G\017\025:fM&DX#A \021\005-\001\025BA\021\r\021\035\021\005!!A\005\002\r\013A\002\035:pIV\034G/\021:jif,\022\001\022\t\003'\025K!A\022\004\003\007%sG\017C\004I\001\005\005I\021A%\002\035A\024x\016Z;di\026cW-\\3oiR\021!*\024\t\003'-K!\001\024\004\003\007\005s\027\020C\004O\017\006\005\t\031\001#\002\007a$\023\007C\004Q\001\005\005I\021I)\002\037A\024x\016Z;di&#XM]1u_J,\022A\025\t\004'ZSU\"\001+\013\005U3\021AC2pY2,7\r^5p]&\021q\013\026\002\t\023R,'/\031;pe\"9\021\fAA\001\n\003Q\026\001C2b]\026\013X/\0317\025\005ms\006CA\n]\023\tifAA\004C_>dW-\0318\t\0179C\026\021!a\001\025\"9\001\rAA\001\n\003\n\027\001\0035bg\"\034u\016Z3\025\003\021Cqa\031\001\002\002\023\005C-\001\004fcV\fGn\035\013\0037\026DqA\0242\002\002\003\007!jB\004h\005\005\005\t\022\0015\002\025\031\013G/\0317FeJ|'\017\005\002*S\0329\021AAA\001\022\003Q7cA5l-A!An\034\017)\033\005i'B\0018\007\003\035\021XO\034;j[\026L!\001]7\003#\005\0237\017\036:bGR4UO\\2uS>t\027\007C\003'S\022\005!\017F\001i\021\035!\030.!A\005FU\f\001\002^8TiJLgn\032\013\002!9q/[A\001\n\003C\030!B1qa2LHC\001\025z\021\025Qb\0171\001\035\021\035Y\030.!A\005\002r\fq!\0368baBd\027\020F\002~\003\003\0012a\005@\035\023\tyhA\001\004PaRLwN\034\005\t\003\007Q\030\021!a\001Q\005\031\001\020\n\031\t\023\005\035\021.!A\005\n\005%\021a\003:fC\022\024Vm]8mm\026$\"!a\003\021\007-\ti!C\002\002\0201\021aa\0242kK\016$\b")
/*    */ public class FatalError extends RuntimeException implements Product, Serializable {
/*    */   private final String msg;
/*    */   
/*    */   public static <A> Function1<String, A> andThen(Function1<FatalError, A> paramFunction1) {
/*    */     return FatalError$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, FatalError> compose(Function1<A, String> paramFunction1) {
/*    */     return FatalError$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public String msg() {
/* 16 */     return this.msg;
/*    */   }
/*    */   
/*    */   public FatalError copy(String msg) {
/* 16 */     return new FatalError(msg);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 16 */     return msg();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 16 */     return "FatalError";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 16 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 16 */     switch (x$1) {
/*    */       default:
/* 16 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 16 */     return msg();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 16 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 16 */     return x$1 instanceof FatalError;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 16 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 75
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/parsing/FatalError
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 79
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/parsing/FatalError
/*    */     //   27: astore #4
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual msg : ()Ljava/lang/String;
/*    */     //   33: aload #4
/*    */     //   35: invokevirtual msg : ()Ljava/lang/String;
/*    */     //   38: astore_3
/*    */     //   39: dup
/*    */     //   40: ifnonnull -> 51
/*    */     //   43: pop
/*    */     //   44: aload_3
/*    */     //   45: ifnull -> 58
/*    */     //   48: goto -> 71
/*    */     //   51: aload_3
/*    */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   55: ifeq -> 71
/*    */     //   58: aload #4
/*    */     //   60: aload_0
/*    */     //   61: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   64: ifeq -> 71
/*    */     //   67: iconst_1
/*    */     //   68: goto -> 72
/*    */     //   71: iconst_0
/*    */     //   72: ifeq -> 79
/*    */     //   75: iconst_1
/*    */     //   76: goto -> 80
/*    */     //   79: iconst_0
/*    */     //   80: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #16	-> 0
/*    */     //   #236	-> 12
/*    */     //   #16	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	81	0	this	Lscala/xml/parsing/FatalError;
/*    */     //   0	81	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public FatalError(String msg) {
/* 16 */     super(msg);
/* 16 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\FatalError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */