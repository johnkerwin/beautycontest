package controllers;

import com.avaje.ebean.Ebean;
import models.Game;
import models.Guess;
import models.Player;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import play.api.libs.json.JsPath;
import play.data.DynamicForm;

import play.data.Form;
import play.libs.Crypto;
import play.libs.Json;
import play.mvc.BodyParser;
import play.*;
import play.mvc.*;
import views.html.*;

import java.util.*;


public class Application extends Controller {



    public static Result guess(){

        //Game game = new Game();
        Game currentGame = Game.getCurrentGame();
        Long id = 1L;
        Player currentPlayer = Player.find.byId(id);
        Guess guess = new Guess();
        guess.game = currentGame;
        guess.value = 12;
        guess.player = currentPlayer;

        return ok("ok");
    }
    public static Result register(){

        String user = session("connected");
        String remote = request().remoteAddress();
        String username = "test";

        response().setCookie("rememberme", Crypto.sign(username) + "-" + username +  '-' + remote, 604800000);


        if (user == null){
            user = "Wow";
        }
        session("connected", "user@gmail.com");
        return ok(user + '-' + remote );



       // return ok(myregister.render("Please Register"));
    }

   // @BodyParser.Of(BodyParser.Json.class)
    public static Result getdata() {
        //JsonNode json = request().body().asJson();
        DynamicForm data = Form.form().bindFromRequest();
        ObjectNode result = Json.newObject();
        String name = data.get("name");

//        if(json == null){
//            return ok("ho there");
//         //return badRequest("empty json"); // PROBLEM: THE JSON IS ALWAYS NULL
//        }
  //      response.setCookie("playlonglivecookie", yourData, "14d");

        result.put("status", "OK");
        result.put("message", name);
        return ok(result);
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
        Game game = new Game();

        //Game game = Ebean.find(Game.class, 1);


         game.name = "test1"  ;
         game.save();
//        if (game != null) {
//            Ebean.delete(game);
//        }

         // return ok("ok");
       // return ok(index.render("Your new application is ready."));
        return ok(index.render(
                Game.find.all(),
                Player.find.all()
        ));


    }

}
