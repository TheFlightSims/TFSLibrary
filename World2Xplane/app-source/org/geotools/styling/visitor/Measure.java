/*     */ package org.geotools.styling.visitor;
/*     */ 
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.filter.visitor.SimplifyingFilterVisitor;
/*     */ import org.geotools.util.Converters;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.PropertyIsNull;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.expression.NilExpression;
/*     */ 
/*     */ class Measure {
/*  43 */   static final FilterFactory ff = CommonFactoryFinder.getFilterFactory();
/*     */   
/*     */   Expression expression;
/*     */   
/*     */   Double value;
/*     */   
/*     */   Unit<Length> uom;
/*     */   
/*     */   Unit<Length> defaultUnit;
/*     */   
/*     */   public Measure(String value, Unit<Length> defaultUnit) {
/*  54 */     setDefaultUnit(defaultUnit);
/*  55 */     processLiteralExpression(value, defaultUnit);
/*     */   }
/*     */   
/*     */   public Measure(Expression unscaled, Unit<Length> defaultUnit) {
/*  59 */     this.expression = unscaled;
/*  60 */     setDefaultUnit(defaultUnit);
/*  61 */     this.uom = defaultUnit;
/*  62 */     if (unscaled instanceof Literal) {
/*  63 */       processLiteralExpression((Literal)unscaled, defaultUnit);
/*     */     } else {
/*  66 */       PropertyIsNull test = ff.isNull(unscaled);
/*  67 */       Filter simplified = (Filter)test.accept((FilterVisitor)new SimplifyingFilterVisitor(), null);
/*  68 */       if (simplified == Filter.INCLUDE) {
/*  70 */         this.expression = NilExpression.NIL;
/*  71 */         this.uom = defaultUnit;
/*  72 */       } else if (simplified instanceof PropertyIsNull) {
/*  73 */         PropertyIsNull pin = (PropertyIsNull)simplified;
/*  74 */         Expression se = pin.getExpression();
/*  75 */         if (se instanceof Literal) {
/*  76 */           processLiteralExpression((Literal)se, defaultUnit);
/*     */         } else {
/*  78 */           this.expression = se;
/*  79 */           this.uom = defaultUnit;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setDefaultUnit(Unit<Length> defaultUnit) {
/*  86 */     if (defaultUnit == null) {
/*  87 */       this.defaultUnit = NonSI.PIXEL;
/*     */     } else {
/*  89 */       this.defaultUnit = defaultUnit;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void processLiteralExpression(Literal literal, Unit<Length> defaultUnit) {
/*  95 */     String value = (String)literal.evaluate(null, String.class);
/*  96 */     if (value == null)
/*     */       return; 
/* 100 */     processLiteralExpression(value, defaultUnit);
/*     */   }
/*     */   
/*     */   private void processLiteralExpression(String value, Unit<Length> defaultUnit) {
/* 104 */     Unit<Length> uom = defaultUnit;
/* 105 */     String unitless = value;
/* 107 */     if (value.endsWith("px")) {
/* 108 */       unitless = value.substring(0, value.length() - 2);
/* 109 */       uom = NonSI.PIXEL;
/* 110 */     } else if (value.endsWith("ft")) {
/* 111 */       unitless = value.substring(0, value.length() - 2);
/* 112 */       uom = NonSI.FOOT;
/* 113 */     } else if (value.endsWith("m")) {
/* 114 */       unitless = value.substring(0, value.length() - 1);
/* 115 */       uom = SI.METER;
/*     */     } 
/* 117 */     Double measure = (Double)Converters.convert(unitless, Double.class);
/* 118 */     if (measure == null)
/* 119 */       throw new IllegalArgumentException("Invalid measure '" + value + "', was expecting a number, eventually followed by px, m or ft"); 
/* 122 */     this.expression = (Expression)ff.literal(value);
/* 123 */     this.value = measure;
/* 124 */     this.uom = uom;
/*     */   }
/*     */   
/*     */   boolean isRealWorldUnit() {
/* 133 */     return (this.uom != null && this.uom != NonSI.PIXEL);
/*     */   }
/*     */   
/*     */   boolean isPixelInPixelDefault() {
/* 143 */     return ((this.uom == null || this.uom == this.defaultUnit) && (this.defaultUnit == null || this.defaultUnit == NonSI.PIXEL));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\visitor\Measure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */