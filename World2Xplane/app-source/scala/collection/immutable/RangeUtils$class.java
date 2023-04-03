/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.math.package$;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public abstract class RangeUtils$class {
/*    */   public static void $init$(RangeUtils $this) {}
/*    */   
/*    */   private static final int inclusiveLast(RangeUtils $this) {
/* 26 */     long size = $this.end() - $this.start();
/* 27 */     return (int)(size / $this.step() * $this.step() + $this.start());
/*    */   }
/*    */   
/*    */   public static final int _last(RangeUtils $this) {
/* 34 */     int inclast = inclusiveLast($this);
/* 38 */     return $this.inclusive() ? (($this.step() == 1 || $this.step() == -1) ? $this.end() : 
/* 39 */       inclusiveLast($this)) : (($this.step() == 1 || $this.step() == -1) ? ($this.end() - $this.step()) : ((($this.end() - $this.start()) % $this.step() == 0L) ? (inclast - $this.step()) : inclast));
/*    */   }
/*    */   
/*    */   public static final void _foreach(RangeUtils $this, Function1 f) {
/* 42 */     if ($this._length() > 0) {
/* 43 */       int i = $this.start();
/* 44 */       int last = $this._last();
/* 45 */       while (i != last) {
/* 46 */         f.apply(BoxesRunTime.boxToInteger(i));
/* 47 */         i += $this.step();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static final int _length(RangeUtils $this) {
/* 52 */     return $this.inclusive() ? 
/*    */       
/* 61 */       (int)(((($this.end() > $this.start()) ? true : false) == (($this.step() > 0) ? true : false) || $this.start() == $this.end()) ? (($this._last() - $this.start()) / $this.step() + 1L) : 0L) : (int)(((($this.end() > $this.start()) ? true : false) == (($this.step() > 0) ? true : false) && $this.start() != $this.end()) ? (($this._last() - $this.start()) / $this.step() + 1L) : 0L);
/*    */   }
/*    */   
/*    */   public static final int _apply(RangeUtils $this, int idx) {
/* 65 */     if (idx < 0 || idx >= $this._length())
/* 65 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString()); 
/* 66 */     return $this.start() + idx * $this.step();
/*    */   }
/*    */   
/*    */   private static int locationAfterN(RangeUtils $this, int n) {
/* 70 */     return (n > 0) ? (
/* 71 */       ($this.step() > 0) ? 
/* 72 */       (int)package$.MODULE$.min($this.start() + $this.step() * n, $this._last()) : 
/*    */       
/* 74 */       (int)package$.MODULE$.max($this.start() + $this.step() * n, $this._last())) : 
/*    */       
/* 76 */       $this.start();
/*    */   }
/*    */   
/*    */   public static final RangeUtils _take(RangeUtils<RangeUtils> $this, int n) {
/* 80 */     return (n > 0 && $this._length() > 0) ? 
/* 81 */       $this.create($this.start(), locationAfterN($this, n), $this.step(), true) : 
/*    */       
/* 83 */       $this.create($this.start(), $this.start(), $this.step(), false);
/*    */   }
/*    */   
/*    */   public static final RangeUtils _drop(RangeUtils<RangeUtils> $this, int n) {
/* 86 */     return $this.create(locationAfterN($this, n), $this.end(), $this.step(), $this.inclusive());
/*    */   }
/*    */   
/*    */   public static final RangeUtils _slice(RangeUtils $this, int from, int until) {
/* 87 */     return $this._drop(from)._take(until - from);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\RangeUtils$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */