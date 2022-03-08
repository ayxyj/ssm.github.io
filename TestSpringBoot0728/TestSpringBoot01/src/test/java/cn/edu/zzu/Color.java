package cn.edu.zzu;

public enum Color implements Behaviour{
//    RED , GREEN , BLANK , YELLOW
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);

    private String color ;
    private Integer index ;

    Color(String color, Integer index) {
        this.color = color;
        this.index = index;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public void print() {
        System.out.println(this.color+":"+this.index);
    }

    @Override
    public String getInfo() {
        return this.color;
    }
}

