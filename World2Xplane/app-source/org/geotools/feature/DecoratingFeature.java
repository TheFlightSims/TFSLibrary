/*     */ package org.geotools.feature;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.opengis.feature.GeometryAttribute;
/*     */ import org.opengis.feature.Property;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.ComplexType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ import org.opengis.filter.identity.FeatureId;
/*     */ import org.opengis.filter.identity.Identifier;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ 
/*     */ public class DecoratingFeature implements SimpleFeature {
/*     */   protected SimpleFeature delegate;
/*     */   
/*     */   public DecoratingFeature(SimpleFeature delegate) {
/*  53 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */   public Object getAttribute(int index) {
/*  57 */     return this.delegate.getAttribute(index);
/*     */   }
/*     */   
/*     */   public Object getAttribute(Name arg0) {
/*  61 */     return this.delegate.getAttribute(arg0);
/*     */   }
/*     */   
/*     */   public Object getAttribute(String path) {
/*  65 */     return this.delegate.getAttribute(path);
/*     */   }
/*     */   
/*     */   public int getAttributeCount() {
/*  69 */     return this.delegate.getAttributeCount();
/*     */   }
/*     */   
/*     */   public List<Object> getAttributes() {
/*  73 */     return this.delegate.getAttributes();
/*     */   }
/*     */   
/*     */   public BoundingBox getBounds() {
/*  77 */     return this.delegate.getBounds();
/*     */   }
/*     */   
/*     */   public Object getDefaultGeometry() {
/*  81 */     return this.delegate.getDefaultGeometry();
/*     */   }
/*     */   
/*     */   public GeometryAttribute getDefaultGeometryProperty() {
/*  85 */     return this.delegate.getDefaultGeometryProperty();
/*     */   }
/*     */   
/*     */   public AttributeDescriptor getDescriptor() {
/*  89 */     return this.delegate.getDescriptor();
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/*  93 */     return this.delegate.getFeatureType();
/*     */   }
/*     */   
/*     */   public FeatureId getIdentifier() {
/*  96 */     return this.delegate.getIdentifier();
/*     */   }
/*     */   
/*     */   public String getID() {
/*  99 */     return this.delegate.getID();
/*     */   }
/*     */   
/*     */   public Name getName() {
/* 103 */     return this.delegate.getName();
/*     */   }
/*     */   
/*     */   public Collection<Property> getProperties() {
/* 107 */     return this.delegate.getProperties();
/*     */   }
/*     */   
/*     */   public Collection<Property> getProperties(Name arg0) {
/* 111 */     return this.delegate.getProperties(arg0);
/*     */   }
/*     */   
/*     */   public Collection<Property> getProperties(String arg0) {
/* 115 */     return this.delegate.getProperties(arg0);
/*     */   }
/*     */   
/*     */   public Property getProperty(Name arg0) {
/* 119 */     return this.delegate.getProperty(arg0);
/*     */   }
/*     */   
/*     */   public Property getProperty(String arg0) {
/* 123 */     return this.delegate.getProperty(arg0);
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getType() {
/* 127 */     return this.delegate.getType();
/*     */   }
/*     */   
/*     */   public Map<Object, Object> getUserData() {
/* 131 */     return this.delegate.getUserData();
/*     */   }
/*     */   
/*     */   public Collection<? extends Property> getValue() {
/* 135 */     return this.delegate.getValue();
/*     */   }
/*     */   
/*     */   public boolean isNillable() {
/* 139 */     return this.delegate.isNillable();
/*     */   }
/*     */   
/*     */   public void setAttribute(int position, Object val) {
/* 143 */     this.delegate.setAttribute(position, val);
/*     */   }
/*     */   
/*     */   public void setAttribute(Name arg0, Object arg1) {
/* 147 */     this.delegate.setAttribute(arg0, arg1);
/*     */   }
/*     */   
/*     */   public void setAttribute(String path, Object attribute) {
/* 151 */     this.delegate.setAttribute(path, attribute);
/*     */   }
/*     */   
/*     */   public void setAttributes(List<Object> arg0) {
/* 155 */     this.delegate.setAttributes(arg0);
/*     */   }
/*     */   
/*     */   public void setAttributes(Object[] arg0) {
/* 159 */     this.delegate.setAttributes(arg0);
/*     */   }
/*     */   
/*     */   public void setDefaultGeometry(Object arg0) {
/* 163 */     this.delegate.setDefaultGeometry(arg0);
/*     */   }
/*     */   
/*     */   public void setDefaultGeometryProperty(GeometryAttribute arg0) {
/* 167 */     this.delegate.setDefaultGeometryProperty(arg0);
/*     */   }
/*     */   
/*     */   public void setDefaultGeometry(Geometry geometry) throws IllegalAttributeException {
/* 172 */     this.delegate.setDefaultGeometry(geometry);
/*     */   }
/*     */   
/*     */   public void setValue(Collection<Property> arg0) {
/* 176 */     this.delegate.setValue(arg0);
/*     */   }
/*     */   
/*     */   public void setValue(Object arg0) {
/* 180 */     this.delegate.setValue(arg0);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 183 */     return this.delegate.equals(obj);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 186 */     return this.delegate.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 189 */     return "<" + getClass().getCanonicalName() + ">" + this.delegate.toString();
/*     */   }
/*     */   
/*     */   public void validate() {
/* 193 */     this.delegate.validate();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\DecoratingFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */