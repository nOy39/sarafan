package org.letscode.restfull.a2lpo.sarafan.controller;

import org.letscode.restfull.a2lpo.sarafan.exceptions.NotFoundExceptions;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "message")
public class MessageController {
    private int counter = 6;
    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>(){{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text","First Message");}});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text","Second Message");}});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text","Third Message");}});
        add(new HashMap<String, String>() {{ put("id", "4"); put("text","Fourth Message");}});
        add(new HashMap<String, String>() {{ put("id", "5"); put("text","Fifth Message");}});

    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping(value = "{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getMessage(id);
    }

    @PostMapping()
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));

        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> messageFromDb = getMessage(id);

        messageFromDb.putAll(message);
        messageFromDb.put("id", id);

        return messageFromDb;
    }


    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> messageFromDb = getMessage(id);
        messages.remove(messageFromDb);
    }


    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(messages -> messages.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundExceptions::new);
    }
}
