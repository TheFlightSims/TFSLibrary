package org.geotools.filter.function;

import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengis.filter.expression.Function;

public interface GeometryTransformation extends Function {
  ReferencedEnvelope invert(ReferencedEnvelope paramReferencedEnvelope);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\GeometryTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */