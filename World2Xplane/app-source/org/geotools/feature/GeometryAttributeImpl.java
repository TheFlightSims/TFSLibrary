/*     */ package org.geotools.feature;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.feature.GeometryAttribute;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.GeometryDescriptor;
/*     */ import org.opengis.feature.type.GeometryType;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ import org.opengis.filter.identity.Identifier;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class GeometryAttributeImpl extends AttributeImpl implements GeometryAttribute {
/*     */   protected BoundingBox bounds;
/*     */   
/*     */   public GeometryAttributeImpl(Object content, GeometryDescriptor descriptor, Identifier id) {
/*  67 */     super(content, (AttributeDescriptor)descriptor, id);
/*     */   }
/*     */   
/*     */   public GeometryType getType() {
/*  71 */     return (GeometryType)super.getType();
/*     */   }
/*     */   
/*     */   public GeometryDescriptor getDescriptor() {
/*  75 */     return (GeometryDescriptor)super.getDescriptor();
/*     */   }
/*     */   
/*     */   public Geometry getValue() {
/*  79 */     return (Geometry)super.getValue();
/*     */   }
/*     */   
/*     */   public void setValue(Object newValue) throws IllegalArgumentException, IllegalStateException {
/*  84 */     setValue((Geometry)newValue);
/*     */   }
/*     */   
/*     */   public void setValue(Geometry geometry) {
/*  88 */     super.setValue(geometry);
/*     */   }
/*     */   
/*     */   public synchronized void setBounds(BoundingBox bbox) {
/*  95 */     this.bounds = bbox;
/*     */   }
/*     */   
/*     */   public synchronized BoundingBox getBounds() {
/* 106 */     if (this.bounds == null) {
/* 107 */       ReferencedEnvelope bbox = new ReferencedEnvelope(getType().getCoordinateReferenceSystem());
/* 108 */       Geometry geom = getValue();
/* 109 */       if (geom != null) {
/* 110 */         bbox.expandToInclude(geom.getEnvelopeInternal());
/*     */       } else {
/* 113 */         bbox.setToNull();
/*     */       } 
/* 115 */       this.bounds = (BoundingBox)bbox;
/*     */     } 
/* 117 */     return this.bounds;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 121 */     if (this == o)
/* 122 */       return true; 
/* 125 */     if (!(o instanceof GeometryAttributeImpl))
/* 126 */       return false; 
/* 129 */     GeometryAttributeImpl att = (GeometryAttributeImpl)o;
/* 131 */     if (!Utilities.equals(this.descriptor, att.descriptor))
/* 132 */       return false; 
/* 134 */     if (!Utilities.equals(this.id, att.id))
/* 135 */       return false; 
/* 137 */     if (this.value != null && att.value != null) {
/* 141 */       if (att.value instanceof GeometryCollection && !(att.value instanceof com.vividsolutions.jts.geom.MultiPoint) && !(att.value instanceof com.vividsolutions.jts.geom.MultiLineString) && !(att.value instanceof com.vividsolutions.jts.geom.MultiPolygon)) {
/* 146 */         if (this.value instanceof GeometryCollection) {
/* 148 */           GeometryCollection c1 = (GeometryCollection)this.value;
/* 149 */           GeometryCollection c2 = (GeometryCollection)att.value;
/* 151 */           if (c1.getNumGeometries() != c2.getNumGeometries())
/* 152 */             return false; 
/* 155 */           for (int i = 0; i < c1.getNumGeometries(); i++) {
/* 156 */             Geometry g1 = c1.getGeometryN(i);
/* 157 */             Geometry g2 = c2.getGeometryN(i);
/* 159 */             if (!g1.equalsExact(g2))
/* 160 */               return false; 
/*     */           } 
/* 164 */           return true;
/*     */         } 
/* 167 */         return false;
/*     */       } 
/* 170 */       if (!((Geometry)this.value).equalsExact((Geometry)att.value))
/* 171 */         return false; 
/*     */     } else {
/* 175 */       return Utilities.deepEquals(this.value, this.value);
/*     */     } 
/* 178 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 182 */     int hash = this.descriptor.hashCode();
/* 184 */     if (this.id != null)
/* 185 */       hash += 37 * this.id.hashCode(); 
/* 188 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 192 */     StringBuffer sb = (new StringBuffer(getClass().getSimpleName())).append(":");
/* 193 */     sb.append(getDescriptor().getName().getLocalPart());
/* 194 */     CoordinateReferenceSystem crs = getDescriptor().getType().getCoordinateReferenceSystem();
/* 195 */     if (!getDescriptor().getName().getLocalPart().equals(getDescriptor().getType().getName().getLocalPart()) || this.id != null || crs != null) {
/* 197 */       sb.append("<");
/* 198 */       sb.append(getDescriptor().getType().getName().getLocalPart());
/* 199 */       if (this.id != null) {
/* 200 */         sb.append(" id=");
/* 201 */         sb.append(this.id);
/*     */       } 
/* 203 */       if (crs != null) {
/* 204 */         sb.append(" crs=");
/* 205 */         sb.append(crs);
/*     */       } 
/* 207 */       if (this.id != null) {
/* 208 */         sb.append(" id=");
/* 209 */         sb.append(this.id);
/*     */       } 
/* 211 */       sb.append(">");
/*     */     } 
/* 213 */     sb.append("=");
/* 214 */     sb.append(this.value);
/* 215 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\GeometryAttributeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */