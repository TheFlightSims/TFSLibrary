/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.generic.ParFactory;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ 
/*    */ public final class ParIterable$ extends ParFactory<ParIterable> {
/*    */   public static final ParIterable$ MODULE$;
/*    */   
/*    */   public class ParIterable$$anonfun$toSeq$1 extends AbstractFunction0<Combiner<T, ParSeq<T>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Combiner<T, ParSeq<T>> apply() {
/* 40 */       return ParSeq$.MODULE$.newCombiner();
/*    */     }
/*    */     
/*    */     public ParIterable$$anonfun$toSeq$1(ParIterable $outer) {}
/*    */   }
/*    */   
/*    */   private ParIterable$() {
/* 47 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> CanCombineFrom<ParIterable<?>, T, ParIterable<T>> canBuildFrom() {
/* 49 */     return (CanCombineFrom<ParIterable<?>, T, ParIterable<T>>)new ParFactory.GenericCanCombineFrom(this);
/*    */   }
/*    */   
/*    */   public <T> Combiner<T, ParIterable<T>> newBuilder() {
/* 51 */     return (Combiner)package$.MODULE$.ParArrayCombiner().apply();
/*    */   }
/*    */   
/*    */   public <T> Combiner<T, ParIterable<T>> newCombiner() {
/* 53 */     return (Combiner)package$.MODULE$.ParArrayCombiner().apply();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParIterable$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */