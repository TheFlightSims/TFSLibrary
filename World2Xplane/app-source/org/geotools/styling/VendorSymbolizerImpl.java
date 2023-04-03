/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.ExtensionSymbolizer;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.style.Symbolizer;
/*     */ 
/*     */ public class VendorSymbolizerImpl extends AbstractSymbolizer implements ExtensionSymbolizer {
/*     */   private String extensionName;
/*     */   
/*  43 */   private Map<String, Expression> parameters = new HashMap<String, Expression>();
/*     */   
/*     */   public int hashCode() {
/*  55 */     int prime = 31;
/*  56 */     int result = super.hashCode();
/*  57 */     result = 31 * result + ((this.extensionName == null) ? 0 : this.extensionName.hashCode());
/*  58 */     result = 31 * result + ((this.parameters == null) ? 0 : this.parameters.hashCode());
/*  59 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  66 */     if (this == obj)
/*  67 */       return true; 
/*  68 */     if (!super.equals(obj))
/*  69 */       return false; 
/*  70 */     if (getClass() != obj.getClass())
/*  71 */       return false; 
/*  72 */     VendorSymbolizerImpl other = (VendorSymbolizerImpl)obj;
/*  73 */     if (this.extensionName == null) {
/*  74 */       if (other.extensionName != null)
/*  75 */         return false; 
/*  76 */     } else if (!this.extensionName.equals(other.extensionName)) {
/*  77 */       return false;
/*     */     } 
/*  78 */     if (this.parameters == null) {
/*  79 */       if (other.parameters != null)
/*  80 */         return false; 
/*  81 */     } else if (!this.parameters.equals(other.parameters)) {
/*  82 */       return false;
/*     */     } 
/*  83 */     return true;
/*     */   }
/*     */   
/*     */   static VendorSymbolizerImpl cast(Symbolizer symbolizer) {
/*  89 */     if (symbolizer == null)
/*  90 */       return null; 
/*  92 */     if (symbolizer instanceof VendorSymbolizerImpl)
/*  93 */       return (VendorSymbolizerImpl)symbolizer; 
/*  95 */     if (symbolizer instanceof ExtensionSymbolizer) {
/*  96 */       ExtensionSymbolizer extensionSymbolizer = (ExtensionSymbolizer)symbolizer;
/*  97 */       VendorSymbolizerImpl copy = new VendorSymbolizerImpl();
/*  98 */       copy.setDescription(extensionSymbolizer.getDescription());
/*  99 */       copy.setGeometryPropertyName(extensionSymbolizer.getGeometryPropertyName());
/* 100 */       copy.setName(extensionSymbolizer.getName());
/* 101 */       copy.setUnitOfMeasure(extensionSymbolizer.getUnitOfMeasure());
/* 103 */       return copy;
/*     */     } 
/* 106 */     return null;
/*     */   }
/*     */   
/*     */   public String getExtensionName() {
/* 111 */     return this.extensionName;
/*     */   }
/*     */   
/*     */   public Map<String, Expression> getParameters() {
/* 115 */     return this.parameters;
/*     */   }
/*     */   
/*     */   public void setExtensionName(String name) {
/* 119 */     this.extensionName = name;
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 123 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 127 */     visitor.visit(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\VendorSymbolizerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */