package calcJB;

public class Power extends Operator {
	@Override
	public Double clac(Double num1, Double num2) {
		return Math.pow(num1, num2);
	}

}
