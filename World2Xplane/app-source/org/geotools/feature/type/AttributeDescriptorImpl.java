/*     */ package org.geotools.feature.type;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ 
/*     */ public class AttributeDescriptorImpl extends PropertyDescriptorImpl implements AttributeDescriptor {
/*     */   protected final Object defaultValue;
/*     */   
/*     */   public AttributeDescriptorImpl(AttributeType type, Name name, int min, int max, boolean isNillable, Object defaultValue) {
/*  40 */     super((PropertyType)type, name, min, max, isNillable);
/*  42 */     this.defaultValue = defaultValue;
/*     */   }
/*     */   
/*     */   public AttributeType getType() {
/*  46 */     return (AttributeType)super.getType();
/*     */   }
/*     */   
/*     */   public Object getDefaultValue() {
/*  50 */     return this.defaultValue;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  54 */     return super.hashCode() ^ ((this.defaultValue != null) ? this.defaultValue.hashCode() : 0);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  59 */     if (!(o instanceof AttributeDescriptorImpl))
/*  60 */       return false; 
/*  62 */     AttributeDescriptorImpl d = (AttributeDescriptorImpl)o;
/*  64 */     return (super.equals(o) && Utilities.deepEquals(this.defaultValue, d.defaultValue));
/*     */   }
/*     */   
/*     */   public String toString() {
/*  68 */     StringBuffer sb = new StringBuffer(Classes.getShortClassName(this));
/*  69 */     sb.append(" ");
/*  70 */     sb.append(getName());
/*  71 */     if (this.type != null) {
/*  72 */       sb.append(" <");
/*  73 */       sb.append(this.type.getName().getLocalPart());
/*  74 */       sb.append(":");
/*  75 */       sb.append(Classes.getShortName(this.type.getBinding()));
/*  76 */       sb.append(">");
/*     */     } 
/*  78 */     if (this.isNillable)
/*  79 */       sb.append(" nillable"); 
/*  81 */     if (this.minOccurs != 1 || this.maxOccurs != 1) {
/*  85 */       sb.append(" ");
/*  86 */       sb.append(this.minOccurs);
/*  87 */       sb.append(":");
/*  88 */       sb.append(this.maxOccurs);
/*     */     } 
/*  90 */     if (this.defaultValue != null) {
/*  91 */       sb.append("\ndefault= ");
/*  92 */       sb.append(this.defaultValue);
/*     */     } 
/*  94 */     if (this.userData != null && !this.userData.isEmpty()) {
/*  95 */       sb.append("\nuserData=(");
/*  96 */       for (Map.Entry<Object, Object> entry : this.userData.entrySet()) {
/*  97 */         sb.append("\n\t");
/*  98 */         sb.append(entry.getKey());
/*  99 */         sb.append(" ==> ");
/* 100 */         sb.append(entry.getValue());
/*     */       } 
/* 102 */       sb.append(")");
/*     */     } 
/* 104 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public String getLocalName() {
/* 108 */     return getName().getLocalPart();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\AttributeDescriptorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */