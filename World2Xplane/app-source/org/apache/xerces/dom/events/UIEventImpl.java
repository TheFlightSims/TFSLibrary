package org.apache.xerces.dom.events;

import org.w3c.dom.events.UIEvent;
import org.w3c.dom.views.AbstractView;

public class UIEventImpl extends EventImpl implements UIEvent {
  private AbstractView fView;
  
  private int fDetail;
  
  public AbstractView getView() {
    return this.fView;
  }
  
  public int getDetail() {
    return this.fDetail;
  }
  
  public void initUIEvent(String paramString, boolean paramBoolean1, boolean paramBoolean2, AbstractView paramAbstractView, int paramInt) {
    this.fView = paramAbstractView;
    this.fDetail = paramInt;
    initEvent(paramString, paramBoolean1, paramBoolean2);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\events\UIEventImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */