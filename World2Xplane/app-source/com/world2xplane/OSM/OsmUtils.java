/*    */ package com.world2xplane.OSM;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.DecimalFormatSymbols;
/*    */ import java.text.NumberFormat;
/*    */ import java.util.Locale;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class OsmUtils {
/* 39 */   private static Logger log = LoggerFactory.getLogger(OsmUtils.class);
/*    */   
/* 41 */   private static DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
/*    */   
/* 44 */   private static NumberFormat format = new DecimalFormat("#####.###########", formatSymbols);
/*    */   
/*    */   public static Float parseHeight(String heightStr) throws Exception {
/* 53 */     if (heightStr == null)
/* 54 */       return null; 
/* 57 */     heightStr = heightStr.replaceAll(" ", "");
/* 58 */     heightStr = heightStr.trim().toLowerCase();
/* 59 */     heightStr = heightStr.replaceAll(",", ".");
/* 61 */     if (heightStr.endsWith("m")) {
/* 62 */       heightStr = heightStr.substring(0, heightStr.length() - 1);
/* 63 */       return Float.valueOf(format.parse(heightStr).floatValue());
/*    */     } 
/* 65 */     if (heightStr.endsWith("ft")) {
/* 68 */       heightStr = heightStr.substring(0, heightStr.length() - 1);
/* 69 */       return Float.valueOf(0.3048F * format.parse(heightStr).floatValue());
/*    */     } 
/* 70 */     if (heightStr.endsWith("feet")) {
/* 73 */       heightStr = heightStr.substring(0, heightStr.length() - 4);
/* 74 */       return Float.valueOf(0.3048F * format.parse(heightStr).floatValue());
/*    */     } 
/* 77 */     return Float.valueOf(format.parse(heightStr).floatValue());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\OSM\OsmUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */