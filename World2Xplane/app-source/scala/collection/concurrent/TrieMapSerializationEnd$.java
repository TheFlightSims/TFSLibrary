/*      */ package scala.collection.concurrent;
/*      */ 
/*      */ import scala.Product;
/*      */ import scala.Serializable;
/*      */ import scala.collection.Iterator;
/*      */ import scala.runtime.BoxesRunTime;
/*      */ 
/*      */ public final class TrieMapSerializationEnd$ implements Product, Serializable {
/*      */   public static final TrieMapSerializationEnd$ MODULE$;
/*      */   
/*      */   public static final long serialVersionUID = -7237891413820527142L;
/*      */   
/*      */   public String productPrefix() {
/* 1063 */     return "TrieMapSerializationEnd";
/*      */   }
/*      */   
/*      */   public int productArity() {
/* 1063 */     return 0;
/*      */   }
/*      */   
/*      */   public Object productElement(int x$1) {
/* 1063 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*      */   }
/*      */   
/*      */   public Iterator<Object> productIterator() {
/* 1063 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*      */   }
/*      */   
/*      */   public boolean canEqual(Object x$1) {
/* 1063 */     return x$1 instanceof TrieMapSerializationEnd$;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1063 */     return 289833389;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1063 */     return "TrieMapSerializationEnd";
/*      */   }
/*      */   
/*      */   private Object readResolve() {
/* 1063 */     return MODULE$;
/*      */   }
/*      */   
/*      */   private TrieMapSerializationEnd$() {
/* 1066 */     MODULE$ = this;
/* 1066 */     Product.class.$init$(this);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\TrieMapSerializationEnd$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */