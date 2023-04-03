/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.LinearSeqOptimized;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ public final class Nil$ extends List<Nothing$> implements Product, Serializable {
/*     */   public static final Nil$ MODULE$;
/*     */   
/*     */   public static final long serialVersionUID = -8256821097970055419L;
/*     */   
/*     */   public String productPrefix() {
/* 334 */     return "Nil";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 334 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 334 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 334 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 334 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Nil$() {
/* 334 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 335 */     return true;
/*     */   }
/*     */   
/*     */   public Nothing$ head() {
/* 337 */     throw new NoSuchElementException("head of empty list");
/*     */   }
/*     */   
/*     */   public List<Nothing$> tail() {
/* 339 */     throw new UnsupportedOperationException("tail of empty list");
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*     */     boolean bool;
/* 341 */     if (that instanceof GenSeq) {
/* 341 */       GenSeq genSeq = (GenSeq)that;
/* 341 */       bool = genSeq.isEmpty();
/*     */     } else {
/* 343 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Nil$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */