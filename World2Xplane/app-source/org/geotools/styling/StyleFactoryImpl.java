/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.measure.unit.Unit;
/*     */ import javax.swing.Icon;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.filter.IllegalFilterException;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.Id;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.metadata.citation.OnLineResource;
/*     */ import org.opengis.style.AnchorPoint;
/*     */ import org.opengis.style.ChannelSelection;
/*     */ import org.opengis.style.ColorMap;
/*     */ import org.opengis.style.ColorReplacement;
/*     */ import org.opengis.style.ContrastEnhancement;
/*     */ import org.opengis.style.ContrastMethod;
/*     */ import org.opengis.style.Description;
/*     */ import org.opengis.style.Displacement;
/*     */ import org.opengis.style.ExtensionSymbolizer;
/*     */ import org.opengis.style.ExternalGraphic;
/*     */ import org.opengis.style.ExternalMark;
/*     */ import org.opengis.style.FeatureTypeStyle;
/*     */ import org.opengis.style.Fill;
/*     */ import org.opengis.style.Font;
/*     */ import org.opengis.style.Graphic;
/*     */ import org.opengis.style.GraphicFill;
/*     */ import org.opengis.style.GraphicLegend;
/*     */ import org.opengis.style.GraphicStroke;
/*     */ import org.opengis.style.GraphicalSymbol;
/*     */ import org.opengis.style.Halo;
/*     */ import org.opengis.style.LabelPlacement;
/*     */ import org.opengis.style.LinePlacement;
/*     */ import org.opengis.style.LineSymbolizer;
/*     */ import org.opengis.style.Mark;
/*     */ import org.opengis.style.OverlapBehavior;
/*     */ import org.opengis.style.PointPlacement;
/*     */ import org.opengis.style.PointSymbolizer;
/*     */ import org.opengis.style.PolygonSymbolizer;
/*     */ import org.opengis.style.RasterSymbolizer;
/*     */ import org.opengis.style.Rule;
/*     */ import org.opengis.style.SelectedChannelType;
/*     */ import org.opengis.style.SemanticType;
/*     */ import org.opengis.style.ShadedRelief;
/*     */ import org.opengis.style.Stroke;
/*     */ import org.opengis.style.Style;
/*     */ import org.opengis.style.StyleFactory;
/*     */ import org.opengis.style.Symbolizer;
/*     */ import org.opengis.style.TextSymbolizer;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class StyleFactoryImpl extends AbstractStyleFactory implements StyleFactory2, StyleFactory {
/*     */   private FilterFactory2 filterFactory;
/*     */   
/*     */   private StyleFactoryImpl2 delegate;
/*     */   
/*     */   public StyleFactoryImpl() {
/*  73 */     this(CommonFactoryFinder.getFilterFactory2(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   protected StyleFactoryImpl(FilterFactory2 factory) {
/*  77 */     this.filterFactory = factory;
/*  78 */     this.delegate = new StyleFactoryImpl2(this.filterFactory);
/*     */   }
/*     */   
/*     */   public Style createStyle() {
/*  82 */     return new StyleImpl();
/*     */   }
/*     */   
/*     */   public NamedStyle createNamedStyle() {
/*  86 */     return new NamedStyleImpl();
/*     */   }
/*     */   
/*     */   public PointSymbolizer createPointSymbolizer() {
/*  90 */     return new PointSymbolizerImpl();
/*     */   }
/*     */   
/*     */   public PointSymbolizer createPointSymbolizer(Graphic graphic, String geometryPropertyName) {
/*  95 */     PointSymbolizer pSymb = new PointSymbolizerImpl();
/*  96 */     pSymb.setGeometryPropertyName(geometryPropertyName);
/*  97 */     pSymb.setGraphic(graphic);
/*  99 */     return pSymb;
/*     */   }
/*     */   
/*     */   public PolygonSymbolizer createPolygonSymbolizer() {
/* 103 */     return new PolygonSymbolizerImpl();
/*     */   }
/*     */   
/*     */   public PolygonSymbolizer createPolygonSymbolizer(Stroke stroke, Fill fill, String geometryPropertyName) {
/* 108 */     PolygonSymbolizer pSymb = new PolygonSymbolizerImpl();
/* 109 */     pSymb.setGeometryPropertyName(geometryPropertyName);
/* 110 */     pSymb.setStroke(stroke);
/* 111 */     pSymb.setFill(fill);
/* 113 */     return pSymb;
/*     */   }
/*     */   
/*     */   public LineSymbolizer createLineSymbolizer() {
/* 117 */     return new LineSymbolizerImpl();
/*     */   }
/*     */   
/*     */   public LineSymbolizer createLineSymbolizer(Stroke stroke, String geometryPropertyName) {
/* 122 */     LineSymbolizer lSymb = new LineSymbolizerImpl();
/* 123 */     lSymb.setGeometryPropertyName(geometryPropertyName);
/* 124 */     lSymb.setStroke(stroke);
/* 126 */     return lSymb;
/*     */   }
/*     */   
/*     */   public TextSymbolizer createTextSymbolizer() {
/* 130 */     return new TextSymbolizerImpl((FilterFactory)this.filterFactory);
/*     */   }
/*     */   
/*     */   public TextSymbolizer createTextSymbolizer(Fill fill, Font[] fonts, Halo halo, Expression label, LabelPlacement labelPlacement, String geometryPropertyName) {
/* 136 */     TextSymbolizer tSymb = new TextSymbolizerImpl((FilterFactory)this.filterFactory);
/* 137 */     tSymb.setFill(fill);
/* 138 */     tSymb.setFonts(fonts);
/* 139 */     tSymb.setGeometryPropertyName(geometryPropertyName);
/* 141 */     tSymb.setHalo(halo);
/* 142 */     tSymb.setLabel(label);
/* 143 */     tSymb.setPlacement(labelPlacement);
/* 145 */     return tSymb;
/*     */   }
/*     */   
/*     */   public TextSymbolizer2 createTextSymbolizer(Fill fill, Font[] fonts, Halo halo, Expression label, LabelPlacement labelPlacement, String geometryPropertyName, Graphic graphic) {
/* 151 */     TextSymbolizer2 tSymb = new TextSymbolizerImpl((FilterFactory)this.filterFactory);
/* 152 */     tSymb.setFill(fill);
/* 153 */     tSymb.setFonts(fonts);
/* 154 */     tSymb.setGeometryPropertyName(geometryPropertyName);
/* 156 */     tSymb.setHalo(halo);
/* 157 */     tSymb.setLabel(label);
/* 158 */     tSymb.setPlacement(labelPlacement);
/* 159 */     tSymb.setGraphic(graphic);
/* 161 */     return tSymb;
/*     */   }
/*     */   
/*     */   public Extent createExtent(String name, String value) {
/* 165 */     Extent extent = new ExtentImpl();
/* 166 */     extent.setName(name);
/* 167 */     extent.setValue(value);
/* 169 */     return extent;
/*     */   }
/*     */   
/*     */   public FeatureTypeConstraint createFeatureTypeConstraint(String featureTypeName, Filter filter, Extent[] extents) {
/* 174 */     FeatureTypeConstraint constraint = new FeatureTypeConstraintImpl();
/* 175 */     constraint.setFeatureTypeName(featureTypeName);
/* 176 */     constraint.setFilter(filter);
/* 177 */     constraint.setExtents(extents);
/* 179 */     return constraint;
/*     */   }
/*     */   
/*     */   public LayerFeatureConstraints createLayerFeatureConstraints(FeatureTypeConstraint[] featureTypeConstraints) {
/* 183 */     LayerFeatureConstraints constraints = new LayerFeatureConstraintsImpl();
/* 184 */     constraints.setFeatureTypeConstraints(featureTypeConstraints);
/* 186 */     return constraints;
/*     */   }
/*     */   
/*     */   public FeatureTypeStyle createFeatureTypeStyle() {
/* 190 */     return new FeatureTypeStyleImpl();
/*     */   }
/*     */   
/*     */   public FeatureTypeStyle createFeatureTypeStyle(Rule[] rules) {
/* 194 */     return new FeatureTypeStyleImpl(rules);
/*     */   }
/*     */   
/*     */   public Rule createRule() {
/* 198 */     return new RuleImpl();
/*     */   }
/*     */   
/*     */   public Rule createRule(Symbolizer[] symbolizers, Description desc, Graphic[] legends, String name, Filter filter, boolean isElseFilter, double maxScale, double minScale) {
/* 210 */     Rule r = new RuleImpl(symbolizers, desc, legends, name, filter, isElseFilter, maxScale, minScale);
/* 220 */     return r;
/*     */   }
/*     */   
/*     */   public ImageOutline createImageOutline(Symbolizer symbolizer) {
/* 224 */     ImageOutline outline = new ImageOutlineImpl();
/* 225 */     outline.setSymbolizer(symbolizer);
/* 227 */     return outline;
/*     */   }
/*     */   
/*     */   public Stroke createStroke(Expression color, Expression width) {
/* 241 */     return createStroke(color, width, (Expression)this.filterFactory.literal(1.0D));
/*     */   }
/*     */   
/*     */   public Stroke createStroke(Expression color, Expression width, Expression opacity) {
/* 258 */     return createStroke(color, width, opacity, (Expression)this.filterFactory.literal("miter"), (Expression)this.filterFactory.literal("butt"), null, (Expression)this.filterFactory.literal(0.0D), null, null);
/*     */   }
/*     */   
/*     */   public Stroke createStroke(Expression color, Expression width, Expression opacity, Expression lineJoin, Expression lineCap, float[] dashArray, Expression dashOffset, Graphic graphicFill, Graphic graphicStroke) {
/* 287 */     Stroke stroke = new StrokeImpl((FilterFactory)this.filterFactory);
/* 289 */     if (color == null)
/* 291 */       color = Stroke.DEFAULT.getColor(); 
/* 293 */     stroke.setColor(color);
/* 295 */     if (width == null)
/* 297 */       width = Stroke.DEFAULT.getWidth(); 
/* 299 */     stroke.setWidth(width);
/* 301 */     if (opacity == null)
/* 302 */       opacity = Stroke.DEFAULT.getOpacity(); 
/* 304 */     stroke.setOpacity(opacity);
/* 306 */     if (lineJoin == null)
/* 307 */       lineJoin = Stroke.DEFAULT.getLineJoin(); 
/* 309 */     stroke.setLineJoin(lineJoin);
/* 311 */     if (lineCap == null)
/* 312 */       lineCap = Stroke.DEFAULT.getLineCap(); 
/* 315 */     stroke.setLineCap(lineCap);
/* 316 */     stroke.setDashArray(dashArray);
/* 317 */     stroke.setDashOffset(dashOffset);
/* 318 */     stroke.setGraphicFill(graphicFill);
/* 319 */     stroke.setGraphicStroke(graphicStroke);
/* 321 */     return stroke;
/*     */   }
/*     */   
/*     */   public Fill createFill(Expression color, Expression backgroundColor, Expression opacity, Graphic graphicFill) {
/* 326 */     Fill fill = new FillImpl((FilterFactory)this.filterFactory);
/* 328 */     if (color == null)
/* 329 */       color = Fill.DEFAULT.getColor(); 
/* 331 */     fill.setColor(color);
/* 332 */     if (backgroundColor == null)
/* 333 */       backgroundColor = Fill.DEFAULT.getBackgroundColor(); 
/* 335 */     fill.setBackgroundColor(backgroundColor);
/* 337 */     if (opacity == null)
/* 338 */       opacity = Fill.DEFAULT.getOpacity(); 
/* 342 */     fill.setOpacity(opacity);
/* 343 */     fill.setGraphicFill(graphicFill);
/* 345 */     return fill;
/*     */   }
/*     */   
/*     */   public Fill createFill(Expression color, Expression opacity) {
/* 349 */     return createFill(color, null, opacity, null);
/*     */   }
/*     */   
/*     */   public Fill createFill(Expression color) {
/* 353 */     return createFill(color, null, (Expression)this.filterFactory.literal(1.0D), null);
/*     */   }
/*     */   
/*     */   public Mark createMark(Expression wellKnownName, Stroke stroke, Fill fill, Expression size, Expression rotation) {
/* 359 */     Mark mark = new MarkImpl((FilterFactory)this.filterFactory, null);
/* 361 */     if (wellKnownName == null)
/* 362 */       throw new IllegalArgumentException("WellKnownName can not be null in mark"); 
/* 366 */     mark.setWellKnownName(wellKnownName);
/* 367 */     mark.setStroke(stroke);
/* 368 */     mark.setFill(fill);
/* 370 */     return mark;
/*     */   }
/*     */   
/*     */   public Mark getSquareMark() {
/* 374 */     Mark mark = createMark((Expression)this.filterFactory.literal("Square"), getDefaultStroke(), getDefaultFill(), (Expression)this.filterFactory.literal(6), (Expression)this.filterFactory.literal(0));
/* 379 */     return mark;
/*     */   }
/*     */   
/*     */   public Mark getCircleMark() {
/* 383 */     Mark mark = getDefaultMark();
/* 384 */     mark.setWellKnownName((Expression)this.filterFactory.literal("Circle"));
/* 386 */     return mark;
/*     */   }
/*     */   
/*     */   public Mark getCrossMark() {
/* 390 */     Mark mark = getDefaultMark();
/* 391 */     mark.setWellKnownName((Expression)this.filterFactory.literal("Cross"));
/* 393 */     return mark;
/*     */   }
/*     */   
/*     */   public Mark getXMark() {
/* 397 */     Mark mark = getDefaultMark();
/* 398 */     mark.setWellKnownName((Expression)this.filterFactory.literal("X"));
/* 400 */     return mark;
/*     */   }
/*     */   
/*     */   public Mark getTriangleMark() {
/* 404 */     Mark mark = getDefaultMark();
/* 405 */     mark.setWellKnownName((Expression)this.filterFactory.literal("Triangle"));
/* 407 */     return mark;
/*     */   }
/*     */   
/*     */   public Mark getStarMark() {
/* 411 */     Mark mark = getDefaultMark();
/* 412 */     mark.setWellKnownName((Expression)this.filterFactory.literal("Star"));
/* 414 */     return mark;
/*     */   }
/*     */   
/*     */   public Mark createMark() {
/* 418 */     Mark mark = new MarkImpl((FilterFactory)this.filterFactory, null);
/* 420 */     return mark;
/*     */   }
/*     */   
/*     */   public Graphic createGraphic(ExternalGraphic[] externalGraphics, Mark[] marks, Symbol[] symbols, Expression opacity, Expression size, Expression rotation) {
/* 426 */     Graphic graphic = new GraphicImpl((FilterFactory)this.filterFactory);
/* 428 */     symbols = (symbols != null) ? symbols : new Symbol[0];
/* 429 */     graphic.setSymbols(symbols);
/* 433 */     if (externalGraphics != null)
/* 434 */       graphic.graphicalSymbols().addAll(Arrays.asList((GraphicalSymbol[])externalGraphics)); 
/* 438 */     if (marks != null)
/* 439 */       graphic.graphicalSymbols().addAll(Arrays.asList((GraphicalSymbol[])marks)); 
/* 441 */     if (opacity == null)
/* 442 */       opacity = Graphic.DEFAULT.getOpacity(); 
/* 444 */     graphic.setOpacity(opacity);
/* 446 */     if (size == null)
/* 447 */       size = Graphic.DEFAULT.getSize(); 
/* 449 */     graphic.setSize(size);
/* 451 */     if (rotation == null)
/* 452 */       rotation = Graphic.DEFAULT.getRotation(); 
/* 455 */     graphic.setRotation(rotation);
/* 457 */     return graphic;
/*     */   }
/*     */   
/*     */   public ExternalGraphic createExternalGraphic(String uri, String format) {
/* 461 */     ExternalGraphic extg = new ExternalGraphicImpl();
/* 462 */     extg.setURI(uri);
/* 463 */     extg.setFormat(format);
/* 465 */     return extg;
/*     */   }
/*     */   
/*     */   public ExternalGraphic createExternalGraphic(Icon inlineContent, String format) {
/* 470 */     ExternalGraphicImpl extg = new ExternalGraphicImpl();
/* 471 */     extg.setInlineContent(inlineContent);
/* 472 */     extg.setFormat(format);
/* 473 */     return extg;
/*     */   }
/*     */   
/*     */   public ExternalGraphic createExternalGraphic(URL url, String format) {
/* 477 */     ExternalGraphic extg = new ExternalGraphicImpl();
/* 478 */     extg.setLocation(url);
/* 479 */     extg.setFormat(format);
/* 481 */     return extg;
/*     */   }
/*     */   
/*     */   public Font createFont(Expression fontFamily, Expression fontStyle, Expression fontWeight, Expression fontSize) {
/* 486 */     Font font = new FontImpl();
/* 488 */     if (fontFamily == null)
/* 489 */       throw new IllegalArgumentException("Null font family specified"); 
/* 491 */     font.setFontFamily(fontFamily);
/* 493 */     if (fontSize == null)
/* 494 */       throw new IllegalArgumentException("Null font size specified"); 
/* 497 */     font.setFontSize(fontSize);
/* 499 */     if (fontStyle == null)
/* 500 */       throw new IllegalArgumentException("Null font Style specified"); 
/* 503 */     font.setFontStyle(fontStyle);
/* 505 */     if (fontWeight == null)
/* 506 */       throw new IllegalArgumentException("Null font weight specified"); 
/* 509 */     font.setFontWeight(fontWeight);
/* 511 */     return font;
/*     */   }
/*     */   
/*     */   public LinePlacement createLinePlacement(Expression offset) {
/* 518 */     LinePlacement linep = new LinePlacementImpl((FilterFactory)this.filterFactory);
/* 519 */     linep.setPerpendicularOffset(offset);
/* 521 */     return linep;
/*     */   }
/*     */   
/*     */   public PointPlacement createPointPlacement(AnchorPoint anchorPoint, Displacement displacement, Expression rotation) {
/* 529 */     PointPlacement pointp = new PointPlacementImpl((FilterFactory)this.filterFactory);
/* 530 */     pointp.setAnchorPoint(anchorPoint);
/* 531 */     pointp.setDisplacement(displacement);
/* 532 */     pointp.setRotation(rotation);
/* 534 */     return pointp;
/*     */   }
/*     */   
/*     */   public AnchorPoint createAnchorPoint(Expression x, Expression y) {
/* 538 */     AnchorPoint anchorPoint = new AnchorPointImpl((FilterFactory)this.filterFactory);
/* 539 */     anchorPoint.setAnchorPointX(x);
/* 540 */     anchorPoint.setAnchorPointY(y);
/* 542 */     return anchorPoint;
/*     */   }
/*     */   
/*     */   public Displacement createDisplacement(Expression x, Expression y) {
/* 546 */     Displacement displacement = new DisplacementImpl((FilterFactory)this.filterFactory);
/* 547 */     displacement.setDisplacementX(x);
/* 548 */     displacement.setDisplacementY(y);
/* 550 */     return displacement;
/*     */   }
/*     */   
/*     */   public Halo createHalo(Fill fill, Expression radius) {
/* 554 */     Halo halo = new HaloImpl((FilterFactory)this.filterFactory);
/* 555 */     halo.setFill(fill);
/* 556 */     halo.setRadius(radius);
/* 558 */     return halo;
/*     */   }
/*     */   
/*     */   public Fill getDefaultFill() {
/* 562 */     Fill fill = new FillImpl((FilterFactory)this.filterFactory);
/*     */     try {
/* 565 */       fill.setColor((Expression)this.filterFactory.literal("#808080"));
/* 566 */       fill.setOpacity((Expression)this.filterFactory.literal(new Double(1.0D)));
/* 568 */       fill.setBackgroundColor((Expression)this.filterFactory.literal("#FFFFFF"));
/* 569 */     } catch (IllegalFilterException ife) {
/* 570 */       throw new RuntimeException("Error creating fill", ife);
/*     */     } 
/* 573 */     return fill;
/*     */   }
/*     */   
/*     */   public LineSymbolizer getDefaultLineSymbolizer() {
/* 577 */     return createLineSymbolizer(getDefaultStroke(), null);
/*     */   }
/*     */   
/*     */   public Mark getDefaultMark() {
/* 581 */     return getSquareMark();
/*     */   }
/*     */   
/*     */   public PointSymbolizer getDefaultPointSymbolizer() {
/* 585 */     return createPointSymbolizer(createDefaultGraphic(), null);
/*     */   }
/*     */   
/*     */   public PolygonSymbolizer getDefaultPolygonSymbolizer() {
/* 589 */     return createPolygonSymbolizer(getDefaultStroke(), getDefaultFill(), null);
/*     */   }
/*     */   
/*     */   public Stroke getDefaultStroke() {
/*     */     try {
/* 595 */       Stroke stroke = createStroke((Expression)this.filterFactory.literal("#000000"), (Expression)this.filterFactory.literal(new Integer(1)));
/* 598 */       stroke.setDashOffset((Expression)this.filterFactory.literal(new Integer(0)));
/* 600 */       stroke.setDashArray(Stroke.DEFAULT.getDashArray());
/* 601 */       stroke.setLineCap((Expression)this.filterFactory.literal("butt"));
/* 602 */       stroke.setLineJoin((Expression)this.filterFactory.literal("miter"));
/* 603 */       stroke.setOpacity((Expression)this.filterFactory.literal(new Integer(1)));
/* 605 */       return stroke;
/* 606 */     } catch (IllegalFilterException ife) {
/* 608 */       throw new RuntimeException("Error creating stroke", ife);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Style getDefaultStyle() {
/* 613 */     Style style = createStyle();
/* 615 */     return style;
/*     */   }
/*     */   
/*     */   public TextSymbolizer getDefaultTextSymbolizer() {
/* 626 */     return createTextSymbolizer(getDefaultFill(), new Font[] { getDefaultFont() }, null, null, getDefaultPointPlacement(), "geometry:text");
/*     */   }
/*     */   
/*     */   public Font getDefaultFont() {
/* 640 */     return FontImpl.createDefault((FilterFactory)this.filterFactory);
/*     */   }
/*     */   
/*     */   public Graphic createDefaultGraphic() {
/* 644 */     Graphic graphic = new GraphicImpl((FilterFactory)this.filterFactory);
/* 645 */     graphic.addMark(createMark());
/* 646 */     graphic.setSize(Expression.NIL);
/* 647 */     graphic.setOpacity((Expression)this.filterFactory.literal(1.0D));
/* 648 */     graphic.setRotation((Expression)this.filterFactory.literal(0.0D));
/* 650 */     return graphic;
/*     */   }
/*     */   
/*     */   public Graphic getDefaultGraphic() {
/* 654 */     return createDefaultGraphic();
/*     */   }
/*     */   
/*     */   public PointPlacement getDefaultPointPlacement() {
/* 664 */     return createPointPlacement(createAnchorPoint((Expression)this.filterFactory.literal(0), (Expression)this.filterFactory.literal(0.5D)), createDisplacement((Expression)this.filterFactory.literal(0), (Expression)this.filterFactory.literal(0)), (Expression)this.filterFactory.literal(0));
/*     */   }
/*     */   
/*     */   public RasterSymbolizer createRasterSymbolizer() {
/* 673 */     return new RasterSymbolizerImpl((FilterFactory)this.filterFactory);
/*     */   }
/*     */   
/*     */   public RasterSymbolizer createRasterSymbolizer(String geometryPropertyName, Expression opacity, ChannelSelection channel, Expression overlap, ColorMap colorMap, ContrastEnhancement cenhancement, ShadedRelief relief, Symbolizer outline) {
/* 681 */     RasterSymbolizer rastersym = new RasterSymbolizerImpl((FilterFactory)this.filterFactory);
/* 683 */     if (geometryPropertyName != null)
/* 684 */       rastersym.setGeometryPropertyName(geometryPropertyName); 
/* 687 */     if (opacity != null)
/* 688 */       rastersym.setOpacity(opacity); 
/* 691 */     if (channel != null)
/* 692 */       rastersym.setChannelSelection(channel); 
/* 695 */     if (overlap != null)
/* 696 */       rastersym.setOverlap(overlap); 
/* 699 */     if (colorMap != null)
/* 700 */       rastersym.setColorMap(colorMap); 
/* 703 */     if (cenhancement != null)
/* 704 */       rastersym.setContrastEnhancement(cenhancement); 
/* 707 */     if (relief != null)
/* 708 */       rastersym.setShadedRelief(relief); 
/* 711 */     if (outline != null)
/* 712 */       rastersym.setImageOutline(outline); 
/* 715 */     return rastersym;
/*     */   }
/*     */   
/*     */   public RasterSymbolizer getDefaultRasterSymbolizer() {
/* 719 */     return createRasterSymbolizer(null, (Expression)this.filterFactory.literal(1.0D), null, null, null, null, null, null);
/*     */   }
/*     */   
/*     */   public ChannelSelection createChannelSelection(SelectedChannelType[] channels) {
/* 726 */     ChannelSelection channelSel = new ChannelSelectionImpl();
/* 728 */     if (channels != null && channels.length > 0)
/* 729 */       channelSel.setSelectedChannels(channels); 
/* 732 */     return channelSel;
/*     */   }
/*     */   
/*     */   public ColorMap createColorMap() {
/* 736 */     return new ColorMapImpl();
/*     */   }
/*     */   
/*     */   public ColorMapEntry createColorMapEntry() {
/* 740 */     return new ColorMapEntryImpl();
/*     */   }
/*     */   
/*     */   public ContrastEnhancement createContrastEnhancement() {
/* 744 */     return new ContrastEnhancementImpl((FilterFactory)this.filterFactory);
/*     */   }
/*     */   
/*     */   public ContrastEnhancement createContrastEnhancement(Expression gammaValue) {
/* 748 */     ContrastEnhancement ce = new ContrastEnhancementImpl();
/* 749 */     ce.setGammaValue(gammaValue);
/* 751 */     return ce;
/*     */   }
/*     */   
/*     */   public SelectedChannelType createSelectedChannelType(String name, ContrastEnhancement enhancement) {
/* 756 */     SelectedChannelType sct = new SelectedChannelTypeImpl((FilterFactory)this.filterFactory);
/* 757 */     sct.setChannelName(name);
/* 758 */     sct.setContrastEnhancement(enhancement);
/* 760 */     return sct;
/*     */   }
/*     */   
/*     */   public SelectedChannelType createSelectedChannelType(String name, Expression gammaValue) {
/* 765 */     SelectedChannelType sct = new SelectedChannelTypeImpl((FilterFactory)this.filterFactory);
/* 766 */     sct.setChannelName(name);
/* 767 */     sct.setContrastEnhancement(createContrastEnhancement(gammaValue));
/* 769 */     return sct;
/*     */   }
/*     */   
/*     */   public StyledLayerDescriptor createStyledLayerDescriptor() {
/* 773 */     return new StyledLayerDescriptorImpl();
/*     */   }
/*     */   
/*     */   public UserLayer createUserLayer() {
/* 777 */     return new UserLayerImpl();
/*     */   }
/*     */   
/*     */   public NamedLayer createNamedLayer() {
/* 781 */     return new NamedLayerImpl();
/*     */   }
/*     */   
/*     */   public RemoteOWS createRemoteOWS(String service, String onlineResource) {
/* 785 */     RemoteOWSImpl remoteOWS = new RemoteOWSImpl();
/* 786 */     remoteOWS.setService(service);
/* 787 */     remoteOWS.setOnlineResource(onlineResource);
/* 789 */     return remoteOWS;
/*     */   }
/*     */   
/*     */   public ShadedRelief createShadedRelief(Expression reliefFactor) {
/* 792 */     ShadedRelief relief = new ShadedReliefImpl((FilterFactory)this.filterFactory);
/* 793 */     relief.setReliefFactor(reliefFactor);
/* 795 */     return relief;
/*     */   }
/*     */   
/*     */   public AnchorPoint anchorPoint(Expression x, Expression y) {
/* 802 */     return this.delegate.anchorPoint(x, y);
/*     */   }
/*     */   
/*     */   public ChannelSelection channelSelection(SelectedChannelType gray) {
/* 807 */     return this.delegate.channelSelection(gray);
/*     */   }
/*     */   
/*     */   public ChannelSelection channelSelection(SelectedChannelType red, SelectedChannelType green, SelectedChannelType blue) {
/* 812 */     return this.delegate.channelSelection(red, green, blue);
/*     */   }
/*     */   
/*     */   public ColorMap colorMap(Expression propertyName, Expression... mapping) {
/* 816 */     return this.delegate.colorMap(propertyName, mapping);
/*     */   }
/*     */   
/*     */   public ColorReplacementImpl colorReplacement(Expression propertyName, Expression... mapping) {
/* 820 */     return this.delegate.colorReplacement(propertyName, mapping);
/*     */   }
/*     */   
/*     */   public ContrastEnhancement contrastEnhancement(Expression gamma, ContrastMethod method) {
/* 825 */     return this.delegate.contrastEnhancement(gamma, method);
/*     */   }
/*     */   
/*     */   public Description description(InternationalString title, InternationalString description) {
/* 829 */     return this.delegate.description(title, description);
/*     */   }
/*     */   
/*     */   public Displacement displacement(Expression dx, Expression dy) {
/* 833 */     return this.delegate.displacement(dx, dy);
/*     */   }
/*     */   
/*     */   public ExternalGraphic externalGraphic(Icon inline, Collection<ColorReplacement> replacements) {
/* 838 */     return this.delegate.externalGraphic(inline, replacements);
/*     */   }
/*     */   
/*     */   public ExternalGraphic externalGraphic(OnLineResource resource, String format, Collection<ColorReplacement> replacements) {
/* 843 */     return this.delegate.externalGraphic(resource, format, replacements);
/*     */   }
/*     */   
/*     */   public ExternalMarkImpl externalMark(Icon inline) {
/* 847 */     return this.delegate.externalMark(inline);
/*     */   }
/*     */   
/*     */   public ExternalMarkImpl externalMark(OnLineResource resource, String format, int markIndex) {
/* 851 */     return this.delegate.externalMark(resource, format, markIndex);
/*     */   }
/*     */   
/*     */   public FeatureTypeStyle featureTypeStyle(String name, Description description, Id definedFor, Set<Name> featureTypeNames, Set<SemanticType> types, List<Rule> rules) {
/* 857 */     return this.delegate.featureTypeStyle(name, description, definedFor, featureTypeNames, types, rules);
/*     */   }
/*     */   
/*     */   public Fill fill(GraphicFill fill, Expression color, Expression opacity) {
/* 861 */     return this.delegate.fill(fill, color, opacity);
/*     */   }
/*     */   
/*     */   public Font font(List<Expression> family, Expression style, Expression weight, Expression size) {
/* 866 */     return this.delegate.font(family, style, weight, size);
/*     */   }
/*     */   
/*     */   public Graphic graphic(List<GraphicalSymbol> symbols, Expression opacity, Expression size, Expression rotation, AnchorPoint anchor, Displacement disp) {
/* 872 */     return this.delegate.graphic(symbols, opacity, size, rotation, anchor, disp);
/*     */   }
/*     */   
/*     */   public Graphic graphicFill(List<GraphicalSymbol> symbols, Expression opacity, Expression size, Expression rotation, AnchorPoint anchorPoint, Displacement displacement) {
/* 877 */     return this.delegate.graphicFill(symbols, opacity, size, rotation, anchorPoint, displacement);
/*     */   }
/*     */   
/*     */   public GraphicLegend graphicLegend(List<GraphicalSymbol> symbols, Expression opacity, Expression size, Expression rotation, AnchorPoint anchorPoint, Displacement displacement) {
/* 882 */     return this.delegate.graphicLegend(symbols, opacity, size, rotation, anchorPoint, displacement);
/*     */   }
/*     */   
/*     */   public Graphic graphicStroke(List<GraphicalSymbol> symbols, Expression opacity, Expression size, Expression rotation, AnchorPoint anchorPoint, Displacement displacement, Expression initialGap, Expression gap) {
/* 887 */     return this.delegate.graphicStroke(symbols, opacity, size, rotation, anchorPoint, displacement, initialGap, gap);
/*     */   }
/*     */   
/*     */   public Halo halo(Fill fill, Expression radius) {
/* 890 */     return this.delegate.halo(fill, radius);
/*     */   }
/*     */   
/*     */   public LinePlacement linePlacement(Expression offset, Expression initialGap, Expression gap, boolean repeated, boolean aligned, boolean generalizedLine) {
/* 895 */     return this.delegate.linePlacement(offset, initialGap, gap, repeated, aligned, generalizedLine);
/*     */   }
/*     */   
/*     */   public LineSymbolizer lineSymbolizer(String name, Expression geometry, Description description, Unit<?> unit, Stroke stroke, Expression offset) {
/* 901 */     return this.delegate.lineSymbolizer(name, geometry, description, unit, stroke, offset);
/*     */   }
/*     */   
/*     */   public Mark mark(Expression wellKnownName, Fill fill, Stroke stroke) {
/* 905 */     return this.delegate.mark(wellKnownName, fill, stroke);
/*     */   }
/*     */   
/*     */   public MarkImpl mark(ExternalMark externalMark, Fill fill, Stroke stroke) {
/* 910 */     return this.delegate.mark(externalMark, fill, stroke);
/*     */   }
/*     */   
/*     */   public PointPlacement pointPlacement(AnchorPoint anchor, Displacement displacement, Expression rotation) {
/* 915 */     return this.delegate.pointPlacement(anchor, displacement, rotation);
/*     */   }
/*     */   
/*     */   public PointSymbolizer pointSymbolizer(String name, Expression geometry, Description description, Unit<?> unit, Graphic graphic) {
/* 920 */     return this.delegate.pointSymbolizer(name, geometry, description, unit, graphic);
/*     */   }
/*     */   
/*     */   public PolygonSymbolizer polygonSymbolizer(String name, Expression geometry, Description description, Unit<?> unit, Stroke stroke, Fill fill, Displacement displacement, Expression offset) {
/* 926 */     return this.delegate.polygonSymbolizer(name, geometry, description, unit, stroke, fill, displacement, offset);
/*     */   }
/*     */   
/*     */   public RasterSymbolizer rasterSymbolizer(String name, Expression geometry, Description description, Unit<?> unit, Expression opacity, ChannelSelection channelSelection, OverlapBehavior overlapsBehaviour, ColorMap colorMap, ContrastEnhancement contrast, ShadedRelief shaded, Symbolizer outline) {
/* 934 */     return this.delegate.rasterSymbolizer(name, geometry, description, unit, opacity, channelSelection, overlapsBehaviour, colorMap, contrast, shaded, outline);
/*     */   }
/*     */   
/*     */   public ExtensionSymbolizer extensionSymbolizer(String name, String propertyName, Description description, Unit<?> unit, String extensionName, Map<String, Expression> parameters) {
/* 940 */     return this.delegate.extensionSymbolizer(name, propertyName, description, unit, extensionName, parameters);
/*     */   }
/*     */   
/*     */   public Rule rule(String name, Description description, GraphicLegend legend, double min, double max, List<Symbolizer> symbolizers, Filter filter) {
/* 945 */     return this.delegate.rule(name, description, legend, min, max, symbolizers, filter);
/*     */   }
/*     */   
/*     */   public SelectedChannelType selectedChannelType(String channelName, ContrastEnhancement contrastEnhancement) {
/* 951 */     return this.delegate.selectedChannelType(channelName, contrastEnhancement);
/*     */   }
/*     */   
/*     */   public ShadedRelief shadedRelief(Expression reliefFactor, boolean brightnessOnly) {
/* 956 */     return this.delegate.shadedRelief(reliefFactor, brightnessOnly);
/*     */   }
/*     */   
/*     */   public Stroke stroke(Expression color, Expression opacity, Expression width, Expression join, Expression cap, float[] dashes, Expression offset) {
/* 961 */     return this.delegate.stroke(color, opacity, width, join, cap, dashes, offset);
/*     */   }
/*     */   
/*     */   public Stroke stroke(GraphicFill fill, Expression color, Expression opacity, Expression width, Expression join, Expression cap, float[] dashes, Expression offset) {
/* 966 */     return this.delegate.stroke(fill, color, opacity, width, join, cap, dashes, offset);
/*     */   }
/*     */   
/*     */   public Stroke stroke(GraphicStroke stroke, Expression color, Expression opacity, Expression width, Expression join, Expression cap, float[] dashes, Expression offset) {
/* 972 */     return this.delegate.stroke(stroke, color, opacity, width, join, cap, dashes, offset);
/*     */   }
/*     */   
/*     */   public Style style(String name, Description description, boolean isDefault, List<FeatureTypeStyle> featureTypeStyles, Symbolizer defaultSymbolizer) {
/* 978 */     return this.delegate.style(name, description, isDefault, featureTypeStyles, defaultSymbolizer);
/*     */   }
/*     */   
/*     */   public TextSymbolizer textSymbolizer(String name, Expression geometry, Description description, Unit<?> unit, Expression label, Font font, LabelPlacement placement, Halo halo, Fill fill) {
/* 985 */     return this.delegate.textSymbolizer(name, geometry, description, unit, label, font, placement, halo, fill);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\StyleFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */