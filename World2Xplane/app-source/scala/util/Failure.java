/*     */ package scala.util;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.util.control.NonFatal$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005Uh\001B\001\003\005\036\021qAR1jYV\024XM\003\002\004\t\005!Q\017^5m\025\005)\021!B:dC2\f7\001A\013\003\021=\031B\001A\005\0329A\031!bC\007\016\003\tI!\001\004\002\003\007Q\023\030\020\005\002\017\0371\001AA\002\t\001\t\013\007\021CA\001U#\t\021b\003\005\002\024)5\tA!\003\002\026\t\t9aj\034;iS:<\007CA\n\030\023\tABAA\002B]f\004\"a\005\016\n\005m!!a\002)s_\022,8\r\036\t\003'uI!A\b\003\003\031M+'/[1mSj\f'\r\\3\t\021\001\002!Q3A\005\002\005\n\021\"\032=dKB$\030n\0348\026\003\t\002\"aI\026\017\005\021JcBA\023)\033\0051#BA\024\007\003\031a$o\\8u}%\tQ!\003\002+\t\0059\001/Y2lC\036,\027B\001\027.\005%!\006N]8xC\ndWM\003\002+\t!Aq\006\001B\tB\003%!%\001\006fq\016,\007\017^5p]\002BQ!\r\001\005\002I\na\001P5oSRtDCA\0325!\rQ\001!\004\005\006AA\002\rA\t\005\006m\001!\taN\001\nSN4\025-\0337ve\026,\022\001\017\t\003'eJ!A\017\003\003\017\t{w\016\\3b]\")A\b\001C\001o\005I\021n]*vG\016,7o\035\005\006}\001!\taP\001\fe\026\034wN^3s/&$\b.\006\002A\007R\021\021I\022\t\004\025-\021\005C\001\bD\t\025!UH1\001F\005\005)\026CA\007\027\021\0259U\b1\001I\003\0051\007\003B\nJE\005K!A\023\003\003\037A\013'\017^5bY\032+hn\031;j_:DQ\001\024\001\005\0025\0131aZ3u+\005i\001\"B(\001\t\003\001\026a\0024mCRl\025\r]\013\003#R#\"AU+\021\007)Y1\013\005\002\017)\022)AI\024b\001#!)qI\024a\001-B!1cV\007S\023\tAFAA\005Gk:\034G/[8oc!)!\f\001C\0017\0069a\r\\1ui\026tWC\001/`)\ti\006\rE\002\013\027y\003\"AD0\005\013\021K&\031A\t\t\013\005L\0069\0012\002\005\0254\b\003B2g\033us!a\0053\n\005\025$\021A\002)sK\022,g-\003\002hQ\n\001B\005\\3tg\022\032w\016\\8oI1,7o\035\006\003K\022AQA\033\001\005\002-\fqAZ8sK\006\034\007.\006\002mgR\021Q\016\035\t\003'9L!a\034\003\003\tUs\027\016\036\005\006\017&\004\r!\035\t\005']k!\017\005\002\017g\022)A)\033b\001#!)Q\017\001C\001m\006\031Q.\0319\026\005]THC\001=|!\rQ1\"\037\t\003\035i$Q\001\022;C\002EAQa\022;A\002q\004BaE,\016s\")a\020\001C\001\0061a-\0337uKJ$2!CA\001\021\035\t\031! a\001\003\013\t\021\001\035\t\005']k\001\bC\004\002\n\001!\t!a\003\002\017I,7m\034<feV!\021QBA\n)\021\ty!!\006\021\t)Y\021\021\003\t\004\035\005MAA\002#\002\b\t\007Q\t\003\005\002\030\005\035\001\031AA\r\003=\021Xm]2vK\026C8-\0329uS>t\007#B\nJE\005E\001bBA\017\001\021\005\021qD\001\007M\006LG.\0323\026\005\005\005\002c\001\006\fE!I\021Q\005\001\002\002\023\005\021qE\001\005G>\004\0300\006\003\002*\005=B\003BA\026\003c\001BA\003\001\002.A\031a\"a\f\005\rA\t\031C1\001\022\021!\001\0231\005I\001\002\004\021\003\"CA\033\001E\005I\021AA\034\0039\031w\016]=%I\0264\027-\0367uIE*B!!\017\002PU\021\0211\b\026\004E\005u2FAA !\021\t\t%a\023\016\005\005\r#\002BA#\003\017\n\021\"\0368dQ\026\0347.\0323\013\007\005%C!\001\006b]:|G/\031;j_:LA!!\024\002D\t\tRO\\2iK\016\\W\r\032,be&\fgnY3\005\rA\t\031D1\001\022\021%\t\031\006AA\001\n\003\n)&A\007qe>$Wo\031;Qe\0264\027\016_\013\003\003/\002B!!\027\002d5\021\0211\f\006\005\003;\ny&\001\003mC:<'BAA1\003\021Q\027M^1\n\t\005\025\0241\f\002\007'R\024\030N\\4\t\023\005%\004!!A\005\002\005-\024\001\0049s_\022,8\r^!sSRLXCAA7!\r\031\022qN\005\004\003c\"!aA%oi\"I\021Q\017\001\002\002\023\005\021qO\001\017aJ|G-^2u\0132,W.\0328u)\r1\022\021\020\005\013\003w\n\031(!AA\002\0055\024a\001=%c!I\021q\020\001\002\002\023\005\023\021Q\001\020aJ|G-^2u\023R,'/\031;peV\021\0211\021\t\006\003\013\013YIF\007\003\003\017S1!!#\005\003)\031w\016\0347fGRLwN\\\005\005\003\033\0139I\001\005Ji\026\024\030\r^8s\021%\t\t\nAA\001\n\003\t\031*\001\005dC:,\025/^1m)\rA\024Q\023\005\n\003w\ny)!AA\002YA\021\"!'\001\003\003%\t%a'\002\021!\f7\017[\"pI\026$\"!!\034\t\023\005}\005!!A\005B\005\005\026\001\003;p'R\024\030N\\4\025\005\005]\003\"CAS\001\005\005I\021IAT\003\031)\027/^1mgR\031\001(!+\t\023\005m\0241UA\001\002\0041r!CAW\005\005\005\t\022AAX\003\0351\025-\0337ve\026\0042ACAY\r!\t!!!A\t\002\005M6#BAY\003kc\002cA\n\0028&\031\021\021\030\003\003\r\005s\027PU3g\021\035\t\024\021\027C\001\003{#\"!a,\t\025\005}\025\021WA\001\n\013\n\t\013\003\006\002D\006E\026\021!CA\003\013\fQ!\0319qYf,B!a2\002NR!\021\021ZAh!\021Q\001!a3\021\0079\ti\r\002\004\021\003\003\024\r!\005\005\007A\005\005\007\031\001\022\t\025\005M\027\021WA\001\n\003\013).A\004v]\006\004\b\017\\=\026\t\005]\027q\035\013\005\0033\fy\016\005\003\024\0037\024\023bAAo\t\t1q\n\035;j_:D!\"!9\002R\006\005\t\031AAr\003\rAH\005\r\t\005\025\001\t)\017E\002\017\003O$a\001EAi\005\004\t\002BCAv\003c\013\t\021\"\003\002n\006Y!/Z1e%\026\034x\016\034<f)\t\ty\017\005\003\002Z\005E\030\002BAz\0037\022aa\0242kK\016$\b")
/*     */ public final class Failure<T> extends Try<T> implements Product, Serializable {
/*     */   private final Throwable exception;
/*     */   
/*     */   public Throwable exception() {
/* 167 */     return this.exception;
/*     */   }
/*     */   
/*     */   public <T> Failure<T> copy(Throwable exception) {
/* 167 */     return new Failure(exception);
/*     */   }
/*     */   
/*     */   public <T> Throwable copy$default$1() {
/* 167 */     return exception();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 167 */     return "Failure";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 167 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 167 */     switch (x$1) {
/*     */       default:
/* 167 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 167 */     return exception();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 167 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 167 */     return x$1 instanceof Failure;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 167 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 167 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 67
/*     */     //   5: aload_1
/*     */     //   6: instanceof scala/util/Failure
/*     */     //   9: ifeq -> 17
/*     */     //   12: iconst_1
/*     */     //   13: istore_2
/*     */     //   14: goto -> 19
/*     */     //   17: iconst_0
/*     */     //   18: istore_2
/*     */     //   19: iload_2
/*     */     //   20: ifeq -> 71
/*     */     //   23: aload_1
/*     */     //   24: checkcast scala/util/Failure
/*     */     //   27: astore_3
/*     */     //   28: aload_0
/*     */     //   29: invokevirtual exception : ()Ljava/lang/Throwable;
/*     */     //   32: aload_3
/*     */     //   33: invokevirtual exception : ()Ljava/lang/Throwable;
/*     */     //   36: astore #4
/*     */     //   38: dup
/*     */     //   39: ifnonnull -> 51
/*     */     //   42: pop
/*     */     //   43: aload #4
/*     */     //   45: ifnull -> 59
/*     */     //   48: goto -> 63
/*     */     //   51: aload #4
/*     */     //   53: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   56: ifeq -> 63
/*     */     //   59: iconst_1
/*     */     //   60: goto -> 64
/*     */     //   63: iconst_0
/*     */     //   64: ifeq -> 71
/*     */     //   67: iconst_1
/*     */     //   68: goto -> 72
/*     */     //   71: iconst_0
/*     */     //   72: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #167	-> 0
/*     */     //   #236	-> 12
/*     */     //   #167	-> 19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	73	0	this	Lscala/util/Failure;
/*     */     //   0	73	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Failure(Throwable exception) {
/* 167 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public boolean isFailure() {
/* 168 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isSuccess() {
/* 169 */     return false;
/*     */   }
/*     */   
/*     */   public <U> Try<U> recoverWith(PartialFunction f) {
/*     */     try {
/*     */     
/*     */     } finally {
/* 171 */       Exception exception = null;
/* 174 */       Option option = NonFatal$.MODULE$.unapply(exception);
/*     */     } 
/* 174 */     return new Failure((Throwable)option.get());
/*     */   }
/*     */   
/*     */   public T get() {
/* 176 */     throw exception();
/*     */   }
/*     */   
/*     */   public <U> Try<U> flatMap(Function1 f) {
/* 177 */     return this;
/*     */   }
/*     */   
/*     */   public <U> Try<U> flatten(Predef$.less.colon.less ev) {
/* 178 */     return this;
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {}
/*     */   
/*     */   public <U> Try<U> map(Function1 f) {
/* 180 */     return this;
/*     */   }
/*     */   
/*     */   public Try<T> filter(Function1 p) {
/* 181 */     return this;
/*     */   }
/*     */   
/*     */   public <U> Try<U> recover(PartialFunction rescueException) {
/*     */     try {
/*     */     
/*     */     } finally {
/* 183 */       Exception exception = null;
/* 188 */       Option option = NonFatal$.MODULE$.unapply(exception);
/*     */     } 
/* 188 */     return new Failure((Throwable)option.get());
/*     */   }
/*     */   
/*     */   public class Failure$$anonfun$recover$1 extends AbstractFunction0<U> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final PartialFunction rescueException$1;
/*     */     
/*     */     public final U apply() {
/*     */       return (U)this.rescueException$1.apply(this.$outer.exception());
/*     */     }
/*     */     
/*     */     public Failure$$anonfun$recover$1(Failure $outer, PartialFunction rescueException$1) {}
/*     */   }
/*     */   
/*     */   public Try<Throwable> failed() {
/* 190 */     return new Success<Throwable>(exception());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Failure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */