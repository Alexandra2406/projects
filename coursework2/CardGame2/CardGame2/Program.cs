using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Linq.Expressions;
using System.Net.Http.Headers;
using System.Runtime.CompilerServices;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;

namespace CardGame2
{

    #region Factory Method

    public enum CardNumber
    {
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine,
        Ten,
        Jack,
        Queen,
        King,
        Ace
    }

    public enum CardSuit
    {
        Hearts,
        Diamonds,
        Clubs,
        Spades
    }
    [Serializable]
    public abstract class Card
    {
        protected CardNumber number;
        protected CardSuit suit;

        public Card(CardNumber number, CardSuit suit)
        {
            this.number = number;
            this.suit = suit;
        }
        public abstract void Play();
        public abstract void Print();
        public abstract bool CompareNumber(Card card);
        public abstract bool EqualsSuit(Card card);
        public abstract bool EqualsNumber(Card card);
        public CardNumber getNumber() { return number; }
        public CardSuit getSuit() { return suit; }
        public override string ToString()
        {
            return number.ToString() + " " + suit.ToString();
        }
    }
    [Serializable]
    public class NumberCard : Card
    {
        public NumberCard(CardNumber number, CardSuit suit) : base(number, suit)
        {
        }

        public override bool CompareNumber(Card card)
        {
            if (card is SpecialCard || card is SpecialAbilityCardDecorator)
                return false;
            return this.number > card.getNumber();
        }

        public override bool EqualsNumber(Card card)
        {
            return this.number == card.getNumber();
        }

        public override bool EqualsSuit(Card card)
        {
            if (card is SpecialCard || card is SpecialAbilityCardDecorator)
                return false;
            return this.suit == card.getSuit();
        }

        public override void Play()
        {
            Console.WriteLine($"Playing number card: {this.number} of {suit}");
        }

        public override void Print()
        {
            Console.WriteLine(this.ToString());
        }
    }
    [Serializable]
    public class SpecialCard : Card
    {
        private string specialAbility;

        public SpecialCard(string specialAbility, CardNumber number, CardSuit suit) : base(number, suit)
        {
            this.specialAbility = specialAbility;
        }

        public override bool CompareNumber(Card card)
        {
            if (card is NumberCard)
                return true;
            return this.number > card.getNumber();
        }

        public override bool EqualsNumber(Card card)
        {
            return this.number == card.getNumber();
        }

        public override bool EqualsSuit(Card card)
        {
            if (card is NumberCard)
                return true;
            return this.suit == card.getSuit();
        }

        public override void Play()
        {
            Console.WriteLine($"Playing special card with ability: {specialAbility}");
        }

        public override void Print()
        {
            Console.WriteLine(this.ToString());
        }

        public override string ToString()
        {
            return base.ToString() + " " + specialAbility;
        }
    }

    public abstract class CardFactory
    {
        public abstract Card CreateCard();
    }

    public class NumberCardFactory : CardFactory
    {
        private CardNumber number;
        private CardSuit suit;

        public NumberCardFactory(CardNumber number, CardSuit suit)
        {
            this.number = number;
            this.suit = suit;
        }

        public override Card CreateCard()
        {
            return new NumberCard(number, suit);
        }
    }

    public class SpecialCardFactory : CardFactory
    {
        private string specialAbility;
        private CardNumber number;
        private CardSuit suit;

        public SpecialCardFactory(string specialAbility, CardNumber number, CardSuit suit)
        {
            this.specialAbility = specialAbility;
            this.number = number;
            this.suit = suit;
        }

        public override Card CreateCard()
        {

            return new SpecialAbilityCardDecorator(new SpecialCard(specialAbility, number, suit));
        }
    }

    #endregion

    #region Observer

    public interface IObserver
    {
        void Update();
    }

    public interface IObservable
    {
        void AddObserver(IObserver observer);
        void RemoveObserver(IObserver observer);
        void NotifyObservers();
    }

    public class Game : IObservable
    {
        private List<IObserver> observers;
        private List<Player> players;
        private bool gameOver;
        private int currentPlayerIndex = 0,
                 cardMovesPlayerIndex = 0,
                 beatesCardPlayerIndex = 0;

        public Game()
        {
            observers = new List<IObserver>();
            players = new List<Player>();
            gameOver = false;
        }

        public void AddObserver(IObserver observer)
        {
            observers.Add(observer);
        }

        public void RemoveObserver(IObserver observer)
        {
            observers.Remove(observer);
        }

        public void NotifyObservers()
        {
            foreach (IObserver observer in observers)
            {
                observer.Update();
            }
        }

        public void AddPlayer(Player player)
        {
            players.Add(player);
            AddObserver(player); 
        }

        public void RemovePlayer(Player player)
        {
            players.Remove(player);
            RemoveObserver(player); 
        }

        public void GameOver()
        {
            gameOver = true;
            NotifyObservers();
        }

        public bool IsGameOver()
        {
            return gameOver;
        }

        public List<Player> GetPlayers()
        {
            return players;
        }
        public void SetPlayers(List<Player> players)
        {
            this.players = players;
        }
        public int GetCurrentIndexPlayer()
        {
            return currentPlayerIndex;
        }
        public int GetBeatesPlayerIndex()
        {
            return beatesCardPlayerIndex;
        }
        public void SaveGameState(string filename)
        {
            try
            {
                using (FileStream stream = new FileStream(filename, FileMode.Create))
                {
                    BinaryFormatter formatter = new BinaryFormatter();
                    formatter.Serialize(stream, players);
                }
                Console.WriteLine("Game state saved successfully!");
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error saving game state: " + ex.Message);
            }
        }

        public void LoadGameState(string filename)
        {
            try
            {
                using (FileStream stream = new FileStream(filename, FileMode.Open))
                {
                    BinaryFormatter formatter = new BinaryFormatter();
                    players = (List<Player>)formatter.Deserialize(stream);
                }
                Console.WriteLine("Game state loaded successfully!");
                NotifyObservers();
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error loading game state: " + ex.Message);
            }
        }

        private void IncreaseBCPI(int i)
        {
            beatesCardPlayerIndex = i + 1;
            if (beatesCardPlayerIndex >= players.Count)
            {
                beatesCardPlayerIndex = 0;
            }
        }
        private void IncreaseCIP()
        {
            currentPlayerIndex = beatesCardPlayerIndex + plus;
            if (currentPlayerIndex >= players.Count)
            {
                currentPlayerIndex = 0;
            }
        }
        public Player SelectPlayer()
        {
            Player selectedPlayer = null;
            CardNumber smallestTrumpValue = CardNumber.Ace;

            foreach (Player player in players)
            {
                List<Card> cards = player.GetRemainingCards();
                foreach (Card card in cards)
                {
                    if (card is SpecialCard || card is SpecialAbilityCardDecorator)
                    {
                        if (card != null && card.getNumber() < smallestTrumpValue)
                        {
                            smallestTrumpValue = card.getNumber();
                            selectedPlayer = player;
                        }
                    }
                }

            }

            return selectedPlayer;
        }
        private int plus = 0;
        private void Beat(Player player, Card move)
        {
            Console.WriteLine($"Player {player.GetName()} is beating...");
            move.Play();
            List<Card> cards = player.GetRemainingCards();
            Console.WriteLine("Available cards : ");
            foreach (Card card in cards)
            {
                card.Print();
            }
            Console.WriteLine("Select card that is bigger than card you should beat. If you could not beat it, type -1");
            int i = Convert.ToInt32(Console.ReadLine());
            if (i == -1)
            {
                foreach (Card card in player.GetUsedCards())
                {
                    player.AddCardToHand(card);
                }
                foreach (Card card in player.GetCardsToBeat())
                {
                    player.AddCardToHand(card);
                }
                player.CleanUsedCards();
                player.CleanCardsToBeat();
                plus = 1;
            }
            else
            {
                cards[i].Play();
                Beat(player, move, cards[i]);
            }

        }
        private void Beat(Player player, Card move, Card beat)
        {
            if (!beat.CompareNumber(move) || !beat.EqualsSuit(move))
            {
                Console.WriteLine("Invalid card");
                Beat(player, move);
            }
            else
            {
                Console.WriteLine("Card is beaten");
                player.AddUsedCard(move);
                player.AddUsedCard(beat);
                player.RemoveCardToBeat(move);
                player.RemoveCardFromHand(beat);
            }
        }
        private Card ChooseCardToMove(PlayerCardSelectionMechanism playerCardSelectionMechanism)
        {
            return playerCardSelectionMechanism.Play();
        }
        private Card ThrowUp(Player player)
        {
            List<Card> cards = player.GetRemainingCards();
            Console.WriteLine("Available cards : ");
            foreach (Card card in cards)
            {
                card.Print();
            }
            Console.WriteLine("Used cards : ");
            foreach (Card card in players[beatesCardPlayerIndex].GetUsedCards())
            {
                card.Print();
            }
            Console.WriteLine($"Do you(player {player.GetName()}) want to throw up? Enter Yes/No");
            string want = Console.ReadLine();
            if (want.Equals("Yes") || want.Equals("yes") || want.Equals("1"))
            {
                Console.WriteLine("Choose a card to throw up :");
                int i = Convert.ToInt32(Console.ReadLine());
                if (ValideCard(cards[i], players[beatesCardPlayerIndex].GetUsedCards()))
                {
                    player.RemoveCardFromHand(cards[i]);
                    return cards[i];
                }
                else
                {
                    Console.WriteLine("Invalid Card");
                    ThrowUp(player);
                }
            }
            else
                return null;
            return null;
        }
        private bool ValideCard(Card card, List<Card> cards)
        {
            foreach (Card card1 in cards)
            {
                if (card.EqualsNumber(card1)) return true;
            }
            return false;
        }
        private void Throws()
        {
            foreach (Player player in players)
            {
                if (players[beatesCardPlayerIndex].GetUsedCards().Count < 12 && players[beatesCardPlayerIndex].GetUsedCards().Count != 0 && players[beatesCardPlayerIndex] != player)
                {
                    Card thrown = ThrowUp(player);
                    if (thrown != null)
                    {
                        Beat(players[beatesCardPlayerIndex], thrown);
                    }
                }
            }
        }
        private void DealCards(Deck deck)
        {
            foreach (Player player in players)
            {
                while (player.GetRemainingCards().Count < 6 && deck.Length() != 0)
                {
                    Card card = deck.DrawCard();
                    player.AddCardToHand(card);
                }
            }
        }
        private void TryUpdatePlayers()
        {
            foreach (Player player in players)
            {
                if (player.GetRemainingCards().Count == 0)
                {
                    RemovePlayer(player);
                    player.Update();
                }
            }
        }
        private bool CheckIsGameOver()
        {
            return players.Count <= 1;
        }

        public void MakeMove(PlayerCardSelectionMechanism playerCardSelectionMechanism, Deck deck)
        {
            cardMovesPlayerIndex = players.IndexOf(playerCardSelectionMechanism.GetPlayer());
            IncreaseBCPI(cardMovesPlayerIndex);
            Card move = ChooseCardToMove(playerCardSelectionMechanism);
            players[beatesCardPlayerIndex].AddCardToBeat(move);
            Beat(players[beatesCardPlayerIndex], move);
            if (players[beatesCardPlayerIndex].GetUsedCards().Count != 0)
            {
                Throws();
            }
            IncreaseCIP();
            plus = 0;
            DealCards(deck);
            TryUpdatePlayers();
            if (CheckIsGameOver())
            {
                GameOver();
            }
        }
    }
    [Serializable]
    public class Player : IObserver
    {
        private string name;
        private Card lastPlayedCard;
        private List<Card> remainingCards;
        private List<Card> cardsToBeat;
        private List<Card> usedCards;

        public Player(string name)
        {
            this.name = name;
            this.lastPlayedCard = null;
            this.remainingCards = new List<Card>();
            this.cardsToBeat = new List<Card>();
            this.usedCards = new List<Card>();
        }

       public void Update()
        {
            Console.WriteLine($"{name} has been eliminated from the game!");
        }

        public void PlayCard(Card card)
        {
            lastPlayedCard = card;
            Console.WriteLine($"{name} plays {card.getNumber()} of {card.getSuit()}");
            this.remainingCards.Remove(lastPlayedCard);
        }
        public string GetName()
        {
            return name;
        }
        public void AddUsedCard(Card card)
        {
            usedCards.Add(card);
        }
        public List<Card> GetUsedCards()
        {
            return usedCards;
        }
        public void CleanUsedCards()
        {
            usedCards.Clear();
        }
        public void AddCardToHand(Card card)
        {
            remainingCards.Add(card);
        }
        public void RemoveCardFromHand(Card card)
        {
            remainingCards.Remove(card);
        }
        public void SetRemainingCards(List<Card> remainingCards)
        {
            this.remainingCards = remainingCards;
        }

        public List<Card> GetRemainingCards()
        {
            return remainingCards;
        }
        public void AddCardToBeat(Card card)
        {
            cardsToBeat.Add(card);
        }
        public void RemoveCardToBeat(Card card)
        {
            cardsToBeat.Remove(card);
        }
        public void SetCardsToBeat(List<Card> cardsToBeat)
        {
            this.cardsToBeat = cardsToBeat;
        }
        public void CleanCardsToBeat()
        {
            cardsToBeat.Clear();
        }
        public List<Card> GetCardsToBeat()
        {
            return cardsToBeat;
        }

    }

    #endregion

    #region Command

    public interface ICommand
    {
        void Execute();
        void Undo();
    }

    public class StartGameCommand : ICommand
    {
        private Game game;
        private int numberOfPlayers;

        public StartGameCommand(Game game)
        {
            this.game = game;
        }
        private void CreatePlayers()
        {
            for (int i = 0; i < numberOfPlayers; i++)
            {
                Console.WriteLine($"Enter name of {i} player");
                string name = Console.ReadLine();
                game.AddPlayer(new Player(name));
            }
        }
        public void Execute()
        {
            Console.WriteLine("Starting the game...");
            do
            {
                Console.WriteLine("Enter the number of players");
                numberOfPlayers = Convert.ToInt32(Console.ReadLine());
            }
            while (numberOfPlayers < 2 || numberOfPlayers > 6);
            CreatePlayers();
        }

        public void Undo()
        {
            Console.WriteLine("Undoing start game command...");
            game.SetPlayers(null);
        }
    }

    public class ShuffleDeckCommand : ICommand
    {
        private Deck deck;
        private Deck lastDeck;

        public ShuffleDeckCommand(Deck deck)
        {
            this.deck = deck;
        }

        public void Execute()
        {
            lastDeck = deck;
            deck.Shuffle();
        }

        public void Undo()
        {
            Console.WriteLine("Undoing shuffle deck command...");
            deck = lastDeck;
        }
    }

    public class DealCardsCommand : ICommand
    {
        private Game game;
        private Deck deck;

        public DealCardsCommand(Game game, Deck deck)
        {
            this.game = game;
            this.deck = deck;
        }

        public void Execute()
        {
            Console.WriteLine("Dealing cards to players...");
            foreach (Player player in game.GetPlayers())
            {
                for (int i = 0; i < 6; i++)
                {
                    Card card = deck.DrawCard();
                    player.AddCardToHand(card);
                }
            }
        }

        public void Undo()
        {
            Console.WriteLine("Undoing deal cards command...");
            foreach (Player player in game.GetPlayers())
            {
                player.SetRemainingCards(null);
            }
        }
    }

    public class PlayGameCommand : ICommand
    {
        private Game game;
        private Deck deck;
        public PlayGameCommand(Game game, Deck deck)
        {
            this.game = game;
            this.deck = deck;
        }

        public void Execute()
        {
            Player player = game.SelectPlayer();
            ICardSelectionStrategy strategy;
            while (!game.IsGameOver())
            {
                Console.WriteLine("Player " + player.GetName() + " can move");
                Console.WriteLine("Choose card selection strategy: \n 1 - random \n 2 - lowest card \n other - choose independently");
                int i = Convert.ToInt32(Console.ReadLine());
                switch (i)
                {
                    case 1:
                        {
                            strategy = new RandomCardSelectionStrategy();
                            break;
                        }
                    case 2:
                        {
                            strategy = new LowestCardSelectionStrategy();
                            break;
                        }
                    default:
                        {
                            strategy = new ByNumberCardSelectionStrategy();
                            break;
                        }
                }
                PlayerCardSelectionMechanism playerCardSelectionMechanism = new PlayerCardSelectionMechanism(player, strategy);
                game.MakeMove(playerCardSelectionMechanism, deck);
                game.GetPlayers()[game.GetBeatesPlayerIndex()].CleanUsedCards();
                player = game.GetPlayers()[game.GetCurrentIndexPlayer()];
                Undo();
            }
        }

        public void Undo()
        {
            Console.WriteLine("If you want to stop game print STOP");
            string stop = Console.ReadLine();
            if (stop.Equals("STOP"))
            {
                Save();
                game.GameOver();
                Console.WriteLine("Game Stop");
                Load();
            }
        }
        public void Save()
        {
            Console.WriteLine("If you want to save game print SAVE");
            string save = Console.ReadLine();
            if (save.Equals("SAVE") || save.Equals("save"))
            {
                // Збереження поточного стану гри
                game.SaveGameState("gamestate.dat");
            }
            else
            {
                Console.WriteLine("game isn`t saved");
            }
        }
        public void Load()
        {
            Console.WriteLine("If you want to load game print LOAD");
            string save = Console.ReadLine();
            if (save.Equals("LOAD") || save.Equals("load"))
            {
                // Перезапуск програми та завантаження збереженого стану гри
                game = new Game();
                game.LoadGameState("gamestate.dat");
                Execute();
            }
            else
            {
                Console.WriteLine("game isn`t loaded");
            }
        }
    }
    public class GameCommandFacade
    {
        private StartGameCommand startGameCommand;
        private ShuffleDeckCommand shuffleDeckCommand;
        private DealCardsCommand dealCardsCommand;
        private PlayGameCommand playGameCommand;

        public GameCommandFacade(Game game, Deck deck)
        {
            startGameCommand = new StartGameCommand(game);
            shuffleDeckCommand = new ShuffleDeckCommand(deck);
            dealCardsCommand = new DealCardsCommand(game, deck);
            playGameCommand = new PlayGameCommand(game, deck);
        }

        public void StartGame()
        {
            startGameCommand.Execute();
        }

        public void ShuffleDeck()
        {
            shuffleDeckCommand.Execute();
        }

        public void DealCards()
        {
            dealCardsCommand.Execute();
        }
        public void Play()
        {
            playGameCommand.Execute();
        }
    }

    #endregion

    #region Template Method

    public abstract class GameTemplate
    {
        protected abstract void InitializeGame();
        protected abstract void PlayGame();
        protected abstract void EndGame();

        public void Run()
        {
            InitializeGame();
            PlayGame();
            EndGame();
        }
    }

    public class CardGame : GameTemplate
    {
        GameFacade gameFacade;
        protected override void InitializeGame()
        {
            Console.WriteLine("Initializing the card game...");
            gameFacade = new GameFacade();
            gameFacade.StartGame();
            gameFacade.ShuffleDeck();
            gameFacade.DealCards();
        }

        protected override void PlayGame()
        {
            Console.WriteLine("Playing the card game...");
            gameFacade.Play();
        }

        protected override void EndGame()
        {
            Console.WriteLine("Ending the card game...");
        }
    }

    #endregion

    #region Facade

    public class Deck
    {
        private List<Card> cards;
        private CardSuit trump;

        public Deck()
        {
            Random rnd = new Random();
            Array values = typeof(CardSuit).GetEnumValues();
            this.trump = (CardSuit)values.GetValue(rnd.Next(0, values.Length));
            cards = CreateDeck();
        }

        public int Length()
        {
            return cards.Count;
        }

        private List<Card> CreateDeck()
        {
            List<Card> deck = new List<Card>();

            // Adding cards to the deck using the factory method
            foreach (CardNumber cardNumber in typeof(CardNumber).GetEnumValues())
            {
                CardFactory specialCardFactory = new SpecialCardFactory("Trump", cardNumber, this.trump);
                deck.Add(specialCardFactory.CreateCard());
            }

            var cardSuits = typeof(CardSuit).GetEnumValues().Cast<CardSuit>().Where(item => item != this.trump).ToArray();
            foreach (CardSuit cardSuit in cardSuits)
            {
                foreach (CardNumber cardNumber in typeof(CardNumber).GetEnumValues())
                {
                    CardFactory numberCardFactory = new NumberCardFactory(cardNumber, cardSuit);
                    deck.Add(numberCardFactory.CreateCard());
                }
            }

            return deck;
        }

        public void Shuffle()
        {
            Console.WriteLine("Shuffling the deck...");

            Random rand = new Random();

            for (int i = this.cards.Count - 1; i >= 1; i--)
            {
                int j = rand.Next(i + 1);

                Card tmp = this.cards[j];
                this.cards[j] = this.cards[i];
                this.cards[i] = tmp;
            }
        }

        public Card DrawCard()
        {
            if (cards.Count > 0)
            {
                Card card = cards[0];
                cards.RemoveAt(0);
                return card;
            }
            return null;
        }
    }

    public class GameFacade
    {
        private GameCommandFacade commandFacade;
        private Game game;
        private Deck deck;

        public GameFacade()
        {
            game = new Game();
            deck = new Deck();
            commandFacade = new GameCommandFacade(game, deck);
        }

        public void StartGame()
        {
            commandFacade.StartGame();
        }

        public void ShuffleDeck()
        {
            commandFacade.ShuffleDeck();
        }

        public void DealCards()
        {
            commandFacade.DealCards();
        }
        public void Play()
        {
            commandFacade.Play();
        }
       
    }

    #endregion

    #region Strategy
    public interface ICardSelectionStrategy
    {
        Card SelectCard(Player player);
    }

    public class RandomCardSelectionStrategy : ICardSelectionStrategy
    {
        private Random random;

        public RandomCardSelectionStrategy()
        {
            random = new Random();
        }

        public Card SelectCard(Player player)
        {
            List<Card> cards = player.GetRemainingCards();
            int index = random.Next(0, cards.Count - 1);
            Card card = cards[index];
            player.RemoveCardFromHand(cards[index]);
            return card;
        }
    }

    public class LowestCardSelectionStrategy : ICardSelectionStrategy
    {
        public Card SelectCard(Player player)
        {
            List<Card> cards = player.GetRemainingCards();
            Card lowestCard = null;
            foreach (Card card in cards)
            {
                if (lowestCard == null || !card.CompareNumber(lowestCard))
                {
                    lowestCard = card;
                }
            }
            player.RemoveCardFromHand(lowestCard);
            return lowestCard;
        }
    }

    public class ByNumberCardSelectionStrategy : ICardSelectionStrategy
    {
        public Card SelectCard(Player player)
        {
            List<Card> cards = player.GetRemainingCards();
            Console.WriteLine("Available cards : ");
            foreach (Card card in cards)
            {
                card.Print();
            }
            Console.WriteLine();
            Console.WriteLine("Choose a card to make a move :");
            int i = Convert.ToInt32(Console.ReadLine());
            Card carD = cards[i];
            player.RemoveCardFromHand(cards[i]);
            return carD;
        }
    }
    #endregion
   
    #region Bridge
    public abstract class GameMechanism
    {
        protected ICardSelectionStrategy cardSelectionStrategy;

        public GameMechanism(ICardSelectionStrategy cardSelectionStrategy)
        {
            this.cardSelectionStrategy = cardSelectionStrategy;
        }

        public abstract Card Play();
    }

    public class PlayerCardSelectionMechanism : GameMechanism
    {
        private Player player;
        private Card selectedCard;
        public PlayerCardSelectionMechanism(Player player, ICardSelectionStrategy cardSelectionStrategy)
            : base(cardSelectionStrategy)
        {
            this.player = player;
        }

        public override Card Play()
        {
            List<Card> hand = GetPlayerHand();
            this.selectedCard = cardSelectionStrategy.SelectCard(player);
            Console.WriteLine(selectedCard.getNumber() + " " + selectedCard.getSuit() + " was selected");
            return this.selectedCard;
        }
        public Player GetPlayer()
        {
            return player;
        }
        private List<Card> GetPlayerHand()
        {
            return player.GetRemainingCards();
        }
    }
    #endregion

    #region Decorator
    [Serializable]
    public abstract class CardDecorator : Card
    {
        protected Card card;

        public CardDecorator(Card card)
            : base(card.getNumber(), card.getSuit())
        {
            this.card = card;
        }

        public override void Play()
        {
            card.Play();
        }
        public override void Print()
        {
            card.Print();
        }
        public override bool CompareNumber(Card card)
        {
            return this.card.CompareNumber(card);
        }
        public override bool EqualsNumber(Card card)
        {
            return this.card.EqualsNumber(card);
        }
        public override bool EqualsSuit(Card card)
        {
            return this.card.EqualsSuit(card);
        }
    }
    [Serializable]
    public class SpecialAbilityCardDecorator : CardDecorator
    {
        public SpecialAbilityCardDecorator(Card card)
            : base(card)
        {
        }

        public override void Play()
        {
            Console.BackgroundColor = ConsoleColor.Blue;
            Console.ForegroundColor = ConsoleColor.White;
            base.Play();
            Console.ResetColor();
        }
        public override void Print()
        {
            Console.BackgroundColor = ConsoleColor.Blue;
            Console.ForegroundColor = ConsoleColor.White;
            base.Print();
            Console.ResetColor();
        }
    }
    #endregion
   
    public class Program
    {
        public static void Main(string[] args)
        {
            GameTemplate gameTemplate = new CardGame();
            gameTemplate.Run();

            Console.ReadLine();
        }
    }
}