/*    */ package scala.xml.pull;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005ea\001B\001\003\001&\021a!\022<UKb$(BA\002\005\003\021\001X\017\0347\013\005\0251\021a\001=nY*\tq!A\003tG\006d\027m\001\001\024\013\001QaBE\013\021\005-aQ\"\001\004\n\00551!AB!osJ+g\r\005\002\020!5\t!!\003\002\022\005\tA\001,\024'Fm\026tG\017\005\002\f'%\021AC\002\002\b!J|G-^2u!\tYa#\003\002\030\r\ta1+\032:jC2L'0\0312mK\"A\021\004\001BK\002\023\005!$\001\003uKb$X#A\016\021\005qybBA\006\036\023\tqb!\001\004Qe\026$WMZ\005\003A\005\022aa\025;sS:<'B\001\020\007\021!\031\003A!E!\002\023Y\022!\002;fqR\004\003\"B\023\001\t\0031\023A\002\037j]&$h\b\006\002(QA\021q\002\001\005\0063\021\002\ra\007\005\bU\001\t\t\021\"\001,\003\021\031w\016]=\025\005\035b\003bB\r*!\003\005\ra\007\005\b]\001\t\n\021\"\0010\0039\031w\016]=%I\0264\027-\0367uIE*\022\001\r\026\0037EZ\023A\r\t\003gaj\021\001\016\006\003kY\n\021\"\0368dQ\026\0347.\0323\013\005]2\021AC1o]>$\030\r^5p]&\021\021\b\016\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007bB\036\001\003\003%\t\005P\001\016aJ|G-^2u!J,g-\033=\026\003u\002\"AP\"\016\003}R!\001Q!\002\t1\fgn\032\006\002\005\006!!.\031<b\023\t\001s\bC\004F\001\005\005I\021\001$\002\031A\024x\016Z;di\006\023\030\016^=\026\003\035\003\"a\003%\n\005%3!aA%oi\"91\nAA\001\n\003a\025A\0049s_\022,8\r^#mK6,g\016\036\013\003\033B\003\"a\003(\n\005=3!aA!os\"9\021KSA\001\002\0049\025a\001=%c!91\013AA\001\n\003\"\026a\0049s_\022,8\r^%uKJ\fGo\034:\026\003U\0032AV-N\033\0059&B\001-\007\003)\031w\016\0347fGRLwN\\\005\0035^\023\001\"\023;fe\006$xN\035\005\b9\002\t\t\021\"\001^\003!\031\027M\\#rk\006dGC\0010b!\tYq,\003\002a\r\t9!i\\8mK\006t\007bB)\\\003\003\005\r!\024\005\bG\002\t\t\021\"\021e\003!A\027m\0355D_\022,G#A$\t\017\031\004\021\021!C!O\006AAo\\*ue&tw\rF\001>\021\035I\007!!A\005B)\fa!Z9vC2\034HC\0010l\021\035\t\006.!AA\0025;q!\034\002\002\002#\005a.\001\004FmR+\007\020\036\t\003\037=4q!\001\002\002\002#\005\001oE\002pcV\001BA];\034O5\t1O\003\002u\r\0059!/\0368uS6,\027B\001<t\005E\t%m\035;sC\016$h)\0368di&|g.\r\005\006K=$\t\001\037\013\002]\"9am\\A\001\n\013:\007bB>p\003\003%\t\t`\001\006CB\004H.\037\013\003OuDQ!\007>A\002mA\001b`8\002\002\023\005\025\021A\001\bk:\f\007\017\0357z)\021\t\031!!\003\021\t-\t)aG\005\004\003\0171!AB(qi&|g\016\003\005\002\fy\f\t\0211\001(\003\rAH\005\r\005\n\003\037y\027\021!C\005\003#\t1B]3bIJ+7o\0347wKR\021\0211\003\t\004}\005U\021bAA\f\t1qJ\0316fGR\004")
/*    */ public class EvText implements XMLEvent, Product, Serializable {
/*    */   private final String text;
/*    */   
/*    */   public static <A> Function1<String, A> andThen(Function1<EvText, A> paramFunction1) {
/*    */     return EvText$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, EvText> compose(Function1<A, String> paramFunction1) {
/*    */     return EvText$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public String text() {
/* 38 */     return this.text;
/*    */   }
/*    */   
/*    */   public EvText copy(String text) {
/* 38 */     return new EvText(text);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 38 */     return text();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 38 */     return "EvText";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 38 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 38 */     switch (x$1) {
/*    */       default:
/* 38 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 38 */     return text();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 38 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 38 */     return x$1 instanceof EvText;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 38 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 38 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 75
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/pull/EvText
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 79
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/pull/EvText
/*    */     //   27: astore #4
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual text : ()Ljava/lang/String;
/*    */     //   33: aload #4
/*    */     //   35: invokevirtual text : ()Ljava/lang/String;
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
/*    */     //   #38	-> 0
/*    */     //   #236	-> 12
/*    */     //   #38	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	81	0	this	Lscala/xml/pull/EvText;
/*    */     //   0	81	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public EvText(String text) {
/* 38 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\pull\EvText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */