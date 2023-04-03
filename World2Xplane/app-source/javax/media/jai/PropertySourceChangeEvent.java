/*    */ package javax.media.jai;
/*    */ 
/*    */ public class PropertySourceChangeEvent extends PropertyChangeEventJAI {
/*    */   public PropertySourceChangeEvent(Object source, String propertyName, Object oldValue, Object newValue) {
/* 47 */     super(source, propertyName, oldValue, newValue);
/* 51 */     if (oldValue == null)
/* 52 */       throw new IllegalArgumentException(JaiI18N.getString("PropertySourceChangeEvent0")); 
/* 53 */     if (newValue == null)
/* 54 */       throw new IllegalArgumentException(JaiI18N.getString("PropertySourceChangeEvent1")); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\PropertySourceChangeEvent.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */