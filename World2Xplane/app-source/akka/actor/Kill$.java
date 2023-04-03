/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.collection.Iterator;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class Kill$ extends Kill implements Product {
/*    */   public static final Kill$ MODULE$;
/*    */   
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   private Kill$() {
/* 50 */     MODULE$ = this;
/* 50 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 50 */     return MODULE$;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 50 */     return "Kill";
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 50 */     return 2338686;
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 50 */     return x$1 instanceof Kill$;
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 50 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 50 */     int i = x$1;
/* 50 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 50 */     return 0;
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 50 */     return "Kill";
/*    */   }
/*    */   
/*    */   public Kill$ getInstance() {
/* 54 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Kill$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */