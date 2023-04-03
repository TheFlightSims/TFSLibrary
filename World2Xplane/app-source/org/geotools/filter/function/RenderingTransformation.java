package org.geotools.filter.function;

import org.geotools.data.Query;
import org.opengis.coverage.grid.GridGeometry;
import org.opengis.filter.expression.Function;

public interface RenderingTransformation extends Function {
  Query invertQuery(Query paramQuery, GridGeometry paramGridGeometry);
  
  GridGeometry invertGridGeometry(Query paramQuery, GridGeometry paramGridGeometry);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\RenderingTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */