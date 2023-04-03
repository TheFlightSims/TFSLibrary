/*    */ package scala.ref;
/*    */ 
/*    */ import java.lang.ref.PhantomReference;
/*    */ import java.lang.ref.ReferenceQueue;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001]2A!\001\002\005\017\tY\002\013[1oi>l'+\0324fe\026t7-Z,ji\"<&/\0319qKJT!a\001\003\002\007I,gMC\001\006\003\025\0318-\0317b\007\001)\"\001\003\013\024\007\001Ia\004E\002\013!Ii\021a\003\006\003\0071Q!!\004\b\002\t1\fgn\032\006\002\037\005!!.\031<b\023\t\t2B\001\tQQ\006tGo\\7SK\032,'/\0328dKB\0211\003\006\007\001\t\025)\002A1\001\027\005\005!\026CA\f\034!\tA\022$D\001\005\023\tQBAA\004O_RD\027N\\4\021\005aa\022BA\017\005\005\031\te.\037*fMB\031q\004\t\n\016\003\tI!!\t\002\003)I+g-\032:f]\016,w+\033;i/J\f\007\017]3s\021!\031\003A!A!\002\023\021\022!\002<bYV,\007\002C\023\001\005\003\005\013\021\002\024\002\013E,X-^3\021\007}9##\003\002)\005\tq!+\0324fe\026t7-Z)vKV,\007\002\003\026\001\005\013\007I\021A\026\002\017]\024\030\r\0359feV\tA\006E\002 [II!!\005\002\t\021=\002!\021!Q\001\n1\n\001b\036:baB,'\017\t\005\006c\001!\tAM\001\007y%t\027\016\036 \025\tM\"TG\016\t\004?\001\021\002\"B\0221\001\004\021\002\"B\0231\001\0041\003\"\002\0261\001\004a\003")
/*    */ public class PhantomReferenceWithWrapper<T> extends PhantomReference<T> implements ReferenceWithWrapper<T> {
/*    */   private final PhantomReference<T> wrapper;
/*    */   
/*    */   public PhantomReference<T> wrapper() {
/* 23 */     return this.wrapper;
/*    */   }
/*    */   
/*    */   public PhantomReferenceWithWrapper(Object value, ReferenceQueue<? super T> queue, PhantomReference<T> wrapper) {
/* 23 */     super(
/* 24 */         (T)value, (ReferenceQueue)queue.underlying());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\ref\PhantomReferenceWithWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */