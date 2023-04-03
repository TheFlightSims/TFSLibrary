package org.apache.xerces.dom.events;

import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

public class MouseEventImpl extends UIEventImpl implements MouseEvent {
  private int fScreenX;
  
  private int fScreenY;
  
  private int fClientX;
  
  private int fClientY;
  
  private boolean fCtrlKey;
  
  private boolean fAltKey;
  
  private boolean fShiftKey;
  
  private boolean fMetaKey;
  
  private short fButton;
  
  private EventTarget fRelatedTarget;
  
  public int getScreenX() {
    return this.fScreenX;
  }
  
  public int getScreenY() {
    return this.fScreenY;
  }
  
  public int getClientX() {
    return this.fClientX;
  }
  
  public int getClientY() {
    return this.fClientY;
  }
  
  public boolean getCtrlKey() {
    return this.fCtrlKey;
  }
  
  public boolean getAltKey() {
    return this.fAltKey;
  }
  
  public boolean getShiftKey() {
    return this.fShiftKey;
  }
  
  public boolean getMetaKey() {
    return this.fMetaKey;
  }
  
  public short getButton() {
    return this.fButton;
  }
  
  public EventTarget getRelatedTarget() {
    return this.fRelatedTarget;
  }
  
  public void initMouseEvent(String paramString, boolean paramBoolean1, boolean paramBoolean2, AbstractView paramAbstractView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, short paramShort, EventTarget paramEventTarget) {
    this.fScreenX = paramInt2;
    this.fScreenY = paramInt3;
    this.fClientX = paramInt4;
    this.fClientY = paramInt5;
    this.fCtrlKey = paramBoolean3;
    this.fAltKey = paramBoolean4;
    this.fShiftKey = paramBoolean5;
    this.fMetaKey = paramBoolean6;
    this.fButton = paramShort;
    this.fRelatedTarget = paramEventTarget;
    initUIEvent(paramString, paramBoolean1, paramBoolean2, paramAbstractView, paramInt1);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\events\MouseEventImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */