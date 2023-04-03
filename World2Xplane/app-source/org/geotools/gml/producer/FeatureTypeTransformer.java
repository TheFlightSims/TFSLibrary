/*     */ package org.geotools.gml.producer;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.sql.Date;
/*     */ import java.sql.Time;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.geotools.xml.transform.TransformerBase;
/*     */ import org.geotools.xml.transform.Translator;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.filter.BinaryComparisonOperator;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ import org.xml.sax.helpers.NamespaceSupport;
/*     */ 
/*     */ public class FeatureTypeTransformer extends TransformerBase {
/* 181 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.gml.producer.FeatureTypeTransformer");
/*     */   
/*     */   private static final String SCHEMA_NS = "http://www.w3.org/2001/XMLSchema";
/*     */   
/*     */   public Translator createTranslator(ContentHandler handler) {
/* 194 */     FeatureTypeTranslator translator = new FeatureTypeTranslator(handler);
/* 196 */     return (Translator)translator;
/*     */   }
/*     */   
/*     */   public static class FeatureTypeTranslator extends TransformerBase.TranslatorSupport {
/*     */     public FeatureTypeTranslator(ContentHandler handler) {
/* 214 */       super(handler, "xs", "http://www.w3.org/2001/XMLSchema");
/*     */     }
/*     */     
/*     */     public void encode(Object o) throws IllegalArgumentException {
/* 229 */       if (o instanceof SimpleFeatureType) {
/* 230 */         encode((SimpleFeatureType)o);
/*     */       } else {
/* 232 */         throw new IllegalArgumentException("Translator does not know how to translate " + o.getClass().getName());
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void encode(SimpleFeatureType type) {
/* 246 */       List<AttributeDescriptor> attributes = type.getAttributeDescriptors();
/*     */       try {
/* 249 */         startSchemaType(type.getTypeName(), type.getName().getNamespaceURI());
/* 251 */         for (int i = 0; i < attributes.size(); i++)
/* 252 */           encode(attributes.get(i)); 
/* 255 */         endSchemaType();
/* 256 */       } catch (SAXException e) {
/* 257 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     protected final void startSchemaType(String name, String namespace) throws SAXException {
/* 271 */       AttributesImpl atts = new AttributesImpl();
/* 273 */       atts.addAttribute("", "name", "name", "", name + "_Type");
/* 275 */       this.contentHandler.startElement("http://www.w3.org/2001/XMLSchema", "complexType", "xs:complexType", atts);
/* 278 */       this.contentHandler.startElement("http://www.w3.org/2001/XMLSchema", "complexContent", "xs:complexContent", new AttributesImpl());
/* 281 */       atts = new AttributesImpl();
/* 283 */       atts.addAttribute("", "base", "base", "", "gml:AbstractFeatureType");
/* 285 */       this.contentHandler.startElement("http://www.w3.org/2001/XMLSchema", "extension", "xs:extension", atts);
/* 288 */       this.contentHandler.startElement("http://www.w3.org/2001/XMLSchema", "sequence", "xs:sequence", new AttributesImpl());
/*     */     }
/*     */     
/*     */     protected void endSchemaType() throws SAXException {
/* 298 */       this.contentHandler.endElement("http://www.w3.org/2001/XMLSchema", "sequence", "xs:sequence");
/* 300 */       this.contentHandler.endElement("http://www.w3.org/2001/XMLSchema", "extension", "xs:extension");
/* 302 */       this.contentHandler.endElement("http://www.w3.org/2001/XMLSchema", "complexContent", "xs:complexContent");
/* 305 */       this.contentHandler.endElement("http://www.w3.org/2001/XMLSchema", "complexType", "xs:complexType");
/*     */     }
/*     */     
/*     */     protected void encode(AttributeDescriptor attribute) throws SAXException {
/* 318 */       Class<?> type = attribute.getType().getBinding();
/* 320 */       if (Number.class.isAssignableFrom(type)) {
/* 321 */         encodeNumber(attribute);
/* 322 */       } else if (Date.class.isAssignableFrom(type)) {
/* 323 */         encodeDate(attribute);
/* 324 */       } else if (type == String.class) {
/* 325 */         encodeString(attribute);
/* 326 */       } else if (Geometry.class.isAssignableFrom(type)) {
/* 327 */         encodeGeometry(attribute);
/* 328 */       } else if (type == Boolean.class) {
/* 329 */         encodeBoolean(attribute);
/*     */       } else {
/* 335 */         throw new RuntimeException("Cannot encode " + type.getName());
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void encodeBoolean(AttributeDescriptor attribute) throws SAXException {
/* 348 */       AttributesImpl atts = createStandardAttributes(attribute);
/* 350 */       atts.addAttribute("", "type", "type", "", "xs:boolean");
/* 352 */       this.contentHandler.startElement("http://www.w3.org/2001/XMLSchema", "element", "xs:element", atts);
/* 354 */       this.contentHandler.endElement("http://www.w3.org/2001/XMLSchema", "element", "xs:element");
/*     */     }
/*     */     
/*     */     protected void encodeString(AttributeDescriptor attribute) throws SAXException {
/* 366 */       int length = Integer.MAX_VALUE;
/* 368 */       for (Filter f : attribute.getType().getRestrictions()) {
/* 369 */         if (f == Filter.INCLUDE || f == Filter.EXCLUDE)
/*     */           continue; 
/*     */         try {
/* 373 */           if (f instanceof org.opengis.filter.PropertyIsLessThan || f instanceof org.opengis.filter.PropertyIsLessThanOrEqualTo) {
/* 375 */             BinaryComparisonOperator cf = (BinaryComparisonOperator)f;
/* 376 */             Expression e = cf.getExpression1();
/* 377 */             if (e != null && e instanceof org.geotools.filter.LengthFunction) {
/* 378 */               length = Integer.parseInt(((Literal)cf.getExpression2()).getValue().toString());
/*     */               continue;
/*     */             } 
/* 380 */             if (cf.getExpression2() instanceof org.geotools.filter.LengthFunction)
/* 381 */               length = Integer.parseInt(((Literal)cf.getExpression1()).getValue().toString()); 
/*     */           } 
/* 385 */         } catch (Throwable t) {
/* 386 */           length = Integer.MAX_VALUE;
/*     */         } 
/*     */       } 
/* 390 */       AttributesImpl atts = createStandardAttributes(attribute);
/* 392 */       if (length == 0) {
/* 393 */         atts.addAttribute("", "type", "type", "", "xs:string");
/* 395 */         this.contentHandler.startElement("http://www.w3.org/2001/XMLSchema", "element", "xs:element", atts);
/* 398 */         this.contentHandler.endElement("http://www.w3.org/2001/XMLSchema", "element", "xs:element");
/*     */       } else {
/* 400 */         this.contentHandler.startElement("http://www.w3.org/2001/XMLSchema", "element", "xs:element", atts);
/* 403 */         this.contentHandler.startElement("http://www.w3.org/2001/XMLSchema", "simpleType", "xs:simpleType", new AttributesImpl());
/* 406 */         atts = new AttributesImpl();
/* 408 */         atts.addAttribute("", "base", "base", "", "xs:string");
/* 410 */         this.contentHandler.startElement("http://www.w3.org/2001/XMLSchema", "restriction", "xs:restriction", atts);
/* 413 */         atts = new AttributesImpl();
/* 415 */         atts.addAttribute("", "value", "value", "", "" + length);
/* 417 */         this.contentHandler.startElement("http://www.w3.org/2001/XMLSchema", "maxLength", "xs:maxLength", atts);
/* 420 */         this.contentHandler.endElement("http://www.w3.org/2001/XMLSchema", "maxLength", "xs:maxLength");
/* 422 */         this.contentHandler.endElement("http://www.w3.org/2001/XMLSchema", "restriction", "xs:restriction");
/* 425 */         this.contentHandler.endElement("http://www.w3.org/2001/XMLSchema", "simpleType", "xs:simpleType");
/* 428 */         this.contentHandler.endElement("http://www.w3.org/2001/XMLSchema", "element", "xs:element");
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void encodeNumber(AttributeDescriptor attribute) throws SAXException {
/*     */       String typeString;
/* 442 */       AttributesImpl atts = createStandardAttributes(attribute);
/* 444 */       Class<Byte> type = attribute.getType().getBinding();
/* 448 */       if (type == Byte.class) {
/* 449 */         typeString = "xs:byte";
/* 450 */       } else if (type == Short.class) {
/* 451 */         typeString = "xs:short";
/* 452 */       } else if (type == Integer.class) {
/* 453 */         typeString = "xs:int";
/* 454 */       } else if (type == Long.class) {
/* 455 */         typeString = "xs:long";
/* 456 */       } else if (type == Float.class) {
/* 457 */         typeString = "xs:float";
/* 458 */       } else if (type == Double.class) {
/* 459 */         typeString = "xs:double";
/* 460 */       } else if (type == BigInteger.class) {
/* 461 */         typeString = "xs:integer";
/* 462 */       } else if (type == BigDecimal.class) {
/* 463 */         typeString = "xs:decimal";
/* 464 */       } else if (Number.class.isAssignableFrom(type)) {
/* 465 */         typeString = "xs:decimal";
/*     */       } else {
/* 467 */         throw new RuntimeException("Called encode number with invalid attribute type.");
/*     */       } 
/* 471 */       atts.addAttribute("", "type", "type", "", typeString);
/* 473 */       this.contentHandler.startElement("http://www.w3.org/2001/XMLSchema", "element", "xs:element", atts);
/* 475 */       this.contentHandler.endElement("http://www.w3.org/2001/XMLSchema", "element", "xs:element");
/*     */     }
/*     */     
/*     */     protected void encodeDate(AttributeDescriptor attribute) throws SAXException {
/* 487 */       AttributesImpl atts = createStandardAttributes(attribute);
/* 489 */       Class<?> binding = attribute.getType().getBinding();
/* 490 */       if (Date.class.isAssignableFrom(binding)) {
/* 491 */         atts.addAttribute("", "type", "type", "", "xs:date");
/* 492 */       } else if (Time.class.isAssignableFrom(binding)) {
/* 493 */         atts.addAttribute("", "type", "type", "", "xs:time");
/*     */       } else {
/* 495 */         atts.addAttribute("", "type", "type", "", "xs:dateTime");
/*     */       } 
/* 497 */       this.contentHandler.startElement("http://www.w3.org/2001/XMLSchema", "element", "xs:element", atts);
/* 499 */       this.contentHandler.endElement("http://www.w3.org/2001/XMLSchema", "element", "xs:element");
/*     */     }
/*     */     
/*     */     protected void encodeGeometry(AttributeDescriptor attribute) throws SAXException {
/* 512 */       AttributesImpl atts = createStandardAttributes(attribute);
/* 514 */       Class<Point> type = attribute.getType().getBinding();
/* 516 */       String typeString = "";
/* 518 */       FeatureTypeTransformer.LOGGER.finer(type.getName());
/* 520 */       if (type == Point.class) {
/* 521 */         typeString = "gml:PointPropertyType";
/* 522 */       } else if (type == LineString.class) {
/* 523 */         typeString = "gml:LineStringPropertyType";
/* 524 */       } else if (type == Polygon.class) {
/* 525 */         typeString = "gml:PolygonPropertyType";
/* 526 */       } else if (type == MultiPoint.class) {
/* 527 */         typeString = "gml:MultiPointPropertyType";
/* 528 */       } else if (type == MultiLineString.class) {
/* 529 */         typeString = "gml:MultiLineStringPropertyType";
/* 530 */       } else if (type == MultiPolygon.class) {
/* 531 */         typeString = "gml:MultiPolygonPropertyType";
/* 532 */       } else if (type == GeometryCollection.class) {
/* 533 */         typeString = "gml:MultiGeometryPropertyType";
/* 534 */       } else if (type == Geometry.class) {
/* 535 */         typeString = "gml:GeometryAssociationType";
/*     */       } else {
/* 537 */         throw new RuntimeException("Unsupported type: " + type.getName());
/*     */       } 
/* 541 */       atts.addAttribute("", "type", "type", "", typeString);
/* 543 */       this.contentHandler.startElement("http://www.w3.org/2001/XMLSchema", "element", "xs:element", atts);
/* 545 */       this.contentHandler.endElement("http://www.w3.org/2001/XMLSchema", "element", "xs:element");
/*     */     }
/*     */     
/*     */     protected AttributesImpl createStandardAttributes(AttributeDescriptor attribute) {
/* 582 */       AttributesImpl atts = new AttributesImpl();
/* 584 */       atts.addAttribute("", "name", "name", "", attribute.getLocalName());
/* 586 */       if (attribute.isNillable() && attribute.getMinOccurs() == 0) {
/* 587 */         atts.addAttribute("", "minOccurs", "minOccurs", "", "0");
/* 589 */         atts.addAttribute("", "nillable", "nillable", "", "true");
/*     */       } else {
/* 591 */         atts.addAttribute("", "minOccurs", "minOccurs", "", "1");
/* 593 */         atts.addAttribute("", "nillable", "nillable", "", "false");
/*     */       } 
/* 596 */       return atts;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\producer\FeatureTypeTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */