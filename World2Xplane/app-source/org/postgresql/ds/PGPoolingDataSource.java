/*    */ package org.postgresql.ds;
/*    */ 
/*    */ import javax.sql.DataSource;
/*    */ import org.postgresql.ds.jdbc4.AbstractJdbc4PoolingDataSource;
/*    */ 
/*    */ public class PGPoolingDataSource extends AbstractJdbc4PoolingDataSource implements DataSource {
/*    */   protected void addDataSource(String dataSourceName) {
/* 49 */     dataSources.put(dataSourceName, this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\ds\PGPoolingDataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */