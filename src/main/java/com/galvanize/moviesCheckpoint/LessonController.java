package com.galvanize.moviesCheckpoint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    private final LessonRepository repository;
    public LessonController(LessonRepository repository){
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson){
        return this.repository.save(lesson);
    }

    @GetMapping("/{id}")
    public Optional<Lesson> read(@PathVariable Long id){
        return this.repository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.repository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Object patch(@PathVariable Long id, @RequestBody Lesson body){
        Optional<Lesson> lessonToUpdate = this.repository.findById(id);
        if (lessonToUpdate.isEmpty()) {
            return "Could not find with that id";
        }
        Lesson lesson = lessonToUpdate.get();
        lesson.setDeliveredOn(body.getDeliveredOn());
        lesson.setTitle(body.getTitle());
        this.repository.save(lesson);
        return this.repository.findById(id);
    }

    @GetMapping("/find/{title}")
    public Lesson getByTitle(@PathVariable String title){
        return this.repository.findByTitle(title);
    }

//    @GetMapping("/find/between")
//    public Lesson getByTitle(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2){
//        return this.repository.findByDeliveredOnBetween(date1, date2);
//    }

    @GetMapping("/find/between")
    public List<Lesson> getByTitle(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2){
        return this.repository.findByDeliveredOnBetween(date1, date2);
    }
}
