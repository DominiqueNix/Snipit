package com.snipit.snipit.controller;

import org.springframework.web.bind.annotation.RestController;

import com.snipit.snipit.entity.*;
import com.snipit.snipit.payload.SnippetDTO;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;


@Controller
@RequestMapping("/snippets")
public class SnipitController {

    //holds all the snippets
    ArrayList<Snippet> snipets;

    

    //seeding data to arrayList
    public SnipitController(){
        snipets = new ArrayList<>();

		// Snippet s1 = new Snippet(1, "Python", "print('Hello, World!')");
		// Snippet s2 = new Snippet(2, "Python", "def add(a, b):\n    return a + b");
		// Snippet s3 = new Snippet(3, "Python", "class Circle:\n    def __init__(self, radius):\n        self.radius = radius\n\n    def area(self):\n        return 3.14 * self.radius ** 2");
		// Snippet s4 = new Snippet(4, "JavaScript", "console.log('Hello, World!');");
		// Snippet s5 = new Snippet(5, "JavaScript", "function multiply(a, b) {\n    return a * b;\n}"); 
		// Snippet s6 = new Snippet(6, "JavaScript", "const square = num => num * num;");
		// Snippet s7 = new Snippet(7, "Java", "public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}");
		// Snippet s8 = new Snippet(8, "Java", "public class Rectangle {\n    private int width;\n    private int height;\n\n    public Rectangle(int width, int height) {\n        this.width = width;\n        this.height = height;\n    }\n\n    public int getArea() {\n        return width * height;\n    }\n}");

        Snippet s1 = new Snippet(1, "Python", "1b14538fc6ec652e902d701f15156762f541b7be614756136231e8b579131c6881bbbcd729ce9096cb79b1d64b12cae9");
		Snippet s2 = new Snippet(2, "Python", "cfacedd247d179a3cea0b1573f3586d5e274a9473bd707a33cbca6b1f10336f2cb972c0b495a3b4b2c3615f7f2d977dd");
		Snippet s3 = new Snippet(3, "Python", "22db5b50ccca103c287b5133e92236f163af09eca30b5368db84ed093566ce52e967b02b4f3ddd8e6aa9af099b4f746c93e3171fd110840e8b276faca84b217ee242042e9759ea516a750c8161465987e13e4ebd0bb20ecb3111da7b3f98c93cbfd8957706145def8457f6b906488caa638c81889cfe45f4acf58b77a445327c7421efc6158264d9117b6b88db814902df1ed13b32518eed8832df4c5eec7cae");
		Snippet s4 = new Snippet(4, "JavaScript", "e214dc3a931b904616ab7fa23606fabe0e7c5b892c6ade029165caef7456cdb4547d5c7f7febd2c835fcdf3c847db64b");
		Snippet s5 = new Snippet(5, "JavaScript", "25a4072dbfb8e5a42c0e36972097a5cb4e06c4f3001d93eddf133f89647fc808a6672c0b5cde2611e3c842299a5c05a8216c4895f448c979881ed93599492246"); 
		Snippet s6 = new Snippet(6, "JavaScript", "f2ebb6c55bf335a8a3c43e095a2dc9e43b43e87c9f23931d795a6c9179d1a1691fa36985a2df3b9d7596e57591920402f7be074830387d2202cfe462c3f9693a");
		Snippet s7 = new Snippet(7, "Java", "6b98929a4a446e13146d7dd628079da2fe880bf1b0a41a36feaa4dddea34fa9cda41cf0732466612ac5403e29361ae41ac5449e5a6c0eb6f3a05b3fd4461d282384e9ebc88c059056ce025e59bcb6cce61a5402ccabb5992e72fda1a021c88f0317dcb5c772f2105f30061dfcb0df3ca5351c9125fdcc6ca652daa024799776efe1f9782370c9d12c7806c873276e119");
		Snippet s8 = new Snippet(8, "Java", "373996f37488d39a2d9329ac2785381d464c0b447929b8ea5fa5ebf45844db0e5b2e636bb999d0886bb06025ac2b42c2b43145c822be5e0f4340bbbf6b5a57f08586db53d8c7b6bdfdde289de6333d3b49c4cb4a4ddb1863d07697c109bacb8daf921ec2340ce93a64ac4d39833150ff5eefa16d6a97fa09392ee43e308de9027fa3f8647d21ea9908b619e992c3f9ef54f58ecb37eb9fd6f51a84d4015fda1bad56bdbd037e19a31beab9b56ba1670b204f0884b3aae580831b161fa95bce11f58bbec1644698112ac91b7d27978eabc727aa91eba33bb0cb0cc91a2f92c953dc309dea6d5686ccdb7ed18039b71e305af572384df148090bc3e4332f40076a8ebcb15a6bc804a11b4584563a8ad3a6");
		  
		snipets.add(s1);
		snipets.add(s2);
		snipets.add(s3);
		snipets.add(s4);
		snipets.add(s5);
		snipets.add(s6);
		snipets.add(s7);
		snipets.add(s8);
    }

    @Value("${encryption.password}")
    private String encPass;

    @Value("${encryption.salt}")
    private String encSalt; 

    //get all snippets or can get snippets based on language param
    @GetMapping
    public String getAll(@RequestParam(required = false, defaultValue = "all") String lang, Model model) {

        TextEncryptor encryptor = Encryptors.text(encPass, encSalt);

        String results = "";
        
        if(!lang.equals("all")) {
            ArrayList<Snippet> langSnips = new ArrayList<>();
            for(Snippet s : snipets) {
                if(s.getLanguage().equalsIgnoreCase(lang)) {
                    langSnips.add(s);
                }
            }
            // String results = "";
            for(Snippet s : langSnips) { 
                s.setCode(encryptor.decrypt(s.getCode()));
                results += s.toString();
                s.setCode(encryptor.encrypt(s.getCode()));
            }
            // return results;    
        } else {
            // String results = "";
            for(Snippet s : snipets) {
                s.setCode(encryptor.decrypt(s.getCode()));
                results += s.toString();
                s.setCode(encryptor.encrypt(s.getCode()));
            }
            // return results; 
        }

        model.addAttribute("snippets", results);

        return "snippets";
        
  }

  // gets one snippet based on the id
  @GetMapping("{id}")
  public String getOneSnippet(@PathVariable int id, Model model) {

    TextEncryptor encryptor = Encryptors.text(encPass, encSalt);

    Snippet snippet = snipets.get(id-1);

    snippet.setCode(encryptor.decrypt(snippet.getCode()));

    String result = snippet.toString();

    snippet.setCode(encryptor.encrypt(snippet.getCode()));

    model.addAttribute("snippets", result);

    return "snippets";
  }

  //adds a new snippet then returns all snippets
  @PostMapping
  public String createSnippet(@ModelAttribute SnippetDTO snipitDto, Model model) {

    TextEncryptor encryptor = Encryptors.text(encPass, encSalt);
      
    int id = snipets.size() + 1;
    String language = snipitDto.getLanguage();
    String code = encryptor.encrypt(snipitDto.getCode());

    snipets.add(new Snippet(id, language, code));
    model.addAttribute("snippet", snipitDto);
      
        return "redirect:/";
  }
  
}
