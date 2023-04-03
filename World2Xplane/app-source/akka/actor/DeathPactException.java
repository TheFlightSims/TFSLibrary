/*     */ package akka.actor;
/*     */ 
/*     */ import akka.AkkaException;
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.util.control.NoStackTrace;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\035b\001B\001\003\001\036\021!\003R3bi\"\004\026m\031;Fq\016,\007\017^5p]*\0211\001B\001\006C\016$xN\035\006\002\013\005!\021m[6b\007\001\031R\001\001\005\r-i\001\"!\003\006\016\003\021I!a\003\003\003\033\005[7.Y#yG\026\004H/[8o!\tiA#D\001\017\025\ty\001#A\004d_:$(o\0347\013\005E\021\022\001B;uS2T\021aE\001\006g\016\fG.Y\005\003+9\021ABT8Ti\006\0347\016\026:bG\026\004\"a\006\r\016\003II!!\007\n\003\017A\023x\016Z;diB\021qcG\005\0039I\021AbU3sS\006d\027N_1cY\026D\001B\b\001\003\026\004%\taH\001\005I\026\fG-F\001!!\t\t#%D\001\003\023\t\031#A\001\005BGR|'OU3g\021!)\003A!E!\002\023\001\023!\0023fC\022\004\003BB\024\001\t\003!\001&\001\004=S:LGO\020\013\003S)\002\"!\t\001\t\013y1\003\031\001\021\t\0171\002\021\021!C\001[\005!1m\0349z)\tIc\006C\004\037WA\005\t\031\001\021\t\017A\002\021\023!C\001c\005q1m\0349zI\021,g-Y;mi\022\nT#\001\032+\005\001\0324&\001\033\021\005URT\"\001\034\013\005]B\024!C;oG\",7m[3e\025\tI$#\001\006b]:|G/\031;j_:L!a\017\034\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\004>\001\005\005I\021\t \002\033A\024x\016Z;diB\023XMZ5y+\005y\004C\001!F\033\005\t%B\001\"D\003\021a\027M\\4\013\003\021\013AA[1wC&\021a)\021\002\007'R\024\030N\\4\t\017!\003\021\021!C\001\023\006a\001O]8ek\016$\030I]5usV\t!\n\005\002\030\027&\021AJ\005\002\004\023:$\bb\002(\001\003\003%\taT\001\017aJ|G-^2u\0132,W.\0328u)\t\0016\013\005\002\030#&\021!K\005\002\004\003:L\bb\002+N\003\003\005\rAS\001\004q\022\n\004b\002,\001\003\003%\teV\001\020aJ|G-^2u\023R,'/\031;peV\t\001\fE\002Z9Bk\021A\027\006\0037J\t!bY8mY\026\034G/[8o\023\ti&L\001\005Ji\026\024\030\r^8s\021\035y\006!!A\005\002\001\f\001bY1o\013F,\030\r\034\013\003C\022\004\"a\0062\n\005\r\024\"a\002\"p_2,\027M\034\005\b)z\013\t\0211\001Q\021\0351\007!!A\005B\035\f\001\002[1tQ\016{G-\032\013\002\025\"9\021\016AA\001\n\003R\027AB3rk\006d7\017\006\002bW\"9A\013[A\001\002\004\001\006f\001\001naB\021qC\\\005\003_J\021\001cU3sS\006dg+\032:tS>tW+\023#\037\003\0059qA\035\002\002\002#\0051/\001\nEK\006$\b\016U1di\026C8-\0329uS>t\007CA\021u\r\035\t!!!A\t\002U\0342\001\036<\033!\0219(\020I\025\016\003aT!!\037\n\002\017I,h\016^5nK&\0211\020\037\002\022\003\n\034HO]1di\032+hn\031;j_:\f\004\"B\024u\t\003iH#A:\t\021}$\030\021!C#\003\003\t\001\002^8TiJLgn\032\013\002!I\021Q\001;\002\002\023\005\025qA\001\006CB\004H.\037\013\004S\005%\001B\002\020\002\004\001\007\001\005C\005\002\016Q\f\t\021\"!\002\020\0059QO\\1qa2LH\003BA\t\003/\001BaFA\nA%\031\021Q\003\n\003\r=\003H/[8o\021%\tI\"a\003\002\002\003\007\021&A\002yIAB\021\"!\bu\003\003%I!a\b\002\027I,\027\r\032*fg>dg/\032\013\003\003C\0012\001QA\022\023\r\t)#\021\002\007\037\nTWm\031;")
/*     */ public class DeathPactException extends AkkaException implements NoStackTrace, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final ActorRef dead;
/*     */   
/*     */   public static <A> Function1<ActorRef, A> andThen(Function1<DeathPactException, A> paramFunction1) {
/*     */     return DeathPactException$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, DeathPactException> compose(Function1<A, ActorRef> paramFunction1) {
/*     */     return DeathPactException$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public Throwable scala$util$control$NoStackTrace$$super$fillInStackTrace() {
/* 230 */     return super.fillInStackTrace();
/*     */   }
/*     */   
/*     */   public Throwable fillInStackTrace() {
/* 230 */     return NoStackTrace.class.fillInStackTrace(this);
/*     */   }
/*     */   
/*     */   public ActorRef dead() {
/* 230 */     return this.dead;
/*     */   }
/*     */   
/*     */   public DeathPactException copy(ActorRef dead) {
/* 230 */     return new DeathPactException(dead);
/*     */   }
/*     */   
/*     */   public ActorRef copy$default$1() {
/* 230 */     return dead();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 230 */     return "DeathPactException";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 230 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 230 */     int i = x$1;
/* 230 */     switch (i) {
/*     */       default:
/* 230 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 230 */     return dead();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 230 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 230 */     return x$1 instanceof DeathPactException;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 230 */     return ScalaRunTime$.MODULE$._hashCode(this);
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
/*     */     //   8: instanceof akka/actor/DeathPactException
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/DeathPactException
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual dead : ()Lakka/actor/ActorRef;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual dead : ()Lakka/actor/ActorRef;
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
/*     */     //   #230	-> 0
/*     */     //   #63	-> 14
/*     */     //   #230	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/actor/DeathPactException;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public DeathPactException(ActorRef dead) {
/* 230 */     super((
/* 231 */         new StringBuilder()).append("Monitored actor [").append(dead).append("] terminated").toString());
/*     */     NoStackTrace.class.$init$(this);
/*     */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\DeathPactException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */