/*    */ package org.geotools.referencing.factory.epsg;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import javax.sql.DataSource;
/*    */ import org.geotools.factory.Hints;
/*    */ 
/*    */ public class FactoryUsingSQL extends DirectEpsgFactory {
/*    */   public FactoryUsingSQL(Hints userHints, Connection connection) {
/* 51 */     super(userHints, connection);
/*    */   }
/*    */   
/*    */   public FactoryUsingSQL(Hints userHints, DataSource dataSource) {
/* 63 */     super(userHints, dataSource);
/*    */   }
/*    */   
/*    */   protected String adaptSQL(String statement) {
/* 76 */     return statement;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\FactoryUsingSQL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */