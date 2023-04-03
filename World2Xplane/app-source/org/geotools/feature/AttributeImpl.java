/*     */ package org.geotools.feature;
/*     */ 
/*     */ import org.geotools.feature.type.AttributeDescriptorImpl;
/*     */ import org.geotools.feature.type.Types;
/*     */ import org.geotools.util.Converters;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.feature.Attribute;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ import org.opengis.filter.identity.Identifier;
/*     */ 
/*     */ public class AttributeImpl extends PropertyImpl implements Attribute {
/*  47 */   protected Identifier id = null;
/*     */   
/*     */   public AttributeImpl(Object content, AttributeDescriptor descriptor, Identifier id) {
/*  51 */     super(content, (PropertyDescriptor)descriptor);
/*  52 */     this.id = id;
/*     */   }
/*     */   
/*     */   public AttributeImpl(Object content, AttributeType type, Identifier id) {
/*  56 */     this(content, (AttributeDescriptor)new AttributeDescriptorImpl(type, type.getName(), 1, 1, true, null), id);
/*     */   }
/*     */   
/*     */   public Identifier getIdentifier() {
/*  60 */     return this.id;
/*     */   }
/*     */   
/*     */   public AttributeDescriptor getDescriptor() {
/*  64 */     return (AttributeDescriptor)super.getDescriptor();
/*     */   }
/*     */   
/*     */   public AttributeType getType() {
/*  68 */     return (AttributeType)super.getType();
/*     */   }
/*     */   
/*     */   public void setValue(Object newValue) throws IllegalArgumentException, IllegalStateException {
/*  78 */     newValue = parse(newValue);
/*  79 */     super.setValue(newValue);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  88 */     return super.hashCode() + 37 * ((this.id == null) ? 0 : this.id.hashCode());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 100 */     if (this == obj)
/* 101 */       return true; 
/* 104 */     if (!(obj instanceof Attribute))
/* 105 */       return false; 
/* 108 */     if (!super.equals(obj))
/* 109 */       return false; 
/* 112 */     Attribute att = (Attribute)obj;
/* 114 */     return Utilities.equals(this.id, att.getIdentifier());
/*     */   }
/*     */   
/*     */   public void validate() {
/* 118 */     Types.validate(this, getValue());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 122 */     StringBuffer sb = (new StringBuffer(getClass().getSimpleName())).append(":");
/* 123 */     sb.append(getDescriptor().getName().getLocalPart());
/* 124 */     if (!getDescriptor().getName().getLocalPart().equals(getDescriptor().getType().getName().getLocalPart()) || this.id != null) {
/* 126 */       sb.append("<");
/* 127 */       sb.append(getDescriptor().getType().getName().getLocalPart());
/* 128 */       if (this.id != null) {
/* 129 */         sb.append(" id=");
/* 130 */         sb.append(this.id);
/*     */       } 
/* 132 */       sb.append(">");
/*     */     } 
/* 134 */     sb.append("=");
/* 135 */     sb.append(this.value);
/* 136 */     return sb.toString();
/*     */   }
/*     */   
/*     */   protected Object parse(Object value) throws IllegalArgumentException {
/* 157 */     if (value != null) {
/* 158 */       Class target = getType().getBinding();
/* 159 */       if (!target.isAssignableFrom(value.getClass())) {
/* 161 */         Object converted = Converters.convert(value, target);
/* 162 */         if (converted != null)
/* 163 */           value = converted; 
/*     */       } 
/*     */     } 
/* 168 */     return value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\AttributeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */