package org.geotools.feature.visitor;

import java.util.List;
import org.opengis.feature.FeatureVisitor;
import org.opengis.filter.expression.Expression;

public interface FeatureAttributeVisitor extends FeatureVisitor {
  List<Expression> getExpressions();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\FeatureAttributeVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */