package priority_queue;

public class FileArrangement implements Comparable<FileArrangement> {
    private String fileName;
    private long length;

    public String getFileName() {
        return this.fileName;
    }

    public long getLength() {
        return this.length;
    }

    public String toString() {
        return "FileName : " + this.fileName + "Length : " + this.length;
    }

    public FileArrangement(String fileName, long length) {
        this.fileName = fileName;
        this.length = length;
    }

    public int compareTo(FileArrangement fi) {
        if (this.length < fi.length) {
            return -1;
        } else {
            return this.length > fi.length ? 1 : 0;
        }
    }
}

