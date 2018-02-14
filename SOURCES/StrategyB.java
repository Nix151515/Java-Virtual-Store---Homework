
public class StrategyB implements Strategy {

	@Override
	public Item execute(WishList wishList) {
		return wishList.executeStrategy(this);
	}

	public String toString()
	{
		return "Strategy B";
	}
}
