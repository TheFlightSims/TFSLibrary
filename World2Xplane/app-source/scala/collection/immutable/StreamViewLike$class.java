/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.SliceInterval;
/*    */ 
/*    */ public abstract class StreamViewLike$class {
/*    */   public static void $init$(StreamViewLike $this) {}
/*    */   
/*    */   public static Object force(StreamViewLike $this, CanBuildFrom bf) {
/* 14 */     return $this.iterator().toStream();
/*    */   }
/*    */   
/*    */   public static StreamViewLike.Transformed newForced(StreamViewLike $this, Function0 xs) {
/* 53 */     return new StreamViewLike$$anon$1($this, (StreamViewLike<A, Coll, This>)xs);
/*    */   }
/*    */   
/*    */   public static StreamViewLike.Transformed newAppended(StreamViewLike $this, GenTraversable that) {
/* 54 */     return new StreamViewLike$$anon$2($this, (StreamViewLike<A, Coll, This>)that);
/*    */   }
/*    */   
/*    */   public static StreamViewLike.Transformed newMapped(StreamViewLike $this, Function1 f) {
/* 55 */     return new StreamViewLike$$anon$3($this, (StreamViewLike<A, Coll, This>)f);
/*    */   }
/*    */   
/*    */   public static StreamViewLike.Transformed newFlatMapped(StreamViewLike $this, Function1 f) {
/* 56 */     return new StreamViewLike$$anon$4($this, (StreamViewLike<A, Coll, This>)f);
/*    */   }
/*    */   
/*    */   public static StreamViewLike.Transformed newFiltered(StreamViewLike $this, Function1 p) {
/* 57 */     return new StreamViewLike$$anon$5($this, (StreamViewLike<A, Coll, This>)p);
/*    */   }
/*    */   
/*    */   public static StreamViewLike.Transformed newSliced(StreamViewLike $this, SliceInterval _endpoints) {
/* 58 */     return new StreamViewLike$$anon$6($this, (StreamViewLike<A, Coll, This>)_endpoints);
/*    */   }
/*    */   
/*    */   public static StreamViewLike.Transformed newDroppedWhile(StreamViewLike $this, Function1 p) {
/* 59 */     return new StreamViewLike$$anon$7($this, (StreamViewLike<A, Coll, This>)p);
/*    */   }
/*    */   
/*    */   public static StreamViewLike.Transformed newTakenWhile(StreamViewLike $this, Function1 p) {
/* 60 */     return new StreamViewLike$$anon$8($this, (StreamViewLike<A, Coll, This>)p);
/*    */   }
/*    */   
/*    */   public static StreamViewLike.Transformed newZipped(StreamViewLike $this, GenIterable that) {
/* 61 */     return new StreamViewLike$$anon$9($this, (StreamViewLike<A, Coll, This>)that);
/*    */   }
/*    */   
/*    */   public static StreamViewLike.Transformed newZippedAll(StreamViewLike $this, GenIterable that, Object _thisElem, Object _thatElem) {
/* 63 */     return new StreamViewLike$$anon$10($this, that, _thisElem, (StreamViewLike<A, Coll, This>)_thatElem);
/*    */   }
/*    */   
/*    */   public static StreamViewLike.Transformed newReversed(StreamViewLike<A, Coll, This> $this) {
/* 65 */     return new StreamViewLike$$anon$13($this);
/*    */   }
/*    */   
/*    */   public static StreamViewLike.Transformed newPatched(StreamViewLike $this, int _from, GenSeq _patch, int _replaced) {
/* 67 */     return new StreamViewLike$$anon$11($this, _from, _patch, _replaced);
/*    */   }
/*    */   
/*    */   public static StreamViewLike.Transformed newPrepended(StreamViewLike $this, Object elem) {
/* 69 */     return new StreamViewLike$$anon$12($this, (StreamViewLike<A, Coll, This>)elem);
/*    */   }
/*    */   
/*    */   public static String stringPrefix(StreamViewLike $this) {
/* 71 */     return "StreamView";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\StreamViewLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */