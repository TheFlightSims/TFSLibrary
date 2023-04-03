/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import org.geotools.util.Converters;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.filter.expression.Add;
/*     */ import org.opengis.filter.expression.Divide;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.expression.Multiply;
/*     */ import org.opengis.filter.expression.NilExpression;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ import org.opengis.filter.expression.Subtract;
/*     */ 
/*     */ public class ConstantExpression implements LiteralExpression, Cloneable {
/*  54 */   public static final ConstantExpression NULL = constant((Object)null);
/*     */   
/*  55 */   public static final ConstantExpression BLACK = color(Color.BLACK);
/*     */   
/*  56 */   public static final ConstantExpression ZERO = constant(0);
/*     */   
/*  57 */   public static final ConstantExpression ONE = constant(1);
/*     */   
/*  58 */   public static final ConstantExpression TWO = constant(2);
/*     */   
/*  59 */   public static final ConstantExpression UNNAMED = constant("");
/*     */   
/*     */   final short type;
/*     */   
/*     */   Object value;
/*     */   
/*     */   protected ConstantExpression(Object value) {
/*  64 */     this(type(value), value);
/*     */   }
/*     */   
/*     */   protected ConstantExpression(short type, Object value) {
/*  68 */     this.type = type;
/*  69 */     this.value = value;
/*     */   }
/*     */   
/*     */   public final void setLiteral(Object literal) throws IllegalFilterException {
/*  76 */     throw new UnsupportedOperationException("Default value is immutable");
/*     */   }
/*     */   
/*     */   public final Object getValue(SimpleFeature feature) {
/*  83 */     return evaluate(feature);
/*     */   }
/*     */   
/*     */   public Object evaluate(SimpleFeature feature) {
/*  87 */     return getValue();
/*     */   }
/*     */   
/*     */   public Object evaluate(Object object) {
/*  91 */     return getValue();
/*     */   }
/*     */   
/*     */   public <T> T evaluate(Object object, Class<T> context) {
/*  95 */     return (T)Converters.convert(getValue(), context);
/*     */   }
/*     */   
/*     */   public Object getValue() {
/*  99 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(Object constant) {
/* 103 */     throw new UnsupportedOperationException("Default value is immutable");
/*     */   }
/*     */   
/*     */   public short getType() {
/* 107 */     return this.type;
/*     */   }
/*     */   
/*     */   public final Object getLiteral() {
/* 114 */     return getValue();
/*     */   }
/*     */   
/*     */   public void accept(final FilterVisitor visitor) {
/* 121 */     accept(new ExpressionVisitor() {
/*     */           public Object visit(Add expression, Object extraData) {
/* 123 */             return null;
/*     */           }
/*     */           
/*     */           public Object visit(Divide expression, Object extraData) {
/* 127 */             return null;
/*     */           }
/*     */           
/*     */           public Object visit(Function expression, Object extraData) {
/* 131 */             return null;
/*     */           }
/*     */           
/*     */           public Object visit(Literal expression, Object extraData) {
/* 135 */             visitor.visit(ConstantExpression.this);
/* 137 */             return null;
/*     */           }
/*     */           
/*     */           public Object visit(Multiply expression, Object extraData) {
/* 141 */             return null;
/*     */           }
/*     */           
/*     */           public Object visit(PropertyName expression, Object extraData) {
/* 145 */             return null;
/*     */           }
/*     */           
/*     */           public Object visit(Subtract expression, Object extraData) {
/* 149 */             return null;
/*     */           }
/*     */           
/*     */           public Object visit(NilExpression arg0, Object arg1) {
/* 153 */             return null;
/*     */           }
/*     */         },  null);
/*     */   }
/*     */   
/*     */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 159 */     return visitor.visit(this, extraData);
/*     */   }
/*     */   
/*     */   protected Object clone() throws CloneNotSupportedException {
/* 163 */     return new ConstantExpression(this.value);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 167 */     if (!(obj instanceof LiteralExpression))
/* 168 */       return false; 
/* 171 */     LiteralExpression other = (LiteralExpression)obj;
/* 172 */     Object otherLiteral = other.getValue();
/* 174 */     if (this.value == null)
/* 175 */       return (otherLiteral == null); 
/* 178 */     if (this.value.getClass().isAssignableFrom(otherLiteral.getClass()))
/* 179 */       return this.value.equals(other.getValue()); 
/* 182 */     if (this.value instanceof Number && 
/* 183 */       otherLiteral instanceof Number) {
/* 184 */       Number myNumber = (Number)this.value;
/* 185 */       Number otherNumber = (Number)otherLiteral;
/* 187 */       return (myNumber.doubleValue() == otherNumber.doubleValue());
/*     */     } 
/* 192 */     String myString = this.value.toString();
/* 193 */     String otherString = otherLiteral.toString();
/* 195 */     return myString.equals(otherString);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 199 */     if (this.value instanceof com.vividsolutions.jts.geom.Geometry || this.value instanceof java.util.Date)
/* 201 */       return this.value.hashCode(); 
/* 204 */     return (this.value == null) ? 0 : this.value.toString().hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 208 */     if (this.value instanceof Color) {
/* 209 */       Color color = (Color)this.value;
/* 210 */       String redCode = Integer.toHexString(color.getRed());
/* 211 */       String greenCode = Integer.toHexString(color.getGreen());
/* 212 */       String blueCode = Integer.toHexString(color.getBlue());
/* 214 */       if (redCode.length() == 1)
/* 215 */         redCode = "0" + redCode; 
/* 218 */       if (greenCode.length() == 1)
/* 219 */         greenCode = "0" + greenCode; 
/* 222 */       if (blueCode.length() == 1)
/* 223 */         blueCode = "0" + blueCode; 
/*     */     } 
/* 227 */     return (this.value == null) ? "NULL" : this.value.toString();
/*     */   }
/*     */   
/*     */   public static ConstantExpression color(Color color) {
/* 232 */     if (color == null)
/* 233 */       return NULL; 
/* 236 */     String redCode = Integer.toHexString(color.getRed());
/* 237 */     String greenCode = Integer.toHexString(color.getGreen());
/* 238 */     String blueCode = Integer.toHexString(color.getBlue());
/* 240 */     if (redCode.length() == 1)
/* 241 */       redCode = "0" + redCode; 
/* 244 */     if (greenCode.length() == 1)
/* 245 */       greenCode = "0" + greenCode; 
/* 248 */     if (blueCode.length() == 1)
/* 249 */       blueCode = "0" + blueCode; 
/* 252 */     String colorCode = ("#" + redCode + greenCode + blueCode).toUpperCase();
/* 254 */     return new ConstantExpression(colorCode);
/*     */   }
/*     */   
/*     */   public static ConstantExpression constant(double number) {
/* 258 */     return new ConstantExpression(new Double(number));
/*     */   }
/*     */   
/*     */   public static ConstantExpression constant(int number) {
/* 262 */     return new ConstantExpression(new Integer(number));
/*     */   }
/*     */   
/*     */   public static ConstantExpression constant(Object value) {
/* 266 */     return new ConstantExpression(value);
/*     */   }
/*     */   
/*     */   static short type(Object value) {
/* 270 */     if (value instanceof Number) {
/* 271 */       if (value instanceof Double)
/* 272 */         return 101; 
/* 273 */       if (value instanceof java.math.BigDecimal)
/* 274 */         return 101; 
/* 276 */       return 102;
/*     */     } 
/* 278 */     if (value instanceof com.vividsolutions.jts.geom.Geometry)
/* 279 */       return 104; 
/* 282 */     return 103;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\ConstantExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */