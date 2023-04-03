/*     */ package org.geotools.feature.simple;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.math.BigInteger;
/*     */ import java.net.URI;
/*     */ import java.sql.Date;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Collections;
/*     */ import javax.xml.namespace.QName;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.geotools.feature.type.FeatureTypeFactoryImpl;
/*     */ import org.geotools.feature.type.SchemaImpl;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.FeatureTypeFactory;
/*     */ import org.opengis.feature.type.GeometryType;
/*     */ import org.opengis.feature.type.Name;
/*     */ 
/*     */ public class SimpleSchema extends SchemaImpl {
/*  65 */   private static FeatureTypeFactory factory = (FeatureTypeFactory)new FeatureTypeFactoryImpl();
/*     */   
/*     */   public static final String NAMESPACE = "http://www.geotools.org/simple";
/*     */   
/*  73 */   public static final AttributeType BOOLEAN = factory.createAttributeType((Name)new NameImpl("http://www.geotools.org/simple", "boolean"), Boolean.class, false, false, Collections.EMPTY_LIST, (AttributeType)null, null);
/*     */   
/*  78 */   public static final AttributeType STRING = factory.createAttributeType((Name)new NameImpl("http://www.geotools.org/simple", "string"), String.class, false, false, Collections.EMPTY_LIST, (AttributeType)null, null);
/*     */   
/*  83 */   public static final AttributeType HEXBINARY = factory.createAttributeType((Name)new NameImpl("http://www.geotools.org/simple", "string"), byte[].class, false, false, Collections.EMPTY_LIST, (AttributeType)null, null);
/*     */   
/*  88 */   public static final AttributeType QNAME = factory.createAttributeType((Name)new NameImpl("http://www.geotools.org/simple", "QName"), QName.class, false, false, Collections.EMPTY_LIST, (AttributeType)null, null);
/*     */   
/*  93 */   public static final AttributeType URI = factory.createAttributeType((Name)new NameImpl("http://www.geotools.org/simple", "anyUri"), URI.class, false, false, Collections.EMPTY_LIST, (AttributeType)null, null);
/*     */   
/* 108 */   public static final AttributeType INT = factory.createAttributeType((Name)new NameImpl("http://www.geotools.org/simple", "int"), Integer.class, false, false, Collections.EMPTY_LIST, null, null);
/*     */   
/* 115 */   public static final AttributeType INTEGER = factory.createAttributeType((Name)new NameImpl("http://www.geotools.org/simple", "integer"), BigInteger.class, false, false, Collections.EMPTY_LIST, null, null);
/*     */   
/* 122 */   public static final AttributeType FLOAT = factory.createAttributeType((Name)new NameImpl("http://www.geotools.org/simple", "float"), Float.class, false, false, Collections.EMPTY_LIST, null, null);
/*     */   
/* 127 */   public static final AttributeType DOUBLE = factory.createAttributeType((Name)new NameImpl("http://www.geotools.org/simple", "double"), Double.class, false, false, Collections.EMPTY_LIST, null, null);
/*     */   
/* 132 */   public static final AttributeType LONG = factory.createAttributeType((Name)new NameImpl("http://www.geotools.org/simple", "long"), Long.class, false, false, Collections.EMPTY_LIST, null, null);
/*     */   
/* 137 */   public static final AttributeType SHORT = factory.createAttributeType((Name)new NameImpl("http://www.geotools.org/simple", "short"), Short.class, false, false, Collections.EMPTY_LIST, null, null);
/*     */   
/* 142 */   public static final AttributeType BYTE = factory.createAttributeType((Name)new NameImpl("http://www.geotools.org/simple", "byte"), Byte.class, false, false, Collections.EMPTY_LIST, null, null);
/*     */   
/* 151 */   public static final AttributeType DATE = factory.createAttributeType((Name)new NameImpl("http://www.geotools.org/simple", "date"), Date.class, false, false, Collections.EMPTY_LIST, (AttributeType)null, null);
/*     */   
/* 156 */   public static final AttributeType TIME = factory.createAttributeType((Name)new NameImpl("http://www.geotools.org/simple", "time"), Time.class, false, false, Collections.EMPTY_LIST, (AttributeType)null, null);
/*     */   
/* 165 */   public static final AttributeType DATETIME = factory.createAttributeType((Name)new NameImpl("http://www.geotools.org/simple", "datetime"), Timestamp.class, false, false, Collections.EMPTY_LIST, (AttributeType)null, null);
/*     */   
/* 174 */   public static final GeometryType GEOMETRY = factory.createGeometryType((Name)new NameImpl("http://www.geotools.org/simple", "GeometryPropertyType"), Geometry.class, null, false, false, Collections.EMPTY_LIST, null, null);
/*     */   
/* 179 */   public static final GeometryType POINT = factory.createGeometryType((Name)new NameImpl("http://www.geotools.org/simple", "PointPropertyType"), Point.class, null, false, false, Collections.EMPTY_LIST, null, null);
/*     */   
/* 184 */   public static final GeometryType LINESTRING = factory.createGeometryType((Name)new NameImpl("http://www.geotools.org/simple", "LineStringPropertyType"), LineString.class, null, false, false, Collections.EMPTY_LIST, null, null);
/*     */   
/* 194 */   public static final GeometryType POLYGON = factory.createGeometryType((Name)new NameImpl("http://www.geotools.org/simple", "PolygonPropertyType"), Polygon.class, null, false, false, Collections.EMPTY_LIST, null, null);
/*     */   
/* 199 */   public static final GeometryType MULTIGEOMETRY = factory.createGeometryType((Name)new NameImpl("http://www.geotools.org/simple", "MultiGeometryPropertyType"), GeometryCollection.class, null, false, false, Collections.EMPTY_LIST, null, null);
/*     */   
/* 205 */   public static final GeometryType MULTIPOINT = factory.createGeometryType((Name)new NameImpl("http://www.geotools.org/simple", "MultiPointPropertyType"), MultiPoint.class, null, false, false, Collections.EMPTY_LIST, null, null);
/*     */   
/* 211 */   public static final GeometryType MULTILINESTRING = factory.createGeometryType((Name)new NameImpl("http://www.geotools.org/simple", "MultiLineStringPropertyType"), MultiLineString.class, null, false, false, Collections.EMPTY_LIST, null, null);
/*     */   
/* 217 */   public static final GeometryType MULTIPOLYGON = factory.createGeometryType((Name)new NameImpl("http://www.geotools.org/simple", "MultiPolytonPropertyType"), MultiPolygon.class, null, false, false, Collections.EMPTY_LIST, (AttributeType)MULTIGEOMETRY, null);
/*     */   
/*     */   public SimpleSchema() {
/* 223 */     super("http://www.geotools.org/simple");
/* 225 */     put(INTEGER.getName(), INTEGER);
/* 226 */     put(DOUBLE.getName(), DOUBLE);
/* 227 */     put(LONG.getName(), LONG);
/* 228 */     put(FLOAT.getName(), FLOAT);
/* 229 */     put(SHORT.getName(), SHORT);
/* 230 */     put(BYTE.getName(), BYTE);
/* 232 */     put(STRING.getName(), STRING);
/* 233 */     put(BOOLEAN.getName(), BOOLEAN);
/* 234 */     put(QNAME.getName(), QNAME);
/* 235 */     put(URI.getName(), URI);
/* 237 */     put(DATE.getName(), DATE);
/* 238 */     put(DATETIME.getName(), DATETIME);
/* 240 */     put(GEOMETRY.getName(), (AttributeType)GEOMETRY);
/* 241 */     put(POINT.getName(), (AttributeType)POINT);
/* 242 */     put(LINESTRING.getName(), (AttributeType)LINESTRING);
/* 244 */     put(POLYGON.getName(), (AttributeType)POLYGON);
/* 245 */     put(MULTIGEOMETRY.getName(), (AttributeType)MULTIGEOMETRY);
/* 246 */     put(MULTIGEOMETRY.getName(), (AttributeType)MULTIGEOMETRY);
/* 247 */     put(MULTIPOINT.getName(), (AttributeType)MULTIPOINT);
/* 248 */     put(MULTILINESTRING.getName(), (AttributeType)MULTILINESTRING);
/* 249 */     put(MULTIPOLYGON.getName(), (AttributeType)MULTIPOLYGON);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\simple\SimpleSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */