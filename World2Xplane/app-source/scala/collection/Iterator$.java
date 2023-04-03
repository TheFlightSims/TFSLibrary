/*      */ package scala.collection;
/*      */ 
/*      */ import java.util.NoSuchElementException;
/*      */ import scala.Function0;
/*      */ import scala.Function1;
/*      */ import scala.Function2;
/*      */ import scala.PartialFunction;
/*      */ import scala.Serializable;
/*      */ import scala.Tuple2;
/*      */ import scala.collection.immutable.Stream;
/*      */ import scala.collection.mutable.ArrayBuffer;
/*      */ import scala.runtime.AbstractFunction0;
/*      */ import scala.runtime.AbstractFunction1;
/*      */ import scala.runtime.BoxesRunTime;
/*      */ 
/*      */ public final class Iterator$ {
/*      */   public static final Iterator$ MODULE$;
/*      */   
/*      */   private final Iterator<scala.runtime.Nothing$> empty;
/*      */   
/*      */   private Iterator$() {
/*   25 */     MODULE$ = this;
/*   37 */     this.empty = new Iterator.$anon$2();
/*      */   }
/*      */   
/*      */   public <A> TraversableOnce.BufferedCanBuildFrom<A, Iterator> IteratorCanBuildFrom() {
/*      */     return new Iterator$$anon$25();
/*      */   }
/*      */   
/*      */   public static class Iterator$$anon$25 extends TraversableOnce.BufferedCanBuildFrom<A, Iterator> {
/*      */     public <B> Iterator<B> bufferToColl(ArrayBuffer coll) {
/*      */       return coll.iterator();
/*      */     }
/*      */     
/*      */     public <B> Iterator<B> traversableToColl(GenTraversable<B> t) {
/*      */       return t.toIterator();
/*      */     }
/*      */   }
/*      */   
/*      */   public Iterator<scala.runtime.Nothing$> empty() {
/*   37 */     return this.empty;
/*      */   }
/*      */   
/*      */   public static class $anon$2 extends AbstractIterator<scala.runtime.Nothing$> {
/*      */     public boolean hasNext() {
/*   38 */       return false;
/*      */     }
/*      */     
/*      */     public scala.runtime.Nothing$ next() {
/*   39 */       throw new NoSuchElementException("next on empty iterator");
/*      */     }
/*      */   }
/*      */   
/*      */   public <A> Iterator<A> single(Object elem) {
/*   49 */     return new Iterator$$anon$3(elem);
/*      */   }
/*      */   
/*      */   public static class Iterator$$anon$3 extends AbstractIterator<A> {
/*      */     private boolean hasnext = true;
/*      */     
/*      */     private final Object elem$1;
/*      */     
/*      */     private boolean hasnext() {
/*   50 */       return this.hasnext;
/*      */     }
/*      */     
/*      */     private void hasnext_$eq(boolean x$1) {
/*   50 */       this.hasnext = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*   51 */       return hasnext();
/*      */     }
/*      */     
/*      */     public A next() {
/*   53 */       hasnext_$eq(false);
/*   53 */       return hasnext() ? (A)this.elem$1 : 
/*   54 */         (A)Iterator$.MODULE$.empty().next();
/*      */     }
/*      */     
/*      */     public Iterator$$anon$3(Object elem$1) {}
/*      */   }
/*      */   
/*      */   public <A> Iterator<A> apply(Seq<A> elems) {
/*   63 */     return elems.iterator();
/*      */   }
/*      */   
/*      */   public <A> Iterator<A> fill(int len, Function0 elem) {
/*   71 */     return new Iterator$$anon$4(len, elem);
/*      */   }
/*      */   
/*      */   public static class Iterator$$anon$4 extends AbstractIterator<A> {
/*   72 */     private int i = 0;
/*      */     
/*      */     private final int len$1;
/*      */     
/*      */     private final Function0 elem$2;
/*      */     
/*      */     private int i() {
/*   72 */       return this.i;
/*      */     }
/*      */     
/*      */     private void i_$eq(int x$1) {
/*   72 */       this.i = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*   73 */       return (i() < this.len$1);
/*      */     }
/*      */     
/*      */     public A next() {
/*   75 */       i_$eq(i() + 1);
/*   75 */       return hasNext() ? (A)this.elem$2.apply() : 
/*   76 */         (A)Iterator$.MODULE$.empty().next();
/*      */     }
/*      */     
/*      */     public Iterator$$anon$4(int len$1, Function0 elem$2) {}
/*      */   }
/*      */   
/*      */   public <A> Iterator<A> tabulate(int end, Function1 f) {
/*   85 */     return new Iterator$$anon$5(end, f);
/*      */   }
/*      */   
/*      */   public static class Iterator$$anon$5 extends AbstractIterator<A> {
/*   86 */     private int i = 0;
/*      */     
/*      */     private final int end$1;
/*      */     
/*      */     private final Function1 f$1;
/*      */     
/*      */     private int i() {
/*   86 */       return this.i;
/*      */     }
/*      */     
/*      */     private void i_$eq(int x$1) {
/*   86 */       this.i = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*   87 */       return (i() < this.end$1);
/*      */     }
/*      */     
/*      */     public A next() {
/*   89 */       Object result = this.f$1.apply(BoxesRunTime.boxToInteger(i()));
/*   89 */       i_$eq(i() + 1);
/*   89 */       return hasNext() ? (A)result : 
/*   90 */         (A)Iterator$.MODULE$.empty().next();
/*      */     }
/*      */     
/*      */     public Iterator$$anon$5(int end$1, Function1 f$1) {}
/*      */   }
/*      */   
/*      */   public Iterator<Object> range(int start, int end) {
/*   99 */     return range(start, end, 1);
/*      */   }
/*      */   
/*      */   public Iterator<Object> range(int start, int end, int step) {
/*  108 */     return new Iterator$$anon$6(start, end, step);
/*      */   }
/*      */   
/*      */   public static class Iterator$$anon$6 extends AbstractIterator<Object> {
/*      */     private int i;
/*      */     
/*      */     private final int end$2;
/*      */     
/*      */     private final int step$1;
/*      */     
/*      */     public Iterator$$anon$6(int start$1, int end$2, int step$1) {
/*  109 */       if (step$1 == 0)
/*  109 */         throw new IllegalArgumentException("zero step"); 
/*  110 */       this.i = start$1;
/*      */     }
/*      */     
/*      */     private int i() {
/*  110 */       return this.i;
/*      */     }
/*      */     
/*      */     private void i_$eq(int x$1) {
/*  110 */       this.i = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  111 */       return ((this.step$1 <= 0 || i() < this.end$2) && (this.step$1 >= 0 || i() > this.end$2));
/*      */     }
/*      */     
/*      */     public int next() {
/*  113 */       int result = i();
/*  113 */       i_$eq(i() + this.step$1);
/*  113 */       return hasNext() ? result : 
/*  114 */         BoxesRunTime.unboxToInt(Iterator$.MODULE$.empty().next());
/*      */     }
/*      */   }
/*      */   
/*      */   public <T> Iterator<T> iterate(Object start, Function1 f) {
/*  123 */     return new Iterator$$anon$7(start, f);
/*      */   }
/*      */   
/*      */   public static class Iterator$$anon$7 extends AbstractIterator<T> {
/*      */     private boolean first;
/*      */     
/*      */     private T acc;
/*      */     
/*      */     private final Function1 f$2;
/*      */     
/*      */     public Iterator$$anon$7(Object start$2, Function1 f$2) {
/*  124 */       this.first = true;
/*  125 */       this.acc = (T)start$2;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  126 */       return true;
/*      */     }
/*      */     
/*      */     public T next() {
/*  128 */       if (this.first) {
/*  128 */         this.first = false;
/*      */       } else {
/*  129 */         this.acc = (T)this.f$2.apply(this.acc);
/*      */       } 
/*  131 */       return this.acc;
/*      */     }
/*      */   }
/*      */   
/*      */   public Iterator<Object> from(int start) {
/*  140 */     return from(start, 1);
/*      */   }
/*      */   
/*      */   public Iterator<Object> from(int start, int step) {
/*  148 */     return new Iterator$$anon$8(start, step);
/*      */   }
/*      */   
/*      */   public static class Iterator$$anon$8 extends AbstractIterator<Object> {
/*      */     private int i;
/*      */     
/*      */     private final int step$2;
/*      */     
/*      */     public Iterator$$anon$8(int start$3, int step$2) {
/*  149 */       this.i = start$3;
/*      */     }
/*      */     
/*      */     private int i() {
/*  149 */       return this.i;
/*      */     }
/*      */     
/*      */     private void i_$eq(int x$1) {
/*  149 */       this.i = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  150 */       return true;
/*      */     }
/*      */     
/*      */     public int next() {
/*  151 */       int result = i();
/*  151 */       i_$eq(i() + this.step$2);
/*  151 */       return result;
/*      */     }
/*      */   }
/*      */   
/*      */   public <A> Iterator<A> continually(Function0 elem) {
/*  160 */     return new Iterator$$anon$9(elem);
/*      */   }
/*      */   
/*      */   public static class Iterator$$anon$9 extends AbstractIterator<A> {
/*      */     private final Function0 elem$3;
/*      */     
/*      */     public Iterator$$anon$9(Function0 elem$3) {}
/*      */     
/*      */     public boolean hasNext() {
/*  161 */       return true;
/*      */     }
/*      */     
/*      */     public A next() {
/*  162 */       return (A)this.elem$3.apply();
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$10 extends AbstractIterator<A> {
/*      */     private int remaining;
/*      */     
/*      */     public Iterator$$anon$10(Iterator $outer, int lo$1, int until$1) {
/*  307 */       this.remaining = until$1 - lo$1;
/*      */     }
/*      */     
/*      */     private int remaining() {
/*  307 */       return this.remaining;
/*      */     }
/*      */     
/*      */     private void remaining_$eq(int x$1) {
/*  307 */       this.remaining = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  308 */       return (remaining() > 0 && this.$outer.hasNext());
/*      */     }
/*      */     
/*      */     public A next() {
/*  311 */       remaining_$eq(remaining() - 1);
/*  312 */       return (remaining() > 0) ? this.$outer.next() : 
/*      */         
/*  314 */         (A)Iterator$.MODULE$.empty().next();
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$11 extends AbstractIterator<B> {
/*      */     private final Function1 f$3;
/*      */     
/*      */     public Iterator$$anon$11(Iterator $outer, Function1 f$3) {}
/*      */     
/*      */     public boolean hasNext() {
/*  327 */       return this.$outer.hasNext();
/*      */     }
/*      */     
/*      */     public B next() {
/*  328 */       return (B)this.f$3.apply(this.$outer.next());
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$12 extends AbstractIterator<B> {
/*      */     private Iterator<B> cur;
/*      */     
/*      */     private boolean selfExhausted;
/*      */     
/*      */     private Iterator<B> it;
/*      */     
/*      */     private final Function0 that$1;
/*      */     
/*      */     private volatile boolean bitmap$0;
/*      */     
/*      */     public Iterator$$anon$12(Iterator<B> $outer, Function0 that$1) {
/*  343 */       this.cur = $outer;
/*  344 */       this.selfExhausted = false;
/*      */     }
/*      */     
/*      */     private Iterator<B> cur() {
/*      */       return this.cur;
/*      */     }
/*      */     
/*      */     private void cur_$eq(Iterator<B> x$1) {
/*      */       this.cur = x$1;
/*      */     }
/*      */     
/*      */     private boolean selfExhausted() {
/*  344 */       return this.selfExhausted;
/*      */     }
/*      */     
/*      */     private void selfExhausted_$eq(boolean x$1) {
/*  344 */       this.selfExhausted = x$1;
/*      */     }
/*      */     
/*      */     private Iterator it$lzycompute() {
/*  348 */       synchronized (this) {
/*  348 */         if (!this.bitmap$0) {
/*  348 */           this.it = ((GenTraversableOnce<B>)this.that$1.apply()).toIterator();
/*  348 */           this.bitmap$0 = true;
/*      */         } 
/*      */         /* monitor exit ThisExpression{ObjectType{scala/collection/Iterator$$anon$12}} */
/*  348 */         this.that$1 = null;
/*  348 */         return this.it;
/*      */       } 
/*      */     }
/*      */     
/*      */     private Iterator<B> it() {
/*  348 */       return this.bitmap$0 ? this.it : it$lzycompute();
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  350 */       if (!cur().hasNext()) {
/*  350 */         if (!selfExhausted() && 
/*  351 */           it().hasNext()) {
/*  352 */           cur_$eq(it());
/*  353 */           selfExhausted_$eq(true);
/*      */           if (true);
/*      */         } 
/*      */         return false;
/*      */       } 
/*      */     }
/*      */     
/*      */     public B next() {
/*  357 */       hasNext();
/*  357 */       return cur().next();
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$13 extends AbstractIterator<B> {
/*  369 */     private Iterator<B> cur = (Iterator)Iterator$.MODULE$.empty();
/*      */     
/*      */     private final Function1 f$4;
/*      */     
/*      */     private Iterator<B> cur() {
/*  369 */       return this.cur;
/*      */     }
/*      */     
/*      */     private void cur_$eq(Iterator<B> x$1) {
/*  369 */       this.cur = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*      */       while (true) {
/*  371 */         if (this.$outer.hasNext()) {
/*  371 */           cur_$eq(((GenTraversableOnce<B>)this.f$4.apply(this.$outer.next())).toIterator());
/*      */           continue;
/*      */         } 
/*  371 */         return cur().hasNext();
/*      */       } 
/*      */     }
/*      */     
/*      */     public B next() {
/*  372 */       return (hasNext() ? (Object<B>)cur() : (Object)Iterator$.MODULE$.empty()).next();
/*      */     }
/*      */     
/*      */     public Iterator$$anon$13(Iterator $outer, Function1 f$4) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$14 extends AbstractIterator<A> {
/*      */     private A hd;
/*      */     
/*      */     private A hd() {
/*  383 */       return this.hd;
/*      */     }
/*      */     
/*      */     private void hd_$eq(Object x$1) {
/*  383 */       this.hd = (A)x$1;
/*      */     }
/*      */     
/*      */     private boolean hdDefined = false;
/*      */     
/*      */     private final Function1 p$1;
/*      */     
/*      */     private boolean hdDefined() {
/*  384 */       return this.hdDefined;
/*      */     }
/*      */     
/*      */     private void hdDefined_$eq(boolean x$1) {
/*  384 */       this.hdDefined = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  386 */       if (!hdDefined()) {
/*  388 */         while (this.$outer.hasNext()) {
/*  389 */           hd_$eq(this.$outer.next());
/*  390 */           if (BoxesRunTime.unboxToBoolean(this.p$1.apply(hd()))) {
/*  391 */             hdDefined_$eq(true);
/*      */             if (true)
/*      */               continue; 
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */       
/*      */       } 
/*      */       return false;
/*      */     }
/*      */     
/*      */     public A next() {
/*  395 */       hdDefined_$eq(false);
/*  395 */       return hasNext() ? hd() : (A)Iterator$.MODULE$.empty().next();
/*      */     }
/*      */     
/*      */     public Iterator$$anon$14(Iterator $outer, Function1 p$1) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anonfun$filterNot$1 extends AbstractFunction1<A, Object> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 p$2;
/*      */     
/*      */     public final boolean apply(Object x$1) {
/*  436 */       return !BoxesRunTime.unboxToBoolean(this.p$2.apply(x$1));
/*      */     }
/*      */     
/*      */     public Iterator$$anonfun$filterNot$1(Iterator $outer, Function1 p$2) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$15 extends AbstractIterator<B> {
/*      */     private final BufferedIterator self$1;
/*      */     
/*      */     private final PartialFunction pf$1;
/*      */     
/*      */     public Iterator$$anon$15(Iterator $outer, BufferedIterator self$1, PartialFunction pf$1) {}
/*      */     
/*      */     private void skip() {
/*  451 */       for (; this.self$1.hasNext() && !this.pf$1.isDefinedAt(this.self$1.head()); this.self$1.next());
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  452 */       skip();
/*  452 */       return this.self$1.hasNext();
/*      */     }
/*      */     
/*      */     public B next() {
/*  453 */       skip();
/*  453 */       return (B)this.pf$1.apply(this.self$1.next());
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$16 extends AbstractIterator<B> {
/*      */     private boolean hasNext;
/*      */     
/*      */     private B elem;
/*      */     
/*      */     private final Function2 op$1;
/*      */     
/*      */     public Iterator$$anon$16(Iterator $outer, Object z$1, Function2 op$1) {
/*  470 */       this.hasNext = true;
/*  471 */       this.elem = (B)z$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*      */       return this.hasNext;
/*      */     }
/*      */     
/*      */     private void hasNext_$eq(boolean x$1) {
/*      */       this.hasNext = x$1;
/*      */     }
/*      */     
/*      */     private B elem() {
/*  471 */       return this.elem;
/*      */     }
/*      */     
/*      */     private void elem_$eq(Object x$1) {
/*  471 */       this.elem = (B)x$1;
/*      */     }
/*      */     
/*      */     public B next() {
/*  473 */       Object res = elem();
/*  474 */       if (this.$outer.hasNext()) {
/*  474 */         elem_$eq((B)this.op$1.apply(elem(), this.$outer.next()));
/*      */       } else {
/*  475 */         hasNext_$eq(false);
/*      */       } 
/*  476 */       return hasNext() ? (B)res : 
/*  477 */         (B)Iterator$.MODULE$.empty().next();
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$17 extends AbstractIterator<A> {
/*      */     private A hd;
/*      */     
/*      */     private boolean hdDefined;
/*      */     
/*      */     private Iterator<A> tail;
/*      */     
/*      */     private final Function1 p$3;
/*      */     
/*      */     public Iterator$$anon$17(Iterator<A> $outer, Function1 p$3) {
/*  507 */       this.hdDefined = false;
/*  508 */       this.tail = $outer;
/*      */     }
/*      */     
/*      */     private A hd() {
/*      */       return this.hd;
/*      */     }
/*      */     
/*      */     private void hd_$eq(Object x$1) {
/*      */       this.hd = (A)x$1;
/*      */     }
/*      */     
/*      */     private boolean hdDefined() {
/*      */       return this.hdDefined;
/*      */     }
/*      */     
/*      */     private void hdDefined_$eq(boolean x$1) {
/*      */       this.hdDefined = x$1;
/*      */     }
/*      */     
/*      */     private Iterator<A> tail() {
/*  508 */       return this.tail;
/*      */     }
/*      */     
/*      */     private void tail_$eq(Iterator<A> x$1) {
/*  508 */       this.tail = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  510 */       if (!hdDefined()) {
/*  510 */         if (tail().hasNext()) {
/*  511 */           hd_$eq(tail().next());
/*  512 */           if (BoxesRunTime.unboxToBoolean(this.p$3.apply(hd()))) {
/*  512 */             hdDefined_$eq(true);
/*      */           } else {
/*  513 */             tail_$eq((Iterator)Iterator$.MODULE$.empty());
/*      */           } 
/*  514 */           if (hdDefined());
/*      */         } 
/*      */         return false;
/*      */       } 
/*      */     }
/*      */     
/*      */     public A next() {
/*  516 */       hdDefined_$eq(false);
/*  516 */       return hasNext() ? hd() : (A)Iterator$.MODULE$.empty().next();
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anonfun$1 extends AbstractFunction1<A, Object> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 p$4;
/*      */     
/*      */     public final boolean apply(Object x$2) {
/*  542 */       return !BoxesRunTime.unboxToBoolean(this.p$4.apply(x$2));
/*      */     }
/*      */     
/*      */     public Iterator$$anonfun$1(Iterator $outer, Function1 p$4) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$18 extends AbstractIterator<A> {
/*      */     private BufferedIterator<A> it;
/*      */     
/*      */     private final BufferedIterator self$3;
/*      */     
/*      */     private final Iterator$Leading$1 leading$1;
/*      */     
/*      */     private volatile boolean bitmap$0;
/*      */     
/*      */     public Iterator$$anon$18(Iterator $outer, BufferedIterator self$3, Iterator$Leading$1 leading$1) {}
/*      */     
/*      */     private BufferedIterator it$lzycompute() {
/*  587 */       synchronized (this) {
/*  587 */         if (!this.bitmap$0) {
/*  588 */           this.leading$1.finish();
/*  589 */           this.it = this.self$3;
/*      */           this.bitmap$0 = true;
/*      */         } 
/*      */         /* monitor exit ThisExpression{ObjectType{scala/collection/Iterator$$anon$18}} */
/*      */         this.self$3 = null;
/*      */         this.leading$1 = null;
/*      */         return this.it;
/*      */       } 
/*      */     }
/*      */     
/*      */     private BufferedIterator<A> it() {
/*      */       return this.bitmap$0 ? this.it : it$lzycompute();
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  591 */       return it().hasNext();
/*      */     }
/*      */     
/*      */     public A next() {
/*  592 */       return it().next();
/*      */     }
/*      */     
/*      */     public String toString() {
/*  593 */       return "unknown-if-empty iterator";
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$19 extends AbstractIterator<A> {
/*      */     private boolean dropped = false;
/*      */     
/*      */     private final BufferedIterator self$4;
/*      */     
/*      */     private final Function1 p$6;
/*      */     
/*      */     private boolean dropped() {
/*  609 */       return this.dropped;
/*      */     }
/*      */     
/*      */     private void dropped_$eq(boolean x$1) {
/*  609 */       this.dropped = x$1;
/*      */     }
/*      */     
/*      */     private void skip() {
/*  611 */       if (!dropped()) {
/*  612 */         for (; this.self$4.hasNext() && BoxesRunTime.unboxToBoolean(this.p$6.apply(this.self$4.head())); this.self$4.next());
/*  613 */         dropped_$eq(true);
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  615 */       skip();
/*  615 */       return this.self$4.hasNext();
/*      */     }
/*      */     
/*      */     public A next() {
/*  616 */       skip();
/*  616 */       return this.self$4.next();
/*      */     }
/*      */     
/*      */     public Iterator$$anon$19(Iterator $outer, BufferedIterator self$4, Function1 p$6) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$20 extends AbstractIterator<Tuple2<A, B>> {
/*      */     private final Iterator that$2;
/*      */     
/*      */     public Iterator$$anon$20(Iterator $outer, Iterator that$2) {}
/*      */     
/*      */     public boolean hasNext() {
/*  634 */       return (this.$outer.hasNext() && this.that$2.hasNext());
/*      */     }
/*      */     
/*      */     public Tuple2<A, B> next() {
/*  635 */       return new Tuple2(this.$outer.next(), this.that$2.next());
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$21 extends AbstractIterator<A1> {
/*  651 */     private int count = 0;
/*      */     
/*      */     private final int len$3;
/*      */     
/*      */     private final Object elem$4;
/*      */     
/*      */     private int count() {
/*  651 */       return this.count;
/*      */     }
/*      */     
/*      */     private void count_$eq(int x$1) {
/*  651 */       this.count = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  652 */       return (this.$outer.hasNext() || count() < this.len$3);
/*      */     }
/*      */     
/*      */     public A1 next() {
/*  654 */       count_$eq(count() + 1);
/*  655 */       return this.$outer.hasNext() ? this.$outer.next() : (
/*  656 */         (count() <= this.len$3) ? (A1)this.elem$4 : 
/*  657 */         (A1)Iterator$.MODULE$.empty().next());
/*      */     }
/*      */     
/*      */     public Iterator$$anon$21(Iterator $outer, int len$3, Object elem$4) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$22 extends AbstractIterator<Tuple2<A, Object>> {
/*  669 */     private int idx = 0;
/*      */     
/*      */     private int idx() {
/*  669 */       return this.idx;
/*      */     }
/*      */     
/*      */     private void idx_$eq(int x$1) {
/*  669 */       this.idx = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  670 */       return this.$outer.hasNext();
/*      */     }
/*      */     
/*      */     public Tuple2<A, Object> next() {
/*  672 */       Tuple2<A, Object> ret = new Tuple2(this.$outer.next(), BoxesRunTime.boxToInteger(idx()));
/*  673 */       idx_$eq(idx() + 1);
/*  674 */       return ret;
/*      */     }
/*      */     
/*      */     public Iterator$$anon$22(Iterator $outer) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$23 extends AbstractIterator<Tuple2<A1, B1>> {
/*      */     private final Iterator that$3;
/*      */     
/*      */     private final Object thisElem$1;
/*      */     
/*      */     private final Object thatElem$1;
/*      */     
/*      */     public Iterator$$anon$23(Iterator $outer, Iterator that$3, Object thisElem$1, Object thatElem$1) {}
/*      */     
/*      */     public boolean hasNext() {
/*  702 */       return (this.$outer.hasNext() || this.that$3.hasNext());
/*      */     }
/*      */     
/*      */     public Tuple2<A1, B1> next() {
/*  704 */       return this.$outer.hasNext() ? (
/*  705 */         this.that$3.hasNext() ? new Tuple2(this.$outer.next(), this.that$3.next()) : 
/*  706 */         new Tuple2(this.$outer.next(), this.thatElem$1)) : (
/*      */         
/*  708 */         this.that$3.hasNext() ? new Tuple2(this.thisElem$1, this.that$3.next()) : 
/*  709 */         (Tuple2<A1, B1>)Iterator$.MODULE$.empty().next());
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anonfun$contains$1 extends AbstractFunction1<A, Object> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Object elem$5;
/*      */     
/*      */     public final boolean apply(Object x$3) {
/*  765 */       Object object = this.elem$5;
/*  765 */       return ((x$3 == object) ? true : ((x$3 == null) ? false : ((x$3 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)x$3, object) : ((x$3 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)x$3, object) : x$3.equals(object)))));
/*      */     }
/*      */     
/*      */     public Iterator$$anonfun$contains$1(Iterator $outer, Object elem$5) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$1 extends AbstractIterator<A> implements BufferedIterator<A> {
/*      */     private A hd;
/*      */     
/*      */     private boolean hdDefined;
/*      */     
/*      */     public BufferedIterator<A> buffered() {
/*  834 */       return BufferedIterator$class.buffered(this);
/*      */     }
/*      */     
/*      */     public Iterator$$anon$1(Iterator $outer) {
/*  834 */       BufferedIterator$class.$init$(this);
/*  836 */       this.hdDefined = false;
/*      */     }
/*      */     
/*      */     private A hd() {
/*      */       return this.hd;
/*      */     }
/*      */     
/*      */     private void hd_$eq(Object x$1) {
/*      */       this.hd = (A)x$1;
/*      */     }
/*      */     
/*      */     private boolean hdDefined() {
/*  836 */       return this.hdDefined;
/*      */     }
/*      */     
/*      */     private void hdDefined_$eq(boolean x$1) {
/*  836 */       this.hdDefined = x$1;
/*      */     }
/*      */     
/*      */     public A head() {
/*  839 */       if (!hdDefined()) {
/*  840 */         hd_$eq(next());
/*  841 */         hdDefined_$eq(true);
/*      */       } 
/*  843 */       return hd();
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  847 */       return (hdDefined() || this.$outer.hasNext());
/*      */     }
/*      */     
/*      */     public A next() {
/*  851 */       hdDefined_$eq(false);
/*  852 */       return hdDefined() ? hd() : 
/*  853 */         this.$outer.next();
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$24 extends AbstractIterator<B> {
/*      */     private Iterator<A> origElems;
/*      */     
/*      */     private int i;
/*      */     
/*      */     private final int from$1;
/*      */     
/*      */     private final Iterator patchElems$1;
/*      */     
/*      */     private final int replaced$1;
/*      */     
/*      */     public Iterator$$anon$24(Iterator<A> $outer, int from$1, Iterator patchElems$1, int replaced$1) {
/* 1079 */       this.origElems = $outer;
/* 1080 */       this.i = 0;
/*      */     }
/*      */     
/*      */     private Iterator<A> origElems() {
/*      */       return this.origElems;
/*      */     }
/*      */     
/*      */     private void origElems_$eq(Iterator<A> x$1) {
/*      */       this.origElems = x$1;
/*      */     }
/*      */     
/*      */     private int i() {
/* 1080 */       return this.i;
/*      */     }
/*      */     
/*      */     private void i_$eq(int x$1) {
/* 1080 */       this.i = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/* 1082 */       return (i() < this.from$1) ? origElems().hasNext() : (
/* 1083 */         (this.patchElems$1.hasNext() || origElems().hasNext()));
/*      */     }
/*      */     
/*      */     public B next() {
/* 1086 */       if (i() == this.from$1)
/* 1086 */         origElems_$eq(origElems().drop(this.replaced$1)); 
/* 1087 */       Object result = 
/* 1088 */         (i() >= this.from$1 && this.patchElems$1.hasNext()) ? 
/* 1089 */         this.patchElems$1.next() : origElems().next();
/* 1090 */       i_$eq(i() + 1);
/* 1091 */       return (B)result;
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anonfun$toStream$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     public final Stream<A> apply() {
/* 1143 */       return this.$outer.toStream();
/*      */     }
/*      */     
/*      */     public Iterator$$anonfun$toStream$1(Iterator $outer) {}
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\Iterator$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */