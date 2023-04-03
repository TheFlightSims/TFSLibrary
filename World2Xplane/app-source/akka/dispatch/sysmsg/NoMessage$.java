/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.collection.Iterator;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ public final class NoMessage$ implements SystemMessage, Product {
/*     */   public static final NoMessage$ MODULE$;
/*     */   
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private transient SystemMessage next;
/*     */   
/*     */   public SystemMessage next() {
/* 250 */     return this.next;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void next_$eq(SystemMessage x$1) {
/* 250 */     this.next = x$1;
/*     */   }
/*     */   
/*     */   public void unlink() {
/* 250 */     SystemMessage$class.unlink(this);
/*     */   }
/*     */   
/*     */   public boolean unlinked() {
/* 250 */     return SystemMessage$class.unlinked(this);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 250 */     return "NoMessage";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 250 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 250 */     int i = x$1;
/* 250 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 250 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 250 */     return x$1 instanceof NoMessage$;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 250 */     return -744497434;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 250 */     return "NoMessage";
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 250 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private NoMessage$() {
/* 250 */     MODULE$ = this;
/* 250 */     SystemMessage$class.$init$(this);
/* 250 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\NoMessage$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */