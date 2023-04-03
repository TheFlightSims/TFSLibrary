package org.geotools.styling;

import java.util.List;
import org.opengis.filter.expression.Expression;
import org.opengis.metadata.citation.OnLineResource;
import org.opengis.style.FeatureTypeStyle;

public interface FeatureTypeStyle extends FeatureTypeStyle {
  void setName(String paramString);
  
  String getTitle();
  
  void setTitle(String paramString);
  
  Description getDescription();
  
  String getAbstract();
  
  void setAbstract(String paramString);
  
  String getFeatureTypeName();
  
  void setFeatureTypeName(String paramString);
  
  String[] getSemanticTypeIdentifiers();
  
  void setSemanticTypeIdentifiers(String[] paramArrayOfString);
  
  Rule[] getRules();
  
  void setRules(Rule[] paramArrayOfRule);
  
  void addRule(Rule paramRule);
  
  List<Rule> rules();
  
  void setOnlineResource(OnLineResource paramOnLineResource);
  
  void accept(StyleVisitor paramStyleVisitor);
  
  Expression getTransformation();
  
  void setTransformation(Expression paramExpression);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\FeatureTypeStyle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */