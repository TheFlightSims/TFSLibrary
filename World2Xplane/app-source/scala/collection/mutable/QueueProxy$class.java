/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableOnce;
/*    */ 
/*    */ public abstract class QueueProxy$class {
/*    */   public static void $init$(QueueProxy $this) {}
/*    */   
/*    */   public static Object apply(QueueProxy $this, int n) {
/* 31 */     return $this.self().apply(n);
/*    */   }
/*    */   
/*    */   public static int length(QueueProxy $this) {
/* 35 */     return $this.self().length();
/*    */   }
/*    */   
/*    */   public static boolean isEmpty(QueueProxy $this) {
/* 41 */     return $this.self().isEmpty();
/*    */   }
/*    */   
/*    */   public static QueueProxy $plus$eq(QueueProxy<Object> $this, Object elem) {
/* 47 */     $this.self().$plus$eq(elem);
/* 47 */     return $this;
/*    */   }
/*    */   
/*    */   public static QueueProxy $plus$plus$eq(QueueProxy $this, TraversableOnce it) {
/* 55 */     $this.self().$plus$plus$eq(it);
/* 56 */     return $this;
/*    */   }
/*    */   
/*    */   public static void enqueue(QueueProxy $this, Seq elems) {
/* 63 */     $this.self().$plus$plus$eq((TraversableOnce)elems);
/*    */   }
/*    */   
/*    */   public static Object dequeue(QueueProxy $this) {
/* 70 */     return $this.self().dequeue();
/*    */   }
/*    */   
/*    */   public static Object front(QueueProxy $this) {
/* 77 */     return $this.self().front();
/*    */   }
/*    */   
/*    */   public static void clear(QueueProxy $this) {
/* 82 */     $this.self().clear();
/*    */   }
/*    */   
/*    */   public static Iterator iterator(QueueProxy $this) {
/* 88 */     return $this.self().iterator();
/*    */   }
/*    */   
/*    */   public static Queue clone(QueueProxy<A> $this) {
/* 94 */     return new QueueProxy$$anon$1($this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\QueueProxy$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */