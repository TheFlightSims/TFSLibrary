/*    */ package org.geotools.text;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.Properties;
/*    */ import org.geotools.util.GrowableInternationalString;
/*    */ import org.geotools.util.ResourceInternationalString;
/*    */ import org.geotools.util.SimpleInternationalString;
/*    */ import org.opengis.util.InternationalString;
/*    */ 
/*    */ public class Text {
/*    */   public static InternationalString text(String english) {
/* 50 */     return (InternationalString)new SimpleInternationalString(english);
/*    */   }
/*    */   
/*    */   public static InternationalString text(String key, String resourceBundle) {
/* 61 */     return (InternationalString)new ResourceInternationalString(resourceBundle, key);
/*    */   }
/*    */   
/*    */   public static InternationalString text(String key, Map<String, String> translations) {
/* 65 */     GrowableInternationalString text = new GrowableInternationalString();
/* 67 */     for (Map.Entry<String, String> entry : translations.entrySet())
/* 68 */       text.add(key, entry.getKey(), entry.getValue()); 
/* 70 */     return (InternationalString)text;
/*    */   }
/*    */   
/*    */   public static InternationalString text(String key, Properties properties) {
/* 74 */     GrowableInternationalString text = new GrowableInternationalString();
/* 76 */     for (Map.Entry<Object, Object> entry : properties.entrySet())
/* 77 */       text.add(key, (String)entry.getKey(), (String)entry.getValue()); 
/* 79 */     return (InternationalString)text;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\text\Text.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */