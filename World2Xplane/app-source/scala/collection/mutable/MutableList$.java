/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SeqFactory;
/*     */ 
/*     */ public final class MutableList$ extends SeqFactory<MutableList> implements Serializable {
/*     */   public static final MutableList$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/* 158 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private MutableList$() {
/* 158 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<MutableList<?>, A, MutableList<A>> canBuildFrom() {
/* 160 */     return (CanBuildFrom<MutableList<?>, A, MutableList<A>>)ReusableCBF();
/*     */   }
/*     */   
/*     */   public <A> Builder<A, MutableList<A>> newBuilder() {
/* 162 */     return new MutableList<A>();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\MutableList$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */