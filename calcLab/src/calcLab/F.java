package calcLab;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;


import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class F extends JFrame {
	private final JLabel calcArea = new JLabel("");

	// calculator control
	private String calcAreaDefault = new String("0.0");
	private boolean initialCalcAreaInputState;
	private enum STATE { INITIAL, SAVE1, SAVE2, CALC };
	private STATE mathState;
	
	// calculator values
	private enum OPERATOR { NOOP, PLUS, MINUS, DIVIDE, MULTIPLY };
    private OPERATOR mathOp;
	private double arg1;
    private double arg2;
    private double calcAnswer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F frame = new F();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void calculateAnswer()  // method to perform calculation
	{
	    switch(mathOp)
	    {
	        case PLUS:
	            calcAnswer = arg1 + arg2;
	            break;
	        case MINUS:
	            calcAnswer = arg1 - arg2;
	            break;
	        case DIVIDE:
	            calcAnswer = arg1 / arg2;
	            break;
	        case MULTIPLY:
	            calcAnswer = arg1 * arg2;
	            break;
	        case NOOP:
	            calcAnswer = arg1;
	    }
		calcArea.setText(String.valueOf(calcAnswer));
	    arg1 = Double.valueOf(calcArea.getText());
	    mathState = STATE.CALC;
		initialCalcAreaInputState = true;
	}
	
	private void updateCalcArea(String string) {
		if (initialCalcAreaInputState) {  // sets text to string on initial key typed
			calcArea.setText(string);
			initialCalcAreaInputState = false;
	    } else  {                         // concatenates string to end of text subsequent keys typed
			calcArea.setText(calcArea.getText() + string);
	    }
	}
	
	/**
	 * Save values for Calculator.
	 */
	private void saveValueOfArg1() { // method to store 1st value in calculation (arg1)
	    arg1 = Double.valueOf(calcArea.getText());
	    mathState = STATE.SAVE1;
		initialCalcAreaInputState = true;
	}
	
	private void saveValueOfArg2() { // method to store 2nd value in calculation (arg2)
		if (mathState != STATE.CALC) {
			arg2 = Double.valueOf(calcArea.getText());
			mathState = STATE.SAVE2;
		}
	}
	
	private void saveValueOfMathOp(OPERATOR op) { // method to store operator
		mathOp = op;
	}
	
	private void clearCalculator() {  // helper method to clear and update calculator to default
		calcArea.setText(calcAreaDefault);
		mathState = STATE.INITIAL;
		initialCalcAreaInputState = true;
		arg1 = 0.0;
		arg2 = 0.0;
		calcAnswer = 0.0;
	}
	
	public F() {
		getContentPane().setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 315);
		getContentPane().setLayout(null);
		calcArea.setForeground(Color.WHITE);
		calcArea.setFont(new Font("Lucida Grande", Font.PLAIN, 72));
		calcArea.setHorizontalAlignment(SwingConstants.RIGHT);
		calcArea.setBounds(18, 6, 377, 67);
		getContentPane().add(calcArea);
		
		JButton button_1 = new JButton("1");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_1.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				button_1.setBackground(Color.DARK_GRAY);
			}
		});
		button_1.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.WHITE));
		button_1.setOpaque(true);
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.DARK_GRAY);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCalcArea(button_1.getText());
			}
		});
		button_1.setBounds(35, 86, 75, 40);
		getContentPane().add(button_1);
		
		JButton button_plus = new JButton("+");
		button_plus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_plus.setBackground(Color.WHITE);
			}
			
			public void mouseReleased(MouseEvent e) {
				button_plus.setBackground(new Color(210, 105, 30));
			}
		});
		button_plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveValueOfArg1();
				saveValueOfMathOp(OPERATOR.PLUS);
			}
		});
		button_plus.setOpaque(true);
		button_plus.setForeground(Color.WHITE);
		button_plus.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.WHITE));
		button_plus.setBackground(new Color(210, 105, 30));
		button_plus.setBounds(327, 138, 75, 40);
		getContentPane().add(button_plus);
		
		JButton button_minus = new JButton("-");
		button_minus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_minus.setBackground(Color.WHITE);
			}
			
			public void mouseReleased(MouseEvent e) {
				button_minus.setBackground(new Color(210, 105, 30));
			}
		});
		button_minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveValueOfArg1();
				saveValueOfMathOp(OPERATOR.MINUS);
			}
		});
		button_minus.setOpaque(true);
		button_minus.setForeground(Color.WHITE);
		button_minus.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.WHITE));
		button_minus.setBackground(new Color(210, 105, 30));
		button_minus.setBounds(327, 190, 75, 40);
		getContentPane().add(button_minus);
		
		JButton button_equals = new JButton("=");
		button_equals.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_equals.setBackground(Color.WHITE);
			}
			
			public void mouseReleased(MouseEvent e) {
				button_equals.setBackground(new Color(210, 105, 30));
			}
		});
		button_equals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveValueOfArg2();
				calculateAnswer();
			}
		});
		button_equals.setOpaque(true);
		button_equals.setForeground(Color.WHITE);
		button_equals.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.WHITE));
		button_equals.setBackground(new Color(210, 105, 30));
		button_equals.setBounds(327, 242, 75, 40);
		getContentPane().add(button_equals);
		
		JButton button_clear = new JButton("AC");
		button_clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button_clear.setBackground(Color.WHITE);
			}
			
			public void mouseReleased(MouseEvent e) {
				button_clear.setBackground(Color.LIGHT_GRAY);
			}
		});
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearCalculator();
			}
		});
		button_clear.setOpaque(true);
		button_clear.setForeground(Color.BLACK);
		button_clear.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.WHITE));
		button_clear.setBackground(Color.LIGHT_GRAY);
		button_clear.setBounds(327, 85, 75, 40);
		getContentPane().add(button_clear);
	}
}