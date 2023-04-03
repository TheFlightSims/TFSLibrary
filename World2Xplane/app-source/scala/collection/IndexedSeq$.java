/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenTraversableFactory;
/*    */ import scala.collection.generic.SeqFactory;
/*    */ import scala.collection.immutable.IndexedSeq$;
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
/* 31 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   private GenTraversableFactory.GenericCanBuildFrom ReusableCBF$lzycompute() {
/* 34 */     synchronized (this) {
/* 34 */       if (!this.bitmap$0) {
/* 34 */         this.ReusableCBF = new IndexedSeq$$anon$1();
/* 34 */         this.bitmap$0 = true;
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/collection/IndexedSeq$}} */
/* 34 */       return this.ReusableCBF;
/*    */     } 
/*    */   }
/*    */   
/*    */   public GenTraversableFactory<IndexedSeq>.GenericCanBuildFrom<scala.runtime.Nothing$> ReusableCBF() {
/* 34 */     return this.bitmap$0 ? this.ReusableCBF : ReusableCBF$lzycompute();
/*    */   }
/*    */   
/*    */   public static class IndexedSeq$$anon$1 extends GenTraversableFactory<IndexedSeq>.GenericCanBuildFrom<scala.runtime.Nothing$> {
/*    */     public IndexedSeq$$anon$1() {
/* 34 */       super((GenTraversableFactory)IndexedSeq$.MODULE$);
/*    */     }
/*    */     
/*    */     public Builder<scala.runtime.Nothing$, IndexedSeq<scala.runtime.Nothing$>> apply() {
/* 35 */       return IndexedSeq$.MODULE$.newBuilder();
/*    */     }
/*    */   }
/*    */   
/*    */   public <A> Builder<A, IndexedSeq<A>> newBuilder() {
/* 37 */     return IndexedSeq$.MODULE$.newBuilder();
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<IndexedSeq<?>, A, IndexedSeq<A>> canBuildFrom() {
/* 39 */     return (CanBuildFrom)ReusableCBF();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\IndexedSeq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */