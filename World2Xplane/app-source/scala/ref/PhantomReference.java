/*    */ package scala.ref;
/*    */ 
/*    */ import java.lang.ref.PhantomReference;
/*    */ import java.lang.ref.Reference;
/*    */ import scala.Function0;
/*    */ import scala.Option;
/*    */ import scala.Proxy;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001q2A!\001\002\001\017\t\001\002\013[1oi>l'+\0324fe\026t7-\032\006\003\007\021\t1A]3g\025\005)\021!B:dC2\f7\001A\013\003\021M\0312\001A\005\016!\tQ1\"D\001\005\023\taAA\001\004B]f\024VM\032\t\004\035=\tR\"\001\002\n\005A\021!\001\005*fM\026\024XM\\2f/J\f\007\017]3s!\t\0212\003\004\001\005\rQ\001AQ1\001\026\005\005!\026C\001\f\n!\tQq#\003\002\031\t\t9aj\034;iS:<\007\002\003\016\001\005\003\005\013\021B\t\002\013Y\fG.^3\t\021q\001!\021!Q\001\nu\tQ!];fk\026\0042A\004\020\022\023\ty\"A\001\bSK\032,'/\0328dKF+X-^3\t\013\005\002A\021\001\022\002\rqJg.\033;?)\r\031C%\n\t\004\035\001\t\002\"\002\016!\001\004\t\002\"\002\017!\001\004i\002bB\024\001\005\004%\t\001K\001\013k:$WM\0357zS:<W#A\0251\005)\032\004cA\0262e5\tAF\003\002\004[)\021afL\001\005Y\006twMC\0011\003\021Q\027M^1\n\005\005a\003C\001\n4\t%!T'!A\001\002\013\0051HA\002`IEBaA\016\001!\002\0239\024aC;oI\026\024H._5oO\002\002$\001\017\036\021\007-\n\024\b\005\002\023u\021IA'NA\001\002\003\025\taO\t\003-E\001")
/*    */ public class PhantomReference<T> implements ReferenceWrapper<T> {
/*    */   private final PhantomReference<? extends T> underlying;
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
/*    */   public PhantomReference(Object value, ReferenceQueue<? extends T> queue) {
/* 15 */     Function0.class.$init$(this);
/* 15 */     Reference$class.$init$(this);
/* 15 */     Proxy.class.$init$(this);
/* 15 */     ReferenceWrapper$class.$init$(this);
/* 16 */     this.underlying = 
/* 17 */       new PhantomReferenceWithWrapper<T>((T)value, queue, this);
/*    */   }
/*    */   
/*    */   public PhantomReference<? extends T> underlying() {
/*    */     return this.underlying;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\ref\PhantomReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */