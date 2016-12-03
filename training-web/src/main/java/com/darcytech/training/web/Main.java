package com.darcytech.training.web;

import java.time.LocalDate;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class Main {

    public static void main(String[] args) {
        foo1();
    }

    private static void foo2() {ExpressionParser parser = new SpelExpressionParser();

        // invokes 'getBytes().length'
        Expression exp = parser.parseExpression("'Hello World'.bytes.length");
        int length = (Integer) exp.getValue();
        System.out.println(length);
    }

    public static void foo1() {
        // The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", LocalDate.of(1856, 7, 9), "Serbian");

        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("name");

        EvaluationContext context = new StandardEvaluationContext(tesla);
        String name = (String) exp.getValue(context);

        System.out.println(name);

    }

    static class Inventor {

        private String name;

        private LocalDate birthday;

        private String nationality;

        public Inventor(String name, LocalDate birthday, String nationality) {
            this.name = name;
            this.birthday = birthday;
            this.nationality = nationality;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalDate getBirthday() {
            return birthday;
        }

        public void setBirthday(LocalDate birthday) {
            this.birthday = birthday;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }
    }

}
