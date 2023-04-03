package org.geotools.geometry.jts;

import com.vividsolutions.jts.geom.CoordinateSequence;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

public interface CoordinateSequenceTransformer {
  CoordinateSequence transform(CoordinateSequence paramCoordinateSequence, MathTransform paramMathTransform) throws TransformException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\CoordinateSequenceTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */