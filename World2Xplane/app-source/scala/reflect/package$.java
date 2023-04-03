/*    */ package scala.reflect;
/*    */ 
/*    */ public final class package$ {
/*    */   public static final package$ MODULE$;
/*    */   
/*    */   private final ClassManifestFactory$ ClassManifest;
/*    */   
/*    */   private final ManifestFactory$ Manifest;
/*    */   
/*    */   private package$() {
/*  3 */     MODULE$ = this;
/* 34 */     this.ClassManifest = ClassManifestFactory$.MODULE$;
/* 41 */     this.Manifest = ManifestFactory$.MODULE$;
/*    */   }
/*    */   
/*    */   public ClassManifestFactory$ ClassManifest() {
/*    */     return this.ClassManifest;
/*    */   }
/*    */   
/*    */   public ManifestFactory$ Manifest() {
/* 41 */     return this.Manifest;
/*    */   }
/*    */   
/*    */   public <T> ClassTag<T> classTag(ClassTag<T> ctag) {
/* 43 */     return ctag;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\package$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */