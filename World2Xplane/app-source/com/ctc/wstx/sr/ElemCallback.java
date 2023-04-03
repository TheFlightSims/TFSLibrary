package com.ctc.wstx.sr;

import com.ctc.wstx.util.BaseNsContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;

public abstract class ElemCallback {
  public abstract Object withStartElement(Location paramLocation, QName paramQName, BaseNsContext paramBaseNsContext, ElemAttrs paramElemAttrs, boolean paramBoolean);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sr\ElemCallback.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */