/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Parallelizable;
/*    */ import scala.collection.SeqLike;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.mutable.ParSeq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.IntRef;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001Y3q!\001\002\021\002\007\005\021BA\004TKFd\025n[3\013\005\r!\021aB7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\004\025Qq2#\002\001\f\037%b\003C\001\007\016\033\0051\021B\001\b\007\005\031\te.\037*fMB!\001#\005\n\036\033\005!\021BA\001\005!\t\031B\003\004\001\005\013U\001!\031\001\f\003\003\005\013\"a\006\016\021\0051A\022BA\r\007\005\035qu\016\0365j]\036\004\"\001D\016\n\005q1!aA!osB\0211C\b\003\007?\001!)\031\001\021\003\tQC\027n]\t\003/\005\0222A\t\023'\r\021\031\003\001A\021\003\031q\022XMZ5oK6,g\016\036 \021\t\025\002!#H\007\002\005A\031Qe\n\n\n\005!\022!aA*fcB\031QEK\017\n\005-\022!!C\"m_:,\027M\0317f!\021\001RFE\030\n\0059\"!A\004)be\006dG.\0327ju\006\024G.\032\t\004aQ\022R\"A\031\013\005\r\021$BA\032\005\003!\001\030M]1mY\026d\027BA\0332\005\031\001\026M]*fc\")q\007\001C\001q\0051A%\0338ji\022\"\022!\017\t\003\031iJ!a\017\004\003\tUs\027\016\036\005\007{\001\001K\021\013 \002\027A\f'oQ8nE&tWM]\013\002A!\001)\021\n0\033\005\021\024B\001\"3\005!\031u.\0342j]\026\024\b\"\002#\001\r\003)\025AB;qI\006$X\rF\002:\r.CQaR\"A\002!\0131!\0333y!\ta\021*\003\002K\r\t\031\021J\034;\t\0131\033\005\031\001\n\002\t\025dW-\034\005\006\035\002!\taT\001\niJ\fgn\0354pe6$\"\001U)\016\003\001AQAU'A\002M\013\021A\032\t\005\031Q\023\"#\003\002V\r\tIa)\0368di&|g.\r")
/*    */ public interface SeqLike<A, This extends SeqLike<A, This> & Seq<A>> extends SeqLike<A, This>, Cloneable<This>, Parallelizable<A, ParSeq<A>> {
/*    */   Combiner<A, ParSeq<A>> parCombiner();
/*    */   
/*    */   void update(int paramInt, A paramA);
/*    */   
/*    */   SeqLike<A, This> transform(Function1<A, A> paramFunction1);
/*    */   
/*    */   public class SeqLike$$anonfun$transform$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final IntRef i$1;
/*    */     
/*    */     private final Function1 f$1;
/*    */     
/*    */     public SeqLike$$anonfun$transform$1(SeqLike $outer, IntRef i$1, Function1 f$1) {}
/*    */     
/*    */     public final void apply(Object el) {
/* 46 */       this.$outer.update(this.i$1.elem, this.f$1.apply(el));
/* 47 */       this.i$1.elem++;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SeqLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */