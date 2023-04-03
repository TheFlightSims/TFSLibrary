/*    */ package scala.ref;
/*    */ 
/*    */ import java.lang.ref.Reference;
/*    */ import java.lang.ref.ReferenceQueue;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Some;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001a3A!\001\002\001\017\tq!+\0324fe\026t7-Z)vKV,'BA\002\005\003\r\021XM\032\006\002\013\005)1oY1mC\016\001QC\001\005\025'\t\001\021\002\005\002\013\0275\tA!\003\002\r\t\t1\021I\\=SK\032DQA\004\001\005\002=\ta\001P5oSRtD#\001\t\021\007E\001!#D\001\003!\t\031B\003\004\001\005\rU\001AQ1\001\027\005\005!\026CA\f\n!\tQ\001$\003\002\032\t\t9aj\034;iS:<\007\002C\016\001\005\004%\tA\001\017\002\025UtG-\032:ms&tw-F\001\036a\tqr\005E\002 K\031j\021\001\t\006\003\007\005R!AI\022\002\t1\fgn\032\006\002I\005!!.\031<b\023\t\t\001\005\005\002\024O\021I\001&KA\001\002\003\025\ta\f\002\004?\022\n\004B\002\026\001A\003%1&A\006v]\022,'\017\\=j]\036\004\003G\001\027/!\ryR%\f\t\003'9\"\021\002K\025\002\002\003\005)\021A\030\022\005]\021\002\"B\031\001\t\003\022\024\001\003;p'R\024\030N\\4\025\003M\002\"\001N\033\016\003\005J!AN\021\003\rM#(/\0338h\021\025A\004\001\"\005:\003\0359&/\0319qKJ$\"A\017!\021\007)YT(\003\002=\t\t1q\n\035;j_:\0042!\005 \023\023\ty$AA\005SK\032,'/\0328dK\")\021i\016a\001\005\006!!N]3ga\t\031e\tE\002 \t\026K!a\020\021\021\005M1E!C$A\003\003\005\tQ!\001I\005\ryFEM\t\003/%\003\"A\003&\n\005-#!aA!os\")Q\n\001C\001\035\006!\001o\0347m+\005Q\004\"\002)\001\t\003q\025A\002:f[>4X\rC\003Q\001\021\005!\013\006\002;'\")A+\025a\001+\0069A/[7f_V$\bC\001\006W\023\t9FA\001\003M_:<\007")
/*    */ public class ReferenceQueue<T> {
/* 18 */   private final ReferenceQueue<? extends T> underlying = new ReferenceQueue<T>();
/*    */   
/*    */   public ReferenceQueue<? extends T> underlying() {
/* 18 */     return this.underlying;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 19 */     return underlying().toString();
/*    */   }
/*    */   
/*    */   public Option<Reference<T>> Wrapper(Reference jref) {
/*    */     Some some;
/* 22 */     if (jref == null) {
/* 22 */       None$ none$ = None$.MODULE$;
/*    */     } else {
/* 24 */       some = new Some(((ReferenceWithWrapper)jref).wrapper());
/*    */     } 
/*    */     return (Option<Reference<T>>)some;
/*    */   }
/*    */   
/*    */   public Option<Reference<T>> poll() {
/* 27 */     return Wrapper(underlying().poll());
/*    */   }
/*    */   
/*    */   public Option<Reference<T>> remove() {
/* 28 */     return Wrapper(underlying().remove());
/*    */   }
/*    */   
/*    */   public Option<Reference<T>> remove(long timeout) {
/* 29 */     return Wrapper(underlying().remove(timeout));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\ref\ReferenceQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */