/*    */ package scala.reflect;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.runtime.Nothing$;
/*    */ 
/*    */ public final class NoManifest$ implements OptManifest<Nothing$>, Serializable {
/*    */   public static final NoManifest$ MODULE$;
/*    */   
/*    */   private NoManifest$() {
/* 15 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 15 */     return MODULE$;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 16 */     return "<?>";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\NoManifest$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */