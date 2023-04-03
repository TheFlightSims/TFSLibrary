/*    */ package scala.text;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class DocBreak$ extends Document implements Product, Serializable {
/*    */   public static final DocBreak$ MODULE$;
/*    */   
/*    */   public String productPrefix() {
/* 14 */     return "DocBreak";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 14 */     return 0;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 14 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 14 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 14 */     return x$1 instanceof DocBreak$;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 14 */     return 879413959;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 14 */     return "DocBreak";
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 14 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private DocBreak$() {
/* 14 */     MODULE$ = this;
/* 14 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\text\DocBreak$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */