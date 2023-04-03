package org.jfree.ui.tabbedui;

import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import javax.swing.JMenu;

public interface RootEditor {
  void setActive(boolean paramBoolean);
  
  boolean isActive();
  
  String getEditorName();
  
  JMenu[] getMenus();
  
  JComponent getToolbar();
  
  JComponent getMainPanel();
  
  boolean isEnabled();
  
  void addPropertyChangeListener(String paramString, PropertyChangeListener paramPropertyChangeListener);
  
  void removePropertyChangeListener(String paramString, PropertyChangeListener paramPropertyChangeListener);
  
  void addPropertyChangeListener(PropertyChangeListener paramPropertyChangeListener);
  
  void removePropertyChangeListener(PropertyChangeListener paramPropertyChangeListener);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\tabbedui\RootEditor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */