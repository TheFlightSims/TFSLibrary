/*    */ package scala.collection.script;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class Start$ extends Location implements Product, Serializable {
/*    */   public static final Start$ MODULE$;
/*    */   
/*    */   public String productPrefix() {
/* 21 */     return "Start";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 21 */     return 0;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 21 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 21 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 21 */     return x$1 instanceof Start$;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 21 */     return 80204866;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 21 */     return "Start";
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 21 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Start$() {
/* 21 */     MODULE$ = this;
/* 21 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\script\Start$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */