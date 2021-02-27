package pl.rethagos.musicplayer.model;

public class FolderPath {
    private String folderName;
    private String folderPath;

    public FolderPath(String folderName, String folderPath) {
        this.folderName = folderName;
        this.folderPath = folderPath;
    }

    @Override
    public String toString() {
        return "FolderPath{" +
                "folderName='" + folderName + '\'' +
                ", folderPath='" + folderPath + '\'' +
                '}';
    }

    public String getFolderName() {
        return folderName;
    }

    public String getFolderPath() {
        return folderPath;
    }
}
