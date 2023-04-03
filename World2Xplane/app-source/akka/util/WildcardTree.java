/*    */ package akka.util;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.immutable.HashMap;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005}xAB\001\003\021\003!a!\001\007XS2$7-\031:e)J,WM\003\002\004\t\005!Q\017^5m\025\005)\021\001B1lW\006\004\"a\002\005\016\003\t1a!\003\002\t\002\021Q!\001D,jY\022\034\027M\0353Ue\026,7c\001\005\f#A\021AbD\007\002\033)\ta\"A\003tG\006d\027-\003\002\021\033\t1\021I\\=SK\032\004\"\001\004\n\n\005Mi!\001D*fe&\fG.\033>bE2,\007\"B\013\t\t\0039\022A\002\037j]&$hh\001\001\025\003\031Aq!\007\005C\002\023%!$A\003f[B$\0300F\001\034!\r9A$\f\004\006\023\t\001E!H\013\003=)\032B\001H\006 #A\021A\002I\005\003C5\021q\001\025:pIV\034G\017\003\005$9\tU\r\021\"\001%\003\021!\027\r^1\026\003\025\0022\001\004\024)\023\t9SB\001\004PaRLwN\034\t\003S)b\001\001B\003,9\t\007AFA\001U#\ti\003\007\005\002\r]%\021q&\004\002\b\035>$\b.\0338h!\ta\021'\003\0023\033\t\031\021I\\=\t\021Qb\"\021#Q\001\n\025\nQ\001Z1uC\002B\001B\016\017\003\026\004%\taN\001\tG\"LG\016\032:f]V\t\001\b\005\003:y}\022eB\001\007;\023\tYT\"\001\004Qe\026$WMZ\005\003{y\0221!T1q\025\tYT\002\005\002:\001&\021\021I\020\002\007'R\024\030N\\4\021\007\035a\002\006\003\005E9\tE\t\025!\0039\003%\031\007.\0337ee\026t\007\005C\003\0269\021\005a\tF\002C\017\"CqaI#\021\002\003\007Q\005C\0047\013B\005\t\031\001\035\t\013)cB\021A&\002\r%t7/\032:u)\r\021EJ\027\005\006\033&\003\rAT\001\006K2,Wn\035\t\004\037^{dB\001)V\035\t\tF+D\001S\025\t\031f#\001\004=e>|GOP\005\002\035%\021a+D\001\ba\006\0347.Y4f\023\tA\026L\001\005Ji\026\024\030\r^8s\025\t1V\002C\003\\\023\002\007\001&A\001e\021\025iF\004\"\002_\003\0211\027N\0343\025\005\t{\006\"B']\001\004q\005F\001/b!\t\021W-D\001d\025\t!W\"\001\006b]:|G/\031;j_:L!AZ2\003\017Q\f\027\016\034:fG\"9\001\016HA\001\n\003I\027\001B2paf,\"A[7\025\007-t\007\017E\002\b91\004\"!K7\005\013-:'\031\001\027\t\017\r:\007\023!a\001_B\031AB\n7\t\017Y:\007\023!a\001cB!\021\bP l\021\035\031H$%A\005\002Q\fabY8qs\022\"WMZ1vYR$\023'\006\002v}V\taO\013\002&o.\n\001\020\005\002zy6\t!P\003\002|G\006IQO\\2iK\016\\W\rZ\005\003{j\024\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\t\025Y#O1\001-\021%\t\t\001HI\001\n\003\t\031!\001\bd_BLH\005Z3gCVdG\017\n\032\026\t\005\025\021\021B\013\003\003\017Q#\001O<\005\013-z(\031\001\027\t\023\0055A$!A\005B\005=\021!\0049s_\022,8\r\036)sK\032L\0070\006\002\002\022A!\0211CA\017\033\t\t)B\003\003\002\030\005e\021\001\0027b]\036T!!a\007\002\t)\fg/Y\005\004\003\006U\001\"CA\0219\005\005I\021AA\022\0031\001(o\0343vGR\f%/\033;z+\t\t)\003E\002\r\003OI1!!\013\016\005\rIe\016\036\005\n\003[a\022\021!C\001\003_\ta\002\035:pIV\034G/\0227f[\026tG\017F\0021\003cA!\"a\r\002,\005\005\t\031AA\023\003\rAH%\r\005\n\003oa\022\021!C!\003s\tq\002\035:pIV\034G/\023;fe\006$xN]\013\003\003w\001R!!\020\002DAj!!a\020\013\007\005\005S\"\001\006d_2dWm\031;j_:L1\001WA \021%\t9\005HA\001\n\003\tI%\001\005dC:,\025/^1m)\021\tY%!\025\021\0071\ti%C\002\002P5\021qAQ8pY\026\fg\016C\005\0024\005\025\023\021!a\001a!I\021Q\013\017\002\002\023\005\023qK\001\tQ\006\034\bnQ8eKR\021\021Q\005\005\n\0037b\022\021!C!\003;\n\001\002^8TiJLgn\032\013\003\003#A\021\"!\031\035\003\003%\t%a\031\002\r\025\fX/\0317t)\021\tY%!\032\t\023\005M\022qLA\001\002\004\001\004bBA5\021\001\006IaG\001\007K6\004H/\037\021\t\017\0055\004\002\"\001\002p\005)\021\r\0359msV!\021\021OA<)\t\t\031\b\005\003\b9\005U\004cA\025\002x\02111&a\033C\0021B\021\"!\034\t\003\003%\t)a\037\026\t\005u\0241\021\013\007\003\n))!#\021\t\035a\022\021\021\t\004S\005\rEAB\026\002z\t\007A\006C\005$\003s\002\n\0211\001\002\bB!ABJAA\021%1\024\021\020I\001\002\004\tY\tE\003:y}\ny\bC\005\002\020\"\t\t\021\"!\002\022\0069QO\\1qa2LX\003BAJ\003C#B!!&\002(B!ABJAL!\035a\021\021TAO\003GK1!a'\016\005\031!V\017\0357feA!ABJAP!\rI\023\021\025\003\007W\0055%\031\001\027\021\013ebt(!*\021\t\035a\022q\024\005\013\003S\013i)!AA\002\005\025\026a\001=%a!I\021Q\026\005\022\002\023\005\021qV\001\020CB\004H.\037\023eK\032\fW\017\034;%cU!\021\021WA^+\t\t\031LK\002\0026^t1\001DA\\\023\r\tI,D\001\005\035>tW\r\002\004,\003W\023\r\001\f\005\n\003C\021\023!C\001\003\003\fq\"\0319qYf$C-\0324bk2$HEM\013\005\003\007\f9.\006\002\002F*\032\021qY<\021\017\005%\027qZ \002T6\021\0211\032\006\005\003\033\fy$A\005j[6,H/\0312mK&!\021\021[Af\005\035A\025m\0355NCB\004Ba\002\017\002VB\031\021&a6\005\r-\niL1\001-\021%\tY\016CI\001\n\003\ti.A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$H%M\013\005\003c\013y\016\002\004,\0033\024\r\001\f\005\n\003GD\021\023!C\001\003K\f1\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\022T\003BAt\003c,\"!!;+\007\005-x\017E\004\002J\006=w(!<\021\t\035a\022q\036\t\004S\005EHAB\026\002b\n\007A\006C\005\002v\"\t\t\021\"\003\002x\006Y!/Z1e%\026\034x\016\034<f)\t\tI\020\005\003\002\024\005m\030\002BA\003+\021aa\0242kK\016$\b")
/*    */ public class WildcardTree<T> implements Product, Serializable {
/*    */   private final Option<T> data;
/*    */   
/*    */   private final Map<String, WildcardTree<T>> children;
/*    */   
/*    */   public Option<T> data() {
/* 14 */     return this.data;
/*    */   }
/*    */   
/*    */   public Map<String, WildcardTree<T>> children() {
/* 14 */     return this.children;
/*    */   }
/*    */   
/*    */   public <T> WildcardTree<T> copy(Option<T> data, Map<String, WildcardTree<T>> children) {
/* 14 */     return new WildcardTree(data, children);
/*    */   }
/*    */   
/*    */   public <T> Option<T> copy$default$1() {
/* 14 */     return data();
/*    */   }
/*    */   
/*    */   public <T> Map<String, WildcardTree<T>> copy$default$2() {
/* 14 */     return children();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 14 */     return "WildcardTree";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 14 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 14 */     int i = x$1;
/* 14 */     switch (i) {
/*    */       default:
/* 14 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 14 */     return data();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 14 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 14 */     return x$1 instanceof WildcardTree;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 14 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 14 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 112
/*    */     //   5: aload_1
/*    */     //   6: astore_2
/*    */     //   7: aload_2
/*    */     //   8: instanceof akka/util/WildcardTree
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 116
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/util/WildcardTree
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual data : ()Lscala/Option;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual data : ()Lscala/Option;
/*    */     //   40: astore #5
/*    */     //   42: dup
/*    */     //   43: ifnonnull -> 55
/*    */     //   46: pop
/*    */     //   47: aload #5
/*    */     //   49: ifnull -> 63
/*    */     //   52: goto -> 108
/*    */     //   55: aload #5
/*    */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   60: ifeq -> 108
/*    */     //   63: aload_0
/*    */     //   64: invokevirtual children : ()Lscala/collection/immutable/Map;
/*    */     //   67: aload #4
/*    */     //   69: invokevirtual children : ()Lscala/collection/immutable/Map;
/*    */     //   72: astore #6
/*    */     //   74: dup
/*    */     //   75: ifnonnull -> 87
/*    */     //   78: pop
/*    */     //   79: aload #6
/*    */     //   81: ifnull -> 95
/*    */     //   84: goto -> 108
/*    */     //   87: aload #6
/*    */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   92: ifeq -> 108
/*    */     //   95: aload #4
/*    */     //   97: aload_0
/*    */     //   98: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   101: ifeq -> 108
/*    */     //   104: iconst_1
/*    */     //   105: goto -> 109
/*    */     //   108: iconst_0
/*    */     //   109: ifeq -> 116
/*    */     //   112: iconst_1
/*    */     //   113: goto -> 117
/*    */     //   116: iconst_0
/*    */     //   117: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #14	-> 0
/*    */     //   #63	-> 14
/*    */     //   #14	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	118	0	this	Lakka/util/WildcardTree;
/*    */     //   0	118	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public WildcardTree(Option<T> data, Map<String, WildcardTree<T>> children) {
/* 14 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public WildcardTree<T> insert(Iterator<String> elems, Object d) {
/* 20 */     String e = (String)elems.next();
/* 21 */     Map<String, WildcardTree<T>> x$1 = children().updated(e, ((WildcardTree<Object>)children().get(e).getOrElse((Function0)new WildcardTree$$anonfun$1(this))).insert(elems, d));
/* 21 */     Option<?> x$2 = copy$default$1();
/* 21 */     return elems.hasNext() ? copy((Option)x$2, x$1) : copy((Option<T>)new Some(d), copy$default$2());
/*    */   }
/*    */   
/*    */   public class WildcardTree$$anonfun$1 extends AbstractFunction0<WildcardTree<T>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final WildcardTree<T> apply() {
/* 21 */       return WildcardTree$.MODULE$.apply();
/*    */     }
/*    */     
/*    */     public WildcardTree$$anonfun$1(WildcardTree $outer) {}
/*    */   }
/*    */   
/*    */   public final WildcardTree<T> find(Iterator elems) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: invokeinterface hasNext : ()Z
/*    */     //   6: ifeq -> 116
/*    */     //   9: aload_0
/*    */     //   10: invokevirtual children : ()Lscala/collection/immutable/Map;
/*    */     //   13: aload_1
/*    */     //   14: invokeinterface next : ()Ljava/lang/Object;
/*    */     //   19: invokeinterface get : (Ljava/lang/Object;)Lscala/Option;
/*    */     //   24: new akka/util/WildcardTree$$anonfun$2
/*    */     //   27: dup
/*    */     //   28: aload_0
/*    */     //   29: invokespecial <init> : (Lakka/util/WildcardTree;)V
/*    */     //   32: invokevirtual orElse : (Lscala/Function0;)Lscala/Option;
/*    */     //   35: astore_3
/*    */     //   36: aload_3
/*    */     //   37: instanceof scala/Some
/*    */     //   40: ifeq -> 67
/*    */     //   43: aload_3
/*    */     //   44: checkcast scala/Some
/*    */     //   47: astore #4
/*    */     //   49: aload #4
/*    */     //   51: invokevirtual x : ()Ljava/lang/Object;
/*    */     //   54: checkcast akka/util/WildcardTree
/*    */     //   57: astore #5
/*    */     //   59: aload #5
/*    */     //   61: aload_1
/*    */     //   62: astore_1
/*    */     //   63: astore_0
/*    */     //   64: goto -> 0
/*    */     //   67: getstatic scala/None$.MODULE$ : Lscala/None$;
/*    */     //   70: aload_3
/*    */     //   71: astore #7
/*    */     //   73: dup
/*    */     //   74: ifnonnull -> 86
/*    */     //   77: pop
/*    */     //   78: aload #7
/*    */     //   80: ifnull -> 94
/*    */     //   83: goto -> 107
/*    */     //   86: aload #7
/*    */     //   88: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   91: ifeq -> 107
/*    */     //   94: getstatic akka/util/WildcardTree$.MODULE$ : Lakka/util/WildcardTree$;
/*    */     //   97: invokevirtual apply : ()Lakka/util/WildcardTree;
/*    */     //   100: astore #6
/*    */     //   102: aload #6
/*    */     //   104: goto -> 117
/*    */     //   107: new scala/MatchError
/*    */     //   110: dup
/*    */     //   111: aload_3
/*    */     //   112: invokespecial <init> : (Ljava/lang/Object;)V
/*    */     //   115: athrow
/*    */     //   116: aload_0
/*    */     //   117: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #25	-> 0
/*    */     //   #27	-> 9
/*    */     //   #28	-> 36
/*    */     //   #29	-> 67
/*    */     //   #27	-> 102
/*    */     //   #25	-> 116
/*    */     //   #24	-> 117
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	118	0	this	Lakka/util/WildcardTree;
/*    */     //   0	118	1	elems	Lscala/collection/Iterator;
/*    */     //   59	59	5	branch	Lakka/util/WildcardTree;
/*    */   }
/*    */   
/*    */   public static <T> HashMap<String, WildcardTree<T>> $lessinit$greater$default$2() {
/*    */     return WildcardTree$.MODULE$.$lessinit$greater$default$2();
/*    */   }
/*    */   
/*    */   public static <T> None$ $lessinit$greater$default$1() {
/*    */     return WildcardTree$.MODULE$.$lessinit$greater$default$1();
/*    */   }
/*    */   
/*    */   public static <T> HashMap<String, WildcardTree<T>> apply$default$2() {
/*    */     return WildcardTree$.MODULE$.apply$default$2();
/*    */   }
/*    */   
/*    */   public static <T> None$ apply$default$1() {
/*    */     return WildcardTree$.MODULE$.apply$default$1();
/*    */   }
/*    */   
/*    */   public static <T> WildcardTree<T> apply() {
/*    */     return WildcardTree$.MODULE$.apply();
/*    */   }
/*    */   
/*    */   public class $anonfun$2 extends AbstractFunction0<Option<WildcardTree<T>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Option<WildcardTree<T>> apply() {
/* 27 */       return this.$outer.children().get("*");
/*    */     }
/*    */     
/*    */     public $anonfun$2(WildcardTree $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\WildcardTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */