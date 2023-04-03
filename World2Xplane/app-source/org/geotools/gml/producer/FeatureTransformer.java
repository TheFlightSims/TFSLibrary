/*     */ package org.geotools.gml.producer;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.io.IOException;
/*     */ import java.sql.Date;
/*     */ import java.sql.Time;
/*     */ import java.util.Date;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureCollectionIteration;
/*     */ import org.geotools.feature.type.DateUtil;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.geotools.xml.transform.TransformerBase;
/*     */ import org.geotools.xml.transform.Translator;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.GeometryDescriptor;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ import org.xml.sax.helpers.NamespaceSupport;
/*     */ 
/*     */ public class FeatureTransformer extends TransformerBase {
/* 107 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.gml");
/*     */   
/*     */   private static Set gmlAtts;
/*     */   
/* 109 */   private String collectionPrefix = "wfs";
/*     */   
/* 110 */   private String collectionNamespace = "http://www.opengis.net/wfs";
/*     */   
/* 111 */   private NamespaceSupport nsLookup = new NamespaceSupport();
/*     */   
/* 112 */   private FeatureTypeNamespaces featureTypeNamespaces = new FeatureTypeNamespaces(this.nsLookup);
/*     */   
/* 113 */   private TransformerBase.SchemaLocationSupport schemaLocation = new TransformerBase.SchemaLocationSupport();
/*     */   
/* 114 */   private int maxFeatures = -1;
/*     */   
/*     */   private boolean prefixGml = false;
/*     */   
/*     */   private boolean featureBounding = false;
/*     */   
/*     */   private boolean collectionBounding = true;
/*     */   
/*     */   private String srsName;
/*     */   
/*     */   private String lockId;
/*     */   
/* 120 */   private int numDecimals = 4;
/*     */   
/*     */   public void setCollectionNamespace(String nsURI) {
/* 123 */     this.collectionNamespace = nsURI;
/*     */   }
/*     */   
/*     */   public String getCollectionNamespace() {
/* 127 */     return this.collectionNamespace;
/*     */   }
/*     */   
/*     */   public void setCollectionPrefix(String prefix) {
/* 131 */     this.collectionPrefix = prefix;
/*     */   }
/*     */   
/*     */   public String getCollectionPrefix() {
/* 135 */     return this.collectionPrefix;
/*     */   }
/*     */   
/*     */   public void setNumDecimals(int numDecimals) {
/* 149 */     this.numDecimals = numDecimals;
/*     */   }
/*     */   
/*     */   public NamespaceSupport getFeatureNamespaces() {
/* 153 */     return this.nsLookup;
/*     */   }
/*     */   
/*     */   public FeatureTypeNamespaces getFeatureTypeNamespaces() {
/* 157 */     return this.featureTypeNamespaces;
/*     */   }
/*     */   
/*     */   public void addSchemaLocation(String nsURI, String uri) {
/* 161 */     this.schemaLocation.setLocation(nsURI, uri);
/*     */   }
/*     */   
/*     */   public void setSrsName(String srsName) {
/* 177 */     this.srsName = srsName;
/*     */   }
/*     */   
/*     */   public void setLockId(String lockId) {
/* 191 */     this.lockId = lockId;
/*     */   }
/*     */   
/*     */   public void setGmlPrefixing(boolean prefixGml) {
/* 218 */     this.prefixGml = prefixGml;
/* 220 */     if (prefixGml && gmlAtts == null) {
/* 221 */       gmlAtts = new HashSet();
/* 222 */       loadGmlAttributes(gmlAtts);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void loadGmlAttributes(Set<String> gmlAtts) {
/* 231 */     gmlAtts.add("pointProperty");
/* 232 */     gmlAtts.add("geometryProperty");
/* 233 */     gmlAtts.add("polygonProperty");
/* 234 */     gmlAtts.add("lineStringProperty");
/* 235 */     gmlAtts.add("multiPointProperty");
/* 236 */     gmlAtts.add("multiLineStringProperty");
/* 237 */     gmlAtts.add("multiPolygonProperty");
/* 238 */     gmlAtts.add("description");
/* 239 */     gmlAtts.add("name");
/*     */   }
/*     */   
/*     */   public void setFeatureBounding(boolean featureBounding) {
/* 268 */     this.featureBounding = featureBounding;
/*     */   }
/*     */   
/*     */   public void setCollectionBounding(boolean collectionBounding) {
/* 280 */     this.collectionBounding = collectionBounding;
/*     */   }
/*     */   
/*     */   public Translator createTranslator(ContentHandler handler) {
/* 285 */     FeatureTranslator t = createTranslator(handler, this.collectionPrefix, this.collectionNamespace, this.featureTypeNamespaces, this.schemaLocation);
/* 287 */     Enumeration<String> prefixes = this.nsLookup.getPrefixes();
/* 290 */     t.setNumDecimals(this.numDecimals);
/* 291 */     t.setGmlPrefixing(this.prefixGml);
/* 292 */     t.setSrsName(this.srsName);
/* 293 */     t.setLockId(this.lockId);
/* 294 */     t.setFeatureBounding(this.featureBounding);
/* 295 */     t.setCollectionBounding(this.collectionBounding);
/* 297 */     while (prefixes.hasMoreElements()) {
/* 298 */       String prefix = prefixes.nextElement().toString();
/* 299 */       String uri = this.nsLookup.getURI(prefix);
/* 300 */       t.getNamespaceSupport().declarePrefix(prefix, uri);
/*     */     } 
/* 303 */     return (Translator)t;
/*     */   }
/*     */   
/*     */   protected FeatureTranslator createTranslator(ContentHandler handler, String prefix, String ns, FeatureTypeNamespaces featureTypeNamespaces, TransformerBase.SchemaLocationSupport schemaLocationSupport) {
/* 313 */     return new FeatureTranslator(handler, prefix, ns, featureTypeNamespaces, schemaLocationSupport);
/*     */   }
/*     */   
/*     */   public static class FeatureTypeNamespaces {
/* 317 */     Map lookup = new HashMap<Object, Object>();
/*     */     
/*     */     NamespaceSupport nsSupport;
/*     */     
/* 319 */     String defaultPrefix = null;
/*     */     
/*     */     public FeatureTypeNamespaces(NamespaceSupport nsSupport) {
/* 322 */       this.nsSupport = nsSupport;
/*     */     }
/*     */     
/*     */     public void declareDefaultNamespace(String prefix, String nsURI) {
/* 326 */       this.defaultPrefix = prefix;
/* 327 */       this.nsSupport.declarePrefix(prefix, nsURI);
/*     */     }
/*     */     
/*     */     public void declareNamespace(FeatureType type, String prefix, String nsURI) {
/* 332 */       this.lookup.put(type, prefix);
/* 333 */       this.nsSupport.declarePrefix(prefix, nsURI);
/*     */     }
/*     */     
/*     */     public String findPrefix(FeatureType type) {
/* 337 */       String pre = (String)this.lookup.get(type);
/* 339 */       if (pre == null)
/* 340 */         pre = this.defaultPrefix; 
/* 343 */       return pre;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 347 */       return "FeatureTypeNamespaces[Default: " + this.defaultPrefix + ", lookUp: " + this.lookup.keySet() + "]";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class FeatureTranslator extends TransformerBase.TranslatorSupport implements FeatureCollectionIteration.Handler {
/* 357 */     String fc = "FeatureCollection";
/*     */     
/*     */     protected GeometryTransformer.GeometryTranslator geometryTranslator;
/*     */     
/*     */     String memberString;
/*     */     
/*     */     String currentPrefix;
/*     */     
/*     */     FeatureTransformer.FeatureTypeNamespaces types;
/*     */     
/*     */     boolean prefixGml = false;
/*     */     
/*     */     boolean featureBounding = false;
/*     */     
/*     */     boolean collectionBounding = true;
/*     */     
/* 373 */     String srsName = null;
/*     */     
/* 382 */     int dimension = 0;
/*     */     
/* 384 */     String lockId = null;
/*     */     
/*     */     ContentHandler handler;
/*     */     
/*     */     private boolean running = true;
/*     */     
/*     */     public FeatureTranslator(ContentHandler handler, String prefix, String ns, FeatureTransformer.FeatureTypeNamespaces types, TransformerBase.SchemaLocationSupport schemaLoc) {
/* 400 */       super(handler, prefix, ns, schemaLoc);
/* 401 */       this.geometryTranslator = createGeometryTranslator(handler);
/* 402 */       this.types = types;
/* 403 */       this.handler = handler;
/* 404 */       getNamespaceSupport().declarePrefix(this.geometryTranslator.getDefaultPrefix(), this.geometryTranslator.getDefaultNamespace());
/* 406 */       this.memberString = this.geometryTranslator.getDefaultPrefix() + ":featureMember";
/*     */     }
/*     */     
/*     */     protected GeometryTransformer.GeometryTranslator createGeometryTranslator(ContentHandler handler) {
/* 417 */       return new GeometryTransformer.GeometryTranslator(handler);
/*     */     }
/*     */     
/*     */     protected GeometryTransformer.GeometryTranslator createGeometryTranslator(ContentHandler handler, int numDecimals) {
/* 420 */       return new GeometryTransformer.GeometryTranslator(handler, numDecimals);
/*     */     }
/*     */     
/*     */     protected GeometryTransformer.GeometryTranslator createGeometryTranslator(ContentHandler handler, int numDecimals, boolean useDummyZ) {
/* 429 */       return new GeometryTransformer.GeometryTranslator(handler, numDecimals, useDummyZ);
/*     */     }
/*     */     
/*     */     protected GeometryTransformer.GeometryTranslator createGeometryTranslator(ContentHandler handler, int numDecimals, int dimension) {
/* 445 */       return new GeometryTransformer.GeometryTranslator(handler, "gml", "http://www.opengis.net/gml", numDecimals, false, dimension);
/*     */     }
/*     */     
/*     */     void setGmlPrefixing(boolean prefixGml) {
/* 449 */       this.prefixGml = prefixGml;
/*     */     }
/*     */     
/*     */     void setFeatureBounding(boolean bounding) {
/* 453 */       this.featureBounding = bounding;
/*     */     }
/*     */     
/*     */     void setCollectionBounding(boolean collectionBounding) {
/* 457 */       this.collectionBounding = collectionBounding;
/*     */     }
/*     */     
/*     */     void setSrsName(String srsName) {
/* 461 */       this.srsName = srsName;
/*     */     }
/*     */     
/*     */     void setNumDecimals(int numDecimals) {
/* 465 */       this.geometryTranslator = createGeometryTranslator(this.handler, numDecimals);
/*     */     }
/*     */     
/*     */     void setUseDummyZ(boolean useDummyZ) {
/* 469 */       this.geometryTranslator = createGeometryTranslator(this.handler, this.geometryTranslator.getNumDecimals(), useDummyZ);
/*     */     }
/*     */     
/*     */     void setDimension(int dimension) {
/* 475 */       this.geometryTranslator = createGeometryTranslator(this.handler, this.geometryTranslator.getNumDecimals(), dimension);
/*     */     }
/*     */     
/*     */     public void setLockId(String lockId) {
/* 480 */       this.lockId = lockId;
/*     */     }
/*     */     
/*     */     public FeatureTransformer.FeatureTypeNamespaces getFeatureTypeNamespaces() {
/* 484 */       return this.types;
/*     */     }
/*     */     
/*     */     public void encode(Object o) throws IllegalArgumentException {
/*     */       try {
/* 489 */         if (o instanceof FeatureCollection) {
/* 490 */           SimpleFeatureCollection fc = (SimpleFeatureCollection)o;
/* 491 */           FeatureCollectionIteration.iteration(this, (FeatureCollection)fc);
/* 492 */         } else if (o instanceof FeatureCollection[]) {
/* 495 */           FeatureCollection[] results = (FeatureCollection[])o;
/* 496 */           startFeatureCollection();
/* 497 */           if (this.collectionBounding) {
/* 498 */             ReferencedEnvelope bounds = null;
/* 499 */             for (int j = 0; j < results.length; j++) {
/* 500 */               ReferencedEnvelope more = results[j].getBounds();
/* 501 */               if (bounds == null) {
/* 502 */                 bounds = new ReferencedEnvelope(more);
/*     */               } else {
/* 505 */                 bounds.expandToInclude((Envelope)more);
/*     */               } 
/*     */             } 
/* 508 */             writeBounds((BoundingBox)bounds);
/*     */           } else {
/* 510 */             writeNullBounds();
/*     */           } 
/* 513 */           for (int i = 0; i < results.length; i++)
/* 514 */             handleFeatureIterator(DataUtilities.simple(results[i]).features()); 
/* 516 */           endFeatureCollection();
/* 517 */         } else if (o instanceof FeatureReader) {
/* 519 */           FeatureReader<SimpleFeatureType, SimpleFeature> r = (FeatureReader<SimpleFeatureType, SimpleFeature>)o;
/* 521 */           startFeatureCollection();
/* 523 */           handleFeatureReader(r);
/* 525 */           endFeatureCollection();
/*     */         } else {
/* 551 */           throw new IllegalArgumentException("Cannot encode " + o);
/*     */         } 
/* 553 */       } catch (IOException ioe) {
/* 554 */         ioe.printStackTrace(System.out);
/* 555 */         throw new RuntimeException("error reading FeatureResults", ioe);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void handleFeatureIterator(SimpleFeatureIterator iterator) throws IOException {
/*     */       try {
/* 562 */         while (iterator.hasNext() && this.running) {
/* 563 */           SimpleFeature f = (SimpleFeature)iterator.next();
/* 564 */           handleFeature((Feature)f);
/* 566 */           SimpleFeatureType t = f.getFeatureType();
/* 568 */           for (int i = 0, ii = f.getAttributeCount(); i < ii; 
/* 569 */             i++) {
/* 570 */             AttributeDescriptor descriptor = t.getDescriptor(i);
/* 571 */             Object value = f.getAttribute(i);
/* 572 */             handleAttribute((PropertyDescriptor)descriptor, value);
/*     */           } 
/* 574 */           endFeature((Feature)f);
/*     */         } 
/* 576 */       } catch (Exception ioe) {
/* 577 */         throw new RuntimeException("Error reading Features", ioe);
/*     */       } finally {
/* 579 */         if (iterator != null) {
/* 580 */           FeatureTransformer.LOGGER.finer("closing reader " + iterator);
/* 581 */           iterator.close();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public void handleFeatureReader(FeatureReader<SimpleFeatureType, SimpleFeature> reader) throws IOException {
/*     */       try {
/* 589 */         while (reader.hasNext() && this.running) {
/* 590 */           SimpleFeature f = (SimpleFeature)reader.next();
/* 591 */           handleFeature((Feature)f);
/* 593 */           SimpleFeatureType t = f.getFeatureType();
/* 595 */           for (int i = 0, ii = f.getAttributeCount(); i < ii; i++) {
/* 596 */             AttributeDescriptor descriptor = t.getDescriptor(i);
/* 597 */             Object value = f.getAttribute(i);
/* 598 */             handleAttribute((PropertyDescriptor)descriptor, value);
/*     */           } 
/* 601 */           endFeature((Feature)f);
/*     */         } 
/* 603 */       } catch (Exception ioe) {
/* 604 */         throw new RuntimeException("Error reading Features", ioe);
/*     */       } finally {
/* 606 */         if (reader != null) {
/* 607 */           FeatureTransformer.LOGGER.finer("closing reader " + reader);
/* 608 */           reader.close();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public void startFeatureCollection() {
/*     */       try {
/* 615 */         String element = (getDefaultPrefix() == null) ? this.fc : (getDefaultPrefix() + ":" + this.fc);
/* 618 */         AttributesImpl atts = new AttributesImpl();
/* 620 */         if (this.lockId != null)
/* 621 */           atts.addAttribute("", "lockId", "lockId", "", this.lockId); 
/* 624 */         this.contentHandler.startElement("", "", element, atts);
/* 626 */       } catch (SAXException se) {
/* 627 */         throw new RuntimeException(se);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void endFeatureCollection() {
/* 632 */       end(this.fc);
/*     */     }
/*     */     
/*     */     public void handleFeatureCollection(FeatureCollection<?, ?> collection) {
/* 641 */       startFeatureCollection();
/* 642 */       if (this.collectionBounding)
/* 643 */         writeBounds((BoundingBox)collection.getBounds()); 
/*     */     }
/*     */     
/*     */     public void writeBounds(BoundingBox bounds) {
/*     */       try {
/* 657 */         String boundedBy = this.geometryTranslator.getDefaultPrefix() + ":" + "boundedBy";
/* 660 */         this.contentHandler.startElement("", "", boundedBy, this.NULL_ATTS);
/* 663 */         Envelope env = null;
/* 664 */         if (bounds != null)
/* 665 */           env = new Envelope(new Coordinate(bounds.getMinX(), bounds.getMinY()), new Coordinate(bounds.getMaxX(), bounds.getMaxY())); 
/* 667 */         this.geometryTranslator.encode(env, this.srsName);
/* 668 */         this.contentHandler.endElement("", "", boundedBy);
/* 669 */       } catch (SAXException se) {
/* 670 */         throw new RuntimeException(se);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void writeNullBounds() {
/*     */       try {
/* 682 */         String boundedBy = this.geometryTranslator.getDefaultPrefix() + ":boundedBy";
/* 683 */         String nullBox = this.geometryTranslator.getDefaultPrefix() + ":null";
/* 685 */         this.contentHandler.startElement("", "", boundedBy, this.NULL_ATTS);
/* 686 */         this.contentHandler.startElement("", "", nullBox, this.NULL_ATTS);
/* 687 */         this.contentHandler.characters("unknown".toCharArray(), 0, "unknown".length());
/* 688 */         this.contentHandler.endElement("", "", nullBox);
/* 689 */         this.contentHandler.endElement("", "", boundedBy);
/* 690 */       } catch (SAXException se) {
/* 691 */         throw new RuntimeException(se);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void endFeatureCollection(FeatureCollection<?, ?> collection) {
/* 701 */       endFeatureCollection();
/*     */     }
/*     */     
/*     */     public void endFeature(Feature f) {
/*     */       try {
/* 713 */         Name typeName = f.getType().getName();
/* 714 */         String name = typeName.getLocalPart();
/* 716 */         if (this.currentPrefix != null)
/* 717 */           name = this.currentPrefix + ":" + name; 
/* 719 */         this.contentHandler.endElement("", "", name);
/* 720 */         this.contentHandler.endElement("", "", this.memberString);
/* 721 */       } catch (Exception e) {
/* 722 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void handleAttribute(PropertyDescriptor descriptor, Object value) {
/*     */       try {
/* 736 */         if (value != null) {
/* 737 */           String name = descriptor.getName().getLocalPart();
/* 743 */           if (this.prefixGml && name.equals("boundedBy") && value instanceof Geometry) {
/* 751 */             Envelope envelopeInternal = ((Geometry)value).getEnvelopeInternal();
/* 752 */             CoordinateReferenceSystem crs = null;
/* 753 */             if (descriptor instanceof GeometryDescriptor) {
/* 754 */               GeometryDescriptor geometryDescriptor = (GeometryDescriptor)descriptor;
/* 755 */               crs = geometryDescriptor.getCoordinateReferenceSystem();
/*     */             } 
/* 758 */             ReferencedEnvelope bounds = new ReferencedEnvelope(envelopeInternal, crs);
/* 760 */             writeBounds((BoundingBox)bounds);
/*     */           } else {
/* 762 */             String thisPrefix = this.currentPrefix;
/* 764 */             if (this.prefixGml && FeatureTransformer.gmlAtts.contains(name))
/* 765 */               thisPrefix = "gml"; 
/* 768 */             if (thisPrefix != null)
/* 769 */               name = thisPrefix + ":" + name; 
/* 772 */             this.contentHandler.startElement("", "", name, this.NULL_ATTS);
/* 774 */             if (value instanceof Geometry) {
/* 775 */               if (this.dimension == 0) {
/* 777 */                 GeometryDescriptor geometryType = (GeometryDescriptor)descriptor;
/* 778 */                 CoordinateReferenceSystem crs = geometryType.getCoordinateReferenceSystem();
/* 779 */                 if (crs == null) {
/* 782 */                   this.dimension = 2;
/*     */                 } else {
/* 786 */                   this.dimension = crs.getCoordinateSystem().getDimension();
/* 788 */                   if (this.dimension == 3)
/* 789 */                     setDimension(this.dimension); 
/*     */                 } 
/*     */               } 
/* 793 */               this.geometryTranslator.encode((Geometry)value, this.srsName);
/* 794 */             } else if (value instanceof Date) {
/* 795 */               String text = null;
/* 796 */               if (value instanceof Date) {
/* 797 */                 text = DateUtil.serializeSqlDate((Date)value);
/* 798 */               } else if (value instanceof Time) {
/* 799 */                 text = DateUtil.serializeSqlTime((Time)value);
/*     */               } else {
/* 801 */                 text = DateUtil.serializeDateTime((Date)value);
/*     */               } 
/* 802 */               this.contentHandler.characters(text.toCharArray(), 0, text.length());
/*     */             } else {
/* 805 */               String text = value.toString();
/* 806 */               this.contentHandler.characters(text.toCharArray(), 0, text.length());
/*     */             } 
/* 810 */             this.contentHandler.endElement("", "", name);
/*     */           } 
/*     */         } 
/* 816 */       } catch (Exception e) {
/* 817 */         throw new IllegalStateException("Could not transform " + descriptor.getName() + ":" + e, e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void handleFeature(Feature f) {
/*     */       try {
/* 838 */         this.contentHandler.startElement("", "", this.memberString, this.NULL_ATTS);
/* 840 */         FeatureType type = f.getType();
/* 841 */         String name = type.getName().getLocalPart();
/* 842 */         String namespaceURI = type.getName().getNamespaceURI();
/* 843 */         if (namespaceURI != null) {
/* 844 */           this.currentPrefix = getNamespaceSupport().getPrefix(namespaceURI);
/* 845 */           if (this.currentPrefix == null) {
/* 846 */             this.currentPrefix = (String)type.getUserData().get("prefix");
/* 847 */             if (this.currentPrefix != null)
/* 848 */               getNamespaceSupport().declarePrefix(this.currentPrefix, namespaceURI); 
/*     */           } 
/*     */         } 
/* 853 */         if (this.currentPrefix == null)
/* 854 */           this.currentPrefix = this.types.findPrefix(type); 
/* 857 */         if (this.currentPrefix == null)
/* 858 */           throw new IllegalStateException("FeatureType namespace/prefix unknown for " + name + "look up in: " + this.types); 
/* 860 */         if (this.currentPrefix.length() != 0)
/* 864 */           name = this.currentPrefix + ":" + name; 
/* 867 */         Attributes fidAtts = encodeFeatureId(f);
/* 869 */         this.contentHandler.startElement("", "", name, fidAtts);
/* 872 */         if (this.featureBounding && f.getBounds() != null && !f.getBounds().isEmpty())
/* 876 */           if (!this.prefixGml || f.getProperty("boundedBy") == null)
/* 879 */             writeBounds(f.getBounds());  
/* 882 */       } catch (Exception e) {
/* 883 */         throw new IllegalStateException("Could not transform " + f.getIdentifier() + " :" + e, e);
/*     */       } 
/*     */     }
/*     */     
/*     */     protected Attributes encodeFeatureId(Feature f) {
/* 888 */       AttributesImpl fidAtts = new AttributesImpl();
/* 889 */       String fid = f.getIdentifier().getID();
/* 891 */       if (fid != null)
/* 892 */         fidAtts.addAttribute("", "fid", "fid", "fids", fid); 
/* 895 */       return fidAtts;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\producer\FeatureTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */