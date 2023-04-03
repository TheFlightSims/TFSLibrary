/*    */ package org.apache.commons.pool;
/*    */ 
/*    */ public abstract class BaseKeyedPoolableObjectFactory implements KeyedPoolableObjectFactory {
/*    */   public abstract Object makeObject(Object paramObject) throws Exception;
/*    */   
/*    */   public void destroyObject(Object key, Object obj) throws Exception {}
/*    */   
/*    */   public boolean validateObject(Object key, Object obj) {
/* 66 */     return true;
/*    */   }
/*    */   
/*    */   public void activateObject(Object key, Object obj) throws Exception {}
/*    */   
/*    */   public void passivateObject(Object key, Object obj) throws Exception {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\BaseKeyedPoolableObjectFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */