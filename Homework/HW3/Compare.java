import java.util.*;

/**
 * 08-722 Data Structures for Application Programmers.
 * Comparator withe Comparable/Comparator interface
 *
 * @author Junrong Huang
 */
public class Compare {

    /**
     * Main function, input cards and sort
     */
    public static void main(String[] args) {
        Card card1 = new Card("hearts", 1);
        Card card2 = new Card("diamonds", 1);
        System.out.println(card1.compareTo(card2));
        System.out.println(card1.equals(card2));
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("hearts", 2));
        cards.add(new Card("diamonds", 2));
        cards.add(new Card("spades", 3));
        cards.add(new Card("clubs", 4));
        cards.add(new Card("hearts", 1));
        Collections.sort(cards);
        for (Card c : cards) {
            System.out.println(c);
        }
        System.out.println();
        Collections.sort(cards, new SortbyClub());
        for (Card c : cards) {
            System.out.println(c);
        }
    }

    /**
    * Comparable 
    */
    static class Card implements Comparable<Card> {
        private String suit;
        private int rank;
        public Card(String s, int r) {
            this.suit = s;
            this.rank = r;
        }
        public String getSuit() {
            return this.suit;
        }
        public int getRank() {
            return this.rank;
        }

        @Override
        public String toString() {
            return this.suit + ", " + this.rank;
        }
        @Override
        public int compareTo(Card other) {
            return this.getRank() - other.getRank();
        }
    }

    /**
    * Comparator
    */
    static class SortbyClub implements Comparator<Card> {
        @Override
        public int compare(Card x, Card y) {
            return (byte)x.getSuit().charAt(0) - (byte)y.getSuit().charAt(0);
        }
    }
}

