/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ReceiveTimeout$ extends ReceiveTimeout implements Product, Serializable {
/*     */   public static final ReceiveTimeout$ MODULE$;
/*     */   
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private ReceiveTimeout$() {
/* 119 */     MODULE$ = this;
/* 119 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 119 */     return MODULE$;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 119 */     return "ReceiveTimeout";
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 119 */     return -988624610;
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 119 */     return x$1 instanceof ReceiveTimeout$;
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 119 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 119 */     int i = x$1;
/* 119 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 119 */     return 0;
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 119 */     return "ReceiveTimeout";
/*     */   }
/*     */   
/*     */   public ReceiveTimeout$ getInstance() {
/* 123 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ReceiveTimeout$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */