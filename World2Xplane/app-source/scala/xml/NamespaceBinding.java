/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.List$;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005}e\001B\001\003\001\036\021\001CT1nKN\004\030mY3CS:$\027N\\4\013\005\r!\021a\001=nY*\tQ!A\003tG\006d\027m\001\001\024\013\001AA\002E\n\021\005%QQ\"\001\003\n\005-!!AB!osJ+g\r\005\002\016\0355\t!!\003\002\020\005\tAQ)];bY&$\030\020\005\002\n#%\021!\003\002\002\b!J|G-^2u!\tIA#\003\002\026\t\ta1+\032:jC2L'0\0312mK\"Aq\003\001BK\002\023\005\001$\001\004qe\0264\027\016_\013\0023A\021!$\b\b\003\023mI!\001\b\003\002\rA\023X\rZ3g\023\tqrD\001\004TiJLgn\032\006\0039\021A\001\"\t\001\003\022\003\006I!G\001\baJ,g-\033=!\021!\031\003A!f\001\n\003A\022aA;sS\"AQ\005\001B\tB\003%\021$\001\003ve&\004\003\002C\024\001\005+\007I\021\001\025\002\rA\f'/\0328u+\005I\003CA\007\001\021!Y\003A!E!\002\023I\023a\0029be\026tG\017\t\005\006[\001!\tAL\001\007y%t\027\016\036 \025\t%z\003'\r\005\006/1\002\r!\007\005\006G1\002\r!\007\005\006O1\002\r!\013\005\006g\001!\t\001N\001\007O\026$XKU%\025\005e)\004\"\002\0343\001\004I\022aB0qe\0264\027\016\037\005\006q\001!\t!O\001\nO\026$\bK]3gSb$\"!\007\036\t\013m:\004\031A\r\002\t}+(/\033\005\006{\001!\tEP\001\ti>\034FO]5oOR\t\021\004C\003A\001\021\005\023)\001\005dC:,\025/^1m)\t\021U\t\005\002\n\007&\021A\t\002\002\b\005>|G.Z1o\021\0251u\b1\001H\003\025yG\017[3s!\tI\001*\003\002J\t\t\031\021I\\=\t\013-\003A\021\t'\002\033M$(/[2u?\022*\027\017J3r)\t\021U\nC\003G\025\002\007A\002C\003P\001\021\005\001+\001\tcCNL7OR8s\021\006\034\bnQ8eKV\t\021\013E\002S5\036s!a\025-\017\005Q;V\"A+\013\005Y3\021A\002\037s_>$h(C\001\006\023\tIF!A\004qC\016\\\027mZ3\n\005mc&aA*fc*\021\021\f\002\005\006=\002!\taX\001\fEVLG\016Z*ue&tw\r\006\002\032A\")\021-\030a\001S\005!1\017^8q\021\025q\006\001\"\001d)\r!w\r\034\t\003\023\025L!A\032\003\003\tUs\027\016\036\005\006Q\n\004\r![\001\003g\n\004\"A\0256\n\005-d&!D*ue&twMQ;jY\022,'\017C\003bE\002\007\021\006C\004o\001\005\005I\021A8\002\t\r|\007/\037\013\005SA\f(\017C\004\030[B\005\t\031A\r\t\017\rj\007\023!a\0013!9q%\034I\001\002\004I\003b\002;\001#\003%\t!^\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\0051(FA\rxW\005A\bCA=\033\005Q(BA>}\003%)hn\0315fG.,GM\003\002~\t\005Q\021M\0348pi\006$\030n\0348\n\005}T(!E;oG\",7m[3e-\006\024\030.\0318dK\"A\0211\001\001\022\002\023\005Q/\001\bd_BLH\005Z3gCVdG\017\n\032\t\023\005\035\001!%A\005\002\005%\021AD2paf$C-\0324bk2$HeM\013\003\003\027Q#!K<\t\023\005=\001!!A\005B\005E\021!\0049s_\022,8\r\036)sK\032L\0070\006\002\002\024A!\021QCA\020\033\t\t9B\003\003\002\032\005m\021\001\0027b]\036T!!!\b\002\t)\fg/Y\005\004=\005]\001\"CA\022\001\005\005I\021AA\023\0031\001(o\0343vGR\f%/\033;z+\t\t9\003E\002\n\003SI1!a\013\005\005\rIe\016\036\005\n\003_\001\021\021!C\001\003c\ta\002\035:pIV\034G/\0227f[\026tG\017F\002H\003gA!\"!\016\002.\005\005\t\031AA\024\003\rAH%\r\005\n\003s\001\021\021!C!\003w\tq\002\035:pIV\034G/\023;fe\006$xN]\013\003\003{\001R!a\020\002F\035k!!!\021\013\007\005\rC!\001\006d_2dWm\031;j_:LA!a\022\002B\tA\021\n^3sCR|'\017K\003\001\003\027\n\t\006E\002\n\003\033J1!a\024\005\005A\031VM]5bYZ+'o]5p]VKEI\b\005^\030iP8ZY:<\020%\t)FAA\001\022\003\t9&\001\tOC6,7\017]1dK\nKg\016Z5oOB\031Q\"!\027\007\021\005\021\021\021!E\001\0037\032R!!\027\002^M\001\002\"a\030\002feI\022&K\007\003\003CR1!a\031\005\003\035\021XO\034;j[\026LA!a\032\002b\t\t\022IY:ue\006\034GOR;oGRLwN\\\032\t\0175\nI\006\"\001\002lQ\021\021q\013\005\n{\005e\023\021!C#\003_\"\"!a\005\t\025\005M\024\021LA\001\n\003\013)(A\003baBd\027\020F\004*\003o\nI(a\037\t\r]\t\t\b1\001\032\021\031\031\023\021\017a\0013!1q%!\035A\002%B!\"a \002Z\005\005I\021QAA\003\035)h.\0319qYf$B!a!\002\020B)\021\"!\"\002\n&\031\021q\021\003\003\r=\003H/[8o!\031I\0211R\r\032S%\031\021Q\022\003\003\rQ+\b\017\\34\021%\t\t*! \002\002\003\007\021&A\002yIAB!\"!&\002Z\005\005I\021BAL\003-\021X-\0313SKN|GN^3\025\005\005e\005\003BA\013\0037KA!!(\002\030\t1qJ\0316fGR\004")
/*    */ public class NamespaceBinding implements Equality, Product, Serializable {
/*    */   public static final long serialVersionUID = -2518644165573446725L;
/*    */   
/*    */   private final String prefix;
/*    */   
/*    */   private final String uri;
/*    */   
/*    */   private final NamespaceBinding parent;
/*    */   
/*    */   public boolean strict_$bang$eq(Equality other) {
/* 22 */     return Equality$class.strict_$bang$eq(this, other);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 22 */     return Equality$class.hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object other) {
/* 22 */     return Equality$class.equals(this, other);
/*    */   }
/*    */   
/*    */   public final boolean xml_$eq$eq(Object other) {
/* 22 */     return Equality$class.xml_$eq$eq(this, other);
/*    */   }
/*    */   
/*    */   public final boolean xml_$bang$eq(Object other) {
/* 22 */     return Equality$class.xml_$bang$eq(this, other);
/*    */   }
/*    */   
/*    */   public String prefix() {
/* 22 */     return this.prefix;
/*    */   }
/*    */   
/*    */   public String uri() {
/* 22 */     return this.uri;
/*    */   }
/*    */   
/*    */   public NamespaceBinding parent() {
/* 22 */     return this.parent;
/*    */   }
/*    */   
/*    */   public NamespaceBinding copy(String prefix, String uri, NamespaceBinding parent) {
/* 22 */     return new NamespaceBinding(prefix, uri, parent);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 22 */     return prefix();
/*    */   }
/*    */   
/*    */   public String copy$default$2() {
/* 22 */     return uri();
/*    */   }
/*    */   
/*    */   public NamespaceBinding copy$default$3() {
/* 22 */     return parent();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 22 */     return "NamespaceBinding";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 22 */     return 3;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 22 */     switch (x$1) {
/*    */       default:
/* 22 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 2:
/*    */       
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 22 */     return prefix();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 22 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public NamespaceBinding(String prefix, String uri, NamespaceBinding parent) {
/* 22 */     Equality$class.$init$(this);
/* 22 */     Product.class.$init$(this);
/* 24 */     if (prefix == null) {
/* 24 */       if ("" != null)
/*    */         return; 
/* 24 */     } else if (!prefix.equals("")) {
/*    */       return;
/*    */     } 
/* 25 */     throw new IllegalArgumentException("zero length prefix not allowed");
/*    */   }
/*    */   
/*    */   public String getURI(String _prefix) {
/* 28 */     if (prefix() == null) {
/* 28 */       prefix();
/* 28 */       if (_prefix != null);
/* 28 */     } else if (prefix().equals(_prefix)) {
/*    */     
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getPrefix(String _uri) {
/* 37 */     String str = uri();
/* 37 */     if (_uri == null) {
/* 37 */       if (str != null);
/* 37 */     } else if (_uri.equals(str)) {
/*    */     
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 39 */     return Utility$.MODULE$.sbToString((Function1<StringBuilder, BoxedUnit>)new NamespaceBinding$$anonfun$toString$1(this));
/*    */   }
/*    */   
/*    */   public class NamespaceBinding$$anonfun$toString$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(StringBuilder x$1) {
/* 39 */       this.$outer.buildString(x$1, TopScope$.MODULE$);
/*    */     }
/*    */     
/*    */     public NamespaceBinding$$anonfun$toString$1(NamespaceBinding $outer) {}
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object other) {
/*    */     boolean bool;
/* 41 */     if (other instanceof NamespaceBinding) {
/* 41 */       bool = true;
/*    */     } else {
/* 43 */       bool = false;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */   
/*    */   public boolean strict_$eq$eq(Equality other) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: instanceof scala/xml/NamespaceBinding
/*    */     //   4: ifeq -> 113
/*    */     //   7: aload_1
/*    */     //   8: checkcast scala/xml/NamespaceBinding
/*    */     //   11: astore #4
/*    */     //   13: aload_0
/*    */     //   14: invokevirtual prefix : ()Ljava/lang/String;
/*    */     //   17: aload #4
/*    */     //   19: invokevirtual prefix : ()Ljava/lang/String;
/*    */     //   22: astore_2
/*    */     //   23: dup
/*    */     //   24: ifnonnull -> 35
/*    */     //   27: pop
/*    */     //   28: aload_2
/*    */     //   29: ifnull -> 42
/*    */     //   32: goto -> 107
/*    */     //   35: aload_2
/*    */     //   36: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   39: ifeq -> 107
/*    */     //   42: aload_0
/*    */     //   43: invokevirtual uri : ()Ljava/lang/String;
/*    */     //   46: aload #4
/*    */     //   48: invokevirtual uri : ()Ljava/lang/String;
/*    */     //   51: astore_3
/*    */     //   52: dup
/*    */     //   53: ifnonnull -> 64
/*    */     //   56: pop
/*    */     //   57: aload_3
/*    */     //   58: ifnull -> 71
/*    */     //   61: goto -> 107
/*    */     //   64: aload_3
/*    */     //   65: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   68: ifeq -> 107
/*    */     //   71: aload_0
/*    */     //   72: invokevirtual parent : ()Lscala/xml/NamespaceBinding;
/*    */     //   75: aload #4
/*    */     //   77: invokevirtual parent : ()Lscala/xml/NamespaceBinding;
/*    */     //   80: astore #5
/*    */     //   82: dup
/*    */     //   83: ifnonnull -> 95
/*    */     //   86: pop
/*    */     //   87: aload #5
/*    */     //   89: ifnull -> 103
/*    */     //   92: goto -> 107
/*    */     //   95: aload #5
/*    */     //   97: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   100: ifeq -> 107
/*    */     //   103: iconst_1
/*    */     //   104: goto -> 108
/*    */     //   107: iconst_0
/*    */     //   108: istore #6
/*    */     //   110: goto -> 116
/*    */     //   113: iconst_0
/*    */     //   114: istore #6
/*    */     //   116: iload #6
/*    */     //   118: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #47	-> 0
/*    */     //   #46	-> 0
/*    */     //   #48	-> 113
/*    */     //   #46	-> 116
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	119	0	this	Lscala/xml/NamespaceBinding;
/*    */     //   0	119	1	other	Lscala/xml/Equality;
/*    */   }
/*    */   
/*    */   public Seq<Object> basisForHashCode() {
/* 51 */     return (Seq<Object>)List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray(new Object[] { prefix(), uri(), parent() }));
/*    */   }
/*    */   
/*    */   public String buildString(NamespaceBinding stop) {
/* 53 */     return Utility$.MODULE$.sbToString((Function1<StringBuilder, BoxedUnit>)new NamespaceBinding$$anonfun$buildString$1(this, stop));
/*    */   }
/*    */   
/*    */   public class NamespaceBinding$$anonfun$buildString$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final NamespaceBinding stop$1;
/*    */     
/*    */     public final void apply(StringBuilder x$2) {
/* 53 */       this.$outer.buildString(x$2, this.stop$1);
/*    */     }
/*    */     
/*    */     public NamespaceBinding$$anonfun$buildString$1(NamespaceBinding $outer, NamespaceBinding stop$1) {}
/*    */   }
/*    */   
/*    */   public void buildString(StringBuilder sb, NamespaceBinding stop) {
/* 56 */     if (this == stop)
/*    */       return; 
/* 58 */     Predef$ predef$ = Predef$.MODULE$;
/* 58 */     String s = (new StringOps(" xmlns%s=\"%s\"")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { (prefix() == null) ? "" : (new StringBuilder()).append(":").append(prefix()).toString(), 
/* 60 */             (uri() == null) ? "" : uri() }));
/* 62 */     parent().buildString(sb.append(s), stop);
/*    */   }
/*    */   
/*    */   public static Function1<Tuple3<String, String, NamespaceBinding>, NamespaceBinding> tupled() {
/*    */     return NamespaceBinding$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<String, Function1<String, Function1<NamespaceBinding, NamespaceBinding>>> curried() {
/*    */     return NamespaceBinding$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\NamespaceBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */