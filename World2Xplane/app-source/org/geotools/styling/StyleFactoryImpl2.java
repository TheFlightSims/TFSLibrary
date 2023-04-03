/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.measure.unit.Unit;
/*     */ import javax.swing.Icon;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.Id;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.PropertyName;
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
/*     */ public class StyleFactoryImpl2 implements StyleFactory {
/*     */   private FilterFactory2 filterFactory;
/*     */   
/*     */   public StyleFactoryImpl2() {
/*  70 */     this(CommonFactoryFinder.getFilterFactory2(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   protected StyleFactoryImpl2(FilterFactory2 factory) {
/*  74 */     this.filterFactory = factory;
/*     */   }
/*     */   
/*     */   public AnchorPoint anchorPoint(Expression x, Expression y) {
/*  78 */     return new AnchorPointImpl((FilterFactory)this.filterFactory, x, y);
/*     */   }
/*     */   
/*     */   public ChannelSelection channelSelection(SelectedChannelType gray) {
/*  83 */     ChannelSelectionImpl channelSelection = new ChannelSelectionImpl();
/*  84 */     channelSelection.setGrayChannel(gray);
/*  85 */     return channelSelection;
/*     */   }
/*     */   
/*     */   public ChannelSelectionImpl channelSelection(SelectedChannelType red, SelectedChannelType green, SelectedChannelType blue) {
/*  90 */     ChannelSelectionImpl channelSelection = new ChannelSelectionImpl();
/*  91 */     channelSelection.setRGBChannels(red, green, blue);
/*  92 */     return channelSelection;
/*     */   }
/*     */   
/*     */   public ColorMapImpl colorMap(Expression propertyName, Expression... mapping) {
/*  96 */     Expression[] arguments = new Expression[mapping.length + 2];
/*  97 */     arguments[0] = propertyName;
/*  98 */     for (int i = 0; i < mapping.length; i++)
/*  99 */       arguments[i + 1] = mapping[i]; 
/* 101 */     Function function = this.filterFactory.function("Categorize", arguments);
/* 102 */     ColorMapImpl colorMap = new ColorMapImpl(function);
/* 104 */     return colorMap;
/*     */   }
/*     */   
/*     */   public ColorReplacementImpl colorReplacement(Expression propertyName, Expression... mapping) {
/* 108 */     Expression[] arguments = new Expression[mapping.length + 2];
/* 109 */     arguments[0] = propertyName;
/* 110 */     for (int i = 0; i < mapping.length; i++)
/* 111 */       arguments[i + 1] = mapping[i]; 
/* 113 */     Function function = this.filterFactory.function("Recode", arguments);
/* 114 */     ColorReplacementImpl colorMap = new ColorReplacementImpl(function);
/* 116 */     return colorMap;
/*     */   }
/*     */   
/*     */   public ContrastEnhancementImpl contrastEnhancement(Expression gamma, ContrastMethod method) {
/* 121 */     return new ContrastEnhancementImpl(this.filterFactory, gamma, method);
/*     */   }
/*     */   
/*     */   public DescriptionImpl description(InternationalString title, InternationalString description) {
/* 125 */     return new DescriptionImpl(title, description);
/*     */   }
/*     */   
/*     */   public DisplacementImpl displacement(Expression dx, Expression dy) {
/* 129 */     return new DisplacementImpl(dx, dy);
/*     */   }
/*     */   
/*     */   public ExternalGraphicImpl externalGraphic(Icon inline, Collection<ColorReplacement> replacements) {
/* 134 */     ExternalGraphicImpl externalGraphic = new ExternalGraphicImpl(inline, replacements, null);
/* 135 */     return externalGraphic;
/*     */   }
/*     */   
/*     */   public ExternalGraphicImpl externalGraphic(OnLineResource resource, String format, Collection<ColorReplacement> replacements) {
/* 140 */     ExternalGraphicImpl externalGraphic = new ExternalGraphicImpl(null, replacements, resource);
/* 141 */     externalGraphic.setFormat(format);
/* 142 */     return externalGraphic;
/*     */   }
/*     */   
/*     */   public ExternalMarkImpl externalMark(Icon inline) {
/* 146 */     return new ExternalMarkImpl(inline);
/*     */   }
/*     */   
/*     */   public ExternalMarkImpl externalMark(OnLineResource resource, String format, int markIndex) {
/* 150 */     return new ExternalMarkImpl(resource, format, markIndex);
/*     */   }
/*     */   
/*     */   public FeatureTypeStyleImpl featureTypeStyle(String name, Description description, Id definedFor, Set<Name> featureTypeNames, Set<SemanticType> types, List<Rule> rules) {
/* 156 */     FeatureTypeStyleImpl featureTypeStyle = new FeatureTypeStyleImpl();
/* 157 */     featureTypeStyle.setName(name);
/* 159 */     if (description != null && description.getTitle() != null)
/* 160 */       featureTypeStyle.setTitle(description.getTitle().toString()); 
/* 162 */     if (description != null && description.getAbstract() != null)
/* 163 */       featureTypeStyle.setAbstract(description.getAbstract().toString()); 
/* 166 */     featureTypeStyle.featureTypeNames().addAll(featureTypeNames);
/* 167 */     featureTypeStyle.semanticTypeIdentifiers().addAll(types);
/* 169 */     for (Rule rule : rules) {
/* 170 */       if (rule instanceof RuleImpl) {
/* 171 */         featureTypeStyle.rules().add((RuleImpl)rule);
/*     */         continue;
/*     */       } 
/* 174 */       featureTypeStyle.rules().add(new RuleImpl(rule));
/*     */     } 
/* 177 */     return featureTypeStyle;
/*     */   }
/*     */   
/*     */   public FillImpl fill(GraphicFill graphicFill, Expression color, Expression opacity) {
/* 181 */     FillImpl fill = new FillImpl((FilterFactory)this.filterFactory);
/* 182 */     fill.setGraphicFill((Graphic)graphicFill);
/* 183 */     fill.setColor(color);
/* 184 */     fill.setOpacity(opacity);
/* 185 */     return fill;
/*     */   }
/*     */   
/*     */   public FontImpl font(List<Expression> family, Expression style, Expression weight, Expression size) {
/* 190 */     FontImpl font = new FontImpl();
/* 191 */     font.getFamily().addAll(family);
/* 192 */     font.setStyle(style);
/* 193 */     font.setWeight(weight);
/* 194 */     font.setSize(size);
/* 196 */     return font;
/*     */   }
/*     */   
/*     */   public GraphicImpl graphic(List<GraphicalSymbol> symbols, Expression opacity, Expression size, Expression rotation, AnchorPoint anchor, Displacement disp) {
/* 203 */     GraphicImpl graphic = new GraphicImpl((FilterFactory)this.filterFactory);
/* 204 */     if (symbols != null)
/* 205 */       for (GraphicalSymbol graphicalSymbol : symbols) {
/* 206 */         if (graphicalSymbol instanceof ExternalGraphic) {
/* 207 */           graphic.graphicalSymbols().add(ExternalGraphicImpl.cast(graphicalSymbol));
/*     */           continue;
/*     */         } 
/* 209 */         if (graphicalSymbol instanceof Mark)
/* 210 */           graphic.graphicalSymbols().add(MarkImpl.cast(graphicalSymbol)); 
/*     */       }  
/* 214 */     graphic.setOpacity(opacity);
/* 215 */     graphic.setSize(size);
/* 216 */     graphic.setRotation(rotation);
/* 217 */     graphic.setAnchorPoint(anchor);
/* 218 */     graphic.setDisplacement(disp);
/* 219 */     return graphic;
/*     */   }
/*     */   
/*     */   public GraphicImpl graphicFill(List<GraphicalSymbol> symbols, Expression opacity, Expression size, Expression rotation, AnchorPoint anchorPoint, Displacement displacement) {
/* 225 */     GraphicImpl graphicFill = new GraphicImpl((FilterFactory)this.filterFactory);
/* 226 */     if (symbols != null)
/* 227 */       for (GraphicalSymbol graphicalSymbol : symbols) {
/* 228 */         if (graphicalSymbol instanceof ExternalGraphic) {
/* 229 */           graphicFill.graphicalSymbols().add(ExternalGraphicImpl.cast(graphicalSymbol));
/*     */           continue;
/*     */         } 
/* 231 */         if (graphicalSymbol instanceof Mark)
/* 232 */           graphicFill.graphicalSymbols().add(MarkImpl.cast(graphicalSymbol)); 
/*     */       }  
/* 236 */     graphicFill.setOpacity(opacity);
/* 237 */     graphicFill.setSize(size);
/* 238 */     graphicFill.setRotation(rotation);
/* 239 */     graphicFill.setAnchorPoint(anchorPoint);
/* 240 */     graphicFill.setDisplacement(displacement);
/* 242 */     return graphicFill;
/*     */   }
/*     */   
/*     */   public GraphicImpl graphicLegend(List<GraphicalSymbol> symbols, Expression opacity, Expression size, Expression rotation, AnchorPoint anchorPoint, Displacement displacement) {
/* 247 */     GraphicImpl graphicLegend = new GraphicImpl((FilterFactory)this.filterFactory);
/* 248 */     if (symbols != null)
/* 249 */       for (GraphicalSymbol graphicalSymbol : symbols) {
/* 250 */         if (graphicalSymbol instanceof ExternalGraphic) {
/* 251 */           graphicLegend.graphicalSymbols().add(ExternalGraphicImpl.cast(graphicalSymbol));
/*     */           continue;
/*     */         } 
/* 253 */         if (graphicalSymbol instanceof Mark)
/* 254 */           graphicLegend.graphicalSymbols().add(MarkImpl.cast(graphicalSymbol)); 
/*     */       }  
/* 258 */     graphicLegend.setOpacity(opacity);
/* 259 */     graphicLegend.setSize(size);
/* 260 */     graphicLegend.setRotation(rotation);
/* 261 */     graphicLegend.setAnchorPoint(anchorPoint);
/* 262 */     graphicLegend.setDisplacement(displacement);
/* 264 */     return graphicLegend;
/*     */   }
/*     */   
/*     */   public GraphicImpl graphicStroke(List<GraphicalSymbol> symbols, Expression opacity, Expression size, Expression rotation, AnchorPoint anchorPoint, Displacement displacement, Expression initialGap, Expression gap) {
/* 269 */     GraphicImpl graphicStroke = new GraphicImpl((FilterFactory)this.filterFactory);
/* 270 */     if (symbols != null)
/* 271 */       for (GraphicalSymbol graphicalSymbol : symbols) {
/* 272 */         if (graphicalSymbol instanceof ExternalGraphic) {
/* 273 */           graphicStroke.graphicalSymbols().add(ExternalGraphicImpl.cast(graphicalSymbol));
/*     */           continue;
/*     */         } 
/* 275 */         if (graphicalSymbol instanceof Mark)
/* 276 */           graphicStroke.graphicalSymbols().add(MarkImpl.cast(graphicalSymbol)); 
/*     */       }  
/* 280 */     graphicStroke.setOpacity(opacity);
/* 281 */     graphicStroke.setSize(size);
/* 282 */     graphicStroke.setRotation(rotation);
/* 283 */     graphicStroke.setAnchorPoint(anchorPoint);
/* 284 */     graphicStroke.setDisplacement(displacement);
/* 285 */     graphicStroke.setInitialGap(initialGap);
/* 286 */     graphicStroke.setGap(gap);
/* 288 */     return graphicStroke;
/*     */   }
/*     */   
/*     */   public HaloImpl halo(Fill fill, Expression radius) {
/* 291 */     HaloImpl halo = new HaloImpl();
/* 292 */     halo.setFill(fill);
/* 293 */     halo.setRadius(radius);
/* 295 */     return halo;
/*     */   }
/*     */   
/*     */   public LinePlacementImpl linePlacement(Expression offset, Expression initialGap, Expression gap, boolean repeated, boolean aligned, boolean generalizedLine) {
/* 300 */     LinePlacementImpl placement = new LinePlacementImpl((FilterFactory)this.filterFactory);
/* 301 */     placement.setPerpendicularOffset(offset);
/* 302 */     placement.setInitialGap(initialGap);
/* 303 */     placement.setGap(gap);
/* 304 */     placement.setRepeated(repeated);
/* 305 */     placement.setAligned(aligned);
/* 306 */     placement.setGeneralized(generalizedLine);
/* 308 */     return placement;
/*     */   }
/*     */   
/*     */   public LineSymbolizerImpl lineSymbolizer(String name, Expression geometry, Description description, Unit<?> unit, Stroke stroke, Expression offset) {
/* 315 */     LineSymbolizerImpl copy = new LineSymbolizerImpl();
/* 316 */     copy.setDescription(description);
/* 317 */     copy.setGeometry(geometry);
/* 318 */     copy.setName(name);
/* 319 */     copy.setPerpendicularOffset(offset);
/* 320 */     copy.setStroke(stroke);
/* 321 */     copy.setUnitOfMeasure((Unit)unit);
/* 322 */     return copy;
/*     */   }
/*     */   
/*     */   public MarkImpl mark(Expression wellKnownName, Fill fill, Stroke stroke) {
/* 327 */     MarkImpl mark = new MarkImpl((FilterFactory)this.filterFactory, null);
/* 328 */     mark.setWellKnownName(wellKnownName);
/* 329 */     mark.setFill(fill);
/* 330 */     mark.setStroke(stroke);
/* 332 */     return mark;
/*     */   }
/*     */   
/*     */   public MarkImpl mark(ExternalMark externalMark, Fill fill, Stroke stroke) {
/* 337 */     MarkImpl mark = new MarkImpl();
/* 338 */     mark.setExternalMark(externalMark);
/* 339 */     mark.setFill(fill);
/* 340 */     mark.setStroke(stroke);
/* 342 */     return mark;
/*     */   }
/*     */   
/*     */   public PointPlacementImpl pointPlacement(AnchorPoint anchor, Displacement displacement, Expression rotation) {
/* 347 */     PointPlacementImpl pointPlacment = new PointPlacementImpl((FilterFactory)this.filterFactory);
/* 348 */     pointPlacment.setAnchorPoint(anchor);
/* 349 */     pointPlacment.setDisplacement(displacement);
/* 350 */     pointPlacment.setRotation(rotation);
/* 351 */     return pointPlacment;
/*     */   }
/*     */   
/*     */   public PointSymbolizerImpl pointSymbolizer(String name, Expression geometry, Description description, Unit<?> unit, Graphic graphic) {
/* 357 */     PointSymbolizerImpl copy = new PointSymbolizerImpl();
/* 358 */     copy.setDescription(description);
/* 359 */     copy.setGeometryPropertyName(((PropertyName)geometry).getPropertyName());
/* 360 */     copy.setGraphic(graphic);
/* 361 */     copy.setName(name);
/* 362 */     copy.setUnitOfMeasure((Unit)unit);
/* 363 */     return copy;
/*     */   }
/*     */   
/*     */   public PolygonSymbolizerImpl polygonSymbolizer(String name, Expression geometry, Description description, Unit<?> unit, Stroke stroke, Fill fill, Displacement displacement, Expression offset) {
/* 370 */     PolygonSymbolizerImpl polygonSymbolizer = new PolygonSymbolizerImpl();
/* 371 */     polygonSymbolizer.setStroke(stroke);
/* 372 */     polygonSymbolizer.setDescription(description);
/* 373 */     polygonSymbolizer.setDisplacement(displacement);
/* 374 */     polygonSymbolizer.setFill(fill);
/* 375 */     polygonSymbolizer.setGeometryPropertyName(((PropertyName)geometry).getPropertyName());
/* 376 */     polygonSymbolizer.setName(name);
/* 377 */     polygonSymbolizer.setPerpendicularOffset(offset);
/* 378 */     polygonSymbolizer.setUnitOfMeasure((Unit)unit);
/* 379 */     return polygonSymbolizer;
/*     */   }
/*     */   
/*     */   public RasterSymbolizerImpl rasterSymbolizer(String name, Expression geometry, Description description, Unit<?> unit, Expression opacity, ChannelSelection channelSelection, OverlapBehavior overlapsBehaviour, ColorMap colorMap, ContrastEnhancement contrast, ShadedRelief shaded, Symbolizer outline) {
/* 388 */     RasterSymbolizerImpl rasterSymbolizer = new RasterSymbolizerImpl((FilterFactory)this.filterFactory);
/* 389 */     rasterSymbolizer.setChannelSelection(channelSelection);
/* 390 */     rasterSymbolizer.setColorMap(colorMap);
/* 391 */     rasterSymbolizer.setContrastEnhancement(contrast);
/* 392 */     rasterSymbolizer.setDescription(description);
/* 393 */     if (geometry != null) {
/* 394 */       rasterSymbolizer.setGeometryPropertyName(((PropertyName)geometry).getPropertyName());
/*     */     } else {
/* 397 */       rasterSymbolizer.setGeometryPropertyName(null);
/*     */     } 
/* 399 */     rasterSymbolizer.setImageOutline(outline);
/* 400 */     rasterSymbolizer.setName(name);
/* 401 */     rasterSymbolizer.setOpacity(opacity);
/* 402 */     rasterSymbolizer.setOverlapBehavior(overlapsBehaviour);
/* 403 */     rasterSymbolizer.setShadedRelief(shaded);
/* 404 */     rasterSymbolizer.setUnitOfMeasure((Unit)unit);
/* 405 */     return rasterSymbolizer;
/*     */   }
/*     */   
/*     */   public ExtensionSymbolizer extensionSymbolizer(String name, String propertyName, Description description, Unit<?> unit, String extensionName, Map<String, Expression> parameters) {
/* 414 */     VendorSymbolizerImpl extension = new VendorSymbolizerImpl();
/* 415 */     extension.setName(name);
/* 416 */     extension.setGeometryPropertyName(propertyName);
/* 417 */     extension.setDescription(description);
/* 418 */     extension.setUnitOfMeasure((Unit)unit);
/* 419 */     extension.setExtensionName(extensionName);
/* 420 */     extension.getParameters().putAll(parameters);
/* 422 */     return extension;
/*     */   }
/*     */   
/*     */   static Symbolizer cast(Symbolizer symbolizer) {
/* 426 */     if (symbolizer instanceof PolygonSymbolizer)
/* 427 */       return PolygonSymbolizerImpl.cast(symbolizer); 
/* 429 */     if (symbolizer instanceof LineSymbolizer)
/* 430 */       return LineSymbolizerImpl.cast(symbolizer); 
/* 432 */     if (symbolizer instanceof PointSymbolizer)
/* 433 */       return PointSymbolizerImpl.cast(symbolizer); 
/* 435 */     if (symbolizer instanceof RasterSymbolizer)
/* 436 */       return RasterSymbolizerImpl.cast(symbolizer); 
/* 438 */     if (symbolizer instanceof TextSymbolizer)
/* 439 */       return TextSymbolizerImpl.cast(symbolizer); 
/* 441 */     if (symbolizer instanceof ExtensionSymbolizer) {
/* 443 */       ExtensionSymbolizer extensionSymbolizer = (ExtensionSymbolizer)symbolizer;
/* 444 */       String name = extensionSymbolizer.getExtensionName();
/* 447 */       return null;
/*     */     } 
/* 449 */     return null;
/*     */   }
/*     */   
/*     */   public RuleImpl rule(String name, Description description, GraphicLegend legend, double min, double max, List<Symbolizer> symbolizers, Filter filter) {
/* 453 */     RuleImpl rule = new RuleImpl();
/* 454 */     rule.setName(name);
/* 455 */     rule.setDescription(description);
/* 456 */     rule.setLegend(legend);
/* 457 */     rule.setMinScaleDenominator(min);
/* 458 */     rule.setMaxScaleDenominator(max);
/* 459 */     if (symbolizers != null)
/* 460 */       for (Symbolizer symbolizer : symbolizers)
/* 461 */         rule.symbolizers().add(cast(symbolizer));  
/* 464 */     if (filter != null) {
/* 465 */       rule.setFilter(filter);
/* 466 */       rule.setElseFilter(false);
/*     */     } else {
/* 469 */       rule.setElseFilter(true);
/*     */     } 
/* 471 */     return rule;
/*     */   }
/*     */   
/*     */   public SelectedChannelTypeImpl selectedChannelType(String channelName, ContrastEnhancement contrastEnhancement) {
/* 477 */     SelectedChannelTypeImpl selectedChannelType = new SelectedChannelTypeImpl((FilterFactory)this.filterFactory);
/* 478 */     selectedChannelType.setChannelName(channelName);
/* 479 */     selectedChannelType.setContrastEnhancement(contrastEnhancement);
/* 480 */     return selectedChannelType;
/*     */   }
/*     */   
/*     */   public ShadedReliefImpl shadedRelief(Expression reliefFactor, boolean brightnessOnly) {
/* 485 */     ShadedReliefImpl shadedRelief = new ShadedReliefImpl((FilterFactory)this.filterFactory);
/* 486 */     shadedRelief.setReliefFactor(reliefFactor);
/* 487 */     shadedRelief.setBrightnessOnly(brightnessOnly);
/* 488 */     return shadedRelief;
/*     */   }
/*     */   
/*     */   public StrokeImpl stroke(Expression color, Expression opacity, Expression width, Expression join, Expression cap, float[] dashes, Expression offset) {
/* 493 */     StrokeImpl stroke = new StrokeImpl((FilterFactory)this.filterFactory);
/* 494 */     stroke.setColor(color);
/* 495 */     stroke.setOpacity(opacity);
/* 496 */     stroke.setWidth(width);
/* 497 */     stroke.setLineJoin(join);
/* 498 */     stroke.setLineCap(cap);
/* 499 */     stroke.setDashArray(dashes);
/* 500 */     stroke.setDashOffset(offset);
/* 501 */     return stroke;
/*     */   }
/*     */   
/*     */   public StrokeImpl stroke(GraphicFill fill, Expression color, Expression opacity, Expression width, Expression join, Expression cap, float[] dashes, Expression offset) {
/* 506 */     StrokeImpl stroke = new StrokeImpl((FilterFactory)this.filterFactory);
/* 507 */     stroke.setGraphicFill((Graphic)fill);
/* 508 */     stroke.setColor(color);
/* 509 */     stroke.setOpacity(opacity);
/* 510 */     stroke.setWidth(width);
/* 511 */     stroke.setLineJoin(join);
/* 512 */     stroke.setLineCap(cap);
/* 513 */     stroke.setDashArray(dashes);
/* 514 */     stroke.setDashOffset(offset);
/* 515 */     return stroke;
/*     */   }
/*     */   
/*     */   public StrokeImpl stroke(GraphicStroke stroke, Expression color, Expression opacity, Expression width, Expression join, Expression cap, float[] dashes, Expression offset) {
/* 521 */     StrokeImpl s = new StrokeImpl((FilterFactory)this.filterFactory);
/* 522 */     s.setColor(color);
/* 523 */     s.setWidth(width);
/* 524 */     s.setOpacity(opacity);
/* 525 */     s.setLineJoin(join);
/* 526 */     s.setLineCap(cap);
/* 527 */     s.setDashArray(dashes);
/* 528 */     s.setDashOffset(offset);
/* 529 */     s.setGraphicStroke(GraphicImpl.cast((Graphic)stroke));
/* 531 */     return s;
/*     */   }
/*     */   
/*     */   public StyleImpl style(String name, Description description, boolean isDefault, List<FeatureTypeStyle> featureTypeStyles, Symbolizer defaultSymbolizer) {
/* 537 */     StyleImpl style = new StyleImpl();
/* 538 */     style.setName(name);
/* 539 */     style.setDescription(description);
/* 540 */     style.setDefault(isDefault);
/* 541 */     if (featureTypeStyles != null)
/* 542 */       for (FeatureTypeStyle featureTypeStyle : featureTypeStyles)
/* 543 */         style.featureTypeStyles().add(FeatureTypeStyleImpl.cast(featureTypeStyle));  
/* 546 */     style.setDefaultSpecification(cast(defaultSymbolizer));
/* 547 */     return style;
/*     */   }
/*     */   
/*     */   public TextSymbolizerImpl textSymbolizer(String name, Expression geometry, Description description, Unit<?> unit, Expression label, Font font, LabelPlacement placement, Halo halo, Fill fill) {
/* 556 */     TextSymbolizerImpl tSymb = new TextSymbolizerImpl((FilterFactory)this.filterFactory);
/* 557 */     tSymb.setName(name);
/* 558 */     tSymb.setFill(fill);
/* 559 */     tSymb.setUnitOfMeasure((Unit)unit);
/* 560 */     tSymb.setFont(font);
/* 561 */     tSymb.setGeometryPropertyName(((PropertyName)geometry).getPropertyName());
/* 562 */     tSymb.setHalo(halo);
/* 563 */     tSymb.setLabel(label);
/* 564 */     tSymb.setLabelPlacement(placement);
/* 566 */     tSymb.setDescription(description);
/* 567 */     return tSymb;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\StyleFactoryImpl2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */