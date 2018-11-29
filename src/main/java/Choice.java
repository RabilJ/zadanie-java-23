public enum Choice {
    ADD(1, "Dodaj książkę"),
    SEARCH(2, "Znajdź książkę po indeksie"),
    DELETE(3, "Usuń książkę z wybranym indeksem"),
    EXIT(4, "Wyjście z programu");

     private int num;
     private String desc;

    Choice(int num, String desc) {
        this.num = num;
        this.desc = desc;
    }

    public int getNum() {
        return num;
    }


    public String getDesc() {
        return desc;
    }
}
