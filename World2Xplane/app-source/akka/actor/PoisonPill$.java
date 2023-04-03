/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.collection.Iterator;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class PoisonPill$ extends PoisonPill implements Product {
/*    */   public static final PoisonPill$ MODULE$;
/*    */   
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   private PoisonPill$() {
/* 37 */     MODULE$ = this;
/* 37 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 37 */     return MODULE$;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 37 */     return "PoisonPill";
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 37 */     return -1023050623;
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 37 */     return x$1 instanceof PoisonPill$;
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 37 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 37 */     int i = x$1;
/* 37 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 37 */     return 0;
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 37 */     return "PoisonPill";
/*    */   }
/*    */   
/*    */   public PoisonPill$ getInstance() {
/* 41 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\PoisonPill$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */