package models;


import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Player extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String name;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date createDate = new Date();

    public static Finder<Long,Player> find = new Finder<Long,Player>(
            Long.class, Player.class
    );

}