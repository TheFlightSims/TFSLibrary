/*    */ package akka.actor;
/*    */ 
/*    */ import akka.AkkaException;
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ import scala.util.control.NoStackTrace;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\rb\001B\001\003\t\036\021!cU2iK\022,H.\032:Fq\016,\007\017^5p]*\0211\001B\001\006C\016$xN\035\006\002\013\005!\021m[6b\007\001\031R\001\001\005\r-i\001\"!\003\006\016\003\021I!a\003\003\003\033\005[7.Y#yG\026\004H/[8o!\tiA#D\001\017\025\ty\001#A\004d_:$(o\0347\013\005E\021\022\001B;uS2T\021aE\001\006g\016\fG.Y\005\003+9\021ABT8Ti\006\0347\016\026:bG\026\004\"a\006\r\016\003II!!\007\n\003\017A\023x\016Z;diB\021qcG\005\0039I\021AbU3sS\006d\027N_1cY\026D\001B\b\001\003\026\004%\taH\001\004[N<W#\001\021\021\005\005\"cBA\f#\023\t\031##\001\004Qe\026$WMZ\005\003K\031\022aa\025;sS:<'BA\022\023\021!A\003A!E!\002\023\001\023\001B7tO\002BQA\013\001\005\002-\na\001P5oSRtDC\001\027/!\ti\003!D\001\003\021\025q\022\0061\001!\021\035\001\004!!A\005\002E\nAaY8qsR\021AF\r\005\b==\002\n\0211\001!\021\035!\004!%A\005\002U\nabY8qs\022\"WMZ1vYR$\023'F\0017U\t\001sgK\0019!\tId(D\001;\025\tYD(A\005v]\016DWmY6fI*\021QHE\001\013C:tw\016^1uS>t\027BA ;\005E)hn\0315fG.,GMV1sS\006t7-\032\005\b\003\002\t\t\021\"\021C\0035\001(o\0343vGR\004&/\0324jqV\t1\t\005\002E\0236\tQI\003\002G\017\006!A.\0318h\025\005A\025\001\0026bm\006L!!J#\t\017-\003\021\021!C\001\031\006a\001O]8ek\016$\030I]5usV\tQ\n\005\002\030\035&\021qJ\005\002\004\023:$\bbB)\001\003\003%\tAU\001\017aJ|G-^2u\0132,W.\0328u)\t\031f\013\005\002\030)&\021QK\005\002\004\003:L\bbB,Q\003\003\005\r!T\001\004q\022\n\004bB-\001\003\003%\tEW\001\020aJ|G-^2u\023R,'/\031;peV\t1\fE\002]?Nk\021!\030\006\003=J\t!bY8mY\026\034G/[8o\023\t\001WL\001\005Ji\026\024\030\r^8s\021\035\021\007!!A\005\002\r\f\001bY1o\013F,\030\r\034\013\003I\036\004\"aF3\n\005\031\024\"a\002\"p_2,\027M\034\005\b/\006\f\t\0211\001T\021\035I\007!!A\005B)\f\001\002[1tQ\016{G-\032\013\002\033\"9A\016AA\001\n\003j\027AB3rk\006d7\017\006\002e]\"9qk[A\001\002\004\031va\0029\003\003\003EI!]\001\023'\016DW\rZ;mKJ,\005pY3qi&|g\016\005\002.e\0329\021AAA\001\022\023\0318c\001:u5A!Q\017\037\021-\033\0051(BA<\023\003\035\021XO\034;j[\026L!!\037<\003#\005\0237\017\036:bGR4UO\\2uS>t\027\007C\003+e\022\0051\020F\001r\021\035i(/!A\005Fy\f\001\002^8TiJLgn\032\013\002\007\"I\021\021\001:\002\002\023\005\0251A\001\006CB\004H.\037\013\004Y\005\025\001\"\002\020\000\001\004\001\003\"CA\005e\006\005I\021QA\006\003\035)h.\0319qYf$B!!\004\002\024A!q#a\004!\023\r\t\tB\005\002\007\037B$\030n\0348\t\023\005U\021qAA\001\002\004a\023a\001=%a!I\021\021\004:\002\002\023%\0211D\001\fe\026\fGMU3t_24X\r\006\002\002\036A\031A)a\b\n\007\005\005RI\001\004PE*,7\r\036")
/*    */ public class SchedulerException extends AkkaException implements NoStackTrace, Product {
/*    */   private final String msg;
/*    */   
/*    */   public static <A> Function1<String, A> andThen(Function1<SchedulerException, A> paramFunction1) {
/*    */     return SchedulerException$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, SchedulerException> compose(Function1<A, String> paramFunction1) {
/*    */     return SchedulerException$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public Throwable scala$util$control$NoStackTrace$$super$fillInStackTrace() {
/* 25 */     return super.fillInStackTrace();
/*    */   }
/*    */   
/*    */   public Throwable fillInStackTrace() {
/* 25 */     return NoStackTrace.class.fillInStackTrace(this);
/*    */   }
/*    */   
/*    */   public String msg() {
/* 25 */     return this.msg;
/*    */   }
/*    */   
/*    */   public SchedulerException copy(String msg) {
/* 25 */     return new SchedulerException(msg);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 25 */     return msg();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 25 */     return "SchedulerException";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 25 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 25 */     int i = x$1;
/* 25 */     switch (i) {
/*    */       default:
/* 25 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 25 */     return msg();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 25 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 25 */     return x$1 instanceof SchedulerException;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 25 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 80
/*    */     //   5: aload_1
/*    */     //   6: astore_2
/*    */     //   7: aload_2
/*    */     //   8: instanceof akka/actor/SchedulerException
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 84
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/actor/SchedulerException
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual msg : ()Ljava/lang/String;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual msg : ()Ljava/lang/String;
/*    */     //   40: astore #5
/*    */     //   42: dup
/*    */     //   43: ifnonnull -> 55
/*    */     //   46: pop
/*    */     //   47: aload #5
/*    */     //   49: ifnull -> 63
/*    */     //   52: goto -> 76
/*    */     //   55: aload #5
/*    */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   60: ifeq -> 76
/*    */     //   63: aload #4
/*    */     //   65: aload_0
/*    */     //   66: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   69: ifeq -> 76
/*    */     //   72: iconst_1
/*    */     //   73: goto -> 77
/*    */     //   76: iconst_0
/*    */     //   77: ifeq -> 84
/*    */     //   80: iconst_1
/*    */     //   81: goto -> 85
/*    */     //   84: iconst_0
/*    */     //   85: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #25	-> 0
/*    */     //   #63	-> 14
/*    */     //   #25	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	86	0	this	Lakka/actor/SchedulerException;
/*    */     //   0	86	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public SchedulerException(String msg) {
/* 25 */     super(msg);
/* 25 */     NoStackTrace.class.$init$(this);
/* 25 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\SchedulerException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */