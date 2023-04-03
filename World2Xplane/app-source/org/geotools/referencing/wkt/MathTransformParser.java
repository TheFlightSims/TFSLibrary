/*     */ package org.geotools.referencing.wkt;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.NoSuchIdentifierException;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransformFactory;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.Operation;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ 
/*     */ public class MathTransformParser extends AbstractParser {
/*     */   protected final MathTransformFactory mtFactory;
/*     */   
/*     */   private String classification;
/*     */   
/*     */   private OperationMethod lastMethod;
/*     */   
/*     */   public MathTransformParser() {
/*  76 */     this(Symbols.DEFAULT);
/*     */   }
/*     */   
/*     */   public MathTransformParser(Symbols symbols) {
/*  88 */     this(symbols, ReferencingFactoryFinder.getMathTransformFactory(null));
/*     */   }
/*     */   
/*     */   public MathTransformParser(Symbols symbols, MathTransformFactory mtFactory) {
/*  98 */     super(symbols);
/*  99 */     this.mtFactory = mtFactory;
/*     */   }
/*     */   
/*     */   public MathTransform parseMathTransform(String text) throws ParseException {
/* 110 */     Element element = getTree(text, new ParsePosition(0));
/* 111 */     MathTransform mt = parseMathTransform(element, true);
/* 112 */     element.close();
/* 113 */     return mt;
/*     */   }
/*     */   
/*     */   protected Object parse(Element element) throws ParseException {
/* 124 */     return parseMathTransform(element, true);
/*     */   }
/*     */   
/*     */   final MathTransform parseMathTransform(Element element, boolean required) throws ParseException {
/* 139 */     this.lastMethod = null;
/* 140 */     this.classification = null;
/* 141 */     Object key = element.peek();
/* 142 */     if (key instanceof Element) {
/* 143 */       String keyword = ((Element)key).keyword.trim().toUpperCase(this.symbols.locale);
/* 144 */       if ("PARAM_MT".equals(keyword))
/* 144 */         return parseParamMT(element); 
/* 145 */       if ("CONCAT_MT".equals(keyword))
/* 145 */         return parseConcatMT(element); 
/* 146 */       if ("INVERSE_MT".equals(keyword))
/* 146 */         return parseInverseMT(element); 
/* 147 */       if ("PASSTHROUGH_MT".equals(keyword))
/* 147 */         return parsePassThroughMT(element); 
/*     */     } 
/* 149 */     if (required)
/* 150 */       throw element.parseFailed(null, Errors.format(187, key)); 
/* 152 */     return null;
/*     */   }
/*     */   
/*     */   private MathTransform parseParamMT(Element parent) throws ParseException {
/*     */     ParameterValueGroup parameters;
/*     */     MathTransform transform;
/* 167 */     Element element = parent.pullElement("PARAM_MT");
/* 168 */     this.classification = element.pullString("classification");
/*     */     try {
/* 171 */       parameters = this.mtFactory.getDefaultParameters(this.classification);
/* 172 */     } catch (NoSuchIdentifierException exception) {
/* 173 */       throw element.parseFailed(exception, null);
/*     */     } 
/*     */     Element param;
/* 180 */     while ((param = element.pullOptionalElement("PARAMETER")) != null) {
/* 181 */       String name = param.pullString("name");
/* 182 */       ParameterValue parameter = parameters.parameter(name);
/* 183 */       Class type = parameter.getDescriptor().getValueClass();
/* 184 */       if (Integer.class.equals(type)) {
/* 185 */         parameter.setValue(param.pullInteger("value"));
/* 186 */       } else if (Double.class.equals(type)) {
/* 187 */         parameter.setValue(param.pullDouble("value"));
/* 188 */       } else if (URI.class.equals(type)) {
/* 189 */         parameter.setValue(URI.create(param.pullString("value")));
/*     */       } else {
/* 191 */         parameter.setValue(param.pullString("value"));
/*     */       } 
/* 193 */       param.close();
/*     */     } 
/* 195 */     element.close();
/*     */     try {
/* 204 */       transform = this.mtFactory.createParameterizedTransform(parameters);
/* 205 */     } catch (FactoryException exception) {
/* 206 */       throw element.parseFailed(exception, null);
/*     */     } 
/* 208 */     this.lastMethod = this.mtFactory.getLastMethodUsed();
/* 209 */     return transform;
/*     */   }
/*     */   
/*     */   private MathTransform parseInverseMT(Element parent) throws ParseException {
/* 224 */     Element element = parent.pullElement("INVERSE_MT");
/*     */     try {
/* 227 */       MathTransform transform = parseMathTransform(element, true).inverse();
/* 228 */       element.close();
/* 229 */       return transform;
/* 231 */     } catch (NoninvertibleTransformException exception) {
/* 232 */       throw element.parseFailed(exception, null);
/*     */     } 
/*     */   }
/*     */   
/*     */   private MathTransform parsePassThroughMT(Element parent) throws ParseException {
/* 248 */     Element element = parent.pullElement("PASSTHROUGH_MT");
/* 249 */     int firstAffectedOrdinate = parent.pullInteger("firstAffectedOrdinate");
/* 250 */     MathTransform transform = parseMathTransform(element, true);
/* 251 */     element.close();
/*     */     try {
/* 253 */       return this.mtFactory.createPassThroughTransform(firstAffectedOrdinate, transform, 0);
/* 254 */     } catch (FactoryException exception) {
/* 255 */       throw element.parseFailed(exception, null);
/*     */     } 
/*     */   }
/*     */   
/*     */   private MathTransform parseConcatMT(Element parent) throws ParseException {
/* 271 */     Element element = parent.pullElement("CONCAT_MT");
/* 272 */     MathTransform transform = parseMathTransform(element, true);
/*     */     MathTransform optionalTransform;
/* 274 */     while ((optionalTransform = parseMathTransform(element, false)) != null) {
/*     */       try {
/* 276 */         transform = this.mtFactory.createConcatenatedTransform(transform, optionalTransform);
/* 277 */       } catch (FactoryException exception) {
/* 278 */         throw element.parseFailed(exception, null);
/*     */       } 
/*     */     } 
/* 281 */     element.close();
/* 282 */     return transform;
/*     */   }
/*     */   
/*     */   final OperationMethod getOperationMethod() {
/* 290 */     if (this.lastMethod == null)
/* 295 */       if (this.classification != null)
/* 296 */         for (OperationMethod method : this.mtFactory.getAvailableMethods(Operation.class)) {
/* 297 */           if (AbstractIdentifiedObject.nameMatches((IdentifiedObject)method, this.classification)) {
/* 298 */             this.lastMethod = method;
/*     */             break;
/*     */           } 
/*     */         }   
/* 304 */     return this.lastMethod;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\wkt\MathTransformParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */