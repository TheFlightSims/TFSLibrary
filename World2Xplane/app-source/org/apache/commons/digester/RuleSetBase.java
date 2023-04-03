/*    */ package org.apache.commons.digester;
/*    */ 
/*    */ public abstract class RuleSetBase implements RuleSet {
/* 39 */   protected String namespaceURI = null;
/*    */   
/*    */   public String getNamespaceURI() {
/* 51 */     return this.namespaceURI;
/*    */   }
/*    */   
/*    */   public abstract void addRuleInstances(Digester paramDigester);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\RuleSetBase.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */