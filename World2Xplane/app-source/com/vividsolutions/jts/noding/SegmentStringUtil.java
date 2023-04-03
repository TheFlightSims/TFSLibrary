/*    */ package com.vividsolutions.jts.noding;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.LineString;
/*    */ import com.vividsolutions.jts.geom.util.LinearComponentExtracter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SegmentStringUtil {
/*    */   public static List extractSegmentStrings(Geometry geom) {
/* 59 */     List<NodedSegmentString> segStr = new ArrayList();
/* 60 */     List lines = LinearComponentExtracter.getLines(geom);
/* 61 */     for (Iterator<LineString> i = lines.iterator(); i.hasNext(); ) {
/* 62 */       LineString line = i.next();
/* 63 */       Coordinate[] pts = line.getCoordinates();
/* 64 */       segStr.add(new NodedSegmentString(pts, geom));
/*    */     } 
/* 66 */     return segStr;
/*    */   }
/*    */   
/*    */   public static String toString(List segStrings) {
/* 71 */     StringBuffer buf = new StringBuffer();
/* 72 */     for (Iterator<SegmentString> i = segStrings.iterator(); i.hasNext(); ) {
/* 73 */       SegmentString segStr = i.next();
/* 74 */       buf.append(segStr.toString());
/* 75 */       buf.append("\n");
/*    */     } 
/* 78 */     return buf.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\SegmentStringUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */