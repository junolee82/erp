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
import java.util.Date;
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

public class TitleMng extends JFrame implements ActionListener {
	private JTextField tfNo;
	private JTextField tfTitle;
	private JButton btnAdd;
	private JButton btnCancel;
	private JPanel panel;
	private JPanel contentPane;

	private JPopupMenu popup = new JPopupMenu();
	private JMenuItem edit = new JMenuItem("수정");
	private JMenuItem del = new JMenuItem("삭제");

	private JTable titleList;

	/**
	 * Create the panel.
	 */
	public TitleMng() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 114, 101, 109, 114, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 531, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(gridBagLayout);
		setTitle("직책 관리");

		JLabel lblNo = new JLabel("번호");
		GridBagConstraints gbc_lblNo = new GridBagConstraints();
		gbc_lblNo.anchor = GridBagConstraints.EAST;
		gbc_lblNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNo.gridx = 1;
		gbc_lblNo.gridy = 0;
		getContentPane().add(lblNo, gbc_lblNo);

		tfNo = new JTextField();
		GridBagConstraints gbc_tfNo = new GridBagConstraints();
		gbc_tfNo.insets = new Insets(0, 0, 5, 5);
		gbc_tfNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNo.gridx = 2;
		gbc_tfNo.gridy = 0;
		getContentPane().add(tfNo, gbc_tfNo);
		tfNo.setColumns(10);

		JLabel lblName = new JLabel("직책명");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		getContentPane().add(lblName, gbc_lblName);

		tfTitle = new JTextField();
		tfTitle.setColumns(10);
		GridBagConstraints gbc_tfTitle = new GridBagConstraints();
		gbc_tfTitle.insets = new Insets(0, 0, 5, 5);
		gbc_tfTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTitle.gridx = 2;
		gbc_tfTitle.gridy = 1;
		getContentPane().add(tfTitle, gbc_tfTitle);

		btnAdd = new JButton("추가");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 3;
		getContentPane().add(btnAdd, gbc_btnAdd);

		btnCancel = new JButton("취소");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.WEST;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 3;
		getContentPane().add(btnCancel, gbc_btnCancel);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 4;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		getContentPane().add(panel, gbc_panel);

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
			if (!tfTitle.getText().trim().equals("")) {
				insertTitle();
				clearInput();
				JOptionPane.showMessageDialog(null, "추가되었습니다");
			} else {
				JOptionPane.showMessageDialog(null, "빈칸없이 입력해주세요");
				tfTitle.requestFocus();
			}
			break;
		case "수정":
			updateTitle();
			clearInput();
			btnAdd.setText("추가");
			JOptionPane.showMessageDialog(null, "수정되었습니다");
			break;
		case "삭제":
			deleteTitle();
			JOptionPane.showMessageDialog(null, "삭제되었습니다");
			break;
		}
	}

	private void insertTitle() {
		ErpService.getInstance()
				.insertTitle(new Title(
						Integer.parseInt(
								titleList.getValueAt(titleList.getRowCount() - 1, 0).toString().replace("T", "")) + 1,
						tfTitle.getText().trim()));
		panel.removeAll();
		getList();
		revalidate();
		repaint();
	}

	private void deleteTitle() {
		ErpService.getInstance().deleteTitle(
				Integer.parseInt(titleList.getValueAt(titleList.getSelectedRow(), 0).toString().replace("T", "")));
		panel.removeAll();
		getList();
		setInput();
		revalidate();
		repaint();

	}

	private void updateTitle() {
		ErpService.getInstance()
				.updateTitle(new Title(Integer.parseInt(tfNo.getText().replace("T", "")), tfTitle.getText().trim()));
		panel.removeAll();
		getList();
		revalidate();
		repaint();

	}

	private void clearInput() {
		setInput();
		tfTitle.setText("");
	}

	private void setInput() {
		tfNo.setEditable(false);
		tfNo.setText(
				String.format("T%03d",
						(Integer.parseInt(
								titleList.getValueAt(titleList.getRowCount() - 1, 0).toString().replace("T", ""))
								+ 1)));
	}

	private void getList() {
		List<Title> list = ErpService.getInstance().getTitleList();

		String[] COL_NAMES = { "번호", "직책" };
		Object[][] data = new Object[list.size()][COL_NAMES.length];
		int idx = 0;
		for (Title s : list) {
			data[idx][0] = String.format("T%03d", s.getTcode());
			data[idx][1] = s.getTname();
			idx++;
		}
		// 테이블 모델 생성
		ModelForTable mft = new ModelForTable(data, COL_NAMES);
		// 리스트별 내용에 따른 cell클래스 지정
		titleList = new JTable(mft);

		// 리스트 세부설정
		mft.resizeColumnHeight(titleList);
		mft.resizeColumnWidth(titleList);
		mft.tableHeaderAlignment(titleList);
		mft.tableCellAlignment(titleList, SwingConstants.CENTER, 0, 1);
		titleList.setModel(mft);
		titleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		titleList.setFont(titleList.getFont().deriveFont(12.0f));
		titleList.getTableHeader().setReorderingAllowed(false);
		titleList.setPreferredScrollableViewportSize(new Dimension(250, 150));
		JScrollPane scrollPane = new JScrollPane(titleList);
		panel.add(scrollPane);
		

		titleList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (titleList.getSelectedRow() >= 0) {
						popup.show(titleList, e.getX(), e.getY());
					} else {
						JOptionPane.showMessageDialog(null, "직책을 선택해주세요");
					}
				}
			}
		});
	}

	private void editInput() {
		btnAdd.setText("수정");
		Title e = ErpService.getInstance().selectTitle(
				Integer.parseInt(titleList.getValueAt(titleList.getSelectedRow(), 0).toString().replace("T", "")));
		tfNo.setText(String.format("T%03d", e.getTcode()));
		tfTitle.setText(e.getTname());
	}
}
