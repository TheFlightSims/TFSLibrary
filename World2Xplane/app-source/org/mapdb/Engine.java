package org.mapdb;

public interface Engine {
  public static final long CATALOG_RECID = 1L;
  
  public static final long CLASS_INFO_RECID = 2L;
  
  public static final long CHECK_RECORD = 3L;
  
  public static final long LAST_RESERVED_RECID = 7L;
  
  long preallocate();
  
  void preallocate(long[] paramArrayOflong);
  
  <A> long put(A paramA, Serializer<A> paramSerializer);
  
  <A> A get(long paramLong, Serializer<A> paramSerializer);
  
  <A> void update(long paramLong, A paramA, Serializer<A> paramSerializer);
  
  <A> boolean compareAndSwap(long paramLong, A paramA1, A paramA2, Serializer<A> paramSerializer);
  
  <A> void delete(long paramLong, Serializer<A> paramSerializer);
  
  void close();
  
  boolean isClosed();
  
  void commit();
  
  void rollback() throws UnsupportedOperationException;
  
  boolean isReadOnly();
  
  boolean canRollback();
  
  boolean canSnapshot();
  
  Engine snapshot() throws UnsupportedOperationException;
  
  void clearCache();
  
  void compact();
  
  @Deprecated
  SerializerPojo getSerializerPojo();
  
  void closeListenerRegister(Runnable paramRunnable);
  
  void closeListenerUnregister(Runnable paramRunnable);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\Engine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */