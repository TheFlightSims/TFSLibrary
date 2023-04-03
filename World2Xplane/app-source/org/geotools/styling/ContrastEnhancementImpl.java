/*     */ package org.geotools.styling;
/*     */ 
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.ContrastEnhancement;
/*     */ import org.opengis.style.ContrastMethod;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ 
/*     */ public class ContrastEnhancementImpl implements ContrastEnhancement {
/*     */   private FilterFactory filterFactory;
/*     */   
/*     */   private Expression gamma;
/*     */   
/*     */   private Expression type;
/*     */   
/*     */   private ContrastMethod method;
/*     */   
/*     */   public ContrastEnhancementImpl() {
/*  81 */     this(CommonFactoryFinder.getFilterFactory(null));
/*     */   }
/*     */   
/*     */   public ContrastEnhancementImpl(FilterFactory factory) {
/*  85 */     this(factory, null);
/*     */   }
/*     */   
/*     */   public ContrastEnhancementImpl(FilterFactory factory, ContrastMethod method) {
/*  89 */     this.filterFactory = factory;
/*  90 */     this.method = method;
/*     */   }
/*     */   
/*     */   public ContrastEnhancementImpl(ContrastEnhancement contrastEnhancement) {
/*  94 */     this.filterFactory = (FilterFactory)CommonFactoryFinder.getFilterFactory2(null);
/*  95 */     this.method = contrastEnhancement.getMethod();
/*  96 */     this.gamma = contrastEnhancement.getGammaValue();
/*     */   }
/*     */   
/*     */   public ContrastEnhancementImpl(FilterFactory2 factory, Expression gamma, ContrastMethod method) {
/* 100 */     this.filterFactory = (FilterFactory)factory;
/* 101 */     this.gamma = gamma;
/* 102 */     this.method = method;
/* 103 */     this.type = (Expression)factory.literal(method.name());
/*     */   }
/*     */   
/*     */   public void setFilterFactory(FilterFactory factory) {
/* 107 */     this.filterFactory = factory;
/*     */   }
/*     */   
/*     */   public Expression getGammaValue() {
/* 111 */     return this.gamma;
/*     */   }
/*     */   
/*     */   public Expression getType() {
/* 115 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setGammaValue(Expression gamma) {
/* 119 */     this.gamma = gamma;
/*     */   }
/*     */   
/*     */   public void setHistogram() {
/* 123 */     this.type = (Expression)this.filterFactory.literal("Histogram");
/* 124 */     this.method = ContrastMethod.HISTOGRAM;
/*     */   }
/*     */   
/*     */   public void setNormalize() {
/* 128 */     this.type = (Expression)this.filterFactory.literal("Normalize");
/* 129 */     this.method = ContrastMethod.NORMALIZE;
/*     */   }
/*     */   
/*     */   public void setLogarithmic() {
/* 133 */     this.type = (Expression)this.filterFactory.literal("Logarithmic");
/* 134 */     this.method = ContrastMethod.NONE;
/*     */   }
/*     */   
/*     */   public void setExponential() {
/* 138 */     this.type = (Expression)this.filterFactory.literal("Exponential");
/* 139 */     this.method = ContrastMethod.NONE;
/*     */   }
/*     */   
/*     */   public void setType(Expression type) {
/* 143 */     this.type = type;
/* 144 */     if (type instanceof org.opengis.filter.expression.Literal) {
/* 145 */       String value = (String)type.evaluate(null, String.class);
/* 146 */       if ("Histogram".equalsIgnoreCase(value)) {
/* 147 */         this.method = ContrastMethod.HISTOGRAM;
/* 148 */       } else if ("Normalize".equalsIgnoreCase(value)) {
/* 149 */         this.method = ContrastMethod.NORMALIZE;
/*     */       } else {
/* 151 */         this.method = ContrastMethod.NONE;
/*     */       } 
/*     */     } else {
/* 154 */       this.method = ContrastMethod.NONE;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setMethod(ContrastMethod method) {
/* 159 */     if (method == ContrastMethod.NORMALIZE) {
/* 160 */       this.type = (Expression)this.filterFactory.literal("Normalize");
/* 161 */       this.method = ContrastMethod.NORMALIZE;
/* 162 */     } else if (method == ContrastMethod.HISTOGRAM) {
/* 163 */       this.type = (Expression)this.filterFactory.literal("Histogram");
/* 164 */       this.method = ContrastMethod.HISTOGRAM;
/*     */     } else {
/* 166 */       this.method = method;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ContrastMethod getMethod() {
/* 171 */     return this.method;
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object extraData) {
/* 175 */     return null;
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 179 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 184 */     int PRIME = 1000003;
/* 185 */     int result = 0;
/* 187 */     if (this.gamma != null)
/* 188 */       result = 1000003 * result + this.gamma.hashCode(); 
/* 191 */     if (this.type != null)
/* 192 */       result = 1000003 * result + this.type.hashCode(); 
/* 195 */     if (this.method != null)
/* 196 */       result = 1000003 * result + this.method.hashCode(); 
/* 199 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 204 */     if (this == obj)
/* 205 */       return true; 
/* 208 */     if (obj instanceof ContrastEnhancementImpl) {
/* 209 */       ContrastEnhancementImpl other = (ContrastEnhancementImpl)obj;
/* 211 */       return (Utilities.equals(this.gamma, other.gamma) && Utilities.equals(this.type, other.type) && Utilities.equals(this.method, other.method));
/*     */     } 
/* 216 */     return false;
/*     */   }
/*     */   
/*     */   static ContrastEnhancementImpl cast(ContrastEnhancement enhancement) {
/* 220 */     if (enhancement == null)
/* 221 */       return null; 
/* 223 */     if (enhancement instanceof ContrastEnhancementImpl)
/* 224 */       return (ContrastEnhancementImpl)enhancement; 
/* 227 */     ContrastEnhancementImpl copy = new ContrastEnhancementImpl();
/* 228 */     copy.setGammaValue(enhancement.getGammaValue());
/* 229 */     copy.setMethod(enhancement.getMethod());
/* 230 */     return copy;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ContrastEnhancementImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */