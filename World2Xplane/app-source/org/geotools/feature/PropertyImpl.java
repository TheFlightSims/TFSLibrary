/*     */ package org.geotools.feature;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.feature.Property;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ 
/*     */ public abstract class PropertyImpl implements Property {
/*     */   protected Object value;
/*     */   
/*     */   protected PropertyDescriptor descriptor;
/*     */   
/*     */   protected final Map<Object, Object> userData;
/*     */   
/*     */   protected PropertyImpl(Object value, PropertyDescriptor descriptor) {
/*  53 */     this.value = value;
/*  54 */     this.descriptor = descriptor;
/*  55 */     this.userData = new HashMap<Object, Object>();
/*  57 */     if (descriptor == null)
/*  58 */       throw new NullPointerException("descriptor"); 
/*     */   }
/*     */   
/*     */   public Object getValue() {
/*  63 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(Object value) {
/*  67 */     this.value = value;
/*     */   }
/*     */   
/*     */   public PropertyDescriptor getDescriptor() {
/*  71 */     return this.descriptor;
/*     */   }
/*     */   
/*     */   public Name getName() {
/*  75 */     return getDescriptor().getName();
/*     */   }
/*     */   
/*     */   public PropertyType getType() {
/*  79 */     return getDescriptor().getType();
/*     */   }
/*     */   
/*     */   public boolean isNillable() {
/*  83 */     return getDescriptor().isNillable();
/*     */   }
/*     */   
/*     */   public Map<Object, Object> getUserData() {
/*  87 */     return this.userData;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  91 */     if (this == obj)
/*  92 */       return true; 
/*  95 */     if (!(obj instanceof PropertyImpl))
/*  96 */       return false; 
/*  99 */     PropertyImpl other = (PropertyImpl)obj;
/* 101 */     if (!Utilities.equals(this.descriptor, other.descriptor))
/* 102 */       return false; 
/* 104 */     if (!Utilities.deepEquals(this.value, other.value))
/* 105 */       return false; 
/* 107 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 111 */     return 37 * this.descriptor.hashCode() + 37 * ((this.value == null) ? 0 : this.value.hashCode());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 116 */     StringBuffer sb = (new StringBuffer(getClass().getSimpleName())).append(":");
/* 117 */     sb.append(getDescriptor().getName().getLocalPart());
/* 118 */     sb.append("<");
/* 119 */     sb.append(getDescriptor().getType().getName().getLocalPart());
/* 120 */     sb.append(">=");
/* 121 */     sb.append(this.value);
/* 123 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\PropertyImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */