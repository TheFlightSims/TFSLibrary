package org.apache.wml.dom;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import org.apache.wml.WMLDocument;
import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

public class WMLDocumentImpl extends DocumentImpl implements WMLDocument {
  private static final long serialVersionUID = -6582904849512384104L;
  
  private static Hashtable _elementTypesWML;
  
  private static final Class[] _elemClassSigWML = new Class[] { WMLDocumentImpl.class, String.class };
  
  public Element createElement(String paramString) throws DOMException {
    Class clazz = (Class)_elementTypesWML.get(paramString);
    if (clazz != null)
      try {
        Constructor constructor = clazz.getConstructor(_elemClassSigWML);
        return constructor.newInstance(new Object[] { this, paramString });
      } catch (Exception exception) {
        Throwable throwable;
        if (exception instanceof InvocationTargetException) {
          throwable = ((InvocationTargetException)exception).getTargetException();
        } else {
          throwable = exception;
        } 
        System.out.println("Exception " + throwable.getClass().getName());
        System.out.println(throwable.getMessage());
        throw new IllegalStateException("Tag '" + paramString + "' associated with an Element class that failed to construct.");
      }  
    return (Element)new WMLElementImpl(this, paramString);
  }
  
  public WMLDocumentImpl(DocumentType paramDocumentType) {
    super(paramDocumentType, false);
  }
  
  static {
    _elementTypesWML = new Hashtable();
    _elementTypesWML.put("b", WMLBElementImpl.class);
    _elementTypesWML.put("noop", WMLNoopElementImpl.class);
    _elementTypesWML.put("a", WMLAElementImpl.class);
    _elementTypesWML.put("setvar", WMLSetvarElementImpl.class);
    _elementTypesWML.put("access", WMLAccessElementImpl.class);
    _elementTypesWML.put("strong", WMLStrongElementImpl.class);
    _elementTypesWML.put("postfield", WMLPostfieldElementImpl.class);
    _elementTypesWML.put("do", WMLDoElementImpl.class);
    _elementTypesWML.put("wml", WMLWmlElementImpl.class);
    _elementTypesWML.put("tr", WMLTrElementImpl.class);
    _elementTypesWML.put("go", WMLGoElementImpl.class);
    _elementTypesWML.put("big", WMLBigElementImpl.class);
    _elementTypesWML.put("anchor", WMLAnchorElementImpl.class);
    _elementTypesWML.put("timer", WMLTimerElementImpl.class);
    _elementTypesWML.put("small", WMLSmallElementImpl.class);
    _elementTypesWML.put("optgroup", WMLOptgroupElementImpl.class);
    _elementTypesWML.put("head", WMLHeadElementImpl.class);
    _elementTypesWML.put("td", WMLTdElementImpl.class);
    _elementTypesWML.put("fieldset", WMLFieldsetElementImpl.class);
    _elementTypesWML.put("img", WMLImgElementImpl.class);
    _elementTypesWML.put("refresh", WMLRefreshElementImpl.class);
    _elementTypesWML.put("onevent", WMLOneventElementImpl.class);
    _elementTypesWML.put("input", WMLInputElementImpl.class);
    _elementTypesWML.put("prev", WMLPrevElementImpl.class);
    _elementTypesWML.put("table", WMLTableElementImpl.class);
    _elementTypesWML.put("meta", WMLMetaElementImpl.class);
    _elementTypesWML.put("template", WMLTemplateElementImpl.class);
    _elementTypesWML.put("br", WMLBrElementImpl.class);
    _elementTypesWML.put("option", WMLOptionElementImpl.class);
    _elementTypesWML.put("u", WMLUElementImpl.class);
    _elementTypesWML.put("p", WMLPElementImpl.class);
    _elementTypesWML.put("select", WMLSelectElementImpl.class);
    _elementTypesWML.put("em", WMLEmElementImpl.class);
    _elementTypesWML.put("i", WMLIElementImpl.class);
    _elementTypesWML.put("card", WMLCardElementImpl.class);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\wml\dom\WMLDocumentImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */