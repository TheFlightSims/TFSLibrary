/*    */ package org.openstreetmap.osmosis.core.database;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public enum DatabaseType {
/* 13 */   POSTGRESQL, MYSQL;
/*    */   
/*    */   public static DatabaseType fromString(String name) {
/* 29 */     if (POSTGRESQL.toString().equalsIgnoreCase(name))
/* 30 */       return POSTGRESQL; 
/* 31 */     if (MYSQL.toString().equalsIgnoreCase(name))
/* 32 */       return MYSQL; 
/* 34 */     throw new OsmosisRuntimeException("The database type name " + name + " is not recognized.");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\DatabaseType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */