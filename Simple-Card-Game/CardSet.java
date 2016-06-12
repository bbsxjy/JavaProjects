package cardgame.jingyu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cardgame.jingyu.CardSet.card;
import cardgame.jingyu.PlayerSet.player;




public class CardSet {
	public List  <card>CardSet;
	
	public CardSet(){
		this.CardSet =new ArrayList<card>();
	}
	
	public class card{
		String huase;
		String number;
		public card(String huase,String number){
			this.huase=huase;
			this.number=number;
		}
	}
	
	
	
	public int Huase(String color){
		int value=0;
		switch(color){
		case "黑桃":value=4;break;
		case "红桃":value=3;break;
		case "梅花":value=2;break;
		case "方片":value=1;break;
		}
		return value;
	}
	
	public int number(String numb){
		int value = 0;
		switch(numb){
		case "3":value=3; break;
		case "4":value=4;break;
		case "5":value=5;break;
		case "6":value=6;break;
		case "7":value=7;break;
		case "8":value=8;break;
		case "9":value=9;break;
		case "10":value=10;break;
		case "J":value=11;break;
		case "Q":value=12;break;
		case "K":value=13;break;
		case "A":value=14;break;
		case "2":value=15;break;
		}
		return value;
	}
	
	public void addSet(){
		System.out.println("----------准备扑克牌中----------");
		String color[]={"黑桃","红桃","梅花","方片"};
		String numb[]={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		//StringBuilder Tset =new StringBuilder();
		//String []Tset2=new String[52];
		//ArrayList <String> Tset2= new ArrayList<String>();
		for(int i =0  ; i<color.length; i++){
			for(int j =0; j<numb.length;j++){
				//Tset.append(color[i]+numb[j]);
				//Tset2=(color[i]+numb[j]);
				card cr=new card(color[i],numb[j]);
				CardSet.add(cr);
			}
		}	
		
		for (card string : CardSet) {
			card cs=(card)string;
			System.out.println(":"+cs.huase+cs.number);
		}
		System.out.println("---------准备扑克牌结束---------");
	}
	
	public void shuffleCards(){
		System.out.println("----------洗牌中----------");
		Collections.shuffle(CardSet);
//		for (card string : CardSet) {
//			card cs=(card)string;
//			System.out.println(":"+cs.huase+cs.number);
//		}
		System.out.println("-----------洗牌结束------------");
		
	}
	
	public String[] player1Cards(){
		System.out.println("--------------开始发牌---------------");
		String [] playercard1={"", "" ,"","","",""};
	
		for(int i =0;i<3;i=i+2){		
		card pcard=CardSet.get(i);	
		playercard1[i]=pcard.huase+pcard.number;
		}
		
		
		return playercard1;
		
	}
	
	public String[] player2Cards(){
		
		String []playercard2={"", "" ,"","","",""};
		for(int i =1;i<4;i=i+2){
		card pcard2=CardSet.get(i);
		playercard2[i]=pcard2.huase+pcard2.number;
		}
		return playercard2;
		
	}
	
	





}




	


	

	
	
	
	
	
	
	

	



//for(int i =0  ; i<numb.length; i++){
//if(numb[i]=="J")
//	value[i]=11;
//if(numb[i]=="Q")
//	value[i]=12;
//if(numb[i]=="K")
//	value[i]=13;
//if(numb[i]=="A")
//	value[i]=14;
//if(numb[i]=="2")
//	value[i]=15;
//	}
//public void forEa(){
//System.out.println("牌的集合为：");
//for (card card : CardSet) {
//	System.out.println(" "+card.huase+":"+card.number);
//}
//}
