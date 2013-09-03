package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "guess",
        uniqueConstraints = @UniqueConstraint(columnNames = "game_id,player_id")
)
public class Guess extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public Integer value;

    @ManyToOne(cascade=CascadeType.ALL)
    public Game game;


    @ManyToOne(cascade=CascadeType.ALL)
    public Player player;



    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date dueDate = new Date();

    public static Finder<Long, Guess> find = new Finder<Long, Guess>(
            Long.class, Guess.class
    );

}