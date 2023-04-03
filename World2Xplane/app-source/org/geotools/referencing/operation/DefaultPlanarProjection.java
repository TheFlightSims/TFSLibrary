/*    */ package org.geotools.referencing.operation;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*    */ import org.opengis.referencing.operation.Conversion;
/*    */ import org.opengis.referencing.operation.MathTransform;
/*    */ import org.opengis.referencing.operation.OperationMethod;
/*    */ import org.opengis.referencing.operation.PlanarProjection;
/*    */ 
/*    */ public class DefaultPlanarProjection extends DefaultProjection implements PlanarProjection {
/*    */   private static final long serialVersionUID = 8171256287775067736L;
/*    */   
/*    */   public DefaultPlanarProjection(Conversion conversion, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform) {
/* 67 */     super(conversion, sourceCRS, targetCRS, transform);
/*    */   }
/*    */   
/*    */   public DefaultPlanarProjection(Map<String, ?> properties, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform, OperationMethod method) {
/* 88 */     super(properties, sourceCRS, targetCRS, transform, method);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\DefaultPlanarProjection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */