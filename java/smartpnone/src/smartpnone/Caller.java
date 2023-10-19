package smartpnone;

public class Caller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Computer c = new Computer();
		Phone p = new Phone();
		Call[] call = new Call[] {new Call(c), new Call(p), new Call(p.getCallBackReference())};
		for(Call t : call) {
			t.go();
		}
	}

}
interface Iprocess{
	void process();
}
class Computer implements Iprocess{
	public void process(){
		System.out.println("the operating system is activated");
	}
}
class Phone implements Iprocess{
	public void process(){
		System.out.println("the network is configured");
	}
	class SmartPhone extends Computer{
		public void process() {
			super.process();
			Phone.this.process();			
		}
	}
	Iprocess getCallBackReference() {
		return new SmartPhone();
	}
}
class Call{
	private Iprocess callbackReference;
	Call(Iprocess cbh){ callbackReference = cbh; }
	void go() {callbackReference.process();}
}