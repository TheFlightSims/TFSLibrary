/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ public class DatabasePreferences {
/*    */   private boolean validateSchemaVersion;
/*    */   
/*    */   private boolean allowIncorrectSchemaVersion;
/*    */   
/*    */   public DatabasePreferences(boolean validateSchemaVersion, boolean allowIncorrectSchemaVersion) {
/* 25 */     this.validateSchemaVersion = validateSchemaVersion;
/* 26 */     this.allowIncorrectSchemaVersion = allowIncorrectSchemaVersion;
/*    */   }
/*    */   
/*    */   public boolean getValidateSchemaVersion() {
/* 36 */     return this.validateSchemaVersion;
/*    */   }
/*    */   
/*    */   public void setValidateSchemaVersion(boolean validateSchemaVersion) {
/* 47 */     this.validateSchemaVersion = validateSchemaVersion;
/*    */   }
/*    */   
/*    */   public boolean getAllowIncorrectSchemaVersion() {
/* 57 */     return this.allowIncorrectSchemaVersion;
/*    */   }
/*    */   
/*    */   public void setAllowIncorrectSchemaVersion(boolean allowIncorrectSchemaVersion) {
/* 68 */     this.allowIncorrectSchemaVersion = allowIncorrectSchemaVersion;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\DatabasePreferences.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */