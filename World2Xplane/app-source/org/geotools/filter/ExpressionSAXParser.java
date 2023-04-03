/*     */ package org.geotools.filter;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.ArrayList;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ public class ExpressionSAXParser {
/*  42 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter");
/*     */   
/*     */   private FilterFactory ff;
/*     */   
/*  48 */   private ExpressionSAXParser expFactory = null;
/*     */   
/*  51 */   private Expression curExprssn = null;
/*     */   
/*  57 */   private String currentState = null;
/*     */   
/*  60 */   private ArrayList accumalationOfExpressions = new ArrayList();
/*     */   
/*  63 */   private String declaredType = null;
/*     */   
/*     */   private boolean readyFlag = false;
/*     */   
/*     */   private SimpleFeatureType schema;
/*     */   
/*     */   private boolean readChars = false;
/*     */   
/*     */   public ExpressionSAXParser() {
/*  84 */     this(FilterFactoryFinder.createFilterFactory());
/*     */   }
/*     */   
/*     */   public ExpressionSAXParser(FilterFactory factory) {
/*  87 */     this(null, factory);
/*     */   }
/*     */   
/*     */   public ExpressionSAXParser(SimpleFeatureType schema) {
/*  96 */     this(schema, FilterFactoryFinder.createFilterFactory());
/*     */   }
/*     */   
/*     */   public ExpressionSAXParser(SimpleFeatureType schema, FilterFactory factory) {
/* 100 */     this.schema = schema;
/* 101 */     this.ff = factory;
/*     */   }
/*     */   
/*     */   public void setFilterFactory(FilterFactory factory) {
/* 105 */     this.ff = factory;
/*     */   }
/*     */   
/*     */   public void start(String declaredType, Attributes atts) throws IllegalFilterException {
/* 118 */     LOGGER.finer("incoming type: " + declaredType);
/* 119 */     LOGGER.finer("declared type: " + this.declaredType);
/* 120 */     LOGGER.finer("current state: " + this.currentState);
/* 122 */     if (this.expFactory == null) {
/* 124 */       this.declaredType = declaredType;
/* 126 */       if (DefaultExpression.isFunctionExpression(convertType(declaredType))) {
/* 128 */         this.expFactory = new ExpressionSAXParser(this.schema);
/* 129 */         this.curExprssn = this.ff.createFunctionExpression(getFunctionName(atts));
/* 130 */         LOGGER.finer("is <function> expression");
/*     */       } 
/* 135 */       if (DefaultExpression.isMathExpression(convertType(declaredType))) {
/* 136 */         this.expFactory = new ExpressionSAXParser(this.schema);
/* 137 */         this.curExprssn = this.ff.createMathExpression(convertType(declaredType));
/* 139 */         LOGGER.finer("is math expression");
/* 140 */       } else if (DefaultExpression.isLiteralExpression(convertType(declaredType))) {
/* 142 */         this.curExprssn = this.ff.createLiteralExpression();
/* 143 */         this.readChars = true;
/* 144 */         LOGGER.finer("is literal expression");
/* 145 */       } else if (DefaultExpression.isAttributeExpression(convertType(declaredType))) {
/* 147 */         this.curExprssn = this.ff.createAttributeExpression(this.schema);
/* 148 */         this.readChars = true;
/* 149 */         LOGGER.finer("is attribute expression");
/*     */       } 
/* 152 */       this.currentState = setInitialState(this.curExprssn);
/* 153 */       this.readyFlag = false;
/*     */     } else {
/* 155 */       this.expFactory.start(declaredType, atts);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void end(String message) throws IllegalFilterException {
/* 168 */     LOGGER.finer("declared type: " + this.declaredType);
/* 169 */     LOGGER.finer("end message: " + message);
/* 170 */     LOGGER.finer("current state: " + this.currentState);
/* 171 */     LOGGER.finest("expression factory: " + this.expFactory);
/* 177 */     if (this.expFactory != null) {
/* 178 */       this.expFactory.end(message);
/* 186 */       if (this.expFactory.isReady())
/* 187 */         if (this.currentState.equals("leftValue")) {
/* 188 */           ((MathExpression)this.curExprssn).addLeftValue(this.expFactory.create());
/* 190 */           this.currentState = "rightValue";
/* 191 */           this.expFactory = new ExpressionSAXParser(this.schema);
/* 192 */           LOGGER.finer("just added left value: " + this.currentState);
/* 193 */         } else if (this.currentState.equals("rightValue")) {
/* 194 */           ((MathExpression)this.curExprssn).addRightValue(this.expFactory.create());
/* 196 */           this.currentState = "complete";
/* 197 */           this.expFactory = null;
/* 198 */           LOGGER.finer("just added right value: " + this.currentState);
/* 199 */         } else if (this.currentState.equals("accumulate")) {
/* 200 */           this.accumalationOfExpressions.add(this.expFactory.create());
/* 201 */           this.expFactory = null;
/* 203 */           LOGGER.finer("just added a parameter for a function: " + this.currentState);
/* 205 */           if (((FunctionExpression)this.curExprssn).getArgCount() == this.accumalationOfExpressions.size()) {
/* 208 */             this.currentState = "complete";
/* 209 */             ((FunctionExpression)this.curExprssn).setArgs((Expression[])this.accumalationOfExpressions.toArray((Object[])new Expression[0]));
/*     */           } else {
/* 213 */             this.expFactory = new ExpressionSAXParser(this.schema);
/*     */           } 
/*     */         } else {
/* 216 */           throw new IllegalFilterException("Attempted to add sub expression in a bad state: " + this.currentState);
/*     */         }  
/* 221 */     } else if (this.declaredType.equals(message) && this.currentState.equals("complete")) {
/* 225 */       this.readChars = false;
/* 226 */       this.readyFlag = true;
/*     */     } else {
/* 228 */       throw new IllegalFilterException("Reached end of unready, non-nested expression: " + this.currentState);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isReady() {
/* 241 */     return this.readyFlag;
/*     */   }
/*     */   
/*     */   public void message(String message, boolean convertToNumber) throws IllegalFilterException {
/* 263 */     LOGGER.finer("incoming message: " + message);
/* 264 */     LOGGER.finer("should read chars: " + this.readChars);
/* 266 */     if (this.readChars) {
/* 268 */       if (this.curExprssn instanceof AttributeExpression) {
/* 269 */         LOGGER.finer("...");
/* 279 */         String[] splitName = message.split("[.:/]");
/* 280 */         String newAttName = message;
/* 282 */         if (splitName.length == 1) {
/* 283 */           newAttName = splitName[0];
/*     */         } else {
/* 291 */           newAttName = splitName[splitName.length - 1];
/*     */         } 
/* 294 */         LOGGER.finer("setting attribute expression: " + newAttName);
/* 295 */         ((AttributeExpression)this.curExprssn).setAttributePath(newAttName);
/* 296 */         LOGGER.finer("...");
/* 297 */         this.currentState = "complete";
/* 298 */         LOGGER.finer("...");
/* 299 */       } else if (this.curExprssn instanceof LiteralExpression) {
/* 309 */         if (convertToNumber) {
/*     */           try {
/* 311 */             Object temp = new Integer(message);
/* 312 */             ((LiteralExpression)this.curExprssn).setLiteral(temp);
/* 313 */             this.currentState = "complete";
/* 314 */           } catch (NumberFormatException nfe1) {
/*     */             try {
/* 316 */               Object temp = new Double(message);
/* 317 */               ((LiteralExpression)this.curExprssn).setLiteral(temp);
/* 318 */               this.currentState = "complete";
/* 319 */             } catch (NumberFormatException nfe2) {
/* 320 */               Object temp = message;
/* 321 */               ((LiteralExpression)this.curExprssn).setLiteral(temp);
/* 322 */               this.currentState = "complete";
/*     */             } 
/*     */           } 
/*     */         } else {
/* 326 */           Object temp = message;
/* 327 */           ((LiteralExpression)this.curExprssn).setLiteral(temp);
/* 328 */           this.currentState = "complete";
/*     */         } 
/* 330 */       } else if (this.expFactory != null) {
/* 331 */         this.expFactory.message(message, convertToNumber);
/*     */       } 
/* 333 */     } else if (this.expFactory != null) {
/* 334 */       this.expFactory.message(message, convertToNumber);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void geometry(Geometry geometry) throws IllegalFilterException {
/* 348 */     LOGGER.finer("got geometry: " + geometry.toString());
/* 352 */     this.curExprssn = this.ff.createLiteralExpression();
/* 353 */     ((LiteralExpression)this.curExprssn).setLiteral(geometry);
/* 354 */     LOGGER.finer("set expression: " + this.curExprssn.toString());
/* 355 */     this.currentState = "complete";
/* 356 */     LOGGER.finer("set current state: " + this.currentState);
/*     */   }
/*     */   
/*     */   public Expression create() {
/* 369 */     LOGGER.finer("about to create expression: " + this.curExprssn.toString());
/* 372 */     return this.curExprssn;
/*     */   }
/*     */   
/*     */   private static String setInitialState(Expression expression) throws IllegalFilterException {
/* 389 */     if (expression instanceof MathExpression)
/* 390 */       return "leftValue"; 
/* 391 */     if (expression instanceof AttributeExpression || expression instanceof LiteralExpression)
/* 393 */       return ""; 
/* 394 */     if (expression instanceof FunctionExpression)
/* 396 */       return "accumulate"; 
/* 399 */     throw new IllegalFilterException("Created illegal expression: " + expression.getClass().toString());
/*     */   }
/*     */   
/*     */   protected static short convertType(String expType) {
/* 414 */     if (expType.equals("Add"))
/* 415 */       return 105; 
/* 416 */     if (expType.equals("Sub"))
/* 417 */       return 106; 
/* 418 */     if (expType.equals("Mul"))
/* 419 */       return 107; 
/* 420 */     if (expType.equals("Div"))
/* 421 */       return 108; 
/* 422 */     if (expType.equals("PropertyName"))
/* 423 */       return 109; 
/* 424 */     if (expType.equals("Literal"))
/* 425 */       return 101; 
/* 427 */     if (expType.equals("Function"))
/* 428 */       return 114; 
/* 431 */     return 115;
/*     */   }
/*     */   
/*     */   public String getFunctionName(Attributes map) {
/* 443 */     String result = map.getValue("name");
/* 444 */     if (result == null)
/* 446 */       result = map.getValue("ogc:name"); 
/* 448 */     if (result == null)
/* 450 */       result = map.getValue("ows:name"); 
/* 452 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\ExpressionSAXParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */