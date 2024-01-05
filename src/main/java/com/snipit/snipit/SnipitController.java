package com.snipit.snipit;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.snipit.snipit.SnipitModel;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class SnipitController {

    ArrayList<SnipitModel> snipets;

    public SnipitController(){
        snipets = new ArrayList<>();

		SnipitModel s1 = new SnipitModel(1, "Python", "print('Hello, World!')");
		SnipitModel s2 = new SnipitModel(2, "Python", "def add(a, b):\n    return a + b");
		SnipitModel s3 = new SnipitModel(3, "Python", "class Circle:\n    def __init__(self, radius):\n        self.radius = radius\n\n    def area(self):\n        return 3.14 * self.radius ** 2");
		SnipitModel s4 = new SnipitModel(4, "JavaScript", "console.log('Hello, World!');");
		SnipitModel s5 = new SnipitModel(5, "JavaScript", "function multiply(a, b) {\n    return a * b;\n}"); 
		SnipitModel s6 = new SnipitModel(6, "JavaScript", "const square = num => num * num;");
		SnipitModel s7 = new SnipitModel(7, "Java", "public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}");
		SnipitModel s8 = new SnipitModel(8, "Java", "public class Rectangle {\n    private int width;\n    private int height;\n\n    public Rectangle(int width, int height) {\n        this.width = width;\n        this.height = height;\n    }\n\n    public int getArea() {\n        return width * height;\n    }\n}");
		  
		snipets.add(s1);
		snipets.add(s2);
		snipets.add(s3);
		snipets.add(s4);
		snipets.add(s5);
		snipets.add(s6);
		snipets.add(s7);
		snipets.add(s8);
    }

    @GetMapping("/snippets")
    public String getAll(@RequestParam(required = false, defaultValue = "all") String lang) {
        if(!lang.equals("all")) {
            ArrayList<SnipitModel> langSnips = new ArrayList<>();
            for(SnipitModel s : snipets) {
                if(s.getLanguage().equalsIgnoreCase(lang)) {
                    langSnips.add(s);
                }
            }
            String results = "";
            for(SnipitModel s : langSnips) {
                results += s.toString();
            }
            return results;    
        } else {
            String results = "";
            for(SnipitModel s : snipets) {
                results += s.toString();
            }
            return results; 
        }
        
  }

  @GetMapping("/snippets/{id}")
  public String getOneSnippet(@PathVariable int id) {
    SnipitModel snippet = snipets.get(id-1);
      return snippet.toString();
  }

  @PostMapping("/snippets")
  public String createSnippet(@RequestBody SnipitModel snipit) {
      
    int id = snipets.size() + 1;
    String language = snipit.getLanguage();
    String code = snipit.getCode();

    snipets.add(new SnipitModel(id, language, code));
      
      return snipets.toString();
  }
  

  @GetMapping("/snippets/lang")
  public String getLangs(@RequestParam("lang") String param) {
      ArrayList<SnipitModel> langSnips = new ArrayList<>();

        for(SnipitModel s : snipets) {
            if(s.getLanguage().equalsIgnoreCase(param)) {
                langSnips.add(s);
            }
        }
         String results = "";
        for(SnipitModel s : langSnips) {
            results += s.toString();
        }
        return results;    
  }   
}
