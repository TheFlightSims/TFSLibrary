/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class PCDATA$ extends ContentModel implements Product, Serializable {
/*    */   public static final PCDATA$ MODULE$;
/*    */   
/*    */   private PCDATA$() {
/* 86 */     MODULE$ = this;
/* 86 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 86 */     return MODULE$;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 86 */     return -1940668387;
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 86 */     return x$1 instanceof PCDATA$;
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 86 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 86 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 86 */     return 0;
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 86 */     return "PCDATA";
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 87 */     return sb.append("(#PCDATA)");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\PCDATA$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */