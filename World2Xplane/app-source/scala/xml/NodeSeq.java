/*     */ package scala.xml;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractSeq;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.Seq$;
/*     */ import scala.collection.immutable.Traversable;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParSeq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.VolatileByteRef;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\rt!B\001\003\021\0039\021a\002(pI\026\034V-\035\006\003\007\021\t1\001_7m\025\005)\021!B:dC2\f7\001\001\t\003\021%i\021A\001\004\006\025\tA\ta\003\002\b\035>$WmU3r'\tIA\002\005\002\016\0355\tA!\003\002\020\t\t1\021I\\=SK\032DQ!E\005\005\002I\ta\001P5oSRtD#A\004\t\017QI!\031!C\003+\005)Q)\0349usV\ta\003\005\002\t/\031)!BAA\0011M)q#\007\022)WA\031!$H\020\016\003mQ!\001\b\003\002\025\r|G\016\\3di&|g.\003\002\0377\tY\021IY:ue\006\034GoU3r!\tA\001%\003\002\"\005\t!aj\0343f!\r\031ceH\007\002I)\021QeG\001\nS6lW\017^1cY\026L!a\n\023\003\007M+\027\017\005\003\033S}1\022B\001\026\034\005\035\031V-\035'jW\026\004\"\001\003\027\n\0055\022!\001C#rk\006d\027\016^=\t\013E9B\021A\030\025\003YAa!M\f!\n#\022\024A\0038fo\n+\030\016\0343feV\t1\007\005\0035o}1R\"A\033\013\005YZ\022aB7vi\006\024G.Z\005\003qU\022qAQ;jY\022,'\017C\003;/\031\0051(\001\004uQ\026\034V-]\013\002yA\031Q(R\020\017\005y\032eBA C\033\005\001%BA!\007\003\031a$o\\8u}%\tQ!\003\002E\t\0059\001/Y2lC\036,\027BA\024G\025\t!E\001C\003I/\021\005\021*\001\004mK:<G\017[\013\002\025B\021QbS\005\003\031\022\0211!\0238u\021\025qu\003\"\021P\003!IG/\032:bi>\024X#\001)\021\007i\tv$\003\002S7\tA\021\n^3sCR|'\017C\003U/\021\005Q+A\003baBd\027\020\006\002 -\")qk\025a\001\025\006\t\021\016C\003U/\021\005\021\f\006\002\0275\")1\f\027a\0019\006\ta\r\005\003\016;~y\026B\0010\005\005%1UO\\2uS>t\027\007\005\002\016A&\021\021\r\002\002\b\005>|G.Z1o\021\025\031w\003\"\001e\003AAX\016\\0tC6,W\t\\3nK:$8/\006\002f[R\021qL\032\005\006O\n\004\r\001[\001\005i\"\fG\017E\002>S.L!A\033$\003\021%#XM]1cY\026\004\"\001\\7\r\001\021)aN\031b\001_\n\t\021)\005\002qgB\021Q\"]\005\003e\022\021qAT8uQ&tw\r\005\002\016i&\021Q\017\002\002\004\003:L\b\"B<\030\t#A\030\001\0052bg&\034hi\034:ICND7i\0343f+\005I\bcA\037Fg\")1p\006C!y\006A1-\0318FcV\fG\016\006\002`{\")aP\037a\001g\006)q\016\0365fe\"9\021\021A\f\005B\005\r\021!D:ue&\034Go\030\023fc\022*\027\017F\002`\003\013AQA`@A\002-Bq!!\003\030\t\003\tY!A\004%ENd\027m\0355\025\007Y\ti\001C\004h\003\017\001\r!a\004\021\t\005E\021q\003\b\004\033\005M\021bAA\013\t\0051\001K]3eK\032LA!!\007\002\034\t11\013\036:j]\036T1!!\006\005\021\035\tyb\006C\001\003C\ta\002\n2tY\006\034\b\016\n2tY\006\034\b\016F\002\027\003GAqaZA\017\001\004\ty\001C\004\002(]!\t%!\013\002\021Q|7\013\036:j]\036$\"!a\004\t\017\0055r\003\"\001\0020\005!A/\032=u+\t\ty\001C\004\0024%\001\013Q\002\f\002\r\025k\007\017^=!\021\035\t9$\003C\001\003s\tqA\032:p[N+\027\017F\002\027\003wAq!!\020\0026\001\007A(A\001t\013\025\t\t%\003\001\027\005\021\031u\016\0347\t\017\005\025\023\002b\001\002H\005a1-\0318Ck&dGM\022:p[V\021\021\021\n\t\t\003\027\n\t&!\026 -5\021\021Q\n\006\004\003\037Z\022aB4f]\026\024\030nY\005\005\003'\niE\001\007DC:\024U/\0337e\rJ|W\016\005\003\002X\005}R\"A\005\t\013EJA\021\001\032\t\017\005u\023\002b\001\002`\005a1/Z9U_:{G-Z*fcR\031a#!\031\t\017\005u\0221\fa\001y\001")
/*     */ public abstract class NodeSeq extends AbstractSeq<Node> implements Seq<Node>, SeqLike<Node, NodeSeq>, Equality {
/*     */   public static class NodeSeq$$anon$1 extends NodeSeq {
/*     */     private final Seq s$1;
/*     */     
/*     */     public NodeSeq$$anon$1(Seq s$1) {}
/*     */     
/*     */     public Seq<Node> theSeq() {
/*  24 */       return this.s$1;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class NodeSeq$$anon$2 implements CanBuildFrom<NodeSeq, Node, NodeSeq> {
/*     */     public Builder<Node, NodeSeq> apply(NodeSeq from) {
/*  29 */       return NodeSeq$.MODULE$.newBuilder();
/*     */     }
/*     */     
/*     */     public Builder<Node, NodeSeq> apply() {
/*  30 */       return NodeSeq$.MODULE$.newBuilder();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class NodeSeq$$anonfun$newBuilder$1 extends AbstractFunction1<Seq<Node>, NodeSeq> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final NodeSeq apply(Seq<Node> s) {
/*  32 */       return NodeSeq$.MODULE$.fromSeq(s);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean strict_$bang$eq(Equality other) {
/*  42 */     return Equality$class.strict_$bang$eq(this, other);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  42 */     return Equality$class.hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/*  42 */     return Equality$class.equals(this, other);
/*     */   }
/*     */   
/*     */   public final boolean xml_$eq$eq(Object other) {
/*  42 */     return Equality$class.xml_$eq$eq(this, other);
/*     */   }
/*     */   
/*     */   public final boolean xml_$bang$eq(Object other) {
/*  42 */     return Equality$class.xml_$bang$eq(this, other);
/*     */   }
/*     */   
/*     */   public GenericCompanion<Seq> companion() {
/*  42 */     return Seq.class.companion(this);
/*     */   }
/*     */   
/*     */   public Seq<Node> toSeq() {
/*  42 */     return Seq.class.toSeq(this);
/*     */   }
/*     */   
/*     */   public Seq<Node> seq() {
/*  42 */     return Seq.class.seq(this);
/*     */   }
/*     */   
/*     */   public Combiner<Node, ParSeq<Node>> parCombiner() {
/*  42 */     return Seq.class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public NodeSeq() {
/*  42 */     Traversable.class.$init$((Traversable)this);
/*  42 */     Iterable.class.$init$((Iterable)this);
/*  42 */     Seq.class.$init$(this);
/*  42 */     Equality$class.$init$(this);
/*     */   }
/*     */   
/*     */   public Builder<Node, NodeSeq> newBuilder() {
/*  46 */     return NodeSeq$.MODULE$.newBuilder();
/*     */   }
/*     */   
/*     */   public int length() {
/*  49 */     return theSeq().length();
/*     */   }
/*     */   
/*     */   public Iterator<Node> iterator() {
/*  50 */     return theSeq().iterator();
/*     */   }
/*     */   
/*     */   public Node apply(int i) {
/*  52 */     return (Node)theSeq().apply(i);
/*     */   }
/*     */   
/*     */   public NodeSeq apply(Function1 f) {
/*  53 */     return (NodeSeq)filter(f);
/*     */   }
/*     */   
/*     */   public <A> boolean xml_sameElements(Iterable that) {
/*  56 */     Iterator<Node> these = iterator();
/*  57 */     Iterator those = that.iterator();
/*  58 */     while (these.hasNext() && those.hasNext()) {
/*  59 */       if (((Equality)these.next()).xml_$bang$eq(those.next()))
/*  60 */         return false; 
/*     */     } 
/*  62 */     return !(these.hasNext() || those.hasNext());
/*     */   }
/*     */   
/*     */   public Seq<Object> basisForHashCode() {
/*  65 */     return (Seq)theSeq();
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object other) {
/*     */     boolean bool;
/*  67 */     if (other instanceof NodeSeq) {
/*  67 */       bool = true;
/*     */     } else {
/*  69 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   public boolean strict_$eq$eq(Equality other) {
/*     */     boolean bool;
/*  72 */     if (other instanceof NodeSeq) {
/*  72 */       NodeSeq nodeSeq = (NodeSeq)other;
/*  72 */       bool = (length() == nodeSeq.length() && theSeq().sameElements((GenIterable)nodeSeq.theSeq())) ? true : false;
/*     */     } else {
/*  74 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   private final Nothing$ fail$1(String that$1) {
/*  93 */     throw new IllegalArgumentException(that$1);
/*     */   }
/*     */   
/*     */   private final Node y$lzycompute$1(ObjectRef y$lzy$1, VolatileByteRef bitmap$0$1) {
/*  95 */     synchronized (this) {
/*  95 */       if ((byte)(bitmap$0$1.elem & 0x1) == 
/*     */         
/* 188 */         0) {
/*     */         y$lzy$1.elem = apply(0);
/*     */         bitmap$0$1.elem = (byte)(bitmap$0$1.elem | 0x1);
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/xml/NodeSeq}} */
/*     */       return (Node)y$lzy$1.elem;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final Node y$1(ObjectRef y$lzy$1, VolatileByteRef bitmap$0$1) {
/* 188 */     return ((byte)(bitmap$0$1.elem & 0x1) == 0) ? y$lzycompute$1(y$lzy$1, bitmap$0$1) : (Node)y$lzy$1.elem;
/*     */   }
/*     */   
/*     */   private final NodeSeq atResult$1(String that$1) {
/*     */     // Byte code:
/*     */     //   0: new scala/runtime/ObjectRef
/*     */     //   3: dup
/*     */     //   4: aconst_null
/*     */     //   5: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   8: astore #8
/*     */     //   10: new scala/runtime/VolatileByteRef
/*     */     //   13: dup
/*     */     //   14: iconst_0
/*     */     //   15: invokespecial <init> : (B)V
/*     */     //   18: astore #9
/*     */     //   20: aload_1
/*     */     //   21: invokevirtual length : ()I
/*     */     //   24: iconst_1
/*     */     //   25: if_icmpne -> 34
/*     */     //   28: aload_0
/*     */     //   29: aload_1
/*     */     //   30: invokespecial fail$1 : (Ljava/lang/String;)Lscala/runtime/Nothing$;
/*     */     //   33: athrow
/*     */     //   34: getstatic scala/collection/immutable/StringOps$.MODULE$ : Lscala/collection/immutable/StringOps$;
/*     */     //   37: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   40: astore_2
/*     */     //   41: aload_1
/*     */     //   42: iconst_1
/*     */     //   43: invokevirtual apply$extension : (Ljava/lang/String;I)C
/*     */     //   46: bipush #123
/*     */     //   48: if_icmpne -> 219
/*     */     //   51: aload_1
/*     */     //   52: bipush #125
/*     */     //   54: invokevirtual indexOf : (I)I
/*     */     //   57: istore_3
/*     */     //   58: iload_3
/*     */     //   59: iconst_m1
/*     */     //   60: if_icmpne -> 69
/*     */     //   63: aload_0
/*     */     //   64: aload_1
/*     */     //   65: invokespecial fail$1 : (Ljava/lang/String;)Lscala/runtime/Nothing$;
/*     */     //   68: athrow
/*     */     //   69: new scala/Tuple2
/*     */     //   72: dup
/*     */     //   73: aload_1
/*     */     //   74: iconst_2
/*     */     //   75: iload_3
/*     */     //   76: invokevirtual substring : (II)Ljava/lang/String;
/*     */     //   79: aload_1
/*     */     //   80: iload_3
/*     */     //   81: iconst_1
/*     */     //   82: iadd
/*     */     //   83: aload_1
/*     */     //   84: invokevirtual length : ()I
/*     */     //   87: invokevirtual substring : (II)Ljava/lang/String;
/*     */     //   90: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   93: astore #7
/*     */     //   95: aload #7
/*     */     //   97: ifnull -> 209
/*     */     //   100: new scala/Tuple2
/*     */     //   103: dup
/*     */     //   104: aload #7
/*     */     //   106: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   109: aload #7
/*     */     //   111: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   114: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   117: astore #4
/*     */     //   119: aload #4
/*     */     //   121: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   124: checkcast java/lang/String
/*     */     //   127: astore #5
/*     */     //   129: aload #4
/*     */     //   131: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   134: checkcast java/lang/String
/*     */     //   137: astore #6
/*     */     //   139: aload #5
/*     */     //   141: dup
/*     */     //   142: ifnonnull -> 154
/*     */     //   145: pop
/*     */     //   146: ldc ''
/*     */     //   148: ifnull -> 185
/*     */     //   151: goto -> 162
/*     */     //   154: ldc ''
/*     */     //   156: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   159: ifne -> 185
/*     */     //   162: aload #6
/*     */     //   164: dup
/*     */     //   165: ifnonnull -> 177
/*     */     //   168: pop
/*     */     //   169: ldc ''
/*     */     //   171: ifnull -> 185
/*     */     //   174: goto -> 191
/*     */     //   177: ldc ''
/*     */     //   179: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   182: ifeq -> 191
/*     */     //   185: aload_0
/*     */     //   186: aload_1
/*     */     //   187: invokespecial fail$1 : (Ljava/lang/String;)Lscala/runtime/Nothing$;
/*     */     //   190: athrow
/*     */     //   191: aload_0
/*     */     //   192: aload #8
/*     */     //   194: aload #9
/*     */     //   196: invokespecial y$1 : (Lscala/runtime/ObjectRef;Lscala/runtime/VolatileByteRef;)Lscala/xml/Node;
/*     */     //   199: aload #5
/*     */     //   201: aload #6
/*     */     //   203: invokevirtual attribute : (Ljava/lang/String;Ljava/lang/String;)Lscala/Option;
/*     */     //   206: goto -> 250
/*     */     //   209: new scala/MatchError
/*     */     //   212: dup
/*     */     //   213: aload #7
/*     */     //   215: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   218: athrow
/*     */     //   219: aload_0
/*     */     //   220: aload #8
/*     */     //   222: aload #9
/*     */     //   224: invokespecial y$1 : (Lscala/runtime/ObjectRef;Lscala/runtime/VolatileByteRef;)Lscala/xml/Node;
/*     */     //   227: new scala/collection/immutable/StringOps
/*     */     //   230: dup
/*     */     //   231: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   234: astore #10
/*     */     //   236: aload_1
/*     */     //   237: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   240: iconst_1
/*     */     //   241: invokevirtual drop : (I)Ljava/lang/Object;
/*     */     //   244: checkcast java/lang/String
/*     */     //   247: invokevirtual attribute : (Ljava/lang/String;)Lscala/Option;
/*     */     //   250: astore #11
/*     */     //   252: aload #11
/*     */     //   254: instanceof scala/Some
/*     */     //   257: ifeq -> 287
/*     */     //   260: aload #11
/*     */     //   262: checkcast scala/Some
/*     */     //   265: astore #12
/*     */     //   267: new scala/xml/Group
/*     */     //   270: dup
/*     */     //   271: aload #12
/*     */     //   273: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   276: checkcast scala/collection/Seq
/*     */     //   279: invokespecial <init> : (Lscala/collection/Seq;)V
/*     */     //   282: astore #13
/*     */     //   284: goto -> 295
/*     */     //   287: getstatic scala/xml/NodeSeq$.MODULE$ : Lscala/xml/NodeSeq$;
/*     */     //   290: invokevirtual Empty : ()Lscala/xml/NodeSeq;
/*     */     //   293: astore #13
/*     */     //   295: aload #13
/*     */     //   297: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #95	-> 8
/*     */     //   #188	-> 10
/*     */     //   #94	-> 18
/*     */     //   #97	-> 20
/*     */     //   #98	-> 37
/*     */     //   #99	-> 51
/*     */     //   #100	-> 58
/*     */     //   #101	-> 69
/*     */     //   #102	-> 139
/*     */     //   #103	-> 191
/*     */     //   #101	-> 209
/*     */     //   #105	-> 219
/*     */     //   #96	-> 250
/*     */     //   #108	-> 252
/*     */     //   #107	-> 252
/*     */     //   #107	-> 271
/*     */     //   #108	-> 273
/*     */     //   #109	-> 287
/*     */     //   #107	-> 295
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	298	0	this	Lscala/xml/NodeSeq;
/*     */     //   0	298	1	that$1	Ljava/lang/String;
/*     */     //   10	287	8	y$lzy	Lscala/runtime/ObjectRef;
/*     */     //   20	277	9	bitmap$0	Lscala/runtime/VolatileByteRef;
/*     */     //   58	240	3	i	I
/*     */     //   129	169	5	uri	Ljava/lang/String;
/*     */     //   139	159	6	key	Ljava/lang/String;
/*     */     //   252	45	11	attr	Lscala/Option;
/*     */   }
/*     */   
/*     */   private final NodeSeq makeSeq$1(Function1 cond) {
/*     */     return NodeSeq$.MODULE$.fromSeq((Seq<Node>)((TraversableLike)flatMap((Function1)new NodeSeq$$anonfun$makeSeq$1$1(this), NodeSeq$.MODULE$.canBuildFrom())).filter(cond));
/*     */   }
/*     */   
/*     */   public class NodeSeq$$anonfun$makeSeq$1$1 extends AbstractFunction1<Node, Seq<Node>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Seq<Node> apply(Node x$2) {
/*     */       return x$2.child();
/*     */     }
/*     */     
/*     */     public NodeSeq$$anonfun$makeSeq$1$1(NodeSeq $outer) {}
/*     */   }
/*     */   
/*     */   public NodeSeq $bslash(String that) {
/*     */     // Byte code:
/*     */     //   0: ldc ''
/*     */     //   2: dup
/*     */     //   3: ifnonnull -> 14
/*     */     //   6: pop
/*     */     //   7: aload_1
/*     */     //   8: ifnull -> 21
/*     */     //   11: goto -> 27
/*     */     //   14: aload_1
/*     */     //   15: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   18: ifeq -> 27
/*     */     //   21: aload_0
/*     */     //   22: aload_1
/*     */     //   23: invokespecial fail$1 : (Ljava/lang/String;)Lscala/runtime/Nothing$;
/*     */     //   26: athrow
/*     */     //   27: ldc '_'
/*     */     //   29: dup
/*     */     //   30: ifnonnull -> 41
/*     */     //   33: pop
/*     */     //   34: aload_1
/*     */     //   35: ifnull -> 48
/*     */     //   38: goto -> 64
/*     */     //   41: aload_1
/*     */     //   42: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   45: ifeq -> 64
/*     */     //   48: aload_0
/*     */     //   49: new scala/xml/NodeSeq$$anonfun$$bslash$1
/*     */     //   52: dup
/*     */     //   53: aload_0
/*     */     //   54: invokespecial <init> : (Lscala/xml/NodeSeq;)V
/*     */     //   57: invokespecial makeSeq$1 : (Lscala/Function1;)Lscala/xml/NodeSeq;
/*     */     //   60: astore_3
/*     */     //   61: goto -> 112
/*     */     //   64: getstatic scala/collection/immutable/StringOps$.MODULE$ : Lscala/collection/immutable/StringOps$;
/*     */     //   67: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   70: astore_2
/*     */     //   71: aload_1
/*     */     //   72: iconst_0
/*     */     //   73: invokevirtual apply$extension : (Ljava/lang/String;I)C
/*     */     //   76: bipush #64
/*     */     //   78: if_icmpne -> 98
/*     */     //   81: aload_0
/*     */     //   82: invokevirtual length : ()I
/*     */     //   85: iconst_1
/*     */     //   86: if_icmpne -> 98
/*     */     //   89: aload_0
/*     */     //   90: aload_1
/*     */     //   91: invokespecial atResult$1 : (Ljava/lang/String;)Lscala/xml/NodeSeq;
/*     */     //   94: astore_3
/*     */     //   95: goto -> 112
/*     */     //   98: aload_0
/*     */     //   99: new scala/xml/NodeSeq$$anonfun$$bslash$2
/*     */     //   102: dup
/*     */     //   103: aload_0
/*     */     //   104: aload_1
/*     */     //   105: invokespecial <init> : (Lscala/xml/NodeSeq;Ljava/lang/String;)V
/*     */     //   108: invokespecial makeSeq$1 : (Lscala/Function1;)Lscala/xml/NodeSeq;
/*     */     //   111: astore_3
/*     */     //   112: aload_3
/*     */     //   113: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #117	-> 0
/*     */     //   #116	-> 0
/*     */     //   #118	-> 27
/*     */     //   #119	-> 67
/*     */     //   #120	-> 98
/*     */     //   #116	-> 112
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	114	0	this	Lscala/xml/NodeSeq;
/*     */     //   0	114	1	that	Ljava/lang/String;
/*     */   }
/*     */   
/*     */   public class NodeSeq$$anonfun$$bslash$1 extends AbstractFunction1<Node, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Node x$3) {
/*     */       return !x$3.isAtom();
/*     */     }
/*     */     
/*     */     public NodeSeq$$anonfun$$bslash$1(NodeSeq $outer) {}
/*     */   }
/*     */   
/*     */   public class NodeSeq$$anonfun$$bslash$2 extends AbstractFunction1<Node, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String that$1;
/*     */     
/*     */     public final boolean apply(Node x$4) {
/*     */       String str = this.that$1;
/*     */       if (x$4.label() == null) {
/*     */         x$4.label();
/*     */         if (str != null);
/*     */       } else if (x$4.label().equals(str)) {
/*     */       
/*     */       } 
/*     */     }
/*     */     
/*     */     public NodeSeq$$anonfun$$bslash$2(NodeSeq $outer, String that$1) {}
/*     */   }
/*     */   
/*     */   private final NodeSeq filt$1(Function1 cond) {
/*     */     return (NodeSeq)((TraversableLike)flatMap((Function1)new NodeSeq$$anonfun$filt$1$1(this), NodeSeq$.MODULE$.canBuildFrom())).filter(cond);
/*     */   }
/*     */   
/*     */   public class NodeSeq$$anonfun$filt$1$1 extends AbstractFunction1<Node, List<Node>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<Node> apply(Node x$5) {
/*     */       return x$5.descendant_or_self();
/*     */     }
/*     */     
/*     */     public NodeSeq$$anonfun$filt$1$1(NodeSeq $outer) {}
/*     */   }
/*     */   
/*     */   public NodeSeq $bslash$bslash(String that) {
/*     */     // Byte code:
/*     */     //   0: ldc '_'
/*     */     //   2: dup
/*     */     //   3: ifnonnull -> 14
/*     */     //   6: pop
/*     */     //   7: aload_1
/*     */     //   8: ifnull -> 21
/*     */     //   11: goto -> 37
/*     */     //   14: aload_1
/*     */     //   15: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   18: ifeq -> 37
/*     */     //   21: aload_0
/*     */     //   22: new scala/xml/NodeSeq$$anonfun$$bslash$bslash$1
/*     */     //   25: dup
/*     */     //   26: aload_0
/*     */     //   27: invokespecial <init> : (Lscala/xml/NodeSeq;)V
/*     */     //   30: invokespecial filt$1 : (Lscala/Function1;)Lscala/xml/NodeSeq;
/*     */     //   33: astore_3
/*     */     //   34: goto -> 105
/*     */     //   37: getstatic scala/collection/immutable/StringOps$.MODULE$ : Lscala/collection/immutable/StringOps$;
/*     */     //   40: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   43: astore_2
/*     */     //   44: aload_1
/*     */     //   45: iconst_0
/*     */     //   46: invokevirtual apply$extension : (Ljava/lang/String;I)C
/*     */     //   49: bipush #64
/*     */     //   51: if_icmpne -> 91
/*     */     //   54: aload_0
/*     */     //   55: new scala/xml/NodeSeq$$anonfun$$bslash$bslash$2
/*     */     //   58: dup
/*     */     //   59: aload_0
/*     */     //   60: invokespecial <init> : (Lscala/xml/NodeSeq;)V
/*     */     //   63: invokespecial filt$1 : (Lscala/Function1;)Lscala/xml/NodeSeq;
/*     */     //   66: new scala/xml/NodeSeq$$anonfun$$bslash$bslash$3
/*     */     //   69: dup
/*     */     //   70: aload_0
/*     */     //   71: aload_1
/*     */     //   72: invokespecial <init> : (Lscala/xml/NodeSeq;Ljava/lang/String;)V
/*     */     //   75: getstatic scala/xml/NodeSeq$.MODULE$ : Lscala/xml/NodeSeq$;
/*     */     //   78: invokevirtual canBuildFrom : ()Lscala/collection/generic/CanBuildFrom;
/*     */     //   81: invokevirtual flatMap : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   84: checkcast scala/xml/NodeSeq
/*     */     //   87: astore_3
/*     */     //   88: goto -> 105
/*     */     //   91: aload_0
/*     */     //   92: new scala/xml/NodeSeq$$anonfun$$bslash$bslash$4
/*     */     //   95: dup
/*     */     //   96: aload_0
/*     */     //   97: aload_1
/*     */     //   98: invokespecial <init> : (Lscala/xml/NodeSeq;Ljava/lang/String;)V
/*     */     //   101: invokespecial filt$1 : (Lscala/Function1;)Lscala/xml/NodeSeq;
/*     */     //   104: astore_3
/*     */     //   105: aload_3
/*     */     //   106: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #142	-> 0
/*     */     //   #141	-> 0
/*     */     //   #143	-> 40
/*     */     //   #144	-> 91
/*     */     //   #141	-> 105
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	107	0	this	Lscala/xml/NodeSeq;
/*     */     //   0	107	1	that	Ljava/lang/String;
/*     */   }
/*     */   
/*     */   public class NodeSeq$$anonfun$$bslash$bslash$1 extends AbstractFunction1<Node, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Node x$6) {
/*     */       return !x$6.isAtom();
/*     */     }
/*     */     
/*     */     public NodeSeq$$anonfun$$bslash$bslash$1(NodeSeq $outer) {}
/*     */   }
/*     */   
/*     */   public class NodeSeq$$anonfun$$bslash$bslash$2 extends AbstractFunction1<Node, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Node x$7) {
/*     */       return !x$7.isAtom();
/*     */     }
/*     */     
/*     */     public NodeSeq$$anonfun$$bslash$bslash$2(NodeSeq $outer) {}
/*     */   }
/*     */   
/*     */   public class NodeSeq$$anonfun$$bslash$bslash$3 extends AbstractFunction1<Node, NodeSeq> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String that$2;
/*     */     
/*     */     public final NodeSeq apply(Node x$8) {
/*     */       return x$8.$bslash(this.that$2);
/*     */     }
/*     */     
/*     */     public NodeSeq$$anonfun$$bslash$bslash$3(NodeSeq $outer, String that$2) {}
/*     */   }
/*     */   
/*     */   public class NodeSeq$$anonfun$$bslash$bslash$4 extends AbstractFunction1<Node, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String that$2;
/*     */     
/*     */     public final boolean apply(Node x) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: invokevirtual isAtom : ()Z
/*     */       //   4: ifne -> 39
/*     */       //   7: aload_1
/*     */       //   8: invokevirtual label : ()Ljava/lang/String;
/*     */       //   11: aload_0
/*     */       //   12: getfield that$2 : Ljava/lang/String;
/*     */       //   15: astore_2
/*     */       //   16: dup
/*     */       //   17: ifnonnull -> 28
/*     */       //   20: pop
/*     */       //   21: aload_2
/*     */       //   22: ifnull -> 35
/*     */       //   25: goto -> 39
/*     */       //   28: aload_2
/*     */       //   29: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   32: ifeq -> 39
/*     */       //   35: iconst_1
/*     */       //   36: goto -> 40
/*     */       //   39: iconst_0
/*     */       //   40: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #144	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	41	0	this	Lscala/xml/NodeSeq$$anonfun$$bslash$bslash$4;
/*     */       //   0	41	1	x	Lscala/xml/Node;
/*     */     }
/*     */     
/*     */     public NodeSeq$$anonfun$$bslash$bslash$4(NodeSeq $outer, String that$2) {}
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     return theSeq().mkString();
/*     */   }
/*     */   
/*     */   public String text() {
/*     */     return ((TraversableOnce)map((Function1)new NodeSeq$$anonfun$text$1(this), Seq$.MODULE$.canBuildFrom())).mkString();
/*     */   }
/*     */   
/*     */   public static NodeSeq seqToNodeSeq(Seq<Node> paramSeq) {
/*     */     return NodeSeq$.MODULE$.seqToNodeSeq(paramSeq);
/*     */   }
/*     */   
/*     */   public static CanBuildFrom<NodeSeq, Node, NodeSeq> canBuildFrom() {
/*     */     return NodeSeq$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static NodeSeq fromSeq(Seq<Node> paramSeq) {
/*     */     return NodeSeq$.MODULE$.fromSeq(paramSeq);
/*     */   }
/*     */   
/*     */   public static NodeSeq Empty() {
/*     */     return NodeSeq$.MODULE$.Empty();
/*     */   }
/*     */   
/*     */   public abstract Seq<Node> theSeq();
/*     */   
/*     */   public class NodeSeq$$anonfun$text$1 extends AbstractFunction1<Node, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(Node x$9) {
/*     */       return x$9.text();
/*     */     }
/*     */     
/*     */     public NodeSeq$$anonfun$text$1(NodeSeq $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\NodeSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */