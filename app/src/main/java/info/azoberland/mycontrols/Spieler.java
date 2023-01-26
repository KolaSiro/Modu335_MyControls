package info.azoberland.mycontrols;

public class Spieler
{
    private String vorname;
    private String nachname;
    private String position;
    private Integer jahresVerdienst;


    public Spieler(String v, String n, String pos, Integer geld)
    {
        this.vorname = v;
        this.nachname = n;
        this.position = pos;
        this.jahresVerdienst = geld;
    }

    @Override
    public String toString()
    {
        return vorname + ", " + nachname + ", " + position;
    }

    public String getJahresVerdienst()
    {
        return Integer.toString(jahresVerdienst);
    }
}
