package guru.qa.dz.domain;

public enum MenuItem {
    MEN("Мужские аксессуары"),
    WOMEN("Женские аксессуары"),
    KIDS("Детские аксессуары"),
    TRAVEL("Путешествия")
    ;
    public final String rusName;

    MenuItem(String rusName) {
        this.rusName = rusName;
    }
}
