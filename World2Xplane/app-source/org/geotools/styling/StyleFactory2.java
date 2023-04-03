package org.geotools.styling;

import org.opengis.filter.expression.Expression;

public interface StyleFactory2 extends StyleFactory {
  TextSymbolizer2 createTextSymbolizer(Fill paramFill, Font[] paramArrayOfFont, Halo paramHalo, Expression paramExpression, LabelPlacement paramLabelPlacement, String paramString, Graphic paramGraphic);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\StyleFactory2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */