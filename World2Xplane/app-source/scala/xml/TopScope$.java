/*    */ package scala.xml;
/*    */ 
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ 
/*    */ public final class TopScope$ extends NamespaceBinding {
/*    */   public static final TopScope$ MODULE$;
/*    */   
/*    */   private Object readResolve() {
/* 16 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private TopScope$() {
/* 16 */     super(null, null, null);
/* 16 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public String getURI(String prefix1) {
/* 21 */     String str = XML$.MODULE$.xml();
/* 21 */     if (prefix1 == null) {
/* 21 */       if (str != null);
/* 21 */     } else if (prefix1.equals(str)) {
/*    */     
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getPrefix(String uri1) {
/* 24 */     String str = XML$.MODULE$.namespace();
/* 24 */     if (uri1 == null) {
/* 24 */       if (str != null);
/* 24 */     } else if (uri1.equals(str)) {
/*    */     
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 26 */     return "";
/*    */   }
/*    */   
/*    */   public String buildString(NamespaceBinding stop) {
/* 28 */     return "";
/*    */   }
/*    */   
/*    */   public void buildString(StringBuilder sb, NamespaceBinding ignore) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\TopScope$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */