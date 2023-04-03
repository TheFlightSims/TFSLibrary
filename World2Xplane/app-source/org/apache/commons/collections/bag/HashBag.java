/*    */ package org.apache.commons.collections.bag;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import org.apache.commons.collections.Bag;
/*    */ 
/*    */ public class HashBag extends AbstractMapBag implements Bag, Serializable {
/*    */   private static final long serialVersionUID = -6561115435802554013L;
/*    */   
/*    */   public HashBag() {
/* 54 */     super(new HashMap());
/*    */   }
/*    */   
/*    */   public HashBag(Collection coll) {
/* 63 */     this();
/* 64 */     addAll(coll);
/*    */   }
/*    */   
/*    */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 72 */     out.defaultWriteObject();
/* 73 */     doWriteObject(out);
/*    */   }
/*    */   
/*    */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 80 */     in.defaultReadObject();
/* 81 */     doReadObject(new HashMap(), in);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bag\HashBag.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */