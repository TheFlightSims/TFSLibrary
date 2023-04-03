/*    */ package scala.collection.convert;
/*    */ 
/*    */ import java.util.concurrent.ConcurrentMap;
/*    */ import scala.collection.mutable.ConcurrentMap;
/*    */ 
/*    */ public abstract class LowPriorityWrapAsScala$class {
/*    */   public static void $init$(WrapAsScala $this) {}
/*    */   
/*    */   public static ConcurrentMap mapAsScalaDeprecatedConcurrentMap(WrapAsScala $this, ConcurrentMap<?, ?> m) {
/* 35 */     return $this.asScalaConcurrentMap(m);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\LowPriorityWrapAsScala$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */