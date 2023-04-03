/*      */ package org.geotools.styling;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.net.URL;
/*      */ import java.util.Arrays;
/*      */ import java.util.logging.Logger;
/*      */ import org.geotools.data.simple.SimpleFeatureCollection;
/*      */ import org.geotools.data.simple.SimpleFeatureIterator;
/*      */ import org.geotools.factory.CommonFactoryFinder;
/*      */ import org.geotools.factory.GeoTools;
/*      */ import org.geotools.filter.IllegalFilterException;
/*      */ import org.geotools.util.logging.Logging;
/*      */ import org.opengis.feature.simple.SimpleFeature;
/*      */ import org.opengis.feature.simple.SimpleFeatureType;
/*      */ import org.opengis.filter.Filter;
/*      */ import org.opengis.filter.FilterFactory;
/*      */ import org.opengis.filter.FilterFactory2;
/*      */ import org.opengis.filter.PropertyIsBetween;
/*      */ import org.opengis.filter.PropertyIsGreaterThan;
/*      */ import org.opengis.filter.PropertyIsLessThan;
/*      */ import org.opengis.filter.expression.Expression;
/*      */ import org.opengis.filter.expression.Literal;
/*      */ import org.opengis.filter.expression.PropertyName;
/*      */ 
/*      */ public class StyleBuilder {
/*   46 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.styling");
/*      */   
/*      */   public static final String LINE_JOIN_MITRE = "mitre";
/*      */   
/*      */   public static final String LINE_JOIN_ROUND = "round";
/*      */   
/*      */   public static final String LINE_JOIN_BEVEL = "bevel";
/*      */   
/*      */   public static final String LINE_CAP_BUTT = "butt";
/*      */   
/*      */   public static final String LINE_CAP_ROUND = "round";
/*      */   
/*      */   public static final String LINE_CAP_SQUARE = "square";
/*      */   
/*      */   public static final String MARK_SQUARE = "square";
/*      */   
/*      */   public static final String MARK_CIRCLE = "circle";
/*      */   
/*      */   public static final String MARK_TRIANGLE = "triangle";
/*      */   
/*      */   public static final String MARK_STAR = "star";
/*      */   
/*      */   public static final String MARK_CROSS = "cross";
/*      */   
/*      */   public static final String MARK_ARROW = "arrow";
/*      */   
/*      */   public static final String MARK_X = "x";
/*      */   
/*      */   public static final String FONT_STYLE_NORMAL = "normal";
/*      */   
/*      */   public static final String FONT_STYLE_ITALIC = "italic";
/*      */   
/*      */   public static final String FONT_STYLE_OBLIQUE = "oblique";
/*      */   
/*      */   public static final String FONT_WEIGHT_NORMAL = "normal";
/*      */   
/*      */   public static final String FONT_WEIGHT_BOLD = "bold";
/*      */   
/*      */   private StyleFactory sf;
/*      */   
/*      */   private FilterFactory2 ff;
/*      */   
/*      */   public StyleBuilder() {
/*   74 */     this(CommonFactoryFinder.getStyleFactory(GeoTools.getDefaultHints()));
/*      */   }
/*      */   
/*      */   public StyleBuilder(StyleFactory styleFactory) {
/*   83 */     this(styleFactory, CommonFactoryFinder.getFilterFactory(GeoTools.getDefaultHints()));
/*      */   }
/*      */   
/*      */   public StyleBuilder(FilterFactory filterFactory) {
/*   92 */     this(CommonFactoryFinder.getStyleFactory(GeoTools.getDefaultHints()), filterFactory);
/*      */   }
/*      */   
/*      */   public StyleBuilder(StyleFactory styleFactory, FilterFactory filterFactory) {
/*  102 */     this.sf = styleFactory;
/*  103 */     this.ff = (FilterFactory2)filterFactory;
/*      */   }
/*      */   
/*      */   public void setStyleFactory(StyleFactory factory) {
/*  112 */     this.sf = factory;
/*      */   }
/*      */   
/*      */   public StyleFactory getStyleFactory() {
/*  120 */     return this.sf;
/*      */   }
/*      */   
/*      */   public void setFilterFactory(FilterFactory factory) {
/*  129 */     this.ff = (FilterFactory2)factory;
/*      */   }
/*      */   
/*      */   public FilterFactory2 getFilterFactory() {
/*  138 */     return this.ff;
/*      */   }
/*      */   
/*      */   public Stroke createStroke() {
/*  147 */     return this.sf.getDefaultStroke();
/*      */   }
/*      */   
/*      */   public Stroke createStroke(double width) {
/*  158 */     return createStroke(Color.BLACK, width);
/*      */   }
/*      */   
/*      */   public Stroke createStroke(Color color) {
/*  169 */     return createStroke(color, 1.0D);
/*      */   }
/*      */   
/*      */   public Stroke createStroke(Color color, double width) {
/*  181 */     return this.sf.createStroke(colorExpression(color), literalExpression(width));
/*      */   }
/*      */   
/*      */   public Stroke createStroke(Color color, double width, String lineJoin, String lineCap) {
/*  195 */     Stroke stroke = createStroke(color, width);
/*  196 */     stroke.setLineJoin(literalExpression(lineJoin));
/*  197 */     stroke.setLineCap(literalExpression(lineCap));
/*  199 */     return stroke;
/*      */   }
/*      */   
/*      */   public Stroke createStroke(Color color, double width, float[] dashArray) {
/*  212 */     Stroke stroke = createStroke(color, width);
/*  213 */     stroke.setDashArray(dashArray);
/*  215 */     return stroke;
/*      */   }
/*      */   
/*      */   public Stroke createStroke(Expression color, Expression width) {
/*  227 */     return this.sf.createStroke(color, width);
/*      */   }
/*      */   
/*      */   public Stroke createStroke(Color color, double width, double opacity) {
/*  241 */     return this.sf.createStroke(colorExpression(color), literalExpression(width), literalExpression(opacity));
/*      */   }
/*      */   
/*      */   public Stroke createStroke(Expression color, Expression width, Expression opacity) {
/*  258 */     return this.sf.createStroke(color, width, opacity);
/*      */   }
/*      */   
/*      */   public Fill createFill() {
/*  267 */     Fill f = this.sf.getDefaultFill();
/*  268 */     f.setColor(literalExpression("#808080"));
/*  269 */     f.setBackgroundColor(literalExpression("#808080"));
/*  270 */     f.setOpacity(literalExpression(1.0D));
/*  272 */     return f;
/*      */   }
/*      */   
/*      */   public Fill createFill(Color fillColor) {
/*  283 */     return createFill(colorExpression(fillColor));
/*      */   }
/*      */   
/*      */   public Fill createFill(Expression fillColor) {
/*  294 */     return this.sf.createFill(fillColor);
/*      */   }
/*      */   
/*      */   public Fill createFill(Color fillColor, double opacity) {
/*  306 */     return this.sf.createFill(colorExpression(fillColor), literalExpression(opacity));
/*      */   }
/*      */   
/*      */   public Fill createFill(Expression color, Expression opacity) {
/*  319 */     return this.sf.createFill(color, opacity);
/*      */   }
/*      */   
/*      */   public Fill createFill(Color color, Color backgroundColor, double opacity, Graphic fill) {
/*  334 */     return this.sf.createFill(colorExpression(color), colorExpression(backgroundColor), literalExpression(opacity), fill);
/*      */   }
/*      */   
/*      */   public Fill createFill(Expression color, Expression backgroundColor, Expression opacity, Graphic fill) {
/*  357 */     return this.sf.createFill(color, backgroundColor, opacity, fill);
/*      */   }
/*      */   
/*      */   public String[] getWellKnownMarkNames() {
/*  366 */     return new String[] { "square", "circle", "triangle", "star", "cross", "arrow", "x" };
/*      */   }
/*      */   
/*      */   public Mark createMark(String wellKnownName) {
/*  384 */     Mark mark = this.sf.createMark();
/*  385 */     mark.setWellKnownName(literalExpression(wellKnownName));
/*  387 */     return mark;
/*      */   }
/*      */   
/*      */   public Mark createMark(String wellKnownName, Color fillColor, Color borderColor, double borderWidth) {
/*  405 */     Mark mark = this.sf.createMark();
/*  406 */     mark.setWellKnownName(literalExpression(wellKnownName));
/*  407 */     mark.setStroke(createStroke(borderColor, borderWidth));
/*  408 */     mark.setFill(createFill(fillColor));
/*  410 */     return mark;
/*      */   }
/*      */   
/*      */   public Mark createMark(String wellKnownName, Color borderColor, double borderWidth) {
/*  423 */     Mark mark = this.sf.createMark();
/*  424 */     mark.setWellKnownName(literalExpression(wellKnownName));
/*  425 */     mark.setStroke(createStroke(borderColor, borderWidth));
/*  427 */     return mark;
/*      */   }
/*      */   
/*      */   public Mark createMark(String wellKnownName, Color fillColor) {
/*  439 */     Mark mark = this.sf.createMark();
/*  440 */     mark.setWellKnownName(literalExpression(wellKnownName));
/*  441 */     mark.setFill(createFill(fillColor, 1.0D));
/*  442 */     mark.setStroke(null);
/*  444 */     return mark;
/*      */   }
/*      */   
/*      */   public Mark createMark(String wellKnownName, Fill fill, Stroke stroke) {
/*  457 */     Mark mark = this.sf.createMark();
/*  458 */     mark.setWellKnownName(literalExpression(wellKnownName));
/*  459 */     mark.setStroke(stroke);
/*  460 */     mark.setFill(fill);
/*  462 */     return mark;
/*      */   }
/*      */   
/*      */   public Mark createMark(Expression wellKnownName, Fill fill, Stroke stroke) {
/*  475 */     Mark mark = this.sf.createMark();
/*  476 */     mark.setWellKnownName(wellKnownName);
/*  477 */     mark.setStroke(stroke);
/*  478 */     mark.setFill(fill);
/*  480 */     return mark;
/*      */   }
/*      */   
/*      */   public ExternalGraphic createExternalGraphic(String uri, String format) {
/*  492 */     return this.sf.createExternalGraphic(uri, format);
/*      */   }
/*      */   
/*      */   public ExternalGraphic createExternalGraphic(URL url, String format) {
/*  504 */     return this.sf.createExternalGraphic(url, format);
/*      */   }
/*      */   
/*      */   public Graphic createGraphic() {
/*  513 */     Graphic gr = this.sf.getDefaultGraphic();
/*  515 */     Mark mark = createMark("square", Color.decode("#808080"), Color.BLACK, 1.0D);
/*  516 */     gr.setMarks(new Mark[] { mark });
/*  517 */     gr.setSize(Expression.NIL);
/*  519 */     return gr;
/*      */   }
/*      */   
/*      */   public Graphic createGraphic(ExternalGraphic externalGraphic, Mark mark, Symbol symbol) {
/*  532 */     Graphic gr = this.sf.getDefaultGraphic();
/*  534 */     if (symbol != null) {
/*  535 */       gr.setSymbols(new Symbol[] { symbol });
/*      */     } else {
/*  537 */       gr.setSymbols(new Symbol[0]);
/*      */     } 
/*  540 */     if (externalGraphic != null)
/*  541 */       gr.setExternalGraphics(new ExternalGraphic[] { externalGraphic }); 
/*  544 */     if (mark != null) {
/*  545 */       gr.setMarks(new Mark[] { mark });
/*      */     } else {
/*  547 */       gr.setMarks(new Mark[0]);
/*      */     } 
/*  550 */     return gr;
/*      */   }
/*      */   
/*      */   public Graphic createGraphic(ExternalGraphic externalGraphic, Mark mark, Symbol symbol, double opacity, double size, double rotation) {
/*  572 */     ExternalGraphic[] egs = null;
/*  573 */     Mark[] marks = null;
/*  574 */     Symbol[] symbols = null;
/*  576 */     if (externalGraphic != null)
/*  577 */       egs = new ExternalGraphic[] { externalGraphic }; 
/*  580 */     if (mark != null)
/*  581 */       marks = new Mark[] { mark }; 
/*  584 */     if (symbol != null)
/*  585 */       symbols = new Symbol[] { symbol }; 
/*  588 */     return createGraphic(egs, marks, symbols, literalExpression(opacity), literalExpression(size), literalExpression(rotation));
/*      */   }
/*      */   
/*      */   public Graphic createGraphic(ExternalGraphic[] externalGraphics, Mark[] marks, Symbol[] symbols, double opacity, double size, double rotation) {
/*  616 */     return createGraphic(externalGraphics, marks, symbols, literalExpression(opacity), literalExpression(size), literalExpression(rotation));
/*      */   }
/*      */   
/*      */   public Graphic createGraphic(ExternalGraphic[] externalGraphics, Mark[] marks, Symbol[] symbols, Expression opacity, Expression size, Expression rotation) {
/*  645 */     ExternalGraphic[] exg = externalGraphics;
/*  647 */     if (exg == null)
/*  648 */       exg = new ExternalGraphic[0]; 
/*  651 */     Mark[] m = marks;
/*  653 */     if (m == null)
/*  654 */       m = new Mark[0]; 
/*  657 */     Symbol[] s = symbols;
/*  659 */     if (s == null)
/*  660 */       s = new Symbol[0]; 
/*  663 */     return this.sf.createGraphic(exg, m, s, opacity, size, rotation);
/*      */   }
/*      */   
/*      */   public AnchorPoint createAnchorPoint(double x, double y) {
/*  675 */     return this.sf.createAnchorPoint(literalExpression(x), literalExpression(y));
/*      */   }
/*      */   
/*      */   public AnchorPoint createAnchorPoint(Expression x, Expression y) {
/*  687 */     return this.sf.createAnchorPoint(x, y);
/*      */   }
/*      */   
/*      */   public Displacement createDisplacement(double x, double y) {
/*  699 */     return this.sf.createDisplacement(literalExpression(x), literalExpression(y));
/*      */   }
/*      */   
/*      */   public Displacement createDisplacement(Expression x, Expression y) {
/*  711 */     return this.sf.createDisplacement(x, y);
/*      */   }
/*      */   
/*      */   public PointPlacement createPointPlacement() {
/*  720 */     return this.sf.getDefaultPointPlacement();
/*      */   }
/*      */   
/*      */   public PointPlacement createPointPlacement(double anchorX, double anchorY, double rotation) {
/*  733 */     AnchorPoint anchorPoint = createAnchorPoint(anchorX, anchorY);
/*  735 */     return this.sf.createPointPlacement(anchorPoint, null, literalExpression(rotation));
/*      */   }
/*      */   
/*      */   public PointPlacement createPointPlacement(double anchorX, double anchorY, double displacementX, double displacementY, double rotation) {
/*  755 */     AnchorPoint anchorPoint = createAnchorPoint(anchorX, anchorY);
/*  756 */     Displacement displacement = createDisplacement(displacementX, displacementY);
/*  758 */     return this.sf.createPointPlacement(anchorPoint, displacement, literalExpression(rotation));
/*      */   }
/*      */   
/*      */   public PointPlacement createPointPlacement(AnchorPoint anchorPoint, Displacement displacement, Expression rotation) {
/*  774 */     return this.sf.createPointPlacement(anchorPoint, displacement, rotation);
/*      */   }
/*      */   
/*      */   public LinePlacement createLinePlacement(double offset) {
/*  785 */     return this.sf.createLinePlacement(literalExpression(offset));
/*      */   }
/*      */   
/*      */   public LinePlacement createLinePlacement(Expression offset) {
/*  796 */     return this.sf.createLinePlacement(offset);
/*      */   }
/*      */   
/*      */   public Font createFont(Font font) {
/*  807 */     Expression style, weight, family = literalExpression(font.getFamily());
/*  811 */     if (font.isBold()) {
/*  812 */       weight = literalExpression("bold");
/*      */     } else {
/*  814 */       weight = literalExpression("normal");
/*      */     } 
/*  817 */     if (font.isItalic()) {
/*  818 */       style = literalExpression("italic");
/*      */     } else {
/*  820 */       style = literalExpression("normal");
/*      */     } 
/*  823 */     return this.sf.createFont(family, style, weight, literalExpression(font.getSize2D()));
/*      */   }
/*      */   
/*      */   public Font createFont(String fontFamily, double fontSize) {
/*  835 */     Expression family = literalExpression(fontFamily);
/*  836 */     Expression style = literalExpression("normal");
/*  837 */     Expression weight = literalExpression("normal");
/*  839 */     return this.sf.createFont(family, style, weight, literalExpression(fontSize));
/*      */   }
/*      */   
/*      */   public Font createFont(String fontFamily, boolean italic, boolean bold, double fontSize) {
/*  853 */     Expression style, weight, family = literalExpression(fontFamily);
/*  857 */     if (bold) {
/*  858 */       weight = literalExpression("bold");
/*      */     } else {
/*  860 */       weight = literalExpression("normal");
/*      */     } 
/*  863 */     if (italic) {
/*  864 */       style = literalExpression("italic");
/*      */     } else {
/*  866 */       style = literalExpression("normal");
/*      */     } 
/*  869 */     return this.sf.createFont(family, style, weight, literalExpression(fontSize));
/*      */   }
/*      */   
/*      */   public Font createFont(Expression fontFamily, Expression fontStyle, Expression fontWeight, Expression fontSize) {
/*  887 */     return this.sf.createFont(fontFamily, fontStyle, fontWeight, fontSize);
/*      */   }
/*      */   
/*      */   public Halo createHalo() {
/*  896 */     return this.sf.createHalo(createFill(Color.WHITE), literalExpression(1));
/*      */   }
/*      */   
/*      */   public Halo createHalo(Color color, double radius) {
/*  908 */     return this.sf.createHalo(createFill(color), literalExpression(radius));
/*      */   }
/*      */   
/*      */   public Halo createHalo(Color color, double opacity, double radius) {
/*  921 */     return this.sf.createHalo(createFill(color, opacity), literalExpression(radius));
/*      */   }
/*      */   
/*      */   public Halo createHalo(Fill fill, double radius) {
/*  933 */     return this.sf.createHalo(fill, literalExpression(radius));
/*      */   }
/*      */   
/*      */   public Halo createHalo(Fill fill, Expression radius) {
/*  945 */     return this.sf.createHalo(fill, radius);
/*      */   }
/*      */   
/*      */   public LineSymbolizer createLineSymbolizer() {
/*  954 */     return this.sf.getDefaultLineSymbolizer();
/*      */   }
/*      */   
/*      */   public LineSymbolizer createLineSymbolizer(double width) {
/*  965 */     return createLineSymbolizer(createStroke(width), (String)null);
/*      */   }
/*      */   
/*      */   public LineSymbolizer createLineSymbolizer(Color color) {
/*  976 */     return createLineSymbolizer(createStroke(color), (String)null);
/*      */   }
/*      */   
/*      */   public LineSymbolizer createLineSymbolizer(Color color, double width) {
/*  988 */     return createLineSymbolizer(createStroke(color, width), (String)null);
/*      */   }
/*      */   
/*      */   public LineSymbolizer createLineSymbolizer(Color color, double width, String geometryPropertyName) {
/* 1004 */     return createLineSymbolizer(createStroke(color, width), geometryPropertyName);
/*      */   }
/*      */   
/*      */   public LineSymbolizer createLineSymbolizer(Stroke stroke) {
/* 1015 */     return this.sf.createLineSymbolizer(stroke, null);
/*      */   }
/*      */   
/*      */   public LineSymbolizer createLineSymbolizer(Stroke stroke, String geometryPropertyName) {
/* 1027 */     return this.sf.createLineSymbolizer(stroke, geometryPropertyName);
/*      */   }
/*      */   
/*      */   public PolygonSymbolizer createPolygonSymbolizer() {
/* 1036 */     PolygonSymbolizer ps = this.sf.createPolygonSymbolizer();
/* 1037 */     ps.setFill(createFill());
/* 1038 */     ps.setStroke(createStroke());
/* 1040 */     return ps;
/*      */   }
/*      */   
/*      */   public PolygonSymbolizer createPolygonSymbolizer(Color fillColor) {
/* 1051 */     return createPolygonSymbolizer((Stroke)null, createFill(fillColor));
/*      */   }
/*      */   
/*      */   public PolygonSymbolizer createPolygonSymbolizer(Color fillColor, Color borderColor, double borderWidth) {
/* 1067 */     return createPolygonSymbolizer(createStroke(borderColor, borderWidth), createFill(fillColor));
/*      */   }
/*      */   
/*      */   public PolygonSymbolizer createPolygonSymbolizer(Color borderColor, double borderWidth) {
/* 1081 */     return createPolygonSymbolizer(createStroke(borderColor, borderWidth), (Fill)null);
/*      */   }
/*      */   
/*      */   public PolygonSymbolizer createPolygonSymbolizer(Stroke stroke, Fill fill) {
/* 1093 */     return createPolygonSymbolizer(stroke, fill, (String)null);
/*      */   }
/*      */   
/*      */   public PolygonSymbolizer createPolygonSymbolizer(Stroke stroke, Fill fill, String geometryPropertyName) {
/* 1109 */     return this.sf.createPolygonSymbolizer(stroke, fill, geometryPropertyName);
/*      */   }
/*      */   
/*      */   public PointSymbolizer createPointSymbolizer() {
/* 1118 */     return this.sf.getDefaultPointSymbolizer();
/*      */   }
/*      */   
/*      */   public PointSymbolizer createPointSymbolizer(Graphic graphic) {
/* 1129 */     PointSymbolizer ps = this.sf.createPointSymbolizer();
/* 1130 */     ps.setGraphic(graphic);
/* 1132 */     return ps;
/*      */   }
/*      */   
/*      */   public PointSymbolizer createPointSymbolizer(Graphic graphic, String geometryPropertyName) {
/* 1144 */     return this.sf.createPointSymbolizer(graphic, geometryPropertyName);
/*      */   }
/*      */   
/*      */   public TextSymbolizer createTextSymbolizer() {
/* 1155 */     TextSymbolizer ts = this.sf.createTextSymbolizer();
/* 1157 */     ts.setFill(createFill(Color.BLACK));
/* 1158 */     ts.setLabel(literalExpression("Label"));
/* 1159 */     ts.setFonts(new Font[] { createFont("Lucida Sans", 10.0D) });
/* 1161 */     return ts;
/*      */   }
/*      */   
/*      */   public TextSymbolizer createTextSymbolizer(Color color, Font font, String attributeName) throws IllegalFilterException {
/* 1177 */     return createTextSymbolizer(createFill(color), new Font[] { font }, null, attributeExpression(attributeName), null, null);
/*      */   }
/*      */   
/*      */   public TextSymbolizer createTextSymbolizer(Color color, Font[] fonts, String attributeName) throws IllegalFilterException {
/* 1199 */     return createTextSymbolizer(createFill(color), fonts, null, attributeExpression(attributeName), null, null);
/*      */   }
/*      */   
/*      */   public TextSymbolizer createStaticTextSymbolizer(Color color, Font font, String label) {
/* 1218 */     return createTextSymbolizer(createFill(color), new Font[] { font }, null, literalExpression(label), null, null);
/*      */   }
/*      */   
/*      */   public TextSymbolizer createStaticTextSymbolizer(Color color, Font[] fonts, String label) {
/* 1237 */     return createTextSymbolizer(createFill(color), fonts, null, literalExpression(label), null, null);
/*      */   }
/*      */   
/*      */   public TextSymbolizer createTextSymbolizer(Fill fill, Font[] fonts, Halo halo, Expression label, LabelPlacement labelPlacement, String geometryPropertyName) {
/* 1265 */     TextSymbolizer ts = this.sf.createTextSymbolizer();
/* 1267 */     if (fill != null)
/* 1268 */       ts.setFill(fill); 
/* 1271 */     if (halo != null)
/* 1272 */       ts.setHalo(halo); 
/* 1275 */     if (label != null)
/* 1276 */       ts.setLabel(label); 
/* 1279 */     if (labelPlacement != null)
/* 1280 */       ts.setPlacement(labelPlacement); 
/* 1283 */     if (geometryPropertyName != null)
/* 1284 */       ts.setGeometryPropertyName(geometryPropertyName); 
/* 1287 */     if (fonts != null)
/* 1288 */       ts.setFonts(fonts); 
/* 1291 */     return ts;
/*      */   }
/*      */   
/*      */   public FeatureTypeStyle createFeatureTypeStyle(Symbolizer symbolizer) {
/* 1302 */     return createFeatureTypeStyle((String)null, symbolizer, Double.NaN, Double.NaN);
/*      */   }
/*      */   
/*      */   public Rule createRule(Symbolizer symbolizer) {
/* 1313 */     return createRule(symbolizer, Double.NaN, Double.NaN);
/*      */   }
/*      */   
/*      */   public Rule createRule(Symbolizer[] symbolizers) {
/* 1324 */     return createRule(symbolizers, Double.NaN, Double.NaN);
/*      */   }
/*      */   
/*      */   public Rule createRule(Symbolizer symbolizer, double minScaleDenominator, double maxScaleDenominator) {
/* 1340 */     return createRule(new Symbolizer[] { symbolizer }, minScaleDenominator, maxScaleDenominator);
/*      */   }
/*      */   
/*      */   public Rule createRule(Symbolizer[] symbolizers, double minScaleDenominator, double maxScaleDenominator) {
/* 1356 */     Rule r = this.sf.createRule();
/* 1357 */     r.setSymbolizers(symbolizers);
/* 1359 */     if (!Double.isNaN(maxScaleDenominator)) {
/* 1360 */       r.setMaxScaleDenominator(maxScaleDenominator);
/*      */     } else {
/* 1362 */       r.setMaxScaleDenominator(Double.POSITIVE_INFINITY);
/*      */     } 
/* 1365 */     if (!Double.isNaN(minScaleDenominator)) {
/* 1366 */       r.setMinScaleDenominator(minScaleDenominator);
/*      */     } else {
/* 1368 */       r.setMinScaleDenominator(0.0D);
/*      */     } 
/* 1371 */     return r;
/*      */   }
/*      */   
/*      */   public FeatureTypeStyle createFeatureTypeStyle(Symbolizer symbolizer, double minScaleDenominator, double maxScaleDenominator) {
/* 1387 */     return createFeatureTypeStyle((String)null, symbolizer, minScaleDenominator, maxScaleDenominator);
/*      */   }
/*      */   
/*      */   public FeatureTypeStyle createFeatureTypeStyle(Symbolizer[] symbolizers, double minScaleDenominator, double maxScaleDenominator) {
/* 1403 */     return createFeatureTypeStyle((String)null, symbolizers, minScaleDenominator, maxScaleDenominator);
/*      */   }
/*      */   
/*      */   public FeatureTypeStyle createFeatureTypeStyle(String featureTypeName, Symbolizer symbolizer) {
/* 1417 */     return createFeatureTypeStyle(featureTypeName, symbolizer, Double.NaN, Double.NaN);
/*      */   }
/*      */   
/*      */   public FeatureTypeStyle createFeatureTypeStyle(String featureTypeName, Symbolizer[] symbolizers) {
/* 1431 */     return createFeatureTypeStyle(featureTypeName, symbolizers, Double.NaN, Double.NaN);
/*      */   }
/*      */   
/*      */   public FeatureTypeStyle createFeatureTypeStyle(String typeName, Symbolizer symbolizer, double minScaleDenominator, double maxScaleDenominator) {
/* 1449 */     return createFeatureTypeStyle(typeName, new Symbolizer[] { symbolizer }, minScaleDenominator, maxScaleDenominator);
/*      */   }
/*      */   
/*      */   public FeatureTypeStyle createFeatureTypeStyle(String typeName, Symbolizer[] symbolizers, double minScaleDenominator, double maxScaleDenominator) {
/* 1471 */     Rule r = createRule(symbolizers, minScaleDenominator, maxScaleDenominator);
/* 1474 */     FeatureTypeStyle fts = this.sf.createFeatureTypeStyle();
/* 1475 */     fts.setRules(new Rule[] { r });
/* 1477 */     if (typeName != null)
/* 1478 */       fts.setFeatureTypeName(typeName); 
/* 1481 */     return fts;
/*      */   }
/*      */   
/*      */   public FeatureTypeStyle createFeatureTypeStyle(String typeName, Rule r) {
/* 1494 */     FeatureTypeStyle fts = this.sf.createFeatureTypeStyle();
/* 1495 */     fts.setRules(new Rule[] { r });
/* 1497 */     if (typeName != null)
/* 1498 */       fts.setFeatureTypeName(typeName); 
/* 1501 */     return fts;
/*      */   }
/*      */   
/*      */   public FeatureTypeStyle createFeatureTypeStyle(String typeName, Rule[] rules) {
/* 1513 */     FeatureTypeStyle fts = this.sf.createFeatureTypeStyle();
/* 1514 */     fts.setRules(rules);
/* 1516 */     if (typeName != null)
/* 1517 */       fts.setFeatureTypeName(typeName); 
/* 1520 */     return fts;
/*      */   }
/*      */   
/*      */   public Style createStyle(Symbolizer symbolizer) {
/* 1531 */     return createStyle(null, symbolizer, Double.NaN, Double.NaN);
/*      */   }
/*      */   
/*      */   public Style createStyle(Symbolizer symbolizer, double minScaleDenominator, double maxScaleDenominator) {
/* 1547 */     return createStyle(null, symbolizer, minScaleDenominator, maxScaleDenominator);
/*      */   }
/*      */   
/*      */   public Style createStyle(String typeName, Symbolizer symbolizer) {
/* 1559 */     return createStyle(typeName, symbolizer, Double.NaN, Double.NaN);
/*      */   }
/*      */   
/*      */   public Style createStyle(String typeName, Symbolizer symbolizer, double minScaleDenominator, double maxScaleDenominator) {
/* 1578 */     FeatureTypeStyle fts = createFeatureTypeStyle(typeName, symbolizer, minScaleDenominator, maxScaleDenominator);
/* 1586 */     Style style = this.sf.createStyle();
/* 1587 */     style.addFeatureTypeStyle(fts);
/* 1589 */     return style;
/*      */   }
/*      */   
/*      */   public Style createStyle() {
/* 1598 */     return this.sf.createStyle();
/*      */   }
/*      */   
/*      */   public Expression colorExpression(Color color) {
/* 1609 */     if (color == null)
/* 1610 */       return null; 
/* 1613 */     String redCode = Integer.toHexString(color.getRed());
/* 1614 */     String greenCode = Integer.toHexString(color.getGreen());
/* 1615 */     String blueCode = Integer.toHexString(color.getBlue());
/* 1617 */     if (redCode.length() == 1)
/* 1618 */       redCode = "0" + redCode; 
/* 1621 */     if (greenCode.length() == 1)
/* 1622 */       greenCode = "0" + greenCode; 
/* 1625 */     if (blueCode.length() == 1)
/* 1626 */       blueCode = "0" + blueCode; 
/* 1629 */     String colorCode = "#" + redCode + greenCode + blueCode;
/* 1631 */     return (Expression)this.ff.literal(colorCode.toUpperCase());
/*      */   }
/*      */   
/*      */   public Expression literalExpression(double value) {
/* 1642 */     return (Expression)this.ff.literal(value);
/*      */   }
/*      */   
/*      */   public Expression literalExpression(int value) {
/* 1653 */     return (Expression)this.ff.literal(value);
/*      */   }
/*      */   
/*      */   public Expression literalExpression(String value) {
/*      */     Literal literal;
/* 1664 */     Expression result = null;
/* 1666 */     if (value != null)
/* 1667 */       literal = this.ff.literal(value); 
/* 1670 */     return (Expression)literal;
/*      */   }
/*      */   
/*      */   public Expression literalExpression(Object value) throws IllegalFilterException {
/*      */     Literal literal;
/* 1683 */     Expression result = null;
/* 1685 */     if (value != null)
/* 1686 */       literal = this.ff.literal(value); 
/* 1689 */     return (Expression)literal;
/*      */   }
/*      */   
/*      */   public Expression attributeExpression(String attributeName) throws IllegalFilterException {
/* 1703 */     return (Expression)this.ff.property(attributeName);
/*      */   }
/*      */   
/*      */   public Style buildClassifiedStyle(SimpleFeatureCollection fc, String name, String[] colors, SimpleFeatureType schema) throws IllegalFilterException {
/* 1726 */     PropertyName value = this.ff.property(name);
/* 1727 */     String geomName = schema.getGeometryDescriptor().getLocalName();
/* 1729 */     double[] values = new double[fc.size()];
/* 1730 */     int count = 0;
/* 1732 */     SimpleFeatureIterator it = fc.features();
/*      */     try {
/* 1734 */       while (it.hasNext()) {
/* 1735 */         SimpleFeature f = (SimpleFeature)it.next();
/* 1736 */         values[count++] = ((Number)f.getAttribute(name)).doubleValue();
/*      */       } 
/*      */     } finally {
/* 1740 */       it.close();
/*      */     } 
/* 1744 */     EqualClasses ec = new EqualClasses(colors.length, values);
/* 1747 */     double[] breaks = ec.getBreaks();
/* 1748 */     Style ret = createStyle();
/* 1751 */     Rule[] rules = new Rule[colors.length + 1];
/* 1753 */     PropertyIsLessThan cf1 = this.ff.less((Expression)value, (Expression)this.ff.literal(breaks[0]));
/* 1755 */     LOGGER.fine(cf1.toString());
/* 1756 */     rules[0] = this.sf.createRule();
/* 1757 */     rules[0].setFilter((Filter)cf1);
/* 1760 */     Color c = createColor(colors[0]);
/* 1761 */     PolygonSymbolizer symb1 = createPolygonSymbolizer(c, Color.black, 1.0D);
/* 1765 */     rules[0].setSymbolizers(new Symbolizer[] { symb1 });
/* 1766 */     LOGGER.fine("added low class " + breaks[0] + " " + colors[0]);
/* 1769 */     for (int i = 1; i < colors.length - 1; i++) {
/* 1770 */       rules[i] = this.sf.createRule();
/* 1772 */       PropertyName propertyName = value;
/* 1773 */       Literal literal1 = this.ff.literal(breaks[i - 1]);
/* 1774 */       Literal literal2 = this.ff.literal(breaks[i]);
/* 1775 */       PropertyIsBetween cf = this.ff.between((Expression)propertyName, (Expression)literal1, (Expression)literal2);
/* 1777 */       LOGGER.fine(cf.toString());
/* 1778 */       c = createColor(colors[i]);
/* 1779 */       LOGGER.fine("color " + c.toString());
/* 1781 */       PolygonSymbolizer symb = createPolygonSymbolizer(c, Color.black, 1.0D);
/* 1784 */       rules[i].setSymbolizers(new Symbolizer[] { symb });
/* 1785 */       rules[i].setFilter((Filter)cf);
/* 1788 */       LOGGER.fine("added class " + breaks[i - 1] + "->" + breaks[i] + " " + colors[i]);
/*      */     } 
/* 1791 */     PropertyIsGreaterThan cf2 = this.ff.greater((Expression)value, (Expression)this.ff.literal(breaks[colors.length - 2]));
/* 1793 */     LOGGER.fine(cf2.toString());
/* 1794 */     rules[colors.length - 1] = this.sf.createRule();
/* 1795 */     rules[colors.length - 1].setFilter((Filter)cf2);
/* 1796 */     rules[colors.length - 1].setName(geomName);
/* 1797 */     c = createColor(colors[colors.length - 1]);
/* 1799 */     PolygonSymbolizer symb2 = createPolygonSymbolizer(c, Color.black, 1.0D);
/* 1802 */     rules[colors.length - 1].setSymbolizers(new Symbolizer[] { symb2 });
/* 1803 */     LOGGER.fine("added upper class " + breaks[colors.length - 2] + "  " + colors[colors.length - 1]);
/* 1805 */     rules[colors.length] = this.sf.createRule();
/* 1807 */     PolygonSymbolizer elsePoly = createPolygonSymbolizer(Color.black, 1.0D);
/* 1808 */     rules[colors.length].setSymbolizers(new Symbolizer[] { elsePoly });
/* 1809 */     rules[colors.length].setElseFilter(true);
/* 1811 */     FeatureTypeStyle ft = this.sf.createFeatureTypeStyle(rules);
/* 1812 */     ft.setFeatureTypeName("Feature");
/* 1813 */     ft.setName(name);
/* 1814 */     ret.addFeatureTypeStyle(ft);
/* 1816 */     return ret;
/*      */   }
/*      */   
/*      */   private Color createColor(String text) {
/* 1820 */     int i = Integer.decode("0x" + text).intValue();
/* 1822 */     return Color.decode("" + i);
/*      */   }
/*      */   
/*      */   public RasterSymbolizer createRasterSymbolizer() {
/* 1829 */     return this.sf.getDefaultRasterSymbolizer();
/*      */   }
/*      */   
/*      */   public RasterSymbolizer createRasterSymbolizer(ColorMap colorMap, double opacity) {
/* 1839 */     RasterSymbolizer rs = this.sf.getDefaultRasterSymbolizer();
/* 1840 */     rs.setColorMap(colorMap);
/* 1841 */     rs.setOpacity(literalExpression(opacity));
/* 1843 */     return rs;
/*      */   }
/*      */   
/*      */   public ColorMap createColorMap(String[] labels, double[] quantities, Color[] colors, int type) {
/* 1854 */     ColorMap colorMap = this.sf.createColorMap();
/* 1855 */     colorMap.setType(type);
/* 1857 */     if (labels == null || quantities == null || colors == null || labels.length != quantities.length || quantities.length != colors.length)
/* 1858 */       throw new IllegalArgumentException("Labels, quantities and colors arrays should be not null and have the same size"); 
/* 1861 */     for (int i = 0; i < colors.length; i++)
/* 1862 */       colorMap.addColorMapEntry(createColorMapEntry(labels[i], quantities[i], colors[i])); 
/* 1865 */     return colorMap;
/*      */   }
/*      */   
/*      */   private ColorMapEntry createColorMapEntry(String label, double quantity, Color color) {
/* 1875 */     ColorMapEntry entry = this.sf.createColorMapEntry();
/* 1876 */     entry.setQuantity(literalExpression(quantity));
/* 1877 */     entry.setColor(colorExpression(color));
/* 1878 */     entry.setOpacity(literalExpression(color.getAlpha() / 255.0D));
/* 1879 */     entry.setLabel(label);
/* 1880 */     return entry;
/*      */   }
/*      */   
/*      */   public class EqualClasses {
/*      */     int numberClasses;
/*      */     
/*      */     double[] breaks;
/*      */     
/*      */     double[] collection;
/*      */     
/*      */     public EqualClasses(int numberClasses, double[] fc) {
/* 1895 */       this.breaks = new double[numberClasses - 1];
/* 1896 */       setCollection(fc);
/* 1897 */       setNumberClasses(numberClasses);
/*      */     }
/*      */     
/*      */     public int getNumberClasses() {
/* 1906 */       return this.numberClasses;
/*      */     }
/*      */     
/*      */     public void setNumberClasses(int numberClasses) {
/* 1914 */       this.numberClasses = numberClasses;
/* 1915 */       if (this.breaks == null)
/* 1916 */         this.breaks = new double[numberClasses - 1]; 
/* 1919 */       Arrays.sort(this.collection);
/* 1921 */       int step = this.collection.length / numberClasses;
/* 1922 */       for (int i = step, j = 0; j < this.breaks.length; j++, i += step)
/* 1923 */         this.breaks[j] = this.collection[i]; 
/*      */     }
/*      */     
/*      */     public double[] getBreaks() {
/* 1935 */       return this.breaks;
/*      */     }
/*      */     
/*      */     public void setCollection(double[] collection) {
/* 1945 */       this.collection = collection;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\StyleBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */