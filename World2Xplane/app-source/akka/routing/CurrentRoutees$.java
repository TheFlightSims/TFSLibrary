/*    */ package akka.routing;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class CurrentRoutees$ extends CurrentRoutees implements Product, Serializable {
/*    */   public static final CurrentRoutees$ MODULE$;
/*    */   
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   private CurrentRoutees$() {
/* 33 */     MODULE$ = this;
/* 33 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 33 */     return MODULE$;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 33 */     return "CurrentRoutees";
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 33 */     return -1206219490;
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 33 */     return x$1 instanceof CurrentRoutees$;
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 33 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 33 */     int i = x$1;
/* 33 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 33 */     return 0;
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 33 */     return "CurrentRoutees";
/*    */   }
/*    */   
/*    */   public CurrentRoutees$ getInstance() {
/* 37 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\CurrentRoutees$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */