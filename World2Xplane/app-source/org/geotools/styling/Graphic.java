/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.geotools.filter.ConstantExpression;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.AnchorPoint;
/*     */ import org.opengis.style.Displacement;
/*     */ import org.opengis.style.Graphic;
/*     */ import org.opengis.style.GraphicFill;
/*     */ import org.opengis.style.GraphicStroke;
/*     */ import org.opengis.style.GraphicalSymbol;
/*     */ 
/*     */ public interface Graphic extends GraphicLegend, Graphic, GraphicFill, GraphicStroke {
/* 108 */   public static final Graphic DEFAULT = new ConstantGraphic() {
/*     */       public ExternalGraphic[] getExternalGraphics() {
/* 110 */         return ExternalGraphic.EXTERNAL_GRAPHICS_EMPTY;
/*     */       }
/*     */       
/*     */       public Mark[] getMarks() {
/* 114 */         return Mark.MARKS_EMPTY;
/*     */       }
/*     */       
/*     */       public Symbol[] getSymbols() {
/* 118 */         return Symbol.SYMBOLS_EMPTY;
/*     */       }
/*     */       
/*     */       public List<GraphicalSymbol> graphicalSymbols() {
/* 122 */         return Collections.emptyList();
/*     */       }
/*     */       
/*     */       public Expression getOpacity() {
/* 125 */         return (Expression)ConstantExpression.ONE;
/*     */       }
/*     */       
/*     */       public Expression getSize() {
/* 130 */         return Expression.NIL;
/*     */       }
/*     */       
/*     */       public Displacement getDisplacement() {
/* 134 */         return Displacement.DEFAULT;
/*     */       }
/*     */       
/*     */       public Expression getRotation() {
/* 138 */         return (Expression)ConstantExpression.ZERO;
/*     */       }
/*     */     };
/*     */   
/* 151 */   public static final Graphic NULL = new ConstantGraphic() {
/*     */       public ExternalGraphic[] getExternalGraphics() {
/* 153 */         return ExternalGraphic.EXTERNAL_GRAPHICS_EMPTY;
/*     */       }
/*     */       
/*     */       public Mark[] getMarks() {
/* 157 */         return Mark.MARKS_EMPTY;
/*     */       }
/*     */       
/*     */       public Symbol[] getSymbols() {
/* 161 */         return Symbol.SYMBOLS_EMPTY;
/*     */       }
/*     */       
/*     */       public List<GraphicalSymbol> graphicalSymbols() {
/* 164 */         return Collections.emptyList();
/*     */       }
/*     */       
/*     */       public Expression getOpacity() {
/* 167 */         return (Expression)ConstantExpression.NULL;
/*     */       }
/*     */       
/*     */       public Expression getSize() {
/* 171 */         return (Expression)ConstantExpression.NULL;
/*     */       }
/*     */       
/*     */       public Displacement getDisplacement() {
/* 175 */         return Displacement.NULL;
/*     */       }
/*     */       
/*     */       public Expression getRotation() {
/* 179 */         return (Expression)ConstantExpression.NULL;
/*     */       }
/*     */     };
/*     */   
/*     */   List<GraphicalSymbol> graphicalSymbols();
/*     */   
/*     */   ExternalGraphic[] getExternalGraphics();
/*     */   
/*     */   void setExternalGraphics(ExternalGraphic[] paramArrayOfExternalGraphic);
/*     */   
/*     */   void addExternalGraphic(ExternalGraphic paramExternalGraphic);
/*     */   
/*     */   Mark[] getMarks();
/*     */   
/*     */   void setMarks(Mark[] paramArrayOfMark);
/*     */   
/*     */   void addMark(Mark paramMark);
/*     */   
/*     */   Symbol[] getSymbols();
/*     */   
/*     */   @Deprecated
/*     */   void setSymbols(Symbol[] paramArrayOfSymbol);
/*     */   
/*     */   @Deprecated
/*     */   void addSymbol(Symbol paramSymbol);
/*     */   
/*     */   AnchorPoint getAnchorPoint();
/*     */   
/*     */   void setAnchorPoint(AnchorPoint paramAnchorPoint);
/*     */   
/*     */   Expression getOpacity();
/*     */   
/*     */   void setOpacity(Expression paramExpression);
/*     */   
/*     */   Expression getSize();
/*     */   
/*     */   void setSize(Expression paramExpression);
/*     */   
/*     */   Displacement getDisplacement();
/*     */   
/*     */   void setDisplacement(Displacement paramDisplacement);
/*     */   
/*     */   Expression getRotation();
/*     */   
/*     */   void setRotation(Expression paramExpression);
/*     */   
/*     */   Expression getGap();
/*     */   
/*     */   void setGap(Expression paramExpression);
/*     */   
/*     */   Expression getInitialGap();
/*     */   
/*     */   void setInitialGap(Expression paramExpression);
/*     */   
/*     */   void accept(StyleVisitor paramStyleVisitor);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\Graphic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */