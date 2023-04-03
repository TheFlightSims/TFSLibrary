/*    */ package org.geotools.referencing.operation;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*    */ import org.opengis.referencing.operation.MathTransform;
/*    */ import org.opengis.referencing.operation.OperationMethod;
/*    */ import org.opengis.referencing.operation.Transformation;
/*    */ 
/*    */ public class DefaultTransformation extends DefaultOperation implements Transformation {
/*    */   private static final long serialVersionUID = -7486704846151648971L;
/*    */   
/*    */   public DefaultTransformation(Map<String, ?> properties, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform, OperationMethod method) {
/* 70 */     super(properties, sourceCRS, targetCRS, transform, method);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\DefaultTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */