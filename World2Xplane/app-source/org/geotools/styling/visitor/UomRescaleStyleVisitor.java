/*     */ package org.geotools.styling.visitor;
/*     */ 
/*     */ import java.util.Map;
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.styling.Displacement;
/*     */ import org.geotools.styling.Fill;
/*     */ import org.geotools.styling.Font;
/*     */ import org.geotools.styling.Graphic;
/*     */ import org.geotools.styling.LabelPlacement;
/*     */ import org.geotools.styling.LinePlacement;
/*     */ import org.geotools.styling.LineSymbolizer;
/*     */ import org.geotools.styling.Mark;
/*     */ import org.geotools.styling.PointPlacement;
/*     */ import org.geotools.styling.PointSymbolizer;
/*     */ import org.geotools.styling.PolygonSymbolizer;
/*     */ import org.geotools.styling.Stroke;
/*     */ import org.geotools.styling.TextSymbolizer;
/*     */ import org.geotools.styling.TextSymbolizer2;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.Displacement;
/*     */ import org.opengis.style.GraphicalSymbol;
/*     */ import org.opengis.style.LabelPlacement;
/*     */ 
/*     */ public class UomRescaleStyleVisitor extends DuplicatingStyleVisitor {
/*     */   double mapScale;
/*     */   
/*     */   public UomRescaleStyleVisitor(double mapScale) {
/*  72 */     if (mapScale <= 0.0D)
/*  73 */       throw new IllegalArgumentException("The mapScale is out of range. Value is " + Double.toString(mapScale) + ". It must be positive."); 
/*  76 */     this.mapScale = mapScale;
/*     */   }
/*     */   
/*     */   protected Expression rescale(Expression unscaled, Unit<Length> uom) {
/*  88 */     if (unscaled == null || unscaled.equals(Expression.NIL))
/*  89 */       return unscaled; 
/*  92 */     Measure m = new Measure(unscaled, uom);
/*  93 */     return RescalingMode.RealWorld.rescaleToExpression((Expression)this.ff.literal(this.mapScale), m);
/*     */   }
/*     */   
/*     */   protected String rescale(String unscaled, Unit<Length> uom) {
/* 105 */     if (unscaled == null)
/* 106 */       return unscaled; 
/* 109 */     Measure v = new Measure(unscaled, uom);
/* 110 */     return RescalingMode.RealWorld.rescaleToString(this.mapScale, v);
/*     */   }
/*     */   
/*     */   protected float[] rescale(float[] dashArray, Unit<Length> unitOfMeasure) {
/* 122 */     if (dashArray == null)
/* 123 */       return null; 
/* 124 */     if (unitOfMeasure == null || unitOfMeasure.equals(NonSI.PIXEL))
/* 125 */       return dashArray; 
/* 127 */     float[] rescaledDashArray = new float[dashArray.length];
/* 129 */     for (int i = 0; i < rescaledDashArray.length; i++)
/* 130 */       rescaledDashArray[i] = Float.parseFloat(rescale(String.valueOf(dashArray[i]), unitOfMeasure)); 
/* 132 */     return rescaledDashArray;
/*     */   }
/*     */   
/*     */   protected void rescaleStroke(Stroke stroke, Unit<Length> uom) {
/* 143 */     if (stroke != null) {
/* 144 */       stroke.setWidth(rescale(stroke.getWidth(), uom));
/* 145 */       stroke.setDashArray(rescale(stroke.getDashArray(), uom));
/* 146 */       stroke.setDashOffset(rescale(stroke.getDashOffset(), uom));
/* 147 */       rescale(stroke.getGraphicFill(), uom);
/* 148 */       rescale(stroke.getGraphicStroke(), uom);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visit(PointSymbolizer ps) {
/* 154 */     super.visit(ps);
/* 155 */     PointSymbolizer copy = (PointSymbolizer)this.pages.peek();
/* 157 */     Unit<Length> uom = copy.getUnitOfMeasure();
/* 158 */     Graphic copyGraphic = copy.getGraphic();
/* 159 */     rescale(copyGraphic, uom);
/* 160 */     copy.setUnitOfMeasure(NonSI.PIXEL);
/*     */   }
/*     */   
/*     */   private void rescale(Graphic graphic, Unit<Length> unit) {
/* 164 */     if (graphic != null) {
/* 165 */       graphic.setSize(rescale(graphic.getSize(), unit));
/* 166 */       graphic.setGap(rescale(graphic.getGap(), unit));
/* 168 */       Displacement disp = graphic.getDisplacement();
/* 169 */       if (disp != null) {
/* 170 */         disp.setDisplacementX(rescale(disp.getDisplacementX(), unit));
/* 171 */         disp.setDisplacementY(rescale(disp.getDisplacementY(), unit));
/* 172 */         graphic.setDisplacement((Displacement)disp);
/*     */       } 
/* 175 */       if (graphic.graphicalSymbols() != null)
/* 176 */         for (GraphicalSymbol gs : graphic.graphicalSymbols()) {
/* 177 */           if (gs instanceof Mark) {
/* 178 */             Mark mark = (Mark)gs;
/* 179 */             rescaleStroke(mark.getStroke(), unit);
/* 180 */             rescaleFill(mark.getFill(), unit);
/*     */           } 
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visit(LineSymbolizer line) {
/* 189 */     super.visit(line);
/* 190 */     LineSymbolizer copy = (LineSymbolizer)this.pages.peek();
/* 191 */     Unit<Length> uom = copy.getUnitOfMeasure();
/* 192 */     Stroke copyStroke = copy.getStroke();
/* 193 */     rescaleStroke(copyStroke, uom);
/* 194 */     copy.setUnitOfMeasure(NonSI.PIXEL);
/*     */   }
/*     */   
/*     */   public void visit(PolygonSymbolizer poly) {
/* 199 */     super.visit(poly);
/* 200 */     PolygonSymbolizer copy = (PolygonSymbolizer)this.pages.peek();
/* 202 */     Unit<Length> uom = copy.getUnitOfMeasure();
/* 203 */     rescaleStroke(copy.getStroke(), uom);
/* 204 */     rescaleFill(copy.getFill(), uom);
/* 205 */     copy.setUnitOfMeasure(NonSI.PIXEL);
/*     */   }
/*     */   
/*     */   private void rescaleFill(Fill copyFill, Unit<Length> unit) {
/* 210 */     if (copyFill != null)
/* 211 */       rescale(copyFill.getGraphicFill(), unit); 
/*     */   }
/*     */   
/*     */   public void visit(TextSymbolizer text) {
/* 218 */     super.visit(text);
/* 219 */     TextSymbolizer copy = (TextSymbolizer)this.pages.peek();
/* 221 */     Unit<Length> uom = copy.getUnitOfMeasure();
/* 223 */     Font[] fonts = copy.getFonts();
/* 224 */     for (Font font : fonts)
/* 225 */       font.setSize(rescale(font.getSize(), uom)); 
/* 226 */     copy.setFonts(fonts);
/* 229 */     LabelPlacement placement = copy.getLabelPlacement();
/* 230 */     if (placement instanceof PointPlacement) {
/* 232 */       PointPlacement pointPlacement = (PointPlacement)placement;
/* 233 */       Displacement disp = pointPlacement.getDisplacement();
/* 234 */       if (disp != null) {
/* 235 */         disp.setDisplacementX(rescale(disp.getDisplacementX(), uom));
/* 236 */         disp.setDisplacementY(rescale(disp.getDisplacementY(), uom));
/* 237 */         pointPlacement.setDisplacement((Displacement)disp);
/*     */       } 
/* 239 */     } else if (placement instanceof LinePlacement) {
/* 241 */       LinePlacement linePlacement = (LinePlacement)placement;
/* 242 */       linePlacement.setGap(rescale(linePlacement.getGap(), uom));
/* 243 */       linePlacement.setInitialGap(rescale(linePlacement.getInitialGap(), uom));
/* 244 */       linePlacement.setPerpendicularOffset(rescale(linePlacement.getPerpendicularOffset(), uom));
/*     */     } 
/* 247 */     copy.setLabelPlacement((LabelPlacement)placement);
/* 250 */     if (copy.getHalo() != null)
/* 251 */       copy.getHalo().setRadius(rescale(copy.getHalo().getRadius(), uom)); 
/* 254 */     if (copy instanceof TextSymbolizer2) {
/* 255 */       TextSymbolizer2 copy2 = (TextSymbolizer2)copy;
/* 257 */       rescale(copy2.getGraphic(), uom);
/*     */     } 
/* 261 */     Map<String, String> options = copy.getOptions();
/* 262 */     scaleIntOption(options, "maxDisplacement", uom);
/* 263 */     scaleIntOption(options, "spaceAround", uom);
/* 264 */     scaleIntOption(options, "minGroupDistance", uom);
/* 265 */     scaleIntOption(options, "repeat", uom);
/* 266 */     scaleIntOption(options, "autoWrap", uom);
/* 267 */     scaleIntArrayOption(options, "graphic-margin", uom);
/* 269 */     copy.setUnitOfMeasure(NonSI.PIXEL);
/*     */   }
/*     */   
/*     */   private void scaleIntOption(Map<String, String> options, String optionName, Unit<Length> uom) {
/* 273 */     if (options.containsKey(optionName)) {
/* 274 */       String rescaled = rescale(options.get(optionName), uom);
/* 276 */       options.put(optionName, toInt(rescaled));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void scaleIntArrayOption(Map<String, String> options, String optionName, Unit<Length> uom) {
/* 282 */     if (options.containsKey(optionName)) {
/* 283 */       String strValue = options.get(optionName);
/* 284 */       String[] splitted = strValue.split("\\s+");
/* 285 */       StringBuilder sb = new StringBuilder();
/* 286 */       for (String value : splitted) {
/* 287 */         String rescaled = rescale(value, uom);
/* 288 */         sb.append(toInt(rescaled)).append(" ");
/*     */       } 
/* 290 */       sb.setLength(sb.length() - 1);
/* 291 */       options.put(optionName, sb.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   String toInt(String value) {
/* 296 */     Double dv = Double.valueOf(value);
/* 297 */     return String.valueOf(dv.intValue());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\visitor\UomRescaleStyleVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */