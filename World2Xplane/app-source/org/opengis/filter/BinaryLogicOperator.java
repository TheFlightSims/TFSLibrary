package org.opengis.filter;

import java.util.List;
import org.opengis.annotation.XmlElement;

@XmlElement("BinaryLogicOpType")
public interface BinaryLogicOperator extends Filter {
  List<Filter> getChildren();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\BinaryLogicOperator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */