/*    */ package scala.util.regexp;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t5a!B\001\003\003\003I!a\004)pS:$X\r\032%fI\036,W\t\0379\013\005\r!\021A\002:fO\026D\bO\003\002\006\r\005!Q\017^5m\025\0059\021!B:dC2\f7\001A\n\003\001)\001\"a\003\007\016\003\tI!!\004\002\003\t\t\0137/\032\005\006\037\001!\t\001E\001\007y%t\027\016\036 \025\003E\001\"a\003\001\005\013M\001!\021\001\013\003\021}\023XmZ3yaR\013\"!F\r\021\005Y9R\"\001\004\n\005a1!a\002(pi\"Lgn\032\t\0035mi\021\001A\005\00391\021aAU3h\013b\004H!\002\020\001\005\003y\"aB0mC\n,G\016V\t\003+\001\002\"AF\021\n\005\t2!aA!os\032!A\005\001!&\005\021qu\016Z3\024\t\rJb%\013\t\003-\035J!\001\013\004\003\017A\023x\016Z;diB\021aCK\005\003W\031\021AbU3sS\006d\027N_1cY\026D\001\"L\022\003\026\004%\tAL\001\006Y\006\024W\r\\\013\002_A\021!$\b\005\tc\r\022\t\022)A\005_\0051A.\0312fY\002B\001bM\022\003\026\004%\t\001N\001\002eV\tQ\007\005\002\033%!Aqg\tB\tB\003%Q'\001\002sA!)qb\tC\001sQ\031!h\017\037\021\005i\031\003\"B\0279\001\004y\003\"B\0329\001\004)\004b\002 $\005\004%)aP\001\013SNtU\017\0347bE2,W#\001!\020\003\005K\022\001\001\005\007\007\016\002\013Q\002!\002\027%\034h*\0367mC\ndW\r\t\005\b\013\016\n\t\021\"\001G\003\021\031w\016]=\025\007i:\005\nC\004.\tB\005\t\031A\030\t\017M\"\005\023!a\001k!9!jII\001\n\003Y\025AD2paf$C-\0324bk2$H%M\013\002\031*\022q&T\026\002\035B\021q\nV\007\002!*\021\021KU\001\nk:\034\007.Z2lK\022T!a\025\004\002\025\005tgn\034;bi&|g.\003\002V!\n\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\017]\033\023\023!C\0011\006q1m\0349zI\021,g-Y;mi\022\022T#A-+\005Uj\005bB.$\003\003%\t\005X\001\016aJ|G-^2u!J,g-\033=\026\003u\003\"AX2\016\003}S!\001Y1\002\t1\fgn\032\006\002E\006!!.\031<b\023\t!wL\001\004TiJLgn\032\005\bM\016\n\t\021\"\001h\0031\001(o\0343vGR\f%/\033;z+\005A\007C\001\fj\023\tQgAA\002J]RDq\001\\\022\002\002\023\005Q.\001\bqe>$Wo\031;FY\026lWM\034;\025\005\001r\007bB8l\003\003\005\r\001[\001\004q\022\n\004bB9$\003\003%\tE]\001\020aJ|G-^2u\023R,'/\031;peV\t1\017E\002uo\002j\021!\036\006\003m\032\t!bY8mY\026\034G/[8o\023\tAXO\001\005Ji\026\024\030\r^8s\021\035Q8%!A\005\002m\f\001bY1o\013F,\030\r\034\013\003y~\004\"AF?\n\005y4!a\002\"p_2,\027M\034\005\b_f\f\t\0211\001!\021%\t\031aIA\001\n\003\n)!\001\005iCND7i\0343f)\005A\007\"CA\005G\005\005I\021IA\006\003!!xn\025;sS:<G#A/\t\023\005=1%!A\005B\005E\021AB3rk\006d7\017F\002}\003'A\001b\\A\007\003\003\005\r\001I\004\n\003/\001\021\021!E\001\0033\tAAT8eKB\031!$a\007\007\021\021\002\021\021!E\001\003;\031R!a\007\002 %\002r!!\t\002(=*$(\004\002\002$)\031\021Q\005\004\002\017I,h\016^5nK&!\021\021FA\022\005E\t%m\035;sC\016$h)\0368di&|gN\r\005\b\037\005mA\021AA\027)\t\tI\002\003\006\002\n\005m\021\021!C#\003\027A!\"a\r\002\034\005\005I\021QA\033\003\025\t\007\017\0357z)\025Q\024qGA\035\021\031i\023\021\007a\001_!11'!\rA\002UB!\"!\020\002\034\005\005I\021QA \003\035)h.\0319qYf$B!!\021\002NA)a#a\021\002H%\031\021Q\t\004\003\r=\003H/[8o!\0251\022\021J\0306\023\r\tYE\002\002\007)V\004H.\032\032\t\023\005=\0231HA\001\002\004Q\024a\001=%a!Q\0211KA\016\003\003%I!!\026\002\027I,\027\r\032*fg>dg/\032\013\003\003/\0022AXA-\023\r\tYf\030\002\007\037\nTWm\031;\007\r\005}\003\001QA1\005\035!v\016]%uKJ\034R!!\030\032M%B!\"!\032\002^\tU\r\021\"\0015\003\t\021\030\007\003\006\002j\005u#\021#Q\001\nU\n1A]\031!\021)\ti'!\030\003\026\004%\t\001N\001\003eJB!\"!\035\002^\tE\t\025!\0036\003\r\021(\007\t\005\b\037\005uC\021AA;)\031\t9(!\037\002|A\031!$!\030\t\017\005\025\0241\017a\001k!9\021QNA:\001\004)\004\"\003 \002^\t\007IQAA@+\005a\bbB\"\002^\001\006i\001 \005\n\013\006u\023\021!C\001\003\013#b!a\036\002\b\006%\005\"CA3\003\007\003\n\0211\0016\021%\ti'a!\021\002\003\007Q\007\003\005K\003;\n\n\021\"\001Y\021!9\026QLI\001\n\003A\006\002C.\002^\005\005I\021\t/\t\021\031\fi&!A\005\002\035D\021\002\\A/\003\003%\t!!&\025\007\001\n9\n\003\005p\003'\013\t\0211\001i\021!\t\030QLA\001\n\003\022\b\"\003>\002^\005\005I\021AAO)\ra\030q\024\005\t_\006m\025\021!a\001A!Q\0211AA/\003\003%\t%!\002\t\025\005%\021QLA\001\n\003\nY\001\003\006\002\020\005u\023\021!C!\003O#2\001`AU\021!y\027QUA\001\002\004\001s!CAW\001\005\005\t\022AAX\003\035!v\016]%uKJ\0042AGAY\r%\ty\006AA\001\022\003\t\031lE\003\0022\006U\026\006\005\005\002\"\005\035R'NA<\021\035y\021\021\027C\001\003s#\"!a,\t\025\005%\021\021WA\001\n\013\nY\001\003\006\0024\005E\026\021!CA\003#b!a\036\002B\006\r\007bBA3\003{\003\r!\016\005\b\003[\ni\f1\0016\021)\ti$!-\002\002\023\005\025q\031\013\005\003\023\fi\rE\003\027\003\007\nY\rE\003\027\003\023*T\007\003\006\002P\005\025\027\021!a\001\003oB!\"a\025\0022\006\005I\021BA+\017\035\t\031\016\001EA\003+\fQ\001U8j]R\0042AGAl\r\035\tI\016\001EA\0037\024Q\001U8j]R\034R!a6\032M%BqaDAl\t\003\ty\016\006\002\002V\"Aa(a6C\002\023\025q\bC\004D\003/\004\013Q\002!\t\021m\0139.!A\005BqC\001BZAl\003\003%\ta\032\005\nY\006]\027\021!C\001\003W$2\001IAw\021!y\027\021^A\001\002\004A\007\002C9\002X\006\005I\021\t:\t\023i\f9.!A\005\002\005MHc\001?\002v\"Aq.!=\002\002\003\007\001\005\003\006\002\004\005]\027\021!C!\003\013A!\"!\003\002X\006\005I\021IA\006\021)\t\031&a6\002\002\023%\021Q\013\025\b\001\005}(Q\001B\005!\r1\"\021A\005\004\005\0071!A\0033faJ,7-\031;fI\006\022!qA\001\033)\"L7\017I2mCN\034\be^5mY\002\022W\r\t:f[>4X\rZ\021\003\005\027\taA\r\0302a9\002\004")
/*    */ public abstract class PointedHedgeExp extends Base {
/*    */   private volatile Node$ Node$module;
/*    */   
/*    */   private volatile TopIter$ TopIter$module;
/*    */   
/*    */   private volatile Point$ Point$module;
/*    */   
/*    */   private Node$ Node$lzycompute() {
/* 24 */     synchronized (this) {
/* 24 */       if (this.Node$module == null)
/* 24 */         this.Node$module = new Node$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/regexp/PointedHedgeExp}} */
/* 24 */       return this.Node$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Node$ Node() {
/* 24 */     return (this.Node$module == null) ? Node$lzycompute() : this.Node$module;
/*    */   }
/*    */   
/*    */   public class Node$ extends AbstractFunction2<Object, Base.RegExp, Node> implements Serializable {
/*    */     public final String toString() {
/* 24 */       return "Node";
/*    */     }
/*    */     
/*    */     public PointedHedgeExp.Node apply(Object label, Base.RegExp r) {
/* 24 */       return new PointedHedgeExp.Node(this.$outer, label, r);
/*    */     }
/*    */     
/*    */     public Option<Tuple2<Object, Base.RegExp>> unapply(PointedHedgeExp.Node x$0) {
/* 24 */       return (x$0 == null) ? (Option<Tuple2<Object, Base.RegExp>>)scala.None$.MODULE$ : (Option<Tuple2<Object, Base.RegExp>>)new Some(new Tuple2(x$0.label(), x$0.r()));
/*    */     }
/*    */     
/*    */     private Object readResolve() {
/* 24 */       return this.$outer.Node();
/*    */     }
/*    */     
/*    */     public Node$(PointedHedgeExp $outer) {}
/*    */   }
/*    */   
/*    */   public class Node extends Base.RegExp implements Product, Serializable {
/*    */     private final Object label;
/*    */     
/*    */     private final Base.RegExp r;
/*    */     
/*    */     private final boolean isNullable;
/*    */     
/*    */     public Object label() {
/* 24 */       return this.label;
/*    */     }
/*    */     
/*    */     public Base.RegExp r() {
/* 24 */       return this.r;
/*    */     }
/*    */     
/*    */     public Node copy(Object label, Base.RegExp r) {
/* 24 */       return new Node(scala$util$regexp$PointedHedgeExp$Node$$$outer(), label, r);
/*    */     }
/*    */     
/*    */     public Object copy$default$1() {
/* 24 */       return label();
/*    */     }
/*    */     
/*    */     public Base.RegExp copy$default$2() {
/* 24 */       return r();
/*    */     }
/*    */     
/*    */     public String productPrefix() {
/* 24 */       return "Node";
/*    */     }
/*    */     
/*    */     public int productArity() {
/* 24 */       return 2;
/*    */     }
/*    */     
/*    */     public Object productElement(int x$1) {
/* 24 */       switch (x$1) {
/*    */         default:
/* 24 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */         case 1:
/*    */         
/*    */         case 0:
/*    */           break;
/*    */       } 
/* 24 */       return label();
/*    */     }
/*    */     
/*    */     public Iterator<Object> productIterator() {
/* 24 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object x$1) {
/* 24 */       return x$1 instanceof Node;
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 24 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 24 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object x$1) {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: if_acmpeq -> 169
/*    */       //   5: aload_1
/*    */       //   6: instanceof scala/util/regexp/PointedHedgeExp$Node
/*    */       //   9: ifeq -> 31
/*    */       //   12: aload_1
/*    */       //   13: checkcast scala/util/regexp/PointedHedgeExp$Node
/*    */       //   16: invokevirtual scala$util$regexp$PointedHedgeExp$Node$$$outer : ()Lscala/util/regexp/PointedHedgeExp;
/*    */       //   19: aload_0
/*    */       //   20: invokevirtual scala$util$regexp$PointedHedgeExp$Node$$$outer : ()Lscala/util/regexp/PointedHedgeExp;
/*    */       //   23: if_acmpne -> 31
/*    */       //   26: iconst_1
/*    */       //   27: istore_2
/*    */       //   28: goto -> 33
/*    */       //   31: iconst_0
/*    */       //   32: istore_2
/*    */       //   33: iload_2
/*    */       //   34: ifeq -> 173
/*    */       //   37: aload_1
/*    */       //   38: checkcast scala/util/regexp/PointedHedgeExp$Node
/*    */       //   41: astore #6
/*    */       //   43: aload_0
/*    */       //   44: invokevirtual label : ()Ljava/lang/Object;
/*    */       //   47: aload #6
/*    */       //   49: invokevirtual label : ()Ljava/lang/Object;
/*    */       //   52: astore #4
/*    */       //   54: dup
/*    */       //   55: astore_3
/*    */       //   56: aload #4
/*    */       //   58: if_acmpne -> 65
/*    */       //   61: iconst_1
/*    */       //   62: goto -> 117
/*    */       //   65: aload_3
/*    */       //   66: ifnonnull -> 73
/*    */       //   69: iconst_0
/*    */       //   70: goto -> 117
/*    */       //   73: aload_3
/*    */       //   74: instanceof java/lang/Number
/*    */       //   77: ifeq -> 92
/*    */       //   80: aload_3
/*    */       //   81: checkcast java/lang/Number
/*    */       //   84: aload #4
/*    */       //   86: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*    */       //   89: goto -> 117
/*    */       //   92: aload_3
/*    */       //   93: instanceof java/lang/Character
/*    */       //   96: ifeq -> 111
/*    */       //   99: aload_3
/*    */       //   100: checkcast java/lang/Character
/*    */       //   103: aload #4
/*    */       //   105: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*    */       //   108: goto -> 117
/*    */       //   111: aload_3
/*    */       //   112: aload #4
/*    */       //   114: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   117: ifeq -> 165
/*    */       //   120: aload_0
/*    */       //   121: invokevirtual r : ()Lscala/util/regexp/Base$RegExp;
/*    */       //   124: aload #6
/*    */       //   126: invokevirtual r : ()Lscala/util/regexp/Base$RegExp;
/*    */       //   129: astore #5
/*    */       //   131: dup
/*    */       //   132: ifnonnull -> 144
/*    */       //   135: pop
/*    */       //   136: aload #5
/*    */       //   138: ifnull -> 152
/*    */       //   141: goto -> 165
/*    */       //   144: aload #5
/*    */       //   146: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   149: ifeq -> 165
/*    */       //   152: aload #6
/*    */       //   154: aload_0
/*    */       //   155: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */       //   158: ifeq -> 165
/*    */       //   161: iconst_1
/*    */       //   162: goto -> 166
/*    */       //   165: iconst_0
/*    */       //   166: ifeq -> 173
/*    */       //   169: iconst_1
/*    */       //   170: goto -> 174
/*    */       //   173: iconst_0
/*    */       //   174: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #24	-> 0
/*    */       //   #236	-> 26
/*    */       //   #24	-> 33
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	175	0	this	Lscala/util/regexp/PointedHedgeExp$Node;
/*    */       //   0	175	1	x$1	Ljava/lang/Object;
/*    */     }
/*    */     
/*    */     public Node(PointedHedgeExp $outer, Object label, Base.RegExp r) {
/* 24 */       super($outer);
/* 24 */       Product.class.$init$(this);
/*    */     }
/*    */     
/*    */     public final boolean isNullable() {
/* 25 */       return false;
/*    */     }
/*    */   }
/*    */   
/*    */   private TopIter$ TopIter$lzycompute() {
/* 28 */     synchronized (this) {
/* 28 */       if (this.TopIter$module == null)
/* 28 */         this.TopIter$module = new TopIter$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/regexp/PointedHedgeExp}} */
/* 28 */       return this.TopIter$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public TopIter$ TopIter() {
/* 28 */     return (this.TopIter$module == null) ? TopIter$lzycompute() : this.TopIter$module;
/*    */   }
/*    */   
/*    */   public class TopIter$ extends AbstractFunction2<Base.RegExp, Base.RegExp, TopIter> implements Serializable {
/*    */     public final String toString() {
/* 28 */       return "TopIter";
/*    */     }
/*    */     
/*    */     public PointedHedgeExp.TopIter apply(Base.RegExp r1, Base.RegExp r2) {
/* 28 */       return new PointedHedgeExp.TopIter(this.$outer, r1, r2);
/*    */     }
/*    */     
/*    */     public Option<Tuple2<Base.RegExp, Base.RegExp>> unapply(PointedHedgeExp.TopIter x$0) {
/* 28 */       return (x$0 == null) ? (Option<Tuple2<Base.RegExp, Base.RegExp>>)scala.None$.MODULE$ : (Option<Tuple2<Base.RegExp, Base.RegExp>>)new Some(new Tuple2(x$0.r1(), x$0.r2()));
/*    */     }
/*    */     
/*    */     private Object readResolve() {
/* 28 */       return this.$outer.TopIter();
/*    */     }
/*    */     
/*    */     public TopIter$(PointedHedgeExp $outer) {}
/*    */   }
/*    */   
/*    */   public class TopIter extends Base.RegExp implements Product, Serializable {
/*    */     private final Base.RegExp r1;
/*    */     
/*    */     private final Base.RegExp r2;
/*    */     
/*    */     private final boolean isNullable;
/*    */     
/*    */     public Base.RegExp r1() {
/* 28 */       return this.r1;
/*    */     }
/*    */     
/*    */     public Base.RegExp r2() {
/* 28 */       return this.r2;
/*    */     }
/*    */     
/*    */     public TopIter copy(Base.RegExp r1, Base.RegExp r2) {
/* 28 */       return new TopIter(scala$util$regexp$PointedHedgeExp$TopIter$$$outer(), r1, r2);
/*    */     }
/*    */     
/*    */     public Base.RegExp copy$default$1() {
/* 28 */       return r1();
/*    */     }
/*    */     
/*    */     public Base.RegExp copy$default$2() {
/* 28 */       return r2();
/*    */     }
/*    */     
/*    */     public String productPrefix() {
/* 28 */       return "TopIter";
/*    */     }
/*    */     
/*    */     public int productArity() {
/* 28 */       return 2;
/*    */     }
/*    */     
/*    */     public Object productElement(int x$1) {
/* 28 */       switch (x$1) {
/*    */         default:
/* 28 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */         case 1:
/*    */         
/*    */         case 0:
/*    */           break;
/*    */       } 
/* 28 */       return r1();
/*    */     }
/*    */     
/*    */     public Iterator<Object> productIterator() {
/* 28 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object x$1) {
/* 28 */       return x$1 instanceof TopIter;
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 28 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 28 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object x$1) {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: if_acmpeq -> 121
/*    */       //   5: aload_1
/*    */       //   6: instanceof scala/util/regexp/PointedHedgeExp$TopIter
/*    */       //   9: ifeq -> 31
/*    */       //   12: aload_1
/*    */       //   13: checkcast scala/util/regexp/PointedHedgeExp$TopIter
/*    */       //   16: invokevirtual scala$util$regexp$PointedHedgeExp$TopIter$$$outer : ()Lscala/util/regexp/PointedHedgeExp;
/*    */       //   19: aload_0
/*    */       //   20: invokevirtual scala$util$regexp$PointedHedgeExp$TopIter$$$outer : ()Lscala/util/regexp/PointedHedgeExp;
/*    */       //   23: if_acmpne -> 31
/*    */       //   26: iconst_1
/*    */       //   27: istore_2
/*    */       //   28: goto -> 33
/*    */       //   31: iconst_0
/*    */       //   32: istore_2
/*    */       //   33: iload_2
/*    */       //   34: ifeq -> 125
/*    */       //   37: aload_1
/*    */       //   38: checkcast scala/util/regexp/PointedHedgeExp$TopIter
/*    */       //   41: astore #5
/*    */       //   43: aload_0
/*    */       //   44: invokevirtual r1 : ()Lscala/util/regexp/Base$RegExp;
/*    */       //   47: aload #5
/*    */       //   49: invokevirtual r1 : ()Lscala/util/regexp/Base$RegExp;
/*    */       //   52: astore_3
/*    */       //   53: dup
/*    */       //   54: ifnonnull -> 65
/*    */       //   57: pop
/*    */       //   58: aload_3
/*    */       //   59: ifnull -> 72
/*    */       //   62: goto -> 117
/*    */       //   65: aload_3
/*    */       //   66: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   69: ifeq -> 117
/*    */       //   72: aload_0
/*    */       //   73: invokevirtual r2 : ()Lscala/util/regexp/Base$RegExp;
/*    */       //   76: aload #5
/*    */       //   78: invokevirtual r2 : ()Lscala/util/regexp/Base$RegExp;
/*    */       //   81: astore #4
/*    */       //   83: dup
/*    */       //   84: ifnonnull -> 96
/*    */       //   87: pop
/*    */       //   88: aload #4
/*    */       //   90: ifnull -> 104
/*    */       //   93: goto -> 117
/*    */       //   96: aload #4
/*    */       //   98: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   101: ifeq -> 117
/*    */       //   104: aload #5
/*    */       //   106: aload_0
/*    */       //   107: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */       //   110: ifeq -> 117
/*    */       //   113: iconst_1
/*    */       //   114: goto -> 118
/*    */       //   117: iconst_0
/*    */       //   118: ifeq -> 125
/*    */       //   121: iconst_1
/*    */       //   122: goto -> 126
/*    */       //   125: iconst_0
/*    */       //   126: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #28	-> 0
/*    */       //   #236	-> 26
/*    */       //   #28	-> 33
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	127	0	this	Lscala/util/regexp/PointedHedgeExp$TopIter;
/*    */       //   0	127	1	x$1	Ljava/lang/Object;
/*    */     }
/*    */     
/*    */     public TopIter(PointedHedgeExp $outer, Base.RegExp r1, Base.RegExp r2) {
/* 28 */       super($outer);
/* 28 */       Product.class.$init$(this);
/* 29 */       this.isNullable = (r1.isNullable() && r2.isNullable());
/*    */     }
/*    */     
/*    */     public final boolean isNullable() {
/* 29 */       return this.isNullable;
/*    */     }
/*    */   }
/*    */   
/*    */   private Point$ Point$lzycompute() {
/* 32 */     synchronized (this) {
/* 32 */       if (this.Point$module == null)
/* 32 */         this.Point$module = new Point$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/regexp/PointedHedgeExp}} */
/* 32 */       return this.Point$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Point$ Point() {
/* 32 */     return (this.Point$module == null) ? Point$lzycompute() : this.Point$module;
/*    */   }
/*    */   
/*    */   public class Point$ extends Base.RegExp implements Product, Serializable {
/*    */     private final boolean isNullable;
/*    */     
/*    */     public Point$(PointedHedgeExp $outer) {
/* 32 */       super($outer);
/* 32 */       Product.class.$init$(this);
/*    */     }
/*    */     
/*    */     private Object readResolve() {
/* 32 */       return scala$util$regexp$PointedHedgeExp$Point$$$outer().Point();
/*    */     }
/*    */     
/*    */     public String toString() {
/* 32 */       return "Point";
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 32 */       return 77292912;
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object x$1) {
/* 32 */       return x$1 instanceof Point$;
/*    */     }
/*    */     
/*    */     public Iterator<Object> productIterator() {
/* 32 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */     }
/*    */     
/*    */     public Object productElement(int x$1) {
/* 32 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */     }
/*    */     
/*    */     public int productArity() {
/* 32 */       return 0;
/*    */     }
/*    */     
/*    */     public String productPrefix() {
/* 32 */       return "Point";
/*    */     }
/*    */     
/*    */     public final boolean isNullable() {
/* 33 */       return false;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\regexp\PointedHedgeExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */