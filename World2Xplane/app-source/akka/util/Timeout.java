/*    */ package akka.util;
/*    */ 
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.concurrent.duration.Duration$;
/*    */ import scala.concurrent.duration.FiniteDuration;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005ed\001B\001\003\001\036\021q\001V5nK>,HO\003\002\004\t\005!Q\017^5m\025\005)\021\001B1lW\006\034\001a\005\003\001\0219\t\002CA\005\r\033\005Q!\"A\006\002\013M\034\027\r\\1\n\0055Q!AB!osJ+g\r\005\002\n\037%\021\001C\003\002\b!J|G-^2u!\tI!#\003\002\024\025\ta1+\032:jC2L'0\0312mK\"AQ\003\001BK\002\023\005a#\001\005ekJ\fG/[8o+\0059\002C\001\r\035\033\005I\"BA\013\033\025\tY\"\"\001\006d_:\034WO\035:f]RL!!H\r\003\035\031Kg.\033;f\tV\024\030\r^5p]\"Aq\004\001B\tB\003%q#A\005ekJ\fG/[8oA!)\021\005\001C\001E\0051A(\0338jiz\"\"aI\023\021\005\021\002Q\"\001\002\t\013U\001\003\031A\f\t\013\005\002A\021A\024\025\005\rB\003\"B\025'\001\004Q\023a\002;j[\026|W\017\036\t\003\023-J!\001\f\006\003\t1{gn\032\025\005M9\n4\007\005\002\n_%\021\001G\003\002\013I\026\004(/Z2bi\026$\027%\001\032\002\017BdW-Y:fA\t,\007%\032=qY&\034\027\016\036\021bE>,H\017\t;iK\002\"\030.\\3!k:LG\017I1oI\002*8/\032\021uQ\026\004Co^8.CJ<W/\\3oi\0022XM]:j_:\f\023\001N\001\004e9\032\004\"B\021\001\t\0031DcA\0228s!)\001(\016a\001U\0051A.\0328hi\"DQAO\033A\002m\nA!\0368jiB\021A(Q\007\002{)\0211D\020\006\003\007}R\021\001Q\001\005U\0064\030-\003\002C{\tAA+[7f+:LG\017C\004E\001\005\005I\021A#\002\t\r|\007/\037\013\003G\031Cq!F\"\021\002\003\007q\003C\004I\001E\005I\021A%\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\t!J\013\002\030\027.\nA\n\005\002N%6\taJ\003\002P!\006IQO\\2iK\016\\W\r\032\006\003#*\t!\"\0318o_R\fG/[8o\023\t\031fJA\tv]\016DWmY6fIZ\013'/[1oG\026Dq!\026\001\002\002\023\005c+A\007qe>$Wo\031;Qe\0264\027\016_\013\002/B\021\001lW\007\0023*\021!lP\001\005Y\006tw-\003\002]3\n11\013\036:j]\036DqA\030\001\002\002\023\005q,\001\007qe>$Wo\031;Be&$\0300F\001a!\tI\021-\003\002c\025\t\031\021J\034;\t\017\021\004\021\021!C\001K\006q\001O]8ek\016$X\t\\3nK:$HC\0014j!\tIq-\003\002i\025\t\031\021I\\=\t\017)\034\027\021!a\001A\006\031\001\020J\031\t\0171\004\021\021!C![\006y\001O]8ek\016$\030\n^3sCR|'/F\001o!\ry'OZ\007\002a*\021\021OC\001\013G>dG.Z2uS>t\027BA:q\005!IE/\032:bi>\024\bbB;\001\003\003%\tA^\001\tG\006tW)];bYR\021qO\037\t\003\023aL!!\037\006\003\017\t{w\016\\3b]\"9!\016^A\001\002\0041\007b\002?\001\003\003%\t%`\001\tQ\006\034\bnQ8eKR\t\001\r\003\005\000\001\005\005I\021IA\001\003!!xn\025;sS:<G#A,\t\023\005\025\001!!A\005B\005\035\021AB3rk\006d7\017F\002x\003\023A\001B[A\002\003\003\005\rA\032\025\006\001\0055\0211\003\t\004\023\005=\021bAA\t\025\t\0012+\032:jC24VM]:j_:,\026\n\022\020\002\003\0359\021q\003\002\t\002\005e\021a\002+j[\026|W\017\036\t\004I\005maAB\001\003\021\003\tib\005\003\002\034!\t\002bB\021\002\034\021\005\021\021\005\013\003\0033A!\"!\n\002\034\t\007I\021AA\024\003\021QXM]8\026\003\rB\001\"a\013\002\034\001\006IaI\001\006u\026\024x\016\t\005\t\003_\tY\002\"\001\0022\005)\021\r\0359msR\0311%a\r\t\r%\ni\0031\001+Q\025\tiCL\0314\021!\ty#a\007\005\002\005eB#B\022\002<\005u\002B\002\035\0028\001\007!\006\003\004;\003o\001\ra\017\005\t\003\003\nY\002b\001\002D\005\tB-\036:bi&|g\016V8US6,w.\036;\025\007\r\n)\005\003\004\026\003\001\ra\006\005\t\003\023\nY\002b\001\002L\005a\021N\034;U_RKW.Z8viR\0311%!\024\t\r%\n9\0051\001a\021!\t\t&a\007\005\004\005M\023!\0047p]\036$v\016V5nK>,H\017F\002$\003+Ba!KA(\001\004Q\003BCA\030\0037\t\t\021\"!\002ZQ\0311%a\027\t\rU\t9\0061\001\030\021)\ty&a\007\002\002\023\005\025\021M\001\bk:\f\007\017\0357z)\021\t\031'!\033\021\t%\t)gF\005\004\003OR!AB(qi&|g\016C\005\002l\005u\023\021!a\001G\005\031\001\020\n\031\t\025\005=\0241DA\001\n\023\t\t(A\006sK\006$'+Z:pYZ,GCAA:!\rA\026QO\005\004\003oJ&AB(cU\026\034G\017")
/*    */ public class Timeout implements Product, Serializable {
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   private final FiniteDuration duration;
/*    */   
/*    */   public static Timeout longToTimeout(long paramLong) {
/*    */     return Timeout$.MODULE$.longToTimeout(paramLong);
/*    */   }
/*    */   
/*    */   public static Timeout intToTimeout(int paramInt) {
/*    */     return Timeout$.MODULE$.intToTimeout(paramInt);
/*    */   }
/*    */   
/*    */   public static Timeout durationToTimeout(FiniteDuration paramFiniteDuration) {
/*    */     return Timeout$.MODULE$.durationToTimeout(paramFiniteDuration);
/*    */   }
/*    */   
/*    */   public static Timeout apply(long paramLong, TimeUnit paramTimeUnit) {
/*    */     return Timeout$.MODULE$.apply(paramLong, paramTimeUnit);
/*    */   }
/*    */   
/*    */   public static Timeout apply(long paramLong) {
/*    */     return Timeout$.MODULE$.apply(paramLong);
/*    */   }
/*    */   
/*    */   public static Timeout zero() {
/*    */     return Timeout$.MODULE$.zero();
/*    */   }
/*    */   
/*    */   public FiniteDuration duration() {
/* 14 */     return this.duration;
/*    */   }
/*    */   
/*    */   public Timeout copy(FiniteDuration duration) {
/* 14 */     return new Timeout(duration);
/*    */   }
/*    */   
/*    */   public FiniteDuration copy$default$1() {
/* 14 */     return duration();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 14 */     return "Timeout";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 14 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 14 */     int i = x$1;
/* 14 */     switch (i) {
/*    */       default:
/* 14 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 14 */     return duration();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 14 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 14 */     return x$1 instanceof Timeout;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 14 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 14 */     return ScalaRunTime$.MODULE$._toString(this);
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
/*    */     //   8: instanceof akka/util/Timeout
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 84
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/util/Timeout
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual duration : ()Lscala/concurrent/duration/FiniteDuration;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual duration : ()Lscala/concurrent/duration/FiniteDuration;
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
/*    */     //   #14	-> 0
/*    */     //   #63	-> 14
/*    */     //   #14	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	86	0	this	Lakka/util/Timeout;
/*    */     //   0	86	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Timeout(FiniteDuration duration) {
/* 14 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Timeout(long timeout) {
/* 20 */     this(Duration$.MODULE$.apply(timeout, TimeUnit.MILLISECONDS));
/*    */   }
/*    */   
/*    */   public Timeout(long length, TimeUnit unit) {
/* 25 */     this(Duration$.MODULE$.apply(length, unit));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\Timeout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */