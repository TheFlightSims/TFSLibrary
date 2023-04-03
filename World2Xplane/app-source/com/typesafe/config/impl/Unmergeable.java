package com.typesafe.config.impl;

import java.util.Collection;

interface Unmergeable {
  Collection<? extends AbstractConfigValue> unmergedValues();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\Unmergeable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */