/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ListSerializeStart$ implements Product, Serializable {
/*     */   public static final ListSerializeStart$ MODULE$;
/*     */   
/*     */   public static final long serialVersionUID = -8287891243975527522L;
/*     */   
/*     */   public String productPrefix() {
/* 647 */     return "ListSerializeStart";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 647 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 647 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 647 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 647 */     return x$1 instanceof ListSerializeStart$;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 647 */     return -279411264;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 647 */     return "ListSerializeStart";
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 647 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ListSerializeStart$() {
/* 650 */     MODULE$ = this;
/* 650 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\ListSerializeStart$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */