/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class ANY$ extends ContentModel implements Product, Serializable {
/*    */   public static final ANY$ MODULE$;
/*    */   
/*    */   private ANY$() {
/* 92 */     MODULE$ = this;
/* 92 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 92 */     return MODULE$;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 92 */     return 64972;
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 92 */     return x$1 instanceof ANY$;
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 92 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 92 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 92 */     return 0;
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 92 */     return "ANY";
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 93 */     return sb.append("ANY");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ANY$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */