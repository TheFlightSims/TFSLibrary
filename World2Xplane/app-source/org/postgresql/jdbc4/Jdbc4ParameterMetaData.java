/*    */ package org.postgresql.jdbc4;
/*    */ 
/*    */ import java.sql.ParameterMetaData;
/*    */ import org.postgresql.core.BaseConnection;
/*    */ 
/*    */ public class Jdbc4ParameterMetaData extends AbstractJdbc4ParameterMetaData implements ParameterMetaData {
/*    */   public Jdbc4ParameterMetaData(BaseConnection connection, int[] oids) {
/* 19 */     super(connection, oids);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc4\Jdbc4ParameterMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */