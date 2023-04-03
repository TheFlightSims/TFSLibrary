/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.Nothing$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001U4q!\001\002\021\002G\005qAA\bUe\0064XM]:bE2,g+[3x\025\t\031A!\001\006d_2dWm\031;j_:T\021!B\001\006g\016\fG.Y\002\001+\rA1#H\n\005\001%i\001\005\005\002\013\0275\tA!\003\002\r\t\t1\021I\\=SK\032\004RAD\b\0229}i\021AA\005\003!\t\0211\003\026:bm\026\0248/\0312mKZKWm\036'jW\026\004\"AE\n\r\001\0211A\003\001CC\002U\021\021!Q\t\003-e\001\"AC\f\n\005a!!a\002(pi\"Lgn\032\t\003\025iI!a\007\003\003\007\005s\027\020\005\002\023;\0211a\004\001CC\002U\021AaQ8mYB!a\002A\t\035!\021q\021%\005\017\n\005\t\022!AE$f]R\023\030M^3sg\006\024G.\032,jK^<Q\001\n\002\t\002\025\nq\002\026:bm\026\0248/\0312mKZKWm\036\t\003\035\0312Q!\001\002\t\002\035\032\"AJ\005\t\013%2C\021\001\026\002\rqJg.\033;?)\005)c\001\002\027'\0015\022\021BT8Ck&dG-\032:\026\005924cA\026\n_A!\001gM\033\027\033\005\t$B\001\032\003\003\035iW\017^1cY\026L!\001N\031\003\017\t+\030\016\0343feB\021!C\016\003\006)-\022\r!\006\005\006S-\"\t\001\017\013\002sA\031!hK\033\016\003\031BQ\001P\026\005\002u\n\001\002\n9mkN$S-\035\013\003}}j\021a\013\005\006\001n\002\r!N\001\005K2,W\016C\003CW\021\0051)\001\005ji\026\024\030\r^8s+\005!\005c\001\bFk%\021aI\001\002\t\023R,'/\031;pe\")\001j\013C\001\023\0061!/Z:vYR$\022A\006\005\006\027.\"\t\001T\001\006G2,\027M\035\013\002\033B\021!BT\005\003\037\022\021A!\0268ji\026!aD\n\001Ra\r\021v\013\026\t\005\035\001\031f\013\005\002\023)\022IQ\013UA\001\002\003\025\t!\006\002\004?\022\n\004C\001\nX\t%A\006+!A\001\002\013\005\021LA\001D#\t1\"\f\r\002\\?B\031a\002\0300\n\005u\023!a\003+sCZ,'o]1cY\026\004\"AE0\005\023\001\f\027\021!A\001\006\003)\"aA0%e\021I\001\fUA\001\002\003\025\t!\027\005\006G\032\"\031\001Z\001\rG\006t')^5mI\032\023x.\\\013\003K:,\022A\032\t\006O*dWn\\\007\002Q*\021\021NA\001\bO\026tWM]5d\023\tY\007N\001\007DC:\024U/\0337e\rJ|W\016\005\002;!B\021!C\034\003\006)\t\024\r!\006\t\005\035\001i\007\017\r\002rgB\031a\002\030:\021\005I\031H!\003;c\003\003\005\tQ!\001\026\005\ryFe\r")
/*    */ public interface TraversableView<A, Coll> extends TraversableViewLike<A, Coll, TraversableView<A, Coll>>, GenTraversableView<A, Coll> {
/*    */   public static class NoBuilder<A> implements Builder<A, Nothing$> {
/*    */     public void sizeHint(int size) {
/* 24 */       Builder.class.sizeHint(this, size);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll) {
/* 24 */       Builder.class.sizeHint(this, coll);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll, int delta) {
/* 24 */       Builder.class.sizeHint(this, coll, delta);
/*    */     }
/*    */     
/*    */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 24 */       Builder.class.sizeHintBounded(this, size, boundingColl);
/*    */     }
/*    */     
/*    */     public <NewTo> Builder<A, NewTo> mapResult(Function1 f) {
/* 24 */       return Builder.class.mapResult(this, f);
/*    */     }
/*    */     
/*    */     public Growable<A> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 24 */       return Growable.class.$plus$eq((Growable)this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Growable<A> $plus$plus$eq(TraversableOnce xs) {
/* 24 */       return Growable.class.$plus$plus$eq((Growable)this, xs);
/*    */     }
/*    */     
/*    */     public NoBuilder() {
/* 24 */       Growable.class.$init$((Growable)this);
/* 24 */       Builder.class.$init$(this);
/*    */     }
/*    */     
/*    */     public NoBuilder<A> $plus$eq(Object elem) {
/* 25 */       return this;
/*    */     }
/*    */     
/*    */     public Iterator<A> iterator() {
/* 26 */       return (Iterator)Iterator$.MODULE$.empty();
/*    */     }
/*    */     
/*    */     public Nothing$ result() {
/* 27 */       throw new UnsupportedOperationException("TraversableView.Builder.result");
/*    */     }
/*    */     
/*    */     public void clear() {}
/*    */   }
/*    */   
/*    */   public static class TraversableView$$anon$1 implements CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, TraversableView<A, Traversable<?>>> {
/*    */     public TraversableView.NoBuilder<A> apply(TraversableView from) {
/* 33 */       return new TraversableView.NoBuilder<A>();
/*    */     }
/*    */     
/*    */     public TraversableView.NoBuilder<A> apply() {
/* 34 */       return new TraversableView.NoBuilder<A>();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\TraversableView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */