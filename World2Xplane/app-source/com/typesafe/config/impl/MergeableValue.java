package com.typesafe.config.impl;

import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigValue;

interface MergeableValue extends ConfigMergeable {
  ConfigValue toFallbackValue();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\MergeableValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */