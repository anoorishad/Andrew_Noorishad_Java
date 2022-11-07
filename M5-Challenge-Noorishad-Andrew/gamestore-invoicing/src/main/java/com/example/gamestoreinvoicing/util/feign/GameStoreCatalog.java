package com.example.gamestoreinvoicing.util.feign;

import com.example.gamestoreinvoicing.model.Console;
import com.example.gamestoreinvoicing.model.Game;
import com.example.gamestoreinvoicing.model.TShirt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="gamestore-catalog")
public interface GameStoreCatalog {
    @GetMapping(value="/console/{consoleId}")
    public Console getConsoleById(@PathVariable long consoleId);
    @GetMapping(value="/game/{gameId}")
    public Game getGameById(@PathVariable long gameId);
    @GetMapping(value="/tshirt/{id}")
    public TShirt getTShirtById(@PathVariable long id);
}
