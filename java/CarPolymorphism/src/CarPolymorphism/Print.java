package CarPolymorphism;

public class Print {

	static void Shift(int n) {		
		for(int i = 0; i < n; i++){
			System.out.print("  ");
		}		
	}
	static void Symbol(int n, String symbol){
		for(int i = 0; i < n; i++){
			System.out.print(symbol + " ");
		}
		System.out.println();		
	}
}
