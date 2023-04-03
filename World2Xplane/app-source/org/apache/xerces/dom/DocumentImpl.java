package org.apache.xerces.dom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.xerces.dom.events.EventImpl;
import org.apache.xerces.dom.events.MouseEventImpl;
import org.apache.xerces.dom.events.MutationEventImpl;
import org.apache.xerces.dom.events.UIEventImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.ranges.DocumentRange;
import org.w3c.dom.ranges.Range;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.traversal.TreeWalker;

public class DocumentImpl extends CoreDocumentImpl implements DocumentTraversal, DocumentEvent, DocumentRange {
  static final long serialVersionUID = 515687835542616694L;
  
  protected Vector iterators;
  
  protected Vector ranges;
  
  protected Hashtable eventListeners;
  
  protected boolean mutationEvents = false;
  
  EnclosingAttr savedEnclosingAttr;
  
  public DocumentImpl() {}
  
  public DocumentImpl(boolean paramBoolean) {
    super(paramBoolean);
  }
  
  public DocumentImpl(DocumentType paramDocumentType) {
    super(paramDocumentType);
  }
  
  public DocumentImpl(DocumentType paramDocumentType, boolean paramBoolean) {
    super(paramDocumentType, paramBoolean);
  }
  
  public Node cloneNode(boolean paramBoolean) {
    DocumentImpl documentImpl = new DocumentImpl();
    callUserDataHandlers(this, documentImpl, (short)1);
    cloneNode(documentImpl, paramBoolean);
    documentImpl.mutationEvents = this.mutationEvents;
    return documentImpl;
  }
  
  public DOMImplementation getImplementation() {
    return DOMImplementationImpl.getDOMImplementation();
  }
  
  public NodeIterator createNodeIterator(Node paramNode, short paramShort, NodeFilter paramNodeFilter) {
    return createNodeIterator(paramNode, paramShort, paramNodeFilter, true);
  }
  
  public NodeIterator createNodeIterator(Node paramNode, int paramInt, NodeFilter paramNodeFilter, boolean paramBoolean) {
    if (paramNode == null) {
      String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
      throw new DOMException((short)9, str);
    } 
    NodeIteratorImpl nodeIteratorImpl = new NodeIteratorImpl(this, paramNode, paramInt, paramNodeFilter, paramBoolean);
    if (this.iterators == null)
      this.iterators = new Vector(); 
    this.iterators.addElement(nodeIteratorImpl);
    return nodeIteratorImpl;
  }
  
  public TreeWalker createTreeWalker(Node paramNode, short paramShort, NodeFilter paramNodeFilter) {
    return createTreeWalker(paramNode, paramShort, paramNodeFilter, true);
  }
  
  public TreeWalker createTreeWalker(Node paramNode, int paramInt, NodeFilter paramNodeFilter, boolean paramBoolean) {
    if (paramNode == null) {
      String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
      throw new DOMException((short)9, str);
    } 
    return new TreeWalkerImpl(paramNode, paramInt, paramNodeFilter, paramBoolean);
  }
  
  void removeNodeIterator(NodeIterator paramNodeIterator) {
    if (paramNodeIterator == null)
      return; 
    if (this.iterators == null)
      return; 
    this.iterators.removeElement(paramNodeIterator);
  }
  
  public Range createRange() {
    if (this.ranges == null)
      this.ranges = new Vector(); 
    RangeImpl rangeImpl = new RangeImpl(this);
    this.ranges.addElement(rangeImpl);
    return rangeImpl;
  }
  
  void removeRange(Range paramRange) {
    if (paramRange == null)
      return; 
    if (this.ranges == null)
      return; 
    this.ranges.removeElement(paramRange);
  }
  
  void replacedText(CharacterDataImpl paramCharacterDataImpl) {
    if (this.ranges != null)
      notifyRangesReplacedText(paramCharacterDataImpl); 
  }
  
  private void notifyRangesReplacedText(CharacterDataImpl paramCharacterDataImpl) {
    int i = this.ranges.size();
    for (int j = 0; j != i; j++)
      ((RangeImpl)this.ranges.elementAt(j)).receiveReplacedText(paramCharacterDataImpl); 
  }
  
  void deletedText(CharacterDataImpl paramCharacterDataImpl, int paramInt1, int paramInt2) {
    if (this.ranges != null)
      notifyRangesDeletedText(paramCharacterDataImpl, paramInt1, paramInt2); 
  }
  
  private void notifyRangesDeletedText(CharacterDataImpl paramCharacterDataImpl, int paramInt1, int paramInt2) {
    int i = this.ranges.size();
    for (int j = 0; j != i; j++)
      ((RangeImpl)this.ranges.elementAt(j)).receiveDeletedText(paramCharacterDataImpl, paramInt1, paramInt2); 
  }
  
  void insertedText(CharacterDataImpl paramCharacterDataImpl, int paramInt1, int paramInt2) {
    if (this.ranges != null)
      notifyRangesInsertedText(paramCharacterDataImpl, paramInt1, paramInt2); 
  }
  
  private void notifyRangesInsertedText(CharacterDataImpl paramCharacterDataImpl, int paramInt1, int paramInt2) {
    int i = this.ranges.size();
    for (int j = 0; j != i; j++)
      ((RangeImpl)this.ranges.elementAt(j)).receiveInsertedText(paramCharacterDataImpl, paramInt1, paramInt2); 
  }
  
  void splitData(Node paramNode1, Node paramNode2, int paramInt) {
    if (this.ranges != null)
      notifyRangesSplitData(paramNode1, paramNode2, paramInt); 
  }
  
  private void notifyRangesSplitData(Node paramNode1, Node paramNode2, int paramInt) {
    int i = this.ranges.size();
    for (int j = 0; j != i; j++)
      ((RangeImpl)this.ranges.elementAt(j)).receiveSplitData(paramNode1, paramNode2, paramInt); 
  }
  
  public Event createEvent(String paramString) throws DOMException {
    if (paramString.equalsIgnoreCase("Events") || "Event".equals(paramString))
      return (Event)new EventImpl(); 
    if (paramString.equalsIgnoreCase("MutationEvents") || "MutationEvent".equals(paramString))
      return (Event)new MutationEventImpl(); 
    if (paramString.equalsIgnoreCase("UIEvents") || "UIEvent".equals(paramString))
      return (Event)new UIEventImpl(); 
    if (paramString.equalsIgnoreCase("MouseEvents") || "MouseEvent".equals(paramString))
      return (Event)new MouseEventImpl(); 
    String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
    throw new DOMException((short)9, str);
  }
  
  void setMutationEvents(boolean paramBoolean) {
    this.mutationEvents = paramBoolean;
  }
  
  boolean getMutationEvents() {
    return this.mutationEvents;
  }
  
  protected void setEventListeners(NodeImpl paramNodeImpl, Vector paramVector) {
    if (this.eventListeners == null)
      this.eventListeners = new Hashtable(); 
    if (paramVector == null) {
      this.eventListeners.remove(paramNodeImpl);
      if (this.eventListeners.isEmpty())
        this.mutationEvents = false; 
    } else {
      this.eventListeners.put(paramNodeImpl, paramVector);
      this.mutationEvents = true;
    } 
  }
  
  protected Vector getEventListeners(NodeImpl paramNodeImpl) {
    return (this.eventListeners == null) ? null : (Vector)this.eventListeners.get(paramNodeImpl);
  }
  
  protected void addEventListener(NodeImpl paramNodeImpl, String paramString, EventListener paramEventListener, boolean paramBoolean) {
    if (paramString == null || paramString.equals("") || paramEventListener == null)
      return; 
    removeEventListener(paramNodeImpl, paramString, paramEventListener, paramBoolean);
    Vector vector = getEventListeners(paramNodeImpl);
    if (vector == null) {
      vector = new Vector();
      setEventListeners(paramNodeImpl, vector);
    } 
    vector.addElement(new LEntry(this, paramString, paramEventListener, paramBoolean));
    LCount lCount = LCount.lookup(paramString);
    if (paramBoolean) {
      lCount.captures++;
      lCount.total++;
    } else {
      lCount.bubbles++;
      lCount.total++;
    } 
  }
  
  protected void removeEventListener(NodeImpl paramNodeImpl, String paramString, EventListener paramEventListener, boolean paramBoolean) {
    if (paramString == null || paramString.equals("") || paramEventListener == null)
      return; 
    Vector vector = getEventListeners(paramNodeImpl);
    if (vector == null)
      return; 
    for (int i = vector.size() - 1; i >= 0; i--) {
      LEntry lEntry = vector.elementAt(i);
      if (lEntry.useCapture == paramBoolean && lEntry.listener == paramEventListener && lEntry.type.equals(paramString)) {
        vector.removeElementAt(i);
        if (vector.size() == 0)
          setEventListeners(paramNodeImpl, null); 
        LCount lCount = LCount.lookup(paramString);
        if (paramBoolean) {
          lCount.captures--;
          lCount.total--;
          break;
        } 
        lCount.bubbles--;
        lCount.total--;
        break;
      } 
    } 
  }
  
  protected void copyEventListeners(NodeImpl paramNodeImpl1, NodeImpl paramNodeImpl2) {
    Vector vector = getEventListeners(paramNodeImpl1);
    if (vector == null)
      return; 
    setEventListeners(paramNodeImpl2, (Vector)vector.clone());
  }
  
  protected boolean dispatchEvent(NodeImpl paramNodeImpl, Event paramEvent) {
    if (paramEvent == null)
      return false; 
    EventImpl eventImpl = (EventImpl)paramEvent;
    if (!eventImpl.initialized || eventImpl.type == null || eventImpl.type.equals("")) {
      String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "UNSPECIFIED_EVENT_TYPE_ERR", null);
      throw new EventException((short)0, str);
    } 
    LCount lCount = LCount.lookup(eventImpl.getType());
    if (lCount.total == 0)
      return eventImpl.preventDefault; 
    eventImpl.target = paramNodeImpl;
    eventImpl.stopPropagation = false;
    eventImpl.preventDefault = false;
    ArrayList arrayList = new ArrayList(10);
    NodeImpl nodeImpl = paramNodeImpl;
    for (Node node = nodeImpl.getParentNode(); node != null; node = node.getParentNode()) {
      arrayList.add(node);
      Node node1 = node;
    } 
    if (lCount.captures > 0) {
      eventImpl.eventPhase = 1;
      for (int i = arrayList.size() - 1; i >= 0 && !eventImpl.stopPropagation; i--) {
        NodeImpl nodeImpl1 = (NodeImpl)arrayList.get(i);
        eventImpl.currentTarget = nodeImpl1;
        Vector vector = getEventListeners(nodeImpl1);
        if (vector != null) {
          Vector vector1 = (Vector)vector.clone();
          int j = vector1.size();
          for (byte b = 0; b < j; b++) {
            LEntry lEntry = vector1.elementAt(b);
            if (lEntry.useCapture && lEntry.type.equals(eventImpl.type) && vector.contains(lEntry))
              try {
                lEntry.listener.handleEvent((Event)eventImpl);
              } catch (Exception exception) {} 
          } 
        } 
      } 
    } 
    if (lCount.bubbles > 0) {
      eventImpl.eventPhase = 2;
      eventImpl.currentTarget = paramNodeImpl;
      Vector vector = getEventListeners(paramNodeImpl);
      if (!eventImpl.stopPropagation && vector != null) {
        Vector vector1 = (Vector)vector.clone();
        int i = vector1.size();
        for (byte b = 0; b < i; b++) {
          LEntry lEntry = vector1.elementAt(b);
          if (!lEntry.useCapture && lEntry.type.equals(eventImpl.type) && vector.contains(lEntry))
            try {
              lEntry.listener.handleEvent((Event)eventImpl);
            } catch (Exception exception) {} 
        } 
      } 
      if (eventImpl.bubbles) {
        eventImpl.eventPhase = 3;
        int i = arrayList.size();
        for (byte b = 0; b < i && !eventImpl.stopPropagation; b++) {
          NodeImpl nodeImpl1 = (NodeImpl)arrayList.get(b);
          eventImpl.currentTarget = nodeImpl1;
          vector = getEventListeners(nodeImpl1);
          if (vector != null) {
            Vector vector1 = (Vector)vector.clone();
            int j = vector1.size();
            for (byte b1 = 0; b1 < j; b1++) {
              LEntry lEntry = vector1.elementAt(b1);
              if (!lEntry.useCapture && lEntry.type.equals(eventImpl.type) && vector.contains(lEntry))
                try {
                  lEntry.listener.handleEvent((Event)eventImpl);
                } catch (Exception exception) {} 
            } 
          } 
        } 
      } 
    } 
    if (lCount.defaults <= 0 || !eventImpl.cancelable || !eventImpl.preventDefault);
    return eventImpl.preventDefault;
  }
  
  protected void dispatchEventToSubtree(Node paramNode, Event paramEvent) {
    ((NodeImpl)paramNode).dispatchEvent(paramEvent);
    if (paramNode.getNodeType() == 1) {
      NamedNodeMap namedNodeMap = paramNode.getAttributes();
      for (int i = namedNodeMap.getLength() - 1; i >= 0; i--)
        dispatchingEventToSubtree(namedNodeMap.item(i), paramEvent); 
    } 
    dispatchingEventToSubtree(paramNode.getFirstChild(), paramEvent);
  }
  
  protected void dispatchingEventToSubtree(Node paramNode, Event paramEvent) {
    if (paramNode == null)
      return; 
    ((NodeImpl)paramNode).dispatchEvent(paramEvent);
    if (paramNode.getNodeType() == 1) {
      NamedNodeMap namedNodeMap = paramNode.getAttributes();
      for (int i = namedNodeMap.getLength() - 1; i >= 0; i--)
        dispatchingEventToSubtree(namedNodeMap.item(i), paramEvent); 
    } 
    dispatchingEventToSubtree(paramNode.getFirstChild(), paramEvent);
    dispatchingEventToSubtree(paramNode.getNextSibling(), paramEvent);
  }
  
  protected void dispatchAggregateEvents(NodeImpl paramNodeImpl, EnclosingAttr paramEnclosingAttr) {
    if (paramEnclosingAttr != null) {
      dispatchAggregateEvents(paramNodeImpl, paramEnclosingAttr.node, paramEnclosingAttr.oldvalue, (short)1);
    } else {
      dispatchAggregateEvents(paramNodeImpl, null, null, (short)0);
    } 
  }
  
  protected void dispatchAggregateEvents(NodeImpl paramNodeImpl, AttrImpl paramAttrImpl, String paramString, short paramShort) {
    NodeImpl nodeImpl = null;
    if (paramAttrImpl != null) {
      LCount lCount1 = LCount.lookup("DOMAttrModified");
      nodeImpl = (NodeImpl)paramAttrImpl.getOwnerElement();
      if (lCount1.total > 0 && nodeImpl != null) {
        MutationEventImpl mutationEventImpl = new MutationEventImpl();
        mutationEventImpl.initMutationEvent("DOMAttrModified", true, false, paramAttrImpl, paramString, paramAttrImpl.getNodeValue(), paramAttrImpl.getNodeName(), paramShort);
        nodeImpl.dispatchEvent((Event)mutationEventImpl);
      } 
    } 
    LCount lCount = LCount.lookup("DOMSubtreeModified");
    if (lCount.total > 0) {
      MutationEventImpl mutationEventImpl = new MutationEventImpl();
      mutationEventImpl.initMutationEvent("DOMSubtreeModified", true, false, null, null, null, null, (short)0);
      if (paramAttrImpl != null) {
        dispatchEvent(paramAttrImpl, (Event)mutationEventImpl);
        if (nodeImpl != null)
          dispatchEvent(nodeImpl, (Event)mutationEventImpl); 
      } else {
        dispatchEvent(paramNodeImpl, (Event)mutationEventImpl);
      } 
    } 
  }
  
  protected void saveEnclosingAttr(NodeImpl paramNodeImpl) {
    this.savedEnclosingAttr = null;
    LCount lCount = LCount.lookup("DOMAttrModified");
    if (lCount.total > 0) {
      NodeImpl nodeImpl = paramNodeImpl;
      while (true) {
        if (nodeImpl == null)
          return; 
        short s = nodeImpl.getNodeType();
        if (s == 2) {
          EnclosingAttr enclosingAttr = new EnclosingAttr(this);
          enclosingAttr.node = (AttrImpl)nodeImpl;
          enclosingAttr.oldvalue = enclosingAttr.node.getNodeValue();
          this.savedEnclosingAttr = enclosingAttr;
          return;
        } 
        if (s == 5) {
          nodeImpl = nodeImpl.parentNode();
          continue;
        } 
        if (s == 3) {
          nodeImpl = nodeImpl.parentNode();
          continue;
        } 
        break;
      } 
      return;
    } 
  }
  
  void modifyingCharacterData(NodeImpl paramNodeImpl, boolean paramBoolean) {
    if (this.mutationEvents && !paramBoolean)
      saveEnclosingAttr(paramNodeImpl); 
  }
  
  void modifiedCharacterData(NodeImpl paramNodeImpl, String paramString1, String paramString2, boolean paramBoolean) {
    if (this.mutationEvents)
      mutationEventsModifiedCharacterData(paramNodeImpl, paramString1, paramString2, paramBoolean); 
  }
  
  private void mutationEventsModifiedCharacterData(NodeImpl paramNodeImpl, String paramString1, String paramString2, boolean paramBoolean) {
    if (!paramBoolean) {
      LCount lCount = LCount.lookup("DOMCharacterDataModified");
      if (lCount.total > 0) {
        MutationEventImpl mutationEventImpl = new MutationEventImpl();
        mutationEventImpl.initMutationEvent("DOMCharacterDataModified", true, false, null, paramString1, paramString2, null, (short)0);
        dispatchEvent(paramNodeImpl, (Event)mutationEventImpl);
      } 
      dispatchAggregateEvents(paramNodeImpl, this.savedEnclosingAttr);
    } 
  }
  
  void replacedCharacterData(NodeImpl paramNodeImpl, String paramString1, String paramString2) {
    modifiedCharacterData(paramNodeImpl, paramString1, paramString2, false);
  }
  
  void insertingNode(NodeImpl paramNodeImpl, boolean paramBoolean) {
    if (this.mutationEvents && !paramBoolean)
      saveEnclosingAttr(paramNodeImpl); 
  }
  
  void insertedNode(NodeImpl paramNodeImpl1, NodeImpl paramNodeImpl2, boolean paramBoolean) {
    if (this.mutationEvents)
      mutationEventsInsertedNode(paramNodeImpl1, paramNodeImpl2, paramBoolean); 
    if (this.ranges != null)
      notifyRangesInsertedNode(paramNodeImpl2); 
  }
  
  private void mutationEventsInsertedNode(NodeImpl paramNodeImpl1, NodeImpl paramNodeImpl2, boolean paramBoolean) {
    LCount lCount = LCount.lookup("DOMNodeInserted");
    if (lCount.total > 0) {
      MutationEventImpl mutationEventImpl = new MutationEventImpl();
      mutationEventImpl.initMutationEvent("DOMNodeInserted", true, false, paramNodeImpl1, null, null, null, (short)0);
      dispatchEvent(paramNodeImpl2, (Event)mutationEventImpl);
    } 
    lCount = LCount.lookup("DOMNodeInsertedIntoDocument");
    if (lCount.total > 0) {
      NodeImpl nodeImpl = paramNodeImpl1;
      if (this.savedEnclosingAttr != null)
        nodeImpl = (NodeImpl)this.savedEnclosingAttr.node.getOwnerElement(); 
      if (nodeImpl != null) {
        for (NodeImpl nodeImpl1 = nodeImpl; nodeImpl1 != null; nodeImpl1 = nodeImpl1.parentNode()) {
          nodeImpl = nodeImpl1;
          if (nodeImpl1.getNodeType() == 2) {
            nodeImpl1 = (NodeImpl)((AttrImpl)nodeImpl1).getOwnerElement();
            continue;
          } 
        } 
        if (nodeImpl.getNodeType() == 9) {
          MutationEventImpl mutationEventImpl = new MutationEventImpl();
          mutationEventImpl.initMutationEvent("DOMNodeInsertedIntoDocument", false, false, null, null, null, null, (short)0);
          dispatchEventToSubtree(paramNodeImpl2, (Event)mutationEventImpl);
        } 
      } 
    } 
    if (!paramBoolean)
      dispatchAggregateEvents(paramNodeImpl1, this.savedEnclosingAttr); 
  }
  
  private void notifyRangesInsertedNode(NodeImpl paramNodeImpl) {
    int i = this.ranges.size();
    for (int j = 0; j != i; j++)
      ((RangeImpl)this.ranges.elementAt(j)).insertedNodeFromDOM(paramNodeImpl); 
  }
  
  void removingNode(NodeImpl paramNodeImpl1, NodeImpl paramNodeImpl2, boolean paramBoolean) {
    if (this.iterators != null)
      notifyIteratorsRemovingNode(paramNodeImpl2); 
    if (this.ranges != null)
      notifyRangesRemovingNode(paramNodeImpl2); 
    if (this.mutationEvents)
      mutationEventsRemovingNode(paramNodeImpl1, paramNodeImpl2, paramBoolean); 
  }
  
  private void notifyIteratorsRemovingNode(NodeImpl paramNodeImpl) {
    int i = this.iterators.size();
    for (int j = 0; j != i; j++)
      ((NodeIteratorImpl)this.iterators.elementAt(j)).removeNode(paramNodeImpl); 
  }
  
  private void notifyRangesRemovingNode(NodeImpl paramNodeImpl) {
    int i = this.ranges.size();
    for (int j = 0; j != i; j++)
      ((RangeImpl)this.ranges.elementAt(j)).removeNode(paramNodeImpl); 
  }
  
  private void mutationEventsRemovingNode(NodeImpl paramNodeImpl1, NodeImpl paramNodeImpl2, boolean paramBoolean) {
    if (!paramBoolean)
      saveEnclosingAttr(paramNodeImpl1); 
    LCount lCount = LCount.lookup("DOMNodeRemoved");
    if (lCount.total > 0) {
      MutationEventImpl mutationEventImpl = new MutationEventImpl();
      mutationEventImpl.initMutationEvent("DOMNodeRemoved", true, false, paramNodeImpl1, null, null, null, (short)0);
      dispatchEvent(paramNodeImpl2, (Event)mutationEventImpl);
    } 
    lCount = LCount.lookup("DOMNodeRemovedFromDocument");
    if (lCount.total > 0) {
      NodeImpl nodeImpl = this;
      if (this.savedEnclosingAttr != null)
        nodeImpl = (NodeImpl)this.savedEnclosingAttr.node.getOwnerElement(); 
      if (nodeImpl != null) {
        for (NodeImpl nodeImpl1 = nodeImpl.parentNode(); nodeImpl1 != null; nodeImpl1 = nodeImpl1.parentNode())
          nodeImpl = nodeImpl1; 
        if (nodeImpl.getNodeType() == 9) {
          MutationEventImpl mutationEventImpl = new MutationEventImpl();
          mutationEventImpl.initMutationEvent("DOMNodeRemovedFromDocument", false, false, null, null, null, null, (short)0);
          dispatchEventToSubtree(paramNodeImpl2, (Event)mutationEventImpl);
        } 
      } 
    } 
  }
  
  void removedNode(NodeImpl paramNodeImpl, boolean paramBoolean) {
    if (this.mutationEvents && !paramBoolean)
      dispatchAggregateEvents(paramNodeImpl, this.savedEnclosingAttr); 
  }
  
  void replacingNode(NodeImpl paramNodeImpl) {
    if (this.mutationEvents)
      saveEnclosingAttr(paramNodeImpl); 
  }
  
  void replacingData(NodeImpl paramNodeImpl) {
    if (this.mutationEvents)
      saveEnclosingAttr(paramNodeImpl); 
  }
  
  void replacedNode(NodeImpl paramNodeImpl) {
    if (this.mutationEvents)
      dispatchAggregateEvents(paramNodeImpl, this.savedEnclosingAttr); 
  }
  
  void modifiedAttrValue(AttrImpl paramAttrImpl, String paramString) {
    if (this.mutationEvents)
      dispatchAggregateEvents(paramAttrImpl, paramAttrImpl, paramString, (short)1); 
  }
  
  void setAttrNode(AttrImpl paramAttrImpl1, AttrImpl paramAttrImpl2) {
    if (this.mutationEvents)
      if (paramAttrImpl2 == null) {
        dispatchAggregateEvents(paramAttrImpl1.ownerNode, paramAttrImpl1, null, (short)2);
      } else {
        dispatchAggregateEvents(paramAttrImpl1.ownerNode, paramAttrImpl1, paramAttrImpl2.getNodeValue(), (short)1);
      }  
  }
  
  void removedAttrNode(AttrImpl paramAttrImpl, NodeImpl paramNodeImpl, String paramString) {
    if (this.mutationEvents)
      mutationEventsRemovedAttrNode(paramAttrImpl, paramNodeImpl, paramString); 
  }
  
  private void mutationEventsRemovedAttrNode(AttrImpl paramAttrImpl, NodeImpl paramNodeImpl, String paramString) {
    LCount lCount = LCount.lookup("DOMAttrModified");
    if (lCount.total > 0) {
      MutationEventImpl mutationEventImpl = new MutationEventImpl();
      mutationEventImpl.initMutationEvent("DOMAttrModified", true, false, paramAttrImpl, paramAttrImpl.getNodeValue(), null, paramString, (short)3);
      dispatchEvent(paramNodeImpl, (Event)mutationEventImpl);
    } 
    dispatchAggregateEvents(paramNodeImpl, null, null, (short)0);
  }
  
  void renamedAttrNode(Attr paramAttr1, Attr paramAttr2) {}
  
  void renamedElement(Element paramElement1, Element paramElement2) {}
  
  class EnclosingAttr implements Serializable {
    private static final long serialVersionUID = 5208387723391647216L;
    
    AttrImpl node;
    
    String oldvalue;
    
    private final DocumentImpl this$0;
    
    EnclosingAttr(DocumentImpl this$0) {
      this.this$0 = this$0;
    }
  }
  
  class LEntry implements Serializable {
    private static final long serialVersionUID = -8426757059492421631L;
    
    String type;
    
    EventListener listener;
    
    boolean useCapture;
    
    private final DocumentImpl this$0;
    
    LEntry(DocumentImpl this$0, String param1String, EventListener param1EventListener, boolean param1Boolean) {
      this.this$0 = this$0;
      this.type = param1String;
      this.listener = param1EventListener;
      this.useCapture = param1Boolean;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DocumentImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */