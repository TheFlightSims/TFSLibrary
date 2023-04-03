/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public abstract class FunctionExpressionImpl extends DefaultExpression implements FunctionExpression {
/*     */   protected String name;
/*     */   
/*     */   protected List<Expression> params;
/*     */   
/*     */   protected Literal fallback;
/*     */   
/*     */   protected FunctionName functionName;
/*     */   
/*     */   protected FunctionExpressionImpl(FunctionName functionName) {
/*  81 */     this(functionName.getName(), (Literal)null);
/*  82 */     this.functionName = functionName;
/*     */   }
/*     */   
/*     */   protected FunctionExpressionImpl(String name) {
/*  85 */     this((Name)new NameImpl(name));
/*     */   }
/*     */   
/*     */   protected FunctionExpressionImpl(Name name) {
/*  88 */     this(name, (Literal)null);
/*     */   }
/*     */   
/*     */   protected FunctionExpressionImpl(String name, Literal fallback) {
/*  94 */     this((Name)new NameImpl(name), fallback);
/*     */   }
/*     */   
/*     */   protected FunctionExpressionImpl(Name name, Literal fallback) {
/* 100 */     this.functionName = (FunctionName)new FunctionNameImpl(name, (Class)null, new Parameter[0]);
/* 101 */     this.name = name.getLocalPart();
/* 102 */     this.fallback = fallback;
/* 103 */     this.params = new ArrayList<Expression>();
/*     */   }
/*     */   
/*     */   public short getType() {
/* 112 */     return 114;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 122 */     return this.name;
/*     */   }
/*     */   
/*     */   public synchronized FunctionName getFunctionName() {
/* 126 */     if (this.functionName == null)
/* 127 */       this.functionName = (FunctionName)new FunctionNameImpl(getName(), getArgCount()); 
/* 129 */     return this.functionName;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 136 */     this.name = name;
/*     */   }
/*     */   
/*     */   public Literal getFallbackValue() {
/* 140 */     return this.fallback;
/*     */   }
/*     */   
/*     */   public void setFallbackValue(Literal fallback) {
/* 143 */     this.fallback = fallback;
/*     */   }
/*     */   
/*     */   public List<Expression> getParameters() {
/* 149 */     return this.params;
/*     */   }
/*     */   
/*     */   public void setParameters(List<? extends Expression> params) {
/* 156 */     if (params == null)
/* 157 */       throw new NullPointerException("Function parameters required"); 
/* 159 */     int argCount = getArgCount();
/* 160 */     int paramsSize = params.size();
/* 161 */     if (argCount > 0 && argCount != paramsSize)
/* 162 */       throw new IllegalArgumentException("Function " + this.name + " expected " + argCount + " arguments, got " + paramsSize); 
/* 165 */     this.params = new ArrayList<Expression>(params);
/*     */   }
/*     */   
/*     */   public Expression[] getArgs() {
/* 176 */     List<Expression> params = getParameters();
/* 177 */     return params.<Expression>toArray(new Expression[params.size()]);
/*     */   }
/*     */   
/*     */   public void setArgs(Expression[] args) {
/* 187 */     setParameters(Arrays.asList(args));
/*     */   }
/*     */   
/*     */   public int getArgCount() {
/* 196 */     if (this.functionName != null && this.functionName.getArguments() != null) {
/* 197 */       int count = 0;
/* 198 */       for (Parameter<?> argument : (Iterable<Parameter<?>>)this.functionName.getArguments()) {
/* 199 */         if (argument.getMinOccurs() != argument.getMaxOccurs())
/* 200 */           return -1; 
/* 202 */         count += argument.getMinOccurs();
/*     */       } 
/* 205 */       return count;
/*     */     } 
/* 207 */     return 0;
/*     */   }
/*     */   
/*     */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 215 */     return visitor.visit(this, extraData);
/*     */   }
/*     */   
/*     */   public Map<RenderingHints.Key, ?> getImplementationHints() {
/* 222 */     return Collections.emptyMap();
/*     */   }
/*     */   
/*     */   protected static FunctionName functionName(String name, String ret, String... args) {
/* 230 */     return FunctionImpl.functionName(name, ret, args);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 239 */     StringBuffer sb = new StringBuffer();
/* 240 */     sb.append(getName());
/* 241 */     sb.append("(");
/* 242 */     List<Expression> params = getParameters();
/* 243 */     if (params != null)
/* 245 */       for (Iterator<Expression> it = params.iterator(); it.hasNext(); ) {
/* 246 */         Expression exp = it.next();
/* 247 */         sb.append("[");
/* 248 */         sb.append(exp);
/* 249 */         sb.append("]");
/* 250 */         if (it.hasNext())
/* 251 */           sb.append(", "); 
/*     */       }  
/* 255 */     sb.append(")");
/* 256 */     return sb.toString();
/*     */   }
/*     */   
/*     */   protected Expression getExpression(int index) {
/* 267 */     Expression exp = getParameters().get(index);
/* 268 */     return exp;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 272 */     if (obj == null || !(obj instanceof Function))
/* 273 */       return false; 
/* 275 */     Function other = (Function)obj;
/* 276 */     if ((getName() == null && other.getName() != null) || (getName() != null && !getName().equalsIgnoreCase(other.getName())))
/* 278 */       return false; 
/* 280 */     if (getParameters() == null && other.getClass() != null)
/* 281 */       return false; 
/* 283 */     return (getParameters() != null && getParameters().equals(other.getParameters()));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FunctionExpressionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */