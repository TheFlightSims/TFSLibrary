/*    */ package scala.util.grammar;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class EmptyHedgeRHS$ extends HedgeRHS implements Product, Serializable {
/*    */   public static final EmptyHedgeRHS$ MODULE$;
/*    */   
/*    */   public String productPrefix() {
/* 26 */     return "EmptyHedgeRHS";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 26 */     return 0;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 26 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 26 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 26 */     return x$1 instanceof EmptyHedgeRHS$;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 26 */     return 1078677733;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 26 */     return "EmptyHedgeRHS";
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 26 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private EmptyHedgeRHS$() {
/* 26 */     MODULE$ = this;
/* 26 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\grammar\EmptyHedgeRHS$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */