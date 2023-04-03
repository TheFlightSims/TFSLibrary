package org.openstreetmap.osmosis.core.store;

public interface ObjectSerializationFactory {
  ObjectReader createObjectReader(StoreReader paramStoreReader, StoreClassRegister paramStoreClassRegister);
  
  ObjectWriter createObjectWriter(StoreWriter paramStoreWriter, StoreClassRegister paramStoreClassRegister);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\ObjectSerializationFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */