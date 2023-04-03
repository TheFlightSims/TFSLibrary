package org.geotools.filter.expression;

public interface PropertyAccessor {
  boolean canHandle(Object paramObject, String paramString, Class<?> paramClass);
  
  <T> T get(Object paramObject, String paramString, Class<T> paramClass) throws IllegalArgumentException;
  
  <T> void set(Object paramObject, String paramString, T paramT, Class<T> paramClass) throws IllegalArgumentException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\expression\PropertyAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */