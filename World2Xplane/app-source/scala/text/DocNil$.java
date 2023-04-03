/*    */ package scala.text;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class DocNil$ extends Document implements Product, Serializable {
/*    */   public static final DocNil$ MODULE$;
/*    */   
/*    */   public String productPrefix() {
/* 13 */     return "DocNil";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 13 */     return 0;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 13 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 13 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 13 */     return x$1 instanceof DocNil$;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 13 */     return 2052320729;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 13 */     return "DocNil";
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 13 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private DocNil$() {
/* 13 */     MODULE$ = this;
/* 13 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\text\DocNil$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */