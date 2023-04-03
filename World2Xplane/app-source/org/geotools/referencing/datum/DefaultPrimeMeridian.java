/*     */ package org.geotools.referencing.datum;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import javax.measure.quantity.Angle;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.datum.PrimeMeridian;
/*     */ 
/*     */ public class DefaultPrimeMeridian extends AbstractIdentifiedObject implements PrimeMeridian {
/*     */   private static final long serialVersionUID = 541978454643213305L;
/*     */   
/*  57 */   public static final DefaultPrimeMeridian GREENWICH = new DefaultPrimeMeridian("Greenwich", 0.0D, NonSI.DEGREE_ANGLE);
/*     */   
/*     */   private final double greenwichLongitude;
/*     */   
/*     */   private final Unit<Angle> angularUnit;
/*     */   
/*     */   public DefaultPrimeMeridian(PrimeMeridian meridian) {
/*  82 */     super((IdentifiedObject)meridian);
/*  83 */     this.greenwichLongitude = meridian.getGreenwichLongitude();
/*  84 */     this.angularUnit = meridian.getAngularUnit();
/*     */   }
/*     */   
/*     */   public DefaultPrimeMeridian(String name, double greenwichLongitude) {
/*  95 */     this(name, greenwichLongitude, NonSI.DEGREE_ANGLE);
/*     */   }
/*     */   
/*     */   public DefaultPrimeMeridian(String name, double greenwichLongitude, Unit<Angle> angularUnit) {
/* 108 */     this(Collections.singletonMap("name", name), greenwichLongitude, angularUnit);
/*     */   }
/*     */   
/*     */   public DefaultPrimeMeridian(Map<String, ?> properties, double greenwichLongitude, Unit<Angle> angularUnit) {
/* 123 */     super(properties);
/* 124 */     this.greenwichLongitude = greenwichLongitude;
/* 125 */     this.angularUnit = angularUnit;
/* 126 */     ensureAngularUnit(angularUnit);
/*     */   }
/*     */   
/*     */   public double getGreenwichLongitude() {
/* 137 */     return this.greenwichLongitude;
/*     */   }
/*     */   
/*     */   public double getGreenwichLongitude(Unit<Angle> targetUnit) {
/* 150 */     return getAngularUnit().getConverterTo(targetUnit).convert(getGreenwichLongitude());
/*     */   }
/*     */   
/*     */   public Unit<Angle> getAngularUnit() {
/* 157 */     return this.angularUnit;
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 170 */     if (object == this)
/* 171 */       return true; 
/* 173 */     if (super.equals(object, compareMetadata)) {
/* 174 */       DefaultPrimeMeridian that = (DefaultPrimeMeridian)object;
/* 175 */       if (compareMetadata)
/* 176 */         return (Utilities.equals(this.greenwichLongitude, that.greenwichLongitude) && Utilities.equals(this.angularUnit, that.angularUnit)); 
/* 179 */       return Utilities.equals(getGreenwichLongitude(NonSI.DEGREE_ANGLE), that.getGreenwichLongitude(NonSI.DEGREE_ANGLE));
/*     */     } 
/* 189 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 204 */     long code = Double.doubleToLongBits(this.greenwichLongitude);
/* 205 */     return (int)(code >>> 32L) ^ (int)code ^ 0x70EC83F9;
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 218 */     Unit<Angle> context = formatter.getAngularUnit();
/* 219 */     if (context == null)
/* 224 */       context = NonSI.DEGREE_ANGLE; 
/* 226 */     formatter.append(getGreenwichLongitude(context));
/* 227 */     return "PRIMEM";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\datum\DefaultPrimeMeridian.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */