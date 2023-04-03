/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.CylindricalProjection;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class Mercator2SP extends Mercator {
/*     */   private static final long serialVersionUID = -5693375873386007245L;
/*     */   
/*     */   protected Mercator2SP(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  59 */     super(parameters);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/*  66 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   private static final class Spherical extends Mercator.Spherical {
/*     */     private static final long serialVersionUID = 7693484746681095374L;
/*     */     
/*     */     protected Spherical(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  92 */       super(parameters);
/*     */     }
/*     */     
/*     */     public ParameterDescriptorGroup getParameterDescriptors() {
/*  99 */       return Mercator2SP.Provider.PARAMETERS;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = 6356028352681135786L;
/*     */     
/* 133 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Mercator_2SP"), new NamedIdentifier(Citations.EPSG, "Mercator (2SP)"), new NamedIdentifier(Citations.EPSG, "Mercator (variant B)"), new NamedIdentifier(Citations.EPSG, "Mercator (variant C)"), new NamedIdentifier(Citations.EPSG, "1044"), new NamedIdentifier(Citations.EPSG, "9805"), new NamedIdentifier(Citations.GEOTIFF, "CT_Mercator"), new NamedIdentifier(Citations.ESRI, "Mercator"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(34)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, STANDARD_PARALLEL_1, LATITUDE_OF_ORIGIN, CENTRAL_MERIDIAN, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 154 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<CylindricalProjection> getOperationType() {
/* 162 */       return CylindricalProjection.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 175 */       if (isSpherical(parameters))
/* 176 */         return (MathTransform)new Mercator2SP.Spherical(parameters); 
/* 178 */       return (MathTransform)new Mercator2SP(parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\Mercator2SP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */