package org.apache.xerces.dom;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Comment;

public class CommentImpl extends CharacterDataImpl implements CharacterData, Comment {
  static final long serialVersionUID = -2685736833408134044L;
  
  public CommentImpl(CoreDocumentImpl paramCoreDocumentImpl, String paramString) {
    super(paramCoreDocumentImpl, paramString);
  }
  
  public short getNodeType() {
    return 8;
  }
  
  public String getNodeName() {
    return "#comment";
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\CommentImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */