package com.google.common.cache;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@Beta
@GwtCompatible
public interface Weigher<K, V> {
  int weigh(K paramK, V paramV);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\cache\Weigher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */