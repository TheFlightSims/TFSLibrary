/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import java.util.Set;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.crs.CompoundCRS;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.DerivedCRS;
/*     */ import org.opengis.referencing.crs.EngineeringCRS;
/*     */ import org.opengis.referencing.crs.GeocentricCRS;
/*     */ import org.opengis.referencing.crs.GeographicCRS;
/*     */ import org.opengis.referencing.crs.ImageCRS;
/*     */ import org.opengis.referencing.crs.ProjectedCRS;
/*     */ import org.opengis.referencing.crs.TemporalCRS;
/*     */ import org.opengis.referencing.crs.VerticalCRS;
/*     */ import org.opengis.referencing.cs.CartesianCS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.CylindricalCS;
/*     */ import org.opengis.referencing.cs.EllipsoidalCS;
/*     */ import org.opengis.referencing.cs.PolarCS;
/*     */ import org.opengis.referencing.cs.SphericalCS;
/*     */ import org.opengis.referencing.cs.TimeCS;
/*     */ import org.opengis.referencing.cs.VerticalCS;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.Ellipsoid;
/*     */ import org.opengis.referencing.datum.EngineeringDatum;
/*     */ import org.opengis.referencing.datum.GeodeticDatum;
/*     */ import org.opengis.referencing.datum.ImageDatum;
/*     */ import org.opengis.referencing.datum.PrimeMeridian;
/*     */ import org.opengis.referencing.datum.TemporalDatum;
/*     */ import org.opengis.referencing.datum.VerticalDatum;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ 
/*     */ abstract class AuthorityFactoryProxy {
/*  79 */   private static final Class[] TYPES = new Class[] { 
/*  79 */       CoordinateOperation.class, OperationMethod.class, ParameterDescriptor.class, ProjectedCRS.class, GeographicCRS.class, GeocentricCRS.class, ImageCRS.class, DerivedCRS.class, VerticalCRS.class, TemporalCRS.class, 
/*  79 */       EngineeringCRS.class, CompoundCRS.class, CoordinateReferenceSystem.class, CoordinateSystemAxis.class, CartesianCS.class, EllipsoidalCS.class, SphericalCS.class, CylindricalCS.class, PolarCS.class, VerticalCS.class, 
/*  79 */       TimeCS.class, CoordinateSystem.class, PrimeMeridian.class, Ellipsoid.class, GeodeticDatum.class, ImageDatum.class, VerticalDatum.class, TemporalDatum.class, EngineeringDatum.class, Datum.class, 
/*  79 */       IdentifiedObject.class };
/*     */   
/*     */   public static AuthorityFactoryProxy getInstance(AuthorityFactory factory, Class type) {
/* 129 */     AbstractAuthorityFactory.ensureNonNull("type", type);
/* 130 */     AbstractAuthorityFactory.ensureNonNull("factory", factory);
/* 131 */     type = getType(type);
/* 135 */     if (factory instanceof CRSAuthorityFactory) {
/* 136 */       CRSAuthorityFactory crsFactory = (CRSAuthorityFactory)factory;
/* 137 */       if (type.equals(ProjectedCRS.class))
/* 137 */         return new Projected(crsFactory); 
/* 138 */       if (type.equals(GeographicCRS.class))
/* 138 */         return new Geographic(crsFactory); 
/* 139 */       if (type.equals(CoordinateReferenceSystem.class))
/* 139 */         return new CRS(crsFactory); 
/*     */     } 
/* 144 */     return new Default(factory, type);
/*     */   }
/*     */   
/*     */   public static Class getType(Class<?> type) throws IllegalArgumentException {
/* 164 */     for (int i = 0; i < TYPES.length; i++) {
/* 165 */       Class candidate = TYPES[i];
/* 166 */       if (candidate.isAssignableFrom(type))
/* 167 */         return candidate; 
/*     */     } 
/* 170 */     throw new IllegalArgumentException(Errors.format(61, type, IdentifiedObject.class));
/*     */   }
/*     */   
/*     */   public abstract Class getType();
/*     */   
/*     */   public abstract AuthorityFactory getAuthorityFactory();
/*     */   
/*     */   public final Set getAuthorityCodes() throws FactoryException {
/* 190 */     return getAuthorityFactory().getAuthorityCodes(getType());
/*     */   }
/*     */   
/*     */   public abstract IdentifiedObject create(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
/*     */   
/*     */   public String toString() {
/* 209 */     return toString(AuthorityFactoryProxy.class);
/*     */   }
/*     */   
/*     */   final String toString(Class owner) {
/* 216 */     AuthorityFactory factory = getAuthorityFactory();
/* 217 */     return Classes.getShortName(owner) + '[' + Classes.getShortName(getType()) + " in " + Classes.getShortClassName(factory) + "(\"" + factory.getAuthority().getTitle() + "\")]";
/*     */   }
/*     */   
/*     */   private static final class Default extends AuthorityFactoryProxy {
/* 235 */     private static final Class[] PARAMETERS = new Class[] { String.class };
/*     */     
/*     */     private final AuthorityFactory factory;
/*     */     
/*     */     private final Class type;
/*     */     
/*     */     private final Method method;
/*     */     
/*     */     Default(AuthorityFactory factory, Class type) throws IllegalArgumentException {
/* 258 */       this.factory = factory;
/* 259 */       this.type = type;
/* 260 */       Method[] candidates = factory.getClass().getMethods();
/* 261 */       for (int i = 0; i < candidates.length; i++) {
/* 262 */         Method c = candidates[i];
/* 263 */         if (c.getName().startsWith("create") && type.equals(c.getReturnType()) && Arrays.equals((Object[])PARAMETERS, (Object[])c.getParameterTypes())) {
/* 266 */           this.method = c;
/*     */           return;
/*     */         } 
/*     */       } 
/* 270 */       throw new IllegalArgumentException(Errors.format(187, type));
/*     */     }
/*     */     
/*     */     public Class getType() {
/* 277 */       return this.type;
/*     */     }
/*     */     
/*     */     public AuthorityFactory getAuthorityFactory() {
/* 284 */       return this.factory;
/*     */     }
/*     */     
/*     */     public IdentifiedObject create(String code) throws FactoryException {
/*     */       try {
/* 292 */         return (IdentifiedObject)this.method.invoke(this.factory, new Object[] { code });
/* 293 */       } catch (InvocationTargetException exception) {
/* 294 */         Throwable cause = exception.getCause();
/* 295 */         if (cause instanceof FactoryException)
/* 296 */           throw (FactoryException)cause; 
/* 298 */         if (cause instanceof RuntimeException)
/* 299 */           throw (RuntimeException)cause; 
/* 301 */         if (cause instanceof Error)
/* 302 */           throw (Error)cause; 
/* 304 */         throw new FactoryException(cause.getLocalizedMessage(), cause);
/* 305 */       } catch (IllegalAccessException exception) {
/* 306 */         throw new FactoryException(exception.getLocalizedMessage(), exception);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static class CRS extends AuthorityFactoryProxy {
/*     */     protected final CRSAuthorityFactory factory;
/*     */     
/*     */     protected CRS(CRSAuthorityFactory factory) {
/* 323 */       this.factory = factory;
/*     */     }
/*     */     
/*     */     public Class getType() {
/* 327 */       return CoordinateReferenceSystem.class;
/*     */     }
/*     */     
/*     */     public final AuthorityFactory getAuthorityFactory() {
/* 331 */       return (AuthorityFactory)this.factory;
/*     */     }
/*     */     
/*     */     public IdentifiedObject create(String code) throws FactoryException {
/* 335 */       return (IdentifiedObject)this.factory.createCoordinateReferenceSystem(code);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Geographic extends CRS {
/*     */     protected Geographic(CRSAuthorityFactory factory) {
/* 348 */       super(factory);
/*     */     }
/*     */     
/*     */     public Class getType() {
/* 353 */       return GeographicCRS.class;
/*     */     }
/*     */     
/*     */     public IdentifiedObject create(String code) throws FactoryException {
/* 358 */       return (IdentifiedObject)this.factory.createGeographicCRS(code);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Projected extends CRS {
/*     */     protected Projected(CRSAuthorityFactory factory) {
/* 371 */       super(factory);
/*     */     }
/*     */     
/*     */     public Class getType() {
/* 376 */       return ProjectedCRS.class;
/*     */     }
/*     */     
/*     */     public IdentifiedObject create(String code) throws FactoryException {
/* 381 */       return (IdentifiedObject)this.factory.createProjectedCRS(code);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\AuthorityFactoryProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */