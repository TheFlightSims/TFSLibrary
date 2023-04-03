/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class ResizablePoolActor$ {
/*     */   public static final ResizablePoolActor$ MODULE$;
/*     */   
/*     */   private ResizablePoolActor$() {
/* 298 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public class ResizablePoolActor$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/* 315 */       Object object1 = x1;
/* 316 */       Object object2 = object1;
/* 316 */       if (ResizablePoolActor.Resize$.MODULE$ == null) {
/* 316 */         if (object2 != null)
/*     */           Object object = default.apply(x1); 
/*     */       } else {
/* 316 */         if (ResizablePoolActor.Resize$.MODULE$.equals(object2)) {
/* 316 */           this.$outer.resizerCell().resize(false);
/* 316 */           return (B1)BoxedUnit.UNIT;
/*     */         } 
/*     */         Object object = default.apply(x1);
/*     */       } 
/* 316 */       this.$outer.resizerCell().resize(false);
/* 316 */       return (B1)BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x1) {
/*     */       Object object1 = x1;
/* 316 */       Object object2 = object1;
/* 316 */       if (ResizablePoolActor.Resize$.MODULE$ == null) {
/* 316 */         if (object2 != null)
/*     */           boolean bool = false; 
/*     */       } else {
/* 316 */         if (ResizablePoolActor.Resize$.MODULE$.equals(object2))
/*     */           return true; 
/*     */         boolean bool = false;
/*     */       } 
/*     */       return true;
/*     */     }
/*     */     
/*     */     public ResizablePoolActor$$anonfun$receive$1(ResizablePoolActor $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ResizablePoolActor$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */