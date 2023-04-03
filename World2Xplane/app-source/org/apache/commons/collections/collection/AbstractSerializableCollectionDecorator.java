/*    */ package org.apache.commons.collections.collection;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
/*    */ 
/*    */ public abstract class AbstractSerializableCollectionDecorator extends AbstractCollectionDecorator implements Serializable {
/*    */   private static final long serialVersionUID = 6249888059822088500L;
/*    */   
/*    */   protected AbstractSerializableCollectionDecorator(Collection coll) {
/* 42 */     super(coll);
/*    */   }
/*    */   
/*    */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 53 */     out.defaultWriteObject();
/* 54 */     out.writeObject(this.collection);
/*    */   }
/*    */   
/*    */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 65 */     in.defaultReadObject();
/* 66 */     this.collection = (Collection)in.readObject();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\collection\AbstractSerializableCollectionDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */