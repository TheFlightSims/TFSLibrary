/*    */ package org.apache.commons.collections.list;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public abstract class AbstractSerializableListDecorator extends AbstractListDecorator implements Serializable {
/*    */   private static final long serialVersionUID = 2684959196747496299L;
/*    */   
/*    */   protected AbstractSerializableListDecorator(List list) {
/* 43 */     super(list);
/*    */   }
/*    */   
/*    */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 54 */     out.defaultWriteObject();
/* 55 */     out.writeObject(this.collection);
/*    */   }
/*    */   
/*    */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 66 */     in.defaultReadObject();
/* 67 */     this.collection = (Collection)in.readObject();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\list\AbstractSerializableListDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */