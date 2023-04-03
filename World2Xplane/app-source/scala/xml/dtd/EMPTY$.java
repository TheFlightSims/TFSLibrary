/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class EMPTY$ extends ContentModel implements Product, Serializable {
/*    */   public static final EMPTY$ MODULE$;
/*    */   
/*    */   private EMPTY$() {
/* 89 */     MODULE$ = this;
/* 89 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 89 */     return MODULE$;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 89 */     return 66096429;
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 89 */     return x$1 instanceof EMPTY$;
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 89 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 89 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 89 */     return 0;
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 89 */     return "EMPTY";
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 90 */     return sb.append("EMPTY");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\EMPTY$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */