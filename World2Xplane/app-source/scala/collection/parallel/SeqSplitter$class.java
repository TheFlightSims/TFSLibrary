/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.parallel.mutable.ParArray;
/*     */ import scala.collection.parallel.mutable.ParArray$;
/*     */ 
/*     */ public abstract class SeqSplitter$class {
/*     */   public static void $init$(SeqSplitter $this) {}
/*     */   
/*     */   public static Seq splitWithSignalling(SeqSplitter<T> $this) {
/* 552 */     Seq pits = $this.split();
/* 553 */     pits.foreach((Function1)new SeqSplitter$$anonfun$splitWithSignalling$2($this));
/* 554 */     return pits;
/*     */   }
/*     */   
/*     */   public static Seq psplitWithSignalling(SeqSplitter<T> $this, Seq sizes) {
/* 558 */     Seq pits = $this.psplit(sizes);
/* 559 */     pits.foreach((Function1)new SeqSplitter$$anonfun$psplitWithSignalling$1($this));
/* 560 */     return pits;
/*     */   }
/*     */   
/*     */   public static SeqSplitter.Taken newTaken(SeqSplitter<T> $this, int until) {
/* 579 */     return new SeqSplitter.Taken($this, until);
/*     */   }
/*     */   
/*     */   public static SeqSplitter take(SeqSplitter $this, int n) {
/* 580 */     return $this.newTaken(n);
/*     */   }
/*     */   
/*     */   public static SeqSplitter slice(SeqSplitter $this, int from1, int until1) {
/* 581 */     return (SeqSplitter)$this.newSliceInternal($this.newTaken(until1), from1);
/*     */   }
/*     */   
/*     */   public static SeqSplitter.Mapped map(SeqSplitter $this, Function1 f) {
/* 589 */     return new SeqSplitter.Mapped($this, f);
/*     */   }
/*     */   
/*     */   public static SeqSplitter.Appended appendParSeq(SeqSplitter $this, SeqSplitter that) {
/* 620 */     return new SeqSplitter.Appended<Object, SeqSplitter>($this, that);
/*     */   }
/*     */   
/*     */   public static SeqSplitter.Zipped zipParSeq(SeqSplitter $this, SeqSplitter<?> that) {
/* 628 */     return new SeqSplitter.Zipped($this, that);
/*     */   }
/*     */   
/*     */   public static SeqSplitter.ZippedAll zipAllParSeq(SeqSplitter $this, SeqSplitter that, Object thisElem, Object thatElem) {
/* 651 */     return new SeqSplitter.ZippedAll<Object, Object>($this, that, thisElem, thatElem);
/*     */   }
/*     */   
/*     */   public static SeqSplitter reverse(SeqSplitter $this) {
/* 654 */     ParArray pa = (ParArray)ParArray$.MODULE$.fromTraversables((Seq)Predef$.MODULE$.genericWrapArray(new GenTraversableOnce[] { (GenTraversableOnce)$this })).reverse();
/* 655 */     return (SeqSplitter)new SeqSplitter$$anon$1($this, (SeqSplitter<T>)pa);
/*     */   }
/*     */   
/*     */   public static SeqSplitter.Patched patchParSeq(SeqSplitter $this, int from, SeqSplitter<?> patchElems, int replaced) {
/* 674 */     return new SeqSplitter.Patched($this, from, patchElems, replaced);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\SeqSplitter$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */