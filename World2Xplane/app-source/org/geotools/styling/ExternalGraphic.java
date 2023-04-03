/*    */ package org.geotools.styling;
/*    */ 
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import java.util.Map;
/*    */ import org.opengis.style.ExternalGraphic;
/*    */ 
/*    */ public interface ExternalGraphic extends ExternalGraphic, Symbol {
/* 80 */   public static final ExternalGraphic[] EXTERNAL_GRAPHICS_EMPTY = new ExternalGraphic[0];
/*    */   
/*    */   void setURI(String paramString);
/*    */   
/*    */   URL getLocation() throws MalformedURLException;
/*    */   
/*    */   void setLocation(URL paramURL);
/*    */   
/*    */   void setFormat(String paramString);
/*    */   
/*    */   void setCustomProperties(Map<String, Object> paramMap);
/*    */   
/*    */   Map<String, Object> getCustomProperties();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ExternalGraphic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */