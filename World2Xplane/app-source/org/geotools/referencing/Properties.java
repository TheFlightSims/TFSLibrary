/*     */ package org.geotools.referencing;
/*     */ 
/*     */ import java.util.AbstractMap;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.geotools.referencing.operation.AbstractCoordinateOperation;
/*     */ import org.geotools.util.MapEntry;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.ReferenceSystem;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ 
/*     */ final class Properties extends AbstractMap<String, Object> {
/*     */   private final IdentifiedObject info;
/*     */   
/*     */   private transient Set<Map.Entry<String, Object>> entries;
/*     */   
/*     */   public Properties(IdentifiedObject info) {
/*  60 */     this.info = info;
/*  61 */     AbstractIdentifiedObject.ensureNonNull("info", info);
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  69 */     return (get(key) != null);
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/*  78 */     if (key instanceof String) {
/*  79 */       String s = ((String)key).trim();
/*  80 */       for (int i = 0; i < KEYS.length; i++) {
/*  81 */         if (KEYS[i].equalsIgnoreCase(s))
/*  82 */           return get(i); 
/*     */       } 
/*     */     } 
/*  86 */     return null;
/*     */   }
/*     */   
/*     */   private Object get(int key) {
/*  94 */     switch (key) {
/*     */       case 0:
/*  95 */         return this.info.getName();
/*     */       case 1:
/*  96 */         return this.info.getIdentifiers().toArray((Object[])AbstractIdentifiedObject.EMPTY_IDENTIFIER_ARRAY);
/*     */       case 2:
/*  97 */         return this.info.getAlias().toArray((Object[])AbstractIdentifiedObject.EMPTY_ALIAS_ARRAY);
/*     */       case 3:
/*  98 */         return this.info.getRemarks();
/*     */       case 4:
/* 100 */         if (this.info instanceof ReferenceSystem)
/* 101 */           return ((ReferenceSystem)this.info).getScope(); 
/* 102 */         if (this.info instanceof Datum)
/* 103 */           return ((Datum)this.info).getScope(); 
/* 104 */         if (this.info instanceof CoordinateOperation)
/* 105 */           return ((CoordinateOperation)this.info).getScope(); 
/*     */         break;
/*     */       case 5:
/* 110 */         if (this.info instanceof ReferenceSystem)
/* 111 */           return ((ReferenceSystem)this.info).getDomainOfValidity(); 
/* 112 */         if (this.info instanceof Datum)
/* 113 */           return ((Datum)this.info).getDomainOfValidity(); 
/* 114 */         if (this.info instanceof CoordinateOperation)
/* 115 */           return ((CoordinateOperation)this.info).getDomainOfValidity(); 
/*     */         break;
/*     */       case 6:
/* 120 */         if (this.info instanceof CoordinateOperation)
/* 121 */           return ((CoordinateOperation)this.info).getOperationVersion(); 
/*     */         break;
/*     */       case 7:
/* 126 */         if (this.info instanceof CoordinateOperation)
/* 127 */           return ((CoordinateOperation)this.info).getCoordinateOperationAccuracy().toArray((Object[])AbstractCoordinateOperation.EMPTY_ACCURACY_ARRAY); 
/*     */         break;
/*     */     } 
/* 133 */     return null;
/*     */   }
/*     */   
/* 142 */   private static final String[] KEYS = new String[] { "name", "identifiers", "alias", "remarks", "scope", "domainOfValidity", "operationVersion", "coordinateOperationAccuracy" };
/*     */   
/*     */   public Set<Map.Entry<String, Object>> entrySet() {
/* 158 */     if (this.entries == null) {
/* 159 */       this.entries = new HashSet<Map.Entry<String, Object>>(Math.round(KEYS.length / 0.75F) + 1, 0.75F);
/* 160 */       for (int i = 0; i < KEYS.length; i++) {
/* 161 */         Object value = get(i);
/* 162 */         if (value != null)
/* 163 */           this.entries.add(new MapEntry(KEYS[i], value)); 
/*     */       } 
/* 166 */       this.entries = Collections.unmodifiableSet(this.entries);
/*     */     } 
/* 168 */     return this.entries;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\Properties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */