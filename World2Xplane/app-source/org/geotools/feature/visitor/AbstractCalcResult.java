/*     */ package org.geotools.feature.visitor;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class AbstractCalcResult implements CalcResult {
/*     */   public boolean isCompatible(CalcResult targetResults) {
/*  45 */     return (targetResults == CalcResult.NULL_RESULT);
/*     */   }
/*     */   
/*     */   public CalcResult merge(CalcResult resultsToAdd) {
/*  49 */     if (resultsToAdd == CalcResult.NULL_RESULT)
/*  50 */       return this; 
/*  52 */     if (!isCompatible(resultsToAdd))
/*  53 */       throw new IllegalArgumentException("Parameter is not a compatible type"); 
/*  56 */     throw new IllegalArgumentException("The CalcResults claim to be compatible, but the appropriate merge method has not been implemented.");
/*     */   }
/*     */   
/*     */   public Object getValue() {
/*  64 */     return null;
/*     */   }
/*     */   
/*     */   public int toInt() {
/*  68 */     Object value = getValue();
/*  69 */     if (value instanceof Number) {
/*  70 */       Number number = (Number)value;
/*  71 */       return number.intValue();
/*     */     } 
/*  73 */     return 0;
/*     */   }
/*     */   
/*     */   public double toDouble() {
/*  78 */     Object value = getValue();
/*  79 */     if (value instanceof Number) {
/*  80 */       Number number = (Number)value;
/*  81 */       return number.doubleValue();
/*     */     } 
/*  83 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public long toLong() {
/*  88 */     Object value = getValue();
/*  89 */     if (value instanceof Number) {
/*  90 */       Number number = (Number)value;
/*  91 */       return number.longValue();
/*     */     } 
/*  93 */     return 0L;
/*     */   }
/*     */   
/*     */   public float toFloat() {
/*  98 */     Object value = getValue();
/*  99 */     if (value instanceof Number) {
/* 100 */       Number number = (Number)value;
/* 101 */       return number.floatValue();
/*     */     } 
/* 103 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public Geometry toGeometry() {
/* 108 */     Object value = getValue();
/* 109 */     if (value instanceof Geometry)
/* 110 */       return (Geometry)getValue(); 
/* 112 */     return null;
/*     */   }
/*     */   
/*     */   public Envelope toEnvelope() {
/* 116 */     Object value = getValue();
/* 117 */     if (value instanceof Envelope)
/* 118 */       return (Envelope)value; 
/* 120 */     return null;
/*     */   }
/*     */   
/*     */   public Point toPoint() {
/* 124 */     Geometry geometry = toGeometry();
/* 125 */     return geometry.getCentroid();
/*     */   }
/*     */   
/*     */   public Set toSet() {
/* 129 */     Object value = getValue();
/* 131 */     if (value == null)
/* 132 */       return null; 
/* 135 */     if (value instanceof Set) {
/* 136 */       Set set = (Set)value;
/* 138 */       return set;
/*     */     } 
/* 141 */     if (value.getClass().isArray()) {
/* 142 */       Set set = new HashSet(Arrays.asList((Object[])value));
/* 144 */       return set;
/*     */     } 
/* 147 */     if (value instanceof Collection) {
/* 148 */       Set set = new HashSet((Collection)value);
/* 150 */       return set;
/*     */     } 
/* 153 */     return null;
/*     */   }
/*     */   
/*     */   public List toList() {
/* 157 */     Object value = getValue();
/* 159 */     if (value == null)
/* 160 */       return null; 
/* 163 */     if (value instanceof List) {
/* 164 */       List list = (List)value;
/* 166 */       return list;
/*     */     } 
/* 169 */     if (value.getClass().isArray())
/* 170 */       return Arrays.asList((Object[])value); 
/* 173 */     if (value instanceof HashSet) {
/* 174 */       Set set = (HashSet)value;
/* 176 */       return Arrays.asList(set.toArray());
/*     */     } 
/* 183 */     if (value instanceof Collection)
/* 184 */       return new ArrayList((Collection)value); 
/* 187 */     return null;
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 191 */     List list = toList();
/* 193 */     if (list == null)
/* 194 */       return null; 
/* 197 */     return list.toArray();
/*     */   }
/*     */   
/*     */   public String[] toStringArray() {
/* 201 */     List list = toList();
/* 203 */     if (list == null)
/* 204 */       return null; 
/* 207 */     String[] strings = new String[list.size()];
/* 209 */     return (String[])list.toArray((Object[])strings);
/*     */   }
/*     */   
/*     */   public Map toMap() {
/* 213 */     return (Map)getValue();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 217 */     return getValue().toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\AbstractCalcResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */