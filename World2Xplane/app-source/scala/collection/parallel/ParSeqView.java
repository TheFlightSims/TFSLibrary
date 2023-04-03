/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Iterator$;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.SeqView;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005EbaB\001\003!\003\r\n!\003\002\013!\006\0248+Z9WS\026<(BA\002\005\003!\001\030M]1mY\026d'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001Q\003\002\006\026?\035\0322\001A\006\020!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\b!E\031bDJ\025+\033\005\021\021B\001\n\003\0059\001\026M]*fcZKWm\036'jW\026\004\"\001F\013\r\001\0211a\003\001CC\002]\021\021\001V\t\0031m\001\"\001D\r\n\005i1!a\002(pi\"Lgn\032\t\003\031qI!!\b\004\003\007\005s\027\020\005\002\025?\0211\001\005\001CC\002\005\022AaQ8mYF\021\001D\t\t\003G\021j\021\001B\005\003K\021\021\001\002U1sC2dW\r\034\t\003)\035\"a\001\013\001\005\006\0049\"aB\"pY2\034V-\035\t\006!\001\031bD\n\t\005G-\032b%\003\002-\t\t91+Z9WS\026<x!\002\030\003\021\003y\023A\003)beN+\027OV5foB\021\001\003\r\004\006\003\tA\t!M\n\003a-AQa\r\031\005\002Q\na\001P5oSRtD#A\030\007\013Y\002\024\021A\034\003\0259{7i\\7cS:,'/\006\0029{M\031QgC\035\021\tAQD\bG\005\003w\t\021\001bQ8nE&tWM\035\t\003)u\"QAF\033C\002]AQaM\033\005\002}\"\022\001\021\t\004\003VbT\"\001\031\t\013\r+D\021\001#\002\021\021\002H.^:%KF$\"!\022$\016\003UBQa\022\"A\002q\nA!\0327f[\")\021*\016C\001\025\006A\021\016^3sCR|'/F\001L!\r\031C\nP\005\003\033\022\021\001\"\023;fe\006$xN\035\005\006\037V\"\t\001U\001\007e\026\034X\017\034;\025\003aAQAU\033\005\002M\013Aa]5{KV\t\001\004C\003Vk\021\005a+A\003dY\026\f'\017F\001X!\ta\001,\003\002Z\r\t!QK\\5u\021\025YV\007\"\001]\003\035\031w.\0342j]\026,2!\0302g)\tAb\fC\003`5\002\007\001-A\003pi\",'\017\005\003\021u\005,\007C\001\013c\t\025\031'L1\001e\005\005q\025C\001\r=!\t!b\rB\003h5\n\007qCA\003OK^$v.\002\003!a\001I\007\007\0026pYn\004R\001\005\001l]j\004\"\001\0067\005\0235D\027\021!A\001\006\0039\"aA0%cA\021Ac\034\003\na\"\f\t\021!A\003\002E\024\021aQ\t\0031I\004$a]<\021\007A!h/\003\002v\005\t1\001+\031:TKF\004\"\001F<\005\023aL\030\021!A\001\006\0039\"aA0%g\021I\001\017[A\001\002\003\025\t!\035\t\003)m$\021\002 5\002\002\003\005)\021A\f\003\007}##\007C\003a\021\rq0\001\007dC:\024U/\0337e\rJ|W.\006\003\002\002\005MQCAA\002!)\t)!a\003\002\020\005E\021QC\007\003\003\017Q1!!\003\005\003\0359WM\\3sS\016LA!!\004\002\b\tq1)\0318D_6\024\027N\\3Ge>l\007CA!i!\r!\0221\003\003\006-u\024\ra\006\t\t!\001\t\t\"a\006\002\032A!\001\003^A\t!\031\tY\"a\013\002\0229!\021QDA\024\035\021\ty\"!\n\016\005\005\005\"bAA\022\021\0051AH]8pizJ\021aB\005\004\003S1\021a\0029bG.\fw-Z\005\005\003[\tyCA\002TKFT1!!\013\007\001")
/*    */ public interface ParSeqView<T, Coll extends scala.collection.Parallel, CollSeq> extends ParSeqViewLike<T, Coll, CollSeq, ParSeqView<T, Coll, CollSeq>, SeqView<T, CollSeq>> {
/*    */   public static abstract class NoCombiner<T> implements Combiner<T, Nothing$> {
/*    */     private volatile transient TaskSupport _combinerTaskSupport;
/*    */     
/*    */     public TaskSupport _combinerTaskSupport() {
/* 28 */       return this._combinerTaskSupport;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void _combinerTaskSupport_$eq(TaskSupport x$1) {
/* 28 */       this._combinerTaskSupport = x$1;
/*    */     }
/*    */     
/*    */     public TaskSupport combinerTaskSupport() {
/* 28 */       return Combiner$class.combinerTaskSupport(this);
/*    */     }
/*    */     
/*    */     public void combinerTaskSupport_$eq(TaskSupport cts) {
/* 28 */       Combiner$class.combinerTaskSupport_$eq(this, cts);
/*    */     }
/*    */     
/*    */     public boolean canBeShared() {
/* 28 */       return Combiner$class.canBeShared(this);
/*    */     }
/*    */     
/*    */     public Object resultWithTaskSupport() {
/* 28 */       return Combiner$class.resultWithTaskSupport(this);
/*    */     }
/*    */     
/*    */     public void sizeHint(int size) {
/* 28 */       Builder.class.sizeHint(this, size);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll) {
/* 28 */       Builder.class.sizeHint(this, coll);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll, int delta) {
/* 28 */       Builder.class.sizeHint(this, coll, delta);
/*    */     }
/*    */     
/*    */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 28 */       Builder.class.sizeHintBounded(this, size, boundingColl);
/*    */     }
/*    */     
/*    */     public <NewTo> Builder<T, NewTo> mapResult(Function1 f) {
/* 28 */       return Builder.class.mapResult(this, f);
/*    */     }
/*    */     
/*    */     public Growable<T> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 28 */       return Growable.class.$plus$eq((Growable)this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Growable<T> $plus$plus$eq(TraversableOnce xs) {
/* 28 */       return Growable.class.$plus$plus$eq((Growable)this, xs);
/*    */     }
/*    */     
/*    */     public NoCombiner() {
/* 28 */       Growable.class.$init$((Growable)this);
/* 28 */       Builder.class.$init$(this);
/* 28 */       Combiner$class.$init$(this);
/*    */     }
/*    */     
/*    */     public NoCombiner<T> $plus$eq(Object elem) {
/* 30 */       return this;
/*    */     }
/*    */     
/*    */     public Iterator<T> iterator() {
/* 31 */       return Iterator$.MODULE$.empty();
/*    */     }
/*    */     
/*    */     public Nothing$ result() {
/* 32 */       throw new UnsupportedOperationException("ParSeqView.Combiner.result");
/*    */     }
/*    */     
/*    */     public Nothing$ size() {
/* 33 */       throw new UnsupportedOperationException("ParSeqView.Combiner.size");
/*    */     }
/*    */     
/*    */     public void clear() {}
/*    */     
/*    */     public <N extends T, NewTo> Nothing$ combine(Combiner other) {
/* 36 */       throw new UnsupportedOperationException("ParSeqView.Combiner.result");
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ParSeqView$$anon$3 implements CanCombineFrom<ParSeqView<?, ? extends ParSeq<?>, ?>, T, ParSeqView<T, ParSeq<T>, Seq<T>>> {
/*    */     public ParSeqView.NoCombiner<T> apply(ParSeqView from) {
/* 43 */       return new ParSeqView$$anon$3$$anon$1(this);
/*    */     }
/*    */     
/*    */     public class ParSeqView$$anon$3$$anon$1 extends ParSeqView.NoCombiner<T> {
/*    */       public ParSeqView$$anon$3$$anon$1(ParSeqView$$anon$3 $outer) {}
/*    */     }
/*    */     
/*    */     public ParSeqView.NoCombiner<T> apply() {
/* 44 */       return new ParSeqView$$anon$3$$anon$2(this);
/*    */     }
/*    */     
/*    */     public class ParSeqView$$anon$3$$anon$2 extends ParSeqView.NoCombiner<T> {
/*    */       public ParSeqView$$anon$3$$anon$2(ParSeqView$$anon$3 $outer) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParSeqView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */