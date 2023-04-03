/*     */ package org.geotools.referencing.wkt;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Set;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import javax.measure.unit.UnitFormat;
/*     */ import org.geotools.measure.Units;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ 
/*     */ abstract class GeoToolsUnitFormat extends UnitFormat {
/*     */   public static UnitFormat getInstance(Citation citation) {
/*  42 */     if (CRS.equalsIgnoreMetadata(Citations.ESRI, citation))
/*  43 */       return (UnitFormat)new ESRIFormat(); 
/*  45 */     return (UnitFormat)new EPSGFormat();
/*     */   }
/*     */   
/*     */   static abstract class BaseGT2Format extends UnitFormat.DefaultFormat {
/*     */     public BaseGT2Format() {
/*  57 */       Unit<?> forceInit = Units.SEXAGESIMAL_DMS;
/*  59 */       UnitFormat.DefaultFormat base = (UnitFormat.DefaultFormat)UnitFormat.getInstance();
/*  62 */       Set<Unit<?>> nonSiUnits = NonSI.getInstance().getUnits();
/*  63 */       for (Unit<?> unit : nonSiUnits) {
/*  64 */         String name = base.nameFor(unit);
/*  65 */         if (name != null)
/*  66 */           label(unit, name); 
/*     */       } 
/*  70 */       Set<Unit<?>> siUnits = NonSI.getInstance().getUnits();
/*  71 */       for (Unit<?> unit : siUnits) {
/*  72 */         String name = base.nameFor(unit);
/*  73 */         if (name != null)
/*  74 */           label(unit, name); 
/*     */       } 
/*  79 */       for (Field field : Units.class.getFields()) {
/*  80 */         if (Modifier.isStatic(field.getModifiers()) && Unit.class.isAssignableFrom(field.getType()))
/*     */           try {
/*  83 */             field.setAccessible(true);
/*  84 */             Unit unit = (Unit)field.get(null);
/*  85 */             String name = base.nameFor(unit);
/*  86 */             if (name != null)
/*  87 */               label(unit, name); 
/*  89 */           } catch (Throwable t) {} 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class EPSGFormat extends BaseGT2Format {
/*     */     private static final long serialVersionUID = -1207705344688824557L;
/*     */     
/*     */     public EPSGFormat() {
/* 106 */       label(NonSI.DEGREE_ANGLE, "degree");
/*     */     }
/*     */   }
/*     */   
/*     */   static class ESRIFormat extends UnitFormat.DefaultFormat {
/*     */     private static final long serialVersionUID = 5769662824845469523L;
/*     */     
/*     */     public ESRIFormat() {
/* 118 */       label(NonSI.DEGREE_ANGLE, "Degree");
/* 119 */       label(SI.METER, "Meter");
/* 120 */       label(SI.METER.times(0.3047997101815088D), "Foot_Gold_Coast");
/* 121 */       label(NonSI.FOOT, "Foot");
/* 122 */       label(NonSI.FOOT_SURVEY_US, "Foot_US");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\wkt\GeoToolsUnitFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */