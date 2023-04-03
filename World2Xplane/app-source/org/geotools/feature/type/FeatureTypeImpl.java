/*     */ package org.geotools.feature.type;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.GeometryDescriptor;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class FeatureTypeImpl extends ComplexTypeImpl implements FeatureType {
/*     */   private GeometryDescriptor defaultGeometry;
/*     */   
/*     */   private CoordinateReferenceSystem crs;
/*     */   
/*     */   public FeatureTypeImpl(Name name, Collection<PropertyDescriptor> schema, GeometryDescriptor defaultGeometry, boolean isAbstract, List<Filter> restrictions, AttributeType superType, InternationalString description) {
/*  54 */     super(name, schema, true, isAbstract, restrictions, superType, description);
/*  55 */     this.defaultGeometry = defaultGeometry;
/*  57 */     if (defaultGeometry != null && !(defaultGeometry.getType() instanceof org.opengis.feature.type.GeometryType))
/*  59 */       throw new IllegalArgumentException("defaultGeometry must have a GeometryType"); 
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getCoordinateReferenceSystem() {
/*  65 */     if (this.crs == null) {
/*  66 */       if (getGeometryDescriptor() != null && getGeometryDescriptor().getType().getCoordinateReferenceSystem() != null)
/*  67 */         this.crs = this.defaultGeometry.getType().getCoordinateReferenceSystem(); 
/*  69 */       if (this.crs == null)
/*  70 */         for (PropertyDescriptor property : getDescriptors()) {
/*  71 */           if (property instanceof GeometryDescriptor) {
/*  72 */             GeometryDescriptor geometry = (GeometryDescriptor)property;
/*  73 */             if (geometry.getType().getCoordinateReferenceSystem() != null) {
/*  74 */               this.crs = geometry.getType().getCoordinateReferenceSystem();
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         }  
/*     */     } 
/*  82 */     return this.crs;
/*     */   }
/*     */   
/*     */   public GeometryDescriptor getGeometryDescriptor() {
/*  86 */     if (this.defaultGeometry == null)
/*  87 */       for (PropertyDescriptor property : getDescriptors()) {
/*  88 */         if (property instanceof GeometryDescriptor) {
/*  89 */           this.defaultGeometry = (GeometryDescriptor)property;
/*     */           break;
/*     */         } 
/*     */       }  
/*  94 */     return this.defaultGeometry;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  98 */     if (this == o)
/*  99 */       return true; 
/* 101 */     if (!super.equals(o))
/* 102 */       return false; 
/* 104 */     if (getClass() != o.getClass())
/* 105 */       return false; 
/* 107 */     FeatureType other = (FeatureType)o;
/* 108 */     if (!Utilities.equals(getGeometryDescriptor(), other.getGeometryDescriptor()))
/* 109 */       return false; 
/* 111 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 115 */     int hashCode = super.hashCode();
/* 117 */     if (this.defaultGeometry != null)
/* 118 */       hashCode ^= this.defaultGeometry.hashCode(); 
/* 121 */     return hashCode;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\FeatureTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */