package shop_for_flowers_UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.DropMode;
import javax.swing.ImageIcon;

import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Window.Type;
import javax.swing.UIManager;
import javax.swing.JTextField;

public class AddOrder extends JFrame {

	public String user_phone="";	//유저 PK 정보를 저장할 변수
	public Boolean manager = false;	//유저가 관리자인지 확인할 변수
	int ret = 1;	//파일 선택 여부를 알려주는 변수
	JButton bookAddButton;
	private JPanel contentPane;
	private JTextField bookNameTextField;
	private JTextField bookHeaderTextField_1;
	private JTextField bookPriceTextField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AddOrder(String user_phone, Boolean manager) {
		this.user_phone = user_phone;
		this.manager = manager;
		
		setTitle("꽃관리 프로그램 - 주문추가");
		setBounds(100, 100, 848, 622);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//이미지 파일 필터
		JFileChooser book_img = new JFileChooser();
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("All Images", "jpg","jpge","png","gif");
		book_img.setFileFilter(fileFilter);
		

		// 책 정보 패널
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(12, 31, 805, 215);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//책 이릅 라벨
		JLabel bookNameLabel = new JLabel("고객 전화번호");
		bookNameLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookNameLabel.setBounds(12, 10, 104, 46);
		panel_1.add(bookNameLabel);

		// 책 이름 텍스트필드
		bookNameTextField = new JTextField();
		bookNameTextField.setFont(new Font("함초롬돋움", Font.BOLD, 22));
		bookNameTextField.setBounds(118, 17, 407, 26);
		panel_1.add(bookNameTextField);
		bookNameTextField.setColumns(10);
		bookNameTextField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    changed();
				  }

				  public void changed() {
				     if (bookISBNTextField.getText().equals("")){
				    	 bookAddButton.setEnabled(false);
				     }
				     else {
				    	 bookAddButton.setEnabled(true);
				    }

				  }
				});

		// 책 저자 라벨
		JLabel bookHeaderLabel_1 = new JLabel("고객이름");
		bookHeaderLabel_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookHeaderLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookHeaderLabel_1.setBounds(12, 60, 81, 26);
		panel_1.add(bookHeaderLabel_1);

		// 책 저자 텍스트필드
		bookHeaderTextField_1 = new JTextField();
		bookHeaderTextField_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookHeaderTextField_1.setColumns(10);
		bookHeaderTextField_1.setBounds(118, 60, 157, 26);
		panel_1.add(bookHeaderTextField_1);
		bookHeaderTextField_1.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    changed();
				  }

				  public void changed() {
				     if (bookISBNTextField.getText().equals("")){
				    	 bookAddButton.setEnabled(false);
				     }
				     else {
				    	 bookAddButton.setEnabled(true);
				    }

				  }
				});

		// 책 가격 라벨
		JLabel bookPriceLabel = new JLabel("상품번호, 수량");
		bookPriceLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceLabel.setBounds(12, 155, 104, 26);
		panel_1.add(bookPriceLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(91, 145, 225, 15);
		panel_1.add(lblNewLabel_1);
		// 책 가격 텍스트필드
		bookPriceTextField = new JTextField();
		bookPriceTextField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookPriceTextField.setColumns(10);
		bookPriceTextField.setBounds(118, 155, 407, 26);
		panel_1.add(bookPriceTextField);
		bookPriceTextField.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	             String value = bookPriceTextField.getText();
	             int l = value.length();
	             if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
	            	 bookPriceTextField.setEditable(true);
	            	 lblNewLabel_1.setText("");
	             } else {
	            	 bookPriceTextField.setEditable(false);
	            	 lblNewLabel_1.setText("가격은 숫자만 가능합니다.");
	             }
	          }
	       });
		
		bookPriceTextField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  
				    changed();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    changed();
				  }
				  
				  public void changed() {
				     if (bookISBNTextField.getText().equals("")){
				    	 bookAddButton.setEnabled(false);
				     }
				     else {
				    	 bookAddButton.setEnabled(true);
				    }
				   
				     if (bookPriceTextField.getText().length() > 10) {
				    	 bookAddButton.setEnabled(false);
				    	 lblNewLabel_1.setText("가격은 10자리 이하만 가능합니다.");
				     }else {
				    	 bookAddButton.setEnabled(true);
				    	 lblNewLabel_1.setText("");
				     }

				  }
				});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(91, 202, 225, 15);
		panel_1.add(lblNewLabel);
		
		JLabel bookHeaderLabel_1_1 = new JLabel("주소");
		bookHeaderLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookHeaderLabel_1_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		bookHeaderLabel_1_1.setBounds(12, 108, 46, 26);
		panel_1.add(bookHeaderLabel_1_1);
		
		textField = new JTextField();
		textField.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(118, 108, 157, 26);
		panel_1.add(textField);

		// 책 추가 버튼
		bookAddButton = new JButton("\uB3C4\uC11C \uCD94\uAC00");
		bookAddButton.setEnabled(false);
		bookAddButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		bookAddButton.setBounds(348, 511, 132, 48);
		//책 추가 버튼을 누르면 호출되는 메소드 연결
//		bookAddButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				boolean isSuccess = false;
//
//					String sql = "insert into BOOK(\r\n"
//							+ "BOOK_ISBN,\r\n"
//							+ "BOOK_TITLE,\r\n"
//							+ "BOOK_AUTHOR,\r\n"
//							+ "BOOK_PUB,\r\n"
//							+ "BOOK_PRICE,\r\n"
//							+ "BOOK_DESCRIPTION,\r\n"
//							+ "BOOK_LINK,\r\n"
//							+ "BOOK_IMAGE,\r\n"
//							+ "BOOK_APPEND_DATE\r\n"
//							+ ")values(\r\n"
//							+ "?, ?, ?, ?, ?, ?, ?, ?, date_format(now(),'%Y-%m-%d'));";
//					
//				try { // DB 접근
//					PreparedStatement ps = dbConn.conn.prepareStatement(sql);
//					
//					ps.setString(1, bookISBNTextField.getText());			//ISBN
//					ps.setString(2, bookNameTextField.getText());			//책 제목
//					ps.setString(3, bookHeaderTextField_1.getText());		//책 저자
//					ps.setString(4, bookHeaderTextField_2.getText());		//책 출판사
//					ps.setInt(5, Integer.parseInt(bookPriceTextField.getText()));	//책 가격	(숫자만 가능)
//					ps.setString(6, bookDescriptionTextField.getText());	//책 줄거리
//					ps.setString(7, bookLinkTextField.getText());			//책 관련링크
//					
//					if(ret == 1) book_img_path.setText("images/nobook.png");
//					//책 이미지
//					FileInputStream fin = new FileInputStream(book_img_path.getText());
//					ps.setBinaryStream(8, fin, fin.available());
//					
//					
//					
//					int count = ps.executeUpdate();
//					if(count==0) {	
//						JOptionPane.showMessageDialog(null,"ISBN : "+bookISBNTextField.getText()+"이(는) 등록에 실패하였습니다.", "신규도서등록 실패", JOptionPane.ERROR_MESSAGE);
//					}
//					else {		
//						JOptionPane.showMessageDialog(null,"ISBN : "+bookISBNTextField.getText()+"이(는) 등록에 성공하였습니다.", "신규도서등록 성공", JOptionPane.NO_OPTION);
//						isSuccess = true;
//					}
//				}
//				catch (NumberFormatException e1) {
//					e1.printStackTrace();	//에러 추적
//					JOptionPane.showMessageDialog(null, "가격에 숫자만 입력가능합니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);	//가격에 문자 입력시 메시지 호출
//				}
//				catch(SQLIntegrityConstraintViolationException e1) {
//					e1.printStackTrace();
//					JOptionPane.showMessageDialog(null, "입력한 ISBN이 이미 존재합니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);	//가격에 문자 입력시 메시지 호출
//				}
//				catch (SQLException e1) {
//					e1.printStackTrace();	//에러 추적
//					System.out.println("도서추가 화면에서 SQL 실행 에러");
//				}catch(FileNotFoundException e1) {
//					e1.printStackTrace();
//					System.out.println("도서추가 화면에서 파일 찾기 에러");
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//				if(isSuccess)
//					dispose();	//추가 후 창 닫기
//			}
//		});
//		contentPane.add(bookAddButton);	//책 추가 버튼 부착
		
	
	}
}