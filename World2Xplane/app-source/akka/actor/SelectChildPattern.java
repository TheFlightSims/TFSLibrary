/*     */ package akka.actor;
/*     */ 
/*     */ import akka.util.Helpers$;
/*     */ import java.util.regex.Pattern;
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005}b!B\001\003\001\0221!AE*fY\026\034Go\0215jY\022\004\026\r\036;fe:T!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lCN)\001aB\007\022)A\021\001bC\007\002\023)\t!\"A\003tG\006d\027-\003\002\r\023\t1\021I\\=SK\032\004\"AD\b\016\003\tI!\001\005\002\003)M+G.Z2uS>t\007+\031;i\0132,W.\0328u!\tA!#\003\002\024\023\t9\001K]8ek\016$\bC\001\005\026\023\t1\022B\001\007TKJL\027\r\\5{C\ndW\r\003\005\031\001\tU\r\021\"\001\033\003)\001\030\r\036;fe:\034FO]\002\001+\005Y\002C\001\017 \035\tAQ$\003\002\037\023\0051\001K]3eK\032L!\001I\021\003\rM#(/\0338h\025\tq\022\002\003\005$\001\tE\t\025!\003\034\003-\001\030\r\036;fe:\034FO\035\021\t\013\025\002A\021\001\024\002\rqJg.\033;?)\t9\003\006\005\002\017\001!)\001\004\na\0017!9!\006\001b\001\n\003Y\023a\0029biR,'O\\\013\002YA\021Q\006N\007\002])\021q\006M\001\006e\026<W\r\037\006\003cI\nA!\036;jY*\t1'\001\003kCZ\f\027BA\033/\005\035\001\026\r\036;fe:Daa\016\001!\002\023a\023\001\0039biR,'O\034\021\t\013e\002A\021\t\036\002\021Q|7\013\036:j]\036$\022a\007\005\by\001\t\t\021\"\001>\003\021\031w\016]=\025\005\035r\004b\002\r<!\003\005\ra\007\005\b\001\002\t\n\021\"\001B\0039\031w\016]=%I\0264\027-\0367uIE*\022A\021\026\0037\r[\023\001\022\t\003\013*k\021A\022\006\003\017\"\013\021\"\0368dQ\026\0347.\0323\013\005%K\021AC1o]>$\030\r^5p]&\0211J\022\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007bB'\001\003\003%\tET\001\016aJ|G-^2u!J,g-\033=\026\003=\003\"\001U*\016\003ES!A\025\032\002\t1\fgnZ\005\003AECq!\026\001\002\002\023\005a+\001\007qe>$Wo\031;Be&$\0300F\001X!\tA\001,\003\002Z\023\t\031\021J\034;\t\017m\003\021\021!C\0019\006q\001O]8ek\016$X\t\\3nK:$HCA/a!\tAa,\003\002`\023\t\031\021I\\=\t\017\005T\026\021!a\001/\006\031\001\020J\031\t\017\r\004\021\021!C!I\006y\001O]8ek\016$\030\n^3sCR|'/F\001f!\r1\027.X\007\002O*\021\001.C\001\013G>dG.Z2uS>t\027B\0016h\005!IE/\032:bi>\024\bb\0027\001\003\003%\t!\\\001\tG\006tW)];bYR\021a.\035\t\003\021=L!\001]\005\003\017\t{w\016\\3b]\"9\021m[A\001\002\004i\006bB:\001\003\003%\t\005^\001\tQ\006\034\bnQ8eKR\tq\013C\004w\001\005\005I\021I<\002\r\025\fX/\0317t)\tq\007\020C\004bk\006\005\t\031A/)\007\001QX\020\005\002\tw&\021A0\003\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022AA\004\n\n\t\t\021#\001\005\003\003\t!cU3mK\016$8\t[5mIB\013G\017^3s]B\031a\"a\001\007\023\005\021\021\021!E\001\t\005\0251#BA\002\003\017!\002CBA\005\003\037Yr%\004\002\002\f)\031\021QB\005\002\017I,h\016^5nK&!\021\021CA\006\005E\t%m\035;sC\016$h)\0368di&|g.\r\005\bK\005\rA\021AA\013)\t\t\t\001C\005:\003\007\t\t\021\"\022\002\032Q\tq\n\003\006\002\036\005\r\021\021!CA\003?\tQ!\0319qYf$2aJA\021\021\031A\0221\004a\0017!Q\021QEA\002\003\003%\t)a\n\002\017Ut\027\r\0359msR!\021\021FA\030!\021A\0211F\016\n\007\0055\022B\001\004PaRLwN\034\005\n\003c\t\031#!AA\002\035\n1\001\037\0231\021)\t)$a\001\002\002\023%\021qG\001\fe\026\fGMU3t_24X\r\006\002\002:A\031\001+a\017\n\007\005u\022K\001\004PE*,7\r\036")
/*     */ public class SelectChildPattern implements SelectionPathElement, Product, Serializable {
/*     */   public static final long serialVersionUID = 2L;
/*     */   
/*     */   private final String patternStr;
/*     */   
/*     */   private final Pattern pattern;
/*     */   
/*     */   public String patternStr() {
/* 269 */     return this.patternStr;
/*     */   }
/*     */   
/*     */   public SelectChildPattern copy(String patternStr) {
/* 269 */     return new SelectChildPattern(patternStr);
/*     */   }
/*     */   
/*     */   public String copy$default$1() {
/* 269 */     return patternStr();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 269 */     return "SelectChildPattern";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 269 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 269 */     int i = x$1;
/* 269 */     switch (i) {
/*     */       default:
/* 269 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 269 */     return patternStr();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 269 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 269 */     return x$1 instanceof SelectChildPattern;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 269 */     return ScalaRunTime$.MODULE$._hashCode(this);
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
/*     */     //   8: instanceof akka/actor/SelectChildPattern
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/SelectChildPattern
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual patternStr : ()Ljava/lang/String;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual patternStr : ()Ljava/lang/String;
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
/*     */     //   #269	-> 0
/*     */     //   #63	-> 14
/*     */     //   #269	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/actor/SelectChildPattern;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public SelectChildPattern(String patternStr) {
/* 269 */     Product.class.$init$(this);
/* 270 */     this.pattern = Helpers$.MODULE$.makePattern(patternStr);
/*     */   }
/*     */   
/*     */   public Pattern pattern() {
/* 270 */     return this.pattern;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 271 */     return patternStr();
/*     */   }
/*     */   
/*     */   public static <A> Function1<String, A> andThen(Function1<SelectChildPattern, A> paramFunction1) {
/*     */     return SelectChildPattern$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, SelectChildPattern> compose(Function1<A, String> paramFunction1) {
/*     */     return SelectChildPattern$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\SelectChildPattern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */