public class Builder {
    public static InputData getinputtype(String s){

        if (s.equals("name")) {
            return new InputPlayerName();
//            inputClass.run();
        }
        else if (s.equals("attributes")) {
            return new InputPlayerAttributes();
//            inputClass.run();
        }
        else {
//            System.out.println("Invalid Response!");
            return null;
        }
    }
}
