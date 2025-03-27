package org.ldamler.example.clients;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

public interface DummyClient {

    @GetExchange(url = "/get", accept = MediaType.APPLICATION_JSON_VALUE)
    Object get(@RequestParam("id") String id);

    @PostExchange(url = "/post",
            accept = MediaType.APPLICATION_JSON_VALUE,
            contentType = MediaType.APPLICATION_JSON_VALUE
    )
    Object post(@RequestBody Object body);

    @PutExchange(url = "/put/{id}",
             accept = MediaType.APPLICATION_JSON_VALUE,
             contentType = MediaType.APPLICATION_JSON_VALUE
    )
    Object put(@PathVariable("id") String id, @RequestBody Object body);

    @DeleteExchange(url = "/delete/{id}",
             accept = MediaType.APPLICATION_JSON_VALUE,
             contentType = MediaType.APPLICATION_JSON_VALUE
    )
    void delete(@PathVariable("id") String id);
}
