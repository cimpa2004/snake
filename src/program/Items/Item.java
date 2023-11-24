package program.Items;

public abstract class  Item {
    protected int xcord;
    protected int ycord;


    protected int pointsadded;
    protected double speedmodifyer;
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

    public int getPointsadded(){return pointsadded;}
    public double getSpeedmodifyer(){return speedmodifyer;}
    public void setXcord(int x){
        xcord = x;
    }
    public void setYcord(int y){
        ycord = y;
    }

}
