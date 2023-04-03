/*      */ package org.geotools.styling.visitor;
/*      */ 
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.Stack;
/*      */ import javax.swing.Icon;
/*      */ import org.geotools.factory.CommonFactoryFinder;
/*      */ import org.geotools.filter.visitor.DuplicatingFilterVisitor;
/*      */ import org.geotools.styling.AnchorPoint;
/*      */ import org.geotools.styling.ChannelSelection;
/*      */ import org.geotools.styling.ColorMap;
/*      */ import org.geotools.styling.ColorMapEntry;
/*      */ import org.geotools.styling.ContrastEnhancement;
/*      */ import org.geotools.styling.Description;
/*      */ import org.geotools.styling.DescriptionImpl;
/*      */ import org.geotools.styling.Displacement;
/*      */ import org.geotools.styling.Extent;
/*      */ import org.geotools.styling.ExternalGraphic;
/*      */ import org.geotools.styling.FeatureTypeConstraint;
/*      */ import org.geotools.styling.FeatureTypeStyle;
/*      */ import org.geotools.styling.FeatureTypeStyleImpl;
/*      */ import org.geotools.styling.Fill;
/*      */ import org.geotools.styling.Font;
/*      */ import org.geotools.styling.Graphic;
/*      */ import org.geotools.styling.Halo;
/*      */ import org.geotools.styling.ImageOutline;
/*      */ import org.geotools.styling.LabelPlacement;
/*      */ import org.geotools.styling.LinePlacement;
/*      */ import org.geotools.styling.LineSymbolizer;
/*      */ import org.geotools.styling.Mark;
/*      */ import org.geotools.styling.NamedLayer;
/*      */ import org.geotools.styling.OtherText;
/*      */ import org.geotools.styling.OtherTextImpl;
/*      */ import org.geotools.styling.OverlapBehavior;
/*      */ import org.geotools.styling.PointPlacement;
/*      */ import org.geotools.styling.PointSymbolizer;
/*      */ import org.geotools.styling.PolygonSymbolizer;
/*      */ import org.geotools.styling.RasterSymbolizer;
/*      */ import org.geotools.styling.Rule;
/*      */ import org.geotools.styling.SelectedChannelType;
/*      */ import org.geotools.styling.ShadedRelief;
/*      */ import org.geotools.styling.Stroke;
/*      */ import org.geotools.styling.Style;
/*      */ import org.geotools.styling.StyleFactory;
/*      */ import org.geotools.styling.StyleVisitor;
/*      */ import org.geotools.styling.StyledLayer;
/*      */ import org.geotools.styling.StyledLayerDescriptor;
/*      */ import org.geotools.styling.Symbol;
/*      */ import org.geotools.styling.Symbolizer;
/*      */ import org.geotools.styling.TextSymbolizer;
/*      */ import org.geotools.styling.TextSymbolizer2;
/*      */ import org.geotools.styling.UserLayer;
/*      */ import org.opengis.filter.Filter;
/*      */ import org.opengis.filter.FilterFactory2;
/*      */ import org.opengis.filter.FilterVisitor;
/*      */ import org.opengis.filter.expression.Expression;
/*      */ import org.opengis.filter.expression.ExpressionVisitor;
/*      */ import org.opengis.style.AnchorPoint;
/*      */ import org.opengis.style.ChannelSelection;
/*      */ import org.opengis.style.ColorMap;
/*      */ import org.opengis.style.ContrastEnhancement;
/*      */ import org.opengis.style.Description;
/*      */ import org.opengis.style.Displacement;
/*      */ import org.opengis.style.FeatureTypeStyle;
/*      */ import org.opengis.style.Fill;
/*      */ import org.opengis.style.Font;
/*      */ import org.opengis.style.Graphic;
/*      */ import org.opengis.style.Halo;
/*      */ import org.opengis.style.LabelPlacement;
/*      */ import org.opengis.style.ShadedRelief;
/*      */ import org.opengis.style.Stroke;
/*      */ import org.opengis.style.Symbolizer;
/*      */ 
/*      */ public class DuplicatingStyleVisitor implements StyleVisitor {
/*      */   protected final StyleFactory sf;
/*      */   
/*      */   protected final FilterFactory2 ff;
/*      */   
/*      */   protected boolean STRICT;
/*      */   
/*      */   protected final DuplicatingFilterVisitor copyFilter;
/*      */   
/*  111 */   protected Stack<Object> pages = new Stack();
/*      */   
/*      */   public DuplicatingStyleVisitor() {
/*  114 */     this(CommonFactoryFinder.getStyleFactory(null));
/*      */   }
/*      */   
/*      */   public DuplicatingStyleVisitor(StyleFactory styleFactory) {
/*  118 */     this(styleFactory, CommonFactoryFinder.getFilterFactory2(null));
/*      */   }
/*      */   
/*      */   public DuplicatingStyleVisitor(StyleFactory styleFactory, FilterFactory2 filterFactory) {
/*  122 */     this.copyFilter = new DuplicatingFilterVisitor(filterFactory);
/*  123 */     this.sf = styleFactory;
/*  124 */     this.ff = filterFactory;
/*  125 */     this.STRICT = false;
/*      */   }
/*      */   
/*      */   public void setStrict(boolean strict) {
/*  133 */     this.STRICT = strict;
/*      */   }
/*      */   
/*      */   public Object getCopy() {
/*  137 */     return this.pages.peek();
/*      */   }
/*      */   
/*      */   public void visit(StyledLayerDescriptor sld) {
/*  141 */     StyledLayerDescriptor copy = null;
/*  143 */     StyledLayer[] layers = sld.getStyledLayers();
/*  144 */     StyledLayer[] layersCopy = new StyledLayer[layers.length];
/*  145 */     int length = layers.length;
/*  146 */     for (int i = 0; i < length; i++) {
/*  147 */       if (layers[i] instanceof UserLayer) {
/*  148 */         ((UserLayer)layers[i]).accept(this);
/*  149 */         layersCopy[i] = (StyledLayer)this.pages.pop();
/*  150 */       } else if (layers[i] instanceof NamedLayer) {
/*  151 */         ((NamedLayer)layers[i]).accept(this);
/*  152 */         layersCopy[i] = (StyledLayer)this.pages.pop();
/*      */       } 
/*      */     } 
/*  156 */     copy = this.sf.createStyledLayerDescriptor();
/*  157 */     copy.setAbstract(sld.getAbstract());
/*  158 */     copy.setName(sld.getName());
/*  159 */     copy.setTitle(sld.getTitle());
/*  160 */     copy.setStyledLayers(layersCopy);
/*  162 */     if (this.STRICT && !copy.equals(sld))
/*  163 */       throw new IllegalStateException("Was unable to duplicate provided SLD:" + sld); 
/*  165 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(NamedLayer layer) {
/*  169 */     NamedLayer copy = null;
/*  171 */     Style[] style = layer.getStyles();
/*  172 */     Style[] styleCopy = new Style[style.length];
/*  173 */     int length = style.length;
/*  174 */     for (int i = 0; i < length; i++) {
/*  175 */       if (style[i] != null) {
/*  176 */         style[i].accept(this);
/*  177 */         styleCopy[i] = (Style)this.pages.pop();
/*      */       } 
/*      */     } 
/*  181 */     FeatureTypeConstraint[] lfc = layer.getLayerFeatureConstraints();
/*  182 */     FeatureTypeConstraint[] lfcCopy = new FeatureTypeConstraint[lfc.length];
/*  184 */     length = lfc.length;
/*      */     int j;
/*  185 */     for (j = 0; j < length; j++) {
/*  186 */       if (lfc[j] != null) {
/*  187 */         lfc[j].accept(this);
/*  188 */         lfcCopy[j] = (FeatureTypeConstraint)this.pages.pop();
/*      */       } 
/*      */     } 
/*  192 */     copy = this.sf.createNamedLayer();
/*  193 */     copy.setName(layer.getName());
/*  194 */     length = styleCopy.length;
/*  195 */     for (j = 0; j < length; j++)
/*  196 */       copy.addStyle(styleCopy[j]); 
/*  199 */     copy.setLayerFeatureConstraints(lfcCopy);
/*  200 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(UserLayer layer) {
/*  204 */     UserLayer copy = null;
/*  207 */     Style[] style = layer.getUserStyles();
/*  208 */     int length = style.length;
/*  209 */     Style[] styleCopy = new Style[length];
/*  210 */     for (int i = 0; i < length; i++) {
/*  211 */       if (style[i] != null) {
/*  212 */         style[i].accept(this);
/*  213 */         styleCopy[i] = (Style)this.pages.pop();
/*      */       } 
/*      */     } 
/*  217 */     FeatureTypeConstraint[] lfc = layer.getLayerFeatureConstraints();
/*  218 */     FeatureTypeConstraint[] lfcCopy = new FeatureTypeConstraint[lfc.length];
/*  220 */     length = lfc.length;
/*  221 */     for (int j = 0; j < length; j++) {
/*  222 */       if (lfc[j] != null) {
/*  223 */         lfc[j].accept(this);
/*  224 */         lfcCopy[j] = (FeatureTypeConstraint)this.pages.pop();
/*      */       } 
/*      */     } 
/*  228 */     copy = this.sf.createUserLayer();
/*  229 */     copy.setName(layer.getName());
/*  230 */     copy.setUserStyles(styleCopy);
/*  231 */     copy.setLayerFeatureConstraints(lfcCopy);
/*  233 */     if (this.STRICT && !copy.equals(layer))
/*  234 */       throw new IllegalStateException("Was unable to duplicate provided UserLayer:" + layer); 
/*  236 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(Style style) {
/*  240 */     Style copy = null;
/*  242 */     FeatureTypeStyle[] fts = style.getFeatureTypeStyles();
/*  243 */     int length = fts.length;
/*  244 */     FeatureTypeStyle[] ftsCopy = new FeatureTypeStyle[length];
/*  245 */     for (int i = 0; i < length; i++) {
/*  246 */       if (fts[i] != null) {
/*  247 */         fts[i].accept(this);
/*  248 */         ftsCopy[i] = (FeatureTypeStyle)this.pages.pop();
/*      */       } 
/*      */     } 
/*  252 */     copy = this.sf.createStyle();
/*  253 */     copy.setAbstract(style.getAbstract());
/*  254 */     copy.setName(style.getName());
/*  255 */     copy.setTitle(style.getTitle());
/*  256 */     copy.setFeatureTypeStyles(ftsCopy);
/*  258 */     if (this.STRICT && !copy.equals(style))
/*  259 */       throw new IllegalStateException("Was unable to duplicate provided Style:" + style); 
/*  261 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(Rule rule) {
/*  265 */     Rule copy = null;
/*  269 */     Filter filterCopy = null;
/*  271 */     if (rule.getFilter() != null) {
/*  272 */       Filter filter = rule.getFilter();
/*  273 */       filterCopy = copy(filter);
/*      */     } 
/*  276 */     Symbolizer[] symsCopy = rule.getSymbolizers();
/*  277 */     for (int i = 0; i < symsCopy.length; i++)
/*  278 */       symsCopy[i] = copy(symsCopy[i]); 
/*  281 */     Graphic[] legendCopy = rule.getLegendGraphic();
/*  282 */     for (int j = 0; j < legendCopy.length; j++)
/*  283 */       legendCopy[j] = copy(legendCopy[j]); 
/*  286 */     Description description1 = rule.getDescription();
/*  287 */     Description description = copy((Description)description1);
/*  289 */     copy = this.sf.createRule();
/*  290 */     copy.setSymbolizers(symsCopy);
/*  291 */     copy.setDescription(description);
/*  292 */     copy.setLegendGraphic(legendCopy);
/*  293 */     copy.setName(rule.getName());
/*  294 */     copy.setFilter(filterCopy);
/*  295 */     copy.setElseFilter(rule.isElseFilter());
/*  296 */     copy.setMaxScaleDenominator(rule.getMaxScaleDenominator());
/*  297 */     copy.setMinScaleDenominator(rule.getMinScaleDenominator());
/*  299 */     if (this.STRICT && !copy.equals(rule))
/*  300 */       throw new IllegalStateException("Was unable to duplicate provided Rule:" + rule); 
/*  302 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(FeatureTypeStyle fts) {
/*  308 */     FeatureTypeStyleImpl featureTypeStyleImpl = new FeatureTypeStyleImpl((FeatureTypeStyle)fts);
/*  319 */     Rule[] rules = fts.getRules();
/*  320 */     int length = rules.length;
/*  321 */     Rule[] rulesCopy = new Rule[length];
/*  322 */     for (int i = 0; i < length; i++) {
/*  323 */       if (rules[i] != null) {
/*  324 */         rules[i].accept(this);
/*  325 */         rulesCopy[i] = (Rule)this.pages.pop();
/*      */       } 
/*      */     } 
/*  334 */     featureTypeStyleImpl.setRules(rulesCopy);
/*  337 */     if (this.STRICT && !featureTypeStyleImpl.equals(fts))
/*  338 */       throw new IllegalStateException("Was unable to duplicate provided FeatureTypeStyle:" + fts); 
/*  340 */     this.pages.push(featureTypeStyleImpl);
/*      */   }
/*      */   
/*      */   protected Expression copy(Expression expression) {
/*  353 */     if (expression == null)
/*  353 */       return null; 
/*  354 */     return (Expression)expression.accept((ExpressionVisitor)this.copyFilter, this.ff);
/*      */   }
/*      */   
/*      */   protected Filter copy(Filter filter) {
/*  361 */     if (filter == null)
/*  361 */       return null; 
/*  362 */     return (Filter)filter.accept((FilterVisitor)this.copyFilter, this.ff);
/*      */   }
/*      */   
/*      */   protected Graphic copy(Graphic graphic) {
/*  371 */     if (graphic == null)
/*  371 */       return null; 
/*  373 */     graphic.accept(this);
/*  374 */     return (Graphic)this.pages.pop();
/*      */   }
/*      */   
/*      */   protected Fill copy(Fill fill) {
/*  383 */     if (fill == null)
/*  383 */       return null; 
/*  385 */     fill.accept(this);
/*  386 */     return (Fill)this.pages.pop();
/*      */   }
/*      */   
/*      */   protected float[] copy(float[] array) {
/*  395 */     if (array == null)
/*  395 */       return null; 
/*  397 */     float[] copy = new float[array.length];
/*  398 */     System.arraycopy(array, 0, copy, 0, array.length);
/*  399 */     return copy;
/*      */   }
/*      */   
/*      */   protected <K, V> Map<K, V> copy(Map<K, V> customProperties) {
/*  409 */     if (customProperties == null)
/*  409 */       return null; 
/*  410 */     return new HashMap<K, V>(customProperties);
/*      */   }
/*      */   
/*      */   protected Stroke copy(Stroke stroke) {
/*  419 */     if (stroke == null)
/*  419 */       return null; 
/*  420 */     stroke.accept(this);
/*  421 */     return (Stroke)this.pages.pop();
/*      */   }
/*      */   
/*      */   protected ShadedRelief copy(ShadedRelief shaded) {
/*  430 */     if (shaded == null)
/*  430 */       return null; 
/*  431 */     Expression reliefFactor = copy(shaded.getReliefFactor());
/*  432 */     ShadedRelief copy = this.sf.createShadedRelief(reliefFactor);
/*  433 */     copy.setBrightnessOnly(shaded.isBrightnessOnly());
/*  435 */     return copy;
/*      */   }
/*      */   
/*      */   protected Description copy(Description desc) {
/*  444 */     if (desc == null)
/*  444 */       return null; 
/*  445 */     DescriptionImpl copy = new DescriptionImpl(desc.getTitle(), desc.getAbstract());
/*  446 */     return (Description)copy;
/*      */   }
/*      */   
/*      */   protected ExternalGraphic copy(ExternalGraphic externalGraphic) {
/*  450 */     if (externalGraphic == null)
/*  450 */       return null; 
/*  451 */     externalGraphic.accept(this);
/*  452 */     return (ExternalGraphic)this.pages.pop();
/*      */   }
/*      */   
/*      */   protected Mark copy(Mark mark) {
/*  456 */     if (mark == null)
/*  456 */       return null; 
/*  457 */     mark.accept(this);
/*  458 */     return (Mark)this.pages.pop();
/*      */   }
/*      */   
/*      */   protected ColorMapEntry copy(ColorMapEntry entry) {
/*  462 */     if (entry == null)
/*  462 */       return null; 
/*  464 */     entry.accept(this);
/*  465 */     return (ColorMapEntry)this.pages.pop();
/*      */   }
/*      */   
/*      */   protected Symbolizer copy(Symbolizer symbolizer) {
/*  469 */     if (symbolizer == null)
/*  469 */       return null; 
/*  471 */     symbolizer.accept(this);
/*  472 */     return (Symbolizer)this.pages.pop();
/*      */   }
/*      */   
/*      */   protected OverlapBehavior copy(OverlapBehavior ob) {
/*  476 */     if (ob == null)
/*  476 */       return null; 
/*  478 */     ob.accept(this);
/*  479 */     return (OverlapBehavior)this.pages.pop();
/*      */   }
/*      */   
/*      */   protected ContrastEnhancement copy(ContrastEnhancement contrast) {
/*  483 */     if (contrast == null)
/*  483 */       return null; 
/*  485 */     ContrastEnhancement copy = this.sf.createContrastEnhancement();
/*  486 */     copy.setGammaValue(copy(contrast.getGammaValue()));
/*  487 */     copy.setMethod(contrast.getMethod());
/*  488 */     if (contrast.getType() != null)
/*  489 */       copy.setType(contrast.getType()); 
/*  491 */     return copy;
/*      */   }
/*      */   
/*      */   protected ColorMap copy(ColorMap colorMap) {
/*  495 */     if (colorMap == null)
/*  495 */       return null; 
/*  497 */     colorMap.accept(this);
/*  498 */     return (ColorMap)this.pages.pop();
/*      */   }
/*      */   
/*      */   protected SelectedChannelType[] copy(SelectedChannelType[] channels) {
/*  502 */     if (channels == null)
/*  502 */       return null; 
/*  504 */     SelectedChannelType[] copy = new SelectedChannelType[channels.length];
/*  505 */     for (int i = 0; i < channels.length; i++)
/*  506 */       copy[i] = copy(channels[i]); 
/*  508 */     return copy;
/*      */   }
/*      */   
/*      */   protected SelectedChannelType copy(SelectedChannelType selectedChannelType) {
/*  512 */     if (selectedChannelType == null)
/*  512 */       return null; 
/*  514 */     ContrastEnhancement enhancement = copy(selectedChannelType.getContrastEnhancement());
/*  515 */     String name = selectedChannelType.getChannelName();
/*  516 */     SelectedChannelType copy = this.sf.createSelectedChannelType(name, enhancement);
/*  518 */     return copy;
/*      */   }
/*      */   
/*      */   protected ChannelSelection copy(ChannelSelection channelSelection) {
/*  522 */     if (channelSelection == null)
/*  522 */       return null; 
/*  524 */     SelectedChannelType[] channels = copy(channelSelection.getSelectedChannels());
/*  525 */     ChannelSelection copy = this.sf.createChannelSelection(channels);
/*  526 */     copy.setGrayChannel(copy(channelSelection.getGrayChannel()));
/*  527 */     copy.setRGBChannels(copy(channelSelection.getRGBChannels()));
/*  528 */     return copy;
/*      */   }
/*      */   
/*      */   protected Font[] copy(Font[] fonts) {
/*  539 */     if (fonts == null)
/*  539 */       return null; 
/*  540 */     Font[] copy = new Font[fonts.length];
/*  541 */     for (int i = 0; i < fonts.length; i++)
/*  542 */       copy[i] = copy(fonts[i]); 
/*  544 */     return copy;
/*      */   }
/*      */   
/*      */   protected Font copy(Font font) {
/*  549 */     if (font == null)
/*  549 */       return font; 
/*  551 */     Expression fontFamily = copy(font.getFontFamily());
/*  552 */     Expression fontStyle = copy(font.getFontStyle());
/*  553 */     Expression fontWeight = copy(font.getFontWeight());
/*  554 */     Expression fontSize = copy(font.getFontSize());
/*  555 */     Font copy = this.sf.createFont(fontFamily, fontStyle, fontWeight, fontSize);
/*  556 */     return copy;
/*      */   }
/*      */   
/*      */   protected Halo copy(Halo halo) {
/*  565 */     if (halo == null)
/*  565 */       return null; 
/*  566 */     halo.accept(this);
/*  567 */     return (Halo)this.pages.pop();
/*      */   }
/*      */   
/*      */   protected Displacement copy(Displacement displacement) {
/*  576 */     if (displacement == null)
/*  576 */       return null; 
/*  577 */     displacement.accept(this);
/*  578 */     return (Displacement)this.pages.pop();
/*      */   }
/*      */   
/*      */   protected LabelPlacement copy(LabelPlacement placement) {
/*  582 */     if (placement == null)
/*  582 */       return null; 
/*  583 */     placement.accept(this);
/*  584 */     return (LabelPlacement)this.pages.pop();
/*      */   }
/*      */   
/*      */   protected Symbol copy(Symbol symbol) {
/*  588 */     if (symbol == null)
/*  588 */       return null; 
/*  589 */     symbol.accept(this);
/*  590 */     return (Symbol)this.pages.pop();
/*      */   }
/*      */   
/*      */   protected AnchorPoint copy(AnchorPoint anchorPoint) {
/*  599 */     if (anchorPoint == null)
/*  599 */       return null; 
/*  600 */     anchorPoint.accept(this);
/*  601 */     return (AnchorPoint)this.pages.pop();
/*      */   }
/*      */   
/*      */   public void visit(Fill fill) {
/*  605 */     Fill copy = this.sf.getDefaultFill();
/*  606 */     copy.setBackgroundColor(copy(fill.getBackgroundColor()));
/*  607 */     copy.setColor(copy(fill.getColor()));
/*  608 */     copy.setGraphicFill((Graphic)copy(fill.getGraphicFill()));
/*  609 */     copy.setOpacity(copy(fill.getOpacity()));
/*  611 */     if (this.STRICT && !copy.equals(fill))
/*  612 */       throw new IllegalStateException("Was unable to duplicate provided Fill:" + fill); 
/*  614 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(Stroke stroke) {
/*  618 */     Stroke copy = this.sf.getDefaultStroke();
/*  619 */     copy.setColor(copy(stroke.getColor()));
/*  620 */     copy.setDashArray(copy(stroke.getDashArray()));
/*  621 */     copy.setDashOffset(copy(stroke.getDashOffset()));
/*  622 */     copy.setGraphicFill((Graphic)copy(stroke.getGraphicFill()));
/*  623 */     copy.setGraphicStroke((Graphic)copy(stroke.getGraphicStroke()));
/*  624 */     copy.setLineCap(copy(stroke.getLineCap()));
/*  625 */     copy.setLineJoin(copy(stroke.getLineJoin()));
/*  626 */     copy.setOpacity(copy(stroke.getOpacity()));
/*  627 */     copy.setWidth(copy(stroke.getWidth()));
/*  629 */     if (this.STRICT && !copy.equals(stroke))
/*  630 */       throw new IllegalStateException("Was unable to duplicate provided Stroke:" + stroke); 
/*  632 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(Symbolizer sym) {
/*  636 */     if (sym instanceof RasterSymbolizer) {
/*  637 */       visit((RasterSymbolizer)sym);
/*  639 */     } else if (sym instanceof LineSymbolizer) {
/*  640 */       visit((LineSymbolizer)sym);
/*  642 */     } else if (sym instanceof PolygonSymbolizer) {
/*  643 */       visit((PolygonSymbolizer)sym);
/*  645 */     } else if (sym instanceof PointSymbolizer) {
/*  646 */       visit((PointSymbolizer)sym);
/*  648 */     } else if (sym instanceof TextSymbolizer) {
/*  649 */       visit((TextSymbolizer)sym);
/*      */     } else {
/*  652 */       throw new RuntimeException("visit(Symbolizer) unsupported");
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visit(PointSymbolizer ps) {
/*  657 */     PointSymbolizer copy = this.sf.getDefaultPointSymbolizer();
/*  659 */     copy.setGeometry(copy(ps.getGeometry()));
/*  661 */     copy.setUnitOfMeasure(ps.getUnitOfMeasure());
/*  662 */     copy.setGraphic((Graphic)copy(ps.getGraphic()));
/*  663 */     copy.getOptions().putAll(ps.getOptions());
/*  665 */     if (this.STRICT && 
/*  666 */       !copy.equals(ps))
/*  667 */       throw new IllegalStateException("Was unable to duplicate provided Graphic:" + ps); 
/*  670 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(LineSymbolizer line) {
/*  674 */     LineSymbolizer copy = this.sf.getDefaultLineSymbolizer();
/*  676 */     copy.setGeometry(copy(line.getGeometry()));
/*  678 */     copy.setUnitOfMeasure(line.getUnitOfMeasure());
/*  679 */     copy.setStroke((Stroke)copy(line.getStroke()));
/*  680 */     copy.getOptions().putAll(line.getOptions());
/*  681 */     copy.setPerpendicularOffset(line.getPerpendicularOffset());
/*  683 */     if (this.STRICT && !copy.equals(line))
/*  684 */       throw new IllegalStateException("Was unable to duplicate provided LineSymbolizer:" + line); 
/*  686 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(PolygonSymbolizer poly) {
/*  690 */     PolygonSymbolizer copy = this.sf.createPolygonSymbolizer();
/*  691 */     copy.setFill((Fill)copy(poly.getFill()));
/*  693 */     copy.setGeometry(copy(poly.getGeometry()));
/*  695 */     copy.setUnitOfMeasure(poly.getUnitOfMeasure());
/*  696 */     copy.setStroke((Stroke)copy(poly.getStroke()));
/*  697 */     copy.getOptions().putAll(poly.getOptions());
/*  699 */     if (this.STRICT && !copy.equals(poly))
/*  700 */       throw new IllegalStateException("Was unable to duplicate provided PolygonSymbolizer:" + poly); 
/*  702 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(TextSymbolizer text) {
/*  706 */     TextSymbolizer copy = this.sf.createTextSymbolizer();
/*  708 */     copy.setFill((Fill)copy(text.getFill()));
/*  709 */     copy.setFont((Font)copy(text.getFont()));
/*  711 */     copy.setGeometry(copy(text.getGeometry()));
/*  713 */     copy.setUnitOfMeasure(text.getUnitOfMeasure());
/*  714 */     copy.setHalo((Halo)copy(text.getHalo()));
/*  715 */     copy.setLabel(copy(text.getLabel()));
/*  716 */     copy.setLabelPlacement((LabelPlacement)copy(text.getLabelPlacement()));
/*  717 */     copy.setPriority(copy(text.getPriority()));
/*  718 */     copy.getOptions().putAll(text.getOptions());
/*  720 */     if (text instanceof TextSymbolizer2) {
/*  721 */       TextSymbolizer2 text2 = (TextSymbolizer2)text;
/*  722 */       TextSymbolizer2 copy2 = (TextSymbolizer2)copy;
/*  724 */       copy2.setGraphic(copy(text2.getGraphic()));
/*  725 */       copy2.setSnippet(copy(text2.getSnippet()));
/*  726 */       copy2.setFeatureDescription(copy(text2.getFeatureDescription()));
/*  727 */       copy2.setOtherText(copy(text2.getOtherText()));
/*      */     } 
/*  730 */     if (this.STRICT && !copy.equals(text))
/*  731 */       throw new IllegalStateException("Was unable to duplicate provided TextSymbolizer:" + text); 
/*  733 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(RasterSymbolizer raster) {
/*  737 */     RasterSymbolizer copy = this.sf.createRasterSymbolizer();
/*  738 */     copy.setChannelSelection((ChannelSelection)copy(raster.getChannelSelection()));
/*  739 */     copy.setColorMap((ColorMap)copy(raster.getColorMap()));
/*  740 */     copy.setContrastEnhancement((ContrastEnhancement)copy(raster.getContrastEnhancement()));
/*  742 */     copy.setGeometry(copy(raster.getGeometry()));
/*  744 */     copy.setUnitOfMeasure(raster.getUnitOfMeasure());
/*  745 */     copy.setImageOutline((Symbolizer)copy(raster.getImageOutline()));
/*  746 */     copy.setOpacity(copy(raster.getOpacity()));
/*  747 */     copy.setOverlap(copy(raster.getOverlap()));
/*  748 */     copy.setShadedRelief((ShadedRelief)copy(raster.getShadedRelief()));
/*  750 */     if (this.STRICT && !copy.equals(raster))
/*  751 */       throw new IllegalStateException("Was unable to duplicate provided raster:" + raster); 
/*  753 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(Graphic gr) {
/*  757 */     Graphic copy = null;
/*  759 */     Displacement displacementCopy = copy(gr.getDisplacement());
/*  760 */     ExternalGraphic[] externalGraphicsCopy = copy(gr.getExternalGraphics());
/*  761 */     Mark[] marksCopy = copy(gr.getMarks());
/*  762 */     Expression opacityCopy = copy(gr.getOpacity());
/*  763 */     Expression rotationCopy = copy(gr.getRotation());
/*  764 */     Expression sizeCopy = copy(gr.getSize());
/*  765 */     AnchorPoint anchorCopy = copy(gr.getAnchorPoint());
/*  770 */     copy = this.sf.createDefaultGraphic();
/*  772 */     copy.setDisplacement((Displacement)displacementCopy);
/*  773 */     copy.setAnchorPoint((AnchorPoint)anchorCopy);
/*  774 */     copy.setExternalGraphics(externalGraphicsCopy);
/*  775 */     copy.setMarks(marksCopy);
/*  776 */     copy.setOpacity(opacityCopy);
/*  777 */     copy.setRotation(rotationCopy);
/*  778 */     copy.setSize(sizeCopy);
/*  781 */     if (this.STRICT && 
/*  782 */       !copy.equals(gr))
/*  783 */       throw new IllegalStateException("Was unable to duplicate provided Graphic:" + gr); 
/*  786 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   private Mark[] copy(Mark[] marks) {
/*  790 */     if (marks == null)
/*  790 */       return null; 
/*  791 */     Mark[] copy = new Mark[marks.length];
/*  792 */     for (int i = 0; i < marks.length; i++)
/*  793 */       copy[i] = copy(marks[i]); 
/*  795 */     return copy;
/*      */   }
/*      */   
/*      */   private Symbol[] copy(Symbol[] symbols) {
/*  799 */     if (symbols == null)
/*  799 */       return null; 
/*  800 */     Symbol[] copy = new Symbol[symbols.length];
/*  801 */     for (int i = 0; i < symbols.length; i++)
/*  802 */       copy[i] = copy(symbols[i]); 
/*  804 */     return copy;
/*      */   }
/*      */   
/*      */   private ExternalGraphic[] copy(ExternalGraphic[] externalGraphics) {
/*  808 */     if (externalGraphics == null)
/*  808 */       return null; 
/*  809 */     ExternalGraphic[] copy = new ExternalGraphic[externalGraphics.length];
/*  810 */     for (int i = 0; i < externalGraphics.length; i++)
/*  811 */       copy[i] = copy(externalGraphics[i]); 
/*  813 */     return copy;
/*      */   }
/*      */   
/*      */   public void visit(Mark mark) {
/*  817 */     Mark copy = null;
/*  819 */     copy = this.sf.createMark();
/*  820 */     copy.setFill((Fill)copy(mark.getFill()));
/*  821 */     copy.setStroke((Stroke)copy(mark.getStroke()));
/*  822 */     copy.setWellKnownName(copy(mark.getWellKnownName()));
/*  824 */     if (this.STRICT && !copy.equals(mark))
/*  825 */       throw new IllegalStateException("Was unable to duplicate provided Mark:" + mark); 
/*  827 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(ExternalGraphic exgr) {
/*      */     ExternalGraphic copy;
/*  831 */     URL uri = null;
/*      */     try {
/*  833 */       uri = exgr.getLocation();
/*  835 */     } catch (MalformedURLException huh) {}
/*  838 */     String format = exgr.getFormat();
/*  839 */     Icon inlineContent = exgr.getInlineContent();
/*  841 */     if (inlineContent != null) {
/*  842 */       copy = this.sf.createExternalGraphic(inlineContent, format);
/*      */     } else {
/*  844 */       copy = this.sf.createExternalGraphic(uri, format);
/*      */     } 
/*  846 */     copy.setCustomProperties(copy(exgr.getCustomProperties()));
/*  848 */     if (this.STRICT && !copy.equals(exgr))
/*  849 */       throw new IllegalStateException("Was unable to duplicate provided ExternalGraphic:" + exgr); 
/*  851 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(PointPlacement pp) {
/*  855 */     PointPlacement copy = this.sf.getDefaultPointPlacement();
/*  856 */     copy.setAnchorPoint((AnchorPoint)copy(pp.getAnchorPoint()));
/*  857 */     copy.setDisplacement((Displacement)copy(pp.getDisplacement()));
/*  858 */     copy.setRotation(copy(pp.getRotation()));
/*  860 */     if (this.STRICT && !copy.equals(pp))
/*  861 */       throw new IllegalStateException("Was unable to duplicate provided PointPlacement:" + pp); 
/*  863 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(AnchorPoint ap) {
/*  867 */     Expression x = copy(ap.getAnchorPointX());
/*  868 */     Expression y = copy(ap.getAnchorPointY());
/*  869 */     AnchorPoint copy = this.sf.createAnchorPoint(x, y);
/*  871 */     if (this.STRICT && !copy.equals(ap))
/*  872 */       throw new IllegalStateException("Was unable to duplicate provided AnchorPoint:" + ap); 
/*  874 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(Displacement dis) {
/*  878 */     Expression x = copy(dis.getDisplacementX());
/*  879 */     Expression y = copy(dis.getDisplacementY());
/*  880 */     Displacement copy = this.sf.createDisplacement(x, y);
/*  882 */     if (this.STRICT && !copy.equals(dis))
/*  883 */       throw new IllegalStateException("Was unable to duplicate provided Displacement:" + dis); 
/*  885 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(LinePlacement lp) {
/*  889 */     Expression offset = copy(lp.getPerpendicularOffset());
/*  890 */     LinePlacement copy = this.sf.createLinePlacement(offset);
/*  891 */     copy.setAligned(lp.isAligned());
/*  892 */     copy.setGap(copy(lp.getGap()));
/*  893 */     copy.setGeneralized(lp.isGeneralizeLine());
/*  894 */     copy.setInitialGap(copy(lp.getInitialGap()));
/*  895 */     copy.setRepeated(lp.isRepeated());
/*  897 */     if (this.STRICT && !copy.equals(lp))
/*  898 */       throw new IllegalStateException("Was unable to duplicate provided LinePlacement:" + lp); 
/*  900 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(Halo halo) {
/*  904 */     Fill fill = copy(halo.getFill());
/*  905 */     Expression radius = copy(halo.getRadius());
/*  906 */     Halo copy = this.sf.createHalo(fill, radius);
/*  908 */     if (this.STRICT && !copy.equals(halo))
/*  909 */       throw new IllegalStateException("Was unable to duplicate provided raster:" + halo); 
/*  911 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(FeatureTypeConstraint ftc) {
/*  915 */     String typeName = ftc.getFeatureTypeName();
/*  916 */     Filter filter = copy(ftc.getFilter());
/*  917 */     Extent[] extents = copy(ftc.getExtents());
/*  918 */     FeatureTypeConstraint copy = this.sf.createFeatureTypeConstraint(typeName, filter, extents);
/*  920 */     if (this.STRICT && !copy.equals(ftc))
/*  921 */       throw new IllegalStateException("Was unable to duplicate provided FeatureTypeConstraint:" + ftc); 
/*  923 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   protected Extent[] copy(Extent[] extents) {
/*  927 */     if (extents == null)
/*  928 */       return null; 
/*  930 */     Extent[] copy = new Extent[extents.length];
/*  931 */     for (int i = 0; i < extents.length; i++)
/*  932 */       copy[i] = copy(extents[i]); 
/*  934 */     return copy;
/*      */   }
/*      */   
/*      */   protected Extent copy(Extent extent) {
/*  938 */     String name = extent.getName();
/*  939 */     String value = extent.getValue();
/*  940 */     Extent copy = this.sf.createExtent(name, value);
/*  941 */     return copy;
/*      */   }
/*      */   
/*      */   private OtherText copy(OtherText otherText) {
/*  946 */     if (otherText == null)
/*  946 */       return null; 
/*  950 */     OtherTextImpl copy = new OtherTextImpl();
/*  951 */     copy.setTarget(otherText.getTarget());
/*  952 */     copy.setText(copy(otherText.getText()));
/*  953 */     return (OtherText)copy;
/*      */   }
/*      */   
/*      */   public void visit(ColorMap colorMap) {
/*  958 */     ColorMap copy = this.sf.createColorMap();
/*  959 */     copy.setType(colorMap.getType());
/*  960 */     copy.setExtendedColors(colorMap.getExtendedColors());
/*  961 */     ColorMapEntry[] entries = colorMap.getColorMapEntries();
/*  962 */     if (entries != null)
/*  963 */       for (int i = 0; i < entries.length; i++) {
/*  964 */         ColorMapEntry entry = entries[i];
/*  965 */         copy.addColorMapEntry(copy(entry));
/*      */       }  
/*  968 */     if (this.STRICT && !copy.equals(colorMap))
/*  969 */       throw new IllegalStateException("Was unable to duplicate provided ColorMap:" + colorMap); 
/*  971 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(ColorMapEntry colorMapEntry) {
/*  975 */     ColorMapEntry copy = this.sf.createColorMapEntry();
/*  976 */     copy.setColor(copy(colorMapEntry.getColor()));
/*  977 */     copy.setLabel(colorMapEntry.getLabel());
/*  978 */     copy.setOpacity(copy(colorMapEntry.getOpacity()));
/*  979 */     copy.setQuantity(colorMapEntry.getQuantity());
/*  981 */     if (this.STRICT && !copy.equals(colorMapEntry))
/*  982 */       throw new IllegalStateException("Was unable to duplicate provided ColorMapEntry:" + colorMapEntry); 
/*  984 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(ContrastEnhancement contrastEnhancement) {
/*  988 */     ContrastEnhancement copy = this.sf.createContrastEnhancement();
/*  989 */     copy.setType(contrastEnhancement.getType());
/*  990 */     copy.setGammaValue(contrastEnhancement.getGammaValue());
/*  991 */     if (this.STRICT && !copy.equals(contrastEnhancement))
/*  992 */       throw new IllegalStateException("Was unable to duplicate provided contrastEnhancement:" + contrastEnhancement); 
/*  994 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(ImageOutline outline) {
/* 1000 */     Symbolizer symb = outline.getSymbolizer();
/* 1001 */     Symbolizer copySymb = copy(symb);
/* 1003 */     ImageOutline copy = this.sf.createImageOutline(copySymb);
/* 1004 */     copy.setSymbolizer(copySymb);
/* 1005 */     if (this.STRICT && !copy.equals(outline))
/* 1006 */       throw new IllegalStateException("Was unable to duplicate provided ImageOutline:" + outline); 
/* 1008 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(ChannelSelection cs) {
/* 1014 */     SelectedChannelType[] sct = copy(cs.getSelectedChannels());
/* 1015 */     ChannelSelection copy = this.sf.createChannelSelection(sct);
/* 1016 */     if (this.STRICT && !copy.equals(cs))
/* 1017 */       throw new IllegalStateException("Was unable to duplicate provided ChannelSelection:" + cs); 
/* 1019 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(OverlapBehavior ob) {
/* 1024 */     String behavior = (String)ob.getValue();
/* 1025 */     if (behavior.equalsIgnoreCase("AVERAGE")) {
/* 1026 */       this.pages.push("AVERAGE");
/* 1027 */     } else if (behavior.equalsIgnoreCase("EARLIEST_ON_TOP")) {
/* 1028 */       this.pages.push("EARLIEST_ON_TOP");
/* 1029 */     } else if (behavior.equalsIgnoreCase("LATEST_ON_TOP")) {
/* 1030 */       this.pages.push("LATEST_ON_TOP");
/* 1031 */     } else if (behavior.equalsIgnoreCase("RANDOM")) {
/* 1032 */       this.pages.push("RANDOM");
/*      */     } else {
/* 1034 */       throw new IllegalStateException("Was unable to duplicate provided OverlapBehavior:" + ob);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visit(SelectedChannelType sct) {
/* 1039 */     SelectedChannelType copy = this.sf.createSelectedChannelType(sct.getChannelName(), copy(sct.getContrastEnhancement()));
/* 1040 */     if (this.STRICT && !copy.equals(sct))
/* 1041 */       throw new IllegalStateException("Was unable to duplicate provided SelectedChannelType:" + sct); 
/* 1043 */     this.pages.push(copy);
/*      */   }
/*      */   
/*      */   public void visit(ShadedRelief sr) {
/* 1047 */     ShadedRelief copy = this.sf.createShadedRelief(copy(sr.getReliefFactor()));
/* 1048 */     copy.setBrightnessOnly(sr.isBrightnessOnly());
/* 1049 */     if (this.STRICT && !copy.equals(sr))
/* 1050 */       throw new IllegalStateException("Was unable to duplicate provided ShadedRelief:" + sr); 
/* 1052 */     this.pages.push(copy);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\visitor\DuplicatingStyleVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */