/*    */ package org.jfree.ui;
/*    */ 
/*    */ import java.awt.LayoutManager;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public abstract class WizardPanel extends JPanel {
/*    */   private WizardDialog owner;
/*    */   
/*    */   protected WizardPanel(LayoutManager layout) {
/* 66 */     super(layout);
/*    */   }
/*    */   
/*    */   public WizardDialog getOwner() {
/* 75 */     return this.owner;
/*    */   }
/*    */   
/*    */   public void setOwner(WizardDialog owner) {
/* 85 */     this.owner = owner;
/*    */   }
/*    */   
/*    */   public Object getResult() {
/* 94 */     return null;
/*    */   }
/*    */   
/*    */   public abstract void returnFromLaterStep();
/*    */   
/*    */   public abstract boolean canRedisplayNextPanel();
/*    */   
/*    */   public abstract boolean hasNextPanel();
/*    */   
/*    */   public abstract boolean canFinish();
/*    */   
/*    */   public abstract WizardPanel getNextPanel();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\WizardPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */