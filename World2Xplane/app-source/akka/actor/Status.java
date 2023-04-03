/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\035v!B\001\003\021\0039\021AB*uCR,8O\003\002\004\t\005)\021m\031;pe*\tQ!\001\003bW.\f7\001\001\t\003\021%i\021A\001\004\006\025\tA\ta\003\002\007'R\fG/^:\024\005%a\001CA\007\021\033\005q!\"A\b\002\013M\034\027\r\\1\n\005Eq!AB!osJ+g\rC\003\024\023\021\005A#\001\004=S:LGO\020\013\002\017\0319!\"\003I\001$C12cA\013\r/A\021Q\002G\005\00339\021AbU3sS\006d\027N_1cY\026LC!F\016\002\004\031!A$\003!\036\005\0351\025-\0337ve\026\034Ra\007\007\037A]\001\"aH\013\016\003%\001\"!D\021\n\005\tr!a\002)s_\022,8\r\036\005\tIm\021)\032!C\001K\005)1-Y;tKV\ta\005\005\002(_9\021\001&\f\b\003S1j\021A\013\006\003W\031\ta\001\020:p_Rt\024\"A\b\n\0059r\021a\0029bG.\fw-Z\005\003aE\022\021\002\0265s_^\f'\r\\3\013\0059r\001\002C\032\034\005#\005\013\021\002\024\002\r\r\fWo]3!\021\025\0312\004\"\0016)\t1t\007\005\002 7!)A\005\016a\001M!9\021hGA\001\n\003Q\024\001B2paf$\"AN\036\t\017\021B\004\023!a\001M!9QhGI\001\n\003q\024AD2paf$C-\0324bk2$H%M\013\002)\022a\005Q\026\002\003B\021!iR\007\002\007*\021A)R\001\nk:\034\007.Z2lK\022T!A\022\b\002\025\005tgn\034;bi&|g.\003\002I\007\n\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\017)[\022\021!C!\027\006i\001O]8ek\016$\bK]3gSb,\022\001\024\t\003\033Jk\021A\024\006\003\037B\013A\001\\1oO*\t\021+\001\003kCZ\f\027BA*O\005\031\031FO]5oO\"9QkGA\001\n\0031\026\001\0049s_\022,8\r^!sSRLX#A,\021\0055A\026BA-\017\005\rIe\016\036\005\b7n\t\t\021\"\001]\0039\001(o\0343vGR,E.Z7f]R$\"!\0301\021\0055q\026BA0\017\005\r\te.\037\005\bCj\013\t\0211\001X\003\rAH%\r\005\bGn\t\t\021\"\021e\003=\001(o\0343vGRLE/\032:bi>\024X#A3\021\007\031LW,D\001h\025\tAg\"\001\006d_2dWm\031;j_:L!A[4\003\021%#XM]1u_JDq\001\\\016\002\002\023\005Q.\001\005dC:,\025/^1m)\tq\027\017\005\002\016_&\021\001O\004\002\b\005>|G.Z1o\021\035\t7.!AA\002uCqa]\016\002\002\023\005C/\001\005iCND7i\0343f)\0059\006b\002<\034\003\003%\te^\001\ti>\034FO]5oOR\tA\nC\004z7\005\005I\021\t>\002\r\025\fX/\0317t)\tq7\020C\004bq\006\005\t\031A/)\tmi\030\021\001\t\003\033yL!a \b\003!M+'/[1m-\026\0248/[8o+&#e$A\001\007\r\005\025\021\002QA\004\005\035\031VoY2fgN\034b!a\001\r=\001:\002bCA\006\003\007\021)\032!C\001\003\033\taa\035;biV\034X#A/\t\025\005E\0211\001B\tB\003%Q,A\004ti\006$Xo\035\021\t\017M\t\031\001\"\001\002\026Q!\021qCA\r!\ry\0221\001\005\b\003\027\t\031\0021\001^\021%I\0241AA\001\n\003\ti\002\006\003\002\030\005}\001\"CA\006\0037\001\n\0211\001^\021%i\0241AI\001\n\003\t\031#\006\002\002&)\022Q\f\021\005\t\025\006\r\021\021!C!\027\"AQ+a\001\002\002\023\005a\013C\005\\\003\007\t\t\021\"\001\002.Q\031Q,a\f\t\021\005\fY#!AA\002]C\001bYA\002\003\003%\t\005\032\005\nY\006\r\021\021!C\001\003k!2A\\A\034\021!\t\0271GA\001\002\004i\006\002C:\002\004\005\005I\021\t;\t\021Y\f\031!!A\005B]D\021\"_A\002\003\003%\t%a\020\025\0079\f\t\005\003\005b\003{\t\t\0211\001^Q\025\t\031!`A\001\017%\t9%CA\001\022\003\tI%A\004Tk\016\034Wm]:\021\007}\tYEB\005\002\006%\t\t\021#\001\002NM)\0211JA(/A9\021\021KA,;\006]QBAA*\025\r\t)FD\001\beVtG/[7f\023\021\tI&a\025\003#\005\0237\017\036:bGR4UO\\2uS>t\027\007C\004\024\003\027\"\t!!\030\025\005\005%\003\002\003<\002L\005\005IQI<\t\025\005\r\0241JA\001\n\003\013)'A\003baBd\027\020\006\003\002\030\005\035\004bBA\006\003C\002\r!\030\005\013\003W\nY%!A\005\002\0065\024aB;oCB\004H.\037\013\005\003_\n)\b\005\003\016\003cj\026bAA:\035\t1q\n\035;j_:D!\"a\036\002j\005\005\t\031AA\f\003\rAH\005\r\005\013\003w\nY%!A\005\n\005u\024a\003:fC\022\024Vm]8mm\026$\"!a \021\0075\013\t)C\002\002\004:\023aa\0242kK\016$x!CAD\023\005\005\t\022AAE\003\0351\025-\0337ve\026\0042aHAF\r!a\022\"!A\t\002\00555#BAF\003\037;\002CBA)\003/2c\007C\004\024\003\027#\t!a%\025\005\005%\005\002\003<\002\f\006\005IQI<\t\025\005\r\0241RA\001\n\003\013I\nF\0027\0037Ca\001JAL\001\0041\003BCA6\003\027\013\t\021\"!\002 R!\021\021UAR!\021i\021\021\017\024\t\023\005]\024QTA\001\002\0041\004BCA>\003\027\013\t\021\"\003\002~\001")
/*     */ public final class Status {
/*     */   public static interface Status extends Serializable {}
/*     */   
/*     */   public static class Success implements Status, Product {
/*     */     public static final long serialVersionUID = 1L;
/*     */     
/*     */     private final Object status;
/*     */     
/*     */     public Object status() {
/* 258 */       return this.status;
/*     */     }
/*     */     
/*     */     public Success copy(Object status) {
/* 258 */       return new Success(status);
/*     */     }
/*     */     
/*     */     public Object copy$default$1() {
/* 258 */       return status();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/* 258 */       return "Success";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 258 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 258 */       int i = x$1;
/* 258 */       switch (i) {
/*     */         default:
/* 258 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/* 258 */       return status();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 258 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 258 */       return x$1 instanceof Success;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 258 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 258 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/* 258 */       if (this != x$1) {
/*     */         boolean bool;
/* 258 */         Object object = x$1;
/* 258 */         if (object instanceof Success) {
/*     */           bool = true;
/*     */         } else {
/*     */           bool = false;
/*     */         } 
/* 258 */         if (bool) {
/* 258 */           Success success = (Success)x$1;
/* 258 */           if ((BoxesRunTime.equals(status(), success.status()) && success.canEqual(this)));
/*     */         } 
/* 258 */         return false;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Success(Object status) {
/* 258 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Success$ extends AbstractFunction1<Object, Success> implements Serializable {
/*     */     public static final Success$ MODULE$;
/*     */     
/*     */     public final String toString() {
/* 258 */       return "Success";
/*     */     }
/*     */     
/*     */     public Status.Success apply(Object status) {
/* 258 */       return new Status.Success(status);
/*     */     }
/*     */     
/*     */     public Option<Object> unapply(Status.Success x$0) {
/* 258 */       return (x$0 == null) ? (Option<Object>)scala.None$.MODULE$ : (Option<Object>)new Some(x$0.status());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 258 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Success$() {
/* 258 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Failure implements Status, Product {
/*     */     public static final long serialVersionUID = 1L;
/*     */     
/*     */     private final Throwable cause;
/*     */     
/*     */     public Throwable cause() {
/* 265 */       return this.cause;
/*     */     }
/*     */     
/*     */     public Failure copy(Throwable cause) {
/* 265 */       return new Failure(cause);
/*     */     }
/*     */     
/*     */     public Throwable copy$default$1() {
/* 265 */       return cause();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/* 265 */       return "Failure";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 265 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 265 */       int i = x$1;
/* 265 */       switch (i) {
/*     */         default:
/* 265 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/* 265 */       return cause();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 265 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 265 */       return x$1 instanceof Failure;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 265 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 265 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 80
/*     */       //   5: aload_1
/*     */       //   6: astore_2
/*     */       //   7: aload_2
/*     */       //   8: instanceof akka/actor/Status$Failure
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 84
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/actor/Status$Failure
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual cause : ()Ljava/lang/Throwable;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual cause : ()Ljava/lang/Throwable;
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 76
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 76
/*     */       //   63: aload #4
/*     */       //   65: aload_0
/*     */       //   66: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   69: ifeq -> 76
/*     */       //   72: iconst_1
/*     */       //   73: goto -> 77
/*     */       //   76: iconst_0
/*     */       //   77: ifeq -> 84
/*     */       //   80: iconst_1
/*     */       //   81: goto -> 85
/*     */       //   84: iconst_0
/*     */       //   85: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #265	-> 0
/*     */       //   #63	-> 14
/*     */       //   #265	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	86	0	this	Lakka/actor/Status$Failure;
/*     */       //   0	86	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public Failure(Throwable cause) {
/* 265 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Failure$ extends AbstractFunction1<Throwable, Failure> implements Serializable {
/*     */     public static final Failure$ MODULE$;
/*     */     
/*     */     public final String toString() {
/* 265 */       return "Failure";
/*     */     }
/*     */     
/*     */     public Status.Failure apply(Throwable cause) {
/* 265 */       return new Status.Failure(cause);
/*     */     }
/*     */     
/*     */     public Option<Throwable> unapply(Status.Failure x$0) {
/* 265 */       return (x$0 == null) ? (Option<Throwable>)scala.None$.MODULE$ : (Option<Throwable>)new Some(x$0.cause());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 265 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Failure$() {
/* 265 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Status.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */