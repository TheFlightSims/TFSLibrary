/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import java.util.NoSuchElementException;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Iterator$;
/*    */ import scala.math.Ordering;
/*    */ import scala.sys.package$;
/*    */ 
/*    */ public abstract class AVLTree$class {
/*    */   public static void $init$(AVLTree $this) {}
/*    */   
/*    */   public static Iterator iterator(AVLTree $this) {
/* 25 */     return Iterator$.MODULE$.empty();
/*    */   }
/*    */   
/*    */   public static boolean contains(AVLTree $this, Object value, Ordering ordering) {
/* 27 */     return false;
/*    */   }
/*    */   
/*    */   public static AVLTree insert(AVLTree $this, Object value, Ordering ordering) {
/* 34 */     return new Node(value, Leaf$.MODULE$, Leaf$.MODULE$);
/*    */   }
/*    */   
/*    */   public static AVLTree remove(AVLTree $this, Object value, Ordering ordering) {
/* 41 */     throw new NoSuchElementException(String.valueOf(value));
/*    */   }
/*    */   
/*    */   public static Tuple2 removeMin(AVLTree $this) {
/* 48 */     throw package$.MODULE$.error("Should not happen.");
/*    */   }
/*    */   
/*    */   public static Tuple2 removeMax(AVLTree $this) {
/* 55 */     throw package$.MODULE$.error("Should not happen.");
/*    */   }
/*    */   
/*    */   public static AVLTree rebalance(AVLTree $this) {
/* 57 */     return $this;
/*    */   }
/*    */   
/*    */   public static Node leftRotation(AVLTree $this) {
/* 59 */     throw package$.MODULE$.error("Should not happen.");
/*    */   }
/*    */   
/*    */   public static Node rightRotation(AVLTree $this) {
/* 61 */     throw package$.MODULE$.error("Should not happen.");
/*    */   }
/*    */   
/*    */   public static Node doubleLeftRotation(AVLTree $this) {
/* 63 */     throw package$.MODULE$.error("Should not happen.");
/*    */   }
/*    */   
/*    */   public static Node doubleRightRotation(AVLTree $this) {
/* 65 */     throw package$.MODULE$.error("Should not happen.");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\AVLTree$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */