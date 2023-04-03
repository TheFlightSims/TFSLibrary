/*    */ package org.geotools.geometry.jts;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*    */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*    */ import com.vividsolutions.jts.geom.DefaultCoordinateSequenceFactory;
/*    */ import org.opengis.referencing.operation.MathTransform;
/*    */ import org.opengis.referencing.operation.TransformException;
/*    */ 
/*    */ public class PreciseCoordinateSequenceTransformer implements CoordinateSequenceTransformer {
/* 45 */   CoordinateSequenceFactory csFactory = (CoordinateSequenceFactory)DefaultCoordinateSequenceFactory.instance();
/*    */   
/*    */   double flatness;
/*    */   
/*    */   public CoordinateSequence transform(CoordinateSequence cs, MathTransform transform) throws TransformException {
/* 69 */     return null;
/*    */   }
/*    */   
/*    */   public double getFlatness() {
/* 73 */     return this.flatness;
/*    */   }
/*    */   
/*    */   public void setFlatness(double flatness) {
/* 76 */     this.flatness = flatness;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\PreciseCoordinateSequenceTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */