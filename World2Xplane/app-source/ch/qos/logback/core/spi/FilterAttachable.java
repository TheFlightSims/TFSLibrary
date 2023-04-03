package ch.qos.logback.core.spi;

import ch.qos.logback.core.filter.Filter;
import java.util.List;

public interface FilterAttachable<E> {
  void addFilter(Filter<E> paramFilter);
  
  void clearAllFilters();
  
  List<Filter<E>> getCopyOfAttachedFiltersList();
  
  FilterReply getFilterChainDecision(E paramE);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\spi\FilterAttachable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */