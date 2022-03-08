package cn.edu.zzu.entity;

import java.util.Date;
import java.util.Objects;

public class Items {
    private Integer id;
    private String  name;
    private Integer price;
    private String pic;
    private Date createtime;
    private String detail;
    public Items(Integer id , String name ,Integer price ,String pic ,Date createtime ,String detail){
        this.id = id;
        this.name = name ;
        this.price = price;
        this.pic  = pic ;
        this.createtime = createtime;
        this.detail = detail;
    }
    public Items(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", pic='" + pic + '\'' +
                ", createtime=" + createtime +
                ", detail='" + detail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return Objects.equals(id, items.id) && Objects.equals(name, items.name) && Objects.equals(price, items.price) && Objects.equals(pic, items.pic) && Objects.equals(createtime, items.createtime) && Objects.equals(detail, items.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, pic, createtime, detail);
    }
}
