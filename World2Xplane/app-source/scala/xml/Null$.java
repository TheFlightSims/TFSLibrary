/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class Null$ extends MetaData implements Product {
/*    */   public static final Null$ MODULE$;
/*    */   
/*    */   public String productPrefix() {
/* 21 */     return "Null";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 21 */     return 0;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 21 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 21 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 21 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Null$() {
/* 21 */     MODULE$ = this;
/* 21 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Iterator<scala.runtime.Nothing$> iterator() {
/* 22 */     return scala.collection.Iterator$.MODULE$.empty();
/*    */   }
/*    */   
/*    */   public int size() {
/* 23 */     return 0;
/*    */   }
/*    */   
/*    */   public MetaData append(MetaData m, NamespaceBinding scope) {
/* 24 */     return m;
/*    */   }
/*    */   
/*    */   public NamespaceBinding append$default$2() {
/* 24 */     return TopScope$.MODULE$;
/*    */   }
/*    */   
/*    */   public MetaData filter(Function1 f) {
/* 25 */     return this;
/*    */   }
/*    */   
/*    */   public MetaData copy(MetaData next) {
/* 27 */     return next;
/*    */   }
/*    */   
/*    */   public scala.runtime.Null$ getNamespace(Node owner) {
/* 28 */     return null;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 30 */     return false;
/*    */   }
/*    */   
/*    */   public scala.runtime.Null$ next() {
/* 31 */     return null;
/*    */   }
/*    */   
/*    */   public scala.runtime.Null$ key() {
/* 32 */     return null;
/*    */   }
/*    */   
/*    */   public scala.runtime.Null$ value() {
/* 33 */     return null;
/*    */   }
/*    */   
/*    */   public boolean isPrefixed() {
/* 34 */     return false;
/*    */   }
/*    */   
/*    */   public int length() {
/* 36 */     return 0;
/*    */   }
/*    */   
/*    */   public int length(int i) {
/* 37 */     return i;
/*    */   }
/*    */   
/*    */   public boolean strict_$eq$eq(Equality other) {
/*    */     boolean bool;
/* 39 */     if (other instanceof MetaData) {
/* 39 */       MetaData metaData = (MetaData)other;
/* 39 */       bool = (metaData.length() == 0) ? true : false;
/*    */     } else {
/* 41 */       bool = false;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */   
/*    */   public Seq<Object> basisForHashCode() {
/* 43 */     return (Seq<Object>)scala.collection.immutable.Nil$.MODULE$;
/*    */   }
/*    */   
/*    */   public scala.runtime.Null$ apply(String namespace, NamespaceBinding scope, String key) {
/* 45 */     return null;
/*    */   }
/*    */   
/*    */   public Seq<Node> apply(String key) {
/* 47 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 47 */     if (Utility$.MODULE$.isNameStart(BoxesRunTime.unboxToChar((new StringOps(key)).head())))
/* 47 */       return null; 
/* 48 */     throw new IllegalArgumentException((new StringBuilder()).append("not a valid attribute name '").append(key).append("', so can never match !").toString());
/*    */   }
/*    */   
/*    */   public void toString1(StringBuilder sb) {}
/*    */   
/*    */   public String toString1() {
/* 51 */     return "";
/*    */   }
/*    */   
/*    */   public String toString() {
/* 53 */     return "";
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 55 */     return sb;
/*    */   }
/*    */   
/*    */   public boolean wellformed(NamespaceBinding scope) {
/* 57 */     return true;
/*    */   }
/*    */   
/*    */   public Null$ remove(String key) {
/* 59 */     return this;
/*    */   }
/*    */   
/*    */   public Null$ remove(String namespace, NamespaceBinding scope, String key) {
/* 60 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Null$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */