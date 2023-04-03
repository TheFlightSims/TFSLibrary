/*    */ package org.geotools.referencing.factory.epsg;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import org.geotools.factory.Hints;
/*    */ 
/*    */ public class AccessDialectEpsgFactory extends AbstractEpsgFactory {
/*    */   public AccessDialectEpsgFactory(Hints userHints, Connection connection) {
/* 46 */     super(userHints, connection);
/*    */   }
/*    */   
/*    */   protected String adaptSQL(String statement) {
/* 59 */     return statement;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\AccessDialectEpsgFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */