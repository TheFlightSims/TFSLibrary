/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class REQUIRED$ extends DefaultDecl implements Product, Serializable {
/*     */   public static final REQUIRED$ MODULE$;
/*     */   
/*     */   public String productPrefix() {
/* 140 */     return "REQUIRED";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 140 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 140 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 140 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 140 */     return x$1 instanceof REQUIRED$;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 140 */     return 389487519;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 140 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private REQUIRED$() {
/* 140 */     MODULE$ = this;
/* 140 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 141 */     return "#REQUIRED";
/*     */   }
/*     */   
/*     */   public StringBuilder buildString(StringBuilder sb) {
/* 142 */     return sb.append("#REQUIRED");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\REQUIRED$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */