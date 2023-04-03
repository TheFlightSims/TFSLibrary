/*    */ package javax.media.jai;
/*    */ 
/*    */ import java.beans.PropertyChangeEvent;
/*    */ 
/*    */ public class PropertyChangeEventJAI extends PropertyChangeEvent {
/*    */   private String originalPropertyName;
/*    */   
/*    */   public PropertyChangeEventJAI(Object source, String propertyName, Object oldValue, Object newValue) {
/* 50 */     super(source, propertyName.toLowerCase(), oldValue, newValue);
/* 52 */     if (source == null)
/* 53 */       throw new IllegalArgumentException(JaiI18N.getString("PropertyChangeEventJAI0")); 
/* 54 */     if (oldValue == null && newValue == null)
/* 55 */       throw new IllegalArgumentException(JaiI18N.getString("PropertyChangeEventJAI1")); 
/* 58 */     this.originalPropertyName = propertyName.equals(getPropertyName()) ? getPropertyName() : propertyName;
/*    */   }
/*    */   
/*    */   public String getOriginalPropertyName() {
/* 67 */     return this.originalPropertyName;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\PropertyChangeEventJAI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */