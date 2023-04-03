package org.postgresql.core.v3;

import java.sql.SQLException;
import org.postgresql.core.ParameterList;

interface V3ParameterList extends ParameterList {
  void checkAllParametersSet() throws SQLException;
  
  void convertFunctionOutParameters();
  
  SimpleParameterList[] getSubparams();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v3\V3ParameterList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */