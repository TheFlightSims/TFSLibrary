/*     */ package org.geotools.styling.visitor;
/*     */ 
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.util.Converters;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ class UomRescaleHelper {
/*  16 */   private double mapScale = 1.0D;
/*     */   
/*     */   private FilterFactory ff;
/*     */   
/*     */   private boolean rescaleRealWorldUnits;
/*     */   
/*     */   public UomRescaleHelper(FilterFactory ff, double mapScale, boolean rescaleRealWorldUnits) {
/*  23 */     this.rescaleRealWorldUnits = rescaleRealWorldUnits;
/*  24 */     this.ff = ff;
/*  25 */     this.mapScale = mapScale;
/*     */   }
/*     */   
/*     */   protected double computeRescaleMultiplier(Unit<Length> uom) {
/*  37 */     if (uom == null || uom.equals(NonSI.PIXEL))
/*  38 */       return 1.0D; 
/*  40 */     if (uom == SI.METER)
/*  41 */       return this.mapScale; 
/*  45 */     UnitConverter converter = uom.getConverterTo(SI.METER);
/*  46 */     return converter.convert(this.mapScale);
/*     */   }
/*     */   
/*     */   protected Expression rescale(Expression unscaled, Unit<Length> uom) {
/*  58 */     if (unscaled == null || unscaled.equals(Expression.NIL))
/*  59 */       return unscaled; 
/*  61 */     if (unscaled instanceof org.opengis.filter.expression.Literal) {
/*  63 */       String value = (String)unscaled.evaluate(null, String.class);
/*  64 */       if (value == null)
/*  65 */         throw new IllegalArgumentException("Invalid empty measure '', was expecting a number, eventually followed by px, m or ft"); 
/*  68 */       if (value.endsWith("px")) {
/*  70 */         value = value.substring(0, value.length() - 2);
/*  71 */         return (Expression)this.ff.literal(Converters.convert(value, Double.class));
/*     */       } 
/*  73 */       if (value.endsWith("ft")) {
/*  74 */         value = value.substring(0, value.length() - 2);
/*  75 */         uom = NonSI.FOOT;
/*  76 */       } else if (value.endsWith("m")) {
/*  77 */         value = value.substring(0, value.length() - 1);
/*  78 */         uom = SI.METER;
/*     */       } 
/*  80 */       Double measure = (Double)Converters.convert(value, Double.class);
/*  81 */       if (measure != null) {
/*  82 */         double rescaled = rescale(measure.doubleValue(), uom);
/*  83 */         return (Expression)this.ff.literal(rescaled);
/*     */       } 
/*  85 */       throw new IllegalArgumentException("Invalid measure '" + value + "', was expecting a number, eventually followed by px, m or ft");
/*     */     } 
/*  91 */     double rescaleMultiplier = computeRescaleMultiplier(uom);
/*  92 */     return (Expression)this.ff.multiply(unscaled, (Expression)this.ff.literal(rescaleMultiplier));
/*     */   }
/*     */   
/*     */   protected float[] rescale(float[] dashArray, Unit<Length> unitOfMeasure) {
/* 105 */     if (dashArray == null)
/* 106 */       return null; 
/* 107 */     if (unitOfMeasure == null || unitOfMeasure.equals(NonSI.PIXEL))
/* 108 */       return dashArray; 
/* 110 */     float[] rescaledDashArray = new float[dashArray.length];
/* 112 */     for (int i = 0; i < rescaledDashArray.length; i++)
/* 113 */       rescaledDashArray[i] = (float)rescale(dashArray[i], unitOfMeasure); 
/* 115 */     return rescaledDashArray;
/*     */   }
/*     */   
/*     */   private double rescale(double unscaled, Unit<Length> uom) {
/* 128 */     return unscaled * computeRescaleMultiplier(uom);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\visitor\UomRescaleHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */