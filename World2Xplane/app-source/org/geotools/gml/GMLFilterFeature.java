/*     */ package org.geotools.gml;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.Vector;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.feature.IllegalAttributeException;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.XMLFilterImpl;
/*     */ 
/*     */ public class GMLFilterFeature extends XMLFilterImpl implements GMLHandlerJTS {
/*  56 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.data.gml");
/*     */   
/*     */   private GMLHandlerFeature parent;
/*     */   
/*  73 */   private Vector attributes = new Vector();
/*     */   
/*  74 */   private Vector attributeNames = new Vector();
/*     */   
/*  75 */   private String fid = null;
/*     */   
/*     */   private boolean insideAttribute = false;
/*     */   
/*     */   private boolean insideFeature = false;
/*     */   
/*  88 */   private Object tempValue = null;
/*     */   
/*  89 */   private String attName = "";
/*     */   
/*     */   private String NAMESPACE;
/*     */   
/*  97 */   private String typeName = "GenericFeature";
/*     */   
/* 102 */   private StringBuffer characters = new StringBuffer();
/*     */   
/*     */   public GMLFilterFeature(GMLHandlerFeature parent) {
/* 111 */     this.parent = parent;
/*     */   }
/*     */   
/*     */   public void setSchema(String uri) {}
/*     */   
/*     */   public void geometry(Geometry geometry) {
/* 129 */     if (this.insideFeature) {
/* 130 */       if (this.attName.equals("")) {
/* 131 */         this.attributeNames.addElement("geometry");
/*     */       } else {
/* 133 */         this.attributeNames.addElement(this.attName);
/*     */       } 
/* 136 */       this.attributes.addElement(geometry);
/* 137 */       endAttribute();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/* 166 */     this.characters.setLength(0);
/* 168 */     if (localName.endsWith("Collection")) {
/* 170 */       this.NAMESPACE = namespaceURI;
/*     */       return;
/*     */     } 
/* 178 */     if (isFeatureMember(localName)) {
/* 179 */       this.attributes = new Vector();
/* 180 */       this.attributeNames = new Vector();
/* 183 */       this.insideFeature = true;
/* 184 */       this.tempValue = null;
/*     */     } else {
/* 187 */       if (this.insideFeature) {
/* 189 */         for (int i = 0; i < atts.getLength(); i++) {
/* 190 */           String name = atts.getLocalName(i);
/* 192 */           if (name.equalsIgnoreCase("fid")) {
/* 194 */             this.typeName = new String(localName);
/* 197 */             this.fid = atts.getValue(i);
/*     */           } else {
/* 199 */             this.attributes.add(atts.getValue(i));
/* 200 */             this.attributeNames.add(name);
/*     */           } 
/*     */         } 
/* 204 */         if (!this.typeName.equalsIgnoreCase(localName))
/* 205 */           if (this.attName.equals("")) {
/* 207 */             this.attName = localName;
/*     */           } else {
/* 210 */             this.attName += "/" + localName;
/*     */           }  
/* 216 */         this.insideAttribute = true;
/*     */         return;
/*     */       } 
/* 219 */       if (!this.insideAttribute)
/* 222 */         this.parent.startElement(namespaceURI, localName, qName, atts); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isFeatureMember(String localName) {
/* 227 */     return (localName.endsWith("Member") && !localName.endsWith("StringMember") && !localName.endsWith("polygonMember") && !localName.endsWith("pointMember"));
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/* 246 */     this.characters.append(ch, start, length);
/*     */   }
/*     */   
/*     */   private void handleCharacters() throws SAXException {
/* 253 */     if (this.characters.length() == 0)
/*     */       return; 
/* 259 */     String rawAttribute = this.characters.toString().trim();
/* 260 */     this.characters.setLength(0);
/* 262 */     if (this.insideAttribute && !rawAttribute.equals("")) {
/* 263 */       LOGGER.info("raw att = " + rawAttribute);
/*     */       try {
/* 266 */         this.tempValue = new Integer(rawAttribute);
/* 267 */       } catch (NumberFormatException e1) {
/*     */         try {
/* 269 */           this.tempValue = new Double(rawAttribute);
/* 270 */         } catch (NumberFormatException e2) {
/* 271 */           if (this.tempValue instanceof StringBuffer) {
/* 272 */             ((StringBuffer)this.tempValue).append(" " + rawAttribute);
/*     */           } else {
/* 274 */             this.tempValue = new StringBuffer(rawAttribute);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 279 */       this.parent.characters(rawAttribute.toCharArray(), 0, rawAttribute.length());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
/* 297 */     handleCharacters();
/* 298 */     if (isFeatureMember(localName)) {
/* 299 */       SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
/* 300 */       tb.setName(this.typeName);
/* 301 */       tb.setNamespaceURI(namespaceURI);
/* 326 */       for (int i = 0, ii = this.attributes.size(); i < ii; i++) {
/* 327 */         String name = this.attributeNames.get(i);
/* 328 */         Class<?> clazz = this.attributes.get(i).getClass();
/* 329 */         tb.add(name, clazz);
/*     */       } 
/* 332 */       SimpleFeatureType featureType = tb.buildFeatureType();
/*     */       try {
/* 335 */         SimpleFeature feature = SimpleFeatureBuilder.build(featureType, this.attributes, this.fid);
/* 336 */         this.parent.feature(feature);
/* 337 */       } catch (IllegalAttributeException ife) {}
/* 343 */       this.attName = "";
/* 344 */       this.insideFeature = false;
/* 345 */     } else if (this.insideAttribute) {
/* 347 */       if (this.tempValue != null && !this.tempValue.toString().trim().equals("")) {
/* 348 */         if (this.tempValue instanceof StringBuffer)
/* 349 */           this.tempValue = this.tempValue.toString(); 
/* 352 */         this.attributes.add(this.tempValue);
/* 353 */         this.attributeNames.add(this.attName);
/* 354 */         this.tempValue = null;
/*     */       } 
/* 357 */       endAttribute();
/*     */     } else {
/* 359 */       this.parent.endElement(namespaceURI, localName, qName);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endAttribute() {
/* 371 */     int index = this.attName.lastIndexOf('/');
/* 373 */     if (index > -1) {
/* 375 */       this.attName = this.attName.substring(0, index);
/*     */     } else {
/* 377 */       this.attName = "";
/*     */     } 
/* 381 */     this.insideAttribute = false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\GMLFilterFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */