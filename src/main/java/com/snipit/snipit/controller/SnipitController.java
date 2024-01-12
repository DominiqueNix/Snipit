package com.snipit.snipit.controller;

import org.springframework.web.bind.annotation.RestController;

import com.snipit.snipit.entity.*;
import com.snipit.snipit.payload.SnippetDTO;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/snippets")
public class SnipitController {

    //holds all the snippets
    ArrayList<Snippet> snipets;

    //seeding data to arrayList
    public SnipitController(){
        snipets = new ArrayList<>();

		Snippet s1 = new Snippet(1, "Python", "print('Hello, World!')");
		Snippet s2 = new Snippet(2, "Python", "def add(a, b):\n    return a + b");
		Snippet s3 = new Snippet(3, "Python", "class Circle:\n    def __init__(self, radius):\n        self.radius = radius\n\n    def area(self):\n        return 3.14 * self.radius ** 2");
		Snippet s4 = new Snippet(4, "JavaScript", "console.log('Hello, World!');");
		Snippet s5 = new Snippet(5, "JavaScript", "function multiply(a, b) {\n    return a * b;\n}"); 
		Snippet s6 = new Snippet(6, "JavaScript", "const square = num => num * num;");
		Snippet s7 = new Snippet(7, "Java", "public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}");
		Snippet s8 = new Snippet(8, "Java", "public class Rectangle {\n    private int width;\n    private int height;\n\n    public Rectangle(int width, int height) {\n        this.width = width;\n        this.height = height;\n    }\n\n    public int getArea() {\n        return width * height;\n    }\n}");
		  
		snipets.add(s1);
		snipets.add(s2);
		snipets.add(s3);
		snipets.add(s4);
		snipets.add(s5);
		snipets.add(s6);
		snipets.add(s7);
		snipets.add(s8);
    }

    //get all snippets or can get snippets based on language param
    @GetMapping
    public String getAll(@RequestParam(required = false, defaultValue = "all") String lang) {
        if(!lang.equals("all")) {
            ArrayList<Snippet> langSnips = new ArrayList<>();
            for(Snippet s : snipets) {
                if(s.getLanguage().equalsIgnoreCase(lang)) {
                    langSnips.add(s);
                }
            }
            String results = "";
            for(Snippet s : langSnips) {
                results += s.toString();
            }
            return results;    
        } else {
            String results = "";
            for(Snippet s : snipets) {
                results += s.toString();
            }
            return results; 
        }
        
  }

  // gets one snippet based on the id
  @GetMapping("{id}")
  public String getOneSnippet(@PathVariable int id) {
    Snippet snippet = snipets.get(id-1);
      return snippet.toString();
  }

  //adds a new snippet then returns all snippets
  @PostMapping
  public String createSnippet(@RequestBody SnippetDTO snipitDto) {
      
    int id = snipets.size() + 1;
    String language = snipitDto.getLanguage();
    String code = snipitDto.getCode();

    snipets.add(new Snippet(id, language, code));
      
      return snipets.toString();
  }
  
}
