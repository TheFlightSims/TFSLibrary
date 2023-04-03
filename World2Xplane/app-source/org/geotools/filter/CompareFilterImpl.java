/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.util.Converters;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public abstract class CompareFilterImpl extends BinaryComparisonAbstract implements CompareFilter {
/*  54 */   static final Logger LOGGER = Logging.getLogger("org.geotools.core");
/*     */   
/*     */   protected CompareFilterImpl(short filterType) throws IllegalFilterException {
/*  65 */     super(CommonFactoryFinder.getFilterFactory(null));
/*  66 */     if (isCompareFilter(filterType)) {
/*  67 */       this.filterType = filterType;
/*     */     } else {
/*  69 */       throw new IllegalFilterException("Attempted to create compare filter with non-compare type.");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected CompareFilterImpl(FilterFactory factory, Expression e1, Expression e2) {
/*  75 */     this(factory, e1, e2, true);
/*     */   }
/*     */   
/*     */   protected CompareFilterImpl(FilterFactory factory, Expression e1, Expression e2, boolean matchCase) {
/*  79 */     super(factory, e1, e2, matchCase);
/*     */   }
/*     */   
/*     */   public final void addLeftValue(Expression leftValue) throws IllegalFilterException {
/*  94 */     setExpression1(leftValue);
/*     */   }
/*     */   
/*     */   public void setExpression1(Expression leftValue) {
/*  99 */     if (isMathFilter(this.filterType)) {
/* 100 */       if (DefaultExpression.isMathExpression(leftValue) || this.permissiveConstruction) {
/* 102 */         this.expression1 = leftValue;
/*     */       } else {
/* 104 */         throw new IllegalFilterException("Attempted to add non-math expression to math filter.");
/*     */       } 
/*     */     } else {
/* 110 */       this.expression1 = leftValue;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void addRightValue(Expression rightValue) throws IllegalFilterException {
/* 126 */     setExpression2(rightValue);
/*     */   }
/*     */   
/*     */   public void setExpression2(Expression rightValue) {
/* 131 */     if (isMathFilter(this.filterType)) {
/* 132 */       if (DefaultExpression.isMathExpression(rightValue) || this.permissiveConstruction) {
/* 134 */         this.expression2 = rightValue;
/*     */       } else {
/* 137 */         throw new IllegalFilterException("Attempted to add non-math expression to math filter.");
/*     */       } 
/*     */     } else {
/* 143 */       this.expression2 = rightValue;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final Expression getLeftValue() {
/* 154 */     return (Expression)getExpression1();
/*     */   }
/*     */   
/*     */   public final Expression getRightValue() {
/* 165 */     return (Expression)getExpression2();
/*     */   }
/*     */   
/*     */   public boolean evaluate(SimpleFeature feature) {
/* 177 */     return evaluate(feature);
/*     */   }
/*     */   
/*     */   protected int compare(Comparable<Comparable> leftObj, Comparable rightObj) {
/* 189 */     if (!(leftObj instanceof Number) || !(rightObj instanceof Number)) {
/* 192 */       if (leftObj.getClass() != rightObj.getClass()) {
/* 196 */         if (leftObj instanceof Date || rightObj instanceof Date) {
/* 198 */           if (!(leftObj instanceof Date))
/* 199 */             leftObj = (Comparable)Converters.convert(leftObj, Date.class); 
/* 201 */           if (!(rightObj instanceof Date))
/* 202 */             rightObj = (Comparable)Converters.convert(rightObj, Date.class); 
/* 205 */           if (leftObj == null || rightObj == null) {
/* 206 */             leftObj = leftObj.toString();
/* 207 */             rightObj = rightObj.toString();
/*     */           } 
/* 209 */         } else if (leftObj instanceof Number && rightObj.getClass() == String.class) {
/*     */           try {
/* 212 */             rightObj = new Double(Double.parseDouble((String)rightObj));
/* 213 */             leftObj = new Double(((Number)leftObj).doubleValue());
/* 215 */           } catch (Exception e) {
/* 217 */             leftObj = leftObj.toString();
/* 218 */             rightObj = rightObj.toString();
/*     */           } 
/* 221 */         } else if (leftObj.getClass() == String.class && rightObj instanceof Number) {
/*     */           try {
/* 224 */             leftObj = new Double(Double.parseDouble((String)leftObj));
/* 225 */             rightObj = new Double(((Number)rightObj).doubleValue());
/* 227 */           } catch (Exception e) {
/* 229 */             leftObj = leftObj.toString();
/* 230 */             rightObj = rightObj.toString();
/*     */           } 
/*     */         } else {
/* 235 */           leftObj = leftObj.toString();
/* 236 */           rightObj = rightObj.toString();
/*     */         } 
/* 238 */       } else if (leftObj instanceof String && rightObj instanceof String) {
/*     */         try {
/* 241 */           leftObj = new Double(Double.parseDouble((String)leftObj));
/* 242 */           rightObj = new Double(Double.parseDouble((String)rightObj));
/* 244 */         } catch (Exception e) {
/* 246 */           leftObj = leftObj.toString();
/* 247 */           rightObj = rightObj.toString();
/*     */         } 
/*     */       } 
/* 250 */       return leftObj.compareTo(rightObj);
/*     */     } 
/* 253 */     double left = ((Number)leftObj).doubleValue();
/* 254 */     double right = ((Number)rightObj).doubleValue();
/* 255 */     return (left > right) ? 1 : ((left == right) ? 0 : -1);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 265 */     if (this.filterType == 21)
/* 266 */       return "[ " + this.expression1 + " IS NULL ]"; 
/* 269 */     String operator = null;
/* 271 */     if (this.filterType == 14)
/* 272 */       operator = " = "; 
/* 275 */     if (this.filterType == 15)
/* 276 */       operator = " < "; 
/* 279 */     if (this.filterType == 16)
/* 280 */       operator = " > "; 
/* 283 */     if (this.filterType == 17)
/* 284 */       operator = " <= "; 
/* 287 */     if (this.filterType == 18)
/* 288 */       operator = " >= "; 
/* 291 */     if (this.filterType == 23)
/* 292 */       operator = " != "; 
/* 295 */     return "[ " + this.expression1 + operator + this.expression2 + " ]";
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 309 */     if (obj instanceof CompareFilterImpl) {
/* 310 */       CompareFilterImpl cFilter = (CompareFilterImpl)obj;
/* 314 */       int filterType = Filters.getFilterType(this);
/* 315 */       return (filterType == Filters.getFilterType(cFilter) && (this.expression1 == cFilter.getExpression1() || (this.expression1 != null && this.expression1.equals(cFilter.getExpression1()))) && (this.expression2 == cFilter.getExpression2() || (this.expression2 != null && this.expression2.equals(cFilter.getExpression2()))));
/*     */     } 
/* 321 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 331 */     int result = 17;
/* 332 */     result = 37 * result + this.filterType;
/* 333 */     result = 37 * result + ((this.expression1 == null) ? 0 : this.expression1.hashCode());
/* 335 */     result = 37 * result + ((this.expression2 == null) ? 0 : this.expression2.hashCode());
/* 338 */     return result;
/*     */   }
/*     */   
/*     */   public abstract Object accept(FilterVisitor paramFilterVisitor, Object paramObject);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\CompareFilterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */