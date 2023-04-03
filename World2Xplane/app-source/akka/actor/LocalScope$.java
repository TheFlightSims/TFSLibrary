/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class LocalScope$ extends LocalScope implements Product, Serializable {
/*     */   public static final LocalScope$ MODULE$;
/*     */   
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   public String productPrefix() {
/* 104 */     return "LocalScope";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 104 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 104 */     int i = x$1;
/* 104 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 104 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 104 */     return x$1 instanceof LocalScope$;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 104 */     return -1009371703;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 104 */     return "LocalScope";
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 104 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private LocalScope$() {
/* 104 */     MODULE$ = this;
/* 104 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public LocalScope$ getInstance() {
/* 108 */     return this;
/*     */   }
/*     */   
/*     */   public Scope withFallback(Scope other) {
/* 110 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\LocalScope$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */