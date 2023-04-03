/*    */ package org.geotools.data;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class SchemaNotFoundException extends IOException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   static final String NOT_FOUND = "Feature type could not be found for ";
/*    */   
/*    */   private String typeName;
/*    */   
/*    */   public SchemaNotFoundException(String typeName) {
/* 37 */     super("Feature type could not be found for " + typeName);
/* 38 */     typeName = null;
/*    */   }
/*    */   
/*    */   public SchemaNotFoundException(String typeName, Throwable t) {
/* 42 */     this(typeName);
/* 43 */     initCause(t);
/*    */   }
/*    */   
/*    */   String getTypeName() {
/* 52 */     return this.typeName;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\SchemaNotFoundException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */