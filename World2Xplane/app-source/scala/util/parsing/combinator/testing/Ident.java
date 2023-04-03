/*   */ package scala.util.parsing.combinator.testing;
/*   */ 
/*   */ import scala.Function1;
/*   */ import scala.Product;
/*   */ import scala.Serializable;
/*   */ import scala.collection.Iterator;
/*   */ import scala.reflect.ScalaSignature;
/*   */ import scala.runtime.BoxesRunTime;
/*   */ import scala.runtime.ScalaRunTime$;
/*   */ 
/*   */ @ScalaSignature(bytes = "\006\001\0055b\001B\001\003\0016\021Q!\0233f]RT!a\001\003\002\017Q,7\017^5oO*\021QAB\001\013G>l'-\0338bi>\024(BA\004\t\003\035\001\030M]:j]\036T!!\003\006\002\tU$\030\016\034\006\002\027\005)1oY1mC\016\0011\003\002\001\017%U\001\"a\004\t\016\003)I!!\005\006\003\r\005s\027PU3g!\ty1#\003\002\025\025\t9\001K]8ek\016$\bCA\b\027\023\t9\"B\001\007TKJL\027\r\\5{C\ndW\r\003\005\032\001\tU\r\021\"\001\033\003\005\031X#A\016\021\005qybBA\b\036\023\tq\"\"\001\004Qe\026$WMZ\005\003A\005\022aa\025;sS:<'B\001\020\013\021!\031\003A!E!\002\023Y\022AA:!\021\025)\003\001\"\001'\003\031a\024N\\5u}Q\021q%\013\t\003Q\001i\021A\001\005\0063\021\002\ra\007\005\bW\001\t\t\021\"\001-\003\021\031w\016]=\025\005\035j\003bB\r+!\003\005\ra\007\005\b_\001\t\n\021\"\0011\0039\031w\016]=%I\0264\027-\0367uIE*\022!\r\026\0037IZ\023a\r\t\003iej\021!\016\006\003m]\n\021\"\0368dQ\026\0347.\0323\013\005aR\021AC1o]>$\030\r^5p]&\021!(\016\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007b\002\037\001\003\003%\t%P\001\016aJ|G-^2u!J,g-\033=\026\003y\002\"a\020#\016\003\001S!!\021\"\002\t1\fgn\032\006\002\007\006!!.\031<b\023\t\001\003\tC\004G\001\005\005I\021A$\002\031A\024x\016Z;di\006\023\030\016^=\026\003!\003\"aD%\n\005)S!aA%oi\"9A\nAA\001\n\003i\025A\0049s_\022,8\r^#mK6,g\016\036\013\003\035F\003\"aD(\n\005AS!aA!os\"9!kSA\001\002\004A\025a\001=%c!9A\013AA\001\n\003*\026a\0049s_\022,8\r^%uKJ\fGo\034:\026\003Y\0032a\026.O\033\005A&BA-\013\003)\031w\016\0347fGRLwN\\\005\0037b\023\001\"\023;fe\006$xN\035\005\b;\002\t\t\021\"\001_\003!\031\027M\\#rk\006dGCA0c!\ty\001-\003\002b\025\t9!i\\8mK\006t\007b\002*]\003\003\005\rA\024\005\bI\002\t\t\021\"\021f\003!A\027m\0355D_\022,G#\001%\t\017\035\004\021\021!C!Q\006AAo\\*ue&tw\rF\001?\021\035Q\007!!A\005B-\fa!Z9vC2\034HCA0m\021\035\021\026.!AA\0029CC\001\0018rgB\021qb\\\005\003a*\021!\002Z3qe\026\034\027\r^3eC\005\021\030A\007+iSN\0043\r\\1tg\002:\030\016\0347!E\026\004#/Z7pm\026$\027%\001;\002\rIr\023\007\r\0301\017\0351(!!A\t\002]\fQ!\0233f]R\004\"\001\013=\007\017\005\021\021\021!E\001sN\031\001P_\013\021\tmt8dJ\007\002y*\021QPC\001\beVtG/[7f\023\tyHPA\tBEN$(/Y2u\rVt7\r^5p]FBa!\n=\005\002\005\rA#A<\t\017\035D\030\021!C#Q\"I\021\021\002=\002\002\023\005\0251B\001\006CB\004H.\037\013\004O\0055\001BB\r\002\b\001\0071\004C\005\002\022a\f\t\021\"!\002\024\0059QO\\1qa2LH\003BA\013\0037\001BaDA\f7%\031\021\021\004\006\003\r=\003H/[8o\021%\ti\"a\004\002\002\003\007q%A\002yIAB\021\"!\ty\003\003%I!a\t\002\027I,\027\r\032*fg>dg/\032\013\003\003K\0012aPA\024\023\r\tI\003\021\002\007\037\nTWm\031;)\tat\027o\035")
/*   */ public class Ident implements Product, Serializable {
/*   */   private final String s;
/*   */   
/*   */   public static <A> Function1<String, A> andThen(Function1<Ident, A> paramFunction1) {
/*   */     return Ident$.MODULE$.andThen(paramFunction1);
/*   */   }
/*   */   
/*   */   public static <A> Function1<A, Ident> compose(Function1<A, String> paramFunction1) {
/*   */     return Ident$.MODULE$.compose(paramFunction1);
/*   */   }
/*   */   
/*   */   public String s() {
/* 9 */     return this.s;
/*   */   }
/*   */   
/*   */   public Ident copy(String s) {
/* 9 */     return new Ident(s);
/*   */   }
/*   */   
/*   */   public String copy$default$1() {
/* 9 */     return s();
/*   */   }
/*   */   
/*   */   public String productPrefix() {
/* 9 */     return "Ident";
/*   */   }
/*   */   
/*   */   public int productArity() {
/* 9 */     return 1;
/*   */   }
/*   */   
/*   */   public Object productElement(int x$1) {
/* 9 */     switch (x$1) {
/*   */       default:
/* 9 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*   */       case 0:
/*   */         break;
/*   */     } 
/* 9 */     return s();
/*   */   }
/*   */   
/*   */   public Iterator<Object> productIterator() {
/* 9 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*   */   }
/*   */   
/*   */   public boolean canEqual(Object x$1) {
/* 9 */     return x$1 instanceof Ident;
/*   */   }
/*   */   
/*   */   public int hashCode() {
/* 9 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*   */   }
/*   */   
/*   */   public String toString() {
/* 9 */     return ScalaRunTime$.MODULE$._toString(this);
/*   */   }
/*   */   
/*   */   public boolean equals(Object x$1) {
/*   */     // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: aload_1
/*   */     //   2: if_acmpeq -> 75
/*   */     //   5: aload_1
/*   */     //   6: instanceof scala/util/parsing/combinator/testing/Ident
/*   */     //   9: ifeq -> 17
/*   */     //   12: iconst_1
/*   */     //   13: istore_2
/*   */     //   14: goto -> 19
/*   */     //   17: iconst_0
/*   */     //   18: istore_2
/*   */     //   19: iload_2
/*   */     //   20: ifeq -> 79
/*   */     //   23: aload_1
/*   */     //   24: checkcast scala/util/parsing/combinator/testing/Ident
/*   */     //   27: astore #4
/*   */     //   29: aload_0
/*   */     //   30: invokevirtual s : ()Ljava/lang/String;
/*   */     //   33: aload #4
/*   */     //   35: invokevirtual s : ()Ljava/lang/String;
/*   */     //   38: astore_3
/*   */     //   39: dup
/*   */     //   40: ifnonnull -> 51
/*   */     //   43: pop
/*   */     //   44: aload_3
/*   */     //   45: ifnull -> 58
/*   */     //   48: goto -> 71
/*   */     //   51: aload_3
/*   */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*   */     //   55: ifeq -> 71
/*   */     //   58: aload #4
/*   */     //   60: aload_0
/*   */     //   61: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*   */     //   64: ifeq -> 71
/*   */     //   67: iconst_1
/*   */     //   68: goto -> 72
/*   */     //   71: iconst_0
/*   */     //   72: ifeq -> 79
/*   */     //   75: iconst_1
/*   */     //   76: goto -> 80
/*   */     //   79: iconst_0
/*   */     //   80: ireturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #9	-> 0
/*   */     //   #236	-> 12
/*   */     //   #9	-> 19
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	81	0	this	Lscala/util/parsing/combinator/testing/Ident;
/*   */     //   0	81	1	x$1	Ljava/lang/Object;
/*   */   }
/*   */   
/*   */   public Ident(String s) {
/* 9 */     Product.class.$init$(this);
/*   */   }
/*   */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\testing\Ident.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */