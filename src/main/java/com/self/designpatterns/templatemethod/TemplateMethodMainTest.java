package com.self.designpatterns.templatemethod;

/**
 * @author shichen
 * @create 2018/6/27
 * @desc
 */
public class TemplateMethodMainTest {

    public static void main(String[] args) {
        Coffee coffee = new Coffee();
        Tea tea = new Tea();

        coffee.prepareRecipe();
        System.out.println("-------------------------------------------------------");
        tea.prepareRecipe();
    }

}
