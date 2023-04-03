/*    */ package org.apache.commons.configuration.web;
/*    */ 
/*    */ import java.applet.Applet;
/*    */ import java.util.Iterator;
/*    */ import org.apache.commons.collections.iterators.ArrayIterator;
/*    */ 
/*    */ public class AppletConfiguration extends BaseWebConfiguration {
/*    */   protected Applet applet;
/*    */   
/*    */   public AppletConfiguration(Applet applet) {
/* 47 */     this.applet = applet;
/*    */   }
/*    */   
/*    */   public Object getProperty(String key) {
/* 52 */     return handleDelimiters(this.applet.getParameter(key));
/*    */   }
/*    */   
/*    */   public Iterator getKeys() {
/* 57 */     String[][] paramsInfo = this.applet.getParameterInfo();
/* 58 */     String[] keys = new String[(paramsInfo != null) ? paramsInfo.length : 0];
/* 59 */     for (int i = 0; i < keys.length; i++)
/* 61 */       keys[i] = paramsInfo[i][0]; 
/* 64 */     return (Iterator)new ArrayIterator(keys);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\web\AppletConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */