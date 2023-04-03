/*     */ package org.geotools.styling;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import org.geotools.data.memory.MemoryDataStore;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
/*     */ import org.geotools.filter.ExpressionDOMParser;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class SLDInlineFeatureParser {
/*  48 */   private static Hashtable<Integer, CoordinateReferenceSystem> SRSLookup = new Hashtable<Integer, CoordinateReferenceSystem>();
/*     */   
/*  51 */   public SimpleFeatureType featureType = null;
/*     */   
/*  52 */   public MemoryDataStore dataStore = null;
/*     */   
/*  53 */   Node rootNode = null;
/*     */   
/*  54 */   ArrayList<Feature> features = new ArrayList<Feature>();
/*     */   
/*  55 */   CoordinateReferenceSystem SRS = null;
/*     */   
/*  57 */   private static int uniqueNumber = 0;
/*     */   
/*     */   public SLDInlineFeatureParser(Node root) throws Exception {
/*  63 */     boolean isFeatureCollection = false;
/*  65 */     if (!isSimple(root))
/*  67 */       throw new Exception("couldnt parse the SLD Inline features!"); 
/*  69 */     Node fc = getNode(root, "FeatureCollection");
/*  70 */     if (fc != null) {
/*  72 */       isFeatureCollection = true;
/*  73 */       root = fc;
/*     */     } 
/*  77 */     this.featureType = makeFeatureType(root, isFeatureCollection);
/*  78 */     if (this.featureType == null)
/*  79 */       throw new Exception("SLD InlineFeature Parser - couldnt determine a FeatureType.  See help for whats supported."); 
/*  81 */     makeFeatures(root, isFeatureCollection);
/*  82 */     if (this.features.size() == 0)
/*  83 */       throw new Exception("SLD InlineFeature Parser - didnt find any features!"); 
/*  85 */     buildStore();
/*     */   }
/*     */   
/*     */   private void makeFeatures(Node root, boolean isCollection) throws Exception {
/* 129 */     NodeList children = root.getChildNodes();
/* 131 */     for (int i = 0; i < children.getLength(); i++) {
/* 133 */       Node child = children.item(i);
/* 135 */       if (child != null && child.getNodeType() == 1) {
/* 139 */         String childName = child.getLocalName();
/* 140 */         if (childName == null)
/* 141 */           childName = child.getNodeName(); 
/* 142 */         if (!childName.equalsIgnoreCase("boundedBy")) {
/* 145 */           if (isCollection)
/* 148 */             child = descend(child); 
/* 150 */           if (child == null)
/* 151 */             throw new Exception("SLD inlineFeature Parser - couldnt extract a feature from the dom."); 
/* 153 */           SimpleFeature f = parseFeature(child, this.featureType);
/* 154 */           this.features.add(f);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private Node descend(Node root) {
/* 167 */     NodeList children = root.getChildNodes();
/* 169 */     for (int i = 0; i < children.getLength(); ) {
/* 171 */       Node child = children.item(i);
/* 173 */       if (child == null || child.getNodeType() != 1) {
/*     */         i++;
/*     */         continue;
/*     */       } 
/* 177 */       return child;
/*     */     } 
/* 179 */     return null;
/*     */   }
/*     */   
/*     */   private SimpleFeature parseFeature(Node feature, SimpleFeatureType featureType) throws Exception {
/* 193 */     Object[] nullAtts = new Object[featureType.getAttributeCount()];
/* 194 */     SimpleFeature f = SimpleFeatureBuilder.build(featureType, nullAtts, null);
/* 196 */     NodeList children = feature.getChildNodes();
/* 197 */     for (int i = 0; i < children.getLength(); i++) {
/* 199 */       Node child = children.item(i);
/* 200 */       if (child != null && child.getNodeType() == 1) {
/* 203 */         String childName = child.getLocalName();
/* 204 */         if (childName == null)
/* 206 */           childName = child.getNodeName(); 
/* 209 */         Object value = getValue(child);
/*     */         try {
/* 211 */           f.setAttribute(childName, value);
/* 213 */         } catch (Exception e) {
/* 215 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/* 218 */     return f;
/*     */   }
/*     */   
/*     */   private Object getValue(Node root) throws Exception {
/* 228 */     NodeList children = root.getChildNodes();
/* 229 */     StringBuffer strVal = new StringBuffer();
/* 231 */     for (int i = 0; i < children.getLength(); i++) {
/* 233 */       Node child = children.item(i);
/* 234 */       if (child != null) {
/* 238 */         if (child.getNodeType() == 3)
/* 240 */           strVal.append(child.getNodeValue()); 
/* 243 */         if (child.getNodeType() == 1)
/* 245 */           return parseGeometry(child); 
/*     */       } 
/*     */     } 
/* 248 */     return strVal;
/*     */   }
/*     */   
/*     */   private Geometry parseGeometry(Node root) throws Exception {
/* 271 */     NamedNodeMap atts = root.getAttributes();
/* 272 */     if (this.SRS == null) {
/* 274 */       Node srsName = atts.getNamedItem("srsName");
/* 275 */       if (srsName != null)
/* 277 */         parseSRS(srsName.getNodeValue()); 
/*     */     } 
/* 280 */     ExpressionDOMParser parser = new ExpressionDOMParser(CommonFactoryFinder.getFilterFactory2(null));
/* 281 */     return parser.gml(root);
/*     */   }
/*     */   
/*     */   private boolean isSimple(Node root) throws Exception {
/* 301 */     int foundFeature = 0;
/* 302 */     int foundFC = 0;
/* 304 */     Node fcNode = null;
/* 305 */     String featureName = null;
/* 307 */     NodeList children = root.getChildNodes();
/*     */     int i;
/* 309 */     for (i = 0; i < children.getLength(); i++) {
/* 311 */       Node child = children.item(i);
/* 313 */       if (child != null && child.getNodeType() == 1) {
/* 317 */         String childName = child.getLocalName();
/* 318 */         if (childName == null)
/* 319 */           childName = child.getNodeName(); 
/* 321 */         if (childName.equalsIgnoreCase("FeatureCollection")) {
/* 323 */           foundFC++;
/* 324 */           fcNode = child;
/*     */         } else {
/* 328 */           if (featureName == null)
/* 329 */             featureName = childName; 
/* 330 */           if (!childName.equalsIgnoreCase(featureName))
/* 331 */             throw new Exception("SLD inline feature parser  - it appear that there is >1 feature type present.  I got a " + childName + " when I was expecting a " + featureName + " tag"); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 334 */     if (foundFC > 1)
/* 335 */       throw new Exception("SLD - UserLayer, inline feature parser - found >1 FeatureCollection.  Not supported"); 
/* 336 */     if (foundFC > 0 && foundFeature > 0)
/* 337 */       throw new Exception("SLD - UserLayer, inline feature parser - found  SimpleFeatureCollection and featureMembers.  Not supported"); 
/* 340 */     if (foundFC == 0)
/* 341 */       return true; 
/* 343 */     featureName = null;
/* 346 */     children = fcNode.getChildNodes();
/* 348 */     for (i = 0; i < children.getLength(); i++) {
/* 350 */       Node child = children.item(i);
/* 352 */       if (child != null && child.getNodeType() == 1) {
/* 356 */         String childName = child.getLocalName();
/* 357 */         if (childName == null)
/* 358 */           childName = child.getNodeName(); 
/* 359 */         if (childName.equalsIgnoreCase("featureMember")) {
/* 360 */           foundFeature++;
/* 361 */         } else if (!childName.equalsIgnoreCase("boundedBy")) {
/* 365 */           if (childName.equalsIgnoreCase("FeatureCollection"))
/* 367 */             throw new Exception("SLD - UserLayer, inline feature parser - found a node of type FeatureCollection.  Expected a featureMember - dont support nested collections."); 
/* 371 */           throw new Exception("SLD - UserLayer, inline feature parser - found a node of type '" + child.getLocalName() + "' and dont understand it.  Expected a featureMember.");
/*     */         } 
/*     */       } 
/*     */     } 
/* 374 */     return true;
/*     */   }
/*     */   
/*     */   private void buildStore() {
/* 382 */     this.dataStore = new MemoryDataStore(this.features.<SimpleFeature>toArray(new SimpleFeature[this.features.size()]));
/*     */   }
/*     */   
/*     */   private SimpleFeatureType makeFeatureType(Node root, boolean isCollection) throws Exception {
/* 413 */     Node feature = null;
/* 415 */     Node featureMember = root;
/* 416 */     if (isCollection)
/* 417 */       featureMember = getNode(root, "featureMember"); 
/* 420 */     NodeList children = featureMember.getChildNodes();
/* 423 */     for (int i = 0; i < children.getLength(); i++) {
/* 425 */       Node child = children.item(i);
/* 426 */       if (child != null && child.getNodeType() == 1) {
/* 429 */         String childName = child.getLocalName();
/* 430 */         if (childName == null)
/* 431 */           childName = child.getNodeName(); 
/* 433 */         if (!childName.equalsIgnoreCase("boundedBy")) {
/* 435 */           feature = child;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 439 */     if (feature == null)
/* 440 */       throw new Exception("couldnt find a Feature in the Inline Features!"); 
/* 449 */     String featureName = feature.getLocalName();
/* 450 */     if (featureName == null)
/* 451 */       featureName = feature.getNodeName(); 
/* 457 */     SimpleFeatureTypeBuilder build = new SimpleFeatureTypeBuilder();
/* 458 */     build.setName(featureName);
/* 459 */     build.setNamespaceURI(new URI("http://temp.inline.feature.sld.com"));
/* 462 */     children = feature.getChildNodes();
/* 463 */     for (int j = 0; j < children.getLength(); j++) {
/* 465 */       Node child = children.item(j);
/* 466 */       if (child != null && child.getNodeType() == 1) {
/* 469 */         String childName = child.getLocalName();
/* 470 */         if (childName == null)
/* 472 */           childName = child.getNodeName(); 
/* 476 */         if (isGeometry(child)) {
/* 479 */           getValue(child);
/* 480 */           build.add(childName, Geometry.class, this.SRS);
/*     */         } else {
/* 484 */           build.add(childName, String.class);
/*     */         } 
/*     */       } 
/*     */     } 
/* 487 */     return build.buildFeatureType();
/*     */   }
/*     */   
/*     */   private boolean isGeometry(Node root) {
/* 499 */     NodeList children = root.getChildNodes();
/* 500 */     for (int i = 0; i < children.getLength(); ) {
/* 502 */       Node child = children.item(i);
/* 503 */       if (child == null || child.getNodeType() != 1) {
/*     */         i++;
/*     */         continue;
/*     */       } 
/* 507 */       return true;
/*     */     } 
/* 509 */     return false;
/*     */   }
/*     */   
/*     */   public Node getNode(Node parentNode, String wantedChildName) {
/* 521 */     NodeList children = parentNode.getChildNodes();
/* 523 */     for (int i = 0; i < children.getLength(); i++) {
/* 525 */       Node child = children.item(i);
/* 527 */       if (child != null && child.getNodeType() == 1) {
/* 530 */         String childName = child.getLocalName();
/* 531 */         if (childName == null)
/* 532 */           childName = child.getNodeName(); 
/* 534 */         if (childName.equalsIgnoreCase(wantedChildName))
/* 536 */           return child; 
/*     */       } 
/*     */     } 
/* 539 */     return null;
/*     */   }
/*     */   
/*     */   public synchronized int getUID() {
/* 544 */     return uniqueNumber++;
/*     */   }
/*     */   
/*     */   private void parseSRS(String srs) throws Exception {
/* 554 */     if (srs == null)
/*     */       return; 
/* 556 */     String epsgCode = srs.substring(srs.indexOf('#') + 1);
/* 557 */     int srsnum = Integer.parseInt(epsgCode);
/* 558 */     this.SRS = getSRS(srsnum);
/*     */   }
/*     */   
/*     */   private CoordinateReferenceSystem getSRS(int epsg) throws Exception {
/* 570 */     CoordinateReferenceSystem result = SRSLookup.get(new Integer(epsg));
/* 571 */     if (result == null) {
/* 574 */       result = CRS.decode("EPSG:" + epsg);
/* 575 */       SRSLookup.put(new Integer(epsg), result);
/*     */     } 
/* 577 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\SLDInlineFeatureParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */