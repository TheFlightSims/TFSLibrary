/*    */ package scala.ref;
/*    */ 
/*    */ import java.lang.ref.Reference;
/*    */ import java.util.NoSuchElementException;
/*    */ import scala.Option;
/*    */ import scala.Option$;
/*    */ 
/*    */ public abstract class ReferenceWrapper$class {
/*    */   public static void $init$(ReferenceWrapper $this) {}
/*    */   
/*    */   public static Option get(ReferenceWrapper $this) {
/* 17 */     return Option$.MODULE$.apply($this.underlying().get());
/*    */   }
/*    */   
/*    */   public static Object apply(ReferenceWrapper $this) {
/* 19 */     Object ret = $this.underlying().get();
/* 20 */     if (ret == null)
/* 20 */       throw new NoSuchElementException(); 
/* 21 */     return ret;
/*    */   }
/*    */   
/*    */   public static void clear(ReferenceWrapper $this) {
/* 23 */     $this.underlying().clear();
/*    */   }
/*    */   
/*    */   public static boolean enqueue(ReferenceWrapper $this) {
/* 24 */     return $this.underlying().enqueue();
/*    */   }
/*    */   
/*    */   public static boolean isEnqueued(ReferenceWrapper $this) {
/* 25 */     return $this.underlying().isEnqueued();
/*    */   }
/*    */   
/*    */   public static Reference self(ReferenceWrapper $this) {
/* 26 */     return $this.underlying();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\ref\ReferenceWrapper$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */