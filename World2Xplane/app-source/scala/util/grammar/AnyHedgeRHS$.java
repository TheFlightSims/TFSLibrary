/*    */ package scala.util.grammar;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class AnyHedgeRHS$ extends HedgeRHS implements Product, Serializable {
/*    */   public static final AnyHedgeRHS$ MODULE$;
/*    */   
/*    */   public String productPrefix() {
/* 22 */     return "AnyHedgeRHS";
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
/* 22 */     return x$1 instanceof AnyHedgeRHS$;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 22 */     return -1600016764;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 22 */     return "AnyHedgeRHS";
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 22 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private AnyHedgeRHS$() {
/* 22 */     MODULE$ = this;
/* 22 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\grammar\AnyHedgeRHS$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */