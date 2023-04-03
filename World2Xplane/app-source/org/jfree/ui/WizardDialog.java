/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class WizardDialog extends JDialog implements ActionListener {
/*     */   private Object result;
/*     */   
/*     */   private int step;
/*     */   
/*     */   private WizardPanel currentPanel;
/*     */   
/*     */   private List panels;
/*     */   
/*     */   private JButton previousButton;
/*     */   
/*     */   private JButton nextButton;
/*     */   
/*     */   private JButton finishButton;
/*     */   
/*     */   private JButton helpButton;
/*     */   
/*     */   public WizardDialog(JDialog owner, boolean modal, String title, WizardPanel firstPanel) {
/* 112 */     super(owner, title + " : step 1", modal);
/* 113 */     this.result = null;
/* 114 */     this.currentPanel = firstPanel;
/* 115 */     this.step = 0;
/* 116 */     this.panels = new ArrayList();
/* 117 */     this.panels.add(firstPanel);
/* 118 */     setContentPane(createContent());
/*     */   }
/*     */   
/*     */   public WizardDialog(JFrame owner, boolean modal, String title, WizardPanel firstPanel) {
/* 133 */     super(owner, title + " : step 1", modal);
/* 134 */     this.result = null;
/* 135 */     this.currentPanel = firstPanel;
/* 136 */     this.step = 0;
/* 137 */     this.panels = new ArrayList();
/* 138 */     this.panels.add(firstPanel);
/* 139 */     setContentPane(createContent());
/*     */   }
/*     */   
/*     */   public Object getResult() {
/* 148 */     return this.result;
/*     */   }
/*     */   
/*     */   public int getStepCount() {
/* 159 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean canDoPreviousPanel() {
/* 168 */     return (this.step > 0);
/*     */   }
/*     */   
/*     */   public boolean canDoNextPanel() {
/* 177 */     return this.currentPanel.hasNextPanel();
/*     */   }
/*     */   
/*     */   public boolean canFinish() {
/* 187 */     return this.currentPanel.canFinish();
/*     */   }
/*     */   
/*     */   public WizardPanel getWizardPanel(int step) {
/* 198 */     if (step < this.panels.size())
/* 199 */       return this.panels.get(step); 
/* 202 */     return null;
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent event) {
/* 212 */     String command = event.getActionCommand();
/* 213 */     if (command.equals("nextButton")) {
/* 214 */       next();
/* 216 */     } else if (command.equals("previousButton")) {
/* 217 */       previous();
/* 219 */     } else if (command.equals("finishButton")) {
/* 220 */       finish();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void previous() {
/* 228 */     if (this.step > 0) {
/* 229 */       WizardPanel previousPanel = getWizardPanel(this.step - 1);
/* 231 */       previousPanel.returnFromLaterStep();
/* 232 */       Container content = getContentPane();
/* 233 */       content.remove(this.currentPanel);
/* 234 */       content.add(previousPanel);
/* 235 */       this.step--;
/* 236 */       this.currentPanel = previousPanel;
/* 237 */       setTitle("Step " + (this.step + 1));
/* 238 */       enableButtons();
/* 239 */       pack();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void next() {
/* 248 */     WizardPanel nextPanel = getWizardPanel(this.step + 1);
/* 249 */     if (nextPanel != null) {
/* 250 */       if (!this.currentPanel.canRedisplayNextPanel())
/* 251 */         nextPanel = this.currentPanel.getNextPanel(); 
/*     */     } else {
/* 255 */       nextPanel = this.currentPanel.getNextPanel();
/*     */     } 
/* 258 */     this.step++;
/* 259 */     if (this.step < this.panels.size()) {
/* 260 */       this.panels.set(this.step, nextPanel);
/*     */     } else {
/* 263 */       this.panels.add(nextPanel);
/*     */     } 
/* 266 */     Container content = getContentPane();
/* 267 */     content.remove(this.currentPanel);
/* 268 */     content.add(nextPanel);
/* 270 */     this.currentPanel = nextPanel;
/* 271 */     setTitle("Step " + (this.step + 1));
/* 272 */     enableButtons();
/* 273 */     pack();
/*     */   }
/*     */   
/*     */   public void finish() {
/* 281 */     this.result = this.currentPanel.getResult();
/* 282 */     setVisible(false);
/*     */   }
/*     */   
/*     */   private void enableButtons() {
/* 290 */     this.previousButton.setEnabled((this.step > 0));
/* 291 */     this.nextButton.setEnabled(canDoNextPanel());
/* 292 */     this.finishButton.setEnabled(canFinish());
/* 293 */     this.helpButton.setEnabled(false);
/*     */   }
/*     */   
/*     */   public boolean isCancelled() {
/* 302 */     return false;
/*     */   }
/*     */   
/*     */   public JPanel createContent() {
/* 312 */     JPanel content = new JPanel(new BorderLayout());
/* 313 */     content.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
/* 314 */     content.add(this.panels.get(0));
/* 315 */     L1R3ButtonPanel buttons = new L1R3ButtonPanel("Help", "Previous", "Next", "Finish");
/* 317 */     this.helpButton = buttons.getLeftButton();
/* 318 */     this.helpButton.setEnabled(false);
/* 320 */     this.previousButton = buttons.getRightButton1();
/* 321 */     this.previousButton.setActionCommand("previousButton");
/* 322 */     this.previousButton.addActionListener(this);
/* 323 */     this.previousButton.setEnabled(false);
/* 325 */     this.nextButton = buttons.getRightButton2();
/* 326 */     this.nextButton.setActionCommand("nextButton");
/* 327 */     this.nextButton.addActionListener(this);
/* 328 */     this.nextButton.setEnabled(true);
/* 330 */     this.finishButton = buttons.getRightButton3();
/* 331 */     this.finishButton.setActionCommand("finishButton");
/* 332 */     this.finishButton.addActionListener(this);
/* 333 */     this.finishButton.setEnabled(false);
/* 335 */     buttons.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
/* 336 */     content.add(buttons, "South");
/* 338 */     return content;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\WizardDialog.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */