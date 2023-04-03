/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.collection.IterableLike;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class Xhtml$ {
/*    */   public static final Xhtml$ MODULE$;
/*    */   
/*    */   private final List<String> minimizableElements;
/*    */   
/*    */   private Xhtml$() {
/* 10 */     MODULE$ = this;
/* 30 */     (new String[10])[0] = "base";
/* 30 */     (new String[10])[1] = "meta";
/* 30 */     (new String[10])[2] = "link";
/* 30 */     (new String[10])[3] = "hr";
/* 30 */     (new String[10])[4] = "br";
/* 30 */     (new String[10])[5] = "param";
/* 30 */     (new String[10])[6] = "img";
/* 30 */     (new String[10])[7] = "area";
/* 30 */     (new String[10])[8] = "input";
/* 30 */     (new String[10])[9] = "col";
/* 30 */     this.minimizableElements = scala.collection.immutable.List$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new String[10]));
/*    */   }
/*    */   
/*    */   public String toXhtml(Node node) {
/*    */     return Utility$.MODULE$.sbToString((Function1<StringBuilder, BoxedUnit>)new Xhtml$$anonfun$toXhtml$1(node));
/*    */   }
/*    */   
/*    */   public static class Xhtml$$anonfun$toXhtml$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Node node$1;
/*    */     
/*    */     public final void apply(StringBuilder sb) {
/*    */       Node x$2 = this.node$1;
/*    */       NamespaceBinding x$4 = Xhtml$.MODULE$.toXhtml$default$2();
/*    */       boolean x$5 = Xhtml$.MODULE$.toXhtml$default$4(), x$6 = Xhtml$.MODULE$.toXhtml$default$5(), x$7 = Xhtml$.MODULE$.toXhtml$default$6(), x$8 = Xhtml$.MODULE$.toXhtml$default$7();
/*    */       Xhtml$.MODULE$.toXhtml(x$2, x$4, sb, x$5, x$6, x$7, x$8);
/*    */     }
/*    */     
/*    */     public Xhtml$$anonfun$toXhtml$1(Node node$1) {}
/*    */   }
/*    */   
/*    */   public String toXhtml(NodeSeq nodeSeq) {
/*    */     return Utility$.MODULE$.sbToString((Function1<StringBuilder, BoxedUnit>)new Xhtml$$anonfun$toXhtml$2(nodeSeq));
/*    */   }
/*    */   
/*    */   public static class Xhtml$$anonfun$toXhtml$2 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final NodeSeq nodeSeq$1;
/*    */     
/*    */     public final void apply(StringBuilder sb) {
/*    */       NodeSeq nodeSeq = this.nodeSeq$1;
/*    */       NamespaceBinding x$11 = Xhtml$.MODULE$.sequenceToXML$default$2();
/*    */       boolean x$12 = Xhtml$.MODULE$.sequenceToXML$default$4(), x$13 = Xhtml$.MODULE$.sequenceToXML$default$5(), x$14 = Xhtml$.MODULE$.sequenceToXML$default$6(), x$15 = Xhtml$.MODULE$.sequenceToXML$default$7();
/*    */       Xhtml$.MODULE$.sequenceToXML((Seq<Node>)nodeSeq, x$11, sb, x$12, x$13, x$14, x$15);
/*    */     }
/*    */     
/*    */     public Xhtml$$anonfun$toXhtml$2(NodeSeq nodeSeq$1) {}
/*    */   }
/*    */   
/*    */   private List<String> minimizableElements() {
/*    */     return this.minimizableElements;
/*    */   }
/*    */   
/*    */   public NamespaceBinding toXhtml$default$2() {
/* 34 */     return TopScope$.MODULE$;
/*    */   }
/*    */   
/*    */   public StringBuilder toXhtml$default$3() {
/* 35 */     return new StringBuilder();
/*    */   }
/*    */   
/*    */   public boolean toXhtml$default$4() {
/* 36 */     return false;
/*    */   }
/*    */   
/*    */   public boolean toXhtml$default$5() {
/* 37 */     return false;
/*    */   }
/*    */   
/*    */   public boolean toXhtml$default$6() {
/* 38 */     return false;
/*    */   }
/*    */   
/*    */   public boolean toXhtml$default$7() {
/* 39 */     return true;
/*    */   }
/*    */   
/*    */   private final StringBuilder decode$1(EntityRef er, StringBuilder sb$1) {
/*    */     // Byte code:
/*    */     //   0: getstatic scala/xml/parsing/XhtmlEntities$.MODULE$ : Lscala/xml/parsing/XhtmlEntities$;
/*    */     //   3: invokevirtual entMap : ()Lscala/collection/immutable/Map;
/*    */     //   6: aload_1
/*    */     //   7: invokevirtual entityName : ()Ljava/lang/String;
/*    */     //   10: invokeinterface get : (Ljava/lang/Object;)Lscala/Option;
/*    */     //   15: astore_3
/*    */     //   16: aload_3
/*    */     //   17: instanceof scala/Some
/*    */     //   20: ifeq -> 60
/*    */     //   23: aload_3
/*    */     //   24: checkcast scala/Some
/*    */     //   27: astore #4
/*    */     //   29: aload #4
/*    */     //   31: invokevirtual x : ()Ljava/lang/Object;
/*    */     //   34: invokestatic unboxToChar : (Ljava/lang/Object;)C
/*    */     //   37: sipush #128
/*    */     //   40: if_icmplt -> 60
/*    */     //   43: aload_2
/*    */     //   44: aload #4
/*    */     //   46: invokevirtual x : ()Ljava/lang/Object;
/*    */     //   49: invokestatic unboxToChar : (Ljava/lang/Object;)C
/*    */     //   52: invokevirtual append : (C)Lscala/collection/mutable/StringBuilder;
/*    */     //   55: astore #5
/*    */     //   57: goto -> 67
/*    */     //   60: aload_1
/*    */     //   61: aload_2
/*    */     //   62: invokevirtual buildString : (Lscala/collection/mutable/StringBuilder;)Lscala/collection/mutable/StringBuilder;
/*    */     //   65: astore #5
/*    */     //   67: aload #5
/*    */     //   69: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #41	-> 0
/*    */     //   #42	-> 16
/*    */     //   #41	-> 29
/*    */     //   #42	-> 31
/*    */     //   #41	-> 44
/*    */     //   #42	-> 46
/*    */     //   #43	-> 60
/*    */     //   #41	-> 67
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	70	0	this	Lscala/xml/Xhtml$;
/*    */     //   0	70	1	er	Lscala/xml/EntityRef;
/*    */     //   0	70	2	sb$1	Lscala/collection/mutable/StringBuilder;
/*    */   }
/*    */   
/*    */   private final boolean shortForm$1(Node x$16, boolean minimizeTags$1) {
/* 46 */     return (minimizeTags$1 && (
/* 47 */       x$16.child() == null || x$16.child().length() == 0) && 
/* 48 */       minimizableElements().contains(x$16.label()));
/*    */   }
/*    */   
/*    */   public void toXhtml(Node x, NamespaceBinding pscope, StringBuilder sb, boolean stripComments, boolean decodeEntities, boolean preserveWhitespace, boolean minimizeTags) {
/* 50 */     if (x instanceof Comment) {
/* 50 */       Comment comment = (Comment)x;
/* 50 */       if (!stripComments)
/* 50 */         comment.buildString(sb); 
/*    */     } else {
/* 52 */       if (x instanceof EntityRef) {
/* 52 */         EntityRef entityRef = (EntityRef)x;
/* 52 */         if (decodeEntities) {
/* 52 */           decode$1(entityRef, sb);
/*    */           return;
/*    */         } 
/*    */       } 
/* 53 */       if (x instanceof SpecialNode) {
/* 53 */         SpecialNode specialNode = (SpecialNode)x;
/* 53 */         specialNode.buildString(sb);
/* 54 */       } else if (x instanceof Group) {
/* 54 */         Group group = (Group)x;
/* 55 */         group.nodes().foreach((Function1)new Xhtml$$anonfun$toXhtml$3(x, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags));
/*    */       } else {
/* 58 */         sb.append('<');
/* 59 */         x.nameToString(sb);
/* 60 */         (x.attributes() != null) ? x.attributes().buildString(sb) : BoxedUnit.UNIT;
/* 61 */         x.scope().buildString(sb, pscope);
/* 63 */         if (shortForm$1(x, minimizeTags)) {
/* 63 */           sb.append(" />");
/*    */         } else {
/* 65 */           sb.append('>');
/* 66 */           sequenceToXML(x.child(), x.scope(), sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags);
/* 67 */           sb.append("</");
/* 68 */           x.nameToString(sb);
/* 69 */           sb.append('>');
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Xhtml$$anonfun$toXhtml$3 extends AbstractFunction1<Node, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Node x$16;
/*    */     
/*    */     private final StringBuilder sb$1;
/*    */     
/*    */     private final boolean stripComments$1;
/*    */     
/*    */     private final boolean decodeEntities$1;
/*    */     
/*    */     private final boolean preserveWhitespace$1;
/*    */     
/*    */     private final boolean minimizeTags$1;
/*    */     
/*    */     public final void apply(Node x$1) {
/*    */       Xhtml$.MODULE$.toXhtml(x$1, this.x$16.scope(), this.sb$1, this.stripComments$1, this.decodeEntities$1, this.preserveWhitespace$1, this.minimizeTags$1);
/*    */     }
/*    */     
/*    */     public Xhtml$$anonfun$toXhtml$3(Node x$16, StringBuilder sb$1, boolean stripComments$1, boolean decodeEntities$1, boolean preserveWhitespace$1, boolean minimizeTags$1) {}
/*    */   }
/*    */   
/*    */   public NamespaceBinding sequenceToXML$default$2() {
/* 79 */     return TopScope$.MODULE$;
/*    */   }
/*    */   
/*    */   public StringBuilder sequenceToXML$default$3() {
/* 80 */     return new StringBuilder();
/*    */   }
/*    */   
/*    */   public boolean sequenceToXML$default$4() {
/* 81 */     return false;
/*    */   }
/*    */   
/*    */   public boolean sequenceToXML$default$5() {
/* 82 */     return false;
/*    */   }
/*    */   
/*    */   public boolean sequenceToXML$default$6() {
/* 83 */     return false;
/*    */   }
/*    */   
/*    */   public boolean sequenceToXML$default$7() {
/* 84 */     return true;
/*    */   }
/*    */   
/*    */   public void sequenceToXML(Seq children, NamespaceBinding pscope, StringBuilder sb, boolean stripComments, boolean decodeEntities, boolean preserveWhitespace, boolean minimizeTags) {
/* 86 */     if (children.isEmpty())
/*    */       return; 
/* 89 */     boolean doSpaces = children.forall((Function1)new Xhtml$$anonfun$1());
/* 90 */     ((IterableLike)children.take(children.length() - 1)).foreach((Function1)new Xhtml$$anonfun$sequenceToXML$1(pscope, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags, doSpaces));
/* 94 */     toXhtml((Node)children.last(), pscope, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags);
/*    */   }
/*    */   
/*    */   public static class Xhtml$$anonfun$1 extends AbstractFunction1<Node, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(Node x) {
/*    */       return Utility$.MODULE$.isAtomAndNotText(x);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Xhtml$$anonfun$sequenceToXML$1 extends AbstractFunction1<Node, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final NamespaceBinding pscope$1;
/*    */     
/*    */     private final StringBuilder sb$2;
/*    */     
/*    */     private final boolean stripComments$2;
/*    */     
/*    */     private final boolean decodeEntities$2;
/*    */     
/*    */     private final boolean preserveWhitespace$2;
/*    */     
/*    */     private final boolean minimizeTags$2;
/*    */     
/*    */     private final boolean doSpaces$1;
/*    */     
/*    */     public Xhtml$$anonfun$sequenceToXML$1(NamespaceBinding pscope$1, StringBuilder sb$2, boolean stripComments$2, boolean decodeEntities$2, boolean preserveWhitespace$2, boolean minimizeTags$2, boolean doSpaces$1) {}
/*    */     
/*    */     public final Object apply(Node c) {
/*    */       Xhtml$.MODULE$.toXhtml(c, this.pscope$1, this.sb$2, this.stripComments$2, this.decodeEntities$2, this.preserveWhitespace$2, this.minimizeTags$2);
/*    */       return this.doSpaces$1 ? this.sb$2.append(' ') : BoxedUnit.UNIT;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Xhtml$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */