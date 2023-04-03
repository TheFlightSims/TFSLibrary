/*    */ package org.apache.commons.digester.plugins.strategies;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import org.apache.commons.digester.Digester;
/*    */ import org.apache.commons.digester.plugins.PluginException;
/*    */ import org.apache.commons.digester.plugins.RuleLoader;
/*    */ import org.apache.commons.digester.xmlrules.FromXmlRuleSet;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.xml.sax.InputSource;
/*    */ 
/*    */ public class LoaderFromStream extends RuleLoader {
/*    */   private byte[] input;
/*    */   
/*    */   private FromXmlRuleSet ruleSet;
/*    */   
/*    */   public LoaderFromStream(InputStream s) throws Exception {
/* 48 */     load(s);
/*    */   }
/*    */   
/*    */   private void load(InputStream s) throws IOException {
/* 59 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 60 */     byte[] buf = new byte[256];
/*    */     while (true) {
/* 62 */       int i = s.read(buf);
/* 63 */       if (i == -1)
/*    */         break; 
/* 65 */       baos.write(buf, 0, i);
/*    */     } 
/* 67 */     this.input = baos.toByteArray();
/*    */   }
/*    */   
/*    */   public void addRules(Digester d, String path) throws PluginException {
/* 75 */     Log log = d.getLogger();
/* 76 */     boolean debug = log.isDebugEnabled();
/* 77 */     if (debug)
/* 78 */       log.debug("LoaderFromStream: loading rules for plugin at path [" + path + "]"); 
/* 89 */     InputSource source = new InputSource(new ByteArrayInputStream(this.input));
/* 90 */     FromXmlRuleSet ruleSet = new FromXmlRuleSet(source);
/* 91 */     ruleSet.addRuleInstances(d, path);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\strategies\LoaderFromStream.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */