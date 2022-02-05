package solovei.sprinsequritydemo.rest;

import org.springframework.web.bind.annotation.*;
import solovei.sprinsequritydemo.model.Developer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestControllerV1 {

    private List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L,"Ivan", "Ivanov"),
            new Developer(2L,"Sergei","Sergeev"),
            new Developer(3L,"Petr","Petrov")
    ).collect(Collectors.toList());

    @GetMapping
    public List<Developer> getAll(){
        return DEVELOPERS;
    }
    @GetMapping("/{id}")
    public Developer getById(@PathVariable Long id){
        return DEVELOPERS.stream().filter(developer -> developer.getId().equals(id))
                .findFirst().orElse(null);
    }

    @PostMapping
    public Developer create(@RequestBody Developer developer){
        DEVELOPERS.add(developer);
        return developer;
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        DEVELOPERS.removeIf(developer->developer.getId().equals(id));
    }
}
