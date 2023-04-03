/*      */ package org.geotools.styling;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URI;
/*      */ import java.net.URL;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.logging.Logger;
/*      */ import javax.measure.quantity.Length;
/*      */ import javax.measure.unit.Unit;
/*      */ import org.geotools.data.DataStore;
/*      */ import org.geotools.data.simple.SimpleFeatureCollection;
/*      */ import org.geotools.data.simple.SimpleFeatureSource;
/*      */ import org.geotools.factory.CommonFactoryFinder;
/*      */ import org.geotools.filter.FilterTransformer;
/*      */ import org.geotools.gml.producer.FeatureTransformer;
/*      */ import org.geotools.referencing.CRS;
/*      */ import org.geotools.util.GrowableInternationalString;
/*      */ import org.geotools.util.logging.Logging;
/*      */ import org.geotools.xml.transform.TransformerBase;
/*      */ import org.geotools.xml.transform.Translator;
/*      */ import org.opengis.feature.simple.SimpleFeatureType;
/*      */ import org.opengis.feature.type.FeatureType;
/*      */ import org.opengis.feature.type.Name;
/*      */ import org.opengis.filter.Filter;
/*      */ import org.opengis.filter.FilterFactory;
/*      */ import org.opengis.filter.FilterFactory2;
/*      */ import org.opengis.filter.expression.Expression;
/*      */ import org.opengis.filter.expression.Function;
/*      */ import org.opengis.filter.expression.Literal;
/*      */ import org.opengis.filter.expression.PropertyName;
/*      */ import org.opengis.referencing.ReferenceIdentifier;
/*      */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*      */ import org.opengis.style.ContrastMethod;
/*      */ import org.opengis.style.SemanticType;
/*      */ import org.opengis.util.InternationalString;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.helpers.AttributesImpl;
/*      */ 
/*      */ public class SLDTransformer extends TransformerBase {
/*   75 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.styling");
/*      */   
/*      */   static final String XLINK_NAMESPACE = "http://www.w3.org/1999/xlink";
/*      */   
/*   79 */   static final FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/*      */   
/*      */   private final Map uri2prefix;
/*      */   
/*      */   public SLDTransformer() {
/*   94 */     this((Map)null);
/*      */   }
/*      */   
/*      */   public SLDTransformer(Map nsBindings) {
/*  106 */     if (nsBindings == null || nsBindings.isEmpty()) {
/*  107 */       this.uri2prefix = new HashMap<Object, Object>();
/*      */     } else {
/*  109 */       this.uri2prefix = new HashMap<Object, Object>(nsBindings.size());
/*  110 */       int count = 0;
/*  111 */       for (Iterator<Map.Entry> it = nsBindings.entrySet().iterator(); it.hasNext(); ) {
/*  112 */         Map.Entry e = it.next();
/*  113 */         URI uri = (URI)e.getKey();
/*  114 */         String prefix = (String)e.getValue();
/*  115 */         if (uri != null && prefix != null) {
/*  116 */           this.uri2prefix.put(uri, prefix.trim());
/*  117 */           count++;
/*      */         } 
/*      */       } 
/*  120 */       LOGGER.info("Added [" + count + "] namespace entries resulting in [" + this.uri2prefix.size() + "] distinct entries");
/*      */     } 
/*      */   }
/*      */   
/*      */   public Translator createTranslator(ContentHandler handler) {
/*  127 */     SLDTranslator sLDTranslator = new SLDTranslator(handler);
/*  129 */     if (!this.uri2prefix.isEmpty())
/*  130 */       for (Iterator<Map.Entry> it = this.uri2prefix.entrySet().iterator(); it.hasNext(); ) {
/*  131 */         Map.Entry e = it.next();
/*  132 */         URI uri = (URI)e.getKey();
/*  133 */         if (uri != null) {
/*  134 */           String prefix = (String)e.getValue();
/*  138 */           String uriStr = String.valueOf(uri);
/*  139 */           sLDTranslator.getNamespaceSupport().declarePrefix(prefix, uriStr);
/*      */         } 
/*      */       }  
/*  143 */     return (Translator)sLDTranslator;
/*      */   }
/*      */   
/*      */   public static final void main(String[] args) throws Exception {
/*  156 */     URL url = (new File(args[0])).toURI().toURL();
/*  157 */     SLDParser s = new SLDParser(CommonFactoryFinder.getStyleFactory(null), url);
/*  158 */     SLDTransformer transformer = new SLDTransformer();
/*  159 */     transformer.setIndentation(4);
/*  160 */     transformer.transform(s.readXML(), new FileOutputStream(System.getProperty("java.io.tmpdir") + "/junk.eraseme"));
/*      */   }
/*      */   
/*      */   static class SLDTranslator extends TransformerBase.TranslatorSupport implements StyleVisitor {
/*      */     FilterTransformer.FilterTranslator filterTranslator;
/*      */     
/*      */     public SLDTranslator(ContentHandler handler) {
/*  187 */       this(handler, "sld", "http://www.opengis.net/sld");
/*      */     }
/*      */     
/*      */     public SLDTranslator(ContentHandler handler, String prefix, String uri) {
/*  195 */       super(handler, prefix, uri);
/*  196 */       this.filterTranslator = new FilterTransformer.FilterTranslator(handler);
/*  197 */       addNamespaceDeclarations((TransformerBase.TranslatorSupport)this.filterTranslator);
/*      */     }
/*      */     
/*      */     boolean isNull(Expression expr) {
/*  201 */       if (expr == null)
/*  201 */         return true; 
/*  202 */       if (expr == Expression.NIL)
/*  202 */         return true; 
/*  203 */       if (expr instanceof Literal) {
/*  204 */         Literal literal = (Literal)expr;
/*  205 */         return (literal.getValue() == null);
/*      */       } 
/*  207 */       return false;
/*      */     }
/*      */     
/*      */     boolean isDefault(Expression expr, Object defaultValue) {
/*  211 */       if (defaultValue == null)
/*  211 */         return isNull(expr); 
/*  213 */       if (expr == null)
/*  213 */         return false; 
/*  214 */       if (expr == Expression.NIL)
/*  214 */         return false; 
/*  215 */       if (expr instanceof Literal) {
/*  216 */         Literal literal = (Literal)expr;
/*  217 */         if (defaultValue.equals(literal.getValue()))
/*  218 */           return true; 
/*  220 */         if (defaultValue.toString().equals(literal.getValue().toString()))
/*  221 */           return true; 
/*      */       } 
/*  224 */       return false;
/*      */     }
/*      */     
/*      */     void element(String element, Expression expr) {
/*  233 */       element(element, expr, (Object)null);
/*      */     }
/*      */     
/*      */     void element(String element, InternationalString intString) {
/*  242 */       if (intString instanceof GrowableInternationalString) {
/*  243 */         GrowableInternationalString growable = (GrowableInternationalString)intString;
/*  244 */         if (growable.getLocales().isEmpty()) {
/*  245 */           element(element, intString.toString());
/*      */         } else {
/*  247 */           start(element);
/*  248 */           chars(intString.toString());
/*  249 */           for (Locale locale : growable.getLocales()) {
/*  250 */             if (locale != null) {
/*  251 */               AttributesImpl atts = new AttributesImpl();
/*  252 */               atts.addAttribute("", "lang", "lang", "", locale.toString());
/*  253 */               element("Localized", growable.toString(locale), atts);
/*      */             } 
/*      */           } 
/*  256 */           end(element);
/*      */         } 
/*      */       } else {
/*  259 */         element(element, intString.toString());
/*      */       } 
/*      */     }
/*      */     
/*      */     void element(String element, Expression expr, Object defaultValue) {
/*  269 */       element(element, expr, defaultValue, (AttributesImpl)null);
/*      */     }
/*      */     
/*      */     void element(String element, Expression expr, Object defaultValue, AttributesImpl atts) {
/*  273 */       if (expr == null || expr == Expression.NIL)
/*      */         return; 
/*  276 */       if (expr instanceof Literal) {
/*  277 */         if (defaultValue != null) {
/*  278 */           Object value = expr.evaluate(null, defaultValue.getClass());
/*  279 */           if (value != null && !value.equals(defaultValue))
/*  280 */             element(element, value.toString(), atts); 
/*      */         } else {
/*  283 */           String value = (String)expr.evaluate(null, String.class);
/*  284 */           if (value != null)
/*  285 */             element(element, value, atts); 
/*      */         } 
/*      */         return;
/*      */       } 
/*  291 */       start(element, atts);
/*  292 */       this.filterTranslator.encode(expr);
/*  293 */       end(element);
/*      */     }
/*      */     
/*      */     void labelContent(Expression expr) {
/*  297 */       if (expr instanceof Literal) {
/*  298 */         Literal literalLabel = (Literal)expr;
/*  299 */         String label = (String)literalLabel.evaluate(null, String.class);
/*  300 */         if (label != null)
/*  302 */           if (label.matches("^\\s+.*$|^.*\\s+$|^.*\\s{2,}.*$")) {
/*  303 */             cdata(label);
/*      */           } else {
/*  305 */             chars(label);
/*      */           }  
/*  308 */       } else if (expr instanceof Function && ("strConcat".equals(((Function)expr).getName()) || "concat".equals(((Function)expr).getName()))) {
/*  311 */         List<Expression> parameters = ((Function)expr).getParameters();
/*  312 */         for (Expression parameter : parameters)
/*  313 */           labelContent(parameter); 
/*      */       } else {
/*  316 */         this.filterTranslator.encode(expr);
/*      */       } 
/*      */     }
/*      */     
/*      */     void elementLiteral(String element, Expression e, String defaultValue) {
/*  330 */       if (e == null || e == Expression.NIL)
/*      */         return; 
/*  332 */       String value = (String)e.evaluate(null, String.class);
/*  333 */       if (defaultValue == null || !defaultValue.equals(value)) {
/*  334 */         start(element);
/*  335 */         start(value);
/*  336 */         end(value);
/*  337 */         end(element);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void visit(PointPlacement pp) {
/*  342 */       start("LabelPlacement");
/*  343 */       start("PointPlacement");
/*  344 */       pp.getAnchorPoint().accept(this);
/*  346 */       visit(pp.getDisplacement());
/*  348 */       encodeValue("Rotation", (Attributes)null, pp.getRotation(), Double.valueOf(0.0D));
/*  349 */       end("PointPlacement");
/*  350 */       end("LabelPlacement");
/*      */     }
/*      */     
/*      */     public void visit(Stroke stroke) {
/*  354 */       start("Stroke");
/*  356 */       if (stroke.getGraphicFill() != null) {
/*  357 */         start("GraphicFill");
/*  358 */         stroke.getGraphicFill().accept(this);
/*  359 */         end("GraphicFill");
/*      */       } 
/*  362 */       if (stroke.getGraphicStroke() != null) {
/*  363 */         start("GraphicStroke");
/*  364 */         stroke.getGraphicStroke().accept(this);
/*  365 */         end("GraphicStroke");
/*      */       } 
/*  368 */       encodeCssParam("stroke", stroke.getColor(), Color.BLACK);
/*  369 */       encodeCssParam("stroke-linecap", stroke.getLineCap(), "butt");
/*  370 */       encodeCssParam("stroke-linejoin", stroke.getLineJoin(), "miter");
/*  371 */       encodeCssParam("stroke-opacity", stroke.getOpacity(), Double.valueOf(1.0D));
/*  372 */       encodeCssParam("stroke-width", stroke.getWidth(), Double.valueOf(1.0D));
/*  373 */       encodeCssParam("stroke-dashoffset", stroke.getDashOffset(), Double.valueOf(0.0D));
/*  375 */       float[] dash = stroke.getDashArray();
/*  377 */       if (dash != null) {
/*  378 */         StringBuffer sb = new StringBuffer();
/*  380 */         for (int i = 0; i < dash.length; i++) {
/*  381 */           sb.append(dash[i]);
/*  382 */           if (i < dash.length - 1)
/*  383 */             sb.append(" "); 
/*      */         } 
/*  387 */         encodeCssParam("stroke-dasharray", (Expression)SLDTransformer.ff.literal(sb.toString()));
/*      */       } 
/*  390 */       end("Stroke");
/*      */     }
/*      */     
/*      */     public void visit(LinePlacement lp) {
/*  394 */       start("LabelPlacement");
/*  395 */       start("LinePlacement");
/*  396 */       element("PerpendicularOffset", lp.getPerpendicularOffset());
/*  397 */       end("LinePlacement");
/*  398 */       end("LabelPlacement");
/*      */     }
/*      */     
/*      */     public void visit(AnchorPoint ap) {
/*  402 */       start("AnchorPoint");
/*  403 */       element("AnchorPointX", ap.getAnchorPointX());
/*  404 */       element("AnchorPointY", ap.getAnchorPointY());
/*  405 */       end("AnchorPoint");
/*      */     }
/*      */     
/*      */     public void visit(TextSymbolizer text) {
/*  409 */       if (text == null)
/*      */         return; 
/*  414 */       AttributesImpl atts = new AttributesImpl();
/*  415 */       Unit<Length> uom = text.getUnitOfMeasure();
/*  416 */       if (uom != null)
/*  417 */         atts.addAttribute("", "uom", "uom", "", UomOgcMapping.get(uom).getSEString()); 
/*  419 */       start("TextSymbolizer", atts);
/*  421 */       encodeGeometryExpression(text.getGeometry());
/*  423 */       if (text.getLabel() != null) {
/*  424 */         start("Label");
/*  425 */         labelContent(text.getLabel());
/*  426 */         end("Label");
/*      */       } 
/*  429 */       if (text.getFonts() != null && (text.getFonts()).length != 0) {
/*  430 */         start("Font");
/*  432 */         Font[] fonts = text.getFonts();
/*  434 */         for (int i = 0; i < fonts.length; i++)
/*  435 */           encodeCssParam("font-family", fonts[i].getFontFamily()); 
/*  438 */         encodeCssParam("font-size", fonts[0].getFontSize());
/*  439 */         encodeCssParam("font-style", fonts[0].getFontStyle());
/*  440 */         encodeCssParam("font-weight", fonts[0].getFontWeight());
/*  441 */         end("Font");
/*      */       } 
/*  444 */       if (text.getPlacement() != null)
/*  445 */         text.getPlacement().accept(this); 
/*  448 */       if (text.getHalo() != null)
/*  449 */         text.getHalo().accept(this); 
/*  452 */       if (text.getFill() != null)
/*  453 */         text.getFill().accept(this); 
/*  456 */       if (text instanceof TextSymbolizer2) {
/*  457 */         TextSymbolizer2 text2 = (TextSymbolizer2)text;
/*  458 */         if (text2.getGraphic() != null)
/*  458 */           visit(text2.getGraphic()); 
/*  459 */         if (text2.getSnippet() != null)
/*  459 */           element("Snippet", text2.getSnippet()); 
/*  460 */         if (text2.getFeatureDescription() != null)
/*  460 */           element("FeatureDescription", text2.getFeatureDescription()); 
/*  461 */         OtherText otherText = text2.getOtherText();
/*  462 */         if (otherText != null) {
/*  463 */           AttributesImpl otherTextAtts = new AttributesImpl();
/*  464 */           otherTextAtts.addAttribute("", "target", "target", "", otherText.getTarget());
/*  465 */           element("OtherText", otherText.getText(), (Object)null, otherTextAtts);
/*      */         } 
/*      */       } 
/*  469 */       if (text.getPriority() != null)
/*  470 */         element("Priority", text.getPriority()); 
/*  473 */       if (text.getOptions() != null)
/*  474 */         encodeVendorOptions(text.getOptions()); 
/*  477 */       end("TextSymbolizer");
/*      */     }
/*      */     
/*      */     public void visit(RasterSymbolizer raster) {
/*  481 */       if (raster == null)
/*      */         return; 
/*  486 */       AttributesImpl atts = new AttributesImpl();
/*  487 */       Unit<Length> uom = raster.getUnitOfMeasure();
/*  488 */       if (uom != null)
/*  489 */         atts.addAttribute("", "uom", "uom", "", UomOgcMapping.get(uom).getSEString()); 
/*  491 */       start("RasterSymbolizer", atts);
/*  493 */       encodeGeometryExpression(raster.getGeometry());
/*  495 */       element("Opacity", raster.getOpacity(), Double.valueOf(1.0D));
/*  497 */       if (raster.getChannelSelection() != null) {
/*  498 */         ChannelSelection cs = raster.getChannelSelection();
/*  499 */         if (cs.getGrayChannel() != null) {
/*  500 */           start("ChannelSelection");
/*  501 */           SelectedChannelType gray = cs.getGrayChannel();
/*  503 */           start("GrayChannel");
/*  504 */           gray.accept(this);
/*  505 */           end("GrayChannel");
/*  507 */           end("ChannelSelection");
/*  508 */         } else if (cs.getRGBChannels() != null && (cs.getRGBChannels()).length == 3 && cs.getRGBChannels()[0] != null && cs.getRGBChannels()[1] != null && cs.getRGBChannels()[2] != null) {
/*  509 */           start("ChannelSelection");
/*  510 */           SelectedChannelType[] rgb = cs.getRGBChannels();
/*  512 */           start("RedChannel");
/*  513 */           rgb[0].accept(this);
/*  514 */           end("RedChannel");
/*  516 */           start("GreenChannel");
/*  517 */           rgb[1].accept(this);
/*  518 */           end("GreenChannel");
/*  520 */           start("BlueChannel");
/*  521 */           rgb[2].accept(this);
/*  522 */           end("BlueChannel");
/*  524 */           end("ChannelSelection");
/*      */         } 
/*      */       } 
/*  531 */       if (raster.getOverlap() != null) {
/*  532 */         Expression overlaps = raster.getOverlap();
/*  533 */         if (overlaps instanceof PropertyName) {
/*  534 */           String pn = ((PropertyName)overlaps).getPropertyName();
/*  535 */           if ("RANDOM".equals(pn)) {
/*  536 */             start("OverlapBehavior");
/*  537 */             start(pn);
/*  538 */             end(pn);
/*  539 */             end("OverlapBehavior");
/*      */           } 
/*      */         } else {
/*  544 */           elementLiteral("OverlapBehavior", overlaps, "RANDOM");
/*      */         } 
/*      */       } 
/*  548 */       ColorMap colorMap = raster.getColorMap();
/*  549 */       if (colorMap != null && colorMap.getColorMapEntries() != null && (colorMap.getColorMapEntries()).length > 0)
/*  550 */         colorMap.accept(this); 
/*  553 */       if (raster.getContrastEnhancement() != null)
/*  554 */         raster.getContrastEnhancement().accept(this); 
/*  557 */       if (raster.getShadedRelief() != null)
/*  558 */         raster.getShadedRelief().accept(this); 
/*  561 */       if (raster.getImageOutline() != null) {
/*  562 */         start("ImageOutline");
/*  563 */         raster.getImageOutline().accept(this);
/*  564 */         end("ImageOutline");
/*      */       } 
/*  567 */       end("RasterSymbolizer");
/*      */     }
/*      */     
/*      */     public void visit(ColorMap colorMap) {
/*      */       String typeString;
/*  573 */       AttributesImpl atts = new AttributesImpl();
/*  575 */       if (colorMap.getType() == 2) {
/*  576 */         typeString = "intervals";
/*  577 */       } else if (colorMap.getType() == 3) {
/*  578 */         typeString = "values";
/*      */       } else {
/*  580 */         typeString = "ramp";
/*      */       } 
/*  581 */       if (!"ramp".equals(typeString))
/*  582 */         atts.addAttribute("", "type", "type", "", typeString); 
/*  584 */       boolean extended = colorMap.getExtendedColors();
/*  585 */       if (extended)
/*  586 */         atts.addAttribute("", "extended", "extended", "", "" + extended); 
/*  589 */       start("ColorMap", atts);
/*  590 */       ColorMapEntry[] mapEntries = colorMap.getColorMapEntries();
/*  591 */       for (int i = 0; i < mapEntries.length; i++)
/*  592 */         mapEntries[i].accept(this); 
/*  594 */       end("ColorMap");
/*      */     }
/*      */     
/*      */     public void visit(ColorMapEntry colorEntry) {
/*  598 */       if (colorEntry != null) {
/*  599 */         AttributesImpl atts = new AttributesImpl();
/*  600 */         atts.addAttribute("", "color", "color", "", (String)colorEntry.getColor().evaluate(null, String.class));
/*  601 */         if (colorEntry.getOpacity() != null)
/*  602 */           atts.addAttribute("", "opacity", "opacity", "", colorEntry.getOpacity().toString()); 
/*  604 */         if (colorEntry.getQuantity() != null)
/*  605 */           atts.addAttribute("", "quantity", "quantity", "", colorEntry.getQuantity().toString()); 
/*  607 */         if (colorEntry.getLabel() != null)
/*  608 */           atts.addAttribute("", "label", "label", "", colorEntry.getLabel()); 
/*  610 */         element("ColorMapEntry", (String)null, atts);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void visit(Symbolizer sym) {
/*      */       try {
/*  616 */         this.contentHandler.startElement("", "!--", "!--", this.NULL_ATTS);
/*  617 */         chars("Unidentified Symbolizer " + sym.getClass());
/*  618 */         this.contentHandler.endElement("", "--", "--");
/*  619 */       } catch (SAXException se) {
/*  620 */         throw new RuntimeException(se);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void visit(PolygonSymbolizer poly) {
/*  627 */       AttributesImpl atts = new AttributesImpl();
/*  628 */       Unit<Length> uom = poly.getUnitOfMeasure();
/*  629 */       if (uom != null)
/*  630 */         atts.addAttribute("", "uom", "uom", "", UomOgcMapping.get(uom).getSEString()); 
/*  632 */       start("PolygonSymbolizer", atts);
/*  633 */       encodeGeometryExpression(poly.getGeometry());
/*  635 */       if (poly.getFill() != null)
/*  636 */         poly.getFill().accept(this); 
/*  639 */       if (poly.getStroke() != null)
/*  640 */         poly.getStroke().accept(this); 
/*  643 */       if (poly.getOptions() != null)
/*  644 */         encodeVendorOptions(poly.getOptions()); 
/*  646 */       end("PolygonSymbolizer");
/*      */     }
/*      */     
/*      */     public void visit(ExternalGraphic exgr) {
/*  650 */       start("ExternalGraphic");
/*  652 */       AttributesImpl atts = new AttributesImpl();
/*      */       try {
/*  654 */         atts.addAttribute("http://www.w3.org/2000/xmlns/", "xlink", "xmlns:xlink", "", "http://www.w3.org/1999/xlink");
/*  655 */         atts.addAttribute("http://www.w3.org/1999/xlink", "type", "xlink:type", "", "simple");
/*  656 */         atts.addAttribute("http://www.w3.org/1999/xlink", "xlink", "xlink:href", "", exgr.getLocation().toString());
/*  657 */       } catch (MalformedURLException e) {
/*  658 */         throw new Error("Failed to encode the xlink location", e);
/*      */       } 
/*  660 */       element("OnlineResource", (String)null, atts);
/*  662 */       element("Format", exgr.getFormat());
/*  664 */       end("ExternalGraphic");
/*      */     }
/*      */     
/*      */     public void visit(LineSymbolizer line) {
/*  670 */       AttributesImpl atts = new AttributesImpl();
/*  671 */       Unit<Length> uom = line.getUnitOfMeasure();
/*  672 */       if (uom != null)
/*  673 */         atts.addAttribute("", "uom", "uom", "", UomOgcMapping.get(uom).getSEString()); 
/*  675 */       start("LineSymbolizer", atts);
/*  676 */       encodeGeometryExpression(line.getGeometry());
/*  678 */       if (line.getStroke() != null)
/*  679 */         line.getStroke().accept(this); 
/*  681 */       if (line.getOptions() != null)
/*  682 */         encodeVendorOptions(line.getOptions()); 
/*  684 */       if (line.getPerpendicularOffset() != null)
/*  685 */         element("PerpendicularOffset", line.getPerpendicularOffset() + ""); 
/*  688 */       end("LineSymbolizer");
/*      */     }
/*      */     
/*      */     public void visit(Fill fill) {
/*  692 */       start("Fill");
/*  694 */       if (fill.getGraphicFill() != null) {
/*  695 */         start("GraphicFill");
/*  696 */         fill.getGraphicFill().accept(this);
/*  697 */         end("GraphicFill");
/*      */       } 
/*  700 */       encodeCssParam("fill", fill.getColor(), "#808080");
/*  701 */       encodeCssParam("fill-opacity", fill.getOpacity(), Double.valueOf(1.0D));
/*  702 */       end("Fill");
/*      */     }
/*      */     
/*      */     public void visit(Rule rule) {
/*  706 */       start("Rule");
/*  707 */       if (rule.getName() != null)
/*  707 */         element("Name", rule.getName()); 
/*  708 */       if (rule.getDescription() != null && rule.getDescription().getTitle() != null)
/*  710 */         element("Title", rule.getDescription().getTitle()); 
/*  711 */       if (rule.getDescription() != null && rule.getDescription().getAbstract() != null)
/*  713 */         element("Abstract", rule.getDescription().getAbstract()); 
/*  715 */       Graphic[] gr = rule.getLegendGraphic();
/*  716 */       for (int i = 0; i < gr.length; i++) {
/*  717 */         start("LegendGraphic");
/*  718 */         gr[i].accept(this);
/*  719 */         end("LegendGraphic");
/*      */       } 
/*  722 */       Filter filter = rule.getFilter();
/*  723 */       if (filter != null && filter != Filter.INCLUDE)
/*  726 */         visit(filter); 
/*  729 */       if (rule.isElseFilter()) {
/*  730 */         start("ElseFilter");
/*  731 */         end("ElseFilter");
/*      */       } 
/*  734 */       if (rule.getMinScaleDenominator() != 0.0D)
/*  735 */         element("MinScaleDenominator", rule.getMinScaleDenominator() + ""); 
/*  739 */       if (rule.getMaxScaleDenominator() != Double.POSITIVE_INFINITY)
/*  740 */         element("MaxScaleDenominator", rule.getMaxScaleDenominator() + ""); 
/*  744 */       Symbolizer[] sym = rule.getSymbolizers();
/*  745 */       for (int j = 0; j < sym.length; j++)
/*  746 */         sym[j].accept(this); 
/*  749 */       end("Rule");
/*      */     }
/*      */     
/*      */     public void visit(Mark mark) {
/*  753 */       start("Mark");
/*  754 */       if (mark.getWellKnownName() != null && !"square".equals(mark.getWellKnownName().evaluate(null)))
/*  755 */         encodeValue("WellKnownName", (Attributes)null, mark.getWellKnownName(), (Object)null); 
/*  758 */       if (mark.getFill() != null)
/*  759 */         mark.getFill().accept(this); 
/*  762 */       if (mark.getStroke() != null)
/*  763 */         mark.getStroke().accept(this); 
/*  766 */       end("Mark");
/*      */     }
/*      */     
/*      */     public void visit(PointSymbolizer ps) {
/*  772 */       AttributesImpl atts = new AttributesImpl();
/*  773 */       Unit<Length> uom = ps.getUnitOfMeasure();
/*  774 */       if (uom != null)
/*  775 */         atts.addAttribute("", "uom", "uom", "", UomOgcMapping.get(uom).getSEString()); 
/*  777 */       start("PointSymbolizer", atts);
/*  779 */       encodeGeometryExpression(ps.getGeometry());
/*  781 */       ps.getGraphic().accept(this);
/*  783 */       if (ps.getOptions() != null)
/*  784 */         encodeVendorOptions(ps.getOptions()); 
/*  786 */       end("PointSymbolizer");
/*      */     }
/*      */     
/*      */     public void visit(Halo halo) {
/*  790 */       start("Halo");
/*  791 */       if (halo.getRadius() != null)
/*  792 */         encodeValue("Radius", (Attributes)null, halo.getRadius(), (Object)null); 
/*  794 */       if (halo.getFill() != null)
/*  795 */         halo.getFill().accept(this); 
/*  797 */       end("Halo");
/*      */     }
/*      */     
/*      */     public void visit(Graphic gr) {
/*  801 */       start("Graphic");
/*  805 */       Symbol[] symbols = gr.getSymbols();
/*  807 */       for (int i = 0; i < symbols.length; i++)
/*  808 */         symbols[i].accept(this); 
/*  811 */       element("Opacity", gr.getOpacity(), Double.valueOf(1.0D));
/*  812 */       element("Size", gr.getSize());
/*  813 */       element("Rotation", gr.getRotation(), Double.valueOf(0.0D));
/*  814 */       visit(gr.getDisplacement());
/*  816 */       end("Graphic");
/*      */     }
/*      */     
/*      */     public void visit(StyledLayerDescriptor sld) {
/*  820 */       AttributesImpl atts = new AttributesImpl();
/*  821 */       atts.addAttribute("", "version", "version", "", "1.0.0");
/*  822 */       start("StyledLayerDescriptor", atts);
/*  824 */       if (sld.getName() != null && sld.getName().length() > 0)
/*  825 */         element("Name", sld.getName()); 
/*  827 */       if (sld.getTitle() != null && sld.getTitle().length() > 0)
/*  828 */         element("Title", sld.getTitle()); 
/*  830 */       if (sld.getAbstract() != null && sld.getAbstract().length() > 0)
/*  831 */         element("Abstract", sld.getAbstract()); 
/*  834 */       StyledLayer[] layers = sld.getStyledLayers();
/*  836 */       for (int i = 0; i < layers.length; i++) {
/*  837 */         if (layers[i] instanceof NamedLayer) {
/*  838 */           visit((NamedLayer)layers[i]);
/*  839 */         } else if (layers[i] instanceof UserLayer) {
/*  840 */           visit((UserLayer)layers[i]);
/*      */         } else {
/*  842 */           throw new IllegalArgumentException("StyledLayer '" + layers[i].getClass().toString() + "' not found");
/*      */         } 
/*      */       } 
/*  847 */       end("StyledLayerDescriptor");
/*      */     }
/*      */     
/*      */     public void visit(NamedLayer layer) {
/*  851 */       start("NamedLayer");
/*  852 */       element("Name", layer.getName());
/*  854 */       FeatureTypeConstraint[] lfc = layer.getLayerFeatureConstraints();
/*  855 */       if (lfc != null && lfc.length > 0) {
/*  856 */         start("LayerFeatureConstraints");
/*  857 */         for (int j = 0; j < lfc.length; j++)
/*  858 */           visit(lfc[j]); 
/*  860 */         end("LayerFeatureConstraints");
/*      */       } 
/*  863 */       Style[] styles = layer.getStyles();
/*  865 */       for (int i = 0; i < styles.length; i++)
/*  866 */         visit(styles[i]); 
/*  869 */       end("NamedLayer");
/*      */     }
/*      */     
/*      */     public void visit(UserLayer layer) {
/*  873 */       start("UserLayer");
/*  875 */       if (layer.getName() != null && layer.getName().length() > 0)
/*  876 */         element("Name", layer.getName()); 
/*  879 */       DataStore inlineFDS = layer.getInlineFeatureDatastore();
/*  880 */       if (inlineFDS != null) {
/*  881 */         visitInlineFeatureType(inlineFDS, layer.getInlineFeatureType());
/*  882 */       } else if (layer.getRemoteOWS() != null) {
/*  883 */         visit(layer.getRemoteOWS());
/*      */       } 
/*  886 */       start("LayerFeatureConstraints");
/*  887 */       FeatureTypeConstraint[] lfc = layer.getLayerFeatureConstraints();
/*  888 */       if (lfc != null && lfc.length > 0) {
/*  889 */         for (int j = 0; j < lfc.length; j++)
/*  890 */           visit(lfc[j]); 
/*      */       } else {
/*  893 */         start("FeatureTypeConstraint");
/*  894 */         end("FeatureTypeConstraint");
/*      */       } 
/*  896 */       end("LayerFeatureConstraints");
/*  898 */       Style[] styles = layer.getUserStyles();
/*  900 */       for (int i = 0; i < styles.length; i++)
/*  901 */         visit(styles[i]); 
/*  904 */       end("UserLayer");
/*      */     }
/*      */     
/*      */     private void visitInlineFeatureType(DataStore dataStore, SimpleFeatureType featureType) {
/*  908 */       start("InlineFeature");
/*      */       try {
/*  910 */         String ftName = featureType.getTypeName();
/*  911 */         SimpleFeatureSource fs = dataStore.getFeatureSource(ftName);
/*  912 */         SimpleFeatureCollection fc = fs.getFeatures();
/*  913 */         FeatureTransformer ftrax = new FeatureTransformer();
/*  914 */         ftrax.setCollectionNamespace(null);
/*  915 */         ftrax.setCollectionPrefix(null);
/*  916 */         ftrax.setGmlPrefixing(true);
/*  917 */         ftrax.setIndentation(2);
/*  918 */         CoordinateReferenceSystem crs = featureType.getGeometryDescriptor().getCoordinateReferenceSystem();
/*  920 */         String srsName = null;
/*  921 */         if (crs == null) {
/*  922 */           SLDTransformer.LOGGER.warning("Null CRS in feature type named [" + ftName + "]. Ignore CRS");
/*      */         } else {
/*  924 */           srsName = CRS.toSRS(crs, true);
/*  925 */           if (srsName == null) {
/*  930 */             Set<ReferenceIdentifier> ids = crs.getIdentifiers();
/*  931 */             if (ids == null || ids.isEmpty()) {
/*  932 */               SLDTransformer.LOGGER.warning("Null or empty set of named identifiers in CRS [" + crs + "] of feature type named [" + ftName + "]. Ignore CRS");
/*      */             } else {
/*  935 */               for (ReferenceIdentifier id : ids) {
/*  936 */                 if (id != null) {
/*  937 */                   srsName = String.valueOf(id);
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*  943 */           if (srsName != null) {
/*  949 */             int ndx = srsName.indexOf(':');
/*  950 */             if (ndx > 0) {
/*  951 */               SLDTransformer.LOGGER.info("Reducing CRS name [" + srsName + "] to its SRID");
/*  952 */               srsName = srsName.substring(ndx + 1).trim();
/*      */             } 
/*      */           } 
/*      */         } 
/*  956 */         if (srsName != null)
/*  957 */           ftrax.setSrsName(srsName); 
/*  960 */         String defaultNS = getDefaultNamespace();
/*  961 */         ftrax.getFeatureTypeNamespaces().declareDefaultNamespace("", defaultNS);
/*  962 */         String ns = featureType.getName().getNamespaceURI();
/*  963 */         if (ns == null) {
/*  964 */           SLDTransformer.LOGGER.info("Null namespace URI in feature type named [" + ftName + "]. Ignore namespace in features");
/*      */         } else {
/*  969 */           String prefix = this.nsSupport.getPrefix(ns);
/*  970 */           if (prefix != null)
/*  971 */             ftrax.getFeatureTypeNamespaces().declareNamespace((FeatureType)featureType, prefix, ns); 
/*      */         } 
/*  973 */         Translator t = ftrax.createTranslator(this.contentHandler);
/*  975 */         t.encode(fc);
/*  976 */       } catch (IOException ignored) {}
/*  978 */       end("InlineFeature");
/*      */     }
/*      */     
/*      */     public void visit(RemoteOWS remoteOWS) {
/*  982 */       start("RemoteOWS");
/*  983 */       element("Service", remoteOWS.getService());
/*  984 */       element("OnlineResource", remoteOWS.getOnlineResource());
/*  985 */       end("RemoteOWS");
/*      */     }
/*      */     
/*      */     public void visit(FeatureTypeConstraint ftc) {
/*  989 */       start("FeatureTypeConstraint");
/*  991 */       if (ftc != null) {
/*  992 */         element("FeatureTypeName", ftc.getFeatureTypeName());
/*  993 */         visit(ftc.getFilter());
/*  995 */         Extent[] extent = ftc.getExtents();
/*  997 */         for (int i = 0; i < extent.length; i++)
/*  998 */           visit(extent[i]); 
/*      */       } 
/* 1002 */       end("FeatureTypeConstraint");
/*      */     }
/*      */     
/*      */     public void visit(Extent extent) {
/* 1006 */       start("Extent");
/* 1007 */       element("Name", extent.getName());
/* 1008 */       element("Value", extent.getValue());
/* 1009 */       end("Extent");
/*      */     }
/*      */     
/*      */     public void visit(Filter filter) {
/*      */       try {
/* 1014 */         this.contentHandler.startElement("", "", "ogc:Filter", this.NULL_ATTS);
/* 1015 */         this.filterTranslator.encode(filter);
/* 1016 */         this.contentHandler.endElement("", "", "ogc:Filter");
/* 1017 */       } catch (SAXException se) {
/* 1018 */         throw new RuntimeException(se);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void visit(Style style) {
/* 1023 */       if (style instanceof NamedStyle) {
/* 1024 */         start("NamedStyle");
/* 1025 */         element("Name", style.getName());
/* 1026 */         end("NamedStyle");
/*      */       } else {
/* 1028 */         start("UserStyle");
/* 1029 */         element("Name", style.getName());
/* 1030 */         if (style.getDescription() != null && style.getDescription().getTitle() != null)
/* 1032 */           element("Title", style.getDescription().getTitle()); 
/* 1034 */         if (style.isDefault())
/* 1035 */           element("IsDefault", "1"); 
/* 1037 */         if (style.getDescription() != null && style.getDescription().getAbstract() != null)
/* 1039 */           element("Abstract", style.getDescription().getAbstract()); 
/* 1040 */         FeatureTypeStyle[] fts = style.getFeatureTypeStyles();
/* 1041 */         for (int i = 0; i < fts.length; i++)
/* 1042 */           visit(fts[i]); 
/* 1044 */         end("UserStyle");
/*      */       } 
/*      */     }
/*      */     
/*      */     public void visit(FeatureTypeStyle fts) {
/* 1049 */       start("FeatureTypeStyle");
/* 1051 */       if (fts.getName() != null && fts.getName().length() > 0)
/* 1052 */         element("Name", fts.getName()); 
/* 1055 */       if (fts.getDescription() != null && fts.getDescription().getTitle() != null)
/* 1057 */         element("Title", fts.getDescription().getTitle()); 
/* 1058 */       if (fts.getDescription() != null && fts.getDescription().getAbstract() != null)
/* 1060 */         element("Abstract", fts.getDescription().getAbstract()); 
/* 1063 */       if (fts.featureTypeNames() != null && fts.featureTypeNames().size() > 0)
/* 1064 */         element("FeatureTypeName", ((Name)fts.featureTypeNames().iterator().next()).toString()); 
/* 1067 */       if (fts.getTransformation() != null)
/* 1068 */         element("Transformation", fts.getTransformation()); 
/* 1071 */       String[] sti = fts.getSemanticTypeIdentifiers();
/* 1073 */       if (sti.length != 1 || !sti[0].equals(SemanticType.ANY.toString()))
/* 1076 */         for (int j = 0; j < sti.length; j++)
/* 1077 */           element("SemanticTypeIdentifier", sti[j]);  
/* 1082 */       Rule[] rules = fts.getRules();
/* 1084 */       for (int i = 0; i < rules.length; i++)
/* 1085 */         rules[i].accept(this); 
/* 1088 */       end("FeatureTypeStyle");
/*      */     }
/*      */     
/*      */     public void visit(Displacement dis) {
/* 1092 */       if (dis == null)
/*      */         return; 
/* 1098 */       Expression dx = dis.getDisplacementX();
/* 1099 */       Expression dy = dis.getDisplacementY();
/* 1100 */       if (isNull(dx) && isNull(dy))
/*      */         return; 
/* 1103 */       if (isDefault(dx, Integer.valueOf(0)) && isDefault(dy, Integer.valueOf(0)))
/*      */         return; 
/* 1107 */       start("Displacement");
/* 1108 */       element("DisplacementX", dis.getDisplacementX());
/* 1109 */       element("DisplacementY", dis.getDisplacementY());
/* 1110 */       end("Displacement");
/*      */     }
/*      */     
/*      */     void encodeGeometryProperty(String name) {
/* 1114 */       if (name == null || name.trim().length() == 0)
/*      */         return; 
/* 1118 */       FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2(null);
/* 1119 */       PropertyName propertyName = ff.property(name);
/* 1121 */       start("Geometry");
/* 1122 */       this.filterTranslator.encode(propertyName);
/* 1123 */       end("Geometry");
/*      */     }
/*      */     
/*      */     void encodeGeometryExpression(Expression geom) {
/* 1128 */       if (geom == null)
/*      */         return; 
/* 1132 */       start("Geometry");
/* 1133 */       this.filterTranslator.encode(geom);
/* 1134 */       end("Geometry");
/*      */     }
/*      */     
/*      */     void encodeCssParam(String name, Expression expression) {
/* 1139 */       encodeCssParam(name, expression, (Object)null);
/*      */     }
/*      */     
/*      */     void encodeCssParam(String name, Expression expression, Object defaultValue) {
/* 1143 */       if (expression == null)
/*      */         return; 
/* 1147 */       AttributesImpl atts = new AttributesImpl();
/* 1148 */       atts.addAttribute("", "name", "name", "", name);
/* 1149 */       encodeValue("CssParameter", atts, expression, defaultValue);
/*      */     }
/*      */     
/*      */     void encodeValue(String elementName, Attributes atts, Expression expression, Object defaultValue) {
/* 1153 */       if (expression == null)
/*      */         return; 
/* 1158 */       if (expression instanceof Literal && defaultValue != null) {
/* 1159 */         Object value = expression.evaluate(null, defaultValue.getClass());
/* 1160 */         if (value != null && value.equals(defaultValue))
/*      */           return; 
/*      */       } 
/* 1165 */       if (atts == null)
/* 1166 */         atts = this.NULL_ATTS; 
/* 1168 */       if (expression instanceof Literal) {
/* 1170 */         element(elementName, (String)expression.evaluate(null, String.class), atts);
/*      */       } else {
/* 1172 */         start(elementName, atts);
/* 1173 */         this.filterTranslator.encode(expression);
/* 1174 */         end(elementName);
/*      */       } 
/*      */     }
/*      */     
/*      */     void encodeVendorOptions(Map options) {
/* 1179 */       if (options != null) {
/* 1180 */         Iterator<String> it = options.keySet().iterator();
/* 1181 */         while (it.hasNext()) {
/* 1182 */           String key = it.next();
/* 1183 */           String value = (String)options.get(key);
/* 1184 */           encodeVendorOption(key, value);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     void encodeVendorOption(String key, String value) {
/* 1190 */       AttributesImpl atts = new AttributesImpl();
/* 1191 */       atts.addAttribute("", "name", "name", "", key);
/* 1192 */       start("VendorOption", atts);
/* 1193 */       chars(value);
/* 1194 */       end("VendorOption");
/*      */     }
/*      */     
/*      */     public void encode(Style[] styles) {
/*      */       try {
/* 1199 */         this.contentHandler.startDocument();
/* 1201 */         start("StyledLayerDescriptor", this.NULL_ATTS);
/* 1202 */         start("NamedLayer", this.NULL_ATTS);
/* 1204 */         for (int i = 0, ii = styles.length; i < ii; i++)
/* 1205 */           styles[i].accept(this); 
/* 1208 */         end("NamedLayer");
/* 1209 */         end("StyledLayerDescriptor");
/* 1211 */         this.contentHandler.endDocument();
/* 1212 */       } catch (SAXException se) {
/* 1213 */         throw new RuntimeException(se);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void encode(StyledLayerDescriptor sld) {
/*      */       try {
/* 1219 */         this.contentHandler.startDocument();
/* 1220 */         sld.accept(this);
/* 1221 */         this.contentHandler.endDocument();
/* 1222 */       } catch (SAXException se) {
/* 1223 */         throw new RuntimeException(se);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void encode(Object o) throws IllegalArgumentException {
/* 1228 */       if (o instanceof StyledLayerDescriptor) {
/* 1229 */         encode((StyledLayerDescriptor)o);
/* 1230 */       } else if (o instanceof Style[]) {
/* 1231 */         encode((Style[])o);
/*      */       } else {
/* 1233 */         Class<?> c = o.getClass();
/*      */         try {
/* 1236 */           Method m = c.getMethod("accept", new Class[] { StyleVisitor.class });
/* 1238 */           m.invoke(o, new Object[] { this });
/* 1239 */         } catch (NoSuchMethodException nsme) {
/* 1240 */           throw new IllegalArgumentException("Cannot encode " + o);
/* 1241 */         } catch (Exception e) {
/* 1242 */           throw new RuntimeException("Internal transformation exception", e);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public void visit(ContrastEnhancement ce) {
/* 1249 */       if (ce == null || ce.getMethod() == null)
/*      */         return; 
/* 1252 */       start("ContrastEnhancement");
/* 1254 */       ContrastMethod method = ce.getMethod();
/* 1255 */       if (method != null && !ContrastMethod.NONE.equals(method)) {
/* 1256 */         String val = method.name();
/* 1257 */         val = val.substring(0, 1).toUpperCase() + val.substring(1).toLowerCase();
/* 1258 */         start(val);
/* 1259 */         end(val);
/*      */       } 
/* 1263 */       Literal literal = (Literal)ce.getGammaValue();
/* 1264 */       if (literal != null)
/* 1266 */         element("GammaValue", literal.getValue().toString()); 
/* 1269 */       end("ContrastEnhancement");
/*      */     }
/*      */     
/*      */     public void visit(ImageOutline outline) {
/* 1274 */       if (outline == null)
/*      */         return; 
/* 1276 */       start("ImageOutline");
/* 1277 */       outline.getSymbolizer().accept(this);
/* 1278 */       end("ImageOutline");
/*      */     }
/*      */     
/*      */     public void visit(ChannelSelection cs) {
/* 1282 */       if (cs == null)
/*      */         return; 
/* 1284 */       start("ChannelSelection");
/* 1285 */       SelectedChannelType[] sct = cs.getSelectedChannels();
/* 1286 */       for (int i = 0; i < sct.length && sct != null; i++)
/* 1287 */         visit(sct[i]); 
/* 1288 */       end("ChannelSelection");
/*      */     }
/*      */     
/*      */     public void visit(OverlapBehavior ob) {
/* 1293 */       start("OverlapBehavior");
/* 1294 */       String pn = (String)ob.getValue();
/* 1295 */       start(pn);
/* 1296 */       end(pn);
/* 1297 */       end("OverlapBehavior");
/*      */     }
/*      */     
/*      */     public void visit(SelectedChannelType sct) {
/* 1302 */       element("SourceChannelName", sct.getChannelName());
/* 1303 */       ContrastEnhancement ce = sct.getContrastEnhancement();
/* 1304 */       if (ce != null)
/* 1305 */         ce.accept(this); 
/*      */     }
/*      */     
/*      */     public void visit(ShadedRelief sr) {
/* 1310 */       start("ShadedRelief");
/* 1312 */       if (sr.isBrightnessOnly()) {
/* 1313 */         element("BrightnessOnly", "true");
/*      */       } else {
/* 1315 */         element("BrightnessOnly", "false");
/*      */       } 
/* 1318 */       if (sr.getReliefFactor() != null) {
/* 1323 */         Literal l = (Literal)sr.getReliefFactor();
/* 1324 */         element("ReliefFactor", l.getValue().toString());
/*      */       } 
/* 1326 */       end("ShadedRelief");
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\SLDTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */