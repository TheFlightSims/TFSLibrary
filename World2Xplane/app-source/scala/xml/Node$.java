/*     */ package scala.xml;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class Node$ {
/*     */   public static final Node$ MODULE$;
/*     */   
/*     */   private final String EmptyNamespace;
/*     */   
/*     */   private Node$() {
/*  17 */     MODULE$ = this;
/*  22 */     this.EmptyNamespace = "";
/*     */   }
/*     */   
/*     */   public final MetaData NoAttributes() {
/*     */     return Null$.MODULE$;
/*     */   }
/*     */   
/*     */   public String EmptyNamespace() {
/*  22 */     return this.EmptyNamespace;
/*     */   }
/*     */   
/*     */   public Some<Tuple3<String, MetaData, Seq<Node>>> unapplySeq(Node n) {
/*  24 */     return new Some(new Tuple3(n.label(), n.attributes(), n.child()));
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
/*     */   public class Node$$anonfun$descendant$1 extends AbstractFunction1<Node, List<Node>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<Node> apply(Node x) {
/* 119 */       return x.descendant().$colon$colon(x);
/*     */     }
/*     */     
/*     */     public Node$$anonfun$descendant$1(Node $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Node$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */