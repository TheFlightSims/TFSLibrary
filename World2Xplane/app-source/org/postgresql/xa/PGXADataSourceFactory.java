/*    */ package org.postgresql.xa;
/*    */ 
/*    */ import java.util.Hashtable;
/*    */ import javax.naming.Context;
/*    */ import javax.naming.Name;
/*    */ import javax.naming.Reference;
/*    */ import org.postgresql.ds.common.BaseDataSource;
/*    */ import org.postgresql.ds.common.PGObjectFactory;
/*    */ 
/*    */ public class PGXADataSourceFactory extends PGObjectFactory {
/*    */   public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable environment) throws Exception {
/* 32 */     Reference ref = (Reference)obj;
/* 33 */     String className = ref.getClassName();
/* 34 */     if (className.equals("org.postgresql.xa.PGXADataSource"))
/* 36 */       return loadXADataSource(ref); 
/* 39 */     return null;
/*    */   }
/*    */   
/*    */   private Object loadXADataSource(Reference ref) {
/* 44 */     PGXADataSource ds = new PGXADataSource();
/* 45 */     return loadBaseDataSource((BaseDataSource)ds, ref);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\xa\PGXADataSourceFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */