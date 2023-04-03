/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class SelectParent$ implements SelectionPathElement, Product, Serializable {
/*     */   public static final SelectParent$ MODULE$;
/*     */   
/*     */   public static final long serialVersionUID = 2L;
/*     */   
/*     */   private SelectParent$() {
/* 278 */     MODULE$ = this;
/* 278 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 278 */     return MODULE$;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 278 */     return 2101727462;
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 278 */     return x$1 instanceof SelectParent$;
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 278 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 278 */     int i = x$1;
/* 278 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 278 */     return 0;
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 278 */     return "SelectParent";
/*     */   }
/*     */   
/*     */   public String toString() {
/* 279 */     return "..";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\SelectParent$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */