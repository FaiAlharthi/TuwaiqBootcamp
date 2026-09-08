//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
//27 AUGUST
    System.out.println("     ");
    System.out.println("                                                             -------------------------------- Lab4 ------------------------------- ");
    System.out.println("     ");

    //creating 5 circles
    Circle circle1 = new Circle(3);
    Circle circle2 = new Circle();
    Circle circle3 = new Circle();
    Circle circle4 = new Circle();
    Circle circle5 = new Circle();
    //setting the rdius by using setter:
    circle2.setRadius(4);
    circle3.setRadius(1);
    circle4.setRadius(2.2);
    circle5.setRadius(4.1);
    System.out.println(" ( CIRCLE SHAPE )  ");
    System.out.println("     ");
    System.out.println(" Circle 1 calculations ");
    System.out.println("Area : " + circle1.calculateArea());
    System.out.println("Circumference : " + circle1.calculateCircumference());
    System.out.println("     ");

    System.out.println(" Circle 2 calculations ");
    System.out.println("Area : " + circle2.calculateArea());
    System.out.println("Circumference : " + circle2.calculateCircumference());
    System.out.println("     ");

    System.out.println(" Circle 3 calculations ");
    System.out.println("Area : " + circle3.calculateArea());
    System.out.println("Circumference : " + circle3.calculateCircumference());
    System.out.println("     ");

    System.out.println(" Circle 4 calculations ");
    System.out.println("Area : " + circle4.calculateArea());
    System.out.println("Circumference : " + circle4.calculateCircumference());
    System.out.println("     ");

    System.out.println(" Circle 5 calculations ");
    System.out.println("Area : " + circle5.calculateArea());
    System.out.println("Circumference : " + circle5.calculateCircumference());
    System.out.println("     ");
    System.out.println(" -------------------------------------------------- ");

    System.out.println(" ( RECTANGLE SHAPE )  ");
    //creating 5 rectangles
    Rectangle rectangle1 = new Rectangle(3.2,4.1);
    Rectangle rectangle2 = new Rectangle();
    Rectangle rectangle3 = new Rectangle();
    Rectangle rectangle4 = new Rectangle();
    Rectangle rectangle5 = new Rectangle();
    //setting the height and width by using setter:
    rectangle2.setWidth(4);
    rectangle2.setHeight(1.2);
    rectangle3.setWidth(2);
    rectangle3.setHeight(2.2);
    rectangle4.setWidth(5);
    rectangle4.setHeight(3.1);
    rectangle5.setWidth(5);
    rectangle5.setHeight(6);
    System.out.println("     ");

    System.out.println(" Rectangle 1 calculations ");
    System.out.println("Area : " + rectangle1.calculateArea());
    System.out.println("Circumference : " + rectangle1.calculateCircumference());
    System.out.println("     ");
    System.out.println(" Rectangle 2 calculations ");
    System.out.println("Area : " + rectangle2.calculateArea());
    System.out.println("Circumference : " + rectangle2.calculateCircumference());
    System.out.println("     ");
    System.out.println(" Rectangle 3 calculations ");
    System.out.println("Area : " + rectangle3.calculateArea());
    System.out.println("Circumference : " + rectangle3.calculateCircumference());
    System.out.println("     ");
    System.out.println(" Rectangle 4 calculations ");
    System.out.println("Area : " + rectangle4.calculateArea());
    System.out.println("Circumference : " + rectangle4.calculateCircumference());
    System.out.println("     ");
    System.out.println(" Rectangle 5 calculations ");
    System.out.println("Area : " + rectangle5.calculateArea());
    System.out.println("Circumference : " + rectangle5.calculateCircumference());
    System.out.println("     ");

    System.out.println(" -------------------------------------------------- ");
    System.out.println(" ( TRIANGLE SHAPE )  ");
    //creating 5 triangles
    Triangle triangle1 = new Triangle(2,3);
    Triangle triangle2 = new Triangle();
    Triangle triangle3 = new Triangle();
    Triangle triangle4 = new Triangle();
    Triangle triangle5 = new Triangle();
    //setting the base and height by using setter:
    triangle2.setBase(4.1);
    triangle2.setHeight(2);
    triangle3.setBase(1);
    triangle3.setHeight(6);
    triangle4.setBase(9);
    triangle4.setHeight(2.3);
    triangle5.setBase(10.2);
    triangle5.setHeight(2);
    System.out.println("     ");

    System.out.println(" Triangle 1 calculations ");
    System.out.println("Area : " + triangle1.calculateArea());
    System.out.println("Circumference : " + triangle1.calculateCircumference());
    System.out.println("     ");
    System.out.println(" Triangle 2 calculations ");
    System.out.println("Area : " + triangle2.calculateArea());
    System.out.println("Circumference : " + triangle2.calculateCircumference());
    System.out.println("     ");
    System.out.println(" Triangle 3 calculations ");
    System.out.println("Area : " + triangle3.calculateArea());
    System.out.println("Circumference : " + triangle3.calculateCircumference());
    System.out.println("     ");
    System.out.println(" Triangle 4 calculations ");
    System.out.println("Area : " + triangle4.calculateArea());
    System.out.println("Circumference : " + triangle4.calculateCircumference());
    System.out.println("     ");
    System.out.println(" Triangle 5 calculations ");
    System.out.println("Area : " + triangle5.calculateArea());
    System.out.println("Circumference : " + triangle5.calculateCircumference());
    System.out.println("     ");


}
