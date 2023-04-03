package org.openstreetmap.osmosis.core.store;

public interface StoreClassRegister {
  void storeIdentifierForClass(StoreWriter paramStoreWriter, Class<?> paramClass);
  
  Class<?> getClassFromIdentifier(StoreReader paramStoreReader);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\StoreClassRegister.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */