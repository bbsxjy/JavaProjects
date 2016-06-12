package cardgame.jingyu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cardgame.jingyu.CardSet.card;

import java.util.*;


public class PlayerSet extends CardSet{
	public List <player> PlayerSet;
	
	public PlayerSet(){
	this.PlayerSet=new ArrayList<player>();
	
	
	}
	 private Scanner console=new Scanner(System.in);
	
	public class player{
	
	String ID;
	String Name;
	String Cards;
	public player(String ID,String Name,String Cards){
		this.ID=ID;
		this.Name=Name;
		this.Cards=Cards;
	
	}
}
	
//	public class playerwcards{
//		String ID;
//		String Name;
//		String[] Cards;
//		public playerwcards(String ID,String Name,String[] Cards){
//			this.ID=ID;
//			this.Name=Name;
//			this.Cards=Cards;
//		}
//	}
	
	public void addPlayer(){
		int i=0;
		while(i<2){
		System.out.println("请输入您的ID：");
		String ID=console.next();
		System.out.println("请输入您的姓名：");
		String Name=console.next();
		player pl=new player(ID,Name,"");
		PlayerSet.add(pl);
		i++;
	}
		player pl2=new player("","","");
		PlayerSet.add(pl2);
		player pl3=new player("","","");
		PlayerSet.add(pl3);
		
	}

	public void playerGetCards(String [] a,String [] b){
		player temp=(player)PlayerSet.get(0);
		player temp2=(player)PlayerSet.get(1);
		for(int i=0;i<3;i=i+2){
			player pw=new player(temp.ID,temp.Name,a[i]);
			//System.out.println(a[i]);
			PlayerSet.set(i,pw);
			player pw2=new player(temp2.ID,temp2.Name,b[i+1]);
			PlayerSet.set(i+1,pw2);
			//System.out.println(b[i+1]);
			
		}
		System.out.println(temp.Name+"拿到牌牌堆为:"+Arrays.toString(a));
		System.out.println(temp2.Name+"拿到牌牌堆为:"+Arrays.toString(b));
		System.out.println("--------------发牌结束---------------");
		
		}

	
	
	
	public void GameStart(){
		System.out.println("--------------游戏开始--------------");
		int Max=0;
		int Max1=0;
		int Max2=0;
		int Max3=0;
		player temp0=(player)PlayerSet.get(0);
		player temp2=(player)PlayerSet.get(1);
		for(int i=0;i<PlayerSet.size();i=i+2){
			player temp=(player)PlayerSet.get(i);
			player temp1=(player)PlayerSet.get(i+1);
			
			int a =number(temp.Cards.substring(2));
			int b =number(temp1.Cards.substring(2));
			int c= Huase(temp.Cards.substring(0,2));
			int d= Huase(temp1.Cards.substring(0,2));
			
			
			if(a>Max){
				Max=a;
			}
			if(b>Max1){
				Max1=b;
			}	
			if(c>Max2){
				Max2=c;
			}
			if(d>Max3){
				Max3=d;
			}
			
		}
			if(Max>Max1){
			System.out.println(temp0.Name+" 赢得了比点大赛");}
			else{System.out.println(temp2.Name+" 赢得了比点大赛");}
			
			if(Max==Max1){
				if(Max2>Max3){
					System.out.println(temp0.Name+" 赢得了比点大赛");
				}else{System.out.println(temp2.Name+" 赢得了比点大赛");}
			}
			

	
			System.out.println("--------------游戏结束---------------");
	}
	
//	public void player2GetCards(String [] b){
//		player temp2=(player)PlayerSet.get(1);
//		System.out.println(temp2.Name+temp2.ID);
//		for(int i=0;i<2;i++){
//			player pw=new player(temp2.ID,temp2.Name,b[i]);
//			PlayerSet.add(i+2,pw);
//			
//			
//	}
//		System.out.println(temp2.Name+"拿到牌牌堆为:"+Arrays.toString(b));
//		System.out.println("--------------发牌结束---------------");
//	
//}
//	for(int j=1;j<PlayerSet.size();j=j+2){
//	
//	
//	
//	if(a>b&&Max<a){
//		Max=a;
//		System.out.println("continue compare");
//	}else {
//		continue;
//	}
//}
}
