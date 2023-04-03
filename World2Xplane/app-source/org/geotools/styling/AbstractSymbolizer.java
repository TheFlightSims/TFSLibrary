/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ import org.opengis.style.Description;
/*     */ 
/*     */ public abstract class AbstractSymbolizer implements Symbolizer {
/*     */   protected String name;
/*     */   
/*     */   protected Description description;
/*     */   
/*     */   protected Expression geometry;
/*     */   
/*     */   protected Unit<Length> unitOfMeasure;
/*     */   
/*     */   protected Map<String, String> options;
/*     */   
/*     */   protected AbstractSymbolizer() {}
/*     */   
/*     */   public AbstractSymbolizer(String name, Description description, Expression geometry, Unit<Length> unitOfMeasure) {
/*  34 */     this.name = name;
/*  35 */     this.description = description;
/*  36 */     this.geometry = geometry;
/*  37 */     this.unitOfMeasure = unitOfMeasure;
/*     */   }
/*     */   
/*     */   public AbstractSymbolizer(String name, Description description, String geometryPropertyName, Unit<Length> unitOfMeasure) {
/*  42 */     this.name = name;
/*  43 */     this.description = description;
/*  44 */     this.unitOfMeasure = unitOfMeasure;
/*  45 */     setGeometryPropertyName(geometryPropertyName);
/*     */   }
/*     */   
/*     */   public Description getDescription() {
/*  49 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(Description description) {
/*  53 */     this.description = DescriptionImpl.cast(description);
/*     */   }
/*     */   
/*     */   public String getName() {
/*  57 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  61 */     this.name = name;
/*     */   }
/*     */   
/*     */   public void setUnitOfMeasure(Unit<Length> uom) {
/*  65 */     this.unitOfMeasure = uom;
/*     */   }
/*     */   
/*     */   public Unit<Length> getUnitOfMeasure() {
/*  69 */     return this.unitOfMeasure;
/*     */   }
/*     */   
/*     */   public Expression getGeometry() {
/*  73 */     return this.geometry;
/*     */   }
/*     */   
/*     */   public void setGeometry(Expression geometry) {
/*  77 */     this.geometry = geometry;
/*     */   }
/*     */   
/*     */   public String getGeometryPropertyName() {
/*  81 */     if (this.geometry instanceof PropertyName) {
/*  82 */       PropertyName pg = (PropertyName)this.geometry;
/*  83 */       return pg.getPropertyName();
/*     */     } 
/*  85 */     return null;
/*     */   }
/*     */   
/*     */   public void setGeometryPropertyName(String geometryPropertyName) {
/*  89 */     if (geometryPropertyName == null) {
/*  90 */       this.geometry = null;
/*     */     } else {
/*  92 */       FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/*  93 */       this.geometry = (Expression)ff.property(geometryPropertyName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasOption(String key) {
/*  98 */     return (this.options != null && this.options.containsKey(key));
/*     */   }
/*     */   
/*     */   public Map<String, String> getOptions() {
/* 102 */     if (this.options == null)
/* 103 */       this.options = new HashMap<String, String>(); 
/* 105 */     return this.options;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 110 */     int prime = 31;
/* 111 */     int result = 1;
/* 112 */     result = 31 * result + ((this.description == null) ? 0 : this.description.hashCode());
/* 113 */     result = 31 * result + ((this.geometry == null) ? 0 : this.geometry.hashCode());
/* 114 */     result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
/* 115 */     result = 31 * result + ((this.unitOfMeasure == null) ? 0 : this.unitOfMeasure.hashCode());
/* 116 */     result = 31 * result + ((this.options == null) ? 0 : this.options.hashCode());
/* 117 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 122 */     if (this == obj)
/* 123 */       return true; 
/* 124 */     if (obj == null)
/* 125 */       return false; 
/* 126 */     if (getClass() != obj.getClass())
/* 127 */       return false; 
/* 128 */     AbstractSymbolizer other = (AbstractSymbolizer)obj;
/* 129 */     if (this.description == null) {
/* 130 */       if (other.description != null)
/* 131 */         return false; 
/* 132 */     } else if (!this.description.equals(other.description)) {
/* 133 */       return false;
/*     */     } 
/* 134 */     if (this.geometry == null) {
/* 135 */       if (other.geometry != null)
/* 136 */         return false; 
/* 137 */     } else if (!this.geometry.equals(other.geometry)) {
/* 138 */       return false;
/*     */     } 
/* 139 */     if (this.name == null) {
/* 140 */       if (other.name != null)
/* 141 */         return false; 
/* 142 */     } else if (!this.name.equals(other.name)) {
/* 143 */       return false;
/*     */     } 
/* 144 */     if (this.unitOfMeasure == null) {
/* 145 */       if (other.unitOfMeasure != null)
/* 146 */         return false; 
/* 147 */     } else if (!this.unitOfMeasure.equals(other.unitOfMeasure)) {
/* 148 */       return false;
/*     */     } 
/* 149 */     if (this.options == null) {
/* 150 */       if (other.options != null && !other.options.isEmpty())
/* 151 */         return false; 
/* 152 */     } else if (!this.options.equals(other.options) && 
/* 153 */       this.options.isEmpty() && other.options != null) {
/* 154 */       return false;
/*     */     } 
/* 155 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\AbstractSymbolizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */