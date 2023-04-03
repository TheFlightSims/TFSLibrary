/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001U3q!\001\002\021\002G\005qAA\004TKF4\026.Z<\013\005\r!\021AC2pY2,7\r^5p]*\tQ!A\003tG\006d\027m\001\001\026\007!\031Rd\005\003\001\0235\001\003C\001\006\f\033\005!\021B\001\007\005\005\031\te.\037*fMB)abD\t\035?5\t!!\003\002\021\005\tY1+Z9WS\026<H*[6f!\t\0212\003\004\001\005\rQ\001AQ1\001\026\005\005\t\025C\001\f\032!\tQq#\003\002\031\t\t9aj\034;iS:<\007C\001\006\033\023\tYBAA\002B]f\004\"AE\017\005\ry\001AQ1\001\026\005\021\031u\016\0347\021\t9\001\021\003\b\t\005\035\005\nB$\003\002#\005\tQq)\0328TKF4\026.Z<\b\013\021\022\001\022A\023\002\017M+\027OV5foB\021aB\n\004\006\003\tA\taJ\n\003M%AQ!\013\024\005\002)\na\001P5oSRtD#A\023\006\ty1\003\001\f\031\004[Q\n\004\003\002\b/aMJ!a\f\002\003\037Q\023\030M^3sg\006\024G.\032,jK^\004\"AE\031\005\023IZ\023\021!A\001\006\003)\"aA0%cA\021!\003\016\003\nk-\n\t\021!A\003\002Y\022\021aQ\t\003-]\002$\001\017\037\021\0079I4(\003\002;\005\tYAK]1wKJ\034\030M\0317f!\t\021B\bB\005>}\005\005\t\021!B\001+\t\031q\f\n\032\005\023UZ\023\021!A\001\006\0031\004\"\002!'\t\007\t\025\001D2b]\n+\030\016\0343Ge>lWC\001\"M+\005\031\005#\002#H\023.kU\"A#\013\005\031\023\021aB4f]\026\024\030nY\005\003\021\026\023AbQ1o\005VLG\016\032$s_6\004\"AS\026\016\003\031\002\"A\005'\005\013Qy$\031A\013\021\t9\0011J\024\031\003\037N\0032A\004)S\023\t\t&AA\002TKF\004\"AE*\005\023Q{\024\021!A\001\006\003)\"aA0%g\001")
/*    */ public interface SeqView<A, Coll> extends SeqViewLike<A, Coll, SeqView<A, Coll>>, GenSeqView<A, Coll> {
/*    */   public static class SeqView$$anon$1 implements CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, SeqView<A, Seq<?>>> {
/*    */     public TraversableView.NoBuilder<A> apply(TraversableView from) {
/* 28 */       return new TraversableView.NoBuilder<A>();
/*    */     }
/*    */     
/*    */     public TraversableView.NoBuilder<A> apply() {
/* 29 */       return new TraversableView.NoBuilder<A>();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SeqView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */