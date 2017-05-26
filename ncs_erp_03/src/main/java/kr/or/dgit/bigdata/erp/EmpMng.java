package kr.or.dgit.bigdata.erp;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.dto.Employee;
import kr.or.dgit.bigdata.erp.dto.Title;
import kr.or.dgit.bigdata.erp.service.ErpService;
import kr.or.dgit.bigdata.erp.service.ModelForTable;

public class EmpMng extends JFrame implements ActionListener {

	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfJoindate;
	private JTable empList;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox comboTitle;
	private JSpinner spinnerSalary;
	private JRadioButton rdMale;
	private JRadioButton rdFemale;
	private JComboBox comboDepartment;
	private JButton btnAdd;
	private JButton btnCancel;
	private JPanel panel;
	private JPanel contentPane;

	private JPopupMenu popup = new JPopupMenu();
	private JMenuItem edit = new JMenuItem("수정");
	private JMenuItem del = new JMenuItem("삭제");

	/**
	 * Create the panel.
	 */
	public EmpMng() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 145, 68, 73, 53, 179, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(gridBagLayout);
		setTitle("사원 관리");
		JLabel lblNo = new JLabel("번호");
		GridBagConstraints gbc_lblNo = new GridBagConstraints();
		gbc_lblNo.anchor = GridBagConstraints.EAST;
		gbc_lblNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNo.gridx = 1;
		gbc_lblNo.gridy = 0;
		add(lblNo, gbc_lblNo);

		tfNo = new JTextField();
		GridBagConstraints gbc_tfNo = new GridBagConstraints();
		gbc_tfNo.gridwidth = 2;
		gbc_tfNo.insets = new Insets(0, 0, 5, 5);
		gbc_tfNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNo.gridx = 2;
		gbc_tfNo.gridy = 0;
		add(tfNo, gbc_tfNo);
		tfNo.setColumns(10);

		JLabel lblName = new JLabel("사원명");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		add(lblName, gbc_lblName);

		tfName = new JTextField();
		tfName.setColumns(10);
		GridBagConstraints gbc_tfName = new GridBagConstraints();
		gbc_tfName.gridwidth = 2;
		gbc_tfName.insets = new Insets(0, 0, 5, 5);
		gbc_tfName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfName.gridx = 2;
		gbc_tfName.gridy = 1;
		add(tfName, gbc_tfName);

		JLabel lblTitle = new JLabel("직책");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.EAST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 2;
		add(lblTitle, gbc_lblTitle);

		comboTitle = new JComboBox();
		GridBagConstraints gbc_comboTitle = new GridBagConstraints();
		gbc_comboTitle.gridwidth = 2;
		gbc_comboTitle.insets = new Insets(0, 0, 5, 5);
		gbc_comboTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboTitle.gridx = 2;
		gbc_comboTitle.gridy = 2;
		add(comboTitle, gbc_comboTitle);

		JLabel lblSalary = new JLabel("급여");
		GridBagConstraints gbc_lblSalary = new GridBagConstraints();
		gbc_lblSalary.anchor = GridBagConstraints.EAST;
		gbc_lblSalary.insets = new Insets(0, 0, 5, 5);
		gbc_lblSalary.gridx = 1;
		gbc_lblSalary.gridy = 3;
		add(lblSalary, gbc_lblSalary);

		spinnerSalary = new JSpinner();
		spinnerSalary.setModel(new SpinnerNumberModel(1500000, 1000000, 5000000, 100000));
		GridBagConstraints gbc_spinnerSalary = new GridBagConstraints();
		gbc_spinnerSalary.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerSalary.gridwidth = 2;
		gbc_spinnerSalary.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerSalary.gridx = 2;
		gbc_spinnerSalary.gridy = 3;
		add(spinnerSalary, gbc_spinnerSalary);

		JLabel lblGender = new JLabel("성별");
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.EAST;
		gbc_lblGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblGender.gridx = 1;
		gbc_lblGender.gridy = 4;
		add(lblGender, gbc_lblGender);

		rdMale = new JRadioButton("남");
		rdMale.setSelected(true);
		buttonGroup.add(rdMale);
		GridBagConstraints gbc_rdMale = new GridBagConstraints();
		gbc_rdMale.anchor = GridBagConstraints.EAST;
		gbc_rdMale.insets = new Insets(0, 0, 5, 5);
		gbc_rdMale.gridx = 2;
		gbc_rdMale.gridy = 4;
		add(rdMale, gbc_rdMale);

		rdFemale = new JRadioButton("여");
		buttonGroup.add(rdFemale);
		GridBagConstraints gbc_rdFemale = new GridBagConstraints();
		gbc_rdFemale.anchor = GridBagConstraints.WEST;
		gbc_rdFemale.insets = new Insets(0, 0, 5, 5);
		gbc_rdFemale.gridx = 3;
		gbc_rdFemale.gridy = 4;
		add(rdFemale, gbc_rdFemale);

		JLabel lblDepartment = new JLabel("부서");
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.anchor = GridBagConstraints.EAST;
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.gridx = 1;
		gbc_lblDepartment.gridy = 5;
		add(lblDepartment, gbc_lblDepartment);

		comboDepartment = new JComboBox();
		GridBagConstraints gbc_comboDepartment = new GridBagConstraints();
		gbc_comboDepartment.gridwidth = 2;
		gbc_comboDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_comboDepartment.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboDepartment.gridx = 2;
		gbc_comboDepartment.gridy = 5;
		add(comboDepartment, gbc_comboDepartment);

		JLabel lblJoindate = new JLabel("입사일");
		GridBagConstraints gbc_lblJoindate = new GridBagConstraints();
		gbc_lblJoindate.anchor = GridBagConstraints.EAST;
		gbc_lblJoindate.insets = new Insets(0, 0, 5, 5);
		gbc_lblJoindate.gridx = 1;
		gbc_lblJoindate.gridy = 6;
		add(lblJoindate, gbc_lblJoindate);

		tfJoindate = new JTextField();
		tfJoindate.setColumns(10);
		GridBagConstraints gbc_tfJoindate = new GridBagConstraints();
		gbc_tfJoindate.gridwidth = 2;
		gbc_tfJoindate.insets = new Insets(0, 0, 5, 5);
		gbc_tfJoindate.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfJoindate.gridx = 2;
		gbc_tfJoindate.gridy = 6;
		add(tfJoindate, gbc_tfJoindate);

		btnAdd = new JButton("추가");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAdd.gridwidth = 3;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 7;
		add(btnAdd, gbc_btnAdd);

		btnCancel = new JButton("취소");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancel.anchor = GridBagConstraints.WEST;
		gbc_btnCancel.gridwidth = 2;
		gbc_btnCancel.gridx = 3;
		gbc_btnCancel.gridy = 7;
		add(btnCancel, gbc_btnCancel);

		btnCancel.addActionListener(this);
		btnAdd.addActionListener(this);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 5;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 8;
		add(panel, gbc_panel);

		getList();
		setInput();
		rdMale.setActionCommand("남자");
		rdFemale.setActionCommand("여자");
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editInput();
		}
		});
		del.addActionListener(this);
		popup.add(edit);
		popup.add(del);
	
	}

	private void setInput() {
		List<Title> tList = ErpService.getInstance().getTitleList();
		List<Department> dList = ErpService.getInstance().getDepartmentList();
		for (Title t : tList) {
			comboTitle.addItem(t.getTname());
		}
		for (Department d : dList) {
			comboDepartment.addItem(d.getDname() + "(" + d.getFloor() + "층)");
		}

		tfJoindate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		tfNo.setEditable(false);
		tfNo.setText(String.format("E%06d", (Integer.parseInt(empList.getValueAt(empList.getRowCount() - 1, 0).toString().replace("E", "")) + 1)));
		
	}

	private void getList() {
		List<Employee> list = ErpService.getInstance().getEmpList();

		String[] COL_NAMES = { "번호", "사원명", "직책", "급여", "성별", "부서", "입사일" };
		Object[][] data = new Object[list.size()][COL_NAMES.length];
		int idx = 0;
		for (Employee s : list) {
			data[idx][0] = String.format("E%06d", s.getEno());
			data[idx][1] = s.getEname();
			data[idx][2] = s.getTitle().getTname();
			data[idx][3] = String.format("%,d", s.getSalary()) + "원";
			if (s.getGender() == 1) {
				data[idx][4] = "남자";
			} else {
				data[idx][4] = "여자";
			}

			data[idx][5] = s.getDno().getDname();
			data[idx][6] = new SimpleDateFormat("yyyy-MM-dd").format(s.getJoindate());
			idx++;
		}
		// 테이블 모델 생성
		ModelForTable mft = new ModelForTable(data, COL_NAMES);
		// 리스트별 내용에 따른 cell클래스 지정
		empList = new JTable(mft);

		// 리스트 세부설정
		mft.resizeColumnHeight(empList);
		mft.resizeColumnWidth(empList);
		mft.tableHeaderAlignment(empList);
		mft.tableCellAlignment(empList, SwingConstants.CENTER, 0, 1, 2, 4, 5);
		mft.tableCellAlignment(empList, SwingConstants.RIGHT, 3);
		empList.setModel(mft);
		empList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		empList.setFont(empList.getFont().deriveFont(12.0f));
		empList.getTableHeader().setReorderingAllowed(false);
		empList.setPreferredScrollableViewportSize(new Dimension(450, 150));
		JScrollPane scrollPane = new JScrollPane(empList);
		panel.add(scrollPane);
		
		empList.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (empList.getSelectedRow() >= 0) {
						popup.show(empList, e.getX(), e.getY());
					} else {
						JOptionPane.showMessageDialog(null, "사원을 선택해주세요");
					}
				}
			}

		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "취소":
			clearInput();
			break;

		case "추가":
			if (!tfName.getText().trim().equals("")) {
				insertEmp();
				clearInput();
				JOptionPane.showMessageDialog(null, "추가되었습니다");
			} else {
				JOptionPane.showMessageDialog(null, "빈칸없이 입력해주세요");
				tfName.requestFocus();
			}
			break;
		case "수정":
			updateEmp();
			clearInput();
			btnAdd.setText("추가");
			JOptionPane.showMessageDialog(null, "수정되었습니다");
			break;
		case "삭제":
			deleteEmp();
			JOptionPane.showMessageDialog(null, "삭제되었습니다");
			break;
		}
	}

	private void deleteEmp() {
		ErpService.getInstance().deleteEmp(
				Integer.parseInt(empList.getValueAt(empList.getSelectedRow(), 0).toString().replace("E", "")));
		panel.removeAll();
		getList();
		setInput();
		revalidate();
		repaint();

	}

	private void updateEmp() {
		int gender = 0;
		if (buttonGroup.getSelection().getActionCommand().equals("남자")) {
			gender = 1;
		} else {
			gender = 0;
		}
		ErpService.getInstance().updateEmp(new Employee(Integer.parseInt(tfNo.getText().replace("E", "")),
				tfName.getText().trim(), Integer.parseInt(spinnerSalary.getValue().toString()),
				new Department(comboDepartment.getSelectedItem().toString().split("\\(")[0].trim()), gender,
				new Date(tfJoindate.getText().replace("-", "/")), new Title(comboTitle.getSelectedItem().toString())));
		panel.removeAll();
		getList();
		revalidate();
		repaint();

	}

	private void editInput() {
		btnAdd.setText("수정");
		Employee e = ErpService.getInstance().selectEmp(
				Integer.parseInt(empList.getValueAt(empList.getSelectedRow(), 0).toString().replace("E", "")));
		tfNo.setText(empList.getValueAt(empList.getSelectedRow(), 0).toString());
		tfName.setText(empList.getValueAt(empList.getSelectedRow(), 1).toString());
		System.out.println(e);
		for (int i = 0; i < comboTitle.getItemCount(); i++) {
			if (comboTitle.getItemAt(i).toString().equals(e.getTitle().getTname())) {
				comboTitle.setSelectedIndex(i);
				break;
			}
		}
		for (int i = 0; i < comboDepartment.getItemCount(); i++) {
			if (comboDepartment.getItemAt(i).toString().contains(e.getDno().getDname())) {
				comboDepartment.setSelectedIndex(i);
				break;
			}
		}
		if (e.getGender() == 1) {
			rdMale.setSelected(true);
		} else {
			rdFemale.setSelected(true);
		}
		tfJoindate.setText(new SimpleDateFormat("yyyy-MM-dd").format(e.getJoindate()));
		spinnerSalary.setValue(e.getSalary());

	}

	private void clearInput() {
		setInput();
		comboDepartment.setSelectedIndex(0);
		comboTitle.setSelectedIndex(0);
		tfName.setText("");
		spinnerSalary.setValue(1500000);
	}

	private void insertEmp() {
		int gender = 0;
		if (buttonGroup.getSelection().getActionCommand().equals("남자")) {
			gender = 1;
		} else {
			gender = 0;
		}
		ErpService.getInstance().insertEmp(new Employee(Integer.parseInt(tfNo.getText().replace("E", "")),
				tfName.getText().trim(), Integer.parseInt(spinnerSalary.getValue().toString()),
				new Department(comboDepartment.getSelectedItem().toString().split("\\(")[0].trim()), gender,
				new Date(tfJoindate.getText().replace("-", "/")), new Title(comboTitle.getSelectedItem().toString())));
		panel.removeAll();
		getList();
		revalidate();
		repaint();
	}

}
