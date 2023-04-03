/*    */ package org.codehaus.stax2.ri;
/*    */ 
/*    */ import javax.xml.stream.Location;
/*    */ import org.codehaus.stax2.XMLStreamLocation2;
/*    */ 
/*    */ public class Stax2LocationAdapter implements XMLStreamLocation2 {
/*    */   protected final Location mWrappedLocation;
/*    */   
/*    */   protected final Location mParentLocation;
/*    */   
/*    */   public Stax2LocationAdapter(Location loc) {
/* 21 */     this(loc, null);
/*    */   }
/*    */   
/*    */   public Stax2LocationAdapter(Location loc, Location parent) {
/* 26 */     this.mWrappedLocation = loc;
/* 27 */     this.mParentLocation = parent;
/*    */   }
/*    */   
/*    */   public int getCharacterOffset() {
/* 34 */     return this.mWrappedLocation.getCharacterOffset();
/*    */   }
/*    */   
/*    */   public int getColumnNumber() {
/* 39 */     return this.mWrappedLocation.getColumnNumber();
/*    */   }
/*    */   
/*    */   public int getLineNumber() {
/* 44 */     return this.mWrappedLocation.getLineNumber();
/*    */   }
/*    */   
/*    */   public String getPublicId() {
/* 49 */     return this.mWrappedLocation.getPublicId();
/*    */   }
/*    */   
/*    */   public String getSystemId() {
/* 54 */     return this.mWrappedLocation.getSystemId();
/*    */   }
/*    */   
/*    */   public XMLStreamLocation2 getContext() {
/* 61 */     if (this.mParentLocation == null)
/* 62 */       return null; 
/* 64 */     if (this.mParentLocation instanceof XMLStreamLocation2)
/* 65 */       return (XMLStreamLocation2)this.mParentLocation; 
/* 67 */     return new Stax2LocationAdapter(this.mParentLocation);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\Stax2LocationAdapter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */