/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.expression.Literal;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class ClassifyFunction extends FunctionExpressionImpl {
/* 34 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("classify", FunctionNameImpl.parameter("value", Object.class), new Parameter[] { FunctionNameImpl.parameter("expression", Object.class), FunctionNameImpl.parameter("classifer", Classifier.class) });
/*    */   
/*    */   public ClassifyFunction() {
/* 40 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Classifier getClassifier(Object context) {
/* 44 */     Expression expr = getParameters().get(1);
/* 45 */     if (expr instanceof Literal) {
/* 47 */       Literal literal = (Literal)expr;
/* 48 */       return (Classifier)literal.getValue();
/*    */     } 
/* 50 */     return (Classifier)expr.evaluate(context, Classifier.class);
/*    */   }
/*    */   
/*    */   public Expression getExpression() {
/* 54 */     return getParameters().get(0);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/* 58 */     Classifier classifier = getClassifier(feature);
/* 59 */     Expression expression = getExpression();
/* 60 */     return new Integer(classifier.classify(expression, feature));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\ClassifyFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */