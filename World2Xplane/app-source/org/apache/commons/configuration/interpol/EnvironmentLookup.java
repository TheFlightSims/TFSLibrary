/*    */ package org.apache.commons.configuration.interpol;
/*    */ 
/*    */ import org.apache.commons.configuration.EnvironmentConfiguration;
/*    */ import org.apache.commons.lang.text.StrLookup;
/*    */ 
/*    */ public class EnvironmentLookup extends StrLookup {
/* 51 */   private final EnvironmentConfiguration environmentConfig = new EnvironmentConfiguration();
/*    */   
/*    */   public String lookup(String key) {
/* 62 */     return this.environmentConfig.getString(key);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\interpol\EnvironmentLookup.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */