/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.operation.MathTransformProvider;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.ConicProjection;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class LambertConformal2SP extends LambertConformal {
/*     */   private static final long serialVersionUID = 7184350446186057405L;
/*     */   
/*     */   protected LambertConformal2SP(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  63 */     super(parameters);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/*  70 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = 3240860802816724947L;
/*     */     
/* 105 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Lambert_Conformal_Conic_2SP"), new NamedIdentifier(Citations.EPSG, "Lambert Conic Conformal (2SP)"), new NamedIdentifier(Citations.ESRI, "Lambert_Conformal_Conic"), new NamedIdentifier(Citations.ESRI, "Lambert_Conformal_Conic_2SP"), new NamedIdentifier(Citations.EPSG, "9802"), new NamedIdentifier(Citations.GEOTIFF, "CT_LambertConfConic_2SP"), new NamedIdentifier(Citations.GEOTIFF, "CT_LambertConfConic"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(119)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, LATITUDE_OF_ORIGIN, STANDARD_PARALLEL_1, STANDARD_PARALLEL_2, FALSE_EASTING, FALSE_NORTHING, SCALE_FACTOR });
/*     */     
/*     */     public Provider() {
/* 127 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<ConicProjection> getOperationType() {
/* 135 */       return ConicProjection.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 148 */       if (getParameter(STANDARD_PARALLEL_2, parameters) == null && getParameter(STANDARD_PARALLEL_1, parameters) == null && getParameter(LATITUDE_OF_ORIGIN, parameters) != null)
/* 152 */         return (MathTransform)new LambertConformal1SP(parameters); 
/* 153 */       if (Utilities.equals(MathTransformProvider.doubleValue(STANDARD_PARALLEL_1, parameters), MathTransformProvider.doubleValue(STANDARD_PARALLEL_2, parameters)) && Utilities.equals(MathTransformProvider.doubleValue(STANDARD_PARALLEL_1, parameters), MathTransformProvider.doubleValue(LATITUDE_OF_ORIGIN, parameters)))
/* 159 */         return (MathTransform)new LambertConformal1SP(parameters); 
/* 160 */       if (getParameter(STANDARD_PARALLEL_2, parameters) == null && Utilities.equals(MathTransformProvider.doubleValue(STANDARD_PARALLEL_1, parameters), MathTransformProvider.doubleValue(LATITUDE_OF_ORIGIN, parameters)))
/* 164 */         return (MathTransform)new LambertConformal1SP(parameters); 
/* 168 */       ParameterValue<Double> sp1 = getParameter(STANDARD_PARALLEL_1, parameters);
/* 169 */       ParameterValue<Double> sp2 = getParameter(STANDARD_PARALLEL_2, parameters);
/* 170 */       if (sp1 != null && sp2 != null && 
/* 171 */         sp1.doubleValue() < sp2.doubleValue()) {
/* 172 */         double temp = sp1.doubleValue();
/* 173 */         sp1.setValue(sp2.doubleValue());
/* 174 */         sp2.setValue(temp);
/*     */       } 
/* 178 */       return (MathTransform)new LambertConformal2SP(parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\LambertConformal2SP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */