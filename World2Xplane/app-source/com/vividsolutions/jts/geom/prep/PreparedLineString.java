/*    */ package com.vividsolutions.jts.geom.prep;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.Lineal;
/*    */ import com.vividsolutions.jts.noding.FastSegmentSetIntersectionFinder;
/*    */ import com.vividsolutions.jts.noding.SegmentStringUtil;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PreparedLineString extends BasicPreparedGeometry {
/* 49 */   private FastSegmentSetIntersectionFinder segIntFinder = null;
/*    */   
/*    */   public PreparedLineString(Lineal line) {
/* 52 */     super((Geometry)line);
/*    */   }
/*    */   
/*    */   public synchronized FastSegmentSetIntersectionFinder getIntersectionFinder() {
/* 63 */     if (this.segIntFinder == null)
/* 64 */       this.segIntFinder = new FastSegmentSetIntersectionFinder(SegmentStringUtil.extractSegmentStrings(getGeometry())); 
/* 65 */     return this.segIntFinder;
/*    */   }
/*    */   
/*    */   public boolean intersects(Geometry g) {
/* 70 */     if (!envelopesIntersect(g))
/* 70 */       return false; 
/* 71 */     return PreparedLineStringIntersects.intersects(this, g);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\prep\PreparedLineString.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */