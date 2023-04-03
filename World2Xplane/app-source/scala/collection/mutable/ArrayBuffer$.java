/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SeqFactory;
/*     */ 
/*     */ public final class ArrayBuffer$ extends SeqFactory<ArrayBuffer> implements Serializable {
/*     */   public static final ArrayBuffer$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/* 186 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ArrayBuffer$() {
/* 186 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<ArrayBuffer<?>, A, ArrayBuffer<A>> canBuildFrom() {
/* 188 */     return (CanBuildFrom<ArrayBuffer<?>, A, ArrayBuffer<A>>)ReusableCBF();
/*     */   }
/*     */   
/*     */   public <A> Builder<A, ArrayBuffer<A>> newBuilder() {
/* 189 */     return new ArrayBuffer<A>();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ArrayBuffer$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */