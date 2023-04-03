/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.generic.IdleSignalling$;
/*     */ import scala.collection.generic.Signalling;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.ObjectRef;
/*     */ 
/*     */ public abstract class IterableSplitter$class {
/*     */   public static void $init$(IterableSplitter $this) {
/* 389 */     $this.signalDelegate_$eq((Signalling)IdleSignalling$.MODULE$);
/*     */   }
/*     */   
/*     */   public static Seq splitWithSignalling(IterableSplitter<T> $this) {
/* 397 */     Seq pits = $this.split();
/* 398 */     pits.foreach((Function1)new IterableSplitter$$anonfun$splitWithSignalling$1($this));
/* 399 */     return pits;
/*     */   }
/*     */   
/*     */   public static boolean shouldSplitFurther(IterableSplitter $this, ParIterable coll, int parallelismLevel) {
/* 402 */     return ($this.remaining() > package$.MODULE$.thresholdFromSize(coll.size(), parallelismLevel));
/*     */   }
/*     */   
/*     */   public static String buildString(IterableSplitter $this, Function1 closure) {
/* 425 */     ObjectRef output = new ObjectRef("");
/* 427 */     closure.apply(new IterableSplitter$$anonfun$buildString$1($this, (IterableSplitter<T>)output));
/* 428 */     return (String)output.elem;
/*     */   }
/*     */   
/*     */   public static final void appendln$1(IterableSplitter $this, String s, ObjectRef output$1) {
/*     */     output$1.elem = (new StringBuilder()).append(output$1.elem).append((new StringBuilder()).append(s).append("\n").toString()).toString();
/*     */   }
/*     */   
/*     */   public static String debugInformation(IterableSplitter $this) {
/* 433 */     return (new StringBuilder()).append("Parallel iterator: ").append($this.getClass()).toString();
/*     */   }
/*     */   
/*     */   public static IterableSplitter.Taken newTaken(IterableSplitter<T> $this, int until) {
/* 454 */     return new IterableSplitter.Taken($this, until);
/*     */   }
/*     */   
/*     */   public static IterableSplitter.Taken newSliceInternal(IterableSplitter $this, IterableSplitter.Taken it, int from1) {
/* 456 */     int count = from1;
/* 457 */     while (count > 0 && it.hasNext()) {
/* 458 */       it.next();
/* 459 */       count--;
/*     */     } 
/* 461 */     return it;
/*     */   }
/*     */   
/*     */   public static IterableSplitter take(IterableSplitter $this, int n) {
/* 463 */     return $this.newTaken(n);
/*     */   }
/*     */   
/*     */   public static IterableSplitter slice(IterableSplitter $this, int from1, int until1) {
/* 464 */     return (IterableSplitter)$this.newSliceInternal($this.newTaken(until1), from1);
/*     */   }
/*     */   
/*     */   public static IterableSplitter.Mapped map(IterableSplitter $this, Function1 f) {
/* 475 */     return new IterableSplitter.Mapped($this, f);
/*     */   }
/*     */   
/*     */   public static IterableSplitter.Appended appendParIterable(IterableSplitter $this, IterableSplitter that) {
/* 494 */     return new IterableSplitter.Appended<Object, IterableSplitter>($this, that);
/*     */   }
/*     */   
/*     */   public static IterableSplitter.Zipped zipParSeq(IterableSplitter $this, SeqSplitter<?> that) {
/* 510 */     return new IterableSplitter.Zipped($this, that);
/*     */   }
/*     */   
/*     */   public static IterableSplitter.ZippedAll zipAllParSeq(IterableSplitter $this, SeqSplitter that, Object thisElem, Object thatElem) {
/* 532 */     return new IterableSplitter.ZippedAll<Object, Object>($this, that, thisElem, thatElem);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\IterableSplitter$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */