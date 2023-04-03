/*     */ package org.geotools.filter;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.math.BigDecimal;
/*     */ import org.geotools.util.Converters;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ 
/*     */ public class LiteralExpressionImpl extends DefaultExpression implements LiteralExpression {
/*  41 */   private static BigDecimal MAX_LONG = BigDecimal.valueOf(Long.MAX_VALUE);
/*     */   
/*  42 */   private static BigDecimal MIN_LONG = BigDecimal.valueOf(Long.MIN_VALUE);
/*     */   
/*  45 */   private Object literal = null;
/*     */   
/*  48 */   private Object parsedValue = null;
/*     */   
/*     */   protected LiteralExpressionImpl() {}
/*     */   
/*     */   protected LiteralExpressionImpl(Object literal) throws IllegalFilterException {
/*  65 */     setLiteral(literal);
/*     */   }
/*     */   
/*     */   protected LiteralExpressionImpl(int value) {
/*     */     try {
/*  77 */       setLiteral(new Integer(value));
/*  78 */     } catch (IllegalFilterException ile) {
/*  81 */       throw new AssertionError("LiteralExpressionImpl is broken, it should accept Integers");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected LiteralExpressionImpl(long value) {
/*     */     try {
/*  88 */       setLiteral(new Long(value));
/*  89 */     } catch (IllegalFilterException ile) {
/*  92 */       throw new AssertionError("LiteralExpressionImpl is broken, it should accept Longs");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected LiteralExpressionImpl(double value) {
/*     */     try {
/* 106 */       setLiteral(new Double(value));
/* 107 */     } catch (IllegalFilterException ile) {
/* 110 */       throw new AssertionError("LiteralExpressionImpl is broken, it should accept Doubles");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected LiteralExpressionImpl(String value) {
/*     */     try {
/* 124 */       setLiteral(value);
/* 125 */     } catch (IllegalFilterException ile) {
/* 128 */       throw new AssertionError("LiteralExpressionImpl is broken, it should accept Strings");
/*     */     } 
/*     */   }
/*     */   
/*     */   public short getType() {
/* 139 */     return this.expressionType;
/*     */   }
/*     */   
/*     */   public final void setLiteral(Object literal) throws IllegalFilterException {
/* 149 */     setValue(literal);
/*     */   }
/*     */   
/*     */   public final Object getLiteral() {
/* 159 */     return getValue();
/*     */   }
/*     */   
/*     */   public Object getValue() {
/* 169 */     return this.literal;
/*     */   }
/*     */   
/*     */   public final void setValue(Object literal) {
/* 180 */     if (literal instanceof Double) {
/* 181 */       this.expressionType = 101;
/* 182 */     } else if (literal instanceof Integer) {
/* 183 */       this.expressionType = 102;
/* 184 */     } else if (literal instanceof Long) {
/* 185 */       this.expressionType = 99;
/* 186 */     } else if (literal instanceof String) {
/* 187 */       this.expressionType = 103;
/* 188 */     } else if (literal instanceof Geometry) {
/* 189 */       this.expressionType = 104;
/*     */     } else {
/* 191 */       this.expressionType = 115;
/*     */     } 
/* 194 */     this.literal = literal;
/*     */   }
/*     */   
/*     */   public Object evaluate(SimpleFeature feature) throws IllegalArgumentException {
/* 216 */     return evaluate(feature);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/* 220 */     return this.literal;
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature, Class context) {
/* 224 */     return Converters.convert(this.literal, context);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 233 */     return (this.literal == null) ? "NULL" : this.literal.toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 250 */     if (obj instanceof LiteralExpressionImpl) {
/* 251 */       LiteralExpressionImpl expLit = (LiteralExpressionImpl)obj;
/* 257 */       if (this.literal == null)
/* 258 */         return (expLit.literal == null); 
/* 259 */       if (expLit.literal == null)
/* 260 */         return false; 
/* 264 */       if (this.expressionType == expLit.expressionType && 
/* 265 */         this.literal.equals(expLit.literal))
/* 266 */         return true; 
/* 271 */       if (this.expressionType == 104)
/* 272 */         return ((Geometry)this.literal).equalsExact((Geometry)expLit.evaluate(null, Geometry.class)); 
/* 273 */       if (this.expressionType == 102)
/* 274 */         return ((Integer)this.literal).equals(expLit.evaluate(null, Integer.class)); 
/* 275 */       if (this.expressionType == 103)
/* 276 */         return ((String)this.literal).equals(expLit.evaluate(null, String.class)); 
/* 277 */       if (this.expressionType == 101)
/* 278 */         return ((Double)this.literal).equals(expLit.evaluate(null, Double.class)); 
/* 279 */       if (this.expressionType == 99)
/* 280 */         return ((Long)this.literal).equals(expLit.evaluate(null, Long.class)); 
/* 283 */       Object other = expLit.evaluate(null, this.literal.getClass());
/* 284 */       if (other != null)
/* 285 */         return other.equals(this.literal); 
/* 288 */       other = expLit.getValue();
/* 289 */       Object converted = evaluate(null, other.getClass());
/* 290 */       if (converted != null)
/* 291 */         return converted.equals(other); 
/* 294 */       String str1 = (String)evaluate(null, String.class);
/* 295 */       String str2 = (String)expLit.evaluate(null, String.class);
/* 296 */       return (str1 != null && str2 != null && str1.equals(str2));
/*     */     } 
/* 299 */     if (obj instanceof Literal) {
/* 301 */       Literal other = (Literal)obj;
/* 302 */       return equals(new LiteralExpressionImpl(other.getValue()));
/*     */     } 
/* 304 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 314 */     int result = 17;
/* 316 */     result = 37 * result + ((this.literal == null) ? 0 : this.literal.hashCode());
/* 317 */     result = 37 * result + this.expressionType;
/* 319 */     return result;
/*     */   }
/*     */   
/*     */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 333 */     return visitor.visit(this, extraData);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\LiteralExpressionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */