/*    */ package org.geotools.feature.visitor;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.Point;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public interface CalcResult {
/* 47 */   public static final CalcResult NULL_RESULT = new AbstractCalcResult() {
/*    */       public boolean isCompatible(CalcResult targetResults) {
/* 52 */         return true;
/*    */       }
/*    */       
/*    */       public CalcResult merge(CalcResult resultsToAdd) {
/* 59 */         return resultsToAdd;
/*    */       }
/*    */     };
/*    */   
/*    */   boolean isCompatible(CalcResult paramCalcResult);
/*    */   
/*    */   CalcResult merge(CalcResult paramCalcResult);
/*    */   
/*    */   Object getValue();
/*    */   
/*    */   int toInt();
/*    */   
/*    */   double toDouble();
/*    */   
/*    */   String toString();
/*    */   
/*    */   long toLong();
/*    */   
/*    */   float toFloat();
/*    */   
/*    */   Geometry toGeometry();
/*    */   
/*    */   Envelope toEnvelope();
/*    */   
/*    */   Point toPoint();
/*    */   
/*    */   Set toSet();
/*    */   
/*    */   List toList();
/*    */   
/*    */   Object[] toArray();
/*    */   
/*    */   Map toMap();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\CalcResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */