/*    */ package ch.qos.logback.core.property;
/*    */ 
/*    */ import ch.qos.logback.core.PropertyDefinerBase;
/*    */ import ch.qos.logback.core.util.Loader;
/*    */ import ch.qos.logback.core.util.OptionHelper;
/*    */ import java.net.URL;
/*    */ 
/*    */ public class ResourceExistsPropertyDefiner extends PropertyDefinerBase {
/*    */   String resourceStr;
/*    */   
/*    */   public String getResource() {
/* 25 */     return this.resourceStr;
/*    */   }
/*    */   
/*    */   public void setResource(String resource) {
/* 34 */     this.resourceStr = resource;
/*    */   }
/*    */   
/*    */   public String getPropertyValue() {
/* 44 */     if (OptionHelper.isEmpty(this.resourceStr)) {
/* 45 */       addError("The \"resource\" property must be set.");
/* 46 */       return null;
/*    */     } 
/* 49 */     URL resourceURL = Loader.getResourceBySelfClassLoader(this.resourceStr);
/* 50 */     return booleanAsStr((resourceURL != null));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\property\ResourceExistsPropertyDefiner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */