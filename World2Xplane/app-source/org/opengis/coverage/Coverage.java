package org.opengis.coverage;

import java.awt.image.renderable.RenderableImage;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.Record;
import org.opengis.util.RecordType;

@UML(identifier = "CV_Coverage", specification = Specification.ISO_19123)
public interface Coverage {
  @UML(identifier = "CRS", obligation = Obligation.MANDATORY, specification = Specification.ISO_19123)
  CoordinateReferenceSystem getCoordinateReferenceSystem();
  
  @UML(identifier = "envelope", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  Envelope getEnvelope();
  
  @UML(identifier = "rangeType", obligation = Obligation.MANDATORY, specification = Specification.ISO_19123)
  RecordType getRangeType();
  
  @UML(identifier = "evaluate", obligation = Obligation.MANDATORY, specification = Specification.ISO_19123)
  Set<Record> evaluate(DirectPosition paramDirectPosition, Collection<String> paramCollection) throws PointOutsideCoverageException, CannotEvaluateException;
  
  @UML(identifier = "evaluate", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  Object evaluate(DirectPosition paramDirectPosition) throws PointOutsideCoverageException, CannotEvaluateException;
  
  @UML(identifier = "evaluateAsBoolean", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  boolean[] evaluate(DirectPosition paramDirectPosition, boolean[] paramArrayOfboolean) throws PointOutsideCoverageException, CannotEvaluateException, ArrayIndexOutOfBoundsException;
  
  @UML(identifier = "evaluateAsByte", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  byte[] evaluate(DirectPosition paramDirectPosition, byte[] paramArrayOfbyte) throws PointOutsideCoverageException, CannotEvaluateException, ArrayIndexOutOfBoundsException;
  
  @UML(identifier = "evaluateAsInteger", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  int[] evaluate(DirectPosition paramDirectPosition, int[] paramArrayOfint) throws PointOutsideCoverageException, CannotEvaluateException, ArrayIndexOutOfBoundsException;
  
  float[] evaluate(DirectPosition paramDirectPosition, float[] paramArrayOffloat) throws PointOutsideCoverageException, CannotEvaluateException, ArrayIndexOutOfBoundsException;
  
  @UML(identifier = "evaluateAsDouble", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  double[] evaluate(DirectPosition paramDirectPosition, double[] paramArrayOfdouble) throws PointOutsideCoverageException, CannotEvaluateException, ArrayIndexOutOfBoundsException;
  
  @UML(identifier = "numSampleDimensions", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  int getNumSampleDimensions();
  
  @UML(identifier = "getSampleDimension", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  SampleDimension getSampleDimension(int paramInt) throws IndexOutOfBoundsException;
  
  @UML(identifier = "getSource, numSource", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  List<? extends Coverage> getSources();
  
  RenderableImage getRenderableImage(int paramInt1, int paramInt2) throws UnsupportedOperationException, IndexOutOfBoundsException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\Coverage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */