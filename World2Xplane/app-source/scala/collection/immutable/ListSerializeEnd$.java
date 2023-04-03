/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ListSerializeEnd$ implements Product, Serializable {
/*     */   public static final ListSerializeEnd$ MODULE$;
/*     */   
/*     */   public static final long serialVersionUID = -8476791151975527571L;
/*     */   
/*     */   public String productPrefix() {
/* 651 */     return "ListSerializeEnd";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 651 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 651 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 651 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 651 */     return x$1 instanceof ListSerializeEnd$;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 651 */     return -1720972871;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 651 */     return "ListSerializeEnd";
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 651 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ListSerializeEnd$() {
/* 652 */     MODULE$ = this;
/* 652 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\ListSerializeEnd$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */