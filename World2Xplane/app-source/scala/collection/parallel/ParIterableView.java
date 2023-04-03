/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.GenIterableView;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.IterableView;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Iterator$;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005]baB\001\003!\003\r\n!\003\002\020!\006\024\030\n^3sC\ndWMV5fo*\0211\001B\001\ta\006\024\030\r\0347fY*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U!!\"F\020('\021\0011bD\027\021\0051iQ\"\001\004\n\00591!AB!osJ+g\rE\004\021#Mqb%\013\026\016\003\tI!A\005\002\003'A\013'/\023;fe\006\024G.\032,jK^d\025n[3\021\005Q)B\002\001\003\007-\001!)\031A\f\003\003Q\013\"\001G\016\021\0051I\022B\001\016\007\005\035qu\016\0365j]\036\004\"\001\004\017\n\005u1!aA!osB\021Ac\b\003\007A\001!)\031A\021\003\t\r{G\016\\\t\0031\t\002\"a\t\023\016\003\021I!!\n\003\003\021A\013'/\0317mK2\004\"\001F\024\005\r!\002AQ1\001\030\005\035\031u\016\0347TKF\004R\001\005\001\024=\031\002BaI\026\024M%\021A\006\002\002\r\023R,'/\0312mKZKWm\036\t\005G9\032b$\003\0020\t\tyq)\0328Ji\026\024\030M\0317f-&,woB\0032\005!\005!'A\bQCJLE/\032:bE2,g+[3x!\t\0012GB\003\002\005!\005Ag\005\0024\027!)ag\rC\001o\0051A(\0338jiz\"\022A\r\004\006sM\n\tA\017\002\013\035>\034u.\0342j]\026\024XCA\036A'\rA4\002\020\t\005!uz\004$\003\002?\005\tA1i\\7cS:,'\017\005\002\025\001\022)a\003\017b\001/!)a\007\017C\001\005R\t1\tE\002Eq}j\021a\r\005\006\rb\"\taR\001\tIAdWo\035\023fcR\021\001*S\007\002q!)!*\022a\001\005!Q\r\\3n\021\025a\005\b\"\001N\003!IG/\032:bi>\024X#\001(\021\007\rzu(\003\002Q\t\tA\021\n^3sCR|'\017C\003Sq\021\0051+\001\004sKN,H\016\036\013\0021!)Q\013\017C\001-\006!1/\033>f+\005A\002\"\002-9\t\003I\026!B2mK\006\024H#\001.\021\0051Y\026B\001/\007\005\021)f.\033;\t\013yCD\021A0\002\017\r|WNY5oKV\031\001-Z5\025\005a\t\007\"\0022^\001\004\031\027!B8uQ\026\024\b\003\002\t>I\"\004\"\001F3\005\013\031l&\031A4\003\0039\013\"\001G \021\005QIG!\0026^\005\0049\"!\002(foR{W\001\002\0214\0011\004D!\034:p}B)\001\003\0018r{B\021Ac\034\003\na.\f\t\021!A\003\002]\0211a\030\0232!\t!\"\017B\005tW\006\005\t\021!B\001i\n\t1)\005\002\031kB\022aO\037\t\004!]L\030B\001=\003\005-\001\026M]%uKJ\f'\r\\3\021\005QQH!C>}\003\003\005\tQ!\001\030\005\ryFe\r\003\ng.\f\t\021!A\003\002Q\004\"\001\006@\005\023}\\\027\021!A\001\006\0039\"aA0%e!9\0211A\032\005\004\005\025\021\001D2b]\n+\030\016\0343Ge>lW\003BA\004\0033)\"!!\003\021\025\005-\021\021CA\013\003/\tY\"\004\002\002\016)\031\021q\002\003\002\017\035,g.\032:jG&!\0211CA\007\0059\031\025M\\\"p[\nLg.\032$s_6\004\"\001R6\021\007Q\tI\002\002\004\027\003\003\021\ra\006\t\t!\001\t9\"!\b\002 A!\001c^A\f!\031\t\t#!\r\002\0309!\0211EA\027\035\021\t)#a\013\016\005\005\035\"bAA\025\021\0051AH]8pizJ\021aB\005\004\003_1\021a\0029bG.\fw-Z\005\005\003g\t)D\001\005Ji\026\024\030M\0317f\025\r\tyC\002")
/*    */ public interface ParIterableView<T, Coll extends scala.collection.Parallel, CollSeq> extends ParIterableViewLike<T, Coll, CollSeq, ParIterableView<T, Coll, CollSeq>, IterableView<T, CollSeq>>, GenIterableView<T, Coll> {
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
/* 32 */       throw new UnsupportedOperationException("ParIterableView.Combiner.result");
/*    */     }
/*    */     
/*    */     public Nothing$ size() {
/* 33 */       throw new UnsupportedOperationException("ParIterableView.Combiner.size");
/*    */     }
/*    */     
/*    */     public void clear() {}
/*    */     
/*    */     public <N extends T, NewTo> Nothing$ combine(Combiner other) {
/* 36 */       throw new UnsupportedOperationException("ParIterableView.Combiner.result");
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ParIterableView$$anon$3 implements CanCombineFrom<ParIterableView<?, ? extends ParIterable<?>, ?>, T, ParIterableView<T, ParIterable<T>, Iterable<T>>> {
/*    */     public ParIterableView.NoCombiner<T> apply(ParIterableView from) {
/* 43 */       return new ParIterableView$$anon$3$$anon$1(this);
/*    */     }
/*    */     
/*    */     public class ParIterableView$$anon$3$$anon$1 extends ParIterableView.NoCombiner<T> {
/*    */       public ParIterableView$$anon$3$$anon$1(ParIterableView$$anon$3 $outer) {}
/*    */     }
/*    */     
/*    */     public ParIterableView.NoCombiner<T> apply() {
/* 44 */       return new ParIterableView$$anon$3$$anon$2(this);
/*    */     }
/*    */     
/*    */     public class ParIterableView$$anon$3$$anon$2 extends ParIterableView.NoCombiner<T> {
/*    */       public ParIterableView$$anon$3$$anon$2(ParIterableView$$anon$3 $outer) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParIterableView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */