/*    */ package org.apache.commons.configuration.web;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import javax.servlet.Servlet;
/*    */ import javax.servlet.ServletConfig;
/*    */ import org.apache.commons.collections.iterators.EnumerationIterator;
/*    */ 
/*    */ public class ServletConfiguration extends BaseWebConfiguration {
/*    */   protected ServletConfig config;
/*    */   
/*    */   public ServletConfiguration(Servlet servlet) {
/* 48 */     this(servlet.getServletConfig());
/*    */   }
/*    */   
/*    */   public ServletConfiguration(ServletConfig config) {
/* 58 */     this.config = config;
/*    */   }
/*    */   
/*    */   public Object getProperty(String key) {
/* 63 */     return handleDelimiters(this.config.getInitParameter(key));
/*    */   }
/*    */   
/*    */   public Iterator getKeys() {
/* 68 */     return (Iterator)new EnumerationIterator(this.config.getInitParameterNames());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\web\ServletConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */