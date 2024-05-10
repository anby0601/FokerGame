package 단판포커;
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
		ProgramBasic app = new ProgramBasic("단판포커");
	}
	
	public ProgramBasic(String s) {
		super(s);
		initializeComponents();

	}
	
	private void initializeComponents() {
		Container c = getContentPane();
		c.setLayout(null);
		GameStart = new JButton("게임시작");
		c.add(GameStart);
		GameStart.setLocation(350, 40);
		GameStart.setSize(180, 80);
		GameStart.addActionListener(this);
		GameStart.setBackground(Color.white);
		
		StringBuilder str = new StringBuilder("<html><pre> 상대를 파산시키자! <br>"
				+ "1~9 숫자의 카드중<br>" + "하나를 가지고 상대를<br>" + "이겨보자. 동일한 금<br>" + "액을 배팅하면 카드를<br>" + 
				"공개한다.<br>" + "<br>" + "단 배팅시 딜러보다<br>" + "적은 돈을 걸면 카드와<br>" + "상관없이 패배한다<br>");
		str.append("<br></pre></html>");
		JLabel p1 = new JLabel(str.toString());
		c.add(p1);
		p1.setLocation(380, 100);
		p1.setSize(300,300);
		p1.setForeground(Color.blue);
		
		
		
		
		JLabel p2 = new JLabel("지닌 돈: ");
		p2.setBounds(100, 330,50, 20);
		c.add(p2);
		
		
		
		p3.setBounds(160, 330 , 50, 20);
		c.add(p3);
		p3.setText(String.valueOf(PlayerMoney));
		
		JLabel p4 = new JLabel("만원");
		p4.setBounds(220, 330, 50, 20);
		c.add(p4);
		
		JLabel p5 = new JLabel("지닌 돈: ");
		p5.setBounds(600, 330, 50, 20);
		c.add(p5);
		
		
		p6.setBounds(660, 330 , 50, 20);
		c.add(p6);
		p6.setText(String.valueOf(DealerMoney));
		
		JLabel p7 = new JLabel("만원");
		p7.setBounds(720, 330, 50, 20);
		c.add(p7);
		
		JLabel p8 = new JLabel("<배팅액 입력>");
		p8.setBounds(270, 350, 100,20);
		c.add(p8);
		
		
		PField.setBounds(250, 370, 120, 20);
		c.add(PField);
		
		JLabel p9 = new JLabel("<배팅액 입력>");
		p9.setBounds(520, 350, 100,20);
		c.add(p9);
		
		
		DField.setBounds(500, 370, 120, 20);
		c.add(DField);
		
		Betting = new JButton("배팅!");
		Betting.setBounds(270, 390, 70, 20);
		c.add(Betting);
		Betting.setEnabled(false);
		Betting.addActionListener(this);
		
		
		JButton Max = new JButton("최대");
		Max.setBounds(380, 350, 70, 20);
		c.add(Max);
		Max.addActionListener(this);
		
		JButton Same = new JButton("동률");
		Same.setBounds(380, 375, 70, 20);
		c.add(Same);
		Same.addActionListener(this);
		
		JButton Min = new JButton("최소");
		Min.setBounds(380, 400, 70, 20);
		c.add(Min);
		Min.addActionListener(this);
		
		JLabel p10 = new JLabel("<배팅액>");
		p10.setBounds(280, 430, 100, 20);
		c.add(p10);
		
		
		p11.setBounds(300, 470, 100, 20);
		c.add(p11);
		p11.setText(String.valueOf("0"));
		
		JLabel p12 = new JLabel("<배팅액>");
		p12.setBounds(540, 430, 100 , 20);
		c.add(p12);
		
		
		p13.setBounds(560, 470, 100, 20);
		c.add(p13);
		p13.setText(String.valueOf("0"));
		
		
		
		ShowPCard.setBounds(130, 370, 100, 150);
		ShowPCard.setBorder(bb);
		c.add(ShowPCard);
		ShowPCard.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		
		
		
		ShowDCard.setBounds(630, 370, 100, 150);
		ShowDCard.setBorder(bb);
		c.add(ShowDCard);
		ShowDCard.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		
		
		
		JLabel p14 = new JLabel(">>총 배팅액: ");
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
		Narrator.setText("당신의 차례입니다. 배팅할 금액을 입력해주세요.");
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
					"당신은 파산했습니다....\n" + "YOU LOSE.\n", null,
					JOptionPane.PLAIN_MESSAGE);
			 System.exit(0);
		}
		
		if(DealerMoney <=0) {
			JOptionPane.showMessageDialog(null,
					"딜러를 파산시켰습니다!\n" + Round + "라운드만에 승리하셨습니다!\n", null,
					JOptionPane.PLAIN_MESSAGE);
			 System.exit(0);
		}
		
	}
	

	Random rnd = new Random();
	int StackBetMoney = 0;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("게임시작")) {
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
			Narrator.setText("당신의 차례입니다. 배팅할 금액을 입력해주세요.");
			GameStart.setEnabled(false);
			Betting.setEnabled(true);
				
		}
		
		
		
		
		if(e.getActionCommand().equals("배팅!")) {
			String s = PField.getText();
			if(s.length() == 0) {
				Narrator.setText("다시 입력해주세요.");
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
						Narrator.setText("당신이 이겼습니다! " + TotalBetMoney + "만원을 얻습니다.");
						StackBetMoney = 0;
						BreakGame();
						
					}
					
					if(PCard == DCard) {
						ShowTotalBetMoney();
						PlayerMoney += TotalBetMoney/2;
						DealerMoney += TotalBetMoney/2;
						p3.setText(String.valueOf(PlayerMoney));
						p6.setText(String.valueOf(DealerMoney));
						Narrator.setText("비겼습니다! " + TotalBetMoney/2 + "만원을 얻습니다.");
						StackBetMoney = 0;
						BreakGame();
					}
					
					if(PCard < DCard) {
						ShowTotalBetMoney();
						DealerMoney += TotalBetMoney;
						p3.setText(String.valueOf(PlayerMoney));
						p6.setText(String.valueOf(DealerMoney));
						Narrator.setText("당신이 졌습니다... " + StackBetMoney + "만원을 잃습니다.");
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
					Narrator.setText("당신이 졌습니다... " + StackBetMoney + "만원을 잃습니다.");
					StackBetMoney = 0;
					BreakGame();
					
				}
				
			}
			
			if(PBetMoney > DBetMoney) {
				RestartGame();
			}
		}
		
		
		if(e.getActionCommand().equals("최대")) {
			PField.setText(String.valueOf(PlayerMoney));
		}
		if(e.getActionCommand().equals("동률")) {
			PField.setText(String.valueOf(DBetMoney));
		}
		if(e.getActionCommand().equals("최소")) {
			PField.setText(String.valueOf("1"));
		}
	}


	

}