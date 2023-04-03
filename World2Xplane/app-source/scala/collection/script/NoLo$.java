/*    */ package scala.collection.script;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class NoLo$ extends Location implements Product, Serializable {
/*    */   public static final NoLo$ MODULE$;
/*    */   
/*    */   public String productPrefix() {
/* 23 */     return "NoLo";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 23 */     return 0;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 23 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 23 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 23 */     return x$1 instanceof NoLo$;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 23 */     return 2432836;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 23 */     return "NoLo";
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 23 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private NoLo$() {
/* 23 */     MODULE$ = this;
/* 23 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\script\NoLo$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */