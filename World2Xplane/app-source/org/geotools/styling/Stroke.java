/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import org.geotools.filter.ConstantExpression;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.Graphic;
/*     */ import org.opengis.style.GraphicFill;
/*     */ import org.opengis.style.GraphicStroke;
/*     */ import org.opengis.style.Stroke;
/*     */ 
/*     */ public interface Stroke extends Stroke {
/*  89 */   public static final Stroke DEFAULT = new ConstantStroke() {
/*     */       public Expression getColor() {
/*  91 */         return (Expression)ConstantExpression.BLACK;
/*     */       }
/*     */       
/*     */       public Color getColor(SimpleFeature f) {
/*  95 */         return Color.BLACK;
/*     */       }
/*     */       
/*     */       public Expression getWidth() {
/*  99 */         return (Expression)ConstantExpression.ONE;
/*     */       }
/*     */       
/*     */       public Expression getOpacity() {
/* 103 */         return (Expression)ConstantExpression.ONE;
/*     */       }
/*     */       
/*     */       public Expression getLineJoin() {
/* 107 */         return (Expression)ConstantExpression.constant("miter");
/*     */       }
/*     */       
/*     */       public Expression getLineCap() {
/* 111 */         return (Expression)ConstantExpression.constant("butt");
/*     */       }
/*     */       
/*     */       public float[] getDashArray() {
/* 115 */         return null;
/*     */       }
/*     */       
/*     */       public Expression getDashOffset() {
/* 119 */         return (Expression)ConstantExpression.ZERO;
/*     */       }
/*     */       
/*     */       public Graphic getGraphicFill() {
/* 123 */         return Graphic.DEFAULT;
/*     */       }
/*     */       
/*     */       public Graphic getGraphicStroke() {
/* 127 */         return Graphic.NULL;
/*     */       }
/*     */       
/*     */       public Object clone() {
/* 131 */         return this;
/*     */       }
/*     */     };
/*     */   
/* 142 */   public static final Stroke NULL = new ConstantStroke() {
/*     */       public Expression getColor() {
/* 144 */         return (Expression)ConstantExpression.NULL;
/*     */       }
/*     */       
/*     */       public Color getColor(SimpleFeature f) {
/* 148 */         return Color.BLACK;
/*     */       }
/*     */       
/*     */       public Expression getWidth() {
/* 152 */         return (Expression)ConstantExpression.NULL;
/*     */       }
/*     */       
/*     */       public Expression getOpacity() {
/* 156 */         return (Expression)ConstantExpression.NULL;
/*     */       }
/*     */       
/*     */       public Expression getLineJoin() {
/* 160 */         return (Expression)ConstantExpression.NULL;
/*     */       }
/*     */       
/*     */       public Expression getLineCap() {
/* 164 */         return (Expression)ConstantExpression.NULL;
/*     */       }
/*     */       
/*     */       public float[] getDashArray() {
/* 168 */         return new float[0];
/*     */       }
/*     */       
/*     */       public Expression getDashOffset() {
/* 172 */         return (Expression)ConstantExpression.NULL;
/*     */       }
/*     */       
/*     */       public Graphic getGraphicFill() {
/* 176 */         return Graphic.NULL;
/*     */       }
/*     */       
/*     */       public Graphic getGraphicStroke() {
/* 180 */         return Graphic.NULL;
/*     */       }
/*     */     };
/*     */   
/*     */   @Deprecated
/*     */   Color getColor(SimpleFeature paramSimpleFeature);
/*     */   
/*     */   void setColor(Expression paramExpression);
/*     */   
/*     */   void setWidth(Expression paramExpression);
/*     */   
/*     */   void setOpacity(Expression paramExpression);
/*     */   
/*     */   Expression getLineJoin();
/*     */   
/*     */   void setLineJoin(Expression paramExpression);
/*     */   
/*     */   Expression getLineCap();
/*     */   
/*     */   void setLineCap(Expression paramExpression);
/*     */   
/*     */   float[] getDashArray();
/*     */   
/*     */   void setDashArray(float[] paramArrayOffloat);
/*     */   
/*     */   Expression getDashOffset();
/*     */   
/*     */   void setDashOffset(Expression paramExpression);
/*     */   
/*     */   Graphic getGraphicFill();
/*     */   
/*     */   void setGraphicFill(Graphic paramGraphic);
/*     */   
/*     */   Graphic getGraphicStroke();
/*     */   
/*     */   void setGraphicStroke(Graphic paramGraphic);
/*     */   
/*     */   void accept(StyleVisitor paramStyleVisitor);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\Stroke.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */