/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class ChildNameReserved$ implements ChildStats, Product, Serializable {
/*    */   public static final ChildNameReserved$ MODULE$;
/*    */   
/*    */   public String productPrefix() {
/* 26 */     return "ChildNameReserved";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 26 */     return 0;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 26 */     int i = x$1;
/* 26 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 26 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 26 */     return x$1 instanceof ChildNameReserved$;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 26 */     return 1132097007;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 26 */     return "ChildNameReserved";
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 26 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ChildNameReserved$() {
/* 26 */     MODULE$ = this;
/* 26 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ChildNameReserved$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */