/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005eb!B\001\003\001\032A!\001\003*fGJ,\027\r^3\013\005\r!\021AB:zg6\034xM\003\002\006\r\005AA-[:qCR\034\007NC\001\b\003\021\t7n[1\024\r\001Iqb\005\f\032!\tQQ\"D\001\f\025\005a\021!B:dC2\f\027B\001\b\f\005\031\te.\037*fMB\021\001#E\007\002\005%\021!C\001\002\016'f\034H/Z7NKN\034\030mZ3\021\005A!\022BA\013\003\005m\031F/Y:i/\",gnV1ji&twMR8s\007\"LG\016\032:f]B\021!bF\005\0031-\021q\001\025:pIV\034G\017\005\002\0135%\0211d\003\002\r'\026\024\030.\0317ju\006\024G.\032\005\t;\001\021)\032!C\001?\005)1-Y;tK\016\001Q#\001\021\021\005\005JcB\001\022(\035\t\031c%D\001%\025\t)c$\001\004=e>|GOP\005\002\031%\021\001fC\001\ba\006\0347.Y4f\023\tQ3FA\005UQJ|w/\0312mK*\021\001f\003\005\t[\001\021\t\022)A\005A\00511-Y;tK\002BQa\f\001\005\002A\na\001P5oSRtDCA\0313!\t\001\002\001C\003\036]\001\007\001\005C\0045\001\005\005I\021A\033\002\t\r|\007/\037\013\003cYBq!H\032\021\002\003\007\001\005C\0049\001E\005I\021A\035\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\t!H\013\002!w-\nA\b\005\002>\0056\taH\003\002@\001\006IQO\\2iK\016\\W\r\032\006\003\003.\t!\"\0318o_R\fG/[8o\023\t\031eHA\tv]\016DWmY6fIZ\013'/[1oG\026Dq!\022\001\002\002\023\005c)A\007qe>$Wo\031;Qe\0264\027\016_\013\002\017B\021\001*T\007\002\023*\021!jS\001\005Y\006twMC\001M\003\021Q\027M^1\n\0059K%AB*ue&tw\rC\004Q\001\005\005I\021A)\002\031A\024x\016Z;di\006\023\030\016^=\026\003I\003\"AC*\n\005Q[!aA%oi\"9a\013AA\001\n\0039\026A\0049s_\022,8\r^#mK6,g\016\036\013\0031n\003\"AC-\n\005i[!aA!os\"9A,VA\001\002\004\021\026a\001=%c!9a\fAA\001\n\003z\026a\0049s_\022,8\r^%uKJ\fGo\034:\026\003\001\0042!\0313Y\033\005\021'BA2\f\003)\031w\016\0347fGRLwN\\\005\003K\n\024\001\"\023;fe\006$xN\035\005\bO\002\t\t\021\"\001i\003!\031\027M\\#rk\006dGCA5m!\tQ!.\003\002l\027\t9!i\\8mK\006t\007b\002/g\003\003\005\r\001\027\005\b]\002\t\t\021\"\021p\003!A\027m\0355D_\022,G#\001*\t\017E\004\021\021!C!e\006AAo\\*ue&tw\rF\001H\021\035!\b!!A\005BU\fa!Z9vC2\034HCA5w\021\035a6/!AA\002aC3\001\001=|!\tQ\0210\003\002{\027\t\0012+\032:jC24VM]:j_:,\026\n\022\020\002\003\035AQPAA\001\022\0031a0\001\005SK\016\024X-\031;f!\t\001rPB\005\002\005\005\005\t\022\001\004\002\002M!q0a\001\032!\031\t)!a\003!c5\021\021q\001\006\004\003\023Y\021a\002:v]RLW.Z\005\005\003\033\t9AA\tBEN$(/Y2u\rVt7\r^5p]FBaaL@\005\002\005EA#\001@\t\017E|\030\021!C#e\"I\021qC@\002\002\023\005\025\021D\001\006CB\004H.\037\013\004c\005m\001BB\017\002\026\001\007\001\005C\005\002 }\f\t\021\"!\002\"\0059QO\\1qa2LH\003BA\022\003S\001BACA\023A%\031\021qE\006\003\r=\003H/[8o\021%\tY#!\b\002\002\003\007\021'A\002yIAB\021\"a\f\000\003\003%I!!\r\002\027I,\027\r\032*fg>dg/\032\013\003\003g\0012\001SA\033\023\r\t9$\023\002\007\037\nTWm\031;")
/*     */ public class Recreate implements SystemMessage, StashWhenWaitingForChildren, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Throwable cause;
/*     */   
/*     */   private transient SystemMessage next;
/*     */   
/*     */   public static <A> Function1<Throwable, A> andThen(Function1<Recreate, A> paramFunction1) {
/*     */     return Recreate$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, Recreate> compose(Function1<A, Throwable> paramFunction1) {
/*     */     return Recreate$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public SystemMessage next() {
/* 215 */     return this.next;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void next_$eq(SystemMessage x$1) {
/* 215 */     this.next = x$1;
/*     */   }
/*     */   
/*     */   public void unlink() {
/* 215 */     SystemMessage$class.unlink(this);
/*     */   }
/*     */   
/*     */   public boolean unlinked() {
/* 215 */     return SystemMessage$class.unlinked(this);
/*     */   }
/*     */   
/*     */   public Throwable cause() {
/* 215 */     return this.cause;
/*     */   }
/*     */   
/*     */   public Recreate copy(Throwable cause) {
/* 215 */     return new Recreate(cause);
/*     */   }
/*     */   
/*     */   public Throwable copy$default$1() {
/* 215 */     return cause();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 215 */     return "Recreate";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 215 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 215 */     int i = x$1;
/* 215 */     switch (i) {
/*     */       default:
/* 215 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 215 */     return cause();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 215 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 215 */     return x$1 instanceof Recreate;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 215 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 215 */     return ScalaRunTime$.MODULE$._toString(this);
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
/*     */     //   8: instanceof akka/dispatch/sysmsg/Recreate
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/dispatch/sysmsg/Recreate
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual cause : ()Ljava/lang/Throwable;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual cause : ()Ljava/lang/Throwable;
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
/*     */     //   #215	-> 0
/*     */     //   #63	-> 14
/*     */     //   #215	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/dispatch/sysmsg/Recreate;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Recreate(Throwable cause) {
/* 215 */     SystemMessage$class.$init$(this);
/* 215 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Recreate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */