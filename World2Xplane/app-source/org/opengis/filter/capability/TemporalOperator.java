package org.opengis.filter.capability;

import java.util.Collection;
import org.opengis.feature.type.Name;

public interface TemporalOperator extends Operator {
  Collection<Name> getTemporalOperands();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\capability\TemporalOperator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */