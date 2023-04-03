/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.AbstractSeq;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.IterableView;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.mutable.ParSeq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t2a!\001\002\002\002\031A!aC!cgR\024\030m\031;TKFT!a\001\003\002\0175,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\026\005%y1c\001\001\0135A\0311\002D\007\016\003\021I!!\001\003\021\0059yA\002\001\003\006!\001\021\rA\005\002\002\003\016\001\021CA\n\030!\t!R#D\001\007\023\t1bAA\004O_RD\027N\\4\021\005QA\022BA\r\007\005\r\te.\037\t\0047qiQ\"\001\002\n\005u\021!aA*fc\")q\004\001C\001A\0051A(\0338jiz\"\022!\t\t\0047\001i\001")
/*    */ public abstract class AbstractSeq<A> extends AbstractSeq<A> implements Seq<A> {
/*    */   public GenericCompanion<Seq> companion() {
/* 47 */     return Seq$class.companion(this);
/*    */   }
/*    */   
/*    */   public Seq<A> seq() {
/* 47 */     return Seq$class.seq(this);
/*    */   }
/*    */   
/*    */   public Combiner<A, ParSeq<A>> parCombiner() {
/* 47 */     return SeqLike$class.parCombiner(this);
/*    */   }
/*    */   
/*    */   public SeqLike<A, Seq<A>> transform(Function1 f) {
/* 47 */     return SeqLike$class.transform(this, f);
/*    */   }
/*    */   
/*    */   public Object scala$collection$mutable$Cloneable$$super$clone() {
/* 47 */     return super.clone();
/*    */   }
/*    */   
/*    */   public Seq<A> clone() {
/* 47 */     return (Seq<A>)Cloneable$class.clone(this);
/*    */   }
/*    */   
/*    */   public AbstractSeq() {
/* 47 */     Traversable$class.$init$(this);
/* 47 */     Iterable$class.$init$(this);
/* 47 */     Cloneable$class.$init$(this);
/* 47 */     SeqLike$class.$init$(this);
/* 47 */     Seq$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\AbstractSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */