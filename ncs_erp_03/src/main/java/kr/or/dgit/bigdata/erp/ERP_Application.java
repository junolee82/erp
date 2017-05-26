package kr.or.dgit.bigdata.erp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ERP_Application extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnEmp;
	private JButton btnDepartment;
	private JButton btnTitle;
	private EmpMng emp = new EmpMng();
	private DeptMng dept = new DeptMng();
	private TitleMng title = new TitleMng();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ERP_Application frame = new ERP_Application();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ERP_Application() {
		setTitle("대구아이티ERP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 123);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 20, 0));
		makeButtons();
	}

	public void makeButtons() {
		btnEmp = new JButton("사원관리");
		btnEmp.addActionListener(this);
		contentPane.add(btnEmp);

		btnDepartment = new JButton("부서관리");
		btnDepartment.addActionListener(this);
		contentPane.add(btnDepartment);

		btnTitle = new JButton("직책관리");
		btnTitle.addActionListener(this);
		contentPane.add(btnTitle);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "사원관리":
			emp.setVisible(true);
			break;
		case "부서관리":
			dept.setVisible(true);
			break;
		case "직책관리":
			title.setVisible(true);
			break;
		}

	}

}
