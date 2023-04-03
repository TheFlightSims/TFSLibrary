/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.Props;
/*     */ import scala.Product;
/*     */ import scala.collection.Iterator;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class NoRouter$ extends NoRouter implements Product {
/*     */   public static final NoRouter$ MODULE$;
/*     */   
/*     */   public String productPrefix() {
/* 337 */     return "NoRouter";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 337 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 337 */     int i = x$1;
/* 337 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 337 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 337 */     return x$1 instanceof NoRouter$;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 337 */     return 682614922;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 337 */     return "NoRouter";
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 337 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private NoRouter$() {
/* 337 */     MODULE$ = this;
/* 337 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public Router createRouter(ActorSystem system) {
/* 338 */     throw new UnsupportedOperationException("NoRouter has no Router");
/*     */   }
/*     */   
/*     */   public RouterActor createRouterActor() {
/* 343 */     throw new UnsupportedOperationException("NoRouter must not create RouterActor");
/*     */   }
/*     */   
/*     */   public String routerDispatcher() {
/* 344 */     throw new UnsupportedOperationException("NoRouter has no dispatcher");
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 345 */     return other;
/*     */   }
/*     */   
/*     */   public NoRouter$ getInstance() {
/* 350 */     return this;
/*     */   }
/*     */   
/*     */   public Props props(Props routeeProps) {
/* 352 */     return routeeProps.withRouter(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\NoRouter$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */