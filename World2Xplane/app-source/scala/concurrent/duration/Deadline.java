/*    */ package scala.concurrent.duration;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.Iterator;
/*    */ import scala.math.Ordered;
/*    */ import scala.math.Ordering;
/*    */ import scala.math.PartialOrdering;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005Ed\001B\001\003\001&\021\001\002R3bI2Lg.\032\006\003\007\021\t\001\002Z;sCRLwN\034\006\003\013\031\t!bY8oGV\024(/\0328u\025\0059\021!B:dC2\f7\001A\n\006\001)qAd\b\t\003\0271i\021AB\005\003\033\031\021a!\0218z%\0264\007cA\b\03059\021\001#\006\b\003#Qi\021A\005\006\003'!\ta\001\020:p_Rt\024\"A\004\n\005Y1\021a\0029bG.\fw-Z\005\0031e\021qa\024:eKJ,GM\003\002\027\rA\0211\004A\007\002\005A\0211\"H\005\003=\031\021q\001\025:pIV\034G\017\005\002\fA%\021\021E\002\002\r'\026\024\030.\0317ju\006\024G.\032\005\tG\001\021)\032!C\001I\005!A/[7f+\005)\003CA\016'\023\t9#A\001\bGS:LG/\032#ve\006$\030n\0348\t\021%\002!\021#Q\001\n\025\nQ\001^5nK\002BQa\013\001\005\n1\na\001P5oSRtDC\001\016.\021\025\031#\0061\001&\021\025y\003\001\"\0011\003\025!\003\017\\;t)\tQ\022\007C\0033]\001\007Q%A\003pi\",'\017C\0035\001\021\005Q'\001\004%[&tWo\035\013\0035YBQAM\032A\002\025BQ\001\016\001\005\002a\"\"!J\035\t\013I:\004\031\001\016\t\013m\002A\021\001\023\002\021QLW.\032'fMRDQ!\020\001\005\002y\n1\002[1t)&lW\rT3giR\tq\b\005\002\f\001&\021\021I\002\002\b\005>|G.Z1o\021\025\031\005\001\"\001?\003%I7o\024<fe\022,X\rC\003F\001\021\005a)A\004d_6\004\030M]3\025\005\035S\005CA\006I\023\tIeAA\002J]RDQA\r#A\002iAq\001\024\001\002\002\023\005Q*\001\003d_BLHC\001\016O\021\035\0313\n%AA\002\025Bq\001\025\001\022\002\023\005\021+\001\bd_BLH\005Z3gCVdG\017J\031\026\003IS#!J*,\003Q\003\"!\026.\016\003YS!a\026-\002\023Ut7\r[3dW\026$'BA-\007\003)\tgN\\8uCRLwN\\\005\0037Z\023\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021\035i\006!!A\005By\013Q\002\035:pIV\034G\017\025:fM&DX#A0\021\005\001,W\"A1\013\005\t\034\027\001\0027b]\036T\021\001Z\001\005U\0064\030-\003\002gC\n11\013\036:j]\036Dq\001\033\001\002\002\023\005\021.\001\007qe>$Wo\031;Be&$\0300F\001H\021\035Y\007!!A\005\0021\fa\002\035:pIV\034G/\0227f[\026tG\017\006\002naB\0211B\\\005\003_\032\0211!\0218z\021\035\t(.!AA\002\035\0131\001\037\0232\021\035\031\b!!A\005BQ\fq\002\035:pIV\034G/\023;fe\006$xN]\013\002kB\031a/_7\016\003]T!\001\037\004\002\025\r|G\016\\3di&|g.\003\002{o\nA\021\n^3sCR|'\017C\004}\001\005\005I\021A?\002\021\r\fg.R9vC2$\"a\020@\t\017E\\\030\021!a\001[\"I\021\021\001\001\002\002\023\005\0231A\001\tQ\006\034\bnQ8eKR\tq\tC\005\002\b\001\t\t\021\"\021\002\n\005AAo\\*ue&tw\rF\001`\021%\ti\001AA\001\n\003\ny!\001\004fcV\fGn\035\013\004\005E\001\002C9\002\f\005\005\t\031A7\b\017\005U!\001#\001\002\030\005AA)Z1eY&tW\rE\002\034\00331a!\001\002\t\002\005m1\003BA\r\025}AqaKA\r\t\003\ty\002\006\002\002\030!A\0211EA\r\t\003\t)#A\002o_^,\022AG\004\t\003S\tI\002c\001\002,\005\tB)Z1eY&tW-S:Pe\022,'/\0323\021\t\0055\022qF\007\003\00331\001\"!\r\002\032!\005\0211\007\002\022\t\026\fG\r\\5oK&\033xJ\0353fe\026$7CBA\030\003k\tY\004E\002a\003oI1!!\017b\005\031y%M[3diB!q\"!\020\033\023\r\ty$\007\002\t\037J$WM]5oO\"91&a\f\005\002\005\rCCAA\026\021\035)\025q\006C\001\003\017\"RaRA%\003\033Bq!a\023\002F\001\007!$A\001b\021\035\ty%!\022A\002i\t\021A\031\005\013\003'\ny#!A\005\n\005U\023a\003:fC\022\024Vm]8mm\026$\"!!\016\t\025\005e\023\021DA\001\n\003\013Y&A\003baBd\027\020F\002\033\003;BaaIA,\001\004)\003BCA1\0033\t\t\021\"!\002d\0059QO\\1qa2LH\003BA3\003W\002BaCA4K%\031\021\021\016\004\003\r=\003H/[8o\021%\ti'a\030\002\002\003\007!$A\002yIAB!\"a\025\002\032\005\005I\021BA+\001")
/*    */ public class Deadline implements Ordered<Deadline>, Product, Serializable {
/*    */   private final FiniteDuration time;
/*    */   
/*    */   public boolean $less(Object that) {
/* 26 */     return Ordered.class.$less(this, that);
/*    */   }
/*    */   
/*    */   public boolean $greater(Object that) {
/* 26 */     return Ordered.class.$greater(this, that);
/*    */   }
/*    */   
/*    */   public boolean $less$eq(Object that) {
/* 26 */     return Ordered.class.$less$eq(this, that);
/*    */   }
/*    */   
/*    */   public boolean $greater$eq(Object that) {
/* 26 */     return Ordered.class.$greater$eq(this, that);
/*    */   }
/*    */   
/*    */   public int compareTo(Object that) {
/* 26 */     return Ordered.class.compareTo(this, that);
/*    */   }
/*    */   
/*    */   public FiniteDuration time() {
/* 26 */     return this.time;
/*    */   }
/*    */   
/*    */   public Deadline copy(FiniteDuration time) {
/* 26 */     return new Deadline(time);
/*    */   }
/*    */   
/*    */   public FiniteDuration copy$default$1() {
/* 26 */     return time();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 26 */     return "Deadline";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 26 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 26 */     switch (x$1) {
/*    */       default:
/* 26 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 26 */     return time();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 26 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 26 */     return x$1 instanceof Deadline;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 26 */     return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 26 */     return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 75
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/concurrent/duration/Deadline
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 79
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/concurrent/duration/Deadline
/*    */     //   27: astore #4
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual time : ()Lscala/concurrent/duration/FiniteDuration;
/*    */     //   33: aload #4
/*    */     //   35: invokevirtual time : ()Lscala/concurrent/duration/FiniteDuration;
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
/*    */     //   #26	-> 0
/*    */     //   #236	-> 12
/*    */     //   #26	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	81	0	this	Lscala/concurrent/duration/Deadline;
/*    */     //   0	81	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Deadline(FiniteDuration time) {
/* 26 */     Ordered.class.$init$(this);
/* 26 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Deadline $plus(FiniteDuration other) {
/* 30 */     return copy(time().$plus(other));
/*    */   }
/*    */   
/*    */   public Deadline $minus(FiniteDuration other) {
/* 34 */     return copy(time().$minus(other));
/*    */   }
/*    */   
/*    */   public FiniteDuration $minus(Deadline other) {
/* 38 */     return time().$minus(other.time());
/*    */   }
/*    */   
/*    */   public FiniteDuration timeLeft() {
/* 45 */     return $minus(Deadline$.MODULE$.now());
/*    */   }
/*    */   
/*    */   public boolean hasTimeLeft() {
/* 52 */     return !isOverdue();
/*    */   }
/*    */   
/*    */   public boolean isOverdue() {
/* 59 */     return (time().toNanos() - System.nanoTime() < 0L);
/*    */   }
/*    */   
/*    */   public int compare(Deadline other) {
/* 63 */     return time().compare(other.time());
/*    */   }
/*    */   
/*    */   public static Deadline now() {
/*    */     return Deadline$.MODULE$.now();
/*    */   }
/*    */   
/*    */   public static class DeadlineIsOrdered$ implements Ordering<Deadline> {
/*    */     public static final DeadlineIsOrdered$ MODULE$;
/*    */     
/*    */     public Some<Object> tryCompare(Object x, Object y) {
/* 77 */       return Ordering.class.tryCompare(this, x, y);
/*    */     }
/*    */     
/*    */     public boolean lteq(Object x, Object y) {
/* 77 */       return Ordering.class.lteq(this, x, y);
/*    */     }
/*    */     
/*    */     public boolean gteq(Object x, Object y) {
/* 77 */       return Ordering.class.gteq(this, x, y);
/*    */     }
/*    */     
/*    */     public boolean lt(Object x, Object y) {
/* 77 */       return Ordering.class.lt(this, x, y);
/*    */     }
/*    */     
/*    */     public boolean gt(Object x, Object y) {
/* 77 */       return Ordering.class.gt(this, x, y);
/*    */     }
/*    */     
/*    */     public boolean equiv(Object x, Object y) {
/* 77 */       return Ordering.class.equiv(this, x, y);
/*    */     }
/*    */     
/*    */     public Object max(Object x, Object y) {
/* 77 */       return Ordering.class.max(this, x, y);
/*    */     }
/*    */     
/*    */     public Object min(Object x, Object y) {
/* 77 */       return Ordering.class.min(this, x, y);
/*    */     }
/*    */     
/*    */     public Ordering<Deadline> reverse() {
/* 77 */       return Ordering.class.reverse(this);
/*    */     }
/*    */     
/*    */     public <U> Ordering<U> on(Function1 f) {
/* 77 */       return Ordering.class.on(this, f);
/*    */     }
/*    */     
/*    */     public Ordering<Deadline>.Ops mkOrderingOps(Object lhs) {
/* 77 */       return Ordering.class.mkOrderingOps(this, lhs);
/*    */     }
/*    */     
/*    */     private Object readResolve() {
/* 77 */       return MODULE$;
/*    */     }
/*    */     
/*    */     public DeadlineIsOrdered$() {
/* 77 */       MODULE$ = this;
/* 77 */       PartialOrdering.class.$init$((PartialOrdering)this);
/* 77 */       Ordering.class.$init$(this);
/*    */     }
/*    */     
/*    */     public int compare(Deadline a, Deadline b) {
/* 78 */       return a.compare(b);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\duration\Deadline.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */