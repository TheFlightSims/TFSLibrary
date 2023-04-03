/*    */ package com.sun.media.jai.rmi;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.util.Hashtable;
/*    */ 
/*    */ public class RenderingKeyState extends SerializableStateImpl {
/*    */   private transient RenderingHintsState.HintElement predefinedKey;
/*    */   
/*    */   public static Class[] getSupportedClasses() {
/* 36 */     return new Class[] { RenderingHints.Key.class };
/*    */   }
/*    */   
/*    */   public static boolean permitsSubclasses() {
/* 41 */     return true;
/*    */   }
/*    */   
/*    */   public RenderingKeyState(Class c, Object o, RenderingHints h) {
/* 55 */     super(c, o, h);
/* 57 */     Hashtable predefinedObjects = RenderingHintsState.getHintTable();
/* 59 */     this.predefinedKey = (RenderingHintsState.HintElement)predefinedObjects.get(o);
/* 62 */     if (this.predefinedKey == null)
/* 63 */       throw new RuntimeException(JaiI18N.getString("RenderingKeyState0")); 
/*    */   }
/*    */   
/*    */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 67 */     out.writeObject(this.predefinedKey);
/*    */   }
/*    */   
/*    */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 72 */     this.predefinedKey = (RenderingHintsState.HintElement)in.readObject();
/* 73 */     this.theObject = this.predefinedKey.getObject();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\RenderingKeyState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */