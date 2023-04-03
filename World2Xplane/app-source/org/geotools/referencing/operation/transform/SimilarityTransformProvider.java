/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import javax.measure.quantity.Dimensionless;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.SI;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.operation.MathTransformProvider;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class SimilarityTransformProvider extends MathTransformProvider {
/*     */   private static final long serialVersionUID = -7413519919588731455L;
/*     */   
/*  51 */   public static final ParameterDescriptor<Double> TRANSLATION_1 = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.EPSG, "Ordinate 1 of evaluation point in target CRS"), new NamedIdentifier(Citations.EPSG, "8621") }0.0D, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, SI.METER);
/*     */   
/*  61 */   public static final ParameterDescriptor<Double> TRANSLATION_2 = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.EPSG, "Ordinate 2 of evaluation point in target CRS"), new NamedIdentifier(Citations.EPSG, "8622") }0.0D, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, SI.METER);
/*     */   
/*  71 */   public static final ParameterDescriptor<Double> SCALE = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.EPSG, "Scale difference"), new NamedIdentifier(Citations.EPSG, "8611") }1.0D, 2.2250738585072014E-308D, Double.POSITIVE_INFINITY, Dimensionless.UNIT);
/*     */   
/*  81 */   public static final ParameterDescriptor<Double> ROTATION = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.EPSG, "Rotation angle of source coordinate reference system axes"), new NamedIdentifier(Citations.EPSG, "8614") }0.0D, 0.0D, 1296000.0D, NonSI.SECOND_ANGLE);
/*     */   
/*  93 */   static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.EPSG, "Similarity transformation"), new NamedIdentifier(Citations.EPSG, "9621") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { TRANSLATION_1, TRANSLATION_2, SCALE, ROTATION });
/*     */   
/*     */   public SimilarityTransformProvider() {
/* 112 */     super(2, 2, PARAMETERS);
/*     */   }
/*     */   
/*     */   protected MathTransform createMathTransform(ParameterValueGroup values) throws ParameterNotFoundException {
/* 137 */     double t1 = doubleValue(TRANSLATION_1, values);
/* 138 */     double t2 = doubleValue(TRANSLATION_2, values);
/* 139 */     double scale = doubleValue(SCALE, values);
/* 140 */     double rotation = doubleValue(ROTATION, values);
/* 143 */     double theta = Math.PI * rotation / 648000.0D;
/* 144 */     double p1 = scale * Math.cos(theta);
/* 145 */     double p2 = scale * Math.sin(theta);
/* 147 */     return (MathTransform)new AffineTransform2D(p1, -p2, p2, p1, t1, t2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\SimilarityTransformProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */