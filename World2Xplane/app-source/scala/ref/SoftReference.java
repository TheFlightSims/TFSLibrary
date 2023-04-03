/*    */ package scala.ref;
/*    */ 
/*    */ import java.lang.ref.Reference;
/*    */ import java.lang.ref.SoftReference;
/*    */ import scala.Function0;
/*    */ import scala.Option;
/*    */ import scala.Proxy;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001}2A!\001\002\001\017\ti1k\0344u%\0264WM]3oG\026T!a\001\003\002\007I,gMC\001\006\003\025\0318-\0317b\007\001)\"\001C\n\024\007\001IQ\002\005\002\013\0275\tA!\003\002\r\t\t1\021I\\=SK\032\0042AD\b\022\033\005\021\021B\001\t\003\005A\021VMZ3sK:\034Wm\026:baB,'\017\005\002\023'1\001AA\002\013\001\t\013\007QCA\001U#\t1\022\002\005\002\013/%\021\001\004\002\002\b\035>$\b.\0338h\021!Q\002A!A!\002\023\t\022!\002<bYV,\007\002\003\017\001\005\003\005\013\021B\017\002\013E,X-^3\021\0079q\022#\003\002 \005\tq!+\0324fe\026t7-Z)vKV,\007\"B\021\001\t\003\021\023A\002\037j]&$h\bF\002$I\025\0022A\004\001\022\021\025Q\002\0051\001\022\021\025a\002\0051\001\036\021\025\t\003\001\"\001()\t\031\003\006C\003\033M\001\007\021\003C\004+\001\t\007I\021A\026\002\025UtG-\032:ms&tw-F\001-a\tic\007E\002/iUj\021a\f\006\003\007AR!!\r\032\002\t1\fgn\032\006\002g\005!!.\031<b\023\t\tq\006\005\002\023m\021Iq\007OA\001\002\003\025\tA\020\002\004?\022\n\004BB\035\001A\003%!(A\006v]\022,'\017\\=j]\036\004\003GA\036>!\rqC\007\020\t\003%u\"\021b\016\035\002\002\003\005)\021\001 \022\005Y\t\002")
/*    */ public class SoftReference<T> implements ReferenceWrapper<T> {
/*    */   private final SoftReference<? extends T> underlying;
/*    */   
/*    */   public Option<T> get() {
/* 15 */     return ReferenceWrapper$class.get(this);
/*    */   }
/*    */   
/*    */   public T apply() {
/* 15 */     return (T)ReferenceWrapper$class.apply(this);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 15 */     ReferenceWrapper$class.clear(this);
/*    */   }
/*    */   
/*    */   public boolean enqueue() {
/* 15 */     return ReferenceWrapper$class.enqueue(this);
/*    */   }
/*    */   
/*    */   public boolean isEnqueued() {
/* 15 */     return ReferenceWrapper$class.isEnqueued(this);
/*    */   }
/*    */   
/*    */   public Reference<? extends T> self() {
/* 15 */     return ReferenceWrapper$class.self(this);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 15 */     return Proxy.class.hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object that) {
/* 15 */     return Proxy.class.equals(this, that);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 15 */     return Proxy.class.toString(this);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZ$sp() {
/* 15 */     return Function0.class.apply$mcZ$sp(this);
/*    */   }
/*    */   
/*    */   public byte apply$mcB$sp() {
/* 15 */     return Function0.class.apply$mcB$sp(this);
/*    */   }
/*    */   
/*    */   public char apply$mcC$sp() {
/* 15 */     return Function0.class.apply$mcC$sp(this);
/*    */   }
/*    */   
/*    */   public double apply$mcD$sp() {
/* 15 */     return Function0.class.apply$mcD$sp(this);
/*    */   }
/*    */   
/*    */   public float apply$mcF$sp() {
/* 15 */     return Function0.class.apply$mcF$sp(this);
/*    */   }
/*    */   
/*    */   public int apply$mcI$sp() {
/* 15 */     return Function0.class.apply$mcI$sp(this);
/*    */   }
/*    */   
/*    */   public long apply$mcJ$sp() {
/* 15 */     return Function0.class.apply$mcJ$sp(this);
/*    */   }
/*    */   
/*    */   public short apply$mcS$sp() {
/* 15 */     return Function0.class.apply$mcS$sp(this);
/*    */   }
/*    */   
/*    */   public void apply$mcV$sp() {
/* 15 */     Function0.class.apply$mcV$sp(this);
/*    */   }
/*    */   
/*    */   public SoftReference(Object value, ReferenceQueue<? extends T> queue) {
/* 15 */     Function0.class.$init$(this);
/* 15 */     Reference$class.$init$(this);
/* 15 */     Proxy.class.$init$(this);
/* 15 */     ReferenceWrapper$class.$init$(this);
/* 17 */     this.underlying = 
/* 18 */       new SoftReferenceWithWrapper<T>((T)value, queue, this);
/*    */   }
/*    */   
/*    */   public SoftReference(Object value) {
/*    */     this((T)value, null);
/*    */   }
/*    */   
/*    */   public SoftReference<? extends T> underlying() {
/*    */     return this.underlying;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\ref\SoftReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */