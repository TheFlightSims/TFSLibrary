package org.geotools.filter;

import com.vividsolutions.jts.geom.Envelope;

public interface BBoxExpression extends LiteralExpression {
  void setBounds(Envelope paramEnvelope) throws IllegalFilterException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\BBoxExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */