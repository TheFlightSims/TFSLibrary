/*    */ package com.ctc.wstx.evt;
/*    */ 
/*    */ import javax.xml.stream.Location;
/*    */ import javax.xml.stream.events.EntityDeclaration;
/*    */ import javax.xml.stream.events.EntityReference;
/*    */ import org.codehaus.stax2.ri.evt.EntityReferenceEventImpl;
/*    */ 
/*    */ public class WEntityReference extends EntityReferenceEventImpl implements EntityReference {
/*    */   final String mName;
/*    */   
/*    */   public WEntityReference(Location loc, EntityDeclaration decl) {
/* 22 */     super(loc, decl);
/* 23 */     this.mName = null;
/*    */   }
/*    */   
/*    */   public WEntityReference(Location loc, String name) {
/* 33 */     super(loc, (EntityDeclaration)null);
/* 34 */     this.mName = name;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 39 */     if (this.mName != null)
/* 40 */       return this.mName; 
/* 42 */     return super.getName();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\evt\WEntityReference.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */