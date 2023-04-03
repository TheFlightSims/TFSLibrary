/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t4A!\001\002\003\023\tia+Z2u_J\024U/\0337eKJT!a\001\003\002\023%lW.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001QC\001\006\030'\021\0011b\004\023\021\0051iQ\"\001\004\n\00591!AB!osJ+g\r\005\003\021'U\001S\"A\t\013\005I!\021aB7vi\006\024G.Z\005\003)E\021qAQ;jY\022,'\017\005\002\027/1\001A!\002\r\001\005\004I\"!A!\022\005ii\002C\001\007\034\023\tabAA\004O_RD\027N\\4\021\0051q\022BA\020\007\005\r\te.\037\t\004C\t*R\"\001\002\n\005\r\022!A\002,fGR|'\017E\002\"K\035J!A\n\002\003\033Y+7\r^8s!>Lg\016^3sU\t)\002fK\001*!\tQs&D\001,\025\taS&A\005v]\016DWmY6fI*\021aFB\001\013C:tw\016^1uS>t\027B\001\031,\005E)hn\0315fG.,GMV1sS\006t7-\032\005\006e\001!\taM\001\007y%t\027\016\036 \025\003Q\0022!\t\001\026\021\0351\004\0011A\005\n]\n!B\0317pG.Le\016Z3y+\005A\004C\001\007:\023\tQdAA\002J]RDq\001\020\001A\002\023%Q(\001\bcY>\0347.\0238eKb|F%Z9\025\005y\n\005C\001\007@\023\t\001eA\001\003V]&$\bb\002\"<\003\003\005\r\001O\001\004q\022\n\004B\002#\001A\003&\001(A\006cY>\0347.\0238eKb\004\003b\002$\001\001\004%IaN\001\003Y>Dq\001\023\001A\002\023%\021*\001\004m_~#S-\035\013\003})CqAQ$\002\002\003\007\001\b\003\004M\001\001\006K\001O\001\004Y>\004\003\"\002(\001\t\003y\025\001\003\023qYV\034H%Z9\025\005A\013V\"\001\001\t\013Ik\005\031A\013\002\t\025dW-\034\005\006)\002!\t%V\001\016IAdWo\035\023qYV\034H%Z9\025\005A3\006\"B,T\001\004A\026A\001=t!\rI&,F\007\002\t%\0211\f\002\002\020)J\fg/\032:tC\ndWm\0248dK\")Q\f\001C\001=\0061!/Z:vYR$\022\001\t\005\006A\002!\t!Y\001\006G2,\027M\035\013\002}\001")
/*     */ public final class VectorBuilder<A> implements Builder<A, Vector<A>>, VectorPointer<A> {
/*     */   private int blockIndex;
/*     */   
/*     */   private int lo;
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
/* 692 */     return this.depth;
/*     */   }
/*     */   
/*     */   public void depth_$eq(int x$1) {
/* 692 */     this.depth = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display0() {
/* 692 */     return this.display0;
/*     */   }
/*     */   
/*     */   public void display0_$eq(Object[] x$1) {
/* 692 */     this.display0 = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display1() {
/* 692 */     return this.display1;
/*     */   }
/*     */   
/*     */   public void display1_$eq(Object[] x$1) {
/* 692 */     this.display1 = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display2() {
/* 692 */     return this.display2;
/*     */   }
/*     */   
/*     */   public void display2_$eq(Object[] x$1) {
/* 692 */     this.display2 = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display3() {
/* 692 */     return this.display3;
/*     */   }
/*     */   
/*     */   public void display3_$eq(Object[] x$1) {
/* 692 */     this.display3 = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display4() {
/* 692 */     return this.display4;
/*     */   }
/*     */   
/*     */   public void display4_$eq(Object[] x$1) {
/* 692 */     this.display4 = x$1;
/*     */   }
/*     */   
/*     */   public Object[] display5() {
/* 692 */     return this.display5;
/*     */   }
/*     */   
/*     */   public void display5_$eq(Object[] x$1) {
/* 692 */     this.display5 = x$1;
/*     */   }
/*     */   
/*     */   public final <U> void initFrom(VectorPointer that) {
/* 692 */     VectorPointer$class.initFrom(this, that);
/*     */   }
/*     */   
/*     */   public final <U> void initFrom(VectorPointer that, int depth) {
/* 692 */     VectorPointer$class.initFrom(this, that, depth);
/*     */   }
/*     */   
/*     */   public final A getElem(int index, int xor) {
/* 692 */     return (A)VectorPointer$class.getElem(this, index, xor);
/*     */   }
/*     */   
/*     */   public final void gotoPos(int index, int xor) {
/* 692 */     VectorPointer$class.gotoPos(this, index, xor);
/*     */   }
/*     */   
/*     */   public final void gotoNextBlockStart(int index, int xor) {
/* 692 */     VectorPointer$class.gotoNextBlockStart(this, index, xor);
/*     */   }
/*     */   
/*     */   public final void gotoNextBlockStartWritable(int index, int xor) {
/* 692 */     VectorPointer$class.gotoNextBlockStartWritable(this, index, xor);
/*     */   }
/*     */   
/*     */   public final Object[] copyOf(Object[] a) {
/* 692 */     return VectorPointer$class.copyOf(this, a);
/*     */   }
/*     */   
/*     */   public final Object[] nullSlotAndCopy(Object[] array, int index) {
/* 692 */     return VectorPointer$class.nullSlotAndCopy(this, array, index);
/*     */   }
/*     */   
/*     */   public final void stabilize(int index) {
/* 692 */     VectorPointer$class.stabilize(this, index);
/*     */   }
/*     */   
/*     */   public final void gotoPosWritable0(int newIndex, int xor) {
/* 692 */     VectorPointer$class.gotoPosWritable0(this, newIndex, xor);
/*     */   }
/*     */   
/*     */   public final void gotoPosWritable1(int oldIndex, int newIndex, int xor) {
/* 692 */     VectorPointer$class.gotoPosWritable1(this, oldIndex, newIndex, xor);
/*     */   }
/*     */   
/*     */   public final Object[] copyRange(Object[] array, int oldLeft, int newLeft) {
/* 692 */     return VectorPointer$class.copyRange(this, array, oldLeft, newLeft);
/*     */   }
/*     */   
/*     */   public final void gotoFreshPosWritable0(int oldIndex, int newIndex, int xor) {
/* 692 */     VectorPointer$class.gotoFreshPosWritable0(this, oldIndex, newIndex, xor);
/*     */   }
/*     */   
/*     */   public final void gotoFreshPosWritable1(int oldIndex, int newIndex, int xor) {
/* 692 */     VectorPointer$class.gotoFreshPosWritable1(this, oldIndex, newIndex, xor);
/*     */   }
/*     */   
/*     */   public void debug() {
/* 692 */     VectorPointer$class.debug(this);
/*     */   }
/*     */   
/*     */   public void sizeHint(int size) {
/* 692 */     Builder.class.sizeHint(this, size);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll) {
/* 692 */     Builder.class.sizeHint(this, coll);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll, int delta) {
/* 692 */     Builder.class.sizeHint(this, coll, delta);
/*     */   }
/*     */   
/*     */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 692 */     Builder.class.sizeHintBounded(this, size, boundingColl);
/*     */   }
/*     */   
/*     */   public <NewTo> Builder<A, NewTo> mapResult(Function1 f) {
/* 692 */     return Builder.class.mapResult(this, f);
/*     */   }
/*     */   
/*     */   public Growable<A> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 692 */     return Growable.class.$plus$eq((Growable)this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public VectorBuilder() {
/* 692 */     Growable.class.$init$((Growable)this);
/* 692 */     Builder.class.$init$(this);
/* 692 */     VectorPointer$class.$init$(this);
/* 697 */     display0_$eq(new Object[32]);
/* 698 */     depth_$eq(1);
/* 700 */     this.blockIndex = 0;
/* 701 */     this.lo = 0;
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
/* 701 */     return this.lo;
/*     */   }
/*     */   
/*     */   private void lo_$eq(int x$1) {
/* 701 */     this.lo = x$1;
/*     */   }
/*     */   
/*     */   public VectorBuilder<A> $plus$eq(Object elem) {
/* 704 */     if (lo() >= (display0()).length) {
/* 705 */       int newBlockIndex = blockIndex() + 32;
/* 706 */       gotoNextBlockStartWritable(newBlockIndex, blockIndex() ^ newBlockIndex);
/* 707 */       blockIndex_$eq(newBlockIndex);
/* 708 */       lo_$eq(0);
/*     */     } 
/* 710 */     display0()[lo()] = elem;
/* 711 */     lo_$eq(lo() + 1);
/* 712 */     return this;
/*     */   }
/*     */   
/*     */   public VectorBuilder<A> $plus$plus$eq(TraversableOnce xs) {
/* 716 */     return (VectorBuilder<A>)Growable.class.$plus$plus$eq((Growable)this, xs);
/*     */   }
/*     */   
/*     */   public Vector<A> result() {
/* 719 */     int size = blockIndex() + lo();
/* 720 */     if (size == 0)
/* 721 */       return Vector$.MODULE$.empty(); 
/* 722 */     Vector<A> s = new Vector(0, size, 0);
/* 723 */     s.initFrom(this);
/* 724 */     if (depth() > 1)
/* 724 */       s.gotoPos(0, size - 1); 
/* 725 */     return s;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 729 */     display0_$eq(new Object[32]);
/* 730 */     depth_$eq(1);
/* 731 */     blockIndex_$eq(0);
/* 732 */     lo_$eq(0);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\VectorBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */