package org.postgresql.core.v3;

import org.postgresql.core.Query;

interface V3Query extends Query {
  SimpleQuery[] getSubqueries();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v3\V3Query.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */