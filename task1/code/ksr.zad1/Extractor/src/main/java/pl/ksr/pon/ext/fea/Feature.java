package pl.ksr.pon.ext.fea;

public abstract class Feature {
    protected boolean isSelected;

    public Feature(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public abstract void extract(String content);

    public boolean isSelected() {
        return isSelected;
    }

}
