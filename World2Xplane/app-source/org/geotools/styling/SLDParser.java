/*      */ package org.geotools.styling;
/*      */ 
/*      */ import java.awt.Component;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import java.util.regex.Pattern;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.swing.Icon;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import org.geotools.data.Base64;
/*      */ import org.geotools.data.DataStore;
/*      */ import org.geotools.factory.CommonFactoryFinder;
/*      */ import org.geotools.factory.GeoTools;
/*      */ import org.geotools.filter.ExpressionDOMParser;
/*      */ import org.geotools.filter.FilterDOMParser;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.util.GrowableInternationalString;
/*      */ import org.geotools.util.SimpleInternationalString;
/*      */ import org.geotools.util.logging.Logging;
/*      */ import org.opengis.filter.Filter;
/*      */ import org.opengis.filter.FilterFactory;
/*      */ import org.opengis.filter.FilterFactory2;
/*      */ import org.opengis.filter.expression.Expression;
/*      */ import org.opengis.filter.expression.Function;
/*      */ import org.opengis.filter.expression.Literal;
/*      */ import org.opengis.filter.expression.PropertyName;
/*      */ import org.opengis.style.Fill;
/*      */ import org.opengis.util.InternationalString;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.xml.sax.EntityResolver;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.SAXException;
/*      */ 
/*      */ public class SLDParser {
/*   77 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.styling");
/*      */   
/*      */   private static final String channelSelectionString = "ChannelSelection";
/*      */   
/*      */   private static final String graphicSt = "Graphic";
/*      */   
/*      */   private static final String geomString = "Geometry";
/*      */   
/*      */   private static final String fillSt = "Fill";
/*      */   
/*      */   private static final String opacityString = "Opacity";
/*      */   
/*      */   private static final String overlapBehaviorString = "OverlapBehavior";
/*      */   
/*      */   private static final String colorMapString = "ColorMap";
/*      */   
/*      */   private static final String colorMapOpacityString = "opacity";
/*      */   
/*      */   private static final String colorMapColorString = "color";
/*      */   
/*      */   private static final String contrastEnhancementString = "ContrastEnhancement";
/*      */   
/*      */   private static final String shadedReliefString = "ShadedRelief";
/*      */   
/*      */   private static final String imageOutlineString = "ImageOutline";
/*      */   
/*      */   private static final String colorMapQuantityString = "quantity";
/*      */   
/*      */   private static final String colorMapLabelString = "label";
/*      */   
/*      */   private static final String strokeString = "Stroke";
/*      */   
/*      */   private static final String uomString = "uom";
/*      */   
/*      */   private static final String VendorOptionString = "VendorOption";
/*      */   
/*      */   private static final String PerpendicularOffsetString = "PerpendicularOffset";
/*      */   
/*  116 */   private static final Pattern WHITESPACES = Pattern.compile("\\s+", 8);
/*      */   
/*  117 */   private static final Pattern LEADING_WHITESPACES = Pattern.compile("^\\s+");
/*      */   
/*  118 */   private static final Pattern TRAILING_WHITESPACES = Pattern.compile("\\s+$");
/*      */   
/*      */   private FilterFactory ff;
/*      */   
/*      */   protected InputSource source;
/*      */   
/*      */   private Document dom;
/*      */   
/*      */   protected StyleFactory factory;
/*      */   
/*      */   private URL sourceUrl;
/*      */   
/*      */   private ResourceLocator onlineResourceLocator;
/*      */   
/*      */   private EntityResolver entityResolver;
/*      */   
/*      */   public SLDParser(StyleFactory factory) {
/*  146 */     this(factory, CommonFactoryFinder.getFilterFactory(GeoTools.getDefaultHints()));
/*      */   }
/*      */   
/*      */   public SLDParser(StyleFactory factory, FilterFactory filterFactory) {
/*  150 */     this.factory = factory;
/*  151 */     this.ff = filterFactory;
/*  152 */     this.onlineResourceLocator = new DefaultResourceLocator();
/*      */   }
/*      */   
/*      */   public SLDParser(StyleFactory factory, String filename) throws FileNotFoundException {
/*  167 */     this(factory);
/*  169 */     File f = new File(filename);
/*  170 */     setInput(f);
/*      */   }
/*      */   
/*      */   public SLDParser(StyleFactory factory, File f) throws FileNotFoundException {
/*  185 */     this(factory);
/*  186 */     setInput(f);
/*      */   }
/*      */   
/*      */   public SLDParser(StyleFactory factory, URL url) throws IOException {
/*  201 */     this(factory);
/*  202 */     setInput(url);
/*      */   }
/*      */   
/*      */   public SLDParser(StyleFactory factory, InputStream s) {
/*  214 */     this(factory);
/*  215 */     setInput(s);
/*      */   }
/*      */   
/*      */   public SLDParser(StyleFactory factory, Reader r) {
/*  227 */     this(factory);
/*  228 */     setInput(r);
/*      */   }
/*      */   
/*      */   public void setInput(String filename) throws FileNotFoundException {
/*  241 */     File f = new File(filename);
/*  242 */     this.source = new InputSource(new FileInputStream(f));
/*      */     try {
/*  244 */       setSourceUrl(f.toURI().toURL());
/*  245 */     } catch (MalformedURLException e) {
/*  246 */       LOGGER.warning("Can't build URL for file " + f.getAbsolutePath());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setInput(File f) throws FileNotFoundException {
/*  260 */     this.source = new InputSource(new FileInputStream(f));
/*      */     try {
/*  262 */       setSourceUrl(f.toURI().toURL());
/*  263 */     } catch (MalformedURLException e) {
/*  264 */       LOGGER.warning("Can't build URL for file " + f.getAbsolutePath());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setInput(URL url) throws IOException {
/*  278 */     this.source = new InputSource(url.openStream());
/*  279 */     setSourceUrl(url);
/*      */   }
/*      */   
/*      */   public void setInput(InputStream in) {
/*  289 */     this.source = new InputSource(in);
/*      */   }
/*      */   
/*      */   public void setInput(Reader in) {
/*  299 */     this.source = new InputSource(in);
/*      */   }
/*      */   
/*      */   public void setOnLineResourceLocator(ResourceLocator onlineResourceLocator) {
/*  306 */     this.onlineResourceLocator = onlineResourceLocator;
/*      */   }
/*      */   
/*      */   public void setEntityResolver(EntityResolver entityResolver) {
/*  316 */     this.entityResolver = entityResolver;
/*      */   }
/*      */   
/*      */   void setSourceUrl(URL sourceUrl) {
/*  323 */     this.sourceUrl = sourceUrl;
/*  324 */     if (this.onlineResourceLocator instanceof DefaultResourceLocator)
/*  325 */       ((DefaultResourceLocator)this.onlineResourceLocator).setSourceUrl(sourceUrl); 
/*      */   }
/*      */   
/*      */   protected DocumentBuilder newDocumentBuilder(boolean namespaceAware) throws ParserConfigurationException {
/*  330 */     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
/*  331 */     dbf.setNamespaceAware(namespaceAware);
/*  332 */     DocumentBuilder db = dbf.newDocumentBuilder();
/*  334 */     if (this.entityResolver != null)
/*  335 */       db.setEntityResolver(this.entityResolver); 
/*  338 */     return db;
/*      */   }
/*      */   
/*      */   public Style[] readXML() {
/*      */     try {
/*  351 */       this.dom = newDocumentBuilder(true).parse(this.source);
/*  352 */     } catch (ParserConfigurationException pce) {
/*  353 */       throw new RuntimeException(pce);
/*  354 */     } catch (SAXException se) {
/*  355 */       throw new RuntimeException(se);
/*  356 */     } catch (IOException ie) {
/*  357 */       throw new RuntimeException(ie);
/*      */     } 
/*  360 */     return readDOM(this.dom);
/*      */   }
/*      */   
/*      */   public Style[] readDOM() {
/*  367 */     if (this.dom == null)
/*  368 */       throw new NullPointerException("dom is null"); 
/*  370 */     return readDOM(this.dom);
/*      */   }
/*      */   
/*      */   public Style[] readDOM(Document document) {
/*  382 */     this.dom = document;
/*  385 */     NodeList nodes = findElements(document, "UserStyle");
/*  386 */     int length = nodes.getLength();
/*  388 */     if (nodes == null)
/*  389 */       return new Style[0]; 
/*  391 */     Style[] styles = new Style[length];
/*  393 */     for (int i = 0; i < length; i++)
/*  394 */       styles[i] = parseStyle(nodes.item(i)); 
/*  397 */     return styles;
/*      */   }
/*      */   
/*      */   private NodeList findElements(Document document, String name) {
/*  405 */     NodeList nodes = document.getElementsByTagNameNS("*", name);
/*  407 */     if (nodes.getLength() == 0)
/*  408 */       nodes = document.getElementsByTagName(name); 
/*  411 */     return nodes;
/*      */   }
/*      */   
/*      */   private NodeList findElements(Element element, String name) {
/*  415 */     NodeList nodes = element.getElementsByTagNameNS("*", name);
/*  417 */     if (nodes.getLength() == 0)
/*  418 */       nodes = element.getElementsByTagName(name); 
/*  421 */     return nodes;
/*      */   }
/*      */   
/*      */   public StyledLayerDescriptor parseSLD() {
/*      */     try {
/*  426 */       this.dom = newDocumentBuilder(true).parse(this.source);
/*  431 */       StyledLayerDescriptor sld = parseDescriptor(this.dom.getDocumentElement());
/*  433 */       return sld;
/*  435 */     } catch (ParserConfigurationException pce) {
/*  436 */       throw new RuntimeException(pce);
/*  437 */     } catch (SAXException se) {
/*  438 */       throw new RuntimeException(se);
/*  439 */     } catch (IOException ie) {
/*  440 */       throw new RuntimeException(ie);
/*      */     } 
/*      */   }
/*      */   
/*      */   public StyledLayerDescriptor parseDescriptor(Node root) {
/*  445 */     StyledLayerDescriptor sld = this.factory.createStyledLayerDescriptor();
/*  449 */     NodeList children = root.getChildNodes();
/*  450 */     int length = children.getLength();
/*  452 */     for (int i = 0; i < length; i++) {
/*  453 */       Node child = children.item(i);
/*  454 */       if (child != null && child.getNodeType() == 1) {
/*  457 */         String childName = child.getLocalName();
/*  458 */         if (childName == null)
/*  459 */           childName = child.getNodeName(); 
/*  462 */         if (childName.equalsIgnoreCase("Name")) {
/*  463 */           sld.setName(getFirstChildValue(child));
/*  464 */         } else if (childName.equalsIgnoreCase("Title")) {
/*  465 */           sld.setTitle(getFirstChildValue(child));
/*  466 */         } else if (childName.equalsIgnoreCase("Abstract")) {
/*  467 */           sld.setAbstract(getFirstChildValue(child));
/*  468 */         } else if (childName.equalsIgnoreCase("NamedLayer")) {
/*  469 */           NamedLayer layer = parseNamedLayer(child);
/*  470 */           sld.addStyledLayer(layer);
/*  471 */         } else if (childName.equalsIgnoreCase("UserLayer")) {
/*  472 */           StyledLayer layer = parseUserLayer(child);
/*  473 */           sld.addStyledLayer(layer);
/*      */         } 
/*      */       } 
/*      */     } 
/*  477 */     return sld;
/*      */   }
/*      */   
/*      */   String getFirstChildValue(Node child) {
/*  486 */     if (child.getFirstChild() != null)
/*  487 */       return child.getFirstChild().getNodeValue(); 
/*  489 */     return null;
/*      */   }
/*      */   
/*      */   private static String getAttribute(Node node, String attrName) {
/*  493 */     NamedNodeMap attributes = node.getAttributes();
/*  494 */     Node attribute = attributes.getNamedItem(attrName);
/*  495 */     return (attribute == null) ? null : attribute.getNodeValue();
/*      */   }
/*      */   
/*      */   private StyledLayer parseUserLayer(Node root) {
/*  499 */     UserLayer layer = new UserLayerImpl();
/*  502 */     NodeList children = root.getChildNodes();
/*  503 */     int length = children.getLength();
/*  504 */     for (int i = 0; i < length; i++) {
/*  505 */       Node child = children.item(i);
/*  506 */       if (child != null && child.getNodeType() == 1) {
/*  509 */         String childName = child.getLocalName();
/*  510 */         if (childName == null)
/*  511 */           childName = child.getNodeName(); 
/*  514 */         if (childName.equalsIgnoreCase("InlineFeature")) {
/*  515 */           parseInlineFeature(child, layer);
/*  516 */         } else if (childName.equalsIgnoreCase("UserStyle")) {
/*  517 */           Style user = parseStyle(child);
/*  518 */           layer.addUserStyle(user);
/*  519 */         } else if (childName.equalsIgnoreCase("Name")) {
/*  520 */           String layerName = getFirstChildValue(child);
/*  521 */           layer.setName(layerName);
/*  522 */           if (LOGGER.isLoggable(Level.INFO))
/*  523 */             LOGGER.info("layer name: " + layer.getName()); 
/*  524 */         } else if (childName.equalsIgnoreCase("RemoteOWS")) {
/*  525 */           RemoteOWS remoteOws = parseRemoteOWS(child);
/*  526 */           layer.setRemoteOWS(remoteOws);
/*  527 */         } else if (childName.equalsIgnoreCase("LayerFeatureConstraints")) {
/*  528 */           layer.setLayerFeatureConstraints(parseLayerFeatureConstraints(child));
/*      */         } 
/*      */       } 
/*      */     } 
/*  533 */     return layer;
/*      */   }
/*      */   
/*      */   private FeatureTypeConstraint[] parseLayerFeatureConstraints(Node root) {
/*  537 */     List<FeatureTypeConstraint> featureTypeConstraints = new ArrayList<FeatureTypeConstraint>();
/*  539 */     NodeList children = root.getChildNodes();
/*  540 */     int length = children.getLength();
/*  541 */     for (int i = 0; i < length; i++) {
/*  542 */       Node child = children.item(i);
/*  543 */       if (child != null && child.getNodeType() == 1) {
/*  546 */         String childName = child.getLocalName();
/*  547 */         if (childName.equalsIgnoreCase("FeatureTypeConstraint")) {
/*  548 */           FeatureTypeConstraint ftc = parseFeatureTypeConstraint(child);
/*  549 */           if (ftc != null)
/*  550 */             featureTypeConstraints.add(ftc); 
/*      */         } 
/*      */       } 
/*      */     } 
/*  553 */     return featureTypeConstraints.<FeatureTypeConstraint>toArray(new FeatureTypeConstraint[featureTypeConstraints.size()]);
/*      */   }
/*      */   
/*      */   protected FeatureTypeConstraint parseFeatureTypeConstraint(Node root) {
/*  558 */     FeatureTypeConstraint ftc = new FeatureTypeConstraintImpl();
/*  560 */     NodeList children = root.getChildNodes();
/*  561 */     int length = children.getLength();
/*  562 */     for (int i = 0; i < length; i++) {
/*  563 */       Node child = children.item(i);
/*  564 */       if (child != null && child.getNodeType() == 1) {
/*  567 */         String childName = child.getLocalName();
/*  568 */         if (childName.equalsIgnoreCase("FeatureTypeName")) {
/*  569 */           ftc.setFeatureTypeName(getFirstChildValue(child));
/*  570 */         } else if (childName.equalsIgnoreCase("Filter")) {
/*  571 */           ftc.setFilter(parseFilter(child));
/*      */         } 
/*      */       } 
/*      */     } 
/*  574 */     ftc.setExtents(new Extent[0]);
/*  575 */     if (ftc.getFeatureTypeName() == null)
/*  576 */       return null; 
/*  578 */     return ftc;
/*      */   }
/*      */   
/*      */   private static Icon parseIcon(String content) throws IOException {
/*  582 */     byte[] bytes = Base64.decode(content);
/*  583 */     BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
/*  584 */     if (image == null)
/*  585 */       throw new IOException("invalid image content"); 
/*  587 */     return new ImageIcon(image);
/*      */   }
/*      */   
/*      */   protected RemoteOWS parseRemoteOWS(Node root) {
/*  591 */     RemoteOWS ows = new RemoteOWSImpl();
/*  593 */     NodeList children = root.getChildNodes();
/*  594 */     int length = children.getLength();
/*  595 */     for (int i = 0; i < length; i++) {
/*  596 */       Node child = children.item(i);
/*  597 */       if (child != null && child.getNodeType() == 1) {
/*  600 */         String childName = child.getLocalName();
/*  602 */         if (childName.equalsIgnoreCase("Service")) {
/*  603 */           ows.setService(getFirstChildValue(child));
/*  604 */         } else if (childName.equalsIgnoreCase("OnlineResource")) {
/*  605 */           ows.setOnlineResource(parseOnlineResource(child));
/*      */         } 
/*      */       } 
/*      */     } 
/*  608 */     return ows;
/*      */   }
/*      */   
/*      */   private void parseInlineFeature(Node root, UserLayer layer) {
/*      */     try {
/*  618 */       SLDInlineFeatureParser inparser = new SLDInlineFeatureParser(root);
/*  619 */       layer.setInlineFeatureDatastore((DataStore)inparser.dataStore);
/*  620 */       layer.setInlineFeatureType(inparser.featureType);
/*  621 */     } catch (Exception e) {
/*  622 */       throw (IllegalArgumentException)(new IllegalArgumentException()).initCause(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   private NamedLayer parseNamedLayer(Node root) {
/*  657 */     NamedLayer layer = new NamedLayerImpl();
/*  659 */     NodeList children = root.getChildNodes();
/*  660 */     int length = children.getLength();
/*  661 */     for (int i = 0; i < length; i++) {
/*  662 */       Node child = children.item(i);
/*  663 */       if (child != null && child.getNodeType() == 1) {
/*  666 */         String childName = child.getLocalName();
/*  667 */         if (childName == null)
/*  668 */           childName = child.getNodeName(); 
/*  671 */         if (childName.equalsIgnoreCase("Name")) {
/*  672 */           layer.setName(getFirstChildValue(child));
/*  673 */         } else if (childName.equalsIgnoreCase("NamedStyle")) {
/*  674 */           NamedStyle style = parseNamedStyle(child);
/*  675 */           layer.addStyle(style);
/*  676 */         } else if (childName.equalsIgnoreCase("UserStyle")) {
/*  677 */           Style user = parseStyle(child);
/*  678 */           layer.addStyle(user);
/*  679 */         } else if (childName.equalsIgnoreCase("LayerFeatureConstraints")) {
/*  680 */           layer.setLayerFeatureConstraints(parseLayerFeatureConstraints(child));
/*      */         } 
/*      */       } 
/*      */     } 
/*  684 */     return layer;
/*      */   }
/*      */   
/*      */   public NamedStyle parseNamedStyle(Node n) {
/*  712 */     if (this.dom == null)
/*      */       try {
/*  714 */         this.dom = newDocumentBuilder(false).newDocument();
/*  715 */       } catch (ParserConfigurationException pce) {
/*  716 */         throw new RuntimeException(pce);
/*      */       }  
/*  720 */     NamedStyle style = this.factory.createNamedStyle();
/*  722 */     NodeList children = n.getChildNodes();
/*  723 */     int length = children.getLength();
/*  724 */     if (LOGGER.isLoggable(Level.FINEST))
/*  725 */       LOGGER.finest("" + children.getLength() + " children to process"); 
/*  728 */     for (int j = 0; j < length; j++) {
/*  729 */       Node child = children.item(j);
/*  731 */       if (child != null && child.getNodeType() == 1 && child.getFirstChild() != null) {
/*  735 */         if (LOGGER.isLoggable(Level.FINEST))
/*  736 */           LOGGER.finest("processing " + child.getLocalName()); 
/*  738 */         String childName = child.getLocalName();
/*  739 */         if (childName == null)
/*  740 */           childName = child.getNodeName(); 
/*  742 */         if (childName.equalsIgnoreCase("Name"))
/*  743 */           style.setName(getFirstChildValue(child)); 
/*      */       } 
/*      */     } 
/*  746 */     return style;
/*      */   }
/*      */   
/*      */   public Style parseStyle(Node n) {
/*  761 */     if (this.dom == null)
/*      */       try {
/*  763 */         this.dom = newDocumentBuilder(false).newDocument();
/*  764 */       } catch (ParserConfigurationException pce) {
/*  765 */         throw new RuntimeException(pce);
/*      */       }  
/*  769 */     Style style = this.factory.createStyle();
/*  771 */     NodeList children = n.getChildNodes();
/*  772 */     int length = children.getLength();
/*  773 */     if (LOGGER.isLoggable(Level.FINEST))
/*  774 */       LOGGER.finest("" + children.getLength() + " children to process"); 
/*  777 */     for (int j = 0; j < length; j++) {
/*  778 */       Node child = children.item(j);
/*  780 */       if (child != null && child.getNodeType() == 1 && child.getFirstChild() != null) {
/*  786 */         if (LOGGER.isLoggable(Level.FINEST))
/*  787 */           LOGGER.finest("processing " + child.getLocalName()); 
/*  789 */         String childName = child.getLocalName();
/*  790 */         if (childName == null)
/*  791 */           childName = child.getNodeName(); 
/*  793 */         String firstChildValue = getFirstChildValue(child);
/*  794 */         if (childName.equalsIgnoreCase("Name")) {
/*  795 */           style.setName(firstChildValue);
/*  796 */         } else if (childName.equalsIgnoreCase("Title")) {
/*  798 */           style.getDescription().setTitle(parseInternationalString(child));
/*  799 */         } else if (childName.equalsIgnoreCase("Abstract")) {
/*  800 */           style.getDescription().setAbstract(parseInternationalString(child));
/*  801 */         } else if (childName.equalsIgnoreCase("IsDefault")) {
/*  802 */           if ("1".equals(firstChildValue)) {
/*  803 */             style.setDefault(true);
/*      */           } else {
/*  805 */             style.setDefault(Boolean.valueOf(firstChildValue).booleanValue());
/*      */           } 
/*  807 */         } else if (childName.equalsIgnoreCase("FeatureTypeStyle")) {
/*  808 */           style.addFeatureTypeStyle(parseFeatureTypeStyle(child));
/*      */         } 
/*      */       } 
/*      */     } 
/*  812 */     return style;
/*      */   }
/*      */   
/*      */   protected FeatureTypeStyle parseFeatureTypeStyle(Node style) {
/*  817 */     if (LOGGER.isLoggable(Level.FINEST))
/*  818 */       LOGGER.finest("Parsing featuretype style " + style.getLocalName()); 
/*  821 */     FeatureTypeStyle ft = this.factory.createFeatureTypeStyle();
/*  822 */     ArrayList<Rule> rules = new ArrayList<Rule>();
/*  823 */     ArrayList<String> sti = new ArrayList<String>();
/*  824 */     NodeList children = style.getChildNodes();
/*  825 */     int length = children.getLength();
/*  826 */     for (int i = 0; i < length; i++) {
/*  827 */       Node child = children.item(i);
/*  829 */       if (child != null && child.getNodeType() == 1) {
/*  833 */         if (LOGGER.isLoggable(Level.FINEST))
/*  834 */           LOGGER.finest("processing " + child.getLocalName()); 
/*  836 */         String childName = child.getLocalName();
/*  837 */         if (childName == null)
/*  838 */           childName = child.getNodeName(); 
/*  841 */         if (childName.equalsIgnoreCase("Name")) {
/*  842 */           ft.setName(getFirstChildValue(child));
/*  843 */         } else if (childName.equalsIgnoreCase("Title")) {
/*  844 */           ft.getDescription().setTitle(parseInternationalString(child));
/*  845 */         } else if (childName.equalsIgnoreCase("Abstract")) {
/*  846 */           ft.getDescription().setAbstract(parseInternationalString(child));
/*  847 */         } else if (childName.equalsIgnoreCase("FeatureTypeName")) {
/*  848 */           ft.setFeatureTypeName(getFirstChildValue(child));
/*  849 */         } else if (childName.equalsIgnoreCase("SemanticTypeIdentifier")) {
/*  850 */           sti.add(getFirstChildValue(child));
/*  851 */         } else if (childName.equalsIgnoreCase("Rule")) {
/*  852 */           rules.add(parseRule(child));
/*  853 */         } else if (childName.equalsIgnoreCase("Transformation")) {
/*  854 */           ExpressionDOMParser parser = new ExpressionDOMParser(CommonFactoryFinder.getFilterFactory2(null));
/*  855 */           Expression tx = parser.expression(getFirstNonTextChild(child));
/*  856 */           ft.setTransformation(tx);
/*      */         } 
/*      */       } 
/*      */     } 
/*  860 */     if (sti.size() > 0)
/*  861 */       ft.setSemanticTypeIdentifiers(sti.<String>toArray(new String[0])); 
/*  863 */     ft.setRules(rules.<Rule>toArray(new Rule[0]));
/*  865 */     return ft;
/*      */   }
/*      */   
/*      */   private Node getFirstNonTextChild(Node node) {
/*  869 */     NodeList children = node.getChildNodes();
/*  870 */     int length = children.getLength();
/*  871 */     for (int i = 0; i < length; ) {
/*  872 */       Node child = children.item(i);
/*  874 */       if (child == null || child.getNodeType() != 1) {
/*      */         i++;
/*      */         continue;
/*      */       } 
/*  878 */       return child;
/*      */     } 
/*  881 */     return null;
/*      */   }
/*      */   
/*      */   protected Rule parseRule(Node ruleNode) {
/*  886 */     if (LOGGER.isLoggable(Level.FINEST))
/*  887 */       LOGGER.finest("Parsing rule " + ruleNode.getLocalName()); 
/*  890 */     Rule rule = this.factory.createRule();
/*  891 */     List<Symbolizer> symbolizers = new ArrayList<Symbolizer>();
/*  892 */     NodeList children = ruleNode.getChildNodes();
/*  893 */     int length = children.getLength();
/*  894 */     for (int i = 0; i < length; i++) {
/*  895 */       Node child = children.item(i);
/*  897 */       if (child != null && child.getNodeType() == 1) {
/*  900 */         String childName = child.getLocalName();
/*  901 */         if (childName == null)
/*  902 */           childName = child.getNodeName(); 
/*  905 */         if (childName.indexOf(':') != -1)
/*  907 */           childName = childName.substring(childName.indexOf(':') + 1); 
/*  910 */         if (LOGGER.isLoggable(Level.FINEST))
/*  911 */           LOGGER.finest("processing " + child.getLocalName()); 
/*  914 */         if (childName.equalsIgnoreCase("Name")) {
/*  915 */           rule.setName(getFirstChildValue(child));
/*  916 */         } else if (childName.equalsIgnoreCase("Title")) {
/*  917 */           rule.getDescription().setTitle(parseInternationalString(child));
/*  918 */         } else if (childName.equalsIgnoreCase("Abstract")) {
/*  919 */           rule.getDescription().setAbstract(parseInternationalString(child));
/*  920 */         } else if (childName.equalsIgnoreCase("MinScaleDenominator")) {
/*  921 */           rule.setMinScaleDenominator(Double.parseDouble(getFirstChildValue(child)));
/*  923 */         } else if (childName.equalsIgnoreCase("MaxScaleDenominator")) {
/*  924 */           rule.setMaxScaleDenominator(Double.parseDouble(getFirstChildValue(child)));
/*  926 */         } else if (childName.equalsIgnoreCase("Filter")) {
/*  927 */           Filter filter = parseFilter(child);
/*  928 */           rule.setFilter(filter);
/*  929 */         } else if (childName.equalsIgnoreCase("ElseFilter")) {
/*  930 */           rule.setElseFilter(true);
/*  931 */         } else if (childName.equalsIgnoreCase("LegendGraphic")) {
/*  932 */           findElements((Element)child, "Graphic");
/*  933 */           NodeList g = findElements((Element)child, "Graphic");
/*  934 */           List<Graphic> legends = new ArrayList<Graphic>();
/*  935 */           int l = g.getLength();
/*  936 */           for (int k = 0; k < l; k++)
/*  937 */             legends.add(parseGraphic(g.item(k))); 
/*  940 */           rule.setLegendGraphic(legends.<Graphic>toArray(new Graphic[0]));
/*  941 */         } else if (childName.equalsIgnoreCase("LineSymbolizer")) {
/*  942 */           symbolizers.add(parseLineSymbolizer(child));
/*  943 */         } else if (childName.equalsIgnoreCase("PolygonSymbolizer")) {
/*  944 */           symbolizers.add(parsePolygonSymbolizer(child));
/*  945 */         } else if (childName.equalsIgnoreCase("PointSymbolizer")) {
/*  946 */           symbolizers.add(parsePointSymbolizer(child));
/*  947 */         } else if (childName.equalsIgnoreCase("TextSymbolizer")) {
/*  948 */           symbolizers.add(parseTextSymbolizer(child));
/*  949 */         } else if (childName.equalsIgnoreCase("RasterSymbolizer")) {
/*  950 */           symbolizers.add(parseRasterSymbolizer(child));
/*      */         } 
/*      */       } 
/*      */     } 
/*  954 */     rule.setSymbolizers(symbolizers.<Symbolizer>toArray(new Symbolizer[0]));
/*  956 */     return rule;
/*      */   }
/*      */   
/*      */   private InternationalString parseInternationalString(Node root) {
/*  969 */     if (LOGGER.isLoggable(Level.FINEST))
/*  970 */       LOGGER.finest("parsingInternationalString " + root); 
/*  973 */     NodeList children = root.getChildNodes();
/*  974 */     int length = children.getLength();
/*  975 */     StringBuilder text = new StringBuilder();
/*  977 */     Map<String, String> translations = new HashMap<String, String>();
/*  979 */     for (int i = 0; i < length; i++) {
/*  980 */       Node child = children.item(i);
/*  982 */       if (child != null)
/*  984 */         if (child.getNodeType() == 3 || child.getNodeType() == 4) {
/*  987 */           String value = child.getNodeValue();
/*  988 */           if (value != null)
/*  990 */             text.append(value.trim()); 
/*  991 */         } else if (child.getNodeType() == 1) {
/*  993 */           if (LOGGER.isLoggable(Level.FINEST))
/*  994 */             LOGGER.finest("about to parse " + child.getLocalName()); 
/*  996 */           Element element = (Element)child;
/*  997 */           if (element.getTagName().equalsIgnoreCase("localized")) {
/*  998 */             String lang = element.getAttribute("lang");
/*  999 */             String translation = getFirstChildValue(element);
/* 1001 */             translations.put(lang, translation);
/*      */           } 
/*      */         }  
/*      */     } 
/* 1007 */     if (translations.size() > 0) {
/* 1008 */       GrowableInternationalString intString = new GrowableInternationalString(text.toString()) {
/*      */           public String toString() {
/* 1013 */             return toString(null);
/*      */           }
/*      */         };
/* 1017 */       for (String lang : translations.keySet())
/* 1018 */         intString.add("", "_" + lang, translations.get(lang)); 
/* 1020 */       return (InternationalString)intString;
/*      */     } 
/* 1022 */     String simpleText = getFirstChildValue(root);
/* 1023 */     return (InternationalString)new SimpleInternationalString((simpleText == null) ? "" : simpleText);
/*      */   }
/*      */   
/*      */   protected Filter parseFilter(Node child) {
/* 1034 */     Node firstChild = child.getFirstChild();
/* 1035 */     while (firstChild != null && firstChild.getNodeType() != 1)
/* 1037 */       firstChild = firstChild.getNextSibling(); 
/* 1039 */     Filter filter = FilterDOMParser.parseFilter(firstChild);
/* 1040 */     return filter;
/*      */   }
/*      */   
/*      */   protected LineSymbolizer parseLineSymbolizer(Node root) {
/* 1052 */     LineSymbolizer symbol = this.factory.createLineSymbolizer();
/* 1054 */     NamedNodeMap namedNodeMap = root.getAttributes();
/* 1055 */     Node uomNode = namedNodeMap.getNamedItem("uom");
/* 1056 */     if (uomNode != null) {
/* 1058 */       UomOgcMapping uomMapping = UomOgcMapping.get(uomNode.getNodeValue());
/* 1059 */       symbol.setUnitOfMeasure(uomMapping.getUnit());
/*      */     } 
/* 1062 */     NodeList children = root.getChildNodes();
/* 1063 */     int length = children.getLength();
/* 1064 */     for (int i = 0; i < length; i++) {
/* 1065 */       Node child = children.item(i);
/* 1067 */       if (child != null && child.getNodeType() == 1) {
/* 1070 */         String childName = child.getLocalName();
/* 1071 */         if (childName == null)
/* 1072 */           childName = child.getNodeName(); 
/* 1074 */         if (childName.equalsIgnoreCase("Geometry")) {
/* 1075 */           symbol.setGeometry(parseGeometry(child));
/* 1076 */         } else if (childName.equalsIgnoreCase("Stroke")) {
/* 1077 */           symbol.setStroke(parseStroke(child));
/* 1078 */         } else if (childName.equalsIgnoreCase("VendorOption")) {
/* 1079 */           parseVendorOption(symbol, child);
/* 1080 */         } else if (childName.equalsIgnoreCase("PerpendicularOffset")) {
/* 1081 */           String offsetValue = getFirstChildValue(child);
/* 1082 */           symbol.setPerpendicularOffset((Expression)this.ff.literal(Double.parseDouble(offsetValue)));
/*      */         } 
/*      */       } 
/*      */     } 
/* 1086 */     return symbol;
/*      */   }
/*      */   
/*      */   protected PolygonSymbolizer parsePolygonSymbolizer(Node root) {
/* 1098 */     PolygonSymbolizer symbol = this.factory.createPolygonSymbolizer();
/* 1099 */     symbol.setFill((Fill)null);
/* 1100 */     symbol.setStroke((Stroke)null);
/* 1102 */     NamedNodeMap namedNodeMap = root.getAttributes();
/* 1103 */     Node uomNode = namedNodeMap.getNamedItem("uom");
/* 1104 */     if (uomNode != null) {
/* 1106 */       UomOgcMapping uomMapping = UomOgcMapping.get(uomNode.getNodeValue());
/* 1107 */       symbol.setUnitOfMeasure(uomMapping.getUnit());
/*      */     } 
/* 1110 */     NodeList children = root.getChildNodes();
/* 1111 */     int length = children.getLength();
/* 1112 */     for (int i = 0; i < length; i++) {
/* 1113 */       Node child = children.item(i);
/* 1115 */       if (child != null && child.getNodeType() == 1) {
/* 1118 */         String childName = child.getLocalName();
/* 1119 */         if (childName == null)
/* 1120 */           childName = child.getNodeName(); 
/* 1122 */         if (childName.equalsIgnoreCase("Geometry")) {
/* 1123 */           symbol.setGeometry(parseGeometry(child));
/* 1124 */         } else if (childName.equalsIgnoreCase("Stroke")) {
/* 1125 */           symbol.setStroke(parseStroke(child));
/* 1126 */         } else if (childName.equalsIgnoreCase("Fill")) {
/* 1127 */           symbol.setFill(parseFill(child));
/* 1128 */         } else if (childName.equalsIgnoreCase("VendorOption")) {
/* 1129 */           parseVendorOption(symbol, child);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1133 */     return symbol;
/*      */   }
/*      */   
/*      */   protected TextSymbolizer parseTextSymbolizer(Node root) {
/* 1145 */     TextSymbolizer symbol = this.factory.createTextSymbolizer();
/* 1146 */     symbol.setFill((Fill)null);
/* 1148 */     NamedNodeMap namedNodeMap = root.getAttributes();
/* 1149 */     Node uomNode = namedNodeMap.getNamedItem("uom");
/* 1150 */     if (uomNode != null) {
/* 1152 */       UomOgcMapping uomMapping = UomOgcMapping.get(uomNode.getNodeValue());
/* 1153 */       symbol.setUnitOfMeasure(uomMapping.getUnit());
/*      */     } 
/* 1156 */     List<Font> fonts = new ArrayList<Font>();
/* 1157 */     NodeList children = root.getChildNodes();
/* 1158 */     int length = children.getLength();
/* 1159 */     for (int i = 0; i < length; i++) {
/* 1160 */       Node child = children.item(i);
/* 1162 */       if (child != null && child.getNodeType() == 1) {
/* 1165 */         String childName = child.getLocalName();
/* 1166 */         if (childName == null)
/* 1167 */           childName = child.getNodeName(); 
/* 1169 */         if (childName.equalsIgnoreCase("Geometry")) {
/* 1170 */           symbol.setGeometry(parseGeometry(child));
/* 1171 */         } else if (childName.equalsIgnoreCase("Fill")) {
/* 1172 */           symbol.setFill(parseFill(child));
/* 1173 */         } else if (childName.equalsIgnoreCase("Label")) {
/* 1174 */           if (LOGGER.isLoggable(Level.FINEST))
/* 1175 */             LOGGER.finest("parsing label " + child.getNodeValue()); 
/* 1178 */           symbol.setLabel(parseCssParameter(child, false));
/* 1179 */           if (symbol.getLabel() == null && 
/* 1180 */             LOGGER.isLoggable(Level.WARNING))
/* 1181 */             LOGGER.warning("parsing TextSymbolizer node - couldnt find anything in the Label element!"); 
/*      */         } 
/* 1186 */         if (childName.equalsIgnoreCase("Font")) {
/* 1187 */           fonts.add(parseFont(child));
/* 1188 */         } else if (childName.equalsIgnoreCase("LabelPlacement")) {
/* 1189 */           symbol.setPlacement(parseLabelPlacement(child));
/* 1190 */         } else if (childName.equalsIgnoreCase("Halo")) {
/* 1191 */           symbol.setHalo(parseHalo(child));
/* 1192 */         } else if (childName.equalsIgnoreCase("Graphic")) {
/* 1193 */           if (LOGGER.isLoggable(Level.FINEST))
/* 1194 */             LOGGER.finest("Parsing non-standard Graphic in TextSymbolizer"); 
/* 1195 */           if (symbol instanceof TextSymbolizer2)
/* 1196 */             ((TextSymbolizer2)symbol).setGraphic(parseGraphic(child)); 
/* 1198 */         } else if (childName.equalsIgnoreCase("Snippet")) {
/* 1199 */           if (LOGGER.isLoggable(Level.FINEST))
/* 1200 */             LOGGER.finest("Parsing non-standard Abstract in TextSymbolizer"); 
/* 1201 */           if (symbol instanceof TextSymbolizer2)
/* 1202 */             ((TextSymbolizer2)symbol).setSnippet(parseCssParameter(child, false)); 
/* 1203 */         } else if (childName.equalsIgnoreCase("FeatureDescription")) {
/* 1204 */           if (LOGGER.isLoggable(Level.FINEST))
/* 1205 */             LOGGER.finest("Parsing non-standard Description in TextSymbolizer"); 
/* 1206 */           if (symbol instanceof TextSymbolizer2)
/* 1207 */             ((TextSymbolizer2)symbol).setFeatureDescription(parseCssParameter(child, false)); 
/* 1209 */         } else if (childName.equalsIgnoreCase("OtherText")) {
/* 1210 */           if (LOGGER.isLoggable(Level.FINEST))
/* 1211 */             LOGGER.finest("Parsing non-standard OtherText in TextSymbolizer"); 
/* 1212 */           if (symbol instanceof TextSymbolizer2)
/* 1213 */             ((TextSymbolizer2)symbol).setOtherText(parseOtherText(child)); 
/* 1214 */         } else if (childName.equalsIgnoreCase("priority")) {
/* 1215 */           symbol.setPriority(parseCssParameter(child));
/* 1216 */         } else if (childName.equalsIgnoreCase("VendorOption")) {
/* 1217 */           parseVendorOption(symbol, child);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1222 */     symbol.setFonts(fonts.<Font>toArray(new Font[0]));
/* 1224 */     return symbol;
/*      */   }
/*      */   
/*      */   protected OtherText parseOtherText(Node root) {
/* 1229 */     OtherText ot = new OtherTextImpl();
/* 1230 */     Node targetAttribute = root.getAttributes().getNamedItem("target");
/* 1231 */     if (targetAttribute == null)
/* 1232 */       throw new IllegalArgumentException("OtherLocation does not have the required 'target' attribute"); 
/* 1234 */     String target = targetAttribute.getNodeValue();
/* 1235 */     Expression text = parseCssParameter(root, true);
/* 1236 */     ot.setTarget(target);
/* 1237 */     ot.setText(text);
/* 1238 */     return ot;
/*      */   }
/*      */   
/*      */   private void parseVendorOption(Symbolizer symbol, Node child) {
/* 1249 */     String key = child.getAttributes().getNamedItem("name").getNodeValue();
/* 1250 */     String value = getFirstChildValue(child);
/* 1252 */     symbol.getOptions().put(key, value);
/*      */   }
/*      */   
/*      */   protected RasterSymbolizer parseRasterSymbolizer(Node root) {
/* 1264 */     RasterSymbolizer symbol = this.factory.getDefaultRasterSymbolizer();
/* 1266 */     NamedNodeMap namedNodeMap = root.getAttributes();
/* 1267 */     Node uomNode = namedNodeMap.getNamedItem("uom");
/* 1268 */     if (uomNode != null) {
/* 1270 */       UomOgcMapping uomMapping = UomOgcMapping.get(uomNode.getNodeValue());
/* 1271 */       symbol.setUnitOfMeasure(uomMapping.getUnit());
/*      */     } 
/* 1274 */     NodeList children = root.getChildNodes();
/* 1275 */     int length = children.getLength();
/* 1276 */     for (int i = 0; i < length; i++) {
/* 1277 */       Node child = children.item(i);
/* 1278 */       if (child != null && child.getNodeType() == 1) {
/* 1281 */         String childName = child.getLocalName();
/* 1282 */         if (childName == null)
/* 1283 */           childName = child.getNodeName(); 
/* 1285 */         if (childName.equalsIgnoreCase("Geometry"))
/* 1286 */           symbol.setGeometry(parseGeometry(child)); 
/* 1288 */         if (childName.equalsIgnoreCase("Opacity")) {
/*      */           try {
/* 1290 */             String opacityString = getFirstChildValue(child);
/* 1291 */             Expression opacity = parseParameterValueExpression(child, false);
/* 1292 */             symbol.setOpacity(opacity);
/* 1293 */           } catch (Throwable e) {
/* 1294 */             if (LOGGER.isLoggable(Level.WARNING))
/* 1295 */               LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e); 
/*      */           } 
/* 1297 */         } else if (childName.equalsIgnoreCase("ChannelSelection")) {
/* 1298 */           symbol.setChannelSelection(parseChannelSelection(child));
/* 1299 */         } else if (childName.equalsIgnoreCase("OverlapBehavior")) {
/*      */           try {
/* 1301 */             String overlapString = child.getFirstChild().getLocalName();
/* 1302 */             symbol.setOverlap((Expression)this.ff.literal(overlapString));
/* 1303 */           } catch (Throwable e) {
/* 1304 */             if (LOGGER.isLoggable(Level.WARNING))
/* 1305 */               LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e); 
/*      */           } 
/* 1307 */         } else if (childName.equalsIgnoreCase("ColorMap")) {
/* 1308 */           symbol.setColorMap(parseColorMap(child));
/* 1309 */         } else if (childName.equalsIgnoreCase("ContrastEnhancement")) {
/* 1310 */           symbol.setContrastEnhancement(parseContrastEnhancement(child));
/* 1311 */         } else if (childName.equalsIgnoreCase("ShadedRelief")) {
/* 1312 */           symbol.setShadedRelief(parseShadedRelief(child));
/* 1313 */         } else if (childName.equalsIgnoreCase("ImageOutline")) {
/* 1314 */           symbol.setImageOutline(parseLineSymbolizer(child));
/*      */         } 
/*      */       } 
/*      */     } 
/* 1318 */     return symbol;
/*      */   }
/*      */   
/*      */   Expression parseParameterValueExpression(Node root, boolean mixedText) {
/* 1338 */     ExpressionDOMParser parser = new ExpressionDOMParser((FilterFactory2)this.ff);
/* 1339 */     Expression expr = parser.expression(root);
/* 1340 */     if (expr != null)
/* 1341 */       return expr; 
/* 1342 */     NodeList children = root.getChildNodes();
/* 1344 */     if (children.getLength() == 1 && root.getFirstChild() instanceof org.w3c.dom.CharacterData) {
/* 1345 */       Node textNode = root.getFirstChild();
/* 1346 */       String text = textNode.getNodeValue();
/* 1347 */       return (Expression)this.ff.literal(text.trim());
/*      */     } 
/* 1349 */     List<Expression> expressionList = new ArrayList<Expression>();
/* 1350 */     for (int index = 0; index < children.getLength(); index++) {
/* 1351 */       Node child = children.item(index);
/* 1352 */       if (child instanceof org.w3c.dom.CharacterData) {
/* 1353 */         if (mixedText) {
/* 1354 */           String text = child.getNodeValue();
/* 1355 */           Literal literal = this.ff.literal(text);
/* 1356 */           expressionList.add(literal);
/*      */         } 
/*      */       } else {
/* 1359 */         Expression childExpr = parser.expression(child);
/* 1360 */         if (childExpr != null)
/* 1361 */           expressionList.add(childExpr); 
/*      */       } 
/*      */     } 
/* 1365 */     if (expressionList.isEmpty())
/* 1366 */       return Expression.NIL; 
/* 1367 */     if (expressionList.size() == 1)
/* 1368 */       return expressionList.get(0); 
/* 1369 */     if (expressionList.size() == 2) {
/* 1370 */       Expression[] arrayOfExpression = expressionList.<Expression>toArray(new Expression[0]);
/* 1371 */       return (Expression)this.ff.function("strConcat", arrayOfExpression);
/*      */     } 
/* 1373 */     Expression[] expressionArray = expressionList.<Expression>toArray(new Expression[0]);
/* 1374 */     return (Expression)this.ff.function("Concatenate", expressionArray);
/*      */   }
/*      */   
/*      */   protected ColorMapEntry parseColorMapEntry(Node root) {
/* 1380 */     ColorMapEntry symbol = this.factory.createColorMapEntry();
/* 1381 */     NamedNodeMap atts = root.getAttributes();
/* 1382 */     if (atts.getNamedItem("label") != null)
/* 1383 */       symbol.setLabel(atts.getNamedItem("label").getNodeValue()); 
/* 1385 */     if (atts.getNamedItem("color") != null)
/* 1386 */       symbol.setColor((Expression)this.ff.literal(atts.getNamedItem("color").getNodeValue())); 
/* 1388 */     if (atts.getNamedItem("opacity") != null)
/* 1389 */       symbol.setOpacity((Expression)this.ff.literal(atts.getNamedItem("opacity").getNodeValue())); 
/* 1391 */     if (atts.getNamedItem("quantity") != null)
/* 1392 */       symbol.setQuantity((Expression)this.ff.literal(atts.getNamedItem("quantity").getNodeValue())); 
/* 1397 */     return symbol;
/*      */   }
/*      */   
/*      */   protected ColorMap parseColorMap(Node root) {
/* 1402 */     ColorMap symbol = this.factory.createColorMap();
/* 1404 */     if (root.hasAttributes()) {
/* 1406 */       NamedNodeMap atts = root.getAttributes();
/* 1407 */       Node typeAtt = atts.getNamedItem("type");
/* 1408 */       if (typeAtt != null) {
/* 1409 */         String type = typeAtt.getNodeValue();
/* 1411 */         if ("ramp".equalsIgnoreCase(type)) {
/* 1412 */           symbol.setType(1);
/* 1413 */         } else if ("intervals".equalsIgnoreCase(type)) {
/* 1414 */           symbol.setType(2);
/* 1415 */         } else if ("values".equalsIgnoreCase(type)) {
/* 1416 */           symbol.setType(3);
/* 1417 */         } else if (LOGGER.isLoggable(Level.FINE)) {
/* 1418 */           LOGGER.fine(Errors.format(58, "ColorMapType", type));
/*      */         } 
/*      */       } 
/* 1423 */       typeAtt = atts.getNamedItem("extended");
/* 1424 */       if (typeAtt != null) {
/* 1425 */         String type = typeAtt.getNodeValue();
/* 1427 */         if ("true".equalsIgnoreCase(type)) {
/* 1428 */           symbol.setExtendedColors(true);
/* 1429 */         } else if ("false".equalsIgnoreCase(type)) {
/* 1430 */           symbol.setExtendedColors(false);
/* 1431 */         } else if (LOGGER.isLoggable(Level.FINE)) {
/* 1432 */           LOGGER.fine(Errors.format(58, "Extended", type));
/*      */         } 
/*      */       } 
/*      */     } 
/* 1437 */     NodeList children = root.getChildNodes();
/* 1438 */     int length = children.getLength();
/* 1439 */     for (int i = 0; i < length; i++) {
/* 1440 */       Node child = children.item(i);
/* 1442 */       if (child != null && child.getNodeType() == 1) {
/* 1445 */         String childName = child.getLocalName();
/* 1446 */         if (childName == null)
/* 1447 */           childName = child.getNodeName(); 
/* 1450 */         if (childName.equalsIgnoreCase("ColorMapEntry"))
/* 1451 */           symbol.addColorMapEntry(parseColorMapEntry(child)); 
/*      */       } 
/*      */     } 
/* 1455 */     return symbol;
/*      */   }
/*      */   
/*      */   protected SelectedChannelType parseSelectedChannel(Node root) {
/* 1460 */     SelectedChannelType symbol = new SelectedChannelTypeImpl();
/* 1462 */     NodeList children = root.getChildNodes();
/* 1463 */     int length = children.getLength();
/* 1464 */     for (int i = 0; i < length; i++) {
/* 1465 */       Node child = children.item(i);
/* 1467 */       if (child != null && child.getNodeType() == 1) {
/* 1470 */         String childName = child.getLocalName();
/* 1472 */         if (childName == null) {
/* 1473 */           childName = child.getNodeName();
/* 1474 */         } else if (childName.equalsIgnoreCase("SourceChannelName")) {
/* 1475 */           if (child.getFirstChild() != null && child.getFirstChild().getNodeType() == 3)
/* 1477 */             symbol.setChannelName(getFirstChildValue(child)); 
/* 1478 */         } else if (childName.equalsIgnoreCase("ContrastEnhancement")) {
/* 1479 */           symbol.setContrastEnhancement(parseContrastEnhancement(child));
/*      */         } 
/*      */       } 
/*      */     } 
/* 1490 */     return symbol;
/*      */   }
/*      */   
/*      */   protected ChannelSelection parseChannelSelection(Node root) {
/* 1495 */     List<SelectedChannelType> channels = new ArrayList<SelectedChannelType>();
/* 1497 */     NodeList children = root.getChildNodes();
/* 1498 */     int length = children.getLength();
/* 1499 */     for (int i = 0; i < length; i++) {
/* 1500 */       Node child = children.item(i);
/* 1502 */       if (child != null && child.getNodeType() == 1) {
/* 1505 */         String childName = child.getLocalName();
/* 1506 */         if (childName == null) {
/* 1507 */           childName = child.getNodeName();
/* 1508 */         } else if (childName.equalsIgnoreCase("GrayChannel")) {
/* 1509 */           channels.add(parseSelectedChannel(child));
/* 1510 */         } else if (childName.equalsIgnoreCase("RedChannel")) {
/* 1511 */           channels.add(parseSelectedChannel(child));
/* 1512 */         } else if (childName.equalsIgnoreCase("GreenChannel")) {
/* 1513 */           channels.add(parseSelectedChannel(child));
/* 1514 */         } else if (childName.equalsIgnoreCase("BlueChannel")) {
/* 1515 */           channels.add(parseSelectedChannel(child));
/*      */         } 
/*      */       } 
/*      */     } 
/* 1519 */     ChannelSelection dap = this.factory.createChannelSelection(channels.<SelectedChannelType>toArray(new SelectedChannelType[channels.size()]));
/* 1522 */     return dap;
/*      */   }
/*      */   
/*      */   protected ContrastEnhancement parseContrastEnhancement(Node root) {
/* 1527 */     ContrastEnhancement symbol = new ContrastEnhancementImpl();
/* 1529 */     NodeList children = root.getChildNodes();
/* 1530 */     int length = children.getLength();
/* 1531 */     for (int i = 0; i < length; i++) {
/* 1532 */       Node child = children.item(i);
/* 1534 */       if (child != null && child.getNodeType() == 1) {
/* 1537 */         String childName = child.getLocalName();
/* 1538 */         if (childName == null)
/* 1539 */           childName = child.getNodeName(); 
/* 1542 */         if (childName.equalsIgnoreCase("Normalize")) {
/* 1543 */           symbol.setNormalize();
/* 1544 */         } else if (childName.equalsIgnoreCase("Histogram")) {
/* 1545 */           symbol.setHistogram();
/* 1546 */         } else if (childName.equalsIgnoreCase("Logarithmic")) {
/* 1547 */           symbol.setLogarithmic();
/* 1548 */         } else if (childName.equalsIgnoreCase("Exponential")) {
/* 1549 */           symbol.setExponential();
/* 1550 */         } else if (childName.equalsIgnoreCase("GammaValue")) {
/*      */           try {
/* 1552 */             String gammaString = getFirstChildValue(child);
/* 1553 */             symbol.setGammaValue((Expression)this.ff.literal(Double.parseDouble(gammaString)));
/* 1554 */           } catch (Exception e) {
/* 1555 */             if (LOGGER.isLoggable(Level.WARNING))
/* 1556 */               LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e); 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1561 */     return symbol;
/*      */   }
/*      */   
/*      */   protected ShadedRelief parseShadedRelief(Node root) {
/* 1566 */     ShadedRelief symbol = new ShadedReliefImpl();
/* 1568 */     NodeList children = root.getChildNodes();
/* 1569 */     int length = children.getLength();
/* 1570 */     for (int i = 0; i < length; i++) {
/* 1571 */       Node child = children.item(i);
/* 1573 */       if (child != null && child.getNodeType() == 1) {
/* 1576 */         String childName = child.getLocalName();
/* 1577 */         if (childName == null)
/* 1578 */           childName = child.getNodeName(); 
/* 1580 */         if ("BrightnessOnly".equalsIgnoreCase(childName)) {
/* 1581 */           symbol.setBrightnessOnly(Boolean.getBoolean(getFirstChildValue(child)));
/* 1582 */         } else if ("ReliefFactor".equalsIgnoreCase(childName)) {
/*      */           try {
/* 1584 */             String reliefString = getFirstChildValue(child);
/* 1585 */             Expression relief = ExpressionDOMParser.parseExpression(child);
/* 1586 */             symbol.setReliefFactor(relief);
/* 1587 */           } catch (Exception e) {
/* 1588 */             if (LOGGER.isLoggable(Level.WARNING))
/* 1589 */               LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e); 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1594 */     return symbol;
/*      */   }
/*      */   
/*      */   protected PointSymbolizer parsePointSymbolizer(Node root) {
/* 1606 */     PointSymbolizer symbol = this.factory.getDefaultPointSymbolizer();
/* 1609 */     NamedNodeMap namedNodeMap = root.getAttributes();
/* 1610 */     Node uomNode = namedNodeMap.getNamedItem("uom");
/* 1611 */     if (uomNode != null) {
/* 1613 */       UomOgcMapping uomMapping = UomOgcMapping.get(uomNode.getNodeValue());
/* 1614 */       symbol.setUnitOfMeasure(uomMapping.getUnit());
/*      */     } 
/* 1617 */     NodeList children = root.getChildNodes();
/* 1618 */     int length = children.getLength();
/* 1619 */     for (int i = 0; i < length; i++) {
/* 1620 */       Node child = children.item(i);
/* 1622 */       if (child != null && child.getNodeType() == 1) {
/* 1625 */         String childName = child.getLocalName();
/* 1626 */         if (childName == null)
/* 1627 */           childName = child.getNodeName(); 
/* 1630 */         if (childName.equalsIgnoreCase("Geometry")) {
/* 1631 */           symbol.setGeometry(parseGeometry(child));
/* 1632 */         } else if (childName.equalsIgnoreCase("Graphic")) {
/* 1633 */           symbol.setGraphic(parseGraphic(child));
/* 1635 */         } else if (childName.equalsIgnoreCase("VendorOption")) {
/* 1636 */           parseVendorOption(symbol, child);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1640 */     return symbol;
/*      */   }
/*      */   
/*      */   protected Graphic parseGraphic(Node root) {
/* 1645 */     if (LOGGER.isLoggable(Level.FINEST))
/* 1646 */       LOGGER.finest("processing graphic " + root); 
/* 1649 */     Graphic graphic = this.factory.getDefaultGraphic();
/* 1651 */     NodeList children = root.getChildNodes();
/* 1652 */     int length = children.getLength();
/* 1653 */     boolean firstGraphic = true;
/* 1654 */     for (int i = 0; i < length; i++) {
/* 1655 */       Node child = children.item(i);
/* 1657 */       if (child != null && child.getNodeType() == 1) {
/* 1660 */         String childName = child.getLocalName();
/* 1661 */         if (childName == null)
/* 1662 */           childName = child.getNodeName(); 
/* 1665 */         if (childName.equalsIgnoreCase("ExternalGraphic")) {
/* 1666 */           if (LOGGER.isLoggable(Level.FINEST))
/* 1667 */             LOGGER.finest("parsing extgraphic " + child); 
/* 1668 */           if (firstGraphic) {
/* 1669 */             graphic.graphicalSymbols().clear();
/* 1670 */             firstGraphic = false;
/*      */           } 
/* 1672 */           graphic.graphicalSymbols().add(parseExternalGraphic(child));
/* 1673 */         } else if (childName.equalsIgnoreCase("Mark")) {
/* 1674 */           if (firstGraphic) {
/* 1675 */             graphic.graphicalSymbols().clear();
/* 1676 */             firstGraphic = false;
/*      */           } 
/* 1678 */           graphic.graphicalSymbols().add(parseMark(child));
/* 1679 */         } else if (childName.equalsIgnoreCase("Opacity")) {
/* 1680 */           graphic.setOpacity(parseCssParameter(child));
/* 1681 */         } else if (childName.equalsIgnoreCase("size")) {
/* 1682 */           graphic.setSize(parseCssParameter(child));
/* 1683 */         } else if (childName.equalsIgnoreCase("displacement")) {
/* 1684 */           graphic.setDisplacement(parseDisplacement(child));
/* 1685 */         } else if (childName.equalsIgnoreCase("rotation")) {
/* 1686 */           graphic.setRotation(parseCssParameter(child));
/*      */         } 
/*      */       } 
/*      */     } 
/* 1690 */     return graphic;
/*      */   }
/*      */   
/*      */   protected String parseGeometryName(Node root) {
/* 1695 */     Expression result = parseGeometry(root);
/* 1696 */     if (result instanceof PropertyName)
/* 1697 */       return ((PropertyName)result).getPropertyName(); 
/* 1699 */     return null;
/*      */   }
/*      */   
/*      */   protected Expression parseGeometry(Node root) {
/* 1704 */     if (LOGGER.isLoggable(Level.FINEST) && 
/* 1705 */       LOGGER.isLoggable(Level.FINEST))
/* 1706 */       LOGGER.finest("parsing GeometryExpression"); 
/* 1709 */     return parseCssParameter(root);
/*      */   }
/*      */   
/*      */   protected Mark parseMark(Node root) {
/* 1714 */     if (LOGGER.isLoggable(Level.FINEST))
/* 1715 */       LOGGER.finest("parsing mark"); 
/* 1718 */     Mark mark = this.factory.createMark();
/* 1719 */     mark.setFill(null);
/* 1720 */     mark.setStroke(null);
/* 1722 */     NodeList children = root.getChildNodes();
/* 1723 */     int length = children.getLength();
/* 1724 */     for (int i = 0; i < length; i++) {
/* 1725 */       Node child = children.item(i);
/* 1727 */       if (child != null && child.getNodeType() == 1) {
/* 1730 */         String childName = child.getLocalName();
/* 1731 */         if (childName == null)
/* 1732 */           childName = child.getNodeName(); 
/* 1735 */         if (childName.equalsIgnoreCase("Stroke")) {
/* 1736 */           mark.setStroke(parseStroke(child));
/* 1737 */         } else if (childName.equalsIgnoreCase("Fill")) {
/* 1738 */           mark.setFill(parseFill(child));
/* 1739 */         } else if (childName.equalsIgnoreCase("WellKnownName")) {
/* 1740 */           if (LOGGER.isLoggable(Level.FINEST))
/* 1741 */             LOGGER.finest("setting mark to " + getFirstChildValue(child)); 
/* 1742 */           mark.setWellKnownName(parseCssParameter(child));
/*      */         } 
/*      */       } 
/*      */     } 
/* 1746 */     return mark;
/*      */   }
/*      */   
/*      */   protected ExternalGraphic parseExternalGraphic(Node root) {
/*      */     ExternalGraphic extgraph;
/* 1751 */     if (LOGGER.isLoggable(Level.FINEST))
/* 1752 */       LOGGER.finest("processing external graphic "); 
/* 1755 */     String format = "";
/* 1756 */     String uri = "";
/* 1757 */     String content = null;
/* 1758 */     Map<String, Object> paramList = new HashMap<String, Object>();
/* 1760 */     NodeList children = root.getChildNodes();
/* 1761 */     int length = children.getLength();
/* 1762 */     for (int i = 0; i < length; i++) {
/* 1763 */       Node child = children.item(i);
/* 1765 */       if (child != null && child.getNodeType() == 1) {
/* 1768 */         String childName = child.getLocalName();
/* 1769 */         if (childName == null)
/* 1770 */           childName = child.getNodeName(); 
/* 1772 */         if (childName.equalsIgnoreCase("InlineContent")) {
/* 1773 */           String contentEncoding = getAttribute(child, "encoding");
/* 1774 */           if (LOGGER.isLoggable(Level.FINEST))
/* 1775 */             LOGGER.finest("inline content with encoding " + contentEncoding); 
/* 1777 */           if ("base64".equals(contentEncoding)) {
/* 1778 */             content = getFirstChildValue(child);
/*      */           } else {
/* 1780 */             content = "";
/* 1781 */             if (LOGGER.isLoggable(Level.WARNING))
/* 1782 */               LOGGER.warning("could not process <" + contentEncoding + "> content encoding"); 
/*      */           } 
/* 1785 */         } else if (childName.equalsIgnoreCase("OnLineResource")) {
/* 1786 */           uri = parseOnlineResource(child);
/*      */         } 
/* 1789 */         if (childName.equalsIgnoreCase("format")) {
/* 1790 */           LOGGER.finest("format child is " + child);
/* 1791 */           LOGGER.finest("seting ExtGraph format " + getFirstChildValue(child));
/* 1792 */           format = getFirstChildValue(child);
/* 1793 */         } else if (childName.equalsIgnoreCase("customProperty")) {
/* 1794 */           if (LOGGER.isLoggable(Level.FINEST))
/* 1795 */             LOGGER.finest("custom child is " + child); 
/* 1796 */           String propName = child.getAttributes().getNamedItem("name").getNodeValue();
/* 1797 */           if (LOGGER.isLoggable(Level.FINEST))
/* 1798 */             LOGGER.finest("seting custom property " + propName + " to " + getFirstChildValue(child)); 
/* 1800 */           Expression value = parseCssParameter(child);
/* 1801 */           paramList.put(propName, value);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1807 */     if (content != null) {
/* 1808 */       Icon icon = null;
/* 1809 */       if (content.length() > 0)
/*      */         try {
/* 1811 */           icon = parseIcon(content);
/* 1813 */         } catch (IOException e) {
/* 1814 */           if (LOGGER.isLoggable(Level.WARNING))
/* 1815 */             LOGGER.log(Level.WARNING, "could not parse graphic inline content: " + content, e); 
/*      */         }  
/* 1820 */       if (icon == null) {
/* 1821 */         LOGGER.warning("returning empty icon");
/* 1822 */         icon = EmptyIcon.INSTANCE;
/*      */       } 
/* 1825 */       extgraph = this.factory.createExternalGraphic(icon, format);
/*      */     } else {
/* 1827 */       URL url = this.onlineResourceLocator.locateResource(uri);
/* 1828 */       if (url == null) {
/* 1829 */         extgraph = this.factory.createExternalGraphic(uri, format);
/*      */       } else {
/* 1831 */         extgraph = this.factory.createExternalGraphic(url, format);
/*      */       } 
/*      */     } 
/* 1834 */     extgraph.setCustomProperties(paramList);
/* 1835 */     return extgraph;
/*      */   }
/*      */   
/*      */   protected String parseOnlineResource(Node root) {
/* 1840 */     Element param = (Element)root;
/* 1841 */     NamedNodeMap map = param.getAttributes();
/* 1842 */     int length = map.getLength();
/* 1843 */     LOGGER.finest("attributes " + map.toString());
/* 1845 */     for (int k = 0; k < length; k++) {
/* 1846 */       String res = map.item(k).getNodeValue();
/* 1847 */       String name = map.item(k).getNodeName();
/* 1851 */       if (LOGGER.isLoggable(Level.FINEST))
/* 1852 */         LOGGER.finest("processing attribute " + name + "=" + res); 
/* 1856 */       if (name.equalsIgnoreCase("xlink:href") || name.equalsIgnoreCase("href")) {
/* 1857 */         if (LOGGER.isLoggable(Level.FINEST))
/* 1858 */           LOGGER.finest("seting ExtGraph uri " + res); 
/* 1859 */         return res;
/*      */       } 
/*      */     } 
/* 1862 */     return null;
/*      */   }
/*      */   
/*      */   protected Stroke parseStroke(Node root) {
/* 1867 */     Stroke stroke = this.factory.getDefaultStroke();
/* 1868 */     NodeList list = findElements((Element)root, "GraphicFill");
/* 1869 */     int length = list.getLength();
/* 1870 */     if (length > 0) {
/* 1871 */       if (LOGGER.isLoggable(Level.FINEST))
/* 1872 */         LOGGER.finest("stroke: found a graphic fill " + list.item(0)); 
/* 1874 */       NodeList kids = list.item(0).getChildNodes();
/* 1876 */       for (int j = 0; j < kids.getLength(); j++) {
/* 1877 */         Node child = kids.item(j);
/* 1879 */         if (child != null && child.getNodeType() == 1) {
/* 1882 */           String childName = child.getLocalName();
/* 1883 */           if (childName == null)
/* 1884 */             childName = child.getNodeName(); 
/* 1886 */           if (childName.equalsIgnoreCase("Graphic")) {
/* 1887 */             Graphic g = parseGraphic(child);
/* 1888 */             if (LOGGER.isLoggable(Level.FINEST))
/* 1889 */               LOGGER.finest("setting stroke graphicfill with " + g); 
/* 1890 */             stroke.setGraphicFill(g);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1895 */     list = findElements((Element)root, "GraphicStroke");
/* 1896 */     length = list.getLength();
/* 1897 */     if (length > 0) {
/* 1898 */       if (LOGGER.isLoggable(Level.FINEST))
/* 1899 */         LOGGER.finest("stroke: found a graphic stroke " + list.item(0)); 
/* 1901 */       NodeList kids = list.item(0).getChildNodes();
/* 1903 */       for (int j = 0; j < kids.getLength(); j++) {
/* 1904 */         Node child = kids.item(j);
/* 1906 */         if (child != null && child.getNodeType() == 1) {
/* 1909 */           String childName = child.getLocalName();
/* 1910 */           if (childName == null)
/* 1911 */             childName = child.getNodeName(); 
/* 1913 */           if (childName.equalsIgnoreCase("Graphic")) {
/* 1914 */             Graphic g = parseGraphic(child);
/* 1915 */             if (LOGGER.isLoggable(Level.FINEST))
/* 1916 */               LOGGER.finest("setting stroke graphicStroke with " + g); 
/* 1917 */             stroke.setGraphicStroke(g);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1922 */     list = findElements((Element)root, "CssParameter");
/* 1923 */     length = list.getLength();
/* 1924 */     for (int i = 0; i < length; i++) {
/* 1925 */       Node child = list.item(i);
/* 1927 */       if (child != null && child.getNodeType() == 1) {
/* 1931 */         if (LOGGER.isLoggable(Level.FINEST))
/* 1932 */           LOGGER.finest("now I am processing " + child); 
/* 1935 */         Element param = (Element)child;
/* 1936 */         NamedNodeMap map = param.getAttributes();
/* 1937 */         int mapLength = map.getLength();
/* 1938 */         if (LOGGER.isLoggable(Level.FINEST))
/* 1939 */           LOGGER.finest("attributes " + map.toString()); 
/* 1942 */         for (int k = 0; k < mapLength; k++) {
/* 1943 */           String res = map.item(k).getNodeValue();
/* 1945 */           if (LOGGER.isLoggable(Level.FINEST))
/* 1946 */             LOGGER.finest("processing attribute " + res); 
/* 1950 */           if (res.equalsIgnoreCase("Stroke")) {
/* 1951 */             Expression color = parseCssParameter(child, true);
/* 1952 */             stroke.setColor(color);
/* 1953 */           } else if (res.equalsIgnoreCase("width") || res.equalsIgnoreCase("stroke-width")) {
/* 1954 */             Expression width = parseCssParameter(child, false);
/* 1955 */             stroke.setWidth(width);
/* 1956 */           } else if (res.equalsIgnoreCase("Opacity") || res.equalsIgnoreCase("stroke-opacity")) {
/* 1958 */             Expression opacity = parseCssParameter(child, false);
/* 1959 */             stroke.setOpacity(opacity);
/* 1960 */           } else if (res.equalsIgnoreCase("linecap") || res.equalsIgnoreCase("stroke-linecap")) {
/* 1963 */             stroke.setLineCap(parseCssParameter(child));
/* 1964 */           } else if (res.equalsIgnoreCase("linejoin") || res.equalsIgnoreCase("stroke-linejoin")) {
/* 1968 */             stroke.setLineJoin(parseCssParameter(child));
/* 1969 */           } else if (res.equalsIgnoreCase("dasharray") || res.equalsIgnoreCase("stroke-dasharray")) {
/* 1971 */             String dashString = null;
/* 1972 */             if (child.getChildNodes().getLength() == 1 && child.getFirstChild().getNodeType() == 3) {
/* 1973 */               dashString = getFirstChildValue(child);
/*      */             } else {
/* 1975 */               Expression definition = parseCssParameter(child);
/* 1976 */               if (definition instanceof Literal) {
/* 1977 */                 dashString = ((Literal)definition).getValue().toString();
/*      */               } else {
/* 1980 */                 LOGGER.warning("Only literal stroke-dasharray supported at this time:" + definition);
/*      */               } 
/*      */             } 
/* 1983 */             if (dashString != null) {
/* 1984 */               StringTokenizer stok = new StringTokenizer(dashString.trim(), " ");
/* 1985 */               float[] dashes = new float[stok.countTokens()];
/* 1986 */               for (int l = 0; l < dashes.length; l++)
/* 1987 */                 dashes[l] = Float.parseFloat(stok.nextToken()); 
/* 1989 */               stroke.setDashArray(dashes);
/*      */             } else {
/* 1992 */               LOGGER.fine("Unable to parse stroke-dasharray");
/*      */             } 
/* 1994 */           } else if (res.equalsIgnoreCase("dashoffset") || res.equalsIgnoreCase("stroke-dashoffset")) {
/* 1996 */             stroke.setDashOffset(parseCssParameter(child));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2001 */     return stroke;
/*      */   }
/*      */   
/*      */   protected Fill parseFill(Node root) {
/* 2006 */     if (LOGGER.isLoggable(Level.FINEST))
/* 2007 */       LOGGER.finest("parsing fill "); 
/* 2010 */     Fill fill = this.factory.getDefaultFill();
/* 2011 */     NodeList list = findElements((Element)root, "GraphicFill");
/* 2012 */     int length = list.getLength();
/* 2013 */     if (length > 0) {
/* 2014 */       if (LOGGER.isLoggable(Level.FINEST))
/* 2015 */         LOGGER.finest("fill found a graphic fill " + list.item(0)); 
/* 2017 */       NodeList kids = list.item(0).getChildNodes();
/* 2019 */       for (int j = 0; j < kids.getLength(); j++) {
/* 2020 */         Node child = kids.item(j);
/* 2022 */         if (child != null && child.getNodeType() == 1) {
/* 2025 */           String childName = child.getLocalName();
/* 2026 */           if (childName == null)
/* 2027 */             childName = child.getNodeName(); 
/* 2029 */           if (childName.equalsIgnoreCase("Graphic")) {
/* 2030 */             Graphic g = parseGraphic(child);
/* 2031 */             if (LOGGER.isLoggable(Level.FINEST))
/* 2032 */               LOGGER.finest("setting fill graphic with " + g); 
/* 2033 */             fill.setGraphicFill(g);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2038 */     list = findElements((Element)root, "CssParameter");
/* 2039 */     length = list.getLength();
/* 2040 */     for (int i = 0; i < length; i++) {
/* 2041 */       Node child = list.item(i);
/* 2043 */       if (child != null && child.getNodeType() == 1) {
/* 2047 */         Element param = (Element)child;
/* 2048 */         NamedNodeMap map = param.getAttributes();
/* 2049 */         int mapLength = map.getLength();
/* 2050 */         if (LOGGER.isLoggable(Level.FINEST))
/* 2051 */           LOGGER.finest("now I am processing " + child); 
/* 2054 */         if (LOGGER.isLoggable(Level.FINEST))
/* 2055 */           LOGGER.finest("attributes " + map.toString()); 
/* 2058 */         for (int k = 0; k < mapLength; k++) {
/* 2059 */           String res = map.item(k).getNodeValue();
/* 2061 */           if (LOGGER.isLoggable(Level.FINEST))
/* 2062 */             LOGGER.finest("processing attribute " + res); 
/* 2065 */           if (res.equalsIgnoreCase("Fill")) {
/* 2066 */             fill.setColor(parseCssParameter(child));
/* 2067 */           } else if (res.equalsIgnoreCase("Opacity") || res.equalsIgnoreCase("fill-opacity")) {
/* 2069 */             fill.setOpacity(parseCssParameter(child));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2074 */     if (LOGGER.isLoggable(Level.FINEST))
/* 2075 */       LOGGER.finest("fill graphic " + fill.getGraphicFill()); 
/* 2078 */     return fill;
/*      */   }
/*      */   
/*      */   private Expression manageMixed(Expression left, Expression right) {
/* 2089 */     if (left == null)
/* 2090 */       return right; 
/* 2091 */     if (right == null)
/* 2092 */       return left; 
/* 2093 */     Function mixed = this.ff.function("strConcat", new Expression[] { left, right });
/* 2094 */     return (Expression)mixed;
/*      */   }
/*      */   
/*      */   private Expression parseCssParameter(Node root) {
/* 2105 */     return parseCssParameter(root, true);
/*      */   }
/*      */   
/*      */   private Expression parseCssParameter(Node root, boolean trimWhiteSpace) {
/* 2119 */     if (LOGGER.isLoggable(Level.FINEST))
/* 2120 */       LOGGER.finest("parsingCssParam " + root); 
/* 2123 */     NodeList children = root.getChildNodes();
/* 2124 */     int length = children.getLength();
/* 2125 */     List<Expression> expressions = new ArrayList<Expression>();
/* 2126 */     List<Boolean> cdatas = new ArrayList<Boolean>();
/* 2127 */     for (int i = 0; i < length; i++) {
/* 2128 */       Node child = children.item(i);
/* 2132 */       if (child != null)
/* 2134 */         if (child.getNodeType() == 3) {
/* 2135 */           String value = child.getNodeValue();
/* 2136 */           if (value != null) {
/* 2139 */             if (trimWhiteSpace) {
/* 2140 */               value = value.trim();
/*      */             } else {
/* 2147 */               value = WHITESPACES.matcher(value).replaceAll(" ");
/*      */             } 
/* 2153 */             if (value != null && value.length() != 0) {
/* 2154 */               Literal literal = this.ff.literal(value);
/* 2156 */               if (LOGGER.isLoggable(Level.FINEST))
/* 2157 */                 LOGGER.finest("Built new literal " + literal); 
/* 2160 */               expressions.add(literal);
/* 2161 */               cdatas.add(Boolean.valueOf(false));
/*      */             } 
/*      */           } 
/* 2163 */         } else if (child.getNodeType() == 1) {
/* 2165 */           if (LOGGER.isLoggable(Level.FINEST))
/* 2166 */             LOGGER.finest("about to parse " + child.getLocalName()); 
/* 2169 */           expressions.add(ExpressionDOMParser.parseExpression(child));
/* 2171 */           cdatas.add(Boolean.valueOf(false));
/* 2172 */         } else if (child.getNodeType() == 4) {
/* 2173 */           String value = child.getNodeValue();
/* 2174 */           if (value != null && value.length() != 0) {
/* 2178 */             Literal literal = this.ff.literal(value);
/* 2180 */             if (LOGGER.isLoggable(Level.FINEST))
/* 2181 */               LOGGER.finest("Built new literal " + literal); 
/* 2184 */             expressions.add(literal);
/* 2185 */             cdatas.add(Boolean.valueOf(true));
/*      */           } 
/*      */         }  
/*      */     } 
/* 2192 */     if (expressions.size() == 0 && LOGGER.isLoggable(Level.FINEST))
/* 2193 */       LOGGER.finest("no children in CssParam"); 
/* 2196 */     if (!trimWhiteSpace) {
/* 2200 */       while (expressions.size() > 0) {
/* 2201 */         Expression ex = expressions.get(0);
/* 2204 */         if (!(ex instanceof Literal))
/*      */           break; 
/* 2206 */         Literal literal = (Literal)ex;
/* 2207 */         if (!(literal.getValue() instanceof String))
/*      */           break; 
/* 2211 */         String s = (String)literal.getValue();
/* 2212 */         if (!((Boolean)cdatas.get(0)).booleanValue()) {
/* 2213 */           if ("".equals(s.trim())) {
/* 2215 */             expressions.remove(0);
/* 2216 */             cdatas.remove(0);
/*      */             continue;
/*      */           } 
/* 2220 */           if (s.startsWith(" ")) {
/* 2221 */             s = LEADING_WHITESPACES.matcher(s).replaceAll("");
/* 2222 */             expressions.set(0, this.ff.literal(s));
/*      */           } 
/*      */         } 
/*      */       } 
/* 2232 */       while (expressions.size() > 0) {
/* 2233 */         int idx = expressions.size() - 1;
/* 2234 */         Expression ex = expressions.get(idx);
/* 2237 */         if (!(ex instanceof Literal))
/*      */           break; 
/* 2239 */         Literal literal = (Literal)ex;
/* 2240 */         if (!(literal.getValue() instanceof String))
/*      */           break; 
/* 2244 */         String s = (String)literal.getValue();
/* 2245 */         if (!((Boolean)cdatas.get(idx)).booleanValue()) {
/* 2246 */           if ("".equals(s.trim())) {
/* 2248 */             expressions.remove(idx);
/* 2249 */             cdatas.remove(idx);
/*      */             continue;
/*      */           } 
/* 2253 */           if (s.endsWith(" ")) {
/* 2254 */             s = TRAILING_WHITESPACES.matcher(s).replaceAll("");
/* 2255 */             expressions.set(idx, this.ff.literal(s));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2266 */     Expression ret = null;
/* 2267 */     for (Expression expression : expressions)
/* 2268 */       ret = manageMixed(ret, expression); 
/* 2271 */     return ret;
/*      */   }
/*      */   
/*      */   protected Font parseFont(Node root) {
/* 2278 */     if (LOGGER.isLoggable(Level.FINEST))
/* 2279 */       LOGGER.finest("parsing font"); 
/* 2282 */     Font font = this.factory.getDefaultFont();
/* 2283 */     NodeList list = findElements((Element)root, "CssParameter");
/* 2284 */     int length = list.getLength();
/* 2285 */     for (int i = 0; i < length; i++) {
/* 2286 */       Node child = list.item(i);
/* 2288 */       if (child != null && child.getNodeType() == 1) {
/* 2292 */         Element param = (Element)child;
/* 2293 */         NamedNodeMap map = param.getAttributes();
/* 2294 */         int mapLength = map.getLength();
/* 2295 */         for (int k = 0; k < mapLength; k++) {
/* 2296 */           String res = map.item(k).getNodeValue();
/* 2298 */           if (res.equalsIgnoreCase("font-family")) {
/* 2299 */             font.setFontFamily(parseCssParameter(child));
/* 2300 */           } else if (res.equalsIgnoreCase("font-style")) {
/* 2301 */             font.setFontStyle(parseCssParameter(child));
/* 2302 */           } else if (res.equalsIgnoreCase("font-size")) {
/* 2303 */             font.setFontSize(parseCssParameter(child));
/* 2304 */           } else if (res.equalsIgnoreCase("font-weight")) {
/* 2305 */             font.setFontWeight(parseCssParameter(child));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2310 */     return font;
/*      */   }
/*      */   
/*      */   protected LabelPlacement parseLabelPlacement(Node root) {
/* 2315 */     if (LOGGER.isLoggable(Level.FINEST))
/* 2316 */       LOGGER.finest("parsing labelPlacement"); 
/* 2319 */     LabelPlacement ret = null;
/* 2320 */     NodeList children = root.getChildNodes();
/* 2321 */     int length = children.getLength();
/* 2322 */     for (int i = 0; i < length; i++) {
/* 2323 */       Node child = children.item(i);
/* 2325 */       if (child != null && child.getNodeType() == 1) {
/* 2328 */         String childName = child.getLocalName();
/* 2329 */         if (childName == null)
/* 2330 */           childName = child.getNodeName(); 
/* 2332 */         if (childName.equalsIgnoreCase("PointPlacement")) {
/* 2333 */           ret = parsePointPlacement(child);
/* 2334 */         } else if (childName.equalsIgnoreCase("LinePlacement")) {
/* 2335 */           ret = parseLinePlacement(child);
/*      */         } 
/*      */       } 
/*      */     } 
/* 2339 */     return ret;
/*      */   }
/*      */   
/*      */   protected PointPlacement parsePointPlacement(Node root) {
/*      */     Expression expression;
/* 2344 */     if (LOGGER.isLoggable(Level.FINEST))
/* 2345 */       LOGGER.finest("parsing pointPlacement"); 
/* 2348 */     Literal literal = this.ff.literal(0.0D);
/* 2349 */     AnchorPoint ap = null;
/* 2350 */     Displacement dp = null;
/* 2352 */     NodeList children = root.getChildNodes();
/* 2353 */     int length = children.getLength();
/* 2354 */     for (int i = 0; i < length; i++) {
/* 2355 */       Node child = children.item(i);
/* 2357 */       if (child != null && child.getNodeType() == 1) {
/* 2360 */         String childName = child.getLocalName();
/* 2361 */         if (childName == null)
/* 2362 */           childName = child.getNodeName(); 
/* 2364 */         if (childName.equalsIgnoreCase("AnchorPoint")) {
/* 2365 */           ap = parseAnchorPoint(child);
/* 2366 */         } else if (childName.equalsIgnoreCase("Displacement")) {
/* 2367 */           dp = parseDisplacement(child);
/* 2368 */         } else if (childName.equalsIgnoreCase("Rotation")) {
/* 2369 */           expression = parseCssParameter(child);
/*      */         } 
/*      */       } 
/*      */     } 
/* 2373 */     if (LOGGER.isLoggable(Level.FINE)) {
/* 2374 */       LOGGER.fine("setting anchorPoint " + ap);
/* 2375 */       LOGGER.fine("setting displacement " + dp);
/*      */     } 
/* 2378 */     PointPlacement dpp = this.factory.createPointPlacement(ap, dp, expression);
/* 2380 */     return dpp;
/*      */   }
/*      */   
/*      */   protected LinePlacement parseLinePlacement(Node root) {
/*      */     Expression expression;
/* 2385 */     if (LOGGER.isLoggable(Level.FINEST))
/* 2386 */       LOGGER.finest("parsing linePlacement"); 
/* 2389 */     Literal literal = this.ff.literal(0.0D);
/* 2390 */     NodeList children = root.getChildNodes();
/* 2391 */     int length = children.getLength();
/* 2392 */     for (int i = 0; i < length; i++) {
/* 2393 */       Node child = children.item(i);
/* 2395 */       if (child != null && child.getNodeType() == 1) {
/* 2398 */         String childName = child.getLocalName();
/* 2399 */         if (childName == null)
/* 2400 */           childName = child.getNodeName(); 
/* 2402 */         if (childName.equalsIgnoreCase("PerpendicularOffset"))
/* 2403 */           expression = parseCssParameter(child); 
/*      */       } 
/*      */     } 
/* 2407 */     LinePlacement dlp = this.factory.createLinePlacement(expression);
/* 2409 */     return dlp;
/*      */   }
/*      */   
/*      */   protected AnchorPoint parseAnchorPoint(Node root) {
/* 2417 */     if (LOGGER.isLoggable(Level.FINEST))
/* 2418 */       LOGGER.finest("parsing anchorPoint"); 
/* 2421 */     Expression x = null;
/* 2422 */     Expression y = null;
/* 2424 */     NodeList children = root.getChildNodes();
/* 2425 */     int length = children.getLength();
/* 2426 */     for (int i = 0; i < length; i++) {
/* 2427 */       Node child = children.item(i);
/* 2429 */       if (child != null && child.getNodeType() == 1) {
/* 2432 */         String childName = child.getLocalName();
/* 2433 */         if (childName == null)
/* 2434 */           childName = child.getNodeName(); 
/* 2436 */         if (childName.equalsIgnoreCase("AnchorPointX")) {
/* 2437 */           x = parseCssParameter(child);
/* 2438 */         } else if (childName.equalsIgnoreCase("AnchorPointY")) {
/* 2439 */           y = parseCssParameter(child);
/*      */         } 
/*      */       } 
/*      */     } 
/* 2443 */     AnchorPoint dap = this.factory.createAnchorPoint(x, y);
/* 2445 */     return dap;
/*      */   }
/*      */   
/*      */   protected Displacement parseDisplacement(Node root) {
/* 2450 */     if (LOGGER.isLoggable(Level.FINEST))
/* 2451 */       LOGGER.finest("parsing displacment"); 
/* 2454 */     Expression x = null;
/* 2455 */     Expression y = null;
/* 2456 */     NodeList children = root.getChildNodes();
/* 2457 */     int length = children.getLength();
/* 2458 */     for (int i = 0; i < length; i++) {
/* 2459 */       Node child = children.item(i);
/* 2461 */       if (child != null && child.getNodeType() == 1) {
/* 2464 */         String childName = child.getLocalName();
/* 2465 */         if (childName == null)
/* 2466 */           childName = child.getNodeName(); 
/* 2468 */         if (childName.equalsIgnoreCase("DisplacementX"))
/* 2469 */           x = parseCssParameter(child); 
/* 2472 */         if (childName.equalsIgnoreCase("DisplacementY"))
/* 2473 */           y = parseCssParameter(child); 
/*      */       } 
/*      */     } 
/* 2477 */     Displacement dd = this.factory.createDisplacement(x, y);
/* 2479 */     return dd;
/*      */   }
/*      */   
/*      */   protected Halo parseHalo(Node root) {
/* 2484 */     if (LOGGER.isLoggable(Level.FINEST))
/* 2485 */       LOGGER.finest("parsing halo"); 
/* 2487 */     Halo halo = this.factory.createHalo(this.factory.createFill((Expression)this.ff.literal("#FFFFFF")), (Expression)this.ff.literal(1.0D));
/* 2489 */     NodeList children = root.getChildNodes();
/* 2490 */     int length = children.getLength();
/* 2491 */     for (int i = 0; i < length; i++) {
/* 2492 */       Node child = children.item(i);
/* 2494 */       if (child != null && child.getNodeType() == 1) {
/* 2497 */         String childName = child.getLocalName();
/* 2498 */         if (childName == null)
/* 2499 */           childName = child.getNodeName(); 
/* 2501 */         if (childName.equalsIgnoreCase("Fill")) {
/* 2502 */           halo.setFill(parseFill(child));
/* 2503 */         } else if (childName.equalsIgnoreCase("Radius")) {
/* 2504 */           halo.setRadius(parseCssParameter(child));
/*      */         } 
/*      */       } 
/*      */     } 
/* 2509 */     return halo;
/*      */   }
/*      */   
/*      */   private static class EmptyIcon implements Icon {
/* 2514 */     public static final EmptyIcon INSTANCE = new EmptyIcon();
/*      */     
/*      */     public void paintIcon(Component c, Graphics g, int x, int y) {}
/*      */     
/*      */     public int getIconWidth() {
/* 2516 */       return 1;
/*      */     }
/*      */     
/*      */     public int getIconHeight() {
/* 2517 */       return 1;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\SLDParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */