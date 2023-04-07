package com.example.dddemo;

class Yugioh{

    int attackPower;

    int defensePower;

    String name;

    String type;


    public void setAttackPower(int _attackPower){
        attackPower = _attackPower;
    }

    public void setDefensePower(int _defensePower){
        attackPower = _defensePower;
    }

    public void setName(String _name){
        name = _name;
    }
    public void setType(String _type){
        type = _type;
    }

    int getAttackPower()
    {
        return attackPower;
    }

    int getDefensePower()
    {
        return defensePower;
    }

    String getName()
    {
        return name;
    }


    String getType()

    {
        return type;
    }

    public String toString() {
        return "\nHere is the name "+getName()+ "\nHere is the type of the card"+getType() + "\nHere is the defense power " + getDefensePower() +"And here is the attack Power -->\n"+ getAttackPower();
    }

}