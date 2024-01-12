package com.snipit.snipit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Snippet {
    int id;
    String language;
    String code;

    public String toString() {
        return " \n {\n  id: "+id+",\n  lang: "+language+",\n  code:"+code+"\n }";
    }
}
