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
/*     */ @ScalaSignature(bytes = "\006\001\005eb!B\001\003\001\032A!A\002*fgVlWM\003\002\004\t\00511/_:ng\036T!!\002\004\002\021\021L7\017]1uG\"T\021aB\001\005C.\\\027m\005\004\001\023=\031b#\007\t\003\0255i\021a\003\006\002\031\005)1oY1mC&\021ab\003\002\007\003:L(+\0324\021\005A\tR\"\001\002\n\005I\021!!D*zgR,W.T3tg\006<W\r\005\002\021)%\021QC\001\002\034'R\f7\017[,iK:<\026-\033;j]\0364uN]\"iS2$'/\0328\021\005)9\022B\001\r\f\005\035\001&o\0343vGR\004\"A\003\016\n\005mY!\001D*fe&\fG.\033>bE2,\007\002C\017\001\005+\007I\021A\020\002\037\r\fWo]3e\005f4\025-\0337ve\026\034\001!F\001!!\t\t\023F\004\002#O9\0211EJ\007\002I)\021QEH\001\007yI|w\016\036 \n\0031I!\001K\006\002\017A\f7m[1hK&\021!f\013\002\n)\"\024xn^1cY\026T!\001K\006\t\0215\002!\021#Q\001\n\001\n\001cY1vg\026$')\037$bS2,(/\032\021\t\013=\002A\021\001\031\002\rqJg.\033;?)\t\t$\007\005\002\021\001!)QD\fa\001A!9A\007AA\001\n\003)\024\001B2paf$\"!\r\034\t\017u\031\004\023!a\001A!9\001\bAI\001\n\003I\024AD2paf$C-\0324bk2$H%M\013\002u)\022\001eO\026\002yA\021QHQ\007\002})\021q\bQ\001\nk:\034\007.Z2lK\022T!!Q\006\002\025\005tgn\034;bi&|g.\003\002D}\t\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\017\025\003\021\021!C!\r\006i\001O]8ek\016$\bK]3gSb,\022a\022\t\003\0216k\021!\023\006\003\025.\013A\001\\1oO*\tA*\001\003kCZ\f\027B\001(J\005\031\031FO]5oO\"9\001\013AA\001\n\003\t\026\001\0049s_\022,8\r^!sSRLX#\001*\021\005)\031\026B\001+\f\005\rIe\016\036\005\b-\002\t\t\021\"\001X\0039\001(o\0343vGR,E.Z7f]R$\"\001W.\021\005)I\026B\001.\f\005\r\te.\037\005\b9V\013\t\0211\001S\003\rAH%\r\005\b=\002\t\t\021\"\021`\003=\001(o\0343vGRLE/\032:bi>\024X#\0011\021\007\005$\007,D\001c\025\t\0317\"\001\006d_2dWm\031;j_:L!!\0322\003\021%#XM]1u_JDqa\032\001\002\002\023\005\001.\001\005dC:,\025/^1m)\tIG\016\005\002\013U&\0211n\003\002\b\005>|G.Z1o\021\035af-!AA\002aCqA\034\001\002\002\023\005s.\001\005iCND7i\0343f)\005\021\006bB9\001\003\003%\tE]\001\ti>\034FO]5oOR\tq\tC\004u\001\005\005I\021I;\002\r\025\fX/\0317t)\tIg\017C\004]g\006\005\t\031\001-)\007\001A8\020\005\002\013s&\021!p\003\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022!A\004\t{\n\t\t\021#\001\007}\0061!+Z:v[\026\004\"\001E@\007\023\005\021\021\021!E\001\r\005\0051\003B@\002\004e\001b!!\002\002\f\001\nTBAA\004\025\r\tIaC\001\beVtG/[7f\023\021\ti!a\002\003#\005\0237\017\036:bGR4UO\\2uS>t\027\007\003\0040\022\005\021\021\003\013\002}\"9\021o`A\001\n\013\022\b\"CA\f\006\005I\021QA\r\003\025\t\007\017\0357z)\r\t\0241\004\005\007;\005U\001\031\001\021\t\023\005}q0!A\005\002\006\005\022aB;oCB\004H.\037\013\005\003G\tI\003\005\003\013\003K\001\023bAA\024\027\t1q\n\035;j_:D\021\"a\013\002\036\005\005\t\031A\031\002\007a$\003\007C\005\0020}\f\t\021\"\003\0022\005Y!/Z1e%\026\034x\016\034<f)\t\t\031\004E\002I\003kI1!a\016J\005\031y%M[3di\002")
/*     */ public class Resume implements SystemMessage, StashWhenWaitingForChildren, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Throwable causedByFailure;
/*     */   
/*     */   private transient SystemMessage next;
/*     */   
/*     */   public static <A> Function1<Throwable, A> andThen(Function1<Resume, A> paramFunction1) {
/*     */     return Resume$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, Resume> compose(Function1<A, Throwable> paramFunction1) {
/*     */     return Resume$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public SystemMessage next() {
/* 225 */     return this.next;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void next_$eq(SystemMessage x$1) {
/* 225 */     this.next = x$1;
/*     */   }
/*     */   
/*     */   public void unlink() {
/* 225 */     SystemMessage$class.unlink(this);
/*     */   }
/*     */   
/*     */   public boolean unlinked() {
/* 225 */     return SystemMessage$class.unlinked(this);
/*     */   }
/*     */   
/*     */   public Throwable causedByFailure() {
/* 225 */     return this.causedByFailure;
/*     */   }
/*     */   
/*     */   public Resume copy(Throwable causedByFailure) {
/* 225 */     return new Resume(causedByFailure);
/*     */   }
/*     */   
/*     */   public Throwable copy$default$1() {
/* 225 */     return causedByFailure();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 225 */     return "Resume";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 225 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 225 */     int i = x$1;
/* 225 */     switch (i) {
/*     */       default:
/* 225 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 225 */     return causedByFailure();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 225 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 225 */     return x$1 instanceof Resume;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 225 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 225 */     return ScalaRunTime$.MODULE$._toString(this);
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
/*     */     //   8: instanceof akka/dispatch/sysmsg/Resume
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/dispatch/sysmsg/Resume
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual causedByFailure : ()Ljava/lang/Throwable;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual causedByFailure : ()Ljava/lang/Throwable;
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
/*     */     //   #225	-> 0
/*     */     //   #63	-> 14
/*     */     //   #225	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/dispatch/sysmsg/Resume;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Resume(Throwable causedByFailure) {
/* 225 */     SystemMessage$class.$init$(this);
/* 225 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Resume.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */