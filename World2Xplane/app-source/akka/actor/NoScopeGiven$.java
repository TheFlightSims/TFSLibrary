/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class NoScopeGiven$ extends NoScopeGiven implements Product, Serializable {
/*     */   public static final NoScopeGiven$ MODULE$;
/*     */   
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   public String productPrefix() {
/* 119 */     return "NoScopeGiven";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 119 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 119 */     int i = x$1;
/* 119 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 119 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 119 */     return x$1 instanceof NoScopeGiven$;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 119 */     return 1365270090;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 119 */     return "NoScopeGiven";
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 119 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private NoScopeGiven$() {
/* 119 */     MODULE$ = this;
/* 119 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public Scope withFallback(Scope other) {
/* 120 */     return other;
/*     */   }
/*     */   
/*     */   public NoScopeGiven$ getInstance() {
/* 125 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\NoScopeGiven$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */