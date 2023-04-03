/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import org.opengis.filter.expression.Add;
/*     */ import org.opengis.filter.expression.Divide;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.expression.Multiply;
/*     */ import org.opengis.filter.expression.NilExpression;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ import org.opengis.filter.expression.Subtract;
/*     */ 
/*     */ public class IsStaticExpressionVisitor implements ExpressionVisitor {
/*  50 */   public static final IsStaticExpressionVisitor VISITOR = new IsStaticExpressionVisitor();
/*     */   
/*     */   public Boolean visit(NilExpression expression, Object data) {
/*  57 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */   public Boolean visit(Add expression, Object data) {
/*  62 */     boolean isStatic = ((Boolean)expression.getExpression1().accept(this, data)).booleanValue();
/*  63 */     if (!isStatic)
/*  63 */       return Boolean.valueOf(false); 
/*  64 */     isStatic = ((Boolean)expression.getExpression2().accept(this, data)).booleanValue();
/*  65 */     return Boolean.valueOf(isStatic);
/*     */   }
/*     */   
/*     */   public Boolean visit(Divide expression, Object data) {
/*  70 */     boolean isStatic = ((Boolean)expression.getExpression1().accept(this, data)).booleanValue();
/*  71 */     if (!isStatic)
/*  71 */       return Boolean.valueOf(false); 
/*  72 */     isStatic = ((Boolean)expression.getExpression2().accept(this, data)).booleanValue();
/*  73 */     return Boolean.valueOf(isStatic);
/*     */   }
/*     */   
/*     */   public Boolean visit(Function expression, Object data) {
/*  79 */     boolean isStatic = true;
/*  80 */     if (expression.getParameters() != null)
/*  81 */       for (Expression parameter : expression.getParameters()) {
/*  82 */         isStatic = ((Boolean)parameter.accept(this, data)).booleanValue();
/*  83 */         if (!isStatic)
/*     */           break; 
/*     */       }  
/*  86 */     return Boolean.valueOf(isStatic);
/*     */   }
/*     */   
/*     */   public Boolean visit(Literal expression, Object data) {
/*  93 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */   public Boolean visit(Multiply expression, Object data) {
/* 100 */     boolean isStatic = ((Boolean)expression.getExpression1().accept(this, data)).booleanValue();
/* 101 */     if (!isStatic)
/* 101 */       return Boolean.valueOf(false); 
/* 102 */     isStatic = ((Boolean)expression.getExpression2().accept(this, data)).booleanValue();
/* 103 */     return Boolean.valueOf(isStatic);
/*     */   }
/*     */   
/*     */   public Boolean visit(PropertyName expression, Object data) {
/* 111 */     return Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   public Boolean visit(Subtract expression, Object data) {
/* 118 */     boolean isStatic = ((Boolean)expression.getExpression1().accept(this, data)).booleanValue();
/* 119 */     if (!isStatic)
/* 119 */       return Boolean.valueOf(false); 
/* 120 */     isStatic = ((Boolean)expression.getExpression2().accept(this, data)).booleanValue();
/* 121 */     return Boolean.valueOf(isStatic);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\IsStaticExpressionVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */