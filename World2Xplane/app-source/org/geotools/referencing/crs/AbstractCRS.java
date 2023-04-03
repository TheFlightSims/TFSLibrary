/*     */ package org.geotools.referencing.crs;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.measure.Measure;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.AbstractReferenceSystem;
/*     */ import org.geotools.referencing.cs.AbstractCS;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.CRSUtilities;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.geotools.util.UnsupportedImplementationException;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.ReferenceSystem;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public abstract class AbstractCRS extends AbstractReferenceSystem implements CoordinateReferenceSystem {
/*     */   private static final long serialVersionUID = -7433284548909530047L;
/*     */   
/*     */   protected final CoordinateSystem coordinateSystem;
/*     */   
/*     */   public AbstractCRS(CoordinateReferenceSystem crs) {
/*  78 */     super((ReferenceSystem)crs);
/*  79 */     this.coordinateSystem = crs.getCoordinateSystem();
/*     */   }
/*     */   
/*     */   public AbstractCRS(Map<String, ?> properties, CoordinateSystem cs) {
/*  91 */     super(properties);
/*  92 */     ensureNonNull("cs", cs);
/*  93 */     this.coordinateSystem = cs;
/*     */   }
/*     */   
/*     */   static Map<String, ?> name(int key) {
/* 106 */     Map<String, Object> properties = new HashMap<String, Object>(4);
/* 107 */     InternationalString name = Vocabulary.formatInternational(key);
/* 108 */     properties.put("name", name.toString());
/* 109 */     properties.put("alias", name);
/* 110 */     return properties;
/*     */   }
/*     */   
/*     */   public CoordinateSystem getCoordinateSystem() {
/* 117 */     return this.coordinateSystem;
/*     */   }
/*     */   
/*     */   final Unit<?> getUnit() {
/* 126 */     return CRSUtilities.getUnit(this.coordinateSystem);
/*     */   }
/*     */   
/*     */   public Measure distance(double[] coord1, double[] coord2) throws UnsupportedOperationException, MismatchedDimensionException {
/* 143 */     if (this.coordinateSystem instanceof AbstractCS)
/* 144 */       return ((AbstractCS)this.coordinateSystem).distance(coord1, coord2); 
/* 146 */     throw new UnsupportedImplementationException(this.coordinateSystem.getClass());
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 161 */     if (super.equals(object, compareMetadata)) {
/* 162 */       AbstractCRS that = (AbstractCRS)object;
/* 163 */       return equals((IdentifiedObject)this.coordinateSystem, (IdentifiedObject)that.coordinateSystem, compareMetadata);
/*     */     } 
/* 165 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 181 */     return 0x3D90441 ^ this.coordinateSystem.hashCode();
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 201 */     formatDefaultWKT(formatter);
/* 203 */     return super.formatWKT(formatter);
/*     */   }
/*     */   
/*     */   void formatDefaultWKT(Formatter formatter) {
/* 211 */     Unit<?> unit = getUnit();
/* 212 */     formatter.append(unit);
/* 213 */     int dimension = this.coordinateSystem.getDimension();
/* 214 */     for (int i = 0; i < dimension; i++)
/* 215 */       formatter.append((IdentifiedObject)this.coordinateSystem.getAxis(i)); 
/* 217 */     if (unit == null)
/* 218 */       formatter.setInvalidWKT(CoordinateReferenceSystem.class); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\crs\AbstractCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */