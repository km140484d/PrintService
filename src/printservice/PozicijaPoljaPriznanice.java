package printservice;

public class PozicijaPoljaPriznanice {
    private String left;
    private String top;
    private String font;
    private String labela;

    public PozicijaPoljaPriznanice(String left, String top, String font, String labela) {
        if (left == null || top == null || "".equals(left) || "".equals(top)) {
            System.out.println("Obe kordinate pozicije su obavezne!");
        }
        this.left = left;
        this.top = top;
        this.font = (font != null ? font : "4");
        this.labela = labela;
    }
    
    public PozicijaPoljaPriznanice(String left, String top, String font) {
        this(left, top, font, null);
    }

    public PozicijaPoljaPriznanice(String left, String top) {
        this(left, top, null);
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getLeft() {
        return left;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getTop() {
        return top;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getFont() {
        return font;
    }

    public void setLabela(String labela) {
        this.labela = labela;
    }

    public String getLabela() {
        return labela;
    }
}
