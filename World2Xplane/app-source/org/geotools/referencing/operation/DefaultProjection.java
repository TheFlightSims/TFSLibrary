/*    */ package org.geotools.referencing.operation;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*    */ import org.opengis.referencing.operation.Conversion;
/*    */ import org.opengis.referencing.operation.MathTransform;
/*    */ import org.opengis.referencing.operation.OperationMethod;
/*    */ import org.opengis.referencing.operation.Projection;
/*    */ 
/*    */ public class DefaultProjection extends DefaultConversion implements Projection {
/*    */   private static final long serialVersionUID = -7176751851369816864L;
/*    */   
/*    */   public DefaultProjection(Conversion conversion, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform) {
/* 77 */     super(conversion, sourceCRS, targetCRS, transform);
/*    */   }
/*    */   
/*    */   public DefaultProjection(Map<String, ?> properties, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform, OperationMethod method) {
/* 98 */     super(properties, sourceCRS, targetCRS, transform, method);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\DefaultProjection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */