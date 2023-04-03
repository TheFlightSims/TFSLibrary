/*    */ package org.apache.commons.configuration.web;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import javax.servlet.Servlet;
/*    */ import javax.servlet.ServletContext;
/*    */ import org.apache.commons.collections.iterators.EnumerationIterator;
/*    */ 
/*    */ public class ServletContextConfiguration extends BaseWebConfiguration {
/*    */   protected ServletContext context;
/*    */   
/*    */   public ServletContextConfiguration(Servlet servlet) {
/* 48 */     this.context = servlet.getServletConfig().getServletContext();
/*    */   }
/*    */   
/*    */   public ServletContextConfiguration(ServletContext context) {
/* 59 */     this.context = context;
/*    */   }
/*    */   
/*    */   public Object getProperty(String key) {
/* 64 */     return handleDelimiters(this.context.getInitParameter(key));
/*    */   }
/*    */   
/*    */   public Iterator getKeys() {
/* 69 */     return (Iterator)new EnumerationIterator(this.context.getInitParameterNames());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\web\ServletContextConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */