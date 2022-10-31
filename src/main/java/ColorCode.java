public class ColorCode {
    int colorValue;
    int frequency;
    String code;


    public ColorCode(int colorValue) {
        this.colorValue = colorValue;
        this.frequency = 0;
        this.code = null;

    }
    public ColorCode(int colorValue, int frequency) {
        this.colorValue = colorValue;
        this.frequency = frequency;
        this.code = null;
    }
    public int getColorValue() {
        return colorValue;
    }

    public void setColorValue(int colorValue) {
        this.colorValue = colorValue;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
