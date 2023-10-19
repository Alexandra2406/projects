package enums;
import java.time.*;  

public enum YearMonthh {
	January(YearMonth.of(2023, 1)), 
	February(YearMonth.of(2023, 2)), 
	March(YearMonth.of(2023, 3)), 
	April(YearMonth.of(2023, 4)), 
	May(YearMonth.of(2023, 5)), 
	June(YearMonth.of(2023, 6)), 
	July(YearMonth.of(2023, 7)), 
	August(YearMonth.of(2023, 8)), 
	September(YearMonth.of(2023, 9)), 
	October(YearMonth.of(2023, 10)), 
	November(YearMonth.of(2023, 11)), 
	December(YearMonth.of(2023, 12));
	YearMonth ym;
	YearMonthh(YearMonth ym){
		this.ym = ym;
	}
	public String toString() {
		return this.ym.toString();
	}	
}