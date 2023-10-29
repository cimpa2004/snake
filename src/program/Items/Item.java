package program.Items;

public abstract class  Item {
    protected int xcord;
    protected int ycord;
    protected int rarity; //0, 1, 2 //higher values mean that its more rare -> 0 is the most common
    protected int sizeadded;
    protected int pointsadded;
    protected int speedmodifyer; //in percent default 0% -> 0% will be added
    protected int speedchangedtime;//in sec

    public Item(int x, int y){
        xcord = x;
        ycord = y;
    }
    public int getXcord(){
        return xcord;
    }
    public int getYcord(){
        return ycord;
    }
    public int getRarity(){
        return rarity;
    }
    public void setXcord(int x){
        xcord = x;
    }
    public void setYcord(int y){
        ycord = y;
    }

}
