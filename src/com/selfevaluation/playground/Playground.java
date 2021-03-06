package com.selfevaluation.playground;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Playground {

    public static void main(String[] args) {
        Dog aDog = new Dog("Max");
        // we pass the object to foo
        foo(aDog);
        // aDog variable is still pointing to the "Max" dog when foo(...) returns
         aDog.getName().equals("amit");
         aDog.getName().equals("Fifi");

         Dog nullDog = null;
         foo(nullDog);

        /*List list =  new ArrayList();
        list.add("test");
        bar(list);
        System.out.println("list.size()" +list.size());;*/

        Optional<Integer> test = Optional.empty();
        System.out.println(test.isPresent());
    }

    private static void bar(List list) {
        list.add("test1");
    }

    public static void foo(Dog d) {
        if(d==null)
        {
            d = new Dog("newDog");
            return;
        }

        d.getName().equals("Max"); // true
        // change d inside of foo() to point to a new Dog instance "Fifi"
        d.setName("Fifi");

        Dog e = d;
        e.setName("amit");
        /*d = new Dog("Fifi");
        d.getName().equals("Fifi"); // true*/
    }

    @Getter
    @Setter
    private static class Dog {
        private  String name;

        public Dog(String name)
        {
            this.name = name;
        }
    }
}
