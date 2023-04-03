/*     */ package org.geotools.referencing.crs;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import javax.measure.quantity.Angle;
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.GeneralDerivedCRS;
/*     */ import org.opengis.referencing.crs.GeographicCRS;
/*     */ import org.opengis.referencing.crs.ProjectedCRS;
/*     */ import org.opengis.referencing.cs.CartesianCS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.Ellipsoid;
/*     */ import org.opengis.referencing.datum.GeodeticDatum;
/*     */ import org.opengis.referencing.operation.Conversion;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ import org.opengis.referencing.operation.Projection;
/*     */ 
/*     */ public class DefaultProjectedCRS extends AbstractDerivedCRS implements ProjectedCRS {
/*     */   private static final long serialVersionUID = -4502680112031773028L;
/*     */   
/*     */   private static final String SEMI_MAJOR = "semi_major";
/*     */   
/*     */   private static final String SEMI_MINOR = "semi_minor";
/*     */   
/*     */   public DefaultProjectedCRS(ProjectedCRS crs) {
/*  91 */     super((GeneralDerivedCRS)crs);
/*     */   }
/*     */   
/*     */   public DefaultProjectedCRS(String name, GeographicCRS base, MathTransform baseToDerived, CartesianCS derivedCS) throws MismatchedDimensionException {
/* 119 */     this(Collections.singletonMap("name", name), base, baseToDerived, derivedCS);
/*     */   }
/*     */   
/*     */   public DefaultProjectedCRS(Map<String, ?> properties, GeographicCRS base, MathTransform baseToDerived, CartesianCS derivedCS) throws MismatchedDimensionException {
/* 153 */     super(properties, (CoordinateReferenceSystem)base, baseToDerived, (CoordinateSystem)derivedCS);
/*     */   }
/*     */   
/*     */   public DefaultProjectedCRS(Map<String, ?> properties, OperationMethod method, GeographicCRS base, MathTransform baseToDerived, CartesianCS derivedCS) throws MismatchedDimensionException {
/* 184 */     super(properties, method, (CoordinateReferenceSystem)base, baseToDerived, (CoordinateSystem)derivedCS);
/*     */   }
/*     */   
/*     */   public DefaultProjectedCRS(Map<String, ?> properties, Conversion conversionFromBase, GeographicCRS base, MathTransform baseToDerived, CartesianCS derivedCS) throws MismatchedDimensionException {
/* 210 */     super(properties, conversionFromBase, (CoordinateReferenceSystem)base, baseToDerived, (CoordinateSystem)derivedCS);
/*     */   }
/*     */   
/*     */   public CartesianCS getCoordinateSystem() {
/* 218 */     return (CartesianCS)super.getCoordinateSystem();
/*     */   }
/*     */   
/*     */   public GeodeticDatum getDatum() {
/* 226 */     return (GeodeticDatum)super.getDatum();
/*     */   }
/*     */   
/*     */   public GeographicCRS getBaseCRS() {
/* 236 */     return (GeographicCRS)super.getBaseCRS();
/*     */   }
/*     */   
/*     */   public Projection getConversionFromBase() {
/* 246 */     return (Projection)super.getConversionFromBase();
/*     */   }
/*     */   
/*     */   Class<? extends Projection> getConversionType() {
/* 254 */     return Projection.class;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 265 */     return 0x59214E9C ^ super.hashCode();
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 278 */     Ellipsoid ellipsoid = ((GeodeticDatum)this.datum).getEllipsoid();
/* 280 */     Unit<Length> unit = (Unit)getUnit();
/* 281 */     Unit<Length> linearUnit = formatter.getLinearUnit();
/* 282 */     Unit<Angle> angularUnit = formatter.getAngularUnit();
/* 283 */     Unit<Length> axisUnit = ellipsoid.getAxisUnit();
/* 284 */     formatter.setLinearUnit(unit);
/* 285 */     formatter.setAngularUnit(DefaultGeographicCRS.getAngularUnit(this.baseCRS.getCoordinateSystem()));
/* 286 */     formatter.append((IdentifiedObject)this.baseCRS);
/* 287 */     formatter.append((IdentifiedObject)this.conversionFromBase.getMethod());
/* 288 */     for (GeneralParameterValue param : this.conversionFromBase.getParameterValues().values()) {
/* 289 */       GeneralParameterDescriptor desc = param.getDescriptor();
/*     */       String name;
/* 291 */       if (nameMatches((IdentifiedObject)desc, name = "semi_major") || nameMatches((IdentifiedObject)desc, name = "semi_minor"))
/* 297 */         if (param instanceof ParameterValue) {
/* 298 */           double value = ((ParameterValue)param).doubleValue(axisUnit);
/* 299 */           double expected = (name == "semi_minor") ? ellipsoid.getSemiMinorAxis() : ellipsoid.getSemiMajorAxis();
/* 301 */           if (value == expected)
/*     */             continue; 
/*     */         }  
/* 306 */       formatter.append(param);
/*     */     } 
/* 308 */     formatter.append(unit);
/* 309 */     int dimension = this.coordinateSystem.getDimension();
/* 310 */     for (int i = 0; i < dimension; i++)
/* 311 */       formatter.append((IdentifiedObject)this.coordinateSystem.getAxis(i)); 
/* 313 */     if (unit == null)
/* 314 */       formatter.setInvalidWKT(ProjectedCRS.class); 
/* 316 */     formatter.setAngularUnit(angularUnit);
/* 317 */     formatter.setLinearUnit(linearUnit);
/* 318 */     return "PROJCS";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\crs\DefaultProjectedCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */