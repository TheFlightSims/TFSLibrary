/*    */ package scala.ref;
/*    */ 
/*    */ import java.lang.ref.ReferenceQueue;
/*    */ import java.lang.ref.WeakReference;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001]2A!\001\002\005\017\tAr+Z1l%\0264WM]3oG\026<\026\016\0365Xe\006\004\b/\032:\013\005\r!\021a\001:fM*\tQ!A\003tG\006d\027m\001\001\026\005!!2c\001\001\n=A\031!\002\005\n\016\003-Q!a\001\007\013\0055q\021\001\0027b]\036T\021aD\001\005U\0064\030-\003\002\022\027\tiq+Z1l%\0264WM]3oG\026\004\"a\005\013\r\001\021)Q\003\001b\001-\t\tA+\005\002\0307A\021\001$G\007\002\t%\021!\004\002\002\b\035>$\b.\0338h!\tAB$\003\002\036\t\t1\021I\\=SK\032\0042a\b\021\023\033\005\021\021BA\021\003\005Q\021VMZ3sK:\034WmV5uQ^\023\030\r\0359fe\"A1\005\001B\001B\003%!#A\003wC2,X\r\003\005&\001\t\005\t\025!\003'\003\025\tX/Z;f!\ryrEE\005\003Q\t\021aBU3gKJ,gnY3Rk\026,X\r\003\005+\001\t\025\r\021\"\001,\003\0359(/\0319qKJ,\022\001\f\t\004?5\022\022BA\t\003\021!y\003A!A!\002\023a\023\001C<sCB\004XM\035\021\t\013E\002A\021\001\032\002\rqJg.\033;?)\021\031D'\016\034\021\007}\001!\003C\003$a\001\007!\003C\003&a\001\007a\005C\003+a\001\007A\006")
/*    */ public class WeakReferenceWithWrapper<T> extends WeakReference<T> implements ReferenceWithWrapper<T> {
/*    */   private final WeakReference<T> wrapper;
/*    */   
/*    */   public WeakReference<T> wrapper() {
/* 40 */     return this.wrapper;
/*    */   }
/*    */   
/*    */   public WeakReferenceWithWrapper(Object value, ReferenceQueue<? super T> queue, WeakReference<T> wrapper) {
/* 40 */     super(
/* 41 */         (T)value, (queue == null) ? null : (ReferenceQueue)queue.underlying());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\ref\WeakReferenceWithWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */