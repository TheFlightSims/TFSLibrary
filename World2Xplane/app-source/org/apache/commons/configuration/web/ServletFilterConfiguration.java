/*    */ package org.apache.commons.configuration.web;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import javax.servlet.FilterConfig;
/*    */ import org.apache.commons.collections.iterators.EnumerationIterator;
/*    */ 
/*    */ public class ServletFilterConfiguration extends BaseWebConfiguration {
/*    */   protected FilterConfig config;
/*    */   
/*    */   public ServletFilterConfiguration(FilterConfig config) {
/* 46 */     this.config = config;
/*    */   }
/*    */   
/*    */   public Object getProperty(String key) {
/* 51 */     return handleDelimiters(this.config.getInitParameter(key));
/*    */   }
/*    */   
/*    */   public Iterator getKeys() {
/* 56 */     return (Iterator)new EnumerationIterator(this.config.getInitParameterNames());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\web\ServletFilterConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */