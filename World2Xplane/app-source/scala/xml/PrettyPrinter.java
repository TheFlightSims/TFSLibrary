/*     */ package scala.xml;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\r\ra\001B\001\003\001\035\021Q\002\025:fiRL\bK]5oi\026\024(BA\002\005\003\rAX\016\034\006\002\013\005)1oY1mC\016\0011C\001\001\t!\tI!\"D\001\005\023\tYAA\001\004B]f\024VM\032\005\t\033\001\021\t\021)A\005\035\005)q/\0333uQB\021\021bD\005\003!\021\0211!\0238u\021!\021\002A!A!\002\023q\021\001B:uKBDQ\001\006\001\005\002U\ta\001P5oSRtDc\001\f\0313A\021q\003A\007\002\005!)Qb\005a\001\035!)!c\005a\001\035\031!1\004\001\001\035\005=\021%o\\6f]\026C8-\0329uS>t7C\001\016\036!\tq2%D\001 \025\t\001\023%\001\003mC:<'\"\001\022\002\t)\fg/Y\005\003I}\021\021\"\022=dKB$\030n\0348\t\013QQB\021\001\024\025\003\035\002\"\001\013\016\016\003\0011AA\013\001\001W\t!\021\n^3n'\tI\003\002C\003\025S\021\005Q\006F\001/!\tA\023fB\0031\001!\005\025'A\003Ce\026\f7\016\005\002)e\031)1\007\001EAi\t)!I]3bWN!!GL\0339!\tIa'\003\0028\t\t9\001K]8ek\016$\bCA\005:\023\tQDA\001\007TKJL\027\r\\5{C\ndW\rC\003\025e\021\005A\bF\0012\021\025q$\007\"\021@\003!!xn\025;sS:<G#\001!\021\005y\t\025B\001\" \005\031\031FO]5oO\"9AIMA\001\n\003*\025!\0049s_\022,8\r\036)sK\032L\0070F\001A\021\0359%'!A\005\002!\013A\002\035:pIV\034G/\021:jif,\022A\004\005\b\025J\n\t\021\"\001L\0039\001(o\0343vGR,E.Z7f]R$\"\001T(\021\005%i\025B\001(\005\005\r\te.\037\005\b!&\013\t\0211\001\017\003\rAH%\r\005\b%J\n\t\021\"\021T\003=\001(o\0343vGRLE/\032:bi>\024X#\001+\021\007UCF*D\001W\025\t9F!\001\006d_2dWm\031;j_:L!!\027,\003\021%#XM]1u_JDqa\027\032\002\002\023\005A,\001\005dC:,\025/^1m)\ti\006\r\005\002\n=&\021q\f\002\002\b\005>|G.Z1o\021\035\001&,!AA\0021CqA\031\032\002\002\023\0053-\001\005iCND7i\0343f)\005q\001bB33\003\003%IAZ\001\fe\026\fGMU3t_24X\rF\001h!\tq\002.\003\002j?\t1qJ\0316fGR4Aa\033\001AY\n\031!i\034=\024\t)tS\007\017\005\t]*\024)\032!C\001\021\006\0311m\0347\t\021AT'\021#Q\001\n9\tAaY8mA!A!O\033BK\002\023\0051/A\001t+\005!\bCA;y\035\tIa/\003\002x\t\0051\001K]3eK\032L!AQ=\013\005]$\001\002C>k\005#\005\013\021\002;\002\005M\004\003\"\002\013k\t\003iH\003\002@\000\003\003\001\"\001\0136\t\0139d\b\031\001\b\t\013Id\b\031\001;\t\023\005\025!.!A\005\002\005\035\021\001B2paf$RA`A\005\003\027A\001B\\A\002!\003\005\rA\004\005\te\006\r\001\023!a\001i\"I\021q\0026\022\002\023\005\021\021C\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\t\t\031BK\002\017\003+Y#!a\006\021\t\005e\0211E\007\003\0037QA!!\b\002 \005IQO\\2iK\016\\W\r\032\006\004\003C!\021AC1o]>$\030\r^5p]&!\021QEA\016\005E)hn\0315fG.,GMV1sS\006t7-\032\005\n\003SQ\027\023!C\001\003W\tabY8qs\022\"WMZ1vYR$#'\006\002\002.)\032A/!\006\t\017\021S\027\021!C!\013\"9qI[A\001\n\003A\005\002\003&k\003\003%\t!!\016\025\0071\0139\004\003\005Q\003g\t\t\0211\001\017\021\035\021&.!A\005BMC\001b\0276\002\002\023\005\021Q\b\013\004;\006}\002\002\003)\002<\005\005\t\031\001'\t\017\tT\027\021!C!G\"9aH[A\001\n\003z\004\"CA$U\006\005I\021IA%\003\031)\027/^1mgR\031Q,a\023\t\021A\013)%!AA\0021;\021\"a\024\001\003\003E\t!!\025\002\007\t{\007\020E\002)\003'2\001b\033\001\002\002#\005\021QK\n\006\003'\n9\006\017\t\b\0033\nyF\004;\033\t\tYFC\002\002^\021\tqA];oi&lW-\003\003\002b\005m#!E!cgR\024\030m\031;Gk:\034G/[8oe!9A#a\025\005\002\005\025DCAA)\021!q\0241KA\001\n\013z\004BCA6\003'\n\t\021\"!\002n\005)\021\r\0359msR)a0a\034\002r!1a.!\033A\0029AaA]A5\001\004!\bBCA;\003'\n\t\021\"!\002x\0059QO\\1qa2LH\003BA=\003\013\003R!CA>\003J1!! \005\005\031y\005\017^5p]B)\021\"!!\017i&\031\0211\021\003\003\rQ+\b\017\\33\021%\t9)a\035\002\002\003\007a0A\002yIAB\001\"ZA*\003\003%IA\032\004\007\003\033\003\001)a$\003\tA\013'/Y\n\006\003\027sS\007\017\005\ne\006-%Q3A\005\002MD\021b_AF\005#\005\013\021\002;\t\017Q\tY\t\"\001\002\030R!\021\021TAN!\rA\0231\022\005\007e\006U\005\031\001;\t\025\005\025\0211RA\001\n\003\ty\n\006\003\002\032\006\005\006\002\003:\002\036B\005\t\031\001;\t\025\005=\0211RI\001\n\003\tY\003\003\005E\003\027\013\t\021\"\021F\021!9\0251RA\001\n\003A\005\"\003&\002\f\006\005I\021AAV)\ra\025Q\026\005\t!\006%\026\021!a\001\035!A!+a#\002\002\023\0053\013C\005\\\003\027\013\t\021\"\001\0024R\031Q,!.\t\021A\013\t,!AA\0021C\001BYAF\003\003%\te\031\005\t}\005-\025\021!C!!Q\021qIAF\003\003%\t%!0\025\007u\013y\f\003\005Q\003w\013\t\0211\001M\017%\t\031\rAA\001\022\003\t)-\001\003QCJ\f\007c\001\025\002H\032I\021Q\022\001\002\002#\005\021\021Z\n\006\003\017\fY\r\017\t\b\0033\ni\r^AM\023\021\ty-a\027\003#\005\0237\017\036:bGR4UO\\2uS>t\027\007C\004\025\003\017$\t!a5\025\005\005\025\007\002\003 \002H\006\005IQI \t\025\005-\024qYA\001\n\003\013I\016\006\003\002\032\006m\007B\002:\002X\002\007A\017\003\006\002v\005\035\027\021!CA\003?$B!!9\002dB!\021\"a\037u\021)\t9)!8\002\002\003\007\021\021\024\005\tK\006\035\027\021!C\005M\"I\021\021\036\001A\002\023E\0211^\001\006SR,Wn]\013\003\003[\004R!a<\002\000:rA!!=\002|:!\0211_A}\033\t\t)PC\002\002x\032\ta\001\020:p_Rt\024\"A\003\n\007\005uH!A\004qC\016\\\027mZ3\n\t\t\005!1\001\002\005\031&\034HOC\002\002~\022A\021Ba\002\001\001\004%\tB!\003\002\023%$X-\\:`I\025\fH\003\002B\006\005#\0012!\003B\007\023\r\021y\001\002\002\005+:LG\017C\005Q\005\013\t\t\0211\001\002n\"A!Q\003\001!B\023\ti/\001\004ji\026l7\017\t\005\t\0053\001\001\031!C\t\021\006\0311-\036:\t\023\tu\001\0011A\005\022\t}\021aB2ve~#S-\035\013\005\005\027\021\t\003\003\005Q\0057\t\t\0211\001\017\021\035\021)\003\001Q!\n9\tAaY;sA!9!\021\006\001\005\022\t-\022!\002:fg\026$HC\001B\006\021\035\021y\003\001C\t\005c\t1aY;u)\031\tiOa\r\0036!1!O!\fA\002QDqAa\016\003.\001\007a\"A\002j]\022DqAa\017\001\t#\021i$A\004nC.,'i\034=\025\r\t-!q\bB!\021\035\0219D!\017A\0029AaA\035B\035\001\004!\bb\002B#\001\021E!qI\001\t[\006\\W\rU1sCR1!1\002B%\005\027BqAa\016\003D\001\007a\002\003\004s\005\007\002\r\001\036\005\b\005\037\002A\021\003B\026\003%i\027m[3Ce\026\f7\016C\004\003T\001!\tB!\026\002\0171,\027M\032+bOR\031AOa\026\t\021\te#\021\013a\001\0057\n\021A\034\t\004/\tu\023b\001B0\005\t!aj\0343f\021\035\021\031\007\001C\t\005K\n\001b\035;beR$\026m\032\013\007\005O\022IGa\033\021\013%\t\t\t\036\b\t\021\te#\021\ra\001\0057B\001B!\034\003b\001\007!qN\001\007aN\034w\016]3\021\007]\021\t(C\002\003t\t\021\001CT1nKN\004\030mY3CS:$\027N\\4\t\017\t]\004\001\"\005\003z\0051QM\0343UC\036$2\001\036B>\021!\021IF!\036A\002\tm\003b\002B@\001\021E!\021Q\001\022G\"LG\016\032:f]\006\023X\rT3bm\026\034HcA/\003\004\"A!\021\fB?\001\004\021Y\006C\004\003\b\002!\tB!#\002\t\031LGo\035\013\004;\n-\005b\002BG\005\013\003\r\001^\001\005i\026\034H\017C\004\003\022\002!IAa%\002\025\021|\007K]3tKJ4X\rF\002^\005+C\001Ba&\003\020\002\007!1L\001\005]>$W\rC\004\003\034\002!\tB!(\002\021Q\024\030M^3sg\026$\002Ba\003\003 \n\005&1\025\005\t\005/\023I\n1\001\003\\!A!Q\016BM\001\004\021y\007C\004\0038\te\005\031\001\b\t\017\tm\005\001\"\005\003(RA!1\002BU\005c\023)\f\003\005\003,\n\025\006\031\001BW\003\tIG\017\005\004\002p\n=&1L\005\0043\n\r\001\002\003BZ\005K\003\rAa\034\002\013M\034w\016]3\t\017\t]\"Q\025a\001\035!9!\021\030\001\005\002\tm\026A\0024pe6\fG\017\006\004\003\f\tu&q\030\005\t\0053\0229\f1\001\003\\!A!\021\031B\\\001\004\021\031-\001\002tEB!\021q\036Bc\023\021\0219Ma\001\003\033M#(/\0338h\005VLG\016Z3s\021\035\021I\f\001C\001\005\027$\002Ba\003\003N\n='\021\033\005\t\0053\022I\r1\001\003\\!A!Q\016Be\001\004\021y\007\003\005\003B\n%\007\031\001Bb\021\035\021I\f\001C\001\005+$R\001\036Bl\0053D\001B!\027\003T\002\007!1\f\005\013\005[\022\031\016%AA\002\t=\004b\002Bo\001\021\005!q\\\001\fM>\024X.\031;O_\022,7\017F\003u\005C\024Y\017\003\005\003d\nm\007\031\001Bs\003\025qw\016Z3t!\031\tyOa:\003\\%!!\021\036B\002\005\r\031V-\035\005\013\005[\022Y\016%AA\002\t=\004b\002Bo\001\021\005!q\036\013\t\005\027\021\tPa=\003v\"A!1\035Bw\001\004\021)\017\003\005\003n\t5\b\031\001B8\021!\021\tM!<A\002\t\r\007\"\003B}\001E\005I\021\001B~\003A1wN]7bi\022\"WMZ1vYR$#'\006\002\003~*\"!qNA\013\021%\031\t\001AI\001\n\003\021Y0A\013g_Jl\027\r\036(pI\026\034H\005Z3gCVdG\017\n\032")
/*     */ public class PrettyPrinter {
/*     */   private final int width;
/*     */   
/*     */   private final int step;
/*     */   
/*     */   public class BrokenException extends Exception {
/*     */     public BrokenException(PrettyPrinter $outer) {}
/*     */   }
/*     */   
/*     */   public class Item {
/*     */     public Item(PrettyPrinter $outer) {}
/*     */   }
/*     */   
/*     */   private Break$ Break$lzycompute() {
/*  29 */     synchronized (this) {
/*  29 */       if (this.Break$module == null)
/*  29 */         this.Break$module = new Break$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/xml/PrettyPrinter}} */
/*  29 */       return this.Break$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Break$ Break() {
/*  29 */     return (this.Break$module == null) ? Break$lzycompute() : this.Break$module;
/*     */   }
/*     */   
/*     */   public class Break$ extends Item implements Product, Serializable {
/*     */     public Break$(PrettyPrinter $outer) {
/*  29 */       super($outer);
/*  29 */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  29 */       return scala$xml$PrettyPrinter$Break$$$outer().Break();
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  29 */       return 64448735;
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  29 */       return x$1 instanceof Break$;
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  29 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  29 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  29 */       return 0;
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  29 */       return "Break";
/*     */     }
/*     */     
/*     */     public String toString() {
/*  30 */       return "\\";
/*     */     }
/*     */   }
/*     */   
/*     */   private Box$ Box$lzycompute() {
/*  32 */     synchronized (this) {
/*  32 */       if (this.Box$module == null)
/*  32 */         this.Box$module = new Box$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/xml/PrettyPrinter}} */
/*  32 */       return this.Box$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Box$ Box() {
/*  32 */     return (this.Box$module == null) ? Box$lzycompute() : this.Box$module;
/*     */   }
/*     */   
/*     */   public class Box$ extends AbstractFunction2<Object, String, Box> implements Serializable {
/*     */     public final String toString() {
/*  32 */       return "Box";
/*     */     }
/*     */     
/*     */     public PrettyPrinter.Box apply(int col, String s) {
/*  32 */       return new PrettyPrinter.Box(this.$outer, col, s);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<Object, String>> unapply(PrettyPrinter.Box x$0) {
/*  32 */       return (x$0 == null) ? (Option<Tuple2<Object, String>>)scala.None$.MODULE$ : (Option<Tuple2<Object, String>>)new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.col()), x$0.s()));
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  32 */       return this.$outer.Box();
/*     */     }
/*     */     
/*     */     public Box$(PrettyPrinter $outer) {}
/*     */   }
/*     */   
/*     */   public class Box extends Item implements Product, Serializable {
/*     */     private final int col;
/*     */     
/*     */     private final String s;
/*     */     
/*     */     public int col() {
/*  32 */       return this.col;
/*     */     }
/*     */     
/*     */     public String s() {
/*  32 */       return this.s;
/*     */     }
/*     */     
/*     */     public Box copy(int col, String s) {
/*  32 */       return new Box(scala$xml$PrettyPrinter$Box$$$outer(), col, s);
/*     */     }
/*     */     
/*     */     public int copy$default$1() {
/*  32 */       return col();
/*     */     }
/*     */     
/*     */     public String copy$default$2() {
/*  32 */       return s();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  32 */       return "Box";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  32 */       return 2;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  32 */       switch (x$1) {
/*     */         default:
/*  32 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 1:
/*     */         
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  32 */       return BoxesRunTime.boxToInteger(col());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  32 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  32 */       return x$1 instanceof Box;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  32 */       return Statics.finalizeHash(Statics.mix(Statics.mix(-889275714, col()), Statics.anyHash(s())), 2);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  32 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 101
/*     */       //   5: aload_1
/*     */       //   6: instanceof scala/xml/PrettyPrinter$Box
/*     */       //   9: ifeq -> 31
/*     */       //   12: aload_1
/*     */       //   13: checkcast scala/xml/PrettyPrinter$Box
/*     */       //   16: invokevirtual scala$xml$PrettyPrinter$Box$$$outer : ()Lscala/xml/PrettyPrinter;
/*     */       //   19: aload_0
/*     */       //   20: invokevirtual scala$xml$PrettyPrinter$Box$$$outer : ()Lscala/xml/PrettyPrinter;
/*     */       //   23: if_acmpne -> 31
/*     */       //   26: iconst_1
/*     */       //   27: istore_2
/*     */       //   28: goto -> 33
/*     */       //   31: iconst_0
/*     */       //   32: istore_2
/*     */       //   33: iload_2
/*     */       //   34: ifeq -> 105
/*     */       //   37: aload_1
/*     */       //   38: checkcast scala/xml/PrettyPrinter$Box
/*     */       //   41: astore #4
/*     */       //   43: aload_0
/*     */       //   44: invokevirtual col : ()I
/*     */       //   47: aload #4
/*     */       //   49: invokevirtual col : ()I
/*     */       //   52: if_icmpne -> 97
/*     */       //   55: aload_0
/*     */       //   56: invokevirtual s : ()Ljava/lang/String;
/*     */       //   59: aload #4
/*     */       //   61: invokevirtual s : ()Ljava/lang/String;
/*     */       //   64: astore_3
/*     */       //   65: dup
/*     */       //   66: ifnonnull -> 77
/*     */       //   69: pop
/*     */       //   70: aload_3
/*     */       //   71: ifnull -> 84
/*     */       //   74: goto -> 97
/*     */       //   77: aload_3
/*     */       //   78: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   81: ifeq -> 97
/*     */       //   84: aload #4
/*     */       //   86: aload_0
/*     */       //   87: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   90: ifeq -> 97
/*     */       //   93: iconst_1
/*     */       //   94: goto -> 98
/*     */       //   97: iconst_0
/*     */       //   98: ifeq -> 105
/*     */       //   101: iconst_1
/*     */       //   102: goto -> 106
/*     */       //   105: iconst_0
/*     */       //   106: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #32	-> 0
/*     */       //   #236	-> 26
/*     */       //   #32	-> 33
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	107	0	this	Lscala/xml/PrettyPrinter$Box;
/*     */       //   0	107	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public Box(PrettyPrinter $outer, int col, String s) {
/*  32 */       super($outer);
/*  32 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   private Para$ Para$lzycompute() {
/*  33 */     synchronized (this) {
/*  33 */       if (this.Para$module == null)
/*  33 */         this.Para$module = new Para$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/xml/PrettyPrinter}} */
/*  33 */       return this.Para$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Para$ Para() {
/*  33 */     return (this.Para$module == null) ? Para$lzycompute() : this.Para$module;
/*     */   }
/*     */   
/*     */   public class Para$ extends AbstractFunction1<String, Para> implements Serializable {
/*     */     public final String toString() {
/*  33 */       return "Para";
/*     */     }
/*     */     
/*     */     public PrettyPrinter.Para apply(String s) {
/*  33 */       return new PrettyPrinter.Para(this.$outer, s);
/*     */     }
/*     */     
/*     */     public Option<String> unapply(PrettyPrinter.Para x$0) {
/*  33 */       return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.s());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  33 */       return this.$outer.Para();
/*     */     }
/*     */     
/*     */     public Para$(PrettyPrinter $outer) {}
/*     */   }
/*     */   
/*     */   public class Para extends Item implements Product, Serializable {
/*     */     private final String s;
/*     */     
/*     */     public String s() {
/*  33 */       return this.s;
/*     */     }
/*     */     
/*     */     public Para copy(String s) {
/*  33 */       return new Para(scala$xml$PrettyPrinter$Para$$$outer(), s);
/*     */     }
/*     */     
/*     */     public String copy$default$1() {
/*  33 */       return s();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  33 */       return "Para";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  33 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  33 */       switch (x$1) {
/*     */         default:
/*  33 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  33 */       return s();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  33 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  33 */       return x$1 instanceof Para;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  33 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  33 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 89
/*     */       //   5: aload_1
/*     */       //   6: instanceof scala/xml/PrettyPrinter$Para
/*     */       //   9: ifeq -> 31
/*     */       //   12: aload_1
/*     */       //   13: checkcast scala/xml/PrettyPrinter$Para
/*     */       //   16: invokevirtual scala$xml$PrettyPrinter$Para$$$outer : ()Lscala/xml/PrettyPrinter;
/*     */       //   19: aload_0
/*     */       //   20: invokevirtual scala$xml$PrettyPrinter$Para$$$outer : ()Lscala/xml/PrettyPrinter;
/*     */       //   23: if_acmpne -> 31
/*     */       //   26: iconst_1
/*     */       //   27: istore_2
/*     */       //   28: goto -> 33
/*     */       //   31: iconst_0
/*     */       //   32: istore_2
/*     */       //   33: iload_2
/*     */       //   34: ifeq -> 93
/*     */       //   37: aload_1
/*     */       //   38: checkcast scala/xml/PrettyPrinter$Para
/*     */       //   41: astore #4
/*     */       //   43: aload_0
/*     */       //   44: invokevirtual s : ()Ljava/lang/String;
/*     */       //   47: aload #4
/*     */       //   49: invokevirtual s : ()Ljava/lang/String;
/*     */       //   52: astore_3
/*     */       //   53: dup
/*     */       //   54: ifnonnull -> 65
/*     */       //   57: pop
/*     */       //   58: aload_3
/*     */       //   59: ifnull -> 72
/*     */       //   62: goto -> 85
/*     */       //   65: aload_3
/*     */       //   66: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   69: ifeq -> 85
/*     */       //   72: aload #4
/*     */       //   74: aload_0
/*     */       //   75: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   78: ifeq -> 85
/*     */       //   81: iconst_1
/*     */       //   82: goto -> 86
/*     */       //   85: iconst_0
/*     */       //   86: ifeq -> 93
/*     */       //   89: iconst_1
/*     */       //   90: goto -> 94
/*     */       //   93: iconst_0
/*     */       //   94: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #33	-> 0
/*     */       //   #236	-> 26
/*     */       //   #33	-> 33
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	95	0	this	Lscala/xml/PrettyPrinter$Para;
/*     */       //   0	95	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public Para(PrettyPrinter $outer, String s) {
/*  33 */       super($outer);
/*  33 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*  35 */   private List<Item> items = (List<Item>)scala.collection.immutable.Nil$.MODULE$;
/*     */   
/*     */   public List<Item> items() {
/*  35 */     return this.items;
/*     */   }
/*     */   
/*     */   public void items_$eq(List<Item> x$1) {
/*  35 */     this.items = x$1;
/*     */   }
/*     */   
/*  37 */   private int cur = 0;
/*     */   
/*     */   private volatile Break$ Break$module;
/*     */   
/*     */   private volatile Box$ Box$module;
/*     */   
/*     */   private volatile Para$ Para$module;
/*     */   
/*     */   public int cur() {
/*  37 */     return this.cur;
/*     */   }
/*     */   
/*     */   public void cur_$eq(int x$1) {
/*  37 */     this.cur = x$1;
/*     */   }
/*     */   
/*     */   public void reset() {
/*  40 */     cur_$eq(0);
/*  41 */     items_$eq((List<Item>)scala.collection.immutable.Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   public List<Item> cut(String s, int ind) {
/*     */     List list;
/*  47 */     int tmp = this.width - cur();
/*  48 */     if (s.length() <= tmp) {
/*  49 */       (new Box[1])[0] = new Box(this, ind, s);
/*  49 */       return scala.collection.immutable.List$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Box[1]));
/*     */     } 
/*  50 */     new StringBuilder();
/*  51 */     int i = s.indexOf(' ');
/*  52 */     if (i > tmp || i == -1)
/*  52 */       throw new BrokenException(this); 
/*  54 */     scala.collection.immutable.Nil$ nil$2 = scala.collection.immutable.Nil$.MODULE$;
/*  55 */     while (i != -1 && i < tmp) {
/*  56 */       list = nil$2.$colon$colon(BoxesRunTime.boxToInteger(i));
/*  57 */       i = s.indexOf(' ', i + 1);
/*     */     } 
/*  59 */     scala.collection.immutable.Nil$ nil$1 = scala.collection.immutable.Nil$.MODULE$;
/*     */     while (true) {
/*  60 */       if (scala.collection.immutable.Nil$.MODULE$ == null) {
/*  60 */         if (list == null)
/*  69 */           throw new BrokenException(this); 
/*     */       } else if (scala.collection.immutable.Nil$.MODULE$.equals(list)) {
/*  69 */         throw new BrokenException(this);
/*     */       } 
/*     */       try {
/*     */         Box b = new Box(this, ind, s.substring(0, BoxesRunTime.unboxToInt(list.head())));
/*     */         cur_$eq(ind);
/*     */         Break$ break$ = Break();
/*     */         cut(s.substring(BoxesRunTime.unboxToInt(list.head()), s.length()), ind).$colon$colon(break$).$colon$colon(b);
/*     */         nil$1 = null;
/*     */         list = (List)list.tail();
/*     */       } catch (BrokenException brokenException) {
/*     */         list = (List)list.tail();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void makeBox(int ind, String s) {
/*  75 */     if (cur() + s.length() > this.width) {
/*  76 */       items_$eq(items().$colon$colon(new Box(this, ind, s)));
/*  77 */       cur_$eq(cur() + s.length());
/*     */     } else {
/*     */       try {
/*  79 */         for (List<Item> list1 = cut(s, ind), these1 = list1; !these1.isEmpty(); ) {
/*  79 */           Object object = these1.head();
/*  79 */           Item item = (Item)object;
/*  79 */           items_$eq(items().$colon$colon(item));
/*  79 */           these1 = (List<Item>)these1.tail();
/*     */         } 
/*  79 */       } catch (BrokenException brokenException) {
/*  80 */         makePara(ind, s);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void makePara(int ind, String s) {
/*  84 */     Break$ break$1 = Break();
/*  84 */     Para para = new Para(this, s);
/*  84 */     Break$ break$2 = Break();
/*  84 */     items_$eq(items().$colon$colon(break$2).$colon$colon(para).$colon$colon(break$1));
/*  85 */     cur_$eq(ind);
/*     */   }
/*     */   
/*     */   public void makeBreak() {
/*  90 */     Break$ break$ = Break();
/*  90 */     items_$eq(items().$colon$colon(break$));
/*  91 */     cur_$eq(0);
/*     */   }
/*     */   
/*     */   public final void scala$xml$PrettyPrinter$$mkLeaf$1(StringBuilder sb, Node n$1) {
/*  96 */     sb.append('<');
/*  97 */     n$1.nameToString(sb);
/*  98 */     n$1.attributes().buildString(sb);
/*  99 */     sb.append("/>");
/*     */   }
/*     */   
/*     */   public String leafTag(Node n) {
/* 101 */     return Utility$.MODULE$.sbToString((Function1<StringBuilder, BoxedUnit>)new PrettyPrinter$$anonfun$leafTag$1(this, n));
/*     */   }
/*     */   
/*     */   public class PrettyPrinter$$anonfun$leafTag$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Node n$1;
/*     */     
/*     */     public final void apply(StringBuilder sb) {
/* 101 */       this.$outer.scala$xml$PrettyPrinter$$mkLeaf$1(sb, this.n$1);
/*     */     }
/*     */     
/*     */     public PrettyPrinter$$anonfun$leafTag$1(PrettyPrinter $outer, Node n$1) {}
/*     */   }
/*     */   
/*     */   public Tuple2<String, Object> startTag(Node n, NamespaceBinding pscope) {
/* 105 */     IntRef i = new IntRef(0);
/* 114 */     return new Tuple2(Utility$.MODULE$.sbToString((Function1<StringBuilder, BoxedUnit>)new PrettyPrinter$$anonfun$startTag$1(this, n, pscope, i)), BoxesRunTime.boxToInteger(i.elem));
/*     */   }
/*     */   
/*     */   public final void scala$xml$PrettyPrinter$$mkStart$1(StringBuilder sb, Node n$2, NamespaceBinding pscope$1, IntRef i$1) {
/*     */     sb.append('<');
/*     */     n$2.nameToString(sb);
/*     */     i$1.elem = sb.length() + 1;
/*     */     n$2.attributes().buildString(sb);
/*     */     n$2.scope().buildString(sb, pscope$1);
/*     */     sb.append('>');
/*     */   }
/*     */   
/*     */   public class PrettyPrinter$$anonfun$startTag$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Node n$2;
/*     */     
/*     */     private final NamespaceBinding pscope$1;
/*     */     
/*     */     private final IntRef i$1;
/*     */     
/*     */     public final void apply(StringBuilder sb) {
/* 114 */       this.$outer.scala$xml$PrettyPrinter$$mkStart$1(sb, this.n$2, this.pscope$1, this.i$1);
/*     */     }
/*     */     
/*     */     public PrettyPrinter$$anonfun$startTag$1(PrettyPrinter $outer, Node n$2, NamespaceBinding pscope$1, IntRef i$1) {}
/*     */   }
/*     */   
/*     */   public final void scala$xml$PrettyPrinter$$mkEnd$1(StringBuilder sb, Node n$3) {
/* 119 */     sb.append("</");
/* 120 */     n$3.nameToString(sb);
/* 121 */     sb.append('>');
/*     */   }
/*     */   
/*     */   public String endTag(Node n) {
/* 123 */     return Utility$.MODULE$.sbToString((Function1<StringBuilder, BoxedUnit>)new PrettyPrinter$$anonfun$endTag$1(this, n));
/*     */   }
/*     */   
/*     */   public class PrettyPrinter$$anonfun$endTag$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Node n$3;
/*     */     
/*     */     public final void apply(StringBuilder sb) {
/* 123 */       this.$outer.scala$xml$PrettyPrinter$$mkEnd$1(sb, this.n$3);
/*     */     }
/*     */     
/*     */     public PrettyPrinter$$anonfun$endTag$1(PrettyPrinter $outer, Node n$3) {}
/*     */   }
/*     */   
/*     */   public final boolean scala$xml$PrettyPrinter$$isLeaf$1(Node l) {
/*     */     boolean bool1;
/*     */     boolean bool2;
/* 127 */     if (l instanceof Atom) {
/* 127 */       bool1 = true;
/* 127 */     } else if (l instanceof Comment) {
/* 127 */       bool1 = true;
/* 127 */     } else if (l instanceof EntityRef) {
/* 127 */       bool1 = true;
/* 127 */     } else if (l instanceof ProcInstr) {
/* 127 */       bool1 = true;
/*     */     } else {
/* 127 */       bool1 = false;
/*     */     } 
/* 127 */     if (bool1) {
/* 127 */       bool2 = true;
/*     */     } else {
/* 129 */       bool2 = false;
/*     */     } 
/*     */     return bool2;
/*     */   }
/*     */   
/*     */   public boolean childrenAreLeaves(Node n) {
/* 131 */     return n.child().forall((Function1)new PrettyPrinter$$anonfun$childrenAreLeaves$1(this));
/*     */   }
/*     */   
/*     */   public class PrettyPrinter$$anonfun$childrenAreLeaves$1 extends AbstractFunction1<Node, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Node l) {
/* 131 */       return this.$outer.scala$xml$PrettyPrinter$$isLeaf$1(l);
/*     */     }
/*     */     
/*     */     public PrettyPrinter$$anonfun$childrenAreLeaves$1(PrettyPrinter $outer) {}
/*     */   }
/*     */   
/*     */   public boolean fits(String test) {
/* 135 */     return (test.length() < this.width - cur());
/*     */   }
/*     */   
/*     */   private boolean doPreserve(Node node) {
/*     */     // Byte code:
/*     */     //   0: getstatic scala/xml/XML$.MODULE$ : Lscala/xml/XML$;
/*     */     //   3: invokevirtual namespace : ()Ljava/lang/String;
/*     */     //   6: getstatic scala/xml/XML$.MODULE$ : Lscala/xml/XML$;
/*     */     //   9: invokevirtual space : ()Ljava/lang/String;
/*     */     //   12: astore #5
/*     */     //   14: astore_3
/*     */     //   15: aload_1
/*     */     //   16: invokevirtual attributes : ()Lscala/xml/MetaData;
/*     */     //   19: astore_2
/*     */     //   20: aload_1
/*     */     //   21: invokevirtual scope : ()Lscala/xml/NamespaceBinding;
/*     */     //   24: astore #4
/*     */     //   26: getstatic scala/Option$.MODULE$ : Lscala/Option$;
/*     */     //   29: aload_2
/*     */     //   30: aload_3
/*     */     //   31: aload #4
/*     */     //   33: aload #5
/*     */     //   35: invokevirtual apply : (Ljava/lang/String;Lscala/xml/NamespaceBinding;Ljava/lang/String;)Lscala/collection/Seq;
/*     */     //   38: invokevirtual apply : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   41: dup
/*     */     //   42: astore #6
/*     */     //   44: invokevirtual isEmpty : ()Z
/*     */     //   47: ifeq -> 56
/*     */     //   50: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   53: goto -> 113
/*     */     //   56: new scala/Some
/*     */     //   59: dup
/*     */     //   60: aload #6
/*     */     //   62: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   65: checkcast scala/collection/Seq
/*     */     //   68: invokeinterface toString : ()Ljava/lang/String;
/*     */     //   73: getstatic scala/xml/XML$.MODULE$ : Lscala/xml/XML$;
/*     */     //   76: invokevirtual preserve : ()Ljava/lang/String;
/*     */     //   79: astore #9
/*     */     //   81: dup
/*     */     //   82: ifnonnull -> 94
/*     */     //   85: pop
/*     */     //   86: aload #9
/*     */     //   88: ifnull -> 102
/*     */     //   91: goto -> 106
/*     */     //   94: aload #9
/*     */     //   96: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   99: ifeq -> 106
/*     */     //   102: iconst_1
/*     */     //   103: goto -> 107
/*     */     //   106: iconst_0
/*     */     //   107: invokestatic boxToBoolean : (Z)Ljava/lang/Boolean;
/*     */     //   110: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   113: dup
/*     */     //   114: astore #10
/*     */     //   116: invokevirtual isEmpty : ()Z
/*     */     //   119: ifeq -> 129
/*     */     //   122: iconst_0
/*     */     //   123: invokestatic boxToBoolean : (Z)Ljava/lang/Boolean;
/*     */     //   126: goto -> 134
/*     */     //   129: aload #10
/*     */     //   131: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   134: invokestatic unboxToBoolean : (Ljava/lang/Object;)Z
/*     */     //   137: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #138	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	138	0	this	Lscala/xml/PrettyPrinter;
/*     */     //   0	138	1	node	Lscala/xml/Node;
/*     */   }
/*     */   
/*     */   public void traverse(Node node, NamespaceBinding pscope, int ind) {
/*     */     // Byte code:
/*     */     //   0: getstatic scala/xml/Text$.MODULE$ : Lscala/xml/Text$;
/*     */     //   3: aload_1
/*     */     //   4: invokevirtual unapply : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   7: astore #4
/*     */     //   9: aload #4
/*     */     //   11: invokevirtual isEmpty : ()Z
/*     */     //   14: ifne -> 51
/*     */     //   17: aload #4
/*     */     //   19: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   22: checkcast java/lang/String
/*     */     //   25: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   28: dup
/*     */     //   29: ifnonnull -> 42
/*     */     //   32: pop
/*     */     //   33: ldc_w ''
/*     */     //   36: ifnull -> 494
/*     */     //   39: goto -> 51
/*     */     //   42: ldc_w ''
/*     */     //   45: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   48: ifne -> 494
/*     */     //   51: aload_1
/*     */     //   52: instanceof scala/xml/Atom
/*     */     //   55: ifeq -> 64
/*     */     //   58: iconst_1
/*     */     //   59: istore #5
/*     */     //   61: goto -> 106
/*     */     //   64: aload_1
/*     */     //   65: instanceof scala/xml/Comment
/*     */     //   68: ifeq -> 77
/*     */     //   71: iconst_1
/*     */     //   72: istore #5
/*     */     //   74: goto -> 106
/*     */     //   77: aload_1
/*     */     //   78: instanceof scala/xml/EntityRef
/*     */     //   81: ifeq -> 90
/*     */     //   84: iconst_1
/*     */     //   85: istore #5
/*     */     //   87: goto -> 106
/*     */     //   90: aload_1
/*     */     //   91: instanceof scala/xml/ProcInstr
/*     */     //   94: ifeq -> 103
/*     */     //   97: iconst_1
/*     */     //   98: istore #5
/*     */     //   100: goto -> 106
/*     */     //   103: iconst_0
/*     */     //   104: istore #5
/*     */     //   106: iload #5
/*     */     //   108: ifeq -> 126
/*     */     //   111: aload_0
/*     */     //   112: iload_3
/*     */     //   113: aload_1
/*     */     //   114: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   117: invokevirtual trim : ()Ljava/lang/String;
/*     */     //   120: invokevirtual makeBox : (ILjava/lang/String;)V
/*     */     //   123: goto -> 494
/*     */     //   126: aload_1
/*     */     //   127: instanceof scala/xml/Group
/*     */     //   130: ifeq -> 158
/*     */     //   133: aload_1
/*     */     //   134: checkcast scala/xml/Group
/*     */     //   137: astore #6
/*     */     //   139: aload_0
/*     */     //   140: aload #6
/*     */     //   142: invokevirtual nodes : ()Lscala/collection/Seq;
/*     */     //   145: invokeinterface iterator : ()Lscala/collection/Iterator;
/*     */     //   150: aload_2
/*     */     //   151: iload_3
/*     */     //   152: invokevirtual traverse : (Lscala/collection/Iterator;Lscala/xml/NamespaceBinding;I)V
/*     */     //   155: goto -> 494
/*     */     //   158: new scala/collection/mutable/StringBuilder
/*     */     //   161: dup
/*     */     //   162: invokespecial <init> : ()V
/*     */     //   165: astore #7
/*     */     //   167: getstatic scala/xml/Utility$.MODULE$ : Lscala/xml/Utility$;
/*     */     //   170: aload_1
/*     */     //   171: aload_2
/*     */     //   172: aload #7
/*     */     //   174: iconst_0
/*     */     //   175: getstatic scala/xml/Utility$.MODULE$ : Lscala/xml/Utility$;
/*     */     //   178: invokevirtual serialize$default$5 : ()Z
/*     */     //   181: getstatic scala/xml/Utility$.MODULE$ : Lscala/xml/Utility$;
/*     */     //   184: invokevirtual serialize$default$6 : ()Z
/*     */     //   187: getstatic scala/xml/Utility$.MODULE$ : Lscala/xml/Utility$;
/*     */     //   190: invokevirtual serialize$default$7 : ()Lscala/Enumeration$Value;
/*     */     //   193: invokevirtual serialize : (Lscala/xml/Node;Lscala/xml/NamespaceBinding;Lscala/collection/mutable/StringBuilder;ZZZLscala/Enumeration$Value;)Lscala/collection/mutable/StringBuilder;
/*     */     //   196: pop
/*     */     //   197: aload_0
/*     */     //   198: aload_1
/*     */     //   199: invokespecial doPreserve : (Lscala/xml/Node;)Z
/*     */     //   202: ifeq -> 213
/*     */     //   205: aload #7
/*     */     //   207: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   210: goto -> 242
/*     */     //   213: getstatic scala/xml/TextBuffer$.MODULE$ : Lscala/xml/TextBuffer$;
/*     */     //   216: aload #7
/*     */     //   218: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   221: invokevirtual fromString : (Ljava/lang/String;)Lscala/xml/TextBuffer;
/*     */     //   224: invokevirtual toText : ()Lscala/collection/Seq;
/*     */     //   227: iconst_0
/*     */     //   228: invokeinterface apply : (I)Ljava/lang/Object;
/*     */     //   233: checkcast scala/xml/Atom
/*     */     //   236: invokevirtual data : ()Ljava/lang/Object;
/*     */     //   239: checkcast java/lang/String
/*     */     //   242: astore #12
/*     */     //   244: aload_0
/*     */     //   245: aload_1
/*     */     //   246: invokevirtual childrenAreLeaves : (Lscala/xml/Node;)Z
/*     */     //   249: ifeq -> 271
/*     */     //   252: aload_0
/*     */     //   253: aload #12
/*     */     //   255: invokevirtual fits : (Ljava/lang/String;)Z
/*     */     //   258: ifeq -> 271
/*     */     //   261: aload_0
/*     */     //   262: iload_3
/*     */     //   263: aload #12
/*     */     //   265: invokevirtual makeBox : (ILjava/lang/String;)V
/*     */     //   268: goto -> 494
/*     */     //   271: aload_0
/*     */     //   272: aload_1
/*     */     //   273: aload_2
/*     */     //   274: invokevirtual startTag : (Lscala/xml/Node;Lscala/xml/NamespaceBinding;)Lscala/Tuple2;
/*     */     //   277: astore #13
/*     */     //   279: aload #13
/*     */     //   281: ifnull -> 495
/*     */     //   284: new scala/Tuple2
/*     */     //   287: dup
/*     */     //   288: aload #13
/*     */     //   290: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   293: aload #13
/*     */     //   295: invokevirtual _2$mcI$sp : ()I
/*     */     //   298: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */     //   301: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   304: astore #8
/*     */     //   306: aload #8
/*     */     //   308: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   311: checkcast java/lang/String
/*     */     //   314: astore #10
/*     */     //   316: aload #8
/*     */     //   318: invokevirtual _2$mcI$sp : ()I
/*     */     //   321: istore #9
/*     */     //   323: aload_0
/*     */     //   324: aload_1
/*     */     //   325: invokevirtual endTag : (Lscala/xml/Node;)Ljava/lang/String;
/*     */     //   328: astore #11
/*     */     //   330: aload #10
/*     */     //   332: invokevirtual length : ()I
/*     */     //   335: aload_0
/*     */     //   336: getfield width : I
/*     */     //   339: aload_0
/*     */     //   340: invokevirtual cur : ()I
/*     */     //   343: isub
/*     */     //   344: if_icmpge -> 391
/*     */     //   347: aload_0
/*     */     //   348: iload_3
/*     */     //   349: aload #10
/*     */     //   351: invokevirtual makeBox : (ILjava/lang/String;)V
/*     */     //   354: aload_0
/*     */     //   355: invokevirtual makeBreak : ()V
/*     */     //   358: aload_0
/*     */     //   359: aload_1
/*     */     //   360: invokevirtual child : ()Lscala/collection/Seq;
/*     */     //   363: invokeinterface iterator : ()Lscala/collection/Iterator;
/*     */     //   368: aload_1
/*     */     //   369: invokevirtual scope : ()Lscala/xml/NamespaceBinding;
/*     */     //   372: iload_3
/*     */     //   373: aload_0
/*     */     //   374: getfield step : I
/*     */     //   377: iadd
/*     */     //   378: invokevirtual traverse : (Lscala/collection/Iterator;Lscala/xml/NamespaceBinding;I)V
/*     */     //   381: aload_0
/*     */     //   382: iload_3
/*     */     //   383: aload #11
/*     */     //   385: invokevirtual makeBox : (ILjava/lang/String;)V
/*     */     //   388: goto -> 494
/*     */     //   391: iload #9
/*     */     //   393: aload_0
/*     */     //   394: getfield width : I
/*     */     //   397: aload_0
/*     */     //   398: invokevirtual cur : ()I
/*     */     //   401: isub
/*     */     //   402: if_icmpge -> 483
/*     */     //   405: aload_0
/*     */     //   406: iload_3
/*     */     //   407: aload #10
/*     */     //   409: iconst_0
/*     */     //   410: iload #9
/*     */     //   412: invokevirtual substring : (II)Ljava/lang/String;
/*     */     //   415: invokevirtual makeBox : (ILjava/lang/String;)V
/*     */     //   418: aload_0
/*     */     //   419: invokevirtual makeBreak : ()V
/*     */     //   422: aload_0
/*     */     //   423: iload_3
/*     */     //   424: aload #10
/*     */     //   426: iload #9
/*     */     //   428: aload #10
/*     */     //   430: invokevirtual length : ()I
/*     */     //   433: invokevirtual substring : (II)Ljava/lang/String;
/*     */     //   436: invokevirtual makeBox : (ILjava/lang/String;)V
/*     */     //   439: aload_0
/*     */     //   440: invokevirtual makeBreak : ()V
/*     */     //   443: aload_0
/*     */     //   444: aload_1
/*     */     //   445: invokevirtual child : ()Lscala/collection/Seq;
/*     */     //   448: invokeinterface iterator : ()Lscala/collection/Iterator;
/*     */     //   453: aload_1
/*     */     //   454: invokevirtual scope : ()Lscala/xml/NamespaceBinding;
/*     */     //   457: iload_3
/*     */     //   458: aload_0
/*     */     //   459: getfield step : I
/*     */     //   462: iadd
/*     */     //   463: invokevirtual traverse : (Lscala/collection/Iterator;Lscala/xml/NamespaceBinding;I)V
/*     */     //   466: aload_0
/*     */     //   467: aload_0
/*     */     //   468: invokevirtual cur : ()I
/*     */     //   471: aload #11
/*     */     //   473: invokevirtual makeBox : (ILjava/lang/String;)V
/*     */     //   476: aload_0
/*     */     //   477: invokevirtual makeBreak : ()V
/*     */     //   480: goto -> 494
/*     */     //   483: aload_0
/*     */     //   484: iload_3
/*     */     //   485: aload #12
/*     */     //   487: invokevirtual makeBox : (ILjava/lang/String;)V
/*     */     //   490: aload_0
/*     */     //   491: invokevirtual makeBreak : ()V
/*     */     //   494: return
/*     */     //   495: new scala/MatchError
/*     */     //   498: dup
/*     */     //   499: aload #13
/*     */     //   501: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   504: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #142	-> 0
/*     */     //   #140	-> 0
/*     */     //   #140	-> 17
/*     */     //   #142	-> 19
/*     */     //   #144	-> 51
/*     */     //   #145	-> 111
/*     */     //   #146	-> 126
/*     */     //   #147	-> 139
/*     */     //   #140	-> 140
/*     */     //   #147	-> 142
/*     */     //   #150	-> 158
/*     */     //   #151	-> 167
/*     */     //   #152	-> 197
/*     */     //   #153	-> 213
/*     */     //   #149	-> 242
/*     */     //   #155	-> 244
/*     */     //   #156	-> 261
/*     */     //   #158	-> 271
/*     */     //   #159	-> 323
/*     */     //   #160	-> 330
/*     */     //   #161	-> 347
/*     */     //   #162	-> 354
/*     */     //   #163	-> 358
/*     */     //   #164	-> 381
/*     */     //   #165	-> 391
/*     */     //   #167	-> 405
/*     */     //   #168	-> 418
/*     */     //   #178	-> 422
/*     */     //   #179	-> 439
/*     */     //   #180	-> 443
/*     */     //   #181	-> 466
/*     */     //   #182	-> 476
/*     */     //   #184	-> 483
/*     */     //   #185	-> 490
/*     */     //   #140	-> 494
/*     */     //   #158	-> 495
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	505	0	this	Lscala/xml/PrettyPrinter;
/*     */     //   0	505	1	node	Lscala/xml/Node;
/*     */     //   0	505	2	pscope	Lscala/xml/NamespaceBinding;
/*     */     //   0	505	3	ind	I
/*     */     //   167	338	7	sb	Lscala/collection/mutable/StringBuilder;
/*     */     //   244	261	12	test	Ljava/lang/String;
/*     */     //   316	189	10	stg	Ljava/lang/String;
/*     */     //   323	182	9	len2	I
/*     */     //   330	175	11	etg	Ljava/lang/String;
/*     */   }
/*     */   
/*     */   public void traverse(Iterator it, NamespaceBinding scope, int ind) {
/* 191 */     it.foreach((Function1)new PrettyPrinter$$anonfun$traverse$1(this, scope, ind));
/*     */   }
/*     */   
/*     */   public class PrettyPrinter$$anonfun$traverse$1 extends AbstractFunction1<Node, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final NamespaceBinding scope$1;
/*     */     
/*     */     private final int ind$1;
/*     */     
/*     */     public PrettyPrinter$$anonfun$traverse$1(PrettyPrinter $outer, NamespaceBinding scope$1, int ind$1) {}
/*     */     
/*     */     public final void apply(Node c) {
/* 192 */       this.$outer.traverse(c, this.scope$1, this.ind$1);
/* 193 */       this.$outer.makeBreak();
/*     */     }
/*     */   }
/*     */   
/*     */   public void format(Node n, StringBuilder sb) {
/* 203 */     format(n, null, sb);
/*     */   }
/*     */   
/*     */   public void format(Node n, NamespaceBinding pscope, StringBuilder sb) {
/*     */     // Byte code:
/*     */     //   0: new scala/runtime/BooleanRef
/*     */     //   3: dup
/*     */     //   4: iconst_0
/*     */     //   5: invokespecial <init> : (Z)V
/*     */     //   8: astore #7
/*     */     //   10: aload_0
/*     */     //   11: invokevirtual reset : ()V
/*     */     //   14: aload_0
/*     */     //   15: aload_1
/*     */     //   16: aload_2
/*     */     //   17: iconst_0
/*     */     //   18: invokevirtual traverse : (Lscala/xml/Node;Lscala/xml/NamespaceBinding;I)V
/*     */     //   21: new scala/runtime/IntRef
/*     */     //   24: dup
/*     */     //   25: iconst_0
/*     */     //   26: invokespecial <init> : (I)V
/*     */     //   29: astore #5
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual items : ()Lscala/collection/immutable/List;
/*     */     //   35: invokevirtual reverse : ()Lscala/collection/immutable/List;
/*     */     //   38: astore #9
/*     */     //   40: aload #9
/*     */     //   42: invokevirtual isEmpty : ()Z
/*     */     //   45: ifeq -> 49
/*     */     //   48: return
/*     */     //   49: aload #9
/*     */     //   51: invokevirtual head : ()Ljava/lang/Object;
/*     */     //   54: checkcast scala/xml/PrettyPrinter$Item
/*     */     //   57: astore #10
/*     */     //   59: aload_0
/*     */     //   60: invokevirtual Break : ()Lscala/xml/PrettyPrinter$Break$;
/*     */     //   63: dup
/*     */     //   64: ifnonnull -> 76
/*     */     //   67: pop
/*     */     //   68: aload #10
/*     */     //   70: ifnull -> 84
/*     */     //   73: goto -> 120
/*     */     //   76: aload #10
/*     */     //   78: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   81: ifeq -> 120
/*     */     //   84: aload #7
/*     */     //   86: getfield elem : Z
/*     */     //   89: ifeq -> 98
/*     */     //   92: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   95: goto -> 104
/*     */     //   98: aload_3
/*     */     //   99: bipush #10
/*     */     //   101: invokevirtual append : (C)Lscala/collection/mutable/StringBuilder;
/*     */     //   104: pop
/*     */     //   105: aload #7
/*     */     //   107: iconst_1
/*     */     //   108: putfield elem : Z
/*     */     //   111: aload #5
/*     */     //   113: iconst_0
/*     */     //   114: putfield elem : I
/*     */     //   117: goto -> 220
/*     */     //   120: aload #10
/*     */     //   122: instanceof scala/xml/PrettyPrinter$Box
/*     */     //   125: ifeq -> 189
/*     */     //   128: aload #10
/*     */     //   130: checkcast scala/xml/PrettyPrinter$Box
/*     */     //   133: astore #6
/*     */     //   135: aload #7
/*     */     //   137: iconst_0
/*     */     //   138: putfield elem : Z
/*     */     //   141: aload #5
/*     */     //   143: getfield elem : I
/*     */     //   146: aload #6
/*     */     //   148: invokevirtual col : ()I
/*     */     //   151: if_icmpge -> 176
/*     */     //   154: aload_3
/*     */     //   155: bipush #32
/*     */     //   157: invokevirtual append : (C)Lscala/collection/mutable/StringBuilder;
/*     */     //   160: pop
/*     */     //   161: aload #5
/*     */     //   163: aload #5
/*     */     //   165: getfield elem : I
/*     */     //   168: iconst_1
/*     */     //   169: iadd
/*     */     //   170: putfield elem : I
/*     */     //   173: goto -> 141
/*     */     //   176: aload_3
/*     */     //   177: aload #6
/*     */     //   179: invokevirtual s : ()Ljava/lang/String;
/*     */     //   182: invokevirtual append : (Ljava/lang/String;)Lscala/collection/mutable/StringBuilder;
/*     */     //   185: pop
/*     */     //   186: goto -> 220
/*     */     //   189: aload #10
/*     */     //   191: instanceof scala/xml/PrettyPrinter$Para
/*     */     //   194: ifeq -> 233
/*     */     //   197: aload #10
/*     */     //   199: checkcast scala/xml/PrettyPrinter$Para
/*     */     //   202: astore #8
/*     */     //   204: aload #7
/*     */     //   206: iconst_0
/*     */     //   207: putfield elem : Z
/*     */     //   210: aload_3
/*     */     //   211: aload #8
/*     */     //   213: invokevirtual s : ()Ljava/lang/String;
/*     */     //   216: invokevirtual append : (Ljava/lang/String;)Lscala/collection/mutable/StringBuilder;
/*     */     //   219: pop
/*     */     //   220: aload #9
/*     */     //   222: invokevirtual tail : ()Ljava/lang/Object;
/*     */     //   225: checkcast scala/collection/immutable/List
/*     */     //   228: astore #9
/*     */     //   230: goto -> 40
/*     */     //   233: new scala/MatchError
/*     */     //   236: dup
/*     */     //   237: aload #10
/*     */     //   239: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   242: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #207	-> 0
/*     */     //   #208	-> 10
/*     */     //   #209	-> 14
/*     */     //   #210	-> 21
/*     */     //   #211	-> 31
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	243	0	this	Lscala/xml/PrettyPrinter;
/*     */     //   0	243	1	n	Lscala/xml/Node;
/*     */     //   0	243	2	pscope	Lscala/xml/NamespaceBinding;
/*     */     //   0	243	3	sb	Lscala/collection/mutable/StringBuilder;
/*     */     //   10	38	7	lastwasbreak	Lscala/runtime/BooleanRef;
/*     */     //   31	17	5	cur	Lscala/runtime/IntRef;
/*     */     //   40	203	9	these1	Lscala/collection/immutable/List;
/*     */     //   135	108	6	x21	Lscala/xml/PrettyPrinter$Box;
/*     */     //   204	39	8	x31	Lscala/xml/PrettyPrinter$Para;
/*     */   }
/*     */   
/*     */   public String format(Node n, NamespaceBinding pscope) {
/* 244 */     return Utility$.MODULE$.sbToString((Function1<StringBuilder, BoxedUnit>)new PrettyPrinter$$anonfun$format$2(this, n, pscope));
/*     */   }
/*     */   
/*     */   public class PrettyPrinter$$anonfun$format$2 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Node n$4;
/*     */     
/*     */     private final NamespaceBinding pscope$2;
/*     */     
/*     */     public final void apply(StringBuilder x$11) {
/* 244 */       this.$outer.format(this.n$4, this.pscope$2, x$11);
/*     */     }
/*     */     
/*     */     public PrettyPrinter$$anonfun$format$2(PrettyPrinter $outer, Node n$4, NamespaceBinding pscope$2) {}
/*     */   }
/*     */   
/*     */   public String formatNodes(Seq nodes, NamespaceBinding pscope) {
/* 252 */     return Utility$.MODULE$.sbToString((Function1<StringBuilder, BoxedUnit>)new PrettyPrinter$$anonfun$formatNodes$1(this, nodes, pscope));
/*     */   }
/*     */   
/*     */   public class PrettyPrinter$$anonfun$formatNodes$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq nodes$1;
/*     */     
/*     */     private final NamespaceBinding pscope$3;
/*     */     
/*     */     public final void apply(StringBuilder x$12) {
/* 252 */       this.$outer.formatNodes(this.nodes$1, this.pscope$3, x$12);
/*     */     }
/*     */     
/*     */     public PrettyPrinter$$anonfun$formatNodes$1(PrettyPrinter $outer, Seq nodes$1, NamespaceBinding pscope$3) {}
/*     */   }
/*     */   
/*     */   public void formatNodes(Seq nodes, NamespaceBinding pscope, StringBuilder sb) {
/* 262 */     nodes.foreach((Function1)new PrettyPrinter$$anonfun$formatNodes$2(this, pscope, sb));
/*     */   }
/*     */   
/*     */   public NamespaceBinding format$default$2() {
/*     */     return null;
/*     */   }
/*     */   
/*     */   public NamespaceBinding formatNodes$default$2() {
/*     */     return null;
/*     */   }
/*     */   
/*     */   public PrettyPrinter(int width, int step) {}
/*     */   
/*     */   public class PrettyPrinter$$anonfun$formatNodes$2 extends AbstractFunction1<Node, StringBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final NamespaceBinding pscope$4;
/*     */     
/*     */     private final StringBuilder sb$2;
/*     */     
/*     */     public final StringBuilder apply(Node n) {
/* 262 */       return this.sb$2.append(this.$outer.format(n, this.pscope$4));
/*     */     }
/*     */     
/*     */     public PrettyPrinter$$anonfun$formatNodes$2(PrettyPrinter $outer, NamespaceBinding pscope$4, StringBuilder sb$2) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\PrettyPrinter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */