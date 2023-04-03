/*      */ package org.geotools.styling;
/*      */ 
/*      */ import com.vividsolutions.jts.geom.LineString;
/*      */ import com.vividsolutions.jts.geom.MultiLineString;
/*      */ import com.vividsolutions.jts.geom.MultiPoint;
/*      */ import com.vividsolutions.jts.geom.MultiPolygon;
/*      */ import com.vividsolutions.jts.geom.Point;
/*      */ import com.vividsolutions.jts.geom.Polygon;
/*      */ import java.awt.Color;
/*      */ import java.io.IOException;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import org.geotools.data.AbstractDataStore;
/*      */ import org.geotools.factory.CommonFactoryFinder;
/*      */ import org.geotools.filter.Filters;
/*      */ import org.geotools.styling.visitor.DuplicatingStyleVisitor;
/*      */ import org.opengis.feature.simple.SimpleFeatureType;
/*      */ import org.opengis.feature.type.FeatureType;
/*      */ import org.opengis.feature.type.GeometryDescriptor;
/*      */ import org.opengis.filter.Filter;
/*      */ import org.opengis.filter.FilterFactory;
/*      */ import org.opengis.filter.expression.Expression;
/*      */ import org.opengis.filter.expression.Literal;
/*      */ import org.opengis.style.GraphicalSymbol;
/*      */ import org.opengis.style.SemanticType;
/*      */ 
/*      */ public class SLD {
/*   62 */   private static StyleFactory sf = CommonFactoryFinder.getStyleFactory(null);
/*      */   
/*   63 */   private static FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/*      */   
/*      */   public static final int NOTFOUND = -1;
/*      */   
/*      */   public static final double ALIGN_LEFT = 1.0D;
/*      */   
/*      */   public static final double ALIGN_CENTER = 0.5D;
/*      */   
/*      */   public static final double ALIGN_RIGHT = 0.0D;
/*      */   
/*      */   public static final double ALIGN_BOTTOM = 1.0D;
/*      */   
/*      */   public static final double ALIGN_MIDDLE = 0.5D;
/*      */   
/*      */   public static final double ALIGN_TOP = 0.0D;
/*      */   
/*      */   public static Color lineColor(LineSymbolizer symbolizer) {
/*   82 */     if (symbolizer == null)
/*   83 */       return null; 
/*   86 */     Stroke stroke = symbolizer.getStroke();
/*   88 */     return color(stroke);
/*      */   }
/*      */   
/*      */   public static Color color(Stroke stroke) {
/*   97 */     if (stroke == null)
/*   98 */       return null; 
/*  100 */     return color(stroke.getColor());
/*      */   }
/*      */   
/*      */   public static Color color(Fill fill) {
/*  109 */     if (fill == null)
/*  110 */       return null; 
/*  113 */     return color(fill.getColor());
/*      */   }
/*      */   
/*      */   public static void setLineColour(Style style, final Color colour) {
/*  126 */     if (style == null)
/*      */       return; 
/*  129 */     for (FeatureTypeStyle featureTypeStyle : style.featureTypeStyles()) {
/*  130 */       for (int i = 0; i < featureTypeStyle.rules().size(); i++) {
/*  131 */         Rule rule = featureTypeStyle.rules().get(i);
/*  132 */         DuplicatingStyleVisitor update = new DuplicatingStyleVisitor() {
/*      */             public void visit(LineSymbolizer line) {
/*  136 */               String name = line.getGeometryPropertyName();
/*  137 */               Stroke stroke = update(line.getStroke());
/*  138 */               LineSymbolizer copy = this.sf.createLineSymbolizer(stroke, name);
/*  139 */               this.pages.push(copy);
/*      */             }
/*      */             
/*      */             Stroke update(Stroke stroke) {
/*  143 */               Literal literal = this.ff.literal(colour);
/*  144 */               Expression width = copy(stroke.getWidth());
/*  145 */               Expression opacity = copy(stroke.getOpacity());
/*  146 */               Expression lineJoin = copy(stroke.getLineJoin());
/*  147 */               Expression lineCap = copy(stroke.getLineCap());
/*  148 */               float[] dashArray = copy(stroke.getDashArray());
/*  149 */               Expression dashOffset = copy(stroke.getDashOffset());
/*  150 */               Graphic graphicStroke = copy(stroke.getGraphicStroke());
/*  151 */               Graphic graphicFill = copy(stroke.getGraphicFill());
/*  152 */               return this.sf.createStroke((Expression)literal, width, opacity, lineJoin, lineCap, dashArray, dashOffset, graphicFill, graphicStroke);
/*      */             }
/*      */           };
/*  155 */         rule.accept((StyleVisitor)update);
/*  156 */         Rule updatedRule = (Rule)update.getCopy();
/*  157 */         featureTypeStyle.rules().set(i, updatedRule);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void setLineColour(LineSymbolizer symbolizer, Color colour) {
/*  169 */     if (symbolizer == null || colour == null)
/*      */       return; 
/*  173 */     Stroke stroke = symbolizer.getStroke();
/*  175 */     if (stroke == null) {
/*  176 */       stroke = sf.createStroke((Expression)ff.literal(colour), Stroke.DEFAULT.getWidth());
/*  177 */       symbolizer.setStroke(stroke);
/*      */     } else {
/*  180 */       stroke.setColor((Expression)ff.literal(colour));
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Color color(LineSymbolizer symbolizer) {
/*  192 */     return lineColor(symbolizer);
/*      */   }
/*      */   
/*      */   public static int lineWidth(LineSymbolizer symbolizer) {
/*  203 */     if (symbolizer == null)
/*  204 */       return -1; 
/*  207 */     Stroke stroke = symbolizer.getStroke();
/*  209 */     return width(stroke);
/*      */   }
/*      */   
/*      */   public static int width(Stroke stroke) {
/*  220 */     if (stroke == null)
/*  221 */       return -1; 
/*  224 */     return Filters.asInt(stroke.getWidth());
/*      */   }
/*      */   
/*      */   public static int size(Mark mark) {
/*  234 */     return -1;
/*      */   }
/*      */   
/*      */   public static int width(LineSymbolizer symbolizer) {
/*  245 */     return lineWidth(symbolizer);
/*      */   }
/*      */   
/*      */   public static double lineOpacity(LineSymbolizer symbolizer) {
/*  256 */     if (symbolizer == null)
/*  257 */       return Double.NaN; 
/*  260 */     Stroke stroke = symbolizer.getStroke();
/*  262 */     return opacity(stroke);
/*      */   }
/*      */   
/*      */   public static double opacity(Stroke stroke) {
/*  273 */     if (stroke == null)
/*  274 */       return Double.NaN; 
/*  276 */     return opacity(stroke.getOpacity());
/*      */   }
/*      */   
/*      */   public static double opacity(RasterSymbolizer rasterSymbolizer) {
/*  287 */     if (rasterSymbolizer == null)
/*  288 */       return 1.0D; 
/*  290 */     return opacity(rasterSymbolizer.getOpacity());
/*      */   }
/*      */   
/*      */   private static double opacity(Expression opacity) {
/*  301 */     if (opacity == null)
/*  302 */       return 1.0D; 
/*  304 */     double value = Filters.asDouble(opacity);
/*  305 */     return Double.isNaN(value) ? 1.0D : value;
/*      */   }
/*      */   
/*      */   public static String lineLinejoin(LineSymbolizer symbolizer) {
/*  316 */     if (symbolizer == null)
/*  317 */       return null; 
/*  320 */     Stroke stroke = symbolizer.getStroke();
/*  322 */     if (stroke == null)
/*  323 */       return null; 
/*  326 */     Expression linejoinExp = stroke.getLineJoin();
/*  328 */     return linejoinExp.toString();
/*      */   }
/*      */   
/*      */   public static String lineLinecap(LineSymbolizer symbolizer) {
/*  339 */     if (symbolizer == null)
/*  340 */       return null; 
/*  343 */     Stroke stroke = symbolizer.getStroke();
/*  345 */     if (stroke == null)
/*  346 */       return null; 
/*  349 */     Expression linecapExp = stroke.getLineCap();
/*  351 */     return linecapExp.toString();
/*      */   }
/*      */   
/*      */   public static float[] lineDash(LineSymbolizer symbolizer) {
/*  362 */     if (symbolizer == null)
/*  363 */       return null; 
/*  366 */     Stroke stroke = symbolizer.getStroke();
/*  368 */     if (stroke == null)
/*  369 */       return null; 
/*  372 */     float[] linedash = stroke.getDashArray();
/*  374 */     return linedash;
/*      */   }
/*      */   
/*      */   public static URL pointGraphic(Style style) {
/*  386 */     PointSymbolizer point = pointSymbolizer(style);
/*  388 */     if (point == null)
/*  389 */       return null; 
/*  392 */     Graphic graphic = point.getGraphic();
/*  394 */     if (graphic == null)
/*  395 */       return null; 
/*  398 */     for (GraphicalSymbol gs : graphic.graphicalSymbols()) {
/*  399 */       if (gs != null && gs instanceof ExternalGraphic) {
/*  400 */         ExternalGraphic externalGraphic = (ExternalGraphic)gs;
/*      */         try {
/*  402 */           URL location = externalGraphic.getLocation();
/*  403 */           if (location != null)
/*  404 */             return location; 
/*  406 */         } catch (MalformedURLException e) {}
/*      */       } 
/*      */     } 
/*  412 */     return null;
/*      */   }
/*      */   
/*      */   public static Mark pointMark(Style style) {
/*  423 */     if (style == null)
/*  424 */       return null; 
/*  427 */     return mark(pointSymbolizer(style));
/*      */   }
/*      */   
/*      */   public static Mark mark(PointSymbolizer sym) {
/*  438 */     return mark(graphic(sym));
/*      */   }
/*      */   
/*      */   public static Mark mark(Graphic graphic) {
/*  449 */     if (graphic == null)
/*  450 */       return null; 
/*  453 */     for (GraphicalSymbol gs : graphic.graphicalSymbols()) {
/*  454 */       if (gs != null && gs instanceof Mark)
/*  455 */         return (Mark)gs; 
/*      */     } 
/*  459 */     return null;
/*      */   }
/*      */   
/*      */   public static Graphic graphic(PointSymbolizer sym) {
/*  463 */     if (sym == null)
/*  464 */       return null; 
/*  467 */     return sym.getGraphic();
/*      */   }
/*      */   
/*      */   public static int pointSize(PointSymbolizer symbolizer) {
/*  481 */     if (symbolizer == null)
/*  482 */       return -1; 
/*  485 */     Graphic g = symbolizer.getGraphic();
/*  487 */     if (g == null)
/*  488 */       return -1; 
/*  491 */     Expression exp = g.getSize();
/*  492 */     return Filters.asInt(exp);
/*      */   }
/*      */   
/*      */   public static String pointWellKnownName(PointSymbolizer symbolizer) {
/*  507 */     if (symbolizer == null)
/*  508 */       return null; 
/*  511 */     Graphic graphic = symbolizer.getGraphic();
/*  513 */     if (graphic == null)
/*  514 */       return null; 
/*  517 */     for (GraphicalSymbol gs : graphic.graphicalSymbols()) {
/*  518 */       if (gs != null && gs instanceof Mark) {
/*  519 */         Mark mark = (Mark)gs;
/*  520 */         String s = wellKnownName(mark);
/*  521 */         if (s != null)
/*  522 */           return s; 
/*      */       } 
/*      */     } 
/*  527 */     return null;
/*      */   }
/*      */   
/*      */   public static String wellKnownName(Mark mark) {
/*  538 */     if (mark == null)
/*  539 */       return null; 
/*  542 */     Expression exp = mark.getWellKnownName();
/*  544 */     if (exp == null)
/*  545 */       return null; 
/*  548 */     return Filters.asString(exp);
/*      */   }
/*      */   
/*      */   public static Color pointColor(PointSymbolizer symbolizer) {
/*  563 */     return color(symbolizer);
/*      */   }
/*      */   
/*      */   public static void setPointColour(Style style, Color colour) {
/*  573 */     if (style == null)
/*      */       return; 
/*  577 */     setPointColour(pointSymbolizer(style), colour);
/*      */   }
/*      */   
/*      */   public static void setPointColour(PointSymbolizer symbolizer, Color colour) {
/*  588 */     if (symbolizer == null || colour == null)
/*      */       return; 
/*  592 */     Graphic graphic = symbolizer.getGraphic();
/*  594 */     if (graphic == null)
/*  595 */       graphic = sf.createDefaultGraphic(); 
/*  598 */     for (GraphicalSymbol gs : graphic.graphicalSymbols()) {
/*  599 */       if (gs != null && gs instanceof Mark) {
/*  600 */         Mark mark = (Mark)gs;
/*  602 */         Stroke stroke = mark.getStroke();
/*  603 */         if (stroke == null) {
/*  604 */           stroke = sf.createStroke((Expression)ff.literal(Color.BLACK), Stroke.DEFAULT.getWidth());
/*  605 */           mark.setStroke(stroke);
/*      */         } 
/*  608 */         Fill fill = mark.getFill();
/*  609 */         if (fill != null)
/*  610 */           fill.setColor((Expression)ff.literal(colour)); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Color color(PointSymbolizer symbolizer) {
/*  627 */     if (symbolizer == null)
/*  628 */       return null; 
/*  631 */     Graphic graphic = symbolizer.getGraphic();
/*  633 */     if (graphic == null)
/*  634 */       return null; 
/*  637 */     for (GraphicalSymbol gs : graphic.graphicalSymbols()) {
/*  638 */       if (gs != null && gs instanceof Mark) {
/*  639 */         Mark mark = (Mark)gs;
/*  640 */         Stroke stroke = mark.getStroke();
/*  641 */         if (stroke != null) {
/*  642 */           Color colour = color(stroke);
/*  643 */           if (colour != null)
/*  644 */             return colour; 
/*      */         } 
/*      */       } 
/*      */     } 
/*  650 */     return null;
/*      */   }
/*      */   
/*      */   public static int pointWidth(PointSymbolizer symbolizer) {
/*  665 */     if (symbolizer == null)
/*  666 */       return -1; 
/*  669 */     Graphic graphic = symbolizer.getGraphic();
/*  671 */     if (graphic == null)
/*  672 */       return -1; 
/*  675 */     for (GraphicalSymbol gs : graphic.graphicalSymbols()) {
/*  676 */       if (gs != null && gs instanceof Mark) {
/*  677 */         Mark mark = (Mark)gs;
/*  678 */         Stroke stroke = mark.getStroke();
/*  679 */         if (stroke != null) {
/*  680 */           Expression exp = stroke.getWidth();
/*  681 */           if (exp != null) {
/*  682 */             int width = Filters.asInt(exp);
/*  683 */             if (width != -1)
/*  684 */               return width; 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  691 */     return -1;
/*      */   }
/*      */   
/*      */   public static double pointBorderOpacity(PointSymbolizer symbolizer) {
/*  705 */     if (symbolizer == null)
/*  706 */       return Double.NaN; 
/*  709 */     Graphic graphic = symbolizer.getGraphic();
/*  711 */     if (graphic == null)
/*  712 */       return Double.NaN; 
/*  715 */     for (GraphicalSymbol gs : graphic.graphicalSymbols()) {
/*  716 */       if (gs != null && gs instanceof Mark) {
/*  717 */         Mark mark = (Mark)gs;
/*  718 */         Stroke stroke = mark.getStroke();
/*  719 */         if (stroke != null) {
/*  720 */           Expression exp = stroke.getOpacity();
/*  721 */           return Filters.asDouble(exp);
/*      */         } 
/*      */       } 
/*      */     } 
/*  726 */     return Double.NaN;
/*      */   }
/*      */   
/*      */   public static double pointOpacity(PointSymbolizer symbolizer) {
/*  740 */     if (symbolizer == null)
/*  741 */       return Double.NaN; 
/*  744 */     Graphic graphic = symbolizer.getGraphic();
/*  746 */     if (graphic == null)
/*  747 */       return Double.NaN; 
/*  750 */     for (GraphicalSymbol gs : graphic.graphicalSymbols()) {
/*  751 */       if (gs != null && gs instanceof Mark) {
/*  752 */         Mark mark = (Mark)gs;
/*  753 */         Fill fill = mark.getFill();
/*  754 */         if (fill != null) {
/*  755 */           Expression expr = fill.getOpacity();
/*  756 */           if (expr != null)
/*  757 */             return opacity(expr); 
/*      */         } 
/*      */       } 
/*      */     } 
/*  763 */     return Double.NaN;
/*      */   }
/*      */   
/*      */   public static Color pointFill(PointSymbolizer symbolizer) {
/*  777 */     if (symbolizer == null)
/*  778 */       return null; 
/*  781 */     Graphic graphic = symbolizer.getGraphic();
/*  783 */     if (graphic == null)
/*  784 */       return null; 
/*  787 */     for (GraphicalSymbol gs : graphic.graphicalSymbols()) {
/*  788 */       if (gs != null && gs instanceof Mark) {
/*  789 */         Mark mark = (Mark)gs;
/*  790 */         Fill fill = mark.getFill();
/*  791 */         if (fill != null) {
/*  792 */           Color colour = color(fill.getColor());
/*  793 */           if (colour != null)
/*  794 */             return colour; 
/*      */         } 
/*      */       } 
/*      */     } 
/*  800 */     return null;
/*      */   }
/*      */   
/*      */   public static int polyWidth(PolygonSymbolizer symbolizer) {
/*  814 */     if (symbolizer == null)
/*  815 */       return -1; 
/*  818 */     Stroke stroke = symbolizer.getStroke();
/*  819 */     if (stroke != null)
/*  820 */       return Filters.asInt(stroke.getWidth()); 
/*  823 */     return -1;
/*      */   }
/*      */   
/*      */   public static Color polyColor(PolygonSymbolizer symbolizer) {
/*  837 */     if (symbolizer == null)
/*  838 */       return null; 
/*  841 */     Stroke stroke = symbolizer.getStroke();
/*  843 */     if (stroke == null)
/*  844 */       return null; 
/*  847 */     Color colour = color(stroke.getColor());
/*  849 */     if (colour != null)
/*  850 */       return colour; 
/*  853 */     return null;
/*      */   }
/*      */   
/*      */   public static void setRasterOpacity(Style style, final double opacity) {
/*  867 */     if (style == null)
/*      */       return; 
/*  870 */     for (FeatureTypeStyle featureTypeStyle : style.featureTypeStyles()) {
/*  871 */       for (int i = 0; i < featureTypeStyle.rules().size(); i++) {
/*  872 */         Rule rule = featureTypeStyle.rules().get(i);
/*  874 */         DuplicatingStyleVisitor update = new DuplicatingStyleVisitor() {
/*      */             public void visit(RasterSymbolizer raster) {
/*  878 */               ChannelSelection channelSelection = copy(raster.getChannelSelection());
/*  879 */               ColorMap colorMap = copy(raster.getColorMap());
/*  880 */               ContrastEnhancement ce = copy(raster.getContrastEnhancement());
/*  881 */               String geometryProperty = raster.getGeometryPropertyName();
/*  882 */               Symbolizer outline = copy(raster.getImageOutline());
/*  883 */               Expression overlap = copy(raster.getOverlap());
/*  884 */               ShadedRelief shadedRelief = copy(raster.getShadedRelief());
/*  886 */               Literal literal = this.ff.literal(opacity);
/*  888 */               RasterSymbolizer copy = this.sf.createRasterSymbolizer(geometryProperty, (Expression)literal, channelSelection, overlap, colorMap, ce, shadedRelief, outline);
/*  891 */               if (this.STRICT && !copy.equals(raster))
/*  892 */                 throw new IllegalStateException("Was unable to duplicate provided raster:" + raster); 
/*  894 */               this.pages.push(copy);
/*      */             }
/*      */           };
/*  898 */         rule.accept((StyleVisitor)update);
/*  899 */         Rule updatedRule = (Rule)update.getCopy();
/*  900 */         featureTypeStyle.rules().set(i, updatedRule);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void setChannelSelection(Style style, final SelectedChannelType[] rgb, final SelectedChannelType gray) {
/*  917 */     if (style == null)
/*      */       return; 
/*  920 */     for (FeatureTypeStyle featureTypeStyle : style.featureTypeStyles()) {
/*  921 */       for (int i = 0; i < featureTypeStyle.rules().size(); i++) {
/*  922 */         Rule rule = featureTypeStyle.rules().get(i);
/*  924 */         DuplicatingStyleVisitor update = new DuplicatingStyleVisitor() {
/*      */             public void visit(RasterSymbolizer raster) {
/*  929 */               ChannelSelection channelSelection = createChannelSelection();
/*  931 */               ColorMap colorMap = copy(raster.getColorMap());
/*  932 */               ContrastEnhancement ce = copy(raster.getContrastEnhancement());
/*  933 */               String geometryProperty = raster.getGeometryPropertyName();
/*  934 */               Symbolizer outline = copy(raster.getImageOutline());
/*  935 */               Expression overlap = copy(raster.getOverlap());
/*  936 */               ShadedRelief shadedRelief = copy(raster.getShadedRelief());
/*  938 */               Expression opacity = copy(raster.getOpacity());
/*  940 */               RasterSymbolizer copy = this.sf.createRasterSymbolizer(geometryProperty, opacity, channelSelection, overlap, colorMap, ce, shadedRelief, outline);
/*  943 */               if (this.STRICT && !copy.equals(raster))
/*  944 */                 throw new IllegalStateException("Was unable to duplicate provided raster:" + raster); 
/*  946 */               this.pages.push(copy);
/*      */             }
/*      */             
/*      */             private ChannelSelection createChannelSelection() {
/*  950 */               if (rgb == null)
/*  951 */                 return this.sf.createChannelSelection(new SelectedChannelType[] { this.val$gray }); 
/*  953 */               return this.sf.createChannelSelection(rgb);
/*      */             }
/*      */           };
/*  958 */         rule.accept((StyleVisitor)update);
/*  959 */         Rule updatedRule = (Rule)update.getCopy();
/*  960 */         featureTypeStyle.rules().set(i, updatedRule);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void setPolyColour(Style style, Color colour) {
/*  973 */     if (style == null)
/*      */       return; 
/*  976 */     setPolyColour(polySymbolizer(style), colour);
/*      */   }
/*      */   
/*      */   public static void setPolyColour(PolygonSymbolizer symbolizer, Color colour) {
/*  986 */     if (symbolizer == null || colour == null)
/*      */       return; 
/*  990 */     Literal literal = ff.literal(colour);
/*  991 */     Stroke stroke = symbolizer.getStroke();
/*  992 */     if (stroke == null) {
/*  993 */       stroke = sf.createStroke((Expression)literal, Stroke.DEFAULT.getWidth());
/*  994 */       symbolizer.setStroke(stroke);
/*      */     } else {
/*  996 */       stroke.setColor((Expression)ff.literal(colour));
/*      */     } 
/*  999 */     Fill fill = symbolizer.getFill();
/* 1000 */     if (fill != null)
/* 1001 */       fill.setColor((Expression)literal); 
/*      */   }
/*      */   
/*      */   public static Color polyFill(PolygonSymbolizer symbolizer) {
/* 1016 */     if (symbolizer == null)
/* 1017 */       return null; 
/* 1020 */     Fill fill = symbolizer.getFill();
/* 1022 */     if (fill == null)
/* 1023 */       return null; 
/* 1026 */     return color(fill.getColor());
/*      */   }
/*      */   
/*      */   public static double polyBorderOpacity(PolygonSymbolizer symbolizer) {
/* 1040 */     if (symbolizer == null)
/* 1041 */       return Double.NaN; 
/* 1044 */     Stroke stroke = symbolizer.getStroke();
/* 1045 */     if (stroke == null)
/* 1046 */       return Double.NaN; 
/* 1049 */     return opacity(stroke);
/*      */   }
/*      */   
/*      */   public static double polyFillOpacity(PolygonSymbolizer symbolizer) {
/* 1063 */     if (symbolizer == null)
/* 1064 */       return Double.NaN; 
/* 1067 */     Fill fill = symbolizer.getFill();
/* 1069 */     return opacity(fill);
/*      */   }
/*      */   
/*      */   public static double opacity(Fill fill) {
/* 1078 */     if (fill == null)
/* 1079 */       fill = Fill.DEFAULT; 
/* 1082 */     Expression opacityExp = fill.getOpacity();
/* 1083 */     if (opacityExp == null)
/* 1084 */       opacityExp = Fill.DEFAULT.getOpacity(); 
/* 1087 */     return Filters.asDouble(opacityExp);
/*      */   }
/*      */   
/*      */   public static double rasterOpacity(RasterSymbolizer symbolizer) {
/* 1101 */     if (symbolizer == null)
/* 1102 */       return Double.NaN; 
/* 1105 */     return Filters.asDouble(symbolizer.getOpacity());
/*      */   }
/*      */   
/*      */   public static double rasterOpacity(Style style) {
/* 1117 */     return rasterOpacity(rasterSymbolizer(style));
/*      */   }
/*      */   
/*      */   public static TextSymbolizer textSymbolizer(FeatureTypeStyle fts) {
/* 1128 */     return (TextSymbolizer)symbolizer(fts, TextSymbolizer.class);
/*      */   }
/*      */   
/*      */   public static TextSymbolizer textSymbolizer(Style style) {
/* 1139 */     return (TextSymbolizer)symbolizer(style, TextSymbolizer.class);
/*      */   }
/*      */   
/*      */   public static Font font(TextSymbolizer symbolizer) {
/* 1151 */     if (symbolizer == null)
/* 1152 */       return null; 
/* 1154 */     return symbolizer.getFont();
/*      */   }
/*      */   
/*      */   public static Expression textLabel(TextSymbolizer symbolizer) {
/* 1165 */     if (symbolizer == null)
/* 1166 */       return null; 
/* 1169 */     Expression exp = symbolizer.getLabel();
/* 1171 */     if (exp == null)
/* 1172 */       return null; 
/* 1175 */     return exp;
/*      */   }
/*      */   
/*      */   public static String textLabelString(TextSymbolizer sym) {
/* 1186 */     Expression exp = textLabel(sym);
/* 1188 */     return (exp == null) ? null : exp.toString();
/*      */   }
/*      */   
/*      */   public static Color textFontFill(TextSymbolizer symbolizer) {
/* 1199 */     if (symbolizer == null)
/* 1200 */       return null; 
/* 1203 */     Fill fill = symbolizer.getFill();
/* 1205 */     if (fill == null)
/* 1206 */       return null; 
/* 1209 */     return color(fill.getColor());
/*      */   }
/*      */   
/*      */   public static Color textHaloFill(TextSymbolizer symbolizer) {
/* 1220 */     Halo halo = symbolizer.getHalo();
/* 1222 */     if (halo == null)
/* 1223 */       return null; 
/* 1226 */     Fill fill = halo.getFill();
/* 1228 */     if (fill == null)
/* 1229 */       return null; 
/* 1232 */     return color(fill.getColor());
/*      */   }
/*      */   
/*      */   public static int textHaloWidth(TextSymbolizer symbolizer) {
/* 1243 */     Halo halo = symbolizer.getHalo();
/* 1245 */     if (halo == null)
/* 1246 */       return 0; 
/* 1249 */     Expression exp = halo.getRadius();
/* 1251 */     if (exp == null)
/* 1252 */       return 0; 
/* 1255 */     int width = (int)Float.parseFloat(exp.toString());
/* 1257 */     if (width != 0)
/* 1258 */       return width; 
/* 1261 */     return 0;
/*      */   }
/*      */   
/*      */   public static double textHaloOpacity(TextSymbolizer symbolizer) {
/* 1272 */     if (symbolizer == null)
/* 1273 */       return Double.NaN; 
/* 1276 */     Halo halo = symbolizer.getHalo();
/* 1278 */     if (halo == null)
/* 1279 */       return Double.NaN; 
/* 1282 */     Fill fill = halo.getFill();
/* 1284 */     if (fill == null)
/* 1285 */       return Double.NaN; 
/* 1288 */     Expression expr = fill.getOpacity();
/* 1289 */     if (expr == null)
/* 1290 */       return Double.NaN; 
/* 1293 */     return Filters.asDouble(expr);
/*      */   }
/*      */   
/*      */   public static Color color(Expression expr) {
/* 1310 */     if (expr == null)
/* 1311 */       return null; 
/* 1313 */     return (Color)expr.evaluate(null, Color.class);
/*      */   }
/*      */   
/*      */   public static RasterSymbolizer rasterSymbolizer(FeatureTypeStyle fts) {
/* 1324 */     return (RasterSymbolizer)symbolizer(fts, RasterSymbolizer.class);
/*      */   }
/*      */   
/*      */   public static RasterSymbolizer rasterSymbolizer(Style style) {
/* 1335 */     return (RasterSymbolizer)symbolizer(style, RasterSymbolizer.class);
/*      */   }
/*      */   
/*      */   public static LineSymbolizer lineSymbolizer(FeatureTypeStyle fts) {
/* 1346 */     return (LineSymbolizer)symbolizer(fts, LineSymbolizer.class);
/*      */   }
/*      */   
/*      */   public static LineSymbolizer lineSymbolizer(Style style) {
/* 1357 */     return (LineSymbolizer)symbolizer(style, LineSymbolizer.class);
/*      */   }
/*      */   
/*      */   public static Stroke stroke(LineSymbolizer sym) {
/* 1366 */     if (sym == null)
/* 1367 */       return null; 
/* 1370 */     return sym.getStroke();
/*      */   }
/*      */   
/*      */   public static Stroke stroke(PolygonSymbolizer sym) {
/* 1379 */     if (sym == null)
/* 1380 */       return null; 
/* 1383 */     return sym.getStroke();
/*      */   }
/*      */   
/*      */   public static Stroke stroke(PointSymbolizer sym) {
/* 1392 */     Mark mark = mark(sym);
/* 1394 */     return (mark == null) ? null : mark.getStroke();
/*      */   }
/*      */   
/*      */   public static Fill fill(PolygonSymbolizer sym) {
/* 1403 */     if (sym == null)
/* 1404 */       return null; 
/* 1407 */     return sym.getFill();
/*      */   }
/*      */   
/*      */   public static Fill fill(PointSymbolizer sym) {
/* 1416 */     Mark mark = mark(sym);
/* 1418 */     return (mark == null) ? null : mark.getFill();
/*      */   }
/*      */   
/*      */   public static PointSymbolizer pointSymbolizer(FeatureTypeStyle fts) {
/* 1429 */     return (PointSymbolizer)symbolizer(fts, PointSymbolizer.class);
/*      */   }
/*      */   
/*      */   public static PointSymbolizer pointSymbolizer(Style style) {
/* 1440 */     return (PointSymbolizer)symbolizer(style, PointSymbolizer.class);
/*      */   }
/*      */   
/*      */   public static PolygonSymbolizer polySymbolizer(FeatureTypeStyle fts) {
/* 1451 */     return (PolygonSymbolizer)symbolizer(fts, PolygonSymbolizer.class);
/*      */   }
/*      */   
/*      */   public static PolygonSymbolizer polySymbolizer(Style style) {
/* 1462 */     return (PolygonSymbolizer)symbolizer(style, PolygonSymbolizer.class);
/*      */   }
/*      */   
/*      */   public static FeatureTypeStyle featureTypeStyle(Style style, SimpleFeatureType type) {
/* 1475 */     if (style == null)
/* 1476 */       return null; 
/* 1479 */     if (type == null || type.getTypeName() == null)
/* 1480 */       return null; 
/* 1483 */     for (FeatureTypeStyle fts : style.featureTypeStyles()) {
/* 1484 */       if (type.getTypeName().equalsIgnoreCase(fts.getName()))
/* 1485 */         return fts; 
/*      */     } 
/* 1489 */     return null;
/*      */   }
/*      */   
/*      */   public static Style matchingStyle(Style[] styles, SimpleFeatureType schema) {
/* 1502 */     if (styles == null || styles.length == 0)
/* 1503 */       return null; 
/* 1506 */     for (int i = 0; i < styles.length; i++) {
/* 1507 */       Style style = styles[i];
/* 1509 */       if (featureTypeStyle(style, schema) != null)
/* 1510 */         return style; 
/*      */     } 
/* 1514 */     return null;
/*      */   }
/*      */   
/*      */   protected static Symbolizer symbolizer(Style style, Class SYMBOLIZER) {
/* 1527 */     if (style == null)
/* 1528 */       return null; 
/* 1531 */     for (FeatureTypeStyle fts : style.featureTypeStyles()) {
/* 1532 */       Symbolizer sym = symbolizer(fts, SYMBOLIZER);
/* 1533 */       if (sym != null)
/* 1534 */         return sym; 
/*      */     } 
/* 1538 */     return null;
/*      */   }
/*      */   
/*      */   protected static Symbolizer symbolizer(FeatureTypeStyle fts, Class SYMBOLIZER) {
/* 1552 */     if (fts == null)
/* 1553 */       return null; 
/* 1556 */     for (Rule rule : fts.rules()) {
/* 1557 */       for (Symbolizer sym : rule.symbolizers()) {
/* 1558 */         if (SYMBOLIZER.isInstance(sym))
/* 1559 */           return sym; 
/*      */       } 
/*      */     } 
/* 1564 */     return null;
/*      */   }
/*      */   
/*      */   public static String colorToHex(Color c) {
/* 1573 */     return "#" + Integer.toHexString(c.getRGB() & 0xFFFFFF);
/*      */   }
/*      */   
/*      */   public static Style[] styles(StyledLayerDescriptor sld) {
/* 1582 */     StyledLayer[] layers = sld.getStyledLayers();
/* 1583 */     List<Style> styles = new ArrayList<Style>();
/* 1585 */     for (int i = 0; i < layers.length; i++) {
/* 1586 */       if (layers[i] instanceof UserLayer) {
/* 1587 */         UserLayer layer = (UserLayer)layers[i];
/* 1588 */         styles.addAll(layer.userStyles());
/* 1590 */       } else if (layers[i] instanceof NamedLayer) {
/* 1591 */         NamedLayer layer = (NamedLayer)layers[i];
/* 1592 */         styles.addAll(layer.styles());
/*      */       } 
/*      */     } 
/* 1596 */     return styles.<Style>toArray(new Style[0]);
/*      */   }
/*      */   
/*      */   public static FeatureTypeStyle[] featureTypeStyles(StyledLayerDescriptor sld) {
/* 1605 */     Style[] style = styles(sld);
/* 1606 */     List<FeatureTypeStyle> fts = new ArrayList<FeatureTypeStyle>();
/* 1607 */     for (int i = 0; i < style.length; i++)
/* 1608 */       fts.addAll(style[i].featureTypeStyles()); 
/* 1610 */     return fts.<FeatureTypeStyle>toArray(new FeatureTypeStyle[0]);
/*      */   }
/*      */   
/*      */   public static FeatureTypeStyle featureTypeStyle(StyledLayerDescriptor sld, SimpleFeatureType type) {
/* 1623 */     Style[] styles = styles(sld);
/* 1624 */     for (int i = 0; i < styles.length; i++) {
/* 1625 */       for (FeatureTypeStyle fts : styles[i].featureTypeStyles()) {
/* 1626 */         if (type.getTypeName().equalsIgnoreCase(fts.getName()))
/* 1627 */           return fts; 
/*      */       } 
/*      */     } 
/* 1631 */     return null;
/*      */   }
/*      */   
/*      */   public static Style defaultStyle(StyledLayerDescriptor sld) {
/* 1643 */     Style[] style = styles(sld);
/* 1644 */     for (int i = 0; i < style.length; i++) {
/* 1645 */       if (style[i].isDefault())
/* 1646 */         return style[i]; 
/*      */     } 
/* 1650 */     if (style.length == 0)
/* 1651 */       return null; 
/* 1653 */     return style[0];
/*      */   }
/*      */   
/*      */   public static Filter[] filters(Rule[] rule) {
/* 1664 */     Filter[] filter = new Filter[rule.length];
/* 1665 */     for (int i = 0; i < rule.length; i++)
/* 1666 */       filter[i] = rule[0].getFilter(); 
/* 1668 */     return filter;
/*      */   }
/*      */   
/*      */   public static Filter[] filters(Style style) {
/* 1679 */     Rule[] rule = rules(style);
/* 1680 */     return filters(rule);
/*      */   }
/*      */   
/*      */   public static Rule[] rules(Style style) {
/* 1691 */     Set<Rule> ruleSet = new HashSet<Rule>();
/* 1692 */     for (FeatureTypeStyle fts : style.featureTypeStyles())
/* 1693 */       ruleSet.addAll(fts.rules()); 
/* 1696 */     if (ruleSet.size() > 0)
/* 1697 */       return ruleSet.<Rule>toArray(new Rule[0]); 
/* 1699 */     return new Rule[0];
/*      */   }
/*      */   
/*      */   public static Symbolizer[] symbolizers(Style style) {
/* 1711 */     Set<Symbolizer> symbolizers = new HashSet<Symbolizer>();
/* 1712 */     for (FeatureTypeStyle fts : style.featureTypeStyles()) {
/* 1713 */       for (Rule rule : fts.rules())
/* 1714 */         symbolizers.addAll(rule.symbolizers()); 
/*      */     } 
/* 1718 */     if (symbolizers.size() > 0)
/* 1719 */       return symbolizers.<Symbolizer>toArray(new Symbolizer[0]); 
/* 1721 */     return new Symbolizer[0];
/*      */   }
/*      */   
/*      */   public static Symbolizer[] symbolizers(Rule rule) {
/* 1733 */     Set<Symbolizer> symbolizers = new HashSet<Symbolizer>();
/* 1734 */     symbolizers.addAll(rule.symbolizers());
/* 1736 */     if (symbolizers.size() > 0)
/* 1737 */       return symbolizers.<Symbolizer>toArray(new Symbolizer[0]); 
/* 1739 */     return new Symbolizer[0];
/*      */   }
/*      */   
/*      */   public static String[] colors(Style style) {
/* 1749 */     Set<String> colorSet = new HashSet<String>();
/* 1751 */     for (FeatureTypeStyle fts : style.featureTypeStyles()) {
/* 1752 */       for (Rule rule : fts.rules()) {
/* 1753 */         String[] color = colors(rule);
/* 1754 */         for (int j = 0; j < color.length; j++)
/* 1755 */           colorSet.add(color[j]); 
/*      */       } 
/*      */     } 
/* 1760 */     if (colorSet.size() > 0)
/* 1761 */       return colorSet.<String>toArray(new String[0]); 
/* 1763 */     return new String[0];
/*      */   }
/*      */   
/*      */   public static String[] colors(Rule rule) {
/* 1773 */     Set<String> colorSet = new HashSet<String>();
/* 1775 */     Color color = null;
/* 1776 */     for (Symbolizer sym : rule.symbolizers()) {
/* 1777 */       if (sym instanceof PolygonSymbolizer) {
/* 1778 */         PolygonSymbolizer symb = (PolygonSymbolizer)sym;
/* 1779 */         color = polyFill(symb);
/* 1781 */       } else if (sym instanceof LineSymbolizer) {
/* 1782 */         LineSymbolizer symb = (LineSymbolizer)sym;
/* 1783 */         color = color(symb);
/* 1785 */       } else if (sym instanceof PointSymbolizer) {
/* 1786 */         PointSymbolizer symb = (PointSymbolizer)sym;
/* 1787 */         color = color(symb);
/*      */       } 
/* 1790 */       if (color != null)
/* 1791 */         colorSet.add(color.toString()); 
/*      */     } 
/* 1795 */     if (colorSet.size() > 0)
/* 1796 */       return colorSet.<String>toArray(new String[0]); 
/* 1798 */     return new String[0];
/*      */   }
/*      */   
/*      */   public static String toHTMLColor(Color color) {
/* 1810 */     if (color == null)
/* 1811 */       return null; 
/* 1814 */     String red = "0" + Integer.toHexString(color.getRed());
/* 1815 */     red = red.substring(red.length() - 2);
/* 1817 */     String grn = "0" + Integer.toHexString(color.getGreen());
/* 1818 */     grn = grn.substring(grn.length() - 2);
/* 1820 */     String blu = "0" + Integer.toHexString(color.getBlue());
/* 1821 */     blu = blu.substring(blu.length() - 2);
/* 1823 */     return ("#" + red + grn + blu).toUpperCase();
/*      */   }
/*      */   
/*      */   public static Color toColor(String htmlColor) {
/* 1834 */     return new Color(Integer.parseInt(htmlColor.substring(1), 16));
/*      */   }
/*      */   
/*      */   public static boolean isSemanticTypeMatch(FeatureTypeStyle fts, String regex) {
/* 1844 */     for (SemanticType id : fts.semanticTypeIdentifiers()) {
/* 1845 */       if (id.matches(regex))
/* 1846 */         return true; 
/*      */     } 
/* 1850 */     return false;
/*      */   }
/*      */   
/*      */   public static double minScale(FeatureTypeStyle fts) {
/* 1861 */     if (fts == null || fts.rules().isEmpty())
/* 1862 */       return 0.0D; 
/* 1864 */     return ((Rule)fts.rules().get(0)).getMinScaleDenominator();
/*      */   }
/*      */   
/*      */   public static double maxScale(FeatureTypeStyle fts) {
/* 1875 */     if (fts == null || fts.rules().isEmpty())
/* 1876 */       return Double.NaN; 
/* 1878 */     return ((Rule)fts.rules().get(0)).getMaxScaleDenominator();
/*      */   }
/*      */   
/*      */   public static PointPlacement getPlacement(double horizAlign, double vertAlign, double rotation) {
/* 1891 */     AnchorPoint anchorPoint = sf.createAnchorPoint((Expression)ff.literal(horizAlign), (Expression)ff.literal(vertAlign));
/* 1892 */     return sf.createPointPlacement(anchorPoint, null, (Expression)ff.literal(rotation));
/*      */   }
/*      */   
/*      */   public static Style createSimpleStyle(AbstractDataStore store, String typeName, Color color) throws IOException {
/* 1910 */     SimpleFeatureType type = store.getSchema(typeName);
/* 1911 */     return createSimpleStyle((FeatureType)type, color);
/*      */   }
/*      */   
/*      */   public static Style createSimpleStyle(FeatureType type) {
/* 1922 */     return createSimpleStyle(type, Color.BLACK);
/*      */   }
/*      */   
/*      */   public static Style createSimpleStyle(FeatureType type, Color color) {
/* 1939 */     GeometryDescriptor desc = type.getGeometryDescriptor();
/* 1940 */     Class<?> clazz = desc.getType().getBinding();
/* 1941 */     Color fillColor = null;
/* 1943 */     if (Polygon.class.isAssignableFrom(clazz) || MultiPolygon.class.isAssignableFrom(clazz)) {
/* 1945 */       if (color.equals(Color.BLACK)) {
/* 1946 */         fillColor = null;
/*      */       } else {
/* 1948 */         fillColor = color;
/*      */       } 
/* 1950 */       return createPolygonStyle(color, fillColor, 0.5F);
/*      */     } 
/* 1952 */     if (LineString.class.isAssignableFrom(clazz) || MultiLineString.class.isAssignableFrom(clazz))
/* 1954 */       return createLineStyle(color, 1.0F); 
/* 1956 */     if (Point.class.isAssignableFrom(clazz) || MultiPoint.class.isAssignableFrom(clazz)) {
/* 1958 */       if (color.equals(Color.BLACK)) {
/* 1959 */         fillColor = null;
/*      */       } else {
/* 1961 */         fillColor = color;
/*      */       } 
/* 1963 */       return createPointStyle("Circle", color, fillColor, 0.5F, 3.0F);
/*      */     } 
/* 1966 */     throw new UnsupportedOperationException("No style method for " + clazz.getName());
/*      */   }
/*      */   
/*      */   public static Style createPolygonStyle(Color outlineColor, Color fillColor, float opacity) {
/* 1979 */     Stroke stroke = sf.createStroke((Expression)ff.literal(outlineColor), (Expression)ff.literal(1.0F));
/* 1980 */     Fill fill = Fill.NULL;
/* 1981 */     if (fillColor != null)
/* 1982 */       fill = sf.createFill((Expression)ff.literal(fillColor), (Expression)ff.literal(opacity)); 
/* 1984 */     return wrapSymbolizers(new Symbolizer[] { sf.createPolygonSymbolizer(stroke, fill, null) });
/*      */   }
/*      */   
/*      */   public static Style createPolygonStyle(Color outlineColor, Color fillColor, float opacity, String labelField, Font labelFont) {
/* 2005 */     Stroke stroke = sf.createStroke((Expression)ff.literal(outlineColor), (Expression)ff.literal(1.0F));
/* 2006 */     Fill fill = Fill.NULL;
/* 2007 */     if (fillColor != null)
/* 2008 */       fill = sf.createFill((Expression)ff.literal(fillColor), (Expression)ff.literal(opacity)); 
/* 2010 */     PolygonSymbolizer polySym = sf.createPolygonSymbolizer(stroke, fill, null);
/* 2012 */     if (labelField == null)
/* 2013 */       return wrapSymbolizers(new Symbolizer[] { polySym }); 
/* 2016 */     Font font = (labelFont == null) ? sf.getDefaultFont() : labelFont;
/* 2017 */     Fill labelFill = sf.createFill((Expression)ff.literal(Color.BLACK));
/* 2019 */     TextSymbolizer textSym = sf.createTextSymbolizer(labelFill, new Font[] { font }, null, (Expression)ff.property(labelField), null, null);
/* 2022 */     return wrapSymbolizers(new Symbolizer[] { polySym, textSym });
/*      */   }
/*      */   
/*      */   public static Style createLineStyle(Color lineColor, float width) {
/* 2035 */     Stroke stroke = sf.createStroke((Expression)ff.literal(lineColor), (Expression)ff.literal(width));
/* 2036 */     return wrapSymbolizers(new Symbolizer[] { sf.createLineSymbolizer(stroke, null) });
/*      */   }
/*      */   
/*      */   public static Style createLineStyle(Color lineColor, float width, String labelField, Font labelFont) {
/* 2056 */     Stroke stroke = sf.createStroke((Expression)ff.literal(lineColor), (Expression)ff.literal(width));
/* 2057 */     LineSymbolizer lineSym = sf.createLineSymbolizer(stroke, null);
/* 2059 */     if (labelField == null)
/* 2060 */       return wrapSymbolizers(new Symbolizer[] { lineSym }); 
/* 2063 */     Font font = (labelFont == null) ? sf.getDefaultFont() : labelFont;
/* 2064 */     Fill labelFill = sf.createFill((Expression)ff.literal(Color.BLACK));
/* 2066 */     TextSymbolizer textSym = sf.createTextSymbolizer(labelFill, new Font[] { font }, null, (Expression)ff.property(labelField), null, null);
/* 2069 */     return wrapSymbolizers(new Symbolizer[] { lineSym, textSym });
/*      */   }
/*      */   
/*      */   public static Style createPointStyle(String wellKnownName, Color lineColor, Color fillColor, float opacity, float size) {
/* 2091 */     return createPointStyle(wellKnownName, lineColor, fillColor, opacity, size, null, null);
/*      */   }
/*      */   
/*      */   public static Style createPointStyle(String wellKnownName, Color lineColor, Color fillColor, float opacity, float size, String labelField, Font labelFont) {
/* 2121 */     Stroke stroke = sf.createStroke((Expression)ff.literal(lineColor), (Expression)ff.literal(1.0F));
/* 2122 */     Fill fill = Fill.NULL;
/* 2123 */     if (fillColor != null)
/* 2124 */       fill = sf.createFill((Expression)ff.literal(fillColor), (Expression)ff.literal(opacity)); 
/* 2127 */     Mark mark = sf.createMark((Expression)ff.literal(wellKnownName), stroke, fill, (Expression)ff.literal(size), (Expression)ff.literal(0));
/* 2130 */     Graphic graphic = sf.createDefaultGraphic();
/* 2131 */     graphic.graphicalSymbols().clear();
/* 2132 */     graphic.graphicalSymbols().add(mark);
/* 2133 */     graphic.setSize((Expression)ff.literal(size));
/* 2135 */     PointSymbolizer pointSym = sf.createPointSymbolizer(graphic, null);
/* 2137 */     if (labelField == null)
/* 2138 */       return wrapSymbolizers(new Symbolizer[] { pointSym }); 
/* 2141 */     Font font = (labelFont == null) ? sf.getDefaultFont() : labelFont;
/* 2142 */     Fill labelFill = sf.createFill((Expression)ff.literal(Color.BLACK));
/* 2143 */     AnchorPoint anchor = sf.createAnchorPoint((Expression)ff.literal(0.5D), (Expression)ff.literal(0.0D));
/* 2144 */     Displacement disp = sf.createDisplacement((Expression)ff.literal(0), (Expression)ff.literal(5));
/* 2145 */     LabelPlacement placement = sf.createPointPlacement(anchor, disp, (Expression)ff.literal(0));
/* 2147 */     TextSymbolizer textSym = sf.createTextSymbolizer(labelFill, new Font[] { font }, null, (Expression)ff.property(labelField), placement, null);
/* 2150 */     return wrapSymbolizers(new Symbolizer[] { pointSym, textSym });
/*      */   }
/*      */   
/*      */   public static Style wrapSymbolizers(Symbolizer... symbolizers) {
/* 2163 */     if (symbolizers == null || symbolizers.length == 0)
/* 2164 */       return null; 
/* 2167 */     Rule rule = sf.createRule();
/* 2169 */     for (Symbolizer sym : symbolizers)
/* 2170 */       rule.symbolizers().add(sym); 
/* 2173 */     FeatureTypeStyle fts = sf.createFeatureTypeStyle(new Rule[] { rule });
/* 2175 */     Style style = sf.createStyle();
/* 2176 */     style.featureTypeStyles().add(fts);
/* 2178 */     return style;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\SLD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */