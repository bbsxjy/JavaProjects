package cardgame.jingyu;

public class CardDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CardSet cs=new CardSet();
		PlayerSet ps=new PlayerSet();
		cs.addSet();
		ps.addPlayer();
		cs.shuffleCards();
		ps.playerGetCards(cs.player1Cards(),cs.player2Cards());
		//ps.player2GetCards(cs.player2Cards());
		ps.GameStart();
		
	}

}
