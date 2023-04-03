package org.apache.commons.collections.functors;

import org.apache.commons.collections.Predicate;

public interface PredicateDecorator extends Predicate {
  Predicate[] getPredicates();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\PredicateDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */