/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.filter.expression.PropertyAccessor;
/*     */ import org.geotools.filter.expression.PropertyAccessorFactory;
/*     */ import org.geotools.filter.expression.PropertyAccessors;
/*     */ import org.geotools.util.Converters;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.xml.sax.helpers.NamespaceSupport;
/*     */ 
/*     */ public class AttributeExpressionImpl extends DefaultExpression implements AttributeExpression {
/*  52 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.core");
/*     */   
/*     */   protected String attPath;
/*     */   
/*  58 */   protected SimpleFeatureType schema = null;
/*     */   
/*     */   NamespaceSupport namespaceSupport;
/*     */   
/*     */   protected boolean lenient = true;
/*     */   
/*     */   private Hints hints;
/*     */   
/*     */   private PropertyAccessor lastAccessor;
/*     */   
/*     */   protected AttributeExpressionImpl(SimpleFeatureType schema) {
/*  81 */     this.schema = schema;
/*  82 */     this.expressionType = 113;
/*     */   }
/*     */   
/*     */   public AttributeExpressionImpl(String xpath) {
/*  91 */     this.attPath = xpath;
/*  92 */     this.schema = null;
/*  93 */     this.namespaceSupport = null;
/*  94 */     this.hints = null;
/*  95 */     this.expressionType = 113;
/*     */   }
/*     */   
/*     */   public AttributeExpressionImpl(Name name) {
/* 104 */     this.attPath = name.getLocalPart();
/* 105 */     this.schema = null;
/* 106 */     if (name.getNamespaceURI() != null) {
/* 107 */       this.namespaceSupport = new NamespaceSupport();
/* 108 */       this.namespaceSupport.declarePrefix("", name.getNamespaceURI());
/*     */     } else {
/* 110 */       this.namespaceSupport = null;
/*     */     } 
/* 112 */     this.expressionType = 113;
/*     */   }
/*     */   
/*     */   public AttributeExpressionImpl(String xpath, NamespaceSupport namespaceContext) {
/* 122 */     this.attPath = xpath;
/* 123 */     this.schema = null;
/* 124 */     this.namespaceSupport = namespaceContext;
/* 125 */     this.expressionType = 113;
/*     */   }
/*     */   
/*     */   public AttributeExpressionImpl(String xpath, Hints hints) {
/* 134 */     this.attPath = xpath;
/* 135 */     this.schema = null;
/* 136 */     this.namespaceSupport = null;
/* 137 */     this.hints = hints;
/* 138 */     this.expressionType = 113;
/*     */   }
/*     */   
/*     */   public NamespaceSupport getNamespaceContext() {
/* 142 */     return this.namespaceSupport;
/*     */   }
/*     */   
/*     */   protected AttributeExpressionImpl(SimpleFeatureType schema, String attPath) throws IllegalFilterException {
/* 156 */     this.schema = schema;
/* 157 */     this.expressionType = 113;
/* 158 */     setAttributePath(attPath);
/*     */   }
/*     */   
/*     */   public final void setAttributePath(String attPath) throws IllegalFilterException {
/* 172 */     setPropertyName(attPath);
/*     */   }
/*     */   
/*     */   public final String getAttributePath() {
/* 181 */     return getPropertyName();
/*     */   }
/*     */   
/*     */   public String getPropertyName() {
/* 190 */     return this.attPath;
/*     */   }
/*     */   
/*     */   public void setPropertyName(String attPath) {
/* 194 */     LOGGER.entering("ExpressionAttribute", "setAttributePath", attPath);
/* 195 */     if (LOGGER.isLoggable(Level.FINEST))
/* 196 */       LOGGER.finest("schema: " + this.schema + "\n\nattribute: " + attPath); 
/* 198 */     if (this.schema != null) {
/* 199 */       if (this.schema.getDescriptor(attPath) != null) {
/* 200 */         this.attPath = attPath;
/*     */       } else {
/* 203 */         throw new IllegalFilterException("Attribute: " + attPath + " is not in stated schema " + this.schema.getTypeName() + ".");
/*     */       } 
/*     */     } else {
/* 209 */       this.attPath = attPath;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object evaluate(SimpleFeature feature) {
/* 221 */     return evaluate(feature, (Class)null);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object obj) {
/* 231 */     return evaluate(obj, (Class)null);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object obj, Class target) {
/* 244 */     PropertyAccessor accessor = getLastPropertyAccessor();
/* 245 */     AtomicReference<Object> value = new AtomicReference();
/* 246 */     AtomicReference<Exception> e = new AtomicReference<Exception>();
/* 248 */     if (accessor == null || !accessor.canHandle(obj, this.attPath, target) || !tryAccessor(accessor, obj, target, value, e)) {
/* 250 */       boolean success = false;
/* 251 */       if (this.namespaceSupport != null && this.hints == null)
/* 252 */         this.hints = new Hints((RenderingHints.Key)PropertyAccessorFactory.NAMESPACE_CONTEXT, this.namespaceSupport); 
/* 254 */       List<PropertyAccessor> accessors = PropertyAccessors.findPropertyAccessors(obj, this.attPath, target, this.hints);
/* 257 */       if (accessors != null) {
/* 258 */         Iterator<PropertyAccessor> it = accessors.iterator();
/* 259 */         while (!success && it.hasNext()) {
/* 260 */           accessor = it.next();
/* 261 */           success = tryAccessor(accessor, obj, target, value, e);
/*     */         } 
/*     */       } 
/* 266 */       if (!success) {
/* 267 */         if (this.lenient)
/* 267 */           return null; 
/* 268 */         throw new IllegalArgumentException("Could not find working property accessor for attribute (" + this.attPath + ") in object (" + obj + ")", (Throwable)e.get());
/*     */       } 
/* 272 */       setLastPropertyAccessor(accessor);
/*     */     } 
/* 277 */     if (target == null)
/* 278 */       return value.get(); 
/* 281 */     return Converters.convert(value.get(), target);
/*     */   }
/*     */   
/*     */   private boolean tryAccessor(PropertyAccessor accessor, Object obj, Class target, AtomicReference<Object> value, AtomicReference<Exception> ex) {
/*     */     try {
/* 289 */       value.set(accessor.get(obj, this.attPath, target));
/* 290 */       return true;
/* 291 */     } catch (Exception e) {
/* 292 */       ex.set(e);
/* 293 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private synchronized PropertyAccessor getLastPropertyAccessor() {
/* 302 */     return this.lastAccessor;
/*     */   }
/*     */   
/*     */   private synchronized void setLastPropertyAccessor(PropertyAccessor accessor) {
/* 306 */     this.lastAccessor = accessor;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 315 */     return this.attPath;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 329 */     if (obj == null)
/* 330 */       return false; 
/* 332 */     if (obj.getClass() == getClass()) {
/* 333 */       AttributeExpressionImpl expAttr = (AttributeExpressionImpl)obj;
/* 335 */       boolean isEqual = (expAttr.getType() == this.expressionType);
/* 336 */       if (LOGGER.isLoggable(Level.FINEST))
/* 337 */         LOGGER.finest("expression type match:" + isEqual + "; in:" + expAttr.getType() + "; out:" + this.expressionType); 
/* 339 */       isEqual = (expAttr.attPath != null) ? ((isEqual && expAttr.attPath.equals(this.attPath))) : ((isEqual && this.attPath == null));
/* 342 */       if (LOGGER.isLoggable(Level.FINEST))
/* 343 */         LOGGER.finest("attribute match:" + isEqual + "; in:" + expAttr.getAttributePath() + "; out:" + this.attPath); 
/* 345 */       isEqual = (expAttr.schema != null) ? ((isEqual && expAttr.schema.equals(this.schema))) : ((isEqual && this.schema == null));
/* 348 */       if (LOGGER.isLoggable(Level.FINEST))
/* 349 */         LOGGER.finest("schema match:" + isEqual + "; in:" + expAttr.schema + "; out:" + this.schema); 
/* 352 */       return isEqual;
/*     */     } 
/* 354 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 364 */     int result = 17;
/* 365 */     result = 37 * result + ((this.attPath == null) ? 0 : this.attPath.hashCode());
/* 366 */     result = 37 * result + ((this.schema == null) ? 0 : this.schema.hashCode());
/* 367 */     return result;
/*     */   }
/*     */   
/*     */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 381 */     return visitor.visit(this, extraData);
/*     */   }
/*     */   
/*     */   public void setLenient(boolean lenient) {
/* 390 */     this.lenient = lenient;
/*     */   }
/*     */   
/*     */   public boolean isLenient() {
/* 399 */     return this.lenient;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\AttributeExpressionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */