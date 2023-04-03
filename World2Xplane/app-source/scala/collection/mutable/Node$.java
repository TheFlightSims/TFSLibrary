/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple3;
/*    */ 
/*    */ public final class Node$ implements Serializable {
/*    */   public static final Node$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 74 */     return "Node";
/*    */   }
/*    */   
/*    */   public <A> Node<A> apply(Object data, AVLTree<A> left, AVLTree<A> right) {
/* 74 */     return new Node<A>((A)data, left, right);
/*    */   }
/*    */   
/*    */   public <A> Option<Tuple3<A, AVLTree<A>, AVLTree<A>>> unapply(Node x$0) {
/* 74 */     return (x$0 == null) ? (Option<Tuple3<A, AVLTree<A>, AVLTree<A>>>)scala.None$.MODULE$ : (Option<Tuple3<A, AVLTree<A>, AVLTree<A>>>)new Some(new Tuple3(x$0.data(), x$0.left(), x$0.right()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 74 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Node$() {
/* 74 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Node$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */