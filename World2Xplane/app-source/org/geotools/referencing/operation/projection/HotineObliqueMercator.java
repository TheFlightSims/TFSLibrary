/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class HotineObliqueMercator extends ObliqueMercator {
/*     */   private static final long serialVersionUID = 7376814731765422533L;
/*     */   
/*     */   protected HotineObliqueMercator(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  67 */     this(parameters, Provider.PARAMETERS.descriptors(), false);
/*     */   }
/*     */   
/*     */   HotineObliqueMercator(ParameterValueGroup parameters, Collection<GeneralParameterDescriptor> expected, boolean twoPoint) throws ParameterNotFoundException {
/*  85 */     super(parameters, expected, twoPoint, true);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/*  93 */     return this.twoPoint ? Provider_TwoPoint.PARAMETERS : Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public static final class Provider extends ObliqueMercator.Provider {
/*     */     private static final long serialVersionUID = 5822488360988630419L;
/*     */     
/* 127 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Hotine_Oblique_Mercator"), new NamedIdentifier(Citations.EPSG, "Hotine Oblique Mercator"), new NamedIdentifier(Citations.EPSG, "Hotine Oblique Mercator (variant A)"), new NamedIdentifier(Citations.EPSG, "Hotine Oblique Mercator"), new NamedIdentifier(Citations.EPSG, "9812"), new NamedIdentifier(Citations.GEOTIFF, "CT_ObliqueMercator_Hotine"), new NamedIdentifier(Citations.ESRI, "Hotine_Oblique_Mercator_Azimuth_Natural_Origin"), new NamedIdentifier(Citations.ESRI, "Rectified_Skew_Orthomorphic_Natural_Origin"), new NamedIdentifier(Citations.GEOTOOLS, NAME) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, LONGITUDE_OF_CENTRE, LATITUDE_OF_CENTRE, AZIMUTH, RECTIFIED_GRID_ANGLE, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 149 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 163 */       Collection<GeneralParameterDescriptor> descriptors = PARAMETERS.descriptors();
/* 164 */       return (MathTransform)new HotineObliqueMercator(parameters, descriptors, false);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Provider_TwoPoint extends ObliqueMercator.Provider_TwoPoint {
/*     */     private static final long serialVersionUID = -3104452416276842816L;
/*     */     
/* 189 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "Hotine_Oblique_Mercator_Two_Point_Natural_Origin"), new NamedIdentifier(Citations.GEOTOOLS, NAME) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, LAT_OF_1ST_POINT, LONG_OF_1ST_POINT, LAT_OF_2ND_POINT, LONG_OF_2ND_POINT, LATITUDE_OF_CENTRE, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider_TwoPoint() {
/* 204 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 218 */       Collection<GeneralParameterDescriptor> descriptors = PARAMETERS.descriptors();
/* 219 */       return (MathTransform)new HotineObliqueMercator(parameters, descriptors, true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\HotineObliqueMercator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */