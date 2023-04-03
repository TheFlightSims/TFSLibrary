/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Some;
/*     */ import scala.collection.Iterator;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class FromConfig$ extends FromConfig implements Product {
/*     */   public static final FromConfig$ MODULE$;
/*     */   
/*     */   public String productPrefix() {
/* 260 */     return "FromConfig";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 260 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 260 */     int i = x$1;
/* 260 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 260 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 260 */     return x$1 instanceof FromConfig$;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 260 */     return -606307412;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 260 */     return "FromConfig";
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 260 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private FromConfig$() {
/* 260 */     MODULE$ = this;
/* 260 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public FromConfig$ getInstance() {
/* 264 */     return this;
/*     */   }
/*     */   
/*     */   public final Option<Resizer> apply$default$1() {
/* 266 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public final SupervisorStrategy apply$default$2() {
/* 267 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */   
/*     */   public final String apply$default$3() {
/* 268 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public final FromConfig apply(Option<Resizer> resizer, SupervisorStrategy supervisorStrategy, String routerDispatcher) {
/* 269 */     return new FromConfig(resizer, supervisorStrategy, routerDispatcher);
/*     */   }
/*     */   
/*     */   public final Option<String> unapply(FromConfig fc) {
/* 271 */     return (Option<String>)new Some(fc.routerDispatcher());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\FromConfig$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */