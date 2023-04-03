/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.collection.GenIterableViewLike;
/*     */ import scala.collection.GenSeqViewLike;
/*     */ import scala.collection.GenTraversableViewLike;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.SeqView;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SliceInterval;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class IndexedSeqView$ {
/*     */   public static final IndexedSeqView$ MODULE$;
/*     */   
/*     */   public class IndexedSeqView$$anon$1 extends IndexedSeqView<A, Coll>.AbstractTransformed<A> implements IndexedSeqView<A, Coll>.Filtered {
/*     */     private final Function1<A, Object> pred;
/*     */     
/*     */     private final int[] index;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public void update(int idx, Object elem) {
/*  79 */       IndexedSeqView.Filtered$class.update(this, idx, elem);
/*     */     }
/*     */     
/*     */     private int[] index$lzycompute() {
/*  79 */       synchronized (this) {
/*  79 */         if (!this.bitmap$0) {
/*  79 */           this.index = GenSeqViewLike.Filtered.class.index((GenSeqViewLike.Filtered)this);
/*  79 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/mutable/IndexedSeqView$$anon$1}} */
/*  79 */         return this.index;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int[] index() {
/*  79 */       return this.bitmap$0 ? this.index : index$lzycompute();
/*     */     }
/*     */     
/*     */     public int length() {
/*  79 */       return GenSeqViewLike.Filtered.class.length((GenSeqViewLike.Filtered)this);
/*     */     }
/*     */     
/*     */     public Object apply(int idx) {
/*  79 */       return GenSeqViewLike.Filtered.class.apply((GenSeqViewLike.Filtered)this, idx);
/*     */     }
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  79 */       return GenIterableViewLike.Filtered.class.iterator((GenIterableViewLike.Filtered)this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  79 */       GenTraversableViewLike.Filtered.class.foreach((GenTraversableViewLike.Filtered)this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  79 */       return GenTraversableViewLike.Filtered.class.viewIdentifier((GenTraversableViewLike.Filtered)this);
/*     */     }
/*     */     
/*     */     public Function1<A, Object> pred() {
/*  79 */       return this.pred;
/*     */     }
/*     */     
/*     */     public IndexedSeqView$$anon$1(IndexedSeqView $outer, Function1<A, Object> p$1) {
/*  79 */       super($outer);
/*  79 */       GenTraversableViewLike.Filtered.class.$init$((GenTraversableViewLike.Filtered)this);
/*  79 */       GenIterableViewLike.Filtered.class.$init$((GenIterableViewLike.Filtered)this);
/*  79 */       GenSeqViewLike.Filtered.class.$init$((GenSeqViewLike.Filtered)this);
/*  79 */       IndexedSeqView.Filtered$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IndexedSeqView$$anon$2 extends IndexedSeqView<A, Coll>.AbstractTransformed<A> implements IndexedSeqView<A, Coll>.Sliced {
/*     */     private final SliceInterval endpoints;
/*     */     
/*     */     public int length() {
/*  80 */       return IndexedSeqView.Sliced$class.length(this);
/*     */     }
/*     */     
/*     */     public void update(int idx, Object elem) {
/*  80 */       IndexedSeqView.Sliced$class.update(this, idx, elem);
/*     */     }
/*     */     
/*     */     public Object apply(int idx) {
/*  80 */       return GenSeqViewLike.Sliced.class.apply((GenSeqViewLike.Sliced)this, idx);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  80 */       GenSeqViewLike.Sliced.class.foreach((GenSeqViewLike.Sliced)this, f);
/*     */     }
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  80 */       return GenSeqViewLike.Sliced.class.iterator((GenSeqViewLike.Sliced)this);
/*     */     }
/*     */     
/*     */     public int from() {
/*  80 */       return GenTraversableViewLike.Sliced.class.from((GenTraversableViewLike.Sliced)this);
/*     */     }
/*     */     
/*     */     public int until() {
/*  80 */       return GenTraversableViewLike.Sliced.class.until((GenTraversableViewLike.Sliced)this);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  80 */       return GenTraversableViewLike.Sliced.class.viewIdentifier((GenTraversableViewLike.Sliced)this);
/*     */     }
/*     */     
/*     */     public SliceInterval endpoints() {
/*  80 */       return this.endpoints;
/*     */     }
/*     */     
/*     */     public IndexedSeqView$$anon$2(IndexedSeqView $outer, SliceInterval _endpoints$1) {
/*  80 */       super($outer);
/*  80 */       GenTraversableViewLike.Sliced.class.$init$((GenTraversableViewLike.Sliced)this);
/*  80 */       GenIterableViewLike.Sliced.class.$init$((GenIterableViewLike.Sliced)this);
/*  80 */       GenSeqViewLike.Sliced.class.$init$((GenSeqViewLike.Sliced)this);
/*  80 */       IndexedSeqView.Sliced$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IndexedSeqView$$anon$3 extends IndexedSeqView<A, Coll>.AbstractTransformed<A> implements IndexedSeqView<A, Coll>.DroppedWhile {
/*     */     private final Function1<A, Object> pred;
/*     */     
/*     */     private final int start;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public void update(int idx, Object elem) {
/*  81 */       IndexedSeqView.DroppedWhile$class.update(this, idx, elem);
/*     */     }
/*     */     
/*     */     private int start$lzycompute() {
/*  81 */       synchronized (this) {
/*  81 */         if (!this.bitmap$0) {
/*  81 */           this.start = GenSeqViewLike.DroppedWhile.class.start((GenSeqViewLike.DroppedWhile)this);
/*  81 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/mutable/IndexedSeqView$$anon$3}} */
/*  81 */         return this.start;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int start() {
/*  81 */       return this.bitmap$0 ? this.start : start$lzycompute();
/*     */     }
/*     */     
/*     */     public int length() {
/*  81 */       return GenSeqViewLike.DroppedWhile.class.length((GenSeqViewLike.DroppedWhile)this);
/*     */     }
/*     */     
/*     */     public Object apply(int idx) {
/*  81 */       return GenSeqViewLike.DroppedWhile.class.apply((GenSeqViewLike.DroppedWhile)this, idx);
/*     */     }
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  81 */       return GenIterableViewLike.DroppedWhile.class.iterator((GenIterableViewLike.DroppedWhile)this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  81 */       GenTraversableViewLike.DroppedWhile.class.foreach((GenTraversableViewLike.DroppedWhile)this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  81 */       return GenTraversableViewLike.DroppedWhile.class.viewIdentifier((GenTraversableViewLike.DroppedWhile)this);
/*     */     }
/*     */     
/*     */     public Function1<A, Object> pred() {
/*  81 */       return this.pred;
/*     */     }
/*     */     
/*     */     public IndexedSeqView$$anon$3(IndexedSeqView $outer, Function1<A, Object> p$2) {
/*  81 */       super($outer);
/*  81 */       GenTraversableViewLike.DroppedWhile.class.$init$((GenTraversableViewLike.DroppedWhile)this);
/*  81 */       GenIterableViewLike.DroppedWhile.class.$init$((GenIterableViewLike.DroppedWhile)this);
/*  81 */       GenSeqViewLike.DroppedWhile.class.$init$((GenSeqViewLike.DroppedWhile)this);
/*  81 */       IndexedSeqView.DroppedWhile$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IndexedSeqView$$anon$4 extends IndexedSeqView<A, Coll>.AbstractTransformed<A> implements IndexedSeqView<A, Coll>.TakenWhile {
/*     */     private final Function1<A, Object> pred;
/*     */     
/*     */     private final int len;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public void update(int idx, Object elem) {
/*  82 */       IndexedSeqView.TakenWhile$class.update(this, idx, elem);
/*     */     }
/*     */     
/*     */     private int len$lzycompute() {
/*  82 */       synchronized (this) {
/*  82 */         if (!this.bitmap$0) {
/*  82 */           this.len = GenSeqViewLike.TakenWhile.class.len((GenSeqViewLike.TakenWhile)this);
/*  82 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/mutable/IndexedSeqView$$anon$4}} */
/*  82 */         return this.len;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int len() {
/*  82 */       return this.bitmap$0 ? this.len : len$lzycompute();
/*     */     }
/*     */     
/*     */     public int length() {
/*  82 */       return GenSeqViewLike.TakenWhile.class.length((GenSeqViewLike.TakenWhile)this);
/*     */     }
/*     */     
/*     */     public Object apply(int idx) {
/*  82 */       return GenSeqViewLike.TakenWhile.class.apply((GenSeqViewLike.TakenWhile)this, idx);
/*     */     }
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  82 */       return GenIterableViewLike.TakenWhile.class.iterator((GenIterableViewLike.TakenWhile)this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  82 */       GenTraversableViewLike.TakenWhile.class.foreach((GenTraversableViewLike.TakenWhile)this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  82 */       return GenTraversableViewLike.TakenWhile.class.viewIdentifier((GenTraversableViewLike.TakenWhile)this);
/*     */     }
/*     */     
/*     */     public Function1<A, Object> pred() {
/*  82 */       return this.pred;
/*     */     }
/*     */     
/*     */     public IndexedSeqView$$anon$4(IndexedSeqView $outer, Function1<A, Object> p$3) {
/*  82 */       super($outer);
/*  82 */       GenTraversableViewLike.TakenWhile.class.$init$((GenTraversableViewLike.TakenWhile)this);
/*  82 */       GenIterableViewLike.TakenWhile.class.$init$((GenIterableViewLike.TakenWhile)this);
/*  82 */       GenSeqViewLike.TakenWhile.class.$init$((GenSeqViewLike.TakenWhile)this);
/*  82 */       IndexedSeqView.TakenWhile$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class IndexedSeqView$$anon$5 extends IndexedSeqView<A, Coll>.AbstractTransformed<A> implements IndexedSeqView<A, Coll>.Reversed {
/*     */     public void update(int idx, Object elem) {
/*  83 */       IndexedSeqView.Reversed$class.update(this, idx, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  83 */       return GenSeqViewLike.Reversed.class.iterator((GenSeqViewLike.Reversed)this);
/*     */     }
/*     */     
/*     */     public int length() {
/*  83 */       return GenSeqViewLike.Reversed.class.length((GenSeqViewLike.Reversed)this);
/*     */     }
/*     */     
/*     */     public Object apply(int idx) {
/*  83 */       return GenSeqViewLike.Reversed.class.apply((GenSeqViewLike.Reversed)this, idx);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  83 */       return GenSeqViewLike.Reversed.class.viewIdentifier((GenSeqViewLike.Reversed)this);
/*     */     }
/*     */     
/*     */     public IndexedSeqView$$anon$5(IndexedSeqView $outer) {
/*  83 */       super($outer);
/*  83 */       GenSeqViewLike.Reversed.class.$init$((GenSeqViewLike.Reversed)this);
/*  83 */       IndexedSeqView.Reversed$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   private IndexedSeqView$() {
/* 107 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, SeqView<A, Seq<?>>> canBuildFrom() {
/* 110 */     return new IndexedSeqView$$anon$6();
/*     */   }
/*     */   
/*     */   public static class IndexedSeqView$$anon$6 implements CanBuildFrom<TraversableView<?, ? extends Traversable<?>>, A, SeqView<A, Seq<?>>> {
/*     */     public TraversableView.NoBuilder<A> apply(TraversableView from) {
/* 111 */       return new TraversableView.NoBuilder();
/*     */     }
/*     */     
/*     */     public TraversableView.NoBuilder<A> apply() {
/* 112 */       return new TraversableView.NoBuilder();
/*     */     }
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<TraversableView<?, Object>, A, SeqView<A, Object>> arrCanBuildFrom() {
/* 115 */     return new IndexedSeqView$$anon$7();
/*     */   }
/*     */   
/*     */   public static class IndexedSeqView$$anon$7 implements CanBuildFrom<TraversableView<?, Object>, A, SeqView<A, Object>> {
/*     */     public TraversableView.NoBuilder<A> apply(TraversableView from) {
/* 116 */       return new TraversableView.NoBuilder();
/*     */     }
/*     */     
/*     */     public TraversableView.NoBuilder<A> apply() {
/* 117 */       return new TraversableView.NoBuilder();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\IndexedSeqView$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */