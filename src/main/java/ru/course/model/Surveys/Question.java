package ru.course.model.Surveys;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        long id;


        String description;

        @OneToMany (fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
        List<Option> option;

        int numberOfOptions;

        public List<Option> getOption() {
                return option;
        }

        public void setOption(List<Option> option) {
                this.option = option;
        }

        public int getNumberOfOptions() {
                return numberOfOptions;
        }

        public void setNumberOfOptions(int numberOfOptions) {
                this.numberOfOptions = numberOfOptions;
        }

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }





        public Question() {
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public List<Option> getOptionList() {
                return option;
        }

        public void setOptionList(List<Option> optionList) {
                this.option = optionList;
        }
}
