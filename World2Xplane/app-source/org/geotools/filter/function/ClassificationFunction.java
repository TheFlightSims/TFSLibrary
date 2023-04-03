/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.filter.DefaultExpression;
/*     */ import org.geotools.filter.Expression;
/*     */ import org.geotools.filter.FunctionExpression;
/*     */ import org.geotools.filter.LiteralExpression;
/*     */ import org.geotools.util.ProgressListener;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ 
/*     */ public abstract class ClassificationFunction extends DefaultExpression implements FunctionExpression {
/*  50 */   protected static final Logger LOGGER = Logging.getLogger("org.geotools.filter.function");
/*     */   
/*     */   FunctionName name;
/*     */   
/*  55 */   List params = new ArrayList(2);
/*     */   
/*     */   Literal fallback;
/*     */   
/*     */   ProgressListener progress;
/*     */   
/*     */   public ClassificationFunction(FunctionName name) {
/*  62 */     this.name = name;
/*  63 */     this.expressionType = 114;
/*     */   }
/*     */   
/*     */   public int getArgCount() {
/*  67 */     return this.name.getArgumentNames().size();
/*     */   }
/*     */   
/*     */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/*  74 */     return visitor.visit((Function)this, extraData);
/*     */   }
/*     */   
/*     */   public Object evaluate(SimpleFeature feature) {
/*  79 */     return evaluate(feature);
/*     */   }
/*     */   
/*     */   public abstract Object evaluate(Object paramObject);
/*     */   
/*     */   public void setFallbackValue(Literal fallback) {
/*  85 */     this.fallback = fallback;
/*     */   }
/*     */   
/*     */   public Literal getFallbackValue() {
/*  88 */     return this.fallback;
/*     */   }
/*     */   
/*     */   public Expression[] getArgs() {
/*  95 */     List parameters = getParameters();
/*  96 */     return (Expression[])parameters.toArray((Object[])new Expression[parameters.size()]);
/*     */   }
/*     */   
/*     */   public void setArgs(Expression[] args) {
/* 103 */     List<Expression> parameters = new ArrayList();
/* 104 */     for (int i = 0; i < args.length; i++)
/* 105 */       parameters.add(i, args[i]); 
/* 107 */     setParameters(parameters);
/*     */   }
/*     */   
/*     */   public String getName() {
/* 117 */     return this.name.getName();
/*     */   }
/*     */   
/*     */   public FunctionName getFunctionName() {
/* 121 */     return this.name;
/*     */   }
/*     */   
/*     */   public List getParameters() {
/* 128 */     return this.params;
/*     */   }
/*     */   
/*     */   public void setParameters(List params) {
/* 135 */     this.params = params;
/*     */   }
/*     */   
/*     */   public ProgressListener getProgressListener() {
/* 139 */     return this.progress;
/*     */   }
/*     */   
/*     */   public void setProgressListener(ProgressListener progress) {
/* 143 */     this.progress = progress;
/*     */   }
/*     */   
/*     */   public int getNumberOfClasses() {
/* 150 */     return getClasses();
/*     */   }
/*     */   
/*     */   public int getClasses() {
/* 154 */     LiteralExpression classes = getParameters().get(1);
/* 155 */     return ((Integer)classes.evaluate(null, Integer.class)).intValue();
/*     */   }
/*     */   
/*     */   public void setNumberOfClasses(int classes) {
/* 162 */     setClasses(classes);
/*     */   }
/*     */   
/*     */   public void setClasses(int classes) {
/* 166 */     FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/* 167 */     Literal expression = ff.literal(classes);
/* 168 */     getParameters().set(1, expression);
/*     */   }
/*     */   
/*     */   public Expression getExpression() {
/* 172 */     return getParameters().get(0);
/*     */   }
/*     */   
/*     */   public void setExpression(Expression e) {
/* 176 */     getParameters().set(0, e);
/*     */   }
/*     */   
/*     */   public Map getImplementationHints() {
/* 183 */     return Collections.EMPTY_MAP;
/*     */   }
/*     */   
/*     */   protected int decimalPlaces(double slotWidth) {
/* 192 */     String str = Double.toString(slotWidth);
/* 193 */     if (str.indexOf(".") > -1)
/* 194 */       while (str.endsWith("0"))
/* 195 */         str = str.substring(0, str.length() - 1);  
/* 198 */     int intPart = (new Double(Math.floor(slotWidth))).intValue();
/* 199 */     double decPart = slotWidth - intPart;
/* 200 */     int intPoints = Integer.toString(intPart).length();
/* 201 */     int decPoints = str.length() - intPoints;
/* 202 */     if (str.indexOf(".") > -1)
/* 203 */       decPoints--; 
/* 205 */     if (decPart == 0.0D)
/* 206 */       decPoints = 0; 
/* 209 */     if (intPart == 0) {
/* 210 */       if (decPoints > 6)
/* 211 */         return 5; 
/* 212 */       if (decPoints > 0)
/* 213 */         return decPoints; 
/* 215 */       return 1;
/*     */     } 
/* 217 */     if (decPoints == 0)
/* 218 */       return 0; 
/* 220 */     int chars = intPoints + decPoints;
/* 221 */     if (chars < 6)
/* 222 */       return decPoints; 
/* 223 */     if (intPoints > 4)
/* 224 */       return 1; 
/* 226 */     return 5 - intPoints;
/*     */   }
/*     */   
/*     */   protected double round(double value, int decimalPlaces) {
/* 243 */     double divisor = Math.pow(10.0D, decimalPlaces);
/* 244 */     double newVal = value * divisor;
/* 245 */     newVal = (new Long(Math.round(newVal))).intValue() / divisor;
/* 246 */     return newVal;
/*     */   }
/*     */   
/*     */   protected double fixRound(double value, int decimalPlaces, boolean up) {
/* 261 */     double divisor = Math.pow(10.0D, decimalPlaces);
/* 262 */     double newVal = value * divisor;
/* 263 */     if (up) {
/* 263 */       newVal++;
/*     */     } else {
/* 264 */       newVal--;
/*     */     } 
/* 265 */     newVal /= divisor;
/* 266 */     return newVal;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\ClassificationFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */