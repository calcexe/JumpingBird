package pl.calcexe.objects;

/**
 * Created by Rubin on 2016-03-11.
 */
public enum BirdsFiles
{
    RED("bird1.png");
    private String value;

    BirdsFiles(String color) {
        value = color;
    }

    public String getPath() {
        return value;
    }
}
