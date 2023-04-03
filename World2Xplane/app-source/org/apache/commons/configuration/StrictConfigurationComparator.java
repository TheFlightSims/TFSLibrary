/*    */ package org.apache.commons.configuration;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class StrictConfigurationComparator implements ConfigurationComparator {
/*    */   public boolean compare(Configuration a, Configuration b) {
/* 51 */     if (a == null && b == null)
/* 53 */       return true; 
/* 55 */     if (a == null || b == null)
/* 57 */       return false; 
/* 60 */     for (Iterator iterator1 = a.getKeys(); iterator1.hasNext(); ) {
/* 62 */       String key = iterator1.next();
/* 63 */       Object value = a.getProperty(key);
/* 64 */       if (!value.equals(b.getProperty(key)))
/* 66 */         return false; 
/*    */     } 
/* 70 */     for (Iterator keys = b.getKeys(); keys.hasNext(); ) {
/* 72 */       String key = keys.next();
/* 73 */       Object value = b.getProperty(key);
/* 74 */       if (!value.equals(a.getProperty(key)))
/* 76 */         return false; 
/*    */     } 
/* 80 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\StrictConfigurationComparator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */