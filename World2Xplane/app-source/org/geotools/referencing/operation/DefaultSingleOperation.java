/*    */ package org.geotools.referencing.operation;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*    */ import org.opengis.referencing.operation.Conversion;
/*    */ import org.opengis.referencing.operation.MathTransform;
/*    */ import org.opengis.referencing.operation.SingleOperation;
/*    */ 
/*    */ public class DefaultSingleOperation extends AbstractCoordinateOperation implements SingleOperation {
/*    */   private static final long serialVersionUID = 672935231344137185L;
/*    */   
/*    */   DefaultSingleOperation(Conversion definition, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform) {
/* 56 */     super(definition, sourceCRS, targetCRS, transform);
/*    */   }
/*    */   
/*    */   public DefaultSingleOperation(Map<String, ?> properties, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform) {
/* 74 */     super(properties, sourceCRS, targetCRS, transform);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\DefaultSingleOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */