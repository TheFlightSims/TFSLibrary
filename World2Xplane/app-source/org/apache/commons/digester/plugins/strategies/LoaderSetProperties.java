/*    */ package org.apache.commons.digester.plugins.strategies;
/*    */ 
/*    */ import org.apache.commons.digester.Digester;
/*    */ import org.apache.commons.digester.plugins.RuleLoader;
/*    */ import org.apache.commons.logging.Log;
/*    */ 
/*    */ public class LoaderSetProperties extends RuleLoader {
/*    */   public void addRules(Digester digester, String path) {
/* 43 */     Log log = digester.getLogger();
/* 44 */     boolean debug = log.isDebugEnabled();
/* 45 */     if (debug)
/* 46 */       log.debug("LoaderSetProperties loading rules for plugin at path [" + path + "]"); 
/* 51 */     digester.addSetProperties(path);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\strategies\LoaderSetProperties.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */