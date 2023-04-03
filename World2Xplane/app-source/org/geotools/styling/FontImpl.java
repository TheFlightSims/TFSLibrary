/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.geotools.filter.IllegalFilterException;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.Font;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class FontImpl implements Font, Cloneable {
/*  46 */   private final List<Expression> fontFamily = new ArrayList<Expression>();
/*     */   
/*  47 */   private Expression fontSize = null;
/*     */   
/*  48 */   private Expression fontStyle = null;
/*     */   
/*  49 */   private Expression fontWeight = null;
/*     */   
/*     */   @Deprecated
/*     */   public Expression getFontFamily() {
/*  64 */     if (this.fontFamily.isEmpty())
/*  65 */       return null; 
/*  67 */     return this.fontFamily.get(0);
/*     */   }
/*     */   
/*     */   public List<Expression> getFamily() {
/*  72 */     return this.fontFamily;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setFontFamily(Expression fontFamily) {
/*  82 */     this.fontFamily.clear();
/*  83 */     this.fontFamily.add(fontFamily);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Expression getFontSize() {
/*  93 */     return this.fontSize;
/*     */   }
/*     */   
/*     */   public Expression getSize() {
/*  96 */     return this.fontSize;
/*     */   }
/*     */   
/*     */   public void setSize(Expression size) {
/*  99 */     this.fontSize = size;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setFontSize(Expression fontSize) {
/* 108 */     this.fontSize = fontSize;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Expression getFontStyle() {
/* 118 */     return this.fontStyle;
/*     */   }
/*     */   
/*     */   public Expression getStyle() {
/* 122 */     return this.fontStyle;
/*     */   }
/*     */   
/*     */   public void setStyle(Expression style) {
/* 125 */     this.fontStyle = style;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setFontStyle(Expression fontStyle) {
/* 134 */     this.fontStyle = fontStyle;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Expression getFontWeight() {
/* 144 */     return this.fontWeight;
/*     */   }
/*     */   
/*     */   public Expression getWeight() {
/* 148 */     return this.fontWeight;
/*     */   }
/*     */   
/*     */   public void setWeight(Expression weight) {
/* 151 */     this.fontWeight = weight;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setFontWeight(Expression fontWeight) {
/* 160 */     this.fontWeight = fontWeight;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 172 */       return super.clone();
/* 173 */     } catch (CloneNotSupportedException e) {
/* 174 */       throw new RuntimeException("This should not happen", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 184 */     int PRIME = 1000003;
/* 185 */     int result = 0;
/* 187 */     if (this.fontFamily != null)
/* 188 */       result = 1000003 * result + this.fontFamily.hashCode(); 
/* 191 */     if (this.fontSize != null)
/* 192 */       result = 1000003 * result + this.fontSize.hashCode(); 
/* 195 */     if (this.fontStyle != null)
/* 196 */       result = 1000003 * result + this.fontStyle.hashCode(); 
/* 199 */     if (this.fontWeight != null)
/* 200 */       result = 1000003 * result + this.fontWeight.hashCode(); 
/* 203 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object oth) {
/* 215 */     if (this == oth)
/* 216 */       return true; 
/* 219 */     if (oth == null)
/* 220 */       return false; 
/* 223 */     if (oth instanceof FontImpl) {
/* 224 */       FontImpl other = (FontImpl)oth;
/* 226 */       return (Utilities.equals(this.fontFamily, other.fontFamily) && Utilities.equals(this.fontSize, other.fontSize) && Utilities.equals(this.fontStyle, other.fontStyle) && Utilities.equals(this.fontWeight, other.fontWeight));
/*     */     } 
/* 232 */     return false;
/*     */   }
/*     */   
/*     */   static Font createDefault(FilterFactory filterFactory) {
/* 240 */     Font font = new FontImpl();
/*     */     try {
/* 242 */       font.setSize((Expression)filterFactory.literal(new Integer(10)));
/* 244 */       font.setStyle((Expression)filterFactory.literal("normal"));
/* 245 */       font.setWeight((Expression)filterFactory.literal("normal"));
/* 246 */       font.setFontFamily((Expression)filterFactory.literal("Serif"));
/* 247 */     } catch (IllegalFilterException ife) {
/* 248 */       throw new RuntimeException("Error creating default", ife);
/*     */     } 
/* 250 */     return font;
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 254 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   static FontImpl cast(Font font) {
/* 258 */     if (font == null)
/* 259 */       return null; 
/* 261 */     if (font instanceof FontImpl)
/* 262 */       return (FontImpl)font; 
/* 265 */     FontImpl copy = new FontImpl();
/* 266 */     copy.getFamily().addAll(font.getFamily());
/* 267 */     copy.setSize(font.getSize());
/* 268 */     copy.setStyle(font.getStyle());
/* 269 */     copy.setWeight(font.getWeight());
/* 270 */     return copy;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\FontImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */