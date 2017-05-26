package kr.or.dgit.bigdata.erp;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.dto.Employee;
import kr.or.dgit.bigdata.erp.dto.Title;
import kr.or.dgit.bigdata.erp.service.ErpService;
import kr.or.dgit.bigdata.erp.service.ModelForTable;

import javax.swing.JButton;
import javax.swing.JFrame;

public class DeptMng extends JFrame implements ActionListener {
	private JTextField tfNo;
	private JTextField tfDept;
	private JTextField tfFloor;
	private JButton btnAdd;
	private JButton btnCancel;
	private JPanel panel;
	private JPanel contentPane;
	private JTable deptList;

	private JPopupMenu popup = new JPopupMenu();
	private JMenuItem edit = new JMenuItem("수정");
	private JMenuItem del = new JMenuItem("삭제");

	/**
	 * Create the panel.
	 */
	public DeptMng() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 114, 101, 109, 114, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(gridBagLayout);
		setTitle("부서 관리");

		JLabel lblNo = new JLabel("번호");
		GridBagConstraints gbc_lblNo = new GridBagConstraints();
		gbc_lblNo.anchor = GridBagConstraints.EAST;
		gbc_lblNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNo.gridx = 1;
		gbc_lblNo.gridy = 0;
		add(lblNo, gbc_lblNo);

		tfNo = new JTextField();
		GridBagConstraints gbc_tfNo = new GridBagConstraints();
		gbc_tfNo.insets = new Insets(0, 0, 5, 5);
		gbc_tfNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNo.gridx = 2;
		gbc_tfNo.gridy = 0;
		add(tfNo, gbc_tfNo);
		tfNo.setColumns(10);

		JLabel lblName = new JLabel("부서명");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		add(lblName, gbc_lblName);

		tfDept = new JTextField();
		tfDept.setColumns(10);
		GridBagConstraints gbc_tfDept = new GridBagConstraints();
		gbc_tfDept.insets = new Insets(0, 0, 5, 5);
		gbc_tfDept.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDept.gridx = 2;
		gbc_tfDept.gridy = 1;
		add(tfDept, gbc_tfDept);

		JLabel lblFloor = new JLabel("위치");
		GridBagConstraints gbc_lblFloor = new GridBagConstraints();
		gbc_lblFloor.anchor = GridBagConstraints.EAST;
		gbc_lblFloor.insets = new Insets(0, 0, 5, 5);
		gbc_lblFloor.gridx = 1;
		gbc_lblFloor.gridy = 2;
		add(lblFloor, gbc_lblFloor);

		tfFloor = new JTextField();
		tfFloor.setColumns(10);
		GridBagConstraints gbc_tfFloor = new GridBagConstraints();
		gbc_tfFloor.insets = new Insets(0, 0, 5, 5);
		gbc_tfFloor.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfFloor.gridx = 2;
		gbc_tfFloor.gridy = 2;
		add(tfFloor, gbc_tfFloor);

		btnAdd = new JButton("추가");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 3;
		add(btnAdd, gbc_btnAdd);

		btnCancel = new JButton("취소");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.WEST;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 3;
		add(btnCancel, gbc_btnCancel);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 4;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		add(panel, gbc_panel);

		btnAdd.addActionListener(this);
		btnCancel.addActionListener(this);
		getList();
		setInput();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "취소":
			clearInput();
			break;

		case "추가":
			if (!tfDept.getText().trim().equals("") && !tfFloor.getText().trim().equals("")) {
				insertDept();
				clearInput();
				JOptionPane.showMessageDialog(null, "추가되었습니다");
			} else {
				JOptionPane.showMessageDialog(null, "빈칸없이 입력해주세요");
			}
			break;
		case "수정":
			updateDept();
			clearInput();
			btnAdd.setText("추가");
			JOptionPane.showMessageDialog(null, "수정되었습니다");
			break;
		case "삭제":
			deleteDept();
			JOptionPane.showMessageDialog(null, "삭제되었습니다");
			break;
		}
	}
	private void editInput() {
		btnAdd.setText("수정");
		Department e = ErpService.getInstance().selectDept(
				Integer.parseInt(deptList.getValueAt(deptList.getSelectedRow(), 0).toString().replace("D", "")));
		tfNo.setText(String.format("D%03d", e.getDcode()));
		tfDept.setText(e.getDname());
		tfFloor.setText(e.getFloor()+"");
	}
	private void deleteDept() {
		ErpService.getInstance().deleteDept(
				Integer.parseInt(deptList.getValueAt(deptList.getSelectedRow(), 0).toString().replace("D", "")));
		panel.removeAll();
		getList();
		setInput();
		revalidate();
		repaint();

	}
	private void updateDept() {
		ErpService.getInstance().updateDept(new Department(Integer.parseInt(tfNo.getText().replace("D", "")),
				tfDept.getText().trim(), Integer.parseInt(tfFloor.getText().trim())));
		panel.removeAll();
		getList();
		revalidate();
		repaint();

	}
	private void insertDept() {
		ErpService.getInstance().insertDept(new Department(Integer.parseInt(tfNo.getText().replace("D", "")),
				tfDept.getText().trim(), Integer.parseInt(tfFloor.getText().trim())));
		panel.removeAll();
		getList();
		revalidate();
		repaint();
	}
	private void clearInput() {
		setInput();
		tfDept.setText("");
		tfFloor.setText("");
	}
	private void setInput() {
		tfNo.setEditable(false);
		tfNo.setText(String.format("D%03d",
				(Integer.parseInt(deptList.getValueAt(deptList.getRowCount() - 1, 0).toString().replace("D", ""))
						+ 1)));
	}

	private void getList() {
		List<Department> list = ErpService.getInstance().getDepartmentList();

		String[] COL_NAMES = { "번호", "부서명", "위치" };
		Object[][] data = new Object[list.size()][COL_NAMES.length];
		int idx = 0;
		for (Department s : list) {
			data[idx][0] = String.format("D%03d", s.getDcode());
			data[idx][1] = s.getDname();
			data[idx][2] = s.getFloor();

			idx++;
		}
		// 테이블 모델 생성
		ModelForTable mft = new ModelForTable(data, COL_NAMES);
		// 리스트별 내용에 따른 cell클래스 지정
		deptList = new JTable(mft);

		// 리스트 세부설정
		mft.resizeColumnHeight(deptList);
		mft.resizeColumnWidth(deptList);
		mft.tableHeaderAlignment(deptList);
		mft.tableCellAlignment(deptList, SwingConstants.CENTER, 0, 1, 2);
		deptList.setModel(mft);
		deptList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		deptList.setFont(deptList.getFont().deriveFont(12.0f));
		deptList.getTableHeader().setReorderingAllowed(false);
		deptList.setPreferredScrollableViewportSize(new Dimension(250, 150));
		JScrollPane scrollPane = new JScrollPane(deptList);
		panel.add(scrollPane);
		deptList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (deptList.getSelectedRow() >= 0) {
						popup.show(deptList, e.getX(), e.getY());
					} else {
						JOptionPane.showMessageDialog(null, "부서를 선택해주세요");
					}
				}
			}
		});
	}

}
