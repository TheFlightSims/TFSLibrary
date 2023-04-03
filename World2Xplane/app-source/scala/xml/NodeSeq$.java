/*     */ package scala.xml;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.ListBuffer;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class NodeSeq$ {
/*     */   public static final NodeSeq$ MODULE$;
/*     */   
/*     */   private final NodeSeq Empty;
/*     */   
/*     */   private NodeSeq$() {
/*  21 */     MODULE$ = this;
/*  22 */     this.Empty = fromSeq((Seq<Node>)scala.collection.immutable.Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   public final NodeSeq Empty() {
/*  22 */     return this.Empty;
/*     */   }
/*     */   
/*     */   public NodeSeq fromSeq(Seq s) {
/*  23 */     return new NodeSeq$$anon$1(s);
/*     */   }
/*     */   
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
/*     */   public CanBuildFrom<NodeSeq, Node, NodeSeq> canBuildFrom() {
/*  28 */     return new NodeSeq$$anon$2();
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
/*     */   public Builder<Node, NodeSeq> newBuilder() {
/*  32 */     return Builder.class.mapResult((Builder)new ListBuffer(), (Function1)new NodeSeq$$anonfun$newBuilder$1());
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
/*     */   public NodeSeq seqToNodeSeq(Seq<Node> s) {
/*  33 */     return fromSeq(s);
/*     */   }
/*     */   
/*     */   public class NodeSeq$$anonfun$makeSeq$1$1 extends AbstractFunction1<Node, Seq<Node>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Seq<Node> apply(Node x$2) {
/* 114 */       return x$2.child();
/*     */     }
/*     */     
/*     */     public NodeSeq$$anonfun$makeSeq$1$1(NodeSeq $outer) {}
/*     */   }
/*     */   
/*     */   public class NodeSeq$$anonfun$$bslash$1 extends AbstractFunction1<Node, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Node x$3) {
/* 118 */       return !x$3.isAtom();
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
/* 120 */       String str = this.that$1;
/* 120 */       if (x$4.label() == null) {
/* 120 */         x$4.label();
/* 120 */         if (str != null);
/* 120 */       } else if (x$4.label().equals(str)) {
/*     */       
/*     */       } 
/*     */     }
/*     */     
/*     */     public NodeSeq$$anonfun$$bslash$2(NodeSeq $outer, String that$1) {}
/*     */   }
/*     */   
/*     */   public class NodeSeq$$anonfun$filt$1$1 extends AbstractFunction1<Node, List<Node>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<Node> apply(Node x$5) {
/* 140 */       return x$5.descendant_or_self();
/*     */     }
/*     */     
/*     */     public NodeSeq$$anonfun$filt$1$1(NodeSeq $outer) {}
/*     */   }
/*     */   
/*     */   public class NodeSeq$$anonfun$$bslash$bslash$1 extends AbstractFunction1<Node, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Node x$6) {
/* 142 */       return !x$6.isAtom();
/*     */     }
/*     */     
/*     */     public NodeSeq$$anonfun$$bslash$bslash$1(NodeSeq $outer) {}
/*     */   }
/*     */   
/*     */   public class NodeSeq$$anonfun$$bslash$bslash$2 extends AbstractFunction1<Node, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Node x$7) {
/* 143 */       return !x$7.isAtom();
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
/* 143 */       return x$8.$bslash(this.that$2);
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
/*     */   public class NodeSeq$$anonfun$text$1 extends AbstractFunction1<Node, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(Node x$9) {
/* 150 */       return x$9.text();
/*     */     }
/*     */     
/*     */     public NodeSeq$$anonfun$text$1(NodeSeq $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\NodeSeq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */