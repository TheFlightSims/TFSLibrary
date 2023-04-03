/*    */ package org.apache.commons.configuration.web;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import javax.servlet.ServletRequest;
/*    */ import org.apache.commons.collections.iterators.EnumerationIterator;
/*    */ 
/*    */ public class ServletRequestConfiguration extends BaseWebConfiguration {
/*    */   protected ServletRequest request;
/*    */   
/*    */   public ServletRequestConfiguration(ServletRequest request) {
/* 50 */     this.request = request;
/*    */   }
/*    */   
/*    */   public Object getProperty(String key) {
/* 55 */     String[] values = this.request.getParameterValues(key);
/* 57 */     if (values == null || values.length == 0)
/* 59 */       return null; 
/* 61 */     if (values.length == 1)
/* 63 */       return handleDelimiters(values[0]); 
/* 68 */     List result = new ArrayList(values.length);
/* 69 */     for (int i = 0; i < values.length; i++) {
/* 71 */       Object val = handleDelimiters(values[i]);
/* 72 */       if (val instanceof Collection) {
/* 74 */         result.addAll((Collection)val);
/*    */       } else {
/* 78 */         result.add(val);
/*    */       } 
/*    */     } 
/* 81 */     return result;
/*    */   }
/*    */   
/*    */   public Iterator getKeys() {
/* 87 */     return (Iterator)new EnumerationIterator(this.request.getParameterNames());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\web\ServletRequestConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */