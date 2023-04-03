/*    */ package org.apache.commons.pool;
/*    */ 
/*    */ public abstract class BasePoolableObjectFactory implements PoolableObjectFactory {
/*    */   public abstract Object makeObject() throws Exception;
/*    */   
/*    */   public void destroyObject(Object obj) throws Exception {}
/*    */   
/*    */   public boolean validateObject(Object obj) {
/* 46 */     return true;
/*    */   }
/*    */   
/*    */   public void activateObject(Object obj) throws Exception {}
/*    */   
/*    */   public void passivateObject(Object obj) throws Exception {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\BasePoolableObjectFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */