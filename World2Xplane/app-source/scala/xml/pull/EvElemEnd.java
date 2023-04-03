/*    */ package scala.xml.pull;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005Eb\001B\001\003\001&\021\021\"\022<FY\026lWI\0343\013\005\r!\021\001\0029vY2T!!\002\004\002\007alGNC\001\b\003\025\0318-\0317b\007\001\031R\001\001\006\017%U\001\"a\003\007\016\003\031I!!\004\004\003\r\005s\027PU3g!\ty\001#D\001\003\023\t\t\"A\001\005Y\0332+e/\0328u!\tY1#\003\002\025\r\t9\001K]8ek\016$\bCA\006\027\023\t9bA\001\007TKJL\027\r\\5{C\ndW\r\003\005\032\001\tU\r\021\"\001\033\003\r\001(/Z\013\0027A\021Ad\b\b\003\027uI!A\b\004\002\rA\023X\rZ3g\023\t\001\023E\001\004TiJLgn\032\006\003=\031A\001b\t\001\003\022\003\006IaG\001\005aJ,\007\005\003\005&\001\tU\r\021\"\001\033\003\025a\027MY3m\021!9\003A!E!\002\023Y\022A\0027bE\026d\007\005C\003*\001\021\005!&\001\004=S:LGO\020\013\004W1j\003CA\b\001\021\025I\002\0061\001\034\021\025)\003\0061\001\034\021\035y\003!!A\005\002A\nAaY8qsR\0311&\r\032\t\017eq\003\023!a\0017!9QE\fI\001\002\004Y\002b\002\033\001#\003%\t!N\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\0051$FA\0168W\005A\004CA\035?\033\005Q$BA\036=\003%)hn\0315fG.,GM\003\002>\r\005Q\021M\0348pi\006$\030n\0348\n\005}R$!E;oG\",7m[3e-\006\024\030.\0318dK\"9\021\tAI\001\n\003)\024AD2paf$C-\0324bk2$HE\r\005\b\007\002\t\t\021\"\021E\0035\001(o\0343vGR\004&/\0324jqV\tQ\t\005\002G\0276\tqI\003\002I\023\006!A.\0318h\025\005Q\025\001\0026bm\006L!\001I$\t\0175\003\021\021!C\001\035\006a\001O]8ek\016$\030I]5usV\tq\n\005\002\f!&\021\021K\002\002\004\023:$\bbB*\001\003\003%\t\001V\001\017aJ|G-^2u\0132,W.\0328u)\t)\006\f\005\002\f-&\021qK\002\002\004\003:L\bbB-S\003\003\005\raT\001\004q\022\n\004bB.\001\003\003%\t\005X\001\020aJ|G-^2u\023R,'/\031;peV\tQ\fE\002_CVk\021a\030\006\003A\032\t!bY8mY\026\034G/[8o\023\t\021wL\001\005Ji\026\024\030\r^8s\021\035!\007!!A\005\002\025\f\001bY1o\013F,\030\r\034\013\003M&\004\"aC4\n\005!4!a\002\"p_2,\027M\034\005\b3\016\f\t\0211\001V\021\035Y\007!!A\005B1\f\001\002[1tQ\016{G-\032\013\002\037\"9a\016AA\001\n\003z\027\001\003;p'R\024\030N\\4\025\003\025Cq!\035\001\002\002\023\005#/\001\004fcV\fGn\035\013\003MNDq!\0279\002\002\003\007QkB\004v\005\005\005\t\022\001<\002\023\0253X\t\\3n\013:$\007CA\bx\r\035\t!!!A\t\002a\0342a^=\026!\025QXpG\016,\033\005Y(B\001?\007\003\035\021XO\034;j[\026L!A`>\003#\005\0237\017\036:bGR4UO\\2uS>t'\007\003\004*o\022\005\021\021\001\013\002m\"9an^A\001\n\013z\007\"CA\004o\006\005I\021QA\005\003\025\t\007\017\0357z)\025Y\0231BA\007\021\031I\022Q\001a\0017!1Q%!\002A\002mA\021\"!\005x\003\003%\t)a\005\002\017Ut\027\r\0359msR!\021QCA\021!\025Y\021qCA\016\023\r\tIB\002\002\007\037B$\030n\0348\021\013-\tibG\016\n\007\005}aA\001\004UkBdWM\r\005\n\003G\ty!!AA\002-\n1\001\037\0231\021%\t9c^A\001\n\023\tI#A\006sK\006$'+Z:pYZ,GCAA\026!\r1\025QF\005\004\003_9%AB(cU\026\034G\017")
/*    */ public class EvElemEnd implements XMLEvent, Product, Serializable {
/*    */   private final String pre;
/*    */   
/*    */   private final String label;
/*    */   
/*    */   public static Function1<Tuple2<String, String>, EvElemEnd> tupled() {
/*    */     return EvElemEnd$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<String, Function1<String, EvElemEnd>> curried() {
/*    */     return EvElemEnd$.MODULE$.curried();
/*    */   }
/*    */   
/*    */   public String pre() {
/* 32 */     return this.pre;
/*    */   }
/*    */   
/*    */   public String label() {
/* 32 */     return this.label;
/*    */   }
/*    */   
/*    */   public EvElemEnd copy(String pre, String label) {
/* 32 */     return new EvElemEnd(pre, label);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 32 */     return pre();
/*    */   }
/*    */   
/*    */   public String copy$default$2() {
/* 32 */     return label();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 32 */     return "EvElemEnd";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 32 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 32 */     switch (x$1) {
/*    */       default:
/* 32 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 32 */     return pre();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 32 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 32 */     return x$1 instanceof EvElemEnd;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 32 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 32 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 107
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/pull/EvElemEnd
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 111
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/pull/EvElemEnd
/*    */     //   27: astore #5
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual pre : ()Ljava/lang/String;
/*    */     //   33: aload #5
/*    */     //   35: invokevirtual pre : ()Ljava/lang/String;
/*    */     //   38: astore_3
/*    */     //   39: dup
/*    */     //   40: ifnonnull -> 51
/*    */     //   43: pop
/*    */     //   44: aload_3
/*    */     //   45: ifnull -> 58
/*    */     //   48: goto -> 103
/*    */     //   51: aload_3
/*    */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   55: ifeq -> 103
/*    */     //   58: aload_0
/*    */     //   59: invokevirtual label : ()Ljava/lang/String;
/*    */     //   62: aload #5
/*    */     //   64: invokevirtual label : ()Ljava/lang/String;
/*    */     //   67: astore #4
/*    */     //   69: dup
/*    */     //   70: ifnonnull -> 82
/*    */     //   73: pop
/*    */     //   74: aload #4
/*    */     //   76: ifnull -> 90
/*    */     //   79: goto -> 103
/*    */     //   82: aload #4
/*    */     //   84: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   87: ifeq -> 103
/*    */     //   90: aload #5
/*    */     //   92: aload_0
/*    */     //   93: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   96: ifeq -> 103
/*    */     //   99: iconst_1
/*    */     //   100: goto -> 104
/*    */     //   103: iconst_0
/*    */     //   104: ifeq -> 111
/*    */     //   107: iconst_1
/*    */     //   108: goto -> 112
/*    */     //   111: iconst_0
/*    */     //   112: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #32	-> 0
/*    */     //   #236	-> 12
/*    */     //   #32	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	113	0	this	Lscala/xml/pull/EvElemEnd;
/*    */     //   0	113	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public EvElemEnd(String pre, String label) {
/* 32 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\pull\EvElemEnd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */