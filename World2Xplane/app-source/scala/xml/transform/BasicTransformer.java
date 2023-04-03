/*    */ package scala.xml.transform;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.MatchError;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Seq$;
/*    */ import scala.collection.SeqLike;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.xml.Elem$;
/*    */ import scala.xml.Group;
/*    */ import scala.xml.Node;
/*    */ import scala.xml.NodeBuffer;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0313Q!\001\002\002\002%\021\001CQ1tS\016$&/\0318tM>\024X.\032:\013\005\r!\021!\003;sC:\034hm\034:n\025\t)a!A\002y[2T\021aB\001\006g\016\fG.Y\002\001'\r\001!B\004\t\003\0271i\021AB\005\003\033\031\021a!\0218z%\0264\007\003B\006\020#EI!\001\005\004\003\023\031+hn\031;j_:\f\004C\001\n\024\033\005!\021B\001\013\005\005\021qu\016Z3\t\013Y\001A\021A\f\002\rqJg.\033;?)\005A\002CA\r\001\033\005\021\001\"B\016\001\t#a\022!C;oG\"\fgnZ3e)\ri\002E\t\t\003\027yI!a\b\004\003\017\t{w\016\\3b]\")\021E\007a\001#\005\ta\016C\003$5\001\007A%\001\002ogB\031Q%L\t\017\005\031ZcBA\024+\033\005A#BA\025\t\003\031a$o\\8u}%\tq!\003\002-\r\0059\001/Y2lC\036,\027B\001\0300\005\r\031V-\035\006\003Y\031AQa\001\001\005\002E\"2\001\n\0328\021\025\031\004\0071\0015\003\tIG\017E\002&kEI!AN\030\003\021%#XM]1u_JDQ\001\017\031A\002e\n!A\0342\021\005IQ\024BA\036\005\005)qu\016Z3Ck\0324WM\035\005\006\007\001!\t!\020\013\003IyBQa\t\037A\002\021BQa\001\001\005\002\001#\"\001J!\t\013\005z\004\031A\t\t\013\r\003A\021\001#\002\013\005\004\b\017\\=\025\005E)\005\"B\021C\001\004\t\002")
/*    */ public abstract class BasicTransformer implements Function1<Node, Node> {
/*    */   public boolean apply$mcZD$sp(double v1) {
/* 19 */     return Function1.class.apply$mcZD$sp(this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDD$sp(double v1) {
/* 19 */     return Function1.class.apply$mcDD$sp(this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFD$sp(double v1) {
/* 19 */     return Function1.class.apply$mcFD$sp(this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcID$sp(double v1) {
/* 19 */     return Function1.class.apply$mcID$sp(this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJD$sp(double v1) {
/* 19 */     return Function1.class.apply$mcJD$sp(this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVD$sp(double v1) {
/* 19 */     Function1.class.apply$mcVD$sp(this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZF$sp(float v1) {
/* 19 */     return Function1.class.apply$mcZF$sp(this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDF$sp(float v1) {
/* 19 */     return Function1.class.apply$mcDF$sp(this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFF$sp(float v1) {
/* 19 */     return Function1.class.apply$mcFF$sp(this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcIF$sp(float v1) {
/* 19 */     return Function1.class.apply$mcIF$sp(this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJF$sp(float v1) {
/* 19 */     return Function1.class.apply$mcJF$sp(this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVF$sp(float v1) {
/* 19 */     Function1.class.apply$mcVF$sp(this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZI$sp(int v1) {
/* 19 */     return Function1.class.apply$mcZI$sp(this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDI$sp(int v1) {
/* 19 */     return Function1.class.apply$mcDI$sp(this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFI$sp(int v1) {
/* 19 */     return Function1.class.apply$mcFI$sp(this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcII$sp(int v1) {
/* 19 */     return Function1.class.apply$mcII$sp(this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJI$sp(int v1) {
/* 19 */     return Function1.class.apply$mcJI$sp(this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVI$sp(int v1) {
/* 19 */     Function1.class.apply$mcVI$sp(this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZJ$sp(long v1) {
/* 19 */     return Function1.class.apply$mcZJ$sp(this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDJ$sp(long v1) {
/* 19 */     return Function1.class.apply$mcDJ$sp(this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFJ$sp(long v1) {
/* 19 */     return Function1.class.apply$mcFJ$sp(this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcIJ$sp(long v1) {
/* 19 */     return Function1.class.apply$mcIJ$sp(this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJJ$sp(long v1) {
/* 19 */     return Function1.class.apply$mcJJ$sp(this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVJ$sp(long v1) {
/* 19 */     Function1.class.apply$mcVJ$sp(this, v1);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Node> compose(Function1 g) {
/* 19 */     return Function1.class.compose(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcZD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcDD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcFD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcID$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcJD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcVD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcZF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcDF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcFF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcIF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcJF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcVF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcZI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcDI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcFI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcII$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcJI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcVI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcZJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcDJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcFJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcIJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcJJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 19 */     return Function1.class.compose$mcVJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Node, A> andThen(Function1 g) {
/* 19 */     return Function1.class.andThen(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcZD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcDD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcFD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcID$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcJD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcVD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcZF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcDF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcFF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcIF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcJF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcVF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcZI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcDI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcFI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcII$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcJI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcVI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcZJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcDJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcFJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcIJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcJJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 19 */     return Function1.class.andThen$mcVJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 19 */     return Function1.class.toString(this);
/*    */   }
/*    */   
/*    */   public BasicTransformer() {
/* 19 */     Function1.class.$init$(this);
/*    */   }
/*    */   
/*    */   public boolean unchanged(Node n, Seq ns) {
/*    */     // Byte code:
/*    */     //   0: aload_2
/*    */     //   1: invokeinterface length : ()I
/*    */     //   6: iconst_1
/*    */     //   7: if_icmpne -> 39
/*    */     //   10: aload_2
/*    */     //   11: invokeinterface head : ()Ljava/lang/Object;
/*    */     //   16: dup
/*    */     //   17: ifnonnull -> 28
/*    */     //   20: pop
/*    */     //   21: aload_1
/*    */     //   22: ifnull -> 35
/*    */     //   25: goto -> 39
/*    */     //   28: aload_1
/*    */     //   29: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   32: ifeq -> 39
/*    */     //   35: iconst_1
/*    */     //   36: goto -> 40
/*    */     //   39: iconst_0
/*    */     //   40: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #22	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	41	0	this	Lscala/xml/transform/BasicTransformer;
/*    */     //   0	41	1	n	Lscala/xml/Node;
/*    */     //   0	41	2	ns	Lscala/collection/Seq;
/*    */   }
/*    */   
/*    */   public Seq<Node> transform(Iterator it, NodeBuffer nb) {
/* 28 */     return ((SeqLike)it.foldLeft(nb, (Function2)new BasicTransformer$$anonfun$transform$1(this))).toSeq();
/*    */   }
/*    */   
/*    */   public class BasicTransformer$$anonfun$transform$1 extends AbstractFunction2<NodeBuffer, Node, NodeBuffer> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final NodeBuffer apply(NodeBuffer x$1, Node x$2) {
/* 28 */       return (NodeBuffer)x$1.$plus$plus$eq((TraversableOnce)this.$outer.transform(x$2));
/*    */     }
/*    */     
/*    */     public BasicTransformer$$anonfun$transform$1(BasicTransformer $outer) {}
/*    */   }
/*    */   
/*    */   public Seq<Node> transform(Seq<Node> ns) {
/* 34 */     Tuple2 tuple2 = ns.span((Function1)new $anonfun$1(this));
/* 34 */     if (tuple2 != null) {
/* 34 */       Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 34 */       Seq xs1 = (Seq)tuple21._1(), xs2 = (Seq)tuple21._2();
/* 36 */       return xs2.isEmpty() ? ns : 
/* 37 */         (Seq<Node>)((TraversableLike)xs1.$plus$plus((GenTraversableOnce)transform((Node)xs2.head()), Seq$.MODULE$.canBuildFrom())).$plus$plus((GenTraversableOnce)transform((Seq<Node>)xs2.tail()), Seq$.MODULE$.canBuildFrom());
/*    */     } 
/*    */     throw new MatchError(tuple2);
/*    */   }
/*    */   
/*    */   public class $anonfun$1 extends AbstractFunction1<Node, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(Node n) {
/*    */       return this.$outer.unchanged(n, this.$outer.transform(n));
/*    */     }
/*    */     
/*    */     public $anonfun$1(BasicTransformer $outer) {}
/*    */   }
/*    */   
/*    */   public Seq<Node> transform(Node n) {
/*    */     Object object;
/* 41 */     if (n.doTransform()) {
/* 42 */       if (n instanceof Group)
/* 42 */         Group group1 = (Group)n, group2 = new Group(transform(group1.nodes())); 
/* 44 */       Seq<Node> ch = n.child();
/* 45 */       Seq<Node> nch = transform(ch);
/* 47 */       object = (ch == nch) ? n : 
/* 48 */         Elem$.MODULE$.apply(n.prefix(), n.label(), n.attributes(), n.scope(), nch);
/*    */     } else {
/* 50 */       return (Seq<Node>)n;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Node apply(Node n) {
/* 54 */     Seq<Node> seq = transform(n);
/* 55 */     if (seq.length() > 1)
/* 56 */       throw new UnsupportedOperationException("transform must return single node for root"); 
/* 57 */     return (Node)seq.head();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\transform\BasicTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */