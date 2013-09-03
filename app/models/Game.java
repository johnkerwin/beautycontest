package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import java.util.List;

@Entity
public class Game extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String name;

    public boolean done;

    @OneToMany
    public Guess guesses;



    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date dueDate = new Date();

    public static Finder<Long,Game> find = new Finder<Long,Game>(
            Long.class, Game.class
    );

    public static Game getCurrentGame(){
        List<Game> games = Game.find.all();

//        List<Game> tasks = find.where()
//                .ilike("name", "%coco%")
//                .orderBy("dueDate asc")
//                .findPagingList(25)
//                .getPage(1);
        return  games.get(0);
    }

    public static Integer getAvg(){
        return Game.find.findRowCount();
    }


}