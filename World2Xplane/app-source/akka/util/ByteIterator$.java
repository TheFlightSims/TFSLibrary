/*     */ package akka.util;
/*     */ 
/*     */ import scala.Function2;
/*     */ import scala.Serializable;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BooleanRef;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ByteRef;
/*     */ import scala.runtime.CharRef;
/*     */ import scala.runtime.DoubleRef;
/*     */ import scala.runtime.FloatRef;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.LongRef;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.ShortRef;
/*     */ 
/*     */ public final class ByteIterator$ {
/*     */   public static final ByteIterator$ MODULE$;
/*     */   
/*     */   private ByteIterator$() {
/*  17 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public class ByteIterator$$anonfun$indexOf$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final byte elem$1;
/*     */     
/*     */     public final boolean apply(byte x$29) {
/* 444 */       return (x$29 == this.elem$1);
/*     */     }
/*     */     
/*     */     public ByteIterator$$anonfun$indexOf$1(ByteIterator $outer, byte elem$1) {}
/*     */   }
/*     */   
/*     */   public class ByteIterator$$anonfun$indexOf$2 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object elem$2;
/*     */     
/*     */     public final boolean apply(byte x$30) {
/* 446 */       return BoxesRunTime.equals(BoxesRunTime.boxToByte(x$30), this.elem$2);
/*     */     }
/*     */     
/*     */     public ByteIterator$$anonfun$indexOf$2(ByteIterator $outer, Object elem$2) {}
/*     */   }
/*     */   
/*     */   public class ByteIterator$$anonfun$foldLeft$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 op$1;
/*     */     
/*     */     private final ObjectRef acc$1;
/*     */     
/*     */     public final void apply(byte byte) {
/* 457 */       this.acc$1.elem = this.op$1.apply(this.acc$1.elem, BoxesRunTime.boxToByte(byte));
/*     */     }
/*     */     
/*     */     public ByteIterator$$anonfun$foldLeft$1(ByteIterator $outer, Function2 op$1, ObjectRef acc$1) {}
/*     */   }
/*     */   
/*     */   public class ByteIterator$$anonfun$foldLeft$mZc$sp$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     private final Function2 op$2;
/*     */     
/*     */     private final BooleanRef acc$2;
/*     */     
/*     */     public final void apply(byte byte) {
/* 457 */       this.acc$2.elem = BoxesRunTime.unboxToBoolean(this.op$2.apply(BoxesRunTime.boxToBoolean(this.acc$2.elem), BoxesRunTime.boxToByte(byte)));
/*     */     }
/*     */     
/*     */     public ByteIterator$$anonfun$foldLeft$mZc$sp$1(ByteIterator $outer, Function2 op$2, BooleanRef acc$2) {}
/*     */   }
/*     */   
/*     */   public class ByteIterator$$anonfun$foldLeft$mBc$sp$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     private final Function2 op$3;
/*     */     
/*     */     private final ByteRef acc$3;
/*     */     
/*     */     public final void apply(byte byte) {
/* 457 */       this.acc$3.elem = BoxesRunTime.unboxToByte(this.op$3.apply(BoxesRunTime.boxToByte(this.acc$3.elem), BoxesRunTime.boxToByte(byte)));
/*     */     }
/*     */     
/*     */     public ByteIterator$$anonfun$foldLeft$mBc$sp$1(ByteIterator $outer, Function2 op$3, ByteRef acc$3) {}
/*     */   }
/*     */   
/*     */   public class ByteIterator$$anonfun$foldLeft$mCc$sp$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     private final Function2 op$4;
/*     */     
/*     */     private final CharRef acc$4;
/*     */     
/*     */     public final void apply(byte byte) {
/* 457 */       this.acc$4.elem = BoxesRunTime.unboxToChar(this.op$4.apply(BoxesRunTime.boxToCharacter(this.acc$4.elem), BoxesRunTime.boxToByte(byte)));
/*     */     }
/*     */     
/*     */     public ByteIterator$$anonfun$foldLeft$mCc$sp$1(ByteIterator $outer, Function2 op$4, CharRef acc$4) {}
/*     */   }
/*     */   
/*     */   public class ByteIterator$$anonfun$foldLeft$mDc$sp$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     private final Function2 op$5;
/*     */     
/*     */     private final DoubleRef acc$5;
/*     */     
/*     */     public final void apply(byte byte) {
/* 457 */       this.acc$5.elem = BoxesRunTime.unboxToDouble(this.op$5.apply(BoxesRunTime.boxToDouble(this.acc$5.elem), BoxesRunTime.boxToByte(byte)));
/*     */     }
/*     */     
/*     */     public ByteIterator$$anonfun$foldLeft$mDc$sp$1(ByteIterator $outer, Function2 op$5, DoubleRef acc$5) {}
/*     */   }
/*     */   
/*     */   public class ByteIterator$$anonfun$foldLeft$mFc$sp$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     private final Function2 op$6;
/*     */     
/*     */     private final FloatRef acc$6;
/*     */     
/*     */     public final void apply(byte byte) {
/* 457 */       this.acc$6.elem = BoxesRunTime.unboxToFloat(this.op$6.apply(BoxesRunTime.boxToFloat(this.acc$6.elem), BoxesRunTime.boxToByte(byte)));
/*     */     }
/*     */     
/*     */     public ByteIterator$$anonfun$foldLeft$mFc$sp$1(ByteIterator $outer, Function2 op$6, FloatRef acc$6) {}
/*     */   }
/*     */   
/*     */   public class ByteIterator$$anonfun$foldLeft$mIc$sp$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     private final Function2 op$7;
/*     */     
/*     */     private final IntRef acc$7;
/*     */     
/*     */     public final void apply(byte byte) {
/* 457 */       this.acc$7.elem = BoxesRunTime.unboxToInt(this.op$7.apply(BoxesRunTime.boxToInteger(this.acc$7.elem), BoxesRunTime.boxToByte(byte)));
/*     */     }
/*     */     
/*     */     public ByteIterator$$anonfun$foldLeft$mIc$sp$1(ByteIterator $outer, Function2 op$7, IntRef acc$7) {}
/*     */   }
/*     */   
/*     */   public class ByteIterator$$anonfun$foldLeft$mJc$sp$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     private final Function2 op$8;
/*     */     
/*     */     private final LongRef acc$8;
/*     */     
/*     */     public final void apply(byte byte) {
/* 457 */       this.acc$8.elem = BoxesRunTime.unboxToLong(this.op$8.apply(BoxesRunTime.boxToLong(this.acc$8.elem), BoxesRunTime.boxToByte(byte)));
/*     */     }
/*     */     
/*     */     public ByteIterator$$anonfun$foldLeft$mJc$sp$1(ByteIterator $outer, Function2 op$8, LongRef acc$8) {}
/*     */   }
/*     */   
/*     */   public class ByteIterator$$anonfun$foldLeft$mSc$sp$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     private final Function2 op$9;
/*     */     
/*     */     private final ShortRef acc$9;
/*     */     
/*     */     public final void apply(byte byte) {
/* 457 */       this.acc$9.elem = BoxesRunTime.unboxToShort(this.op$9.apply(BoxesRunTime.boxToShort(this.acc$9.elem), BoxesRunTime.boxToByte(byte)));
/*     */     }
/*     */     
/*     */     public ByteIterator$$anonfun$foldLeft$mSc$sp$1(ByteIterator $outer, Function2 op$9, ShortRef acc$9) {}
/*     */   }
/*     */   
/*     */   public class ByteIterator$$anonfun$foldLeft$mVc$sp$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     private final Function2 op$10;
/*     */     
/*     */     private final ObjectRef acc$10;
/*     */     
/*     */     public final void apply(byte byte) {
/* 457 */       this.acc$10.elem = this.op$10.apply(this.acc$10.elem, BoxesRunTime.boxToByte(byte));
/*     */     }
/*     */     
/*     */     public ByteIterator$$anonfun$foldLeft$mVc$sp$1(ByteIterator $outer, Function2 op$10, ObjectRef acc$10) {}
/*     */   }
/*     */   
/*     */   public class ByteIterator$$anonfun$getLongPart$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final LongRef x$63;
/*     */     
/*     */     public final void apply(int x$31) {
/* 532 */       apply$mcVI$sp(x$31);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int x$31) {
/* 532 */       this.x$63.elem = this.x$63.elem << 8L | (this.$outer.next() & 0xFF);
/*     */     }
/*     */     
/*     */     public ByteIterator$$anonfun$getLongPart$1(ByteIterator $outer, LongRef x$63) {}
/*     */   }
/*     */   
/*     */   public class ByteIterator$$anonfun$getLongPart$2 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final LongRef x$64;
/*     */     
/*     */     public final void apply(int i) {
/* 536 */       apply$mcVI$sp(i);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int i) {
/* 536 */       this.x$64.elem |= ((this.$outer.next() & 0xFF) << 8 * i);
/*     */     }
/*     */     
/*     */     public ByteIterator$$anonfun$getLongPart$2(ByteIterator $outer, LongRef x$64) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\ByteIterator$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */