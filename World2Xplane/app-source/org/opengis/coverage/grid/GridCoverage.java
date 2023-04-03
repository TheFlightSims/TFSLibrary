package org.opengis.coverage.grid;

import java.awt.image.RenderedImage;
import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.coverage.Coverage;

@UML(identifier = "CV_GridCoverage", specification = Specification.OGC_01004)
public interface GridCoverage extends Coverage {
  @UML(identifier = "dataEditable", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  boolean isDataEditable();
  
  @UML(identifier = "gridGeometry", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  GridGeometry getGridGeometry();
  
  @UML(identifier = "optimalDataBlockSizes", obligation = Obligation.OPTIONAL, specification = Specification.OGC_01004)
  int[] getOptimalDataBlockSizes();
  
  @UML(identifier = "numOverviews", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  int getNumOverviews();
  
  @UML(identifier = "getOverviewGridGeometry", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  GridGeometry getOverviewGridGeometry(int paramInt) throws IndexOutOfBoundsException;
  
  @UML(identifier = "getOverview", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  GridCoverage getOverview(int paramInt) throws IndexOutOfBoundsException;
  
  List<GridCoverage> getSources();
  
  RenderedImage getRenderedImage();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\grid\GridCoverage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */