/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenTraversableFactory;
/*    */ import scala.collection.generic.SeqFactory;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class IndexedSeq$ extends SeqFactory<IndexedSeq> {
/*    */   public static final IndexedSeq$ MODULE$;
/*    */   
/*    */   private GenTraversableFactory<IndexedSeq>.GenericCanBuildFrom<scala.runtime.Nothing$> ReusableCBF;
/*    */   
/*    */   private volatile boolean bitmap$0;
/*    */   
/*    */   private IndexedSeq$() {
/* 34 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   private GenTraversableFactory.GenericCanBuildFrom ReusableCBF$lzycompute() {
/* 35 */     synchronized (this) {
/* 35 */       if (!this.bitmap$0) {
/* 35 */         this.ReusableCBF = 
/* 36 */           scala.collection.IndexedSeq$.MODULE$.ReusableCBF();
/*    */         this.bitmap$0 = true;
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/IndexedSeq$}} */
/*    */       return this.ReusableCBF;
/*    */     } 
/*    */   }
/*    */   
/*    */   public GenTraversableFactory<IndexedSeq>.GenericCanBuildFrom<scala.runtime.Nothing$> ReusableCBF() {
/*    */     return this.bitmap$0 ? this.ReusableCBF : ReusableCBF$lzycompute();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, IndexedSeq<A>> newBuilder() {
/* 41 */     return (Builder)Vector$.MODULE$.newBuilder();
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<IndexedSeq<?>, A, IndexedSeq<A>> canBuildFrom() {
/* 43 */     return (CanBuildFrom)ReusableCBF();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\IndexedSeq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */