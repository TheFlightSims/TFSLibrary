/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.Date;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jfree.date.SerialDate;
/*     */ 
/*     */ public class SerialDateChooserPanel extends JPanel implements ActionListener {
/*  77 */   public static final Color DEFAULT_DATE_BUTTON_COLOR = Color.red;
/*     */   
/*  80 */   public static final Color DEFAULT_MONTH_BUTTON_COLOR = Color.lightGray;
/*     */   
/*     */   private SerialDate date;
/*     */   
/*     */   private Color dateButtonColor;
/*     */   
/*     */   private Color monthButtonColor;
/*     */   
/*  92 */   private Color chosenOtherButtonColor = Color.darkGray;
/*     */   
/*  95 */   private int firstDayOfWeek = 1;
/*     */   
/*  98 */   private int yearSelectionRange = 20;
/*     */   
/* 101 */   private Font dateFont = new Font("SansSerif", 0, 10);
/*     */   
/* 104 */   private JComboBox monthSelector = null;
/*     */   
/* 107 */   private JComboBox yearSelector = null;
/*     */   
/* 110 */   private JButton todayButton = null;
/*     */   
/* 113 */   private JButton[] buttons = null;
/*     */   
/*     */   private boolean refreshing = false;
/*     */   
/*     */   public SerialDateChooserPanel() {
/* 123 */     this(SerialDate.createInstance(new Date()), false, DEFAULT_DATE_BUTTON_COLOR, DEFAULT_MONTH_BUTTON_COLOR);
/*     */   }
/*     */   
/*     */   public SerialDateChooserPanel(SerialDate date, boolean controlPanel) {
/* 138 */     this(date, controlPanel, DEFAULT_DATE_BUTTON_COLOR, DEFAULT_MONTH_BUTTON_COLOR);
/*     */   }
/*     */   
/*     */   public SerialDateChooserPanel(SerialDate date, boolean controlPanel, Color dateButtonColor, Color monthButtonColor) {
/* 155 */     super(new BorderLayout());
/* 157 */     this.date = date;
/* 158 */     this.dateButtonColor = dateButtonColor;
/* 159 */     this.monthButtonColor = monthButtonColor;
/* 161 */     add(constructSelectionPanel(), "North");
/* 162 */     add(getCalendarPanel(), "Center");
/* 163 */     if (controlPanel)
/* 164 */       add(constructControlPanel(), "South"); 
/*     */   }
/*     */   
/*     */   public void setDate(SerialDate date) {
/* 176 */     this.date = date;
/* 177 */     this.monthSelector.setSelectedIndex(date.getMonth() - 1);
/* 178 */     refreshYearSelector();
/* 179 */     refreshButtons();
/*     */   }
/*     */   
/*     */   public SerialDate getDate() {
/* 189 */     return this.date;
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 199 */     if (e.getActionCommand().equals("monthSelectionChanged")) {
/* 200 */       JComboBox c = (JComboBox)e.getSource();
/* 201 */       this.date = SerialDate.createInstance(this.date.getDayOfMonth(), c.getSelectedIndex() + 1, this.date.getYYYY());
/* 204 */       refreshButtons();
/* 206 */     } else if (e.getActionCommand().equals("yearSelectionChanged")) {
/* 207 */       if (!this.refreshing) {
/* 208 */         JComboBox c = (JComboBox)e.getSource();
/* 209 */         Integer y = (Integer)c.getSelectedItem();
/* 210 */         this.date = SerialDate.createInstance(this.date.getDayOfMonth(), this.date.getMonth(), y.intValue());
/* 213 */         refreshYearSelector();
/* 214 */         refreshButtons();
/*     */       } 
/* 217 */     } else if (e.getActionCommand().equals("todayButtonClicked")) {
/* 218 */       setDate(SerialDate.createInstance(new Date()));
/* 220 */     } else if (e.getActionCommand().equals("dateButtonClicked")) {
/* 221 */       JButton b = (JButton)e.getSource();
/* 222 */       int i = Integer.parseInt(b.getName());
/* 223 */       SerialDate first = getFirstVisibleDate();
/* 224 */       SerialDate selected = SerialDate.addDays(i, first);
/* 225 */       setDate(selected);
/*     */     } 
/*     */   }
/*     */   
/*     */   private JPanel getCalendarPanel() {
/* 238 */     JPanel panel = new JPanel(new GridLayout(7, 7));
/* 239 */     panel.add(new JLabel("Sun", 0));
/* 240 */     panel.add(new JLabel("Mon", 0));
/* 241 */     panel.add(new JLabel("Tue", 0));
/* 242 */     panel.add(new JLabel("Wed", 0));
/* 243 */     panel.add(new JLabel("Thu", 0));
/* 244 */     panel.add(new JLabel("Fri", 0));
/* 245 */     panel.add(new JLabel("Sat", 0));
/* 247 */     this.buttons = new JButton[42];
/* 248 */     for (int i = 0; i < 42; i++) {
/* 249 */       JButton button = new JButton("");
/* 250 */       button.setMargin(new Insets(1, 1, 1, 1));
/* 251 */       button.setName(Integer.toString(i));
/* 252 */       button.setFont(this.dateFont);
/* 253 */       button.setFocusPainted(false);
/* 254 */       button.setActionCommand("dateButtonClicked");
/* 255 */       button.addActionListener(this);
/* 256 */       this.buttons[i] = button;
/* 257 */       panel.add(button);
/*     */     } 
/* 259 */     return panel;
/*     */   }
/*     */   
/*     */   protected Color getButtonColor(SerialDate targetDate) {
/* 272 */     if (this.date.equals(this.date))
/* 273 */       return this.dateButtonColor; 
/* 275 */     if (targetDate.getMonth() == this.date.getMonth())
/* 276 */       return this.monthButtonColor; 
/* 279 */     return this.chosenOtherButtonColor;
/*     */   }
/*     */   
/*     */   protected SerialDate getFirstVisibleDate() {
/* 292 */     SerialDate result = SerialDate.createInstance(1, this.date.getMonth(), this.date.getYYYY());
/* 293 */     result = SerialDate.addDays(-1, result);
/* 294 */     while (result.getDayOfWeek() != getFirstDayOfWeek())
/* 295 */       result = SerialDate.addDays(-1, result); 
/* 297 */     return result;
/*     */   }
/*     */   
/*     */   private int getFirstDayOfWeek() {
/* 307 */     return this.firstDayOfWeek;
/*     */   }
/*     */   
/*     */   protected void refreshButtons() {
/* 315 */     SerialDate current = getFirstVisibleDate();
/* 316 */     for (int i = 0; i < 42; i++) {
/* 317 */       JButton button = this.buttons[i];
/* 318 */       button.setText(String.valueOf(current.getDayOfWeek()));
/* 319 */       button.setBackground(getButtonColor(current));
/* 320 */       current = SerialDate.addDays(1, current);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void refreshYearSelector() {
/* 330 */     if (!this.refreshing) {
/* 331 */       this.refreshing = true;
/* 332 */       this.yearSelector.removeAllItems();
/* 333 */       Vector v = getYears(this.date.getYYYY());
/* 334 */       for (Enumeration e = v.elements(); e.hasMoreElements();)
/* 335 */         this.yearSelector.addItem(e.nextElement()); 
/* 337 */       this.yearSelector.setSelectedItem(new Integer(this.date.getYYYY()));
/* 338 */       this.refreshing = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Vector getYears(int chosenYear) {
/* 351 */     Vector v = new Vector();
/* 352 */     int i = chosenYear - this.yearSelectionRange;
/* 353 */     for (; i <= chosenYear + this.yearSelectionRange; i++)
/* 354 */       v.addElement(new Integer(i)); 
/* 356 */     return v;
/*     */   }
/*     */   
/*     */   private JPanel constructSelectionPanel() {
/* 366 */     JPanel p = new JPanel();
/* 367 */     this.monthSelector = new JComboBox(SerialDate.getMonths());
/* 368 */     this.monthSelector.addActionListener(this);
/* 369 */     this.monthSelector.setActionCommand("monthSelectionChanged");
/* 370 */     p.add(this.monthSelector);
/* 372 */     this.yearSelector = new JComboBox(getYears(0));
/* 373 */     this.yearSelector.addActionListener(this);
/* 374 */     this.yearSelector.setActionCommand("yearSelectionChanged");
/* 375 */     p.add(this.yearSelector);
/* 377 */     return p;
/*     */   }
/*     */   
/*     */   private JPanel constructControlPanel() {
/* 388 */     JPanel p = new JPanel();
/* 389 */     p.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
/* 390 */     this.todayButton = new JButton("Today");
/* 391 */     this.todayButton.addActionListener(this);
/* 392 */     this.todayButton.setActionCommand("todayButtonClicked");
/* 393 */     p.add(this.todayButton);
/* 394 */     return p;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\SerialDateChooserPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */