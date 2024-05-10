package ������Ŀ;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.LineBorder;



public class ProgramBasic extends JFrame implements ActionListener{
	JButton GameStart;
	JButton Betting;
	int PlayerMoney = 500;
	int DealerMoney = 500;
	int PBetMoney = 0;
	int DBetMoney = 0;
	int PCard = (int) (Math.random() * 9) + 1;
	int DCard = (int) (Math.random() * 9) + 1;
	JLabel ShowPCard = new JLabel();
	JLabel ShowDCard = new JLabel();
	JTextField PField = new JTextField(20);
	JTextField DField = new JTextField(20);
	JLabel p3 = new JLabel();
	JLabel p6 = new JLabel();
	JLabel p11 = new JLabel();
	JLabel p13 = new JLabel();
	JLabel p15 = new JLabel();
	JTextField Narrator = new JTextField(500);
	int TotalBetMoney = 0;
	int Round = 0;
	
	private LineBorder bb = new LineBorder(Color.black, 1, true);


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProgramBasic app = new ProgramBasic("������Ŀ");
	}
	
	public ProgramBasic(String s) {
		super(s);
		initializeComponents();

	}
	
	private void initializeComponents() {
		Container c = getContentPane();
		c.setLayout(null);
		GameStart = new JButton("���ӽ���");
		c.add(GameStart);
		GameStart.setLocation(350, 40);
		GameStart.setSize(180, 80);
		GameStart.addActionListener(this);
		GameStart.setBackground(Color.white);
		
		StringBuilder str = new StringBuilder("<html><pre> ��븦 �Ļ��Ű��! <br>"
				+ "1~9 ������ ī����<br>" + "�ϳ��� ������ ��븦<br>" + "�̰ܺ���. ������ ��<br>" + "���� �����ϸ� ī�带<br>" + 
				"�����Ѵ�.<br>" + "<br>" + "�� ���ý� ��������<br>" + "���� ���� �ɸ� ī���<br>" + "������� �й��Ѵ�<br>");
		str.append("<br></pre></html>");
		JLabel p1 = new JLabel(str.toString());
		c.add(p1);
		p1.setLocation(380, 100);
		p1.setSize(300,300);
		p1.setForeground(Color.blue);
		
		
		
		
		JLabel p2 = new JLabel("���� ��: ");
		p2.setBounds(100, 330,50, 20);
		c.add(p2);
		
		
		
		p3.setBounds(160, 330 , 50, 20);
		c.add(p3);
		p3.setText(String.valueOf(PlayerMoney));
		
		JLabel p4 = new JLabel("����");
		p4.setBounds(220, 330, 50, 20);
		c.add(p4);
		
		JLabel p5 = new JLabel("���� ��: ");
		p5.setBounds(600, 330, 50, 20);
		c.add(p5);
		
		
		p6.setBounds(660, 330 , 50, 20);
		c.add(p6);
		p6.setText(String.valueOf(DealerMoney));
		
		JLabel p7 = new JLabel("����");
		p7.setBounds(720, 330, 50, 20);
		c.add(p7);
		
		JLabel p8 = new JLabel("<���þ� �Է�>");
		p8.setBounds(270, 350, 100,20);
		c.add(p8);
		
		
		PField.setBounds(250, 370, 120, 20);
		c.add(PField);
		
		JLabel p9 = new JLabel("<���þ� �Է�>");
		p9.setBounds(520, 350, 100,20);
		c.add(p9);
		
		
		DField.setBounds(500, 370, 120, 20);
		c.add(DField);
		
		Betting = new JButton("����!");
		Betting.setBounds(270, 390, 70, 20);
		c.add(Betting);
		Betting.setEnabled(false);
		Betting.addActionListener(this);
		
		
		JButton Max = new JButton("�ִ�");
		Max.setBounds(380, 350, 70, 20);
		c.add(Max);
		Max.addActionListener(this);
		
		JButton Same = new JButton("����");
		Same.setBounds(380, 375, 70, 20);
		c.add(Same);
		Same.addActionListener(this);
		
		JButton Min = new JButton("�ּ�");
		Min.setBounds(380, 400, 70, 20);
		c.add(Min);
		Min.addActionListener(this);
		
		JLabel p10 = new JLabel("<���þ�>");
		p10.setBounds(280, 430, 100, 20);
		c.add(p10);
		
		
		p11.setBounds(300, 470, 100, 20);
		c.add(p11);
		p11.setText(String.valueOf("0"));
		
		JLabel p12 = new JLabel("<���þ�>");
		p12.setBounds(540, 430, 100 , 20);
		c.add(p12);
		
		
		p13.setBounds(560, 470, 100, 20);
		c.add(p13);
		p13.setText(String.valueOf("0"));
		
		
		
		ShowPCard.setBounds(130, 370, 100, 150);
		ShowPCard.setBorder(bb);
		c.add(ShowPCard);
		ShowPCard.setFont(new Font("���� ���", Font.BOLD, 50));
		
		
		
		ShowDCard.setBounds(630, 370, 100, 150);
		ShowDCard.setBorder(bb);
		c.add(ShowDCard);
		ShowDCard.setFont(new Font("���� ���", Font.BOLD, 50));
		
		
		
		JLabel p14 = new JLabel(">>�� ���þ�: ");
		p14.setBounds(340, 540, 100, 20);
		c.add(p14);
		
		
		p15.setBounds(440, 540, 100, 20);
		c.add(p15);
		p15.setText(String.valueOf("0"));
		
		
		Narrator.setBounds(150, 580, 600, 20);
		c.add(Narrator);
		
		
	
		setSize(900,700);
		setLocation(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(Color.blue);
		
	}
	
	private void RestartGame(){
		Betting.setEnabled(true);
		DBetMoney = (int) AIInvest(DCard, PlayerMoney, DealerMoney);
		DField.setText(String.valueOf(DBetMoney));
		DealerMoney-=DBetMoney;
		p6.setText(String.valueOf(DealerMoney));
		Narrator.setText("����� �����Դϴ�. ������ �ݾ��� �Է����ּ���.");
	}
	
	private void ShowTotalBetMoney(){
		TotalBetMoney += (PBetMoney + DBetMoney);
		p15.setText(String.valueOf(TotalBetMoney));
	}
	private int min(int P, int D) {
		// TODO Auto-generated method stub
		int k =0;
		if(P < D) {
			return k = P;
		}	
		else if(P>=D) {
			return k = D;
		}
		return k;
	}
	
	private int AIInvest(int k, int i, int j) {
		int num= 0;
		if(k == 1) {
			if((int)(min(i, j)*0.2)<=0) {
				num = 1;
			}
			else {
				num =rnd.nextInt((int)(min(i, j)*0.2)) +1;
			}
		}
		if(k == 2|| k == 3) {
			if(((int)(min(i, j)*0.4)) <= 0) {
				num = 1;
			}
			else {
				num =rnd.nextInt((int)(min(i, j)*0.4))+1;
			}
		}
		if(k == 4|| k == 5) {
			num = rnd.nextInt(min(i, j))+1;
			
		}
		if(k == 6|| k == 7) {
			if((int)(min(i, j)*0.6) <= 0) {
				num = 1;
			}
			else {
				num = rnd.nextInt((int)(min(i, j)*0.6))+(int)(min(i, j)*0.4);
			}
			
		}
		if(k == 8|| k == 9) {
			if((int)(min(i, j)*0.3) <= 0) {
				num = (int)(min(i, j)*0.7);
			}
			else {
				num = rnd.nextInt((int)(min(i, j)*0.3))+(int)(min(i, j)*0.7);
			}
		}
		return num;
	}
	
	private void BreakGame() {
		if(PlayerMoney <= 0) {
			JOptionPane.showMessageDialog(null,
					"����� �Ļ��߽��ϴ�....\n" + "YOU LOSE.\n", null,
					JOptionPane.PLAIN_MESSAGE);
			 System.exit(0);
		}
		
		if(DealerMoney <=0) {
			JOptionPane.showMessageDialog(null,
					"������ �Ļ���׽��ϴ�!\n" + Round + "���常�� �¸��ϼ̽��ϴ�!\n", null,
					JOptionPane.PLAIN_MESSAGE);
			 System.exit(0);
		}
		
	}
	

	Random rnd = new Random();
	int StackBetMoney = 0;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("���ӽ���")) {
			Round ++;
			PCard = rnd.nextInt(9) + 1;
			DCard = rnd.nextInt(9) + 1;
			TotalBetMoney = 0;
			p15.setText(String.valueOf(TotalBetMoney));
			ShowPCard.setText(String.valueOf("  "));
			ShowDCard.setText(String.valueOf("  " + DCard));
			DBetMoney = AIInvest(DCard, PlayerMoney, DealerMoney);
			DField.setText(String.valueOf(DBetMoney));
			DField.setEditable(false);
			PField.setText("");
			DealerMoney-=DBetMoney;
			p6.setText(String.valueOf(DealerMoney));
			Narrator.setText("����� �����Դϴ�. ������ �ݾ��� �Է����ּ���.");
			GameStart.setEnabled(false);
			Betting.setEnabled(true);
				
		}
		
		
		
		
		if(e.getActionCommand().equals("����!")) {
			String s = PField.getText();
			if(s.length() == 0) {
				Narrator.setText("�ٽ� �Է����ּ���.");
				return;
			}
			
			int k = Integer.parseInt(s);
			PBetMoney = k;
			GameStart.setEnabled(true);
			Betting.setEnabled(false);
			
			
			if(PField.getText() != null) {
				if(PBetMoney > DBetMoney) {
					GameStart.setEnabled(false);
					p11.setText(String.valueOf(PBetMoney));
					p13.setText(String.valueOf(DBetMoney));
					PlayerMoney -= PBetMoney;
					p3.setText(String.valueOf(PlayerMoney));
					PField.setText("");
					ShowTotalBetMoney();
					StackBetMoney += PBetMoney;
					
					
				}
				
				
				
				if(PBetMoney == DBetMoney) {
					ShowPCard.setText(String.valueOf("  " + PCard));
					p11.setText(String.valueOf(PBetMoney));
					p13.setText(String.valueOf(DBetMoney));
					PlayerMoney -= PBetMoney;
					p3.setText(String.valueOf(PlayerMoney));
					StackBetMoney += PBetMoney;
					if(PCard > DCard) {
						ShowTotalBetMoney();
						PlayerMoney += TotalBetMoney;
						p3.setText(String.valueOf(PlayerMoney));
						p6.setText(String.valueOf(DealerMoney));
						Narrator.setText("����� �̰���ϴ�! " + TotalBetMoney + "������ ����ϴ�.");
						StackBetMoney = 0;
						BreakGame();
						
					}
					
					if(PCard == DCard) {
						ShowTotalBetMoney();
						PlayerMoney += TotalBetMoney/2;
						DealerMoney += TotalBetMoney/2;
						p3.setText(String.valueOf(PlayerMoney));
						p6.setText(String.valueOf(DealerMoney));
						Narrator.setText("�����ϴ�! " + TotalBetMoney/2 + "������ ����ϴ�.");
						StackBetMoney = 0;
						BreakGame();
					}
					
					if(PCard < DCard) {
						ShowTotalBetMoney();
						DealerMoney += TotalBetMoney;
						p3.setText(String.valueOf(PlayerMoney));
						p6.setText(String.valueOf(DealerMoney));
						Narrator.setText("����� �����ϴ�... " + StackBetMoney + "������ �ҽ��ϴ�.");
						StackBetMoney = 0;
						BreakGame();
						
					}
				}
				
				if(PBetMoney < DBetMoney) {
					ShowPCard.setText(String.valueOf("  " + PCard));
					p11.setText(String.valueOf(PBetMoney));
					p13.setText(String.valueOf(DBetMoney));
					PlayerMoney -= PBetMoney;
					StackBetMoney += PBetMoney;
					PField.setText("");
					ShowTotalBetMoney();
					DealerMoney += TotalBetMoney;
					p3.setText(String.valueOf(PlayerMoney));
					p6.setText(String.valueOf(DealerMoney));
					Narrator.setText("����� �����ϴ�... " + StackBetMoney + "������ �ҽ��ϴ�.");
					StackBetMoney = 0;
					BreakGame();
					
				}
				
			}
			
			if(PBetMoney > DBetMoney) {
				RestartGame();
			}
		}
		
		
		if(e.getActionCommand().equals("�ִ�")) {
			PField.setText(String.valueOf(PlayerMoney));
		}
		if(e.getActionCommand().equals("����")) {
			PField.setText(String.valueOf(DBetMoney));
		}
		if(e.getActionCommand().equals("�ּ�")) {
			PField.setText(String.valueOf("1"));
		}
	}


	

}