/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collection;
/*     */ import java.util.logging.Level;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.CylindricalProjection;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class EquidistantCylindrical extends MapProjection {
/*     */   private static final long serialVersionUID = -848975059471102069L;
/*     */   
/*     */   private final double cosStandardParallel;
/*     */   
/*     */   protected final double standardParallel;
/*     */   
/*     */   protected EquidistantCylindrical(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  96 */     super(parameters);
/*  97 */     Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/*  98 */     if (expected.contains(Provider.STANDARD_PARALLEL_1)) {
/*  99 */       this.standardParallel = Math.abs(doubleValue(expected, Provider.STANDARD_PARALLEL_1, parameters));
/* 100 */       ensureLatitudeInRange(Provider.STANDARD_PARALLEL_1, this.standardParallel, false);
/* 101 */       this.cosStandardParallel = Math.cos(this.standardParallel);
/*     */     } else {
/* 104 */       this.standardParallel = 0.0D;
/* 105 */       this.cosStandardParallel = 1.0D;
/*     */     } 
/* 107 */     assert this.latitudeOfOrigin == 0.0D : this.latitudeOfOrigin;
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 114 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() {
/* 122 */     ParameterValueGroup values = super.getParameterValues();
/* 123 */     if (!Double.isNaN(this.standardParallel)) {
/* 124 */       Collection<GeneralParameterDescriptor> expected = getParameterDescriptors().descriptors();
/* 125 */       set(expected, Provider.STANDARD_PARALLEL_1, values, this.standardParallel);
/*     */     } 
/* 127 */     return values;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 141 */     x *= this.cosStandardParallel;
/* 142 */     if (ptDst != null) {
/* 143 */       ptDst.setLocation(x, y);
/* 144 */       return ptDst;
/*     */     } 
/* 146 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/* 156 */     x /= this.cosStandardParallel;
/* 157 */     if (ptDst != null) {
/* 158 */       ptDst.setLocation(x, y);
/* 159 */       return ptDst;
/*     */     } 
/* 161 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 169 */     long code = Double.doubleToLongBits(this.standardParallel);
/* 170 */     return ((int)code ^ (int)(code >>> 32L)) + 37 * super.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 178 */     if (object == this)
/* 180 */       return true; 
/* 182 */     if (super.equals(object)) {
/* 183 */       EquidistantCylindrical that = (EquidistantCylindrical)object;
/* 184 */       return equals(this.standardParallel, that.standardParallel);
/*     */     } 
/* 186 */     return false;
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = -278288251842178001L;
/*     */     
/* 221 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Equidistant_Cylindrical"), new NamedIdentifier(Citations.EPSG, "Equidistant Cylindrical"), new NamedIdentifier(Citations.ESRI, "Equidistant_Cylindrical"), new NamedIdentifier(Citations.EPSG, "9823"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(62)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, LATITUDE_OF_ORIGIN, STANDARD_PARALLEL_1, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 238 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<CylindricalProjection> getOperationType() {
/* 246 */       return CylindricalProjection.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException, FactoryException {
/* 260 */       if (!isSpherical(parameters))
/* 261 */         MapProjection.LOGGER.log(Level.FINE, "GeoTools EquidistantCylindrical is defined only on the sphere, we're going to use spherical equations even if the projection is using an ellipsoid"); 
/* 264 */       return (MathTransform)new EquidistantCylindrical(parameters);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SphericalProvider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = 8929981563074475828L;
/*     */     
/* 290 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.EPSG, "Equidistant Cylindrical (Spherical)"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(62)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, LATITUDE_OF_ORIGIN, STANDARD_PARALLEL_1, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public SphericalProvider() {
/* 304 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<CylindricalProjection> getOperationType() {
/* 312 */       return CylindricalProjection.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException, FactoryException {
/* 326 */       return (MathTransform)new EquidistantCylindrical(parameters) {
/*     */           public ParameterDescriptorGroup getParameterDescriptors() {
/* 329 */             return EquidistantCylindrical.SphericalProvider.PARAMETERS;
/*     */           }
/*     */         };
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\EquidistantCylindrical.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */