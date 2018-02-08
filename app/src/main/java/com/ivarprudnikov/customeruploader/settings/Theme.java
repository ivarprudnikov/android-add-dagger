package com.ivarprudnikov.customeruploader.settings;

public enum Theme {
    DEFAULT("Default", 0xFFFFFFFF, 0xFF333333),
    RED("Red", 0xFFf31111, 0xFFFFFFFF),
    GREEN("Green", 0xFF5fba7d, 0xFFFFFFFF);

    private final String label;
    private final int backgroundHex;
    private final int foregroundHex;

    Theme(String label, int backgroundHex, int foregroundHex){
        this.label = label;
        this.backgroundHex = backgroundHex;
        this.foregroundHex = foregroundHex;
    }

    public String getLabel(){
        return this.label;
    }

    public int getBackgroundHex(){
        return this.backgroundHex;
    }

    public int getForegroundHex(){
        return this.foregroundHex;
    }

    public static String[] getLabels(){
        Theme[] themes = Theme.values();
        String[] labels = new String[themes.length];
        for (int i = 0; i < themes.length; i++) {
            labels[i] = themes[i].label;
        }
        return labels;
    }

    public static Theme fromLabel(String label){
        for (Theme theme: Theme.values()) {
            if(theme.label.equals(label))
                return theme;
        }

        return Theme.DEFAULT;
    }
}
