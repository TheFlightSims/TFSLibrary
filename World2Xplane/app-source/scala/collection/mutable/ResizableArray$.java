/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SeqFactory;
/*     */ 
/*     */ public final class ResizableArray$ extends SeqFactory<ResizableArray> {
/*     */   public static final ResizableArray$ MODULE$;
/*     */   
/*     */   private ResizableArray$() {
/* 120 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<ResizableArray<?>, A, ResizableArray<A>> canBuildFrom() {
/* 122 */     return (CanBuildFrom<ResizableArray<?>, A, ResizableArray<A>>)ReusableCBF();
/*     */   }
/*     */   
/*     */   public <A> Builder<A, ResizableArray<A>> newBuilder() {
/* 124 */     return (Builder)new ArrayBuffer<A>();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ResizableArray$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */