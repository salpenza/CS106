package xmlvalidator;

public class BasicStringStack implements StringStack {

	private String[] bigStack;
	private int stackP;


	public BasicStringStack() {
		// TODO Auto-generated metohd stub
		stackP = 0;
		bigStack = new String[1000];
	}


	private void resize() {
		String[] newA = new String[bigStack.length];
		System.arraycopy(bigStack, 0, newA, 0, bigStack.length);
		bigStack = newA;
	}


	@Override
	public void push(String item) {
		// TODO Auto-generated method stub
		if (bigStack.length - 2 == stackP) {
			resize();
		}
		bigStack[stackP] = item;
		stackP++;
	}


	@Override
	public String pop() {
		// TODO Auto-generated method stub
		if (stackP == 0)
			return null;
		else {
			stackP--;
			String temp = bigStack[stackP];
			bigStack[stackP] = null;
			return temp;

		}
	}


	@Override
	public String peek(int position) {
		// TODO Auto-generated method stub
		if (stackP - position < 0 || position < 0)
			return null;
		else {
			return bigStack[stackP - position - 1];
		}
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return stackP;
	}

}
