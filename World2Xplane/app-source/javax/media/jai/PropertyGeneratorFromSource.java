/*    */ package javax.media.jai;
/*    */ 
/*    */ import com.sun.media.jai.util.PropertyGeneratorImpl;
/*    */ import java.awt.Image;
/*    */ import java.util.Vector;
/*    */ 
/*    */ class PropertyGeneratorFromSource extends PropertyGeneratorImpl {
/*    */   int sourceIndex;
/*    */   
/*    */   String propertyName;
/*    */   
/*    */   PropertyGeneratorFromSource(int sourceIndex, String propertyName) {
/* 30 */     super(new String[] { propertyName }, new Class[] { Object.class }, new Class[] { OperationNode.class });
/* 34 */     if (propertyName == null)
/* 35 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 38 */     this.sourceIndex = sourceIndex;
/* 39 */     this.propertyName = propertyName;
/*    */   }
/*    */   
/*    */   public Object getProperty(String name, Object opNode) {
/* 44 */     if (name == null || opNode == null)
/* 45 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 48 */     if (this.sourceIndex >= 0 && opNode instanceof OperationNode && this.propertyName.equalsIgnoreCase(name)) {
/* 51 */       OperationNode op = (OperationNode)opNode;
/* 52 */       Vector sources = op.getParameterBlock().getSources();
/* 53 */       if (sources != null && this.sourceIndex < sources.size()) {
/* 54 */         Object src = sources.elementAt(this.sourceIndex);
/* 55 */         if (src instanceof PropertySource)
/* 56 */           return ((PropertySource)src).getProperty(name); 
/*    */       } 
/*    */     } 
/* 61 */     return Image.UndefinedProperty;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\PropertyGeneratorFromSource.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */