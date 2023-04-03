/*     */ package scala.xml;
/*     */ 
/*     */ import scala.Enumeration;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\005t!B\001\003\021\0039\021\001\002(pI\026T!a\001\003\002\007alGNC\001\006\003\025\0318-\0317b\007\001\001\"\001C\005\016\003\t1QA\003\002\t\002-\021AAT8eKN\021\021\002\004\t\003\0339i\021\001B\005\003\037\021\021a!\0218z%\0264\007\"B\t\n\t\003\021\022A\002\037j]&$h\bF\001\b\021\025!\022\002\"\002\026\0031qu.\021;ue&\024W\017^3t+\0051\002C\001\005\030\023\tA\"A\001\005NKR\fG)\031;b\021\035Q\022B1A\005\002m\ta\"R7qift\025-\\3ta\006\034W-F\001\035!\ti\"%D\001\037\025\ty\002%\001\003mC:<'\"A\021\002\t)\fg/Y\005\003Gy\021aa\025;sS:<\007BB\023\nA\003%A$A\bF[B$\030PT1nKN\004\030mY3!\021\0259\023\002\"\001)\003))h.\0319qYf\034V-\035\013\004S\005u\003cA\007+Y%\0211\006\002\002\005'>lW\rE\003\016[=2R'\003\002/\t\t1A+\0369mKN\002\"\001M\032\017\0055\t\024B\001\032\005\003\031\001&/\0323fM&\0211\005\016\006\003e\021\0012A\016 B\035\t9DH\004\0029w5\t\021H\003\002;\r\0051AH]8pizJ\021!B\005\003{\021\tq\001]1dW\006<W-\003\002@\001\n\0311+Z9\013\005u\"\001C\001\005C\r\025Q!!!\001D'\t\021E\t\005\002\t\013&\021aI\001\002\b\035>$WmU3r\021\025\t\"\t\"\001I)\005\t\005\"\002&C\t\003Y\025A\0029sK\032L\0070F\0010\021\025i%I\"\001L\003\025a\027MY3m\021\025y%\t\"\001Q\003\031I7/\021;p[V\t\021\013\005\002\016%&\0211\013\002\002\b\005>|G.Z1o\021\025)&\t\"\001Q\003M!wnQ8mY\026\034GOT1nKN\004\030mY3t\021\0259&\t\"\001Q\003-!w\016\026:b]N4wN]7\t\013e\023E\021\001.\002\013M\034w\016]3\026\003m\003\"\001\003/\n\005u\023!\001\005(b[\026\034\b/Y2f\005&tG-\0338h\021\025y&\t\"\001L\003%q\027-\\3ta\006\034W\rC\003b\005\022\005!-\001\007hKRt\025-\\3ta\006\034W\r\006\0020G\")A\r\031a\001_\005\031\001O]3\t\013\031\024EQA4\002\023\005$HO]5ckR,GC\0015l!\ri\021.N\005\003U\022\021aa\0249uS>t\007\"\0027f\001\004y\023aA6fs\")aM\021C\003]R\031\001n\\9\t\013Al\007\031A\030\002\007U\024\030\016C\003m[\002\007q\006C\003t\005\022\005Q#\001\006biR\024\030NY;uKNDQ!\036\"\007\002Y\fQa\0315jY\022,\022!\016\005\006q\n#\tA^\001\021]>tW)\0349us\016C\027\016\0343sK:DQA\037\"\005\002m\f!\002Z3tG\026tG-\0318u+\005a\bc\001\034~\003&\021a\020\021\002\005\031&\034H\017\003\004\002\002\t#\ta_\001\023I\026\0348-\0328eC:$xl\034:`g\026dg\rC\004\002\006\t#\t%a\002\002\021\r\fg.R9vC2$2!UA\005\021!\tY!a\001A\002\0055\021!B8uQ\026\024\bcA\007\002\020%\031\021\021\003\003\003\007\005s\027\020C\004\002\026\t#\t&a\006\002!\t\f7/[:G_JD\025m\0355D_\022,WCAA\r!\0211d(!\004\t\017\005u!\t\"\021\002 \005i1\017\036:jGR|F%Z9%KF$2!UA\021\021!\tY!a\007A\002\005\r\002c\001\005\002&%\031\021q\005\002\003\021\025\013X/\0317jifDa!a\013C\t\0031\030A\002;iKN+\027\017C\004\0020\t#\t!!\r\002\027\t,\030\016\0343TiJLgn\032\013\004_\005M\002bBA\033\003[\001\r!U\001\016gR\024\030\016]\"p[6,g\016^:\t\017\005e\"\t\"\021\002<\005AAo\\*ue&tw\rF\0010\021\035\tyD\021C\001\003\003\nAB\\1nKR{7\013\036:j]\036$B!a\021\002JA\031a'!\022\n\007\005\035\003IA\007TiJLgn\032\"vS2$WM\035\005\t\003\027\ni\0041\001\002D\005\0211O\031\005\b\003\037\022E\021AA)\003\035AX\016\034+za\026$\"!a\025\021\007!\t)&C\002\002X\t\021!\002V=qKNKXNY8m\021\031\tYF\021C!\027\006!A/\032=u\021\031\tyF\na\001\003\006\ta\016")
/*     */ public abstract class Node extends NodeSeq {
/*     */   public static Some<Tuple3<String, MetaData, Seq<Node>>> unapplySeq(Node paramNode) {
/*     */     return Node$.MODULE$.unapplySeq(paramNode);
/*     */   }
/*     */   
/*     */   public static String EmptyNamespace() {
/*     */     return Node$.MODULE$.EmptyNamespace();
/*     */   }
/*     */   
/*     */   public static MetaData NoAttributes() {
/*     */     return Node$.MODULE$.NoAttributes();
/*     */   }
/*     */   
/*     */   public String prefix() {
/*     */     return null;
/*     */   }
/*     */   
/*     */   public abstract String label();
/*     */   
/*     */   public boolean isAtom() {
/*  44 */     return this instanceof Atom;
/*     */   }
/*     */   
/*     */   public boolean doCollectNamespaces() {
/*  47 */     return true;
/*     */   }
/*     */   
/*     */   public boolean doTransform() {
/*  48 */     return true;
/*     */   }
/*     */   
/*     */   public NamespaceBinding scope() {
/*  55 */     return TopScope$.MODULE$;
/*     */   }
/*     */   
/*     */   public String namespace() {
/*  60 */     return getNamespace(prefix());
/*     */   }
/*     */   
/*     */   public String getNamespace(String pre) {
/*  70 */     return (scope() == null) ? null : scope().getURI(pre);
/*     */   }
/*     */   
/*     */   public final Option<Seq<Node>> attribute(String key) {
/*  80 */     return attributes().get(key);
/*     */   }
/*     */   
/*     */   public final Option<Seq<Node>> attribute(String uri, String key) {
/*  92 */     return attributes().get(uri, this, key);
/*     */   }
/*     */   
/*     */   public MetaData attributes() {
/* 101 */     return Null$.MODULE$;
/*     */   }
/*     */   
/*     */   public abstract Seq<Node> child();
/*     */   
/*     */   public Seq<Node> nonEmptyChildren() {
/* 112 */     return (Seq<Node>)child().filterNot((Function1)new Node$$anonfun$nonEmptyChildren$1(this));
/*     */   }
/*     */   
/*     */   public class Node$$anonfun$nonEmptyChildren$1 extends AbstractFunction1<Node, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Node x$1) {
/* 112 */       if (x$1.toString() == null) {
/* 112 */         x$1.toString();
/* 112 */         if ("" != null);
/* 112 */       } else if (x$1.toString().equals("")) {
/*     */       
/*     */       } 
/*     */     }
/*     */     
/*     */     public Node$$anonfun$nonEmptyChildren$1(Node $outer) {}
/*     */   }
/*     */   
/*     */   public List<Node> descendant() {
/* 119 */     return (List<Node>)child().toList().flatMap((Function1)new Node$$anonfun$descendant$1(this), List$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public class Node$$anonfun$descendant$1 extends AbstractFunction1<Node, List<Node>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<Node> apply(Node x) {
/* 119 */       return x.descendant().$colon$colon(x);
/*     */     }
/*     */     
/*     */     public Node$$anonfun$descendant$1(Node $outer) {}
/*     */   }
/*     */   
/*     */   public List<Node> descendant_or_self() {
/* 125 */     return descendant().$colon$colon(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object other) {
/*     */     boolean bool;
/* 127 */     if (other instanceof Group) {
/* 127 */       bool = false;
/* 129 */     } else if (other instanceof Node) {
/* 129 */       bool = true;
/*     */     } else {
/* 130 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   public Seq<Object> basisForHashCode() {
/* 134 */     String str1 = prefix(), str2 = label();
/* 134 */     MetaData metaData = attributes();
/* 134 */     return (Seq<Object>)nonEmptyChildren().toList().$colon$colon(metaData).$colon$colon(str2).$colon$colon(str1);
/*     */   }
/*     */   
/*     */   public boolean strict_$eq$eq(Equality other) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: instanceof scala/xml/Group
/*     */     //   4: ifeq -> 13
/*     */     //   7: iconst_0
/*     */     //   8: istore #6
/*     */     //   10: goto -> 146
/*     */     //   13: aload_1
/*     */     //   14: instanceof scala/xml/Node
/*     */     //   17: ifeq -> 143
/*     */     //   20: aload_1
/*     */     //   21: checkcast scala/xml/Node
/*     */     //   24: astore #5
/*     */     //   26: aload_0
/*     */     //   27: invokevirtual prefix : ()Ljava/lang/String;
/*     */     //   30: aload #5
/*     */     //   32: invokevirtual prefix : ()Ljava/lang/String;
/*     */     //   35: astore_2
/*     */     //   36: dup
/*     */     //   37: ifnonnull -> 48
/*     */     //   40: pop
/*     */     //   41: aload_2
/*     */     //   42: ifnull -> 55
/*     */     //   45: goto -> 137
/*     */     //   48: aload_2
/*     */     //   49: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   52: ifeq -> 137
/*     */     //   55: aload_0
/*     */     //   56: invokevirtual label : ()Ljava/lang/String;
/*     */     //   59: aload #5
/*     */     //   61: invokevirtual label : ()Ljava/lang/String;
/*     */     //   64: astore_3
/*     */     //   65: dup
/*     */     //   66: ifnonnull -> 77
/*     */     //   69: pop
/*     */     //   70: aload_3
/*     */     //   71: ifnull -> 84
/*     */     //   74: goto -> 137
/*     */     //   77: aload_3
/*     */     //   78: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   81: ifeq -> 137
/*     */     //   84: aload_0
/*     */     //   85: invokevirtual attributes : ()Lscala/xml/MetaData;
/*     */     //   88: aload #5
/*     */     //   90: invokevirtual attributes : ()Lscala/xml/MetaData;
/*     */     //   93: astore #4
/*     */     //   95: dup
/*     */     //   96: ifnonnull -> 108
/*     */     //   99: pop
/*     */     //   100: aload #4
/*     */     //   102: ifnull -> 116
/*     */     //   105: goto -> 137
/*     */     //   108: aload #4
/*     */     //   110: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   113: ifeq -> 137
/*     */     //   116: aload_0
/*     */     //   117: invokevirtual nonEmptyChildren : ()Lscala/collection/Seq;
/*     */     //   120: aload #5
/*     */     //   122: invokevirtual nonEmptyChildren : ()Lscala/collection/Seq;
/*     */     //   125: invokeinterface sameElements : (Lscala/collection/GenIterable;)Z
/*     */     //   130: ifeq -> 137
/*     */     //   133: iconst_1
/*     */     //   134: goto -> 138
/*     */     //   137: iconst_0
/*     */     //   138: istore #6
/*     */     //   140: goto -> 146
/*     */     //   143: iconst_0
/*     */     //   144: istore #6
/*     */     //   146: iload #6
/*     */     //   148: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #137	-> 0
/*     */     //   #136	-> 0
/*     */     //   #138	-> 13
/*     */     //   #139	-> 26
/*     */     //   #140	-> 55
/*     */     //   #141	-> 84
/*     */     //   #143	-> 116
/*     */     //   #141	-> 133
/*     */     //   #145	-> 143
/*     */     //   #136	-> 146
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	149	0	this	Lscala/xml/Node;
/*     */     //   0	149	1	other	Lscala/xml/Equality;
/*     */   }
/*     */   
/*     */   public Seq<Node> theSeq() {
/* 153 */     return (Seq<Node>)Nil$.MODULE$.$colon$colon(this);
/*     */   }
/*     */   
/*     */   public String buildString(boolean stripComments) {
/* 161 */     NamespaceBinding x$10 = Utility$.MODULE$.serialize$default$2();
/* 161 */     StringBuilder x$11 = Utility$.MODULE$.serialize$default$3();
/* 161 */     boolean x$12 = Utility$.MODULE$.serialize$default$5(), x$13 = Utility$.MODULE$.serialize$default$6();
/* 161 */     Enumeration.Value x$14 = Utility$.MODULE$.serialize$default$7();
/* 161 */     return Utility$.MODULE$.serialize(this, x$10, x$11, stripComments, x$12, x$13, x$14).toString();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 166 */     return buildString(false);
/*     */   }
/*     */   
/*     */   public StringBuilder nameToString(StringBuilder sb) {
/* 173 */     sb.append(prefix());
/* 174 */     (prefix() == null) ? BoxedUnit.UNIT : sb.append(':');
/* 176 */     return sb.append(label());
/*     */   }
/*     */   
/*     */   public TypeSymbol xmlType() {
/*     */     return null;
/*     */   }
/*     */   
/*     */   public String text() {
/* 196 */     return super.text();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Node.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */