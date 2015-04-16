package tictactoe;

import java.util.Scanner;

public class Tictactoe
	{
	public static Scanner read=new Scanner(System.in);

	public static void main(String[] args)
		{
		int choice=0;
		while(choice!=2)
			{
			System.out.println("1 - play tic tac toe (pvp)");
			System.out.println("2 - Quit");
			try
				{
				choice=read.nextInt();
				}
			catch (Exception e)
				{
				choice=0;
				read.next();
				}
			if(choice==1)
				{
				playgamepvp();
				}
			}
		read.close();
		}

	// plays a game with 2 human players
	public static void playgamepvp()
		{
		int boardnum=0;
		int player=1;
		int choice=0;
		for(int temp=0;temp<9;temp++)
			{
			choice=-1;
			while(choice==-1)// get a valid input
				{
				printboard(boardnum);
				System.out.print("it is currently player ");
				if(player==1)
					System.out.print("x");
				else
					System.out.print("o");
				System.out.println(" turn");
				try
					{
					choice=turn(boardnum,player,read.nextInt());//error catch
					}
				catch (Exception e)
					{
					if(e.getMessage()==null)
						read.next();
					}
				if(choice==-1)
					System.out.println("invalid spot");
				else
					{
					boardnum=choice;
					if(wintest(boardnum)!=0)
						{
						System.out.print("player ");
						if(wintest(boardnum)==1)
							System.out.println("x won");
						else
							System.out.println("o won");
						printboard(boardnum);
						player=0;
						break;
						}
					if(player==1)
						player=2;
					else
						player=1;
					}
				}
			if(player==0)// someone won
				{
				break;
				}
			}
		if(player!=0)
			{
			printboard(boardnum);
			System.out.println("tie game");
			}
		}

	// plays a turn in tic tac toe returns new boardnum or -1 if invalad place
	public static int turn(int boardnum,int player,int place)
		{
		int[] board=new int[9];
		int temp2=boardnum;
		for(int temp=0;temp<9;temp++)
			{
			board[temp]=temp2%10;
			temp2=temp2/10;
			}
		if(board[9-place]!=0)
			return -1;
		else
			return boardnum+player*(int)Math.pow(10,9-place);
		}

	// super simple number printout for testing purpose
	public static void printboard(int boardnum)
		{
		char[] board=new char[9];
		char[] board2=new char[9];
		for(int temp=0;temp<9;temp++)
			{
			if(boardnum%10==1)
				{
				board[temp]='x';
				board2[temp]='x';
				}
			else if(boardnum%10==2)
				{
				board[temp]='o';
				board2[temp]='o';
				}
			else
				{
				board2[temp]=' ';
				String temp2=Integer.toString(9-temp);
				board[temp]=temp2.charAt(0);
				}
			boardnum=boardnum/10;
			}
		System.out.println(board[8]+"|"+board[7]+"|"+board[6]+" "+board2[8]+"|"+board2[7]+"|"+board2[6]);
		System.out.println("-+-+- -+-+-");
		System.out.println(board[5]+"|"+board[4]+"|"+board[3]+" "+board2[5]+"|"+board2[4]+"|"+board2[3]);
		System.out.println("-+-+- -+-+-");
		System.out.println(board[2]+"|"+board[1]+"|"+board[0]+" "+board2[2]+"|"+board2[1]+"|"+board2[0]);
		}

	// checks a game state to see if there is a winner returns (0=no
	// winner,1=x,2=o)
	public static int wintest(int boardnum)
		{
		int[] win=new int[9];
		for(int temp=0;temp<9;temp++)
			{
			win[temp]=boardnum%10;
			boardnum=boardnum/10;
			}
		if(win[0]==win[1]&&win[0]==win[2]&&win[0]!=0)// xxx000000 012
			return win[0];
		if(win[3]==win[4]&&win[3]==win[5]&&win[3]!=0)// 000xxx000 345
			return win[3];
		if(win[6]==win[7]&&win[6]==win[8]&&win[6]!=0)// 000000xxx 678
			return win[6];
		if(win[0]==win[3]&&win[0]==win[6]&&win[0]!=0)// x00x00x00 036
			return win[0];
		if(win[1]==win[4]&&win[1]==win[7]&&win[1]!=0)// 0x00x00x0 147
			return win[1];
		if(win[2]==win[5]&&win[2]==win[8]&&win[2]!=0)// 00x00x00x 258
			return win[2];
		if(win[0]==win[4]&&win[0]==win[8]&&win[0]!=0)// x000x000x 048
			return win[0];
		if(win[2]==win[6]&&win[2]==win[6]&&win[2]!=0)// 00x0x0x00 246
			return win[2];
		return 0;
		}
	}
