/*     */ package org.geotools.styling.visitor;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ enum RescalingMode {
/*  40 */   KeepUnits {
/*  46 */     final Map<Unit, String> UNIT_SYMBOLS = new HashMap<Unit, String>() {
/*     */       
/*     */       };
/*     */     
/*     */     public String rescaleToStringInternal(double scaleFactor, Measure measure) {
/*     */       String rescaledString;
/*  56 */       double rescaled = measure.value.doubleValue() * scaleFactor;
/*  58 */       if (rescaled == (int)rescaled) {
/*  59 */         rescaledString = String.valueOf((int)rescaled);
/*     */       } else {
/*  61 */         rescaledString = String.valueOf(rescaled);
/*     */       } 
/*  63 */       if (measure.isPixelInPixelDefault())
/*  64 */         return rescaledString; 
/*  66 */       return rescaledString + (String)this.UNIT_SYMBOLS.get(measure.uom);
/*     */     }
/*     */   },
/*  75 */   Pixels {
/*     */     public String rescaleToStringInternal(double scaleFactor, Measure measure) {
/*  78 */       if (measure.isRealWorldUnit())
/*  79 */         return String.valueOf(measure.value); 
/*  81 */       return String.valueOf(measure.value.doubleValue() * scaleFactor);
/*     */     }
/*     */     
/*     */     public Expression rescaleToExpression(Expression scaleFactor, Measure measure) {
/*  87 */       if (measure.isRealWorldUnit())
/*  88 */         return measure.expression; 
/*  90 */       return super.rescaleToExpression(scaleFactor, measure);
/*     */     }
/*     */   },
/*  99 */   RealWorld {
/*     */     public String rescaleToStringInternal(double scaleFactor, Measure measure) {
/* 102 */       return String.valueOf(measure.value.doubleValue() * computeRescaleMultiplier(scaleFactor, measure.uom));
/*     */     }
/*     */     
/*     */     double computeRescaleMultiplier(double mapScale, Unit<Length> uom) {
/* 115 */       if (uom == null || uom.equals(NonSI.PIXEL))
/* 116 */         return 1.0D; 
/* 118 */       if (uom == SI.METER)
/* 119 */         return mapScale; 
/* 123 */       UnitConverter converter = uom.getConverterTo(SI.METER);
/* 124 */       return converter.convert(mapScale);
/*     */     }
/*     */   };
/*     */   
/*     */   public String rescaleToString(double scaleFactor, Measure measure) {
/* 132 */     if (measure.value == null)
/* 133 */       throw new IllegalStateException("Cannot rescale to literal, the value is a generic expression, not a static value: " + measure.expression); 
/* 138 */     return rescaleToStringInternal(scaleFactor, measure);
/*     */   }
/*     */   
/*     */   public Expression rescaleToExpression(Expression scaleFactor, Measure measure) {
/* 142 */     if (measure.value != null && scaleFactor instanceof org.opengis.filter.expression.Literal) {
/* 143 */       Double scale = (Double)scaleFactor.evaluate(null, Double.class);
/* 144 */       return (Expression)Measure.ff.literal(rescaleToStringInternal(scale.doubleValue(), measure));
/*     */     } 
/* 149 */     return (Expression)Measure.ff.function("rescaleToPixels", new Expression[] { measure.expression, (Expression)Measure.ff.literal(measure.uom), scaleFactor, (Expression)Measure.ff.literal(this) });
/*     */   }
/*     */   
/*     */   public abstract String rescaleToStringInternal(double paramDouble, Measure paramMeasure);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\visitor\RescalingMode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */