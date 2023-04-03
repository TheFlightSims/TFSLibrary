/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.SI;
/*     */ import org.geotools.measure.Units;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.parameter.DefaultParameterDescriptor;
/*     */ import org.geotools.parameter.FloatParameter;
/*     */ import org.geotools.parameter.ParameterGroup;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.datum.BursaWolfParameters;
/*     */ import org.geotools.referencing.operation.MathTransformProvider;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.referencing.operation.Transformation;
/*     */ 
/*     */ public class GeocentricTranslation extends ProjectiveTransform {
/*     */   private static final long serialVersionUID = -168669443433018655L;
/*     */   
/*     */   private final ParameterDescriptorGroup descriptor;
/*     */   
/*     */   public GeocentricTranslation(BursaWolfParameters parameters) {
/*  95 */     this(parameters, parameters.isTranslation() ? Provider.PARAMETERS : ProviderSevenParam.PARAMETERS);
/*     */   }
/*     */   
/*     */   GeocentricTranslation(BursaWolfParameters parameters, ParameterDescriptorGroup descriptor) {
/* 105 */     super((Matrix)parameters.getAffineTransform());
/* 106 */     this.descriptor = descriptor;
/*     */   }
/*     */   
/*     */   private GeocentricTranslation(Matrix matrix, ParameterDescriptorGroup descriptor) {
/* 115 */     super(matrix);
/* 116 */     this.descriptor = descriptor;
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 124 */     return this.descriptor;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 132 */     BursaWolfParameters parameters = new BursaWolfParameters(null);
/* 133 */     parameters.setAffineTransform(getMatrix(), Double.POSITIVE_INFINITY);
/* 134 */     if (ProviderFrameRotation.PARAMETERS.equals(this.descriptor)) {
/* 135 */       parameters.ex = -parameters.ex;
/* 136 */       parameters.ey = -parameters.ey;
/* 137 */       parameters.ez = -parameters.ez;
/*     */     } 
/* 139 */     boolean isTranslation = Provider.PARAMETERS.equals(this.descriptor);
/* 140 */     FloatParameter[] param = new FloatParameter[isTranslation ? 3 : 7];
/* 141 */     param[0] = new FloatParameter(Provider.DX, parameters.dx);
/* 142 */     param[1] = new FloatParameter(Provider.DY, parameters.dy);
/* 143 */     param[2] = new FloatParameter(Provider.DZ, parameters.dz);
/* 144 */     if (!isTranslation) {
/* 145 */       param[3] = new FloatParameter(ProviderSevenParam.EX, parameters.ex);
/* 146 */       param[4] = new FloatParameter(ProviderSevenParam.EY, parameters.ey);
/* 147 */       param[5] = new FloatParameter(ProviderSevenParam.EZ, parameters.ez);
/* 148 */       param[6] = new FloatParameter(ProviderSevenParam.PPM, parameters.ppm);
/*     */     } 
/* 150 */     return (ParameterValueGroup)new ParameterGroup(getParameterDescriptors(), (GeneralParameterValue[])param);
/*     */   }
/*     */   
/*     */   MathTransform createInverse(Matrix matrix) {
/* 158 */     return new GeocentricTranslation(matrix, this.descriptor);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 168 */     return super.hashCode() ^ this.descriptor.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 176 */     if (super.equals(object)) {
/* 177 */       GeocentricTranslation that = (GeocentricTranslation)object;
/* 178 */       return Utilities.equals(this.descriptor, that.descriptor);
/*     */     } 
/* 180 */     return false;
/*     */   }
/*     */   
/*     */   public static class Provider extends MathTransformProvider {
/*     */     private static final long serialVersionUID = -7160250630666911608L;
/*     */     
/*     */     static final int DEFAULT_DIMENSION = 2;
/*     */     
/* 213 */     public static final ParameterDescriptor<Integer> SRC_DIM = (ParameterDescriptor<Integer>)DefaultParameterDescriptor.create(Collections.singletonMap("name", new NamedIdentifier(Citations.GEOTOOLS, "src_dim")), 2, 2, 3, false);
/*     */     
/* 223 */     public static final ParameterDescriptor<Integer> TGT_DIM = (ParameterDescriptor<Integer>)DefaultParameterDescriptor.create(Collections.singletonMap("name", new NamedIdentifier(Citations.GEOTOOLS, "tgt_dim")), 2, 2, 3, false);
/*     */     
/* 234 */     public static final ParameterDescriptor<Double> SRC_SEMI_MAJOR = createOptionalDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "src_semi_major") }0.0D, Double.POSITIVE_INFINITY, SI.METER);
/*     */     
/* 246 */     public static final ParameterDescriptor<Double> SRC_SEMI_MINOR = createOptionalDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "src_semi_minor") }0.0D, Double.POSITIVE_INFINITY, SI.METER);
/*     */     
/* 258 */     public static final ParameterDescriptor<Double> TGT_SEMI_MAJOR = createOptionalDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "tgt_semi_major") }0.0D, Double.POSITIVE_INFINITY, SI.METER);
/*     */     
/* 270 */     public static final ParameterDescriptor<Double> TGT_SEMI_MINOR = createOptionalDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "tgt_semi_minor") }0.0D, Double.POSITIVE_INFINITY, SI.METER);
/*     */     
/* 280 */     public static final ParameterDescriptor<Double> DX = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "dx"), new NamedIdentifier(Citations.EPSG, "X-axis translation") }0.0D, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, SI.METER);
/*     */     
/* 291 */     public static final ParameterDescriptor<Double> DY = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "dy"), new NamedIdentifier(Citations.EPSG, "Y-axis translation") }0.0D, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, SI.METER);
/*     */     
/* 302 */     public static final ParameterDescriptor<Double> DZ = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "dz"), new NamedIdentifier(Citations.EPSG, "Z-axis translation") }0.0D, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, SI.METER);
/*     */     
/* 312 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.EPSG, "Geocentric translations (geog2D domain)"), new NamedIdentifier(Citations.EPSG, "9603") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { DX, DY, DZ, SRC_SEMI_MAJOR, SRC_SEMI_MINOR, TGT_SEMI_MAJOR, TGT_SEMI_MINOR, SRC_DIM, TGT_DIM });
/*     */     
/*     */     public Provider() {
/* 326 */       this(PARAMETERS);
/*     */     }
/*     */     
/*     */     Provider(ParameterDescriptorGroup parameters) {
/* 333 */       super(3, 3, parameters);
/*     */     }
/*     */     
/*     */     public Class<Transformation> getOperationType() {
/* 341 */       return Transformation.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup values) throws ParameterNotFoundException {
/* 354 */       BursaWolfParameters parameters = new BursaWolfParameters(null);
/* 355 */       fill(parameters, values);
/* 356 */       return concatenate(concatenate(new GeocentricTranslation(parameters, getParameters()), values, SRC_SEMI_MAJOR, SRC_SEMI_MINOR, SRC_DIM), values, TGT_SEMI_MAJOR, TGT_SEMI_MINOR, TGT_DIM);
/*     */     }
/*     */     
/*     */     protected void fill(BursaWolfParameters parameters, ParameterValueGroup values) {
/* 369 */       parameters.dx = doubleValue(DX, values);
/* 370 */       parameters.dy = doubleValue(DY, values);
/* 371 */       parameters.dz = doubleValue(DZ, values);
/*     */     }
/*     */     
/*     */     private static MathTransform concatenate(MathTransform transform, ParameterValueGroup values, ParameterDescriptor major, ParameterDescriptor minor, ParameterDescriptor<Integer> dim) {
/* 385 */       double semiMajor = doubleValue(major, values);
/* 386 */       double semiMinor = doubleValue(minor, values);
/* 387 */       int dimension = intValue(dim, values);
/* 388 */       switch (dimension) {
/*     */         case 0:
/* 389 */           if (Double.isNaN(semiMajor) && Double.isNaN(semiMinor))
/* 389 */             return transform; 
/*     */           break;
/*     */         case 2:
/*     */         case 3:
/*     */           break;
/*     */         default:
/* 392 */           throw new IllegalArgumentException(Errors.format(58, dim.getName().getCode(), Integer.valueOf(dimension)));
/*     */       } 
/* 395 */       ensureValid(major, semiMajor);
/* 396 */       ensureValid(minor, semiMinor);
/* 398 */       GeocentricTransform step = new GeocentricTransform(semiMajor, semiMinor, SI.METER, (dimension == 3));
/* 400 */       if (dim == SRC_DIM)
/* 401 */         return ConcatenatedTransform.create(step, transform); 
/* 403 */       return ConcatenatedTransform.create(transform, step.inverse());
/*     */     }
/*     */     
/*     */     private static void ensureValid(ParameterDescriptor param, double value) {
/* 411 */       if (value <= 0.0D)
/* 412 */         throw new IllegalStateException(Errors.format(99, param.getName().getCode())); 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ProviderSevenParam extends Provider {
/*     */     private static final long serialVersionUID = -6398226638364450229L;
/*     */     
/*     */     private static final double MAX_ROTATION = 648000.0D;
/*     */     
/* 443 */     public static final ParameterDescriptor EX = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "ex"), new NamedIdentifier(Citations.EPSG, "X-axis rotation") }0.0D, -648000.0D, 648000.0D, NonSI.SECOND_ANGLE);
/*     */     
/* 454 */     public static final ParameterDescriptor EY = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "ey"), new NamedIdentifier(Citations.EPSG, "Y-axis rotation") }0.0D, -648000.0D, 648000.0D, NonSI.SECOND_ANGLE);
/*     */     
/* 465 */     public static final ParameterDescriptor EZ = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "ez"), new NamedIdentifier(Citations.EPSG, "Z-axis rotation") }0.0D, -648000.0D, 648000.0D, NonSI.SECOND_ANGLE);
/*     */     
/* 477 */     public static final ParameterDescriptor PPM = createDescriptor((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "ppm"), new NamedIdentifier(Citations.EPSG, "Scale difference") }0.0D, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Units.PPM);
/*     */     
/* 487 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup("Position Vector transformation (geog2D domain)", "9606");
/*     */     
/*     */     static ParameterDescriptorGroup createDescriptorGroup(String name, String code) {
/* 494 */       return createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.EPSG, name), new NamedIdentifier(Citations.EPSG, code) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { 
/* 494 */             DX, DY, DZ, EX, EY, EZ, PPM, SRC_SEMI_MAJOR, SRC_SEMI_MINOR, TGT_SEMI_MAJOR, 
/* 494 */             TGT_SEMI_MINOR, SRC_DIM, TGT_DIM });
/*     */     }
/*     */     
/*     */     public ProviderSevenParam() {
/* 509 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     ProviderSevenParam(ParameterDescriptorGroup parameters) {
/* 516 */       super(parameters);
/*     */     }
/*     */     
/*     */     protected void fill(BursaWolfParameters parameters, ParameterValueGroup values) {
/* 524 */       super.fill(parameters, values);
/* 525 */       parameters.ppm = doubleValue(PPM, values);
/* 526 */       parameters.ex = doubleValue(EX, values);
/* 527 */       parameters.ey = doubleValue(EY, values);
/* 528 */       parameters.ez = doubleValue(EZ, values);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ProviderFrameRotation extends ProviderSevenParam {
/*     */     private static final long serialVersionUID = 5513675854809530038L;
/*     */     
/* 550 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup("Coordinate Frame Rotation (geog2D domain)", "9607");
/*     */     
/*     */     public ProviderFrameRotation() {
/* 557 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     protected void fill(BursaWolfParameters parameters, ParameterValueGroup values) {
/* 565 */       super.fill(parameters, values);
/* 566 */       parameters.ex = -parameters.ex;
/* 567 */       parameters.ey = -parameters.ey;
/* 568 */       parameters.ez = -parameters.ez;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\GeocentricTranslation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */