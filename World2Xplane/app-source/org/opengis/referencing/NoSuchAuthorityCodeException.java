/*    */ package org.opengis.referencing;
/*    */ 
/*    */ public class NoSuchAuthorityCodeException extends FactoryException {
/*    */   private static final long serialVersionUID = -1573748311981746573L;
/*    */   
/*    */   private final String authority;
/*    */   
/*    */   private final String code;
/*    */   
/*    */   public NoSuchAuthorityCodeException(String message, String authority, String code) {
/* 51 */     super(message);
/* 52 */     this.authority = authority;
/* 53 */     this.code = code;
/*    */   }
/*    */   
/*    */   public String getAuthority() {
/* 62 */     return this.authority;
/*    */   }
/*    */   
/*    */   public String getAuthorityCode() {
/* 71 */     return this.code;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\NoSuchAuthorityCodeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */