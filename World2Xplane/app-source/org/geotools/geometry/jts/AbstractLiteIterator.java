/*    */ package org.geotools.geometry.jts;
/*    */ 
/*    */ import java.awt.geom.PathIterator;
/*    */ import java.util.logging.Logger;
/*    */ import org.geotools.util.logging.Logging;
/*    */ 
/*    */ public abstract class AbstractLiteIterator implements PathIterator {
/* 36 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.rendering");
/*    */   
/* 38 */   protected double[] dcoords = new double[2];
/*    */   
/*    */   public int currentSegment(float[] coords) {
/* 44 */     int result = currentSegment(this.dcoords);
/* 45 */     coords[0] = (float)this.dcoords[0];
/* 46 */     coords[1] = (float)this.dcoords[1];
/* 48 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\AbstractLiteIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */