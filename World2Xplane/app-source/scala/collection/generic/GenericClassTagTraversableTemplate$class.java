/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ClassTag;
/*    */ 
/*    */ public abstract class GenericClassTagTraversableTemplate$class {
/*    */   public static void $init$(GenericClassTagTraversableTemplate $this) {}
/*    */   
/*    */   public static Builder genericClassTagBuilder(GenericClassTagTraversableTemplate $this, ClassTag tag) {
/* 26 */     return $this.classTagCompanion().newBuilder(tag);
/*    */   }
/*    */   
/*    */   public static GenericClassTagCompanion classManifestCompanion(GenericClassTagTraversableTemplate $this) {
/* 28 */     return $this.classTagCompanion();
/*    */   }
/*    */   
/*    */   public static Builder genericClassManifestBuilder(GenericClassTagTraversableTemplate $this, ClassTag manifest) {
/* 30 */     return $this.genericClassTagBuilder(manifest);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenericClassTagTraversableTemplate$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */