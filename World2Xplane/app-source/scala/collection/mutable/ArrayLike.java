/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.AbstractSeq;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.IndexedSeq;
/*    */ import scala.collection.IndexedSeqLike;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.IterableView;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\00112q!\001\002\021\002\007\005\021BA\005BeJ\f\027\020T5lK*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\rQQ\003H\n\004\001-y\001C\001\007\016\033\0051\021B\001\b\007\005\r\te.\037\t\005!E\0312$D\001\003\023\t\021\"AA\nJ]\022,\0070\0323TKF|\005\017^5nSj,G\r\005\002\025+1\001A!\002\f\001\005\0049\"!A!\022\005aY\001C\001\007\032\023\tQbAA\004O_RD\027N\\4\021\005QaBAB\017\001\t\013\007qC\001\003SKB\024\b\"B\020\001\t\003\001\023A\002\023j]&$H\005F\001\"!\ta!%\003\002$\r\t!QK\\5u\021\025)\003\001\"\001'\003\021!W-\0329\026\003\035\0022\001K\025\f\033\005!\021B\001\026\005\005)Ie\016Z3yK\022\034V-\035\t\005!\001\0312\004")
/*    */ public interface ArrayLike<A, Repr> extends IndexedSeqOptimized<A, Repr> {
/*    */   IndexedSeq<Object> deep();
/*    */   
/*    */   public class ArrayLike$$anon$1 extends AbstractSeq<Object> implements IndexedSeq<Object> {
/*    */     public GenericCompanion<IndexedSeq> companion() {
/* 41 */       return IndexedSeq.class.companion(this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<Object> seq() {
/* 41 */       return IndexedSeq.class.seq(this);
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 41 */       return IndexedSeqLike.class.hashCode((IndexedSeqLike)this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<Object> thisCollection() {
/* 41 */       return IndexedSeqLike.class.thisCollection((IndexedSeqLike)this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<Object> toCollection(Object repr) {
/* 41 */       return IndexedSeqLike.class.toCollection((IndexedSeqLike)this, repr);
/*    */     }
/*    */     
/*    */     public Iterator<Object> iterator() {
/* 41 */       return IndexedSeqLike.class.iterator((IndexedSeqLike)this);
/*    */     }
/*    */     
/*    */     public <A1> Buffer<A1> toBuffer() {
/* 41 */       return IndexedSeqLike.class.toBuffer((IndexedSeqLike)this);
/*    */     }
/*    */     
/*    */     public ArrayLike$$anon$1(ArrayLike $outer) {
/* 41 */       IndexedSeqLike.class.$init$((IndexedSeqLike)this);
/* 41 */       IndexedSeq.class.$init$(this);
/*    */     }
/*    */     
/*    */     public int length() {
/* 42 */       return this.$outer.length();
/*    */     }
/*    */     
/*    */     public Object apply(int idx) {
/* 43 */       Object object2, object1 = this.$outer.apply(idx);
/* 44 */       if (object1 instanceof Object && object1.getClass().isArray()) {
/* 44 */         object2 = WrappedArray$.MODULE$.make(object1).deep();
/*    */       } else {
/* 45 */         object2 = object1;
/*    */       } 
/*    */       return object2;
/*    */     }
/*    */     
/*    */     public String stringPrefix() {
/* 47 */       return "Array";
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ArrayLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */