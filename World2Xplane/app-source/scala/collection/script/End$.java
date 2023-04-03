/*    */ package scala.collection.script;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class End$ extends Location implements Product, Serializable {
/*    */   public static final End$ MODULE$;
/*    */   
/*    */   public String productPrefix() {
/* 22 */     return "End";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 22 */     return 0;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 22 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 22 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 22 */     return x$1 instanceof End$;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 22 */     return 69819;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 22 */     return "End";
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 22 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private End$() {
/* 22 */     MODULE$ = this;
/* 22 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\script\End$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */