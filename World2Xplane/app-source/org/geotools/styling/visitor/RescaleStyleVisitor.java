/*     */ package org.geotools.styling.visitor;
/*     */ 
/*     */ import java.util.Map;
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.styling.Displacement;
/*     */ import org.geotools.styling.ExternalGraphic;
/*     */ import org.geotools.styling.Font;
/*     */ import org.geotools.styling.Graphic;
/*     */ import org.geotools.styling.LabelPlacement;
/*     */ import org.geotools.styling.LinePlacement;
/*     */ import org.geotools.styling.LineSymbolizer;
/*     */ import org.geotools.styling.Mark;
/*     */ import org.geotools.styling.PointPlacement;
/*     */ import org.geotools.styling.PointSymbolizer;
/*     */ import org.geotools.styling.PolygonSymbolizer;
/*     */ import org.geotools.styling.RasterSymbolizer;
/*     */ import org.geotools.styling.Stroke;
/*     */ import org.geotools.styling.Symbol;
/*     */ import org.geotools.styling.Symbolizer;
/*     */ import org.geotools.styling.TextSymbolizer;
/*     */ import org.geotools.util.Converters;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.Displacement;
/*     */ import org.opengis.style.Graphic;
/*     */ import org.opengis.style.LabelPlacement;
/*     */ 
/*     */ public class RescaleStyleVisitor extends DuplicatingStyleVisitor {
/*     */   protected Expression scale;
/*     */   
/*     */   protected Unit<Length> defaultUnit;
/*     */   
/*     */   public RescaleStyleVisitor(double scale) {
/*  82 */     this(CommonFactoryFinder.getFilterFactory2(null), scale);
/*     */   }
/*     */   
/*     */   public RescaleStyleVisitor(Expression scale) {
/*  86 */     this(CommonFactoryFinder.getFilterFactory2(null), scale);
/*     */   }
/*     */   
/*     */   public RescaleStyleVisitor(FilterFactory2 filterFactory, double scale) {
/*  90 */     this(filterFactory, (Expression)filterFactory.literal(scale));
/*     */   }
/*     */   
/*     */   public RescaleStyleVisitor(FilterFactory2 filterFactory, Expression scale) {
/*  94 */     super(CommonFactoryFinder.getStyleFactory(null), filterFactory);
/*  95 */     this.scale = scale;
/*     */   }
/*     */   
/*     */   protected Expression rescale(Expression expr) {
/* 107 */     if (expr == null)
/* 108 */       return null; 
/* 110 */     if (expr == Expression.NIL)
/* 111 */       return Expression.NIL; 
/* 114 */     Measure m = new Measure(expr, this.defaultUnit);
/* 115 */     return RescalingMode.KeepUnits.rescaleToExpression(this.scale, m);
/*     */   }
/*     */   
/*     */   public void visit(Stroke stroke) {
/* 125 */     Stroke copy = this.sf.getDefaultStroke();
/* 126 */     copy.setColor(copy(stroke.getColor()));
/* 127 */     copy.setDashArray(rescale(stroke.getDashArray()));
/* 128 */     copy.setDashOffset(rescale(stroke.getDashOffset()));
/* 129 */     copy.setGraphicFill((Graphic)copy(stroke.getGraphicFill()));
/* 130 */     copy.setGraphicStroke((Graphic)copy(stroke.getGraphicStroke()));
/* 131 */     copy.setLineCap(copy(stroke.getLineCap()));
/* 132 */     copy.setLineJoin(copy(stroke.getLineJoin()));
/* 133 */     copy.setOpacity(copy(stroke.getOpacity()));
/* 134 */     copy.setWidth(rescale(stroke.getWidth()));
/* 135 */     this.pages.push(copy);
/*     */   }
/*     */   
/*     */   float[] rescale(float[] original) {
/* 139 */     if (original == null)
/* 140 */       return null; 
/* 144 */     float[] rescaled = new float[original.length];
/* 145 */     float scaleFactor = 1.0F;
/* 146 */     if (this.scale instanceof org.opengis.filter.expression.Literal)
/* 147 */       scaleFactor = ((Float)this.scale.evaluate(null, Float.class)).floatValue(); 
/* 149 */     for (int i = 0; i < rescaled.length; i++)
/* 150 */       rescaled[i] = scaleFactor * original[i]; 
/* 153 */     return rescaled;
/*     */   }
/*     */   
/*     */   public void visit(Graphic gr) {
/* 158 */     Graphic copy = null;
/* 160 */     Displacement displacementCopy = null;
/* 162 */     if (gr.getDisplacement() != null) {
/* 163 */       gr.getDisplacement().accept(this);
/* 164 */       displacementCopy = (Displacement)this.pages.pop();
/*     */     } 
/* 167 */     ExternalGraphic[] externalGraphics = gr.getExternalGraphics();
/* 168 */     ExternalGraphic[] externalGraphicsCopy = new ExternalGraphic[externalGraphics.length];
/* 170 */     int length = externalGraphics.length;
/* 171 */     for (int i = 0; i < length; i++)
/* 172 */       externalGraphicsCopy[i] = copy(externalGraphics[i]); 
/* 175 */     Mark[] marks = gr.getMarks();
/* 176 */     Mark[] marksCopy = new Mark[marks.length];
/* 177 */     length = marks.length;
/* 178 */     for (int j = 0; j < length; j++)
/* 179 */       marksCopy[j] = copy(marks[j]); 
/* 182 */     Expression opacityCopy = copy(gr.getOpacity());
/* 183 */     Expression rotationCopy = copy(gr.getRotation());
/* 184 */     Expression sizeCopy = rescaleGraphicSize(gr);
/* 186 */     Symbol[] symbols = gr.getSymbols();
/* 187 */     length = symbols.length;
/* 188 */     Symbol[] symbolCopys = new Symbol[length];
/* 190 */     for (int k = 0; k < length; k++)
/* 191 */       symbolCopys[k] = copy(symbols[k]); 
/* 194 */     copy = this.sf.createDefaultGraphic();
/* 195 */     copy.setDisplacement((Displacement)displacementCopy);
/* 196 */     copy.setExternalGraphics(externalGraphicsCopy);
/* 197 */     copy.setMarks(marksCopy);
/* 198 */     copy.setOpacity(opacityCopy);
/* 199 */     copy.setRotation(rotationCopy);
/* 200 */     copy.setSize(sizeCopy);
/* 201 */     copy.setSymbols(symbolCopys);
/* 203 */     this.pages.push(copy);
/*     */   }
/*     */   
/*     */   protected Expression rescaleGraphicSize(Graphic gr) {
/* 207 */     return rescale(gr.getSize());
/*     */   }
/*     */   
/*     */   public void visit(TextSymbolizer text) {
/* 212 */     this.defaultUnit = text.getUnitOfMeasure();
/*     */     try {
/* 214 */       super.visit(text);
/* 215 */       TextSymbolizer copy = (TextSymbolizer)this.pages.peek();
/* 218 */       Font[] fonts = copy.getFonts();
/* 219 */       for (Font font : fonts)
/* 220 */         font.setSize(rescale(font.getSize())); 
/* 222 */       copy.setFonts(fonts);
/* 225 */       LabelPlacement placement = copy.getLabelPlacement();
/* 226 */       if (placement instanceof PointPlacement) {
/* 228 */         PointPlacement pointPlacement = (PointPlacement)placement;
/* 229 */         Displacement disp = pointPlacement.getDisplacement();
/* 230 */         if (disp != null) {
/* 231 */           disp.setDisplacementX(rescale(disp.getDisplacementX()));
/* 232 */           disp.setDisplacementY(rescale(disp.getDisplacementY()));
/* 233 */           pointPlacement.setDisplacement((Displacement)disp);
/*     */         } 
/* 235 */       } else if (placement instanceof LinePlacement) {
/* 237 */         LinePlacement linePlacement = (LinePlacement)placement;
/* 238 */         linePlacement.setGap(rescale(linePlacement.getGap()));
/* 239 */         linePlacement.setInitialGap(rescale(linePlacement.getInitialGap()));
/* 240 */         linePlacement.setPerpendicularOffset(rescale(linePlacement.getPerpendicularOffset()));
/*     */       } 
/* 242 */       copy.setLabelPlacement((LabelPlacement)placement);
/* 245 */       if (copy.getHalo() != null)
/* 246 */         copy.getHalo().setRadius(rescale(copy.getHalo().getRadius())); 
/* 250 */       Map<String, String> options = copy.getOptions();
/* 251 */       rescaleOption(options, "spaceAround", 0);
/* 252 */       rescaleOption(options, "maxDisplacement", 0);
/* 253 */       rescaleOption(options, "minGroupDistance", -1);
/* 254 */       rescaleOption(options, "repeat", 0);
/* 255 */       rescaleOption(options, "autoWrap", 0);
/* 256 */       rescaleArrayOption(options, "graphic-margin", 0);
/*     */     } finally {
/* 258 */       this.defaultUnit = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visit(Symbolizer sym) {
/* 264 */     this.defaultUnit = sym.getUnitOfMeasure();
/*     */     try {
/* 266 */       super.visit(sym);
/*     */     } finally {
/* 268 */       this.defaultUnit = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visit(PointSymbolizer sym) {
/* 274 */     this.defaultUnit = sym.getUnitOfMeasure();
/*     */     try {
/* 276 */       super.visit(sym);
/*     */     } finally {
/* 278 */       this.defaultUnit = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visit(LineSymbolizer sym) {
/* 284 */     this.defaultUnit = sym.getUnitOfMeasure();
/*     */     try {
/* 286 */       super.visit(sym);
/*     */     } finally {
/* 288 */       this.defaultUnit = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visit(PolygonSymbolizer sym) {
/* 294 */     this.defaultUnit = sym.getUnitOfMeasure();
/*     */     try {
/* 296 */       super.visit(sym);
/*     */     } finally {
/* 298 */       this.defaultUnit = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visit(RasterSymbolizer sym) {
/* 304 */     this.defaultUnit = sym.getUnitOfMeasure();
/*     */     try {
/* 306 */       super.visit(sym);
/*     */     } finally {
/* 308 */       this.defaultUnit = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void rescaleOption(Map<String, String> options, String key, double defaultValue) {
/* 320 */     double scaleFactor = ((Double)this.scale.evaluate(null, Double.class)).doubleValue();
/* 321 */     if (options.get(key) != null) {
/* 322 */       double rescaled = ((Double)Converters.convert(options.get(key), Double.class)).doubleValue() * scaleFactor;
/* 323 */       options.put(key, String.valueOf(rescaled));
/* 324 */     } else if (defaultValue != 0.0D) {
/* 325 */       options.put(key, String.valueOf(defaultValue * scaleFactor));
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void rescaleOption(Map<String, String> options, String key, int defaultValue) {
/* 338 */     double scaleFactor = ((Double)this.scale.evaluate(null, Double.class)).doubleValue();
/* 339 */     if (options.get(key) != null) {
/* 340 */       int rescaled = (int)Math.round(((Double)Converters.convert(options.get(key), Double.class)).doubleValue() * scaleFactor);
/* 341 */       options.put(key, String.valueOf(rescaled));
/* 342 */     } else if (defaultValue != 0) {
/* 343 */       options.put(key, String.valueOf((int)Math.round(defaultValue * scaleFactor)));
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void rescaleArrayOption(Map<String, String> options, String key, int defaultValue) {
/* 356 */     double scaleFactor = ((Double)this.scale.evaluate(null, Double.class)).doubleValue();
/* 357 */     if (options.get(key) != null) {
/* 358 */       String strValue = options.get(key);
/* 359 */       String[] splitted = strValue.split("\\s+");
/* 360 */       StringBuilder sb = new StringBuilder();
/* 361 */       for (String value : splitted) {
/* 362 */         double rescaled = (int)Math.round(Double.parseDouble(value) * scaleFactor);
/* 363 */         sb.append((int)rescaled).append(" ");
/*     */       } 
/* 365 */       sb.setLength(sb.length() - 1);
/* 366 */       options.put(key, sb.toString());
/* 367 */     } else if (defaultValue != 0) {
/* 368 */       options.put(key, String.valueOf((int)Math.round(defaultValue * scaleFactor)));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\visitor\RescaleStyleVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */