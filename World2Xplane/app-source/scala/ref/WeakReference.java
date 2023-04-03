/*    */ package scala.ref;
/*    */ 
/*    */ import java.lang.ref.Reference;
/*    */ import java.lang.ref.WeakReference;
/*    */ import scala.Function0;
/*    */ import scala.Option;
/*    */ import scala.Proxy;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001i3A!\001\002\001\017\tiq+Z1l%\0264WM]3oG\026T!a\001\003\002\007I,gMC\001\006\003\025\0318-\0317b\007\001)\"\001C\n\024\007\001IQ\002\005\002\013\0275\tA!\003\002\r\t\t1\021I\\=SK\032\0042AD\b\022\033\005\021\021B\001\t\003\005A\021VMZ3sK:\034Wm\026:baB,'\017\005\002\023'1\001AA\002\013\001\t\013\007QCA\001U#\t1\022\002\005\002\013/%\021\001\004\002\002\b\035>$\b.\0338h\021!Q\002A!A!\002\023\t\022!\002<bYV,\007\002\003\017\001\005\003\005\013\021B\017\002\013E,X-^3\021\0079q\022#\003\002 \005\tq!+\0324fe\026t7-Z)vKV,\007\"B\021\001\t\003\021\023A\002\037j]&$h\bF\002$I\025\0022A\004\001\022\021\025Q\002\0051\001\022\021\025a\002\0051\001\036\021\025\t\003\001\"\001()\t\031\003\006C\003\033M\001\007\021\003C\004+\001\t\007I\021A\026\002\025UtG-\032:ms&tw-F\001-a\tic\007E\002/iUj\021a\f\006\003\007AR!!\r\032\002\t1\fgn\032\006\002g\005!!.\031<b\023\t\tq\006\005\002\023m\021Iq\007OA\001\002\003\025\tA\020\002\004?\022\n\004BB\035\001A\003%!(A\006v]\022,'\017\\=j]\036\004\003GA\036>!\rqC\007\020\t\003%u\"\021b\016\035\002\002\003\005)\021\001 \022\005Y\tr!\002!\003\021\003\t\025!D,fC.\024VMZ3sK:\034W\r\005\002\017\005\032)\021A\001E\001\007N\021!)\003\005\006C\t#\t!\022\013\002\003\")qI\021C\001\021\006)\021\r\0359msV\021\021\n\024\013\003\0256\0032A\004\001L!\t\021B\nB\003\025\r\n\007Q\003C\003\033\r\002\0071\nC\003P\005\022\005\001+A\004v]\006\004\b\017\\=\026\005E3FC\001*X!\rQ1+V\005\003)\022\021aa\0249uS>t\007C\001\nW\t\025!bJ1\001\026\021\025Af\n1\001Z\003\t9(\017E\002\017\001U\003")
/*    */ public class WeakReference<T> implements ReferenceWrapper<T> {
/*    */   private final WeakReference<? extends T> underlying;
/*    */   
/*    */   public Option<T> get() {
/* 18 */     return ReferenceWrapper$class.get(this);
/*    */   }
/*    */   
/*    */   public T apply() {
/* 18 */     return (T)ReferenceWrapper$class.apply(this);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 18 */     ReferenceWrapper$class.clear(this);
/*    */   }
/*    */   
/*    */   public boolean enqueue() {
/* 18 */     return ReferenceWrapper$class.enqueue(this);
/*    */   }
/*    */   
/*    */   public boolean isEnqueued() {
/* 18 */     return ReferenceWrapper$class.isEnqueued(this);
/*    */   }
/*    */   
/*    */   public Reference<? extends T> self() {
/* 18 */     return ReferenceWrapper$class.self(this);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 18 */     return Proxy.class.hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object that) {
/* 18 */     return Proxy.class.equals(this, that);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 18 */     return Proxy.class.toString(this);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZ$sp() {
/* 18 */     return Function0.class.apply$mcZ$sp(this);
/*    */   }
/*    */   
/*    */   public byte apply$mcB$sp() {
/* 18 */     return Function0.class.apply$mcB$sp(this);
/*    */   }
/*    */   
/*    */   public char apply$mcC$sp() {
/* 18 */     return Function0.class.apply$mcC$sp(this);
/*    */   }
/*    */   
/*    */   public double apply$mcD$sp() {
/* 18 */     return Function0.class.apply$mcD$sp(this);
/*    */   }
/*    */   
/*    */   public float apply$mcF$sp() {
/* 18 */     return Function0.class.apply$mcF$sp(this);
/*    */   }
/*    */   
/*    */   public int apply$mcI$sp() {
/* 18 */     return Function0.class.apply$mcI$sp(this);
/*    */   }
/*    */   
/*    */   public long apply$mcJ$sp() {
/* 18 */     return Function0.class.apply$mcJ$sp(this);
/*    */   }
/*    */   
/*    */   public short apply$mcS$sp() {
/* 18 */     return Function0.class.apply$mcS$sp(this);
/*    */   }
/*    */   
/*    */   public void apply$mcV$sp() {
/* 18 */     Function0.class.apply$mcV$sp(this);
/*    */   }
/*    */   
/*    */   public WeakReference(Object value, ReferenceQueue<? extends T> queue) {
/* 18 */     Function0.class.$init$(this);
/* 18 */     Reference$class.$init$(this);
/* 18 */     Proxy.class.$init$(this);
/* 18 */     ReferenceWrapper$class.$init$(this);
/* 20 */     this.underlying = 
/* 21 */       new WeakReferenceWithWrapper<T>((T)value, queue, this);
/*    */   }
/*    */   
/*    */   public WeakReference(Object value) {
/*    */     this((T)value, null);
/*    */   }
/*    */   
/*    */   public WeakReference<? extends T> underlying() {
/*    */     return this.underlying;
/*    */   }
/*    */   
/*    */   public static <T> Option<T> unapply(WeakReference<T> paramWeakReference) {
/*    */     return WeakReference$.MODULE$.unapply(paramWeakReference);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\ref\WeakReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */