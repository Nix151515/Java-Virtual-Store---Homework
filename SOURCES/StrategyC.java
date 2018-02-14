
public class StrategyC implements Strategy {

	@Override
	public Item execute(WishList wishList) {
		return wishList.executeStrategy(this);
		
	}
	public String toString()
	{
		return "Strategy C";
	}

}
