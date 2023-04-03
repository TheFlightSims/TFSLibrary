/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ObjectRef;
/*     */ 
/*     */ public final class BitSetLike$ {
/*     */   public static final BitSetLike$ MODULE$;
/*     */   
/*     */   private final int LogWL;
/*     */   
/*     */   private final int scala$collection$BitSetLike$$WordLength;
/*     */   
/*     */   public class BitSetLike$$anon$1 extends AbstractIterator<Object> {
/*     */     private int current;
/*     */     
/*     */     private final int end;
/*     */     
/*     */     public BitSetLike$$anon$1(BitSetLike $outer) {
/* 103 */       this.current = 0;
/* 104 */       this.end = $outer.nwords() * BitSetLike$.MODULE$.scala$collection$BitSetLike$$WordLength();
/*     */     }
/*     */     
/*     */     private int current() {
/*     */       return this.current;
/*     */     }
/*     */     
/*     */     private void current_$eq(int x$1) {
/*     */       this.current = x$1;
/*     */     }
/*     */     
/*     */     private int end() {
/* 104 */       return this.end;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 106 */       for (; current() < end() && !this.$outer.contains(current()); current_$eq(current() + 1));
/* 107 */       return (current() < end());
/*     */     }
/*     */     
/*     */     public int next() {
/* 110 */       int r = current();
/* 110 */       current_$eq(current() + 1);
/* 110 */       return hasNext() ? r : 
/* 111 */         BoxesRunTime.unboxToInt(Iterator$.MODULE$.empty().next());
/*     */     }
/*     */   }
/*     */   
/*     */   public class BitSetLike$$anonfun$foreach$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Function1 f$1;
/*     */     
/*     */     public final void apply(int i) {
/* 115 */       apply$mcVI$sp(i);
/*     */     }
/*     */     
/*     */     public BitSetLike$$anonfun$foreach$1(BitSetLike $outer, Function1 f$1) {}
/*     */     
/*     */     public void apply$mcVI$sp(int i) {
/* 116 */       long w = this.$outer.word(i);
/* 117 */       int j = i * BitSetLike$.MODULE$.scala$collection$BitSetLike$$WordLength();
/* 117 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 117 */       BitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1 bitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1 = new BitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1(this, w);
/*     */       Range range;
/* 117 */       if ((range = scala.runtime.RichInt$.MODULE$.until$extension0(j, (i + 1) * BitSetLike$.MODULE$.scala$collection$BitSetLike$$WordLength())).validateRangeBoundaries((Function1)bitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1)) {
/*     */         int terminal1;
/*     */         int step1;
/*     */         int i1;
/* 117 */         for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 117 */           int k = i1;
/* 117 */           bitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1.apply(k);
/* 117 */           i1 += step1;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public class BitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final long w$1;
/*     */       
/*     */       public BitSetLike$$anonfun$foreach$1$$anonfun$apply$mcVI$sp$1(BitSetLike$$anonfun$foreach$1 $outer, long w$1) {}
/*     */       
/*     */       public final Object apply(int j) {
/* 118 */         return ((this.w$1 & 1L << j) != 0L) ? this.$outer.f$1.apply(BoxesRunTime.boxToInteger(j)) : BoxedUnit.UNIT;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class BitSetLike$$anonfun$$bar$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final long[] words$1;
/*     */     
/*     */     public final BitSet other$1;
/*     */     
/*     */     public final void apply(int idx) {
/* 133 */       apply$mcVI$sp(idx);
/*     */     }
/*     */     
/*     */     public BitSetLike$$anonfun$$bar$1(BitSetLike $outer, long[] words$1, BitSet other$1) {}
/*     */     
/*     */     public void apply$mcVI$sp(int idx) {
/* 134 */       this.words$1[idx] = this.$outer.word(idx) | this.other$1.word(idx);
/*     */     }
/*     */   }
/*     */   
/*     */   public class BitSetLike$$anonfun$$amp$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final long[] words$2;
/*     */     
/*     */     public final BitSet other$2;
/*     */     
/*     */     public final void apply(int idx) {
/* 147 */       apply$mcVI$sp(idx);
/*     */     }
/*     */     
/*     */     public BitSetLike$$anonfun$$amp$1(BitSetLike $outer, long[] words$2, BitSet other$2) {}
/*     */     
/*     */     public void apply$mcVI$sp(int idx) {
/* 148 */       this.words$2[idx] = this.$outer.word(idx) & this.other$2.word(idx);
/*     */     }
/*     */   }
/*     */   
/*     */   public class BitSetLike$$anonfun$$amp$tilde$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final long[] words$3;
/*     */     
/*     */     public final BitSet other$3;
/*     */     
/*     */     public final void apply(int idx) {
/* 162 */       apply$mcVI$sp(idx);
/*     */     }
/*     */     
/*     */     public BitSetLike$$anonfun$$amp$tilde$1(BitSetLike $outer, long[] words$3, BitSet other$3) {}
/*     */     
/*     */     public void apply$mcVI$sp(int idx) {
/* 163 */       this.words$3[idx] = this.$outer.word(idx) & (this.other$3.word(idx) ^ 0xFFFFFFFFFFFFFFFFL);
/*     */     }
/*     */   }
/*     */   
/*     */   public class BitSetLike$$anonfun$$up$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final long[] words$4;
/*     */     
/*     */     public final BitSet other$4;
/*     */     
/*     */     public final void apply(int idx) {
/* 177 */       apply$mcVI$sp(idx);
/*     */     }
/*     */     
/*     */     public BitSetLike$$anonfun$$up$1(BitSetLike $outer, long[] words$4, BitSet other$4) {}
/*     */     
/*     */     public void apply$mcVI$sp(int idx) {
/* 178 */       this.words$4[idx] = this.$outer.word(idx) ^ this.other$4.word(idx);
/*     */     }
/*     */   }
/*     */   
/*     */   public class BitSetLike$$anonfun$subsetOf$1 extends AbstractFunction1.mcZI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BitSet other$5;
/*     */     
/*     */     public final boolean apply(int idx) {
/* 192 */       return apply$mcZI$sp(idx);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int idx) {
/* 192 */       return ((this.$outer.word(idx) & (this.other$5.word(idx) ^ 0xFFFFFFFFFFFFFFFFL)) == 0L);
/*     */     }
/*     */     
/*     */     public BitSetLike$$anonfun$subsetOf$1(BitSetLike $outer, BitSet other$5) {}
/*     */   }
/*     */   
/*     */   public class BitSetLike$$anonfun$addString$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ObjectRef pre$1;
/*     */     
/*     */     public final StringBuilder sb$1;
/*     */     
/*     */     public final String sep$1;
/*     */     
/*     */     public final void apply(int i) {
/* 197 */       apply$mcVI$sp(i);
/*     */     }
/*     */     
/*     */     public BitSetLike$$anonfun$addString$1(BitSetLike $outer, ObjectRef pre$1, StringBuilder sb$1, String sep$1) {}
/*     */     
/*     */     public void apply$mcVI$sp(int i) {
/* 198 */       if (this.$outer.contains(i)) {
/* 199 */         this.sb$1.append((String)this.pre$1.elem).append(i);
/* 200 */         this.pre$1.elem = this.sep$1;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private BitSetLike$() {
/* 209 */     MODULE$ = this;
/* 210 */     this.LogWL = 6;
/* 211 */     this.scala$collection$BitSetLike$$WordLength = 64;
/*     */   }
/*     */   
/*     */   public int LogWL() {
/*     */     return this.LogWL;
/*     */   }
/*     */   
/*     */   public int scala$collection$BitSetLike$$WordLength() {
/* 211 */     return this.scala$collection$BitSetLike$$WordLength;
/*     */   }
/*     */   
/*     */   public long[] updateArray(long[] elems, int idx, long w) {
/* 214 */     int len = elems.length;
/* 215 */     for (; len > 0 && (elems[len - 1] == 0L || (w == 0L && idx == len - 1)); len--);
/* 216 */     int newlen = len;
/* 217 */     if (idx >= len && w != 0L)
/* 217 */       newlen = idx + 1; 
/* 218 */     long[] newelems = new long[newlen];
/* 219 */     scala.Array$.MODULE$.copy(elems, 0, newelems, 0, len);
/* 220 */     if (idx < newlen) {
/* 220 */       newelems[idx] = w;
/*     */     } else {
/* 221 */       scala.Predef$.MODULE$.assert((w == 0L));
/*     */     } 
/* 222 */     return newelems;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\BitSetLike$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */