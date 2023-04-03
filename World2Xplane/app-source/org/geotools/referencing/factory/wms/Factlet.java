/*    */ package org.geotools.referencing.factory.wms;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import org.geotools.referencing.crs.DefaultGeographicCRS;
/*    */ import org.geotools.referencing.cs.DefaultCartesianCS;
/*    */ import org.geotools.referencing.factory.ReferencingFactoryContainer;
/*    */ import org.geotools.referencing.operation.DefiningConversion;
/*    */ import org.opengis.parameter.ParameterValueGroup;
/*    */ import org.opengis.referencing.FactoryException;
/*    */ import org.opengis.referencing.crs.GeographicCRS;
/*    */ import org.opengis.referencing.crs.ProjectedCRS;
/*    */ import org.opengis.referencing.cs.CartesianCS;
/*    */ import org.opengis.referencing.operation.Conversion;
/*    */ 
/*    */ abstract class Factlet {
/*    */   public abstract int code();
/*    */   
/*    */   public abstract String getName();
/*    */   
/*    */   public abstract String getClassification();
/*    */   
/*    */   public final ProjectedCRS create(Code code, ReferencingFactoryContainer factories) throws FactoryException {
/* 70 */     String classification = getClassification();
/* 72 */     ParameterValueGroup parameters = factories.getMathTransformFactory().getDefaultParameters(classification);
/* 73 */     setProjectionParameters(parameters, code);
/* 74 */     String name = getName();
/* 75 */     DefiningConversion conversion = new DefiningConversion(name, parameters);
/* 76 */     return factories.getCRSFactory().createProjectedCRS(Collections.singletonMap("name", name), (GeographicCRS)DefaultGeographicCRS.WGS84, (Conversion)conversion, (CartesianCS)DefaultCartesianCS.PROJECTED);
/*    */   }
/*    */   
/*    */   protected abstract void setProjectionParameters(ParameterValueGroup paramParameterValueGroup, Code paramCode);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\wms\Factlet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */