/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class GetRoutees$ extends GetRoutees implements Product, Serializable {
/*     */   public static final GetRoutees$ MODULE$;
/*     */   
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private GetRoutees$() {
/* 368 */     MODULE$ = this;
/* 368 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 368 */     return MODULE$;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 368 */     return "GetRoutees";
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 368 */     return 121006241;
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 368 */     return x$1 instanceof GetRoutees$;
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 368 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 368 */     int i = x$1;
/* 368 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 368 */     return 0;
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 368 */     return "GetRoutees";
/*     */   }
/*     */   
/*     */   public GetRoutees$ getInstance() {
/* 372 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\GetRoutees$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */