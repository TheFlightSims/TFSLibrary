/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.util.regexp.Base;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005}b\001B\001\003\001&\021\001\"\022'F\033\026sEk\025\006\003\007\021\t1\001\032;e\025\t)a!A\002y[2T\021aB\001\006g\016\fG.Y\002\001'\021\001!B\004\n\021\005-aQ\"\001\002\n\0055\021!a\004#G\003\016{g\016^3oi6{G-\0327\021\005=\001R\"\001\004\n\005E1!a\002)s_\022,8\r\036\t\003\037MI!\001\006\004\003\031M+'/[1mSj\f'\r\\3\t\021Y\001!Q3A\005\002]\t\021A]\013\0021A\021\021\004\b\b\003\027iI!a\007\002\002\031\r{g\016^3oi6{G-\0327\n\005uq\"A\002*fO\026C\b/\003\002 A\t!!)Y:f\025\t\t#%\001\004sK\036,\007\020\035\006\003G\031\tA!\036;jY\"AQ\005\001B\tB\003%\001$\001\002sA!)q\005\001C\001Q\0051A(\0338jiz\"\"!\013\026\021\005-\001\001\"\002\f'\001\004A\002\"\002\027\001\t\003j\023a\0032vS2$7\013\036:j]\036$\"A\f\036\021\005=:dB\001\0316\035\t\tD'D\0013\025\t\031\004\"\001\004=e>|GOP\005\002\017%\021aGB\001\ba\006\0347.Y4f\023\tA\024HA\007TiJLgn\032\"vS2$WM\035\006\003m\031AQaO\026A\0029\n!a\0352\t\017u\002\021\021!C\001}\005!1m\0349z)\tIs\bC\004\027yA\005\t\031\001\r\t\017\005\003\021\023!C\001\005\006q1m\0349zI\021,g-Y;mi\022\nT#A\"+\005a!5&A#\021\005\031[U\"A$\013\005!K\025!C;oG\",7m[3e\025\tQe!\001\006b]:|G/\031;j_:L!\001T$\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\004O\001\005\005I\021I(\002\033A\024x\016Z;diB\023XMZ5y+\005\001\006CA)W\033\005\021&BA*U\003\021a\027M\\4\013\003U\013AA[1wC&\021qK\025\002\007'R\024\030N\\4\t\017e\003\021\021!C\0015\006a\001O]8ek\016$\030I]5usV\t1\f\005\002\0209&\021QL\002\002\004\023:$\bbB0\001\003\003%\t\001Y\001\017aJ|G-^2u\0132,W.\0328u)\t\tG\r\005\002\020E&\0211M\002\002\004\003:L\bbB3_\003\003\005\raW\001\004q\022\n\004bB4\001\003\003%\t\005[\001\020aJ|G-^2u\023R,'/\031;peV\t\021\016E\002k[\006l\021a\033\006\003Y\032\t!bY8mY\026\034G/[8o\023\tq7N\001\005Ji\026\024\030\r^8s\021\035\001\b!!A\005\002E\f\001bY1o\013F,\030\r\034\013\003eV\004\"aD:\n\005Q4!a\002\"p_2,\027M\034\005\bK>\f\t\0211\001b\021\0359\b!!A\005Ba\f\001\002[1tQ\016{G-\032\013\0027\"9!\020AA\001\n\003Z\030AB3rk\006d7\017\006\002sy\"9Q-_A\001\002\004\twa\002@\003\003\003E\ta`\001\t\0132+U*\022(U'B\0311\"!\001\007\021\005\021\021\021!E\001\003\007\031R!!\001\002\006I\001b!a\002\002\016aISBAA\005\025\r\tYAB\001\beVtG/[7f\023\021\ty!!\003\003#\005\0237\017\036:bGR4UO\\2uS>t\027\007C\004(\003\003!\t!a\005\025\003}D!\"a\006\002\002\005\005IQIA\r\003!!xn\025;sS:<G#\001)\t\025\005u\021\021AA\001\n\003\013y\"A\003baBd\027\020F\002*\003CAaAFA\016\001\004A\002BCA\023\003\003\t\t\021\"!\002(\0059QO\\1qa2LH\003BA\025\003_\001BaDA\0261%\031\021Q\006\004\003\r=\003H/[8o\021%\t\t$a\t\002\002\003\007\021&A\002yIAB!\"!\016\002\002\005\005I\021BA\034\003-\021X-\0313SKN|GN^3\025\005\005e\002cA)\002<%\031\021Q\b*\003\r=\023'.Z2u\001")
/*     */ public class ELEMENTS extends DFAContentModel implements Product, Serializable {
/*     */   private final Base.RegExp r;
/*     */   
/*     */   public Base.RegExp r() {
/* 117 */     return this.r;
/*     */   }
/*     */   
/*     */   public ELEMENTS copy(Base.RegExp r) {
/* 117 */     return new ELEMENTS(r);
/*     */   }
/*     */   
/*     */   public Base.RegExp copy$default$1() {
/* 117 */     return r();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 117 */     return "ELEMENTS";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 117 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 117 */     switch (x$1) {
/*     */       default:
/* 117 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 117 */     return r();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 117 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 117 */     return x$1 instanceof ELEMENTS;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 117 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 75
/*     */     //   5: aload_1
/*     */     //   6: instanceof scala/xml/dtd/ELEMENTS
/*     */     //   9: ifeq -> 17
/*     */     //   12: iconst_1
/*     */     //   13: istore_2
/*     */     //   14: goto -> 19
/*     */     //   17: iconst_0
/*     */     //   18: istore_2
/*     */     //   19: iload_2
/*     */     //   20: ifeq -> 79
/*     */     //   23: aload_1
/*     */     //   24: checkcast scala/xml/dtd/ELEMENTS
/*     */     //   27: astore #4
/*     */     //   29: aload_0
/*     */     //   30: invokevirtual r : ()Lscala/util/regexp/Base$RegExp;
/*     */     //   33: aload #4
/*     */     //   35: invokevirtual r : ()Lscala/util/regexp/Base$RegExp;
/*     */     //   38: astore_3
/*     */     //   39: dup
/*     */     //   40: ifnonnull -> 51
/*     */     //   43: pop
/*     */     //   44: aload_3
/*     */     //   45: ifnull -> 58
/*     */     //   48: goto -> 71
/*     */     //   51: aload_3
/*     */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   55: ifeq -> 71
/*     */     //   58: aload #4
/*     */     //   60: aload_0
/*     */     //   61: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   64: ifeq -> 71
/*     */     //   67: iconst_1
/*     */     //   68: goto -> 72
/*     */     //   71: iconst_0
/*     */     //   72: ifeq -> 79
/*     */     //   75: iconst_1
/*     */     //   76: goto -> 80
/*     */     //   79: iconst_0
/*     */     //   80: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #117	-> 0
/*     */     //   #236	-> 12
/*     */     //   #117	-> 19
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	81	0	this	Lscala/xml/dtd/ELEMENTS;
/*     */     //   0	81	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ELEMENTS(Base.RegExp r) {
/* 117 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public StringBuilder buildString(StringBuilder sb) {
/* 119 */     return ContentModel$.MODULE$.buildString(r(), sb);
/*     */   }
/*     */   
/*     */   public static <A> Function1<Base.RegExp, A> andThen(Function1<ELEMENTS, A> paramFunction1) {
/*     */     return ELEMENTS$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, ELEMENTS> compose(Function1<A, Base.RegExp> paramFunction1) {
/*     */     return ELEMENTS$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ELEMENTS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */