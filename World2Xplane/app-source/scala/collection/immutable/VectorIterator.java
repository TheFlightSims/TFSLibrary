/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Predef$;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.Iterator;
/*     */ import scala.math.package$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.RichInt$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001]4A!\001\002\001\023\tqa+Z2u_JLE/\032:bi>\024(BA\002\005\003%IW.\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!\006\002\013#M!\001aC\016\037!\raQbD\007\002\t%\021a\002\002\002\021\003\n\034HO]1di&#XM]1u_J\004\"\001E\t\r\001\0211!\003\001CC\002M\021\021!Q\t\003)a\001\"!\006\f\016\003\031I!a\006\004\003\0179{G\017[5oOB\021Q#G\005\0035\031\0211!\0218z!\raAdD\005\003;\021\021\001\"\023;fe\006$xN\035\t\004?\001\022S\"\001\002\n\005\005\022!!\004,fGR|'\017U8j]R,'O\013\002\020G-\nA\005\005\002&U5\taE\003\002(Q\005IQO\\2iK\016\\W\r\032\006\003S\031\t!\"\0318o_R\fG/[8o\023\tYcEA\tv]\016DWmY6fIZ\013'/[1oG\026D\001\"\f\001\003\002\003\006IAL\001\f?N$\030M\035;J]\022,\007\020\005\002\026_%\021\001G\002\002\004\023:$\b\002\003\032\001\005\003\005\013\021\002\030\002\023}+g\016Z%oI\026D\b\"\002\033\001\t\003)\024A\002\037j]&$h\bF\0027oa\0022a\b\001\020\021\025i3\0071\001/\021\025\0214\0071\001/\021\035Q\004\0011A\005\nm\n!B\0317pG.Le\016Z3y+\005q\003bB\037\001\001\004%IAP\001\017E2|7m[%oI\026Dx\fJ3r)\ty$\t\005\002\026\001&\021\021I\002\002\005+:LG\017C\004Dy\005\005\t\031\001\030\002\007a$\023\007\003\004F\001\001\006KAL\001\fE2|7m[%oI\026D\b\005C\004H\001\001\007I\021B\036\002\0051|\007bB%\001\001\004%IAS\001\007Y>|F%Z9\025\005}Z\005bB\"I\003\003\005\rA\f\005\007\033\002\001\013\025\002\030\002\0071|\007\005C\004P\001\001\007I\021B\036\002\021\025tG-\0238eKbDq!\025\001A\002\023%!+\001\007f]\022Le\016Z3y?\022*\027\017\006\002@'\"91\tUA\001\002\004q\003BB+\001A\003&a&A\005f]\022Le\016Z3yA!9q\013\001a\001\n\023Y\024!B3oI2{\007bB-\001\001\004%IAW\001\nK:$Gj\\0%KF$\"aP.\t\017\rC\026\021!a\001]!1Q\f\001Q!\n9\na!\0328e\031>\004\003\"B0\001\t\003\001\027a\0025bg:+\007\020^\013\002CB\021QCY\005\003G\032\021qAQ8pY\026\fg\016C\004f\001\001\007I\021\0021\002\021}C\027m\035(fqRDqa\032\001A\002\023%\001.\001\007`Q\006\034h*\032=u?\022*\027\017\006\002@S\"91IZA\001\002\004\t\007BB6\001A\003&\021-A\005`Q\006\034h*\032=uA!)Q\016\001C\001]\006!a.\032=u)\005y\001B\0029\001\t\003!1(A\013sK6\f\027N\\5oO\026cW-\\3oi\016{WO\034;\t\rI\004A\021\001\003t\003=\021X-\\1j]&twMV3di>\024X#\001;\021\007})x\"\003\002w\005\t1a+Z2u_J\004")
/*     */ public class VectorIterator<A> extends AbstractIterator<A> implements Iterator<A>, VectorPointer<A> {
/*     */   private final int _endIndex;
/*     */   
/*     */   private int blockIndex;
/*     */   
/*     */   private int lo;
/*     */   
/*     */   private int endIndex;
/*     */   
/*     */   private int endLo;
/*     */   
/*     */   private boolean _hasNext;
/*     */   
/*     */   private int depth;
/*     */   
/*     */   private Object[] display0;
/*     */   
/*     */   private Object[] display1;
/*     */   
/*     */   private Object[] display2;
/*     */   
/*     */   private Object[] display3;
/*     */   
/*     */   private Object[] display4;
/*     */   
/*     */   private Object[] display5;
/*     */   
/*     */   public int depth() {
/* 642 */     return this.depth;
/*     */   }
/*     */   
/*     */   public void depth_$eq(int x$1) {
/* 642 */     this.depth = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display0() {
/* 642 */     return this.display0;
/*     */   }
/*     */   
/*     */   public void display0_$eq(Object[] x$1) {
/* 642 */     this.display0 = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display1() {
/* 642 */     return this.display1;
/*     */   }
/*     */   
/*     */   public void display1_$eq(Object[] x$1) {
/* 642 */     this.display1 = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display2() {
/* 642 */     return this.display2;
/*     */   }
/*     */   
/*     */   public void display2_$eq(Object[] x$1) {
/* 642 */     this.display2 = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display3() {
/* 642 */     return this.display3;
/*     */   }
/*     */   
/*     */   public void display3_$eq(Object[] x$1) {
/* 642 */     this.display3 = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display4() {
/* 642 */     return this.display4;
/*     */   }
/*     */   
/*     */   public void display4_$eq(Object[] x$1) {
/* 642 */     this.display4 = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display5() {
/* 642 */     return this.display5;
/*     */   }
/*     */   
/*     */   public void display5_$eq(Object[] x$1) {
/* 642 */     this.display5 = x$1;
/*     */   }
/*     */   
/*     */   public final <U> void initFrom(VectorPointer that) {
/* 642 */     VectorPointer$class.initFrom(this, that);
/*     */   }
/*     */   
/*     */   public final <U> void initFrom(VectorPointer that, int depth) {
/* 642 */     VectorPointer$class.initFrom(this, that, depth);
/*     */   }
/*     */   
/*     */   public final A getElem(int index, int xor) {
/* 642 */     return (A)VectorPointer$class.getElem(this, index, xor);
/*     */   }
/*     */   
/*     */   public final void gotoPos(int index, int xor) {
/* 642 */     VectorPointer$class.gotoPos(this, index, xor);
/*     */   }
/*     */   
/*     */   public final void gotoNextBlockStart(int index, int xor) {
/* 642 */     VectorPointer$class.gotoNextBlockStart(this, index, xor);
/*     */   }
/*     */   
/*     */   public final void gotoNextBlockStartWritable(int index, int xor) {
/* 642 */     VectorPointer$class.gotoNextBlockStartWritable(this, index, xor);
/*     */   }
/*     */   
/*     */   public final Object[] copyOf(Object[] a) {
/* 642 */     return VectorPointer$class.copyOf(this, a);
/*     */   }
/*     */   
/*     */   public final Object[] nullSlotAndCopy(Object[] array, int index) {
/* 642 */     return VectorPointer$class.nullSlotAndCopy(this, array, index);
/*     */   }
/*     */   
/*     */   public final void stabilize(int index) {
/* 642 */     VectorPointer$class.stabilize(this, index);
/*     */   }
/*     */   
/*     */   public final void gotoPosWritable0(int newIndex, int xor) {
/* 642 */     VectorPointer$class.gotoPosWritable0(this, newIndex, xor);
/*     */   }
/*     */   
/*     */   public final void gotoPosWritable1(int oldIndex, int newIndex, int xor) {
/* 642 */     VectorPointer$class.gotoPosWritable1(this, oldIndex, newIndex, xor);
/*     */   }
/*     */   
/*     */   public final Object[] copyRange(Object[] array, int oldLeft, int newLeft) {
/* 642 */     return VectorPointer$class.copyRange(this, array, oldLeft, newLeft);
/*     */   }
/*     */   
/*     */   public final void gotoFreshPosWritable0(int oldIndex, int newIndex, int xor) {
/* 642 */     VectorPointer$class.gotoFreshPosWritable0(this, oldIndex, newIndex, xor);
/*     */   }
/*     */   
/*     */   public final void gotoFreshPosWritable1(int oldIndex, int newIndex, int xor) {
/* 642 */     VectorPointer$class.gotoFreshPosWritable1(this, oldIndex, newIndex, xor);
/*     */   }
/*     */   
/*     */   public void debug() {
/* 642 */     VectorPointer$class.debug(this);
/*     */   }
/*     */   
/*     */   public VectorIterator(int _startIndex, int _endIndex) {
/* 642 */     VectorPointer$class.$init$(this);
/* 647 */     this.blockIndex = _startIndex & (0x1F ^ 0xFFFFFFFF);
/* 648 */     this.lo = _startIndex & 0x1F;
/* 649 */     this.endIndex = _endIndex;
/* 651 */     this.endLo = package$.MODULE$.min(endIndex() - blockIndex(), 32);
/* 655 */     this._hasNext = (blockIndex() + lo() < endIndex());
/*     */   }
/*     */   
/*     */   private int blockIndex() {
/*     */     return this.blockIndex;
/*     */   }
/*     */   
/*     */   private void blockIndex_$eq(int x$1) {
/*     */     this.blockIndex = x$1;
/*     */   }
/*     */   
/*     */   private int lo() {
/*     */     return this.lo;
/*     */   }
/*     */   
/*     */   private void lo_$eq(int x$1) {
/*     */     this.lo = x$1;
/*     */   }
/*     */   
/*     */   private int endIndex() {
/*     */     return this.endIndex;
/*     */   }
/*     */   
/*     */   private void endIndex_$eq(int x$1) {
/*     */     this.endIndex = x$1;
/*     */   }
/*     */   
/*     */   private int endLo() {
/*     */     return this.endLo;
/*     */   }
/*     */   
/*     */   private void endLo_$eq(int x$1) {
/*     */     this.endLo = x$1;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*     */     return _hasNext();
/*     */   }
/*     */   
/*     */   private boolean _hasNext() {
/* 655 */     return this._hasNext;
/*     */   }
/*     */   
/*     */   private void _hasNext_$eq(boolean x$1) {
/* 655 */     this._hasNext = x$1;
/*     */   }
/*     */   
/*     */   public A next() {
/* 658 */     if (_hasNext()) {
/* 660 */       Object res = display0()[lo()];
/* 661 */       lo_$eq(lo() + 1);
/* 663 */       if (lo() == endLo())
/* 664 */         if (blockIndex() + lo() < endIndex()) {
/* 665 */           int newBlockIndex = blockIndex() + 32;
/* 666 */           gotoNextBlockStart(newBlockIndex, blockIndex() ^ newBlockIndex);
/* 668 */           blockIndex_$eq(newBlockIndex);
/* 669 */           endLo_$eq(package$.MODULE$.min(endIndex() - blockIndex(), 32));
/* 670 */           lo_$eq(0);
/*     */         } else {
/* 672 */           _hasNext_$eq(false);
/*     */         }  
/* 676 */       return (A)res;
/*     */     } 
/*     */     throw new NoSuchElementException("reached iterator end");
/*     */   }
/*     */   
/*     */   public int remainingElementCount() {
/* 679 */     int i = this._endIndex - blockIndex() + lo();
/* 679 */     Predef$ predef$ = Predef$.MODULE$;
/* 679 */     return RichInt$.MODULE$.max$extension(i, 0);
/*     */   }
/*     */   
/*     */   public Vector<A> remainingVector() {
/* 685 */     Vector<A> v = new Vector(blockIndex() + lo(), this._endIndex, blockIndex() + lo());
/* 686 */     v.initFrom(this);
/* 687 */     return v;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\VectorIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */