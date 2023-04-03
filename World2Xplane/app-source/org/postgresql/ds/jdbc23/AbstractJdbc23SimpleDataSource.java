/*    */ package org.postgresql.ds.jdbc23;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.Serializable;
/*    */ import org.postgresql.Driver;
/*    */ import org.postgresql.ds.common.BaseDataSource;
/*    */ 
/*    */ public abstract class AbstractJdbc23SimpleDataSource extends BaseDataSource implements Serializable {
/*    */   public String getDescription() {
/* 34 */     return "Non-Pooling DataSource from " + Driver.getVersion();
/*    */   }
/*    */   
/*    */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 39 */     writeBaseObject(out);
/*    */   }
/*    */   
/*    */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 44 */     readBaseObject(in);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\ds\jdbc23\AbstractJdbc23SimpleDataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */