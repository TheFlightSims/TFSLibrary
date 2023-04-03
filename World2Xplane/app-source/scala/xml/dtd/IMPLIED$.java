/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class IMPLIED$ extends DefaultDecl implements Product, Serializable {
/*     */   public static final IMPLIED$ MODULE$;
/*     */   
/*     */   public String productPrefix() {
/* 145 */     return "IMPLIED";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 145 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 145 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 145 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 145 */     return x$1 instanceof IMPLIED$;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 145 */     return -1651045240;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 145 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private IMPLIED$() {
/* 145 */     MODULE$ = this;
/* 145 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 146 */     return "#IMPLIED";
/*     */   }
/*     */   
/*     */   public StringBuilder buildString(StringBuilder sb) {
/* 147 */     return sb.append("#IMPLIED");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\IMPLIED$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */