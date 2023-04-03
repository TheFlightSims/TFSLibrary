/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.math.Ordering;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.Nothing$;
/*    */ 
/*    */ public final class Leaf$ implements AVLTree<Nothing$>, Product, Serializable {
/*    */   public static final Leaf$ MODULE$;
/*    */   
/*    */   private final int balance;
/*    */   
/*    */   private final int depth;
/*    */   
/*    */   public <B> Iterator<B> iterator() {
/* 68 */     return AVLTree$class.iterator(this);
/*    */   }
/*    */   
/*    */   public <B> boolean contains(Object value, Ordering ordering) {
/* 68 */     return AVLTree$class.contains(this, value, ordering);
/*    */   }
/*    */   
/*    */   public <B> AVLTree<B> insert(Object value, Ordering ordering) {
/* 68 */     return AVLTree$class.insert(this, value, ordering);
/*    */   }
/*    */   
/*    */   public <B> AVLTree<Nothing$> remove(Object value, Ordering ordering) {
/* 68 */     return AVLTree$class.remove(this, value, ordering);
/*    */   }
/*    */   
/*    */   public <B> Tuple2<B, AVLTree<B>> removeMin() {
/* 68 */     return AVLTree$class.removeMin(this);
/*    */   }
/*    */   
/*    */   public <B> Tuple2<B, AVLTree<B>> removeMax() {
/* 68 */     return AVLTree$class.removeMax(this);
/*    */   }
/*    */   
/*    */   public <B> AVLTree<B> rebalance() {
/* 68 */     return AVLTree$class.rebalance(this);
/*    */   }
/*    */   
/*    */   public <B> Node<B> leftRotation() {
/* 68 */     return AVLTree$class.leftRotation(this);
/*    */   }
/*    */   
/*    */   public <B> Node<B> rightRotation() {
/* 68 */     return AVLTree$class.rightRotation(this);
/*    */   }
/*    */   
/*    */   public <B> Node<B> doubleLeftRotation() {
/* 68 */     return AVLTree$class.doubleLeftRotation(this);
/*    */   }
/*    */   
/*    */   public <B> Node<B> doubleRightRotation() {
/* 68 */     return AVLTree$class.doubleRightRotation(this);
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 68 */     return "Leaf";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 68 */     return 0;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 68 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 68 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 68 */     return x$1 instanceof Leaf$;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 68 */     return 2364286;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 68 */     return "Leaf";
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 68 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Leaf$() {
/* 68 */     MODULE$ = this;
/* 68 */     AVLTree$class.$init$(this);
/* 68 */     Product.class.$init$(this);
/* 69 */     this.balance = 0;
/* 71 */     this.depth = -1;
/*    */   }
/*    */   
/*    */   public int balance() {
/*    */     return this.balance;
/*    */   }
/*    */   
/*    */   public int depth() {
/* 71 */     return this.depth;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Leaf$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */