package ru.course.model.Surveys;

import javax.persistence.*;

@Entity
@Table(name = "option")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String text;
     @ManyToOne (fetch = FetchType.EAGER )
    Category category;

    public String getProxycategoryId() {
        return proxycategoryId;
    }

    public void setProxycategoryId(String proxycategoryId) {
        this.proxycategoryId = proxycategoryId;
    }

    int weight;

     String proxycategoryId;

    public Category getCategory() {
        return category;
    }

    public String getCategoryName(){
        if (this.category == null){
            return null;
        }
        else
        return category.getName();
    }



    public void setCategory(Category category) {
        this.category = category;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Option() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
