/*     */ package scala.xml;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple5;
/*     */ import scala.collection.Seq;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class Elem$ implements Serializable {
/*     */   public static final Elem$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/*  19 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Elem$() {
/*  19 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public Elem apply(String prefix, String label, MetaData attributes, NamespaceBinding scope, Seq<Node> child) {
/*  28 */     return apply(prefix, label, attributes, scope, child.isEmpty(), child);
/*     */   }
/*     */   
/*     */   public Elem apply(String prefix, String label, MetaData attributes, NamespaceBinding scope, boolean minimizeEmpty, Seq<Node> child) {
/*  31 */     return new Elem(prefix, label, attributes, scope, minimizeEmpty, child);
/*     */   }
/*     */   
/*     */   public Option<Tuple5<String, String, MetaData, NamespaceBinding, Seq<Node>>> unapplySeq(Node n) {
/*     */     boolean bool;
/*     */     Some some;
/*  33 */     if (n instanceof SpecialNode) {
/*  33 */       bool = true;
/*  33 */     } else if (n instanceof Group) {
/*  33 */       bool = true;
/*     */     } else {
/*  33 */       bool = false;
/*     */     } 
/*  33 */     if (bool) {
/*  33 */       scala.None$ none$ = scala.None$.MODULE$;
/*     */     } else {
/*  35 */       some = new Some(new Tuple5(n.prefix(), n.label(), n.attributes(), n.scope(), n.child()));
/*     */     } 
/*     */     return (Option<Tuple5<String, String, MetaData, NamespaceBinding, Seq<Node>>>)some;
/*     */   }
/*     */   
/*     */   public class Elem$$anonfun$text$1 extends AbstractFunction1<Node, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(Node x$4) {
/* 110 */       return x$4.text();
/*     */     }
/*     */     
/*     */     public Elem$$anonfun$text$1(Elem $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Elem$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */