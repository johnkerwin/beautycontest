package controllers;

import com.avaje.ebean.Ebean;
import models.Game;
import models.Player;
import play.*;
import play.mvc.*;
import views.html.*;

import java.util.*;


public class Application extends Controller {



    public static Result register(){
        return ok(register.render("hi"));

       // return ok(myregister.render("Please Register"));
    }


    public static Result game(){

        String user = session("connected");
        if(user != null) {
            return ok("Hello " + user);
        } else {
            return redirect("/register");
        }

        //return ok(game.render("hi"));
    }
    public static Result index() {
        //Game game = new Game();

        Game game = Ebean.find(Game.class, 1);


        // game.name = "test"  ;
        // game.save();
        if (game != null) {
            Ebean.delete(game);
        }


        //return ok(index.render("Your new application is ready."));
        return ok(index.render(
                Game.find.all(),
                Player.find.all()
        ));


    }

}
