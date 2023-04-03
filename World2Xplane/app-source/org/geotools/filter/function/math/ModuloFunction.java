/*     */ package org.geotools.filter.function.math;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.geotools.util.Converters;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class ModuloFunction implements Function {
/*  37 */   static FunctionName NAME = (FunctionName)new FunctionNameImpl("modulo", Integer.class, new Parameter[] { FunctionNameImpl.parameter("dividend", Integer.class), FunctionNameImpl.parameter("divisor", Integer.class) });
/*     */   
/*     */   private final FunctionName functionName;
/*     */   
/*     */   private final List<Expression> parameters;
/*     */   
/*     */   private final Literal fallback;
/*     */   
/*     */   public ModuloFunction() {
/*  51 */     this.functionName = NAME;
/*  52 */     this.parameters = Collections.emptyList();
/*  53 */     this.fallback = null;
/*     */   }
/*     */   
/*     */   public ModuloFunction(List<Expression> parameters, Literal fallback) {
/*  57 */     if (parameters == null)
/*  58 */       throw new NullPointerException("parameters must be provided"); 
/*  61 */     if (parameters.size() != NAME.getArguments().size())
/*  62 */       throw new IllegalArgumentException(NAME.getArguments().size() + " function parameters are required"); 
/*  65 */     this.functionName = NAME;
/*  66 */     this.parameters = parameters;
/*  67 */     this.fallback = fallback;
/*     */   }
/*     */   
/*     */   public Object evaluate(Object object) {
/*  71 */     return evaluate(object, this.functionName.getReturn().getType());
/*     */   }
/*     */   
/*     */   public <T> T evaluate(Object object, Class<T> context) {
/*  75 */     Expression dividendExpression = this.parameters.get(0);
/*  76 */     int dividend = ((Integer)dividendExpression.evaluate(object, Integer.class)).intValue();
/*  78 */     Expression divisorExpression = this.parameters.get(1);
/*  79 */     int divisor = ((Integer)divisorExpression.evaluate(object, Integer.class)).intValue();
/*  81 */     if (divisor == 0)
/*  82 */       throw new IllegalArgumentException("divisor cannot be 0"); 
/*  85 */     int modulo = dividend - divisor * (int)Math.floor(dividend / divisor);
/*  87 */     return (T)Converters.convert(Integer.valueOf(modulo), context);
/*     */   }
/*     */   
/*     */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/*  91 */     return visitor.visit(this, extraData);
/*     */   }
/*     */   
/*     */   public String getName() {
/*  95 */     return this.functionName.getName();
/*     */   }
/*     */   
/*     */   public FunctionName getFunctionName() {
/*  99 */     return this.functionName;
/*     */   }
/*     */   
/*     */   public List<Expression> getParameters() {
/* 103 */     return Collections.unmodifiableList(this.parameters);
/*     */   }
/*     */   
/*     */   public Literal getFallbackValue() {
/* 107 */     return this.fallback;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\math\ModuloFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */