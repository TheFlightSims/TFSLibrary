/*     */ package org.geotools.feature.type;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ 
/*     */ public class PropertyDescriptorImpl implements PropertyDescriptor {
/*     */   protected final PropertyType type;
/*     */   
/*     */   protected final Name name;
/*     */   
/*     */   protected final int minOccurs;
/*     */   
/*     */   protected final int maxOccurs;
/*     */   
/*     */   protected final boolean isNillable;
/*     */   
/*     */   final Map<Object, Object> userData;
/*     */   
/*     */   protected PropertyDescriptorImpl(PropertyType type, Name name, int min, int max, boolean isNillable) {
/*  43 */     this.type = type;
/*  44 */     this.name = name;
/*  45 */     this.minOccurs = min;
/*  46 */     this.maxOccurs = max;
/*  47 */     this.isNillable = isNillable;
/*  48 */     this.userData = new HashMap<Object, Object>();
/*  50 */     if (type == null)
/*  51 */       throw new NullPointerException("type"); 
/*  54 */     if (name == null)
/*  55 */       throw new NullPointerException("name"); 
/*  58 */     if (type == null)
/*  59 */       throw new NullPointerException(); 
/*  62 */     if (max > 0 && max < min)
/*  63 */       throw new IllegalArgumentException("max must be -1, or >= min"); 
/*     */   }
/*     */   
/*     */   public PropertyType getType() {
/*  68 */     return this.type;
/*     */   }
/*     */   
/*     */   public Name getName() {
/*  72 */     return this.name;
/*     */   }
/*     */   
/*     */   public int getMinOccurs() {
/*  76 */     return this.minOccurs;
/*     */   }
/*     */   
/*     */   public int getMaxOccurs() {
/*  80 */     return this.maxOccurs;
/*     */   }
/*     */   
/*     */   public boolean isNillable() {
/*  84 */     return this.isNillable;
/*     */   }
/*     */   
/*     */   public Map<Object, Object> getUserData() {
/*  88 */     return this.userData;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  92 */     if (!(obj instanceof PropertyDescriptorImpl))
/*  93 */       return false; 
/*  96 */     PropertyDescriptorImpl other = (PropertyDescriptorImpl)obj;
/*  97 */     return (Utilities.equals(this.type, other.type) && Utilities.equals(this.name, other.name) && this.minOccurs == other.minOccurs && this.maxOccurs == other.maxOccurs && this.isNillable == other.isNillable);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 104 */     return 37 * this.minOccurs + 37 * this.maxOccurs ^ this.type.hashCode() ^ this.name.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 108 */     StringBuffer sb = new StringBuffer(Classes.getShortClassName(this));
/* 109 */     sb.append(" ");
/* 110 */     sb.append(getName());
/* 111 */     if (this.type != null) {
/* 112 */       sb.append(" <");
/* 113 */       sb.append(this.type.getName().getLocalPart());
/* 114 */       sb.append(":");
/* 115 */       sb.append(Classes.getShortName(this.type.getBinding()));
/* 116 */       sb.append(">");
/*     */     } 
/* 118 */     if (this.isNillable)
/* 119 */       sb.append(" nillable"); 
/* 121 */     if (this.minOccurs != 1 || this.maxOccurs != 1) {
/* 125 */       sb.append(" ");
/* 126 */       sb.append(this.minOccurs);
/* 127 */       sb.append(":");
/* 128 */       sb.append(this.maxOccurs);
/*     */     } 
/* 130 */     if (this.userData != null && !this.userData.isEmpty()) {
/* 131 */       sb.append("\nuserData=(");
/* 132 */       for (Map.Entry<Object, Object> entry : this.userData.entrySet()) {
/* 133 */         sb.append("\n\t");
/* 134 */         sb.append(entry.getKey());
/* 135 */         sb.append(" ==> ");
/* 136 */         sb.append(entry.getValue());
/*     */       } 
/* 138 */       sb.append(")");
/*     */     } 
/* 140 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\PropertyDescriptorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */